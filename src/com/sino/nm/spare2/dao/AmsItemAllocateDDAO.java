package com.sino.nm.spare2.dao;


import java.sql.Connection;

import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.nm.spare2.dto.AmsItemAllocateDDTO;
import com.sino.nm.spare2.model.AmsItemAllocateDModel;
import com.sino.nm.spare2.model.AmsItemTransLModel;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsItemAllocateDDAO</p>
 * <p>Description:�����Զ����ɷ������AmsItemAllocateDDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Herui
 * @version 1.0
 */


public class AmsItemAllocateDDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�AMS_ITEM_ALLOCATE_D ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsItemAllocateDDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsItemAllocateDDAO(SfUserDTO userAccount, AmsItemAllocateDDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsItemAllocateDDTO dtoPara = (AmsItemAllocateDDTO)dtoParameter;
		super.sqlProducer = new AmsItemAllocateDModel((SfUserDTO)userAccount, dtoPara);
	}


    public RowSet getDetails(String transId) throws QueryException {
        AmsItemTransLModel model = (AmsItemTransLModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getDataByTransIdModel(transId),conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }
}