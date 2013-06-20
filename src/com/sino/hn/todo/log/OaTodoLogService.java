package com.sino.hn.todo.log;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.beanutils.BeanUtils;

import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.hn.todo.dto.OaResponseDTO;
import com.sino.hn.todo.log.dao.OaTodoLogDAO;
import com.sino.hn.todo.log.dto.OaTodoLogDTO;
import com.sino.sinoflow.todo.dto.OaTodoDTO;

/**
 * 
 * @ϵͳ����:
 * @��������:
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Dec 1, 2011
 */
public class OaTodoLogService {

	public OaTodoLogService() {
	}

	public void saveLog(OaTodoLogDTO logDTO, Connection conn)
			throws DataHandleException {
		OaTodoLogDAO oaTodoLogDAO = new OaTodoLogDAO(null, logDTO, conn);
		oaTodoLogDAO.createData();
	}

	/**
	 * �Է�������
	 * 
	 * @param logDTO
	 */
	public void saveLog(OaTodoLogDTO logDTO) {
		boolean isSuccess = false;
		Connection conn = null;
		try {
			conn = DBManager.getDBConnection();
			saveLog(logDTO, conn);
			isSuccess = true;
		} catch (PoolException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			Logger.logError(ex);
		} catch (Throwable ex) {
			Logger.logError(ex);
		} finally {
			if( isSuccess ){
				try {
					conn.commit();
				} catch (SQLException e) {
					Logger.logError( e );
				}
			}else{
				try {
					conn.rollback();
				} catch (SQLException e) {
					Logger.logError( e );
				}
			}
			DBManager.closeDBConnection(conn);
		}
	}

	/**
	 * �Է�������
	 * 
	 * @param logDTO
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void saveLog(OaTodoDTO oaTodoDTO, OaResponseDTO responseDTO) {
		OaTodoLogDTO logDTO = new OaTodoLogDTO();
		try {
			BeanUtils.copyProperties(logDTO, oaTodoDTO);
			if( StrUtil.isEmpty( responseDTO.getResultDesc() ) ){
				responseDTO.setResultDesc( responseDTO.getEamMsg() );
			}
			BeanUtils.copyProperties(logDTO, responseDTO);
			this.saveLog(logDTO);
		} catch (IllegalAccessException ex) {
			Logger.logError(ex);
		} catch (InvocationTargetException ex) {
			Logger.logError(ex);
		}
	}

	/**
	 * ��������
	 * 
	 * @param logDTO
	 * @throws DataHandleException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void saveLogInTrans(OaTodoDTO oaTodoDTO, OaResponseDTO responseDTO, Connection conn ) throws DataHandleException, IllegalAccessException, InvocationTargetException {
		OaTodoLogDTO logDTO = new OaTodoLogDTO();
		BeanUtils.copyProperties(logDTO, oaTodoDTO);
		BeanUtils.copyProperties(logDTO, responseDTO);
		this.saveLogInTrans(logDTO , conn );
	}
	
	public void saveLogInTrans(OaTodoDTO oaTodoDTO,  Connection conn ) throws DataHandleException, IllegalAccessException, InvocationTargetException {
		OaTodoLogDTO logDTO = new OaTodoLogDTO();
		BeanUtils.copyProperties(logDTO, oaTodoDTO);
		this.saveLogInTrans(logDTO , conn );
	}

	/**
	 * ��������
	 * 
	 * @param logDTO
	 * @throws DataHandleException
	 */
	public void saveLogInTrans(OaTodoLogDTO logDTO, Connection conn)
			throws DataHandleException {
		saveLog(logDTO, conn);
	}
}
