package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.util;
import com.sino.sinoflow.dto.SfGroupDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 * <p>Title: SfGroupModel</p>
 * <p>Description:�����Զ�����SQL��������SfGroupModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfMultiGroupModel extends BaseSQLProducer {

	private SfUserBaseDTO sfUser = null;
	private SfGroupDTO sfGroup = null;

	/**
	 * ���ܣ�������� SF_GROUP ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfGroupDTO ���β���������
	 */
	public SfMultiGroupModel(SfUserBaseDTO userAccount, SfGroupDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfGroup = dtoParameter;
		sfUser = userAccount;
	}

    /**
     * ���ܣ����û�Ȩ���еõ����Ӧ������ɫ
     * @param projName String ��������
     * @param roleName String ��ɫ����
     * @param matchGroup ��Ӧ���
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getMatchGroupsModel(String projName, String matchGroup, String roleName) {
        SQLModel sqlModel = new SQLModel();
        String groupCond;
        if(matchGroup == null || matchGroup.equals("")) {
            groupCond = "1 = 1";
        } else {
            List groupArr = util.explodeToList(matchGroup, ";");
            groupCond = "(";
            for(int i = 0; i < groupArr.size(); i++) {
                if(i == (groupArr.size() - 1)) {
                    groupCond += "dbo.SFK_IS_SAME_GROUP_WITH_MASK('" + groupArr.get(i) + "', SUA.GROUP_NAME) <> 0";
                } else {
                    groupCond += "dbo.SFK_IS_SAME_GROUP_WITH_MASK('" + groupArr.get(i) + "', SUA.GROUP_NAME) <> 0 OR ";
                }
            }
            groupCond += ")";
        }

        List sqlArgs = new ArrayList();
            String sqlStr = "SELECT  /*+ USE_HASH(SUA, SMD) */"
                    + " DISTINCT dbo.SFK_GET_MATCH_GROUP(SUA.GROUP_NAME, '+++.+++') GROUP_NAME,"
                    + " SMD.COMPANY_CODE,"
                    + " SMD.DISPLAY_ORDER"
                    + " FROM"
                    + " SF_USER_AUTHORITY SUA, SINO_MIS_DEPT SMD"
                    + " WHERE"
                    + " SUA.ROLE_NAME = ?"
                    + " AND SUA.PROJECT_NAME = ?"
                    + " AND STR_REPLACE(SUA.GROUP_NAME, '.', '\\') = SMD.DEPT_NAME"
                    + " AND " + groupCond
                    + " GROUP BY SMD.COMPANY_CODE, SMD.DISPLAY_ORDER, SUA.GROUP_NAME";

        sqlArgs.add(roleName);
        sqlArgs.add(projName);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}