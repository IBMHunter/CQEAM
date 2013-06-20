package com.sino.ams.task.service.soa.td.write;

import com.sino.ams.task.dao.soa.td.TDAssetsRetirementSynchronizeDAO;
import com.sino.ams.task.service.soa.AbstractTaskSOAService;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvType;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ErrorItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvInputItem;
import com.sino.soa.td.srv.assetretire.srv.TDImportAssetRetirmentSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import com.sino.soa.util.dto.EtsMisfaTransactionRespDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateBatchDTO;
import com.sino.soa.util.dto.SynLogDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>����: SinoEAM�й��ƶ��ʲ�����ϵͳ��̨ͬ������ҵ���������</p>
 * <p>����: ͨ��SOA����EAMϵͳ�����ı����ʲ�����ͬ����TDϵͳ</p>
 * <p>��Ȩ: �����л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ��</p>
 * <p>������: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TDAssetsRetirementWriteService extends AbstractTaskSOAService {

    private TDAssetsRetirementSynchronizeDAO retireDAO = null;

    /**
     * <p>����˵������EAMϵͳ�����ı����ʲ�����ͬ����TDϵͳ </p>
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��EAMϵͳ�����ı����ʲ�����ͬ����TDϵͳ����ʱ�����ݴ����쳣
     */
    public void writeTDAssetsRetirement() throws DataHandleException {
        Connection conn = null;
        try {
            conn = getDBConnection();
            RowSet rows = getTDCompanyList(conn);
            if (rows != null && !rows.isEmpty()) {
                int dataCount = rows.getSize();

                SynLogUtil logUtil = new SynLogUtil();
                for (int i = 0; i < dataCount; i++) {
                    Row row = rows.getRow(i);
                    String orgId = row.getStrValue("ORGANIZATION_ID");
                    int organizationId = Integer.parseInt(orgId);
                    taskExecutor = getOUTaskExecutor(conn, organizationId);
                    if (taskExecutor == null) {
                        continue;
                    }
                    initAssetsRetirementDAO(conn);
                    RowSet retiredAssets = retireDAO.getOU2RetiredAssets(organizationId);
                    if (retiredAssets == null || retiredAssets.isEmpty()) {
                        continue;
                    }
                    synchronizeRetireAssets(conn, retiredAssets, logUtil);
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            closeDBConnection(conn);
        }
    }

    /**
     * <p>����˵������ʼ������DAO��
     *
     * @param conn ���ݿ�����
     */
    private void initAssetsRetirementDAO(Connection conn) {
        if (retireDAO == null) {
            retireDAO = new TDAssetsRetirementSynchronizeDAO(taskExecutor, null, conn);
        } else {
            retireDAO.setUserAccount(taskExecutor);
        }
    }

    /**
     * <p>����˵����ͬ�������ʲ�
     *
     * @param conn          ���ݿ�����
     * @param retiredAssets �����ʲ�����
     * @param logUtil       ��־����
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void synchronizeRetireAssets(Connection conn, RowSet retiredAssets, SynLogUtil logUtil) throws DataHandleException {
        logSynStart(conn, logUtil);
        EtsMisfaUpdateBatchDTO updateBatchDTO = createMisUpdateBatch(conn, retiredAssets, logUtil);
        TDImportAssetRetirmentSrv srv = sendData2RemoteServer(conn, retiredAssets, logUtil);
        updateSynchronizeResponse(conn, srv, updateBatchDTO, logUtil);
        logSynEnd(conn, logUtil);
    }

    /**
     * <p>����˵������¼ͬ����ʼ��־
     *
     * @param conn    ���ݿ�����
     * @param logUtil ��־����
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void logSynStart(Connection conn, SynLogUtil logUtil) throws DataHandleException {
        SynLogDTO logDTO = new SynLogDTO();
        logDTO.setSynType(SrvType.SRV_TD_IMP_RETIRE);
        logDTO.setCreatedBy(taskExecutor.getUserId());
        logDTO.setSynMsg("����TD�ʲ�ͬ����ʼ��");
        logUtil.synLog(logDTO, conn);
    }

    /**
     * <p>����˵������¼ͬ��������־
     *
     * @param conn    ���ݿ�����
     * @param logUtil ��־����
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private void logSynEnd(Connection conn, SynLogUtil logUtil) throws DataHandleException {
        SynLogDTO logDTO = new SynLogDTO();
        logDTO.setSynType(SrvType.SRV_TD_IMP_RETIRE);
        logDTO.setCreatedBy(taskExecutor.getUserId());
        logDTO.setSynMsg("����TD�ʲ�ͬ��������");
        logUtil.synLog(logDTO, conn);
    }

    /**
     * <p>����˵����ͬ�������ʲ�
     *
     * @param conn          ���ݿ�����
     * @param retiredAssets �����ʲ�����
     * @param logUtil       ��־����
     * @return EtsMisfaUpdateBatchDTO ����ͬ��������
     * @throws com.sino.base.exception.DataHandleException
     *          ͬ���ʲ�����ʱ�׳����ݴ����쳣
     */
    private EtsMisfaUpdateBatchDTO createMisUpdateBatch(Connection conn, RowSet retiredAssets, SynLogUtil logUtil) throws DataHandleException {
        EtsMisfaUpdateBatchDTO updateBatchDTO = new EtsMisfaUpdateBatchDTO();
        try {
            SeqProducer sp = new SeqProducer(conn);
            String batchSeq = sp.getGUID();
            updateBatchDTO.setBatchId(batchSeq);
            updateBatchDTO.setOrganizationId(taskExecutor.getOrganizationId());
            updateBatchDTO.setCreatedBy(taskExecutor.getUserId());
            updateBatchDTO.setTransStatus(0);
            updateBatchDTO.setTransType(SrvType.SRV_TD_IMP_RETIRE);
            updateBatchDTO.setRemark("���ι�ͬ��" + retiredAssets.getSize() + "��");
            logUtil.createMisUpdateBatch(updateBatchDTO, conn);

            retireDAO.logRetireAssets(getSystemIds(retiredAssets), batchSeq);
        } catch (ContainerException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
        return updateBatchDTO;
    }

    /**
     * <p>����˵������ȡͬ�������ʲ�������SYSTEMID�ֶ�ֵ���ɵ�����
     *
     * @param retiredAssets �����ʲ�
     * @return SYSTEMID�ֶ�ֵ���ɵ�����
     * @throws com.sino.base.exception.ContainerException
     *          ������������SYSTEMID�ֶ�ʱ�׳����쳣
     */
    private String[] getSystemIds(RowSet retiredAssets) throws ContainerException {
        String[] systemIds = new String[retiredAssets.getSize()];
        for (int i = 0; i < retiredAssets.getSize(); i++) {
            Row row = retiredAssets.getRow(i);
            systemIds[i] = row.getStrValue("SYSTEMID");
        }
        return systemIds;
    }

    /**
     * <p>����˵����ͬ�������ʲ���Զ�̷�����
     *
     * @param conn          ���ݿ�����
     * @param retiredAssets �����ʲ�
     * @param logUtil       ��־��¼����
     * @return Զ��ͬ��WebService����
     * @throws com.sino.base.exception.DataHandleException
     *          �������ݳ���ʱ�׳����ݴ����쳣
     */
    private TDImportAssetRetirmentSrv sendData2RemoteServer(Connection conn, RowSet retiredAssets, SynLogUtil logUtil) throws DataHandleException {
        TDImportAssetRetirmentSrv srv = new TDImportAssetRetirmentSrv();
        try {
            EtsMisfaTransactionRespDTO rsRespDTO = logUtil.getMisfaResp(taskExecutor.getOrganizationId(), taskExecutor.getEmployeeNumber(), conn);   //���й�˾����
            List<ImportAssetRetirmentSrvInputItem> inputItemList = new ArrayList<ImportAssetRetirmentSrvInputItem>();
            for (int i = 0; i < retiredAssets.getSize(); i++) {
                Row row = retiredAssets.getRow(i);
                ImportAssetRetirmentSrvInputItem inputItem = new ImportAssetRetirmentSrvInputItem();
                inputItem.setPRIKEY(row.getStrValue("BARCODE"));
                inputItem.setBOOKTYPECODE(row.getStrValue("BOOK_TYPE_CODE"));
                inputItem.setTAGNUMBER(row.getStrValue("TAG_NUMBER"));
                inputItem.setDATERRETIRED(XMLGregorianCalendarUtil.getXMLGregorianCalendar(row.getStrValue("DATE_RRETIRED")));
//	                        inputItem.setRETIREMENTTYPECODE(row.getStrValue("REJECT_TYPE"));//NORMAL��TRANSFER ��������
                inputItem.setRETIREMENTTYPECODE("");//NORMAL��TRANSFER ��������
                inputItem.setCURRENTCOST(new BigDecimal(row.getStrValue("COST")).setScale(2, BigDecimal.ROUND_HALF_UP));
                inputItem.setRETIREMENTCOST(new BigDecimal(row.getStrValue("COST")).setScale(2, BigDecimal.ROUND_HALF_UP));
                inputItem.setCREATEDBY(rsRespDTO.getUserId());
                inputItem.setEMPLOYEENUMBER(rsRespDTO.getEmployeeNumber());
                inputItemList.add(inputItem);
            }
            srv.setSrvInputItems(inputItemList);
            srv.excute();
        } catch (ContainerException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        }
        return srv;
    }

    /**
     * <p>����˵������¼Զ��ͬ���Ľ��
     *
     * @param conn           ���ݿ�����
     * @param srv            Զ��ͬ��WebService����
     * @param updateBatchDTO ͬ��������
     * @param logUtil        ��־��¼����
     * @throws com.sino.base.exception.DataHandleException
     *          �������ݳ���ʱ�׳����ݴ����쳣
     */
    private void updateSynchronizeResponse(Connection conn,
                                           TDImportAssetRetirmentSrv srv,
                                           EtsMisfaUpdateBatchDTO updateBatchDTO,
                                           SynLogUtil logUtil) throws DataHandleException {
        SrvReturnMessage returnMessage = srv.getReturnMessage();
        if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
            updateBatchDTO.setRemark(updateBatchDTO.getRemark() + ",ȫ��ͬ���ɹ�!");
            updateBatchDTO.setErrmsg("");
            updateBatchDTO.setTransStatus(2);
            logUtil.updateMisUpdateBach(updateBatchDTO, conn);
            retireDAO.updateResponseLog(updateBatchDTO.getBatchId());
        } else {
            updateBatchDTO.setRemark(updateBatchDTO.getRemark() + ",ȫ��ͬ��ʧ��!");
            updateBatchDTO.setErrmsg(returnMessage.getErrorMessage());
            updateBatchDTO.setTransStatus(2);
            logUtil.updateMisUpdateBach(updateBatchDTO, conn);
            List<ErrorItem> errorItemList = srv.getErrorItemList();
            if (errorItemList != null && errorItemList.size() > 0) {
                retireDAO.updateErrorLog(errorItemList, updateBatchDTO.getBatchId());
            }
        }
    }
}
