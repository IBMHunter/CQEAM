package com.sino.rds.foundation.web.component;

import com.sino.base.SinoBaseObject;
import com.sino.base.constant.WorldConstant;
import com.sino.base.util.StrUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>@todo���ڴ˼��뱾������������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 0.1
 */
public class WebOption extends SinoBaseObject {
    private String value = "";
    private String label = "";
    private boolean selected = false;
    private Map<String, String> attributes = null;

    public WebOption() {
        super();
        attributes = new LinkedHashMap<String, String>();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public boolean isSelected() {
        return selected;
    }

    public void addAttribute(String attributeName, String attributeValue) {
        if (StrUtil.isEmpty(attributeName)) {
            return;
        }
        attributes.put(attributeName, StrUtil.nullToString(attributeValue));
    }

    public String toString() {
        StringBuilder optionHTML = new StringBuilder();
        optionHTML.append("<option value=\"");
        optionHTML.append(value);
        optionHTML.append("\"");
        if (selected) {
            optionHTML.append(" selected");
        }
        if (attributes != null) {
            Iterator<String> attributeNames = attributes.keySet().iterator();
            while(attributeNames.hasNext()){
                String attributeName = attributeNames.next();
                String attributeValue = attributes.get(attributeName);
                optionHTML.append(WorldConstant.EMPTY_SPACE);
                optionHTML.append(attributeName);
                optionHTML.append("=\"");
                optionHTML.append(attributeValue);
                optionHTML.append("\"");
            }
        }
        optionHTML.append(">");
        optionHTML.append(label);
        optionHTML.append("</option>");
        return optionHTML.toString();
    }

    public String toXMLContent(){
        StringBuilder optionXML = new StringBuilder();
        optionXML.append("<option value=\"");
        optionXML.append(getValue());
        optionXML.append("\" text=\"");
        optionXML.append(getLabel());
        optionXML.append("\" selected=\"");
        optionXML.append(isSelected());
        optionXML.append("\"");
        if (attributes != null) {
            Iterator<String> attributeNames = attributes.keySet().iterator();
            while(attributeNames.hasNext()){
                String attributeName = attributeNames.next();
                String attributeValue = attributes.get(attributeName);
                optionXML.append(attributeName);
                optionXML.append("=\"");
                optionXML.append(attributeValue);
                optionXML.append("\"");
            }
        }
        optionXML.append("/>");
        return optionXML.toString();
    }    
}
