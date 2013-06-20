package com.sino.framework.security.bean;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sino.base.constant.web.WebConstant;
import com.sino.framework.security.dto.FilterConfigDTO;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class FormDataProducer {

    private boolean dataCreated = false;
    private HttpServletRequest req = null;
    private String reDirectURL = "";
	private FilterConfigDTO filterConfig = null;

    public FormDataProducer(HttpServletRequest req, String reDirectURL) {
        super();
        this.req = req;
        this.reDirectURL = reDirectURL;
		filterConfig = SessionUtil.getFilterConfigDTO(req);
    }

    /**
     * ���ܣ���¼�Ự����ʱԭ���ύ�ĸ������ݡ����ڼ����ύ��
     */
    public void produceData(){
        if(!dataCreated){
            produceFrmData();
//			Message message = MessageManager.getMessage(MsgKeyConstant.SESSION_TIME_OUT);
//            req.setAttribute(MessageConstant.MESSAGE_DATA, message);
            dataCreated = true;
        }
    }

    /**
     * ���ܣ����������ݡ�
     */
    private void produceFrmData(){
        StringBuffer reqData = new StringBuffer();
        Map nameMap = req.getParameterMap();
        Iterator fieldNames = nameMap.keySet().iterator();
        String fieldName = "";
        String[] fieldValues = null;
        while(fieldNames.hasNext()){
            fieldName = String.valueOf(fieldNames.next());
            if(isSkipField(fieldName)){
                continue;
            }
            fieldValues = req.getParameterValues(fieldName);
            for(int i = 0; i < fieldValues.length; i++){
                reqData.append("<input type=\"hidden\" name=\"");
                reqData.append(fieldName);
                reqData.append("\" value=\"");
                reqData.append(fieldValues[i]);
                reqData.append("\">\n");
            }
        }
        reqData.append("<input type=\"hidden\" name=\"");
        reqData.append(WebConstant.REDIRECT_URL);
        reqData.append("\" value=\"");
        reqData.append(reDirectURL);
        reqData.append("\">\n");
        req.setAttribute(WebConstant.FORM_DATA, reqData.toString());
    }

	/**
	 * ���ܣ�����ֶ��Ƿ���Ժ��Բ��ƣ����������ظ�����
	 * @param fieldName String
	 * @return boolean
	 */
	private boolean isSkipField(String fieldName){
        boolean isSkipField = false;
        if(fieldName.equals(filterConfig.getLoginName())){
            isSkipField = true;
        } else if(fieldName.equals(filterConfig.getLoginPwd())){
            isSkipField = true;
        } else if(fieldName.equals(WebConstant.REDIRECT_URL)){
            isSkipField = true;
        }
        return isSkipField;
    }
}
