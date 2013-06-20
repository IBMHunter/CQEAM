package com.sino.nm.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.nm.ams.newasset.dto.InformationMaterialManageDTO;
import com.sino.nm.ams.newasset.model.InformationMaterialManageModel;
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

public class InformationMaterialManageExportDAO extends AMSBaseDAO {

	public InformationMaterialManageExportDAO(SfUserDTO userAccount,
			InformationMaterialManageDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		InformationMaterialManageDTO dto = (InformationMaterialManageDTO)dtoParameter;
		this.sqlProducer = new InformationMaterialManageModel(user, dto); 
	}
	
	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "�ʲ���ǩ");
		fieldMap.put("ITEM_NAME", "�ʲ�����");
		fieldMap.put("ITEM_SPEC", "�ʲ��ͺ�");
		fieldMap.put("ITEM_STATUS", "�ʲ�״̬");
		fieldMap.put("ITEM_BRAND", "�ʲ�Ʒ��");
		fieldMap.put("ITEM_SERIAL", "�ʲ����к�");
		fieldMap.put("CREATION_DATE", "��������");
		fieldMap.put("CREATED_BY", "������");
		fieldMap.put("USE_BY_SYSTEM", "����ϵͳ");
		fieldMap.put("PRODUCT_ID", "��Ʒ��");
		fieldMap.put("RESPONSIBILITY_USER", "������");
		fieldMap.put("RESPONSIBILITY_DEPT", "���β���");

		return fieldMap;
	}
	
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			InformationMaterialManageModel modelProducer = (InformationMaterialManageModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "��Ϣ�����ʲ�ѯ��������";
			// or .csv
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

}
