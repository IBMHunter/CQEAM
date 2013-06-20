/**
 *
 */
package com.sino.soa.common;

/**
 * @author dell
 */
public interface SrvURLDefineList {
    public final String SRV_PROJECT_PAGE = "/srv/project/SynProjectInfo.jsp";
    public final String MESSAGE_PRINT_PUB = "/srv/messagePrint.jsp";
    public final String SRV_VENDOR_INFO_SERVLET = "/servlet/srv.ams.vendor.servlet.SrvVendorInfoSrvServlet";
    public final String SRV_VENDOR_INFO_PAGE = "/srv/VendorInfo/VendorInfoEdit.jsp";
    public final String SRV_LOC_INFO_PAGE = "/srv/loc/synLoc.jsp";
    public final String SRV_ASSET_PERIOD_PAGE = "/srv/AssetPeriod/AssetPeriodEdit.jsp";
    public final String SRV_ASSET_PERIOD_SERVLET = "/servlet/srv.ams.assetperiod.servlet.SrvAssetPeriodServlet";
    public final String SRV_ASSET_CUST_DETAIL_SERVLET = "/servlet/srv.ams.assetcustdetail.servlet.SrvAssetCustDetailServlet";
    public final String TRANS_ASSET_CUST_DETAIL_PAGE = "/srv/assetcustdetail/TransAssetCustDetail.jsp";
    public final String SRV_Retired_Asset_Detail_SERVLET = "/servlet/srv.ams.retiredassetdetail.servlet.SrvRetiredAssetDetailServlet";
    public final String SRV_Retired_Asset_Detail_PAGE = "/srv/retiredassetdetail/retiredAssetDetailEdit.jsp";
    public final String SRV_TRANS_RETIRED_PAGE = "/srv/retiredassetdetail/retiredAssetDetail.jsp";      //�����ʲ���Ϣ��ȡ��ODI��
    public final String SRV_SERVICETYPE_PAGE = "/srv/servicetype/SynServiceTypeInfo.jsp";
    public final String SRV_PROJECTTASK_PAGE = "/srv/project/SynProjectTaskInfo.jsp";
    public final String SRV_ACCOUNTBALANCE_PAGE = "/srv/accountbalance/SynBalanceInfo.jsp";
    public final String SRV_ASSETS_UPDATE = "/srv/assetcustdetail/assetsUpdate.jsp";
    public final String SRV_ASSET_DEPRECATION_PAGE = "/srv/assetdeprecation/SynAssetDeprecation.jsp";
    public final String SRV_ASSET_LOCATION_PAGE="/srv/inquiryassetlocation/assetLocationEdit.jsp";
	public final String SRV_ASSET_LOCATION_SERVLET="/servlet/srv.ams.inquiryassetlocation.servlet.SrvAssetLocationServlet";
	
	public final String SRV_TRANSASSETHEADERINFO ="/srv/assetcustdetail/TransAssetHeaderInfo.jsp";       //��ѯ�ʲ�ͷ������ϢODI
	public final String TRANS_ASSET_DISTRIBUTION_PAGE = "/srv/transassetdistribution/transassetdistribution.jsp";
	public final String TRANS_ASSET_DISTRIBUTION_SERVLET = "/servlet/srv.ams.transassetdistribution.servlet.TransAssetDistributionSevlet";
	
	public final String MIS_PROJECTTASK_PAGE ="/srv/project/misProjectTaskQuery.jsp";
	public final String MIS_EMPLOYEE_PAGE ="/srv/employee/misEmployeeQuery.jsp";
	public final String MIS_ACCOUTBANALCE_PAGE ="/srv/accountbalance/misAccountBanalceQuery.jsp";
	
	public final String SEARCH_BOOKTYPE_CODE_PAGE="/srv/assetbook/searchBookTypeCode.jsp";
	public final String SEARCH_BOOKTYPE_CODE_SERVLET="/servlet/srv.ams.searchbooktypecode.servlet.SearchBookTypeCodeServlet";
	public final String SEARCH_lOCATION_PAGE="/srv/inquiryassetlocation/SearchLocation.jsp";
	public final String SEARCH_lOCATION__SERVLET="/servlet/srv.ams.inquiryassetlocation.servlet.SearchLocationServlet";

