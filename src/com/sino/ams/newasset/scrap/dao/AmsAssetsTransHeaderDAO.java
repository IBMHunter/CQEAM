package com.sino.ams.newasset.scrap.dao;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.constant.*;
import com.sino.ams.newasset.dao.AmsAssetsChkLogDAO;
import com.sino.ams.newasset.dao.AmsAssetsReservedDAO;
import com.sino.ams.newasset.dao.AmsAssetsTransLineDAO;
import com.sino.ams.newasset.dao.AmsItemInfoHistoryDAO;
import com.sino.ams.newasset.dto.*;
import com.sino.ams.newasset.lease.constant.LeaseAppConstant;
import com.sino.ams.newasset.scrap.constant.ScrapAppConstant;
import com.sino.ams.newasset.scrap.model.TransHeaderModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.Row;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: AmsAssetsTransHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AmsAssetsTransHeaderDAO extends AMSProcedureBaseDAO {
    private AmsAssetsTransHeaderDTO headerDTO = null;
    private TransHeaderModel transHeaderModel = null;
    private String msg = null;

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsAssetsTransHeaderDAO(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.initSQLProducer(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        transHeaderModel = new TransHeaderModel((SfUserDTO) userAccount, headerDTO);
        sqlProducer = transHeaderModel;
    }


    /**
     * ���ܣ������ʲ����ݣ������������ϣ�����
     *
     * @param lineSet DTOSet �ʲ�����
     * @return boolean
     */
    public boolean saveOrder(DTOSet lineSet) {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        String transType = dtoPara.getTransType();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            saveOrderHeader();
            saveOrderLines(lineSet);
            operateResult = processProcedure();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    if (headerDTO.getAct().equals(AssetsActionConstant.SUBMIT_ACTION)) {
                        msg = "���ϵ�(" + headerDTO.getTransNo() + ")�ύʧ��";
                    } else {
                        msg = "���ϵ�(" + headerDTO.getTransNo() + ")����ʧ��";
                    }
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_FAILURE);
                    }
                } else {
                    conn.commit();
                    if (headerDTO.getAct().equals(AssetsActionConstant.SUBMIT_ACTION)) {
                        msg = "���ϵ�(" + headerDTO.getTransNo() + ")�ύ�ɹ�";
                    } else {
                        msg = "���ϵ�(" + headerDTO.getTransNo() + ")����ɹ�";
                    }
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_SUCCESS);
                    }
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("����");
                message.setIsError(!operateResult);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    public boolean doDelete(DTOSet lineSet) {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        String transType = dtoPara.getTransType();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            deleteLines();
            deleteReserveAssets();
            saveOrderLines(lineSet);
            operateResult = processProcedure(true);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_FAILURE);
                    }
                } else {
                    conn.commit();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_SUCCESS);
                    }
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("ɾ��");
                message.setIsError(!operateResult);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ����浥������Ϣ
     *
     * @param lineSet DTOSet
     * @throws DataHandleException
     */
    private void saveOrderLines(DTOSet lineSet) throws
            DataHandleException {
        if (lineSet != null && !lineSet.isEmpty()) {
            AmsAssetsTransHeaderDTO orderDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
            AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, null, conn);
            String transferType = orderDTO.getTransferType();
            String transId = orderDTO.getTransId();
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsAssetsTransLineDTO lineData = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
                if (lineData.getBarcode().equals("")) {
                    continue;
                }
                lineData.setTransId(transId);
                lineData.setLineStatus(orderDTO.getTransStatus());
                if (lineData.getOldResponsibilityDept().equals("")) {
                    lineData.setOldResponsibilityDept(StrUtil.nullToString(orderDTO.getFromDept()));
                }
                if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) { //�����ڵ���
                    lineData.setResponsibilityDept(StrUtil.nullToString(orderDTO.getFromDept()));
                }
                lineDAO.setDTOParameter(lineData);
                lineDAO.createData();
                createReserveAssets(lineData.getBarcode()); //�����ʲ�
            }
        }
    }

    /**
     * ���ܣ�ɾ�����ݵ�����Ϣ
     *
     * @throws DataHandleException
     */
    private void deleteLines() throws DataHandleException {
        AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        lineDTO.setTransId(headerDTO.getTransId());
        AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, lineDTO, conn);
        lineDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ܣ�ɾ�������ݱ������ʲ�
     *
     * @throws DataHandleException
     */
    private void deleteReserveAssets() throws DataHandleException {
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        reserveDTO.setTransId(headerDTO.getTransId());
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount, reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ܣ������ʲ�
     *
     * @param batrcode String
     * @throws DataHandleException
     */
    private void createReserveAssets(String batrcode) throws DataHandleException {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        reserveDTO.setTransId(headerDTO.getTransId());
        reserveDTO.setBarcode(batrcode);
        reserveDTO.setCurrCalendar("reservedDate");
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount, reserveDTO, conn);
        reserveDAO.createData();
    }

    /**
     * ���ܣ������ݴ�ĵ���
     *
     * @return boolean
     */
    public boolean cancelOrders() {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            cancelOrderHeader();
            cancelOrderLines();
            deleteReservedAssets();
            operateResult = super.cancelProcedure();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.ORDER_CANCEL_SUCCESS);
                } else {
                    conn.rollback();
                    prodMessage(AssetsMessageKeys.ORDER_CANCEL_FAILURE);
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue(headerDTO.getTransTypeValue());
                message.setIsError(!operateResult);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ���������ͷ
     *
     * @throws DataHandleException
     */
    private void cancelOrderHeader() throws DataHandleException {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        TransHeaderModel modelProducer = (TransHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getOrderCancelModel(headerDTO.getTransId()); //��������
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�����������
     *
     * @throws DataHandleException
     */
    private void cancelOrderLines() throws DataHandleException {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
        lineDTO.setTransId(headerDTO.getTransId());
        AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, lineDTO, conn);
        lineDAO.cancelLinesByHeader();

    }

    /**
     * ���ܣ����������ʲ�
     *
     * @throws DataHandleException
     */
    private void deleteReservedAssets() throws DataHandleException {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        reserveDTO.setTransId(headerDTO.getTransId()); //ɾ����������
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount, reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }


    public File exportFile() throws DataTransException { //����ģ��
        File file = null;
        DataTransfer transfer = null;
        TransHeaderModel modelProducer = (TransHeaderModel)
                sqlProducer;
        SQLModel sqlModel = modelProducer.getOrderModel();
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setCalPattern(CalendarConstant.LINE_PATTERN);
        rule.setSourceConn(conn);

        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)
                dtoParameter;
        String transferType = headerDTO.getTransferType();
        String fileName = "";
        if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) { //�����ڵ���
            fileName = "�����ڵ���.xls";
        } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)) { //�����ڵ���
            fileName = "���ż����.xls";
        } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) { //���м����
            fileName = "���м����.xls";
        }

        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);

        Map fieldMap = new HashMap();
        fieldMap.put("MB1", "�ʲ���ǩ");
        fieldMap.put("MB2", "�ʲ����");
        fieldMap.put("MB3", "�ʲ�����");
        fieldMap.put("MB4", "�ʲ��ͺ�");
        fieldMap.put("MB5", "����");
        if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) {
            fieldMap.put("MB6", "�����ص�NO");
            fieldMap.put("MB7", "�����ص�");
            fieldMap.put("MB8", "ԭ������Ա��ID");
            fieldMap.put("MB9", "ԭ������");
            fieldMap.put("MB10", "����ص�NO");
            fieldMap.put("MB11", "����ص�");
            fieldMap.put("MB12", "��������Ա��ID");
            fieldMap.put("MB13", "��������");
            fieldMap.put("MB14", "��������");
            fieldMap.put("MB15", "ժҪ");
        } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)) {
            fieldMap.put("MB6", "�����ص�NO");
            fieldMap.put("MB7", "�����ص�");
            fieldMap.put("MB8", "ԭ������Ա��ID");
            fieldMap.put("MB9", "ԭ������");
            fieldMap.put("MB10", "���벿�Ŵ���");
            fieldMap.put("MB11", "���벿��");
            fieldMap.put("MB12", "����ص�N0");
            fieldMap.put("MB13", "����ص�");
            fieldMap.put("MB14", "��������Ա��ID");
            fieldMap.put("MB15", "��������");
            fieldMap.put("MB16", "��������");
            fieldMap.put("MB17", "��ע");
        } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) {
            fieldMap.put("MB6", "ԭֵ");
            fieldMap.put("MB7", "�ۼ��۾�");
            fieldMap.put("MB8", "��ֵ");
            fieldMap.put("MB9", "��������");
            fieldMap.put("MB10", "�������Ŵ���");
            fieldMap.put("MB11", "��������");
            fieldMap.put("MB12", "�����ص�NO");
            fieldMap.put("MB13", "�����ص�");
            fieldMap.put("MB14", "ԭ������Ա��ID");
            fieldMap.put("MB15", "ԭ������");
            fieldMap.put("MB16", "ԭ�۾��˻�");
            fieldMap.put("MB17", "ԭ���");
            fieldMap.put("MB18", "���벿�Ŵ���");
            fieldMap.put("MB19", "���벿��");
            fieldMap.put("MB20", "����ص�NO");
            fieldMap.put("MB21", "����ص�");
            fieldMap.put("MB22", "��������Ա��ID");
            fieldMap.put("MB23", "��������");
            fieldMap.put("MB24", "���۾��˻�");
            fieldMap.put("MB25", "�����");
            fieldMap.put("MB26", "��������");
            fieldMap.put("MB27", "��ע");
        }

        rule.setFieldMap(fieldMap);
        CustomTransData custData = new CustomTransData();
