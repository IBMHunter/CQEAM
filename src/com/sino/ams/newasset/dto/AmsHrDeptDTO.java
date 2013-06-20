package com.sino.ams.newasset.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
 * <p>Title: MIS����(HR) AmsHrDept</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsHrDeptDTO extends CheckBoxDTO {

    private String hrDeptId = "";
    private String hrDeptName = "";
    private String companyCode = "";

    public AmsHrDeptDTO() {
        super();
    }

    /**
     * ���ܣ�����MIS����(HR)���� ����ID
     * @param hrDeptId String
     */
    public void setHrDeptId(String hrDeptId) {
        this.hrDeptId = hrDeptId;
    }

    /**
     * ���ܣ�����MIS����(HR)���� ��������
     * @param hrDeptName String
     */
    public void setHrDeptName(String hrDeptName) {
        this.hrDeptName = hrDeptName;
    }

    /**
     * ���ܣ�����MIS����(HR)���� ��˾����
     * @param companyCode String
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    /**
     * ���ܣ���ȡMIS����(HR)���� ����ID
     * @return String
     */
    public String getHrDeptId() {
        return this.hrDeptId;
    }

    /**
     * ���ܣ���ȡMIS����(HR)���� ��������
     * @return String
     */
    public String getHrDeptName() {
        return this.hrDeptName;
    }

    /**
     * ���ܣ���ȡMIS����(HR)���� ��˾����
     * @return String
     */
    public String getCompanyCode() {
        return this.companyCode;
    }

}
