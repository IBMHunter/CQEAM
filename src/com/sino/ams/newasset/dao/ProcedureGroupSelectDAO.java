package com.sino.ams.newasset.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.model.ProcedureGroupSelectModel;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;

/**
 * <p>Title: ProcedureGroupSelectDAO</p>
 * <p>Description:�����Զ����ɷ������EtsFaAssetsDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class ProcedureGroupSelectDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݷ��ʲ㹹�캯��
	 * @param userAccount BaseUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter DTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public ProcedureGroupSelectDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		super.sqlProducer = new ProcedureGroupSelectModel(userAccount, dtoParameter);
	}

    public RowSet getProSpecialGroupList() throws QueryException {
        ProcedureGroupSelectModel modelProducer = (ProcedureGroupSelectModel)sqlProducer;
        SQLModel sqlModel = modelProducer.getProSpecialGroupModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }
}
