package com.sino.ams.workorder.dao;


import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsSuggestionDTO;
import com.sino.ams.workorder.model.EtsSuggestionModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsSuggestionDAO</p>
 * <p>Description:�����Զ����ɷ������EtsSuggestionDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */


public class EtsSuggestionDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ����������������(EAM) ETS_SUGGESTION ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsSuggestionDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsSuggestionDAO(SfUserDTO userAccount, EtsSuggestionDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsSuggestionDTO dtoPara = (EtsSuggestionDTO)dtoParameter;
		super.sqlProducer = new EtsSuggestionModel((SfUserDTO)userAccount, dtoPara);
	}

}