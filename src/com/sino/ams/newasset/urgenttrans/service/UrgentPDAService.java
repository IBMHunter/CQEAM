package com.sino.ams.newasset.urgenttrans.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.urgenttrans.dto.UrgentDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;

public class UrgentPDAService extends UrgentBaseService{

	public UrgentPDAService(SfUserDTO user, UrgentDTO dto, Connection conn) {
		super(user, dto, conn);
	}

	/**
	 * �ϴ�������
	 */
	public boolean saveUploadTransOut(){
		boolean operateResult = false;
		boolean autoCommit = true; 
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false); 
			headerDTO.setTransStatus( AssetsDictConstant.SAVE_TEMP );
			updateHeaderStatus(); 
			operateResult = this.processProcedure();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			Logger.logError(ex);
		}  
		finally {
			try {
				if (!operateResult) {
					this.msg = "����ʧ��";
					conn.rollback(); 
				} else {
					this.msg = "����ɹ�";
					conn.commit(); 
				}
				conn.setAutoCommit(autoCommit); 
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}
	
	/**
	 * �ϴ�������
	 */
	public boolean saveUploadTransInt(){
		boolean operateResult = false;
		boolean autoCommit = true; 
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false); 
			headerDTO.setTransStatus( AssetsDictConstant.SAVE_TEMP );
			updateHeaderStatus();
			operateResult = this.processProcedure();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			Logger.logError(ex);
		} 
		finally {
			try {
				if (!operateResult) {
					this.msg = "����ʧ��";
					conn.rollback(); 
				} else {
					this.msg = "����ɹ�";
					conn.commit(); 
				}
				conn.setAutoCommit(autoCommit); 
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}
	
	public void updateHeaderStatus() throws DataHandleException{
		headerDAO.updateHeaderStatus( headerDTO );
	}

}
