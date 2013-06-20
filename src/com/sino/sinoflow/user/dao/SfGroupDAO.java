package com.sino.sinoflow.user.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.user.dto.SfGroupDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.sinoflow.user.model.SfGroupModel;


/**
 * <p>Title: SfGroupDAO</p>
 * <p>Description:�����Զ����ɷ������SfGroupDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfGroupDAO extends BaseDAO {
	SfGroupDTO sfGroupDTO = new SfGroupDTO();
	private String msg = "";
	/**
	 * ���ܣ�SF_GROUP ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfGroupDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfGroupDAO(SfUserBaseDTO userAccount, SfGroupDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		this.initSQLProducer(userAccount, dtoParameter);
	}
	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
//		SfGroupDTO dtoPara = (SfGroupDTO)dtoParameter;
		sfGroupDTO = (SfGroupDTO)dtoParameter;
		super.sqlProducer = new SfGroupModel((SfUserBaseDTO)userAccount, sfGroupDTO );
	}
	
	 public int getCurIdValue() throws QueryException, NumberFormatException, ContainerException{
    	SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT @@IDENTITY "; 
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if( simpleQuery.hasResult() ){
        	return  Integer.parseInt( simpleQuery.getSearchResult().getRow( 0 ).getStrValue( 0 ) );
        } else{
        	return 0;
        }
    }
	  
 
//	private void insertDept( int groupId ,String deptId , int orgId ) throws DataHandleException{
//		SQLModel sqlModel = new SQLModel();
//		List sqlArgs = new ArrayList();
//		StringBuffer sql = new StringBuffer();
//		
//		sql.append( " INSERT INTO SINO_MIS_DEPT(  \n " );
//		sql.append( " DEPT_ID,  \n " );
//		sql.append( " DEPT_NAME,  \n " );
//		sql.append( " ORG_ID,  \n " );
//		sql.append( " COMPANY_CODE,  \n " );
//		sql.append( " ENABLED ,  \n " );
//		sql.append( " SECOND_DEPT,  \n " );
//		sql.append( " LAST_UPDATE_DATE,  \n " );
//		sql.append( " DEPT_PROPERTY  \n " ); 
//		sql.append( " )( " );
//		sql.append( " SELECT \n " );
//		sql.append(   deptId );
//		sql.append( " ,	SG.GROUP_NAME , \n " );
//		sql.append(   orgId );
//		sql.append( " ,	''  \n " );
//		sql.append( " ,	'Y'   \n " );
//		sql.append( " ,	'N'   \n " );
//		sql.append( " ,	GETDATE()   \n " );
//		sql.append( " ,	'0'   \n " );
//		sql.append( " FROM SF_GROUP SG   \n " );
//		sql.append( " WHERE  \n " );
//		sql.append( " SG.GROUP_ID = ?  \n " );
//		sql.append( " )  \n " );
//		sqlArgs.add( groupId );
//		sqlModel.setArgs( sqlArgs );
//		sqlModel.setSqlStr( sql.toString() );
//		
//		DBOperator.updateRecord( sqlModel , conn );
//	}
// 
	
	/**
     * ���ܣ��������
     * @return String
     * @throws SQLException
     */
    public void synGroupMatch( int groupId, String groupName ,String projectName ) throws SQLException {
        CallableStatement cst = null;
        String sqlStr = "{CALL dbo.SYN_GROUP_DEPT(?,?,?) }";
        try {
            cst = conn.prepareCall(sqlStr);
            cst.setInt(1, groupId );
            cst.setString(2, groupName );
            cst.setString(3, projectName );
            cst.execute();
        } finally {
            DBManager.closeDBStatement(cst);
        }
    }
    
	
	public boolean saveGroup(){
		boolean isSuccess = false;
		boolean isAuto = false;
		try {
			isAuto = conn.getAutoCommit();
			conn.setAutoCommit( false );
			this.createData();
			int groupId = getCurIdValue();
			this.synGroupMatch(groupId, sfGroupDTO.getGroupName(), sfGroupDTO.getProjectName() ); 
			isSuccess = true;
		} catch (SQLException e) {
			msg = e.getMessage();
			Logger.logError( e );
		} catch (DataHandleException e) {
			msg = e.getMessage();
			e.printLog();
		} catch (QueryException e) {
			msg = e.getMessage();
			e.printLog();
		} catch (NumberFormatException e) {
			msg = e.getMessage();
			Logger.logError( e );
		} catch (ContainerException e) {
			msg = e.getMessage();
			e.printLog();
		}finally{
			try {
				if (isSuccess) {
					conn.commit();
				} else {
					conn.rollback();
				}
				conn.setAutoCommit(isAuto);
			} catch (SQLException e) {
				msg = e.getMessage();
				Logger.logError( e );
			} 
		}
		return isSuccess; 
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
