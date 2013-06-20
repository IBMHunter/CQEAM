package com.sino.nm.ams.inv.storeman.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.nm.ams.inv.storeman.dto.EamInvStoremanDTO;
import com.sino.nm.ams.inv.storeman.model.EamInvStoremanModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.DBActionConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EamInvStoremanDAO</p>
 * <p>Description:Ĭ�ϲֹ�Աά��</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yushibo
 * @version 1.0
 */
public class EamInvStoremanDAO extends AMSBaseDAO {

	EamInvStoremanModel storemanModel = null;

	/**
	 * ���ܣ�Ĭ�ϲֹ�Ա��(AMS) EAM_INV_STOREMAN ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamInvStoremanDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamInvStoremanDAO(SfUserDTO userAccount, EamInvStoremanDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		storemanModel = (EamInvStoremanModel)sqlProducer;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		super.sqlProducer = new EamInvStoremanModel((SfUserDTO) userAccount, (EamInvStoremanDTO) dtoParameter);
	}
	
	public void saveDatas(String[] storemanIds, String[] workorderObjectNos, String userId)throws DataHandleException{
		boolean operateResult = false;
		boolean autoCommit = true;
		CallableStatement cst = null;
		int dataCount = storemanIds.length;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(true);
//			String sqlStr = "BEGIN ? := EAM_INV_STOREMAN_PKG.updateStoremanRec(?, ?, ?, ? ,?); END;";
			String sqlStr = "{call dbo.EISP_UPDATESTOREMANREC(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sqlStr);
			String storemanId = "";
			int succeedRecord = 0;
			for(int i = 0; i < dataCount; i++){
				storemanId = storemanIds[i];
				cst.registerOutParameter(6, Types.INTEGER);
				cst.setString(1, storemanId);
				cst.setString(2, workorderObjectNos[i]);
				cst.setInt(3, StrUtil.strToInt(userId));
				cst.setInt(4, userAccount.getUserId());
				cst.registerOutParameter(5, Types.VARCHAR);
				cst.execute();
				succeedRecord += cst.getInt(6);
				
//				conn.setAutoCommit(true);
			}
			operateResult = (succeedRecord == dataCount);
		} catch (SQLException ex) {
			Logger.logError(ex.toString());
			throw new DataHandleException(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage("BATCH_SAVE_SUCCESS");
				} else {
					conn.rollback();
					prodMessage("BATCH_SAVE_FAILURE");
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
	 * ���ܣ��������²ֹ�Ա(AMS)��"EAM_INV_STOREMAN"���ݡ�
	 */
	public void updateDatas(String storemanId, String workorderObjectNo, String userId) throws DataHandleException {
		boolean autoCommit = true;
		DataHandleException error = null;
		CallableStatement cst = null;
		boolean operateResult = false;
		int success = 0;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(true);
//			String sqlStr = "BEGIN ? := EAM_INV_STOREMAN_PKG.updateStoremanRec(?, ?, ?, ? ,?); END;";
			String sqlStr = "{call dbo.EISP_UPDATESTOREMANREC(?,?,?,?,?,?)}";
			cst = conn.prepareCall(sqlStr);
			
			cst.registerOutParameter(6, Types.INTEGER);
			
//			if(storemanId == null  ||storemanId.equals("") ){
//				cst.setString(2, "");
//			} else {
//				cst.setString(2, storemanId);
//			}
			cst.setString(1, storemanId);
			cst.setString(2, workorderObjectNo);
			cst.setInt(3, StrUtil.strToInt(userId));
			cst.setInt(4, userAccount.getUserId());
			cst.registerOutParameter(5, Types.VARCHAR);
			
			cst.execute();
			
			success = cst.getInt(6);
			
			if(success > 0){
				operateResult = true;
			}else{
				Logger.logError(cst.getString(6));
			}
		} catch (SQLException ex) {
			Logger.logError(ex.toString());
			error = new DataHandleException(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
				} else {
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
				if(!operateResult){
					throw error;
				}
				DBManager.closeDBStatement(cst);
			} catch (SQLException ex) {
				Logger.logError(ex);
				throw new DataHandleException(ex);
			}
		}
	}
	
	/**
	 * ���ܣ���������ֹ�Ա(AMS)��"EAM_INV_STOREMAN"���ݡ�
	 */
	public void createDatas(String workorderObjectNo, String userId) throws DataHandleException {
		boolean operateResult = false;
		SQLModel sqlModel = null;
		sqlModel = storemanModel.getDatasCreateModel(workorderObjectNo, userId);
		if (sqlModel != null && !sqlModel.isEmpty()) {
			DBOperator.updateRecord(sqlModel, conn);
			
			operateResult = true;
		}
	}
	
	public void updateData(String storemanId) throws DataHandleException {
		boolean operateResult = false;
		SQLModel sqlModel = null;
		sqlModel = storemanModel.getDataUpdateModel(storemanId);
		if (sqlModel != null && !sqlModel.isEmpty()) {
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		}
	}
	
	/**
	 * ���ܣ�EAM_INV_STOREMAN����WORKORDER_OBJECT_NO�ļ�ֵ���ֿ��Ƿ��Ѵ��ڡ�
	 */
	public boolean getUniqueKeyDataDAO(String workorderObjectNo) throws QueryException {
//		EamInvStoremanDTO dto = null;
//		SQLModel sqlModel = null;
//		try{
//			sqlModel = storemanModel.getUniqueKeyDataModel(workorderObjectNo);
//			SimpleQuery splq = new SimpleQuery(sqlModel, conn);
//			splq.setCalPattern(getCalPattern());
//			splq.executeQuery();
//		}catch (QueryException ex) {
//			ex.printLog();
//			try {
//				throw new QueryException(ex);
//			} catch (QueryException e) {
//				e.printLog();
//			}
//		}
//		return dto;
		
	    SQLModel sqlModel = storemanModel.getUniqueKeyDataModel(workorderObjectNo);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);    
		sq.executeQuery();
		return sq.hasResult();
	}
	
	public EamInvStoremanDTO getDataUniqueKeyDAO(String workorderObjectNo) throws QueryException {
		EamInvStoremanDTO resource = new EamInvStoremanDTO();
		EamInvStoremanModel modelProducer = (EamInvStoremanModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getPrimaryKeyDataModel(workorderObjectNo);
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(EamInvStoremanDTO.class.getName());
		simp.executeQuery();
		if(simp.hasResult()){
			resource = (EamInvStoremanDTO)simp.getFirstDTO();
		}
		return resource;
	}
}