//            custData.setReportTitle(fileName);
//            custData.setReportPerson(sfUser.getUsername());
        custData.setNeedReportDate(false);
        rule.setCustData(custData);

        TransferFactory factory = new TransferFactory();
        transfer = factory.getTransfer(rule);
        transfer.transData();
        file = (File) transfer.getTransResult();
        return file;
    }

    /**
     * ���ܣ������û�����Ӧ��PID��������
     */
    public boolean isGroupFlowId() throws QueryException {
        TransHeaderModel modelProducer = (TransHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getGroupPidModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    //�ж��Ƿ����
    public boolean isFinanceGroup() throws QueryException {
        TransHeaderModel modelProducer = (TransHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsFinanceGroupModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    /**
     * Function:        �ж��û���ǰ��������Ƿ�רҵ���
     * author:         ����
     * date            2009-08-25
     * param           //fromGroupName
     *
     * @return boolean����
     * @throws QueryException
     */
    public boolean isProfessionalGroup(String fromGroup) throws QueryException {
        TransHeaderModel modelProducer = (TransHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsGroupFlowIdModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    public String findGroupFlowId() throws QueryException {
        String GroupPid = "";
        try {
            TransHeaderModel modelProducer = (TransHeaderModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getGroupPidModel();
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                Row row = simp.getFirstRow();
                GroupPid = row.getStrValue("P_FLOW_ID");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return GroupPid;
    }


    /**
     * ���Ҳ���
     *
     * @param fDept
     * @param tDept
     * @return
     * @throws QueryException
     */
    public boolean findThredDept(String fDept, String tDept) throws QueryException {
        boolean isThredDept = false;
        try {
            TransHeaderModel modelProducer = (TransHeaderModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getThredDeptModel(fDept, tDept);
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                Row row = simp.getFirstRow();
                String thredDept = row.getStrValue("IS_THRED");
//                if (thredDept.equals("2")) {
//                    isThredDept = true;
//                }
                if (!thredDept.equals("0")) {
                    isThredDept = true;
                } else {
                    isThredDept = false;
                }
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return isThredDept;
    }


    /**
     * ����ͷ��Ϣ
     *
     * @return
     * @throws DataHandleException
     */
    private boolean saveOrderHeader() throws DataHandleException {
        boolean isSuccess = false;
        try {
            String transId = headerDTO.getTransId();
            String transNo = headerDTO.getTransNo();
            headerDTO.setFromPerson(userAccount.getEmployeeNumber());
            String act = headerDTO.getAct();
            if (transNo.equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
                if (StrUtil.isEmpty(transId)) {
                    SeqProducer seq = new SeqProducer(conn);
                    transId = seq.getGUID();
                    headerDTO.setTransId(transId);
                }
                String companyCode = userAccount.getCompanyCode(); //���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ
                String transType = headerDTO.getTransType();
                OrderNumGenerator numberProducer = new OrderNumGenerator(conn, companyCode, transType);
                headerDTO.setTransNo(numberProducer.getOrderNum());
                setDTOParameter(headerDTO);
                createData();
            } else {
                updateData();
                deleteLines();
                deleteReserveAssets();
            }
            isSuccess = true;
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } finally {
            return isSuccess;
        }
    }


    private boolean saveNewOrderHeader() throws DataHandleException {
        boolean isSuccess = false;
        try {
            String transId = headerDTO.getTransId();
            String transNo = headerDTO.getTransNo();
            headerDTO.setFromPerson(userAccount.getEmployeeNumber());

            if (transNo.equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
                if (StrUtil.isEmpty(transId)) {
                    SeqProducer seq = new SeqProducer(conn);
                    transId = seq.getGUID();
                    headerDTO.setTransId(transId);
                }
                String companyCode = userAccount.getCompanyCode(); //���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ
                String transType = headerDTO.getTransType();
                OrderNumGenerator numberProducer = new OrderNumGenerator(conn, companyCode, transType);
                headerDTO.setTransNo(numberProducer.getOrderNum());
                setDTOParameter(headerDTO);
                createData();

            } else {
                updateData();
                deleteLines();
                deleteReserveAssets();
            }
            isSuccess = true;
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } finally {
            return isSuccess;
        }
    }

    /**
     * ����ͷ״̬
     *
     * @throws DataHandleException
     */
    public void updateHeaderStatus() throws DataHandleException {
        SQLModel sqlModel = transHeaderModel.getOrderApproveModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ������״̬
     *
     * @throws DataHandleException
     */
    public void updateLineStatus() throws DataHandleException {
        SQLModel sqlModel = transHeaderModel.getLineStatusUpdateModel(); //�����ʲ�����������״̬Ϊ������
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ������״̬
     *
     * @throws DataHandleException
     */
    public void updateAssetsDiscard() throws DataHandleException {
        SQLModel sqlModel = transHeaderModel.getAssetsDiscardModel(); //��ȡ�ʲ�����SQL
        DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ���¼�豸����һ�ν�����������籨�ϣ��������Ӿ�����Ҫ����
     * ��Ҫͬ����MIS��ʱ����ø÷�����
     *
     * @param orderType String
     * @param isExist   String
     * @throws DataHandleException
     */
    private void recordChkLog(String orderType, String isExist) throws DataHandleException {
        try {
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            AmsAssetsTransLineDTO line = new AmsAssetsTransLineDTO();
            line.setTransId(dto.getTransId());
            AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, line, conn);
            lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
            DTOSet dtos = (DTOSet) lineDAO.getDataByForeignKey("transId");
            if (dtos != null && !dtos.isEmpty()) {
                int lineCount = dtos.getSize();
                String orderUrl = "";
                AmsAssetsChkLogDTO chkLogDTO = null;
                AmsAssetsChkLogDAO chkLogDAO = new AmsAssetsChkLogDAO(userAccount, null, conn);
                for (int i = 0; i < lineCount; i++) {
                    line = (AmsAssetsTransLineDTO) dtos.getDTO(i);
                    chkLogDTO = new AmsAssetsChkLogDTO();
                    chkLogDTO.setBarcode(line.getBarcode());
                    chkLogDTO.setLastChkNo(line.getTransNo());
                    chkLogDTO.setHeaderId(line.getTransId());
                    chkLogDTO.setResponsibilityUser(line.getResponsibilityUser());
                    chkLogDTO.setResponsibilityDept(line.getDeptCode());
                    chkLogDTO.setAddressId(line.getAddressId());
                    chkLogDTO.setOrganizationId(userAccount.getOrganizationId());
                    chkLogDTO.setCreatedBy(userAccount.getUserId());
                    chkLogDTO.setOrderType(orderType);
                    chkLogDTO.setIsExist(isExist);
                    orderUrl = AssetsURLList.ASSETS_TRANS_SERVLET;
                    orderUrl += "?act=" + AssetsActionConstant.DETAIL_ACTION;
                    orderUrl += "&transId=" + line.getTransId();
                    chkLogDTO.setOrderDtlUrl(orderUrl);
                    chkLogDAO.setDTOParameter(chkLogDTO);
                    chkLogDAO.saveCheckLogData();
                }
            }
        } catch (QueryException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
    }


    /**
     * �ύ�¼�
     *
     * @param lineSet
     * @return
     */
    public boolean doSave(DTOSet lineSet) {
        String att3 = headerDTO.getSf_task_attribute3();
        if (att3.equals(ScrapAppConstant.ATT3_FILL_DATA)) { //��ݽڵ�
            return this.saveOrder(lineSet);
        }//���̽���
        else if (att3.equals(ScrapAppConstant.ATT3_APPROVING)) { //�����ڵ�
            return this.doApprove(lineSet);
        } else {
            return false;
        }
    }

    /**
     * ����
     *
     * @return
     * @throws SQLException
     */
    private boolean doApprove(DTOSet lineSet) {
        boolean isSuccess = false;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            if (headerDTO.isFlowOver()) {
                headerDTO.setTransStatus(AssetsDictConstant.COMPLETED); //DISCARDED
                this.updateHeaderStatus(); //����ͷ״̬
                this.updateLineStatus();   //������״̬
                this.updateAssetsDiscard(); //����
                deleteReserveAssets();      //ɾ������
                saveEIIHistoty(lineSet);
                this.recordChkLog(AssetsDictConstant.ASS_DIS_OTHER, AssetsDictConstant.STATUS_NO);
            } else {
                this.updateHeaderStatus();
            }
            isSuccess = super.processProcedure();
        } catch (DataHandleException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isSuccess) {
                    msg = "���ϵ�(" + headerDTO.getTransNo() + ")�ύ�ɹ�";
                    conn.commit();
                } else {
                    msg = "���ϵ�(" + headerDTO.getTransNo() + ")�ύʧ��";
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                msg = e.getMessage();
                Logger.logError(e);
            }
            return isSuccess;
        }
    }

    private void saveEIIHistoty(DTOSet orderLines) {
        if (orderLines != null && !orderLines.isEmpty()) {
            String orderURL = "/servlet/com.sino.ams.newasset.scrap.servlet.TransServlet";
            orderURL += "?act=DETAIL_ACTION";
            orderURL += "&transId=" + headerDTO.getTransId();
            AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(userAccount, null, conn);

            for (int i = 0; i < orderLines.getSize(); i++) {
                AmsAssetsTransLineDTO line = (AmsAssetsTransLineDTO) orderLines.getDTO(i);

                AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();

                historyDTO.setOrderCategory(LeaseAppConstant.ORDER_CATEGORY);
                historyDTO.setOrderNo(headerDTO.getTransNo());
                historyDTO.setOrderDtlUrl(orderURL);
                historyDTO.setRemark("�����ʲ���������");
                historyDTO.setBarcode(line.getBarcode());
                historyDTO.setCreatedBy(userAccount.getUserId());

                historyDAO.setDTOParameter(historyDTO);
                historyDAO.recordHistory();
            }
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    protected void prepareProcedureData() {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        headerDTO.setPrimaryKey(headerDTO.getTransId());
        headerDTO.setOrderNo(headerDTO.getTransNo());
        headerDTO.setOrderName(headerDTO.getTransTypeValue());
    }
}
