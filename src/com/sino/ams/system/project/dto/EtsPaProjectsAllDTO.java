package com.sino.ams.system.project.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.calen.SimpleDate;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DateException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ��Ŀά����(EAM) EtsPaProjectsAll</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class EtsPaProjectsAllDTO extends CheckBoxDTO {

    private String projectId = "";
    private String bookTypeCode = "";
    private String name = "";
    private String segment1 = "";
    private String projectType = "";
    private String projectStatusCode = "";
    private Timestamp startDate = null;
    private Timestamp completionDate = null;
    private String enabledFlag = "";
    private String source = "";
    private Timestamp creationDate = null;
    private int createdBy = 0;
    private Timestamp lastUpdateDate = null;
    private int lastUpdateBy = 0;
    private int misProjectId = 0;
    private String projectNumber = "";

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }


    /**
     * ���ܣ�������Ŀά����(EAM)���� ���к�
     * @param projectId String
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� ��Ŀ����
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� ��Ŀ���
     * @param segment1 String
     */
    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� ��Ŀ����
     * @param projectType String
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� ��Ŀ״̬����
     * @param projectStatusCode String
     */
    public void setProjectStatusCode(String projectStatusCode) {
        this.projectStatusCode = projectStatusCode;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� ��Ŀ��ʼ����
     * @param startDate Timestamp
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setStartDate(String startDate) throws CalendarException {
        if (!StrUtil.isEmpty(startDate)) {
            SimpleCalendar cal = new SimpleCalendar(startDate);
            this.startDate = cal.getSQLTimestamp();
        }
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� ��Ŀ�������
     * @param completionDate Timestamp
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCompletionDate(String completionDate) throws CalendarException {
        if (!StrUtil.isEmpty(completionDate)) {
            SimpleCalendar cal = new SimpleCalendar(completionDate);
            this.completionDate = cal.getSQLTimestamp();
        }
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� 'Y'
     * @param enabledFlag String
     */
    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� ��Դ 'MIS'  or 'AMS'
     * @param source String
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� ��������
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
     * ���ܣ�������Ŀά����(EAM)���� ������
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� �ϴ��޸�����
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
     * ���ܣ�������Ŀά����(EAM)���� �ϴ��޸���
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ�������Ŀά����(EAM)���� MIS��ĿID
     * @param misProjectId String
     */
    public void setMisProjectId(int misProjectId) {
        this.misProjectId = misProjectId;
    }


    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ���к�
     * @return String
     */
    public String getProjectId() {
        return this.projectId;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ����
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ���
     * @return String
     */
    public String getSegment1() {
        return this.segment1;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ����
     * @return String
     */
    public String getProjectType() {
        return this.projectType;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ״̬����
     * @return String
     */
    public String getProjectStatusCode() {
        return this.projectStatusCode;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ��ʼ����
     * @return Timestamp
     */
    public Timestamp getStartDate() {
        return this.startDate;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ��Ŀ�������
     * @return Timestamp
     */
    public Timestamp getCompletionDate() {
        return this.completionDate;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� 'Y'
     * @return String
     */
    public String getEnabledFlag() {
        return this.enabledFlag;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ��Դ 'MIS'  or 'AMS'
     * @return String
     */
    public String getSource() {
        return this.source;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ��������
     * @return Timestamp
     */
    public Timestamp getCreationDate() {
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� ������
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� �ϴ��޸�����
     * @return Timestamp
     */
    public Timestamp getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ��Ŀά����(EAM)���� MIS��ĿID
     * @return String
     */
    public int getMisProjectId() {
        return this.misProjectId;
    }

    public String getBookTypeCode() {
		return bookTypeCode;
	}

	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}

    public String getStartDate1() throws DateException {
        String strActiveStartDate = "";
        SimpleDate date = new SimpleDate();
        if (startDate != null) {
            date.setDateValue(startDate);
        }
        strActiveStartDate = date.getDateValue();
        return strActiveStartDate;
    }


    public String getCompletionDate1() throws DateException {
        String strActiveEndDate = "";
        if (completionDate != null) {
            SimpleDate date = new SimpleDate();
            date.setDateValue(completionDate);
            strActiveEndDate = date.getDateValue();
        }
        return strActiveEndDate;
    }

}