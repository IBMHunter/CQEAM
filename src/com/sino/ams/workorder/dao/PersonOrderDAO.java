package com.sino.ams.workorder.dao;

import java.sql.Connection;
import java.util.List;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.OrderExtendModel;
import com.sino.ams.workorder.model.WorkPersonModel;
import com.sino.base.constant.db.DBActionConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.DataUniqueChecker;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.ValidateException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * User: zhoujs
 * Date: 2008-1-9
 * Time: 11:39:27
 * Function:���˹����嵥ǩ�ա�ǩ�ա����·���ִ����
 */
public class PersonOrderDAO extends AMSBaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�����(EAM) ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsRentDeadlineDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public PersonOrderDAO(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsWorkorderDTO dtoPara = (EtsWorkorderDTO) dtoParameter;
        super.sqlProducer = new WorkPersonModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�����������ݡ�
     * @return boolean
     */
    public void createData() {
        boolean operateResult = false;
        try {
            String tableName = "";
            DataUniqueChecker datChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
            datChecker.setDBAction(DBActionConstant.INSERT);
            datChecker.setServletConfig(servletConfig);
            boolean isValid = datChecker.isDataValid();
            if (!isValid) {
                prodMessage(MsgKeyConstant.UNIQUE_ERROR);
                getMessage().addParameterValue("");
            } else {
                super.createData();
                operateResult = true;
                getMessage().addParameterValue("");
            }
        } catch (ValidateException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        } catch (DataHandleException ex) {
        }
//        return operateResult;
    }

    /**
     * ���ܣ�����(EAM)�������ݡ�
     * @return boolean
     */
    public void updateData() throws DataHandleException {
//		boolean operateResult = super.updateData();
        super.updateData();
        getMessage().addParameterValue("");
//		return operateResult;
    }

    /**
     * ���ܣ�ɾ�������ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("");
    }


    /**
     * ǩ�չ���
     * @param workorders
     * @return
     * @throws DataHandleException
     */
    public boolean signOrders(String[] workorders) throws DataHandleException {
        boolean operateResult = true;
        OrderExtendModel orderExtendModel=new OrderExtendModel();
        List sqlModelList=orderExtendModel.getSignOrdersModel(workorders,userAccount);
        operateResult= DBOperator.updateBatchRecords(sqlModelList,conn);
        return operateResult;
    }

}
