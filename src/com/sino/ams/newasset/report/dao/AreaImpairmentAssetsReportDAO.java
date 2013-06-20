package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.report.dto.SpecialAssetsReportDTO;
import com.sino.ams.newasset.report.model.AreaImpairmentAssetsReportModel;
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
 * User: ����
 * Date: 2009-6-14
 * Time: 22:56:55
 * Function:	�������ʲ����ɷֲ�(��ֵ)
 */
public class AreaImpairmentAssetsReportDAO extends AMSBaseDAO {

	public AreaImpairmentAssetsReportDAO(SfUserDTO userAccount, SpecialAssetsReportDTO dtoParameter, Connection conn) {
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
		SpecialAssetsReportDTO dto = (SpecialAssetsReportDTO) dtoParameter;
		sqlProducer = new AreaImpairmentAssetsReportModel(user, dto);
	}


	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "�����ʲ����ɷֲ�(��ֵ)";
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
			fieldMap.put("ASSETS_SPECIES", "��");
			fieldMap.put("ASSETS_NAPE", "��");
			fieldMap.put("CITY_IMPAIR_AMOUNT", "ʡ������̶��ʲ����ڼ�ֵ��");
			fieldMap.put("COUNTY_IMPAIR_AMOUNT", "�س�����̶��ʲ����ڼ�ֵ��");
			fieldMap.put("VILLAGE_IMPAIR_AMOUNT", "ũ������̶��ʲ����ڼ�ֵ��");
			fieldMap.put("CITY_COUNT", "ʡ�������ʲ�����");
			fieldMap.put("COUNTY_COUNT", "�س������ʲ�����");
			fieldMap.put("VILLAGE_COUNT", "ũ�������ʲ�����");
			
            fieldMap.put("CITY_RATE", "ʡ������ռ�����ʲ���������");
            fieldMap.put("COUNTY_RATE", "�س�����ռ�����ʲ���������");
            fieldMap.put("VILLAGE_RATE", "ũ������ռ�����ʲ���������");
            
            fieldMap.put("CITY_IMPAIR_RATE", "ʡ������ռ�����ʲ���ֵ�����");
            fieldMap.put("COUNTY_IMPAIR_RATE", "�س�����ռ�����ʲ���ֵ�����");
            fieldMap.put("VILLAGE_IMPAIR_RATE", "ũ������ռ�����ʲ���ֵ�����");
            
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
