package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.model.MisDiscardedAssetsQueryModel;
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

public class MisDiscardedAssetsQueryDAO extends AMSBaseDAO {

	/**
	 * Function��		MIS�ʲ����ϲ�ѯ			
	 * Title: 			SinoApplication
	 * @param 			userAccount
	 * @param 			dtoParameter
	 * @param conn
	 * Description:		Java Enterprise Edition Ӧ�ÿ���
	 * Copyright:		�����Ȩ����Copyright (c)2009~2022��
	 * Copyright: 		����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�
	 * Copyright: 		������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��
	 * Company: 		����˼ŵ����Ϣ�������޹�˾
	 * @author 			����
	 * @version 		0.1
	 * @Date			May 12, 2009
	 */
	public MisDiscardedAssetsQueryDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
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
		sqlProducer = new MisDiscardedAssetsQueryModel(user,dto);
	}


	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			MisDiscardedAssetsQueryModel modelProducer = (MisDiscardedAssetsQueryModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "MIS�ʲ����ϲ�ѯ";
			String fileName = reportTitle + ".xls";
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
		fieldMap.put("TAG_NUMBER", "�ʲ���ǩ");
		fieldMap.put("MIS_TAG_NUMBER", "ԭMIS��ǩ");
		fieldMap.put("ASSET_NUMBER", "�ʲ����");
		fieldMap.put("FA_CATEGORY1", "Ӧ������");
		fieldMap.put("SEGMENT2", "�ʲ�������");
		fieldMap.put("FA_CATEGORY2", "�ʲ����");

		fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
		fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
		fieldMap.put("CURRENT_UNITS", "�ʲ�����");
		fieldMap.put("UNIT_OF_MEASURE", "������λ");
		fieldMap.put("ASSETS_LOCATION_CODE", "�ص����");

		fieldMap.put("ASSETS_LOCATION", "�ص�λ��");
		fieldMap.put("ASSIGNED_TO_NAME", "����������");
		fieldMap.put("ASSIGNED_TO_NUMBER", "������Ա����");
		fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
		fieldMap.put("PROJECT_NAME", "��Ŀ����");

		fieldMap.put("PROJECT_TYPE", "��Ŀ����");
		fieldMap.put("LIFE_IN_YEARS", "�۾�����");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
		fieldMap.put("ASSETS_CREATE_DATE", "��������");
		fieldMap.put("ORIGINAL_COST", "ԭʼ�ɱ�");

		fieldMap.put("COST", "��ǰ�ɱ�");
		fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
		fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
		fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
		fieldMap.put("IMPAIR_RESERVE", "��ֵ׼���ۼ�");

		fieldMap.put("COMPANY", "��˾����");
		fieldMap.put("BOOK_TYPE_CODE", "�ʲ��˲�");
		fieldMap.put("DEPRECIATION_ACCOUNT", "�۾��˻�����");
		fieldMap.put("DEPRECIATION_ACCOUNT_NAME", "�۾��˻�����");
		
		fieldMap.put("DATE_RETIRED", "��������");
		fieldMap.put("DATE_EFFECTIVE", "������Ч����");
		fieldMap.put("COST_RETIRED", "���ϳɱ�");
		fieldMap.put("RETIREMENT_TYPE_CODE", "��������");
		return fieldMap;
	}
}

