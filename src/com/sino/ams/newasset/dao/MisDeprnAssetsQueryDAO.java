package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsDeprnAssetsDTO;
import com.sino.ams.newasset.model.MisDeprnAssetsQueryModel;
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

public class MisDeprnAssetsQueryDAO extends AMSBaseDAO {

	public MisDeprnAssetsQueryDAO(SfUserDTO userAccount, AmsDeprnAssetsDTO dtoParameter, Connection conn) {
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
		AmsDeprnAssetsDTO dto = (AmsDeprnAssetsDTO)dtoParameter;
		sqlProducer = new MisDeprnAssetsQueryModel(user,dto);
	}


	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			MisDeprnAssetsQueryModel modelProducer = (MisDeprnAssetsQueryModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "MIS�ʲ��۾ɲ�ѯ";
			String fileName = reportTitle + ".xls";
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setPageSize(2000);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = getFieldMap();
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


	  	  
	  	  
	  	  
	
	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("BOOK_TYPE_CODE", "�ʲ��˲�����");
		fieldMap.put("BOOK_TYPE_NAME", "�ʲ��˲�����");
		fieldMap.put("ASSET_ID", "�ʲ�Ψһ��ʶ");
		fieldMap.put("TAG_NUMBER", "�ʲ���ǩ��");
		fieldMap.put("ASSET_NUMBER", "�̶��ʲ����");
		fieldMap.put("FA_CATEGORY1", "Ӧ������");
		fieldMap.put("FA_CATEGORY2", "�ʲ����");
		fieldMap.put("DESCRIPTION", "�̶��ʲ�����");
		fieldMap.put("SEGMENT1", "������˾");
		
		fieldMap.put("COST", "�̶��ʲ�ԭֵ");
		fieldMap.put("NET_BOOK_VALUE", "��ֵ");
		fieldMap.put("PTD_IMPAIRMENT", "���¼�ֵ׼��");
		fieldMap.put("YTD_IMPAIRMENT", "�����ֵ׼��");
		fieldMap.put("IMPAIRMENT_RESERVE", "�ۼƼ�ֵ׼��");
		
		fieldMap.put("PTD_DEPRN", "�����۾�");
		fieldMap.put("YTD_DEPRN", "�����۾�");
		fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
		fieldMap.put("PERIOD_NAME", "�۾��ڼ�");

		fieldMap.put("DEPRN_LEFT_MONTH", "�۾�ʣ������");
		fieldMap.put("LAST_UPDATE_DATE", "����������");
		return fieldMap;
	}
}

