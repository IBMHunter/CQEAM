package com.sino.ams.web.item.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: ʵ���ʲ�ά�޳ɱ�(EAM) EtsItemFixfee</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsItemFixfeeDTO extends CheckBoxDTO{

	private String systemId = "";
	private String barcode = "";
	private SimpleCalendar fixDate = null;
    private String amount = "";
	private String fixNo = "";
	private String attribute1 = "";
	private String attribute2 = "";
	private String remark = "";
	private Timestamp creationDate = null;
	private int createdBy;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy;
    private String itemName="";
    private String fromDate="";
    private String toDate="";
    private String itemSpec="";
    private String company="";

     public EtsItemFixfeeDTO(){
         this.fixDate=new SimpleCalendar();
     }

   public SimpleCalendar getFixDate() throws CalendarException {
        fixDate.setCalPattern(getCalPattern());
        return this.fixDate;
    }

    public void setFixDate(String fixDate) throws CalendarException {
        this.fixDate.setCalendarValue(fixDate);
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }







    /**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� ���к�
	 * @param systemId String
	 */
	public void setSystemId(String systemId){
		this.systemId = systemId;
	}





	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� ά�޷���
	 * @param amount String
	 */
	public void setAmount(String amount){
		this.amount = amount;
	}

	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� null
	 * @param fixNo String
	 */
	public void setFixNo(String fixNo){
		this.fixNo = fixNo;
	}

	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� null
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� null
	 * @param attribute2 String
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}

	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� ��������
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
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� �ϴ��޸�����
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
	 * ���ܣ�����ʵ���ʲ�ά�޳ɱ�(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� ���к�
	 * @return String
	 */
	public String getSystemId(){
		return this.systemId;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� ��ǩ��
	 * @return String
	 */

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� ά�޷���
	 * @return String
	 */
	public String getAmount(){
		return this.amount;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� null
	 * @return String
	 */
	public String getFixNo(){
		return this.fixNo;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� null
	 * @return String
	 */
	public String getAttribute1(){
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� null
	 * @return String
	 */
	public String getAttribute2(){
		return this.attribute2;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� ��ע
	 * @return String
	 */
	public String getRemark(){
		return this.remark;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� ��������
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� ������
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� �ϴ��޸�����
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡʵ���ʲ�ά�޳ɱ�(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

}