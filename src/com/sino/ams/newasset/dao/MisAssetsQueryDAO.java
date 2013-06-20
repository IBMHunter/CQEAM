package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.model.MisAssetsQueryModel;
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
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

public class MisAssetsQueryDAO extends AMSBaseDAO {

	public MisAssetsQueryDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
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
		sqlProducer = new MisAssetsQueryModel(user,dto);
	}


	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile(String excelType) throws DataTransException {
		File file = null;
		try {
			MisAssetsQueryModel modelProducer = (MisAssetsQueryModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "MIS�ʲ�̨��";
			if (!StrUtil.isNotEmpty(excelType)) {
				excelType = "xls";
			}
			String fileName = reportTitle + "." + excelType;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setPageSize(2000);
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
		fieldMap.put("MIS_TAG_NUMBER", "��ǩ��");
		fieldMap.put("ASSET_NUMBER", "�ʲ����");
		fieldMap.put("FA_CATEGORY1", "Ӧ������");
		fieldMap.put("SEGMENT2", "�ʲ�������");
		fieldMap.put("FA_CATEGORY2", "�ʲ����");

		fieldMap.put("ASSETS_DESCRIPTION", "�豸����");
		fieldMap.put("MODEL_NUMBER", "����ͺ�");
		fieldMap.put("CURRENT_UNITS", "����");
		fieldMap.put("UNIT_OF_MEASURE", "��λ");
		fieldMap.put("ASSETS_LOCATION_CODE", "�ص���");

		fieldMap.put("ASSETS_LOCATION", "�ص�����");
		fieldMap.put("ASSIGNED_TO_NAME", "������");
		fieldMap.put("ASSIGNED_TO_NUMBER", "Ա�����");
		fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
		fieldMap.put("PROJECT_NAME", "��Ŀ����");

		fieldMap.put("PROJECT_TYPE", "��Ŀ����");
		fieldMap.put("LIFE_IN_YEARS", "����");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
		fieldMap.put("ASSETS_CREATE_DATE", "��������");
		fieldMap.put("ORIGINAL_COST", "ԭʼ�ɱ�");
 
        fieldMap.put("NET_ASSET_VALUE", "��ֵ");
		fieldMap.put("COST", "��ǰ�ɱ�");
		fieldMap.put("DEPRN_COST", "�ʲ�����");
		fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
		fieldMap.put("DEPRN_AMOUNT", "�����۾ɶ�");
		
		fieldMap.put("YTD_DEPRN", "�����۾ɶ�");
		fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
		fieldMap.put("IMPAIR_AMOUNT", "�ʲ���ֵ׼��");
		fieldMap.put("YTD_IMPAIRMENT", "�����ֵ׼��");
		fieldMap.put("IMPAIR_RESERVE", "��ֵ׼���ۼ�");
		
//		fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
//		fieldMap.put("DEPRN_COST", "�ʲ�����");
//		fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
//		fieldMap.put("IMPAIR_RESERVE", "��ֵ׼���ۼ�");

		fieldMap.put("COMPANY", "��˾����");
		fieldMap.put("BOOK_TYPE_CODE", "�ʲ��ʲ�����");
        fieldMap.put("BOOK_TYPE_NAME", "�ʲ��ʲ�����");
        fieldMap.put("DEPRECIATION_ACCOUNT", "�۾��ʻ�����");
		fieldMap.put("DEPRECIATION_ACCOUNT_NAME", "�۾��ʻ�����");
		fieldMap.put("IS_RETIREMENTS", "����״̬");
		fieldMap.put("DATE_RETIRED", "��������");
		return fieldMap;
	}
}
