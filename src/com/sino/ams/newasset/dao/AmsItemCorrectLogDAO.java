package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsItemCorrectLogDTO;
import com.sino.ams.newasset.model.AmsItemCorrectLogModel;
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
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsItemCorrectLogDAO</p>
 * <p>Description:�����Զ����ɷ������AmsItemCorrectLogDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsItemCorrectLogDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��ʲ�̨��ά����־ AMS_ITEM_CORRECT_LOG ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemCorrectLogDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsItemCorrectLogDAO(SfUserDTO userAccount, AmsItemCorrectLogDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsItemCorrectLogDTO dto = (AmsItemCorrectLogDTO)dtoParameter;
		super.sqlProducer = new AmsItemCorrectLogModel((SfUserDTO)userAccount, dto);
	}

	public File getExportFile() throws DataTransException {
		AmsItemCorrectLogDTO dto = (AmsItemCorrectLogDTO)dtoParameter;
		AmsItemCorrectLogModel modelProducer = (AmsItemCorrectLogModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getDataByForeignKeyModel("barcode");
        String reportTitle = dto.getBarcode() + "̨��ά����ʷ��¼";
        String fileName = reportTitle + ".xls";
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
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
        return (File) transfer.getTransResult();
	}

	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("CREATED_USER", "�޸���");
		fieldMap.put("CREATION_DATE", "�޸�ʱ��");
		fieldMap.put("CORRECT_CONTENT", "�޸�����");
		return fieldMap;
	}
}
