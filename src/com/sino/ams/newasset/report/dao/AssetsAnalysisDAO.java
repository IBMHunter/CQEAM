package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.report.model.AssetsAnalysisModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.WebFileDownException;
import com.sino.framework.dto.BaseUserDTO;

public class AssetsAnalysisDAO extends AMSBaseDAO {
	public AssetsAnalysisDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO) userAccount;
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		sqlProducer = new AssetsAnalysisModel(user, dto);
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "�̶��ʲ������䶯������";
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
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		} catch (DataTransException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		}
		return file;
	}

	/**
	 * ���ܣ����쵼�����ݵ��ֶ�ӳ��
	 * @return Map
	 */
	private Map getFieldMap() {
		Map fieldMap = new HashMap();
		fieldMap.put("COMPANY", "��˾����");
		fieldMap.put("TOTAL_COUNT", "�ʲ�����");
		fieldMap.put("TOTAL_COST", "�ʲ����");
		fieldMap.put("YEAR_ADD_COUNT", "������������");
		fieldMap.put("YEAR_ADD_COST", "�����������");
		fieldMap.put("MONTH_ADD_COUNT", "������������");
		fieldMap.put("MONTH_ADD_COST", "�����������");
		fieldMap.put("YEAR_DIS_COUNT", "���걨������");
		fieldMap.put("YEAR_DIS_COST", "���걨�Ͻ��");
		fieldMap.put("MONTH_DIS_COUNT", "���±�������");
		fieldMap.put("MONTH_DIS_COST", "���±��Ͻ��");
		return fieldMap;
	}

	/**
	 * ���ܣ���ȡ�ض���˾���ʲ��䶯����
	 * @return RowSet
	 * @throws QueryException
	 */
	public RowSet getOrgAssetsChangeData() throws QueryException {
		RowSet rows = new RowSet();
		try {
			AssetsAnalysisModel modelProducer = (AssetsAnalysisModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getOrgAssetsChangeModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			rows = simp.getSearchResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return rows;
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws WebFileDownException
	 */
	public File exportOrgAssets() throws WebFileDownException {
		File file = null;
		try {
			AssetsAnalysisModel modelProducer = (AssetsAnalysisModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getOrgAssetsChangeModel();
			AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
			String reportTitle = dto.getCompany() + "�ʲ��䶯����";
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
			rule.setFieldMap(getOrgAssetsFieldMap());
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
			throw new WebFileDownException(ex);
		} catch (DataTransException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		}
		return file;
	}

	/**
	 * ���ܣ���ȡ�ʲ��䶯��������ڶ����е��ֶ�ӳ��
	 * @return Map
	 */
	private Map getOrgAssetsFieldMap(){
		Map fieldMap = new HashMap();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		String analyseType = dto.getAnalyseType();
		fieldMap.put("TOTAL_COUNT", "�ʲ�����");
		fieldMap.put("TOTAL_COST", "�ʲ����");
		fieldMap.put("YEAR_ADD_COUNT", "������������");
		fieldMap.put("YEAR_ADD_COST", "�����������");
		fieldMap.put("MONTH_ADD_COUNT", "������������");
		fieldMap.put("MONTH_ADD_COST", "�����������");
		fieldMap.put("YEAR_DIS_COUNT", "���걨������");
		fieldMap.put("YEAR_DIS_COST", "���걨�Ͻ��");
		fieldMap.put("MONTH_DIS_COUNT", "���±�������");
		fieldMap.put("MONTH_DIS_COST", "���±��Ͻ��");
		if(analyseType.equals(AssetsDictConstant.ANALYZE_CATEGORY_1)){
			fieldMap.put("SEGMENT", "Ӧ���������");
			fieldMap.put("FA_CATEGORY", "Ӧ������");
		} else if(analyseType.equals(AssetsDictConstant.ANALYZE_CATEGORY_2)){
			fieldMap.put("SEGMENT", "�ʲ��������");
			fieldMap.put("FA_CATEGORY", "�ʲ�����");
		} else if(analyseType.equals(AssetsDictConstant.ANALYZE_CATEGORY_3)){
			fieldMap.put("SEGMENT", "�ʲ������");
			fieldMap.put("FA_CATEGORY", "�ʲ���");
		}
		return fieldMap;
	}
}
