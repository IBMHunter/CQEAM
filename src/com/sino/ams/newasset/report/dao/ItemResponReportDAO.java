package com.sino.ams.newasset.report.dao;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.model.ItemResponReportModel;
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
 * <p>Title: SinoEAM</p>
 * <p>Description: �й��ƶ��ʲ�ʵ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ItemResponReportDAO extends AMSBaseDAO {

	public ItemResponReportDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)dtoParameter;
		sqlProducer = new ItemResponReportModel((SfUserDTO)userAccount, dto);
	}

	/**
	 * ���ܣ�������ά��˾�̵����Excel�����ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)dtoParameter;
			String exportType = dto.getExportType();
			String reportTitle = "";
			ItemResponReportModel modelProducer = (ItemResponReportModel) sqlProducer;
			SQLModel sqlModel = null;
			if(exportType.equals(DictConstant.EXPORT_LOC_ITEM)){
				sqlModel = modelProducer.getResponItemsModel();
				reportTitle = "�ص�" + dto.getCheckLocationName() +  "�豸�嵥";
			} else if(exportType.equals(DictConstant.EXPORT_SCAN_ITEM)){
				sqlModel = modelProducer.getScanedItemsModel();
				reportTitle = "�ص�" + dto.getCheckLocationName() + "����̵���";
			} else if(exportType.equals(DictConstant.EXPORT_DIFF_ITEM)){
				sqlModel = modelProducer.getDiffItemsModel();
				reportTitle = "�ص�" + dto.getCheckLocationName() + "�������";
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
			Map fieldMap = new HashMap();
			fieldMap.put("BARCODE", "��ǩ��");
			fieldMap.put("ITEM_CATEGORY", "�豸����");
			fieldMap.put("ITEM_NAME", "�豸����");
			fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
			fieldMap.put("ITEM_UNIT", "������λ");
			if(exportType.equals(DictConstant.EXPORT_DIFF_ITEM)){
				fieldMap.put("SYSTEM_EXISTS", "ϵͳ״̬");
				fieldMap.put("SCAN_EXISTS", "ɨ��״̬");
			}
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
}
