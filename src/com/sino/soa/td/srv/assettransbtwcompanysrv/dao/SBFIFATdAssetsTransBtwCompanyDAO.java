package com.sino.soa.td.srv.assettransbtwcompanysrv.dao;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.mis.srv.assettransbtwcompanysrv.model.SBFIFAAssetsTransBtwCompanyModel;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_createassettransferintercompanyssrv.createassettransferintercompanyssrv.CreateAssetTransferIntercompanysSrvInputCollection;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_createassettransferintercompanyssrv.createassettransferintercompanyssrv.CreateAssetTransferIntercompanysSrvInputItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_createassettransferintercompanyssrv.importsrvresponse.ErrorItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_createassettransferintercompanyssrv.importsrvresponse.ResponseItem;
import com.sino.soa.td.srv.assettransbtwcompanysrv.dto.SBFIFATdAssetsTransBtwCompanyDTO;
import com.sino.soa.td.srv.assettransbtwcompanysrv.model.SBFIFATdAssetsTransBtwCompanyModel;
import com.sino.soa.td.srv.assettransbtwcompanysrv.srv.SBFIFATdAssetsTransBtwCompanySrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import com.sino.soa.util.dto.EtsMisfaTransactionRespDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateBatchDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateLogDTO;

import javax.xml.datatype.DatatypeConfigurationException;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-15
 * Time: 18:37:37
 * To change this template use File | Settings | File Templates.
 */
public class SBFIFATdAssetsTransBtwCompanyDAO extends BaseDAO {
    private ResponseItem responseItem = null;
    private ErrorItem errorItem = null;
    private SrvReturnMessage returnMessage = null;
    private SfUserDTO sfUser = null;

    public SBFIFATdAssetsTransBtwCompanyDAO(SfUserDTO userAccount, SBFIFATdAssetsTransBtwCompanyDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SBFIFATdAssetsTransBtwCompanyDTO dto = (SBFIFATdAssetsTransBtwCompanyDTO) dtoParameter;
        sfUser = (SfUserDTO) userAccount;
        super.sqlProducer = new SBFIFATdAssetsTransBtwCompanyModel(sfUser, dto);
    }

