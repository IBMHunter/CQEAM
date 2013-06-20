package com.sino.rds.share.form;

import com.sino.base.util.StrUtil;
import com.sino.rds.appbase.form.RDSBaseFrm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Title: �����ѯ���� RDS_REPORT_PARAMETER</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class SearchParameterFrm extends RDSBaseFrm {
    private Map<String, String> parameters = null;
    private List<String> fieldNames = null;
    public SearchParameterFrm(){
        parameters = new HashMap<String, String>();
        fieldNames = new ArrayList<String>();
    }

    public void addParameter(String fieldName, String fieldValue){
        if(fieldNames.contains(fieldName)){
            return;
        }
        fieldNames.add(fieldName);
        parameters.put(fieldName, fieldValue);
    }

    public List<String> getFieldNames(){
        return fieldNames;
    }

    public String getParameter(String fieldName){
        return StrUtil.nullToString(parameters.get(fieldName));
    }

    public String getReportId(){
        return getParameter("reportId");
    }

    public String getReportCode(){
        return getParameter("reportCode");
    }

    public String getLookUpId(){
        return getParameter("lookUpId");
    }

    public String getLookUpCode(){
        return getParameter("lookUpCode");
    }

    public String getPreview(){
        return getParameter("preview");
    }

    public String getClientWidth(){
        return getParameter("clientWidth");
    }
}