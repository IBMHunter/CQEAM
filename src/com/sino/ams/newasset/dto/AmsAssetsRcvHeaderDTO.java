package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: �����ʲ�����ͷ��(���ż�͹�˾����Ҫ) AmsAssetsRcvHeader</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsRcvHeaderDTO extends CommonRecordDTO {

    private String receiveHeaderId = "";
    private int transId;
    private String receiveNo = "";
    private int receiveUser = SyBaseSQLUtil.NULL_INT_VALUE;
    private SimpleCalendar receiveDate = null;
    private String orderStatus = "";
    private String orderStatusName = "";
    private String receiveDept = "";
    private int receiveOrganizationId = SyBaseSQLUtil.NULL_INT_VALUE;
    private String faContentCode = "";
    private String faContentName = "";
    private String receiveDeptName = "";
    private String receiveUserName = "";
    private String receiveCompany = "";
    private String transReason = "";
    private String transferType = "";
    private String transferTypeValue = "";
    private String fromDept = "";
    private String fromDeptName = "";
    private String fromCompany = "";
    private SimpleCalendar transOutDate = null;
    private String transNo = "";
    private int groupId = SyBaseSQLUtil.NULL_INT_VALUE;
    private String groupName = "";
    private int approvedUser = SyBaseSQLUtil.NULL_INT_VALUE;
    private SimpleCalendar approvedDate = null;
    private String procdureName = "";
    private String flowCode = "";
    private boolean flow2End = false; //�Ƿ����̽���
    private boolean canApprove = false;

    public AmsAssetsRcvHeaderDTO() {
        super();
        this.receiveDate = new SimpleCalendar();
        this.transOutDate = new SimpleCalendar();
        this.approvedDate = new SimpleCalendar();
        this.procdureName = AssetsDictConstant.PROCEDURE_NAME_RCV;
    }

    /**
     * ���ܣ����õ����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ���յ�ID
     * @param receiveHeaderId String
     */
    public void setReceiveHeaderId(String receiveHeaderId) {
        this.receiveHeaderId = receiveHeaderId;
    }

    /**
     * ���ܣ����õ����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ������ID
     * @param transId String
     */
    public void setTransId(int transId) {
        this.transId = transId;
    }

    /**
     * ���ܣ����õ����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ���յ����
     * @param receiveNo String
     */
    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    /**
     * ���ܣ����õ����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ��������
     * @param receiveDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setReceiveDate(String receiveDate) throws CalendarException {
        this.receiveDate.setCalendarValue(receiveDate);
    }

    /**
     * ���ܣ����õ����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ��������
     * @param transOutDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setTransOutDate(String transOutDate) throws CalendarException {
        this.transOutDate.setCalendarValue(transOutDate);
    }


    /**
     * ���ܣ����õ����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ��������
     * @param approvedDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setApprovedDate(String approvedDate) throws CalendarException {
        this.approvedDate.setCalendarValue(approvedDate);
    }

    /**
     * ���ܣ����õ����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ���յ�״̬
     * @param orderStatus String
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * ���ܣ����õ����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ���ղ���
     * @param receiveDept String
     */
    public void setReceiveDept(String receiveDept) {
        this.receiveDept = receiveDept;
    }


    public void setFaContentCode(String faContentCode) {
        this.faContentCode = faContentCode;
    }

    public void setFaContentName(String faContentName) {
        this.faContentName = faContentName;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    public void setReceiveDeptName(String receiveDeptName) {
        this.receiveDeptName = receiveDeptName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public void setTransferTypeValue(String transferTypeValue) {
        this.transferTypeValue = transferTypeValue;
    }

    public void setTransReason(String transReason) {
        this.transReason = transReason;
    }

    public void setFromDept(String fromDept) {
        this.fromDept = fromDept;
    }

    public void setFromDeptName(String fromDeptName) {
        this.fromDeptName = fromDeptName;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }


    public void setFromCompany(String fromCompany) {
        this.fromCompany = fromCompany;
    }


    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public void setFlow2End(boolean flow2End) {
        this.flow2End = flow2End;
    }

    public void setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
    }

    /**
     * ���ܣ���ȡ�����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ���յ�ID
     * @return String
     */
    public String getReceiveHeaderId() {
        return this.receiveHeaderId;
    }

    /**
     * ���ܣ���ȡ�����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ������ID
     * @return String
     */
    public int getTransId() {
        return this.transId;
    }

    /**
     * ���ܣ���ȡ�����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ���յ����
     * @return String
     */
    public String getReceiveNo() {
        return this.receiveNo;
    }


    /**
     * ���ܣ���ȡ�����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ��������
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getReceiveDate() throws CalendarException {
        receiveDate.setCalPattern(getCalPattern());
        return this.receiveDate;
    }

    /**
     * ���ܣ���ȡ�����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ��������
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getTransOutDate() throws CalendarException {
        transOutDate.setCalPattern(getCalPattern());
        return this.transOutDate;
    }


    /**
     * ���ܣ���ȡ�����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ��������
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getApprovedDate() throws CalendarException {
        approvedDate.setCalPattern(getCalPattern());
        return this.approvedDate;
    }

    /**
     * ���ܣ���ȡ�����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ���յ�״̬
     * @return String
     */
    public String getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * ���ܣ���ȡ�����ʲ�����ͷ��(���ż�͹�˾����Ҫ)���� ���ղ���
     * @return String
     */
    public String getReceiveDept() {
        return this.receiveDept;
    }


    public String getFaContentCode() {
        return faContentCode;
    }

    public String getFaContentName() {
        return faContentName;
    }

    public String getReceiveCompany() {
        return receiveCompany;
    }

    public String getReceiveDeptName() {
        return receiveDeptName;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public String getTransferType() {
        return transferType;
    }

    public String getTransferTypeValue() {
        return transferTypeValue;
    }

    public String getTransReason() {
        return transReason;
    }

    public String getFromDept() {
        return fromDept;
    }

    public String getFromDeptName() {
        return fromDeptName;
    }

    public String getTransNo() {
        return transNo;
    }


    public String getFromCompany() {
        return fromCompany;
    }


    public String getGroupName() {
        return groupName;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public String getProcdureName() {
        return procdureName;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public boolean isFlow2End() {
        return flow2End;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

	public int getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(int receiveUser) {
		this.receiveUser = receiveUser;
	}

	public int getReceiveOrganizationId() {
		return receiveOrganizationId;
	}

	public void setReceiveOrganizationId(int receiveOrganizationId) {
		this.receiveOrganizationId = receiveOrganizationId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getApprovedUser() {
		return approvedUser;
	}

	public void setApprovedUser(int approvedUser) {
		this.approvedUser = approvedUser;
	}

	public void setReceiveDate(SimpleCalendar receiveDate) {
		this.receiveDate = receiveDate;
	}
}
