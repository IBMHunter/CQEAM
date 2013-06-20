package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
 * <p>Title: ��¼MIS��ǩ�ű����ʷ AmsMisTagChg</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsMisTagChgDTO extends CommonRecordDTO {

	private String id = "";
	private int fromOrganizationId;
	private int toOrganizationId;
	private String fromOrganizationName = "";
	private String toOrganizationName = "";
	private String tagNumberFrom = "";
	private String tagNumberTo = "";
	private String refNumber = "";
	private String transId = "";

	public AmsMisTagChgDTO() {
		super();
	}

	/**
	 * ���ܣ����ü�¼MIS��ǩ�ű����ʷ���� AMS_MIS_TAG_CHG_S.nextval
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ���ܣ����ü�¼MIS��ǩ�ű����ʷ���� ����OU
	 * @param fromOrganizationId String
	 */
	public void setFromOrganizationId(int fromOrganizationId) {
		this.fromOrganizationId = fromOrganizationId;
	}

	/**
	 * ���ܣ����ü�¼MIS��ǩ�ű����ʷ���� ����OU
	 * @param toOrganizationId String
	 */
	public void setToOrganizationId(int toOrganizationId) {
		this.toOrganizationId = toOrganizationId;
	}

	/**
	 * ���ܣ����ü�¼MIS��ǩ�ű����ʷ���� TAG_NUMBER_FROM
	 * @param tagNumberFrom String
	 */
	public void setTagNumberFrom(String tagNumberFrom) {
		this.tagNumberFrom = tagNumberFrom;
	}

	/**
	 * ���ܣ����ü�¼MIS��ǩ�ű����ʷ���� TAG_NUMBER_TO
	 * @param tagNumberTo String
	 */
	public void setTagNumberTo(String tagNumberTo) {
		this.tagNumberTo = tagNumberTo;
	}

	/**
	 * ���ܣ����ü�¼MIS��ǩ�ű����ʷ���� �ο���Ϣ���磺�������š�����
	 * @param refNumber String
	 */
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}


	/**
	 * ���ܣ���ȡ��¼MIS��ǩ�ű����ʷ���� AMS_MIS_TAG_CHG_S.nextval
	 * @return String
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * ���ܣ���ȡ��¼MIS��ǩ�ű����ʷ���� ����OU
	 * @return String
	 */
	public int getFromOrganizationId() {
		return this.fromOrganizationId;
	}

	/**
	 * ���ܣ���ȡ��¼MIS��ǩ�ű����ʷ���� ����OU
	 * @return String
	 */
	public int getToOrganizationId() {
		return this.toOrganizationId;
	}

	/**
	 * ���ܣ���ȡ��¼MIS��ǩ�ű����ʷ���� TAG_NUMBER_FROM
	 * @return String
	 */
	public String getTagNumberFrom() {
		return this.tagNumberFrom;
	}

	/**
	 * ���ܣ���ȡ��¼MIS��ǩ�ű����ʷ���� TAG_NUMBER_TO
	 * @return String
	 */
	public String getTagNumberTo() {
		return this.tagNumberTo;
	}

	/**
	 * ���ܣ���ȡ��¼MIS��ǩ�ű����ʷ���� �ο���Ϣ���磺�������š�����
	 * @return String
	 */
	public String getRefNumber() {
		return this.refNumber;
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

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}
}
