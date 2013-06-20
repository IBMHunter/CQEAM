package com.sino.framework.security.filter;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.FilterConfig2DTO;
import com.sino.base.exception.ConfigException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.message.MessageManager;
import com.sino.base.util.ArrUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.validate.FilterConfigValidator;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.bean.FormDataProducer;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dao.PrivilegeAuthenticator;
import com.sino.framework.security.dto.FilterConfigDTO;

/**
 *
 * <p>Description: Ȩ�޿��Ƽ����� </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ ,��Ȩ���� Copyright (c) 2003-2008��
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * <p>Author ����˼ŵ����Ϣ�������޹�˾
 * <p>Version 2.0
 */

public class WebAccessFilter implements Filter {
	private FilterConfig config = null;
	private FilterConfigDTO filterDTO = null;
    private String[] exceptURLs = null;
	private String[] exceptServletArr = null;
	private String[] exceptJSPArr = null;

	/**
	 * ���ܣ����������ļ���ʼ��Filter��
	 * @param config FilterConfig
	 * @throws ServletException
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		initConfig();
	}

	/**
	 * ���ܣ���ȡFilter������Ϣ
	 * @throws ServletException ServletException
	 */
	private void initConfig() throws ServletException {
		try {
			ServletContext serContext = config.getServletContext();
			String attrName = WebConstant.FILTER_DTO;
			filterDTO = (FilterConfigDTO) serContext.getAttribute(attrName);
			serContext.setAttribute("modulePath", config.getInitParameter("modulePath"));
			if (filterDTO == null) {
				FilterConfig2DTO config2DTO = new FilterConfig2DTO(config);
				config2DTO.setDTOClassName(FilterConfigDTO.class.getName());
				filterDTO = (FilterConfigDTO) config2DTO.getDTO();
				FilterConfigValidator.validateFilterConfig(filterDTO);
				if (!StrUtil.isEmpty(filterDTO.getExceptions())) {
					this.exceptURLs = StrUtil.splitStr(filterDTO.getExceptions());
				}
				if (!StrUtil.isEmpty(filterDTO.getExceptServlets())) {
					this.exceptServletArr = StrUtil.splitStr(filterDTO.getExceptServlets());
				}
				if (!StrUtil.isEmpty(filterDTO.getExceptJSPs())) {
					this.exceptJSPArr = StrUtil.splitStr(filterDTO.getExceptJSPs());
				}
				SessionUtil.saveFilterConfig(serContext, filterDTO);
			}
		} catch (DTOException ex) {
			ex.printLog();
			throw new ServletException(ex);
		} catch (ConfigException ex) {
			ex.printLog();
			throw new ServletException(ex);
		}
	}

	/**
	 * ���ܣ����лỰ��Ȩ�޵���֤
	 * @param request ServletRequest
	 * @param response ServletResponse
	 * @param chain FilterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
		ServletException {
		if (chain == null) {
			return;
		}
        BaseUserDTO userAccount;
        String reDirectURL; //��Ҫ�����Ա��ض����URL
        String authenticateURL; //��Ҫ��֤Ȩ�޵�URL
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        userAccount = SessionUtil.getUserAccount(req);
        reDirectURL = req.getRequestURI();
        if (StrUtil.isEmpty(reDirectURL) || reDirectURL.equals("/")) {
            reDirectURL = filterDTO.getLoginUrl();
        }
        authenticateURL = reDirectURL;
        String queryStr = req.getQueryString();
        if (!StrUtil.isEmpty(queryStr)) {
            reDirectURL = reDirectURL + "?" + queryStr;
        }
        boolean bNeedChain = true;
        if (userAccount == null) {
			if (needSessionValidate(authenticateURL)) {
				processNoSessionData(req, res, reDirectURL);
                bNeedChain = false;
            }
		} else if (needPriviValidate(authenticateURL)) {
			boolean hasPrivilege = validatePrivilege(req, res, userAccount, authenticateURL);
			if (!hasPrivilege){
                bNeedChain = false;
            }
        }
        if(bNeedChain){
			chain.doFilter(request, response);
		}
	}

	/**
	 * ���ܣ���ȡ��Ϣ����
	 * @param messageKey String
	 * @return Message
	 */
	private Message getMessage(String messageKey) {
		Message refMessage = MessageManager.getMessage(messageKey);
		Message message;
		if(refMessage != null){
			message = new Message();
			message.setMessageKey(messageKey);
			message.setMessageValue(refMessage.getMessageValue(false));
			message.setParent(refMessage.getParent());
		} else {
			message = Message.getUndefinedMessage();
		}
		return message;
	}

