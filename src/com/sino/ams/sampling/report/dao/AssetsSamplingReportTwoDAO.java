package com.sino.ams.sampling.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.report.model.AssetsSamplingReportTwoModel;
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

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-9-18
 * Time: 10:30:12
 * To change this template use File | Settings | File Templates.
 */
public class AssetsSamplingReportTwoDAO extends AMSBaseDAO {

	public AssetsSamplingReportTwoDAO(SfUserDTO userAccount, AmsAssetsSamplingHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) dtoParameter;
		sqlProducer = new AssetsSamplingReportTwoModel(user, dto);
	}



	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			AssetsSamplingReportTwoModel modelProducer = (AssetsSamplingReportTwoModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "��ʵ����";
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
		fieldMap.put("BARCODE", "�豸����");
        fieldMap.put("AMS_ITEM_NAME", "�豸����(EAMϵͳ)");
		fieldMap.put("SCAN_ITEM_NAME", "�豸����(ʵ��ɨ��)");
		fieldMap.put("AMS_ITEM_SPEC", "�豸�ͺ�(EAMϵͳ)");
		fieldMap.put("SCAN_ITEM_SPEC", "�豸�ͺ�(ʵ��ɨ��)");
		fieldMap.put("AMS_LOCATION_CODE", "�ص����(EAMϵͳ)");
		fieldMap.put("SCAN_LOCATION_CODE", "�ص����(ʵ��ɨ��)");
		fieldMap.put("AMS_LOCATION", "�ص�����(EAMϵͳ)");
		fieldMap.put("SCAN_LOCATION", "�ص�����(ʵ��ɨ��)");
		fieldMap.put("AMS_EMPLOYEE_NUMBER", "Ա�����(EAMϵͳ)");
		fieldMap.put("SCAN_EMPLOYEE_NUMBER", "Ա�����(ʵ��ɨ��)");
		fieldMap.put("AMS_USER_NAME", "������(EAMϵͳ)");
		fieldMap.put("SCAN_USER_NAME", "������(EAMϵͳ)");
		fieldMap.put("CHANGED_CONTENT", "�������");
		return fieldMap;
	}
}
