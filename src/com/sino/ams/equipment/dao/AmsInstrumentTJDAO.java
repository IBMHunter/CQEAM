package com.sino.ams.equipment.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.equipment.dto.AmsInstrumentTJDTO;
import com.sino.ams.equipment.model.AmsInstrumentTJModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsInstrumentTJDAO</p>
 * <p>Description:�����Զ����ɷ������AmsInstrumentTJDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */
public class AmsInstrumentTJDAO extends AMSBaseDAO {
	
	AmsInstrumentTJModel modelProducer = null;

	/**
     * ���ܣ������Ǳ����(EAM) ETS_ITEM_INFO  ETS_SYSTEM_ITEM   ETS_OBJECT  EAM_ITEM_DISPOSE ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentRegistrationDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
	public AmsInstrumentTJDAO(SfUserDTO userAccount, AmsInstrumentTJDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		modelProducer = (AmsInstrumentTJModel)sqlProducer;
	}

	/**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsInstrumentTJDTO dtoPara = (AmsInstrumentTJDTO) dtoParameter;
		super.sqlProducer = new AmsInstrumentTJModel((SfUserDTO) userAccount, dtoPara);
	}
	
}
