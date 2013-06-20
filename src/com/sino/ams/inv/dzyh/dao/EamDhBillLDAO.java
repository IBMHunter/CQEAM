package com.sino.ams.inv.dzyh.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.inv.dzyh.dto.EamDhBillLDTO;
import com.sino.ams.inv.dzyh.model.EamDhBillLModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.pda.inv.model.InvOperateModel;

/**
 * <p>Title: EamDhBillLDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhBillLDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ��ʿ��
 * @version 1.0
 */
public class EamDhBillLDAO extends AMSBaseDAO {

    EamDhBillLModel eamDhBillLModel = null;

    /**
     * ���ܣ���ֵ�׺�ƷĿ¼ά����(EAM) EAM_DH_BILL_L ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EamDhBillLDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EamDhBillLDAO(SfUserDTO userAccount, EamDhBillLDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        eamDhBillLModel = (EamDhBillLModel) sqlProducer;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EamDhBillLDTO dtoPara = (EamDhBillLDTO) dtoParameter;
        super.sqlProducer = new EamDhBillLModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�����ETS_ITEM_INFO���е�MAINTAIN_USER�ֶ�
     */
    public void updateData() throws DataHandleException {
        super.updateData();
    }

    /**
     * ���ܣ����ɵ��ݺ�
     */
    public String createBillNo() throws DataHandleException {
        boolean autoCommit = true;
        DataHandleException error = null;
        boolean operateResult = false;

        String transactionNo = "";

        try {
            autoCommit = conn.getAutoCommit();

            conn.setAutoCommit(false);

            InvOperateModel iom = new InvOperateModel();

            transactionNo = iom.getAssetNumber(conn, userAccount.getCompanyCode(), "INV-O", 1); //���ݱ��

            if (!("".equals(transactionNo) || transactionNo == null)) {
                operateResult = true;
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            error = new DataHandleException(ex);
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
                if (!operateResult) {
                    throw error;
                }
            } catch (SQLException ex) {
                Logger.logError(ex);
                throw new DataHandleException(ex);
            }
        }
        return transactionNo;
    }


    /**
     * ���ܣ������б���Ϣ����Ҫ��trans_id�ֶ�
     */
    public int createTransId(String transactionNo, String workorderObjectCode) throws DataHandleException {
        boolean autoCommit = true;
        DataHandleException error = null;
        boolean operateResult = false;

        int trans_id = 0;

        try {
            autoCommit = conn.getAutoCommit();

            conn.setAutoCommit(false);

            trans_id = eamDhBillLModel.createInvBillHeaderOut(
                    conn, transactionNo, workorderObjectCode,
                    "������", "��ֵ�׺�",
                    StrUtil.strToInt(String.valueOf(userAccount.getUserId())));  //��������Ϣ����Ҫ��  ..��

            if (trans_id != 0) {
                operateResult = true;
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            error = new DataHandleException(ex);
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
                if (!operateResult) {
                    throw error;
                }
            } catch (SQLException ex) {
                Logger.logError(ex);
                throw new DataHandleException(ex);
            }
        }
        return trans_id;
    }


    /* int k = 0;
    sqlStr = "BEGIN ? := AMS_ORDERNO_PKG.GET_ORDER_NO(?, ?, ?); END;";
    cst = conn.prepareCall(sqlStr);

    cst.registerOutParameter(k++, Types.INTEGER);
    cst.setString(k++, companyCode);
    cst.setString(k++, "INV-O");
    cst.setInt(k++, 4);

    cst.registerOutParameter(k++, Types.VARCHAR);
    cst.execute();


    InvOperateModel iom = new InvOperateModel();
    String transactionNo = iom.getAssetNumber(conn, userAccount.getCompanyCode(), "INV-O", 1); //���ݱ��

    int bill_id = eamDhBillLModel.createInvBillHeaderOut(
            conn, transactionNo, workorderObjectCode,
            "������", "��ֵ�׺�",
            StrUtil.strToInt( userAccount.getUserId())
            );  //��������Ϣ����Ҫ��  ..��

    */

