package com.sino.sms.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.sms.dto.SfMsgDefineDTO;

/**
 * Created by IntelliJ IDEA.
 * User: GLJ
 * Date: 2008-5-5
 * Time: 11:27:06
 * ChangeList:2008-07-28:���Ӳ���userId�����û�Id��Ϣ  ---zhoujs
 */
public class MessageSaver {
    private Connection conn = null;

    public MessageSaver(Connection conn) {
        this.conn = conn;
    }

	/**
	 * ���ܣ��������Ϣ���ݵ����ݿ����
	 * @param msgDefineDTO SfMsgDefineDTO
	 * @throws DataHandleException
	 */
	public void saveMsg(SfMsgDefineDTO msgDefineDTO) throws DataHandleException{
        String strSql = "{CALL SF_MSG.SAVE_MESSAGE(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement callStat = null;
		try {
			callStat = conn.prepareCall(strSql);
			callStat.setString(1, msgDefineDTO.getMsgCategoryId());
			callStat.setString(2, msgDefineDTO.getMsgContent());
			callStat.setString(3, msgDefineDTO.getCellPhone());
			callStat.setInt(4, msgDefineDTO.getCreatedBy());
			callStat.setString(5, msgDefineDTO.getApplyNumber());
			callStat.setString(6, msgDefineDTO.getUserId());
			callStat.registerOutParameter(7, Types.NUMERIC);
			callStat.registerOutParameter(8, Types.VARCHAR);
			callStat.execute();
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} finally {
			DBManager.closeDBStatement(callStat);
		}
    }
}
