package com.sino.ams.spare.version.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

import com.sino.ams.spare.version.dto.AmsItemVersionDTO;
import com.sino.ams.spare.version.model.AmsItemVersionModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: AmsItemVersionDAO</p>
 * <p>Description:�����Զ����ɷ������AmsItemVersionDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsItemVersionDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ������汾�䶯�� AMS_ITEM_VERSION ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemVersionDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsItemVersionDAO(SfUserDTO userAccount, AmsItemVersionDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsItemVersionDTO dtoPara = (AmsItemVersionDTO)dtoParameter;
		super.sqlProducer = new AmsItemVersionModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ����뱸���汾�䶯���AMS_ITEM_VERSION�����ݡ�
	 * @throws DataHandleException
	 */
	public void createData() throws DataHandleException {
		boolean hasError = true;
		boolean autoCommit = false;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			super.createData();
			if (!hasSystemItem()) {
				createSystemItem();
			}
			conn.commit();
			hasError = false;
		} catch (QueryException ex) {
			ex.printLog();
			prodMessage(MsgKeyConstant.CREATE_DATA_SUCCESS);
			throw new DataHandleException(ex);
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} finally{
			try {
				if (hasError) {
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
				throw new DataHandleException(ex1);
			}
		}
		getMessage().addParameterValue("�����汾�䶯��");
	}

	/**
	 * ���ܣ����±����汾�䶯���AMS_ITEM_VERSION�����ݡ�
	 * @throws DataHandleException
	 */
	public void updateData() throws DataHandleException {
		super.updateData();
		getMessage().addParameterValue("�����汾�䶯��");
	}

	private boolean hasSystemItem() throws QueryException {
		boolean hasItem = false;
		AmsItemVersionModel modelProducer = (AmsItemVersionModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getHasItemModel();
		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
		sq.executeQuery();
		hasItem = sq.hasResult();
		return hasItem;
	}

	/**
	 * ���ܣ������µ��豸����
	 * @throws DataHandleException
	 */
	private void createSystemItem() throws DataHandleException {
		AmsItemVersionModel modelProducer = (AmsItemVersionModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getCreateSysItemModel();
		DBOperator.updateRecord(sqlModel, conn);
	}
}
