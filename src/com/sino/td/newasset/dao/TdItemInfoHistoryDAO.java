package com.sino.td.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
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
import com.sino.td.newasset.dto.TdItemInfoHistoryDTO;
import com.sino.td.newasset.model.TdItemInfoHistoryModel;


/**
 * <p>Title: AmsItemInfoHistoryDAO</p>
 * <p>Description:�����Զ����ɷ������AmsItemInfoHistoryDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class TdItemInfoHistoryDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter TdItemInfoHistoryDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public TdItemInfoHistoryDAO(SfUserDTO userAccount, TdItemInfoHistoryDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		TdItemInfoHistoryDTO dto = (TdItemInfoHistoryDTO) dtoParameter;
		SfUserDTO user = (SfUserDTO) userAccount;
		sqlProducer = new TdItemInfoHistoryModel(user, dto);
	}

    public File getExportFile(String barcode) throws DataTransException {
		TdItemInfoHistoryModel modelProducer = (TdItemInfoHistoryModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getDataByBarcodeModel(barcode);
		String reportTitle = "�豸�䶯��ʷ����";
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
		fieldMap.put("BARCODE", "��ǩ��");
		fieldMap.put("CREATION_DATE", "�䶯ʱ��");
		fieldMap.put("ORDER_NO", "�䶯����");
		fieldMap.put("ITEM_CATEGORY_NAME", "�豸רҵ");
		fieldMap.put("ITEM_NAME", "�豸����");
		fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
		fieldMap.put("WORKORDER_OBJECT_NAME", "���ڵص�");
		fieldMap.put("ADDRESS_NO", "��ϵ�ַ");
		fieldMap.put("RESPONSIBILITY_USER_NAME", "������");
		fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
        return fieldMap;
	}
}
