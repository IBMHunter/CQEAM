package com.sino.ams.spare.check.dto;

import com.sino.base.SinoBaseObject;
import com.sino.base.math.AdvancedNumber;

/**
* <p>Title: �����̵��б�(AMS) AmsItemCheckLine</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsItemCheckLineDTO extends SinoBaseObject {

	private AdvancedNumber headerId = null;
	private AdvancedNumber lineId = null;
	private AdvancedNumber systemid = null;
	private String systemStatus = "";
	private String scanStatus = "";
	private String archiveStatus = "";
	private String remark = "";
	private String barcode = "";
	private String archiveRemark = "";
     private String itemName="";
    private String itemSpec="";
     private String itemQty="";

    public AmsItemCheckLineDTO() {
		super();

		this.headerId = new AdvancedNumber();
		this.lineId = new AdvancedNumber();
		this.systemid = new AdvancedNumber();
	}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    /**
	 * ���ܣ����ñ����̵��б�(AMS)���� ͷID
	 * @param headerId AdvancedNumber
	 */
	public void setHeaderId(AdvancedNumber headerId){
		this.headerId = headerId;
	}

	/**
	 * ���ܣ����ñ����̵��б�(AMS)���� �̵���ID
	 * @param lineId AdvancedNumber
	 */
	public void setLineId(AdvancedNumber lineId){
		this.lineId = lineId;
	}

	/**
	 * ���ܣ����ñ����̵��б�(AMS)���� �豸ID
	 * @param systemid AdvancedNumber
	 */
	public void setSystemid(AdvancedNumber systemid){
		this.systemid = systemid;
	}

	/**
	 * ���ܣ����ñ����̵��б�(AMS)���� ϵͳ״̬
	 * @param systemStatus String
	 */
	public void setSystemStatus(String systemStatus){
		this.systemStatus = systemStatus;
	}

	/**
	 * ���ܣ����ñ����̵��б�(AMS)���� ɨ��״̬
	 * @param scanStatus String
	 */
	public void setScanStatus(String scanStatus){
		this.scanStatus = scanStatus;
	}

	/**
	 * ���ܣ����ñ����̵��б�(AMS)���� �鵵״̬
	 * @param archiveStatus String
	 */
	public void setArchiveStatus(String archiveStatus){
		this.archiveStatus = archiveStatus;
	}

	/**
	 * ���ܣ����ñ����̵��б�(AMS)���� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ����ñ����̵��б�(AMS)���� ��ǩ��
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ����ñ����̵��б�(AMS)���� ��ʵ��ע(0:��ɨ����Ϊ׼��1:��Ŀǰ״̬Ϊ׼)
	 * @param archiveRemark String
	 */
	public void setArchiveRemark(String archiveRemark){
		this.archiveRemark = archiveRemark;
	}


	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� ͷID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getHeaderId() {
		return this.headerId;
	}

	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� �̵���ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getLineId() {
		return this.lineId;
	}

	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� �豸ID
	 * @return AdvancedNumber
	 */
	public AdvancedNumber getSystemid() {
		return this.systemid;
	}

	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� ϵͳ״̬
	 * @return String
	 */
	public String getSystemStatus() {
		return this.systemStatus;
	}

	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� ɨ��״̬
	 * @return String
	 */
	public String getScanStatus() {
		return this.scanStatus;
	}

	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� �鵵״̬
	 * @return String
	 */
	public String getArchiveStatus() {
		return this.archiveStatus;
	}

	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� ��ǩ��
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ�����̵��б�(AMS)���� ��ʵ��ע(0:��ɨ����Ϊ׼��1:��Ŀǰ״̬Ϊ׼)
	 * @return String
	 */
	public String getArchiveRemark() {
		return this.archiveRemark;
	}

}