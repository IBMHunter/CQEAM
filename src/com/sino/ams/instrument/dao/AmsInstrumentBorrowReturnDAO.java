package com.sino.ams.instrument.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.instrument.dto.AmsInstrumentRegistrationDTO;
import com.sino.ams.instrument.model.AmsInstrumentBorrowReturnModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.Row;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

public class AmsInstrumentBorrowReturnDAO extends AMSBaseDAO {

	AmsInstrumentBorrowReturnModel modelProducer = null;
	
	
	/**
     * ���ܣ������Ǳ����(EAM) ETS_ITEM_INFO  ETS_SYSTEM_ITEM   ETS_OBJECT  EAM_ITEM_DISPOSE ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentRegistrationDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
	public AmsInstrumentBorrowReturnDAO(SfUserDTO userAccount,
			AmsInstrumentRegistrationDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		modelProducer = (AmsInstrumentBorrowReturnModel)sqlProducer;
	}

	/**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsInstrumentRegistrationDTO dtoPara = (AmsInstrumentRegistrationDTO) dtoParameter;
		super.sqlProducer = new AmsInstrumentBorrowReturnModel((SfUserDTO) userAccount, dtoPara);
	}

	/**
	 * ���ܣ����������Ǳ����-���÷�����¼(EAM)��"EAM_YB_BORROW_LOG"���ݡ�
	 * @throws CalendarException 
	 * 
	 */
	public void updateBorrowLogData(String[] barcodes) throws DataHandleException {
		boolean hasInv = false;
		boolean autoCommit = true;
		boolean operateResult = false;
		int succeedRecord = 0;
		CallableStatement cst = null;
		try {
			AmsInstrumentBorrowReturnModel modelProducer = (AmsInstrumentBorrowReturnModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getInsNorInvModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			hasInv = simp.hasResult();
			
			if(hasInv){//�������Ǳ�������
				Row row = simp.getFirstRow();
				String workorderObjectNo = row.getStrValue("WORKORDER_OBJECT_NO");
				
				autoCommit = conn.getAutoCommit();
		        conn.setAutoCommit(false);
		        
		        String sqlStr = "BEGIN ? := ETS_INSTRUMENT_CARD_PKG.EII_INSTRUMENT_RETURN(?,?,?,?,?); END;";
		        cst = conn.prepareCall(sqlStr);

		        cst.registerOutParameter(1, Types.INTEGER);
				cst.setInt(2, userAccount.getOrganizationId());
				cst.setInt(4, StrUtil.strToInt(workorderObjectNo));
				cst.registerOutParameter(5, Types.VARCHAR);
				cst.registerOutParameter(6, Types.VARCHAR);
				int barcodeCount = barcodes.length;
				
				for(int i = 0; i < barcodeCount; i++){
					cst.setString(3, barcodes[i]);
					cst.execute();
					succeedRecord += cst.getInt(1);
		        }
				
				operateResult = (succeedRecord == barcodeCount);
			}
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} catch (QueryException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} catch (ContainerException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} finally{
			try {
				if(hasInv){
					if (operateResult) {
						conn.commit();
						prodMessage("INS_RETURN_SUCCESS");
					  } else {
						conn.rollback();
						prodMessage("INS_RETURN_FAILURE");
						message.setIsError(true);
					}
				} else {
					prodMessage("INSTRUMENT_INV_TYPE_NOT_EXIST");
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
}
