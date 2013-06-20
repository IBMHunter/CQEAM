package com.sino.ams.workorder.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ������ʱ��(EAM) EtsWorkorderTmp</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class EtsWorkorderTmpDTO extends CheckBoxDTO {

    private String systemid = "";
    private String workorderBatch = "";
    private String workorderNo = "";
    private String workorderType = "";
    private String workorderObjectNo;
    private SimpleCalendar startDate = null;
    private int implementDays;
    private int groupId;
    private int implementBy;
    private String prjId;
    private SimpleCalendar distributeDate = null;
    private int distributeBy;
    private SimpleCalendar downloadDate = null;
    private int downloadBy;
    private SimpleCalendar scanoverDate = null;
    private int scanoverBy;
    private SimpleCalendar uploadDate = null;
    private int uploadBy;
    private SimpleCalendar checkoverDate = null;
    private int checkoverBy;
    private String responsibilityUser;
    private String differenceReason = "";
    private int organizationId ;
    private String workorderFlag;
    private String remark = "";
    private String actid;
    private String caseid = "";
    private int archflag ;
    private String attribute1 = "";
    private String attribute2 = "";
    private String attribute3 = "";
    private String attribute4 = "";
    private String attribute5 = "";
    private int attribute6 ;
    private int distributeGroup;
    private String attribute7 = "";
    private SimpleCalendar creationDate = null;
    private int createdBy ;
    private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateBy ;

    private String workorderTypeDesc = "";
    private String groupName = "";
    private String workorderObjectCode = "";
    private String workorderObjectName = "";
    private String transObjectCode = "";//��Ǩ
    private String transObjectName = "";//��Ǩ

    private String costCenterCode = "";//�ɱ����Ĵ���
    private String costCenterName = "";//�ɱ���������


    public EtsWorkorderTmpDTO() {
        this.startDate = new SimpleCalendar();
        this.distributeDate = new SimpleCalendar();
        this.downloadDate = new SimpleCalendar();
        this.scanoverDate = new SimpleCalendar();
        this.uploadDate = new SimpleCalendar();
        this.checkoverDate = new SimpleCalendar();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }

    public String getTransObjectCode() {
        return transObjectCode;
    }

    public void setTransObjectCode(String transObjectCode) {
        this.transObjectCode = transObjectCode;
    }

    public String getTransObjectName() {
        return transObjectName;
    }

    public void setTransObjectName(String transObjectName) {
        this.transObjectName = transObjectName;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ϵͳID
     * @param systemid String
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��������
     * @param workorderBatch String
     */
    public void setWorkorderBatch(String workorderBatch) {
        this.workorderBatch = workorderBatch;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ������(����6λ+���к�3λ��������Ψһ)
     * @param workorderNo String
     */
    public void setWorkorderNo(String workorderNo) {
        this.workorderNo = workorderNo;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��������
     * @param workorderType String
     */
    public void setWorkorderType(String workorderType) {
        this.workorderType = workorderType;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��������(����վ����)
     * @param workorderObjectNo String
     */
    public void setWorkorderObjectNo(String workorderObjectNo) {
        this.workorderObjectNo = workorderObjectNo;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ������ʼ����
     * @param startDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setStartDate(String startDate) throws CalendarException {
        if (!StrUtil.isEmpty(startDate)) {
            this.startDate = new SimpleCalendar(startDate);
        }
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param implementDays String
     */
    public void setImplementDays(int implementDays) {
        this.implementDays = implementDays;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��������/�ӵ��������ID
     * @param groupId String
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ����ִ����
     * @param implementBy String
     */
    public void setImplementBy(int implementBy) {
        this.implementBy = implementBy;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param prjId String
     */
    public void setPrjId(String prjId) {
        this.prjId = prjId;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� �·�����
     * @param distributeDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setDistributeDate(String distributeDate) throws CalendarException {
        if (!StrUtil.isEmpty(distributeDate)) {
            this.distributeDate = new SimpleCalendar(distributeDate);
        }
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� �·���
     * @param distributeBy String
     */
    public void setDistributeBy(int distributeBy) {
        this.distributeBy = distributeBy;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��������
     * @param downloadDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setDownloadDate(String downloadDate) throws CalendarException {
        if (!StrUtil.isEmpty(downloadDate)) {
            this.downloadDate = new SimpleCalendar(downloadDate);
        }
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ������
     * @param downloadBy String
     */
    public void setDownloadBy(int downloadBy) {
        this.downloadBy = downloadBy;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ɨ���������
     * @param scanoverDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setScanoverDate(String scanoverDate) throws CalendarException {
        if (!StrUtil.isEmpty(scanoverDate)) {
            this.scanoverDate = new SimpleCalendar(scanoverDate);
        }
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ɨ����
     * @param scanoverBy String
     */
    public void setScanoverBy(int scanoverBy) {
        this.scanoverBy = scanoverBy;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� �ϴ�����/ʵ���������
     * @param uploadDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setUploadDate(String uploadDate) throws CalendarException {
        if (!StrUtil.isEmpty(uploadDate)) {
            this.uploadDate = new SimpleCalendar(uploadDate);
        }
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� �ϴ���
     * @param uploadBy String
     */
    public void setUploadBy(int uploadBy) {
        this.uploadBy = uploadBy;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��ʵ����
     * @param checkoverDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCheckoverDate(String checkoverDate) throws CalendarException {
        if (!StrUtil.isEmpty(checkoverDate)) {
            this.checkoverDate = new SimpleCalendar(checkoverDate);
        }
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��ʵ��
     * @param checkoverBy String
     */
    public void setCheckoverBy(int checkoverBy) {
        this.checkoverBy = checkoverBy;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ������
     * @param responsibilityUser String
     */
    public void setResponsibilityUser(String responsibilityUser) {
        this.responsibilityUser = responsibilityUser;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ����ԭ��
     * @param differenceReason String
     */
    public void setDifferenceReason(String differenceReason) {
        this.differenceReason = differenceReason;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��֯
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ����״̬(10:������11:���·���12:�����أ�13:����ɣ�14:�Ѻ�ʵ��15:��ȡ��)
     * @param workorderFlag String
     */
    public void setWorkorderFlag(String workorderFlag) {
        this.workorderFlag = workorderFlag;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��ע
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ACTIVE ID
     * @param actid String
     */
    public void setActid(String actid) {
        this.actid = actid;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� CASEID
     * @param caseid String
     */
    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� �鵵��־(1:�ѹ鵵��0:δ�鵵)
     * @param archflag String
     */
    public void setArchflag(int archflag) {
        this.archflag = archflag;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param attribute1 String
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param attribute2 String
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param attribute3 String
     */
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param attribute4 String
     */
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param attribute5 String
     */
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param attribute6 String
     */
    public void setAttribute6(int attribute6) {
        this.attribute6 = attribute6;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param distributeGroup String
     */
    public void setDistributeGroup(int distributeGroup) {
        this.distributeGroup = distributeGroup;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� null
     * @param attribute7 String
     */
    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ��������
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        if (!StrUtil.isEmpty(creationDate)) {
            this.creationDate = new SimpleCalendar(creationDate);
        }
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� ������
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� �ϴ��޸�����
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        if (!StrUtil.isEmpty(lastUpdateDate)) {
            this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
        }
    }

    /**
     * ���ܣ����ù�����ʱ��(EAM)���� �ϴ��޸���
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }


    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ϵͳID
     * @return String
     */
    public String getSystemid() {
        return this.systemid;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��������
     * @return String
     */
    public String getWorkorderBatch() {
        return this.workorderBatch;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ������(����6λ+���к�3λ��������Ψһ)
     * @return String
     */
    public String getWorkorderNo() {
        return this.workorderNo;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��������
     * @return String
     */
    public String getWorkorderType() {
        return this.workorderType;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��������(����վ����)
     * @return String
     */
    public String getWorkorderObjectNo() {
        return this.workorderObjectNo;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ������ʼ����
     * @return SimpleCalendar
     */
    public SimpleCalendar getStartDate() {
        return this.startDate;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public int getImplementDays() {
        return this.implementDays;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��������/�ӵ��������ID
     * @return String
     */
    public int getGroupId() {
        return this.groupId;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ����ִ����
     * @return String
     */
    public int getImplementBy() {
        return this.implementBy;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public String getPrjId() {
        return this.prjId;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� �·�����
     * @return SimpleCalendar
     */
    public SimpleCalendar getDistributeDate() {
        return this.distributeDate;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� �·���
     * @return String
     */
    public int getDistributeBy() {
        return this.distributeBy;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��������
     * @return SimpleCalendar
     */
    public SimpleCalendar getDownloadDate() {
        return this.downloadDate;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ������
     * @return String
     */
    public int getDownloadBy() {
        return this.downloadBy;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ɨ���������
     * @return SimpleCalendar
     */
    public SimpleCalendar getScanoverDate() {
        return this.scanoverDate;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ɨ����
     * @return String
     */
    public int getScanoverBy() {
        return this.scanoverBy;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� �ϴ�����/ʵ���������
     * @return SimpleCalendar
     */
    public SimpleCalendar getUploadDate() {
        return this.uploadDate;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� �ϴ���
     * @return String
     */
    public int getUploadBy() {
        return this.uploadBy;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��ʵ����
     * @return SimpleCalendar
     */
    public SimpleCalendar getCheckoverDate() {
        return this.checkoverDate;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��ʵ��
     * @return String
     */
    public int getCheckoverBy() {
        return this.checkoverBy;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ������
     * @return String
     */
    public String getResponsibilityUser() {
        return this.responsibilityUser;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ����ԭ��
     * @return String
     */
    public String getDifferenceReason() {
        return this.differenceReason;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��֯
     * @return String
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ����״̬(10:������11:���·���12:�����أ�13:����ɣ�14:�Ѻ�ʵ��15:��ȡ��)
     * @return String
     */
    public String getWorkorderFlag() {
        return this.workorderFlag;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��ע
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ACTIVE ID
     * @return String
     */
    public String getActid() {
        return this.actid;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� CASEID
     * @return String
     */
    public String getCaseid() {
        return this.caseid;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� �鵵��־(1:�ѹ鵵��0:δ�鵵)
     * @return String
     */
    public int getArchflag() {
        return this.archflag;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public String getAttribute1() {
        return this.attribute1;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public String getAttribute2() {
        return this.attribute2;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public String getAttribute3() {
        return this.attribute3;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public String getAttribute4() {
        return this.attribute4;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public String getAttribute5() {
        return this.attribute5;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public int getAttribute6() {
        return this.attribute6;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public int getDistributeGroup() {
        return this.distributeGroup;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� null
     * @return String
     */
    public String getAttribute7() {
        return this.attribute7;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ��������
     * @return SimpleCalendar
     */
    public SimpleCalendar getCreationDate() {
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� ������
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� �ϴ��޸�����
     * @return SimpleCalendar
     */
    public SimpleCalendar getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ������ʱ��(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public String getWorkorderTypeDesc() {
        return workorderTypeDesc;
    }

    public void setWorkorderTypeDesc(String workorderTypeDesc) {
        this.workorderTypeDesc = workorderTypeDesc;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getWorkorderObjectCode() {
        return workorderObjectCode;
    }

    public void setWorkorderObjectCode(String workorderObjectCode) {
        this.workorderObjectCode = workorderObjectCode;
    }

    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    public String getCostCenterCode() {
        return costCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        this.costCenterCode = costCenterCode;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }
}