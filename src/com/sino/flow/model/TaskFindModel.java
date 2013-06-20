package com.sino.flow.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.UploadException;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowParmDTO;

/**
 * <p>Title: SinoCPS</p>
 * <p>Description: �����ƶ����к���ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TaskFindModel {
    private FlowParmDTO dto;
    private HttpServletRequest req;

    public TaskFindModel(FlowParmDTO dto, HttpServletRequest req) {
        this.dto = dto;
        this.req = req;
    }

    public void setDto(FlowParmDTO dto) {
        this.dto = dto;
    }

    /**
     * ���ܣ���ȡ�����еĵ�һ���ӵ�
     *
     * @return SQLModel
     */
    public SQLModel getBeginTaskModel() {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT"
                + " STD.*,"
                + " SPD.PROC_NAME"
                + " FROM"
                + " SF_TASK_DEFINE STD,"
                + " SF_PROCEDURE_DEF SPD"
                + " WHERE"
                + " STD.PROC_ID = SPD.PROC_ID"
                + " AND SPD.ORGANIZATION_ID = ?"
                + " AND STD.TASK_PROP = ?"
                + " AND SPD.PROC_ID = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(dto.getOrgId());
        sqlArgs.add(FlowConstant.TASK_PROP_START);
        sqlArgs.add(dto.getProcId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡ��ǰ�ڵ����һ�ڵ�.
     *
     * @return SQLModel
     * @throws UploadException
     */
    public SQLModel getNextTaskModel(String flowCode) throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr =
                "SELECT STDN.TASK_ID,\n" +
                        "       STDN.TASK_NAME,\n" +
                        "       STDN.TASK_MSG,\n" +
                        "       STDN.TASK_PROP,\n" +
                        "       STDN.PROC_ID,\n" +
                        "       STDN.HIDDEN_RIGHT,\n"+
                        "       STDN.ORGANIZATION_ID,\n" +
                        " CASE WHEN STDN.DEPT_ID=-1 THEN ? ELSE STD.DEPT_ID END DEPT_ID,"+
                        "       SFD.FLOW_DESC\n" +
                        "  FROM SF_TASK_DEFINE   STD,\n" +
                        "       SF_TASK_DEFINE   STDN,\n" +
                        "       SF_FLOW_DEFINE   SFD,\n" +
                        "       SF_PROCEDURE_DEF SPD\n" +
                        " WHERE STD.TASK_ID = SFD.FROM_TASK_ID\n" +
                        "   AND SFD.TO_TASK_ID = STDN.TASK_ID\n" +
                        "   AND STDN.PROC_ID = SPD.PROC_ID\n" +
                        "   AND SPD.PROC_ID = SFD.PROC_ID\n" +
                        "   AND STD.PROC_ID = STDN.PROC_ID\n" +
                        "   AND SFD.DISABLED = 'N'\n" +
                        "   AND STD.TASK_ID = dbo.NVL(?, SF_FLOW_PKG.GET_BEGIN_TASK(?))\n" +
                        "   AND SPD.PROC_ID = ?\n";
        // "   AND SFD.FLOW_CODE = ?";

        List sqlArgs = new ArrayList();
        sqlArgs.add(dto.getCurrDeptId());
        sqlArgs.add(dto.getCurrTaskId());
        sqlArgs.add(dto.getProcId());
        sqlArgs.add(dto.getProcId());

        if (flowCode != null && !flowCode.equals("")) {
            sqlStr += " AND SFD.FLOW_CODE = '" + flowCode + "'";
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��жϵ�ǰTask�Ƿ���ʼTask��
     *
     * @return SQLModel
     * @throws UploadException
     */
    public SQLModel getTaskPropModel() throws UploadException {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT"
                + " TASK_PROP"
                + " FROM"
                + " SF_ACT         SA,"
                + " SF_TASK_DEFINE STD"
                + " WHERE"
                + " SA.CUR_TASK_ID = STD.TASK_ID"
                + " SA.PROC_ID=STD.PROC_ID "
                + " AND SA.CUR_USERID = ?"
                + " AND SA.APP_ID = ?"
                + " AND STD.PROC_ID=?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(dto.getUserId());
        sqlArgs.add(dto.getApplyId());
        sqlArgs.add(dto.getProcId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    //* ͨ��OU��������ȡ����ID
    public SQLModel getProcIdModel() {
        String sql =
                "SELECT SPD.PROC_ID\n" +
                        "  FROM SF_PROCEDURE_DEF SPD\n" +
                        " WHERE SPD.PROC_NAME = ?\n" ;
                      //  "   AND SPD.ORGANIZATION_ID = ?";
        ArrayList al = new ArrayList();
        al.add(dto.getProcName());
        //al.add(dto.getOrgId());
        SQLModel sm = new SQLModel();
        sm.setArgs(al);
        sm.setSqlStr(sql);
        return sm;
    }

    /**
     * �������̺�������ʱ���ȸ���actId�ҵ������Ϣ
     *
     * @param actId
     * @return
     */
    public SQLModel getActDetail(String actId) {
        SQLModel sm = new SQLModel();
        String sql =
                "SELECT * FROM SF_ACT SA WHERE SA.ACTID = ?";
        ArrayList al = new ArrayList();
        al.add(actId);
        sm.setArgs(al);
        sm.setSqlStr(sql);
        return sm;
    }

    /**
     * ȡ��һ������ĸ����������������1����Ӧ�ü����������͡�
     *
     * @return
     * @throws UploadException
     */
    public SQLModel getNextTaskCount() throws UploadException {
        String sql =
                "SELECT COUNT(*) TOTAL\n" +
                        "  FROM SF_FLOW_DEFINE SFD\n" +
                        " WHERE SFD.FROM_TASK_ID = dbo.NVL(?,SF_FLOW_PKG.GET_BEGIN_TASK(?))\n" +
                        // "   AND SFD.FLOW_CODE = ?" +
                        "   AND SFD.DISABLED = 'N' ";
        ArrayList al = new ArrayList();
        al.add(dto.getCurrTaskId());
        al.add(dto.getProcId());
        SQLModel sm = new SQLModel();
        sm.setArgs(al);
        sm.setSqlStr(sql);
        return sm;
    }

    public void setCurrTaskId(String currTaskId) {
        dto.setCurrTaskId(currTaskId);
    }

    public void setProcId(String procId) {
        dto.setProcId(procId);
    }
}
