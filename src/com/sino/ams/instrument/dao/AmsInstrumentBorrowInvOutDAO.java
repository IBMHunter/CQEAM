package com.sino.ams.instrument.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.instrument.dto.AmsInstrumentRegistrationDTO;
import com.sino.ams.instrument.model.AmsInstrumentBorrowInvOutModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

public class AmsInstrumentBorrowInvOutDAO extends AMSBaseDAO {

	AmsInstrumentBorrowInvOutModel modelProducer = null;
	
	/**
     * ���ܣ������Ǳ����(EAM) ETS_ITEM_INFO  ETS_SYSTEM_ITEM   ETS_OBJECT  EAM_ITEM_DISPOSE ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentRegistrationDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
	public AmsInstrumentBorrowInvOutDAO(SfUserDTO userAccount,
			AmsInstrumentRegistrationDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		modelProducer = (AmsInstrumentBorrowInvOutModel)sqlProducer;
	}

	/**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsInstrumentRegistrationDTO dtoPara = (AmsInstrumentRegistrationDTO) dtoParameter;
		super.sqlProducer = new AmsInstrumentBorrowInvOutModel((SfUserDTO) userAccount, dtoPara);
	}

	/**
	 * ���ܣ����������Ǳ����-ʵ������¼(EAM)��"EAM_YB_BORROW_LOG"���ݡ�
	 * @throws CalendarException 
	 * 
	 */
	public void updateBorrowLogData(Connection conn, String barcode, String handleUserId, 
			String handleDate, String responsibilityUser, 
			String borrowDate, String workorderObjectNo) throws DataHandleException, CalendarException {
		
//		System.out.println("��ʼ��ӡ�����б�");
//		System.out.println(barcode);
//		System.out.println(handleUserId);
//		System.out.println(handleDate);
//		System.out.println(responsibilityUser);
//		System.out.println(borrowDate);
//		System.out.println(workorderObjectNo);
		
		boolean autoCommit = true;
		DataHandleException error = null;
		boolean operateResult = false;

		try {
			autoCommit = conn.getAutoCommit();
	        conn.setAutoCommit(false);
	        int success = 0;

	        success = modelProducer.updateEamYbBorrowLog(conn, handleDate, handleUserId, borrowDate, responsibilityUser, barcode, workorderObjectNo);
	        	
        	if(success > 0) {
        		operateResult = true;
        	}
		} catch (SQLException ex) {
			Logger.logError(ex);
			error = new DataHandleException(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage("INSTRUMENT_INV_OUT_SUCCESS");
				} else {
					conn.rollback();
					prodMessage("INSTRUMENT_INV_OUT_FAILURE");
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
