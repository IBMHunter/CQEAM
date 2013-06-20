package com.sino.nm.ams.others.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.nm.spare2.dto.AmsItemTransHDTO;
import com.sino.nm.spare2.dto.AmsItemTransLDTO;
import com.sino.nm.spare2.model.AmsItemTransHModel;
import com.sino.nm.spare2.model.AmsItemTransLModel;
import com.sino.nm.spare2.dao.AmsItemTransLDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.constant.DictConstant;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.CalendarException;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.conn.DBManager;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.flow.dto.FlowDTO;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2009-5-22
 * Time: 10:34:04
 * To change this template use File | Settings | File Templates.
 */
public class DeptItemBarcodeDAO extends AMSBaseDAO {

    private AmsItemTransHDTO amsItemTransH = null;

    /**
     * ���ܣ���������ͷ��(AMS) AMS_ITEM_TRANS_H ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsItemTransHDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public DeptItemBarcodeDAO(SfUserDTO userAccount, AmsItemTransHDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.amsItemTransH = (AmsItemTransHDTO) super.dtoParameter;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsItemTransHDTO dtoPara = (AmsItemTransHDTO) dtoParameter;
        super.sqlProducer = new AmsItemTransHModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ����뱸������ͷ��(AMS)��AMS_ITEM_TRANS_H�����ݡ�
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("��������ͷ��(AMS)");
    }

    /**
     * ���ܣ����±�������ͷ��(AMS)��AMS_ITEM_TRANS_H�����ݡ�
     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("��������ͷ��(AMS)");
    }

    /**
     * ���ܣ�ɾ����������ͷ��(AMS)��AMS_ITEM_TRANS_H�����ݡ�
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("��������ͷ��(AMS)");
    }

    /**
     * ���浥��
     * @param lineSet ������
     * @param flowDTO FlowDTO
     * @return success
     * @throws java.sql.SQLException
     */
    public boolean saveOrder(DTOSet lineSet, FlowDTO flowDTO) throws SQLException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String transId = amsItemTransH.getTransId();
            if (transId.equals("")) {
                OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), amsItemTransH.getTransType());
                String transNo;
                if (amsItemTransH.getTransType().equals(DictConstant.BJSL)) {
                    transNo = ong.getBjslOrderNo();
                } else {
                    transNo = ong.getOrderNum();
                }
                amsItemTransH.setTransNo(transNo);
                SeqProducer seq = new SeqProducer(conn);
                transId = seq.getGUID();
                amsItemTransH.setTransId(transId);
                createData();
                if (amsItemTransH.getTransType().equals(DictConstant.BJSL)) {
                    flowDTO.setApplyId(transId);
                    flowDTO.setApplyNo(amsItemTransH.getTransNo());
                    flowDTO.setActivity("9");
                    FlowAction fa = new FlowAction(conn);
                    fa.setDto(flowDTO);
                    fa.add2Flow(flowDTO.getProcName());
                }
            } else {
                updateData();
                //�����ݣ�ɾ����Ϣ
                deleteLines(transId);
            }
            saveLines(lineSet);
            conn.commit();
            prodMessage("SAVE_SUCCESS");
            success = true;
        } catch (SQLException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (DataHandleException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (QueryException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } finally {
            conn.setAutoCommit(autoCommit);
        }

        return success;
    }

    /**
     * �ύ����
     * @param lineSet ������
     * @param flowDTO FlowDTO
     * @return success
     * @throws SQLException
     */
    public boolean submitOrder(DTOSet lineSet, FlowDTO flowDTO) throws SQLException {
        boolean success = false;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String transId = amsItemTransH.getTransId();
//            amsItemTransH.setTransStatus(DictConstant.COMPLETED);
            amsItemTransH.setTransDate(CalendarUtil.getCurrDate());
            if (flowDTO == null) {
                flowDTO = new FlowDTO();
            }
//            if (flowDTO.getActId().equals("")) {
            flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_SUBMIT);
//            } else {
//                flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_RESUBMIT);
//            }
            FlowAction fa = null;

            if (transId.equals("")) {
                OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), amsItemTransH.getTransType());
                String transNo;
                if (amsItemTransH.getTransType().equals(DictConstant.BJSL)) {
                    transNo = ong.getBjslOrderNo();
                } else {
                    transNo = ong.getOrderNum();
                }
                amsItemTransH.setTransNo(transNo);
                SeqProducer seq = new SeqProducer(conn);
                transId = seq.getGUID();
                amsItemTransH.setTransId(transId);
                amsItemTransH.setToOrganizationId(userAccount.getOrganizationId());
                amsItemTransH.setFromOrganizationId(userAccount.getOrganizationId());
                createData();

                /*if (amsItemTransH.getTransType().equals(DictConstant.BJSL)){
                    fa.add2Flow(flowDTO.getProcName());
                }*/

            } else {
                updateData();
                //�����ݣ�ɾ����Ϣ
                deleteLines(transId);
            }
            saveLines(lineSet);
          if (amsItemTransH.getTransType().equals(DictConstant.TMRK)) { //�����豸���
                addToItemInfo(lineSet, "UNKNOW");
            } else if (amsItemTransH.getTransType().equals(DictConstant.TMCK)) { //�����豸����
                updateAddressIdOnWay(lineSet);
            } else if (amsItemTransH.getTransType().equals(DictConstant.FTMRK)) {//�������豸���
//                addToItemInfo(lineSet, "OTHERS");
                InvStorageIn(lineSet);
            } else if (amsItemTransH.getTransType().equals(DictConstant.FTMCK)) {//�������豸����
                InvStorageOut(lineSet);
            }
            conn.commit();
            prodMessage("SAVE_SUCCESS");
            success = true;
        } catch (SQLException e) {
            conn.rollback();
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (DataHandleException e) {
            conn.rollback();
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (CalendarException e) {
            conn.rollback();
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } finally {
            conn.setAutoCommit(autoCommit);
        }
        return success;
    }

    /**
     * ��������Ϣ�����ݱ�
     * @param lineSet ������
     */
    public void saveLines(DTOSet lineSet) throws DataHandleException {
        if (lineSet != null && !lineSet.isEmpty()) {
            AmsItemTransLDAO lineDAO = new AmsItemTransLDAO(userAccount, null, conn);
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
                lineData.setTransId(amsItemTransH.getTransId());
                lineDAO.setDTOParameter(lineData);
                lineDAO.createData();
            }
        }
    }

    public void deleteLines(String transId) throws DataHandleException {
        AmsItemTransLModel model = new AmsItemTransLModel(userAccount, null);
        DBOperator.updateRecord(model.getDeleteByTransIdModel(transId), conn);
    }

    /**
     * дETS_ITEM_INFO��
     * @param lineSet     ������
     * @param financeProp ����
     * @throws DataHandleException
     */
    private void addToItemInfo(DTOSet lineSet, String financeProp) throws DataHandleException, SQLException {
        if (lineSet != null && !lineSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call dbo.AMS_ITEM_TRANS_ADD_DEPT_ITEM_INFO(?,?,?,?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
                    cStmt.setString(1, lineData.getBarcode());
                    cStmt.setString(2, lineData.getItemCode());
                    cStmt.setInt(3, userAccount.getOrganizationId());
                    cStmt.setInt(4, userAccount.getUserId());
                    cStmt.setString(5, amsItemTransH.getToObjectNo());
                    cStmt.setString(6, financeProp);
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    /**
     * дAMS_SPARE_INFO��
     * @param lineSet ������
     * @throws DataHandleException
     */
    private void addSpareInfo(DTOSet lineSet) throws DataHandleException, SQLException {
        if (lineSet != null && !lineSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call AMS_INV_TRANS2.ADD_SPARE_INFO(?,?,?,?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
                    cStmt.setString(1, lineData.getBarcode());
                    cStmt.setString(2, lineData.getItemCode());
                    cStmt.setInt(3, lineData.getQuantity());
                    cStmt.setInt(4, userAccount.getOrganizationId());
                    cStmt.setInt(5, userAccount.getUserId());
                    cStmt.setString(6, amsItemTransH.getToObjectNo());
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    /**
     * �����������ڲֿ�Ϊ��;��
     * @param dtoSet DTOSet
     * @throws SQLException
     */
    private void updateAddressIdOnWay(DTOSet dtoSet) throws SQLException {
        if (dtoSet != null && !dtoSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call dbo.AMS_ITEM_TRANS_UPDATE_DEPT_ITEM_INFO(?,?,?,?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) dtoSet.getDTO(i);
                    cStmt.setString(1, lineData.getBarcode());
                    cStmt.setInt(2, amsItemTransH.getToOrganizationId());
                    cStmt.setInt(3, userAccount.getUserId());
                    cStmt.setString(4,lineData.getEmployeeId());
                    cStmt.setString(5,lineData.getResponsibilityDept());
                    cStmt.setString(6,lineData.getAddressId());
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    public void InvStorageIn(DTOSet lineSet) throws SQLException {
        if (lineSet != null && !lineSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call AMS_ITEM_TRANS.INV_STORAGE_IN(?,?,?,?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
//                    String batchNo =  lineData.getBatchNo().equals("")?
                    cStmt.setString(1, lineData.getBatchNo());
                    cStmt.setString(2, amsItemTransH.getToObjectNo());
                    cStmt.setInt(3, amsItemTransH.getToOrganizationId());
                    cStmt.setString(4, lineData.getItemCode());
                    cStmt.setInt(5, lineData.getQuantity());
                    cStmt.setInt(6, userAccount.getUserId());
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    public void InvStorageOut(DTOSet lineSet) throws SQLException {
        if (lineSet != null && !lineSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call AMS_ITEM_TRANS.INV_STORAGE_OUT(?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsItemTransLDTO lineData = (AmsItemTransLDTO) lineSet.getDTO(i);
                    cStmt.setString(1, lineData.getStorageId());
                    cStmt.setInt(2, lineData.getOutQuantity());
                    cStmt.setInt(3, userAccount.getUserId());
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    /**
     * �ӿ��ת��
     * @throws SQLException
     */
    public void invTrans() throws SQLException {
        CallableStatement cStmt = null;
        String sqlStr = "{call AMS_INV_TRANS2.SUB_INV_TRANS(?,?)}";
        try {
            cStmt = conn.prepareCall(sqlStr);
            cStmt.setString(1, amsItemTransH.getTransId());
            cStmt.setInt(2, userAccount.getUserId());
            cStmt.execute();
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }


    private void spareReturnSubmit() throws SQLException {
        CallableStatement cStmt = null;
        String sqlStr = "{call AMS_INV_TRANS2.SPARE_RETURN_SUBMIT(?)}";
        try {
            cStmt = conn.prepareCall(sqlStr);
            cStmt.setString(1, amsItemTransH.getTransId());
//            cStmt.setString(2, userAccount.getUserId());
            cStmt.execute();
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }
}
