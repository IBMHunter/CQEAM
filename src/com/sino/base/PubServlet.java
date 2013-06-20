package com.sino.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public abstract class PubServlet extends HttpServlet {

    /**
     * ���е�Servlet������ʵ�ֵķ�����
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public abstract void performTask(HttpServletRequest req,
                                     HttpServletResponse res) throws
            ServletException, IOException;

    /**
     * ���ܣ���doGet����ת��doPost��������
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
        super.doPost(req, res);
    }

    /**
     * ��Web���������õķ������������performTask������
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void service(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
        performTask(req, res);
    }
}
