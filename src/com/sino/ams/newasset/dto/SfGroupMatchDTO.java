package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
 * <p>Title: GROUP_MATCH GroupMatch</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class SfGroupMatchDTO extends CommonRecordDTO {

    private String deptCode = "";
    private String deptName = "";
    private int groupId;
    private String groupname = "";

    /**
     * ���ܣ�����GROUP_MATCH���� DEPT_CODE
     * @param deptCode String
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * ���ܣ�����GROUP_MATCH���� DEPT_NAME
     * @param deptName String
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * ���ܣ�����GROUP_MATCH���� GROUP_ID
     * @param groupId String
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * ���ܣ�����GROUP_MATCH���� GROUPNAME
     * @param groupname String
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }


    /**
     * ���ܣ���ȡGROUP_MATCH���� DEPT_CODE
     * @return String
     */
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * ���ܣ���ȡGROUP_MATCH���� DEPT_NAME
     * @return String
     */
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * ���ܣ���ȡGROUP_MATCH���� GROUP_ID
     * @return String
     */
    public int getGroupId() {
        return this.groupId;
    }

    /**
     * ���ܣ���ȡGROUP_MATCH���� GROUPNAME
     * @return String
     */
    public String getGroupname() {
        return this.groupname;
    }

}
