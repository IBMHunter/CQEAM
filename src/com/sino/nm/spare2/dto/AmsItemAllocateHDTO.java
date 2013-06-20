package com.sino.nm.spare2.dto;

import com.sino.base.SinoBaseObject;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.calen.SimpleDate;
import com.sino.base.calen.SimpleTime;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.TimeException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ����ҵ��ͷ��(AMS) AmsItemAllocateH</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 1.0
 */

public class AmsItemAllocateHDTO extends SinoBaseObject implements DTO {
    public AmsItemAllocateHDTO() {
        super();
        this.transDate = new SimpleCalendar();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
        this.canceledDate = new SimpleCalendar();
        this.confirmedDate = new SimpleCalendar();
        this.respectReturnDate = new SimpleCalendar();
        this.fromDate = new SimpleCalendar();
        this.toDate = new SimpleCalendar();
    }

    private String transId = "";
    private String transNo = "";
    private String transType = "";
    private String transStatus = "";
    private String fromUser = "";
    private String toUser = "";
    private String fromDept = "";
    private String toDept = "";
    private String fromObjectNo = "";
    private String toObjectNo = "";
    private String fromOrganizationId = "";
    private String toOrganizationId = "";
    private String fromObjectName = "";
    private String toObjectName = "";
    private String fromOrganizationName = "";
    private String toOrganizationName = "";

    private SimpleCalendar transDate = null;
    private String rcvUser = "";
    private SimpleCalendar creationDate = null;
    private String createdBy = "";
    private String createdUser = "";
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateBy = "";
    private SimpleCalendar canceledDate = null;
    private String canceledReason = "";
    private SimpleCalendar confirmedDate = null;
    private String confirmedBy = "";
    private String sourceId = "";
    private String remark = "";
    private SimpleCalendar respectReturnDate = null;
    private SimpleCalendar fromDate = null;
    private SimpleCalendar toDate = null;

