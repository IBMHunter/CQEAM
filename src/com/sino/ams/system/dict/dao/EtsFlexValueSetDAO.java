package com.sino.ams.system.dict.dao;


import java.sql.Connection;

import com.sino.ams.system.dict.dto.EtsFlexValueSetDTO;
import com.sino.ams.system.dict.model.EtsFlexValueSetModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.DBActionConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.util.DataUniqueChecker;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.ValidateException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EtsFlexValueSetDAO</p>
 * <p>Description:�����Զ����ɷ������EtsFlexValueSetDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class EtsFlexValueSetDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ֵ�����(AMS) ETS_FLEX_VALUE_SET ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsFlexValueSetDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsFlexValueSetDAO(SfUserDTO userAccount, EtsFlexValueSetDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EtsFlexValueSetDTO dtoPara = (EtsFlexValueSetDTO)dtoParameter;
		super.sqlProducer = new EtsFlexValueSetModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������ֵ�����(AMS)��ETS_FLEX_VALUE_SET�����ݡ�
	 * @return boolean
	 */
	public void createData() throws DataHandleException {
		 saveData(DBActionConstant.INSERT);
	}

	/**
	 * ���ܣ������ֵ�����(AMS)��ETS_FLEX_VALUE_SET�����ݡ�
	 * @return boolean
	 */
	public void updateData() throws DataHandleException {
		 saveData(DBActionConstant.UPDATE);
	}

	private boolean saveData(String dbAction) throws DataHandleException {
		boolean operateResult = false;
		try {
			String tableName = "ETS_FLEX_VALUE_SET";
			DataUniqueChecker dataChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
			dataChecker.setDBAction(dbAction);
			if (dataChecker.isDataValid()) {
				if(dbAction.equals(DBActionConstant.INSERT)){
					 super.createData();
				} else if(dbAction.equals(DBActionConstant.UPDATE)){
					super.updateData();
				}
				getMessage().addParameterValue("�ֵ����");
			} else {
				prodMessage(MsgKeyConstant.UNIQUE_ERROR);
				message.addParameterValue(dataChecker.getInValidData());
				message.setIsError(true);
			}
		} catch (ValidateException ex) {
			ex.printLog();
			prodMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
		}
		return operateResult;
	}
	/**
	 * ���ܣ�ɾ���ֵ�����(AMS)��ETS_FLEX_VALUE_SET�����ݡ�
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException {
		 super.deleteData();
		getMessage().addParameterValue("�ֵ�����(AMS)");
	}
}