    public String syschronizeAssets(String systemids) {
        SBFIFATdAssetsTransBtwCompanyModel synLocModel = (SBFIFATdAssetsTransBtwCompanyModel) sqlProducer;
        SQLModel sqlModel = synLocModel.getSynAssModel(systemids);
        EtsMisfaUpdateBatchDTO updateBatchDTO = new EtsMisfaUpdateBatchDTO();
        SeqProducer seqProducer = new SeqProducer(conn);
        SynLogUtil synLogUtil = new SynLogUtil();
        CreateAssetTransferIntercompanysSrvInputCollection collection = new CreateAssetTransferIntercompanysSrvInputCollection();
        String userRespExists = "Y";
        try {
            EtsMisfaTransactionRespDTO respDTO = synLogUtil.getMisfaResp(sfUser.getOrganizationId(), sfUser.getEmployeeNumber(), conn);
            if (respDTO == null) {
                userRespExists = "N";
            } else {
                SimpleQuery sq = new SimpleQuery(sqlModel, conn);
                sq.executeQuery();
                CreateAssetTransferIntercompanysSrvInputItem item = null;
                int count = 0;
                if (sq.hasResult()) {
                    RowSet rs = sq.getSearchResult();
                    String batchId = seqProducer.getGUID();
                    updateBatchDTO.setBatchId(batchId);
                    updateBatchDTO.setTransType("BTWTRANSCOMPANY");
//                    updateBatchDTO.setTransStatus(2); //����ʤע�ͣ�
                    updateBatchDTO.setTransStatus(0);//����ʤ��ӣ�1��ʾ����ͬ��
                    updateBatchDTO.setOrganizationId(sfUser.getOrganizationId());
                    updateBatchDTO.setCreatedBy(sfUser.getUserId());
                    updateBatchDTO.setErrmsg("");
                    updateBatchDTO.setRemark("���ι�ͬ��" + rs.getSize() + "��");
                    synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);
                    EtsMisfaUpdateLogDTO dto = null;
                    List<EtsMisfaUpdateLogDTO> list = new ArrayList<EtsMisfaUpdateLogDTO>();
                    for (int i = 0; i < rs.getSize(); i++) {
                        Row row = rs.getRow(i);
                        dto = new EtsMisfaUpdateLogDTO();
                        item = new CreateAssetTransferIntercompanysSrvInputItem();
                        item.setPRIKEY(row.getStrValue("FROM_TAG_NUMBER"));
                        item.setFROMBOOKTYPECODE(row.getStrValue("FROM_BOOK_TYPE_CODE"));
                        item.setTOBOOKTYPECODE(row.getStrValue("TO_BOOK_TYPE_CODE"));
                        item.setCOMPANYFROM(row.getStrValue("COMPANY_FROM"));
                        item.setCOMPANYTO(row.getStrValue("COMPANY_TO"));
                        item.setCONCATENATEDSEGMENTS(row.getStrValue("TO_FA_CATEGORY_CODE"));
                        item.setTAGNUMBER(row.getStrValue("FROM_TAG_NUMBER"));

                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String nowDate=formatter.format(cal.getTime());
                        item.setTRANSACTIONDATE(XMLGregorianCalendarUtil.getXMLGregorianCalendar(nowDate));

                        item.setLOCATIONCODE(row.getStrValue("TO_WORKORDER_OBJECT_CODE"));
                        item.setEXPENSEDEPRNCODE(row.getStrValue("TO_DEPRECIATION_ACCOUNT"));
                        item.setASSETUNIT(new BigDecimal(1));
                        item.setASSIGNEDNUMBER(row.getStrValue("TO_EMPLOYEE_NUMBER"));
    //                    item.setASSIGNEDTO("");
                        item.setCREATEDBY(respDTO.getUserId());
//                        item.setEMPLOYEENUMBER(respDTO.getEmployeeNumber());
    //                    item.setTAGNUMBERNEW(row.getStrValue("TO_TAG_NUMBER"));

                        collection.getCreateAssetTransferIntercompanysSrvInputItem().add(item);
                        count = count + 1;
                        dto.setAssetId(StrUtil.strToInt(row.getStrValue("ASSET_ID")));
                        dto.setTransactionNo(row.getStrValue("TRANS_NO"));
                        dto.setBarcode(row.getStrValue("FROM_TAG_NUMBER"));
                        dto.setLocationFrom(row.getStrValue("OLD_ASSETS_LOCATION"));
                        dto.setLocationTo(row.getStrValue("NEW_ASSETS_LOCATION"));
                        dto.setTagNumberFrom(row.getStrValue("FROM_TAG_NUMBER"));
                        dto.setTagNumberTo(row.getStrValue("TO_TAG_NUMBER"));
                        dto.setOwnerFrom(row.getStrValue("OLD_DEPT_CODE"));
                        dto.setOwnerTo(row.getStrValue("NEW_DEPT"));
                        list.add(dto);
                    }

                    SBFIFATdAssetsTransBtwCompanySrv srv = new SBFIFATdAssetsTransBtwCompanySrv();
                    srv.setCollection(collection);
                    srv.excute();
                    returnMessage = srv.getReturnMessage();

                    if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                        updateBatchDTO = new EtsMisfaUpdateBatchDTO();
                        updateBatchDTO.setBatchId(batchId);
                        updateBatchDTO.setTransType("BTWTRANSCOMPANY");
                        updateBatchDTO.setTransStatus(1);//����ʤ��ӣ�1��ʾͬ���ɹ�
                        updateBatchDTO.setOrganizationId(sfUser.getOrganizationId());
                        updateBatchDTO.setCreatedBy(sfUser.getUserId());
                        updateBatchDTO.setErrmsg("");
                        updateBatchDTO.setRemark("����ͬ��'TD��˾���ʲ���������'��" + count + "����¼��ȫ��ͬ���ɹ�");
                        synLogUtil.updateMisUpdateBach(updateBatchDTO, conn);
                    } else {
                        updateBatchDTO = new EtsMisfaUpdateBatchDTO();
                        updateBatchDTO.setBatchId(batchId);
                        updateBatchDTO.setTransType("BTWTRANSCOMPANY");
                        updateBatchDTO.setTransStatus(2);
                        updateBatchDTO.setOrganizationId(sfUser.getOrganizationId());
                        updateBatchDTO.setCreatedBy(sfUser.getUserId());
                        updateBatchDTO.setRemark("���ι�ͬ��'TD��˾���ʲ���������'" + count + "����¼��ͬ��ʧ�ܣ�ʧ��ԭ��:"+returnMessage.getErrorMessage());
                        updateBatchDTO.setErrmsg("ͬ��ʧ�ܣ���������Ϣ�鿴���屨����Ϣ");
                        synLogUtil.updateMisUpdateBach(updateBatchDTO, conn);
                    }
                    if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                        int status = 1;
                        this.getEtsMisfaUpdateLog(sfUser.getOrganizationId(), list, updateBatchDTO.getBatchId(), status);
                    } else {
                        int status = 2;
                        List<ErrorItem> errorItemList = srv.getErrorItemList();
                        this.getEtsMisfaUpdateLog(sfUser.getOrganizationId(), list, updateBatchDTO.getBatchId(), status);
                        if (errorItemList.size() > 0) {
                            for (int i = 0; i < errorItemList.size(); i++) {
                                ErrorItem item1 = errorItemList.get(i);
                                String barCode = item1.getRECORDNUMBER();
                                String msg = item1.getERRORMESSAGE();
                                synLogUtil.getUpdateMisUpdateLogModel(updateBatchDTO.getBatchId(), msg, barCode, conn);
                            }
                        }
                        //���ͬ��ʧ�ܵļ�¼������ͬ����¼����������־��¼ȫ����¼Ϊʧ�ܣ����ٴβ�ѯʱ����ѯ������ͬ���ɹ��ļ�¼���ٴ�ͬ���ͻᱨ��
                    }
                }
            }
        } catch (ContainerException e) {
           e.printLog();
        } catch (DatatypeConfigurationException e) {
            Logger.logError(e);
        } catch (DataHandleException e) {
            e.printLog();
        } catch (QueryException e) {
            e.printLog();
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (Exception e) {
            Logger.logError(e);
        } finally {
        }
        return userRespExists;
    }

    public ResponseItem getResponseItem() {
        return responseItem;
    }

    public void setResponseItem(ResponseItem responseItem) {
        this.responseItem = responseItem;
    }

    public ErrorItem getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(ErrorItem errorItem) {
        this.errorItem = errorItem;
    }

    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(SrvReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getNextSeq(Connection conn) throws SQLException {
        String nextSeq = "";
        SeqProducer seq = new SeqProducer(conn);
        nextSeq = seq.getGUID();
        return nextSeq;
    }

    public void getEtsMisfaUpdateLog(int orgnization_id, List<EtsMisfaUpdateLogDTO> list, String batchId, int status) {
        try {
            if (list != null && list.size() > 0) {
                SynLogUtil synLogUtil = new SynLogUtil();
                for (EtsMisfaUpdateLogDTO dto : list) {
                    dto.setUpdateType("BTWTRANSCOMPANY");
                    dto.setTransStatus(status);
                    dto.setBatchId(batchId);
                    dto.setOrganizationId(orgnization_id);
                    synLogUtil.updateMisUpdateLog(dto, conn);
                }
            }
        } catch (DataHandleException e) {
            e.printStackTrace();
        } catch (CalendarException e) {
            e.printStackTrace();
        }
    }

    public String autoSyschronizeAssets() {
        SBFIFATdAssetsTransBtwCompanyModel synLocModel = (SBFIFATdAssetsTransBtwCompanyModel) sqlProducer;
        SQLModel sqlModel = synLocModel.getAutoSynAssModel();
        EtsMisfaUpdateBatchDTO updateBatchDTO = new EtsMisfaUpdateBatchDTO();
        SeqProducer seqProducer = new SeqProducer(conn);
        SynLogUtil synLogUtil = new SynLogUtil();
        CreateAssetTransferIntercompanysSrvInputCollection collection = new CreateAssetTransferIntercompanysSrvInputCollection();
        String userRespExists = "Y";
        try {
            EtsMisfaTransactionRespDTO respDTO = synLogUtil.getMisfaResp(sfUser.getOrganizationId(), sfUser.getEmployeeNumber(), conn);
            if (respDTO == null) {
                userRespExists = "N";
            } else {
                SimpleQuery sq = new SimpleQuery(sqlModel, conn);
                sq.executeQuery();
                CreateAssetTransferIntercompanysSrvInputItem item = null;
                int count = 0;
                if (sq.hasResult()) {
                    RowSet rs = sq.getSearchResult();
                    updateBatchDTO.setBatchId(seqProducer.getGUID() );
                    updateBatchDTO.setTransType("BTWTRANSCOMPANY");
                    updateBatchDTO.setTransStatus(2);
                    updateBatchDTO.setOrganizationId(sfUser.getOrganizationId());
                    updateBatchDTO.setCreatedBy(sfUser.getUserId());
                    updateBatchDTO.setErrmsg("");
                    updateBatchDTO.setRemark("���ι�ͬ��" + rs.getSize() + "��");
                    synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);
                    EtsMisfaUpdateLogDTO dto = null;
                    List<EtsMisfaUpdateLogDTO> list = new ArrayList();
                    for (int i = 0; i < rs.getSize(); i++) {
                        Row row = rs.getRow(i);
                        dto = new EtsMisfaUpdateLogDTO();
                        item = new CreateAssetTransferIntercompanysSrvInputItem();
                        item.setPRIKEY(row.getStrValue("FROM_TAG_NUMBER"));
                        item.setFROMBOOKTYPECODE(row.getStrValue("FROM_BOOK_TYPE_CODE"));
                        item.setTOBOOKTYPECODE(row.getStrValue("TO_BOOK_TYPE_CODE"));
                        item.setCOMPANYFROM(row.getStrValue("COMPANY_FROM"));
                        item.setCOMPANYTO(row.getStrValue("COMPANY_TO"));
                        item.setCONCATENATEDSEGMENTS(row.getStrValue("TO_FA_CATEGORY_CODE"));
                        item.setTAGNUMBER(row.getStrValue("FROM_TAG_NUMBER"));

                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String nowDate=formatter.format(cal.getTime());
                        item.setTRANSACTIONDATE(XMLGregorianCalendarUtil.getXMLGregorianCalendar(nowDate));

                        item.setLOCATIONCODE(row.getStrValue("TO_WORKORDER_OBJECT_CODE"));
                        item.setEXPENSEDEPRNCODE(row.getStrValue("TO_DEPRECIATION_ACCOUNT"));
                        item.setASSETUNIT(new BigDecimal(1));
                        item.setASSIGNEDNUMBER(row.getStrValue("TO_EMPLOYEE_NUMBER"));
    //                    item.setASSIGNEDTO("");
//                        item.setCREATEDBY(respDTO.getUserId());
                        item.setEMPLOYEENUMBER(respDTO.getEmployeeNumber());
    //                    item.setTAGNUMBERNEW(row.getStrValue("TO_TAG_NUMBER"));

                        collection.getCreateAssetTransferIntercompanysSrvInputItem().add(item);
                        count = count + 1;
                        dto.setAssetId(StrUtil.strToInt(row.getStrValue("ASSET_ID")));
                        dto.setTransactionNo(row.getStrValue("TRANS_NO"));
                        dto.setBarcode(row.getStrValue("FROM_TAG_NUMBER"));
                        dto.setLocationFrom(row.getStrValue("OLD_ASSETS_LOCATION"));
                        dto.setLocationTo(row.getStrValue("NEW_ASSETS_LOCATION"));
                        dto.setTagNumberFrom(row.getStrValue("FROM_TAG_NUMBER"));
                        dto.setTagNumberTo(row.getStrValue("TO_TAG_NUMBER"));
                        dto.setOwnerFrom(row.getStrValue("OLD_DEPT_CODE"));
                        dto.setOwnerTo(row.getStrValue("NEW_DEPT"));
                        list.add(dto);
                    }

                    SBFIFATdAssetsTransBtwCompanySrv srv = new SBFIFATdAssetsTransBtwCompanySrv();
                    srv.setCollection(collection);
                    srv.excute();
                    returnMessage = srv.getReturnMessage();

                    if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                        updateBatchDTO = new EtsMisfaUpdateBatchDTO();
                        updateBatchDTO.setBatchId(this.getNextSeq(conn));
                        updateBatchDTO.setTransType("BTWTRANSCOMPANY");
                        updateBatchDTO.setTransStatus(2);
                        updateBatchDTO.setOrganizationId(sfUser.getOrganizationId());
                        updateBatchDTO.setCreatedBy(sfUser.getUserId());
                        updateBatchDTO.setErrmsg("");
                        updateBatchDTO.setRemark("����ͬ����" + count + "����¼��ȫ��ͬ���ɹ�");
                        synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);
                    } else {
                        updateBatchDTO = new EtsMisfaUpdateBatchDTO();
                        updateBatchDTO.setBatchId(this.getNextSeq(conn));
                        updateBatchDTO.setTransType("BTWTRANSCOMPANY");
                        updateBatchDTO.setTransStatus(2);
                        updateBatchDTO.setOrganizationId(sfUser.getOrganizationId());
                        updateBatchDTO.setCreatedBy(sfUser.getUserId());
                        updateBatchDTO.setRemark("���ι�ͬ��" + count + "����¼��ͬ��ʧ��");
                        updateBatchDTO.setRemark("");
                        synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);
                    }
                    if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                        int status = 1;
                        this.getEtsMisfaUpdateLog(sfUser.getOrganizationId(), list, updateBatchDTO.getBatchId(), status);
                    } else {
                        int status = 2;
                        List<ErrorItem> errorItemList = srv.getErrorItemList();
                        this.getEtsMisfaUpdateLog(sfUser.getOrganizationId(), list, updateBatchDTO.getBatchId(), status);
                        if (errorItemList.size() > 0) {
                            for (int i = 0; i < errorItemList.size(); i++) {
                                ErrorItem item1 = errorItemList.get(i);
                                String barCode = item1.getRECORDNUMBER();
                                String msg = item1.getERRORMESSAGE();
                                synLogUtil.getUpdateMisUpdateLogModel(updateBatchDTO.getBatchId(), msg, barCode, conn);
                            }
                        }
                    }
                }
            }
        } catch (ContainerException e) {
           e.printLog();
        } catch (DatatypeConfigurationException e) {
            Logger.logError(e);
        } catch (DataHandleException e) {
            e.printLog();
        } catch (QueryException e) {
            e.printLog();
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (Exception e) {
            Logger.logError(e);
        } finally {
        }
        return userRespExists;
    }
    
    
    /**
     * ��˾��(TD)�ʲ�����Excel����
     */
    public File getExportFile() throws DataTransException, SQLModelException {
    	SBFIFATdAssetsTransBtwCompanyModel synLocModel = (SBFIFATdAssetsTransBtwCompanyModel) sqlProducer;
        SQLModel sqlModel = synLocModel.getPageQueryModel();
        String reportTitle = "TDϵͳ��˾���ʲ�����";
        String fileName = reportTitle + ".xls";
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);
        rule.setFieldMap(getFieldMap());
        CustomTransData custData = new CustomTransData();
        custData.setReportTitle(reportTitle);
        custData.setReportPerson(sfUser.getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        rule.setCalPattern(CAL_PATT_50);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("TRANSFER_TYPE", "������ʽ");
        fieldMap.put("TRANS_NO", "��������");
        fieldMap.put("FROM_TAG_NUMBER", "�ʲ���ǩ(��)");
        fieldMap.put("TO_TAG_NUMBER", "�ʲ���ǩ(��)");
        fieldMap.put("NEW_ITEM_NAME", "�ʲ�����");
        fieldMap.put("NEW_ITEM_SPEC", "�ʲ��ͺ�");
        fieldMap.put("OLD_ASSETS_LOCATION", "ԭ�ʲ��ص�");
        fieldMap.put("NEW_ASSETS_LOCATION", "���ʲ��ص�");
        fieldMap.put("OLD_DEPT_NAME", "ԭ���β���");
        fieldMap.put("NEW_DEPT_NAME", "�����β���");
        fieldMap.put("OLD_USER_NAME", "ԭ������");
        fieldMap.put("NEW_USER_NAME", "��������");
        return fieldMap;
    } 
    
}