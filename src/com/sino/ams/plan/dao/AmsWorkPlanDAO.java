package com.sino.ams.plan.dao;


import java.sql.Connection;

import com.sino.ams.plan.dto.AmsWorkPlanDTO;
import com.sino.ams.plan.model.AmsWorkPlanModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsWorkPlanDAO</p>
 * <p>Description:�����Զ����ɷ������AmsWorkPlanDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class AmsWorkPlanDAO extends BaseDAO {

    private AmsWorkPlanModel amsWorkPlanModel = null;
    private SfUserDTO SfUser = null;
    private SQLModel sModel = null;

    /**
     * ���ܣ������ƻ����� AMS_WORK_PLAN ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsWorkPlanDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsWorkPlanDAO(SfUserDTO userAccount, AmsWorkPlanDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        SfUser = userAccount;
        sModel = new SQLModel();
        initSQLProducer(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsWorkPlanDTO dtoPara = (AmsWorkPlanDTO) dtoParameter;
        super.sqlProducer = new AmsWorkPlanModel((SfUserDTO) userAccount, dtoPara);
        amsWorkPlanModel = (AmsWorkPlanModel) sqlProducer;
    }

    /**
     * ���ܣ����빤���ƻ������AMS_WORK_PLAN�����ݡ�
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("�����ƻ�����");

    }

    /**
     * ���ܣ����¹����ƻ������AMS_WORK_PLAN�����ݡ�
     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("�����ƻ�����");

    }

    /**
     * ���ܣ�ɾ�������ƻ������AMS_WORK_PLAN�����ݡ�
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("�����ƻ�����");

    }

    public void repealData(String planId) throws DataHandleException {
        sModel = amsWorkPlanModel.getDataRepealModel(planId);
        DBOperator.updateRecord(sModel,conn);
  }
    public void compData(String planId)throws DataHandleException {
        sModel = amsWorkPlanModel.updateStatus(planId);
        DBOperator.updateRecord(sModel,conn);
    }
}