package com.sino.ams.newasset.dto;


import com.sino.ams.bean.CommonRecordDTO;

/**
 * <p>Title: ���ű�(EAM) AmsMisDept</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsMisDeptDTO extends CommonRecordDTO {

    private String companyCode = "";
    private String deptCode = "";
    private String deptName = "";
    private String enabled = "";
    private int organizationId;
    private int displayOrder;

    public AmsMisDeptDTO() {
        super();
    }

    /**
     * ���ܣ����ò��ű�(EAM)���� ��˾����
     * @param companyCode String
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * ���ܣ����ò��ű�(EAM)���� ���Ŵ���
     * @param deptCode String
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * ���ܣ����ò��ű�(EAM)���� ��������
     * @param deptName String
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ���ȡ���ű�(EAM)���� ��˾����
     * @return String
     */
    public String getCompanyCode() {
        return this.companyCode;
    }

    /**
     * ���ܣ���ȡ���ű�(EAM)���� ���Ŵ���
     * @return String
     */
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * ���ܣ���ȡ���ű�(EAM)���� ��������
     * @return String
     */
    public String getDeptName() {
        return this.deptName;
    }

    public String getEnabled() {
        return enabled;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
