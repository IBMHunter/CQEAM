package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;

import com.sino.framework.dto.BaseUserDTO;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckBatchDTO;
import com.sino.ams.newasset.report.model.CheckReportModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.exception.CalendarException;

public class CheckReportDAO extends AMSBaseDAO {

	public CheckReportDAO(SfUserDTO userAccount, AmsAssetsCheckBatchDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
		sqlProducer = new CheckReportModel(user, dto);
	}


	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "�̵�ͳ�Ʊ���";
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
			Map fieldMap = new HashMap();
			fieldMap.put("COMPANY", "��˾����");
			fieldMap.put("TOTAL_COUNT", "MIS�ʲ�����");
			fieldMap.put("SCANED_COUNT", "���̵�����");
			fieldMap.put("NOT_SCANED_COUNT", "δ�̵�����");
			fieldMap.put("SCAN_RATE", "�̵�ٷֱ�");
			rule.setFieldMap(fieldMap);
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
	 * ���ܣ���ȡ�ɱ���������
	 * @return RowSet
	 * @throws QueryException
	 */
	public RowSet getCostReportData() throws QueryException {
		RowSet rows = null;
		try {
			CheckReportModel modelProducer = (CheckReportModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getCostCheckModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			rows = simp.getSearchResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return rows;
	}

	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File exportCostData() throws DataTransException {
		File file = null;
		try {
			AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO) dtoParameter;
			String reportTitle = "��ֹ" + dto.getEndDate() + dto.getCompanyName() + "�̵��";
			CheckReportModel modelProducer = (CheckReportModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getCostCheckModel();
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
			Map fieldMap = new HashMap();
			fieldMap.put("COST_CENTER_CODE", "�ɱ����Ĵ���");
			fieldMap.put("COST_CENTER_NAME", "�ɱ���������");
			fieldMap.put("TOTAL_COUNT", "MIS�ʲ�����");
			fieldMap.put("SCANED_COUNT", "���̵�����");
			fieldMap.put("NOT_SCANED_COUNT", "δ�̵�����");
			fieldMap.put("SCAN_RATE", "�̵�ٷֱ�");
			rule.setFieldMap(fieldMap);
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
		} catch (CalendarException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}
}
