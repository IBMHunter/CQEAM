package com.sino.sso.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.framework.servlet.BaseServlet;

/**
 * User: T_zhoujinsong
 * Date: 11-12-5 ����2:47
 * Function: ɽ��Portal������룬�ɹ�������ʲ�ʵ��ϵͳ��ҳ�桢��������ʲ�ʵ��ϵͳ��¼ҳ��
 */
public class PortalLoginServlet extends BaseServlet {
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        FilterConfigDTO filterDTO = null;
        Message msg = new Message();
        try {
            filterDTO = SessionUtil.getFilterConfigDTO(req);
            SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
            if (userAccount != null && userAccount.getUserId() > 0) {
                String home = StrUtil.nullToString(req.getParameter("home"));
                forwardURL = filterDTO.getLoginSuccessURL();
                if (home.equals("1")) {  //ת�뵽
                    forwardURL += "?home=1";
                }
            } else {
                msg.setIsError(true);
                msg.setMessageValue("�ǳ���Ǹ����û��Ȩ�ޣ����ڹ���ϵͳ����ݡ�ϵͳ����Ȩ�ޡ�������ӦȨ�ޡ�");
                forwardURL = "/login.jsp";
            }
        } catch (Exception e) {
            Logger.logError(e);
            forwardURL = "/login.jsp";
        } finally {
            setHandleMessage(req, msg);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
        }
    }
}
