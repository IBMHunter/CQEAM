package com.sino.ams.newasset.dto;

import java.util.ArrayList;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: �ʲ��̵�ͷ��(EAM) AmsAssetsCheckHeader</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsCheckHeaderDTO extends AmsAssetsCheckBatchDTO {

	private String headerId = "";
	private String transNo = "";
	private SimpleCalendar downloadDate = null;
	private int downloadBy;
	private String downloadUser = "";
	private SimpleCalendar scanoverDate = null;
	private int scanoverBy;
	private String scanoverUser = "";
	private SimpleCalendar uploadDate = null;
	private int uploadBy;
	private String uploadUser = "";
	private SimpleCalendar archivedDate = null;
	private int archivedBy = -1;
	private String archivedUser = "";
	private String differenceReason = "";
	private String orderStatus = "";
	private String statusName = "";
	private String responsibleUser = "";
	private int implementBy = -1;
	private String implementUser = "";
	private int implementDays = 0;
	private SimpleCalendar startTime = null;

	private String objectCode = "";
	private String objectName = "";
	private String objectLocation = "";
	private String  checkLocation = "";
	private SimpleCalendar deadlineDate = null;
	private String objectCategory = "";
	private String objectCategoryName = "";
	private String objectCategoryOpt = "";
	private String companyOpt = "";
	private String exportType = "";
	private String maintainCompany = "";
	private String maintainComapnyOpt = "";
	private String costCode = "";
	private String costName = "";
	private String costCenterOpt = "";
	private String deptOpt = "";
	private String groupOpt = "";
	private boolean hasPreviousOrder = false;
	private String tagNumber = "";
	private String assetsDescription = "";
	private String deptCode = "";
	private String responsibilityUser = "";
	private String averageChkTime = "";
	private String disabled = "";//�Ƿ��ֹ�ɱ����ĵ�ѡ��
    private String fromBarcode = "";
    private String toBarcode = "";
    private String threshold = "0"; //��ֵ
    private String deptCategoryValues = "";
    private ArrayList deptCategoryCodes = new ArrayList(0);

    private String newLocation = "";
    private String checkTpye = "";
    
    private String qryBarcode = "";//�����̵㹤����ѯ�����ѯ����
    
    public String getQryBarcode() {
        return qryBarcode;
    }

    public void setQryBarcode(String qryBarcode) {
        this.qryBarcode = qryBarcode;
    }

    public String getCheckTpye() {
        return checkTpye;
    }

    public void setCheckTpye(String checkTpye) {
        this.checkTpye = checkTpye;
    }


    public String getDeptCategoryValues() {
        return deptCategoryValues;
    }

    public void setDeptCategoryValues(String deptCategoryValues) {
        this.deptCategoryValues = deptCategoryValues;
    }

    public ArrayList getDeptCategoryCodes() {
        return deptCategoryCodes;
    }

    public void setDeptCategoryCodes(ArrayList deptCategoryCodes) {
        this.deptCategoryCodes = deptCategoryCodes;
    }

    public String getFromBarcode() {
        return fromBarcode;
    }

    public void setFromBarcode(String fromBarcode) {
        this.fromBarcode = fromBarcode;
    }

    public String getToBarcode() {
        return toBarcode;
    }

    public void setToBarcode(String toBarcode) {
        this.toBarcode = toBarcode;
    }

    public AmsAssetsCheckHeaderDTO() {
		super();
		this.downloadDate = new SimpleCalendar();
		this.scanoverDate = new SimpleCalendar();
		this.uploadDate = new SimpleCalendar();
		this.archivedDate = new SimpleCalendar();
		this.startTime = new SimpleCalendar();
		this.deadlineDate = new SimpleCalendar();
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� �̵㵥���к�
	 * @param headerId String
	 */
	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}


	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� ���ݺ�
	 * @param transNo String
	 */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� ��������
	 * @param downloadDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDownloadDate(String downloadDate) throws CalendarException {
		this.downloadDate.setCalendarValue(downloadDate);
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� ��ֹ����
	 * @param deadlineDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDeadlineDate(String deadlineDate) throws CalendarException {
		this.deadlineDate.setCalendarValue(deadlineDate);
	}


	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� ������
	 * @param downloadBy String
	 */
	public void setDownloadBy(int downloadBy) {
		this.downloadBy = downloadBy;
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� ɨ���������
	 * @param scanoverDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setScanoverDate(String scanoverDate) throws CalendarException {
		this.scanoverDate.setCalendarValue(scanoverDate);
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� ɨ����
	 * @param scanoverBy String
	 */
	public void setScanoverBy(int scanoverBy) {
		this.scanoverBy = scanoverBy;
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� �ϴ�����/ʵ���������
	 * @param uploadDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setUploadDate(String uploadDate) throws CalendarException {
		this.uploadDate.setCalendarValue(uploadDate);
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� �ϴ���
	 * @param uploadBy String
	 */
	public void setUploadBy(int uploadBy) {
		this.uploadBy = uploadBy;
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� �鵵����
	 * @param archivedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setArchivedDate(String archivedDate) throws CalendarException {
		this.archivedDate.setCalendarValue(archivedDate);
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� �鵵��
	 * @param archivedBy String
	 */
	public void setArchivedBy(int archivedBy) {
		this.archivedBy = archivedBy;
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� ����ԭ��
	 * @param differenceReason String
	 */
	public void setDifferenceReason(String differenceReason) {
		this.differenceReason = differenceReason;
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� �̵㵥״̬(10:������11:���·���12:�����أ�13:����ɣ�14:�Ѻ�ʵ��15:�ѳ���)
	 * @param orderStatus String
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * ���ܣ������ʲ��̵�ͷ��(EAM)���� ������
	 * @param responsibleUser String
	 */
	public void setResponsibleUser(String responsibleUser) {
		this.responsibleUser = responsibleUser;
	}

	/**
	 * ���ܣ������ʲ��ص��(EAM)���� �ص����
	 *
	 * @param objectCode String
	 */
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	/**
	 * ���ܣ������ʲ��ص��(EAM)���� �ص�����
	 *
	 * @param objectName String
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * ���ܣ������ʲ��ص��(EAM)���� ���ڵص�
	 *
	 * @param objectLocation String
	 */
	public void setObjectLocation(String objectLocation) {
		this.objectLocation = objectLocation;
	}

	public void setImplementBy(int implementBy) {
		this.implementBy = implementBy;
	}

	public void setImplementUser(String implementUser) {
		this.implementUser = implementUser;
	}

	public void setImplementDays(int implementDays) {
		this.implementDays = implementDays;
	}

	public void setCheckLocation(String  checkLocation) {
		this.checkLocation = checkLocation;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setArchivedUser(String archivedUser) {
		this.archivedUser = archivedUser;
	}

	public void setDownloadUser(String downloadUser) {
		this.downloadUser = downloadUser;
	}

	public void setScanoverUser(String scanoverUser) {
		this.scanoverUser = scanoverUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public void setObjectCategory(String objectCategory) {
		this.objectCategory = objectCategory;
	}

	public void setObjectCategoryOpt(String objectCategoryOpt) {
		this.objectCategoryOpt = objectCategoryOpt;
	}

	public void setCompanyOpt(String companyOpt) {
		this.companyOpt = companyOpt;
	}

	public void setStartTime(String startTime) throws CalendarException {
		this.startTime.setCalendarValue(startTime);
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� �̵㵥���к�
	 * @return String
	 */
	public String getHeaderId() {
		return this.headerId;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� ���ݺ�
	 * @return String
	 */
	public String getTransNo() {
		return this.transNo;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDownloadDate() throws CalendarException {
		downloadDate.setCalPattern(getCalPattern());
		return this.downloadDate;
	}


	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� ��ֹ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDeadlineDate() throws CalendarException {
		deadlineDate.setCalPattern(getCalPattern());
		return this.deadlineDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� ������
	 * @return String
	 */
	public int getDownloadBy() {
		return this.downloadBy;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� ɨ���������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getScanoverDate() throws CalendarException {
		scanoverDate.setCalPattern(getCalPattern());
		return this.scanoverDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� ɨ����
	 * @return String
	 */
	public int getScanoverBy() {
		return this.scanoverBy;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� �ϴ�����/ʵ���������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getUploadDate() throws CalendarException {
		uploadDate.setCalPattern(getCalPattern());
		return this.uploadDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� �ϴ���
	 * @return String
	 */
	public int getUploadBy() {
		return this.uploadBy;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� �鵵����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getArchivedDate() throws CalendarException {
		archivedDate.setCalPattern(getCalPattern());
		return this.archivedDate;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� �鵵��
	 * @return String
	 */
	public int getArchivedBy() {
		return this.archivedBy;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� ����ԭ��
	 * @return String
	 */
	public String getDifferenceReason() {
		return this.differenceReason;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� �̵㵥״̬(10:������11:���·���12:�����أ�13:����ɣ�14:�Ѻ�ʵ��15:�ѳ���)
	 * @return String
	 */
	public String getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 * ���ܣ���ȡ�ʲ��̵�ͷ��(EAM)���� ������
	 * @return String
	 */
	public String getResponsibleUser() {
		return this.responsibleUser;
	}

	/**
	 * ���ܣ���ȡ�ʲ��ص��(EAM)���� �ص����
	 *
	 * @return String
	 */
	public String getObjectCode() {
		return this.objectCode;
	}

	/**
	 * ���ܣ���ȡ�ʲ��ص��(EAM)���� �ص�����
	 *
	 * @return String
	 */
	public String getObjectName() {
		return this.objectName;
	}

	/**
	 * ���ܣ���ȡ�ʲ��ص��(EAM)���� ���ڵص�
	 *
	 * @return String
	 */
	public String getObjectLocation() {
		return this.objectLocation;
	}

	public int getImplementBy() {
		return this.implementBy;
	}

	public String getImplementUser() {
		return this.implementUser;
	}

	public int getImplementDays() {
		return this.implementDays;
	}

	public SimpleCalendar getStartTime() throws CalendarException {
		this.startTime.setCalPattern(getCalPattern());
		return this.startTime;
	}

	public String  getCheckLocation() {
		return this.checkLocation;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public String getArchivedUser() {
		return this.archivedUser;
	}


	public String getDownloadUser() {
		return this.downloadUser;
	}

	public String getScanoverUser() {
		return this.scanoverUser;
	}

	public String getUploadUser() {
		return this.uploadUser;
	}

	public String getObjectCategory() {
		return this.objectCategory;
	}

	public String getObjectCategoryOpt() {
		return this.objectCategoryOpt;
	}

	public String getCompanyOpt() {
		return this.companyOpt;
	}

	public String getObjectCategoryName() {
		return this.objectCategoryName;
	}

	public String getExportType() {
		return this.exportType;
	}

	public String getMaintainCompany() {
		return this.maintainCompany;
	}

	public String getMaintainComapnyOpt() {
		return this.maintainComapnyOpt;
	}

	public String getGroupOpt() {
		return groupOpt;
	}

	public String getCostCode() {
		return costCode;
	}

	public String getCostCenterOpt() {
		return costCenterOpt;
	}

	public String getDeptOpt() {
		return deptOpt;
	}

	public String getCostName() {
		return costName;
	}

	public String getTagNumber() {
		return this.tagNumber;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public String getAssetsDescription() {
		return assetsDescription;
	}

	public String getResponsibilityUser() {
		return responsibilityUser;
	}

	public String getAverageChkTime() {
		return averageChkTime;
	}

	public String getDisabled() {
		return disabled;
	}

	public boolean hasPreviousOrder() {
		return hasPreviousOrder;
	}

	public void setObjectCategoryName(String objectCategoryName) {
		this.objectCategoryName = objectCategoryName;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public void setMaintainCompany(String maintainCompany) {
		this.maintainCompany = maintainCompany;
	}

	public void setMaintainComapnyOpt(String maintainComapnyOpt) {
		this.maintainComapnyOpt = maintainComapnyOpt;
	}

	public void setGroupOpt(String groupOpt) {
		this.groupOpt = groupOpt;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public void setCostCenterOpt(String costCenterOpt) {

		this.costCenterOpt = costCenterOpt;
	}

	public void setDeptOpt(String deptOpt) {
		this.deptOpt = deptOpt;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public void setHasPreviousOrder(boolean hasPreviousOrder) {
		this.hasPreviousOrder = hasPreviousOrder;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public void setAssetsDescription(String assetsDescription) {
		this.assetsDescription = assetsDescription;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public void setResponsibilityUser(String responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public void setAverageChkTime(String averageChkTime) {
		this.averageChkTime = averageChkTime;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }
}
