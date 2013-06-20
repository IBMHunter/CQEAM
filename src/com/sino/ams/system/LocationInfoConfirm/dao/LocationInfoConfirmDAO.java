package com.sino.ams.system.LocationInfoConfirm.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.LocationInfoConfirm.dto.LocationInfoConfirmDTO;
import com.sino.ams.system.LocationInfoConfirm.model.LocationInfoConfirmModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * @author Administrator
 *
 */
public class LocationInfoConfirmDAO extends AMSBaseDAO {
	
	LocationInfoConfirmModel model = (LocationInfoConfirmModel)sqlProducer;
	
	public LocationInfoConfirmDAO(SfUserDTO userAccount, LocationInfoConfirmDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		initSQLProducer(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		LocationInfoConfirmDTO dtoPara = (LocationInfoConfirmDTO) dtoParameter;
		super.sqlProducer = new LocationInfoConfirmModel((SfUserDTO) userAccount, dtoPara);
	}
	
	public String getLocationName(String workorderObjectNo,String transNo) throws QueryException, ContainerException {
		LocationInfoConfirmModel model = (LocationInfoConfirmModel)sqlProducer;
		SQLModel sqlModel = model.getWorkorderObjectNameModel(workorderObjectNo,transNo);
		SimpleQuery sq = new SimpleQuery(sqlModel,conn);
		sq.executeQuery();
		Row row = sq.getSearchResult().getRow(0);
		String locationName = (String) row.getValue("NEW_LOCATION");
		return locationName;
	}

	public boolean confirm(String[] workorderObjectNos,String[] transNos) throws QueryException, ContainerException { 
		boolean operateResult = false;
		boolean autoCommit = true;
		CallableStatement cStmt = null;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String status = "��ȷ��";
			for (int i = 0; i < workorderObjectNos.length; i++) {
				String locationName = getLocationName(workorderObjectNos[i],transNos[i]);
				DBOperator.updateRecord(model.confirmModel(locationName,workorderObjectNos[i]),conn);
				DBOperator.updateRecord(model.insertIntoCheck(workorderObjectNos[i],status), conn);
			}
			getMessage().addParameterValue("�ʲ��ص���Ϣȷ��");
			operateResult = true;
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLModelException e) {
			Logger.logError(e);
		} finally {
			try {
				if (!operateResult) {
					conn.rollback();                      //�ع�
					prodMessage(CustMessageKey.ITEM_CONFIRM_FAILURE);
				} else {
					conn.commit();
					prodMessage(CustMessageKey.ITEM_CONFIRM_SUCCESS);
				}
				getMessage().setIsError(!operateResult);
				conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
				DBManager.closeDBStatement(cStmt);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
			}
		}
		return operateResult;
	}
	
	public boolean cancel(String[] workorderObjectNos,String[] transNos) throws QueryException, ContainerException { 
		boolean operateResult = false;
		boolean autoCommit = true;
		CallableStatement cStmt = null;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String status = "��ȡ��";
			for (int i = 0; i < workorderObjectNos.length; i++) {
				String locationName = getLocationName(workorderObjectNos[i],transNos[i]);
				DBOperator.updateRecord(model.insertIntoCheck(workorderObjectNos[i],status), conn);
			}
			getMessage().addParameterValue("�ʲ��ص���Ϣȷ��");
			operateResult = true;
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLModelException e) {
			Logger.logError(e);
		} finally {
			try {
				if (!operateResult) {
					conn.rollback();                      //�ع�
					prodMessage(CustMessageKey.ITEM_CONFIRM_FAILURE);
				} else {
					conn.commit();
					prodMessage(CustMessageKey.ITEM_CONFIRM_SUCCESS);
				}
				getMessage().setIsError(!operateResult);
				conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
				DBManager.closeDBStatement(cStmt);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
			}
		}
		return operateResult;
	}
	
	public RowSet getQueryPage() throws QueryException, SQLModelException {
		SQLModel sqlModel = model.getQueryPageData();
		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
		sq.executeQuery();
		sq.setTotalSummary(false);
		RowSet rows = sq.getSearchResult();
		return rows;		
	}
}
