package com.sino.nm.ams.spare.invprivi.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.ReflectException;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;

import com.sino.ams.constant.DictConstant;

/**
 * <p>Title: �ֿ�Ȩ�ޱ�(AMS) AmsInvPrivi</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class AmsInvPriviDTO extends CheckBoxDTO {
    private String rownum = "";
    private int userId;
    private String invCode = "";
    private String actionCode = "";
    private String priviId = "";

    //Ȩ������
    private String invIn = "0";
    private String invOut = "0";
    private String invQuery = "0";

    /*  private String invApply = "0";
private String invBadIn = "0";
private String invBadReturn = "0";
private String invDiscard = "0";
private String invNewIn = "0";
private String invOrderPrint = "0";
private String invOut = "0";
private String invQuery = "0";
private String invRcvIn = "0";
private String invRepairIn = "0";
private String invSendRepair = "0";
private String invTransfer = "0";*/

    //��ѯ����
    private String executeUser = "";
    private String executeInv = "";
    
    private String businessCategory = ""; //ҵ�����ͣ���Ӧҵ������ѡ�
    private String bizCategoryOpt = ""; //ҵ�����������б�

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	public String getInvIn() {
        return invIn;
    }

    public void setInvIn(String invIn) {
        this.invIn = invIn;
    }

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getExecuteInv() {
        return executeInv;
    }

    public void setExecuteInv(String executeInv) {
        this.executeInv = executeInv;
    }


    public String getExecuteUser() {
        return executeUser;
    }

    public void setExecuteUser(String executeUser) {
        this.executeUser = executeUser;
    }


    /**
     * ���ܣ����òֿ�Ȩ�ޱ�(AMS)���� �û�ID
     *
     * @param userId String
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * ���ܣ����òֿ�Ȩ�ޱ�(AMS)���� �ֿ�ID
     *
     * @param invCode String
     */
    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    /**
     * ���ܣ����òֿ�Ȩ�ޱ�(AMS)���� ��������
     *
     * @param actionCode String
     */
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    /**
     * ���ܣ����òֿ�Ȩ�ޱ�(AMS)���� ���к�
     *
     * @param priviId String
     */
    public void setPriviId(String priviId) {
        this.priviId = priviId;
    }


    public void setInvOut(String invOut) {
        this.invOut = invOut;
    }

    public void setInvQuery(String invQuery) {
        this.invQuery = invQuery;
    }


    /**
     * ���ܣ���ȡ�ֿ�Ȩ�ޱ�(AMS)���� �û�ID
     *
     * @return String
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * ���ܣ���ȡ�ֿ�Ȩ�ޱ�(AMS)���� �ֿ�ID
     *
     * @return String
     */
    public String getInvCode() {
        return this.invCode;
    }

    /**
     * ���ܣ���ȡ�ֿ�Ȩ�ޱ�(AMS)���� ��������
     *
     * @return String
     */
    public String getActionCode() {
        return this.actionCode;
    }

    /**
     * ���ܣ���ȡ�ֿ�Ȩ�ޱ�(AMS)���� ���к�
     *
     * @return String
     */
    public String getPriviId() {
        return this.priviId;
    }


    public String getInvOut() {
        return invOut;
    }

    public String getInvQuery() {
        return invQuery;
    }


    public boolean hasPrivi(String property) throws ReflectException {
        boolean hasPrivi = false;
        property = StrUtil.getJavaField(property);
        if (ReflectionUtil.hasProperty(this.getClass(), property)) {
            String propValue = (String) ReflectionUtil.getProperty(this, property);
            hasPrivi = propValue.equals(DictConstant.HAS_PRIVI_YES);
        }
        return hasPrivi;
    }

	public String getBizCategoryOpt() {
		return bizCategoryOpt;
	}

	public void setBizCategoryOpt(String bizCategoryOpt) {
		this.bizCategoryOpt = bizCategoryOpt;
	}
}
