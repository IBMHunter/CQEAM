package com.sino.ams.system.user.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.SfUserRightDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: SfUserRightModel</p>
 * <p>Description:�����Զ�����SQL��������SfUserRightModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfUserRightModel extends BaseSQLProducer {

    /**
     * ���ܣ�SF_USER_RIGHT ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfUserRightDTO ���β���������
     */
    public SfUserRightModel(SfUserDTO userAccount, SfUserRightDTO dtoParameter) {
        super(userAccount, dtoParameter);

    }

    /**
     * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
		SfUserRightDTO sfUserRight = (SfUserRightDTO) dtoParameter;
        List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO "
						+ " SF_USER_RIGHT("
						+ " GROUP_ID,"
						+ " ROLE_ID,"
						+ " USER_ID,"
						+ " CREATION_DATE"
						+ ") VALUES ("
						+ " ?, ?, ?, GETDATE())";

        sqlArgs.add(sfUserRight.getGroupId());
        sqlArgs.add(sfUserRight.getRoleId());
        sqlArgs.add(sfUserRight.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SfUserRightDTO sfUserRight = (SfUserRightDTO) dtoParameter;

        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE SF_USER_RIGHT"
                + " SET"
                + " ROLE_ID = ?,"
                + " USER_ID = ?,"
                + " CREATION_DATE = ?,"
                + " CREATED_BY = ?,"
                + " LAST_UPDATE_DATE = ?,"
                + " LAST_UPDATE_BY = ?,"
                + " WHERE"
                + " GROUP_ID = ?";

        sqlArgs.add(sfUserRight.getRoleId());
        sqlArgs.add(sfUserRight.getUserId());
        sqlArgs.add(sfUserRight.getCreationDate());
        sqlArgs.add(sfUserRight.getCreatedBy());
        sqlArgs.add(sfUserRight.getLastUpdateDate());
        sqlArgs.add(sfUserRight.getLastUpdateBy());
        sqlArgs.add(sfUserRight.getGroupId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
		SfUserRightDTO sfUserRight = (SfUserRightDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " SF_USER_RIGHT"
                + " WHERE"
                + " USER_ID = ?";
        sqlArgs.add(sfUserRight.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SfUserRightDTO sfUserRight = (SfUserRightDTO) dtoParameter;

        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " GROUP_ID,"
                + " ROLE_ID,"
                + " USER_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " FROM"
                + " SF_USER_RIGHT"
                + " WHERE"
                + " USER_ID = ?";
        sqlArgs.add(sfUserRight.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɶ���������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() {
        SfUserRightDTO sfUserRight = (SfUserRightDTO) dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " GROUP_ID,"
						+ " ROLE_ID,"
						+ " USER_ID,"
						+ " CREATION_DATE,"
						+ " CREATED_BY,"
						+ " LAST_UPDATE_DATE,"
						+ " LAST_UPDATE_BY"
						+ " FROM"
						+ " SF_USER_RIGHT"
						+ " WHERE"
						+ " GROUP_ID = ?"
						+ " ROLE_ID = ?"
						+ " USER_ID = ?"
						+ " CREATION_DATE = ?"
						+ " CREATED_BY = ?"
						+ " LAST_UPDATE_DATE = ?"
						+ " LAST_UPDATE_BY = ?";
		sqlArgs.add(sfUserRight.getGroupId());
		sqlArgs.add(sfUserRight.getRoleId());
		sqlArgs.add(sfUserRight.getUserId());
		sqlArgs.add(sfUserRight.getCreationDate());
		sqlArgs.add(sfUserRight.getCreatedBy());
		sqlArgs.add(sfUserRight.getLastUpdateDate());
		sqlArgs.add(sfUserRight.getLastUpdateBy());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� groupId �����ѯ����SQL��
     * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param groupId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByGroupIdModel(int groupId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " ROLE_ID,"
                + " USER_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_USER_RIGHT"
                + " WHERE"
                + " GROUP_ID = ?";
        sqlArgs.add(groupId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� roleId �����ѯ����SQL��
     * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param roleId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByRoleIdModel(String roleId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " GROUP_ID,"
                + " USER_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_USER_RIGHT"
                + " WHERE"
                + " ROLE_ID = ?";
        sqlArgs.add(roleId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� userId �����ѯ����SQL��
     * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param userId String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByUserIdModel(int userId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " SUR.GROUP_ID,"
						+ " '' GROUP_CODE,"
						+ " SG.GROUP_NAME,"
						+ " 0 GROUP_PROP,"
						+ " SUR.ROLE_ID,"
						+ " SR.ROLE_NAME,"
						+ " SMD.DEPT_ID,"
						+ " SMD.DEPT_NAME,"
						+ " SUR.CREATION_DATE,"
						+ " SUR.CREATED_BY,"
						+ " SUR.LAST_UPDATE_DATE,"
						+ " SUR.LAST_UPDATE_BY"
						+ " FROM"
						+ " SF_USER_RIGHT SUR,"
						+ " SF_GROUP SG,"
                        + " SF_ROLE SR,"
                        + " SINO_GROUP_MATCH SGM,"
						+ " SINO_MIS_DEPT    SMD"
						+ " WHERE"
						+ " SUR.GROUP_ID = SG.GROUP_ID"
						+ " AND SUR.ROLE_ID = SR.ROLE_ID"
						+ " AND SGM.DEPT_ID *= SMD.DEPT_ID"
						+ " AND SG.GROUP_ID *= SGM.GROUP_ID"
						+ " AND SUR.USER_ID = ?"
						+ " AND SG.ENABLED = 'Y'"
                        + " ORDER BY SR.ROLE_NAME";
		sqlArgs.add(userId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� createdBy �����ѯ����SQL��
     * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param createdBy String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByCreatedByModel(int createdBy) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " GROUP_ID,"
                + " ROLE_ID,"
                + " USER_ID,"
                + " CREATION_DATE,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_USER_RIGHT"
                + " WHERE"
                + " CREATED_BY = ?";
        sqlArgs.add(createdBy);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������������ֶ� lastUpdateBy �����ѯ����SQL��
     * ����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @param lastUpdateBy String
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    private SQLModel getDataByLastUpdateByModel(int lastUpdateBy) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " GROUP_ID,"
                + " ROLE_ID,"
                + " USER_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE"
                + " FROM"
                + " SF_USER_RIGHT"
                + " WHERE"
                + " LAST_UPDATE_BY = ?";
        sqlArgs.add(lastUpdateBy);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����������ȡ����
     * @param foreignKey ���������ֶ����ơ�
     * @return SQLModel
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey) {
        SfUserRightDTO sfUserRight = (SfUserRightDTO) dtoParameter;

        SQLModel sqlModel = null;
        if (foreignKey.equals("groupId")) {
            sqlModel = getDataByGroupIdModel(sfUserRight.getGroupId());
        } else if (foreignKey.equals("roleId")) {
            sqlModel = getDataByRoleIdModel(sfUserRight.getRoleId());
        } else if (foreignKey.equals("userId")) {
            sqlModel = getDataByUserIdModel( sfUserRight.getUserId() );
        } else if (foreignKey.equals("createdBy")) {
            sqlModel = getDataByCreatedByModel(sfUserRight.getCreatedBy());
        } else if (foreignKey.equals("lastUpdateBy")) {
            sqlModel = getDataByLastUpdateByModel(sfUserRight.getLastUpdateBy());
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SfUserRightDTO sfUserRight = (SfUserRightDTO) dtoParameter;

        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
                + " GROUP_ID,"
                + " ROLE_ID,"
                + " USER_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_USER_RIGHT"
                + " WHERE"
                + " ISNULL(GROUP_ID, -1) = ISNULL(?, -1)"
                + " AND ISNULL(ROLE_ID, -1) = ISNULL(?, -1)"
                + " AND ISNULL(USER_ID, -1) = ISNULL(?, -1)"
                + " AND ISNULL(CREATION_DATE, -1) = ISNULL(?, -1)"
                + " AND ISNULL(CREATED_BY, -1) = ISNULL(?, -1)"
                + " AND ISNULL(LAST_UPDATE_DATE, -1) = ISNULL(?, -1)"
                + " AND ISNULL(LAST_UPDATE_BY, -1) = ISNULL(?, -1)";
        sqlArgs.add(sfUserRight.getGroupId());
        sqlArgs.add(sfUserRight.getRoleId());
        sqlArgs.add(sfUserRight.getUserId());
        sqlArgs.add(sfUserRight.getCreationDate());
        sqlArgs.add(sfUserRight.getCreatedBy());
        sqlArgs.add(sfUserRight.getLastUpdateDate());
        sqlArgs.add(sfUserRight.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}
