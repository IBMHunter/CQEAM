package com.sino.ams.newasset.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class CustomQryFields {
    private static Map fieldMap = null;

    static {
        fieldMap = new HashMap();
        fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
        fieldMap.put("ASSETS_LOCATION", "�ʲ��ص�");
        fieldMap.put("ASSETS_STATUS", "�ʲ�״̬");
        fieldMap.put("ASSET_ID", "�ʲ�ID");
        fieldMap.put("ASSET_NUMBER", "�ʲ����");
        fieldMap.put("BARCODE", "��ǩ��");
        fieldMap.put("BOOK_TYPE_CODE", "�ʲ��˲�");
        fieldMap.put("COMPANY", "��˾����");
        fieldMap.put("COMPANY_CODE", "��˾����");
        fieldMap.put("COST", "ԭֵ");
        fieldMap.put("COUNTY_NAME", "��������");
        fieldMap.put("CURRENT_UNITS", "����");
        fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
        fieldMap.put("DEPRECIATION_ACCOUNT", "�۾��˻�");
        fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
        fieldMap.put("DEPT_CODE", "���Ŵ���");
        fieldMap.put("DEPT_NAME", "��������");
        fieldMap.put("FA_CATEGORY1", "һ������");
        fieldMap.put("FA_CATEGORY2", "��������");
        fieldMap.put("FA_CATEGORY_CODE", "�ʲ�������");
        fieldMap.put("ITEM_CATEGORY_NAME", "�豸����");
        fieldMap.put("ITEM_NAME", "�豸����");
        fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
        fieldMap.put("ITEM_STATUS", "�豸״̬");
        fieldMap.put("LIFE_IN_YEARS", "��������");
        fieldMap.put("MAINTAIN_USER", " ά����Ա");
        fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
        fieldMap.put("PROJECT_NAME", "��������");
        fieldMap.put("RESPONSIBILITY_USER", "������");
        fieldMap.put("SEGMENT1", "���1����");
        fieldMap.put("SEGMENT2", "���2����");
        fieldMap.put("UNIT_OF_MEASURE", "������λ");
        fieldMap.put("VENDOR_BARCODE", "��������");
        fieldMap.put("VENDOR_NAME", "��������");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
        fieldMap.put("WORKORDER_OBJECT_LOCATION", "���ڵص�");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
    }

    /**
     * ���ܣ���ȡ�ֶ������뺬���ӳ���ϵ
     * @return Map
     */
    public static Map getFieldsMap() {
        return fieldMap;
    }
}
