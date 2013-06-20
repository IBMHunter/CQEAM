package com.sino.sinoflow.dao;

import java.sql.Connection;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfTaskDTO;
import com.sino.sinoflow.model.SfTaskModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 * <p>
 * Title: SfTaskDAO
 * </p>
 * <p>
 * Description:�����Զ����ɷ������SfTaskDAO�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author Hing
 * @version 1.0
 */

public class SfTaskDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��������� SF_TASK ���ݷ��ʲ㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            SfTaskDTO ���β���������
	 * @param conn
	 *            Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfTaskDAO(SfUserBaseDTO userAccount, SfTaskDTO dtoParameter,
			Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * 
	 * @param userAccount
	 *            BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter
	 *            DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfTaskDTO dtoPara = (SfTaskDTO) dtoParameter;
		super.sqlProducer = new SfTaskModel((SfUserBaseDTO) userAccount, dtoPara);
	}

	/**
     * ���ܣ��ӹ�����������������ҵ�һ������
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
	public Row getFirstTask(String projName,String procName) {
		SQLModel sqlModel = new SfTaskModel(null,null).
							getFirstTaskModel(projName,procName);
		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
		Row row = new Row();
		try {
			sq.executeQuery();
			if (sq.hasResult()) {
				 RowSet rowSet = sq.getSearchResult();
				 row = rowSet.getRow(0);
			}
		} catch (QueryException e) {
			e.printLog();
		}
		return row;
	}

}