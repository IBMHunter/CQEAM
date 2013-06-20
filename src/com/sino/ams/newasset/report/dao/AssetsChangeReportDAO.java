package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsChangedVDTO;
import com.sino.ams.newasset.report.model.AssetsChangeReportModel;
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

public class AssetsChangeReportDAO extends AMSBaseDAO {

	public AssetsChangeReportDAO(SfUserDTO userAccount, AmsAssetsChangedVDTO dtoParameter, Connection conn) {
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
		AmsAssetsChangedVDTO dto = (AmsAssetsChangedVDTO) dtoParameter;
		sqlProducer = new AssetsChangeReportModel(user, dto);
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
			String reportTitle = "�ʲ��������";
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

	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("COMPANY", "��˾����");

		fieldMap.put("BARCODE", "EAM��ǩ");
		fieldMap.put("TAG_NUMBER", "MIS��ǩ");
		fieldMap.put("ITEM_NAME", "EAM����");

		fieldMap.put("ASSETS_DESCRIPTION", "MIS����");
		fieldMap.put("ITEM_SPEC", "EAM�ͺ�");
		fieldMap.put("MODEL_NUMBER", "MIS�ͺ�");

		fieldMap.put("EMPLOYEE_NUMBER", "EAMԱ����");
		fieldMap.put("ASSIGNED_TO_NUMBER", "MISԱ����");
		fieldMap.put("USER_NAME", "EAM������");
		fieldMap.put("ASSIGNED_TO_NAME", "MIS������");

		fieldMap.put("WORKORDER_OBJECT_CODE", "EAM�ص����");
		fieldMap.put("ASSETS_LOCATION_CODE", "MIS�ص����");
		fieldMap.put("WORKORDER_OBJECT_LOCATION", "EAM�ص�");
		fieldMap.put("ASSETS_LOCATION", "MIS�ص�");

		fieldMap.put("AMS_COST_CODE", "EAM�ɱ����Ĵ���");
		fieldMap.put("MIS_COST_CODE", "MIS�ɱ����Ĵ���");
		fieldMap.put("AMS_COST_NAME", "EAM�ɱ�����");
		fieldMap.put("MIS_COST_NAME", "MIS�ɱ�����");

		fieldMap.put("CHANGED_CONTENT", "�������");

		return fieldMap;
	}
}
