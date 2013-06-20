package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.model.NewAssetsMonitorNotScanedModel;
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

public class NewAssetsMonitorNotScanedDAO extends AMSBaseDAO {

	public NewAssetsMonitorNotScanedDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) dtoParameter;
		sqlProducer = new NewAssetsMonitorNotScanedModel(user, dto);
	}


	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			NewAssetsMonitorNotScanedModel modelProducer = (NewAssetsMonitorNotScanedModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "δɨ���ʲ�";
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

		fieldMap.put("COMPANY", "��˾����");
		fieldMap.put("TAG_NUMBER", "�ʲ���ǩ");
//		fieldMap.put("ITEM_CATEGORY", "�豸����");
		fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
		fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
//		fieldMap.put("AMS_YEARS", "EAMʹ������");
//		fieldMap.put("MIS_YEARS", "MISʹ������");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
//		fieldMap.put("ORIGINAL_COST", "ԭʼ�ɱ�");
//		fieldMap.put("COST", "��ǰ�ɱ�");
//		fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
//		fieldMap.put("IMPAIR_RESERVE", "��ֵ׼���ۼ�");
//		fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
//		fieldMap.put("DEPRECIATION_ACCOUNT", "�۾ɷ����˻�");
//		fieldMap.put("DEPT_NAME", "EAM���β���");
		fieldMap.put("ASSETS_LOCATION_CODE", "�ص����");
		fieldMap.put("ASSETS_LOCATION", "�ص�����");
		fieldMap.put("ASSIGNED_TO_NUMBER", "Ա�����");
		fieldMap.put("ASSIGNED_TO_NAME", "������");
		fieldMap.put("PROJECT_NAME", "��Ŀ����");
		fieldMap.put("CHANGED_CONTENT", "�������");
		return fieldMap;
	}
}