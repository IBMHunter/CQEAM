package com.sino.ams.newasset.assetsharing.constant;

import com.sino.ams.constant.AMSActionConstant;
import com.sino.base.constant.web.WebActionConstant;

/**
 * ��Դ�������̹���ר�� ����
 * @author xiaohua
 *
 */
public  interface AssetSharingConstant extends WebActionConstant,
AMSActionConstant{
	String ASSET_SHARE_EDIT="/newasset/share/shareAssetsEdit.jsp";//��Դ���� ����ҳ��
	String ASSET_SHARE_DETAIL="/newasset/share/shareAssetsDetail.jsp";//��Դ���� ����ҳ��
	String ASSET_SHARE_PAGEDQUERY="/newasset/share/shareAssetsPageQuery.jsp";//��Դ���� ��ѯ
	String ASSET_SHARE_PAGEDQUERY_DETAIL="/newasset/share/shareAssetsPageQueryDetail.jsp";//��Դ���� ��ѯ
	String ASSET_SHARE_CODE="ASS-SHARE";//��Դ���� CODE
	String PRINT_QUERY_ACTION="PRINT_QUERY_ACTION";//
	String DTO_SET_HEADER="DTO_SET_HEADER";//
	String ASSET_SHARE_CODE_DESC="�ʲ�����";//��Դ����
	String ASSET_SHARE_DETAIL_ACTION="DETAIL_ACTION";//��Դ����
	String ASSET_SHARE_EDITABLE="EDITABLE";//�����Ƿ��ܱ༭
	String ASSET_SHARE_TASK_NAME0="�����ʲ��������뵥";//
	String ASSET_SHARE_TASK_NAME1="���ž������";//
	String ASSET_SHARE_TABLE_NAME="AMS_ASSETS_TRANS_HEADER";//
	String ASSET_SHARE_SERVLET="/servlet/com.sino.ams.newasset.assetsharing.servlet.AssetSharingServlet";//��Դ����
	String Trans_No="���ʱ�Զ�����";
	String SHARE_SF_APP_NAME="SHARE_ASSETS";//�ʲ����� Ӧ����
	String OPERATE_SUCCESS="OPERATE_SUCCESS";//"�����ɹ�";
	String OPERATE_FAILURE="OPERATE_FAILURE";//"����ʧ��";
	String OPERATE_SUCCESS_VALUE="�����ɹ�";//"�����ɹ�";
	String OPERATE_FAILURE_VALUE="����ʧ��";//"����ʧ��";
	String SHARE_TITLE1="�����ݲ�ѯ";//"�����ɹ�";
	String SHARE_TITLE2="�����ݴ�ӡ��ѯ";//"����ʧ��";
	 
}
