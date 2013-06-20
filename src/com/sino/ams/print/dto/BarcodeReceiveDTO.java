package com.sino.ams.print.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

public class BarcodeReceiveDTO  extends CheckBoxDTO {
	
	private String barcodeReceiveId = "";

	//��ʼ��ǩ
	private String fromBarcode = "";	
	
	//������ǩ
	private String toBarcode = "";
	
	//��ǩ����
	private int barcodeQty ;
	
	//���ò��ű��
	private String receiveDept = "";
	
	private String receiveDeptName = "";
	
	//�����˱��
	private int receiveUser;
	
	//����������
	private String receiveUserName = "";
	
	//��������
	private SimpleCalendar receiveDate = null;
	
	//��ӡ�˱��
	private int printUser;
	
	//��ӡ������
	private String printUserName = "";
	
	//��ӡ����		
	private SimpleCalendar printDate = null;
	
	//��������
	private int organizationId;
	
	//������������
	private String company = "";
	
	//����ԭ��
	private String receiveRemark = "";
	
	//�û�����
	private String userName = "";
	
	//�û���¼����
	private String loginName = "";
	
	//Ա�����
	private String employeeNumber = "";
	
	//��ӡ��ǩ����
	private int barcodePrintNum;
	
	private SimpleCalendar printDateBegin = null;
	
	private SimpleCalendar printDateEnd = null;
	
	private SimpleCalendar receiveDateBegin = null;
	
	private SimpleCalendar receiveDateEnd = null;
	
	//��ǩ
	private String barcode = "";
	
	//OU
	private String organization = "";

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setReceiveDate(SimpleCalendar receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setPrintDate(SimpleCalendar printDate) {
		this.printDate = printDate;
	}

	public int getBarcodeQty() {
		return barcodeQty;
	}

	public void setBarcodeQty(int barcodeQty) {
		this.barcodeQty = barcodeQty;
	}

	public String getFromBarcode() {
		return fromBarcode;
	}

	public void setFromBarcode(String fromBarcode) {
		this.fromBarcode = fromBarcode;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public SimpleCalendar getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) throws CalendarException {
		this.printDate.setCalendarValue(printDate);
	}

	public int getPrintUser() {
		return printUser;
	}

	public void setPrintUser(int printUser) {
		this.printUser = printUser;
	}

	public SimpleCalendar getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) throws CalendarException {
		this.receiveDate.setCalendarValue(receiveDate);
	}

	public String getReceiveDept() {
		return receiveDept;
	}

	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}

	public String getReceiveRemark() {
		return receiveRemark;
	}

	public void setReceiveRemark(String receiveRemark) {
		this.receiveRemark = receiveRemark;
	}

	public int getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(int receiveUser) {
		this.receiveUser = receiveUser;
	}

	public String getToBarcode() {
		return toBarcode;
	}

	public void setToBarcode(String toBarcode) {
		this.toBarcode = toBarcode;
	}

	public BarcodeReceiveDTO() {
		super();
		this.printDate = new SimpleCalendar();
		this.receiveDate = new SimpleCalendar();
		this.printDateBegin = new SimpleCalendar();
		this.printDateEnd = new SimpleCalendar();
		this.receiveDateBegin = new SimpleCalendar();
		this.receiveDateEnd = new SimpleCalendar();
	}


	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrintUserName() {
		return printUserName;
	}

	public void setPrintUserName(String printUserName) {
		this.printUserName = printUserName;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public String getReceiveDeptName() {
		return receiveDeptName;
	}

	public void setReceiveDeptName(String receiveDeptName) {
		this.receiveDeptName = receiveDeptName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBarcodeReceiveId() {
		return barcodeReceiveId;
	}

	public void setBarcodeReceiveId(String barcodeReceiveId) {
		this.barcodeReceiveId = barcodeReceiveId;
	}

	public SimpleCalendar getPrintDateBegin() {
		return printDateBegin;
	}

	public void setPrintDateBegin(String printDateBegin) throws CalendarException {
		this.printDateBegin.setCalendarValue(printDateBegin);
	}

	public SimpleCalendar getPrintDateEnd() {
		return printDateEnd;
	}

	public void setPrintDateEnd(String printDateEnd) throws CalendarException {
		this.printDateEnd.setCalendarValue(printDateEnd);
	}

	public SimpleCalendar getReceiveDateBegin() {
		return receiveDateBegin;
	}

	public void setReceiveDateBegin(String receiveDateBegin) throws CalendarException {
		this.receiveDateBegin.setCalendarValue(receiveDateBegin);
	}

	public SimpleCalendar getReceiveDateEnd() {
		return receiveDateEnd;
	}

	public void setReceiveDateEnd(String receiveDateEnd) throws CalendarException {
		this.receiveDateEnd.setCalendarValue(receiveDateEnd);
	}

	public int getBarcodePrintNum() {
		return barcodePrintNum;
	}

	public void setBarcodePrintNum(int barcodePrintNum) {
		this.barcodePrintNum = barcodePrintNum;
	}
	
	
}