    //�ʲ��˲�
    public final String SRV_ESSET_BOOK_PAGE = "/srv/assetbook/assetBookEdit.jsp";
    public final String TD_SRV_ESSET_BOOK_PAGE = "/srv/assetbook/assetBookEditTd.jsp";
    public final String SRV_ESSET_BOOK_SERVLET = "/servlet/com.sino.soa.mis.srv.assetbook.servlet.SBFIFAAssetBookServlet";
    public final String TD_SRV_ESSET_BOOK_SERVLET = "/servlet/com.sino.soa.td.srv.assetbook.servlet.SBFIFATdAssetBookServlet";
    //�ʲ����
    public final String SRV_ESSET_CATEGORY_PAGE = "/srv/AssetCategory/AssetCategoryEdit.jsp";
    public final String TD_SRV_ESSET_CATEGORY_PAGE = "/srv/AssetCategory/AssetCategoryEditTd.jsp";
    public final String SRV_ESSET_CATEGORY_SERVLET = "/servlet/com.sino.soa.mis.srv.assetcategory.servlet.SBFIFASrvAssetCategoryServlet";
    public final String TD_SRV_ESSET_CATEGORY_SERVLET = "/servlet/com.sino.soa.td.srv.assetcategory.servlet.SBFIFATdSrvAssetCategoryServlet";
    //MISԱ����Ϣ
    public final String SRV_EMPLOYEE_PAGE = "/srv/employee/SynEmployeeInfo.jsp";
    public final String TD_SRV_EMPLOYEE_PAGE = "/srv/employee/SynEmployeeInfoTd.jsp";
    //ֵ����Ϣ
    public final String SRV_SETVALUE_PAGE = "/srv/setvalue/SynSetValueInfo.jsp";
    public final String TD_SRV_SETVALUE_PAGE = "/srv/setvalue/SynSetValueInfoTd.jsp";
    //��˾���ʲ�����
    public final String ASSETS_COMMITS = "/srv/assettransincompany/assettransincompany.jsp";
    public final String TD_ASSETS_COMMITS = "/srv/assettransincompany/tdAssetsInCompany.jsp";
    //��˾���ʲ�����
    public final String ASSETS_BTW_COMMITS = "/srv/assettransbtwcompany/assettransBtwcompany.jsp";
    public final String TD_ASSETS_BTW_COMMITS = "/srv/assettransbtwcompany/assettransBtwcompanyTd.jsp";
    
    //�ʲ��ص������������
    public final String SRV_ASSET_LOC_COMB_PAGE = "/srv/loccomb/synLocComb.jsp";
    public final String TD_SRV_ASSET_LOC_COMB_PAGE = "/srv/loccomb/synLocCombTd.jsp";       //TD
    public final String SRV_ASSET_LOC_COMB_SERVLET = "/servlet/com.sino.soa.mis.srv.assetLocComb.servlet.SrvAssetLocCombServlet";
    public final String TD_SRV_ASSET_LOC_COMB_SERVLET = "/servlet/com.sino.soa.td.srv.assetLocComb.servlet.TDSrvAssetLocCombServlet";  //TD
    //�ʲ����״̬
    public final String TD_SRV_ASSET_PERIOD_PAGE = "/srv/AssetPeriod/AssetPeriodTdEdit.jsp";  //TD  
    public final String SRV_ASSET_PERIOD_SERVLET1 = "/servlet/com.sino.soa.mis.srv.AssetPeriodStatus.servlet.SrvAssetPeriodServlet";  //��
    public final String TD_SRV_ASSET_PERIOD_SERVLET1 = "/servlet/com.sino.soa.td.srv.AssetPeriodStatus.servlet.TDSrvAssetPeriodServlet";  //TD

    //�ʲ��ص���
    public final String TD_SRV_ASSET_LOCATION_PAGE="/srv/inquiryassetlocation/assetLocationTdEdit.jsp";
	public final String SRV_ASSET_LOCATION_SERVLET1="/servlet/com.sino.soa.mis.srv.inquiryassetlocation.servlet.SrvAssetLocationServlet";   //��
	public final String TD_SRV_ASSET_LOCATION_SERVLET1="/servlet/com.sino.soa.td.srv.inquiryassetlocation.servlet.TDSrvAssetLocationServlet";   //TD
	 
	//��Ӧ����Ϣͬ��
    public final String SRV_VENDOR_INFO_SERVLET1 = "/servlet/com.sino.soa.mis.srv.vendor.servlet.SrvVendorInfoSrvServlet";   //��
    public final String TD_SRV_VENDOR_INFO_PAGE = "/srv/VendorInfo/VendorInfoTdEdit.jsp";
    public final String TD_SRV_VENDOR_INFO_SERVLET1= "/servlet/com.sino.soa.td.srv.vendor.servlet.TDSrvVendorInfoSrvServlet"; //TD
    
    //�ʲ�������Ϣ�޸�
    public final String TD_SRV_ASSETS_UPDATE = "/srv/assetcustdetail/assetsTdUpdate.jsp";
    
