package com.sino.ams.workorder.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.TrunListPrintQueryModel;
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

public class TrunListPrintQueryDAO extends BaseDAO {
	private SfUserDTO user = null;

	public TrunListPrintQueryDAO(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        user = userAccount;
        initSQLProducer(userAccount, dtoParameter);
    }
	
	@Override
	protected void initSQLProducer(BaseUserDTO dto, DTO dtoParameter) {
		// TODO Auto-generated method stub
		EtsWorkorderDTO ffDTO=(EtsWorkorderDTO) dtoParameter;
//        super.sqlProducer = new TrunListQueryModel((SfUserDTO)userAccount, ffDTO);
        super.sqlProducer = new TrunListPrintQueryModel((SfUserDTO)userAccount, ffDTO);
        
	}
	
	
	/**
	 * ���ܣ���ȡת�������ѯExcel�ļ�
	 * @return
	 * @throws DataTransException
	 */
	public File exportFile(EtsWorkorderDTO dto,String opinion,List partBarCode) throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel;
			if(opinion.equals("part")){
				 dto.setOpinionType("part");
				 dto.setPartBarcode(partBarCode);
				 sqlModel = sqlProducer.getPageQueryModel();
			}else{
				 sqlModel = sqlProducer.getPageQueryModel();
			}
			String reportTitle = reportTitle = "ת�������ѯ";
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
		fieldMap.put("BARCODE", "��ǩ��");
		fieldMap.put("PRINT_NUM", "��ʷ��ӡ����");
		fieldMap.put("ASSETS_LOCATION_CODE", "�ص���");
        fieldMap.put("ASSETS_LOCATION", "�ص���");
		fieldMap.put("SEGMENT1", "��Ŀ���");
        fieldMap.put("NAME", "��Ŀ����");
        fieldMap.put("ORG_NAME", "��˾");
        
        
        fieldMap.put("ITEM_NAME", "�ʲ�����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("ITEM_QTY", "����");
        fieldMap.put("ITEM_CATEGORY_DESC", "�ʲ����");
        fieldMap.put("DEPT_NAME", "���β���");
        fieldMap.put("USER_NAME", "������");
        
        fieldMap.put("MAINTAIN_DEPT", "ʹ�ò���");
        fieldMap.put("MAINTAIN_USER", "ʹ����");
        
        fieldMap.put("CREATION_DATE", "��������");
        fieldMap.put("LAST_UPDATE_DATE", "����޸�����");
        
        return fieldMap;
	}
}
