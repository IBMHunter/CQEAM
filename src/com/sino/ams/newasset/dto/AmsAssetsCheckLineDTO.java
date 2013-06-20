package com.sino.ams.newasset.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;

/**
 * <p>Title: �ʲ��̵��б� AmsAssetsCheckLine</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsCheckLineDTO extends AmsAssetsAddressVDTO {

    private String headerId = "";
    private String systemStatus = "";
    private String systemStatusName = "";
    private String scanStatus = "";
    private String scanStatusName = "";
    private String archiveStatus = "";
    private String archiveStatusName = "";
    private int batchId;
    private String archiveRemark = "";

    private String scanItemCode = "";
    private String scanItemCategory = "";
    private String scanItemName = "";
    private String scanItemSpec = "";
    private String scanResponsibilityUser = "";
    private String scanResponsibilityDept = "";
    private String scanResponsibilityUserName = "";
    private String scanResponsibilityDeptName = "";
    private String archItemCode = "";
    private String archResponsibilityUser = "";
    private String archResponsibilityDept = "";
    private String archToTempInv = "";
    private String addressDiffReason = "";
    private String itemCodeDiffReason = "";
    private String userDiffReason = "";
    private String deptDiffReason = "";
    private int scanOrganizationId;
    private int chkTimes = 1;
    private SimpleCalendar lastChkDate = null;
    private String lastChkNo = "";

    private String scanItemCategoryName = "";
    private String  scanMaintainUser = "";
    private String  archMaintainUser ="";
    private String companyOpt = "";
    private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;

    private String replaceFlag = "";
    private String newTag = "";
    private String constructStatus = "";
    private String lneId = "";
    private String lneName = "";
    private String cexId = "";
    private String cexName = "";
    private String opeId = "";
    private String opeName = "";
    private String nleId = "";
    private String nleName = "";

    private SimpleCalendar scanStartDate = null;
    private SimpleCalendar archStartDate = null;

    public AmsAssetsCheckLineDTO() {
        super();
        this.lastChkDate = new SimpleCalendar();
        this.scanStartDate = new SimpleCalendar();
        this.archStartDate = new SimpleCalendar();
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� ͷID
     *
     * @param headerId String
     */
    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }


    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� ϵͳ״̬
     *
     * @param systemStatus String
     */
    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� ɨ��״̬
     *
     * @param scanStatus String
     */
    public void setScanStatus(String scanStatus) {
        this.scanStatus = scanStatus;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� �鵵״̬
     *
     * @param archiveStatus String
     */
    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public void setArchiveRemark(String archiveRemark) {
        this.archiveRemark = archiveRemark;
    }

    public void setArchiveStatusName(String archiveStatusName) {
        this.archiveStatusName = archiveStatusName;
    }

    public void setScanStatusName(String scanStatusName) {
        this.scanStatusName = scanStatusName;
    }

    public void setSystemStatusName(String systemStatusName) {
        this.systemStatusName = systemStatusName;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� ͷID
     *
     * @return String
     */
    public String getHeaderId() {
        return this.headerId;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� ϵͳ״̬
     *
     * @return String
     */
    public String getSystemStatus() {
        return this.systemStatus;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� ɨ��״̬
     *
     * @return String
     */
    public String getScanStatus() {
        return this.scanStatus;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� �鵵״̬
     *
     * @return String
     */
    public String getArchiveStatus() {
        return this.archiveStatus;
    }

    public int getBatchId() {
        return batchId;
    }

    public String getArchiveRemark() {
        return archiveRemark;
    }

    public String getArchiveStatusName() {
        return archiveStatusName;
    }

    public String getScanStatusName() {
        return scanStatusName;
    }

    public String getSystemStatusName() {
        return systemStatusName;
    }


    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� ɨ���ύ�ķ������
     *
     * @param scanItemCode String
     */
    public void setScanItemCode(String scanItemCode) {
        this.scanItemCode = scanItemCode;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� ɨ���ύ��������
     *
     * @param scanResponsibilityUser String
     */
    public void setScanResponsibilityUser(String scanResponsibilityUser) {
        this.scanResponsibilityUser = scanResponsibilityUser;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� ɨ���ύ�����β���
     *
     * @param scanResponsibilityDept String
     */
    public void setScanResponsibilityDept(String scanResponsibilityDept) {
        this.scanResponsibilityDept = scanResponsibilityDept;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� �鵵�������
     *
     * @param archItemCode String
     */
    public void setArchItemCode(String archItemCode) {
        this.archItemCode = archItemCode;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� �鵵������
     *
     * @param archResponsibilityUser String
     */
    public void setArchResponsibilityUser(String archResponsibilityUser) {
        this.archResponsibilityUser = archResponsibilityUser;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� �鵵���β���
     *
     * @param archResponsibilityDept String
     */
    public void setArchResponsibilityDept(String archResponsibilityDept) {
        this.archResponsibilityDept = archResponsibilityDept;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� �Ƿ�鵵����;��
     *
     * @param archToTempInv String
     */
    public void setArchToTempInv(String archToTempInv) {
        this.archToTempInv = archToTempInv;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� �ص�������;��ԭ��
     *
     * @param addressDiffReason String
     */
    public void setAddressDiffReason(String addressDiffReason) {
        this.addressDiffReason = addressDiffReason;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� �豸������ԭ��
     *
     * @param itemCodeDiffReason String
     */
    public void setItemCodeDiffReason(String itemCodeDiffReason) {
        this.itemCodeDiffReason = itemCodeDiffReason;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� �����˱��ԭ��
     *
     * @param userDiffReason String
     */
    public void setUserDiffReason(String userDiffReason) {
        this.userDiffReason = userDiffReason;
    }

    /**
     * ���ܣ������ʲ��̵��б�--���̵��ʲ���(EAM)���� ���β��ű��ԭ��
     *
     * @param deptDiffReason String
     */
    public void setDeptDiffReason(String deptDiffReason) {
        this.deptDiffReason = deptDiffReason;
    }

    public void setScanItemName(String scanItemName) {
        this.scanItemName = scanItemName;
    }

    public void setScanItemSpec(String scanItemSpec) {
        this.scanItemSpec = scanItemSpec;
    }

    public void setScanItemCategory(String scanItemCategory) {
        this.scanItemCategory = scanItemCategory;
    }

    public void setScanOrganizationId(int scanOrganizationId) {
        this.scanOrganizationId = scanOrganizationId;
    }

    public void setScanResponsibilityDeptName(String scanResponsibilityDeptName) {
        this.scanResponsibilityDeptName = scanResponsibilityDeptName;
    }

    public void setScanResponsibilityUserName(String scanResponsibilityUserName) {
        this.scanResponsibilityUserName = scanResponsibilityUserName;
    }

    public void setChkTimes(int chkTimes) {
        this.chkTimes = chkTimes;
    }

    public void setLastChkNo(String lastChkNo) {
        this.lastChkNo = lastChkNo;
    }

    public void setScanItemCategoryName(String scanItemCategoryName) {
        this.scanItemCategoryName = scanItemCategoryName;
    }

    public void setScanMaintainUser(String  scanMaintainUser) {
        this.scanMaintainUser = scanMaintainUser;
    }

    public void setArchMaintainUser(String  archMaintainUser) {
        this.archMaintainUser = archMaintainUser;
    }

    public void setCompanyOpt(String companyOpt) {
        this.companyOpt = companyOpt;
    }

    /**
     * ���ܣ������ʲ��̵��¼���� ����̵�����
     *
     * @param lastChkDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastChkDate(String lastChkDate) throws CalendarException {
        this.lastChkDate.setCalendarValue(lastChkDate);
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� ɨ���ύ�ķ������
     *
     * @return String
     */
    public String getScanItemCode() {
        return this.scanItemCode;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� ɨ���ύ��������
     *
     * @return String
     */
    public String getScanResponsibilityUser() {
        return this.scanResponsibilityUser;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� ɨ���ύ�����β���
     *
     * @return String
     */
    public String getScanResponsibilityDept() {
        return this.scanResponsibilityDept;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� �鵵�������
     *
     * @return String
     */
    public String getArchItemCode() {
        return this.archItemCode;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� �鵵������
     *
     * @return String
     */
    public String getArchResponsibilityUser() {
        return this.archResponsibilityUser;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� �鵵���β���
     *
     * @return String
     */
    public String getArchResponsibilityDept() {
        return this.archResponsibilityDept;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� �Ƿ�鵵����;��
     *
     * @return String
     */
    public String getArchToTempInv() {
        return this.archToTempInv;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� �ص�������;��ԭ��
     *
     * @return String
     */
    public String getAddressDiffReason() {
        return this.addressDiffReason;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� �豸������ԭ��
     *
     * @return String
     */
    public String getItemCodeDiffReason() {
        return this.itemCodeDiffReason;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� �����˱��ԭ��
     *
     * @return String
     */
    public String getUserDiffReason() {
        return this.userDiffReason;
    }

    /**
     * ���ܣ���ȡ�ʲ��̵��б�--���̵��ʲ���(EAM)���� ���β��ű��ԭ��
     *
     * @return String
     */
    public String getDeptDiffReason() {
        return this.deptDiffReason;
    }

    public String getScanItemName() {
        return scanItemName;
    }

    public String getScanItemSpec() {
        return scanItemSpec;
    }

    public String getScanItemCategory() {
        return scanItemCategory;
    }

    public int getScanOrganizationId() {
        return scanOrganizationId;
    }

    public String getScanResponsibilityDeptName() {
        return scanResponsibilityDeptName;
    }

    public String getScanResponsibilityUserName() {
        return scanResponsibilityUserName;
    }

    public int getChkTimes() {
        return chkTimes;
    }

    public String getLastChkNo() {
        return lastChkNo;
    }


    /**
     * ���ܣ���ȡ�ʲ��̵��¼���� ����̵�����
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastChkDate() throws CalendarException {
        lastChkDate.setCalPattern(getCalPattern());
        return this.lastChkDate;
    }

    public String getScanItemCategoryName() {
        return scanItemCategoryName;
    }

    public String  getScanMaintainUser() {
        return scanMaintainUser;
    }

    public String  getArchMaintainUser() {
        return archMaintainUser;
    }

    public String getCompanyOpt() {
        return companyOpt;
    }

    public SimpleCalendar getScanStartDate() throws CalendarException {
        scanStartDate.setCalPattern(getCalPattern());
        return scanStartDate;
    }

    public void setScanStartDate(String scanStartDate) throws CalendarException {
        this.scanStartDate.setCalendarValue(scanStartDate);
    }

    public SimpleCalendar getArchStartDate() throws CalendarException {
        archStartDate.setCalPattern(getCalPattern());
        return archStartDate;
    }

    public void setArchStartDate(String archStartDate) throws CalendarException {
        this.archStartDate.setCalendarValue(archStartDate);
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getReplaceFlag() {
        return replaceFlag;
    }

    public void setReplaceFlag(String replaceFlag) {
        this.replaceFlag = replaceFlag;
    }

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }

    public String getConstructStatus() {
        return constructStatus;
    }

    public void setConstructStatus(String constructStatus) {
        this.constructStatus = constructStatus;
    }

    public String getLneId() {
        return lneId;
    }

    public void setLneId(String lneId) {
        this.lneId = lneId;
    }

    public String getLneName() {
        return lneName;
    }

    public void setLneName(String lneName) {
        this.lneName = lneName;
    }

    public String getCexId() {
        return cexId;
    }

    public void setCexId(String cexId) {
        this.cexId = cexId;
    }

    public String getCexName() {
        return cexName;
    }

    public void setCexName(String cexName) {
        this.cexName = cexName;
    }

    public String getOpeId() {
        return opeId;
    }

    public void setOpeId(String opeId) {
        this.opeId = opeId;
    }

    public String getOpeName() {
        return opeName;
    }

    public void setOpeName(String opeName) {
        this.opeName = opeName;
    }

    public String getNleId() {
        return nleId;
    }

    public void setNleId(String nleId) {
        this.nleId = nleId;
    }

    public String getNleName() {
        return nleName;
    }

    public void setNleName(String nleName) {
        this.nleName = nleName;
    }

    /**
     * ���ܣ�����鵵ѡ��������
     *
     * @return String
     */
    public String getArchiveOption() {
        StringBuffer archiveOption = new StringBuffer();
        archiveOption.append("<option value=\"\">ѡ���ʵ���</option>");
        archiveOption.append("<option value=\"");
        archiveOption.append(AssetsDictConstant.ARCHIVE_AS_SCAN);
        archiveOption.append("\"");
        if (archiveStatus.equals(AssetsDictConstant.ARCHIVE_AS_SCAN)) {
            archiveOption.append(" selected");
		}
		archiveOption.append(">��ɨ����Ϊ׼</option>");
		archiveOption.append("<option value=\"");
		archiveOption.append(AssetsDictConstant.ARCHIVE_AS_CURR);
		archiveOption.append("\"");
		if (archiveStatus.equals(AssetsDictConstant.ARCHIVE_AS_CURR)) {
			archiveOption.append(" selected");
		}
		archiveOption.append(">��Ŀǰ״̬Ϊ׼</option>");
		return archiveOption.toString();
	}
}
