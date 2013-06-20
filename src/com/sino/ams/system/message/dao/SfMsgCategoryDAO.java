package com.sino.ams.system.message.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.message.model.SfMsgCategoryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sms.dto.SfMsgCategoryDTO;

/**
 * <p>Title: SfMsgCategoryDAO</p>
 * <p>Description:�����Զ����ɷ������SfMsgCategoryDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class SfMsgCategoryDAO extends AMSBaseDAO {


	/**
	 * ���ܣ���Ϣ����� SF_MSG_CATEGORY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfMsgCategoryDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfMsgCategoryDAO(SfUserDTO userAccount, SfMsgCategoryDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		SfMsgCategoryDTO dtoPara = (SfMsgCategoryDTO)dtoParameter;
		super.sqlProducer = new SfMsgCategoryModel((SfUserDTO)userAccount, dtoPara);
	}

	public void createData() throws DataHandleException{
		try {
			SfMsgCategoryDTO dtoPara = (SfMsgCategoryDTO) dtoParameter;
			SeqProducer seqProducer = new SeqProducer(conn);
			int msgCategoryId = seqProducer.getStrNextSeq("SF_MSG_CATEGORY");
			dtoPara.setMsgCategoryId(msgCategoryId);
			super.createData();
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ��������Ϣ����
	 * @return boolean
	 */
	public boolean saveMessageData() {
		boolean operateResult = false;
		try {
			SfMsgCategoryDTO dtoPara = (SfMsgCategoryDTO) dtoParameter;
			int msgCategoryId = dtoPara.getMsgCategoryId();
			if ( msgCategoryId < 1 ) {
				createData();
			} else {
				updateData();
			}
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if(operateResult){
				prodMessage(CustMessageKey.MESSAGE_SAVE_SUCCESS);
			} else {
				prodMessage(CustMessageKey.MESSAGE_SAVE_FAILURE);
			}
			message.setIsError(!operateResult);
		}
		return operateResult;
	}

	/**
	 * ���ܣ���ʼ������Ϣ�����
	 * @return boolean
	 */
	public boolean initMessage(){
		boolean operateResult = false;
		try {
			SfMsgCategoryModel modelProducer = (SfMsgCategoryModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getMessageInitModel();
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally {
			if(operateResult){
				prodMessage(CustMessageKey.MESSAGE_INIT_SUCCESS);
			} else {
				prodMessage(CustMessageKey.MESSAGE_INIT_FAILURE);
			}
			message.setIsError(!operateResult);
		}
		return operateResult;
	}
}
