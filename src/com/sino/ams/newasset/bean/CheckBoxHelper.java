package com.sino.ams.newasset.bean;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.newasset.dto.AmsAssetsChangedVDTO;
import com.sino.base.exception.ReflectException;
import com.sino.base.util.ReflectionUtil;

public class CheckBoxHelper {
	private static List fields = new ArrayList();
	private static List labels = new ArrayList();

	static{
		fields.add("tagNumberChanged");
		fields.add("itemNameChanged");
		fields.add("itemSpecChanged");
		fields.add("userChanged");
		fields.add("locationChanged");
		fields.add("costCenterChanged");

		labels.add("��ǩ���");
		labels.add("���Ʊ��");
		labels.add("�ͺű��");
		labels.add("�����˱��");
		labels.add("�ص���");
		labels.add("�ɱ����ı��");
	}


	public static String getCheckBox(AmsAssetsChangedVDTO dto){
		StringBuffer checkStr = new StringBuffer();
		try {
			String fieldName = "";
			Object fieldValue = "";
			String checked = "";
			for (int i = 0; i < fields.size(); i++) {
				fieldName = (String) fields.get(i);
				fieldValue = ReflectionUtil.getProperty(dto, fieldName);
				if (fieldValue == null) {
					fieldValue = "";
				}
				checked = fieldValue.equals("Y") ? " checked" : "";
				checkStr.append("<input type=\"checkbox\" name=\"");
				checkStr.append(fieldName);
				checkStr.append("\" id=\"");
				checkStr.append(fieldName);
				checkStr.append("\" value=\"Y\"");
				checkStr.append(checked);
				checkStr.append("><label for=\"");
				checkStr.append(fieldName);
				checkStr.append("\">");
				checkStr.append(labels.get(i));
				checkStr.append("</label>&nbsp;&nbsp;");
			}
//�������и�ѡ��
//			String ctlBox = dto.getCtlBox();
//			checked = ctlBox.equals("Y") ? " checked" : "";
//			checkStr.append("<input type=\"checkbox\" name=\"ctlBox\" id=\"ctlBox\"  value=\"Y\"");
//			checkStr.append(checked);
//			checkStr.append(" onClick=\"do_ControlSelect(this)\"><label for=\"ctlBox\">����</label>");
		} catch (ReflectException ex) {
			ex.printLog();
		}
		return checkStr.toString();
	}
}
