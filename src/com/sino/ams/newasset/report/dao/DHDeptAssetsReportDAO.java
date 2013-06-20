package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.report.dto.DeptAssetsReportDTO;
import com.sino.ams.newasset.report.model.DHDeptAssetsReportModel;
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

public class DHDeptAssetsReportDAO extends AMSBaseDAO {

	public DHDeptAssetsReportDAO(SfUserDTO userAccount, DeptAssetsReportDTO dtoParameter, Connection conn) {
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
		DeptAssetsReportDTO dto = (DeptAssetsReportDTO) dtoParameter;
		sqlProducer = new DHDeptAssetsReportModel(user, dto);
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
			String reportTitle = "��ֵ�׺��ʲ�����ͳ�Ʊ���";
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
			fieldMap.put("COMPANY", "��˾");
			fieldMap.put("DEPT_NAME", "���β���");
			
			fieldMap.put("SUM_COST", "ԭֵ");
			fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
			fieldMap.put("NET_BOOK_VALUE", "��ֵ");
			fieldMap.put("IMPAIRMENT_RESERVE", "�ۼƼ�ֵ׼��");
			fieldMap.put("LIMIT_VALUE", "����");
			fieldMap.put("PTD_DEPRN", "�����۾�");
			fieldMap.put("ITEM_QTY", "�ʲ�����");
			fieldMap.put("ASSETS_RATE", "ռ�����ʲ���������");
			fieldMap.put("ASSETS_RATE_PRICE", "ռ�����ʲ��ܶ����");
			fieldMap.put("LAST_YEAR_RATE", "��ȥ��ͬ��������");
			
            fieldMap.put("LAST_MONTH_RATE", "������������");
            fieldMap.put("THREE_YEER_ONE", "��3��������(2006)");
            fieldMap.put("THREE_YEER_TWO", "��3��������(2007)");
            fieldMap.put("THREE_YEER_THREE", "��3��������(2008)");
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