	/**
	 * ���ܣ���鵱ǰURL�Ƿ���Ҫ�Ự��֤��
	 * @param authenticateURL String
	 * @return boolean
	 */
	private boolean needSessionValidate(String authenticateURL) {
		boolean needValidate = true;
		String singleURL = getSingleURL(authenticateURL);
		if(ArrUtil.contains(exceptJSPArr, singleURL)){
			needValidate = false;
		} else if (isRedirectURL(authenticateURL)) {
			needValidate = false;
		} else if (authenticateURL.endsWith(filterDTO.getLoginUrl())) {
			needValidate = false;
        } else if (authenticateURL.indexOf(filterDTO.getTimeOutUrl()) > -1) {
            needValidate = false;
        } else if (authenticateURL.indexOf(filterDTO.getNoPriviledgeURL()) > -1) {
            needValidate = false;
         } else if (authenticateURL.indexOf(filterDTO.getLoginServlet()) > -1) {
			needValidate = false;
		} else if (authenticateURL.indexOf(filterDTO.getLogOutServlet()) > -1) {
			needValidate = false;
		} else if (authenticateURL.indexOf(filterDTO.getSessionServlet()) > -1) {
			needValidate = false;
		} else if (ArrUtil.contains(exceptServletArr, singleURL)) {
			needValidate = false;
		} else {
			if (exceptURLs != null && exceptURLs.length > 0) {
				int index = authenticateURL.lastIndexOf(".");
				if (index == -1) {
					index = authenticateURL.lastIndexOf("/");
				}
				String urlExtName = authenticateURL.substring(index + 1);
				needValidate = !ArrUtil.contains(exceptURLs, urlExtName, true);
			}
		}
		return needValidate;
	}

	/**
	 * ���ܣ���鵱ǰURL�Ƿ���ҪȨ����֤��
	 * @param authenticateURL String
	 * @return boolean
	 */
	private boolean needPriviValidate(String authenticateURL) {
		boolean needValidate = true;
		String singleURL = getSingleURL(authenticateURL);
		if (needSessionValidate(authenticateURL)) {
			if (StrUtil.endsWith(authenticateURL, exceptJSPArr)) {
				needValidate = false;
			} else if (ArrUtil.contains(exceptServletArr, singleURL)) {
				needValidate = false;
			}
		}
        //�ж������URL�ĺ�׺�ǲ������ڲ����Ȩ�޵ĺ�׺
        if (exceptURLs != null && exceptURLs.length > 0) {
            int index = authenticateURL.lastIndexOf(".");
            if (index == -1) {
                index = authenticateURL.lastIndexOf("/");
            }
            String urlExtName = authenticateURL.substring(index + 1);
            needValidate = !ArrUtil.contains(exceptURLs, urlExtName, true);
        }
        return needValidate;
	}

	/**
	 * ���ܣ���ȡURL���еľ����ļ�
	 * @param authenticateURL String
	 * @return String
	 */
	private String getSingleURL(String authenticateURL) {
		String singleURL = authenticateURL;
		int servletIndex = authenticateURL.indexOf("/servlet");
		int jspIndex = authenticateURL.indexOf(".jsp");
		if (servletIndex > -1 || jspIndex > -1) {
			int index = authenticateURL.lastIndexOf("?");
			if (index != -1) {
				singleURL = authenticateURL.substring(0, index);
			}
			if (servletIndex > -1) {
				index = singleURL.lastIndexOf(".");
				if (index == -1) {
					index = singleURL.lastIndexOf("/");
				}
				singleURL = singleURL.substring(index + 1);
			} else {
				index = singleURL.lastIndexOf("/");
				singleURL = singleURL.substring(index + 1);
			}
		}
		return singleURL;
	}

