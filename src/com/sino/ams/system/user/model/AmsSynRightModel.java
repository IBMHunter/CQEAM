package com.sino.ams.system.user.model;

import com.sino.framework.sql.BaseSQLProducer;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.AmsSynRightDTO;
import com.sino.base.db.sql.model.SQLModel;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2009-6-12
 * Time: 11:19:40
 * To change this template use File | Settings | File Templates.
 */
public class AmsSynRightModel extends BaseSQLProducer {

	private AmsSynRightDTO dto = null;
	private SfUserDTO sfUser=null;

	/**
	 * ���ܣ�SF_GROUP ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfGroupDTO ���β���������
	 */
	public AmsSynRightModel(SfUserDTO userAccount, AmsSynRightDTO dtoParameter) {
		super(userAccount, dtoParameter);
		this.dto = (AmsSynRightDTO)dtoParameter;
		this.sfUser=userAccount;
	}
	/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "";


		sqlArgs.add(dto.getUserId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "";


		sqlArgs.add(sfUser.getUserId());


		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "";
		sqlArgs.add(dto.getSynRightId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr ="SELECT SU.LOGIN_NAME,\n" +
                "       SU.USERNAME USER_NAME,\n" +
                "       SU.EMPLOYEE_NUMBER,\n" +
                "       SU.MOVETEL,\n" +
                "       SU.USER_ID\n" +
                "  FROM SF_USER SU\n" +
                " WHERE SU.USER_ID = ?\n" +
                "   AND (SU.DISABLE_DATE IS NULL OR SU.DISABLE_DATE < GETDATE())";
       sqlArgs.add(dto.getUserId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ����ɶ���������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataMuxModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "";


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
	private SQLModel getDataByCreatedByModel(String createdBy){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " GROUP_ID,"
			+ " GROUP_CODE,"
			+ " GROUPNAME,"
			+ " GROUP_PID,"
			+ " ORGANIZATION_ID,"
			+ " SORTNO,"
			+ " ISROOT,"
			+ " CATEGORY,"
			+ " ENABLED,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY,"
			+ " IS_DESIGNER,"
			+ " P_FLOW_ID,"
            + " ABBREVIATION"
            + " FROM"
			+ " SF_GROUP"
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
	private SQLModel getDataByLastUpdateByModel(String lastUpdateBy){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " GROUP_ID,"
			+ " GROUP_CODE,"
			+ " GROUPNAME,"
			+ " GROUP_PID,"
			+ " ORGANIZATION_ID,"
			+ " SORTNO,"
			+ " ISROOT,"
			+ " CATEGORY,"
			+ " ENABLED,"
			+ " IS_INNER,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " IS_DESIGNER,"
			+ " P_FLOW_ID,"
			+ " ABBREVIATION"
			+ " FROM"
			+ " SF_GROUP"
			+ " WHERE"
			+ " LAST_UPDATE_BY = ?";
		sqlArgs.add(lastUpdateBy);

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr ="SELECT SU.LOGIN_NAME,\n" +
                "       SU.USERNAME,\n" +
                "       SU.EMPLOYEE_NUMBER,\n" +
                "       SU.MOVETEL,\n" +
                "       dbo.ADP_GET_ORG_NAME(SU.USER_ID) ORGNIZATION_NAME,\n" +
                "       CONVERT(INT,SU.USER_ID) USER_ID\n" +
                "  FROM SF_USER SU\n" +
                " WHERE SU.ORGANIZATION_ID = ?\n" +
                "   AND (SU.DISABLE_DATE IS NULL OR SU.DISABLE_DATE < GETDATE())\n" +
                "   AND EXISTS\n" +
                " (SELECT 'A' FROM AMS_SYN_RIGHT AYR WHERE SU.USER_ID = AYR.USER_ID)\n" +
                "   AND SU.LOGIN_NAME LIKE dbo.NVL(?, SU.LOGIN_NAME)\n" +
                "   AND SU.USERNAME LIKE dbo.NVL(?, SU.USERNAME)";
		
		sqlArgs.add(sfUser.getOrganizationId());
		sqlArgs.add(dto.getLoginName());
		sqlArgs.add(dto.getUserName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
    public SQLModel deletByUser(){
       SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
      String sql="DELETE FROM AMS_SYN_RIGHT WHERE USER_ID = ?";
        	sqlArgs.add(dto.getUserId());
        sqlModel.setSqlStr(sql);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
    }
      public SQLModel insertByUser(AmsSynRightDTO aDto){
       SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
      String sql="INSERT INTO AMS_SYN_RIGHT\n" +
              "  (SYN_RIGHT_ID, USER_ID, ORGANIZATION_ID, CREATION_DATE, CREATED_BY)\n" +
              "VALUES\n" +
              "  (NEWID(), ?, ?, GETDATE(), ?)";
        	sqlArgs.add(aDto.getUserId());
        	sqlArgs.add(aDto.getOrganizationId());
        	sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sql);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
    }

}
