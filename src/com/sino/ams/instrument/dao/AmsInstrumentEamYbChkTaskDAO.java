package com.sino.ams.instrument.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.instrument.dto.AmsInstrumentEamYbChkTaskDTO;
import com.sino.ams.instrument.model.AmsInstrumentEamYbChkTaskModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

public class AmsInstrumentEamYbChkTaskDAO extends AMSBaseDAO {
	
	AmsInstrumentEamYbChkTaskModel modelProducer = null;

	/**
     * ���ܣ������Ǳ����(EAM) EAM_YB_CHK_TASK ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentEamYbChkTaskDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
	public AmsInstrumentEamYbChkTaskDAO(SfUserDTO userAccount, AmsInstrumentEamYbChkTaskDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		modelProducer = (AmsInstrumentEamYbChkTaskModel)sqlProducer;
	}

	/**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsInstrumentEamYbChkTaskDTO dtoPara = (AmsInstrumentEamYbChkTaskDTO) dtoParameter;
		super.sqlProducer = new AmsInstrumentEamYbChkTaskModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ��������������Ƿ��Ѿ�����
	 * @param objCode
	 * @return
	 * @throws QueryException
	 */
	public boolean checkName(String objName) throws QueryException {
        SQLModel sqlModel = modelProducer.getNameHasBeenModel(objName);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.hasResult();
    }
}
