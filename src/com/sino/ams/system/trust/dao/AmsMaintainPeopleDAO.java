package com.sino.ams.system.trust.dao;


import java.sql.Connection;

import com.sino.ams.system.trust.dto.AmsMaintainPeopleDTO;
import com.sino.ams.system.trust.model.AmsMaintainPeopleModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsMaintainPeopleDAO</p>
 * <p>Description:�����Զ����ɷ������AmsMaintainPeopleDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsMaintainPeopleDAO extends BaseDAO {

    private AmsMaintainPeopleModel amsMaintainPeopleModel = null;
    private SfUserDTO SfUser = null;

    /**
     * ���ܣ���ά��Ա��(EAM) AMS_MAINTAIN_PEOPLE ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsMaintainPeopleDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsMaintainPeopleDAO(SfUserDTO userAccount, AmsMaintainPeopleDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        SfUser = userAccount;
        initSQLProducer(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsMaintainPeopleDTO dtoPara = (AmsMaintainPeopleDTO) dtoParameter;
        super.sqlProducer = new AmsMaintainPeopleModel((SfUserDTO) userAccount, dtoPara);
        amsMaintainPeopleModel = (AmsMaintainPeopleModel) sqlProducer;
    }

    /**
     * ���ܣ������ά��Ա��(EAM)��AMS_MAINTAIN_PEOPLE�����ݡ�

     */
    public void createData() throws DataHandleException {
    	AmsMaintainPeopleDTO dto=(AmsMaintainPeopleDTO)dtoParameter;
    	SeqProducer seq = new SeqProducer(conn);
    	dto.setUserId(seq.getGUID());
        super.createData();
        getMessage().addParameterValue("��ά��Ա��");
    }

    /**
     * ���ܣ����´�ά��Ա��(EAM)��AMS_MAINTAIN_PEOPLE�����ݡ�

     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("��ά��Ա��");
    }

    /**
     * ���ܣ�ɾ����ά��Ա��(EAM)��AMS_MAINTAIN_PEOPLE�����ݡ�

     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("��ά��Ա��");
	}

}