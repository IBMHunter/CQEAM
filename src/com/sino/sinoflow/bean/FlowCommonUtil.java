package com.sino.sinoflow.bean;

import java.sql.Connection;

import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import org.json.JSONObject;

import com.sino.base.exception.DataHandleException;
import com.sino.base.util.StrUtil;
import com.sino.config.SinoConfig;
import com.sino.sinoflow.bean.impl.DefaultFlowCommon;
import com.sino.sinoflow.bean.impl.HNFlowCommon;
import com.sino.sinoflow.flowinterface.AppFlowBaseDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA. User: T_yuyao Date: 2011-3-21 Time: 14:37:15 To change this template
 * use File | Settings | File Templates.
 */
public class FlowCommonUtil {
	private AppFlowBaseDTO bForm = null;
	private Connection conn = null;
//	private OaTodoDAO oaTodoDAO = null;

	FlowCommon commonUtil = null; 
	
	public FlowCommonUtil(AppFlowBaseDTO bForm, Connection conn) {
		this.bForm = bForm;
		this.conn = conn;
		commonUtil = new DefaultFlowCommon( bForm, conn );
		if( SinoConfig.getFlowHnOpen() ){
			commonUtil = new HNFlowCommon( bForm, conn );
		}
	}

	/**
	 * ���ܣ�����������Ϣ
	 * 
	 * @param isSubmit
	 *            boolean
	 * @return boolean
	 */
	public boolean processProcedure(boolean isSubmit) {
		return commonUtil.processProcedure(isSubmit);
	}

	/**
	 * ������������
	 * 
	 * @return
	 */
	public boolean processDel() {
		return commonUtil.processDel();
	}

	/**
	 * ȡ������
	 * 
	 * @return
	 */
	public boolean processCancel() {
		return commonUtil.processCancel();
	}

	/**
	 * �˻�ʱ���õķ���
	 * 
	 * @return boolean
	 */
	public boolean reject() {
		return commonUtil.reject();
	}

	public String getFlowCode() throws Exception {
		String flowCode = "";
		String fieldValue = "";

		if (bForm != null) {
			fieldValue = bForm.getSf_appFieldValue();
		} else {
			throw new DataHandleException("�������̲�����ʱ,δ����DTO����");
		}

		if (!fieldValue.equals("")) {
			JSONObject jObj = new JSONObject(fieldValue);
			String nextTaskDate = jObj.getString("sf_nextTaskData");
			JSONObject nextTaskObj = new JSONObject(nextTaskDate);
			flowCode = nextTaskObj.getString("flowCode");
		}
		return flowCode;
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	private String getPrimaryKey() {
		String primaryKey = bForm.getPrimaryKey();
		if (StrUtil.isEmpty(primaryKey)) {
			primaryKey = bForm.getApp_dataID();
		}
		return primaryKey;
	}
}
