package com.sino.sinoflow.todo.dao;

import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.todo.dto.OaTodoDTO;
import com.sino.sinoflow.todo.model.OaTodoModel;

/**
 * 
 * @ϵͳ����:
 * @��������:
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Nov 30, 2011
 */
public class OaTodoDAO extends BaseDAO {
	protected OaTodoDTO oaTodoForm = null;

	public OaTodoDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		this.initSQLProducer(userAccount, dtoParameter);
	}

	@Override
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		oaTodoForm = (OaTodoDTO) dtoParameter;
		sqlProducer = new OaTodoModel((SfUserDTO) userAccount, oaTodoForm);
	}

	/**
	 * ��EAM����������ݵ��м��OA_TODO
	 * 
	 * @param appId
	 * @return
	 */
	public boolean insertOaTODOFromEAM(String appId) {
		if (StrUtil.isEmpty(appId)) {
			return false;
		}
		boolean isSuccess = false;
		OaTodoModel oaTodoModel = null;
		try {
			oaTodoModel = (OaTodoModel) sqlProducer;
			if (oaTodoModel != null) {
				oaTodoModel.setDTOParameter(oaTodoForm);
				isSuccess = DBOperator.updateRecord(oaTodoModel
						.getInsertOATodoFromEAMModel(appId), conn);
			}
		} catch (Exception ex) {
		} finally {
			return isSuccess;
		}
	}

//	/**
//	 * ����������������ύ�����Ч ��EAM�����Ѱ����ݵ��м��OA_TODO_DELE
//	 * 
//	 * @param appId
//	 * @return
//	 */
//	public boolean insertOaTodoDeleFromEAM(String appId, String actId) {
//		if (StrUtil.isEmpty(appId)) {
//			return false;
//		}
//		boolean isSuccess = false;
//		OaTodoModel oaTodoModel = null;
//		try {
//			oaTodoModel = (OaTodoModel) sqlProducer;
//			if (oaTodoModel != null) {
//				oaTodoModel.setDTOParameter(oaTodoForm);
//				isSuccess = DBOperator.updateRecord(oaTodoModel
//						.getInsertOATodoDeleFromEAMModel(appId, actId), conn);
//			}
//		} catch (Exception ex) {
//		} finally {
//			return isSuccess;
//		}
//	}

	/**
	 * ����������������ύǰ����Ч,һ��ʹ�������������ʹ������2�������� ��EAM�����Ѱ����ݵ��м��OA_TODO_DELE
	 * 
	 * @param appId
	 * @return
	 */
	public boolean insertOaTodoDeleFromEAM(String appId) {
		if (StrUtil.isEmpty(appId)) {
			return false;
		}
		boolean isSuccess = false;
		OaTodoModel oaTodoModel = null;
		try {
			oaTodoModel = (OaTodoModel) sqlProducer;
			if (oaTodoModel != null) {
				oaTodoModel.setDTOParameter(oaTodoForm);
				isSuccess = DBOperator.updateRecord(oaTodoModel
						.getInsertOATodoDeleFromEAMModel(appId), conn);
			}
		} catch (Exception ex) {
		} finally {
			return isSuccess;
		}
	} 

}
