package com.sino.ams.newasset.dto;

import com.sino.ams.bean.SyBaseSQLUtil;


/**
 * <p>Title: �̶��ʲ������(EAM) AmsFaChgHistory</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsFaChgHistoryDTO extends AmsAssetsTransLineDTO {

    private String chgLogId = "";
    private int fromOrganizationId;
    private int toOrganizationId;
    private int toDept;
    private String fromPerson = "";
    private String toPerson = "";
    private String fromStatus = "";
    private String toStatus = "";
    private int createdBy = SyBaseSQLUtil.NULL_INT_VALUE;
    private String fromDept = "";

    public AmsFaChgHistoryDTO() {
        super();
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� �����־ID
     * @param chgLogId String
     */
    public void setChgLogId(String chgLogId) {
        this.chgLogId = chgLogId;
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� ���ǰOU��֯ID
     * @param fromOrganizationId String
     */
    public void setFromOrganizationId(int fromOrganizationId) {
        this.fromOrganizationId = fromOrganizationId;
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� �����OU��֯ID
     * @param toOrganizationId String
     */
    public void setToOrganizationId(int toOrganizationId) {
        this.toOrganizationId = toOrganizationId;
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� �������
     * @param toDept String
     */
    public void setToDept(int toDept) {
        this.toDept = toDept;
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� ���ǰ������
     * @param fromPerson String
     */
    public void setFromPerson(String fromPerson) {
        this.fromPerson = fromPerson;
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� ����󱣹���
     * @param toPerson String
     */
    public void setToPerson(String toPerson) {
        this.toPerson = toPerson;
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� ���ǰ״̬
     * @param fromStatus String
     */
    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� �����״̬
     * @param toStatus String
     */
    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    /**
     * ���ܣ����ù̶��ʲ������(EAM)���� ������
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setFromDept(String fromDept) {
        this.fromDept = fromDept;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� �����־ID
     * @return String
     */
    public String getChgLogId() {
        return this.chgLogId;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� ���ǰOU��֯ID
     * @return String
     */
    public int getFromOrganizationId() {
		return fromOrganizationId;
	}    

    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� �����OU��֯ID
     * @return String
     */
    public int getToOrganizationId() {
        return this.toOrganizationId;
    }


    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� �������
     * @return String
     */
    public int getToDept() {
        return this.toDept;
    }


    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� ���ǰ������
     * @return String
     */
    public String getFromPerson() {
        return this.fromPerson;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� ����󱣹���
     * @return String
     */
    public String getToPerson() {
        return this.toPerson;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� ���ǰ״̬
     * @return String
     */
    public String getFromStatus() {
        return this.fromStatus;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� �����״̬
     * @return String
     */
    public String getToStatus() {
        return this.toStatus;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ������(EAM)���� ������
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    public String getFromDept() {
        return fromDept;
    }
}
