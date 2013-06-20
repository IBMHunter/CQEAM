package com.sino.ams.workorder.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.TrunListDifferentModel;
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
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Author: 	����
 * Date: 2009-6-3
 * Time: 11:34:55
 * Function	:		ת���嵥����DAO
 */
public class TrunListDifferentDAO extends BaseDAO {
    public TrunListDifferentDAO(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        user = userAccount;
        initSQLProducer(userAccount, dtoParameter);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
    	EtsWorkorderDTO ffDTO=(EtsWorkorderDTO) dtoParameter;
        super.sqlProducer = new TrunListDifferentModel((SfUserDTO)userAccount, ffDTO);
    }
    
    private SfUserDTO user = null;
    
    /**
	 * ���ܣ���ȡת���嵥����Excel�ļ�
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile(EtsWorkorderDTO dto) throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = reportTitle = "ת���嵥����";
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
			custData.setReportPerson(user.getUsername());
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

    private Map getFieldMap(EtsWorkorderDTO dto){
    	Map fieldMap = new HashMap();
		fieldMap.put("SEGMENT1", "��Ŀ���");
        fieldMap.put("NAME", "��Ŀ����");
        fieldMap.put("ORG_NAME", "��˾");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
        fieldMap.put("DIFFERENCE_REASON", "����ԭ��");
        fieldMap.put("WORKORDER_NO", "������");
        
        fieldMap.put("IMPLEMENT_USER", "ִ����");
        fieldMap.put("CHECKOVER_USER", "�鵵��");
        
        fieldMap.put("BARCODE", "��ǩ��");
        fieldMap.put("ITEM_NAME", "�ʲ�����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("ITEM_STATUS_NAME", "�ʲ���ǰ״̬");
        fieldMap.put("SCAN_STATUS_NAME", "ɨ��״̬");
        fieldMap.put("ITEM_QTY", "����");
        fieldMap.put("ITEM_CATEGORY_DESC", "�ʲ����");
        fieldMap.put("DEPT_NAME", "���β���");
        fieldMap.put("USER_NAME", "������");
        return fieldMap;
	}
    

}
