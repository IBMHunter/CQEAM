package com.sino.ams.system.fixing.dto;


import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;
import com.sino.ams.appbase.dto.AMSBaseDTO;
import com.sino.ams.bean.SyBaseSQLUtil;

/**
 * <p>Title: ��ǩ����Ϣ(EAM) EtsItemInfo</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class EtsItemInfoDTO extends AMSBaseDTO {

	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String  countyCode = "";
	private String diffTypeCode = ""; 
	private String systemid;
    private String barcode = "";
    private String faBarcode = "";
    private String vendorBarcode = "";
    private String vendorId;
    private String itemQty = "";
    private SimpleCalendar disableDate = null;
    private String remark = "";
    private String belongto = "";
    private String itemCode = "";
    private String years = "";
    private String projectid;
    private String itemStatus = "";
    private String attribute1 = "";
    private String attribute2 = "";
    private String sendtomisFlag = "";
    private String misItemname = "";
    private String misItemspec = "";
    private int assetId;
    private String addressId = "";
    private String addressNo = "";
    
    private String itemName = "";
    private String vendorName = "";
    private String itemCategory = "";
    private String itemSpec = "";
    private String userDate = "";
    private String age = "";
    private String address = "";
    private String projectName = "";
    private String prjName = "";
    private String workorderObjectNo = "";
    private String workorderObjectName = "";
    private String prjId;
    private String barcode1 = "";
    private String company = "";
    private String itemCategoryDesc = "";
    private String disable = "";
    private String systemId;
    private String companyCode = "";

    private String parentBarcode = "";
    private String boxNo = "";
    private String netUnit = "";
   
    private String key = "";
    private String workorderObjectLocation = "";
    private String workorderObjectCode = "";
    private String financeProp = "";

    private String responsibilityUser = ""; //������
    private String userName = ""; //������
    private String responsibilityUserName = "";//������
    private String responsibilityDept = "";//���β���
    private String deptName = ""; //ʹ�ò�������
    private String responsibilityDeptName = "";//���β���
    private String maintainUser = "" ;//ά����Ա����ά��Ա��
    private String maintainUserName = "";//ά����Ա����ά��Ա��
    private String maintainDept = "";//ʹ�ò���
    private String maintainDeptName = "";
    private String financePropName = "";
	private String newResponsibilityUser = "";//������
	private String newResponsibilityDept = "";//���β���
	private String newMaintainUser = "";//ά����Ա����ά��Ա��
	private String newAddressId = "";

    //---��������
    String manufacturerId = "" ; //����ID
    String manufacturerName = "";//����
    String isShare = "";//�Ƿ����豸
    String shareOption="";//����������
    String contentCode = ""; //�ʲ�Ŀ¼����
    String contentName = ""; //�ʲ�Ŀ¼����
    String power = "";//�����
    String lneId = ""; //�߼�����Ԫ������
    String cexId = ""; //Ͷ�ʷ�������
    String opeId = ""; //ҵ��ƽ̨����
    String nleId = ""; //����������
    String snId = ""; //֧�����豸����
    String shareStatus="";//����״̬
    private String dzyhAddress = "";//ɽ����ֵ�׺ĵص�

    private String financePropOpt = "";
    private String itemUnit = "";
    private String discardType = "";
    private String dealType = "";

    private String lneCode = "";
    private String cexCode = "";
    private String opeCode = "";
    private String nleCode = "";
    private String lneName = "";
    private String cexName = "";
    private String opeName = "";
    private String nleName = "";
    private String contentCodeBar = "";

    private String logNetEle = "";
    private String investCatName = "";
    private String snName = "";
    private String check = "";

    private String searchType = ""; 
       
    private String orderCategory = "";
    private String orderDtlUrl = "";
    private String oldContentCode = "";
    
    public String getOldContentCode() {
		return oldContentCode;
	}

	public void setOldContentCode(String oldContentCode) {
		this.oldContentCode = oldContentCode;
	}

	public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }


    public EtsItemInfoDTO() {
        this.disableDate = new SimpleCalendar();
        setPrimaryKeyName("systemid");
    }
    

    public String getLneCode() {
        return lneCode;
    }

    public void setLneCode(String lneCode) {
        this.lneCode = lneCode;
    }

    public String getCexCode() {
        return cexCode;
    }

    public void setCexCode(String cexCode) {
        this.cexCode = cexCode;
    }

    public String getOpeCode() {
        return opeCode;
    }

    public void setOpeCode(String opeCode) {
        this.opeCode = opeCode;
    }

    public String getNleCode() {
        return nleCode;
    }

    public void setNleCode(String nleCode) {
        this.nleCode = nleCode;
    }

    public String getCexName() {
        return cexName;
    }

    public void setCexName(String cexName) {
        this.cexName = cexName;
    }

    public String getNleName() {
        return nleName;
    }

    public void setNleName(String nleName) {
        this.nleName = nleName;
    }

    public String getContentCodeBar() {
        return contentCodeBar;
    }

    public void setContentCodeBar(String contentCodeBar) {
        this.contentCodeBar = contentCodeBar;
    }

    public String getSnId() {
        return snId;
    }

    public void setSnId(String snId) {
        this.snId = snId;
    }

    public String getLogNetEle() {
        return logNetEle;
    }

    public void setLogNetEle(String logNetEle) {
        this.logNetEle = logNetEle;
    }

    public String getInvestCatName() {
        return investCatName;
    }

    public void setInvestCatName(String investCatName) {
        this.investCatName = investCatName;
    }

    public String getOpeName() {
        return opeName;
    }

    public void setOpeName(String opeName) {
        this.opeName = opeName;
    }

    public String getLneName() {
        return lneName;
    }

    public void setLneName(String lneName) {
        this.lneName = lneName;
    }

    public String getSnName() {
        return snName;
    }

    public void setSnName(String snName) {
        this.snName = snName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDiscardType() {
        return discardType;
    }

    public void setDiscardType(String discardType) {
        this.discardType = discardType;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getFinancePropOpt() {
        return financePropOpt;
    }

    public void setFinancePropOpt(String financePropOpt) {
        this.financePropOpt = financePropOpt;
    }

    public String getDzyhAddress() {
        return dzyhAddress;
    }

    public void setDzyhAddress(String dzyhAddress) {
        this.dzyhAddress = dzyhAddress;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String getItemCategoryDesc() {
        return itemCategoryDesc;
    }

    public void setItemCategoryDesc(String itemCategoryDesc) {
        this.itemCategoryDesc = itemCategoryDesc;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBarcode1() {
        return barcode1;
    }

    public void setBarcode1(String barcode1) {
        this.barcode1 = barcode1;
    }

    public String  getPrjId() {
        return prjId;
    }

    public void setPrjId(String  prjId) {
        this.prjId = prjId;
    }

    public String getWorkorderObjectName() {
        return workorderObjectName;
    }

    public void setWorkorderObjectName(String workorderObjectName) {
        this.workorderObjectName = workorderObjectName;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserDate() {
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ϵͳID(���к�)
     * @param systemid String
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��ǩ��
     * @param barcode String
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��ӦMIS�̶��ʲ�����
     * @param faBarcode String
     */
    public void setFaBarcode(String faBarcode) {
        this.faBarcode = faBarcode;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��Ӧ��������
     * @param vendorBarcode String
     */
    public void setVendorBarcode(String vendorBarcode) {
        this.vendorBarcode = vendorBarcode;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��Ӧ��
     * @param vendorId String
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �豸����
     * @param itemQty String
     */
    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��Ч����
     * @param disableDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setDisableDate(String disableDate) throws CalendarException {
        this.disableDate.setCalendarValue(disableDate);
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��ע
     * @param remark String
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
     * @param belongto String
     */
    public void setBelongto(String belongto) {
        this.belongto = belongto;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �������
     * @param itemCode String
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��������
     * @param years String
     */
    public void setYears(String years) {
        this.years = years;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ����ID
     * @param projectid String
     */
    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �豸״̬
     * @param itemStatus String
     */
    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� null
     * @param attribute1 String
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� null
     * @param attribute2 String
     */
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ͬ����MIS��־
     * @param sendtomisFlag String
     */
    public void setSendtomisFlag(String sendtomisFlag) {
        this.sendtomisFlag = sendtomisFlag;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� MIS�豸����
     * @param misItemname String
     */
    public void setMisItemname(String misItemname) {
        this.misItemname = misItemname;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� MIS����ͺ�
     * @param misItemspec String
     */
    public void setMisItemspec(String misItemspec) {
        this.misItemspec = misItemspec;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �����ʲ�ID
     * @param assetId String
     */
    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �ʲ��ص�ID
     * @param addressId String
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ��֯ID
     * @param organizationId String
     */
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }


    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ϵͳID(���к�)
     * @return String
     */
    public String getSystemid() {
        return this.systemid;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��ǩ��
     * @return String
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��ӦMIS�̶��ʲ�����
     * @return String
     */
    public String getFaBarcode() {
        return this.faBarcode;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��Ӧ��������
     * @return String
     */
    public String getVendorBarcode() {
        return this.vendorBarcode;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��Ӧ��
     * @return String
     */
    public String getVendorId() {
        return this.vendorId;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �豸����
     * @return String
     */
    public String getItemQty() {
        return this.itemQty;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��Ч����
     * @return SimpleCalendar
     */
    public SimpleCalendar getDisableDate() {
        return this.disableDate;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��ע
     * @return String
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
     * @return String
     */
    public String getBelongto() {
        return this.belongto;
    }


    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �������
     * @return String
     */
    public String getItemCode() {
        return this.itemCode;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��������
     * @return String
     */
    public String getYears() {
        return this.years;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ����ID
     * @return String
     */
    public String getProjectid() {
        return this.projectid;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �豸״̬
     * @return String
     */
    public String getItemStatus() {
        return this.itemStatus;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� null
     * @return String
     */
    public String getAttribute1() {
        return this.attribute1;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� null
     * @return String
     */
    public String getAttribute2() {
        return this.attribute2;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ͬ����MIS��־
     * @return String
     */
    public String getSendtomisFlag() {
        return this.sendtomisFlag;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� MIS�豸����
     * @return String
     */
    public String getMisItemname() {
        return this.misItemname;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� MIS����ͺ�
     * @return String
     */
    public String getMisItemspec() {
        return this.misItemspec;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �����ʲ�ID
     * @return String
     */
    public int getAssetId() {
        return this.assetId;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �ʲ��ص�ID
     * @return String
     */
    public String getAddressId() {
        return this.addressId;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ��֯ID
     * @return String
     */
    public int getOrganizationId() {
        return this.organizationId;
    }


    public String getParentBarcode() {
        return parentBarcode;
    }

    public void setParentBarcode(String parentBarcode) {
        this.parentBarcode = parentBarcode;
    }


    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getNetUnit() {
        return netUnit;
    }

    public void setNetUnit(String netUnit) {
        this.netUnit = netUnit;
    }

    public String  getCountyCode() {
        return countyCode;
    }

    public String getDiffTypeCode() {
		return diffTypeCode;
	}

	public void setDiffTypeCode(String diffTypeCode) {
		this.diffTypeCode = diffTypeCode;
	}
    
    public void setCountyCode(String  countyCode) {
        this.countyCode = countyCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWorkorderObjectNo() {
        return workorderObjectNo;
    }

    public String  getMaintainUser() {
        return maintainUser;
    }

    public String getMaintainUserName() {
        return maintainUserName;
    }

    public String getResponsibilityDept() {
        return responsibilityDept;
    }

    public String getResponsibilityDeptName() {
        return responsibilityDeptName;
    }

    public String getResponsibilityUser() {
        return responsibilityUser;
    }

    public String getResponsibilityUserName() {
        return responsibilityUserName;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setWorkorderObjectNo(String workorderObjectNo) {
        this.workorderObjectNo = workorderObjectNo;
    }

    public void setMaintainUser(String  maintainUser) {
        this.maintainUser = maintainUser;
    }

    public void setMaintainUserName(String maintainUserName) {
        this.maintainUserName = maintainUserName;
    }

    public void setResponsibilityUserName(String responsibilityUserName) {
        this.responsibilityUserName = responsibilityUserName;
    }

    public void setResponsibilityUser(String responsibilityUser) {
        this.responsibilityUser = responsibilityUser;
    }

    public void setResponsibilityDeptName(String responsibilityDeptName) {
        this.responsibilityDeptName = responsibilityDeptName;
    }

    public void setResponsibilityDept(String responsibilityDept) {
        this.responsibilityDept = responsibilityDept;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public String getWorkorderObjectLocation() {
        return workorderObjectLocation;
    }

    public void setWorkorderObjectLocation(String workorderObjectLocation) {
        this.workorderObjectLocation = workorderObjectLocation;
    }

    public String getWorkorderObjectCode() {
        return workorderObjectCode;
    }

    public void setWorkorderObjectCode(String workorderObjectCode) {
        this.workorderObjectCode = workorderObjectCode;
    }

    public String getFinanceProp() {
        return financeProp;
    }

    public String getMaintainDept() {
        return maintainDept;
    }

    public void setFinanceProp(String financeProp) {
        this.financeProp = financeProp;
    }

    public void setMaintainDept(String maintainDept) {
        this.maintainDept = maintainDept;
    }

    public String getMaintainDeptName() {
        return maintainDeptName;
    }

    public void setMaintainDeptName(String maintainDeptName) {
        this.maintainDeptName = maintainDeptName;
    }

    public String getFinancePropName() {
        return financePropName;
    }

    public void setFinancePropName(String financePropName) {
        this.financePropName = financePropName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getShare() {
        return isShare;
    }

    public void setShare(String share) {
        isShare = share;
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getLneId() {
        return lneId;
    }

    public void setLneId(String lneId) {
        this.lneId = lneId;
    }

    public String getCexId() {
        return cexId;
    }

    public void setCexId(String cexId) {
        this.cexId = cexId;
    }

    public String getOpeId() {
        return opeId;
    }

    public void setOpeId(String opeId) {
        this.opeId = opeId;
    }

    public String getNleId() {
        return nleId;
    }

    public void setNleId(String nleId) {
        this.nleId = nleId;
    }

    public String getShareOption() {
        return shareOption;
    }

    public void setShareOption(String shareOption) {
        this.shareOption = shareOption;
    }

    public String getShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(String shareStatus) {
        this.shareStatus = shareStatus;
    }

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(String orderCategory) {
		this.orderCategory = orderCategory;
	}

	public String getOrderDtlUrl() {
		return orderDtlUrl;
	}

	public void setOrderDtlUrl(String orderDtlUrl) {
		this.orderDtlUrl = orderDtlUrl;
	}

	public String getNewResponsibilityUser() {
		return newResponsibilityUser;
	}

	public void setNewResponsibilityUser(String newResponsibilityUser) {
		this.newResponsibilityUser = newResponsibilityUser;
	}

	public String getNewResponsibilityDept() {
		return newResponsibilityDept;
	}

	public void setNewResponsibilityDept(String newResponsibilityDept) {
		this.newResponsibilityDept = newResponsibilityDept;
	}

	public String getNewMaintainUser() {
		return newMaintainUser;
	}

	public void setNewMaintainUser(String newMaintainUser) {
		this.newMaintainUser = newMaintainUser;
	}

	public String getNewAddressId() {
		return newAddressId;
	}

	public void setNewAddressId(String newAddressId) {
		this.newAddressId = newAddressId;
	}
}
