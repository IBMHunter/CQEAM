package com.sino.ams.print.dao;


import java.sql.Connection;

import com.sino.ams.print.dto.EtsBarcodePrintHistoryDTO;
import com.sino.ams.print.model.BarcodePrintModel;
import com.sino.ams.print.model.EtsBarcodePrintHistoryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsBarcodePrintDAO</p>
 * <p>Description:�����Զ����ɷ������AmsBarcodePrintDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsBarcodePrintHistoryDAO extends BaseDAO {
	private EtsBarcodePrintHistoryModel historyModel = null;
	private SfUserDTO sfUser = null;
	EtsBarcodePrintHistoryDTO etsBarcodePrintHistoryDTO = null;
	/**
	 * ���ܣ������ӡ��Ϣ�� AMS_BARCODE_PRINT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsBarcodePrintDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsBarcodePrintHistoryDAO(SfUserDTO userAccount, EtsBarcodePrintHistoryDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		this.etsBarcodePrintHistoryDTO = dtoParameter;
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsBarcodePrintHistoryDTO dtoPara = (EtsBarcodePrintHistoryDTO)dtoParameter;
		this.historyModel = new EtsBarcodePrintHistoryModel((SfUserDTO)userAccount, dtoPara );
		super.sqlProducer = historyModel ;
	} 
	
	public int getBarcodePrintCount( int orgId , String barcode ) throws QueryException, ContainerException{
		SQLModel sqlModel = historyModel.getBarcodePrintCount( orgId , barcode );
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        int count = (Integer) simpleQuery.getFirstRow().getValue( 0 );
        return count;
	}
	
}