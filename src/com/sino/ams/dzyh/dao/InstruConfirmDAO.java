package com.sino.ams.dzyh.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.constant.LvecMessageKeys;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.dzyh.model.OrderHeaderConfirmModel;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class InstruConfirmDAO extends AMSBaseDAO {
	private OrderLineConfirmDAO lineDAO = null;

	public InstruConfirmDAO(BaseUserDTO userAccount, EamDhCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		lineDAO = new OrderLineConfirmDAO(userAccount, null, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO)dtoParameter;
		sqlProducer = new OrderHeaderConfirmModel(userAccount, dto);
	}

	/**
	 * ���ܣ�ȷ���豸
	 * @param orderLines DTOSet
	 * @throws DataHandleException
	 */
	public void ConfirmInstrument(DTOSet orderLines) throws DataHandleException{
		boolean operateResult = false;
		boolean autoCommit = true;
		try {
			int itemCount = orderLines.getSize();
			EamDhCheckLineDTO dto = null;
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			for (int i = 0; i < itemCount; i++) {
				dto = (EamDhCheckLineDTO)orderLines.getDTO(i);

				lineDAO.setDTOParameter(dto);
				lineDAO.confirmItem();

				if (lineDAO.isLastBarcode()) {//�鵵������ͷ���С�д��־��д��ʷ
					setDTOParameter(dto);//EamDhCheckLineDTO��EamDhCheckHeaderDTO�����࣬��ֱ�����ø�DTO����
					archiveOrderHeader();
				}
			}
			operateResult = true;
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage(LvecMessageKeys.INSTR_CONFIRM_SUCCESS);
				} else {
					conn.rollback();
					prodMessage(LvecMessageKeys.INSTR_CONFIRM_FAILURE);
					message.setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
				throw new DataHandleException(ex);
			}
		}
	}

	/**
	 * ���ܣ��鵵����ͷ
	 * @throws DataHandleException
	 */
	private void archiveOrderHeader() throws DataHandleException{
		OrderHeaderConfirmModel modelProducer = (OrderHeaderConfirmModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getOrderArchiveModel();
		DBOperator.updateRecord(sqlModel, conn);
	}
}
