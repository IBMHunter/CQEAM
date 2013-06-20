package com.sino.sinoflow.user.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.sinoflow.user.dto.SfUserWithProjectDTO;


/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����ʤ��Ȩ����Copyright (c) 2003~2007��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Copyright: ������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 0.1
 */


public class SfUserWithProjectModel extends BaseSQLProducer {

    /**
     * ���ܣ��û�ά��Model���캯��
     */
    public SfUserWithProjectModel() {
    	super(null,null);
    }

    
    /**
     * ���ܣ��û�ά��Model���캯��
     *
     * @param userAccount  BaseUserDTO ����ִ�е�ǰ�������û�
     * @param dtoParameter SfUserBaseDTO ����ǰ����������
     */
    public SfUserWithProjectModel(SfUserBaseDTO userAccount, SfUserWithProjectDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
    	
        SfUserWithProjectDTO sfUserProj = (SfUserWithProjectDTO) super.dtoParameter;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "";
        sqlStr = "SELECT"
    		+ " CONVERT(INT,SU.USER_ID) USER_ID,"
        	+ " SU.USERNAME,"
        	+ " SU.PASSWORD,"
        	+ " SU.EMPLOYEE_ID,"
        	+ " SU.OFFICE_TEL,"
        	+ " SU.FAX,"
        	+ " SU.MOBILE_PHONE,"
        	+ " SU.EMAIL,"
        	+ " SU.ORGANIZATION,"
        	+ " SU.WORK_SCHEDULE_ID,"
        	+ " SU.LOGIN_NAME "
        	+ " FROM "
        	+ " SF_USER SU"
        	+ " WHERE"
        	+ " (? = '' OR SU.USERNAME LIKE ?)"
        	+ " AND (? = '' OR UPPER(SU.LOGIN_NAME) LIKE UPPER(?))";
        if(!sfUserProj.getProjectName().equals("")) {
            sqlStr += " AND EXISTS"
                    + "   (SELECT USER_ID FROM SF_USER_AUTHORITY SUA"
                    + "    WHERE "
                    + "      (? = '' OR SUA.PROJECT_NAME LIKE ?)"
                    + "      AND (? = '' OR SUA.GROUP_NAME LIKE ?)"
                    + "      AND (? = '' OR SUA.ROLE_NAME LIKE ?)"
                    + "      AND (SUA.USER_ID = SU.USER_ID))";
        }

        sqlArgs.add(sfUserProj.getUsername());
        sqlArgs.add(sfUserProj.getUsername());
        sqlArgs.add(sfUserProj.getLoginName());
        sqlArgs.add(sfUserProj.getLoginName());

        if(!sfUserProj.getProjectName().equals("")) {
            sqlArgs.add(sfUserProj.getProjectName());
            sqlArgs.add(sfUserProj.getProjectName());
            sqlArgs.add(sfUserProj.getGroupName());
            sqlArgs.add(sfUserProj.getGroupName());
            sqlArgs.add(sfUserProj.getRoleName());
            sqlArgs.add(sfUserProj.getRoleName());
        }


        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}
