package com.sino.ams.workorder.util;

import com.sino.ams.constant.DictConstant;

/**
 * User: zhoujs
 * Date: 2008-7-31
 * Time: 20:40:45
 * Function: ��������SQL
 */
public interface OrderDiffSQL {
    public final static String verifySql =
            "SELECT BARCODE,\n" +
                    "       '5' PREV_STATUS,\n" +
                    "       ITEM_STATUS,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       '' BOX_NO,\n" +
                    "       '' NET_UNIT\n" +
                    "  FROM ETS_WORKORDER_INTERFACE EWI\n" +
                    " WHERE EWI.ITEM_STATUS IN ('6', '7')\n" +
                    "   AND EWI.BARCODE IN\n" +
                    "       (SELECT BARCODE\n" +
                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA\n" +
                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    "           AND AOA.OBJECT_NO = '{BASEID}')\n" +
                    "   AND EWI.WORKORDER_NO = '{ORDERID}'";


    public String diffSQL =
//                    "--�����豸���� status��8\n" +
//                    "--�޲��죺ϵͳ���У�������\n" +
//                    "-- ���죺ϵͳ���ޣ�\n" +
//                    "--���촦��������;��\n" +
            "SELECT EWD.BARCODE,\n" +
                    "       '" + DictConstant.SCAN_STATUS_NONE + "' ITEM_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_NONE + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       EWD.BOX_NO,\n" +
                    "       EWD.NET_UNIT,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       ESI.ITEM_CATEGORY,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
                    "       EWD.REMARK \n" +
                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND NOT EXISTS (SELECT 1 FROM ETS_ITEM_INFO EII WHERE EII.BARCODE=EWD.BARCODE)\n" +
                    "   AND EWD.ITEM_STATUS  = '" + DictConstant.SCAN_STATUS_REMAIN + "'\n" +
                    "   AND EWD.WORKORDER_NO = ?\n" +
                    "\n" +
                    " UNION\n" +
//                    "--�����豸���� status��7\n" +
//                    "--�޲��죺ϵͳ��ǰ�ص��ޣ������� ��������\n" +
//                    "--���죺ϵͳ��ǰ�ص���\n" +
//                    "--���촦�������豸����;��\n" +
                    "SELECT EWD.BARCODE,\n" +
                    "       '" + DictConstant.SCAN_STATUS_EXISTS + "' ITEM_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_EXISTS + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       EWD.BOX_NO,\n" +
                    "       EWD.NET_UNIT,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       ESI.ITEM_CATEGORY,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
                    "       EWD.REMARK \n" +
                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND EWD.BARCODE  IN\n" +
                    "       (SELECT BARCODE\n" +
                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA\n" +
                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    "           AND AOA.OBJECT_NO = ?)\n" +
                    "   AND EWD.WORKORDER_NO = ?\n" +
                    "   AND EWD.ITEM_STATUS ='" + DictConstant.SCAN_STATUS_OFFLINE + "'\n" +
                    "\n" +
                    " UNION\n" +
//                    "--δɨ���豸 status��6\n" +
//                    "--�޲��죺ϵͳ��ǰ�ص��ޣ������� ��������\n" +
//                    "--���죺ϵͳ��ǰ�ص���\n" +
//                    "--���촦�������豸����;��\n" +
                    "SELECT EWD.BARCODE,\n" +
                    "       '" + DictConstant.SCAN_STATUS_EXISTS + "' ITEM_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_EXISTS + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       EWD.BOX_NO,\n" +
                    "       EWD.NET_UNIT,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       ESI.ITEM_CATEGORY,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
                    "       EWD.REMARK \n" +
                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND EWD.BARCODE  IN\n" +
                    "       (SELECT BARCODE\n" +
                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA\n" +
                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    "           AND AOA.OBJECT_NO = ?)\n" +
                    "   AND EWD.WORKORDER_NO = ?\n" +
                    "   AND EWD.ITEM_STATUS ='" + DictConstant.SCAN_STATUS_NONE + "'\n" +
                    "\n" +
                    " UNION\n" +
//                    "--ɨ���豸 status��5\n" +
//                    "--�޲��죺ϵͳ��ǰ�ص��У������� ��������\n" +
//                    "--���죺ϵͳ��ǰ�ص���\n" +
//                    "--���촦��\n" +
                    "SELECT EWD.BARCODE,\n" +
                    "       '" + DictConstant.SCAN_STATUS_NONE + "' ITEM_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_NONE + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       EWD.BOX_NO,\n" +
                    "       EWD.NET_UNIT,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       ESI.ITEM_CATEGORY,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
                    "       EWD.REMARK \n" +
                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND EWD.BARCODE NOT  IN\n" +
                    "       (SELECT BARCODE\n" +
                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA\n" +
                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    "           AND AOA.OBJECT_NO = ?)\n" +
                    "   AND EWD.WORKORDER_NO = ?\n" +
                    "   AND EWD.ITEM_STATUS ='" + DictConstant.SCAN_STATUS_EXISTS + "'\n" +
                    "\n" +
                    " UNION\n" +
//                    "--�����豸 status��0\n" +
//                    "--�޲��죺ϵͳ��ǰ�ص��У������� ��������\n" +
//                    "--���죺ϵͳ��ǰ�ص���\n" +
//                    "--���촦��\n" +
                    "SELECT EWD.BARCODE,\n" +
                    "       '" + DictConstant.SCAN_STATUS_NONE + "' ITEM_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_NONE + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       EWD.BOX_NO,\n" +
                    "       EWD.NET_UNIT,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       ESI.ITEM_CATEGORY,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
                    "       EWD.REMARK \n" +
                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND EWD.BARCODE NOT IN\n" +
                    "       (SELECT BARCODE\n" +
                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA\n" +
                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    "           AND AOA.OBJECT_NO = ?)\n" +
                    "   AND EWD.WORKORDER_NO = ?\n" +
                    "   AND EWD.ITEM_STATUS ='" + DictConstant.SCAN_STATUS_NEW + "'\n" +
                    "";

