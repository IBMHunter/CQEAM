package com.sino.ams.newasset.constant;

import com.sino.ams.constant.DictConstant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface AssetsDictConstant extends DictConstant {
	String LOCATION_TMP_YES = "1"; //��ʱ�ص�
	String LOCATION_TMP_NO = "0"; //����ʱ�ص�
	String STATUS_YES = "Y"; //�϶�״̬
	String STATUS_NO = "N"; //��״̬

	//��������
	String FILE_TYPE_SCANNED = "SCANNED"; //ɨ���
	String FILE_TYPE_OTHERS = "OTHERS"; //һ�㸽��
	String DOC_TYPE = "UPLOAD"; //�ж����ϴ����ǲ鿴
	String DOC_TYPE2 = "LOOK"; //�ж����ϴ����ǲ鿴

	String TRANS_INN_DEPT = "INN_DEPT"; //�����ڵ���
	String TRANS_BTW_DEPT = "BTW_DEPT"; //���ż����
	String TRANS_BTW_COMP = "BTW_COMP"; //��˾�����
	String TRANS_INN_DEPT_RFU = "INN_DEPT_RFU"; //��������(������)

	String END_INN_DEPT = "END_INN_DEPT"; //�����ڵ��������ڵ�ֵ
	String END_BTW_DEPT = "END_BTW_DEPT"; //���ż���������ڵ�ֵ
	String END_BTW_COMP = "END_BTW_COMP"; //��˾����������ڵ�ֵ
	String END_INN_DEPT_RFU = "END_INN_DEPT_RFU"; //��������(������)�����ڵ�ֵ
	String OTHER_FLOW = "OTHER"; //������·�ߣ�����Ҫ��������


	String[] TRANS_OPT_VALUES = {TRANS_INN_DEPT, TRANS_BTW_DEPT, TRANS_BTW_COMP, TRANS_INN_DEPT_RFU, OTHER_FLOW};
	String[] TRANS_OPT_LABELS = {"�����ڵ�����", "���ż������", "��˾�������", "����������(������)", "����"};
	String[] TRANS_OPT_LABELS_TD = {"TD�����ڵ�����", "TD���ż������", "TD��˾�������"};

	String SPEC_ASS_RED = "ASS-RED"; //�����ʲ�������
	String SPEC_ASS_DIS = "ASS-DIS"; //�����ʲ����ϵ�
	String SPEC_ASS_FREE = "ASS-FREE"; //�����ʲ����õ�

	String[]SP_ASSETS_OPT_VALUES = {SPEC_ASS_RED,SPEC_ASS_DIS,SPEC_ASS_FREE};
	String[]SP_ASSETS_OPT_LABELS = {"������","���ϵ�","���õ�"};

	String SPEC_DG_ASSETS = "DG_ASSETS";
	String SPEC_TD_ASSETS = "TD_ASSETS";
	String SPEC_RENT_ASSETS = "RENT_ASSETS";
	String SPEC_DH_ASSETS = "DH_ASSETS";

	String[]ASSETS_TYPE_OPT_VALUES ={SPEC_DG_ASSETS,SPEC_TD_ASSETS,SPEC_RENT_ASSETS,SPEC_DH_ASSETS};
	String[]ASSETS_TYPE_OPT_LABELS = {"��ͨ�������ʲ�","TD�ʲ�","�����ʲ�","��ֵ�׺��ʲ�"};

	String DEFAULT_YES = "Y"; //Ĭ��״̬--Y
	String DEFAULT_NO = "N"; //Ĭ��״̬--N

	String ORDER_STS_RECEIVED = "RECEIVED"; //�ѽ��գ��ʲ�����Ա�Ѿ����յ����ʲ�
	String ORDER_STS_ASSIGNED = "ASSIGNED"; //�ѷ��䣺�ʲ�����Ա�Ѿ������ʲ���������
	String ORDER_STS_CONFIRMD = "CONFIRMD"; //��ȷ�ϣ��������Ѿ�ȷ���յ��ʲ�

	String ASSETS_STATUS = "ASSETS_STATUS"; //�ʲ�״̬
	String ASSETS_STATUS_DISCARDED = "DISCARDED"; //�ʲ�״̬--�ѱ���
    String ASSETS_STAY_DISCARDED = "TO_DISCARD";//�ʲ�״̬--������
    String ASSETS_STATUS_CLEARED = "DISCARDED_YCZ"; //�ʲ�״̬--�ѱ����Ѵ���
	String ASSETS_STATUS_FREED = "FREE"; //�ʲ�״̬--������
	String ASSETS_STATUS_SHARE = "SHARE";     //�ʲ�״̬--�ѹ���
	String ASSETS_STATUS_SUB = "IMPAIRMENT"; //�ʲ�״̬--�Ѽ�ֵ
	String ASSETS_STATUS_SELL = "SELL";	//�ʲ�״̬����
	String ASSETS_STATUS_RENT = "RENT";	//�ʲ�״̬--����
	String ASSETS_STATUS_DONATION = "DONATE";	//�ʲ�״̬--����
	String ASSETS_STATUS_PRE_DISCARDE = "PRE_DISCARDE";	//�ʲ�״̬--Ԥ����
	String ASSETS_STATUS_EXCHANGE = "EXCHANGE";	//�ʲ�״̬--�û�
	String ASSETS_STATUS_SEND_REPAIR = "SEND_REPAIR";	//�ʲ�״̬--����

	String ASSETS_STS_NORMAL = "0"; //����
	String ASSETS_STS_DISCARD = "1"; //����
	String ASSETS_STS_CLEAR = "2"; //����
	String ADOPT_SCAN_RESULT = "0"; //��ɨ����Ϊ׼
	String ADOPT_SYST_STATUS = "1"; //��ϵͳ״̬Ϊ׼
    String ITEM_STATUS = "ITEM_STATUS"; //�豸״̬
    String SHARE_STATUS = "SHARE_STATUS"; //�豸״̬
    String DIS_TYPE = "DIS_TYPE"; //��������
    String DEAL_TYPE = "DEAL_TYPE";//��������

    //start===============����ָ���౨������=========================//
	String MANAGE_INDICATORS = "MANAGE_INDICATORS"; //����ָ���౨������
	//end===============����ָ���౨������=========================//

    //start===============�ʲ��������Ͳ���=========================//
	String ASS_CHK = "ASS-CHK"; //�̵㹤��
	String ASS_RCV = "ASS-RCV"; //�ʲ����յ�
	String ASS_DIS = "ASS-DIS"; //�ʲ�baofei��
	String ASS_FREE = "ASS-FREE"; //�ʲ����õ�
	String ASS_LOSSRPT = "ASS-LOSSRPT"; //�ʲ���ʧ�� 
	
	String ASS_SUB = "ASS-SUB"; //�ʲ���ֵ��
	String ASS_SHARE = "ASS-SHARE";	//�ʲ�����
	String RNT_CHK = "RNT-CHK"; //�����̵�
	String INS_CHK = "INS-CHK"; //�Ǳ��̵�(������)
	String ASS_SELL = "ASS-SELL"; //�ʲ����۵�
	String ASS_RENT = "ASS-RENT"; //�ʲ����ⵥ
	String ASS_DONA = "ASS-DONA"; //�ʲ�������
	String ASS_RENTINTO ="ASS-RENTINTO";//��Ӫ���뵥
	String ASS_DEVALUE = "ASS-DEVALUE"; //�ʲ���ֵ��
	String ASS_WASTEFORECAST ="ASS-WASTEFORECAST";//�ʲ�Ԥ���ϵ�
	String ASS_REPAIR ="ASS-REPAIR";//�ʲ�Ԥ���ϵ�
	//end===============�ʲ��������Ͳ���=========================//

	//start===============�̵㹤��״̬=========================//
	String CHKORDER_STATUS = "CHKORDER_STATUS"; //�̵㹤��״̬
	String CHK_STATUS_SAVE_TEMP = "SAVE_TEMP"; //�ݴ�
	String CHK_STATUS_CANCELED = "CANCELED"; //�ѳ���
	String CHK_STATUS_IN_PROCESS = "IN_PROCESS"; //������
	String CHK_STATUS_REJECTED = "REJECTED"; //���˻�
	String CHK_STATUS_APPROVED = "APPROVED"; //������
	String CHK_STATUS_DISTRUIBUTED = "DISTRIBUTED"; //���·�
	String CHK_STATUS_DOWNLOADED = "DOWNLOADED"; //������
	String CHK_STATUS_UPLOADED = "UPLOADED"; //������
	String CHK_STATUS_ARCHIEVED = "ARCHIEVED"; //�ѹ鵵

	String CHKD_STATUS_SAVE_TEMP = "�ݴ�"; //�ݴ�
	String CHKD_STATUS_CANCELED = "�ѳ���"; //�ѳ���
	String CHKD_STATUS_IN_PROCESS = "������"; //������
	String CHKD_STATUS_REJECTED = "���˻�"; //���˻�
	String CHKD_STATUS_APPROVED = "������"; //������
	String CHKD_STATUS_DISTRUIBUTED = "���·�"; //���·�
	String CHKD_STATUS_DOWNLOADED = "������"; //������
	String CHKD_STATUS_UPLOADED = "������"; //������
	String CHKD_STATUS_ACHIEVED = "�ѹ鵵"; //�ѹ鵵


	String CHKORDER_STATUS_NEW = "����";
	String CHKORDER_STATUS_DISTRUIBUTED = "���·�";
	String CHKORDER_STATUS_DOWNLOADED = "������";
	String CHKORDER_STATUS_UPLOADED = "���ϴ�";
	String CHKORDER_STATUS_ACHIEVED = "�ѹ鵵";
	String CHKORDER_STATUS_CANCELE = "�ѳ���";
	//end===============�̵㹤��״̬=========================//


	String TRANSFER_INN_DEPT = "INN_DEPT"; //�����ڵ���
	String TRANSFER_BTW_DEPT = "BTW_DEPT"; //���ż����
	String TRANSFER_BTW_COMP = "BTW_COMP"; //OU�����

	String PROCEDURE_NAME_TRANSFER = "�ʲ���������";
	String PROCEDURE_TRANS_INN_DEPT = "�ʲ���������(������)";
	String PROCEDURE_TRANS_BTW_DEPT = "�ʲ���������(���ż�)";
	
	String PROCEDURE_TRANS_INN_DEPT_RFU = "�ʲ�������������(������)";
	
    String PROCEDURE_ITEM_TRANS_INN_DEPT = "ʵ���������(������)";
	String PROCEDURE_ITEM_TRANS_BTW_DEPT = "ʵ���������(���ż�)";
	String PROCEDURE_ITEM_TRANS_BTW_COMP = "ʵ���������(��˾��)";
	String PROCEDURE_TRANS_BTW_COMP = "�ʲ���������(��˾��)";
	String PROCEDURE_NAME_TRANSFER_TD = "TD�ʲ���������";
	String PROCEDURE_TRANS_BTW_COMP_TD = "TD�ʲ���������(��˾��)";
	String PROCEDURE_NAME_DISCARD = "�ʲ���������";
	String PROCEDURE_NAME_DISCARD_TD = "TD�ʲ���������";
	String PROCEDURE_NAME_CLEAR = "�ʲ���������";
	String PROCEDURE_NAME_CHECK = "�ʲ��̵�����";
	String PROCEDURE_NAME_FREE = "�ʲ���������";
	String PROCEDURE_NAME_SUB = "�ʲ���ֵ����";
	String PROCEDURE_NAME_RCV = "�ʲ�����������������";
	String PROCEDURE_NAME_SHARE = "�ʲ������������";
	String PROCEDURE_NAME_SELL = "�ʲ���������";
	String PROCEDURE_NAME_RENT = "�ʲ���������";
	String PROCEDURE_NAME_DONA = "�ʲ���������";
	String PROCEDURE_NAME_REPAIRRETURN = "�ʲ����޷�������";
	String PROCEDURE_NAME_REPAIR = "�ʲ���������";
	String PROCEDURE_NAME_WASTEFORECAST = "�ʲ�Ԥ��������";
	String PROCEDURE_NAME_REPEAL="�ʲ�Ԥ���ϳ�������";
	String PROCEDURE_NAME_REPLACEMENT = "�ʲ��û�����";
	String PROCEDURE_NAME_BORROW = "�ʲ���������";
	String PROCEDURE_NAME_RETURN = "�ʲ��ͻ�����";
	String PROCEDURE_NAME_SPECIAL_TRANSFER ="�����ʲ���������";

	String ASSIGNED_BOTH_NOT = "1"; //�����˺����β��ž�δ����
	String ASSIGNED_PERSON_NOT = "2"; //������δ����
	String ASSIGNED_DEPT_NOT = "3"; //���β���δ����
	String ASSIGNED_PERSON_YES = "4"; //�������ѷ���
	String ASSIGNED_DEPT_YES = "5"; //���β����ѷ���
	String ASSIGNED_BOTH_YES = "6"; //�����˺����β��ž��ѷ���

	String ASSIGNED_BOTH_NOT_L = "��δ����";
	String ASSIGNED_PERSON_NOT_L = "������δ����";
	String ASSIGNED_DEPT_NOT_L = "���β���δ����";
	String ASSIGNED_PERSON_YES_L = "�������ѷ���";
	String ASSIGNED_DEPT_YES_L = "���β����ѷ���";
	String ASSIGNED_BOTH_YES_L = "���ѷ���";

	String[] ASSIGNED_VALUE_LIMIT = {ASSIGNED_BOTH_NOT, ASSIGNED_PERSON_NOT,
									ASSIGNED_DEPT_NOT, ASSIGNED_PERSON_YES,
									ASSIGNED_DEPT_YES, ASSIGNED_BOTH_YES};
	String[] ASSIGNED_LABEL_LIMIT = {ASSIGNED_BOTH_NOT_L, ASSIGNED_PERSON_NOT_L,
									ASSIGNED_DEPT_NOT_L,
									ASSIGNED_PERSON_YES_L, ASSIGNED_DEPT_YES_L,
									ASSIGNED_BOTH_YES_L};

	String ARCHIVE_AS_SCAN = "0"; //��ɨ��״̬Ϊ׼
	String ARCHIVE_AS_CURR = "1"; //��Ŀǰ״̬Ϊ׼

	String ARCHIVE_SCAN_REMARK = "��ɨ��״̬Ϊ׼";
	String ARCHIVE_CURR_REMARK = "��Ŀǰ״̬Ϊ׼";
	String TRANS_ASSETS = "TRANS_ASSETS"; //�����ʲ��������ʲ�����
	String OWNER_ASSERS = "OWNER_ASSERS"; //ԭ���ʲ��������ʲ�����

	String TRANS_ASSETS_L = "�����ʲ�"; //�����ʲ��������ʲ�����
	String OWNER_ASSERS_L = "ԭ���ʲ�"; //ԭ���ʲ��������ʲ�����

	String DEPRE_ACCOUNT_SET_NAME = "DEPRE_ACCOUNT_SET_NAME"; //�۾��˻�

	String AMS_FA_CATEGORY_V = "AMS_FA_CATEGORY_V"; //�ʲ������ͼ(��Ҫ��Ϊ��̬��ͼ����)
	String AMS_ACCOUNT_V = "AMS_ACCOUNT_V"; //�۾��˻���ͼ(��Ҫ��Ϊ��̬��ͼ����)
	String ASS_CHK_TASK = "CHK-TASK"; //�̵�����
	String FIELD_FOR_QUERY = "FOR_QUERY"; //��ѯ���ֶ�
	String FIELD_FOR_DISPL = "FOR_DISPL"; //��ʾ���ֶ�

	String ASS_CHK_PAD = "�̵�"; //�ʲ��̵㹤����PDA�˵�����(������Ϊ����ǰ����)
	String INS_CHK_PAD = "�Ǳ��̵�"; //�Ǳ��̵㹤����PDA�˵�����
	String RNT_CHK_PAD = "�����̵�"; //�����ʲ��̵㹤����PDA�˵�����
	String ASS_CHK_SRV = "�ʲ��̵�"; //�ʲ��̵㹤���ڷ���˶˵�����


	String SACN_YES = "5"; //ɨ�赽�̵��ʲ�
	String SACN_NO = "6"; //δɨ�赽�̵��ʲ�
	String FA_CONTENT_CODE = "FA_CONTENT_CODE"; //�̶��ʲ�Ŀ¼����
	String FA_CONTENT_NET = "NET-ASSETS"; //�������ʲ�
	String FA_CONTENT_MGR = "MGR-ASSETS"; //�������ʲ�
	String COMM_FILE = "COMM_FILE"; //��ͨ����
	String SCAN_FILE = "COMM_FILE"; //ɨ�踽��
	String SPLIT_FLOW = "SPLIT_FLOW"; //���ڷ���(�ۺϲ��������粿)ǰ�Ľڵ����������
    String SPLIT_FLOW2 = "SPLIT_FLOW2";//���ڲ��ż�����ж��Ƿ�����ύ������
    String SPLIT_FLOW3 = "SPLIT_FLOW3"; //���ڲ��ż�͹�˾�g�����ж��Ƿ�ֱ���ύ�o�{�벿�T���������
    String FINANCE_DEPT = "����";
    String SYN_STATUS_YES = "1"; //��ͬ��
	String SYN_STATUS_NO = "0"; //δͬ��

	String PROVINCE_CODE_NM = DictConstant.PROVINCE_CODE_NM; //�����ƶ���˾ʡ����
	String PROVINCE_CODE_SX = DictConstant.PROVINCE_CODE_JIN; //ɽ���ƶ���˾ʡ����
	String PROVINCE_CODE_SN = DictConstant.PROVINCE_CODE_SHAN; //�����ƶ���˾ʡ����
	String TRANS_EDIT_YES = "EDIT_YES"; //�ʲ����������еĿɱ༭�ڵ�
	String GROUP_PROP_COMM = "0"; //��ͨ���
	String GROUP_PROP_SPEC = "1"; //רҵ���
	String MGR_DPT = "MGR-DPT";
	String RCV_DPT = "RCV-DPT";
	String ACHIEVE_ROLE="%�鵵��%";
	String CANCAL_REASON = "�ݴ��������";

	String ANALYZE_CATEGORY_1 = "1";//��Ӧ���������
	String ANALYZE_CATEGORY_2 = "2";//���ʲ�������
	String ANALYZE_CATEGORY_3 = "3";//����(����Ŀ��)����
	String ANALYZE_CATEGORY_4 = "4";//�̵�������

	String CHECK_RESULT_1 = "1";//��ʵһ��
	String CHECK_RESULT_2 = "2";//����һ�£�����������һ�����
	String CHECK_RESULT_3 = "3";//�п�����
	String CHECK_RESULT_4 = "4";//�����޿�
	String CHECK_RESULT_5 = "5";//�������̵�ص���ʲ�ͳ���п�����
    String CHECK_RESULT_6 = "6";//����PDAɨ���嵥

    String NEW_TAG_NODE = "NEW_TAG_NODE";//�����±�ǩ�ڵ�
	String TRANS_OUT_REMARK = "��˾���ʲ�����(����)����";
	String TRANS_IN_REMARK = "��˾���ʲ�����(����)����";
	String EDIT_ACCOUNT = "EDIT_ACCOUNT";//���ڹ�˾������п��Ա༭ѡ����շ����˻�������

    String ORDER_TYPE_ASSETS = "ORDER_TYPE_ASSETS"; //�ʲ��������ͣ����ڸ��˹�����ѯ
    String[] TRANS_TYPE_VALUES = {CREATE, SAVE_TEMP, CANCELED, COMPLETED, REJECTED, IN_PROCESS, APPROVED, RECEIVED, ASSIGNED, CONFIRMD, DISTRIBUTED, DOWNLOADED, UPLOADED, ARCHIEVED};
	String[] TRANS_TYPE_LABELS = {"����", "�ݴ�", "����", "���", "�˻�", "������", "������", "�ѽ���", "�ѷ���", "��ȷ��", "���·�", "������", "������", "�ѹ鵵"};

	String EMERGENT_LEVEL = "EMERGENT_LEVEL"; //ҵ������̶�
	
	String PROCEDURE_NAME_DEVALUE = "�ʲ���ֵ����";
}
