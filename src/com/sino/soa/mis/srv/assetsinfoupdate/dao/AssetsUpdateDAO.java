package com.sino.soa.mis.srv.assetsinfoupdate.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.ErrorItem;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.UpdateAssetRetirmentSrvInputItem;
import com.sino.soa.mis.srv.assetsinfoupdate.dto.SrvAssetsUpdateDTO;
import com.sino.soa.mis.srv.assetsinfoupdate.dto.SrvEamSyschronizeDTO;
import com.sino.soa.mis.srv.assetsinfoupdate.model.AssetsUpdateModel;
import com.sino.soa.mis.srv.assetsinfoupdate.model.SrvAssetsUpdateModel;
import com.sino.soa.mis.srv.assetsinfoupdate.model.SynAttributeModel;
import com.sino.soa.mis.srv.assetsinfoupdate.srv.UpdateAssetRetirmentSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.dto.EtsMisfaTransactionRespDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateBatchDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateLogDTO;

/**
 * User: wangzp Date: 2011-09-26 Function:�ʲ�������Ϣ�޸�_�ӿ�ʵ����
 */
public class AssetsUpdateDAO extends AMSBaseDAO {

    private SfUserDTO sfUser = null;
    private SrvReturnMessage returnMessage = null;

    /**
     * ���ܣ��ʲ�������Ϣ�޸� ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AssetsUpdateDAO(SfUserDTO userAccount, SrvEamSyschronizeDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SrvEamSyschronizeDTO dtoPara = (SrvEamSyschronizeDTO) dtoParameter;
        super.sqlProducer = new AssetsUpdateModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ����:ͬ����ϸ��Ϣ��¼
     * @param orgnization_id
     * @param assetIds
     * @param batchId
     * @param status
     */
    public void getEtsMisfaUpdateLog(int orgnization_id, String[] assetIds, String batchId, String status) {
        try {
            if (assetIds != null && assetIds.length > 0) {
                String targetStr = "";
                int orgId = 0;
                SynLogUtil synLogUtil = new SynLogUtil();
                for (int i = 0; i < assetIds.length; i++) {
                    targetStr = assetIds[i];
                    SrvAssetsUpdateDAO assetsUpdateDao = new SrvAssetsUpdateDAO();
                    SrvAssetsUpdateDTO assetsUpdateDto;
                    assetsUpdateDto = assetsUpdateDao.getAssetsDtoBydId(targetStr, conn); // ��ȡ�ʲ��������
                    if (assetsUpdateDto.getOrganizationId() > 0) {
                        EtsMisfaTransactionRespDTO emDto = synLogUtil.getMisfaResp(userAccount.getOrganizationId(), userAccount.getEmployeeNumber(), conn); // ְ����Ϣ
                        assetsUpdateDto.setCreatedBy(emDto.getUserId().toString());
                        assetsUpdateDto.setResponsibilityId(emDto.getRespId().toString());
                        assetsUpdateDto.setEmployeeNumber(emDto.getEmployeeNumber());
                    }
                    orgId = assetsUpdateDto.getOrganizationId();
                    EtsMisfaUpdateLogDTO etsMisfaUpdateLogDto = new EtsMisfaUpdateLogDTO();
                    etsMisfaUpdateLogDto.setBatchId(batchId);
                    etsMisfaUpdateLogDto.setBarcode(assetsUpdateDto.getTagNumber());
                    etsMisfaUpdateLogDto.setAssetId(Integer.parseInt(targetStr));
                    etsMisfaUpdateLogDto.setOrganizationId(orgId);
                    etsMisfaUpdateLogDto.setNameFrom(assetsUpdateDto.getItemName());
                    etsMisfaUpdateLogDto.setNameTo(assetsUpdateDto.getAssetsDescription());
                    etsMisfaUpdateLogDto.setManufacturerFrom(assetsUpdateDto.getEammanufname());
                    etsMisfaUpdateLogDto.setManufacturerTo(assetsUpdateDto.getManufacturerName());
                    etsMisfaUpdateLogDto.setTagNumberFrom(assetsUpdateDto.getBarcode());
                    etsMisfaUpdateLogDto.setTagNumberTo(assetsUpdateDto.getTagNumber());
                    etsMisfaUpdateLogDto.setModelFrom(assetsUpdateDto.getItemSpec());
                    etsMisfaUpdateLogDto.setModelTo(assetsUpdateDto.getModelNumber());
                    etsMisfaUpdateLogDto.setUpdateType("ASSETSINFO");
                    etsMisfaUpdateLogDto.setTransStatus(Integer.parseInt(status));
                    synLogUtil.updateMisUpdateLog(etsMisfaUpdateLogDto, conn);
                }
            }

        } catch (QueryException e) {
            e.printStackTrace();
        } catch (DataHandleException e) {
            e.printStackTrace();
        } catch (CalendarException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���ܣ��ʲ�������Ϣͬ��
     * @param orgnization_id String
     * @param assetIds       String []
     * @throws QueryException
     * @throws SQLException
     * @throws DataHandleException
     * @throws CalendarException
     */
    public String syschronizeAssets(int orgnization_id, String[] assetIds) throws QueryException, SQLException, DataHandleException, CalendarException {
        List<UpdateAssetRetirmentSrvInputItem> list1 = new ArrayList<UpdateAssetRetirmentSrvInputItem>();
        List<String> totalTagNumbers = new ArrayList<String>();
        UpdateAssetRetirmentSrvInputItem inputItem = null;
        EtsMisfaUpdateBatchDTO batchDTO = null;
        SynLogUtil synLogUtil = new SynLogUtil();
        int totalCount = 0;
        int orgId = -1;
        String isRespExist = "YES";
        try {
            EtsMisfaTransactionRespDTO emDto = synLogUtil.getMisfaResp(userAccount.getOrganizationId(), userAccount.getEmployeeNumber(), conn);
            if (emDto == null) {
                isRespExist = "NO";
                return isRespExist;
            } else {
                if (assetIds != null && assetIds.length > 0) {
                    String targetStr = "";
                    UpdateAssetRetirmentSrv updateAssetSrv = new UpdateAssetRetirmentSrv();
                    for (int i = 0; i < assetIds.length; i++) {
                        inputItem = new UpdateAssetRetirmentSrvInputItem();
                        targetStr = assetIds[i];
                        SrvAssetsUpdateDAO assetsUpdateDao = new SrvAssetsUpdateDAO();
                        SrvAssetsUpdateDTO assetsUpdateDto;

                        // ����ASSET_ID��ȡ����
                        assetsUpdateDto = assetsUpdateDao.getAssetsDtoBydId(targetStr, conn);
                        if (assetsUpdateDto.getOrganizationId() > -1) {
                            assetsUpdateDto.setCreatedBy(emDto.getUserId().toString());
                            assetsUpdateDto.setResponsibilityId(emDto.getRespId().toString());
                            assetsUpdateDto.setEmployeeNumber(emDto.getEmployeeNumber());
                        }
                        if (!assetsUpdateDto.getCexId().equals("")) { // Ͷ�ʷ��ࣨ��ȡ֧�����豸���ͣ�
                            EtsMisfaTransactionRespDTO dto1 = this.getCexType(assetsUpdateDto.getCexId());
                            if (null != dto1) {
                                assetsUpdateDto.setSnCode(dto1.getSnCode());
                            }
                        } else {
                            assetsUpdateDto.setSnCode("");
                        }
                        if (!assetsUpdateDto.getOpeId().equals("")) { // ҵ��ƽ̨
                            EtsMisfaTransactionRespDTO dto1 = this.getOpe(assetsUpdateDto.getOpeId());
                            if (null != dto1) {
                                assetsUpdateDto.setOpeCode(dto1.getOpeCode());
                            }
                        } else {
                            assetsUpdateDto.setOpeCode("");
                        }
                        if (!assetsUpdateDto.getNleId().equals("")) { // ������
                            EtsMisfaTransactionRespDTO dto1 = this.getOpe(assetsUpdateDto.getNleId());
                            if (null != dto1) {
                                assetsUpdateDto.setNleCode(dto1.getOpeCode());
                            }
                        } else {
                            assetsUpdateDto.setNleCode("");
                        }
                        inputItem.setPRIKEY(assetsUpdateDto.getTagNumber());
                        inputItem.setBOOKTYPECODE(assetsUpdateDto.getBookTypeCode());
                        inputItem.setTAGNUMBER(assetsUpdateDto.getTagNumber());
                        inputItem.setDESCRIPTION(assetsUpdateDto.getItemName());
                        inputItem.setMANUFACTURERNAME(assetsUpdateDto.getEammanufname());
                        inputItem.setMODELNUMBER(assetsUpdateDto.getItemSpec());
//                        inputItem.setCREATEDBY(new BigDecimal(assetsUpdateDto.getCreatedBy()));
                        inputItem.setEMPLOYEENUMBER(assetsUpdateDto.getEmployeeNumber());
                        inputItem.setRESPONSIBILITYID(new BigDecimal(assetsUpdateDto.getResponsibilityId()));

                        inputItem.setATTRIBUTE8(assetsUpdateDto.getConstructStatus()); // �Ƿ񹲽��豸
                        inputItem.setATTRIBUTE9(assetsUpdateDto.getSnCode()); // ֧�����豸���� ---
                        inputItem.setATTRIBUTE10(assetsUpdateDto.getOpeCode()); // ҵ��ƽ̨����
                        inputItem.setATTRIBUTE11(assetsUpdateDto.getNleCode()); // �����α���
                        list1.add(inputItem);
                        totalCount++;
                        orgId = assetsUpdateDto.getOrganizationId();

                        totalTagNumbers.add(assetsUpdateDto.getTagNumber());//����ʤ����
                    }
                    updateAssetSrv.setSrvInputItems(list1);
                    updateAssetSrv.excute();

                    SrvReturnMessage srvMessage = updateAssetSrv.getReturnMessage();
                    returnMessage = updateAssetSrv.getReturnMessage();
                    if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                        batchDTO = new EtsMisfaUpdateBatchDTO();
                        batchDTO.setBatchId(this.getNextSeq(conn));
                        batchDTO.setTransType("ASSETSINFO");
//                        batchDTO.setTransStatus(2);
                        batchDTO.setTransStatus(1);//����ʤ�޸�Ϊ1����ʾ�ɹ�
                        batchDTO.setOrganizationId(orgId);
                        batchDTO.setCreatedBy(userAccount.getUserId());
                        batchDTO.setErrmsg("");
                        batchDTO.setRemark("���ι�ͬ��'�ʲ�������Ϣ'" + totalCount + "����¼��ȫ��ͬ���ɹ�");
                        synLogUtil.createMisUpdateBatch(batchDTO, conn);

                        totalTagNumbers.clear();//added by mshtang
                    } else {
                        batchDTO = new EtsMisfaUpdateBatchDTO();
                        batchDTO.setBatchId(this.getNextSeq(conn));
                        batchDTO.setTransType("ASSETSINFO");
                        batchDTO.setTransStatus(2);
                        batchDTO.setOrganizationId(orgId);
                        batchDTO.setCreatedBy(userAccount.getUserId());
                        batchDTO.setErrmsg("ͬ��ʧ�ܣ���������Ϣ�鿴���屨����Ϣ");
                        batchDTO.setRemark("���ι�ͬ��'�ʲ�������Ϣ'" + totalCount + "����¼,ͬ��ʧ�ܡ�ʧ��ԭ��"+returnMessage.getErrorMessage());
                        synLogUtil.createMisUpdateBatch(batchDTO, conn);
                    }
                    if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                        String status = "1";
                        this.getEtsMisfaUpdateLog(orgnization_id, assetIds, batchDTO.getBatchId(), status);
                    } else {
                        String status = "2";
                        List<ErrorItem> errorItemList = updateAssetSrv.getErrorItemList();
                        this.getEtsMisfaUpdateLog(orgnization_id, assetIds, batchDTO.getBatchId(), status);
                        if (errorItemList.size() > 0) {
                            for (int i = 0; i < errorItemList.size(); i++) {
                                ErrorItem item = errorItemList.get(i);
                                String barCode = item.getRECORDNUMBER();
                                String msg = item.getERRORMESSAGE();
                                synLogUtil.getUpdateMisUpdateLogModel(batchDTO.getBatchId(), msg, barCode, conn);

                                totalTagNumbers.remove(barCode);
                            }
                            if(!totalTagNumbers.isEmpty()){ //���ͬ��ʧ�ܵļ�¼������ͬ����¼����������־��¼ȫ����¼Ϊʧ�ܣ����ٴβ�ѯʱ����ѯ������ͬ���ɹ��ļ�¼���ٴ�ͬ���ͻᱨ��,����ʤ����
                                synLogUtil.writeMisFASuccessLog(batchDTO.getBatchId(), totalTagNumbers, conn);
                                totalTagNumbers.clear();
                            }
                        } else {
                            totalTagNumbers.clear();
                        }
                    }
                }
                return isRespExist;
            }
        } finally {
        }
    }

