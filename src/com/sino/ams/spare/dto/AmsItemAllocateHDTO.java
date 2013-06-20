package com.sino.ams.spare.dto;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

import com.sino.ams.bean.CommonRecordDTO;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */

public class AmsItemAllocateHDTO extends AMSFlowDTO {
	private SimpleCalendar fromDate = null;
	private SimpleCalendar toDate = null;
	private String transId = "";
	private String transNo = "";
	private String transType = "";
	private String transStatus = "";
	private int fromUser = -1;
	private int toUser = -1;
	private String fromDept = "";
	private String toDept = "";
	private String fromObjectNo = "";
	private String toObjectNo = "";
	private int fromOrganizationId = -1;
	private int toOrganizationId = -1;
	private SimpleCalendar transDate = null;
	private String rcvUser = "";
	private SimpleCalendar canceledDate = null;
	private String canceledReason = "";
	private SimpleCalendar confirmedDate = null;
	private String confirmedBy = "";
	private String fromOrganizationName = "";
	private String createdUser = "";
	private String sourceId = "";
	private String transStatusName = "";
	private String remark = "";
	private String toOrganizationName = "";
    private String fromObjectName = "";
    private String toObjectName = "";
    private String attribute1 = "";

    public AmsItemAllocateHDTO() {
		super();
		this.transDate = new SimpleCalendar();
		this.canceledDate = new SimpleCalendar();
		this.confirmedDate = new SimpleCalendar();
		this.fromDate = new SimpleCalendar();
		this.toDate = new SimpleCalendar();
	}

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getFromObjectName() {
        return fromObjectName;
    }

    public void setFromObjectName(String fromObjectName) {
        this.fromObjectName = fromObjectName;
    }

    public String getToObjectName() {
        return toObjectName;
    }

    public void setToObjectName(String toObjectName) {
        this.toObjectName = toObjectName;
    }

    public String getToOrganizationName() {
		return toOrganizationName;
	}

	public void setToOrganizationName(String toOrganizationName) {
		this.toOrganizationName = toOrganizationName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFromOrganizationName() {
		return fromOrganizationName;
	}

	public void setFromOrganizationName(String fromOrganizationName) {
		this.fromOrganizationName = fromOrganizationName;
	}

	public String getTransStatusName() {
		return transStatusName;
	}

	public void setTransStatusName(String transStatusName) {
		this.transStatusName = transStatusName;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public void setFromDate(String fromDate) throws CalendarException {
		this.fromDate.setCalendarValue(fromDate);
	}

	public SimpleCalendar getFromDate() {
		return this.fromDate;
	}

	public void setToDate(String toDate) throws CalendarException {
		this.toDate.setCalendarValue(toDate);
	}

	public SimpleCalendar getToDate() {
		return this.toDate;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ���к�
	 *
	 * @param transId String
	 */
	public void setTransId(String transId) {
		this.transId = transId;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ���ݺ�
	 *
	 * @param transNo String
	 */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ��������
	 *
	 * @param transType String
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ����״̬
	 *
	 * @param transStatus String
	 */
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Դ������
	 *
	 * @param fromUser String
	 */
	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Ŀ�걣����
	 *
	 * @param toUser String
	 */
	public void setToUser(int toUser) {
		this.toUser = toUser;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Դ���ܲ���
	 *
	 * @param fromDept String
	 */
	public void setFromDept(String fromDept) {
		this.fromDept = fromDept;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Ŀ�걣�ܲ���
	 *
	 * @param toDept String
	 */
	public void setToDept(String toDept) {
		this.toDept = toDept;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Դ�ֿ�
	 *
	 * @param fromObjectNo String
	 */
	public void setFromObjectNo(String fromObjectNo) {
		this.fromObjectNo = fromObjectNo;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Ŀ��ֿ�
	 *
	 * @param toObjectNo String
	 */
	public void setToObjectNo(String toObjectNo) {
		this.toObjectNo = toObjectNo;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ԴOU��֯
	 *
	 * @param fromOrganizationId String
	 */
	public void setFromOrganizationId(int fromOrganizationId) {
		this.fromOrganizationId = fromOrganizationId;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Ŀ��OU��֯
	 *
	 * @param toOrganizationId String
	 */
	public void setToOrganizationId(int toOrganizationId) {
		this.toOrganizationId = toOrganizationId;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ��������
	 *
	 * @param transDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setTransDate(String transDate) throws CalendarException {
		this.transDate.setCalendarValue(transDate);
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ������
	 *
	 * @param rcvUser String
	 */
	public void setRcvUser(String rcvUser) {
		this.rcvUser = rcvUser;
	}


	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ȡ������
	 *
	 * @param canceledDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCanceledDate(String canceledDate) throws CalendarException {
		this.canceledDate.setCalendarValue(canceledDate);
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ȡ��ԭ��
	 *
	 * @param canceledReason String
	 */
	public void setCanceledReason(String canceledReason) {
		this.canceledReason = canceledReason;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ȷ������
	 *
	 * @param confirmedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setConfirmedDate(String confirmedDate) throws CalendarException {
		this.confirmedDate.setCalendarValue(confirmedDate);
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ȷ����
	 *
	 * @param confirmedBy String
	 */
	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	/**
	 * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ������/���䵥��Ӧ�����쵥��
	 *
	 * @param sourceId String
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}


	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ���к�
	 *
	 * @return String
	 */
	public String getTransId() {
		return this.transId;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ���ݺ�
	 *
	 * @return String
	 */
	public String getTransNo() {
		return this.transNo;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ��������
	 *
	 * @return String
	 */
	public String getTransType() {
		return this.transType;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ����״̬
	 *
	 * @return String
	 */
	public String getTransStatus() {
		return this.transStatus;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Դ������
	 *
	 * @return String
	 */
	public int getFromUser() {
		return this.fromUser;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Ŀ�걣����
	 *
	 * @return String
	 */
	public int getToUser() {
		return this.toUser;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Դ���ܲ���
	 *
	 * @return String
	 */
	public String getFromDept() {
		return this.fromDept;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Ŀ�걣�ܲ���
	 *
	 * @return String
	 */
	public String getToDept() {
		return this.toDept;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Դ�ֿ�
	 *
	 * @return String
	 */
	public String getFromObjectNo() {
		return this.fromObjectNo;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Ŀ��ֿ�
	 *
	 * @return String
	 */
	public String getToObjectNo() {
		return this.toObjectNo;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ԴOU��֯
	 *
	 * @return String
	 */
	public int getFromOrganizationId() {
		return this.fromOrganizationId;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Ŀ��OU��֯
	 *
	 * @return String
	 */
	public int getToOrganizationId() {
		return this.toOrganizationId;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ��������
	 *
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getTransDate() {
		return this.transDate;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ������
	 *
	 * @return String
	 */
	public String getRcvUser() {
		return this.rcvUser;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ȡ������
	 *
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getCanceledDate() {
		return this.canceledDate;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ȡ��ԭ��
	 *
	 * @return String
	 */
	public String getCanceledReason() {
		return this.canceledReason;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ȷ������
	 *
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getConfirmedDate() {
		return this.confirmedDate;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ȷ����
	 *
	 * @return String
	 */
	public String getConfirmedBy() {
		return this.confirmedBy;
	}

	/**
	 * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ������/���䵥��Ӧ�����쵥��
	 *
	 * @return String
	 */
	public String getSourceId() {
		return this.sourceId;
	}

}
