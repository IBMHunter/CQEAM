package com.sino.ams.dzyh.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamItemDisposeDTO;
import com.sino.ams.dzyh.model.DzyhDisposeModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: DzyhDisposeDAO</p>
 * <p>Description:�����Զ����ɷ������DzyhDisposeDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class DzyhDisposeDAO extends AMSBaseDAO {

	/**
	 * ���ܣ����ʴ��õ���(EAM) EAM_ITEM_DISPOSE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamItemDisposeDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public DzyhDisposeDAO(SfUserDTO userAccount, EamItemDisposeDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EamItemDisposeDTO dtoPara = (EamItemDisposeDTO)dtoParameter;
		super.sqlProducer = new DzyhDisposeModel((SfUserDTO)userAccount, dtoPara);
	}

	public void createDisposeUpdate(EamItemDisposeDTO disponseDto) throws DataHandleException{
		boolean autoCommit = true;
		DataHandleException error = null;
		boolean operateResult = false;

		DzyhDisposeModel disposeModel=new DzyhDisposeModel(userAccount,null);
		try {
			autoCommit = conn.getAutoCommit();
	        conn.setAutoCommit(false);
	        int success = 0;
	        success = disposeModel.createDisposeUpdateEii(conn, disponseDto);
	        if(success > 0){
	        	operateResult = true;
	        }
		} catch (SQLException ex) {
			Logger.logError(ex);
			error = new DataHandleException(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage("DISPOSE_SAVE_SUCCESS");
				} else {
					conn.rollback();
					prodMessage("DISPOSE_SAVE_FAILURE");
					message.setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
				if(!operateResult){
					throw error;
				}
			} catch (SQLException ex) {
				Logger.logError(ex);
				throw new DataHandleException(ex);
			}
		}
	}
}