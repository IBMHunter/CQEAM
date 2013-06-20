package com.sino.ams.sampling.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.ams.sampling.model.AmsAssetsSamplingLineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsSamplingLineDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsSamplingLineDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsAssetsSamplingLineDAO extends AMSBaseDAO {

	/**
	 * ���ܣ���鹤���б� AMS_ASSETS_SAMPLING_LINE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsSamplingLineDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsSamplingLineDAO(SfUserDTO userAccount, AmsAssetsSamplingLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsAssetsSamplingLineDTO dto = (AmsAssetsSamplingLineDTO)dtoParameter;
		super.sqlProducer = new AmsAssetsSamplingLineModel((SfUserDTO)userAccount, dto);
	}

	/**
	 * ���ܣ������豸��
	 * @param isExist boolean
	 * @throws DataHandleException
	 */
//	public void uploadLine(boolean isExist) throws DataHandleException {
//		try {
//			AmsAssetsSamplingLineModel modelProducer = (AmsAssetsSamplingLineModel) sqlProducer;
//			SQLModel sqlModel = null;
//			if (!isExist) {
//				sqlModel = modelProducer.getUploadCreateModel();
//			} else {
//				sqlModel = modelProducer.getUploadUpdateModel();
//			}
//			DBOperator.updateRecord(sqlModel, conn);
//		} catch (SQLModelException ex) {
//			ex.printLog();
//			throw new DataHandleException(ex);
//		}
//	}
}
