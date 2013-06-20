package com.sino.ams.constant;

import com.sino.base.constant.message.MsgKeyConstant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ�����ӿ����ڶ��屾ϵͳ�õ�����Ϣ������ͬʱ��Ҫ���������������ļ������Ӽ����ͼ�ֵ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface CustMessageKey extends MsgKeyConstant {
	
	String UPDATE_YCZ_DATA = "UPDATE_YCZ_DATA";
	String UPDATE_ZY_DATA = "UPDATE_ZY_DATA";
	String UPDATE_XZ_DATA = "UPDATE_XZ_DATA";
	String UPDATE_JZ_DATA = "UPDATE_JZ_DATA";
	String UPDATE_ZH_DATA = "UPDATE_ZH_DATA";
	String UPDATE_YBF_DATA = "UPDATE_YBF_DATA";
	String UPDATE_CZ_DATA = "UPDATE_CZ_DATA";
	String UPDATE_XS_DATA = "UPDATE_XS_DATA";
	
    //��Ŀ��������Ϣ��ʾ
    String INNER_RES = "INNER_RES";
    String PRIVI_SAVE_SUCCESS = "PRIVI_SAVE_SUCCESS";
    String RRIVI_SAVE_FAILURE = "RRIVI_SAVE_FAILURE";
    String INNER_DICT = "INNER_DICT";
    String DISABLE_SUCCESS = "DISABLE_SUCCESS";
    String DISABLE_FAILURE = "DISABLE_FAILURE";
    String ENABLE_SUCCESS = "ENABLE_SUCCESS";
    String ENABLE_FAILURE = "ENABLE_FAILURE";
    String SAVE_PRIVI_SUCCESS = "SAVE_PRIVI_SUCCESS";
    String SAVE_PRIVI_FAILURE = "SAVE_PRIVI_FAILURE";
    String MESSAGE_INIT_SUCCESS = "MESSAGE_INIT_SUCCESS";
    String MESSAGE_INIT_FAILURE = "MESSAGE_INIT_FAILURE";
    String MESSAGE_SAVE_SUCCESS = "MESSAGE_SAVE_SUCCESS";
    String MESSAGE_SAVE_FAILURE = "MESSAGE_SAVE_FAILURE";
    String TRANSFER_SUCCESS = "TRANSFER_SUCCESS";
    String TRANSFER_FAILURE = "TRANSFER_FAILURE"; 

    //�豸����ȷ��
    String ITEM_CONFIRM_SUCCESS = "ITEM_CONFIRM_SUCCESS";
    String ITEM_CONFIRM_FAILURE = "ITEM_CONFIRM_FAILURE";
    String ITEM_REPLACE_SUCCESS = "ITEM_REPLACE_SUCCESS";
    String ITEM_REPLACE_FAILURE = "ITEM_REPLACE_FAILURE";
    //�豸����ȷ��

    //EAMʵ����MISת���嵥Ԥƥ��
    String MATCH_SUCCESS = "MATCH_SUCCESS";
    String MATCH_FAILURE = "MATCH_FAILURE";
    String DELETE_MATCH_SUCCESS = "DELETE_MATCH_SUCCESS";
    String DELETE_MATCH_FAILURE = "DELETE_MATCH_FAILURE";
    String CAN_NOT_DELETE = "CAN_NOT_DELETE";

    //����ά������
    String ACTION_ORDER_MSG = "ACTION_ORDER_MSG";
    String OBJECT_CREATE_SUCCESS = "OBJECT_CREATE_SUCCESS";
    String OBJECT_CREATE_FAILURE = "OBJECT_CREATE_FAILURE";
    String OBJECT_UPDATE_SUCCESS = "OBJECT_UPDATE_SUCCESS";
    String OBJECT_UPDATE_FAILURE = "OBJECT_UPDATE_FAILURE";
    String OBJECT_DISABLE_SUCCESS = "OBJECT_DISABLE_SUCCESS";
    String OBJECT_DISABLE_FAILURE = "OBJECT_DISABLE_FAILURE";
    String OBJECT_ENABLE_SUCCESS = "OBJECT_ENABLE_SUCCESS";
    String OBJECT_ENABLE_FAILURE = "OBJECT_ENABLE_FAILURE";

    //EXCEL������ʾ�Ƿ�ȫ������ɹ�
    String EXPORT_SUCCESS = "EXPORT_SUCCESS";
    String EXPORT_FAILURE = "EXPORT_FAILURE";

    String EMPTY_ERROR = "EMPTY_ERROR";
    String LENGTH_ERROR = "LENGTH_ERROR";
    String NUMBER_ERROR = "NUMBER_ERROR";
    String PROV_OPERATION_ONLY = "PROV_OPERATION_ONLY";

    String RUNOUTREPORT_SUCCESS = "RUNOUTREPORT_SUCCESS";
    String RUNOUTREPORT_FAILURE = "RUNOUTREPORT_FAILURE";
    String RUNOUTREPORT_CHECK_FAILURE = "RUNOUTREPORT_CHECK_FAILURE";
    String RUNOUTREPORT_ISEXISTS = "RUNOUTREPORT_ISEXISTS";
    String CAPITALCOMPUTE_RUNOUTREPORT = "CAPITALCOMPUTE_RUNOUTREPORT";
    
    String LOAD_FILE_LOCK = "LOAD_FILE_LOCK";
    String IMPORTDATA_SUCCESS = "IMPORTDATA_SUCCESS";
    String IMPORTDATA_FAILURE = "IMPORTDATA_FAILURE";
    String IMPORTDATA_ERROR = "IMPORTDATA_ERROR";
    String LOAD_FILE_ISNOTEXISTS = "LOAD_FILE_ISNOTEXISTS";
    String LOAD_SUCCESS = "LOAD_SUCCESS";
    String LOAD_FAILURE = "LOAD_FAILURE";
    String LOAD_FILE_ISEXISTS = "LOAD_FILE_ISEXISTS";
    String IMPORTDATA_CCID_ERROR = "IMPORTDATA_CCID_ERROR";
    String IMPORTDATA_CCID_ERRORINFO = "IMPORTDATA_CCID_ERRORINFO";
    String IMPORTDATA_INVOKEPROCDURE_SUCCESS = "IMPORTDATA_INVOKEPROCDURE_SUCCESS";
    String IMPORTDATA_UPDATEFINCAPEX_FAILURE = "IMPORTDATA_UPDATEFINCAPEX_FAILURE";
    String IMPORTDATA_UPDATEFINOPEX_FAILURE = "IMPORTDATA_UPDATEFINOPEX_FAILURE";
    String IMPORTDATA_UPDATEFINREVENUE_FAILURE = "IMPORTDATA_UPDATEFINREVENUE_FAILURE";
    String IMPORTDATA_INVOKEPROCDURE_FAILURE = "IMPORTDATA_INVOKEPROCDURE_FAILURE";
    String UPDATE_BUZICODEFORMULA_FAILURE = "UPDATE_BUZICODEFORMULA_FAILURE";
    String UPDATE_BUZICODEFORMULA_SUCCESS = "UPDATE_BUZICODEFORMULA_SUCCESS";
    String UPDATE_BUZICODEFORMULA_BUZICODE_ERROR = "UPDATE_BUZICODEFORMULA_BUZICODE_ERROR";
    String RUNOUTREPORT_DATA_ABSENT = "RUNOUTREPORT_DATA_ABSENT";
    //����ά��
    String DRIVER_INFO_SUCCESS = "DRIVER_INFO_SUCCESS";
    String DRIVER_INFO_FAILURE = "DRIVER_INFO_FAILURE";
    
    String MODULE_ISNOTEXISTS = "MODULE_ISNOTEXISTS";

	String ETS_ASSETS_FLEX_SET_SUCCESS = "ETS_ASSETS_FLEX_SET_SUCCESS";
    String ETS_ASSETS_FLEX_SET_FAILURE = "ETS_ASSETS_FLEX_SET_FAILURE";
    String ETS_ASSETS_FLEX_SET_REMOVE_SUCCESS = "ETS_ASSETS_FLEX_SET_REMOVE_SUCCESS";
    String ETS_ASSETS_FLEX_SET_REMOVE_FAILURE = "ETS_ASSETS_FLEX_SET_REMOVE_FAILURE";
	String IMPORTDATA_SETINFO_ERROR = "IMPORTDATA_SETINFO_ERROR";
    String IMPORTTABLEDATA_SUCCESS = "IMPORTTABLEDATA_SUCCESS";
    String IMPORTTABLEDATA_FAILURE = "IMPORTTABLEDATA_FAILURE";
    String IMPORTDATA_BILLING_FAILURE = "IMPORTDATA_BILLING_FAILURE";
    String IMPORTDATA_BILLING_SUCCESS = "IMPORTDATA_BILLING_SUCCESS";
    String IMPORT_ERROR = "IMPORT_ERROR";
    String IMPORTDATA_SET_ERROR = "IMPORTDATA_SET_ERROR";
	String ETS_ASSETS_FLEX_SET_ISEXISTS = "ETS_ASSETS_FLEX_SET_ISEXISTS";
    String IMPORTDATA_BILLING_AMOUNT_ERROR = "IMPORTDATA_BILLING_AMOUNT_ERROR";
    String EXITS_DISABLE_DATA = "EXITS_DISABLE_DATA";
    String UPDATE_DISABLE_DATA = "UPDATE_DISABLE_DATA";

    String SPARE_REPLACE_SUCCESS = "SPARE_REPLACE_SUCCESS";
    String SPARE_REPLACE_FAILURE = "SPARE_REPLACE_FAILURE";
}