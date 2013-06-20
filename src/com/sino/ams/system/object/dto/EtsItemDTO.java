package com.sino.ams.system.object.dto;

import java.util.Date;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-4-26
 * Time: 13:14:17
 * To change this template use File | Settings | File Templates.
 */
public class EtsItemDTO extends CommonRecordDTO {
    private String bookTypeCode = "";//�ʲ��ʲ�  ETS_OU_CITY_MAP
    private String barcode = "";//��ǩ��
    private String contentCode = "";
    private String oldContentCode = "";
    private String oldItemName = "";
    private String newItemName = "";
    private String oldItemSpec = "";
    private String newItemSpec = "";
    private String oldObjectCode = "";
    private String newObjectCode = "";
    private String oldResponsibilityDept = "";
    private String newResponsibilityDept = "";
    private String oldSpecialityDept = "";
    private String newSpecialityDept = "";
    private String oldEmployeeNumber = "";
    private String newEmployeeNumber = "";
    private String oldMaintainDept = "";
    private String newMaintainDept = "";
    private String oldMaintainUser = "";
    private String newMaintainUser = "";
    private String oldManufacturerId = "";
    private String newManufacturerId = "";
    private String oldLneId = "";
    private String newLneId = "";
    private String oldCexId = "";
    private String newCexId = "";
    private String oldOpeId = "";
    private String newOpeId = "";
    private String oldNleId = "";
    private String newNleId = "";
    private String oldSnId = "";
    private String newSnId = "";
    private int userId ;
    private String oldRemark1 = "";
    private String newRemark1 = "";
    private String oldPower = "";
    private String newPower = "";
    private String oldShareStatus = "";
    private String newShareStatus = "";
    private String oldConstructStatus = "";
    private String newConstructStatus = "";

    private String itemCode = "";//�豸�������
    private String addressId = "";
    private String employeeId = "";
    
    //----------------------------------------
    private String excelLineId;//����Excel�е��к�
    private String  vendorBarcode;//��Ӧ��������
    private int  itemQty;//����
    private Date disableDate;//ʧЧ����
    private String remark;//��ע
    private Date startDates;//��������
    private String projectObjId;//����ID
    private String itemSatus;//�豸״̬(�����Ǿ�״̬)
    private String attribute1;//��չ����1;��������
    private String attribute2;//��չ����2
    private String sendtomisFlag;//ͬ����MIS��־
    private String misItemname;//MIS�豸����
    private String misItemspec;//MIS����ͺ�
    //private Date creationDate;//��������
    private int  createdBy;//������
    //private Date lastUpdateDate;//�ϴ��޸�����
    //private int lastUpdateBy;//�ϴ��޸���
    private int assetId;//�����ʲ�ID
    //private String addressId;//�ʲ��ص�ID
    private String  financeProp;//��������
    private String attribute3;//��չ����3(���Ǿ���;)
    private String parentBarcode;//���豸����
    private Date LastLocChgDate;//�豸�ص����䶯ʱ��
    //private int organizationId;//��֯ID
    private String faBarCode;//��ӦMIS�̶��ʲ�����;��ֹʹ���ֶ�
    private String isParent;//�Ƿ��Ǹ��豸,��һ�豸��Ϊ���豸
    //private String responsibilityUser;//������
    //private String responsibilityDept;//���β���
    //private String mainainUser;//ά����Ա(�Ǿ������)
    //private String maintainDept;//ʹ�ò���
    private String manufacturerId;//������ϢID
    private String isShare;//�Ƿ���
   // private String contentCode;//�ʲ�Ŀ¼����
    private String contentName;//�ʲ�Ŀ¼����
    private String power;//�����
    //private String lneId;//�߼�����Ԫ������
    //private String cexId;//Ͷ�ʷ�������
    private String opeId;//ҵ��ƽ̨����
    //private String nleId;//����������;
    private int isTap;//1:��ʱ�豸
    private double price;//����
    //private String oldContentCode;//�ʲ�Ŀ¼����
    private String oldContentName;//�ʲ�Ŀ¼����
    private String repManufacturerId;//��¼��ǰ�豸ά�޳���ID
    //private String specialityDept;//�豸רҵ������
    private String dzyhAddress;//��ֵ�׺ĵ�ַ
    private String otherInfo;//������Ϣ��һ����д�豸���ܣ�����������
    //private String shareStatus;//����״̬,������,����ͨ����,����Ź���,��������
    private String isTd;//�Ƿ�TD
    private double actualQty;//
    private String isRental;//�Ƿ����
    //private String remark1;
    private String remark2;
    private String unitOfMeasure;//
    private String discardType;//
    private String dealType;//
    private String referNationalFound;//
    private String snId;//
    //private String constructStatus;//
    private String tfnetAssetValue;
    private String tfDeprnCost;//
    private String tfDepreciation;
    private String oldBarcode;
    private String specialityUser2;
    private String specialityUser;//
    
    
    
