package com.sino.rds.share.action;

import com.sino.base.dto.DTO;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.action.RDSBaseAction;
import com.sino.rds.appbase.service.RDSBaseService;
import com.sino.rds.share.constant.RDSDictionaryList;
import com.sino.rds.share.form.AjaxParameterFrm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.*;


public class AjaxProcessAction extends RDSBaseAction {

    /**
     * ����Action����ʵ�ֵķ���
     *
     * @param mapping ActionMapping
     * @param form    ActionForm
     * @param req     HttpServletRequest
     * @param res     HttpServletResponse
     * @return ActionForward
     * @throws javax.servlet.ServletException
     */
    protected ActionForward performTask(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws ServletException {
        AjaxParameterFrm frm = (AjaxParameterFrm) form;
        BaseUserDTO userAccount = getUserAccount(req);
        Connection conn = null;
        try {
            conn = getDBConnection();
            String className = frm.getServiceClass();
            DTO dto = getDTOFromRequest(req, frm, mapping);
            Object[] parameters = {userAccount, dto, conn};
            RDSBaseService service = (RDSBaseService) ReflectionUtil.getInstance(className, parameters);
            Object[] args = new Object[0];
            Object returnValue = ReflectionUtil.invokeMethod(service, frm.getMethodName(), args);
            processAjaxResponse(returnValue, frm.getResType(), res);
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            closeDBConnection(conn);
        }
        return null;
    }

    /**
     * ���ܣ�����DTO����
     *
     * @param req     request�������
     * @param frm     Ajax������
     * @param mapping struts mappingӳ�����
     * @return DTO����
     * @throws DTOException
     */
    private DTO getDTOFromRequest(HttpServletRequest req, AjaxParameterFrm frm, ActionMapping mapping) throws DTOException {
        DTO dto = null;
        try {
            ModuleConfig moduleConfig = mapping.getModuleConfig();
            FormBeanConfig frmConfig = moduleConfig.findFormBeanConfig(frm.getFrmName());
            if(frmConfig != null){
                String frmType = frmConfig.getType();
                Request2DTO frmProducer = new Request2DTO();
                frmProducer.setDTOClassName(frmType);
                dto = frmProducer.getDTO(req);
                parseDataLines(req, dto, frm);
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DTOException(ex.getMessage());
        }
        return dto;
    }

    private String getListFieldType(DTO dto, String fieldName) {
        String typeStr = "";
        if (dto != null) {
            Class cls = dto.getClass();
            Field field = ReflectionUtil.getField(cls, fieldName);
            Type type = field.getGenericType();
            typeStr = type.toString();
            int startIndex = typeStr.indexOf("<");
            int endIndex = typeStr.indexOf(">");
            if (startIndex > 0 && endIndex > startIndex) {
                typeStr = typeStr.substring(startIndex + 1, endIndex);
            } else {
                typeStr = "";
            }
        }
        return typeStr;
    }


    /**
     * ���ܣ�����Ajax����
     *
     * @param obj     Http��Ӧ����
     * @param resType AJAX����
     * @param res     Http��Ӧ����
     * @throws java.io.IOException ��ӦAjax����ʱ�׳����쳣
     */
    private void processAjaxResponse(Object obj, String resType, HttpServletResponse res) throws IOException {
        if (obj != null) {
            if (resType.equals(RDSDictionaryList.AJAX_RESPONSE_XML)) {
                res.setContentType("text/xml;charset=GBK");
            } else {
                res.setContentType("text/plain;charset=GBK");
            }
            PrintWriter out = res.getWriter();
            if (!StrUtil.isEmpty(out)) {
                out.print(obj);
                System.out.println(obj);
            }
            out.close();
        }
    }

    /**
     * ���ܣ�ҳ���������DTO�������ӱ����ݵĹ��졣
     * @param req ServletRequest ҳ���������
     * @param dto DTO ���ݴ������
     * @param frm AjaxParameterFrm Ajaxͳһ����Form����
     * @throws DTOException
     */
    private void parseDataLines(ServletRequest req, DTO dto, AjaxParameterFrm frm) throws DTOException {
        try {
            String listFieldName = frm.getListFieldName();
            if (!StrUtil.isEmpty(listFieldName)) {
                Map keyValues = req.getParameterMap();
                Iterator iterator = keyValues.keySet().iterator();
                String listTypeStr = getListFieldType(dto, listFieldName);
                Map<String,DTO> dataMap = new HashMap<String,DTO>();
                while (iterator.hasNext()) {
                    String fieldName = String.valueOf(iterator.next());
                    String fieldValue = req.getParameter(fieldName);
                    int startIndex = fieldName.indexOf(listFieldName + "[");
                    int dotIndex = fieldName.indexOf(".") + 1;
                    int endIndex = fieldName.indexOf("]");
                    if(startIndex == 0 && endIndex > 2 && dotIndex > 4){
                        String instanceKey = fieldName.substring(0, dotIndex);
                        DTO dataLine = dataMap.get(instanceKey);
                        if(dataLine == null){
                            dataLine = (DTO)(Class.forName(listTypeStr).newInstance());
                        }
                        fieldName = fieldName.substring(dotIndex);
                        ReflectionUtil.setProperty(dataLine, fieldName, fieldValue);
                        dataMap.put(instanceKey, dataLine);
                    }
                }
                List<DTO> dataList = new ArrayList<DTO>();
                Collection<DTO> dataCollection = dataMap.values();
                Iterator<DTO> dataIterator = dataCollection.iterator();
                while(dataIterator.hasNext()){
                    dataList.add(dataIterator.next());
                }
                ReflectionUtil.setProperty(dto, listFieldName, dataList);
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DTOException(ex.getMessage());
        }
    }
}
