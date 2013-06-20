package com.sino.ams.newasset.dao;

import java.sql.Connection;

import com.sino.ams.newasset.dto.AmsAssetsRcvHeaderDTO;
import com.sino.ams.newasset.model.RcvOrderPrintModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

public class RcvOrderPrintDAO extends AmsAssetsRcvHeaderDAO {

	public RcvOrderPrintDAO(SfUserDTO userAccount, AmsAssetsRcvHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsAssetsRcvHeaderDTO dto = (AmsAssetsRcvHeaderDTO) dtoParameter;
		sqlProducer = new RcvOrderPrintModel(user, dto);
	}

	/**
	 * ���ܣ�ȡ���յ�����������������������������
	 * @return RowSet
	 * @throws QueryException
	 */
	public RowSet getApproveContent() throws QueryException {
		RcvOrderPrintModel modelProducer = (RcvOrderPrintModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getApproveContentModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		return simp.getSearchResult();
	}
}
