package com.sino.ams.newasset.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.ItemInfoHistoryDTO;
import com.sino.ams.newasset.model.ItemInfoHistoryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>Title: AmsItemInfoHistoryDAO</p>
 * <p>Description:�����Զ����ɷ������AmsItemInfoHistoryDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class ItemInfoHistoryDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter ItemInfoHistoryDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public ItemInfoHistoryDAO(SfUserDTO userAccount, ItemInfoHistoryDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		sqlProducer = new ItemInfoHistoryModel(userAccount, dtoParameter);
	}

    public File getExportFile(String excelType) throws DataTransException {
		ItemInfoHistoryModel modelProducer = (ItemInfoHistoryModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getPageQueryModel();
		String reportTitle = "�豸�䶯��ʷ����";
		if (!StrUtil.isNotEmpty(excelType)) {
			excelType = "xls";
		}
		String fileName = reportTitle + "." + excelType;
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
		fieldMap.put("LOG_USER", "�䶯��");
		fieldMap.put("ORDER_NO", "���ݺ�");
		fieldMap.put("ITEM_CATEGORY_NAME", "�豸רҵ");
		
		fieldMap.put("ITEM_NAME", "�豸����");
		fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
		fieldMap.put("LOG_NET_ELE", "�߼�����Ԫ��");
		fieldMap.put("INVEST_CAT_NAME", "Ͷ�ʷ���");
		fieldMap.put("OPE_NAME", "ҵ��ƽ̨");
		
		fieldMap.put("LNE_NAME", "������");
		fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
		fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
        fieldMap.put("DEPT_NAME", "���β���");
        fieldMap.put("USER_NAME", "������");
        
        fieldMap.put("EMPLOYEE_NUMBER", "�����˱��");
        fieldMap.put("MAINTAIN_USER", "ʹ����");
		fieldMap.put("OLD_CONTENT_CODE", "��Ŀ¼����");
		fieldMap.put("CONTENT_CODE", "Ŀ¼����");
		fieldMap.put("CONTENT_NAME", "Ŀ¼����");
		
		fieldMap.put("ITEM_STATUS_NAME", "�豸״̬");
		
        return fieldMap;
	}
}
