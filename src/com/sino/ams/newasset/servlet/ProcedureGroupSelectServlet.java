package com.sino.ams.newasset.servlet;

import com.sino.ams.newasset.service.ProcedureGroupSelectService;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ProcedureGroupSelectServlet extends BaseServlet {
	/**
	 * ���е�Servlet������ʵ�ֵķ�����
	 *
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Connection conn = null;
		try {
            conn = getDBConnection(req);
			SfUserDTO user = (SfUserDTO) getUserAccount(req);
            ProcedureGroupSelectService service = new ProcedureGroupSelectService(user, null, conn);
            service.setServletConfig(getServletConfig(req));
            String groups = service.getProSpecialGroups();
            processAjaxResponse(groups, res);
		} catch (Throwable ex) {
            Logger.logError(ex);
		} finally {
			closeDBConnection(conn);
			if (!forwardURL.equals("")) {
				ServletForwarder forwarder = new ServletForwarder(req, res);
				forwarder.forwardView(forwardURL);
			}
		}
	}


    /**
     * ���ܣ�����Ajax����
     *
     * @param responseResult     Http��Ӧ����
     * @param res     Http��Ӧ����
     * @throws java.io.IOException ��ӦAjax����ʱ�׳����쳣
     */
    private void processAjaxResponse(String responseResult, HttpServletResponse res) throws IOException {
        if (responseResult != null) {
            res.setContentType("text/plain;charset=GBK");
            PrintWriter out = res.getWriter();
            if (!StrUtil.isEmpty(out)) {
                out.print(responseResult);
                System.out.println(responseResult);
            }
            out.close();
        }
    }
}