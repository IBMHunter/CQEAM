package com.sino.ams.system.log.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.log.dto.SfUserLogDTO;
import com.sino.ams.system.log.model.SfUserLogModel;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfUserLogDAO</p>
 * <p>Description:�����Զ����ɷ������SfUserLogDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfUserLogDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��û�URL������־��(EAM) SF_USER_LOG ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfUserLogDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfUserLogDAO(SfUserBaseDTO userAccount, SfUserLogDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		SfUserLogDTO dtoPara = (SfUserLogDTO)dtoParameter;
		sqlProducer = new SfUserLogModel((SfUserBaseDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ�����SQL�����ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile(String excelType) throws DataTransException {
		File file = null;
		try {
			SfUserLogModel modelProducer = (SfUserLogModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�û�������־";
			if (!StrUtil.isNotEmpty(excelType)) {
				excelType = "xls";
			}
			String fileName = reportTitle + "." + excelType;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			rule.setPageSize(2000);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = getFieldMap();
			rule.setFieldMap(fieldMap);
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
//			rule.setCalPattern("YYYY��MM��DD��  HH24ʱMI��SS�� ");
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
	 * ���ܣ���ȡ��ȷ���ʲ��ĵ�����ͷ
	 * @return Map
	 */
	private static Map getFieldMap() {
		Map fieldMap = new HashMap();
		fieldMap.put("USER_ID", "�û�ID");
		fieldMap.put("USERNAME", "�û�����");
		fieldMap.put("USER_ACCOUNT", "�û��˺�");
		fieldMap.put("CLIENT_IP", "�ͻ���IP");
		fieldMap.put("RES_NAME", "������Դ����");
		fieldMap.put("SERVER", "Ӧ�÷�����");
		fieldMap.put("REQ_URL", "����URL");
		fieldMap.put("ACTION_TYPE", "��������");
		fieldMap.put("LOG_TIME", "����ʱ��");
		return fieldMap;
	}
}
