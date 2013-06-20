package com.sino.ams.print.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: �����ӡ��Ϣ�� AmsBarcodePrint</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsBarcodePrintDTO extends CheckBoxDTO{

	private String id = "";
	private String batchNo = "";
	private String tagType = "";
	private int tagNumber;
	private String applyReason = "";
	private int applyUser;
	private SimpleCalendar applyDate = null;
	private int approveUser;
	private SimpleCalendar approveDate = null;
	private String approveResult = "";
	private int printUser;
	private SimpleCalendar printDate = null;
	private String remark = "";
	private int status;
    private String first = "";
    private String applyName ="";
    private String approveName ="";
    private String printName ="";
    private String applyGroup = "";
    private String tagColor = "";

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

    public String getApplyGroup() {
        return applyGroup;
    }

    public void setApplyGroup(String applyGroup) {
        this.applyGroup = applyGroup;
    }

    private SimpleCalendar fromDate = null;
    private SimpleCalendar toDate = null;
    private SimpleCalendar endDate = null;

//
    public SimpleCalendar getEndDate() throws CalendarException {
	    endDate.setCalPattern(getCalPattern());
        return this.endDate;
    }

    public void setEndDate(String endDate)throws CalendarException{
		this.endDate.setCalendarValue(endDate);
	}

    public SimpleCalendar getToDate() throws CalendarException {
		toDate.setCalPattern(getCalPattern());
        return this.toDate;
    }

    public void setToDate(String toDate)throws CalendarException{
		this.toDate.setCalendarValue(toDate);
	}

    public SimpleCalendar getFromDate() throws CalendarException {
		fromDate.setCalPattern(getCalPattern());
        return this.fromDate;
    }

    public void setFromDate(String fromDate)throws CalendarException{
		this.fromDate.setCalendarValue(fromDate);
	}
    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public AmsBarcodePrintDTO() {
		super();
		this.applyDate = new SimpleCalendar();
		this.approveDate = new SimpleCalendar();
		this.printDate = new SimpleCalendar();
        this.fromDate = new SimpleCalendar();
		this.toDate = new SimpleCalendar();
		this.endDate = new SimpleCalendar();
    }

	/**
	 * ���ܣ����������ӡ��Ϣ������ ����
	 * @param id String
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ���ݺ�
	 * @param batchNo String
	 */
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ��ǩ����
	 * @param tagType String
	 */
	public void setTagType(String tagType){
		this.tagType = tagType;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ��ǩ����
	 * @param tagNumber String
	 */
	public void setTagNumber(int tagNumber){
		this.tagNumber = tagNumber;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ����ԭ��
	 * @param applyReason String
	 */
	public void setApplyReason(String applyReason){
		this.applyReason = applyReason;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ������
	 * @param applyUser String
	 */
	public void setApplyUser(int applyUser){
		this.applyUser = applyUser;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ��������
	 * @param applyDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setApplyDate(String applyDate) throws CalendarException{
		this.applyDate.setCalendarValue(applyDate);
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ������
	 * @param approveUser String
	 */
	public void setApproveUser(int approveUser){
		this.approveUser = approveUser;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ��������
	 * @param approveDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setApproveDate(String approveDate) throws CalendarException{
		this.approveDate.setCalendarValue(approveDate);
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ �������
	 * @param approveResult String
	 */
	public void setApproveResult(String approveResult){
		this.approveResult = approveResult;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ��ӡ��
	 * @param printUser String
	 */
	public void setPrintUser(int printUser){
		this.printUser = printUser;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ��ӡ����
	 * @param printDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setPrintDate(String printDate) throws CalendarException{
		this.printDate.setCalendarValue(printDate);
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ����������ӡ��Ϣ������ ����״̬(0-������;1-�ѳ���;2-�����)
	 * @param status String
	 */
	public void setStatus(int status){
		this.status = status;
	}


	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ����
	 * @return String
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ���ݺ�
	 * @return String
	 */
	public String getBatchNo() {
		return this.batchNo;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ��ǩ����
	 * @return String
	 */
	public String getTagType() {
		return this.tagType;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ��ǩ����
	 * @return String
	 */
	public int getTagNumber() {
		return this.tagNumber;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ����ԭ��
	 * @return String
	 */
	public String getApplyReason() {
		return this.applyReason;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ������
	 * @return String
	 */
	public int getApplyUser() {
		return this.applyUser;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getApplyDate() throws CalendarException {
		applyDate.setCalPattern(getCalPattern());
		return this.applyDate;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ������
	 * @return String
	 */
	public int getApproveUser() {
		return this.approveUser;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getApproveDate() throws CalendarException {
		approveDate.setCalPattern(getCalPattern());
		return this.approveDate;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ �������
	 * @return String
	 */
	public String getApproveResult() {
		return this.approveResult;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ��ӡ��
	 * @return String
	 */
	public int getPrintUser() {
		return this.printUser;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ��ӡ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getPrintDate() throws CalendarException {
		printDate.setCalPattern(getCalPattern());
		return this.printDate;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ�����ӡ��Ϣ������ ����״̬(0-������;1-�ѳ���;2-�����)
	 * @return String
	 */
	public int getStatus() {
		return this.status;
	}

}