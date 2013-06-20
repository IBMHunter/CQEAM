package com.sino.ams.system.user.dao;


import java.sql.Connection;

import com.sino.ams.system.user.dto.SfGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.model.SfGroupModel;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: SfGroupDAO</p>
 * <p>Description:�����Զ����ɷ������SfGroupDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfGroupDAO extends BaseDAO {

	/**
	 * ���ܣ�SF_GROUP ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfGroupDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfGroupDAO(SfUserDTO userAccount, SfGroupDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}
	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		SfGroupDTO dtoPara = (SfGroupDTO)dtoParameter;
		super.sqlProducer = new SfGroupModel((SfUserDTO)userAccount, dtoPara);
	}

    /**
	 * ���ܣ�������������ѡ��ĸ�����GROUP_THRED�ֶ����ó��Լ���GROUP_ID
	 * @return boolean
	 */
	public boolean updateThirdGroup(){
	    boolean exist = false;
        try {
            SfGroupModel model = (SfGroupModel)sqlProducer;
            SQLModel sqlModel = model.getUpdateThirdGroupModel();
            DBOperator.updateRecord(sqlModel, conn);
            exist = true;
        } catch (DataHandleException e) {
            Logger.logError(e);
        }
		return exist;
	}
}
