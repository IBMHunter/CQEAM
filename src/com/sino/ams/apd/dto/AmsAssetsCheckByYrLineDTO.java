package com.sino.ams.apd.dto;

import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

public class AmsAssetsCheckByYrLineDTO extends AmsAssetsAddressVDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1825048860185049313L;
	private String transId = "";     					//������¼ID
//    private SimpleCalendar  checkDate=null ;		    //�̵��׼��
    private int receivdBy =0;							//������
    private String receivdByName = "";					//����������
    private int RborganizationId = 0;				    //�����˻���
    private String transStatus = "";   					//�̵�״̬
    private String transStatusValue = "";               //�̵�״̬����
    private String companyCode = "";                    //���й�˾����
    private String company = "";						//���й�˾����
    private String bookTypeCode = "";					//�ʲ��ʲ�����
    private String bookTypeName = "";					//�ʲ��ʲ�����
    private SimpleCalendar  basicDateBegin = null;		//�̵��׼�տ�ʼ��Χ
    private SimpleCalendar  basicDateEnd = null;		//�̵��׼�ս�����Χ
    private String typeValueOption = "" ;               //��ʵ���̵��ʲ��·�������ʽ
    private String  createType = "";                    //��ʵ���̵��ʲ��·�������ʽ����
    private String lineId = "";                         //�����м�¼
//	private SimpleCalendar taskStartDate =null;         //����ʼ����
//	private SimpleCalendar taskEndDate =null;           //�����������
    
//    private SimpleCalendar nowDate =null;
    
    
//    
//	public SimpleCalendar getNowDate() throws CalendarException {
//		this.nowDate.setCalendarValue(getCalPattern());
//		return nowDate;
//	}
//	public void setNowDate(SimpleCalendar nowDate) throws CalendarException {
//		this.nowDate.setCalendarValue(nowDate);
//	}
//	
	
	public AmsAssetsCheckByYrLineDTO() {
		super();
		this.basicDateBegin = new SimpleCalendar();
		this.basicDateEnd   = new SimpleCalendar();
//		this.nowDate        = new SimpleCalendar();
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getCreateType() {
		return createType;
	}
	public void setCreateType(String createType) {
		this.createType = createType;
	}
	public String getTypeValueOption() {
		return typeValueOption;
	}
	public void setTypeValueOption(String typeValueOption) {
		this.typeValueOption = typeValueOption;
	}
	
	public SimpleCalendar getBasicDateBegin() {
		return basicDateBegin;
	}
	public void setBasicDateBegin(SimpleCalendar basicDateBegin) {
		this.basicDateBegin = basicDateBegin;
	}
	public SimpleCalendar getBasicDateEnd() {
		return basicDateEnd;
	}
	public void setBasicDateEnd(SimpleCalendar basicDateEnd) {
		this.basicDateEnd = basicDateEnd;
	}
	public String getTransStatusValue() {
		return transStatusValue;
	}
	public void setTransStatusValue(String transStatusValue) {
		this.transStatusValue = transStatusValue;
	}
	public String getReceivdByName() {
		return receivdByName;
	}
	public void setReceivdByName(String receivdByName) {
		this.receivdByName = receivdByName;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public int getReceivdBy() {
		return receivdBy;
	}
	public void setReceivdBy(int receivdBy) {
		this.receivdBy = receivdBy;
	}
	public int getRborganizationId() {
		return RborganizationId;
	}
	public void setRborganizationId(int rborganizationId) {
		RborganizationId = rborganizationId;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBookTypeCode() {
		return bookTypeCode;
	}
	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
    
}
