package com.sino.ams.spare.repair.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ����ά�޳�����Ϣ AmsVendorInfo</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsVendorInfoDTO extends CheckBoxDTO{
	private String vendorCode = "";
	private String vendorName = "";
	private String address = "";
	private String contact = "";
	private String phone = "";
	private String fax = "";
	private String attribute1 = "";
	private String attribute2 = "";
    private String vendorId = "";

    public AmsVendorInfoDTO() {
		super();
	}

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
    /**
	 * ���ܣ����ñ���ά�޳�����Ϣ���� ��Ӧ�̱���
	 * @param vendorCode String
	 */
	public void setVendorCode(String vendorCode){
		this.vendorCode = vendorCode;
	}

	/**
	 * ���ܣ����ñ���ά�޳�����Ϣ���� ��Ӧ������
	 * @param vendorName String
	 */
	public void setVendorName(String vendorName){
		this.vendorName = vendorName;
	}

	/**
	 * ���ܣ����ñ���ά�޳�����Ϣ���� ��Ӧ�̵�ַ
	 * @param address String
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * ���ܣ����ñ���ά�޳�����Ϣ���� ��ϵ��
	 * @param contact String
	 */
	public void setContact(String contact){
		this.contact = contact;
	}

	/**
	 * ���ܣ����ñ���ά�޳�����Ϣ���� �绰
	 * @param phone String
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}

	/**
	 * ���ܣ����ñ���ά�޳�����Ϣ���� ����
	 * @param fax String
	 */
	public void setFax(String fax){
		this.fax = fax;
	}

	/**
	 * ���ܣ����ñ���ά�޳�����Ϣ���� ATTRIBUTE1
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ����ñ���ά�޳�����Ϣ���� ATTRIBUTE2
	 * @param attribute2 String
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}


	/**
	 * ���ܣ���ȡ����ά�޳�����Ϣ���� ��Ӧ�̱���
	 * @return String
	 */
	public String getVendorCode() {
		return this.vendorCode;
	}

	/**
	 * ���ܣ���ȡ����ά�޳�����Ϣ���� ��Ӧ������
	 * @return String
	 */
	public String getVendorName() {
		return this.vendorName;
	}

	/**
	 * ���ܣ���ȡ����ά�޳�����Ϣ���� ��Ӧ�̵�ַ
	 * @return String
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * ���ܣ���ȡ����ά�޳�����Ϣ���� ��ϵ��
	 * @return String
	 */
	public String getContact() {
		return this.contact;
	}

	/**
	 * ���ܣ���ȡ����ά�޳�����Ϣ���� �绰
	 * @return String
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * ���ܣ���ȡ����ά�޳�����Ϣ���� ����
	 * @return String
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * ���ܣ���ȡ����ά�޳�����Ϣ���� ATTRIBUTE1
	 * @return String
	 */
	public String getAttribute1() {
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡ����ά�޳�����Ϣ���� ATTRIBUTE2
	 * @return String
	 */
	public String getAttribute2() {
		return this.attribute2;
	}

}