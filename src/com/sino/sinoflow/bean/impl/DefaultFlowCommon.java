package com.sino.sinoflow.bean.impl;

import java.sql.Connection;

import org.json.JSONObject;

import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.sinoflow.bean.FlowCommon;
import com.sino.sinoflow.flowinterface.AppFlowBaseDTO;
import com.sino.sinoflow.utilities.CaseRoutine;
import com.sino.sinoflow.utilities.FlowUtil;

/**
 * 
 * @ϵͳ����: 
 * @��������: Ĭ�������ύ�ȹ���
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Dec 15, 2011
 */
public class DefaultFlowCommon implements FlowCommon{
	private AppFlowBaseDTO bForm = null;
    private Connection conn = null;

    public DefaultFlowCommon(AppFlowBaseDTO bForm, Connection conn) { 
        this.bForm = bForm;
        this.conn = conn;
    }
     

    /**
     * ���ܣ�����������Ϣ
     * @param isSubmit boolean
     * @return boolean
     */
    public boolean processProcedure(boolean isSubmit) {
        boolean operateResult = false;
        CaseRoutine cr = new CaseRoutine();
        String appFieldValue = bForm.getSf_appFieldValue();
        String[] valueList = appFieldValue.split("&;&");
        String keyword = bForm.getOrderNo();
        if (keyword.equals("")) {
            keyword = valueList[3];
        }
        String subject = bForm.getOrderName();
        if (subject.equals("")) {
            subject = valueList[4];
        }

        if (isSubmit) {
            operateResult = cr.caseComplete(appFieldValue, getPrimaryKey(), keyword, subject, conn);
        } else {
            operateResult = cr.caseSave(appFieldValue, getPrimaryKey(), bForm.getOrderNo(), bForm.getOrderName(),
                    conn);
        } 

        return operateResult;
    }

    /**
     * ������������
     * @return
     */
    public boolean processDel()  {
        boolean operateResult = false;
        try {
            if (!StrUtil.isEmpty(bForm.getSfOpinion())) {
                operateResult = FlowUtil.removeCase(bForm.getSf_caseID(), bForm.getSfOpinion(), conn);
            } else if (!StrUtil.isEmpty(bForm.getSfAppName())) {
                operateResult = FlowUtil.removeCase(bForm.getSfAppName(), StrUtil.nullToString(getPrimaryKey()), "�������뵥��", conn);
            } else {
                operateResult = FlowUtil.removeCase(bForm.getSf_caseID(), conn);
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return operateResult;
    }

    /**
     * ȡ��������Ϣ�� �������
     * @return
     */
    public boolean processCancel() {
    	return processDel();
//        CaseRoutine cr = new CaseRoutine();
//        String appFieldValue = bForm.getSf_appFieldValue();
//        return cr.caseCancel(appFieldValue, conn);
    }

    /**
     * �˻�ʱ���õķ���
     *
     * @return boolean
     */
    public boolean reject() {
        CaseRoutine cr = new CaseRoutine();
        return cr.caseBack(bForm.getSf_appFieldValue(), conn);
    }


    public String getFlowCode() throws Exception {
        String flowCode = "";
        String fieldValue = "";

        if (bForm != null) {
            fieldValue = bForm.getSf_appFieldValue();
        } else {
            throw new DataHandleException("�������̲�����ʱ,δ����DTO����");
        }

        if (!fieldValue.equals("")) {
            JSONObject jObj = new JSONObject(fieldValue);
            String nextTaskDate = jObj.getString("sf_nextTaskData");
            JSONObject nextTaskObj = new JSONObject(nextTaskDate);
            flowCode = nextTaskObj.getString("flowCode");
        }
        return flowCode;
    }
 
 

    /**
     * ��������
     *
     * @return
     */
    private String getPrimaryKey() {
        String primaryKey = bForm.getPrimaryKey();
        if (StrUtil.isEmpty(primaryKey)) {
            primaryKey = bForm.getApp_dataID();
        }
        return primaryKey;
    }


	public AppFlowBaseDTO getBForm() {
		return bForm;
	}


	public void setBForm(AppFlowBaseDTO form) {
		bForm = form;
	}


	public Connection getConn() {
		return conn;
	}


	public void setConn(Connection conn) {
		this.conn = conn;
	}
 
}
