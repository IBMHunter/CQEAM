package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.model.NewAssetsMonitorModel;
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

public class NewAssetsMonitorDAO extends AMSBaseDAO {

	public NewAssetsMonitorDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
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
		sqlProducer = new NewAssetsMonitorModel(user, dto);
	}



	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			NewAssetsMonitorModel modelProducer = (NewAssetsMonitorModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�����ʲ���ر���";
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
		fieldMap.put("TOTAL_COUNT", "MIS�����ʲ�����");
		fieldMap.put("TOTAL_NEED_SCANCEL_COUNT", "��PDAɨ��ȷ������");

		fieldMap.put("SCANED_TOTAL_COUNT", "�ڼ�ʵ��ɨ������");
		fieldMap.put("SCANCER_IDENTICAL_COUNT", "��ʵ�������");
        fieldMap.put("IDENTICAL_RATE", "��ʵ����ٷֱ�");

        fieldMap.put("SCANED_FILTER_COUNT", "������ת���ʲ�ɨ������");
		fieldMap.put("SCANER_NEED_SCANED_COUNT", "�������ת���ʲ�������PDAɨ��ȷ�ϵ�����");
        fieldMap.put("NOT_SCANED_COUNT", "��ת���ʲ���δɨ��ȷ������");
        fieldMap.put("SCANED_RATE", "��ת���ʲ�ɨ��ȷ�ϰٷֱ�");
        return fieldMap;
	}
}
