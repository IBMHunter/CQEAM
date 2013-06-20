package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.report.dto.SpecialAssetsReportDTO;
import com.sino.ams.newasset.report.model.AppAreaRentAssetsReportModel;
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
 * @author:			����
 * Function:		Ӧ������ͳ��(����)
 * Date: 2009-6-2
 * Time: 11:19:30
 * To change this template use File | Settings | File Templates.
 */
public class AppAreaRentAssetsReportDAO extends AMSBaseDAO {

	public AppAreaRentAssetsReportDAO(SfUserDTO userAccount, SpecialAssetsReportDTO dtoParameter, Connection conn) {
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
		sqlProducer = new AppAreaRentAssetsReportModel(user, dto);
	}


	/**
	 * ���ܣ���ȡ�����ʲ�Ӧ������ͳ��Excel�ļ�
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile(SpecialAssetsReportDTO dto) throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "Ӧ������ͳ��(����)";
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
			rule.setFieldMap(getFieldMap(dto));
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

    private Map getFieldMap(SpecialAssetsReportDTO dto){
		Map fieldMap = new HashMap();
        fieldMap.put("COMPANY", "��˾");
        fieldMap.put("FA_DESCRIPTION", "Ӧ������");
        fieldMap.put("SUM_COST", "ԭֵ");
		fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
		fieldMap.put("NET_BOOK_VALUE", "��ֵ");
		fieldMap.put("IMPAIRMENT_RESERVE", "�ۼƼ�ֵ׼��");
		fieldMap.put("LIMIT_VALUE", "����");
		fieldMap.put("PTD_DEPRN", "�����۾�");
		fieldMap.put("SUM_COUNT", "�ʲ�����");
		fieldMap.put("ASSETS_RATE", "ռ�����ʲ��ܶ����");
		
		fieldMap.put("LAST_MONTH_RATE", "������������");
		fieldMap.put("LAST_YEAR_RATE", "��ȥ��ͬ��������");
		fieldMap.put("THREE_YEER_THREE_RATE", "��3��������("+ (Integer.parseInt(dto.getYear())-3) +")");
        fieldMap.put("THREE_YEER_TWO_RATE", "��3��������("+ (Integer.parseInt(dto.getYear())-2) +")");
        fieldMap.put("THREE_YEER_ONE_RATE", "��3��������("+ (Integer.parseInt(dto.getYear())-1) +")");
        return fieldMap;
	}
}
