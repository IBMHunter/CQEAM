package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
* <p>Title: �ʲ�̨��ά����־ AmsItemCorrectLog</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsItemCorrectLogDTO extends CommonRecordDTO{

	private String logId = "";
	private String barcode = "";
	private String correctContent = "";
	private String createdUser = "";

	public AmsItemCorrectLogDTO() {
		super();
	}

	/**
	 * ���ܣ������ʲ�̨��ά����־���� ��־���к�
	 * @param logId String
	 */
	public void setLogId(String logId){
		this.logId = logId;
	}

	/**
	 * ���ܣ������ʲ�̨��ά����־���� ��ǩ��
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ������ʲ�̨��ά����־���� �޸�����
	 * @param correctContent String
	 */
	public void setCorrectContent(String correctContent){
		this.correctContent = correctContent;
	}

	/**
	 * ���ܣ���ȡ�ʲ�̨��ά����־���� ��־���к�
	 * @return String
	 */
	public String getLogId() {
		return this.logId;
	}

	/**
	 * ���ܣ���ȡ�ʲ�̨��ά����־���� ��ǩ��
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ�ʲ�̨��ά����־���� �޸�����
	 * @return String
	 */
	public String getCorrectContent() {
		return this.correctContent;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
}
