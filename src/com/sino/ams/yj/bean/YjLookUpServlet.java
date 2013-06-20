package com.sino.ams.yj.bean;

import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.yj.constant.YjLookUpConstant;
import com.sino.ams.yj.resource.dto.AmsYjCommunicateResourceDTO;
import com.sino.ams.yj.dto.AmsYjItemDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.web.WebConstant;
import com.sino.base.log.Logger;
import com.sino.base.lookup.LookUpProp;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ���ʲ�����ģ���LookUpServlet</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class YjLookUpServlet extends BaseServlet {
    /**
     * ���е�Servlet������ʵ�ֵķ�����
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws
            ServletException, IOException {
        String forwardURL = "";
        String lookUpName = req.getParameter("lookUpName");
        lookUpName = StrUtil.nullToString(lookUpName);
        Message message = SessionUtil.getMessage(req);
        try {
            if (lookUpName.equals("")) {
                message = getMessage(AssetsMessageKeys.INVALID_REQ);
                message.setIsError(true);
                message.setNeedClose(true);
                forwardURL = MessageConstant.MSG_PRC_SERVLET;
            } else {
                LookUpProp lookProp = new LookUpProp(lookUpName);
                String[] dispNames = null;
                String[] retFields = null;
                String[] dispLabels = null;
                String[] viewPercent = null;
                String[] qryNames = null;
                String[] qryLabels = null;
                String[] primaryKeys = null;
                if (lookUpName.equals(YjLookUpConstant.LOOK_UP_RESOURCE)) { //ѡ��ս����Դ
                    dispNames = new String[]{"RESOURCE_ID", "EQUIPMENT_NAME", "MODEL", "DEPT_NAME", "LOCATION"};
                    dispLabels = new String[]{"���", "��Դ����", "���", "��λ", "�ص�"};
                    viewPercent = new String[]{"8%", "22%", "22%", "20%", "22%"};
                    retFields = new String[]{"RESOURCE_ID", "EQUIPMENT_NAME"};
                    qryNames = new String[]{"DEPT_NAME","EQUIPMENT_NAME", "MODEL"};
                    qryLabels = new String[]{"��λ","��Դ����", "���"};
                    primaryKeys = new String[]{"RESOURCE_ID"};

                    lookProp.setTotalWidth(700);
                    lookProp.setMultipleChose(false);
                    lookProp.setDtoClass(AmsYjCommunicateResourceDTO.class);
                }else if (lookUpName.equals(YjLookUpConstant.LOOK_UP_EQUIPMENT)) { //ѡ��װ������
                    dispNames = new String[]{"ITEM_CODE","ITEM_NAME"};
                    dispLabels = new String[]{"����", "����"};
                    viewPercent = new String[]{"15%", "85%"};
                    retFields = new String[]{"ITEM_NAME"};
                    qryNames = new String[]{"ITEM_NAME"};
                    qryLabels = new String[]{"����"};
                    primaryKeys = new String[]{"ITEM_CODE"};

                    lookProp.setTotalWidth(700);
                    lookProp.setMultipleChose(false);
                    lookProp.setDtoClass(AmsYjItemDTO.class);
                }
                lookProp.setCalPattern(LINE_PATTERN);
                lookProp.setDisFieldNames(dispNames);
                lookProp.setDisFieldLabels(dispLabels);
                lookProp.setRetFields(retFields);
                lookProp.setViewPercent(viewPercent);
                lookProp.setQryFieldNames(qryNames);
                lookProp.setQryFieldLabels(qryLabels);
                lookProp.setPrimaryKeys(primaryKeys);
                lookProp.setModelClass(YjLookUpModel.class);   //����model
                SessionUtil.saveLoopUpProp(req, lookProp);

                forwardURL = WebConstant.LOOK_UP_SERVLET;
            }
        } catch (Exception ex) {
            Logger.logError(ex);
            throw new ServletException(ex);
        } finally {
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
		}
	}
}