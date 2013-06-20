package com.sino.ams.newasset.rent.dto;

import java.util.List;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;
import com.sino.framework.security.dto.ServletConfigDTO;

public class AssetsTransHeaderDTO extends AMSFlowDTO {
	 private String transId = "";
	    private String transNo = "";
	    private String transType = "";
	    private String transStatus = "";
	    private int fromOrganizationId = 0;
	    private int toOrganizationId = 0;
	    private String fromDept = "";
	    private String toDept = "";
	    private String fromObjectNo = "";
	    private String toObjectNo = "";
	    private String fromPerson = "";
	    private String toPerson = "";
	    private String createdReason = "";
	    private SimpleCalendar canceledDate = null;
	    private String canceledReason = "";
	    private SimpleCalendar approvedDate = null;
	    private int approvedBy;
	    private int receivedUser;
	    private String receivedUserName = "";
	    private SimpleCalendar transDate = null;

	    private String transTypeValue = ""; //�ʲ�ҵ����������
	    private String created = ""; //����������;
	    private String toObjectName = ""; //Ŀ�ĵص�����
	    private String toUserName = ""; //Ŀ�걣��������
	    private String transStatusDesc = ""; //����״̬����
	    private String toDeptName = ""; //Ŀ�Ĳ�������
	    private String fromCompanyName = "";
	    private String fromDeptName = "";
	    private String toCompanyName = "";

	    private int fromGroup=0;
	    private String fromGroupName = "";
	    private String fromGroupOption = "";

	    private String toGroup = "";
	    private String toGroupName = "";
	    private String toGroupOption = "";

	    private String fromDeptOption = "";
	    private String toDeptOption = "";
	    private String approvedUser = "";
	    private String statusOption = "";
	    private boolean flow2End = false; //�Ƿ����̽���
	    private String transferType = ""; //����������һ�����ͣ������ڣ����ż䣬���м�
	    private String transferTypeOption = "";
	    private String printType = "";
	    private boolean canReceive = true;

	    private String email = ""; //�����˵����ʼ�
	    private String phoneNumber = ""; //�������ֻ�����
	    private String bookTypeCode = ""; //������˾�ʲ��˲�����
	    private String bookTypeName = ""; //������˾�ʲ��˲�����
	    private String toCompanyOption = ""; //���빫˾������ѡ��
	    private String userDeptName = ""; //�û���������
	    private String faContentCode = ""; //�ʲ������Ŀǰ��Ϊ�������໹��������
	    private String faContentName = ""; //�ʲ������Ŀǰ��Ϊ�������໹��������
	    private String faContentOption = ""; //�ʲ������������ѡ��

	    private ServletConfigDTO servletConfig = null;
	    private String fromCompanyOption = ""; //Ϊ���ɹ��ƶ�������
	    private String bookTypeOption = ""; //Ϊ���ɹ��ƶ�������

	    private String sectionRight = ""; //�������������Ϣ
	    private String hiddenRight = ""; //�������������Ϣ
	    private String attribute1 = ""; //�������������Ϣ
	    private String attribute2 = ""; //�������������Ϣ
	    private String attribute3 = ""; //�������������Ϣ
	    private String attribute4 = ""; //�������������Ϣ
	    private String attribute5 = ""; //�������������Ϣ
	    private String groupProp = ""; //�����������

	    private String lossesName = ""; //�������
	    private SimpleCalendar lossesDate = null; //�������
	    private String groupPid = ""; //�������������У���ǰ���ĸ����
	    private String producedNewBarcode = "N";
	    private String controlPrivi = "N";

	    private String currTaskId = "";//�������������е��������
	    private String currRoleName = "";//�������������е��������
	    private String accessSheet = "";//��������

	    private String[] barcodess = null;

	    private String isThred = "";
	    private String rejectHType = "";
	    private String rejectTypeHName = "";
	    private String rejectTypeHOpt = "";


	    private List<AssetsTransLineDTO> lines = null;
	    
	    private String financeProp = ""; //�ʲ����࣬Ŀǰ��Ϊ�������໹��������
	    private String financePropName = ""; //�ʲ����࣬Ŀǰ��Ϊ�������໹��������
	    private String financePropOption = ""; //�ʲ�����������ѡ��

	    public String getRejectHType() {
	        return rejectHType;
	    }

	    public void setRejectHType(String rejectHType) {
	        this.rejectHType = rejectHType;
	    }

	    public String getRejectTypeHName() {
	        return rejectTypeHName;
	    }

