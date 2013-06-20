package com.sino.sinoflow.framework.resource.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.web.ServletForwarder;
import com.sino.framework.servlet.BaseServlet;
import com.sino.sinoflow.constant.URLDefineList;


/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ResourceFrmServlet extends BaseServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletForwarder forwarder = new ServletForwarder(req, res);
        forwarder.forwardView(URLDefineList.RES_FRM_PAGE);
    }
}
