package com.sino.nm.spare2.dto;


import com.sino.base.dto.CheckBoxDTO;

/**
 * <p>Title: ����ҵ����ϸ��(AMS) AmsItemTransD</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsItemTransDDTO extends CheckBoxDTO {

    private String transId = "";
    private String lineId = "";
    private String detailId = "";
    private int organizationId = 0;
    private String itemCode = "";
    private int quantity = 0;
    private int confirmQuantity = 0;
    private int curOnhandQty =0;
    private int onhandQty = 0;
    private String objectNo = "";

    public AmsItemTransDDTO() {
        super();


    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ������ID
     * @param transId String
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ID
     * @param lineId String
     */
    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ϸID
     * @param detailId String
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� OU
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� �豸����
     * @param itemCode String
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ����
     * @param quantity String
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ȷ������
     * @param confirmQuantity String
     */
    public void setConfirmQuantity(int confirmQuantity) {
        this.confirmQuantity = confirmQuantity;
    }

    /**
     * ���ܣ����ñ���ҵ����ϸ��(AMS)���� ��ǰ���������
     * @param curOnhandQty String
     */
    public void setCurOnhandQty(int curOnhandQty) {
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
    public int getConfirmQuantity() {
        return this.confirmQuantity;
    }

    /**
     * ���ܣ���ȡ����ҵ����ϸ��(AMS)���� ��ǰ���������
     * @return String
     */
    public int getCurOnhandQty() {
        return this.curOnhandQty;
    }


    public String getObjectNo() {
        return objectNo;
    }

    public void setObjectNo(String objectNo) {
        this.objectNo = objectNo;
    }

    public int getOnhandQty() {
        return onhandQty;
    }

    public void setOnhandQty(int onhandQty) {
        this.onhandQty = onhandQty;
    }
}