	    public void setRejectTypeHName(String rejectTypeHName) {
	        this.rejectTypeHName = rejectTypeHName;
	    }

	    public String getRejectTypeHOpt() {
	        return rejectTypeHOpt;
	    }

	    public void setRejectTypeHOpt(String rejectTypeHOpt) {
	        this.rejectTypeHOpt = rejectTypeHOpt;
	    }

	    public String getThred() {
	        return isThred;
	    }

	    public void setThred(String thred) {
	        isThred = thred;
	    }

	    public String[] getBarcodess() {
	        return barcodess;
	    }

	    public void setBarcodess(String[] barcodess) {
	        this.barcodess = barcodess;
	    }

	    public String getAccessSheet() {
	        return accessSheet;
	    }

	    public void setAccessSheet(String accessSheet) {
	        this.accessSheet = accessSheet;
	    }

	    public String getCurrTaskId() {
	        return currTaskId;
	    }

	    public void setCurrTaskId(String currTaskId) {
	        this.currTaskId = currTaskId;
	    }

	    public String getCurrRoleName() {
	        return currRoleName;
	    }

	    public void setCurrRoleName(String currRoleName) {
	        this.currRoleName = currRoleName;
	    }

	    public AssetsTransHeaderDTO() {
	        super();
	        this.canceledDate = new SimpleCalendar();
	        this.approvedDate = new SimpleCalendar();
	        this.transDate = new SimpleCalendar();
	        this.printType = AssetsWebAttributes.PRINT_TRANS_OUT;
	        this.lossesDate = new SimpleCalendar();
	        setCurrCalendar("lossesDate");
	        setPrimaryKeyName("transId");
	    }

