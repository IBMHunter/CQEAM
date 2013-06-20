package com.sino.ams.system.cost.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.cost.dto.AmsMisCostMatchDTO;
import com.sino.ams.system.cost.model.AmsMisCostMatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-7-31
 * Time: 10:43:50
 * To change this template use File | Settings | File Templates.
 */
public class AmsMisCostMatchDAO extends AMSBaseDAO {


	/**
	 * ���ܣ���������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsMisCostMatchDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsMisCostMatchDAO(SfUserDTO userAccount, AmsMisCostMatchDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsMisCostMatchDTO dtoPara = (AmsMisCostMatchDTO)dtoParameter;
		super.sqlProducer = new AmsMisCostMatchModel((SfUserDTO)userAccount, dtoPara);
	}


     /**
     * ���ĳ������ƥ���??
     * @param batchId ����
     * @return success
     * @throws SQLException
     */
    public int unMatchBatch(String batchId) throws SQLException {
        int count = 0;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);

            conn.commit();
        } catch (SQLException e) {
            Logger.logError(e);
            conn.rollback();
            prodMessage("SAVE_FAILURE");
            count = -1;
        } finally {
            conn.setAutoCommit(autoCommit);
        }
        return count;
    }



  /**
     * ���ܣ����н��ƥ��Ĳ�����
     * @return boolean
     */
    public void unMatch(String[] subCheck) throws DataHandleException {          //do_save����
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
//            AmsMisLocMatchDTO dtopara = (AmsMisLocMatchDTO) dtoParameter;
            for(int i=0; i<subCheck.length;i++){
               AmsMisCostMatchModel unMatchModel =new AmsMisCostMatchModel(userAccount, null);
               DBOperator.updateRecord(unMatchModel.getDelMatchModel(subCheck[i]),conn);
            }
            conn.commit();
            hasError = false;
//            getMessage().addParameterValue("�ɱ�����");
//            prodMessage(MsgKeyConstant.DELETE_DATA_SUCCESS);
            prodMessage(AssetsMessageKeys.ORDER_CANCEL_SUCCESS);
            getMessage().addParameterValue("�ɱ����ĺͲ��Ŷ��չ�ϵ");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } finally {
            if (hasError) {
                try {
                    conn.rollback();
                    conn.setAutoCommit(autoCommit);
                } catch (SQLException ex) {
                    Logger.logError(ex);
                    prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
                }
            }
        }
    }

}
