package com.sino.ams.newasset.message.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.newasset.message.model.FreeAssetsMsgSetAndQueryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.newasset.message.dto.FreeAssetsMsgSetAndQueryDTO;

public class FreeAssetsMsgSetAndQueryDAO extends AMSBaseDAO {

	/**
	 * ���ܣ���Ϣ����� SF_MSG_CATEGORY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfMsgCategoryDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public FreeAssetsMsgSetAndQueryDAO(SfUserDTO userAccount, FreeAssetsMsgSetAndQueryDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		FreeAssetsMsgSetAndQueryDTO dtoPara = (FreeAssetsMsgSetAndQueryDTO)dtoParameter;
		super.sqlProducer = new FreeAssetsMsgSetAndQueryModel((SfUserDTO)userAccount, dtoPara);
	}

	public void createData() throws DataHandleException{
		FreeAssetsMsgSetAndQueryDTO dtoPara = (FreeAssetsMsgSetAndQueryDTO) dtoParameter;
		SeqProducer seqProducer = new SeqProducer(conn);
		//int msgCategoryId = seqProducer.getStrNextSeq("SF_MSG_CATEGORY");
		String msgCategoryId = "NEWID()";
		dtoPara.setMsgCategoryId(msgCategoryId);
		super.createData();
	}

	/**
	 * ���ܣ��������Ϣ����
	 * @return boolean
	 */
	public boolean saveMessageData() {
		boolean operateResult = false;
		try {
			FreeAssetsMsgSetAndQueryDTO dtoPara = (FreeAssetsMsgSetAndQueryDTO) dtoParameter;
			//int msgCategoryId = dtoPara.getMsgCategoryId();
			String msgCategoryId = dtoPara.getMsgCategoryId();
			//if ( msgCategoryId < 1 ) {
			if ( msgCategoryId.equals("") ) {
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
			FreeAssetsMsgSetAndQueryModel modelProducer = (FreeAssetsMsgSetAndQueryModel) sqlProducer;
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
