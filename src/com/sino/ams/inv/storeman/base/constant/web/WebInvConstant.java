package com.sino.ams.inv.storeman.base.constant.web;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ��ʿ��
 * @version 0.1
 */
public interface WebInvConstant {

	String SYSTEM_NAME = "SYSTEM_NAME";//WebӦ��ϵͳ����
	String USER_INFO = "USER_INFO";//session�����д洢�û�����ʹ�õ�������
	String FORM_DATA = "FORM_DATA";//�Ự����ʱ����ĻỰ��������
	String REDIRECT_URL = "REDIRECT_URL";//�ض����URL
	String FILTER_DTO = "FILTER_DTO";//������DTO��
	String SERVLET_DTO = "SERVLET_DTO";//��ʼ��Servlet����DTO��
	String LOOP_UP_PROP = "LOOP_UP_PROP";//LookUpModel���Զ���

	String CHAR_ENCODING = "CHAR_ENCODING";//��������
	String LOOK_MODEL_CLASS = "LOOK_MODEL_CLASS";//LookUpModel����ļ̳���
	String LOOK_UP_DTO = "LOOK_UP_DTO";//LookUpModel�����DTO��ѯ��������ļ̳���
//	String LOOK_UP_NAME = "LOOK_UP_NAME";//��������
    String CHOOSE_DICT="[��]";//ҳ��ѡ��

    String LOOK_UP_PAGE = "/lookUp.jsp";
    String LOOK_UP_INV_PAGE = "/inv/storeman/lookUpStoremanDetail.jsp";
	String LOOK_UP_SERVLET = "/servlet/com.sino.base.lookup.LoopUpServlet";

	String LOGIN_SERVLET = "/servlet/com.sino.framework.security.servlet.UserLoginServlet"; //��¼��֤Servlet
	String WAIT_TIP_MSG = "<div id=\"$$$waitTipMsg$$$\" style=\"position:absolute; bottom:45%; left:5; z-index:10; visibility:hidden\">\n"
						  + "\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
						  + "\t\t<tr>\n"
						  + "\t\t\t<td width=\"30%\"></td>\n"
						  + "\t\t\t<td bgcolor=\"#ff9900\">\n"
						  + "\t\t\t\t<table width=\"100%\" height=\"60\" border=\"0\" cellspacing=\"2\" cellpadding=\"0\">\n"
						  + "\t\t\t\t\t<tr>\n"
						  + "\t\t\t\t\t\t<td bgcolor=\"#eeeeee\" align=\"center\">�����������ݣ����Ժ�......<img\n" +
            "                                src=\"/images/wait.gif\" alt=\"\"></td>\n"
						  + "\t\t\t\t\t</tr>\n"
						  + "\t\t\t\t</table>\n"
						  + "\t\t\t</td>\n"
						  + "\t\t\t<td width=\"30%\"></td>\n"
						  + "\t\t</tr>\n"
						  + "\t</table>\n"
						  + "</div>";//��ѯʱʹ�õ���ʾ��Ϣ
}
