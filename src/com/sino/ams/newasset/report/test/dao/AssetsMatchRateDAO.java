package com.sino.ams.newasset.report.test.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.report.test.dto.SpecialAssetsReportDTO;
import com.sino.ams.newasset.report.test.model.AssetsMatchRateModel;
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
 * Date: 2009-3-10
 * Time: 13:57:34
 * To change this template use File | Settings | File Templates.
 */
public class AssetsMatchRateDAO extends AMSBaseDAO {

	public AssetsMatchRateDAO(SfUserDTO userAccount, SpecialAssetsReportDTO dtoParameter, Connection conn) {
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
		sqlProducer = new AssetsMatchRateModel(user, dto);
	}


	/**
	 * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile(String matchAssetsType) throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
            String reportTitle = "";
            if (matchAssetsType.equals("MATCH_DEPT")) {
               reportTitle = "�ʲ���ʵ�����(ʵ������Ų���)";
            } else {
               reportTitle = "�ʲ���ʵ�����(��˾����)";
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
            if (matchAssetsType.equals("MATCH_DEPT")) {
                fieldMap.put("COMPANY", "��˾");
                fieldMap.put("DEPT_NAME", "���β���");
                fieldMap.put("SUM_UNITS", "MIS�ʲ�����");
                fieldMap.put("SUM_COST2", "MIS�ʲ�ԭֵ");
                fieldMap.put("SUM_COUNT", "��ʵ�������");
                fieldMap.put("SUM_COST", "��ʵ���ԭֵ");
                fieldMap.put("AMOUNT_RATE", "��ʵ��������ٷֱ�");
                fieldMap.put("MONEY_RATE", "��ʵ���ԭֵ�ٷֱ�");
            } else {
                fieldMap.put("COMPANY", "��˾");
                fieldMap.put("SUM_UNITS", "MIS�ʲ�����");
                fieldMap.put("SUM_COST2", "MIS�ʲ�ԭֵ");
                fieldMap.put("SUM_COUNT", "��ʵ�������");
                fieldMap.put("SUM_COST", "��ʵ���ԭֵ");
                fieldMap.put("AMOUNT_RATE", "��ʵ��������ٷֱ�");
                fieldMap.put("MONEY_RATE", "��ʵ���ԭֵ�ٷֱ�"); 
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
