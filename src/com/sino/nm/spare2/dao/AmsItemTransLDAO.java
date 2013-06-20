package com.sino.nm.spare2.dao;


import java.sql.Connection;

import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.nm.spare2.dto.AmsItemTransLDTO;
import com.sino.nm.spare2.model.AmsItemTransLModel;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemTransLDAO</p>
 * <p>Description:�����Զ����ɷ������AmsItemTransLDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsItemTransLDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ����������б�(AMS) AMS_ITEM_TRANS_L ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemTransLDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsItemTransLDAO(SfUserDTO userAccount, AmsItemTransLDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsItemTransLDTO dtoPara = (AmsItemTransLDTO)dtoParameter;
		super.sqlProducer = new AmsItemTransLModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ����뱸�������б�(AMS)��AMS_ITEM_TRANS_L�����ݡ�
	 */
	public void createData() throws DataHandleException {
		super.createData();
		getMessage().addParameterValue("���������б�(AMS)");
	}

	/**
	 * ���ܣ����±��������б�(AMS)��AMS_ITEM_TRANS_L�����ݡ�
	 */
	public void updateData() throws DataHandleException {
		super.updateData();
		getMessage().addParameterValue("���������б�(AMS)");
	}

	/**
	 * ���ܣ�ɾ�����������б�(AMS)��AMS_ITEM_TRANS_L�����ݡ�
	 */
	public void deleteData() throws DataHandleException {
		super.deleteData();
		getMessage().addParameterValue("���������б�(AMS)");
	}

    public RowSet getLines(String transId) throws QueryException {
        AmsItemTransLModel model = (AmsItemTransLModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getDataByTransIdModel(transId),conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }
     public RowSet getLines1(String transId) throws QueryException {
        AmsItemTransLModel model = (AmsItemTransLModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getDataByTransIdModel1(transId),conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }
     public RowSet getDeptLines(String transId) throws QueryException {
        AmsItemTransLModel model = (AmsItemTransLModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getDeptDataByTransIdModel(transId),conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }
}