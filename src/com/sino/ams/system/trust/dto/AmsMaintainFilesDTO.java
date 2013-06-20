package com.sino.ams.system.trust.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: ��ά��˾����ļ� AmsMaintainFiles</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsMaintainFilesDTO extends CheckBoxDTO{

	private int systemId;
	private String fileDescription = "";
	private String filePath = "";
    private String  companyId;
	private Timestamp creationDate = null;
	private int createdBy;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy;
	private String fileName = "";

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String  getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String  companyId) {
        this.companyId = companyId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

	/**
	 * ���ܣ����ô�ά��˾����ļ����� �ļ�����
	 * @param fileDescription String
	 */
	public void setFileDescription(String fileDescription){
		this.fileDescription = fileDescription;
	}

	/**
	 * ���ܣ����ô�ά��˾����ļ����� �ļ��洢·��
	 * @param filePath String
	 */
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	/**
	 * ���ܣ����ô�ά��˾����ļ����� ��������
	 * @param creationDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		if(!StrUtil.isEmpty(creationDate)){
			SimpleCalendar cal = new SimpleCalendar(creationDate);
			this.creationDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ����ô�ά��˾����ļ����� �ϴ��޸�����
	 * @param lastUpdateDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		if(!StrUtil.isEmpty(lastUpdateDate)){
			SimpleCalendar cal = new SimpleCalendar(lastUpdateDate);
			this.lastUpdateDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ����ô�ά��˾����ļ����� �ļ���
	 * @param fileName String
	 */
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	/**
	 * ���ܣ���ȡ��ά��˾����ļ����� �ļ�����
	 * @return String
	 */
	public String getFileDescription(){
		return this.fileDescription;
	}

	/**
	 * ���ܣ���ȡ��ά��˾����ļ����� �ļ��洢·��
	 * @return String
	 */
	public String getFilePath(){
		return this.filePath;
	}

	/**
	 * ���ܣ���ȡ��ά��˾����ļ����� ��������
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ��ά��˾����ļ����� �ϴ��޸�����
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ��ά��˾����ļ����� �ļ���
	 * @return String
	 */
	public String getFileName(){
		return this.fileName;
	}

}