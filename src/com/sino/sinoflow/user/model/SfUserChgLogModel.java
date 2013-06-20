package com.sino.sinoflow.user.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.sinoflow.user.dto.SfUserChgLogDTO;

/**
 * <p>
 * Title: SinoApplication
 * </p>
 * <p>
 * Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������
 * </p>
 * <p>
 * Copyright: ����ʤ��Ȩ����Copyright (c) 2003~2007��
 * <p>
 * Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�
 * </p>
 * <p>
 * Copyright: ������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author ����ʤ
 * @version 0.1
 */

public class SfUserChgLogModel extends BaseSQLProducer {

	/**
	 * ���ܣ��û�ά��Model���캯��
	 * 
	 * @param userAccount
	 *            BaseUserDTO ����ִ�е�ǰ�������û�
	 * @param dtoParameter
	 *            SfUserBaseDTO ����ǰ����������
	 */
	public SfUserChgLogModel(SfUserBaseDTO userAccount,
			SfUserChgLogDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SfUserChgLogDTO dto = (SfUserChgLogDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = 
				  "INSERT INTO SF_USER_CHG_LOG\n" 
				+ "  (LOG_ID,\n"
				+ "   USER_ID,\n" 
				+ "   CHG_TYPE,\n" 
				+ "   OPERATOR,\n"
				+ "   OPERATOR_DATE,\n" 
				+ "   LOG_FROM,\n" 
				+ "   LOG_TO,\n"
				+ "   REMARK)\n" 
				+ "VALUES\n"
				+ "  (NEWID(), ?, ?, ?, GETDATE(), ?, ?, ?)";

		// sqlArgs.add(dto.getLogId());
		sqlArgs.add(dto.getUserId());
		sqlArgs.add(dto.getChgType());
		sqlArgs.add(dto.getOperator());
		// sqlArgs.add(dto.getOperatorDate());
		sqlArgs.add(dto.getLogFrom());
		sqlArgs.add(dto.getLogTo());
		sqlArgs.add(dto.getRemark());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SfUserChgLogDTO dto = (SfUserChgLogDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

		String sqlStr = "DELETE FROM" + " SF_USER_CHG_LOG" + " WHERE"
				+ " LOG_ID = ?";
		sqlArgs.add(dto.getLogId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {

		SfUserBaseDTO dto = (SfUserBaseDTO) super.dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT LOG_ID,\n" + "       USER_ID,\n"
				+ "       CHG_TYPE,\n" + "       OPERATOR,\n"
				+ "       OPERATOR_DATE,\n" + "       LOG_FROM,\n"
				+ "       LOG_TO,\n" + "       REMARK\n"
				+ "  FROM SF_USER_CHG_LOG\n" + " WHERE LOG_ID = ?";

		sqlArgs.add(dto.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * 
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */

    /**
     * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {

            SfUserChgLogDTO dto = (SfUserChgLogDTO) dtoParameter;
            List sqlArgs = new ArrayList();
            String sqlStr =
                    "SELECT LOG_ID,\n" +
                    "       USER_ID,\n" +
                    "       dbo.APP_GET_USER_NAME(USER_ID) USER_NAME,\n" +
                    "       CHG_TYPE,\n" +
                    "       OPERATOR,\n" +
                    "       dbo.APP_GET_USER_NAME(OPERATOR) OPERATOR_NAME,\n" +
                    "       OPERATOR_DATE,\n" +
                    "       LOG_FROM,\n" +
                    "       LOG_TO,\n" +
                    "       REMARK\n" +
                    "  FROM SF_USER_CHG_LOG\n" +
                    " WHERE (? = '' OR dbo.APP_GET_USER_NAME(USER_ID) = ?) " +
                    "   AND (? = '' OR dbo.APP_GET_USER_NAME(OPERATOR)= ?)\n" +
                    "   AND (? = '' OR OPERATOR_DATE >=ISNULL(?,OPERATOR_DATE))\n" +
                    "   AND (? = '' OR OPERATOR_DATE <=ISNULL(?,OPERATOR_DATE))\n" +
                    "   ORDER BY OPERATOR_DATE DESC\n";

            sqlArgs.add(dto.getUserName());
            sqlArgs.add(dto.getUserName());
            sqlArgs.add(dto.getOperatorName());
            sqlArgs.add(dto.getOperatorName());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getEndDate());
            sqlArgs.add(dto.getEndDate());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }
}
