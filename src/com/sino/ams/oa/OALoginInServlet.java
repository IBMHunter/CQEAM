package com.sino.ams.oa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dao.BaseLoginDAO;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;

/**
 * <p>
 * Title: SinoApplication
 * </p>
 * <p>
 * Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������
 * </p>
 * <p>
 * Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>
 * Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author ����ʤ
 * @version 0.1
 */
public class OALoginInServlet extends BaseServlet {

	/**
	 * ���е�Servlet������ʵ�ֵķ�����
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @param res
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String forwardURL = "";
		Connection con = null;
		Cookie[] cookies = req.getCookies();
		String ltpaToken = "";
		String smsession = "";
		String kmUserId = "";
		try {
			con = getDBConnection(req);
		} catch (PoolPassivateException e2) {
			openErrorPage(req,res,"ϵͳ��֤ʧ��!");
			e2.printStackTrace();
		}
		SSOLoginDAO ssoLoginDao = new SSOLoginDAO(con);
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				// ��Cookie�л�ȡLtpaToken
				if ("LtpaToken".equals(cookies[i].getName())) {
					ltpaToken = cookies[i].getValue();
					ltpaToken += "==";
				} else if ("SMSESSION".equals(cookies[i].getName())) {
					smsession = cookies[i].getValue();
					smsession += "==";
				}
			}
		}else{
			openErrorPage(req,res,"ϵͳ��֤ʧ��!");
		}
		if(ltpaToken != null && ltpaToken.length() <= 0&&smsession != null && smsession.length() <= 0)
		{
			openErrorPage(req,res,"������յ�¼");
		}
		// �ж�LTPA Token
		if (ltpaToken != null && ltpaToken.length() > 0) {
			if (ltpaToken == null || ltpaToken.trim().length() == 0)
				try {
					throw new Exception("The LtpaToken is null!");
				} catch (Exception e1) {
					openErrorPage(req,res,"������յ�¼");
					e1.printStackTrace();
				}

			try {
				// ���ӳ�ʱʱ��
				System.setProperty("sun.net.client.defaultConnectTimeout",
						"20000");
				// ��ȡ��ʱʱ��
				System
						.setProperty("sun.net.client.defaultReadTimeout",
								"20000");
				// ȡSSO Ӧ�ñ�����userToken��ҳ���ַ
				URL url = new URL(
						"http://kmpassport.bmcc.com.cn/kmsso/index.jsp");
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDefaultUseCaches(false);
				conn.setUseCaches(false);
				conn.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
				// ʹ��LtpaTokenΪcookie
				conn.setRequestProperty("Cookie", "LtpaToken=" + ltpaToken
						+ "; path=/; domain=.bmcc.com.cn");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line = reader.readLine();
				reader.close();
				conn.disconnect();
				// �����û�id
				if (line != null && line.length() > 0
						&& line.indexOf("SSO_KM_USERID") != -1) {
					kmUserId = line.substring("SSO_KM_USERID".length() + 1);
					if (kmUserId.toLowerCase().equals("null"))
						throw new Exception("userId return null!");
				} else {
					openErrorPage(req,res,"ϵͳ��֤ʧ��!");
					throw new Exception("wrong token format file!");
				}

			} catch (MalformedURLException e) {
				openErrorPage(req,res,"ϵͳ��֤ʧ��!");
				System.out.println("LtpaToken url error!");
			} catch (IOException e) {
				openErrorPage(req,res,"ϵͳ��֤ʧ��!");
				System.out.println("Read LTPA Token error!");
			} catch (Exception e) {
				openErrorPage(req,res,"ϵͳ��֤ʧ��!");
				e.printStackTrace();
			}

			if (kmUserId != null && kmUserId.length() > 0) {
				// ��õ�SSO�û�ID������ҵ����������
				// .....
				try {
					List<SfUserDTO> userList = ssoLoginDao.getUserDto(kmUserId);
					if (userList.size() == 1) {
						SfUserDTO userDto = userList.get(0);
						req.setAttribute("loginName", userDto.getLoginName());
						req.setAttribute("loginPwd", userDto.getPassword());
						req.setAttribute("userType", "sso");
						forwardURL = "/servlet/com.sino.framework.security.servlet.UserLoginServlet";
					}else if(userList.size()>1){
						req.setAttribute("listUser", userList);
						forwardURL="/chooseUerPage.jsp";
					}else {
						openErrorPage(req,res,"ϵͳ�޴��û�!");
					}
				} catch (QueryException e) {
					openErrorPage(req,res,"ϵͳ��֤ʧ��!");
					e.printStackTrace();
				}
			}
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
			return;
		}
		// �ж�Siteminder Token
		if (smsession != null && smsession.length() > 0) {
			if (smsession == null || smsession.trim().length() == 0)
				try {
					throw new Exception("The SMSESSION is null!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			try {
				// ���ӳ�ʱʱ��
				System.setProperty("sun.net.client.defaultConnectTimeout",
						"20000");
				// ��ȡ��ʱʱ��
				System
						.setProperty("sun.net.client.defaultReadTimeout",
								"20000");
				// ȡsiteminder������userToken��ҳ���ַ
				URL url = new URL(
						"http://kmapp01.bmcc.com.cn/mvnforum/token.jsp");
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setDefaultUseCaches(false);
				conn.setUseCaches(false);
				conn.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
				// ʹ��SMSESSIONΪcookie
				conn.setRequestProperty("Cookie", "SMSESSION=" + smsession
						+ "; path=/; domain=.bmcc.com.cn");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), "utf-8"));
				String line = reader.readLine();
				reader.close();
				conn.disconnect();
				// �����û�id
				if (line != null && line.length() > 0
						&& line.indexOf("SSO_KM_USERID") != -1) {
					kmUserId = line.substring("SSO_KM_USERID".length() + 1);
					if (kmUserId.toLowerCase().equals("null")) {
						throw new Exception("userId return null!");
					}
				} else {
					openErrorPage(req,res,"ϵͳ��֤ʧ��!");
					throw new Exception("wrong token format file!");
				}

			} catch (MalformedURLException e) {
				openErrorPage(req,res,"ϵͳ��֤ʧ��!");
				System.out.println("SMSESSION Token url error!");
			} catch (IOException e) {
				openErrorPage(req,res,"ϵͳ��֤ʧ��!");
				System.out.println("Read SMSESSION Token error!");
			} catch (Exception e) {
				openErrorPage(req,res,"ϵͳ��֤ʧ��!");
				e.printStackTrace();
			}
			if (kmUserId != null && kmUserId.length() > 0) {
				// ��õ�SSO�û�ID������ҵ����������
				// .....
				try {
					List<SfUserDTO> userList = ssoLoginDao.getUserDto(kmUserId);
					if (userList.size() == 1) {
						SfUserDTO userDto = userList.get(0);
						req.setAttribute("loginName", userDto.getLoginName());
						req.setAttribute("loginPwd", userDto.getPassword());
						req.setAttribute("userType", "sso");
						forwardURL = "/servlet/com.sino.framework.security.servlet.UserLoginServlet";
					}else if(userList.size()>1){
						req.setAttribute("listUser", userList);
						forwardURL="/chooseUerPage.jsp";
					}else{
						openErrorPage(req,res,"ϵͳ�޴��û�!");
					}
				} catch (QueryException e) {
					openErrorPage(req,res,"ϵͳ��֤ʧ��!");
					e.printStackTrace();
				}
			}
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
			return;
		}
	}
	
	private void openErrorPage(HttpServletRequest req,HttpServletResponse res,String errorMsg)
	{
		try {
			res.setContentType("text/html;charset=gbk");
			PrintWriter print=res.getWriter();
			print.print("<script> window.showModalDialog('/ssoLoginError.html?inputStr="+errorMsg+"','','dialogWidth:500px;dialogHeight:100px;scroll:no;status:no')</script>");
			print.close();
			print.flush();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
