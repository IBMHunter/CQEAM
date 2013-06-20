package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.report.model.RentDeptAssetsReportModel;
import com.sino.ams.system.rent.dto.RentDTO;
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
 * Date: 2009-3-4
 * Time: 13:29:25
 * To change this template use File | Settings | File Templates.
 */

public class RentDeptAssetsReportDAO extends AMSBaseDAO {

	public RentDeptAssetsReportDAO(SfUserDTO userAccount, RentDTO dtoParameter, Connection conn) {
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
		RentDTO dto = (RentDTO) dtoParameter;
		sqlProducer = new RentDeptAssetsReportModel(user, dto);
	}


	/**
	 * ���ܣ���ȡ��Ӫ���޲���ͳ��Excel�ļ�
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "��Ӫ�����ʲ�����ͳ�Ʊ���";
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
			fieldMap.put("COMPANY", "��˾OU");
			fieldMap.put("RESPONSIBILITY_DEPT", "���β���");
            fieldMap.put("BARCODE", "��ǩ��");
            fieldMap.put("ITEM_NAME", "�ʲ�����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("ITEM_UNIT", "��λ");
            
            fieldMap.put("MANUFACTURER_NAME", "������������");
            fieldMap.put("POWER", "�����");
            fieldMap.put("OTHER_INFO", "�豸����");
            fieldMap.put("CONTENT_CODE", "�ʲ����������");
            fieldMap.put("CONTENT_NAME", "�ʲ��������");
            fieldMap.put("RESPONSIBILITY_USER", "�����˱��");
            
            fieldMap.put("USER_NAME", "����������");
            fieldMap.put("OBJECT_NAME", "�ʲ��ص�");
            fieldMap.put("RENT_DATE", "��ʼ����");
            fieldMap.put("END_DATE", "����ʱ��");
            fieldMap.put("RENT_PERSON", "���ⷽ");
            
            fieldMap.put("TENANCY", "����(��)");
            fieldMap.put("YEAR_RENTAL", "�����(Ԫ)");
            fieldMap.put("MONTH_REANTAL", "�����");

//            fieldMap.put("LAST_MONTH_RATE", "������������");
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
