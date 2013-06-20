package com.sino.sinoflow.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.dto.SfHolidaysDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfHolidaysModel</p>
 * <p>Description:�����Զ�����SQL��������SfHolidaysModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author �׼�
 * @version 1.0
 */ 


public class SfHolidaysModel extends BaseSQLProducer {

//	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ�SF_HOLIDAYS ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfHolidaysDTO ���β���������
	 */
	public SfHolidaysModel(SfUserBaseDTO userAccount, SfHolidaysDTO dtoParameter) {
		super(userAccount, dtoParameter);
//		sfUser = userAccount;
	}

	/**
	 * ���ܣ�����Զ�����SF_HOLIDAYS���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfHolidaysDTO sfHolidays = (SfHolidaysDTO)dtoParameter;
		String sqlStr = "INSERT INTO "
			+ " SF_HOLIDAYS("
			+ " HOLIDAYS,"
			+ " NAME,"
			+ " YEAR"
			+ ") VALUES ("
			+ " ?,?,?)";
		sqlArgs.add(sfHolidays.getHolidays().trim());
		sqlArgs.add(sfHolidays.getName().trim());
		sqlArgs.add(sfHolidays.getYear());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SF_HOLIDAYS���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfHolidaysDTO sfHolidays = (SfHolidaysDTO)dtoParameter;
		String sqlStr = "UPDATE SF_HOLIDAYS"
			+ " SET"
			+ " HOLIDAYS = ?,"
			+ " NAME = ?,"
			+ " YEAR = ?"
			+ " WHERE"
			+ " HOLIDAYS_ID = ?";
		
		sqlArgs.add(sfHolidays.getHolidays());
		sqlArgs.add(sfHolidays.getName());
		sqlArgs.add(sfHolidays.getYear());
		sqlArgs.add(sfHolidays.getHolidaysId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SF_HOLIDAYS����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfHolidaysDTO sfHolidays = (SfHolidaysDTO)dtoParameter;
		String sqlStr = "DELETE FROM"
				+ " SF_HOLIDAYS"
				+ " WHERE"
				+ " HOLIDAYS_ID = ?";
			sqlArgs.add(sfHolidays.getHolidaysId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SF_HOLIDAYS������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfHolidaysDTO sfHolidays = (SfHolidaysDTO)dtoParameter;
		String sqlStr = "SELECT "
			+ " HOLIDAYS_ID,"
			+ " HOLIDAYS,"
			+ " YEAR,"
			+ " NAME"
			+ " FROM"
			+ " SF_HOLIDAYS"
			+ " WHERE"
			+ " HOLIDAYS_ID = ?";
		sqlArgs.add(sfHolidays.getHolidaysId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SF_HOLIDAYS����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getMuxDataModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfHolidaysDTO sfHolidays = (SfHolidaysDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " HOLIDAYS_ID,"
			+ " HOLIDAYS"
			+ " FROM"
			+ " SF_HOLIDAYS"
			+ " WHERE"
			+ " (? <= 0 OR HOLIDAYS_ID = ?)"
			+ " AND (? = '' OR HOLIDAYS LIKE ?)";
		sqlArgs.add(sfHolidays.getHolidaysId());
		sqlArgs.add(sfHolidays.getHolidaysId());
		sqlArgs.add(sfHolidays.getHolidays());
		sqlArgs.add(sfHolidays.getHolidays());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����SF_HOLIDAYSҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SfHolidaysDTO sfHolidays = (SfHolidaysDTO)dtoParameter;
			String sqlStr = "SELECT "
			+ " CONVERT(INT,HOLIDAYS_ID) HOLIDAYS_ID,"
			+ " NAME,"
			+ " YEAR,"
			+ " HOLIDAYS"
			+ " FROM"
			+ " SF_HOLIDAYS"
			+ " WHERE"
			+ " (? <= 0 OR HOLIDAYS_ID = ?)"
			+ " AND (? = '' OR NAME LIKE ?)"
			+ " AND (? <= 0 OR YEAR = ?)"
			+ " AND (? = '' OR HOLIDAYS LIKE ?)"
			+ " ORDER BY NAME,HOLIDAYS_ID";
		sqlArgs.add(sfHolidays.getHolidaysId());
		sqlArgs.add(sfHolidays.getHolidaysId());
		sqlArgs.add(sfHolidays.getName());
		sqlArgs.add(sfHolidays.getName());
		sqlArgs.add(sfHolidays.getYear());
		sqlArgs.add(sfHolidays.getYear());
		sqlArgs.add(sfHolidays.getHolidays());
		sqlArgs.add(sfHolidays.getHolidays());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ����ؽڼ����б�,Ϊҳ�����������б�
     * @return SQLModel SQL Model
	 */
	public SQLModel getHolidays() {
		SQLModel sqlModel = new SQLModel();
//		SfHolidaysDTO sfHolidays = (SfHolidaysDTO)dtoParameter;
			String sqlStr = "SELECT DISTINCT"
			+ " NAME,"
			+ " NAME"
			+ " FROM"
			+ " SF_HOLIDAYS";
		
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}

	/**
     * ���ܣ����ݴ��������������������б�
     * @param JJR JJR
     * @return SQLModel SQL Model
     */
    public SQLModel getYear(String JJR){
    	SQLModel sqlModel = new SQLModel();
    	String sqlStr = "SELECT "
    					+ " YEAR"
    					+ " FROM"
    					+ " SF_HOLIDAYS"
    					+ " WHERE"
    					+ " NAME"
    					+ " LIKE '" + JJR+"'";
    	sqlModel.setSqlStr(sqlStr);
    	return sqlModel;
    }
    
    /**
     * ���ܣ����ݴ��������������������б�
     * @param JJR JJR
     * @param Year year
     * @return SQLModel SQL Model
     */
    public SQLModel getRQ(String JJR,String Year){
    	SQLModel sqlModel = new SQLModel();
    	String sqlStr = "SELECT "
    					+ " HOLIDAYS"
    					+ " FROM"
    					+ " SF_HOLIDAYS"
    					+ " WHERE"
    					+ " NAME LIKE '" + JJR
    					+ "' AND"
    					+ " YEAR = " + Year;
    	sqlModel.setSqlStr(sqlStr);
    	return sqlModel;
    }

	/**
     * ���ܣ�ɾ��
     * @param ids ids
     * @return SQLModel 
     */
    public SQLModel del(String[] ids){
	    String str = "";
		for (int i = 0; i < ids.length; i++) {
			str += ids[i]+",";
		}
		str = str.substring(0,str.length()-1);
    	SQLModel sqlModel = new SQLModel();
        String sqlStr = "DELETE" 
        		+ " FROM "
        		+ " SF_HOLIDAYS"
        		+ " WHERE"
        		+ " HOLIDAYS_ID IN("+str+")";
        sqlModel.setSqlStr(sqlStr);        
        return sqlModel;
    }

    /**
     * ���ܣ����ؽڼ����б�,��ݴ��ڻ���ڴ������݁K�����Ƶ��ڴ��������
     * @param name ��������
     * @param year ���
     * @return SQLModel SQL Model
     */
    public SQLModel getHolidaysAfter(String name, int year) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
//        SfHolidaysDTO sfHolidays = (SfHolidaysDTO)dtoParameter;
            String sqlStr = "SELECT *"
            + " FROM"
            + " SF_HOLIDAYS"
            + " WHERE NAME = ?";

        sqlArgs.add(name);
        if(year > 0) {
            sqlStr += " AND YEAR >= ?";
            sqlArgs.add(String.valueOf(year));
        }

        sqlStr += " ORDER BY YEAR";

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}