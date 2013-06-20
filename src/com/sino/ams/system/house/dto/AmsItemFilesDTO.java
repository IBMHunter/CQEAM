package com.sino.ams.system.house.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: �豸��ظ���(EAM) AmsItemFiles</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsItemFilesDTO extends CheckBoxDTO{

//	private String barcodeNo = "";
    private String barcode ="";

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    private String fileDesc = "";
	private String filePath = "";
	private String systemId = "";
	private SimpleCalendar creationDate = null;
	private String createdBy = "";
	private SimpleCalendar lastUpdateDate = null;
	private String lastUpdateBy = "";


	/**
	 * ���ܣ������豸��ظ���(EAM)���� ��ǩ��
	 * @param barcodeNo String
	 */
//	public void setBarcodeNo(String barcodeNo){
//		this.barcodeNo = barcodeNo;
//	}

	/**
	 * ���ܣ������豸��ظ���(EAM)���� ��������
	 * @param fileDesc String
	 */
	public void setFileDesc(String fileDesc){
		this.fileDesc = fileDesc;
	}

	/**
	 * ���ܣ������豸��ظ���(EAM)���� �洢·��
	 * @param filePath String
	 */
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	/**
	 * ���ܣ������豸��ظ���(EAM)���� ���к�
	 * @param systemId String
	 */
	public void setSystemId(String systemId){
		this.systemId = systemId;
	}

	/**
	 * ���ܣ������豸��ظ���(EAM)���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		if(!StrUtil.isEmpty(creationDate)){
			this.creationDate = new SimpleCalendar(creationDate);
		}
	}

	/**
	 * ���ܣ������豸��ظ���(EAM)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ������豸��ظ���(EAM)���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		if(!StrUtil.isEmpty(lastUpdateDate)){
			this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
		}
	}

	/**
	 * ���ܣ������豸��ظ���(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(String lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡ�豸��ظ���(EAM)���� ��ǩ��
	 * @return String
	 */
//	public String getBarcodeNo(){
//		return this.barcodeNo;
//	}

	/**
	 * ���ܣ���ȡ�豸��ظ���(EAM)���� ��������
	 * @return String
	 */
	public String getFileDesc(){
		return this.fileDesc;
	}

	/**
	 * ���ܣ���ȡ�豸��ظ���(EAM)���� �洢·��
	 * @return String
	 */
	public String getFilePath(){
		return this.filePath;
	}

	/**
	 * ���ܣ���ȡ�豸��ظ���(EAM)���� ���к�
	 * @return String
	 */
	public String getSystemId(){
		return this.systemId;
	}

	/**
	 * ���ܣ���ȡ�豸��ظ���(EAM)���� ��������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ�豸��ظ���(EAM)���� ������
	 * @return String
	 */
	public String getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ�豸��ظ���(EAM)���� �ϴ��޸�����
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�豸��ظ���(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getLastUpdateBy(){
		return this.lastUpdateBy;
	}

}