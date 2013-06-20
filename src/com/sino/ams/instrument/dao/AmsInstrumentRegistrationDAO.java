package com.sino.ams.instrument.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.instrument.dto.AmsInstrumentRegistrationDTO;
import com.sino.ams.instrument.model.AmsInstrumentRegistrationModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsInstrumentRegistrationDAO</p>
 * <p>Description:�����Զ����ɷ������AmsInstrumentRegistrationDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yushibo
 * @version 1.0
 */
public class AmsInstrumentRegistrationDAO extends AMSBaseDAO {
	
	AmsInstrumentRegistrationModel modelProducer = null;

	/**
     * ���ܣ������Ǳ����(EAM) ETS_ITEM_INFO  ETS_SYSTEM_ITEM   ETS_OBJECT  EAM_ITEM_DISPOSE ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentRegistrationDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
	public AmsInstrumentRegistrationDAO(SfUserDTO userAccount, AmsInstrumentRegistrationDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		modelProducer = (AmsInstrumentRegistrationModel)sqlProducer;
	}

	/**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsInstrumentRegistrationDTO dtoPara = (AmsInstrumentRegistrationDTO) dtoParameter;
		super.sqlProducer = new AmsInstrumentRegistrationModel((SfUserDTO) userAccount, dtoPara);
	}
	
	/**
     * ���ܣ����������Ǳ����(EAM)��ETS_ITEM_INFO  ETS_SYSTEM_ITEM   ETS_OBJECT  EAM_ITEM_DISPOSE�����ݡ�
     * @return boolean
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("�����Ǳ����(EAM)");
    }
    
    /**
     * ���ܣ����������Ǳ�ʧЧ(EAM)��ETS_ITEM_INFO �е�DISABLE_DATE�ֶΡ����ݡ�
     * @return boolean
     */
    public void updateData(String barcode) throws DataHandleException {
		boolean operateResult = false;
		SQLModel sqlModel = null;
		sqlModel = modelProducer.getDataUpdateModel(barcode);
		if (sqlModel != null && !sqlModel.isEmpty()) {
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		}
	}
    
    /**
     * ���ܣ��������������Ǳ�ʧЧ(EAM)��ETS_ITEM_INFO �е�DISABLE_DATE�ֶΡ����ݡ�
     * @return boolean
     */
    public void updateDatas(String[] barcodes) throws DataHandleException {
		boolean operateResult = false;
		SQLModel sqlModel = null;
		for(int i=0; i<barcodes.length; i++){
			sqlModel = modelProducer.getDataUpdateModel(barcodes[i]);
			if (sqlModel != null && !sqlModel.isEmpty()) {
				DBOperator.updateRecord(sqlModel, conn);
				operateResult = true;
			}
		}
	}
	
	public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);

            String fileName = "�����Ǳ�Ǽǿ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("ORGNIZATION_NAME", "��˾");
            fieldMap.put("BARCODE", "����");
            fieldMap.put("ITEM_NAME", "�����Ǳ�����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("VENDOR_NAME", "�Ǳ���");
            fieldMap.put("MAINTAIN_DEPT_NAME", "ʹ�ò���");
            fieldMap.put("ITEM_QTY", "����");
            fieldMap.put("ATTRIBUTE3", "��;");
            fieldMap.put("OBJECT_NAME", "ʹ�õص�");
            fieldMap.put("MAINTAIN_USER", "ʹ����Ա");
            fieldMap.put("REMARK", "�Ǳ�����");
            fieldMap.put("ITEM_STATUS", "�Ǳ�״̬");
            fieldMap.put("RESPONSIBILITY_NAME", "������");
            fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
            fieldMap.put("ATTRIBUTE2", "����");
            fieldMap.put("TOTAL_PR", "�ܼ�");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�����Ǳ�Ǽǿ�");
            custData.setReportPerson(userAccount.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            /*rule.setSheetSize(1000);*/
            //���÷�ҳ��ʾ
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

	/**
	 * ���ܣ����������Ǳ�Ǽǿ���¼(EAM)��"ETS_ITEM_INFO"���ݡ�
	 * @throws CalendarException 
	 * 
	 */
	public void createCardData(Connection conn, String barcode, String vendorCode, String itemQty,
			String price, String attribute3, String responsibilityDept, 
			String responsibilityUser, String startDate, String workorderObjectNo,
			String itemStatus, String maintainUser, String remark, String ifpage, String itemCode) throws DataHandleException, CalendarException {
		
		boolean autoCommit = true;
		DataHandleException error = null;
		boolean operateResult = false;
		
		try {
			autoCommit = conn.getAutoCommit();
	        conn.setAutoCommit(false);
	        int success = 0;
	        success = modelProducer.createEtsItemInfoCard(conn, barcode, vendorCode, itemQty, price, attribute3, responsibilityDept, responsibilityUser, startDate, workorderObjectNo, itemStatus, maintainUser, remark, ifpage, itemCode);
	        if(success > 0){
	        	operateResult = true;
	        }
		} catch (SQLException ex) {
			Logger.logError(ex);
			error = new DataHandleException(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage("INSTRUMENT_CARD_CREATE_SUCCESS");
				} else {
					conn.rollback();
					prodMessage("INSTRUMENT_CARD_CREATE_FAILURE");
					message.setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
				if(!operateResult){
					throw error;
				}
			} catch (SQLException ex) {
				Logger.logError(ex);
				throw new DataHandleException(ex);
			}
		}
	}
	
	/**
	 * ���ܣ����������Ǳ�Ǽǿ���¼(EAM)��"ETS_ITEM_INFO"���ݡ�
	 * @throws CalendarException 
	 * 
	 */
	public void updateCardData(Connection conn, String oldBarcode, String barcode, String vendorCode, String itemQty,
			String price, String attribute3, String responsibilityDept, 
			String responsibilityUser, String startDate, String workorderObjectNo,
			String itemStatus, String maintainUser, String remark, String ifpage, String itemCode) throws DataHandleException, CalendarException {
		
		boolean autoCommit = true;
		DataHandleException error = null;
		boolean operateResult = false;
		
		try {
			autoCommit = conn.getAutoCommit();
	        conn.setAutoCommit(false);
	        int success = 0;
	        success = modelProducer.updateEtsItemInfoCard(conn, oldBarcode, barcode, vendorCode, itemQty, price, attribute3, responsibilityDept, responsibilityUser, startDate, workorderObjectNo, itemStatus, maintainUser, remark, ifpage, itemCode);
	        if(success > 0){
	        	operateResult = true;
	        }
		} catch (SQLException ex) {
			Logger.logError(ex);
			error = new DataHandleException(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage("INSTRUMENT_CARD_UPDATE_SUCCESS");
				} else {
					conn.rollback();
					prodMessage("INSTRUMENT_CARD_UPDATE_FAILURE");
					message.setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
				if(!operateResult){
					throw error;
				}
			} catch (SQLException ex) {
				Logger.logError(ex);
				throw new DataHandleException(ex);
			}
		}
	}
}
