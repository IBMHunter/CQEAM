package com.sino.nm.ams.instrument.dao;

import java.sql.*;

import com.sino.base.db.conn.DBManager;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;

import com.sino.framework.dto.BaseUserDTO;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.nm.ams.instrument.dto.AmsInstrumentEamYbChkMaintainDTO;
import com.sino.nm.ams.instrument.model.AmsInstrumentEamYbChkMaintainModel;
import com.sino.ams.system.user.dto.SfUserDTO;

public class AmsInstrumentEamYbChkMaintainDAO extends AMSBaseDAO {

	AmsInstrumentEamYbChkMaintainModel modelProducer = null;

	/**
	 * ���ܣ������Ǳ����(AMS) ETS_ITEM_INFO  ETS_SYSTEM_ITEM   ETS_OBJECT  EAM_ITEM_DISPOSE ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsInstrumentEamYbChkMaintainDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsInstrumentEamYbChkMaintainDAO(
			SfUserDTO userAccount, 
			AmsInstrumentEamYbChkMaintainDTO dtoParameter, 
			Connection conn
			) {
		super(userAccount, dtoParameter, conn);
		modelProducer = (AmsInstrumentEamYbChkMaintainModel)sqlProducer;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsInstrumentEamYbChkMaintainDTO dtoPara = (AmsInstrumentEamYbChkMaintainDTO) dtoParameter;
		super.sqlProducer = new AmsInstrumentEamYbChkMaintainModel((SfUserDTO)userAccount, dtoPara);
//		setDTOParameter(dtoParameter);
	}

	/**
	 * ���ܣ����������Ǳ����-�ǼǼ�¼(AMS)��"EAM_YB_CHK_MAINSTAIN"���ݡ�
	 * @param dtos DTOSet
	 * @throws DataHandleException
	 * @throws SQLException 
	 */
	public void updateYbChkMaintainData(String[] barcodes, String[] checkUserIds, String[] remarks, String[] checkStatuses, String[] checkDates, String[] statuses) throws DataHandleException {
		AmsInstrumentEamYbChkMaintainDTO dto = (AmsInstrumentEamYbChkMaintainDTO)dtoParameter;
		
		boolean autoCommit = true;
		boolean operateResult = false;
		int succeedRecord = 0;
		int count = 0;
		CallableStatement cst = null;
		try {
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(true);

//				String sqlStr = "BEGIN ? := ETS_INSTRUMENT_CARD_PKG.EII_INSTRUMENT_CHK_MAINTAIN(?,?,?,?,?,?,?,?,?,?,?); END;";
				String sqlStr = "{call dbo.EICP_EII_INSTRUMENT_CHK_MAINTAIN(?,?,?,?,?,?,?,?,?,?,?)}";
				
				cst = conn.prepareCall(sqlStr);

//				cst.registerOutParameter(1, Types.INTEGER);
				cst.setInt(7, userAccount.getOrganizationId());
				cst.setString(8, dto.getTaskId());
				cst.setInt(9, userAccount.getUserId());
				cst.registerOutParameter(10, Types.VARCHAR);
				cst.registerOutParameter(11, Types.INTEGER);

				int barcodeCount = barcodes.length;

				for(int i = 0; i < barcodeCount; i++){
					
					if(canEscape(checkStatuses[i], checkDates[i], checkUserIds[i])){
						count++;
						continue;
					}

					cst.setString(1, barcodes[i]);
					cst.setString(2, statuses[i]);
					cst.setInt(3, StrUtil.strToInt(checkUserIds[i]));
					cst.setString(4, checkDates[i]);
					cst.setString(5, checkStatuses[i]);
					cst.setString(6, remarks[i]);
					cst.execute();
					
					succeedRecord += cst.getInt(11);
				}
				
//				operateResult = (succeedRecord == barcodeCount);
				operateResult = (succeedRecord == (barcodeCount - count));
	
		} catch (Exception ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage("INS_CHK_MAINTAIN_SUCCESS");
				  } else {
					conn.rollback();
					prodMessage("INS_CHK_MAINTAIN_FAILURE");
					message.setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
				DBManager.closeDBStatement(cst);
			} catch (SQLException ex) {
				Logger.logError(ex);
				throw new DataHandleException(ex);
			}
		}
	}

	/**
	 * ���ܣ�����������һ��Ϊ�յľ������ķ���
	 * @param checkStatus
	 * @param checkDate
	 * @param checkUserId
	 * @return
	 * @throws CalendarException
	 */
	private boolean canEscape(String checkStatus, String checkDate, String checkUserId) throws CalendarException {
		boolean canEscape = false;
		if(checkStatus.equals("")){
			canEscape = true;
		} else if(checkDate.equals("")){
			canEscape = true;
		} else if(checkUserId.equals("")){
			canEscape = true;
		}
		return canEscape;
	}
}
