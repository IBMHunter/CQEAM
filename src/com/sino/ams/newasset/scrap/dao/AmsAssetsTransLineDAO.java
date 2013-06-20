package com.sino.ams.newasset.scrap.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.AmsAssetsTransLineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsAssetsTransLineDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransLineDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class AmsAssetsTransLineDAO extends AMSBaseDAO {

    /**
	 * ���ܣ�AMS_ASSETS_TRANS_LINE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransLineDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsTransLineDAO(SfUserDTO userAccount, AmsAssetsTransLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsTransLineDTO dtoPara = (AmsAssetsTransLineDTO) dtoParameter;
		sqlProducer = new AmsAssetsTransLineModel((SfUserDTO) userAccount,dtoPara);
	}

	/**
	 * ����д�����ݵķ���
	 * @throws DataHandleException
	 */
	public void createData() throws DataHandleException {
		try {
			if (!hasReserved()) {
				super.createData();
			}
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ��жϸ��ʲ��Ƿ��Ѿ�������
	 * @return boolean
	 * @throws QueryException
	 */
	public boolean hasReserved() throws QueryException {
		AmsAssetsTransLineModel modelProducer = (AmsAssetsTransLineModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getHasReservedModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		return simp.hasResult();
	}

	/**
	 * ���ܣ�ȡ������
	 * @throws DataHandleException
	 */
	public void cancelLinesByHeader() throws DataHandleException {
		AmsAssetsTransLineModel modelProducer = (AmsAssetsTransLineModel)sqlProducer;
//		SQLModel sqlModel = modelProducer.getHasReservedModel();
		
		SQLModel sqlModel = modelProducer.getCancelLinesByHeaderModel();
		DBOperator.updateRecord(sqlModel, conn);
	}


	/**
	 * ���ܣ����µ��������۾ɷ����˻�(2008-12-01 17��46)
	 * @param lines ������������
	 * @throws DataHandleException
	 */
	public void uodateAccount(DTOSet lines) throws DataHandleException {
		int lineCount = lines.getSize();
		if(lineCount > 0){
			AmsAssetsTransLineModel modelProducer = (AmsAssetsTransLineModel) sqlProducer;
			SQLModel sqlModel = null;
			for (int i = 0; i < lineCount; i++) {
				AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) lines.getDTO(i);
				setDTOParameter(dto);
				sqlModel = modelProducer.getAccountUpdateModel();
				DBOperator.updateRecord(sqlModel, conn);
			}
		}
	}

    /**
	 * ���ܣ����µ��������۾ɷ����˻����ʲ����(������˾�������������������Ҫ�õ��ù���)
	 * @param lines ������������
	 * @throws DataHandleException
	 */
	public void updateTransLine(DTOSet lines) throws DataHandleException {
		int lineCount = lines.getSize();
		if(lineCount > 0){
			AmsAssetsTransLineModel modelProducer = (AmsAssetsTransLineModel) sqlProducer;
			SQLModel sqlModel = null;
			for (int i = 0; i < lineCount; i++) {
				AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) lines.getDTO(i);
				setDTOParameter(dto);
				sqlModel = modelProducer.getTransLineUpdateModel();
				DBOperator.updateRecord(sqlModel, conn);
			}
		}
	}
}
