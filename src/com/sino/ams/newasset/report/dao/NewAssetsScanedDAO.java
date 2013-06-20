package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.model.NewAssetsScanedModel;
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

public class NewAssetsScanedDAO extends AMSBaseDAO {

	public NewAssetsScanedDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
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
		sqlProducer = new NewAssetsScanedModel(user, dto);
	}


	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			NewAssetsScanedModel modelProducer = (NewAssetsScanedModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "ɨ���ʲ�";
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
		fieldMap.put("BARCODE", "�ʲ���ǩ");
//		fieldMap.put("ITEM_CATEGORY", "�豸����");
		fieldMap.put("AMS_ITEM_NAME", "EAM����");
		fieldMap.put("MIS_ITEM_NAME", "MIS����");
		fieldMap.put("AMS_ITEM_SPEC", "EAM�ͺ�");
		fieldMap.put("MIS_ITEM_SPEC", "MIS�ͺ�");
//		fieldMap.put("AMS_YEARS", "EAMʹ������");
//		fieldMap.put("MIS_YEARS", "MISʹ������");
		fieldMap.put("AMS_START_DATE", "EAM��������");
		fieldMap.put("MIS_START_DATE", "MIS��������");
//		fieldMap.put("ORIGINAL_COST", "ԭʼ�ɱ�");
//		fieldMap.put("COST", "��ǰ�ɱ�");
//		fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
//		fieldMap.put("IMPAIR_RESERVE", "��ֵ׼���ۼ�");
//		fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
//		fieldMap.put("DEPRECIATION_ACCOUNT", "�۾ɷ����˻�");
//		fieldMap.put("DEPT_NAME", "EAM���β���");
		fieldMap.put("AMS_LOCATION_CODE", "EAM�ص����");
		fieldMap.put("MIS_LOCATION_CODE", "MIS�ص����");
		fieldMap.put("AMS_LOCATION", "EAM�ص�����");
		fieldMap.put("MIS_LOCATION", "MIS�ص�����");
		fieldMap.put("AMS_EMPLOYEE_NUMBER", "EAMԱ�����");
		fieldMap.put("MIS_EMPLOYEE_NUMBER", "MISԱ�����");
		fieldMap.put("AMS_USER_NAME", "EAM������");
		fieldMap.put("MIS_USER_NAME", "MIS������");
		fieldMap.put("AMS_PROJECT_NAME", "EAM��Ŀ����");
		fieldMap.put("MIS_PROJECT_NAME", "MIS��Ŀ����");
		fieldMap.put("AMS_PROJECT_NUMBER", "EAM��Ŀ���");
		fieldMap.put("MIS_PROJECT_NUMBER", "MIS��Ŀ���");
		fieldMap.put("CHANGED_CONTENT", "�������");

		return fieldMap;
	}
}
