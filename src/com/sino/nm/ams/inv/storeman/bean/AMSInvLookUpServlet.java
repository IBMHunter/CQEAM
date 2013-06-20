package com.sino.nm.ams.inv.storeman.bean;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sino.ams.bean.AMSLookUpServlet;
import com.sino.ams.constant.LookUpConstant;
import com.sino.ams.ct.bean.LookUpCtConstant;
import com.sino.ams.ct.dto.EtsItemInfoDTO;
import com.sino.ams.inv.dzyh.dto.EamDhBillLDTO;
import com.sino.nm.ams.inv.storeman.dto.EamInvStoremanDTO;
import com.sino.nm.ams.inv.storeman.base.constant.web.WebInvConstant;
import com.sino.ams.system.cost.dto.CostCenterDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebConstant;
import com.sino.base.log.Logger;
import com.sino.base.lookup.LookUpProp;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.ams.system.item.dto.EtsSystemItemDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

public class AMSInvLookUpServlet extends AMSLookUpServlet {

	public void performTask(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		String forwardURL = "";
		String lookUpName = req.getParameter("lookUpName");
		lookUpName = StrUtil.nullToString(lookUpName);
		Message message = SessionUtil.getMessage(req);
		try {
			if (lookUpName.equals("")) {
				message = getMessage(MsgKeyConstant.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			} else {
				LookUpProp lookProp = new LookUpProp(lookUpName);

				String[] dispFieldNames = null;
				String[] retFields = null;
				String[] dispFieldLabels = null;
				String[] viewPercent = null;
				String[] qryFieldNames = null;
				String[] qryFieldLabels = null;
				String[] primaryKeys = null;
				if (lookUpName.equals(LookUpConstant.LOOK_UP_SYS_ITEM)) {

					dispFieldNames = new String[]{"ITEM_NAME", "ITEM_SPEC"};
					dispFieldLabels = new String[]{"�����Ǳ�����", "����ͺ�"};
					retFields = new String[]{"ITEM_NAME", "ITEM_SPEC", "ITEM_CODE"};
					viewPercent = new String[]{"40%", "50%"};
					qryFieldNames = new String[]{"ITEM_NAME", "ITEM_SPEC"};
					qryFieldLabels = new String[]{"�����Ǳ�����", "����ͺ�"};
					primaryKeys = new String[]{"ITEM_CODE"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpConstant.LOOK_UP_SYS_ITEM_DZYH)) {

					dispFieldNames = new String[]{"ITEM_NAME", "ITEM_SPEC"};
					dispFieldLabels = new String[]{"�豸����", "����ͺ�"};
					retFields = new String[]{"ITEM_NAME", "ITEM_SPEC", "ITEM_CODE"};
					viewPercent = new String[]{"40%", "50%"};
					qryFieldNames = new String[]{"ITEM_NAME", "ITEM_SPEC"};
					qryFieldLabels = new String[]{"�豸����", "����ͺ�"};
					primaryKeys = new String[]{"ITEM_CODE"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpCtConstant.LOOK_UP_SYS_ITEM_NAME)) {

					dispFieldNames = new String[]{"ITEM_NAME"};
					dispFieldLabels = new String[]{"�豸����"};
					//retFields = new String[]{"ITEM_NAME", "ITEM_SPEC", "ITEM_CODE", "VENDOR_NAME", "VENDOR_ID"};
					retFields = new String[]{"ITEM_CODE", "ITEM_NAME", "ITEM_SPEC"};
					viewPercent = new String[]{"50%"};
					qryFieldNames = new String[]{"ITEM_NAME"};
					qryFieldLabels = new String[]{"�豸����"};
					primaryKeys = new String[]{"ITEM_CODE"};


					lookProp.setMultipleChose(false);  //���óɵ�ѡ��ť�ɣ�true�ǲ��Ǿ��Ƕ�ѡ��ť����

					//lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EtsItemInfoDTO.class);

				} else if (lookUpName.equals(LookUpCtConstant.LOOK_UP_SYS_ITEM_SPEC)) {

					dispFieldNames = new String[]{"ITEM_SPEC"};
					dispFieldLabels = new String[]{"����ͺ�"};
					retFields = new String[]{"ITEM_CODE", "ITEM_NAME", "ITEM_SPEC"};
					viewPercent = new String[]{"50%"};
					qryFieldNames = new String[]{"ITEM_SPEC"};
					qryFieldLabels = new String[]{"����ͺ�"};
					primaryKeys = new String[]{"ITEM_CODE"};

					lookProp.setMultipleChose(false);
					//lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EtsItemInfoDTO.class);
				} else if(lookUpName.equals(LookUpInvConstant.LOOK_UP_WORKORDER_OBJECT_NO)) { //ѡ��ֿ�

					dispFieldNames = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "INV_CATEGORY_NAME", "WORKORDER_OBJECT_LOCATION", "BIZ_CATEGORY_NAME"};
					dispFieldLabels = new String[]{"�ֿ����", "�ֿ�����", "�ֿ�����", "�ֿ�ص�", "ҵ������"};
					retFields = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "INV_CATEGORY_NAME", "WORKORDER_OBJECT_LOCATION", "BIZ_CATEGORY_NAME"};
					viewPercent = new String[]{"10%", "30%", "20%", "30%", "10%"};
					qryFieldNames = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "WORKORDER_OBJECT_LOCATION"};
					qryFieldLabels = new String[]{"�ֿ����", "�ֿ�����", "�ֿ�ص�"};
					primaryKeys = new String[]{"WORKORDER_OBJECT_NO"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamInvStoremanDTO.class);
				} else if(lookUpName.equals(LookUpInvConstant.LOOK_UP_USER_ID)) { //ѡ��ֹ�Ա
					dispFieldNames = new String[]{"LOGIN_NAME", "USER_NAME"};
					dispFieldLabels = new String[]{"��¼��", "�ֹ�Ա����"};
					retFields = new String[]{"USER_ID", "USER_NAME", "LOGIN_NAME"};
					viewPercent = new String[]{"40%", "50%"};
					qryFieldNames = new String[]{"LOGIN_NAME", "USERNAME"};
					qryFieldLabels = new String[]{"��¼��", "�ֹ�Ա����"};
					primaryKeys = new String[]{"USER_ID"};
					
					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(SfUserDTO.class);
				} else if(lookUpName.equals(LookUpInvConstant.LOOK_UP_UPDATED_USER)) { //ѡ���޸���
					dispFieldNames = new String[]{"LAST_UPDATE_BY", "UPDATED_USER"};
					dispFieldLabels = new String[]{"�޸���ID", "�޸���"};
					retFields = new String[]{"LAST_UPDATE_BY", "UPDATED_USER"};
					viewPercent = new String[]{"40%", "50%"};
					qryFieldNames = new String[]{"LAST_UPDATE_BY", "UPDATED_USER"};
					qryFieldLabels = new String[]{"�޸���ID", "�޸���"};
					primaryKeys = new String[]{"LAST_UPDATE_BY"};
					
					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(SfUserDTO.class);
				} else if(lookUpName.equals(LookUpInvConstant.LOOK_UP_CREATED_USER)) { //ѡ�񴴽���
					dispFieldNames = new String[]{"CREATED_BY", "CREATED_USER"};
					dispFieldLabels = new String[]{"������ID", "������"};
					retFields = new String[]{"CREATED_BY", "CREATED_USER"};
					viewPercent = new String[]{"40%", "50%"};
					qryFieldNames = new String[]{"CREATED_BY", "CREATED_USER"};
					qryFieldLabels = new String[]{"������ID", "������"};
					primaryKeys = new String[]{"CREATED_BY"};
					
					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(SfUserDTO.class);
				} else if (lookUpName.equals(LookUpConstant.COST_CENTER)) {//���ҳɱ�����
					lookProp.setMultipleChose(false);
					dispFieldNames = new String[]{"COST_CENTER_CODE", "COST_CENTER_NAME"};
					dispFieldLabels = new String[]{"�ɱ����Ĵ���", "�ɱ���������"};
					retFields = new String[]{"COST_CENTER_CODE", "COST_CENTER_NAME"};
					viewPercent = new String[]{"30%", "60%"};
					qryFieldNames = new String[]{"COST_CENTER_CODE", "COST_CENTER_NAME"};
					qryFieldLabels = new String[]{"�ɱ����Ĵ���", "�ɱ���������"};
					primaryKeys = new String[]{"COST_CENTER_CODE"};

					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(CostCenterDTO.class);
				} else if (lookUpName.equals(LookUpConstant.LOOK_UP_CATALOG_VALUE_ID)) { //����Ŀ¼���
					dispFieldNames = new String[]{"CATALOG_CODE", "CATALOG_NAME"};
					dispFieldLabels = new String[]{"Ŀ¼���", "Ʒ��"};
					retFields = new String[]{"CATALOG_VALUE_ID", "CATALOG_CODE", "CATALOG_NAME"};
					viewPercent = new String[]{"30%", "60%"};
					qryFieldNames = new String[]{"CATALOG_CODE", "CATALOG_NAME"};
					qryFieldLabels = new String[]{"Ŀ¼���", "Ʒ��"};
					primaryKeys = new String[]{"CATALOG_VALUE_ID"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpConstant.LOOK_UP_RESPONSIBILITY_DEPT)) { //����ʹ�ò���
					dispFieldNames = new String[]{"DEPT_CODE", "DEPT_NAME"};
					dispFieldLabels = new String[]{"���Ŵ���", "��������"};
					retFields = new String[]{"DEPT_CODE", "DEPT_NAME"};
					viewPercent = new String[]{"40%", "50%"};
					qryFieldNames = new String[]{"DEPT_CODE", "DEPT_NAME"};
					qryFieldLabels = new String[]{"���Ŵ���", "��������"};
					primaryKeys = new String[]{"DEPT_CODE"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpConstant.LOOK_UP_RESPONSIBILITY_USER)) { //����������
					dispFieldNames = new String[]{"DEPT_NAME", "USER_NAME"};
					dispFieldLabels = new String[]{"��������������", "����������"};
					retFields = new String[]{"EMPLOYEE_ID", "DEPT_CODE", "USER_NAME", "DEPT_NAME"};
					viewPercent = new String[]{"40%", "40%"};
					qryFieldNames = new String[]{"DEPT_NAME", "USER_NAME"};
					qryFieldLabels = new String[]{"��������������", "����������"};
					primaryKeys = new String[]{"EMPLOYEE_ID"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpConstant.BJSL_ITEM_INFO)) { //����������������
					dispFieldNames = new String[]{"BARCODE", "ITEM_NAME"};
					dispFieldLabels = new String[]{"�豸����", "�豸����"};
					retFields = new String[]{"BARCODE", "ITEM_NAME"};
					viewPercent = new String[]{"40%", "50%"};
					qryFieldNames = new String[]{"BARCODE", "ITEM_NAME"};
					qryFieldLabels = new String[]{"�豸����", "�豸����"};
					primaryKeys = new String[]{"BARCODE"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpInvConstant.LOOK_UP_BARCODE)) { //ѡ���ǩ���
					dispFieldNames = new String[]{"BARCODE_FLAG", "CATALOG_NAME", "VALUE"};
					dispFieldLabels = new String[]{"��ǩ���", "Ʒ��", "������λ"};
					retFields = new String[]{"BARCODE_FLAG", "CATALOG_NAME", "UNIT" , "VALUE"};
					viewPercent = new String[]{"30%", "40%", "20%"};
					qryFieldNames = new String[]{"BARCODE_FLAG", "CATALOG_NAME"};
					qryFieldLabels = new String[]{"��ǩ���", "Ʒ��"};
					primaryKeys = new String[]{"BARCODE_FLAG"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpInvConstant.LOOK_UP_RESPONSIBILITY_EMPLOYEE)) { //ѡ��������
					dispFieldNames = new String[]{"EMPLOYEE_NUMBER", "USER_NAME"};
					dispFieldLabels = new String[]{"Ա�����", "Ա������"};
					retFields = new String[]{"EMPLOYEE_ID", "USER_NAME", "EMPLOYEE_NUMBER"};
					viewPercent = new String[]{"40%", "50%"};
					qryFieldNames = new String[]{"EMPLOYEE_NUMBER", "USER_NAME"};
					qryFieldLabels = new String[]{"Ա�����", "Ա������"};
					primaryKeys = new String[]{"EMPLOYEE_ID"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpInvConstant.LOOK_UP_WORKORDER_OBJECT_NO_DZYH_WARE)) { //ѡ���ֵ�׺�Ʒ�ֿ�
					dispFieldNames = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "WORKORDER_OBJECT_CODE"};
					dispFieldLabels = new String[]{"�ֿ���", "�ֿ�����", "�ֿ����"};
					retFields = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "WORKORDER_OBJECT_CODE"};
					viewPercent = new String[]{"20%", "40%", "30%"};
					qryFieldNames = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "WORKORDER_OBJECT_CODE"};
					qryFieldLabels = new String[]{"�ֿ���", "�ֿ�����", "�ֿ����"};
					primaryKeys = new String[]{"WORKORDER_OBJECT_NO"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpInvConstant.LOOK_UP_WORKORDER_OBJECT_NO_DZYH)) { //ѡ���ֵ�׺�Ʒ�ص�
					dispFieldNames = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "WORKORDER_OBJECT_CODE"};
					dispFieldLabels = new String[]{"�ص���", "�ص�����", "�ص����"};
					retFields = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "WORKORDER_OBJECT_CODE"};
					viewPercent = new String[]{"20%", "40%", "30%"};
					qryFieldNames = new String[]{"WORKORDER_OBJECT_NO", "WORKORDER_OBJECT_NAME", "WORKORDER_OBJECT_CODE"};
					qryFieldLabels = new String[]{"�ص���", "�ص�����", "�ص����"};
					primaryKeys = new String[]{"WORKORDER_OBJECT_NO"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				} else if (lookUpName.equals(LookUpInvConstant.LOOK_UP_ITEM_CATEGORY2)) { //ѡ��ETS_SYSTEM_ITEM���е�Ŀ¼���
					dispFieldNames = new String[]{"ITEM_CATEGORY2", "ITEM_NAME", "ITEM_SPEC"};
					dispFieldLabels = new String[]{"Ŀ¼���", "�豸����", "����ͺ�"};
					retFields = new String[]{"ITEM_CATEGORY2", "ITEM_NAME", "ITEM_SPEC", "ITEM_UNIT", "ITEM_CODE"};
					viewPercent = new String[]{"20%", "30%", "40%"};
					qryFieldNames = new String[]{"ITEM_CATEGORY2", "ITEM_NAME", "ITEM_SPEC"};
					qryFieldLabels = new String[]{"Ŀ¼���", "�豸����", "����ͺ�"};
					primaryKeys = new String[]{"ITEM_CATEGORY2"};

					lookProp.setMultipleChose(false);
					lookProp.setTotalWidth(750);
					lookProp.setMemorySpan(true);
					lookProp.setDtoClass(EamDhBillLDTO.class);
				}
				lookProp.setDisFieldNames(dispFieldNames);
				lookProp.setDisFieldLabels(dispFieldLabels);
				lookProp.setRetFields(retFields);
				lookProp.setViewPercent(viewPercent);
				lookProp.setQryFieldNames(qryFieldNames);
				lookProp.setQryFieldLabels(qryFieldLabels);
				lookProp.setPrimaryKeys(primaryKeys);
				//lookProp.setModelClass(AMSLookUpModel.class);
				lookProp.setModelClass(AMSInvLookUpModel.class);
				
				forwardURL = WebConstant.LOOK_UP_SERVLET;

				SessionUtil.saveLoopUpProp(req, lookProp);
			}

		} catch (Exception ex) {
			Logger.logError(ex);
		} finally {
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
	}
}
