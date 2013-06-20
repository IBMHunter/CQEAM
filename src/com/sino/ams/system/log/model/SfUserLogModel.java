package com.sino.ams.system.log.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.log.dto.SfUserLogDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfUserLogModel</p>
 * <p>Description:�����Զ�����SQL��������SfUserLogModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfUserLogModel extends AMSSQLProducer {

	/**
	 * ���ܣ��û�URL������־��(EAM) SF_USER_LOG ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfUserLogDTO ���β���������
	 */
	public SfUserLogModel(SfUserBaseDTO userAccount, SfUserLogDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}
	/**
	 * ���ܣ�����Զ������û�URL������־��(EAM) SF_USER_LOG���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfUserLogDTO dto = (SfUserLogDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
						+ " SF_USER_LOG("
						+ " LOG_ID,"
						+ " USER_ID,"
						+ " USER_ACCOUNT,"
						+ " CLIENT_IP,"
						+ " REQ_URL,"
						+ " ACTION_TYPE,"
						+ " SOURCE,"
						+ " SERVER"
						+ ") VALUES ("
						+ "  NEWID() , ?, ?, ?, ?, ?, ?, ?)";

		sqlArgs.add(dto.getUserId());
		sqlArgs.add(dto.getUserAccount());
		sqlArgs.add(dto.getClientIp());
		String mm = dto.getReqUrl();
		if (mm.length() > 900) {
			mm = mm.substring(0, 900) + "...";
		}
		sqlArgs.add(mm);
		sqlArgs.add(dto.getActionType());
		sqlArgs.add(dto.getSource());
		sqlArgs.add(dto.getServer());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ�����Զ������û�URL������־��(EAM) SF_USER_LOG����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfUserLogDTO dto = (SfUserLogDTO) dtoParameter;
		String sqlStr = "DELETE FROM"
						+ " SF_USER_LOG"
						+ " WHERE"
						+ " LOG_ID = ?";
		sqlArgs.add(dto.getLogId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� userId �����ѯ����SQL��
	 * ����Զ����������û�URL������־��(EAM) SF_USER_LOG��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param userId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByUserIdModel(int userId) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
						+ " LOG_ID,"
						+ " CLIENT_IP,"
						+ " REQ_URL,"
						+ " ACTION_TYPE,"
						+ " SOURCE,"
						+ " SERVER,"
						+ " LOG_TIME"
						+ " FROM"
						+ " SF_USER_LOG"
						+ " WHERE"
						+ " USER_ID = ?";
		sqlArgs.add(userId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		SfUserLogDTO dto = (SfUserLogDTO)dtoParameter;
		if(foreignKey.equals("userId")){
			sqlModel = getDataByUserIdModel(dto.getUserId());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� userId ��������ɾ��SQL��
	 * ����Զ����������û�URL������־��(EAM) SF_USER_LOG ����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @param userId String
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDeleteByUserIdModel(int userId){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
						+ " SF_USER_LOG"
						+ " WHERE"
						+ " USER_ID = ?";
		sqlArgs.add(userId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ���������ֶ�ɾ������
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		SfUserLogDTO dto = (SfUserLogDTO)dtoParameter;
		if(foreignKey.equals("userId")){
			sqlModel = getDeleteByUserIdModel(dto.getUserId());
		}
		return sqlModel;
	}

	
	/**
	 * ���ܣ�����Զ������û�URL������־��(EAM) SF_USER_LOGҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 * @updater		����
	 * @update date Apr 24, 2009
	 */
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			SfUserLogDTO dto = (SfUserLogDTO) dtoParameter;
			String sqlStr = "SELECT "
							+ " SUL.LOG_ID,"
							+ " SUL.USER_ID,"
							+ " SU.USERNAME,"
							+ " SUL.CLIENT_IP,"
							+ " SRD.RES_NAME,"
							+ " SUL.ACTION_TYPE,"
							+ " SUL.SOURCE,"
							+ " SUL.SERVER,"
							+ " SUL.LOG_TIME,"
							+ " SUL.USER_ACCOUNT"
							+ " FROM"
							+ " SF_USER_LOG SUL,"
							+ " SF_USER     SU,"
							+ " SF_RES_DEFINE SRD "
							+ " WHERE"
							+ " SU.USER_ID = SUL.USER_ID AND SUL.REQ_URL = SRD.RES_URL"
							+ " AND  (" + SyBaseSQLUtil.nullSimpleCalendarParam() + " OR  SUL.LOG_TIME >= ? ) "
							+ " AND  (" + SyBaseSQLUtil.nullSimpleCalendarParam() + " OR  SUL.LOG_TIME <= ? ) ";
							SyBaseSQLUtil.nullSimpleCalendarParamArgs(sqlArgs, dto.getStartDate() );
							SyBaseSQLUtil.nullSimpleCalendarParamArgs(sqlArgs, dto.getSQLEndDate() );
							if(dto.getUserAccount()!=null&!dto.getUserAccount().trim().equals(""))
							{
								sqlStr+=" AND SU.USERNAME LIKE '%"+dto.getUserAccount()+"%'";
							}
							if(dto.getClientIp()!=null&!dto.getClientIp().trim().equals(""))
							{
								sqlStr+="AND SUL.CLIENT_IP LIKE '%"+dto.getClientIp()+"%'";
							}
							if(dto.getResName()!=null&!dto.getResName().equals(""))
							{
								sqlStr+="AND SRD.RES_NAME LIKE '"+dto.getResName()+"'";
							}
//			sqlArgs.add(dto.getStartDate());
//			sqlArgs.add(dto.getSQLEndDate());
			
			//Ҫ�ж� ��ǰ��½�û���ɫ�ǵ��й���Ա1100, ��������ѯ���û����������е���־��¼
//			        ��ǰ��¼�û���ɫ��ϵͳ����Ա (1),���е��е���־��¼���ܿ���
//			        ��ǰ��¼�û���ɫ��Ա��,ֻ�ܿ����Լ�����������־
			//����ǵ��й���Ա(ӵ��ϵͳ����Ա�Ľ�ɫ  ���������)
//			if (!"".equals(userAccount.getCurrGroupId()) && !"".equals(userAccount.getRoleId())) {
			
			if ("PERSONAL".equals(dto.getColumeType())) {// ���˹���̨  //Ԥ��ͬ��ͬ�գ�����Ҫ��USER_ID
				sqlStr += " AND SUL.USER_ID = ?";  
				sqlArgs.add(userAccount.getUserId());
			} else {
				if (!userAccount.isSysAdmin()) {
					if (userAccount.isCityAdmin()) {
						sqlStr += " AND EXISTS (SELECT 1\n"
								+ "       FROM ETS_OU_CITY_MAP EOCM\n"
								+ "      WHERE EOCM.ORGANIZATION_ID= SU.ORGANIZATION_ID\n"
								+ "			 AND SU.ORGANIZATION_ID = ?)";
						sqlArgs.add(userAccount.getOrganizationId());
						//	��ǰ��½�û���ɫ�ǵ��й���Ա1100, ��������ѯ���û����������е���־��¼
					}
					else {  // ������Աֻ�ܲ鿴�Լ�����־
						sqlStr += " AND SUL.USER_ID = ?";
						sqlArgs.add(userAccount.getUserId());
					}
				}else{
                //	��ǰ��¼�û���ɫ��ϵͳ����Ա (1),���е��е���־��¼���ܿ���
				}
			}
			sqlStr += " ORDER BY SUL.LOG_TIME DESC";
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}
}
