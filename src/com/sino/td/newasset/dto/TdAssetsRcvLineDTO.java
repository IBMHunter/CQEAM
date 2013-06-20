package com.sino.td.newasset.dto;

/**
 * <p>Title: �ʲ����������б�(���ڲ��ż�͹�˾���ʲ�����) AmsAssetsRcvLine</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class TdAssetsRcvLineDTO extends TdAssetsTransLineDTO {

    private String receiveHeaderId = "";
    private String receiveLineId = "";
    private String transLineId = "";

    public TdAssetsRcvLineDTO() {
        super();
    }

    /**
     * ���ܣ������ʲ����������б�(���ڲ��ż�͹�˾���ʲ�����)���� ���յ���ID
     * @param receiveLineId String
     */
    public void setReceiveLineId(String receiveLineId) {
        this.receiveLineId = receiveLineId;
    }

    /**
     * ���ܣ������ʲ����������б�(���ڲ��ż�͹�˾���ʲ�����)���� ��������ID
     * @param transLineId String
     */
    public void setTransLineId(String transLineId) {
        this.transLineId = transLineId;
    }

    public void setReceiveHeaderId(String receiveHeaderId) {
        this.receiveHeaderId = receiveHeaderId;
    }

    /**
     * ���ܣ���ȡ�ʲ����������б�(���ڲ��ż�͹�˾���ʲ�����)���� ���յ���ID
     * @return String
     */
    public String getReceiveLineId() {
        return this.receiveLineId;
    }

    /**
     * ���ܣ���ȡ�ʲ����������б�(���ڲ��ż�͹�˾���ʲ�����)���� ��������ID
     * @return String
     */
    public String getTransLineId() {
        return this.transLineId;
    }

    public String getReceiveHeaderId() {
        return receiveHeaderId;
    }
}

