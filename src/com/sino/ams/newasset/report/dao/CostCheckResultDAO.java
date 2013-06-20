package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;

import com.sino.framework.dto.BaseUserDTO;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.model.CostCheckResultModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.util.DBOperator;
import com.sino.base.exception.DataHandleException;

public class CostCheckResultDAO extends AMSBaseDAO {

	public CostCheckResultDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 * @todo Implement this com.sino.framework.dao.BaseDAO method
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) dtoParameter;
		sqlProducer = new CostCheckResultModel(user, dto);
	}



	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			CostCheckResultModel modelProducer = (CostCheckResultModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�̵������������ţ�";
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
	 * ���ܣ���ȡ���������ֶ�ӳ��
	 * @return Map
	 */
	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("COST_CENTER_CODE", "�ɱ����Ĵ���");
		fieldMap.put("COST_CENTER_NAME", "�ɱ���������");
		fieldMap.put("TOTAL_COUNT", "MIS�ʲ�����");
        fieldMap.put("NEED_COUNT", "MIS��PDAɨ������");
		fieldMap.put("NOT_NEED_COUNT", "MIS����PDAɨ������");
        fieldMap.put("SCANED_COUNT", "���̵�����");
		fieldMap.put("IDENTICAL_COUNT", "����MIS����");
		fieldMap.put("UNMATCHED_COUNT", "����ʵ������");
		fieldMap.put("IDENTICAL_RATE_1", "�̵���");
//		fieldMap.put("IDENTICAL_RATE_2", "��ʵ�����(���̵��ʲ�)");
		return fieldMap;
	}
}
