package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsChkLogDTO;
import com.sino.ams.newasset.model.AmsAssetsChkLogModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsChkLogDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsChkLogDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsAssetsChkLogDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��ʲ��̵��¼ AMS_ASSETS_CHK_LOG ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsChkLogDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsChkLogDAO(SfUserDTO userAccount,
							  AmsAssetsChkLogDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsChkLogDTO dtoPara = (AmsAssetsChkLogDTO) dtoParameter;
		super.sqlProducer = new AmsAssetsChkLogModel((SfUserDTO) userAccount,
				dtoPara);
	}

	/**
	 * ���ܣ��������µ��ʲ��̵���Ϣ
	 * @throws DataHandleException
	 */
	public void saveCheckLogData() throws DataHandleException {
		try {
			AmsAssetsChkLogModel modelProducer = (AmsAssetsChkLogModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getHasDataModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				updateData();
			} else {
				createData();
			}
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}
}
