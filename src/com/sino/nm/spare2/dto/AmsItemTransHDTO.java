package com.sino.nm.spare2.dto;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.calen.SimpleDate;
import com.sino.base.calen.SimpleTime;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.TimeException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ��������ͷ��(AMS) AmsItemTransH</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsItemTransHDTO extends AMSFlowDTO {

    private String transId = "";
    private String transNo = "";
    private String transType = "";
    private String transStatus = "";
    private int fromUser=-1 ;
    private int toUser=-1 ;
    private String fromDept = "";
    private String toDept = "";
    private String fromObjectNo = "";
    private String fromObjectName = "";
    private String fromObjectLocation = "";
    private String toObjectNo = "";
    private String toObjectName = "";
    private String toObjectLocation = "";
    private int fromOrganizationId=-1 ;
    private String fromOrganizationName = "";
    private int toOrganizationId=-1 ;
    private String toOrganizationName = "";
    private SimpleCalendar transDate = null;
    private int rcvUser =-1;
    private SimpleCalendar creationDate = null;
    private int createdBy =-1;
    private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateBy=-1;
    private SimpleCalendar canceledDate = null;
    private String canceledReason = "";
    private String transStatusName = "";
    private String createdUser = "";
    private SimpleCalendar fromDate = null;
    private SimpleCalendar toDate = null;
    private String flag = "";
    private String remark = "";
    private String company  ="";        //��˾,����
    private String address  ="";        //��ַ���ʱ�
    private String contact  ="";        //��ϵ��
    private String tel  ="";            //��ϵ�绰
    private String fax  ="";            //����
    private String attribute1  ="";     //ί������
    private String attribute2  ="";     //������
    private String attribute3 ="";      //��ֵ���
    private SimpleCalendar respectReturnDate = null;       //Ԥ�ƹ黹����
    private String deptCode = "";           //���ò��ţ��ң���ѡ�����HR������ѡ��
    private String deptName = "";           //���ò��ţ��ң���ѡ�����HR������ѡ��
    private String authorizationUser = "";  //��Ȩ�ˣ��ֹ���д����췽������
    private String invManager = "";         //�ֹ�Ա���ֹ���д��
    private String reason = "";             //��;
    private String spareManufacturer = "";             
    private String spareManufacturerOpt = "";
    private String spareType = "";
    private String fromGroup = "";
    private String spareManufacturerName = "";
    private String feedbackInfo = "";
    private String feedbackType = "";
    private String feedbackTypeOpt = "";

    public AmsItemTransHDTO() {
        transDate = new SimpleCalendar();
        creationDate = new SimpleCalendar();
        lastUpdateDate = new SimpleCalendar();
        canceledDate = new SimpleCalendar();
        fromDate = new SimpleCalendar();
        toDate = new SimpleCalendar();
        respectReturnDate = new SimpleCalendar();
    }


    public String getFeedbackInfo() {
        return feedbackInfo;
    }

    public void setFeedbackInfo(String feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackTypeOpt() {
        return feedbackTypeOpt;
    }

    public void setFeedbackTypeOpt(String feedbackTypeOpt) {
        this.feedbackTypeOpt = feedbackTypeOpt;
    }

    public String getSpareManufacturerName() {
        return spareManufacturerName;
    }

    public void setSpareManufacturerName(String spareManufacturerName) {
        this.spareManufacturerName = spareManufacturerName;
    }

    public String getSpareManufacturerOpt() {
        return spareManufacturerOpt;
    }

    public void setSpareManufacturerOpt(String spareManufacturerOpt) {
        this.spareManufacturerOpt = spareManufacturerOpt;
    }

    public String getSpareManufacturer() {
        return spareManufacturer;
    }

    public void setSpareManufacturer(String spareManufacturer) {
        this.spareManufacturer = spareManufacturer;
    }

    public String getSpareType() {
        return spareType;
    }

    public void setSpareType(String spareType) {
        this.spareType = spareType;
    }

    public String getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(String fromGroup) {
        this.fromGroup = fromGroup;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ���к�
     * @param transId String
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ���ݺ�
     * @param transNo String
     */
    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ��������
     * @param transType String
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ����״̬
     * @param transStatus String
     */
    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� Դ������
     * @param fromUser String
     */
    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� Ŀ�걣����
     * @param toUser String
     */
    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� Դ���ܲ���
     * @param fromDept String
     */
    public void setFromDept(String fromDept) {
        this.fromDept = fromDept;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� Ŀ�걣�ܲ���
     * @param toDept String
     */
    public void setToDept(String toDept) {
        this.toDept = toDept;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� Դ�ֿ�
     * @param fromObjectNo String
     */
    public void setFromObjectNo(String fromObjectNo) {
        this.fromObjectNo = fromObjectNo;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� Ŀ��ֿ�
     * @param toObjectNo String
     */
    public void setToObjectNo(String toObjectNo) {
        this.toObjectNo = toObjectNo;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ԴOU��֯
     * @param fromOrganizationId String
     */
    public void setFromOrganizationId(int fromOrganizationId) {
        this.fromOrganizationId = fromOrganizationId;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� Ŀ��OU��֯
     * @param toOrganizationId String
     */
    public void setToOrganizationId(int toOrganizationId) {
        this.toOrganizationId = toOrganizationId;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ��������
     * @param transDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setTransDate(String transDate) throws CalendarException {
        this.transDate.setCalendarValue(transDate);
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ������
     * @param rcvUser String
     */
    public void setRcvUser(int rcvUser) {
        this.rcvUser = rcvUser;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ��������
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ������
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� �ϴθ�������
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� �ϴθ�����
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ȡ������
     * @param canceledDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCanceledDate(String canceledDate) throws CalendarException {
        this.canceledDate.setCalendarValue(canceledDate);
    }

    /**
     * ���ܣ����ñ�������ͷ��(AMS)���� ȡ��ԭ��
     * @param canceledReason String
     */
    public void setCanceledReason(String canceledReason) {
        this.canceledReason = canceledReason;
    }


    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ���к�
     * @return String
     */
    public String getTransId() {
        return this.transId;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ���ݺ�
     * @return String
     */
    public String getTransNo() {
        return this.transNo;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ��������
     * @return String
     */
    public String getTransType() {
        return this.transType;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ����״̬
     * @return String
     */
    public String getTransStatus() {
        return this.transStatus;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� Դ������
     * @return String
     */
    public int getFromUser() {
        return this.fromUser;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� Ŀ�걣����
     * @return String
     */
    public int getToUser() {
        return this.toUser;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� Դ���ܲ���
     * @return String
     */
    public String getFromDept() {
        return this.fromDept;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� Ŀ�걣�ܲ���
     * @return String
     */
    public String getToDept() {
        return this.toDept;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� Դ�ֿ�
     * @return String
     */
    public String getFromObjectNo() {
        return this.fromObjectNo;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� Ŀ��ֿ�
     * @return String
     */
    public String getToObjectNo() {
        return this.toObjectNo;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ԴOU��֯
     * @return String
     */
    public int getFromOrganizationId() {
        return this.fromOrganizationId;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� Ŀ��OU��֯
     * @return String
     */
    public int getToOrganizationId() {
        return this.toOrganizationId;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ��������
     * @return SimpleCalendar
     */
    public SimpleCalendar getTransDate() throws CalendarException {
        transDate.setCalPattern(getCalPattern());
        return this.transDate;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ������
     * @return String
     */
    public int getRcvUser() {
        return this.rcvUser;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ��������
     * @return SimpleCalendar
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        creationDate.setCalPattern(getCalPattern());
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ������
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� �ϴθ�������
     * @return SimpleCalendar
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� �ϴθ�����
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ȡ������
     * @return SimpleCalendar
     */
    public SimpleCalendar getCanceledDate() throws CalendarException {
        canceledDate.setCalPattern(getCalPattern());
        return this.canceledDate;
    }

    /**
     * ���ܣ���ȡ��������ͷ��(AMS)���� ȡ��ԭ��
     * @return String
     */
    public String getCanceledReason() {
        return this.canceledReason;
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

    public String getToObjectName() {
        return toObjectName;
    }

    public void setToObjectName(String toObjectName) {
        this.toObjectName = toObjectName;
    }

    public String getToObjectLocation() {
        return toObjectLocation;
    }

    public void setToObjectLocation(String toObjectLocation) {
        this.toObjectLocation = toObjectLocation;
    }

    public SimpleCalendar getFromDate() throws CalendarException {
        fromDate.setCalPattern(getCalPattern());
        return fromDate;
    }

    public void setFromDate(String fromDate) throws CalendarException {
        this.fromDate.setCalendarValue(fromDate);
    }

    public SimpleCalendar getToDate() throws CalendarException {
        toDate.setCalPattern(getCalPattern());
        return toDate;
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

    public void setToDate(String toDate) throws CalendarException {
        this.toDate.setCalendarValue(toDate);
    }

    public String getToOrganizationName() {
        return toOrganizationName;
    }

    public void setToOrganizationName(String toOrganizationName) {
        this.toOrganizationName = toOrganizationName;
    }

    public String getFromOrganizationName() {
        return fromOrganizationName;
    }

    public void setFromOrganizationName(String fromOrganizationName) {
        this.fromOrganizationName = fromOrganizationName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFromObjectName() {
        return fromObjectName;
    }

    public void setFromObjectName(String fromObjectName) {
        this.fromObjectName = fromObjectName;
    }

    public String getFromObjectLocation() {
        return fromObjectLocation;
    }

    public void setFromObjectLocation(String fromObjectLocation) {
        this.fromObjectLocation = fromObjectLocation;
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

    public void setRespectReturnDate(String  respectReturnDate) throws CalendarException {
        this.respectReturnDate.setCalendarValue(respectReturnDate);
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getAuthorizationUser() {
        return authorizationUser;
    }

    public void setAuthorizationUser(String authorizationUser) {
        this.authorizationUser = authorizationUser;
    }

    public String getInvManager() {
        return invManager;
    }

    public void setInvManager(String invManager) {
        this.invManager = invManager;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