    //�����ʲ���Ϣ��ȡ_TD��ODI��
    public final String TD_SRV_TRANS_RETIRED_PAGE = "/srv/retiredassetdetail/retiredAssetDetailTd.jsp";      
    //��ѯ�ʲ�ͷ������Ϣ_TD��ODI��
	public final String TD_SRV_TRANSASSETHEADERINFO ="/srv/assetcustdetail/TransAssetHeaderInfoTd.jsp";
    
    //�̶��ʲ��۾���Ϣ��ODI��
    public final String TRANS_ASSET_DEPRECATION_PAGE = "/srv/assetdeprecation/TransAssetDeprecation.jsp";
    public final String TD_TRANS_ASSET_DEPRECATION_PAGE = "/srv/assetdeprecation/TransAssetDeprecationTd.jsp";

    //��ѯ�̶��ʲ��۾���Ϣ����ҳ��
    public final String PAGE_TRANS_ASSET_DEPRECATION_PAGE = "/srv/pageassetdeprecation/pageTransAssetDeprecation.jsp";
    public final String PAGE_TD_TRANS_ASSET_DEPRECATION_PAGE = "/srv/pageassetdeprecation/pageTransAssetDeprecationTd.jsp";

    //��ѯ��Ŀ���(ODI)
    public final String SRV_ACCOUNT_BALANCE_PAGE = "/srv/accountbalance/accountBalance.jsp";
    public final String TD_SRV_ACCOUNT_BALANCE_PAGE = "/srv/accountbalance/accountBalanceTd.jsp";
    
    //��ѯ��Ŀ����ҳ��
    public final String PAGE_ACCOUNT_BALANCE_PAGE = "/srv/pagequiryaccountbalance/pageAccountBalance.jsp";
    public final String PAGE_TD_ACCOUNT_BALANCE_PAGE = "/srv/pagequiryaccountbalance/pageAccountBalanceTd.jsp";

    //ת���ʲ��嵥(ODI)
    public final String SRV_ASSET_CUST_DETAIL_PAGE = "/srv/assetcustdetail/assetCustDetailEdit.jsp";
    public final String TD_SRV_ASSET_CUST_DETAIL_PAGE = "/srv/assetcustdetail/assetCustDetailEditTd.jsp";

    //ת���ʲ��嵥����ҳ��
    public final String PAGE_CUST_DETAIL_PAGE = "/srv/pagequiryassetcustdetail/pageAssetCustDetail.jsp";
    public final String PAGE_TD_CUST_DETAIL_PAGE = "/srv/pagequiryassetcustdetail/pageAssetCustDetailTd.jsp";
    public final String PAGE_CUST_DETAIL_PAGE_ERROR = "/srv/pagequiryassetcustdetail/pageAssetCustDetailError.jsp";
    public final String PAGE__TD_CUST_DETAIL_PAGE_ERROR = "/srv/pagequiryassetcustdetail/pageAssetCustDetailErrorTd.jsp";
	public final String CUST_DETAIL_SERVLET = "/servlet/com.sino.soa.mis.srv.pagequiryassetcustdetail.servlet.SBFIFAPageinquiryAssetCustDetailServlet";
	public final String TD_CUST_DETAIL_SERVLET = "/servlet/com.sino.soa.td.srv.pagequiryassetcustdetail.servlet.SBFIFATdPageinquiryAssetCustDetailServlet" ;
	
	//��ѯ�ʲ���������Ϣ(ODI)
	public final String TD_TRANS_ASSET_DISTRIBUTION_PAGE = "/srv/transassetdistribution/transassetdistributionTd.jsp";   
	public final String TRANS_ASSET_DISTRIBUTION_SERVLET1 = "/servlet/com.sino.soa.mis.srv.transassetdistribution.servlet.TransAssetDistributionSevlet";
	public final String TD_TRANS_ASSET_DISTRIBUTION_SERVLET1 ="/servlet/com.sino.soa.td.srv.transassetdistribution.servlet.TDTransAssetDistributionSevlet";
	//��ѯת�������嵥_TD(ODI)
    public final String TD_TRANS_ASSET_CUST_DETAIL_PAGE = "/srv/assetcustdetail/TransAssetCustDetailTd.jsp";  
    
    //��ѯ��Ŀ������Ϣ(ODI)(TransTaskInfoSrv)
    public final String TRANS_TASKINFO_PAGE = "/srv/transTaskInfo/transTaskInfo.jsp";
    public final String TD_TRANS_TASKINFO_PAGE = "/srv/transTaskInfo/transTaskInfoTd.jsp";
    public final String TRANS_TASKINFO_SERVLET = "/servlet/com.sino.soa.mis.srv.transTaskInfo.servlet.TransTaskInfoSevlet";
    public final String TD_TRANS_TASKINFO_SERVLET = "/servlet/com.sino.soa.td.srv.transTaskInfo.servlet.TDTransTaskInfoSevlet";
     
