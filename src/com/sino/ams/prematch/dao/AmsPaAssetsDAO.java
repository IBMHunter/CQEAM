package com.sino.ams.prematch.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.prematch.dto.AmsPaAssetsDTO;
import com.sino.ams.prematch.model.AmsPaAssetsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsPaAssetsDAO</p>
 * <p>Description:�����Զ����ɷ������AmsPaAssetsDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsPaAssetsDAO extends AMSBaseDAO {

	/**
	 * ���ܣ�MISת��׼���嵥 AMS_PA_ASSETS ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsPaAssetsDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsPaAssetsDAO(SfUserDTO userAccount, AmsPaAssetsDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsPaAssetsDTO dto = (AmsPaAssetsDTO)dtoParameter;
		super.sqlProducer = new AmsPaAssetsModel((SfUserDTO)userAccount, dto);
	}

	/**
	 * ���ܣ�����ת��׼���嵥��Excel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			AmsPaAssetsModel modelProducer = (AmsPaAssetsModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "MISת��׼���嵥";
			String fileName = reportTitle + ".xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			rule.setFieldMap(getFieldMap());
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			rule.setCalPattern(LINE_PATTERN);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}

	/**
	 * ���ܣ���ȡת��׼���嵥�����ֶ�ӳ��
	 * @return Map
	 */
	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("TAG_NUMBER", "��ǩ��");
		fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
		fieldMap.put("MODEL_NUMBER", "����ͺ�");
		fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
		fieldMap.put("PROJECT_NAME", "��Ŀ����");
		fieldMap.put("ASSETS_LOCATION_CODE", "�ص����");
		fieldMap.put("ASSETS_LOCATION", "�ص�����");
		fieldMap.put("ASSIGNED_TO_NUMBER", "Ա�����");
		fieldMap.put("ASSIGNED_TO_NAME", "������");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
		fieldMap.put("ASSET_UNITS", "�ʲ�����");
		fieldMap.put("TASK_NAME", "��������");
		fieldMap.put("FA_CATEGORY_CODE", "�ʲ����");
		return fieldMap;
	}

	public void readPaAssets(){
		AmsPaAssetsDTO dto = (AmsPaAssetsDTO)dtoParameter;

	}
}
