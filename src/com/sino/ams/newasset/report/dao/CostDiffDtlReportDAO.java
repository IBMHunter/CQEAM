package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.model.CostDiffDtlReportModel;
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

public class CostDiffDtlReportDAO extends AMSBaseDAO {

	public CostDiffDtlReportDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
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
		AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)dtoParameter;
		sqlProducer = new CostDiffDtlReportModel(user, dto);
	}

	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			CostDiffDtlReportModel modelProducer = (CostDiffDtlReportModel) sqlProducer;
			AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)dtoParameter;
			String exportType = dto.getExportType();
			String reportTitle = "";
			SQLModel sqlModel = null;
			if(exportType.equals(DictConstant.OWN_ITEM)){
				sqlModel = modelProducer.getOwnAssetsModel();
				reportTitle = "�ɱ�����"
							  + dto.getCostName()
							  + "�����ʲ�";
			} else if(exportType.equals(DictConstant.SCAN_ITEM_Y)){
				sqlModel = modelProducer.getScanedItemsModel();
				reportTitle ="�ɱ�����"
							 + dto.getCostName()
							 + "���̵��豸";
			} else if(exportType.equals(DictConstant.SCAN_ITEM_N)){
				sqlModel = modelProducer.getNotScanedAssetsModel();
				reportTitle ="�ɱ�����"
							 + dto.getCostName()
							 + "δ�̵��ʲ�";
			}
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
			rule.setFieldMap(getFieldMap(exportType));
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

	private Map getFieldMap(String exportType){
		Map fieldMap = new HashMap();
		if(!exportType.endsWith(AssetsDictConstant.SCAN_ITEM_Y)){
			fieldMap.put("TAG_NUMBER", "�ʲ�����");
			fieldMap.put("FA_CATEGORY1", "Ӧ������");
			fieldMap.put("FA_CATEGORY2", "�ʲ����");
			fieldMap.put("SEGMENT2", "������");
			fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
			fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
			fieldMap.put("CURRENT_UNITS", "�ʲ�����");
			fieldMap.put("UNIT_OF_MEASURE", "������λ");
			fieldMap.put("ASSETS_LOCATION_CODE", "�ص����");
			fieldMap.put("ASSETS_LOCATION", "�ʲ��ص�");
			fieldMap.put("ASSIGNED_TO_NUMBER", "Ա�����");
			fieldMap.put("ASSIGNED_TO_NAME", "������");
			fieldMap.put("MIS_PROJECT_NUMBER", "��Ŀ���");
			fieldMap.put("PROJECT_NAME", "��Ŀ����");
			fieldMap.put("ORIGINAL_COST", "ԭʼ�ɱ�");
			fieldMap.put("COST", "��ǰ�ɱ�");
			fieldMap.put("LIFE_IN_YEARS", "�۾�����");
			fieldMap.put("ASSETS_CREATE_DATE", "��������");
			fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
			fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
			fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
			fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
		} else {
			fieldMap.put("BARCODE", "��ǩ��");
			fieldMap.put("ITEM_CATEGORY_NAME", "�豸����");
			fieldMap.put("ITEM_NAME", "�豸����");
			fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
			fieldMap.put("START_DATE", "��������");
			fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
			fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
			fieldMap.put("USER_NAME", "������");
			fieldMap.put("EMPLOYEE_NUMBER", "Ա����");
			fieldMap.put("DEPT_NAME", "���β���");
			fieldMap.put("SCAN_MAINTAIN_USER", "ʹ����");
		}
		return fieldMap;
	}
}