    //ͬ��TD��Ŀ��Ϣ
    public final String TD_SRV_PROJECT_PAGE = "/srv/project/SynProjectInfoTd.jsp";
    
    //��ѯ�ʲ�������Ϣ����(ODI)
    public final String TRANS_FAASSET_PAGE = "/srv/transFaAssetInfo/transFaAssetInfo.jsp";
    public final String TD_TRANS_FAASSET_PAGE = "/srv/transFaAssetInfo/transFaAssetInfoTd.jsp";
    public final String TRANS_FAASSET_SERVLET = "/servlet/com.sino.soa.mis.srv.transfaassetinfo.servlet.TransFaAssetInfoSevlet";
    public final String TD_TRANS_FAASSET_SERVLET = "/servlet/com.sino.soa.td.srv.transfaassetinfo.servlet.TDTransFaAssetInfoSevlet";
    
    
    //��ѯ��Ŀ������Ϣ����(��ҳ)(PageInquiryTaskInfoSrv) 
    public final String SRV_PROJECTTASK1_PAGE    = "/srv/transTaskInfo/SynProjectTaskInfo.jsp";
    public final String TD_SRV_PROJECTTASK1_PAGE = "/srv/transTaskInfo/SynProjectTaskInfoTd.jsp";
    
    //��֯�ṹ
    public final String SRV_ORG_STRUCTURE_PAGE = "/srv/orgstructure/orgstructure.jsp";
    public final String TD_SRV_ORG_STRUCTURE_PAGE = "/srv/orgstructure/orgstructureTd.jsp";
    
    //��ѯ�����ʲ�������Ϣ����ҳ��
    public final String SRV_RETIRED_ASSET_DETAIL1_PAGE = "/srv/retiredassetdetail/retiredAssetDetailEdit.jsp";    //��ѯ�����ʲ�������Ϣ����ҳ��
    public final String SRV_RETIRED_ASSET_DETAIL1_SERVLET = "/servlet/com.sino.soa.mis.srv.InquiryRetiredAssetDetail.servlet.PageRetiredAssetDetailServlet";
    public final String TD_SRV_RETIRED_ASSET_DETAIL1_PAGE = "/srv/retiredassetdetail/retiredAssetDetailTdEdit.jsp";     //TD
    public final String TD_SRV_RETIRED_ASSET_DETAIL1_SERVLET = "/servlet/com.sino.soa.td.srv.InquiryRetiredAssetDetail.servlet.TDPageRetiredAssetDetailServlet";
    
    //��ѯ�ʲ�ͷ������Ϣ����ҳ��
	public final String SRV_ASSET_HEADER_PAGE = "/srv/PageInquiryAssetHeader/PageAssetHeaderInfo.jsp";
	public final String TD_SRV_ASSET_HEADER_PAGE = "/srv/PageInquiryAssetHeader/PageAssetHeaderInfoTd.jsp";
	public final String SRV_ASSET_HEADER_SERVLET  = "/servlet/com.sino.soa.mis.srv.PageInquiryAssetHeaderInfo.servlet.PageInquiryAssetHeaderInfoServlet";
	public final String TD_SRV_ASSET_HEADER_SERVLET  = "/servlet/com.sino.soa.td.srv.PageInquiryAssetHeaderInfo.servlet.TDPageInquiryAssetHeaderInfoServlet";
	
	//��ѯ�ʲ���������Ϣ����ҳ��
	public final String SRV_ASSET_DISTRIBUTION_PAGE    = "/srv/PageAssetDistribution/AssetDistributionInfo.jsp";   
	public final String TD_SRV_ASSET_DISTRIBUTION_PAGE = "/srv/PageAssetDistribution/AssetDistributionInfoTd.jsp"; 
	public final String SRV_ASSET_DISTRIBUTION_SERVLET    = "/servlet/com.sino.soa.mis.srv.PageInquiryAssetDistribution.servlet.PageInquiryAssetDistributionServlet"; 
	public final String TD_SRV_ASSET_DISTRIBUTION_SERVLET = "/servlet/com.sino.soa.td.srv.PageInquiryAssetDistribution.servlet.TDPageInquiryAssetDistributionServlet"; 
    
	//�ص�ڶ���ͬ��
	 public final String SRV_ASSET_LOC2_COMB_PAGE = "/srv/loc2comb/synLoc2Comb.jsp";
	 public final String SRV_ASSET_LOC2_COMB_SERVLET = "/servlet/com.sino.soa.mis.srv.assetLoc2Comb.servlet.SrvAssetLoc2CombServlet";
	 
	 
}