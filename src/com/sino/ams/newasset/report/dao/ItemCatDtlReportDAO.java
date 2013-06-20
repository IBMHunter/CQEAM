package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.newasset.report.model.ItemCatDetailRptModel;
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

public class ItemCatDtlReportDAO extends AMSBaseDAO {

	public ItemCatDtlReportDAO(SfUserDTO userAccount, AmsAssetsCheckLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsAssetsCheckLineDTO dto = (AmsAssetsCheckLineDTO)dtoParameter;
		sqlProducer = new ItemCatDetailRptModel(user, dto);
	}

	/**
	 * ���ܣ�������˾�̵����Excel�����ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			AmsAssetsCheckLineDTO dto = (AmsAssetsCheckLineDTO)dtoParameter;
			String exportType = dto.getExportType();
			String reportTitle = "";
			ItemCatDetailRptModel modelProducer = (ItemCatDetailRptModel) sqlProducer;
			SQLModel sqlModel = null;
			if(exportType.equals(DictConstant.EXPORT_LOC_ITEM)){
				sqlModel = modelProducer.getOwnItemModel();
				reportTitle = "��רҵ�����豸(" + dto.getItemCategoryName() + ")";
			} else if(exportType.equals(DictConstant.EXPORT_SCAN_ITEM)){
				sqlModel = modelProducer.getScanedItemModel();
				reportTitle = "���̵��豸(" + dto.getItemCategoryName() + ")";
			} else if(exportType.equals(DictConstant.EXPORT_DIFF_ITEM)){
				sqlModel = modelProducer.getDiffItemModel();
				reportTitle = "�̵����(" + dto.getItemCategoryName() + ")";
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
		fieldMap.put("BARCODE", "��ǩ��");
		fieldMap.put("FINANCE_PROP_NAME", "�ʲ�����");
		fieldMap.put("YEARS", "ʹ������");
		fieldMap.put("ITEM_UNIT", "������λ");
		fieldMap.put("WORKORDER_OBJECT_NAME", "���ڵص�");
		fieldMap.put("COUNTY_NAME", "����λ��");
		fieldMap.put("PROJECT_NAME", "��Ŀ����");
		fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
		fieldMap.put("COMPANY_CODE", "��˾����");
		fieldMap.put("COMPANY", "��˾����");
		if(exportType.equals(DictConstant.EXPORT_DIFF_ITEM)){
			fieldMap.put("ITEM_CATEGORY_NAME", "�豸רҵ(ϵͳ)");
			fieldMap.put("ITEM_NAME", "�豸����(ϵͳ)");
			fieldMap.put("ITEM_SPEC", "�豸�ͺ�(ϵͳ)");
			fieldMap.put("VENDOR_NAME", "��Ӧ������(ϵͳ)");
			fieldMap.put("VENDOR_NUMBER", "��Ӧ�̴���(ϵͳ)");
			fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���(ϵͳ)");
			fieldMap.put("RESPONSIBILITY_USER_NAME", "����������(ϵͳ)");
			fieldMap.put("EMPLOYEE_NUMBER", "������Ա����(ϵͳ)");
			fieldMap.put("MAINTAIN_DEPT_NAME", "ʹ�ò���(ϵͳ)");
			fieldMap.put("MAINTAIN_USER", "ʹ����(ϵͳ)");

			fieldMap.put("SCAN_ITEM_CATEGORY_NAME","�豸רҵ(ɨ��)");
			fieldMap.put("SCAN_ITEM_NAME","�豸����(ɨ��)");
			fieldMap.put("SCAN_ITEM_SPEC","�豸�ͺ�(ɨ��)");
			fieldMap.put("SCAN_VENDOR_NAME","��Ӧ������(ɨ��)");
			fieldMap.put("SCAN_VENDOR_NUMBER","��Ӧ�̴���(ɨ��)");
			fieldMap.put("SCAN_RESPONSIBILITY_DEPT_NAME","���β���(ɨ��)");
			fieldMap.put("SCAN_RESPONSIBILITY_USER_NAME","����������(ɨ��)");
			fieldMap.put("SCAN_EMPLOYEE_NUMBER","������Ա����(ɨ��)");
			fieldMap.put("SCAN_MAINTAIN_DEPT_NAME","ʹ�ò���(ɨ��)");
			fieldMap.put("SCAN_MAINTAIN_USER","ʹ����(ɨ��)");
		} else {
			fieldMap.put("ITEM_CATEGORY_NAME", "�豸רҵ");
			fieldMap.put("ITEM_NAME", "�豸����");
			fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
			fieldMap.put("VENDOR_NAME", "��Ӧ������");
			fieldMap.put("VENDOR_NUMBER", "��Ӧ�̴���");
			fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
			fieldMap.put("RESPONSIBILITY_USER_NAME", "����������");
			fieldMap.put("EMPLOYEE_NUMBER", "������Ա����");
			fieldMap.put("MAINTAIN_DEPT_NAME", "ʹ�ò���");
			fieldMap.put("MAINTAIN_USER", "ʹ����");
		}
		return fieldMap;
	}
}
