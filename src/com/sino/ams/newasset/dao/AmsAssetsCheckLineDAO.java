package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.newasset.model.AmsAssetsCheckLineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsCheckLineDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsCheckLineDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class AmsAssetsCheckLineDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��ʲ��̵��б�--���̵��ʲ���(EAM) AMS_ASSETS_CHECK_LINE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsCheckLineDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsCheckLineDAO(SfUserDTO userAccount, AmsAssetsCheckLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsCheckLineDTO dto = (AmsAssetsCheckLineDTO) dtoParameter;
		sqlProducer = new AmsAssetsCheckLineModel((SfUserDTO) userAccount, dto);
	}


	/**
	 * ����:���ݹ�����IDɾ�����̵��ʲ���Ϣ
	 * @throws DataHandleException
	 */
//	public void deleteByBatchId() throws DataHandleException {
//		AmsAssetsCheckLineModel modelProducer = (AmsAssetsCheckLineModel) sqlProducer;
//		SQLModel sqlModel = modelProducer.getDeleteByForeignKeyModel("batchId");
//		DBOperator.updateRecord(sqlModel, conn);
//	}
}