    private int freightUserId = -1;  //Number		N		���ⲿ��ʵ��ֹ�ԱID
    private String freightUserName = "";//VARCHAR2	32	N		���ⲿ��ʵ��ֹ�Ա
    private String freightDeptCode = "";//Number		N		���ⲿ��
    private String freightDeptName = "";//      		N		���ⲿ��
    private int freightMisUser = -1; //Number		N		���ⲿ�ű�������Ա
    private String freightMisUserName = ""; //		N		���ⲿ�ű�������Ա
    private String receiveUserName = ""; //VARCHAR2	32	N		���ձ�����Ա
    private String receiveUserTel = "";  //VARCHAR2	64	N		���ձ�����Ա�绰


    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ���к�
     * @param transId String
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ���ݺ�
     * @param transNo String
     */
    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ��������
     * @param transType String
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ����״̬
     * @param transStatus String
     */
    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Դ������
     * @param fromUser String
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Ŀ�걣����
     * @param toUser String
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Դ���ܲ���
     * @param fromDept String
     */
    public void setFromDept(String fromDept) {
        this.fromDept = fromDept;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Ŀ�걣�ܲ���
     * @param toDept String
     */
    public void setToDept(String toDept) {
        this.toDept = toDept;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Դ�ֿ�
     * @param fromObjectNo String
     */
    public void setFromObjectNo(String fromObjectNo) {
        this.fromObjectNo = fromObjectNo;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Ŀ��ֿ�
     * @param toObjectNo String
     */
    public void setToObjectNo(String toObjectNo) {
        this.toObjectNo = toObjectNo;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ԴOU��֯
     * @param fromOrganizationId String
     */
    public void setFromOrganizationId(String fromOrganizationId) {
        this.fromOrganizationId = fromOrganizationId;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� Ŀ��OU��֯
     * @param toOrganizationId String
     */
    public void setToOrganizationId(String toOrganizationId) {
        this.toOrganizationId = toOrganizationId;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ��������
     * @param transDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setTransDate(String transDate) throws CalendarException {
        this.transDate.setCalendarValue(transDate);
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ������
     * @param rcvUser String
     */
    public void setRcvUser(String rcvUser) {
        this.rcvUser = rcvUser;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ��������
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ������
     * @param createdBy String
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� �ϴθ�������
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� �ϴθ�����
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ȡ������
     * @param canceledDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCanceledDate(String canceledDate) throws CalendarException {
        this.canceledDate.setCalendarValue(canceledDate);
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ȡ��ԭ��
     * @param canceledReason String
     */
    public void setCanceledReason(String canceledReason) {
        this.canceledReason = canceledReason;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ȷ������
     * @param confirmedDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setConfirmedDate(String confirmedDate) throws CalendarException {
        this.confirmedDate.setCalendarValue(confirmedDate);
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ȷ����
     * @param confirmedBy String
     */
    public void setConfirmedBy(String confirmedBy) {
        this.confirmedBy = confirmedBy;
    }

    /**
     * ���ܣ����ñ���ҵ��ͷ��(AMS)���� ������/���䵥��Ӧ�����쵥��
     * @param sourceId String
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }


    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ���к�
     * @return String
     */
    public String getTransId() {
        return this.transId;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ���ݺ�
     * @return String
     */
    public String getTransNo() {
        return this.transNo;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ��������
     * @return String
     */
    public String getTransType() {
        return this.transType;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ����״̬
     * @return String
     */
    public String getTransStatus() {
        return this.transStatus;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Դ������
     * @return String
     */
    public String getFromUser() {
        return this.fromUser;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Ŀ�걣����
     * @return String
     */
    public String getToUser() {
        return this.toUser;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Դ���ܲ���
     * @return String
     */
    public String getFromDept() {
        return this.fromDept;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Ŀ�걣�ܲ���
     * @return String
     */
    public String getToDept() {
        return this.toDept;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Դ�ֿ�
     * @return String
     */
    public String getFromObjectNo() {
        return this.fromObjectNo;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Ŀ��ֿ�
     * @return String
     */
    public String getToObjectNo() {
        return this.toObjectNo;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ԴOU��֯
     * @return String
     */
    public String getFromOrganizationId() {
        return this.fromOrganizationId;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� Ŀ��OU��֯
     * @return String
     */
    public String getToOrganizationId() {
        return this.toOrganizationId;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ��������
     * @return SimpleCalendar
     */
    public SimpleCalendar getTransDate() {
        return this.transDate;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ������
     * @return String
     */
    public String getRcvUser() {
        return this.rcvUser;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ��������
     * @return SimpleCalendar
     */
    public SimpleCalendar getCreationDate() {
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ������
     * @return String
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� �ϴθ�������
     * @return SimpleCalendar
     */
    public SimpleCalendar getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� �ϴθ�����
     * @return String
     */
    public String getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ȡ������
     * @return SimpleCalendar
     */
    public SimpleCalendar getCanceledDate() {
        return this.canceledDate;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ȡ��ԭ��
     * @return String
     */
    public String getCanceledReason() {
        return this.canceledReason;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ȷ������
     * @return SimpleCalendar
     */
    public SimpleCalendar getConfirmedDate() {
        return this.confirmedDate;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ȷ����
     * @return String
     */
    public String getConfirmedBy() {
        return this.confirmedBy;
    }

    /**
     * ���ܣ���ȡ����ҵ��ͷ��(AMS)���� ������/���䵥��Ӧ�����쵥��
     * @return String
     */
    public String getSourceId() {
        return this.sourceId;
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

    public String getFromOrganizationName() {
        return fromOrganizationName;
    }

    public void setFromOrganizationName(String fromOrganizationName) {
        this.fromOrganizationName = fromOrganizationName;
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

    public SimpleCalendar getRespectReturnDate() {
        return respectReturnDate;
    }

    public void setRespectReturnDate(String respectReturnDate) throws CalendarException {
        this.respectReturnDate.setCalendarValue(respectReturnDate);
    }


    public SimpleCalendar getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) throws CalendarException {
        this.fromDate.setCalendarValue(fromDate);
    }

    public SimpleCalendar getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) throws CalendarException {
        this.toDate.setCalendarValue(toDate);
    }

    /**
	 * ���ܣ������ѯ�����������ڵ����һ����������������ȥӦ����ÿ����ѯSQL�Լ������������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getSQLToDate(){
		SimpleCalendar sqlEndCal = new SimpleCalendar();
		if (!StrUtil.isEmpty(toDate.toString())) {
			try {
				SimpleDate date = toDate.getSimpleDate();
				SimpleTime time = SimpleTime.getEndTime();
				sqlEndCal = new SimpleCalendar(date, time);
			} catch (TimeException ex) {
				ex.printLog();
			}
		}
		return sqlEndCal;
	}

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public int getFreightUserId() {
        return freightUserId;
    }

    public void setFreightUserId(int freightUserId) {
        this.freightUserId = freightUserId;
    }

    public String getFreightUserName() {
        return freightUserName;
    }

    public void setFreightUserName(String freightUserName) {
        this.freightUserName = freightUserName;
    }

    public String getFreightDeptCode() {
        return freightDeptCode;
    }

    public void setFreightDeptCode(String freightDeptCode) {
        this.freightDeptCode = freightDeptCode;
    }

    public int getFreightMisUser() {
        return freightMisUser;
    }

    public void setFreightMisUser(int freightMisUser) {
        this.freightMisUser = freightMisUser;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getReceiveUserTel() {
        return receiveUserTel;
    }

    public void setReceiveUserTel(String receiveUserTel) {
        this.receiveUserTel = receiveUserTel;
    }

    public String getFreightDeptName() {
        return freightDeptName;
    }

    public void setFreightDeptName(String freightDeptName) {
        this.freightDeptName = freightDeptName;
    }

    public String getFreightMisUserName() {
        return freightMisUserName;
    }

    public void setFreightMisUserName(String freightMisUserName) {
        this.freightMisUserName = freightMisUserName;
    }
}