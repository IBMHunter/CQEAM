package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.dto.SfActInfoDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfActInfoModel</p>
 * <p>Description:�����Զ�����SQL��������SfActInfoModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class TransferTrayModel extends BaseSQLProducer {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ���ת���̣��ڰ���ת��Ϣ SF_ACT_INFO ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfActInfoDTO ���β���������
	 */
	public TransferTrayModel(SfUserBaseDTO userAccount, SfActInfoDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ�������ת���̣��ڰ���ת��Ϣ SF_ACT_INFOҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param keyword  �ؼ���
     * @param subject ����
     * @param createby �а���
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
	 */
	public SQLModel getPageQueryModel(String keyword, String subject, String others, String createby, int sortType) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
//        try {
            List sqlArgs = new ArrayList();
//            SfActInfoDTO sfActInfo = (SfActInfoDTO)dtoParameter;
            String sqlStr = "SELECT "
                    + " SAI.SFACT_DOC_TYPE,"
                    + " SAI.SFACT_ACT_ID,"
                    + " SAI.SFACT_PROC_NAME,"
                    + " SAI.SFACT_APPL_MSG,"
                    + " SAI.SFACT_SIGN_DATE,"
                    + " SAI.SFACT_SIGN_DUE_DATE,"
                    + " SAI.SFACT_CASE_ID,"
                    + " SAI.SFACT_PROC_ID,"
                    + " SAI.SFACT_TASK_NAME,"
                    + " SAI.SFACT_TASK_ID,"
                    + " SAI.SFACT_SORT_COLUMN_1,"
                    + " SAI.SFACT_SORT_COLUMN_2,"
                    + " SAI.SFACT_SORT_COLUMN_3,"
                    + " SAI.SFACT_APPL_COLUMN_1,"
                    + " SAI.SFACT_APPL_COLUMN_2,"
                    + " SAI.SFACT_APPL_COLUMN_3,"
                    + " SAI.SFACT_APPL_COLUMN_4,"
                    + " SAI.SFACT_APPL_COLUMN_5,"
                    + " SAI.SFACT_APPL_COLUMN_6,"
                    + " SAI.SFACT_APPL_COLUMN_7,"
                    + " SAI.SFACT_APPL_COLUMN_8,"
                    + " SAI.SFACT_APPL_COLUMN_9,"
                    + " SAI.SFACT_DELIVERY_PRIORITY,"
                    + " SAI.SFACT_CASE_LOCK_STATUS,"
                    + " SAI.SFACT_TASK_ATTRIBUTE_1,"
                    + " SAI.SFACT_TASK_ATTRIBUTE_2,"
                    + " SAI.SFACT_TASK_ATTRIBUTE_3,"
                    + " SAI.SFACT_TASK_ATTRIBUTE_4,"
                    + " SAI.SFACT_TASK_ATTRIBUTE_5,"
                    + " SAI.SFACT_HANDLER,"
                    + " SAI.SFACT_HANDLER_GROUP,"
                    + " SAI.SFACT_PLUS_GROUP,"
                    + " SAI.SFACT_TASK_GROUP,"
                    + " SA.CATEGORY_NAME,"
                    + " SA.APP_NAME,"
                    + " SA.PROJECT_NAME,"
                    + " SA.WINDOW_TYPE,"
                    + " SA.ALLOW_OPERATION,"
                    + " SA.FINISH_MESSAGE,"
                    + " SP.TRAY_TYPE,"
                    + " SAI.SFACT_COMMENT_QTY,"
                    + " ST.REVIEW_DESC SFACT_COMMENT_APPL_MSG,"
                    + " SAI.SFACT_COMMENT_TYPE,"
//                    + " dbo.SFK_GET_REALNAMES(SAI.SFACT_COMPOSE_USER) SFACT_COMPOSE_USER"
                    + " SU.USERNAME SFACT_COMPOSE_USER"
                    + " FROM"
                    + " SF_ACT_INFO SAI, SF_APPLICATION SA, SF_PROCEDURE SP, SF_TASK ST, SF_USER SU"
                    + " WHERE"
                    + " SAI.SFACT_COMPLETE_STATUS <> 1"
                    + " AND SAI.SFACT_APPDEF_ID = SA.APP_ID"
                    + " AND SAI.SFACT_PROC_ID = SP.PROCEDURE_ID"
                    + " AND ST.TASK_ID = SAI.SFACT_TASK_ID"
                    + " AND (? = '' OR SAI.SFACT_APPL_COLUMN_1 LIKE ?)"
                    + " AND (? = '' OR SAI.SFACT_APPL_COLUMN_2 LIKE ?)"
                    + " AND (? = '' OR SAI.SFACT_APPL_COLUMN_3 LIKE ?)"
                    + " AND SAI.SFACT_COMPOSE_USER = SU.LOGIN_NAME"
                    + " AND (? = '' OR SU.USERNAME LIKE ?)"
                    + " AND SU.ORGANIZATION_ID = ?";
            switch(sortType) {
                case 0: default:
                    sqlStr += " ORDER BY SAI.SFACT_PROC_NAME, SAI.SFACT_APPL_MSG,";
                    break;
                case 1:
                    sqlStr += " ORDER BY SA.APP_NAME, SAI.SFACT_APPL_MSG,";
                    break;
                case 2:
                    sqlStr += " ORDER BY SA.CATEGORY_NAME, SAI.SFACT_APPL_MSG, ";
                    break;

            }
            sqlStr += " SAI.SFACT_SIGN_DATE, SAI.SFACT_APPL_COLUMN_1, SAI.SFACT_APPL_COLUMN_2";

            sqlModel.setSqlStr(sqlStr);
            if(keyword == null || keyword.equals("")) {
                sqlArgs.add("");
                sqlArgs.add("");
            } else {
                sqlArgs.add("%" + keyword + "%");
                sqlArgs.add("%" + keyword + "%");
            }
            if(subject == null || subject.equals("")) {
                sqlArgs.add("");
                sqlArgs.add("");
            } else {
                sqlArgs.add("%" + subject + "%");
                sqlArgs.add("%" + subject + "%");
            }
            if(others == null || others.equals("")) {
                sqlArgs.add("");
                sqlArgs.add("");
            } else {
                sqlArgs.add("%" + others + "%");
                sqlArgs.add("%" + others + "%");
            }
            if(createby == null || createby.equals("")) {
                sqlArgs.add("");
                sqlArgs.add("");
            } else {
                sqlArgs.add("%" + createby + "%");
                sqlArgs.add("%" + createby + "%");
            }
            sqlArgs.add(sfUser.getOrganizationId());
            sqlModel.setArgs(sqlArgs);
//        } catch (CalendarException ex) {
//            Logger.logError(ex);
//            throw new SQLModelException(ex);
//        }
        return sqlModel;
	}
}