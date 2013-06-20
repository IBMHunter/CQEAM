package com.sino.ams.constant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface WebAttrConstant {

    String TRUE_VALUE = "Y";
    String FALSE_VALUE = "N";
    String PAGE_TITLE="PAGE_TITLE";

    String ORDER_NO_AUTO_PRODUCE = "���ʱ�Զ�����";
    String LOOKUP_PROP = "LOOKUP_PROP";//�ݷ�һ�£������Ժ���Ҫ


    String ROOT_RESOURCE = "ROOT_RESOURCE";//�û���Ȩ���ʵĸ��˵���Դ
    String MENU_RESOURCE = "MENU_RESOURCE";//�û���Ȩ���ʵ���߲˵���Դ
    String FIRST_RESOURCE = "FIRST_RESOURCE";//�û���Ȩ���ʵĵ�һ�������˵�
    String MENU_OPTION = "MENU_OPTION";   //����Ŀ
    String SMALL_MENU_OPTION = "SMALL_MENU_OPTION";//����Ŀ
    String TRANS_TYPE = "TRANS_TYPE";  //��������
    String RES_DATA = "RES_DATA";
    String RESOURCE_OPTION = "RESOURCE_OPTION";//�˵�ѡ��
    String ALL_ROLE_OPTION = "ALL_ROLE_OPTION";
    String VIEW_ROLE_OPTION = "VIEW_ROLE_OPTION";
    String MENU_TREE = "MENU_TREE";//��Ŀ���������β˵�
    String RES_PRIV_DTO = "RES_PRIV_DTO";//��ĿȨ������

    String ENABLED_OPTION = "ENABLED_OPTION";//��Ч������ѡ��
    String GROUP_OPTION = "ALL_GROUP_OPTION";//���ѡ��
    String ROLE_OPTION = "ROLE_OPTION";//��ɫ������
    String FLOW_GROUP_OPTION = "FLOW_GROUP_OPTION";  //�������
    String GROUP_CATEGORY = "GROUP_CATEGORY";   //���רҵ
    String GROUP_DTOSET = "GROUP_DTOSET";//���DTOSET
    String GROUP_ATTR = "GROUP_ATTR";//�����Ϣ
    String ROLE_ATTR = "ROLE_ATTR";//��ɫ��Ϣ
    String USER_ATTR = "USER_ATTR";//�û���Ϣ
    String ORG_GROUP_ATTR = "";//��֯�������Ϣ--�û�ά����
    String ALL_ROLE_ATTR = "ALL_ROLE_ATTR";//���н�ɫ��Ϣ--�û�ά����
    String USER_RIGHT_ATTR = "USER_RIGHT_ATTR";//�û�Ȩ��--�û�ά����
    String ALL_ORG_OPTION = "ALL_ORG_OPTION";//��˾ѡ��
    String COUNTY_ATTR = "COUNTY_ATTR";// ������Ϣ
    String RENT_DTO = "RENT_DTO";//����DTO
    String IS_RENT_OPTION = "IS_RENT_OPTION";//�Ƿ�����ѡ��
    String HOUSE_USAGE_OPTION = "HOUSE_USAGE";// ������;����ѡ��
    String HOUSE_STATUS_OPTION = "HOUSE_STATUS"; //  ����״̬����ѡ��
    String IS_CERTIFICATE_OPTION = "IS_CERTIFICATE_OPTION"; //�Ƿ��з��ݲ�Ȩ֤����ѡ��
    String ISLAND_CERTIFICATE_OPTION = "IS_CERTIFICATE";//�Ƿ�������ʹ��֤
    String DZYH_DTO = "DZYH_DTO"; //   ��ֵ�׺�
    String USER_OBJECT_DTO = "USER_OBJECT_DTO";//�û��ص�DTO
    //==================�����б���start===========================
    String PROJECT_OPTION = "PROJECT_OPTION";//��Ŀ���������б�
    String USER_OPTION = "USER_OPTION";//�û�ѡ�������б�
    String CATEGORY_OPTION = "CATEGORY_OPTION";//רҵѡ�������б�
    //==================�����б���end===========================
    String DIFF_COUNT = "DIFF_COUNT";//��������
    String MATCHABLE = "MATCHABLE"; //
    String CUR_SCAN_DTL = "CUR_SCAN_DTL";//��ǰɨ����
    String PRE_SCAN_DTL = "PRE_SCAN_DTL";//�ϴ�Ѳ��ɨ����
    String DIFF_SCAN_DTL = "DIFF_SCAN_DTL"; //������
    String DIFF_DTOSET = "DIFF_DOTSET";//����DTOSet
    String BOUNDEN = "BOUNDEN";//�������б�
    String WORKORDER_BATCH_ATTR = "WORKORDER_BATCH_ATTR";//��������Ϣ
    String WORKORDER_ATTR = "WORKORDER_ATTR";//������Ϣ
    String WORKORDER_DATAS = "WORKORDER_DATAS";//�������еĹ�����Ϣ
    String WORKORDER_LOC_ROWSET = "WORKORDER_LOC_ROWSET";//�����ص����ݼ�����Ϣ
    String WORK_PLAN_DTO = "WORK_PLAN_DTO";    //�����ƻ�ά��DTO����
    String PLAN_STATUS_OPTION = "PLAN_STATUS_OPTION";  //  ״̬�����б�
    String PLAN_TYPE_OPTION = "PLAN_TYPE_OPTION";  //  �������������б�
    String OBJECT_BTS_DTO = "OBJECT_BTS_DTO";       //��վά��DTO
    String OBJECT_ELE_DTO = "OBJECT_ELE_DTO";      //��վ��ѵ�ά��
    String ETS_OBJECT_DTO = "ETS_OBJECT_DTO";  //�ʲ��ص��(EAM)DTO
    String ETS_WORKORDER_DTO = "ETS_WORKORDER_DTO";  //����DTO
    String ETS_WORKORDER_TMP_DTO = "ETS_WORKORDER_TMP_DTO";  //��ʱ����DTO
    String ETS_WO_SCHEME_RST = "ETS_WO_SCHEME_RST";//����������ϢRowSet
    String CUR_OBJ_SCHEME_RST = "CUR_OBJ_SCHEME_RST";//������ǰ�ص��豸������ϢRowSet
    String ETS_WORKORDER_BATCH_DTO = "ETS_WORKORDER_BATCH_DTO";  //������DTO
    String ETS_WORKORDER_ITEM_DTO = "ETS_WORKORDER_ITEM_DTO"; //����ά��
    String ETS_ITEM_DTO = "ETS_ITEM_DTO";//�豸ά��
    String AMS_INSTRUMENT_DTO = "EAM_INSTRUMENT_DTO";//����ά��
    String AMS_INSTRUMENT_REGISTRATION = "AMS_INSTRUMENT_REGISTRATION"; //�����Ǽǿ���Ϣ
    String AMS_INSTRUMENT_INV_OUT = "EAM_INSTRUMENT_INV_OUT"; //�����Ǳ����ʵ�����
    String AMS_INSTRUMENT_RETURN = "EAM_INSTRUMENT_RETURN"; //�����Ǳ���÷���
    String AMS_INSTRUMENT_REPAIR = "EAM_INSTRUMENT_REPAIR"; //�����Ǳ�����
    String AMS_INSTRUMENT_REPAIR_RETURN = "EAM_INSTRUMENT_REPAIR_RETURN"; //�����Ǳ����޷���
    String AMS_INSTRUMENT_CHK_HISTORY = "EAM_INSTRUMENT__CHK_HISTORY"; //�����Ǳ������ʷ
    String AMS_INSTRUMENT_WORKORDER_OBJECT_NO = "EAM_INSTRUMENT_WORKORDER_OBJECT_NO"; //�����Ǳ�ص�ά��
    String TASK_NAME_EXIST = "TASK_NAME_EXIST"; //�������ƴ���
    String TASK_NAME_NOT_EXIST = "TASK_NAME_NOT_EXIST"; //�������Ʋ�����
    String AMS_INSTRUMENTH_DTO = "AMS_INSTRUMENTH_DTO"; //ͷ��Ϣ
    String AMS_INSTRUMENTL_DTO = "EAM_INSTRUMENTL_DTO";//����Ϣ
    String AMS_ITEMH_REPAIR = "EAM_ITEMH_REPAIR";// ͷ��Ϣ
    String AMS_ITEML_REPAIR = "EAM_ITEMH_REPAIR";// ����Ϣ
    String OBJECT_CATEGORY_DTO = "OBJECT_CATEGORY_DTO"; //ETS_OBJECT_CATEGOY DTO
    String BARCODE_PRINT_DTO = "BARCODE_PRINT_DTO"; //EAM_BARCODE_PRINT DTO
    String BARCODE_RECEIVE_DTO = "BARCODE_RECEIVE_DTO";   //��ǩ����DTO
    

    String BARCODE_MAX_MAINTAIN_DTO = "BARCODE_MAX_MAINTAIN_DTO";  //����ǩά��DTO
    String ON_NET_DTO = "ON_NET_DTO";  // EAM_ITEM_ON_NET DTO
    String AMS_SPARE_DTO = "EAM_SPARE_DTO";//EAM_SPARE_DTO
    String AMS_ELEMENT_MATCH_DTO = "EAM_ELEMENT_MATCH_DTO";        //�����������Զ�Ӧ��ϵDTO
    String MANUFACTURER_DTO = "MANUFACTURER_DTO";     //������ϢDTO
    String IMPINFO_DTO = "IMPINFO_DTO";     //������ϢDTO
    //---------------------------------------------------------------------------
    String COUNTY_OPTION = "COUNTY_OPTION";//��������ѡ��
    String CITY_OPTION = "CITY_OPTION";//��������ѡ��
    String MAINTAIN_CORP_OPTION = "MAINTAIN_CORP_OPTION";//��ά��˾����ѡ��
    String AREA_OPTION = "AREA_OPTION";//������������ѡ��

    String INV_CATEGORY = "70";
    String INV_CATEGORY_MAX = "80";

    String MAINTAIN_CORP_ATTR = "MAINTAIN_CORP_ATTR"; //��ά��˾��Ϣ
    String MAINTAIN_CORP_USR_ATTR = "MAINTAIN_CORP_USR_ATTR";//��ά��˾��Ա��Ϣ
    String MAINTAIN_CORP_RSP_ATTR = "MAINTAIN_CORP_RSP_ATTR";//��ά��˾������Ϣ
    String ATTACH_FILE_ATTR = "ATTACH_FILE_ATTR";//�Ƿ����ظ���
    String ATTACH_FILES = "ATTACH_FILES";//��ά��˾��ظ���
    String DEPT_CATEGORYS = "DEPT_CATEGORYS"; //Ա���������ŷ�������ѡ��
    //---------------------------------------------------------------------------
    String PORCDURE_ATTR = "PORCDURE_ATTR";//������Ϣ
    //---------------------------------------------------------------------------
    String OU_OPTION = "OU_OPTION"; //ou����ѡ��
    String OU_OPTION2 = "OU_OPTION2"; //ou����ѡ��
    String DIS_OU_OPTION = "DIS_OU_OPTION";//���Ϸ�����֯
    String EQUIPMENT_OPTION = "EQUIPMENT"; //�豸��������ѡ��
    String ETS_SYSTEM_ITEM_DTO = "ETS_SYSTEM_ITEM"; //�豸�����(EAM)DTO
    String ETS_SYSTEM_DISTRIBUTE_DTO = "ETS_SYSTEM_DISTRIBUTE"; //������֯�����DTO
    String AMS_HOUSE_INFO_DTO = "AMS_HOUSE_INFO_DTO"; //���޷��ݱ�DTO
    String AMS_LAND_INFO_DTO = "AMS_LAND_INFO_DTO"; //����ά��DTO
    String AMS_RENT_DEADLINE_DTO = "AMS_RENT_DEADLINE_DTO";//�������ñ�DTO
    String ITEM_UNIT_OPTION = "ITEM_UNIT_OPTION";//������λ
    String PROJECT_TYPE_OPTION = "PROJECT_TYPE_OPTION";//��Ŀ����
    String PROJECT_STATUS_OPTION = "PROJECT_STATUS_OPTION";//��Ŀ״̬
    String PAY_TYPE_OPTION = "PAY_TYPE_OPTION";//���ʽ����ѡ��
    String LAND_AREA_UNIT_OPTION = "LAND_AREA_UNIT_OPTION";  //�ػ���λ����ѡ��
    String AREA_UNIT_OPTION = "AREA_UNIT_OPTION"; //�����λ����ѡ�� �����������
    String IS_MATCH_OPTION = "IS_MATCH_OPTION";  //�Ƿ�ƥ�������ѡ��
    String IS_SHARE_OPTION = "IS_SHARE_OPTION"; //�Ƿ��������˵�
    //-----------------------------�ֵ�ά������-----------------------------------//
    String FIRST_DICT = "FIRST_DICT";//
    String TREE_HTML = "TREE_HTML";
    String TREE_JS = "TREE_JS";
    String DICT_TYPE_DATA = "DICT_TYPE_DATA";
    String DICT_DATA = "DICT_DATA";
    String IS_INNER_RADIO = "IS_INNER_RADIO";//�Ƿ����õ�ѡ��ť
    String ENABLED_RADIO = "ENABLED_RADIO";//��Ч�Ե�ѡ��ť
    String MAINTAIN_RADIO = "MAINTAIN_RADIO";//��ά���Ե�ѡ��ť
    String DICT_PARENT_OPT = "DICT_PARENT_OPT";//�ֵ����������
    //----------------------------------------------------------------//

    String ETS_PA_PROJECTS_ALL_DTO = "ETS_PA_PROJECTS_ALL"; //��Ŀ��Ϣά��DTO
    String ETS_OBJECT_ATTRIBUTE_DTO = "ETS_OBJECT_ATTRIBUTE"; //�ʲ��ص�������չ�������DTO
    //----------------------------------------------------------------//
    String INSTRU_STATUS = "INSTRU_STATUS";//�����Ƿ�����������
    String TRANS_STATUS = "TRANS_STATUS";//����״̬
    String SPARE_REASON = "SPARE_REASON";//���������ԭ��
    String INV_OPTION = "INV_OPTION";//�ֿ������б�
    String CHECK_OPTION = "CHECK_OPTION";//Ѳ��ģʽ�����б�
    String BORROW_OPTION = "BORROW_OPTION";//�����Ǳ���й���Աѡ��
    //�����������������
    String SUGGETION_DTO = "SUGGETION_DTO";//�����������
    String ORDER_FILE_DTO = "ORDER_FILE_DTO";//��������

    //----------------------------------------------------------------//
    String WAREHOUSE_ATTR = "WAREHOUSE_ATTR";//�ֿ���Ϣ
    String WAREHOUSE_TYPE_OPTION = "WAREHOUSE_TYPE_OPTION";//�ֿ����������б�
    String BUSIHOUSE_TYPE_OPTION = "BUSIHOUSE_TYPE_OPTION";//ҵ�����������б�
    String CODE_EXIST = "CODE_EXIST";
    String CODE_NOT_EXIST = "CODE_NOT_EXIST";
    String CATEGORY_EXIST = "CATEGORY_EXIST";
    String CATEGORY_NOT_EXIST = "CATEGORY_NOT_EXISTT";
    String OBJECT_CODE = "OBJECT_CODE";

    //--------------------------------------�ʲ�̨����----------------------------------//
    String ASSETS_TREE = "ASSETS_TREE";//�ʲ�̨����
    String ASSETS_DATA = "ASSETS_DATA";//�ʲ�������Ϣ/

    //--------------------------------------�ֿ�Ȩ�޶�����----------------------------------//
    String INV_PRIVI_DTO = "INV_PRIVI_ATTR"; //�ֿ�Ȩ��DTO

    //--------------------------------------�����汾���ƶ�����----------------------------------//
    String AMS_ITEM_VERSION_DTO = "EAM_ITEM_VERSION_DTO";       //�汾DTO
    //-----------------------------------------------------------------------------------------//

    //-----------------------------------------------------------------------------------------//
    String CABEL_INFO_DTO = "CABEL_INFO_DTO";           //����DTO
    String SPREAD_TYPE_OPTION = "SPREAD_TYPE_OPTION";         //���跽ʽopt
    String CABEL_USAGE_OPTION = "CABEL_USAGE_OPTION";         //������;opt
    //-----------------------------------------------------------------------------------------//


    //-----------------------------------------------------------------------------------------//
    String TRANS_D_DTO = "TRANS_D_DTO";        //����������ϢDTO
    String TRANS_D_DTOS = "TRANS_D_DTOS";    //����������ϢDTO��
    String ALLOT_H_DTO = "ALLOT_H_DTO";//��������ͷ��Ϣ
    String ALLOT_D_DTO = "ALLOT_D_DTO";  //������������Ϣ
    String SPARE_CATEGORY_DTO = "SPARE_CATEGORY_DTO";   //������ά��
    //-----------------------------------------------------------------------------------------//


    //-----------------------------------------------------------------------------------------//
    String LOCUS_DTO = "LOCUS_DTO";  //�ص�DTO
    String LOCUS_FLAG = "LOCUS_FLAG"; //�ص����ͱ�ǣ�objectCategory ��� ����
    String UNCHECK = "225";                       //δѲ��
    //-----------------------------------------------------------------------------------------//


    //-----------------------------------------------------------------------------------------//
    String BY_PROJECTID = "BY_PROJECTID";         //����Ŀ ��ѯͳ��
    String BY_BARCODE = "BY_BARCODE";             //������ ��ѯͳ��
    String BY_SPEC = "BY_SPEC";                    //��רҵ ��ѯͳ��
    String BY_CATEGORY = "BY_CATEGORY";          //������ ��ѯͳ��
    String BY_LOCUS = "BY_LOCUS";                 //���ص� ��ѯͳ��
    String BY_ALLOT = "BY_ALLOT";                 //���ֿ� ��ѯͳ��
    String BY_CITY = "BY_CITY";                   //������ ��ѯͳ��
    String BY_COUNTY = "BY_COUNTY";              //������ ��ѯͳ��
    String BY_PROV = "BY_PROV";                   //��ʡ  ��ѯͳ��
    String BY_NAME = "BY_NAME";                   //������ ��ѯͳ��
    String BY_VENDOR = "BY_VENDOR";               //��Ӧ�� ��ѯͳ��
    String BY_DAIWEI = "BY_DAIWEI";               //����ά��˾ ��ѯͳ��

    //--����ͳ��--

    String BY_MONTH = "BY_MONTH";                 //����     ͳ��
    String BY_CHECK = "BY_CHECK";                   //Ѳ��    ͳ��
    String BY_YEAR = "BY_YEAR";                   //����     ͳ��
    String BY_TIME = "BY_TIME";                   //��ʱ��� ͳ��

    String AVISO_DTO = "AVISO_DTO";
    String LAST_FIVE_YEAR_OPTION = "Last_FIVE_YEAR_OPTION";
    String FULL_MONTH_OPTION = "FULL_MONTH_OPTION";
    String ITEM_INFO_DTO = "ITEM_INFO";          //�豸DTO
    String ITEM_TYPE_OPTION = "ITEM_TYPE_OPTION";
    String FINANCE_PROP_OPTION = "FINANCE_PROP_OPTION";
    String OBJECT_CATEGORY_OPTION = "OBJECT_CATEGORY_OPTION";
    String ITEM_CATEGORY_OPTION = "ITEM_CATEGORY_OPTION";
    String INV_TYPE_OPTION = "INV_TYPE_OPTION";
    String ITEM_STATUS_OPTION = "ITEM_STATUS_OPTION";
    String LEFT_CATEGORY_OPTION = "LEFT_CATEGORY_OPTION"; //רҵѡ��
    String RIGHT_CATEGORY_OPTION = "RIGHT_CATEGORY_OPTION"; //רҵ�Ѵ���
    String PRINT_OPTION = "PRINT_OPTION"; //��ǩ��������ѡ��ou����ѡ��

    //-----------------------------------������ѯ����--------------------------------------------//
    String WOR_STATUS_NEW = "10"; //����
    String WOR_STATUS_DEPLOY = "11"; //���·�
    String WOR_STATUS_DOWNLOAD = "12"; //������
    String WOR_STATUS_UPLOAD = "13"; //���ϴ�
    String WOR_STATUS_ARCHIVED = "14"; //�ѹ鵵
    String WOR_STATUS_CANCELED = "15"; //��ȡ��
    String WOR_STATUS_OVERTIME = "1";    //��ʱ
    String WOR_STATUS_INTEGAZATION = "76";    //�����ۺϲ�ѯ
    String WOR_STATUS_BATCH = "2";     //��������������ѯ
    //-----------------------------------------------------------------------------------------//

    //-------------------------------------------ƥ�䷽ʽ(��ʱ)--------------------------------//
    String MATCH_MODE_SPARE = "0";        //����ȷ��
    String MATCH_MODE_SPARE_RET = "1";   //����������ϵ
    String MATCH_MODE_PRJMTL = "2";      //��������ȷ��
    String MATCH_MODE_PRJMTL_RET = "3"; //������������ƥ���ϵ
    String MATCH_MODE_OTHER = "4";       //�豸����
    String MATCH_MODE_0THER_RET = "5";  //�����豸����
    String MATCH_MODE_CHANGED_ASSETS = "6"; //    6��ת��ƥ��
    String MATHC_MODE_CHANGED_ASSETS_RET = "7";  //    7�������ʲ�ƥ���ϵ
    String MATCH_MODE_BARCODE = "8"; //    8���豸���ʲ�����ƥ��
    //    9���豸���ʲ��˹�ƥ��
    String MATCH_MODE_UNUSED_ASSETS = "10";//δʹ���豸ɾ��
    String MATCH_MODE_RENT = "11";//�����ʲ�ȷ��
    String MATCH_MODE_RENT_RET = "12";//���������ʲ�ȷ��
    String MATCH_MODE_DG = "13";//�����ʲ�ȷ��
    String MATCH_MODE_DG_RET = "14";//���������ʲ�ȷ��
    String SCREEN_EXPROT = "15";// MIS�����ʲ�����
    String MATCH_MODE_LC = "16";//��ֵ�׺��ʲ�ȷ��
    String MATCH_MODE_CT = "17";//��ͨ�ʲ�ȷ��
    String MATCH_MODE_LC_RET = "18";//��ֵ�׺��ʲ�����
    String MATCH_MODE_TD = "19";//TD�ʲ�ȷ��
    String MATCH_MODE_TD_RET = "20";//TD�ʲ�����

    //-----------------------------------------------------------------------------------------//

    //---------------------------------------�ص�ƥ��-------------------------------------------//
    String AMS_LOCATION_OPTION = "AMS_LOCATION_OPTION"; //amsδƥ���������
    String MIS_LOCATION_OPTION = "AMS_LOCATION_OPTION";  //misδƥ���������
    //-----------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------//

    //-----------------------------------------�ʲ�ƥ��--------------------------------------------//
    String CHANGED_ASSETS_LEFT = "CHANGED_ASSETS_LEFT";//ת��ƥ��:����������Ϣ
    String CHANGED_ASSETS_RIGHT = "CHANGED_ASSETS_RIGHT";//ת��ƥ��:MIS�ʲ���Ϣ
    String CHANGED_ASSETS_MACT = "CHANGED_ASSETS_MACT";
    String ETS_EQUIPMENT_DTO = "ETS_EQUIPMENT_DTO";//�ʲ�ƥ�䱨��ʣ���豸�嵥
    String BARCODE_MATCH_DTO = "BARCODE_MATCH_DTO";//�ʲ�����ƥ��
    String ETS_EQUIPMENT = "1";//�豸�ʲ����ࣺ1���豸��
    String ETS_PRACTINFO_DTO = "ETS_PRACTINFO_DTO";

    //-----------------------------------------��Ʒ����--------------------------------------------//
    String ETS_SPARE_DTO = "ETS_SPARE_DTO"; //��Ʒ����DTO
    String SPARE_VENDOR_OPTION = "SPARE_VENDOR_OPTION";
    String SPARE_FROM_OBJECT_OPTION = "SPARE_FROM_OBJECT_OPTION";

    //-----------------------------------------ͬ������--------------------------------------------//
    String SYSCHRONIZE_DTO = "SYSCHRONIZE_DTO";
    String MESSAGE_DATA = "MESSAGE_DATA";

    String EFFECTIVE_Y = "Y";
    String EFFECTIVE_N = "N";
    String EFFECTIVE_YES = "��";
    String EFFECTIVE_NO = "��";
    String[] EFFECTIVE_CAP = {EFFECTIVE_YES, EFFECTIVE_NO};
    String[] EFFECTIVE_VAL = {EFFECTIVE_Y, EFFECTIVE_N};

    String RESEND_Y = "Y";
    String RESEND_N = "N";
    String RESEND_YES = "��";
    String RESEND_NO = "��";
    String[] RESEND_CAP = {RESEND_YES, RESEND_NO};
    String[] RESEND_VAL = {RESEND_Y, RESEND_N};

    String COLLECT_Y = "Y";
    String COLLECT_N = "N";
    String COLLECT_YES = "��";
    String COLLECT_NO = "��";
    String[] COLLECT_CAP = {COLLECT_YES, COLLECT_NO};
    String[] COLLECT_VAL = {COLLECT_Y, COLLECT_N};

    String RESPONSI_LOCATIONS = "RESPONSI_LOCATIONS";//���εص�
    String SCANED_LOCATIONS_Y = "SCANED_LOCATIONS_Y";//��Ѳ��ص�
    String SCANED_LOCATIONS_N = "SCANED_LOCATIONS_N";//δѲ��ص�
    String WORKORDER_DTO = "WORKORDER_DTO";//��ѯ����


    String RESPONSI_ITEMS = "RESPONSI_ITEMS";//�����豸
    String SCANED_ITEMS_Y = "SCANED_ITEMS_Y";//��Ѳ���豸
    String SCANED_ITEMS_N = "SCANED_ITEMS_N";//δѲ���豸

    String GROUP_LOCATIONS = "GROUP_LOCATIONS";
    String GROUP_ITEMS = "GROUP_ITEMS";
    String OU_DTO = "OU_DTO";//��ѯ����
    String DEPT_DTO = "DEPT_DTO";//��ѯ����

    String SPARE_CATEGORY_OPTION = "SPARE_CATEGORY_OPTION";
    String VENDOR_OPTION = "VENDOR_OPTION";

    String DEFAULT_PASSWORD = "eamprd";

    //-----------------------------��ֵ�׺�ά������-----------------------------------//
    int DZYH_TRUE_VALUE = 1;
    String DZYH_FALSE_VALUE = "0";
    String FIRST_DZYH = "FIRST_DZYH";//
    String DZYH_TREE_HTML = "DZYH_TREE_HTML";
    String DZYH_TREE_JS = "DZYH_TREE_JS";
    String DZYH_TYPE_DATA = "DZYH_TYPE_DATA";
    String DZYH_DATA = "DZYH_DATA";
    String DZYH_ENABLED_RADIO = "ENABLED_RADIO";//��Ч�Ե�ѡ��ť
    String DZYH_BARCODE_FLAG = "DZYH_BARCODE_FLAG";//��ֵ�׺������ʶ
    String DZYH_COMMON_FLAG = "DZYH_COMMON_FLAG";//��ֵ�׺ĳ��ñ�ʶ
    String UNIT_OF_MEASURE = "19";//������λ
    String DZYH_BILL_STATUS = "DZYH_BILL_STATUS";//��ֵ�׺ĵ���״̬
    String DZYH_DISPOSE_REASON = "DZYH_DISPOSE_REASON";//��ֵ�׺Ĵ���ԭ��
    String DZYH_DISPOSE_TYPE = "DZYH_DISPOSE_TYPE";//��ֵ�׺Ĵ��÷�ʽ
    String DZYH_HISTORY_DATA = "DZYH_HISTORY_DATA";

    String DZYH_BILL_ORG_OPT = "DZYH_BILL_ORG_OPT";//��ֵ�׺ĵ���״̬������
    String DZYH_BILL_STATUS_OPT = "DZYH_BILL_STATUS_OPT";//��ֵ�׺ĵ���״̬������
    String DZYH_CATEGORY2_OPT = "DZYH_CATEGORY2_OPT";//��ֵ�׺�Ŀ¼���������
    String DZYH_ITEM_NAME_OPT = "DZYH_ITEM_NAME_OPT";//��ֵ�׺�Ʒ��������
    String DZYH_ITEM_SPEC_OPT = "DZYH_ITEM_SPEC_OPT";//��ֵ�׺Ĺ������������
    String DZYH_RESPONSIBILITY_DEPT_OPT = "DZYH_RESPONSIBILITY_DEPT_OPT";//��ֵ�׺�ʹ�ò���������
    String DZYH_RESPONSIBILITY_USER_OPT = "DZYH_RESPONSIBILITY_USER_OPT";//��ֵ�׺�������������
    String DZYH_ADDRESS_NAME_OPT = "DZYH_ADDRESS_NAME_OPT";//��ֵ�׺ĵص�������
    String DZYH_PARENT_OPT = "DZYH_PARENT_OPT";//��ֵ�׺�������
    String DZYH_BARCODE_OPT = "DZYH_PBARCODE_OPT";//�����ʶ������
    String DZYH_COMMON_OPT = "DZYH_COMMON_OPT";//���ñ�ʶ������
    String DZYH_UNIT_OF_MEASURE_OPT = "DZYH_UNIT_OF_MEASURE_OPT";//������λ������
    String DZYH_DISPOSE_REASON_OPT = "DZYH_DISPOSE_REASON_OPT";//��ֵ�׺Ĵ���ԭ��������
    String DZYH_DISPOSE_TYPE_OPT = "DZYH_DISPOSE_TYPE_OPT";//��ֵ�׺Ĵ��÷�ʽ������

    //-----------------------------------------------------------------------------------//

    //-----------------------------��ֵ�׺�Ȩ�޶���ά������-----------------------------------//
    String DZYH_PRIVI_DATA = "DZYH_PRIVI_DATA";
    String DZYH_ENABLED_OPTION = "DZYH_ENABLED_OPTION";
    String DZYH_DEFAULTFLAG_OPTION = "DZYH_DEFAULTFLAG_OPTION";
    //-----------------------------------------------------------------------------------//

    //-----------------------------����ά������-----------------------------------//
    String FILE_VERSION_OPTION = "FILE_VERSION_OPTION";
    String DRIVER_CATEGORY_OPTION = "DRIVER_CATEGORY_OPTION";// �������������
    String DRIVER_NAME_OPTION = "DRIVER_NAME_OPTION";// ��������������

    //-----------------------------�����Ӧ��ϵά������-----------------------------------//
    String MAPPING_CATEGORY_OPTION = "MAPPING_CATEGORY_OPTION";
    String DIRVER_RELATION_OPTION = "DIRVER_RELATION_OPTION";
    String DRIVER_MAPPING_DTO = "DRIVER_MAPPING_DTO";
    String DIRVER_CODE_OPTION = "DIRVER_CODE_OPTION";

    String RESOURCE_DATA="RESOURCE_DATA";
    
    //-----------------------------�������˱����ʲ�����-----------------------------------//
    String OTHER_DISCARDED_ITEM = "OTHER_DISCARDED_ITEM";//�������˱����ʲ�
    
    //----------------------------------�ʲ�����----------------------------------------//
    String FINANCE_PROP = "FINANCE_PROP";//�ʲ�����
     
    String ALL_RES_NAME = "ALL_RES_NAME";
    String SEARCH_ADVANCE = "ADVANCE"; //�߼���ѯ
    
    String FIELDS_EXIST = "FIELDS_EXIST";
	String FIELDS_NOT_EXIST = "FIELDS_NOT_EXIST";
	
	String REC_BARCODE_DTO = "REC_BARCODE_DTO"; //��ǩ����DTO
}