    /**
     * @param conn
     * @return
     * @throws SQLException
     */
    public String getNextSeq(Connection conn) throws SQLException {
        String nextSeq = "";
        SeqProducer seq = new SeqProducer(conn);
        nextSeq = seq.getGUID();
        return nextSeq;
    }

    /**
     * ���ܣ��õ������ʲ��˲�
     * @return
     * @throws QueryException
     */
    public List getBookTypeCode() throws QueryException {
        SrvAssetsUpdateModel assetsUpdateModel = new SrvAssetsUpdateModel((SfUserDTO) userAccount, (SrvEamSyschronizeDTO) dtoParameter);
        List bookTypeList = new ArrayList();
        SQLModel sqlModel = assetsUpdateModel.getBookTypeCode();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        if (simp.hasResult()) {
            RowSet rs = simp.getSearchResult();
            for (int i = 0; i < rs.getSize(); i++) {
                Row row = rs.getRow(i);
                try {
                    bookTypeList.add(row.getStrValue("COMPANY"));
                } catch (ContainerException e) {
                    e.printStackTrace();
                }
            }
        }
        return bookTypeList;
    }

    /**
     * ����:Ͷ�ʷ���ID ��ȡ֧�����豸����
     */
    public EtsMisfaTransactionRespDTO getCexType(String cexId)
            throws QueryException {
        EtsMisfaTransactionRespDTO dto = null;
        SynAttributeModel logModel = new SynAttributeModel();
        SQLModel sqlModel = logModel.getCexType(cexId);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(EtsMisfaTransactionRespDTO.class.getName());
        sq.executeQuery();
        if (sq.hasResult()) {
            dto = (EtsMisfaTransactionRespDTO) sq.getFirstDTO();
        }

        return dto;
    }

    /**
     * ����: ��ȡҵ��ƽ̨����,�����α���
     */
    public EtsMisfaTransactionRespDTO getOpe(String opeId)
            throws QueryException {
        EtsMisfaTransactionRespDTO dto = null;
        SynAttributeModel logModel = new SynAttributeModel();
        SQLModel sqlModel = logModel.getOpeModel(opeId);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(EtsMisfaTransactionRespDTO.class.getName());
        sq.executeQuery();
        if (sq.hasResult()) {
            dto = (EtsMisfaTransactionRespDTO) sq.getFirstDTO();
        }

        return dto;
    }

    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(SrvReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }

}
