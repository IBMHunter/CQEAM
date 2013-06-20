package com.sino.ams.workorder.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.workorder.dto.ZeroTurnBursurHDTO;
import com.sino.ams.workorder.dto.ZeroTurnLineDTO;
import com.sino.ams.workorder.dto.ZeroturnLineBursurDTO;
import com.sino.ams.workorder.model.ZeroTurnBursurModel;
import com.sino.ams.workorder.model.ZeroTurnLineModel;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.DeptGroupDTO;
import com.sino.sinoflow.model.DeptGroupModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

public class ZeroTurnBursurDAO extends BaseDAO {

	public ZeroTurnBursurDAO(BaseUserDTO userAccount, DTO dtoParameter,
			Connection conn) {
		super(userAccount, dtoParameter, conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initSQLProducer(BaseUserDTO arg0, DTO arg1) {
		// TODO Auto-generated method stub
		ZeroTurnBursurHDTO dtoPara = (ZeroTurnBursurHDTO)dtoParameter;
		super.sqlProducer = new ZeroTurnBursurModel((SfUserBaseDTO)userAccount, dtoPara);
	}
	
	public void reimburser(DTOSet deptLines){
		try {
		 if(deptLines == null)
	            return;
	        for(int i = 0; i < deptLines.getSize(); i++) {
	            ZeroturnLineBursurDTO  line = (ZeroturnLineBursurDTO)deptLines.getDTO(i);
	            if(line.getIsCheck().equals("0")) {
	                continue;
	            }
	            String barcode=line.getBarcode();
	            ZeroTurnBursurModel model = (ZeroTurnBursurModel) sqlProducer;
	            SQLModel sqlModel = new SQLModel();
	            sqlModel=model.reimburser(barcode);
			    DBOperator.updateRecord(sqlModel, conn);
	        }
		} catch (DataHandleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void reimburserCancel(DTOSet deptLines){
		try {
		 if(deptLines == null)
	            return;
	        for(int i = 0; i < deptLines.getSize(); i++) {
	            ZeroturnLineBursurDTO  line = (ZeroturnLineBursurDTO)deptLines.getDTO(i);
	            if(line.getIsCheck().equals("0")) {
	                continue;
	            }
	            String barcode=line.getBarcode();
	            ZeroTurnBursurModel model = (ZeroTurnBursurModel) sqlProducer;
	            SQLModel sqlModel = new SQLModel();
	            sqlModel=model.reimburserCancle(barcode);
			    DBOperator.updateRecord(sqlModel, conn);
	        }
		} catch (DataHandleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 public File exportFile() throws DataTransException, SQLModelException {           //����
	        File file = null;
	        SQLModel sqlModel = sqlProducer.getPageQueryModel();
	        TransRule rule = new TransRule();
	        rule.setDataSource(sqlModel);
	        rule.setSourceConn(conn);
	        String fileName = "�㹺������Ϣ�嵥.xls";
	        String filePath = WorldConstant.USER_HOME;
	        filePath += WorldConstant.FILE_SEPARATOR;
	        filePath += fileName;
	        rule.setTarFile(filePath);
	        DataRange range = new DataRange();
	        rule.setDataRange(range);

	        Map fieldMap = new HashMap();
	        fieldMap.put("MIS_PROCURE_CODE", "���������");
	        fieldMap.put("BARCODE", "�ʲ���ǩ��");
	        fieldMap.put("IS_RECEIVED", "����״̬");
	        fieldMap.put("REIMBURSE_STATUS_NAME", "����״̬");
	        fieldMap.put("REIMBURSE_DATE", "��������");
	        fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
	        fieldMap.put("ITEM_SPEC", "����ͺ�");
	        fieldMap.put("OBJECT_NO", "�ص���");
	        fieldMap.put("RESPONSIBILITY_USER", "������");
	        fieldMap.put("RESPONSIBILITY_DEPT", "���β���");
	        fieldMap.put("MANUFACTURER_NAME", "������");
	        fieldMap.put("PRICE", "���");

	        rule.setFieldMap(fieldMap);

	        CustomTransData custData = new CustomTransData();
	        custData.setReportTitle("�㹺������Ϣ�嵥");
	        custData.setReportPerson(((SfUserBaseDTO)userAccount).getUsername());
	        custData.setNeedReportDate(true);
	        rule.setCustData(custData);
	        TransferFactory factory = new TransferFactory();
	        DataTransfer transfer = factory.getTransfer(rule);
	        transfer.transData();
	        file = (File) transfer.getTransResult();
	        return file;
	    }


}
