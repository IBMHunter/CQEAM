package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.model.FreeAssetsQueryModel;
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

public class FreeAssetsExportDAO extends AMSBaseDAO {

	public FreeAssetsExportDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
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
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)dtoParameter;
		sqlProducer = new FreeAssetsQueryModel(user, dto);
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			FreeAssetsQueryModel modelProducer = (FreeAssetsQueryModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�����ʲ���ѯ��������";
			String fileName = reportTitle + ".xls";
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = getFieldMap();
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

	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "�ʲ���ǩ");
		fieldMap.put("ASSET_NUMBER", "�ʲ����");
		fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
		fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
		fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
		fieldMap.put("WORKORDER_OBJECT_LOCATION", "�ص�λ��");
		fieldMap.put("DEPT_NAME", "���β���");
		fieldMap.put("RESPONSIBILITY_USER_NAME", "����������");
		fieldMap.put("EMPLOYEE_NUMBER", "������Ա����");
		fieldMap.put("UNIT_OF_MEASURE", "������λ");
		fieldMap.put("LIFE_IN_YEARS", "��������");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");

		fieldMap.put("COST", "�ʲ�ԭֵ");
		fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
		fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
		fieldMap.put("DEPRECIATION_ACCOUNT", "�۾��˻�����");
		fieldMap.put("DEPRECIATION_ACCOUNT_NAME", "�۾��˻�����");
		fieldMap.put("TRANS_NO", "�ϴ����õ���");
		fieldMap.put("TRANS_DATE", "�ϴ���������");
		fieldMap.put("USERNAME", "�ϴ�������");
		return fieldMap;
	}
}
