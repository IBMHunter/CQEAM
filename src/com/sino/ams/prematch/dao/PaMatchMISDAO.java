package com.sino.ams.prematch.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.prematch.dto.AmsPaMatchDTO;
import com.sino.ams.prematch.model.PaMatchMISModel;
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
import com.sino.framework.dto.BaseUserDTO;

public class PaMatchMISDAO extends AMSBaseDAO {

	public PaMatchMISDAO(SfUserDTO userAccount, AmsPaMatchDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 * @todo Implement this com.sino.framework.dao.BaseDAO method
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsPaMatchDTO dto = (AmsPaMatchDTO)dtoParameter;
		sqlProducer = new PaMatchMISModel(user, dto);
	}

	/**
	 * ���ܣ�����ת��׼���嵥��Excel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		PaMatchMISModel modelProducer = (PaMatchMISModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getPageQueryModel();
		String reportTitle = "��ƥ��MISת���嵥";
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
		fieldMap.put("ASSETS_LOCATION", "�ʲ��ص�");
		fieldMap.put("ASSIGNED_TO_NUMBER", "Ա�����");
		fieldMap.put("ASSIGNED_TO_NAME", "������");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
		return fieldMap;
	}
}
