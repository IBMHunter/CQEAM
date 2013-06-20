package com.sino.ams.system.user.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: SfGroup</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class SfGroupDTO extends CheckBoxDTO {
    private int groupId ;
    private String groupCode = "";
    private String groupname = "";
    private String groupName = "";
    private String groupPid = "";
    private int organizationId;
    private String sortno = "";
    private String isroot = "";
    private String category = "";
    private String enabled = "";
    private String isInner = "";
    private Timestamp creationDate = null;
    private int createdBy;
    private Timestamp lastUpdateDate = null;
    private int lastUpdateBy;
    private String isDesigner = "";
    private int pFlowId;
    private String categoryDesc = "";
    private String groupProp = "";
    private String isDesignerOption = "";
    private String flowGroupOption = "";
    private String enableOption = "";
    private String groupThred = "";
    private String deptId = "";
    private String deptName = "";

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    /**
     * ���ܣ���ȡDTO���� pFlowId
     * @return String
     */
    public int getpFlowId() {
        return this.pFlowId;
    }

    /**
     * ���ܣ�����DTO���� pFlowId
     * @param pFlowId String
     */
    public void setpFlowId(int pFlowId) {
        this.pFlowId = pFlowId;
    }

    /**
     * ���ܣ���ȡDTO���� isDesigner
     * @return String
     */
    public String getIsDesigner() {
        return this.isDesigner;
    }

    /**
     * ���ܣ�����DTO���� isDesigner
     * @param isDesigner String
     */
    public void setIsDesigner(String isDesigner) {
        this.isDesigner = isDesigner;
    }

    /**
     * ���ܣ�����DTO���� groupId
     * @param groupId String
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * ���ܣ�����DTO���� groupCode
     * @param groupCode String
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * ���ܣ�����DTO���� groupname
     * @param groupname String
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * ���ܣ�����DTO���� groupPid
     * @param groupPid String
     */
    public void setGroupPid(String groupPid) {
        this.groupPid = groupPid;
    }

    /**
     * ���ܣ�����DTO���� organizationId
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ�����DTO���� sortno
     * @param sortno String
     */
    public void setSortno(String sortno) {
        this.sortno = sortno;
    }

    /**
     * ���ܣ�����DTO���� isroot
     * @param isroot String
     */
    public void setIsroot(String isroot) {
        this.isroot = isroot;
    }

    /**
     * ���ܣ�����DTO���� category
     * @param category String
     */
    public void setCategory(String category) {
        this.category = category;
    }


    /**
     * ���ܣ�����DTO���� enabled
     * @param enabled String
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * ���ܣ�����DTO���� isInner
     * @param isInner String
     */
    public void setIsInner(String isInner) {
        this.isInner = isInner;
    }

    /**
     * ���ܣ�����DTO���� creationDate
     * @param creationDate Timestamp
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        if (!StrUtil.isEmpty(creationDate)) {
            SimpleCalendar cal = new SimpleCalendar(creationDate);
            this.creationDate = cal.getSQLTimestamp();
        }
    }

    /**
     * ���ܣ�����DTO���� createdBy
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ�����DTO���� lastUpdateDate
     * @param lastUpdateDate Timestamp
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        if (!StrUtil.isEmpty(lastUpdateDate)) {
            SimpleCalendar cal = new SimpleCalendar(lastUpdateDate);
            this.lastUpdateDate = cal.getSQLTimestamp();
        }
    }

    /**
     * ���ܣ�����DTO���� lastUpdateBy
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public void setGroupProp(String groupProp) {
        this.groupProp = groupProp;
    }

    public void setFlowGroupOption(String flowGroupOption) {
        this.flowGroupOption = flowGroupOption;
    }

    public void setIsDesignerOption(String isDesignerOption) {
        this.isDesignerOption = isDesignerOption;
    }

    public void setEnableOption(String enableOption) {
        this.enableOption = enableOption;
    }

    /**
     * ���ܣ���ȡDTO���� groupId
     * @return String
     */
    public int getGroupId() {
        return this.groupId;
    }

    /**
     * ���ܣ���ȡDTO���� groupCode
     * @return String
     */
    public String getGroupCode() {
        return this.groupCode;
    }

    /**
     * ���ܣ���ȡDTO���� groupname
     * @return String
     */
    public String getGroupname() {
        return this.groupname;
    }

    /**
     * ���ܣ���ȡDTO���� groupPid
     * @return String
     */
    public String getGroupPid() {
        return this.groupPid;
    }

    /**
     * ���ܣ���ȡDTO���� organizationId
     * @return String
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡDTO���� sortno
     * @return String
     */
    public String getSortno() {
        return this.sortno;
    }

    /**
     * ���ܣ���ȡDTO���� isroot
     * @return String
     */
    public String getIsroot() {
        return this.isroot;
    }

    /**
     * ���ܣ���ȡDTO���� category
     * @return String
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * ���ܣ���ȡDTO���� enabled
     * @return String
     */
    public String getEnabled() {
        return this.enabled;
    }

    /**
     * ���ܣ���ȡDTO���� isInner
     * @return String
     */
    public String getIsInner() {
        return this.isInner;
    }

    /**
     * ���ܣ���ȡDTO���� creationDate
     * @return Timestamp
     */
    public Timestamp getCreationDate() {
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡDTO���� createdBy
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡDTO���� lastUpdateDate
     * @return Timestamp
     */
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡDTO���� lastUpdateBy
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public String getGroupProp() {
        return groupProp;
    }

    public String getFlowGroupOption() {
        return flowGroupOption;
    }

    public String getIsDesignerOption() {
        return isDesignerOption;
    }

    public String getEnableOption() {
        return enableOption;
    }

    public String getGroupThred() {
        return groupThred;
    }

    public void setGroupThred(String groupThred) {
        this.groupThred = groupThred;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
