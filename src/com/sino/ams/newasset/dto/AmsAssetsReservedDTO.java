package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
 * <p>Title: �ʲ��������� AmsAssetsReserved</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsAssetsReservedDTO extends CommonRecordDTO {

    private String transId = null;
    private SimpleCalendar reservedDate = null;
    private String assetId = null;
    private String barcode = "";

    public AmsAssetsReservedDTO() {
        super();
        this.reservedDate = new SimpleCalendar();
    }

    /**
     * ���ܣ������ʲ������������� ����ID
     * @param transId String
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }

    /**
     * ���ܣ������ʲ������������� ��������
     * @param reservedDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setReservedDate(String reservedDate) throws CalendarException {
        this.reservedDate.setCalendarValue(reservedDate);
    }

    /**
     * ���ܣ������ʲ������������� �ʲ�ID
     * @param assetId String
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    /**
     * ���ܣ������ʲ������������� ��ǩ��
     * @param barcode String
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    /**
     * ���ܣ���ȡ�ʲ������������� ����ID
     * @return String
     */
    public String getTransId() {
        return this.transId;
    }

    /**
     * ���ܣ���ȡ�ʲ������������� ��������
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getReservedDate() throws CalendarException {
        reservedDate.setCalPattern(getCalPattern());
        return this.reservedDate;
    }

    /**
     * ���ܣ���ȡ�ʲ������������� �ʲ�ID
     * @return String
     */
    public String getAssetId() {
        return this.assetId;
    }

    /**
     * ���ܣ���ȡ�ʲ������������� ��ǩ��
     * @return String
     */
    public String getBarcode() {
        return this.barcode;
    }
}
