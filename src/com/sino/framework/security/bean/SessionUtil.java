package com.sino.framework.security.bean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sino.ams.constant.WebAttrConstant;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.web.WebConstant;
import com.sino.base.db.query.GridPageSession;
import com.sino.base.lookup.LookUpProp;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.framework.security.dto.ServletConfigDTO;
/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class SessionUtil {
    private SessionUtil() {
        super();
    }

    /**
     * ���ܣ���ȡ�û�������Ϣ��
     * @param req HttpServletRequest
     * @return BaseUserDTO
     */
    public static BaseUserDTO getUserAccount(HttpServletRequest req){
		BaseUserDTO userAccount = null;
		HttpSession session = req.getSession();
		userAccount = (BaseUserDTO) session.getAttribute(WebConstant.USER_INFO);
		return userAccount;
	}

	/**
	 * ���ܣ������û���Ϣ���Ự����
	 * @param req HttpServletRequest
	 * @param userAccount BaseUserDTO
	 */
	public static void saveUserSession(HttpServletRequest req, BaseUserDTO userAccount){
		HttpSession session = req.getSession();
		session.setAttribute(WebConstant.USER_INFO, userAccount);
	}

	/**
	 * ���ܣ�ʹ�Ự����ʧЧ
	 * @param req HttpServletRequest
	 */
	public static void invalidateSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
	}

	/**
	 * ���ܣ�����filterDTO��ȫ������
	 * @param serContext ServletContext
	 * @param filterDTO FilterConfigDTO
	 */
	public static void saveFilterConfig(ServletContext serContext, FilterConfigDTO filterDTO) {
		serContext.setAttribute(WebConstant.FILTER_DTO, filterDTO);
	}

	/**
	 * ���ܣ���ȡFilter������ϢDTO����
	 * @param req HttpServletRequest
	 * @return FilterConfigDTO
	 */
	public static FilterConfigDTO getFilterConfigDTO(HttpServletRequest req){
		HttpSession session = req.getSession();
		return getFilterConfigDTO(session);
	}


	/**
	 * ���ܣ���ȡFilter������ϢDTO����
	 * @param session HttpSession
	 * @return FilterConfigDTO
	 */
	public static FilterConfigDTO getFilterConfigDTO(HttpSession session){
		ServletContext context = session.getServletContext();
		return getFilterConfigDTO(context);
	}


	/**
	 * ���ܣ���ȡFilter������ϢDTO����
	 * @param context ServletContext
	 * @return FilterConfigDTO
	 */
	public static FilterConfigDTO getFilterConfigDTO(ServletContext context){
		FilterConfigDTO filterDTO = (FilterConfigDTO) context.getAttribute(WebConstant.FILTER_DTO);
		return filterDTO;
	}
	/**
	 * ���ܣ������ʼ��Servlet���õ�ȫ������
	 * @param serContext ServletContext
	 * @param servletDTO ServletConfigDTO
	 */
	public static void saveServletConfig(ServletContext serContext, ServletConfigDTO servletDTO) {
		serContext.setAttribute(WebConstant.SERVLET_DTO, servletDTO);
	}

	/**
	 * ���ܣ���ȡ��ʼ��Servlet������ϢDTO����
	 * @param req HttpServletRequest
	 * @return ServletConfigDTO
	 */
	public static ServletConfigDTO getServletConfigDTO(HttpServletRequest req){
		HttpSession session = req.getSession();
		return getServletConfigDTO(session);
	}

	/**
	 * ���ܣ���ȡ��ʼ��Servlet������ϢDTO����
	 * @param session HttpSession
	 * @return ServletConfigDTO
	 */
	public static ServletConfigDTO getServletConfigDTO(HttpSession session){
		ServletContext serContext = session.getServletContext();
		return getServletConfigDTO(serContext);
	}


	/**
	 * ���ܣ���ȡ��ʼ��Servlet������ϢDTO����
	 * @param context ServletContext
	 * @return ServletConfigDTO
	 */
	public static ServletConfigDTO getServletConfigDTO(ServletContext context){
		ServletConfigDTO servletDTO = (ServletConfigDTO) context.getAttribute(WebConstant.SERVLET_DTO);
		return servletDTO;
	}

	/**
	 * ���ܣ�����LookUpProp����Session
	 * @param req HttpServletRequest
	 * @param lookProp LookUpProp
	 */
	public static void saveLoopUpProp(HttpServletRequest req, LookUpProp lookProp){
		HttpSession session = req.getSession();
		session.setAttribute(WebConstant.LOOP_UP_PROP, lookProp);
	}

	/**
	 * ���ܣ������ַ�����
	 * @param serContext ServletContext
	 * @param encoding String
	 */
	public static void saveEncoding(ServletContext serContext, String encoding){
		serContext.setAttribute(WebConstant.CHAR_ENCODING, encoding);
	}

	/**
	 * ���ܣ������ַ�����
	 * @param serContext ServletContext
	 * @return String
	 */
	public static String getEncoding(ServletContext serContext) {
		return (String)serContext.getAttribute(WebConstant.CHAR_ENCODING);
	}

	/**
	 * ���ܣ���ȡ�û�������Ϣ��
	 * @param req HttpServletRequest
	 * @return LookUpProp
	 */
	public static LookUpProp getLookUpProp(HttpServletRequest req){
		LookUpProp lookProp = null;
		HttpSession session = req.getSession();
		lookProp = (LookUpProp)session.getAttribute(WebConstant.LOOP_UP_PROP);
		return lookProp;
	}

	/**
	 * ���ܣ���ȡrequest���������õ���ʾ��Ϣ
	 * @param req HttpServletRequest
	 * @return Message
	 */
	public static Message getMessage(HttpServletRequest req){
		Message message = (Message) req.getAttribute(MessageConstant.MESSAGE_DATA);
		return message;
	}

	/**
	 * ���ܣ�����GridPageSession����
	 * @param pageSession GridPageSession
	 * @param req HttpServletRequest
	 */
	public static void saveGridPageSession(GridPageSession pageSession, HttpServletRequest req){
		HttpSession session = req.getSession();
		session.setAttribute(QueryConstant.GRID_PAGE_SESSION, pageSession);
	}

	/**
	 * ���ܣ���ȡ����ͳ�Ƶ�GridPageSession����
	 * @param req HttpServletRequest
	 * @return GridPageSession
	 */
	public static GridPageSession getGridPageSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		GridPageSession pageSession = null;
		pageSession = (GridPageSession) session.getAttribute(QueryConstant.GRID_PAGE_SESSION);
		return pageSession;
	}


	/**
	 * ���ܣ���ȡ����ͳ�Ƶ�GridPageSession����
	 * @param session HttpSession
	 * @return GridPageSession
	 */
	public static GridPageSession getGridPageSession(HttpSession session) {
		GridPageSession pageSession = null;
		pageSession = (GridPageSession) session.getAttribute(QueryConstant.GRID_PAGE_SESSION);
		return pageSession;
	}

        /**
     * ���ܣ�����ҳ�����
     * @param req
     * @param pageTitle
     */
    public static void setPageTitle(HttpServletRequest req, String pageTitle) {
        HttpSession session = req.getSession();
        session.setAttribute(WebAttrConstant.PAGE_TITLE, pageTitle);
    }

    /**
     * ���ܣ���ȡҳ�����
     * @param req
     */
    public static String getPageTile(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return StrUtil.nullToString(session.getAttribute(WebAttrConstant.PAGE_TITLE));
    }
}
