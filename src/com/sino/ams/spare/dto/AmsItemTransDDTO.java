package com.sino.ams.spare.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: ����ҵ����ϸ��(AMS) AmsItemTransD</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsItemTransDDTO extends CheckBoxDTO{

	private String transId = "";
	private String lineId = "";
	private String detailId = "";
	private int organizationId = -1;
	private String itemCode = "-1";
	private String barcode = "";
	private int quantity = -1;
    private String confirmQuantity = "";
	private String curOnhandQty = "";
    private String serialNo = "";
    private String isAllot="0";
    private String itemName="";
    private String itemSpec="";
    private String troubleReason="";
    private String troubleLoc="";
    private String itemCategory = "";

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getTroubleReason() {
    return troubleReason;
}

    public void setTroubleReason(String troubleReason) {
        this.troubleReason = troubleReason;
    }

    public String getTroubleLoc() {
        return troubleLoc;
    }

    public void setTroubleLoc(String troubleLoc) {
        this.troubleLoc = troubleLoc;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public AmsItemTransDDTO() {
		super();
	}

	/**
	 * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ������ID
	 * @param transId String
	 */
	public void setTransId(String transId){
		this.transId = transId;
	}

	/**
	 * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ID
	 * @param lineId String
	 */
	public void setLineId(String lineId){
		this.lineId = lineId;
	}

	/**
	 * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ϸID
	 * @param detailId String
	 */
	public void setDetailId(String detailId){
		this.detailId = detailId;
	}

	/**
	 * ���ܣ����ñ���ҵ����ϸ��(AMS)���� OU
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ����ñ���ҵ����ϸ��(AMS)���� �豸����
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ����
	 * @param quantity String
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	/**
	 * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ȷ������
	 * @param confirmQuantity String
	 */
	public void setConfirmQuantity(String confirmQuantity){
		this.confirmQuantity = confirmQuantity;
	}

	/**
	 * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ǰ���������
	 * @param curOnhandQty String
	 */
	public void setCurOnhandQty(String curOnhandQty){
		this.curOnhandQty = curOnhandQty;
	}


	/**
	 * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ������ID
	 * @return String
	 */
	public String getTransId() {
		return this.transId;
	}

	/**
	 * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ��ID
	 * @return String
	 */
	public String getLineId() {
		return this.lineId;
	}

	/**
	 * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ��ϸID
	 * @return String
	 */
	public String getDetailId() {
		return this.detailId;
	}

	/**
	 * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� OU
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� �豸����
	 * @return String
	 */
	public String getItemCode() {
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ����
	 * @return String
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ȷ������
	 * @return String
	 */
	public String getConfirmQuantity() {
		return this.confirmQuantity;
	}

	/**
	 * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ��ǰ���������
	 * @return String
	 */
	public String getCurOnhandQty() {
		return this.curOnhandQty;
	}

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getAllot() {
        return isAllot;
    }

    public void setAllot(String allot) {
        isAllot = allot;
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
}