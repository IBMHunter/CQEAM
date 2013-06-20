package com.sino.flow.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.util.StrUtil;
import com.sino.flow.bean.FlowBusiness;
import com.sino.flow.exception.FlowException;
import com.sino.framework.security.bean.SessionUtil;

/**
 * Created by wwb.
 * User: V-wangwenbin
 * Date: 2007-9-20
 * Time: 17:05:34
 */
public class ApproveDAO {
    private HttpServletRequest req;
    private Connection conn;
    SfUserDTO userAccount;

    public ApproveDAO(HttpServletRequest req, Connection conn) {
        this.req = req;
        this.conn = conn;
        this.userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
    }

    //ͨ����ת��ת����һ������
    public void pass() throws SQLException, FlowException {
        try {
            conn.setAutoCommit(false);
            //step1;����Ӧ��ҵ��
            //step2:����ת����һ�ڵ�
            /*
            *���̹���������˵����
            *conn,����
            *request,
            *appId,Ӧ��ID��Ҳ����Ӧ������
            *appNum,Ӧ�ñ��,�����ݺ�
            *userId,��ǰ�û�ID
            *userName,��ǰ�û��û���
            */
            String appId = StrUtil.nullToString(req.getParameter("appId"));
            String appNum = StrUtil.nullToString(req.getParameter("appNum"));
            FlowBusiness fb = new FlowBusiness(conn, req, appId, appNum, StrUtil.nullToString(userAccount.getUserId()), userAccount.getUsername());
            /*
            *flow2Next����˵��
            *��һ��������������������Ϊ�գ��Զ�����
            *�ڶ����������Ƿ��Ͷ���
            */
            fb.flow2Next("", true);
            conn.commit();
        } catch (FlowException e) {
            conn.rollback();
            throw e;
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }

    }

    //�˻�
    public void back() throws FlowException, SQLException {
        try {
            conn.setAutoCommit(false);
            //step1;����Ӧ��ҵ��
            //step2:����ת����һ�ڵ�
            /*
            *���̹���������˵����
            *conn,����
            *request,
            *appId,Ӧ��ID��Ҳ����Ӧ������
            *appNum,Ӧ�ñ��,�����ݺ�
            *userId,��ǰ�û�ID
            *userName,��ǰ�û��û���
            */
            String appId = StrUtil.nullToString(req.getParameter("appId"));
            String appNum = StrUtil.nullToString(req.getParameter("appNum"));
            FlowBusiness fb = new FlowBusiness(conn, req, appId, appNum, StrUtil.nullToString(userAccount.getUserId()), userAccount.getUsername());
             /*
            *reject2Begin����˵��
            *��һ��������������������Ϊ�գ��Զ�����
            *�ڶ����������Ƿ��Ͷ���
            */
            fb.reject2Begin("",true);
            //����˻���һ��������
            //fb.reject("",true);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } catch (FlowException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
