package com.sino.soa.mis.srv.assetLocComb.dao;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.config.SinoConfig;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.BImportAssetLocCombSrvInputItem;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.ErrorItem;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.ResponseItem;
import com.sino.soa.mis.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ImportVSetValueInfoSrvInputItem;
import com.sino.soa.mis.srv.assetLocComb.dto.SrvAssetLocCombDTO;
import com.sino.soa.mis.srv.assetLocComb.model.SrvAssetLocCombModel;
import com.sino.soa.mis.srv.assetLocComb.srv.ImportAssetLocCombSrv;
import com.sino.soa.mis.srv.valueinfo.srv.SBSYSYImportVSetValueInfoSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.dto.EtsMisfaTransactionRespDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateBatchDTO;
import com.sino.soa.util.dto.MisLocDTO;

//�ӿ������

/**
 * date��2011-09-16
 * user��wangzhipeng
 * function���ʲ��ص������������
 */
public class SrvAssetLocCombDAO extends AMSBaseDAO {

    private ResponseItem responseItem = null;
    private ErrorItem errorItem = null;
    private SrvReturnMessage returnMessage = null;
    //private SrvReturnMessage returnMessage1=null;

    public SrvAssetLocCombDAO(SfUserDTO userAccount, SrvAssetLocCombDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SrvAssetLocCombDTO dtoPara = (SrvAssetLocCombDTO) dtoParameter;
        super.sqlProducer = new SrvAssetLocCombModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * EAM�ص�ͬ��
     *
     * @param objectNos EO������
     * @throws QueryException
     * @throws ContainerException
     * @throws SQLException
     */
    public String synLocComb(String objectNos) throws QueryException, ContainerException, SQLException, DataHandleException {
        SrvAssetLocCombModel synLocModel = (SrvAssetLocCombModel) sqlProducer;
        List<BImportAssetLocCombSrvInputItem> inputItems = new ArrayList<BImportAssetLocCombSrvInputItem>(); //��ϵص�
        List<ImportVSetValueInfoSrvInputItem> inputItems1 = new ArrayList<ImportVSetValueInfoSrvInputItem>(); //ֵ��
        EtsMisfaUpdateBatchDTO updateBatchDTO = new EtsMisfaUpdateBatchDTO();
        SeqProducer seqProducer = new SeqProducer(conn);
        SynLogUtil synLogUtil = new SynLogUtil();
        String isRespExist = "YES";
        try {
            EtsMisfaTransactionRespDTO respDTO = synLogUtil.getMisfaResp(userAccount.getOrganizationId(), userAccount.getEmployeeNumber(), conn);
            if (respDTO == null) {
                isRespExist = "NO";
                return isRespExist;
            } else {
                SQLModel sqlModel = synLocModel.getSynLocModel(objectNos);
                SimpleQuery sq = new SimpleQuery(sqlModel, conn);
                sq.executeQuery();
                BImportAssetLocCombSrvInputItem inputItem = null;    //��ϵص㵼�����
                ImportVSetValueInfoSrvInputItem inputItem1 = null;   //ֵ��
                if (sq.hasResult()) {
                    RowSet rs = sq.getSearchResult();
                    String batchSeq = seqProducer.getGUID();
                    updateBatchDTO.setBatchId(batchSeq);
                    updateBatchDTO.setOrganizationId(userAccount.getOrganizationId());
                    updateBatchDTO.setCreatedBy(userAccount.getUserId());
                    updateBatchDTO.setTransType("SYNLOC");
                    updateBatchDTO.setTransStatus(0);
                    updateBatchDTO.setRemark("���ι�ͬ��'�ص������Ϣ'" + rs.getSize() + "��");
                    //ͬ����־ ��  ETS_MISFA_UPDATE_BATCH
//                    synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);//����ʤע�ͣ��ô�����Ҫ��¼��־������ͬһ��ͬ���������������
                    //������־ ��  ETS_FA_NEW_LOC
//                    DBOperator.updateRecord(synLocModel.getInsertSynLogModel(objectNos, batchSeq, userAccount), conn);
                    MisLocDTO locDTO = null;
                    List<MisLocDTO> list = new ArrayList<MisLocDTO>();
                    List<String> totalObjectNos = new ArrayList<String>();
                    boolean hasProcessed = false;
                    for (int i = 0; i < rs.getSize(); i++) {
                        Row row = rs.getRow(i);
                        //��ϵص�
                        inputItem = new BImportAssetLocCombSrvInputItem();
                        locDTO = new MisLocDTO();

                        locDTO.setKey(row.getStrValue("WORKORDER_OBJECT_NO"));         //���
                        locDTO.setCode(row.getStrValue("WORKORDER_OBJECT_CODE"));      //����
                        locDTO.setName(row.getStrValue("WORKORDER_OBJECT_NAME"));      //����
                        locDTO.setOrganizationId(row.getStrValue("ORGANIZATION_ID"));  //��֯ID
                        validateLoc(locDTO);
                        if (locDTO.getSegment1().length() == 0) {
                            continue;
                        }
                        hasProcessed = true;
                        inputItem.setPRIKEY(row.getStrValue("WORKORDER_OBJECT_NO"));
                        inputItem.setSEGMENT1(locDTO.getSegment1()); //��1�� ����
                        inputItem.setSEGMENT2(locDTO.getSegment2()); //  2
                        inputItem.setSEGMENT3(locDTO.getSegment3()); //  3
                        inputItem.setENABLEDFLAG("Y");
                        inputItem.setCREATEDBY(respDTO.getUserId()); //�Ƶ���ID new BigDecimal(2750)
//                        inputItem.setEMPLOYEENUMBER(respDTO.getEmployeeNumber());      //�Ƶ���Ա������
                        inputItem.setRESPONSIBILITYID((BigDecimal) respDTO.getRespId()); //����ְ��ID
                        //inputItem.setRESPONSIBILITYNAME("");//����ְ������
                        inputItems.add(inputItem);
                        list.add(locDTO);

                        //ֵ��
                        inputItem1 = new ImportVSetValueInfoSrvInputItem();
                        inputItem1.setPRIKEY(row.getStrValue("WORKORDER_OBJECT_NO")); //�ص�ͬ����Ϊ����WORKORDER_OBJECT_NO,������WORKORDER_OBJECT_CODE����ҪԬ���Ķ���
                        inputItem1.setFLEXVALUESETNAME(SinoConfig.getFlexValueSetNameMis());   //CMCC_FA_LOC_2
                        inputItem1.setVALIDATIONTYPE("I");
                        inputItem1.setFLEXVALUE(locDTO.getSegment2());            //code
                        //Ϊ��ֻ���ݵص����ĵڶ��Σ�������ȫ����
                        inputItem1.setDESCRIPTION(locDTO.getSegment2Desc());      //name
                        inputItem1.setENABLEDFLAG("Y");
                        inputItem1.setSUMMARYFLAG("N");
                        inputItem1.setPARENTFLEXVALUE(locDTO.getSegment1());     //�ص��һ��
//        	        inputItem1.setCREATEDBY(respDTO.getUserId());
                        inputItem1.setEMPLOYEENUMBER(respDTO.getEmployeeNumber());
                        inputItems1.add(inputItem1);

                        totalObjectNos.add(locDTO.getKey());//����ʤ����
                    }
                    if (!hasProcessed) {
                        return "";
                    }
                    //����ֵ��
                    SBSYSYImportVSetValueInfoSrv srv1 = new SBSYSYImportVSetValueInfoSrv();
                    srv1.setImportVSetValueInfoSrvInputItems(inputItems1);
                    srv1.excute();

                    DBOperator.updateRecord(synLocModel.getInsertSynLogModel(objectNos, batchSeq, userAccount), conn);//��д����־���ڵ���SOA����֮��

                    if (srv1.getReturnMessage().getErrorFlag().equalsIgnoreCase("Y")) { //����ֵ���ɹ�
                        //����ص���Ϣ
                        ImportAssetLocCombSrv srv2 = new ImportAssetLocCombSrv();
                        srv2.setSrvInputItems(inputItems);
                        srv2.excute();
                        if (srv2.getReturnMessage().getErrorFlag().equalsIgnoreCase("Y")) {
                            returnMessage = srv2.getReturnMessage();
                            updateBatchDTO.setBatchId(batchSeq);
                            updateBatchDTO.setOrganizationId(userAccount.getOrganizationId());
                            updateBatchDTO.setCreatedBy(userAccount.getUserId());
                            updateBatchDTO.setTransType("SYNLOC");
                            updateBatchDTO.setTransStatus(1);
                            updateBatchDTO.setRemark("���ι�ͬ��'�ص������Ϣ'" + rs.getSize() + "��,ȫ��ͬ���ɹ�!");
                            synLogUtil.updateMisUpdateBach(updateBatchDTO, conn);
                            DBOperator.updateBatchRecords(synLocModel.getUpdateSynLogModel(batchSeq, list), conn);

                            totalObjectNos.clear();//����ʤ����
                        } else {
                            returnMessage = srv2.getReturnMessage();
                            updateBatchDTO.setBatchId(batchSeq);
                            updateBatchDTO.setOrganizationId(userAccount.getOrganizationId());
                            updateBatchDTO.setCreatedBy(userAccount.getUserId());
                            updateBatchDTO.setTransType("SYNLOC");
                            updateBatchDTO.setTransStatus(2);
                            updateBatchDTO.setErrmsg("ͬ��ʧ�ܣ���������Ϣ�鿴���屨����Ϣ");
                            updateBatchDTO.setRemark("���ι�ͬ��'�ص������Ϣ'" + rs.getSize() + "��,ͬ��ʧ��,ʧ��ԭ��"+returnMessage.getErrorMessage());
                            synLogUtil.updateMisUpdateBach(updateBatchDTO, conn);
                            List<ErrorItem> errorlist = srv2.getErrorItemList();
                            if (errorlist.size() > 0) {
                                for (int i = 0; i < errorlist.size(); i++) {
                                    ErrorItem item1 = errorlist.get(i);
                                    String barCode = item1.getRECORDNUMBER();
                                    String msg = item1.getERRORMESSAGE();
                                    DBOperator.updateRecord(synLocModel.getUpdateSynErrorLogModel3(batchSeq, barCode, msg), conn);

                                    totalObjectNos.remove(barCode);
                                }
                                if(!totalObjectNos.isEmpty()){//���ͬ��ʧ�ܵļ�¼������ͬ����¼����������־��¼ȫ����¼Ϊʧ�ܣ����ٴβ�ѯʱ����ѯ������ͬ���ɹ��ļ�¼���ٴ�ͬ���ͻᱨ��.����ʤ���ӱ�����
                                    List<SQLModel> sqlList = synLocModel.getLocationSynSuccessModel(batchSeq, totalObjectNos);
                                    DBOperator.updateBatchRecords(sqlList, conn);
                                    totalObjectNos.clear();
                                }
                            } else {
                                totalObjectNos.clear();
                            }
                        }
                    } else {
                        returnMessage = srv1.getReturnMessage();
                        updateBatchDTO.setBatchId(batchSeq);
                        updateBatchDTO.setOrganizationId(userAccount.getOrganizationId());
                        updateBatchDTO.setCreatedBy(userAccount.getUserId());
                        updateBatchDTO.setTransType("SYNLOC");
                        updateBatchDTO.setTransStatus(2);
                        updateBatchDTO.setErrmsg("ͬ��ʧ�ܣ���������Ϣ�鿴���屨����Ϣ");
                        updateBatchDTO.setRemark("���ι�ͬ��'�ص������Ϣ'" + rs.getSize() + "��,����ص��Ӧ��ֵ��ʧ��,ʧ��ԭ��"+returnMessage.getErrorMessage());
                        synLogUtil.updateMisUpdateBach(updateBatchDTO, conn);
                        List<com.sino.soa.mis.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ErrorItem> errorlist1 = srv1.getErrorItemList();
                        if (errorlist1.size() > 0) {
                            for (int i = 0; i < errorlist1.size(); i++) {
                                com.sino.soa.mis.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ErrorItem item1 = errorlist1.get(i);
                                String barCode = item1.getRECORDNUMBER();
                                String msg = item1.getERRORMESSAGE();
                                DBOperator.updateRecord(synLocModel.getUpdateSynErrorLogModel3(batchSeq, barCode, msg), conn);

                                totalObjectNos.remove(barCode);
                            }
                            if(!totalObjectNos.isEmpty()){//���ͬ��ʧ�ܵļ�¼������ͬ����¼����������־��¼ȫ����¼Ϊʧ�ܣ����ٴβ�ѯʱ����ѯ������ͬ���ɹ��ļ�¼���ٴ�ͬ���ͻᱨ��.����ʤ���ӱ�����
                                List<SQLModel> sqlList = synLocModel.getLocationSynSuccessModel(batchSeq, totalObjectNos);
                                DBOperator.updateBatchRecords(sqlList, conn);
                                totalObjectNos.clear();
                            }
                        } else {
                            totalObjectNos.clear();//����ʤ����
                        }
                        //���ͬ��ʧ�ܵļ�¼������ͬ����¼����������־��¼ȫ����¼Ϊʧ�ܣ����ٴβ�ѯʱ����ѯ������ͬ���ɹ��ļ�¼���ٴ�ͬ���ͻᱨ��
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return isRespExist;
    }

    //�ָ��ַ���
    private void validateLoc(MisLocDTO locDTO) {
        String locName = locDTO.getName();
        int index = locName.indexOf(".");
        int lastIndex = locName.lastIndexOf(".");
        if (lastIndex > index && index > -1) {
            locDTO.setSegment1Desc(locName.substring(0, index));
            locDTO.setSegment2Desc(locName.substring(index + 1, lastIndex));
            locDTO.setSegment3Desc(locName.substring(lastIndex + 1));

            String code[] = StrUtil.splitStr(locDTO.getCode(), ".");     //�������
            locDTO.setSegment1(code[0]);
            locDTO.setSegment2(code[1]);
            locDTO.setSegment3(code[2]);
        }
    }

    public File getExportFile() throws DataTransException {
        SrvAssetLocCombModel modelProducer = (SrvAssetLocCombModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getPageQueryModel();
        String reportTitle = "EAMϵͳ�����ص�";
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
        custData.setReportPerson(userAccount.getUsername());
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
        fieldMap.put("LOCATION_CODE", "�ص����");
        fieldMap.put("WORKORDER_OBJECT_LOCATION", "�ص�����");
        fieldMap.put("WORKORDER_CATEGORY", "�ص����");
        fieldMap.put("CREATION_DATE", "��������");
        return fieldMap;
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


}
