package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.model.CheckResultModel;
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
import com.sino.base.exception.SinoBaseException;
import com.sino.framework.dto.BaseUserDTO;

public class CheckResultDAO extends AMSBaseDAO {

	public CheckResultDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
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
		sqlProducer = new CheckResultModel(user, dto);
	}



	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws DataTransException
	 * @throws SinoBaseException 
	 */
	public File getExportFile() throws DataTransException, SinoBaseException {
		File file = null;
		CheckResultModel modelProducer = (CheckResultModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getExportModel();
		String reportTitle = getExportTitle();
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
	 * ���ܣ���ȡ����Excel�ı���
	 * @return String
	 */
	private String getExportTitle() {
		String expTitle = "";
		AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) dtoParameter;
		String analyseType = dto.getAnalyseType();
		if(analyseType.equals(AssetsDictConstant.CHECK_RESULT_1)){//��ʵһ��
			expTitle = "����MIS�嵥";
		} else if(analyseType.equals(AssetsDictConstant.CHECK_RESULT_3)){//�п�����
			expTitle = "δ��MIS�嵥";
		} else if(analyseType.equals(AssetsDictConstant.CHECK_RESULT_4)){//�����޿�
			expTitle = "�����޿�";
		} else if(analyseType.equals(AssetsDictConstant.CHECK_RESULT_5)){//�п�����
			expTitle = "�п�����(���̵ص�)";
		} else if(analyseType.equals(AssetsDictConstant.CHECK_RESULT_6)){//�п�����
			expTitle = "����PDAɨ���嵥";
		}
		return expTitle;
	}

	/**
	 * ���ܣ���ȡ���������ֶ�ӳ��
	 * @return Map
	 */
	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) dtoParameter;
		String analyseType = dto.getAnalyseType();
		if(analyseType.equals(AssetsDictConstant.CHECK_RESULT_1)){//��ʵһ��
			fieldMap.put("MIS_TAG_NUMBER", "�ʲ���ǩ");
			fieldMap.put("DEPT_NAME", "רҵ����");
			fieldMap.put("ITEM_QTY", "ԭʼ����");
			fieldMap.put("ACTUAL_QTY", "ʵ������");
			fieldMap.put("ITEM_NAME", "EAM����");
			fieldMap.put("ASSETS_DESCRIPTION", "MIS����");
			fieldMap.put("ITEM_SPEC", "EAM�ͺ�");
			fieldMap.put("MODEL_NUMBER", "MIS�ͺ�");
			fieldMap.put("START_DATE", "EAM��������");
			fieldMap.put("DATE_PLACED_IN_SERVICE", "MIS��������");
			fieldMap.put("WORKORDER_OBJECT_CODE", "EAM�ص����");
			fieldMap.put("ASSETS_LOCATION_CODE", "MIS�ص����");
			fieldMap.put("WORKORDER_OBJECT_NAME", "EAM�ص�����");
			fieldMap.put("ASSETS_LOCATION", "MIS�ص�����");
			fieldMap.put("EMPLOYEE_NUMBER", "EAMԱ�����");
			fieldMap.put("ASSIGNED_TO_NUMBER", "MISԱ�����");
			fieldMap.put("USER_NAME", "EAM������");
			fieldMap.put("ASSIGNED_TO_NAME", "MIS������");
			fieldMap.put("CHANGED_CONTENT", "�������");
			String disabled = dto.getDisabled();
			if(disabled.equals("")){
				fieldMap.put("AMS_COST_CODE", "EAM�ɱ����Ĵ���");
				fieldMap.put("MIS_COST_CODE", "MIS�ɱ����Ĵ���");
				fieldMap.put("AMS_COST_NAME", "EAM�ɱ���������");
				fieldMap.put("MIS_COST_NAME", "MIS�ɱ���������");
			}
		} else if(analyseType.equals(AssetsDictConstant.CHECK_RESULT_3) || analyseType.equals(AssetsDictConstant.CHECK_RESULT_5)|| analyseType.equals(AssetsDictConstant.CHECK_RESULT_6)){//�п�����
			fieldMap.put("TAG_NUMBER", "�ʲ���ǩ");
            fieldMap.put("DEPT_NAME", "רҵ����");
//			fieldMap.put("ITEM_QTY", "ԭʼ����");
			fieldMap.put("ACTUAL_QTY", "ʵ������");
			fieldMap.put("FA_CATEGORY1", "Ӧ������");
			fieldMap.put("FA_CATEGORY2", "�ʲ����");
			fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
			fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
			fieldMap.put("CURRENT_UNITS", "�ʲ�����");
			fieldMap.put("UNIT_OF_MEASURE", "������λ");
			fieldMap.put("ASSIGNED_TO_NUMBER", "Ա����");
			fieldMap.put("ASSIGNED_TO_NAME", "������");
			fieldMap.put("ASSETS_LOCATION_CODE", "�ص����");
			fieldMap.put("ASSETS_LOCATION", "�ص�����");
			fieldMap.put("ASSETS_CREATE_DATE", "�ʲ���������");
			fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
			fieldMap.put("ORIGINAL_COST", "ԭʼ�ɱ�");
			fieldMap.put("COST", "��ǰ�ɱ�");
			fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
			fieldMap.put("IMPAIR_RESERVE", "��ֵ׼���ۼ�");
			fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
			fieldMap.put("DEPRECIATION_ACCOUNT", "�۾ɷ����˻�");
			fieldMap.put("MIS_PROJECT_NUMBER", "��Ŀ���");
			fieldMap.put("PROJECT_NAME", "��Ŀ����");
		} else if(analyseType.equals(AssetsDictConstant.CHECK_RESULT_4)){//�����޿�
			fieldMap.put("BARCODE", "�豸����");
            fieldMap.put("DEPT_NAME", "רҵ����");
			fieldMap.put("ITEM_QTY", "ԭʼ����");
			fieldMap.put("ACTUAL_QTY", "ʵ������");
			fieldMap.put("ITEM_NAME", "�豸����");
			fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
			fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");

			fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
			fieldMap.put("EMPLOYEE_NUMBER", "Ա����");
			fieldMap.put("USER_NAME", "������");
			fieldMap.put("COST_CENTER_NAME", "�ɱ���������");

			fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
			fieldMap.put("PROJECT_NAME", "��Ŀ����");
		}
		return fieldMap;
	}


	/**
	 * ���ܣ���OU�̵�����������Excel��
	 * @return File
	 * @throws DataTransException
	 */
	public File getPageExportFile() throws DataTransException {
		File file = null;
		try {
			CheckResultModel modelProducer = (CheckResultModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�̵�����������˾��";
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
			rule.setFieldMap(getPageFieldMap());
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

	private Map getPageFieldMap() {
		Map fieldMap = new HashMap();
		fieldMap.put("COMPANY", "��˾����");
		fieldMap.put("TOTAL_COUNT", "MIS�ʲ�����");
		fieldMap.put("NEED_COUNT", "MIS��PDAɨ������");
		fieldMap.put("NOT_NEED_COUNT", "MIS����PDAɨ������");
		fieldMap.put("SCANED_COUNT", "���̵�����");
		fieldMap.put("IDENTICAL_COUNT", "����MIS����");
		fieldMap.put("UNMATCHED_COUNT", "����ʵ������");
		fieldMap.put("IDENTICAL_RATE_1", "�̵���");
//		fieldMap.put("IDENTICAL_RATE_2", "��ʵ�����(���̵���)");
		return fieldMap;
	}
}
