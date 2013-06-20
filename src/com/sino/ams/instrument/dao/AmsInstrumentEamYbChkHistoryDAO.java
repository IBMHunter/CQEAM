package com.sino.ams.instrument.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.instrument.dto.AmsInstrumentEamYbChkMaintainDTO;
import com.sino.ams.instrument.model.AmsInstrumentEamYbChkHistoryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

public class AmsInstrumentEamYbChkHistoryDAO extends AMSBaseDAO {

	AmsInstrumentEamYbChkHistoryModel modelProducer = null;
	
	public AmsInstrumentEamYbChkHistoryDAO(SfUserDTO userAccount, AmsInstrumentEamYbChkMaintainDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		modelProducer = (AmsInstrumentEamYbChkHistoryModel)sqlProducer;
	}

	/**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsInstrumentEamYbChkMaintainDTO dtoPara = (AmsInstrumentEamYbChkMaintainDTO) dtoParameter;
		super.sqlProducer = new AmsInstrumentEamYbChkHistoryModel((SfUserDTO)userAccount, dtoPara);
	}

}
