package com.sino.sinoflow.framework.resource.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.constant.WebAttrConstant;
import com.sino.sinoflow.framework.resource.dto.SfResDefineDTO;
import com.sino.sinoflow.framework.resource.dto.SfResPrivsDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfResPrivsModel</p>
 * <p>Description:�����Զ�����SQL��������SfResPrivsModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 *          <p/>
 *          �޸��ˣ��׼�
 *          �޸����ڣ�2008.8.20
 */


public class SfResPrivsModel extends BaseSQLProducer {

    private SfResPrivsDTO sfResPrivs = null;
    private SfUserBaseDTO sfUser = null;

    /**
     * ���ܣ�SF_RES_PRIVS ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserBaseDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfResPrivsDTO ���β���������
     */
    public SfResPrivsModel(SfUserBaseDTO userAccount, SfResPrivsDTO dtoParameter) {
        super(userAccount, dtoParameter);
        this.sfResPrivs = (SfResPrivsDTO) dtoParameter;
        this.sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "INSERT INTO "
                + " SF_RES_PRIVS("
                + " GROUP_NAME,"
                + " ROLE_NAME,"
                + " SYSTEM_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + ") VALUES ("
                + " ?, ?, ?, ?, ?, ?, ?)";

        sqlArgs.add(sfResPrivs.getGroupName());
        sqlArgs.add(sfResPrivs.getRoleName());
        sqlArgs.add(sfResPrivs.getSystemId());
        sqlArgs.add(sfResPrivs.getCreationDate());
        sqlArgs.add(sfResPrivs.getCreatedBy());
        sqlArgs.add(sfResPrivs.getLastUpdateDate());
        sqlArgs.add(sfResPrivs.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE SF_RES_PRIVS"
                + " SET"
                + " GROUP_NAME = ?"
                + " ROLE_NAME = ?"
                + " SYSTEM_ID = ?"
                + " CREATION_DATE = ?"
                + " CREATED_BY = ?"
                + " LAST_UPDATE_DATE = ?"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " ROLE_NAME = ?";

        sqlArgs.add(sfResPrivs.getGroupName());
        sqlArgs.add(sfResPrivs.getRoleName());
        sqlArgs.add(sfResPrivs.getSystemId());
        sqlArgs.add(sfResPrivs.getCreationDate());
        sqlArgs.add(sfResPrivs.getCreatedBy());
        sqlArgs.add(sfResPrivs.getLastUpdateDate());
        sqlArgs.add(sfResPrivs.getLastUpdateBy());
        sqlArgs.add(sfResPrivs.getRoleName());

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
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " SF_RES_PRIVS"
                + " WHERE"
                + " ROLE_NAME = ?";
        sqlArgs.add(sfResPrivs.getRoleName());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT "
//                        + " GROUP_ID,"
                + " SYSTEM_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY,"
                + " FROM"
                + " SF_RES_PRIVS + "
                + " WHERE"
                + " ROLE_NAME = ?";
        sqlArgs.add(sfResPrivs.getRoleName());

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
//                        + " GROUP_ID,"
                + " GROUP_NAME,"
                + " ROLE_NAME,"
                + " SYSTEM_ID,"
                + " CREATION_DATE,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " SF_RES_PRIVS"
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
        String sqlStr = "SELECT *"
                + " FROM"
                + " SF_RES_PRIVS"
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
        SQLModel sqlModel = null;
        if (foreignKey.equals("groupName")) {
//			sqlModel = getDataByGroupIdModel(sfResPrivs.getGroupId());
        } else if (foreignKey.equals("createdBy")) {
            sqlModel = getDataByCreatedByModel(sfResPrivs.getCreatedBy());
        } else if (foreignKey.equals("lastUpdateBy")) {
            sqlModel = getDataByLastUpdateByModel(sfResPrivs.getLastUpdateBy());
        }
        return sqlModel;
    }

    /**
     * ���ܣ���ȡURL��Դ�˵���Ŀ�������б�SQL��
     * @return SQLModel
     */
    public SQLModel getResourceOptionModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " SRD.RES_ID,"
                + " SRD.RES_NAME"
                + " FROM"
                + " SF_RES_DEFINE SRD"
                + " ORDER  BY"
                + " SRD.RES_ID,"
                + " SRD.RES_PAR_ID";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����URL��Դ����SQL����Ŀ������
     * @return SQLModel
     */
    public SQLModel getResourceTreeModel() {
        SQLModel sqlModel = new SQLModel();
//        String sqlStr = " SELECT"
//                        + " SRD.SYSTEM_ID,"
//                        + " SRD.RES_ID,"
//                        + " SRD.RES_PAR_ID,"
//                        + " SRD.RES_NAME,"
//                        + " SRD.RES_ID RES_URL,"
//                        + " SRD.SORT_NO,"
//                        + " 'N' IS_POPUP,"
//                        + " SRD.POPSCRIPT"
//                        + " FROM"
//                        + " SF_RES_DEFINE SRD"
//                        + " WHERE"
//                        + " SRD.ENABLED = 'Y'"
//                        + " AND SRD.VISIBLE = 'Y'"
//                        + " ORDER BY"
//                        + " SRD.RES_ID,"
//                        + " SRD.RES_PAR_ID";
//     String sqlStr  = " SELECT "
//        			   + " A.RES_ID ID1, "
//        			   + " A.RES_NAME TEXT1, "
//        			   + " A2.RES_ID ID2, "
//        			   + " A2.RES_NAME TEXT2, "
//        			   + " A3.RES_ID ID3, "
//        			   + " A3.RES_NAME TEXT3, "
//        			   + " A4.RES_ID ID4,"
//	      			   + " A4.RES_NAME TEXT4"
//        			   + " FROM "
//        			   + " SF_RES_DEFINE A, "
//        			   + " SF_RES_DEFINE A2, "
//        			   + " SF_RES_DEFINE A3, "
//	      			   + " SF_RES_DEFINE A4"
//        			   + " WHERE "
//        			   + " A4.RES_PAR_ID =* A3.RES_ID"
//        			   + " AND A3.RES_PAR_ID =* A2.RES_ID "
//        			   + " AND A2.RES_PAR_ID = A.RES_ID "
//        			   + " AND A.RES_PAR_ID ='' "
//        			   + " ORDER BY A.RES_ID, A2.RES_ID, A3.RES_ID, A4.RES_ID ";

        String sqlStr = "exec sp_get_all_res '" + WebAttrConstant.TRUE_VALUE + "','" + WebAttrConstant.TRUE_VALUE + "' ";
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ��һ��URL��ԴSQL����ĿȨ�޹�����
     * @return SQLModel
     */
    public SQLModel getFirstResourceModel() {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = " SELECT"
                + " SRD.*"
                + " FROM"
                + " SF_RES_DEFINE SRD"
                + " WHERE"
                + " SRD.ENABLED = 'Y'"
                + " AND SRD.VISIBLE = 'Y'"
                + " ORDER BY"
                + " SRD.RES_ID,"
                + " SRD.RES_PAR_ID";
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ��һ��URL��ԴSQL����ĿȨ�޹�����
     * @return SQLModel
     */
    public SQLModel getResourceByIdModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT"
                + " SRD.*"
                + " FROM"
                + " SF_RES_DEFINE SRD"
                + " WHERE"
                + " SRD.RES_ID = ?";
        sqlArgs.add(sfResPrivs.getResId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ��ѯ����Դ��SQL��
     * @param systemId String
     * @return SQLModel
     */
    public SQLModel getParentResModel(int systemId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " *"
                + " FROM"
                + " SF_RES_DEFINE SRD"
                + " WHERE"
                + " CHARINDEX("
                + " SRD.RES_ID+'.',"
                + " ("
                + " SELECT"
                + " SRD2.RES_ID"
                + " FROM"
                + " SF_RES_DEFINE SRD2"
                + " WHERE SRD2.SYSTEM_ID = ?)) = 1";
        sqlArgs.add(systemId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡȨ��ɾ��SQL
     * @param sysId systemID
     * @return SQLModel
     */
    public SQLModel getPriviDeleteModel(int sysId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " SF_RES_PRIVS"
                + " WHERE"
                + " SYSTEM_ID = ?";
        sqlArgs.add(sysId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    public SQLModel getDeleteInValidatePriviModel(SfResDefineDTO resDefineDTO) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "DELETE SF_RES_PRIVS\n" +
                        " WHERE SYSTEM_ID = ?\n" +
                        "   AND ROLE_NAME NOT IN (SELECT DISTINCT ROLE_NAME\n" +
                        "                           FROM SF_RES_PRIVS SRP, SF_RES_DEFINE SRD\n" +
                        "                          WHERE SRP.SYSTEM_ID = SRD.SYSTEM_ID\n" +
                        "                            AND SRD.RES_PAR_ID = ?\n" +
                        "                            AND SRD.ENABLED = 'Y'\n" +
                        "                            AND SRD.VISIBLE = 'Y')";

        sqlArgs.add(resDefineDTO.getSystemId());
        sqlArgs.add(resDefineDTO.getResId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡȨ�������Ƿ���ڵ�SQL
     * @param sfResPriv SfResPrivsDTO
     * @return List
     */
    public SQLModel getPriviExistModel(SfResPrivsDTO sfResPriv) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT"
                + " *"
                + " FROM"
                + " SF_RES_PRIVS"
                + " WHERE"
                + " GROUP_NAME = ?"
                + " AND ROLE_NAME = ?"
                + " AND SYSTEM_ID = ?";
        sqlArgs.add(sfResPriv.getGroupName());
        sqlArgs.add(sfResPriv.getRoleName());
        sqlArgs.add(sfResPriv.getSystemId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡȨ�޲���SQL
     * @param sfResPriv SfResPrivsDTO
     * @return List
     */
    public SQLModel getPriviCreateModel(SfResPrivsDTO sfResPriv) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " INSERT INTO"
                + " SF_RES_PRIVS("
                + " GROUP_NAME,"
                + " ROLE_NAME,"
                + " SYSTEM_ID,"
                + " CREATED_BY"
                + " ) VALUES (?, ?, ?, ?)";
        sqlArgs.add(sfResPriv.getGroupName());
        sqlArgs.add(sfResPriv.getRoleName());
        sqlArgs.add(sfResPriv.getSystemId());
        sqlArgs.add(String.valueOf(sfUser.getUserId()));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ�˵���Ȩ��
     * @return List
     */
    public SQLModel getPriviModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT * FROM SF_RES_PRIVS WHERE SYSTEM_ID = ?";
        sqlArgs.add(sfResPrivs.getSystemId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
