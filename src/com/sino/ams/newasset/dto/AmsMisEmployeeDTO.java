package com.sino.ams.newasset.dto;

/**
 * <p>Title: MISԱ���� AmsMisEmployee</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsMisEmployeeDTO extends AmsMisDeptDTO {

    private String employeeId = "";
    private String userName = "";
    private String employeeNumber = "";
    private int hrDeptId;
    private String hrDeptName = "";
    private String loginName = "";

    private String transferType = "";

    public AmsMisEmployeeDTO() {
        super();
    }

    /**
     * ���ܣ�����MISԱ�������� Ա��ID
     * @param employeeId String
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * ���ܣ�����MISԱ�������� Ա������
     * @param userName String
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * ���ܣ�����MISԱ�������� Ա�����
     * @param employeeNumber String
     */
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * ���ܣ�����MISԱ�������� �������ű��
     * @param hrDeptId String
     */
    public void setHrDeptId(int hrDeptId) {
        this.hrDeptId = hrDeptId;
    }

    /**
     * ���ܣ�����MISԱ�������� ������������
     * @param hrDeptName String
     */
    public void setHrDeptName(String hrDeptName) {
        this.hrDeptName = hrDeptName;
    }

    /**
     * ���ܣ�����MISԱ�������� MIS��¼��
     * @param loginName String
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    /**
     * ���ܣ���ȡMISԱ�������� Ա��ID
     * @return String
     */
    public String getEmployeeId() {
        return this.employeeId;
    }

    /**
     * ���ܣ���ȡMISԱ�������� Ա������
     * @return String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * ���ܣ���ȡMISԱ�������� Ա�����
     * @return String
     */
    public String getEmployeeNumber() {
        return this.employeeNumber;
    }

    /**
     * ���ܣ���ȡMISԱ�������� �������ű��
     * @return String
     */
    public int getHrDeptId() {
        return this.hrDeptId;
    }

    /**
     * ���ܣ���ȡMISԱ�������� ������������
     * @return String
     */
    public String getHrDeptName() {
        return this.hrDeptName;
    }

    /**
     * ���ܣ���ȡMISԱ�������� MIS��¼��
     * @return String
     */
    public String getLoginName() {
        return this.loginName;
    }

    public String getTransferType() {
        return transferType;
    }

}
