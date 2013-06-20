package com.sino.flow.dao;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.UploadException;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.constant.MessageDefineList;
import com.sino.flow.dto.FlowParmDTO;
import com.sino.flow.dto.SfTaskDefineDTO;
import com.sino.flow.model.TaskFindModel;

/**
 * <p>Title: SinoCPS</p>
 * <p>Description: �����ƶ����к���ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class TaskFindDAO extends BaseProcessorDAO {
    private TaskFindModel taskFindModel = null;

    public TaskFindDAO(Connection conn, HttpServletRequest req, FlowParmDTO dto) {
        super(conn, req);
        taskFindModel = new TaskFindModel(dto, req);
    }

    /**
     * ���ܣ���ȡ��һ���ڵ�
     *
     * @return SfTaskDefineDTO
     * @throws QueryException
     */
    public SfTaskDefineDTO getBeginTask() throws QueryException {
        SfTaskDefineDTO beginTask = null;
        SQLModel sqlModel = taskFindModel.getBeginTaskModel();
        SimpleQuery queryBean = new SimpleQuery(sqlModel, conn);
        queryBean.setDTOClassName(SfTaskDefineDTO.class.getName());
        queryBean.executeQuery();
        if (queryBean.hasResult()) {
            beginTask = (SfTaskDefineDTO) queryBean.getFirstDTO();
        } else {
            setProcessMsg(MessageDefineList.DATA_NOT_EXISTS);
        }
        return beginTask;
    }

    /**
     * ���ܣ���ȡ��ǰ�ڵ����һ�ڵ�.�����һ�ڵ��ж������Ϊ����������Ϊֱ��.
     *
     * @return DTOSet
     * @throws QueryException
     */
    public JSONArray getNextTasks(String flowCode) throws QueryException, UploadException, JSONException, ContainerException {
        JSONArray arr = new JSONArray();
        SQLModel sqlModel = taskFindModel.getNextTaskModel(flowCode);
        SimpleQuery queryBean = new SimpleQuery(sqlModel, conn);
        queryBean.executeQuery();
        RowSet rs = queryBean.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            for (int i = 0; i < rs.getSize(); i++) {
                Row row = rs.getRow(i);
                JSONObject obj = new JSONObject();
                obj.put("nextTaskId", row.getValue("TASK_ID")); //��һ�ڵ�
                obj.put("nextDeptId", row.getValue("DEPT_ID"));//��һ������
                obj.put("procId", row.getValue("PROC_ID"));//����ID
                obj.put("flowDesc", row.getValue("FLOW_DESC"));//��������
                obj.put("nextTaskName", row.getValue("TASK_NAME"));//�ڵ���
                obj.put("nextHiddenRight", row.getValue("HIDDEN_RIGHT"));//��һ�ڵ���������ԣ��˴�Ҫ��Ŀ���ǣ�����һ�ڵ��Ƿ�Ϊ������˵Ľڵ�
                arr.put(obj);
            }
        }
        return arr;
    }

    public String getTaskProp() throws QueryException, UploadException, ContainerException {
        String taskProp = "";
        SQLModel sqlModel = null;
        sqlModel = taskFindModel.getTaskPropModel();
        SimpleQuery queryBean = new SimpleQuery(sqlModel, conn);
        queryBean.executeQuery();
        if (queryBean.hasResult()) {
            Row row = queryBean.getFirstRow();
            taskProp = String.valueOf(row.getValue("TASK_PROP"));
        }
        return taskProp;
    }

    /**
     * ���ܣ��жϵ�ǰTask�Ƿ���ʼTask��
     *
     * @return boolean
     * @throws QueryException
     */
    public boolean isBeginTask() throws QueryException, UploadException, ContainerException {
        String taskProp = getTaskProp();
        return taskProp.equals(FlowConstant.TASK_PROP_START);
    }

    /**
     * ���ܣ��жϵ�ǰ�ڵ��Ƿ������е���ֹ�ڵ㡣
     *
     * @return boolean
     * @throws QueryException
     */
    public boolean isEndTask() throws QueryException, UploadException, ContainerException {
        String taskProp = getTaskProp();
        return taskProp.equals(FlowConstant.TASK_PROP_START);
    }

    public void setProcId2DTO() throws QueryException, ContainerException {
        SQLModel sm = taskFindModel.getProcIdModel();
        SimpleQuery sq = new SimpleQuery(sm, conn);
        String procId = "";
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            procId = (String) row.getValue("PROC_ID");
        }
        taskFindModel.setProcId(procId);

    }

    /**
     * ����ǰ���̼�¼��
     *
     * @param actId
     * @throws QueryException
     * @throws ContainerException
     */
    public void setCurrAct2DTO(String actId) throws QueryException, ContainerException {
        String currTaskId = req.getParameter("currTaskId");
        String procId = req.getParameter("procId");
        taskFindModel.setCurrTaskId(currTaskId);
        taskFindModel.setProcId(procId);
//        SQLModel sm = taskFindModel.getActDetail(actId);
//        SimpleQuery sq = new SimpleQuery(sm, conn);
//        sq.executeQuery();
//        RowSet rs = sq.getSearchResult();
//        if (rs != null && !rs.isEmpty()) {
//            Row row = rs.getRow(0);
//            String currTaskId = (String) row.getValue("CUR_TASK_ID");
//            String procId = (String) row.getValue("PROC_ID");
//
//        }
    }

    public int getNextTaskCount() throws QueryException, UploadException, ContainerException {
        int count = 0;
        SQLModel sm = taskFindModel.getNextTaskCount();
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && !rs.isEmpty()) {
            Row row = rs.getRow(0);
            String total = (String) row.getValue("TOTAL");
            count = Integer.parseInt(total);
        }
        return count;
    }

}
