package com.sino.ams.workorder.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.trust.dto.AmsMaintainCompanyDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.LocResponReportModel;
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
public class LocResponReportDAO extends AMSBaseDAO {
	public LocResponReportDAO(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EtsWorkorderDTO dto = (EtsWorkorderDTO)dtoParameter;
		sqlProducer = new LocResponReportModel((SfUserDTO)userAccount, dto);
	}

	/**
	 * ���ܣ�������ά��˾Ѳ�����Excel�����ļ�
	 * @param mainCompany AmsMaintainCompanyDTO
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile(AmsMaintainCompanyDTO mainCompany) throws DataTransException {
		File file = null;
		try {
			EtsWorkorderDTO dto = (EtsWorkorderDTO)dtoParameter;
			String exportType = dto.getExportType();
			String reportTitle = "";
			LocResponReportModel modelProducer = (LocResponReportModel) sqlProducer;
			SQLModel sqlModel = null;
			if(exportType.equals(DictConstant.EXPORT_RES_LOC)){
				sqlModel = modelProducer.getResponLocationsModel();
				reportTitle = mainCompany.getName() +  "��ά���εص�";
			} else if(exportType.equals(DictConstant.EXPORT_SCAN_LOC_Y)){
				sqlModel = modelProducer.getScanedLocationsModel();
				reportTitle = mainCompany.getName() + "��Ѳ��ص�";
			} else if(exportType.equals(DictConstant.EXPORT_SCAN_LOC_N)){
				sqlModel = modelProducer.getNotScanedLocationsModel();
				reportTitle = mainCompany.getName() + "δѲ��ص�";
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
			fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
			fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
			fieldMap.put("WORKORDER_OBJECT_LOCATION", "����λ��");
			fieldMap.put("OBJECT_CATEGORY", "�ص����");
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
