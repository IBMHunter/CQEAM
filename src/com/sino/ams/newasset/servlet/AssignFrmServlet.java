package com.sino.ams.newasset.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AssignFrmServlet extends BaseServlet {

    /**
     * ���е�Servlet������ʵ�ֵķ�����
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
        SfUserDTO user = (SfUserDTO) getUserAccount(req);
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        boolean hasError = false;
        try {
//			if (user.getEmployeeNumber().equals("")) {
//				message = getMessage(AssetsMessageKeys.HAS_NO_MIS_NUMBER);
//				message.setIsError(true);
//				message.setNeedClose(true);
//				forwardURL = MessageConstant.MSG_PRC_SERVLET;
//				hasError = true;
//			} else {
            forwardURL = AssetsURLList.ASSIGN_USER_SERVLET;
            req.setAttribute(AssetsWebAttributes.ASSETS_DYNAMIC_URL, forwardURL);
            req.setAttribute(AssetsWebAttributes.ASS_PROP,
                             AssetsWebAttributes.ASSIGN_MAINTAIN_USER);
//			}
        } finally {
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            if (hasError) {
                forwarder.forwardView(forwardURL);
            } else {
                forwarder.forwardView(AssetsURLList.ASSETS_ASSIGN_FRM);
            }
        }
//		if(user.isComAssetsManager()){//��˾�ʲ�����Ա���ɷ��䲿��
//			forwardURL = AssetsURLList.ASSIGN_DEPT_SERVLET;
//		} else if(user.isDptAssetsManager()){//�����ʲ�����Ա���ɷ���������
//			forwardURL = AssetsURLList.ASSIGN_LEFT_SERVLET;
//		} else {//��ͨ�û����ɷ����ʲ�ʹ����
//			forwardURL = AssetsURLList.ASSIGN_USER_SERVLET;
//		}
    }
}
