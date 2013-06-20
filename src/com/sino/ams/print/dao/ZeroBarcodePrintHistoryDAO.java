package com.sino.ams.print.dao;

import java.sql.Connection;

import com.sino.ams.print.dto.ZeroBarcodePrintHistoryDTO;
import com.sino.ams.print.model.ZeroBarcodePrintHistoryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

public class ZeroBarcodePrintHistoryDAO extends BaseDAO{
	
	private ZeroBarcodePrintHistoryModel historyModel = null;
	private SfUserDTO sfUser = null;
	ZeroBarcodePrintHistoryDTO etsBarcodePrintHistoryDTO = null;
	/**
	 * ���ܣ������ӡ��Ϣ�� AMS_BARCODE_PRINT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsBarcodePrintDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public ZeroBarcodePrintHistoryDAO(SfUserDTO userAccount, ZeroBarcodePrintHistoryDTO dtoParameter, Connection conn) {
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
		ZeroBarcodePrintHistoryDTO dtoPara = (ZeroBarcodePrintHistoryDTO)dtoParameter;
		this.historyModel = new ZeroBarcodePrintHistoryModel((SfUserDTO)userAccount, dtoPara );
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
