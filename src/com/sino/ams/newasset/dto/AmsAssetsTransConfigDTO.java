package com.sino.ams.newasset.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
 * <p>Title: �̶��ʲ������������� AmsAssetsTransConfig</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsTransConfigDTO extends CheckBoxDTO {

    private int organizationId = -1;
    private String transferType = "";
    private String faContentCode = "";
    private String faContentName = "";
    private String enabled = "";

    /**
     * ���ܣ����ù̶��ʲ����������������� OU��֯ID
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ����ù̶��ʲ����������������� �������ͣ������ڵ��������ż���������м����
     * @param transferType String
     */
    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    /**
     * ���ܣ����ù̶��ʲ����������������� �ʲ������ֵ����
     * @param faContentCode String
     */
    public void setFaContentCode(String faContentCode) {
        this.faContentCode = faContentCode;
    }

    /**
     * ���ܣ����ù̶��ʲ����������������� �Ƿ���Ч
     * @param enabled String
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public void setFaContentName(String faContentName) {
        this.faContentName = faContentName;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ����������������� OU��֯ID
     * @return String
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ����������������� �������ͣ������ڵ��������ż���������м����
     * @return String
     */
    public String getTransferType() {
        return this.transferType;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ����������������� �ʲ������ֵ����
     * @return String
     */
    public String getFaContentCode() {
        return this.faContentCode;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ����������������� �Ƿ���Ч
     * @return String
     */
    public String getEnabled() {
        return this.enabled;
    }

    public String getFaContentName() {
        return faContentName;
    }

}