    /**
     * ���ܣ������������ó����¼(EAM)��"EAM_ITEM_INFO"���ݡ�
     */
    public void updateDatas(String systemid, String catalogValueId2,
                            String barcode, String deptCode, String employeeId,
                            String addressId, String maintainUser, int trans_id,
                            String itemCode, String itemCategory, String itemCategory2)
            throws DataHandleException {


        boolean autoCommit = true;
        DataHandleException error = null;
        boolean operateResult = false;

        try {
            autoCommit = conn.getAutoCommit();

            conn.setAutoCommit(false);

            int success = 0;

            if (trans_id > 0) {

                success = eamDhBillLModel.createInvBillLineOut(conn, trans_id, barcode, itemCode, itemCategory, itemCategory2, deptCode, employeeId, maintainUser, addressId);
            }

            if (success > 0) {
                operateResult = true;
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            error = new DataHandleException(ex);
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
                if (!operateResult) {
                    throw error;
                }
            } catch (SQLException ex) {
                Logger.logError(ex);
                throw new DataHandleException(ex);
            }
        }
    }


    /**
     * ���ܣ�����ETS_ITEM_INFO���е�RESPONSIBILITY_DEPT�ֶΡ�
     */
    public void updateResponsibilityDeptData(String barcode, String newDept) throws DataHandleException {
        boolean operateResult = false;
        SQLModel sqlModel = null;
        sqlModel = eamDhBillLModel.getDataUpdateDeptModel(barcode, newDept);

        if (sqlModel != null && !sqlModel.isEmpty()) {
            DBOperator.updateRecord(sqlModel, conn);
            operateResult = true;
        }
    }

    /**
     * ���ܣ�����ETS_ITEM_INFO���е�RESPONSIBILITY_USER�ֶΡ�
     */
    public void updateResponsibilityUserData(String barcode, String responsibilityUser) throws DataHandleException {
        boolean operateResult = false;
        SQLModel sqlModel = null;
        sqlModel = eamDhBillLModel.getDataUpdateUserModel(barcode, responsibilityUser);
        if (sqlModel != null && !sqlModel.isEmpty()) {
            DBOperator.updateRecord(sqlModel, conn);
            operateResult = true;
        }
    }

    /**
     * ���ܣ�����ETS_ITEM_INFO���е�WORKORDER_OBJECT_NO�ֶΡ�
     */
    public void updateAddressIdData(String barcode, String addressId) throws DataHandleException {
        boolean operateResult = false;
        SQLModel sqlModel = null;
        sqlModel = eamDhBillLModel.getDataUpdateAddressIdModel(barcode, addressId);
        if (sqlModel != null && !sqlModel.isEmpty()) {
            DBOperator.updateRecord(sqlModel, conn);
            operateResult = true;
        }
    }

    /**
     * ���ܣ�����ETS_ITEM_INFO���е�LAST_LOC_CHG_DATE�ֶΣ�����ֶ����������ͣ���Ҫע�⣬�������Ĳ�����String���͵ġ�
     * @throws CalendarException
     */
    public void updateLastLocChgDateData(String barcode, String lastLocChgDate) throws DataHandleException, CalendarException {
        boolean operateResult = false;
        SQLModel sqlModel = null;
        sqlModel = eamDhBillLModel.getDataUpdateLastLocChgDateModel(barcode, lastLocChgDate);
        if (sqlModel != null && !sqlModel.isEmpty()) {
            DBOperator.updateRecord(sqlModel, conn);
            operateResult = true;
        }
    }

    /**
     * ���ܣ�����ETS_ITEM_INFO���е�MAINTAIN_USER�ֶΣ�����ֶ����������ͣ���Ҫע�⣬�������Ĳ�����String���͵ġ�
     */
    public void updateMaintainUserDateData(String barcode, String maintainUser) throws DataHandleException {
        boolean operateResult = false;
        SQLModel sqlModel = null;
        sqlModel = eamDhBillLModel.getDataUpdateMaintainUserModel(barcode, maintainUser);
        if (sqlModel != null && !sqlModel.isEmpty()) {
            DBOperator.updateRecord(sqlModel, conn);
            operateResult = true;
        }
    }
}
