package com.sino.ams.workorder.dto;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ��������(EAM) EtsWorkorder</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class EtsWorkorderDTO extends CommonRecordDTO {

    private String systemid = "";
    private String workorderBatch = "";//��������
    private String workorderNo = "";//������
    private String workorderType = "";
    private String workorderObjectNo;
    private int implementDays;
    private int groupId = 0;
    private int implementBy=-1;
    private String implementUser = "";
    private String prjId = "" ;
    private String prjName = "";
    private SimpleCalendar distributeDate = null;
    private int distributeBy;
    private String distributeUser = "";
    private SimpleCalendar downloadDate = null;
    private int downloadBy;
    private String downloadUser = "";
    private SimpleCalendar scanoverDate = null;
    private int scanoverBy;
    private String scanoverUser = "";
    private SimpleCalendar uploadDate = null;
    private int uploadBy;
    private SimpleCalendar checkoverDate = null;
    private int checkoverBy;
    private String checkoverUser = "";
    private String responsibilityUser = "";
    private String differenceReason = "";
    private int organizationId;
    private String workorderFlag = "";//����״̬
    private String workorderFlagDesc = "";//����״̬˵��
    private String remark = "";
    private String actid;
    private String caseid = "";
    private int archflag;
    private String attribute1 = "";
    private String attribute2 = "";
    private String attribute3 = "";
    private String attribute4 = "";
    private String attribute5 = "";
    private int attribute6;
    private int distributeGroup;
    private String attribute7 = "";
    private String createUser = "";
    private String lastUpdateUser = "";
    private String executeUserName = "";
    private String loginName = "";
    private String workorderTypeDesc = "";
    private String groupName = "";
    private String workorderObjectCode = "";
    private String workorderObjectName = "";
    private String workorderObjectLocation = "";
    private String transObjectCode = "";//��Ǩ
    private String transObjectName = "";//��Ǩ
    private String workorderBatchName = "";
    private String queryType = "";//��ѯ����

    private String costCenterCode = "";//�ɱ����Ĵ���
    private String costCenterName = "";//�ɱ���������

    private String contentCode = "";//�ʲ�Ŀ¼����
    private String contentName = "";//�ʲ�Ŀ¼����
    
    private String projectCode = "";//���̱���
    private String projectName = "";//��������

    /**
     * ******************added by mshtang*****************************
     */
    private String objectCategory = "";//�ص�����
    private String maintainCompany = "";//��ά��˾
    private String objectCategoryOpt = "";//�ص�����б�
    private String maintainComOpt = "";//��ά��˾�б�
    private String company = "";//��˾
    private String companyOpt = "";//��˾�б�
    private String groupOpt = "";//���������
    private String exportType = "";//Excel��������
    private String itemCategory = "";//�豸רҵ
    private String itemName = "";//�豸����
    private String itemSpec = "";//����ͺ�
    private String deptCode = "";//���β��Ŵ���
    private String deptName = "";//���β�������
    private String deptOpt = "";//�����б�
    private String userName = "";//������
    private String userId = "";//������Id
    private String employeeNumber = "";//������Number
    private String diffProcessDesc = "";//���촦������
    private String barcode = "";  //��ǩ��
    private String opinionType="";  //���������ж�
    private List partBarcode=new ArrayList();
    
    private String checkedBarcode = ""; // ����ѡ������
	/**
     * ******************added by mshtang*****************************
     */
    private String financeProp = "ASSETS";

    public EtsWorkorderDTO() {
        this.distributeDate = new SimpleCalendar();
        this.downloadDate = new SimpleCalendar();
        this.scanoverDate = new SimpleCalendar();
        this.uploadDate = new SimpleCalendar();
        this.checkoverDate = new SimpleCalendar();
    }

    public String getExecuteUserName() {
        return executeUserName;
    }

    public String getUserId() {
		return userId;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }

    public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param systemid String
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param workorderBatch String
     */
    public void setWorkorderBatch(String workorderBatch) {
        this.workorderBatch = workorderBatch;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param workorderNo String
     */
    public void setWorkorderNo(String workorderNo) {
        this.workorderNo = workorderNo;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param workorderType String
     */
    public void setWorkorderType(String workorderType) {
        this.workorderType = workorderType;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param workorderObjectNo String
     */
    public void setWorkorderObjectNo(String workorderObjectNo) {
        this.workorderObjectNo = workorderObjectNo;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     *
     * @param startDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
//    public void setStartDate(String startDate) throws CalendarException {
//        if (!StrUtil.isEmpty(startDate)) {
//            this.startDate = new SimpleCalendar(startDate);
//        }
//    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param implementDays String
     */
    public void setImplementDays(int implementDays) {
        this.implementDays = implementDays;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param groupId String
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param implementBy String
     */
    public void setImplementBy(int implementBy) {
        this.implementBy = implementBy;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param prjId String
     */
    public void setPrjId(String prjId) {
        this.prjId = prjId;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param distributeDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setDistributeDate(String distributeDate) throws CalendarException {
        if (!StrUtil.isEmpty(distributeDate)) {
            this.distributeDate = new SimpleCalendar(distributeDate);
        }
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param distributeBy String
     */
    public void setDistributeBy(int distributeBy) {
        this.distributeBy = distributeBy;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param downloadDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setDownloadDate(String downloadDate) throws CalendarException {
        if (!StrUtil.isEmpty(downloadDate)) {
            this.downloadDate = new SimpleCalendar(downloadDate);
        }
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param downloadBy String
     */
    public void setDownloadBy(int downloadBy) {
        this.downloadBy = downloadBy;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param scanoverDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setScanoverDate(String scanoverDate) throws CalendarException {
        if (!StrUtil.isEmpty(scanoverDate)) {
            this.scanoverDate = new SimpleCalendar(scanoverDate);
        }
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param scanoverBy String
     */
    public void setScanoverBy(int scanoverBy) {
        this.scanoverBy = scanoverBy;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param uploadDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setUploadDate(String uploadDate) throws CalendarException {
        if (!StrUtil.isEmpty(uploadDate)) {
            this.uploadDate = new SimpleCalendar(uploadDate);
        }
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param uploadBy String
     */
    public void setUploadBy(int uploadBy) {
        this.uploadBy = uploadBy;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param checkoverDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCheckoverDate(String checkoverDate) throws CalendarException {
        if (!StrUtil.isEmpty(checkoverDate)) {
            this.checkoverDate = new SimpleCalendar(checkoverDate);
        }
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param checkoverBy String
     */
    public void setCheckoverBy(int checkoverBy) {
        this.checkoverBy = checkoverBy;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param responsibilityUser String
     */
    public void setResponsibilityUser(String responsibilityUser) {
        this.responsibilityUser = responsibilityUser;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param differenceReason String
     */
    public void setDifferenceReason(String differenceReason) {
        this.differenceReason = differenceReason;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
    
	/**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param workorderFlag String
     */
    public void setWorkorderFlag(String workorderFlag) {
        this.workorderFlag = workorderFlag;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param actid String
     */
    public void setActid(String actid) {
        this.actid = actid;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param caseid String
     */
    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param archflag String
     */
    public void setArchflag(int archflag) {
        this.archflag = archflag;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param attribute1 String
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param attribute2 String
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param attribute3 String
     */
    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param attribute4 String
     */
    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param attribute5 String
     */
    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param attribute6 String
     */
    public void setAttribute6(int attribute6) {
        this.attribute6 = attribute6;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param distributeGroup String
     */
    public void setDistributeGroup(int distributeGroup) {
        this.distributeGroup = distributeGroup;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param attribute7 String
     */
    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     *
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
//    public void setCreationDate(String creationDate) throws CalendarException {
//        if (!StrUtil.isEmpty(creationDate)) {
//            this.creationDate = new SimpleCalendar(creationDate);
//        }
//    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     *
     * @param createdBy String
     */
//    public void setCreatedBy(int createdBy) {
//        this.createdBy = createdBy;
//    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
//    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
//        if (!StrUtil.isEmpty(lastUpdateDate)) {
//            this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
//        }
//    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     *
     * @param lastUpdateBy String
     */
//    public void setLastUpdateBy(int lastUpdateBy) {
//        this.lastUpdateBy = lastUpdateBy;
//    }

    /**
     * ���ܣ����ù�������(EAM)���� �ϴ��޸���
     * @param queryType String
     */
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }


    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getSystemid() {
        return this.systemid;
    }


    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getWorkorderBatch() {
        return this.workorderBatch;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getWorkorderNo() {
        return this.workorderNo;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getWorkorderType() {
        return this.workorderType;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getWorkorderObjectNo() {
        return this.workorderObjectNo;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     *
     * @return SimpleCalendar
     */
//    public SimpleCalendar getStartDate() {
//        return this.startDate;
//    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getImplementDays() {
        return this.implementDays;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getGroupId() {
        return this.groupId;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getImplementBy() {
        return this.implementBy;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getPrjId() {
        return this.prjId;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return SimpleCalendar
     */
    public SimpleCalendar getDistributeDate() {
        return this.distributeDate;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getDistributeBy() {
        return this.distributeBy;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return SimpleCalendar
     */
    public SimpleCalendar getDownloadDate() {
        return this.downloadDate;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getDownloadBy() {
        return this.downloadBy;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return SimpleCalendar
     */
    public SimpleCalendar getScanoverDate() {
        return this.scanoverDate;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getScanoverBy() {
        return this.scanoverBy;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return SimpleCalendar
     */
    public SimpleCalendar getUploadDate() {
        return this.uploadDate;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getUploadBy() {
        return this.uploadBy;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return SimpleCalendar
     */
    public SimpleCalendar getCheckoverDate() {
        return this.checkoverDate;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getCheckoverBy() {
        return this.checkoverBy;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getResponsibilityUser() {
        return this.responsibilityUser;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getDifferenceReason() {
        return this.differenceReason;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

	/**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getWorkorderFlag() {
        return this.workorderFlag;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getActid() {
        return this.actid;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getCaseid() {
        return this.caseid;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getArchflag() {
        return this.archflag;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getAttribute1() {
        return this.attribute1;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getAttribute2() {
        return this.attribute2;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getAttribute3() {
        return this.attribute3;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getAttribute4() {
        return this.attribute4;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getAttribute5() {
        return this.attribute5;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getAttribute6() {
        return this.attribute6;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getDistributeGroup() {
        return this.distributeGroup;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
    public String getAttribute7() {
        return this.attribute7;
    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     *
     * @return SimpleCalendar
     */
//    public SimpleCalendar getCreationDate() {
//        return this.creationDate;
//    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     *
     * @return String
     */
//    public int getCreatedBy() {
//        return this.createdBy;
//    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     *
     * @return SimpleCalendar
     */
//    public SimpleCalendar getLastUpdateDate() {
//        return this.lastUpdateDate;
//    }

    /**
     * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
     * @return String
     */
//    public int getLastUpdateBy() {
//        return this.lastUpdateBy;
//    }
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

    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    public String getWorkorderObjectCode() {
        return workorderObjectCode;
    }

    public void setWorkorderObjectCode(String workorderObjectCode) {
        this.workorderObjectCode = workorderObjectCode;
    }

    public String getWorkorderBatchName() {
        return workorderBatchName;
    }

    public void setWorkorderBatchName(String workorderBatchName) {
        this.workorderBatchName = workorderBatchName;
    }

    public String getDistributeUser() {
        return distributeUser;
    }

    public void setDistributeUser(String distributeUser) {
        this.distributeUser = distributeUser;
    }

    public String getDownloadUser() {
        return downloadUser;
    }

    public void setDownloadUser(String downloadUser) {
        this.downloadUser = downloadUser;
    }

    public String getScanoverUser() {
        return scanoverUser;
    }

    public void setScanoverUser(String scanoverUser) {
        this.scanoverUser = scanoverUser;
    }

    public String getCheckoverUser() {
        return checkoverUser;
    }

    public void setCheckoverUser(String checkoverUser) {
        this.checkoverUser = checkoverUser;
    }

    public String getWorkorderFlagDesc() {
        return workorderFlagDesc;
    }

    public void setWorkorderFlagDesc(String workorderFlagDesc) {
        this.workorderFlagDesc = workorderFlagDesc;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
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

    public String getImplementUser() {
        return implementUser;
    }

    public void setImplementUser(String implementUser) {
        this.implementUser = implementUser;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public void setMaintainCompany(String maintainCompany) {
        this.maintainCompany = maintainCompany;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }

    public void setMaintainComOpt(String maintainComOpt) {
        this.maintainComOpt = maintainComOpt;
    }

    public void setObjectCategoryOpt(String objectCategoryOpt) {
        this.objectCategoryOpt = objectCategoryOpt;
    }

    public void setGroupOpt(String groupOpt) {
        this.groupOpt = groupOpt;
    }

    public void setWorkorderObjectLocation(String workorderObjectLocation) {
        this.workorderObjectLocation = workorderObjectLocation;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public void setCompanyOpt(String companyOpt) {
        this.companyOpt = companyOpt;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
    
    public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    
	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setDeptOpt(String deptOpt) {
        this.deptOpt = deptOpt;
    }

    public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getQueryType() {
        return queryType;
    }

    public String getMaintainCompany() {
        return maintainCompany;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public String getMaintainComOpt() {
        return maintainComOpt;
    }

    public String getObjectCategoryOpt() {
        return objectCategoryOpt;
    }

    public String getGroupOpt() {
        return groupOpt;
    }

    public String getWorkorderObjectLocation() {
        return workorderObjectLocation;
    }

    public String getExportType() {
        return exportType;
    }

    public String getCompanyOpt() {
        return companyOpt;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemName() {
		return itemName;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public String getDeptCode() {
        return deptCode;
    }

    public String getDeptOpt() {
        return deptOpt;
    }

    public String getUserName() {
		return userName;
	}

	public String getDeptName() {
        return deptName;
    }

    public String getCompany() {
        return company;
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
    
    public String getDiffProcessDesc() {
		return diffProcessDesc;
	}

	public void setDiffProcessDesc(String diffProcessDesc) {
		this.diffProcessDesc = diffProcessDesc;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCheckedBarcode() {
		return checkedBarcode;
	}

	public void setCheckedBarcode(String checkedBarcode) {
		this.checkedBarcode = checkedBarcode;
	}

	public String getFinanceProp() {
		return financeProp;
	}

	public void setFinanceProp(String financeProp) {
		this.financeProp = financeProp;
	}
	
	public String getOpinionType() {
		return opinionType;
	}

	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}

	public List getPartBarcode() {
		return partBarcode;
	}

	public void setPartBarcode(List partBarcode) {
		this.partBarcode = partBarcode;
	}

	public String getContentCode() {
		return contentCode;
	}

	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
