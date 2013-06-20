package com.sino.ams.newasset.dto;

import com.sino.base.util.StrUtil;

/**
 * <p>Title: �豸�ص�䶯��ʷ��(EAM) AmsItemInfoHistory</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsItemInfoHistoryDTO extends AmsAssetsAddressVDTO {

    private String historyId = "";
    private String orderNo = "";
    private String orderCategory = "";
    private String orderDtlUrl = "";

    public AmsItemInfoHistoryDTO() {
        super();
    }

    /**
     * ���ܣ������豸�ص�䶯��ʷ��(EAM)���� ��ʷID
     * @param historyId String
     */
    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    /**
     * ���ܣ������豸�ص�䶯��ʷ��(EAM)���� ���ݺ�(�洢�����š���浥�ݺ��Լ��ʲ����ݺ�)
     * @param orderNo String
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * ���ܣ������豸�ص�䶯��ʷ��(EAM)���� �������ͣ�1Ϊ������2Ϊ��浥�ݣ�3Ϊ�ʲ�����
     * @param orderCategory String
     */
    public void setOrderCategory(String orderCategory) {
        this.orderCategory = orderCategory;
    }

    /**
     * ���ܣ������豸�ص�䶯��ʷ��(EAM)���� ������ϸ��ϢURL
     * @param orderDtlUrl String
     */
    public void setOrderDtlUrl(String orderDtlUrl) {
        this.orderDtlUrl = orderDtlUrl;
    }


    /**
     * ���ܣ���ȡ�豸�ص�䶯��ʷ��(EAM)���� ��ʷID
     * @return String
     */
    public String getHistoryId() {
        return this.historyId;
    }

    /**
     * ���ܣ���ȡ�豸�ص�䶯��ʷ��(EAM)���� ���ݺ�(�洢�����š���浥�ݺ��Լ��ʲ����ݺ�)
     * @return String
     */
    public String getOrderNo() {
        return this.orderNo;
    }

    /**
     * ���ܣ���ȡ�豸�ص�䶯��ʷ��(EAM)���� �������ͣ�1Ϊ������2Ϊ��浥�ݣ�3Ϊ�ʲ�����
     * @return String
     */
    public String getOrderCategory() {
        return this.orderCategory;
    }

    /**
     * ���ܣ���ȡ�豸�ص�䶯��ʷ��(EAM)���� ������ϸ��ϢURL
     * @return String
     */
    public String getOrderDtlUrl() {
        return this.orderDtlUrl;
    }

    /**
     * ���ܣ��жϸ�DTO�����������Ƿ����д���豸�䶯��ʷ
     * @return boolean
     */
    public boolean needLogHistory() {
        String logValue = getItemCode() + getAddressId() +
                          getResponsibilityUser() + getResponsibilityDept();
        return!StrUtil.isEmpty(logValue);
    }
}
