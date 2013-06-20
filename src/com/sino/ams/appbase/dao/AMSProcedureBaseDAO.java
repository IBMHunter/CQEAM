package com.sino.ams.appbase.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.dto.DTO;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.bean.FlowCommonUtil;
import com.sino.sinoflow.flowinterface.AppFlowBaseDTO;
import com.sino.sinoflow.utilities.CaseRoutine;
import com.sino.sinoflow.utilities.FlowUtil;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public abstract class AMSProcedureBaseDAO extends AMSBaseDAO {//����Ĵ�����Ϊ����ȥ����DAO�ദ�������û�����

    public AMSProcedureBaseDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }


    /**
     * ���ܣ��������̵���
     *
     * @param isSubmit true��ʾ�ύ��false��ʾ�ݴ�
     * @return true��ʾ�ɹ���false��ʾʧ��
     */
    protected boolean processProcedure(boolean isSubmit) {
        boolean operateResult = false;
        if (dtoParameter != null && dtoParameter instanceof AppFlowBaseDTO) {
            prepareProcedureData();
            AppFlowBaseDTO frm = (AppFlowBaseDTO) dtoParameter;
            FlowCommonUtil fcu = new FlowCommonUtil(frm, conn);
            operateResult = fcu.processProcedure(isSubmit);
        }
        return operateResult;
    }

    /**
     * <p>ע�⣺������������ʹ�ã�������ȷ����������ύ���õ���SUBMIT_ACTION</p>
     * <p>ע�⣺������֮������protected����ȷ�����������е��ã���������servlet�е���</p>
     * ���ܣ��������̵���
     *
     * @return true��ʾ�ɹ���false��ʾʧ��
     */
    protected boolean processProcedure() {
        boolean operateResult = false;
        if (dtoParameter != null && dtoParameter instanceof AppFlowBaseDTO) {
            prepareProcedureData();
            AppFlowBaseDTO frm = (AppFlowBaseDTO) dtoParameter;
            FlowCommonUtil fcu = new FlowCommonUtil(frm, conn);
            String act = frm.getAct();
            boolean isSubmit = act.equals(WebActionConstant.SUBMIT_ACTION);
            operateResult = fcu.processProcedure(isSubmit);
        }
        return operateResult;
    }

    /**
     * ���ܣ��˻����̵���
     *
     * @return true��ʾ�ɹ���false��ʾʧ��
     */
    public boolean rejectProcedure() {
        if (dtoParameter != null && dtoParameter instanceof AppFlowBaseDTO) {
            AppFlowBaseDTO frm = (AppFlowBaseDTO) dtoParameter;
            FlowCommonUtil fcu = new FlowCommonUtil(frm, conn);
            return fcu.reject();
        }
        return false;
    }

    /**
     * ���ܣ��������̵���
     *
     * @return true��ʾ�ɹ���false��ʾʧ��
     */
    public boolean cancelProcedure() {
        boolean operateResult = false;
        try {
            if (dtoParameter != null && dtoParameter instanceof AMSFlowDTO) {
                AMSFlowDTO frm = (AMSFlowDTO) dtoParameter;
                FlowCommonUtil fcu = new FlowCommonUtil(frm, conn);
                operateResult = fcu.processDel();
//                operateResult = FlowUtil.removeCase(frm.getSf_caseID(), conn);
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return operateResult;
    }


    /**
     * ���ܣ�׼����������,��Ӧ��ʵ��
     */
    protected void prepareProcedureData(){

    }

}