	/**
	 * ���ܣ�����ỰʧЧʱ��Ҫ���������ݣ�����¼�ỰʧЧǰ�û������URL��
	 * �Ա�Ự�ָ�ʱ�ָ��û������ݺ�����
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @param reDirectURL reDirectURL
	 * @throws ServletException ServletException
	 */
	private void processNoSessionData( HttpServletRequest req,HttpServletResponse res,String reDirectURL) throws ServletException {
		try {
			FormDataProducer dataProducer = new FormDataProducer(req, reDirectURL);
			dataProducer.produceData();
			ServletContext context = config.getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher(filterDTO.getTimeOutUrl());
			Message message = (Message) req.getAttribute(MessageConstant.MESSAGE_DATA);
			if (message == null) {
				message = getMessage(MsgKeyConstant.SESSION_TIME_OUT);
			}
			message.setIsError(true);
			req.setAttribute(MessageConstant.MESSAGE_DATA, message);
			rd.forward(req, res);
		} catch (IOException ex) {
			Logger.logError(ex);
			throw new ServletException(ex);
		}
	}

	/**
	 * ���ܣ�����Ȩ����֤
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @param userAccount BaseUserDTO
	 * @param authenticateURL String
	 * @return boolean
	 * @throws ServletException ServletException
	 */
	private boolean validatePrivilege(HttpServletRequest req,HttpServletResponse res,BaseUserDTO userAccount,String authenticateURL) throws ServletException {
		boolean hasPrivilege = false;
		Connection conn = null;
		try {
			conn = DBManager.getDBConnection();
			Object[] consParas = new Object[2];
			consParas[0] = userAccount;
			consParas[1] = conn;
			String authenticator = filterDTO.getAuthenticator();
			PrivilegeAuthenticator priviAuthenticator;
			priviAuthenticator = (PrivilegeAuthenticator) ReflectionUtil.getInstance(authenticator, consParas);
			hasPrivilege = priviAuthenticator.hasPrivilege(authenticateURL);
            String resoureName = priviAuthenticator.getUrlName();
            if(!hasPrivilege){
                processNoPrivilege(resoureName,authenticateURL,req,res);
            }
        } catch (QueryException ex) {
			ex.printLog();
			throw new ServletException(ex);
		} catch (ReflectException ex) {
			ex.printLog();
			throw new ServletException(ex);
		} catch (PoolException ex) {
			ex.printLog();
			throw new ServletException(ex);
		} finally {
			DBManager.closeDBConnection(conn);
		}
		return hasPrivilege;
	}

	/**
	 * ���ܣ�������Ȩ��ʱ�Ĺ�����URL����
	 * @param resoureName String
	 * @param authenticateURL String
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException ServletException
	 */
	private void processNoPrivilege(String resoureName,String authenticateURL,HttpServletRequest req,HttpServletResponse res) throws ServletException {
		try {
			ServletContext context = config.getServletContext();
			String noPriviURL;
			noPriviURL = filterDTO.getNoPriviledgeURL();
			RequestDispatcher rd = context.getRequestDispatcher(noPriviURL);
			Message message = getMessage(MsgKeyConstant.NO_RES_PRIVI);
			if (StrUtil.isEmpty(resoureName)) {
				resoureName = authenticateURL;
			}
			message.addParameterValue(resoureName);
			req.setAttribute(MessageConstant.MESSAGE_DATA, message);
			rd.forward(req, res);
		} catch (IOException ex) {
			Logger.logError(ex);
			throw new ServletException(ex);
		}
	}

	/**
	 * ���ܣ��жϸ�URL���ض�����forward��
	 * @param reDirectURL String
	 * @return boolean
	 */
	private boolean isRedirectURL(String reDirectURL) {
		boolean retValue = true;
		if (reDirectURL.startsWith("/servlet") || reDirectURL.indexOf(".jsp") > -1) {
			retValue = false;
		}
		return retValue;
	}

	/**
	 * ���ܣ�����Filter������Ϣ��
	 */
	public void destroy() {
		this.config = null;
	}
}