    //����SQL���豸רҵΪָ����ĳ��ʱ��
    public String diffCateSQL =
////                    "--�����豸���� status��8\n" +
////                    "--�޲��죺ϵͳ���У�������\n" +
////                    "-- ���죺ϵͳ���ޣ�\n" +
////                    "--���촦��������;��\n" +
//            "SELECT EWD.BARCODE,\n" +
//                    "       '" + DictConstant.SCAN_STATUS_NONE + "' ITEM_STATUS,\n" +
//                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_NONE + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
//                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
//                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
//                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
//                    "       EWD.BOX_NO,\n" +
//                    "       EWD.NET_UNIT,\n" +
//                    "       ESI.ITEM_NAME,\n" +
//                    "       ESI.ITEM_SPEC,\n" +
//                    "       ESI.ITEM_CATEGORY,\n" +
//                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
//                    "       EWD.REMARK \n" +
//                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
//                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
//                    " AND NOT EXISTS (SELECT 1 FROM ETS_ITEM_INFO EII WHERE EII.BARCODE=EWD.BARCODE)\n" +
//                    "   AND EWD.ITEM_STATUS  = '" + DictConstant.SCAN_STATUS_REMAIN + "'\n" +
//                    "   AND EWD.WORKORDER_NO = ?\n" +
//                    "\n" +
//                    " UNION\n" +
////                    "--�����豸���� status��7\n" +
////                    "--�޲��죺ϵͳ��ǰ�ص��ޣ������� ��������\n" +
////                    "--���죺ϵͳ��ǰ�ص���\n" +
////                    "--���촦�������豸����;��\n" +
//                    "SELECT EWD.BARCODE,\n" +
//                    "       '" + DictConstant.SCAN_STATUS_EXISTS + "' ITEM_STATUS,\n" +
//                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_EXISTS + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
//                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
//                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
//                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
//                    "       EWD.BOX_NO,\n" +
//                    "       EWD.NET_UNIT,\n" +
//                    "       ESI.ITEM_NAME,\n" +
//                    "       ESI.ITEM_SPEC,\n" +
//                    "       ESI.ITEM_CATEGORY,\n" +
//                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
//                    "       EWD.REMARK \n" +
//                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
//                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
//                    " AND EWD.BARCODE  IN\n" +
//                    "       (SELECT BARCODE\n" +
//                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA\n" +
//                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
//                    "           AND AOA.OBJECT_NO = ?)\n" +
//                    "   AND EWD.WORKORDER_NO = ?\n" +
//                    "   AND EWD.ITEM_STATUS ='" + DictConstant.SCAN_STATUS_OFFLINE + "'\n" +
//                    "\n" +
//                    " UNION\n" +
//                    "--δɨ���豸 status��6\n" +
//                    "--�޲��죺ϵͳ��ǰ�ص��ޣ������� ��������\n" +
//                    "--���죺ϵͳ��ǰ�ص���\n" +
//                    "--���촦�������豸����;��\n" +
                    "SELECT EWD.BARCODE,\n" +
                    "       '" + DictConstant.SCAN_STATUS_EXISTS + "' ITEM_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_EXISTS + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       EWD.BOX_NO,\n" +
                    "       EWD.NET_UNIT,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       ESI.ITEM_CATEGORY,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
                    "       EWD.REMARK \n" +
                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND EWD.BARCODE  IN\n" +
                    "       (SELECT BARCODE\n" +
                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA,ETS_SYSTEM_ITEM ESI1\n" +
                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    "           AND EII.ITEM_CODE = ESI1.ITEM_CODE\n" +
                    "           AND ESI1.ITEM_CATEGORY=? \n" +
                    "           AND AOA.OBJECT_NO = ?)\n" +
                    "   AND EWD.WORKORDER_NO = ?\n" +
                    "   AND EWD.ITEM_STATUS ='" + DictConstant.SCAN_STATUS_NONE + "'\n" +
                    "\n" +
                    " UNION\n" +
//                    "--ɨ���豸 status��5\n" +
//                    "--�޲��죺ϵͳ��ǰ�ص��У������� ��������\n" +
//                    "--���죺ϵͳ��ǰ�ص���\n" +
//                    "--���촦��\n" +
                    "SELECT EWD.BARCODE,\n" +
                    "       '" + DictConstant.SCAN_STATUS_NONE + "' ITEM_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_NONE + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       EWD.BOX_NO,\n" +
                    "       EWD.NET_UNIT,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       ESI.ITEM_CATEGORY,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
                    "       EWD.REMARK \n" +
                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND EWD.BARCODE NOT  IN\n" +
                    "       (SELECT BARCODE\n" +
                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA\n" +
                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    "           AND AOA.OBJECT_NO = ?)\n" +
                    "   AND EWD.WORKORDER_NO = ?\n" +
                    "   AND EWD.ITEM_STATUS ='" + DictConstant.SCAN_STATUS_EXISTS + "'\n" +
                    "\n" +
                    " UNION\n" +
//                    "--�����豸 status��0\n" +
//                    "--�޲��죺ϵͳ��ǰ�ص��У������� ��������\n" +
//                    "--���죺ϵͳ��ǰ�ص���\n" +
//                    "--���촦��\n" +
                    "SELECT EWD.BARCODE,\n" +
                    "       '" + DictConstant.SCAN_STATUS_NONE + "' ITEM_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE('" + DictConstant.SCAN_STATUS_NONE + "','" + DictConstant.ORDER_ITEM_STATUS + "') ITEM_STATUS_DESC,\n" +
                    "       EWD.ITEM_STATUS SCAN_STATUS,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(EWD.ITEM_STATUS,'" + DictConstant.ORDER_ITEM_STATUS + "') SCAN_STATUS_DESC,\n" +
                    "       'PLACEHOLDER' PLACEHOLDER,\n" +
                    "       EWD.BOX_NO,\n" +
                    "       EWD.NET_UNIT,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       ESI.ITEM_CATEGORY,\n" +
                    "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY,'" + DictConstant.ITEM_TYPE + "') ITEM_CATEGORY_DESC, \n" +
                    "       EWD.REMARK \n" +
                    " FROM ETS_WORKORDER_DTL EWD, ETS_SYSTEM_ITEM ESI\n" +
                    "WHERE EWD.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND EWD.BARCODE NOT IN\n" +
                    "       (SELECT BARCODE\n" +
                    "          FROM ETS_ITEM_INFO EII, AMS_OBJECT_ADDRESS AOA\n" +
                    "         WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    "           AND AOA.OBJECT_NO = ?)\n" +
                    "   AND EWD.WORKORDER_NO = ?\n" +
                    "   AND EWD.ITEM_STATUS ='" + DictConstant.SCAN_STATUS_NEW + "'\n" +
                    "";
}