	    /**
	     * ���ܣ�����servletConfig���ö���
	     *
	     * @param servletConfig ServletConfigDTO
	     */
	    public void setServletConfig(ServletConfigDTO servletConfig) {
	        this.servletConfig = servletConfig;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ �������к�
	     *
	     * @param transId String
	     */
	    public void setTransId(String transId) {
	        this.transId = transId;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ���ݱ��
	     *
	     * @param transNo String
	     */
	    public void setTransNo(String transNo) {
	        this.transNo = transNo;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��������
	     *
	     * @param transType String
	     */
	    public void setTransType(String transType) {
	        this.transType = transType;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ����״̬
	     *
	     * @param transStatus String
	     */
	    public void setTransStatus(String transStatus) {
	        this.transStatus = transStatus;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ԭOU��֯ID
	     *
	     * @param fromOrganizationId String
	     */
	    public void setFromOrganizationId(int fromOrganizationId) {
	        this.fromOrganizationId = fromOrganizationId;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ Ŀ��OU��֯ID
	     *
	     * @param toOrganizationId String
	     */
	    public void setToOrganizationId(int toOrganizationId) {
	        this.toOrganizationId = toOrganizationId;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ԭ���
	     *
	     * @param fromDept String
	     */
	    public void setFromDept(String fromDept) {
	        this.fromDept = fromDept;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ Ŀ�����
	     *
	     * @param toDept String
	     */
	    public void setToDept(String toDept) {
	        this.toDept = toDept;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ԭ�ص�
	     *
	     * @param fromObjectNo String
	     */
	    public void setFromObjectNo(String fromObjectNo) {
	        this.fromObjectNo = fromObjectNo;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ Ŀ��ص�
	     *
	     * @param toObjectNo String
	     */
	    public void setToObjectNo(String toObjectNo) {
	        this.toObjectNo = toObjectNo;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ԭ������,��employeeNumber
	     *
	     * @param fromPerson String
	     */
	    public void setFromPerson(String fromPerson) {
	        this.fromPerson = fromPerson;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ Ŀ�걣����,��employeeNumber
	     *
	     * @param toPerson String
	     */
	    public void setToPerson(String toPerson) {
	        this.toPerson = toPerson;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ���ݴ���ԭ��
	     *
	     * @param createdReason String
	     */
	    public void setCreatedReason(String createdReason) {
	        this.createdReason = createdReason;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��������
	     *
	     * @param canceledDate SimpleCalendar
	     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	     */
	    public void setCanceledDate(String canceledDate) throws CalendarException {
	        this.canceledDate.setCalendarValue(canceledDate);
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ����ԭ��
	     *
	     * @param canceledReason String
	     */
	    public void setCanceledReason(String canceledReason) {
	        this.canceledReason = canceledReason;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��׼����
	     *
	     * @param approvedDate SimpleCalendar
	     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	     */
	    public void setApprovedDate(String approvedDate) throws CalendarException {
	        this.approvedDate.setCalendarValue(approvedDate);
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��׼��
	     *
	     * @param approvedBy String
	     */
	    public void setApprovedBy(int approvedBy) {
	        this.approvedBy = approvedBy;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ������
	     *
	     * @param receivedUser String
	     */
	    public void setReceivedUser(int receivedUser) {
	        this.receivedUser = receivedUser;
	    }

	    public void setCreated(String created) {
	        this.created = created;
	    }

	    public void setFromCompanyName(String fromCompanyName) {
	        this.fromCompanyName = fromCompanyName;
	    }

	    public void setFromDeptName(String fromDeptName) {
	        this.fromDeptName = fromDeptName;
	    }

	    public void setToCompanyName(String toCompanyName) {
	        this.toCompanyName = toCompanyName;
	    }

	    public void setToDeptName(String toDeptName) {
	        this.toDeptName = toDeptName;
	    }

	    public void setToObjectName(String toObjectName) {
	        this.toObjectName = toObjectName;
	    }

	    public void setToUserName(String toUserName) {
	        this.toUserName = toUserName;
	    }

	    public void setTransStatusDesc(String transStatusDesc) {
	        this.transStatusDesc = transStatusDesc;
	    }

	    public void setTransTypeValue(String transTypeValue) {
	        this.transTypeValue = transTypeValue;
	    }

	    public void setFromDeptOption(String fromDeptOption) {
	        this.fromDeptOption = fromDeptOption;
	    }

	    public void setToDeptOption(String toDeptOption) {
	        this.toDeptOption = toDeptOption;
	    }

	    public void setFromGroup(int fromGroup) {
	        this.fromGroup = fromGroup;
	    }

	    public void setFromGroupName(String fromGroupName) {
	        this.fromGroupName = fromGroupName;
	    }

	    public void setFromGroupOption(String fromGroupOption) {
	        this.fromGroupOption = fromGroupOption;
	    }

	    public void setToGroup(String toGroup) {
	        this.toGroup = toGroup;
	    }

	    public void setToGroupName(String toGroupName) {
	        this.toGroupName = toGroupName;
	    }

	    public void setToGroupOption(String toGroupOption) {
	        this.toGroupOption = toGroupOption;
	    }

	    public void setApprovedUser(String approvedUser) {
	        this.approvedUser = approvedUser;
	    }

	    public void setReceivedUserName(String receivedUserName) {
	        this.receivedUserName = receivedUserName;
	    }

	    public void setStatusOption(String statusOption) {
	        this.statusOption = statusOption;
	    }

	    public void setFlow2End(boolean flow2End) {
	        this.flow2End = flow2End;
	    }

	    public void setTransferType(String transferType) {
	        this.transferType = transferType;
	    }

	    public void setTransferTypeOption(String transferTypeOption) {
	        this.transferTypeOption = transferTypeOption;
	    }

	    public void setPrintType(String printType) {
	        this.printType = printType;
	    }

	    public void setCanReceive(boolean canReceive) {
	        this.canReceive = canReceive;
	    }

	    public void setBookTypeCode(String bookTypeCode) {
	        this.bookTypeCode = bookTypeCode;
	    }

	    public void setBookTypeName(String bookTypeName) {
	        this.bookTypeName = bookTypeName;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public void setToCompanyOption(String toCompanyOption) {
	        this.toCompanyOption = toCompanyOption;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public void setUserDeptName(String userDeptName) {
	        this.userDeptName = userDeptName;
	    }

	    public void setFaContentCode(String faContentCode) {
	        this.faContentCode = faContentCode;
	    }

	    public void setFaContentOption(String faContentOption) {
	        this.faContentOption = faContentOption;
	    }

	    public void setFaContentName(String faContentName) {
	        this.faContentName = faContentName;
	    }

	    public void setFromCompanyOption(String fromCompanyOption) {
	        this.fromCompanyOption = fromCompanyOption;
	    }

	    public void setBookTypeOption(String bookTypeOption) {
	        this.bookTypeOption = bookTypeOption;
	    }

	    public void setHiddenRight(String hiddenRight) {
	        this.hiddenRight = hiddenRight;
	    }

	    public void setSectionRight(String sectionRight) {
	        this.sectionRight = sectionRight;
	    }

	    public void setAttribute1(String attribute1) {
	        this.attribute1 = attribute1;
	    }

	    public void setAttribute2(String attribute2) {
	        this.attribute2 = attribute2;
	    }

	    public void setAttribute3(String attribute3) {
	        this.attribute3 = attribute3;
	    }

	    public void setAttribute4(String attribute4) {
	        this.attribute4 = attribute4;
	    }

	    public void setAttribute5(String attribute5) {
	        this.attribute5 = attribute5;
	    }

	    public void setGroupProp(String groupProp) {
	        this.groupProp = groupProp;
	    }

	    public void setLossesDate(String lossesDate) throws CalendarException {
	        this.lossesDate.setCalendarValue(lossesDate);
	    }

	    public void setLossesName(String lossesName) {
	        this.lossesName = lossesName;
	    }

	    /**
	     * ���ܣ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��������
	     *
	     * @param transDate SimpleCalendar
	     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	     */
	    public void setTransDate(String transDate) throws CalendarException {
	        this.transDate.setCalendarValue(transDate);
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ �������к�
	     *
	     * @return String
	     */
	    public String getTransId() {
	        return this.transId;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ���ݱ��
	     *
	     * @return String
	     */
	    public String getTransNo() {
	        return this.transNo;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��������
	     *
	     * @return String
	     */
	    public String getTransType() {
	        return this.transType;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ����״̬
	     *
	     * @return String
	     */
	    public String getTransStatus() {
	        return this.transStatus;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ԭOU��֯ID
	     *
	     * @return String
	     */
	    public int getFromOrganizationId() {
	        return this.fromOrganizationId;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ Ŀ��OU��֯ID
	     *
	     * @return String
	     */
	    public int getToOrganizationId() {
	        return this.toOrganizationId;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ԭ���
	     *
	     * @return String
	     */
	    public String getFromDept() {
	        return this.fromDept;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ Ŀ�����
	     *
	     * @return String
	     */
	    public String getToDept() {
	        return this.toDept;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ԭ�ص�
	     *
	     * @return String
	     */
	    public String getFromObjectNo() {
	        return this.fromObjectNo;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ Ŀ��ص�
	     *
	     * @return String
	     */
	    public String getToObjectNo() {
	        return this.toObjectNo;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ԭ������,��employeeNumber
	     *
	     * @return String
	     */
	    public String getFromPerson() {
	        return this.fromPerson;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ Ŀ�걣����,��employeeNumber
	     *
	     * @return String
	     */
	    public String getToPerson() {
	        return this.toPerson;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ���ݴ���ԭ��
	     *
	     * @return String
	     */
	    public String getCreatedReason() {
	        return this.createdReason;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��������
	     *
	     * @return SimpleCalendar
	     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	     */
	    public SimpleCalendar getCanceledDate() throws CalendarException {
	        canceledDate.setCalPattern(getCalPattern());
	        return this.canceledDate;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ����ԭ��
	     *
	     * @return String
	     */
	    public String getCanceledReason() {
	        return this.canceledReason;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��׼����
	     *
	     * @return SimpleCalendar
	     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	     */
	    public SimpleCalendar getApprovedDate() throws CalendarException {
	        approvedDate.setCalPattern(getCalPattern());
	        return this.approvedDate;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��׼��
	     *
	     * @return String
	     */
	    public int getApprovedBy() {
	        return this.approvedBy;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ������
	     *
	     * @return String
	     */
	    public int getReceivedUser() {
	        return this.receivedUser;
	    }

	    /**
	     * ���ܣ���ȡ�ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ������ ��������
	     *
	     * @return SimpleCalendar
	     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	     */
	    public SimpleCalendar getTransDate() throws CalendarException {
	        transDate.setCalPattern(getCalPattern());
	        return this.transDate;
	    }

	    public String getCreated() {
	        return created;
	    }

	    public String getFromCompanyName() {
	        return fromCompanyName;
	    }

	    public String getFromDeptName() {
	        return fromDeptName;
	    }

	    public String getToCompanyName() {
	        return toCompanyName;
	    }

	    public String getToDeptName() {
	        return toDeptName;
	    }

	    public String getToObjectName() {
	        return toObjectName;
	    }

	    public String getToUserName() {
	        return toUserName;
	    }

	    public String getTransStatusDesc() {
	        return transStatusDesc;
	    }

	    /**
	     * ���ܣ���ȡ��������
	     *
	     * @return String
	     */
	    public String getTransTypeValue() {
	        String strValue = transTypeValue;
	        if (transType.equals(AssetsDictConstant.ASS_RED)) {
	            String[] transOpts = AssetsDictConstant.TRANS_OPT_VALUES;
	            for (int i = 0; i < transOpts.length; i++) {
	                if (transferType.equals(transOpts[i])) {
	                    strValue = AssetsDictConstant.TRANS_OPT_LABELS[i];
	                    break;
	                }
	            }
	        }
	        return strValue;
	    }

	    public String getFromDeptOption() {
	        return fromDeptOption;
	    }

	    public String getToDeptOption() {
	        return toDeptOption;
	    }

	    public int getFromGroup() {
	        return fromGroup;
	    }

	    public String getFromGroupName() {
	        return fromGroupName;
	    }

	    public String getFromGroupOption() {
	        return fromGroupOption;
	    }

	    public String getToGroup() {
	        return toGroup;
	    }

	    public String getToGroupName() {
	        return toGroupName;
	    }

	    public String getToGroupOption() {
	        return toGroupOption;
	    }

	    public String getApprovedUser() {
	        return approvedUser;
	    }

	    public String getReceivedUserName() {
	        return receivedUserName;
	    }

	    public String getStatusOption() {
	        return statusOption;
	    }

	    public boolean isFlow2End() {
	        return flow2End;
	    }

	    public String getTransferType() {
	        return transferType;
	    }

	    public String getTransferTypeOption() {
	        return transferTypeOption;
	    }

	    public String getPrintType() {
	        return printType;
	    }

	    public String getBookTypeCode() {
	        return bookTypeCode;
	    }

	    public String getBookTypeName() {
	        return bookTypeName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getToCompanyOption() {
	        return toCompanyOption;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public String getUserDeptName() {
	        return userDeptName;
	    }

	    public String getFaContentCode() {
	        return faContentCode;
	    }

	    public String getFaContentOption() {
	        return faContentOption;
	    }

	    public String getFaContentName() {
	        return faContentName;
	    }

	    public String getFromCompanyOption() {
	        return fromCompanyOption;
	    }

	    public ServletConfigDTO getServletConfig() {
	        return servletConfig;
	    }

	    public String getBookTypeOption() {
	        return bookTypeOption;
	    }

	    public String getHiddenRight() {
	        return hiddenRight;
	    }

	    public String getSectionRight() {
	        return sectionRight;
	    }

	    public String getAttribute1() {
	        return attribute1;
	    }

	    public String getAttribute2() {
	        return attribute2;
	    }

	    public String getAttribute3() {
	        return attribute3;
	    }

	    public String getAttribute4() {
	        return attribute4;
	    }

	    public String getAttribute5() {
	        return attribute5;
	    }

	    public String getGroupProp() {
	        return groupProp;
	    }

	    public SimpleCalendar getLossesDate() throws CalendarException {
	        lossesDate.setCalPattern(getCalPattern());
	        return lossesDate;
	    }

	    public String getLossesName() {
	        return lossesName;
	    }

	    public boolean canReceive() {
	        return canReceive;
	    }

	    public void setGroupPid(String groupPid) {
	        this.groupPid = groupPid;
	    }

	    public void setProducedNewBarcode(String producedNewBarcode) {
	        this.producedNewBarcode = producedNewBarcode;
	    }

	    public void setControlPrivi(String controlPrivi) {
	        this.controlPrivi = controlPrivi;
	    }

	    public String getGroupPid() {
	        return this.groupPid;
	    }

	    public String getProducedNewBarcode() {
	        return producedNewBarcode;
	    }

	    public String getControlPrivi() {
	        return controlPrivi;
	    }

	    public List<AssetsTransLineDTO> getLines() {
	        return lines;
	    }

	    public void setLines(List<AssetsTransLineDTO> lines) {
	        this.lines = lines;
	    }

	    /**
	     * ���ܣ���ȡ��������
	     *
	     * @return String
	     */
	    public String getProcdureName() {
	        String procedureName = "";
	        String provinceCode = servletConfig.getProvinceCode();	       
	        
	        if ("ASS-RENT".equals(transType)) { //����
	        	procedureName = AssetsDictConstant.PROCEDURE_NAME_RENT;
            } else if ("ASS-DONATE".equals(transType)) { //����
            	procedureName = AssetsDictConstant.PROCEDURE_NAME_DONA;
        	} else if ("ASS-REPAIRRETURN".equals(transType)) { //���޷���
        		procedureName = AssetsDictConstant.PROCEDURE_NAME_REPAIRRETURN;
        	} else if ("ASS-REPAIR".equals(transType)) { //����
        		procedureName = AssetsDictConstant.PROCEDURE_NAME_REPAIR;
        	} else if ("ASS-SELL".equals(transType)) { //����
        		procedureName = AssetsDictConstant.PROCEDURE_NAME_SELL;
        	} else if ("ASS-WASTEFORECAST".equals(transType)) { //Ԥ����
        		procedureName = AssetsDictConstant.PROCEDURE_NAME_WASTEFORECAST;
        	} else if ("ASS-EXCHANGE".equals(transType)) { //�û�
        		procedureName = AssetsDictConstant.PROCEDURE_NAME_REPLACEMENT;
        	} 
	        return procedureName;
	    }

	    /**
	     * ���ܣ���ȡ��������
	     *
	     * @return String
	     */
	    public String getProcdureNameIncludeTd(String isTd) {
	        String procedureName = "";
	        String provinceCode = servletConfig.getProvinceCode();
	        if (transType.equals(AssetsDictConstant.ASS_RED)) { //����
	            if (!provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SN)) {
	                if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) {
	                    procedureName = AssetsDictConstant.PROCEDURE_TRANS_INN_DEPT;
	                } else if (transferType
	                        .equals(AssetsDictConstant.TRANS_BTW_DEPT)) {
	                    procedureName = AssetsDictConstant.PROCEDURE_TRANS_BTW_DEPT;
	                } else if (transferType
	                        .equals(AssetsDictConstant.TRANS_BTW_COMP)) {
	                    procedureName = AssetsDictConstant.PROCEDURE_TRANS_BTW_COMP;
	                }
	            } else {
	                if ("Y".equalsIgnoreCase(isTd)) {
	                    if (!transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) {
	                        procedureName = AssetsDictConstant.PROCEDURE_NAME_TRANSFER_TD;
	                    } else {
	                        procedureName = AssetsDictConstant.PROCEDURE_TRANS_BTW_COMP_TD;
	                    }
	                } else {
	                    if (!transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) {
	                        procedureName = AssetsDictConstant.PROCEDURE_NAME_TRANSFER;
	                    } else {
	                        procedureName = AssetsDictConstant.PROCEDURE_TRANS_BTW_COMP;
	                    }
	                }

	            }
	        } else if (transType.equals(AssetsDictConstant.ASS_DIS)) { //����
	            if ("Y".equalsIgnoreCase(isTd)) {
	                procedureName = AssetsDictConstant.PROCEDURE_NAME_DISCARD_TD;
	            } else {
	                procedureName = AssetsDictConstant.PROCEDURE_NAME_DISCARD;
	            }
	        } else if (transType.equals(AssetsDictConstant.ASS_CLR)) { //����
	            procedureName = AssetsDictConstant.PROCEDURE_NAME_CLEAR;
	        } else if (transType.equals(AssetsDictConstant.ASS_CHK)) { //�̵�
	            procedureName = AssetsDictConstant.PROCEDURE_NAME_CHECK;
	        } else if (transType.equals(AssetsDictConstant.ASS_FREE)) { //����
	            procedureName = AssetsDictConstant.PROCEDURE_NAME_FREE;
	        } else if (transType.equals(AssetsDictConstant.ASS_SUB)) { //��ֵ
	            procedureName = AssetsDictConstant.PROCEDURE_NAME_SUB;
	        } else if (transType.equals(AssetsDictConstant.ASS_SHARE)) { //����
	            procedureName = AssetsDictConstant.PROCEDURE_NAME_SHARE;
	        } else if (transType.equals(AssetsDictConstant.ASS_SELL)) { //����
	            procedureName = AssetsDictConstant.PROCEDURE_NAME_SELL;
	        } else if (transType.equals(AssetsDictConstant.ASS_RENT)) { //����
	            procedureName = AssetsDictConstant.PROCEDURE_NAME_RENT;
	        } else if (transType.equals(AssetsDictConstant.ASS_DONA)) { //����
	            procedureName = AssetsDictConstant.PROCEDURE_NAME_DONA;
	        }
	        return procedureName;
	    }

		public String getFinanceProp() {
			return financeProp;
		}

		public void setFinanceProp(String financeProp) {
			this.financeProp = financeProp;
		}

		public String getFinancePropName() {
			return financePropName;
		}

		public void setFinancePropName(String financePropName) {
			this.financePropName = financePropName;
		}

		public String getFinancePropOption() {
			return financePropOption;
		}

		public void setFinancePropOption(String financePropOption) {
			this.financePropOption = financePropOption;
		}
}
