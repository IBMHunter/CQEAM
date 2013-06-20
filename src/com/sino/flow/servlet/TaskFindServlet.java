package com.sino.flow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sino.base.PubServlet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.UploadException;
import com.sino.base.log.Logger;
import com.sino.flow.dao.TaskFindDAO;
import com.sino.flow.dto.FlowParmDTO;

/**
 * <p>Title: SinoCPS</p>
 * <p>Description: �����ƶ����к���ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 */
public class TaskFindServlet extends PubServlet {
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Connection conn = null;
        TaskFindDAO taskFindDAO = null;
        JSONObject jsonObject;//�ӿͻ��˶������Ķ���
        JSONArray retArray;//���ظ��ͻ��˵Ķ���
        res.setContentType("text/xml;charset=GBK");
        req.setCharacterEncoding("GBK");
        PrintWriter pw = res.getWriter();
        try {
            //step1:read data from request
            ClientReader cr = new ClientReader();
            String json = cr.readToJSON(req);
            jsonObject = new JSONObject(json);
            //step2:fetch next task information by current parameter
            conn = DBManager.getDBConnection();
            FlowParmDTO dto = new FlowParmDTO();//MODEL�õ��Ĳ�����������һ��DTO
            dto.setCurrDeptId(jsonObject.getString("deptId"));
            dto.setProcName(jsonObject.getString("procdureName"));
            dto.setOrgId(jsonObject.getString("orgId"));
            String actId = jsonObject.getString("actId");
            taskFindDAO = new TaskFindDAO(conn, req, dto);
            if (actId != null && !actId.equals("")) {  //�Ѿ������̼�¼
                taskFindDAO.setCurrAct2DTO(actId);//����ǰACT��Ҫ����Ϣ���ȥ
            } else { //��һ���ύ����ʱ ��û�����̼�¼
                taskFindDAO.setProcId2DTO();  //ͨ���������ͺ�OU�ҵ�����ID
            }
            taskFindDAO = new TaskFindDAO(conn, req, dto);
            //step3:�ж��¸������м���
            int nextTaskCount = taskFindDAO.getNextTaskCount();
            String flowCode = "";
            if (nextTaskCount > 1) {//����������һ��������Ӧ���ṩ���̿����룬�����������ͻ���ʾȫ�����û�ȥѡ
                flowCode = jsonObject.getString("flowCode");
            }
            retArray = taskFindDAO.getNextTasks(flowCode);
            pw.print(retArray.toString());
        } catch (PoolException e) {
            Logger.logError(e);
            pw.write("ERROR");
        } catch (QueryException e) {
            Logger.logError(e);
            pw.write("ERROR");
        } catch (ContainerException e) {
            Logger.logError(e);
            pw.write("ERROR");
        } catch (JSONException e) {
            Logger.logError(e);
            pw.write("ERROR");
        } catch (UploadException e) {
            Logger.logError(e);
            pw.write("ERROR");
        } finally {
            pw.flush();
            pw.close();
            DBManager.closeDBConnection(conn);
        }
    }
}
