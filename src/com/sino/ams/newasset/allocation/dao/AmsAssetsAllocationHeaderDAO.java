package com.sino.ams.newasset.allocation.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.allocation.model.AmsAssetsAllocationHeaderModel;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.AmsAssetsReservedDAO;
import com.sino.ams.newasset.dao.AmsItemInfoHistoryDAO;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.utilities.FlowUtil;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-3-28
 * Time: 16:04:36
 * To change this template use File | Settings | File Templates.
 */
public class AmsAssetsAllocationHeaderDAO extends AMSProcedureBaseDAO {

    public AmsAssetsAllocationHeaderDAO(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        sqlProducer = new AmsAssetsAllocationHeaderModel((SfUserDTO) userAccount, dtoPara);
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
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            saveOrderHeader();
            saveOrderLines(lineSet);
            operateResult = processProcedure(false);
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                } else {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
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

    /**
     * ���ܣ��ύ�ʲ����ݣ������������ϣ�����
     *
     * @param lineSet DTOSet �ʲ�����
     * @return boolean
     */
    public boolean submitOrder(DTOSet lineSet) {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            saveOrderHeader();
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
                    prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                } else {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("�ύ");
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
            operateResult = true;
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
     * ���ܣ����浥��ͷ��Ϣ
     *
     * @return String
     * @throws DataHandleException
     */
    private void saveOrderHeader() throws DataHandleException {
        try {
            AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
            String transNo = headerDTO.getTransNo();
            headerDTO.setFromPerson(userAccount.getEmployeeNumber());
            String act = headerDTO.getAct();
            if (transNo.equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
                String transId = headerDTO.getTransId();
                if (StrUtil.isEmpty(transId)) {
                    SeqProducer seq = new SeqProducer(conn);
                    transId = StrUtil.nullToString(seq.getGUID());
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
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        }
    }

    protected void prepareProcedureData() {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        headerDTO.setPrimaryKey(headerDTO.getTransId());
        headerDTO.setOrderNo(headerDTO.getTransNo());
        if (headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_INN_DEPT)) {
            headerDTO.setOrderName(AssetsDictConstant.PROCEDURE_TRANS_INN_DEPT);
        } else if (headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_BTW_DEPT)) {
            headerDTO.setOrderName(AssetsDictConstant.PROCEDURE_TRANS_BTW_DEPT);
        } else if (headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_INN_DEPT_RFU)) {
            headerDTO.setOrderName(AssetsDictConstant.PROCEDURE_TRANS_INN_DEPT_RFU);
        } else {
            headerDTO.setOrderName(AssetsDictConstant.PROCEDURE_TRANS_BTW_COMP);
        }
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
            AmsAssetsAllocationLineDAO lineDAO = new AmsAssetsAllocationLineDAO(userAccount, null, conn);
            String transferType = orderDTO.getTransferType();
            String transType = orderDTO.getTransType();
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsAssetsTransLineDTO lineData = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
                if (lineData.getBarcode().equals("")) {
                    continue;
                }
                lineData.setTransId(orderDTO.getTransId());
                lineData.setTransType(transType);
                lineData.setLineStatus(orderDTO.getTransStatus());
                if (lineData.getOldResponsibilityDept().equals("")) {
                    lineData.setOldResponsibilityDept(StrUtil.nullToString(orderDTO.getFromDept()));
                }
                if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) { //�����ڵ���
                    lineData.setResponsibilityDept(StrUtil.nullToString(orderDTO.getFromDept()));
                }
                lineDAO.setDTOParameter(lineData);
                if (!transferType.equals(AssetsDictConstant.TRANS_INN_DEPT_RFU)) { //�ǽ�������(������)��Ҫ�����ʲ�
                    lineDAO.createData();
                    createReserveAssets(lineData.getBarcode()); //�����ʲ�
                } else {
                    lineDAO.createDataRfu();
                }
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
        AmsAssetsAllocationLineDAO lineDAO = new AmsAssetsAllocationLineDAO(userAccount, lineDTO, conn);
        lineDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ܣ�ɾ�������ݱ������ʲ�
     *
     * @throws DataHandleException
     */
    private void deleteReserveAssets() throws DataHandleException {
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)
                dtoParameter;
        reserveDTO.setTransId(headerDTO.getTransId());
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount,
                reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ܣ������ʲ�
     *
     * @param batrcode String
     * @throws DataHandleException
     */
    private void createReserveAssets(String batrcode) throws
            DataHandleException {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)
                dtoParameter;
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        reserveDTO.setTransId(headerDTO.getTransId());
        reserveDTO.setBarcode(batrcode);
        reserveDTO.setCurrCalendar("reservedDate");
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount,
                reserveDTO, conn);
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
        AmsAssetsAllocationHeaderModel modelProducer = (AmsAssetsAllocationHeaderModel) sqlProducer;
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
        AmsAssetsAllocationLineDAO lineDAO = new AmsAssetsAllocationLineDAO(userAccount, lineDTO, conn);
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
        AmsAssetsAllocationHeaderModel modelProducer = (AmsAssetsAllocationHeaderModel) sqlProducer;
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
        custData.setNeedReportDate(false);
        rule.setCustData(custData);

        TransferFactory factory = new TransferFactory();
        transfer = factory.getTransfer(rule);
        transfer.transData();
        file = (File) transfer.getTransResult();
        return file;
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
        AmsAssetsAllocationHeaderModel modelProducer = (AmsAssetsAllocationHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsGroupFlowIdModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    public boolean findThredDept(String fDept, String tDept) throws QueryException {
        boolean isThredDept = false;
        try {
            AmsAssetsAllocationHeaderModel modelProducer = (AmsAssetsAllocationHeaderModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getThredDeptModel(fDept, tDept);
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                RowSet rs = simp.getSearchResult();
                String groupThred1 = rs.getRow(0).getStrValue("GROUP_THRED");
                String groupThred2 = rs.getRow(1).getStrValue("GROUP_THRED");
                if (groupThred1.equals(groupThred2) & StrUtil.isNotEmpty(groupThred1)) {
                    isThredDept = true;
                }
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return isThredDept;
    }

    public void logItemChgHistory(DTOSet lineSet) {
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(userAccount, null, conn);
        String orderURL = "/servlet/com.sino.ams.newasset.allocation.servlet.AmsAssetsAllocationHeaderServlet";
        orderURL += "?act=DETAIL_ACTION";
        orderURL += "&transId=" + dto.getTransId();
        for (int i = 0; i < lineSet.getSize(); i++) {
            AmsAssetsTransLineDTO lineData = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
            String barcode = lineData.getBarcode();

            AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();
            historyDTO.setOrderCategory("3");
            historyDTO.setOrderNo(dto.getTransNo());
            historyDTO.setCreatedBy(userAccount.getUserId());
            historyDTO.setOrderDtlUrl(orderURL);
            historyDTO.setBarcode(barcode);
            historyDAO.setDTOParameter(historyDTO);
            historyDAO.recordHistory();
        }
    }
    public String getDeptCode(String deptName) throws QueryException {
        	String deptCode="";
        try {
            AmsAssetsAllocationHeaderModel modelProducer = (AmsAssetsAllocationHeaderModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getDeptCode(deptName);
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                RowSet rs = simp.getSearchResult();
                deptCode = rs.getRow(0).getStrValue("DEPT_CODE");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return deptCode;
    }
}
