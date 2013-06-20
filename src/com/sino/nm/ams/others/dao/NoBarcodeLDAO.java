package com.sino.nm.ams.others.dao;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.nm.spare2.dto.AmsItemTransLDTO;
import com.sino.nm.ams.others.model.NobarcodeLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2008-7-4
 * Time: 10:06:42
 * To change this template use File | Settings | File Templates.
 */

public class NoBarcodeLDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ����������б�(AMS) AMS_ITEM_TRANS_L ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemTransLDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public NoBarcodeLDAO(SfUserDTO userAccount, AmsItemTransLDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsItemTransLDTO dtoPara = (AmsItemTransLDTO)dtoParameter;
		super.sqlProducer = new NobarcodeLModel((SfUserDTO)userAccount, dtoPara);
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
        NobarcodeLModel model = (NobarcodeLModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getDataByTransIdModel(transId),conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }
     public RowSet getLines1(String transId) throws QueryException {
        NobarcodeLModel model = (NobarcodeLModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getDataByTransIdModel1(transId),conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }
}