	public String getItemSatus() {
		return itemSatus;
	}

	public void setItemSatus(String itemSatus) {
		this.itemSatus = itemSatus;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getSendtomisFlag() {
		return sendtomisFlag;
	}

	public void setSendtomisFlag(String sendtomisFlag) {
		this.sendtomisFlag = sendtomisFlag;
	}

	public String getMisItemname() {
		return misItemname;
	}

	public void setMisItemname(String misItemname) {
		this.misItemname = misItemname;
	}

	public String getMisItemspec() {
		return misItemspec;
	}

	public void setMisItemspec(String misItemspec) {
		this.misItemspec = misItemspec;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}


	public String getFinanceProp() {
		return financeProp;
	}

	public void setFinanceProp(String financeProp) {
		this.financeProp = financeProp;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getParentBarcode() {
		return parentBarcode;
	}

	public void setParentBarcode(String parentBarcode) {
		this.parentBarcode = parentBarcode;
	}

	public Date getLastLocChgDate() {
		return LastLocChgDate;
	}

	public void setLastLocChgDate(Date lastLocChgDate) {
		LastLocChgDate = lastLocChgDate;
	}

	public String getFaBarCode() {
		return faBarCode;
	}

	public void setFaBarCode(String faBarCode) {
		this.faBarCode = faBarCode;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
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

	public String getOpeId() {
		return opeId;
	}

	public void setOpeId(String opeId) {
		this.opeId = opeId;
	}

	public int getIsTap() {
		return isTap;
	}

	public void setIsTap(int isTap) {
		this.isTap = isTap;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOldContentName() {
		return oldContentName;
	}

	public void setOldContentName(String oldContentName) {
		this.oldContentName = oldContentName;
	}

	public String getRepManufacturerId() {
		return repManufacturerId;
	}

	public void setRepManufacturerId(String repManufacturerId) {
		this.repManufacturerId = repManufacturerId;
	}

	public String getDzyhAddress() {
		return dzyhAddress;
	}

	public void setDzyhAddress(String dzyhAddress) {
		this.dzyhAddress = dzyhAddress;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getIsTd() {
		return isTd;
	}

	public void setIsTd(String isTd) {
		this.isTd = isTd;
	}

	public double getActualQty() {
		return actualQty;
	}

	public void setActualQty(double actualQty) {
		this.actualQty = actualQty;
	}

	public String getIsRental() {
		return isRental;
	}

	public void setIsRental(String isRental) {
		this.isRental = isRental;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getDiscardType() {
		return discardType;
	}

	public void setDiscardType(String discardType) {
		this.discardType = discardType;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getReferNationalFound() {
		return referNationalFound;
	}

	public void setReferNationalFound(String referNationalFound) {
		this.referNationalFound = referNationalFound;
	}

	public String getSnId() {
		return snId;
	}

	public void setSnId(String snId) {
		this.snId = snId;
	}

	public String getTfnetAssetValue() {
		return tfnetAssetValue;
	}

	public void setTfnetAssetValue(String tfnetAssetValue) {
		this.tfnetAssetValue = tfnetAssetValue;
	}

	public String getTfDeprnCost() {
		return tfDeprnCost;
	}

	public void setTfDeprnCost(String tfDeprnCost) {
		this.tfDeprnCost = tfDeprnCost;
	}

	public String getTfDepreciation() {
		return tfDepreciation;
	}

	public void setTfDepreciation(String tfDepreciation) {
		this.tfDepreciation = tfDepreciation;
	}

	public String getOldBarcode() {
		return oldBarcode;
	}

	public void setOldBarcode(String oldBarcode) {
		this.oldBarcode = oldBarcode;
	}

	public String getSpecialityUser2() {
		return specialityUser2;
	}

	public void setSpecialityUser2(String specialityUser2) {
		this.specialityUser2 = specialityUser2;
	}

	public String getSpecialityUser() {
		return specialityUser;
	}

	public void setSpecialityUser(String specialityUser) {
		this.specialityUser = specialityUser;
	}

	public String getProjectObjId() {
		return projectObjId;
	}

	public void setProjectObjId(String projectObjId) {
		this.projectObjId = projectObjId;
	}

	public Date getStartDates() {
		return startDates;
	}

	public void setStartDates(Date startDates) {
		this.startDates = startDates;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}

	public String getVendorBarcode() {
		return vendorBarcode;
	}

	public void setVendorBarcode(String vendorBarcode) {
		this.vendorBarcode = vendorBarcode;
	}

	public String getExcelLineId() {
		return excelLineId;
	}

	public void setExcelLineId(String excelLineId) {
		this.excelLineId = excelLineId;
	}
	
	

	public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public String getOldContentCode() {
        return oldContentCode;
    }

    public void setOldContentCode(String oldContentCode) {
        this.oldContentCode = oldContentCode;
    }

    public String getOldSnId() {
        return oldSnId;
    }

    public void setOldSnId(String oldSnId) {
        this.oldSnId = oldSnId;
    }

    public String getNewSnId() {
        return newSnId;
    }

    public void setNewSnId(String newSnId) {
        this.newSnId = newSnId;
    }

    public String getOldConstructStatus() {
        return oldConstructStatus;
    }

    public void setOldConstructStatus(String oldConstructStatus) {
        this.oldConstructStatus = oldConstructStatus;
    }

    public String getNewConstructStatus() {
        return newConstructStatus;
    }

    public void setNewConstructStatus(String newConstructStatus) {
        this.newConstructStatus = newConstructStatus;
    }

    public String getOldPower() {
        return oldPower;
    }

    public void setOldPower(String oldPower) {
        this.oldPower = oldPower;
    }

    public String getNewPower() {
        return newPower;
    }

    public void setNewPower(String newPower) {
        this.newPower = newPower;
    }

    public String getOldShareStatus() {
        return oldShareStatus;
    }

    public void setOldShareStatus(String oldShareStatus) {
        this.oldShareStatus = oldShareStatus;
    }

    public String getNewShareStatus() {
        return newShareStatus;
    }

    public void setNewShareStatus(String newShareStatus) {
        this.newShareStatus = newShareStatus;
    }

    public String getOldRemark1() {
        return oldRemark1;
    }

    public void setOldRemark1(String oldRemark1) {
        this.oldRemark1 = oldRemark1;
    }

    public String getNewRemark1() {
        return newRemark1;
    }

    public void setNewRemark1(String newRemark1) {
        this.newRemark1 = newRemark1;
    }

    public String getOldSpecialityDept() {
        return oldSpecialityDept;
    }

    public void setOldSpecialityDept(String oldSpecialityDept) {
        this.oldSpecialityDept = oldSpecialityDept;
    }

    public String getNewSpecialityDept() {
        return newSpecialityDept;
    }

    public void setNewSpecialityDept(String newSpecialityDept) {
        this.newSpecialityDept = newSpecialityDept;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getNewItemSpec() {
        return newItemSpec;
    }

    public void setNewItemSpec(String newItemSpec) {
        this.newItemSpec = newItemSpec;
    }

    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getOldItemName() {
        return oldItemName;
    }

    public void setOldItemName(String oldItemName) {
        this.oldItemName = oldItemName;
    }

    public String getNewItemName() {
        return newItemName;
    }

    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    public String getOldItemSpec() {
        return oldItemSpec;
    }

    public void setOldItemSpec(String oldItemSpec) {
        this.oldItemSpec = oldItemSpec;
    }

    public String getOldObjectCode() {
        return oldObjectCode;
    }

    public void setOldObjectCode(String oldObjectCode) {
        this.oldObjectCode = oldObjectCode;
    }

    public String getNewObjectCode() {
        return newObjectCode;
    }

    public void setNewObjectCode(String newObjectCode) {
        this.newObjectCode = newObjectCode;
    }

    public String getOldResponsibilityDept() {
        return oldResponsibilityDept;
    }

    public void setOldResponsibilityDept(String oldResponsibilityDept) {
        this.oldResponsibilityDept = oldResponsibilityDept;
    }

    public String getNewResponsibilityDept() {
        return newResponsibilityDept;
    }

    public void setNewResponsibilityDept(String newResponsibilityDept) {
        this.newResponsibilityDept = newResponsibilityDept;
    }

    public String getOldEmployeeNumber() {
        return oldEmployeeNumber;
    }

    public void setOldEmployeeNumber(String oldEmployeeNumber) {
        this.oldEmployeeNumber = oldEmployeeNumber;
    }

    public String getNewEmployeeNumber() {
        return newEmployeeNumber;
    }

    public void setNewEmployeeNumber(String newEmployeeNumber) {
        this.newEmployeeNumber = newEmployeeNumber;
    }

    public String getOldMaintainDept() {
        return oldMaintainDept;
    }

    public void setOldMaintainDept(String oldMaintainDept) {
        this.oldMaintainDept = oldMaintainDept;
    }

    public String getNewMaintainDept() {
        return newMaintainDept;
    }

    public void setNewMaintainDept(String newMaintainDept) {
        this.newMaintainDept = newMaintainDept;
    }

    public String getOldMaintainUser() {
        return oldMaintainUser;
    }

    public void setOldMaintainUser(String oldMaintainUser) {
        this.oldMaintainUser = oldMaintainUser;
    }

    public String getNewMaintainUser() {
        return newMaintainUser;
    }

    public void setNewMaintainUser(String newMaintainUser) {
        this.newMaintainUser = newMaintainUser;
    }

    public String getOldManufacturerId() {
        return oldManufacturerId;
    }

    public void setOldManufacturerId(String oldManufacturerId) {
        this.oldManufacturerId = oldManufacturerId;
    }

    public String getNewManufacturerId() {
        return newManufacturerId;
    }

    public void setNewManufacturerId(String newManufacturerId) {
        this.newManufacturerId = newManufacturerId;
    }

    public String getOldLneId() {
        return oldLneId;
    }

    public void setOldLneId(String oldLneId) {
        this.oldLneId = oldLneId;
    }

    public String getNewLneId() {
        return newLneId;
    }

    public void setNewLneId(String newLneId) {
        this.newLneId = newLneId;
    }

    public String getOldCexId() {
        return oldCexId;
    }

    public void setOldCexId(String oldCexId) {
        this.oldCexId = oldCexId;
    }

    public String getNewCexId() {
        return newCexId;
    }

    public void setNewCexId(String newCexId) {
        this.newCexId = newCexId;
    }

    public String getOldOpeId() {
        return oldOpeId;
    }

    public void setOldOpeId(String oldOpeId) {
        this.oldOpeId = oldOpeId;
    }

    public String getNewOpeId() {
        return newOpeId;
    }

    public void setNewOpeId(String newOpeId) {
        this.newOpeId = newOpeId;
    }

    public String getOldNleId() {
        return oldNleId;
    }

    public void setOldNleId(String oldNleId) {
        this.oldNleId = oldNleId;
    }

    public String getNewNleId() {
        return newNleId;
    }

    public void setNewNleId(String newNleId) {
        this.newNleId = newNleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
