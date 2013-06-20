package com.sino.ams.apd.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

public class AmsAssetsCheckByYrHeaderDTO extends CommonRecordDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8703638110529907329L;
	private String transId = "";     					//������¼ID
	private String transType = "";   					//���
	private String transTypeValue = "";                 //������������
    private SimpleCalendar  creationDate = null;		//����ʱ��
    private int createdBy =0;							//������
    private String createdByName = "";   			    //����������
    private int organizationId = 0;						//�����˻���
    private String transStatus = "";   					//�̵�״̬
    private String transStatusValue = "";               //�̵�״̬����
//    private SimpleCalendar taskStartDate =null;         //����ʼ����
//    private SimpleCalendar taskEndDate =null;           //�����������
//    private SimpleCalendar  basicDateBegin = null;		//�̵��׼�տ�ʼ��Χ
//    private SimpleCalendar  basicDateEnd = null;		//�̵��׼�ս�����Χ
    
    
    public AmsAssetsCheckByYrHeaderDTO() {
        super();
        setPrimaryKeyName("transId");
    }
    
	public int getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	public String getTransStatusValue() {
		return transStatusValue;
	}
	public void setTransStatusValue(String transStatusValue) {
		this.transStatusValue = transStatusValue;
	}
	
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getTransTypeValue() {
		return transTypeValue;
	}
	public void setTransTypeValue(String transTypeValue) {
		this.transTypeValue = transTypeValue;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public SimpleCalendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(SimpleCalendar creationDate) {
		this.creationDate = creationDate;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
    
}
