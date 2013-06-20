package com.sino.ams.system.specialty.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CalendarUtililyDTO;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-12-20
 * Time: 11:53:42
 * To change this template use File | Settings | File Templates.
 */
public class OtherDTO  extends CalendarUtililyDTO {

    private String systemid = "";
    private String barcode = "";
    private String faBarcode = "";
    private String vendorBarcode = "";
    private int vendorId;
    private String itemQty = "";
    private SimpleCalendar disableDate = null;
    private String remark = "";
    private String belongto = "";
    private String itemCode = "";
    private int years;
    private String projectid = "";
    private String itemStatus = "";
    private String attribute1 = "";
    private String attribute2 = "";
    private String sendtomisFlag = "";
    private String misItemname = "";
    private String misItemspec = "";
    private int createdBy;
    private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateBy;
    private String assetId = "";
    private String addressId = "";
    private int organizationId;
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
    private String prjId = "";
    private String barcode1 = "";
    private String company = "";
    private String itemCategoryDesc = "";
    private String disable = "";
    private String systemId = "";
    private String companyCode = "";

    private String parentBarcode = "";
    private String boxNo = "";
    private String netUnit = "";
    private String countyCode = "";
    private String key = "";

	private String responsibilityUser = "";//������
	private String responsibilityUserName = "";//������
	private String responsibilityDept = "";//���β���
	private String responsibilityDeptName = "";//���β���
	private String maintainUser = "";//ά����Ա����ά��Ա��
	private String maintainUserName = "";//ά����Ա����ά��Ա��

    private String manufacturerId = "";//����ID
    private String manufacturerCode = "";//����CODE
    private String manufacturerName = "";//��������
    private String isShare = "";//�Ƿ���
    private String contentCode = "";//Ŀ¼����
    private String contentName = "";//Ŀ¼����
    private String power = "";//����

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public String getShare() {
        return isShare;
    }

    public void setShare(String share) {
        isShare = share;
    }

    public OtherDTO() {
        this.lastUpdateDate = new SimpleCalendar();
        this.disableDate = new SimpleCalendar();
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

    public String getPrjId() {
        return prjId;
    }

    public void setPrjId(String prjId) {
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
    public void setVendorId(int vendorId) {
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
     * @throws com.sino.base.exception.CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
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
    public void setYears(int years) {
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
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� ������
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �ϴ��޸�����
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �ϴ��޸���
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * ���ܣ����ñ�ǩ����Ϣ(EAM)���� �����ʲ�ID
     * @param assetId String
     */
    public void setAssetId(String assetId) {
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
    public int getVendorId() {
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
    public int getYears() {
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
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� ������
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �ϴ��޸�����
     * @return SimpleCalendar
     * @throws com.sino.base.exception.CalendarException
     *
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    /**
     * ���ܣ���ȡ��ǩ����Ϣ(EAM)���� �����ʲ�ID
     * @return String
     */
    public String getAssetId() {
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

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
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

	public String getMaintainUser() {
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

	public void setWorkorderObjectNo(String workorderObjectNo) {
        this.workorderObjectNo = workorderObjectNo;
    }

	public void setMaintainUser(String maintainUser) {
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
}
