package com.sino.ams.workorder.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.math.AdvancedNumber;

/**
* <p>Title: �����ļ�����(EAM) EtsOrderFiles</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsOrderFilesDTO extends CheckBoxDTO{

	private AdvancedNumber systemid = null;
	private String workorderBatch = "";
	private String titel = "";
	private String fileName = "";
	private String filePath = "";
	private String fileType = "";
	private AdvancedNumber isTruefile = null;
	private String remark = "";
	private AdvancedNumber handler = null;
	private SimpleCalendar recordDate = null;

	public EtsOrderFilesDTO() {
		super();
		this.recordDate = new SimpleCalendar();

		this.systemid = new AdvancedNumber();
		this.isTruefile = new AdvancedNumber();
		this.handler = new AdvancedNumber();
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� ��ˮ��
	 * @param systemid AdvancedNumber
	 */
	public void setSystemid(AdvancedNumber systemid){
		this.systemid = systemid;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� ��������
	 * @param workorderBatch String
	 */
	public void setWorkorderBatch(String workorderBatch){
		this.workorderBatch = workorderBatch;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� ����
	 * @param titel String
	 */
	public void setTitel(String titel){
		this.titel = titel;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� �ļ���
	 * @param fileName String
	 */
	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� �ļ�����·��
	 * @param filePath String
	 */
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� �ļ�����
	 * @param fileType String
	 */
	public void setFileType(String fileType){
		this.fileType = fileType;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� �����ļ�(1��ʾ�����ļ���0 ��ʾ�޸��ļ�
)
	 * @param isTruefile AdvancedNumber
	 */
	public void setIsTruefile(AdvancedNumber isTruefile){
		this.isTruefile = isTruefile;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� �ύ��
	 * @param handler AdvancedNumber
	 */
	public void setHandler(AdvancedNumber handler){
		this.handler = handler;
	}

	/**
	 * ���ܣ����ù����ļ�����(EAM)���� �ύ����
	 * @param recordDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setRecordDate(String recordDate) throws CalendarException{
		this.recordDate.setCalendarValue(recordDate);
	}


	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� ��ˮ��
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getSystemid() {
		return this.systemid;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� ��������
	 * @return String
	 */
	public String getWorkorderBatch() {
		return this.workorderBatch;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� ����
	 * @return String
	 */
	public String getTitel() {
		return this.titel;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� �ļ���
	 * @return String
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� �ļ�����·��
	 * @return String
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� �ļ�����
	 * @return String
	 */
	public String getFileType() {
		return this.fileType;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� �����ļ�(1��ʾ�����ļ���0 ��ʾ�޸��ļ�
)
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getIsTruefile() {
		return this.isTruefile;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� �ύ��
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getHandler() {
		return this.handler;
	}

	/**
	 * ���ܣ���ȡ�����ļ�����(EAM)���� �ύ����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getRecordDate() throws CalendarException {
		recordDate.setCalPattern(getCalPattern());
		return this.recordDate;
	}

}