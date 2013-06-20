package com.sino.soa.mis.srv.ouorganization.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.dto.CheckBoxDTO;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-8
 * Time: 16:05:40
 * To change this template use File | Settings | File Templates.
 */
public class SBFIGLOuOrganizationDTO extends CheckBoxDTO {

    private int orgId = SyBaseSQLUtil.NULL_INT_VALUE;;
    private String orgName = "";
    private String setOfBooksName = "";
    private String setOfBooksId = "";
    private String attribute1 = "";
    private String enableFlag = "";

    public SBFIGLOuOrganizationDTO() {
        super();
    }

    /**
     * ���ܣ�����OU��֯��Ϣ�������� ��֯ID
     * @param orgId String
     */
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    /**
     * ���ܣ�����OU��֯��Ϣ�������� OU����
     * @param orgName String
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * ���ܣ�����OU��֯��Ϣ�������� ��������
     * @param setOfBooksName String
     */
    public void setSetOfBooksName(String setOfBooksName) {
        this.setOfBooksName = setOfBooksName;
    }

    /**
     * ���ܣ�����OU��֯��Ϣ�������� ���ױ�ʶ
     * @param setOfBooksId String
     */
    public void setSetOfBooksId(String setOfBooksId) {
        this.setOfBooksId = setOfBooksId;
    }

    /**
     * ���ܣ�����OU��֯��Ϣ�������� ��˾����
     * @param attribute1 String
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * ���ܣ�����OU��֯��Ϣ�������� �Ƿ���Ч
     * @param enableFlag String
     */
    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }


    /**
     * ���ܣ���ȡOU��֯��Ϣ�������� ��֯ID
     * @return String
     */
    public int getOrgId() {
        return this.orgId;
    }

    /**
     * ���ܣ���ȡOU��֯��Ϣ�������� OU����
     * @return String
     */
    public String getOrgName() {
        return this.orgName;
    }

    /**
     * ���ܣ���ȡOU��֯��Ϣ�������� ��������
     * @return String
     */
    public String getSetOfBooksName() {
        return this.setOfBooksName;
    }

    /**
     * ���ܣ���ȡOU��֯��Ϣ�������� ���ױ�ʶ
     * @return String
     */
    public String getSetOfBooksId() {
        return this.setOfBooksId;
    }

    /**
     * ���ܣ���ȡOU��֯��Ϣ�������� ��˾����
     * @return String
     */
    public String getAttribute1() {
        return this.attribute1;
    }

    /**
     * ���ܣ���ȡOU��֯��Ϣ�������� �Ƿ���Ч
     * @return String
     */
    public String getEnableFlag() {
        return this.enableFlag;
    }

}