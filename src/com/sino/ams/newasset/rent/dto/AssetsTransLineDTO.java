package com.sino.ams.newasset.rent.dto;

import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

public class AssetsTransLineDTO extends AmsAssetsAddressVDTO{
	private String transId = "";
	private String transNo = "";
	private String lineId;
	private String assignedToLocation = "";
	private String assignedToLocationName = "";
	private SimpleCalendar assignedDate = null;
	private SimpleCalendar confirmDate = null;
	private String transType = "";
	private String hrDeptId = "";
	private String hrDeptOption = "";
	private String oldAddressId = "";
	private String oldLocation = "";
	private String oldLocationCode = "";
	private String oldLocationName = "";
	private String oldResponsibilityUser = "";
	private String oldResponsibilityDept = "";
	private String oldResponsibilityUserName = "";
	private String oldResponsibilityDeptName = "";
	private int toOrganizationId;

	private String lineStatus = "";
	private int receivedUser;
	private SimpleCalendar receivedDate = null;
	private String oldDepreciationAccount = "";
	private String oldFaCategoryCode = "";
	private String oldFaCategoryName = "";
	private SimpleCalendar lineTransDate = null;
	private String faContentCode = ""; //�ʲ������Ŀǰ��Ϊ�������໹��������
	private String faContentName = ""; //�ʲ������Ŀǰ��Ϊ�������໹��������

	private String lineReason = ""; //ҵ������������ԭ��
	private String softInuseVersion = ""; //������ð汾
	private String softDevalueVersion = ""; //�����ֵ�汾
	private String sumDepreciation = ""; //�ۼ��۾�
	private String netValue = ""; //��ֵ
	private float prepareDevalue; //�����ֵ
	private String userName = "";
	private String barcode = "";
	private String responsibilityUser = "";
	private String newBarcode = "";
    private float impairReserve ;
    private float deprnCost;
    private String manufacturerName = "";
    
    private String recycleValue="";
	private String assetsStatusDes = "";
	private int assetsStatus;
    private float retirementCost;//���ϳɱ�
    private String assetsType = "";//�ʲ����
    private String rejectType = "";
	private String rejectTypeName = "";
	private String rejectTypeOpt = "";
    public String getAssetsType() {
		return assetsType;
	}

    public String getRejectType() {
        return rejectType;
    }

    public void setRejectType(String rejectType) {
        this.rejectType = rejectType;
    }

    public String getRejectTypeName() {
        return rejectTypeName;
    }

    public void setRejectTypeName(String rejectTypeName) {
        this.rejectTypeName = rejectTypeName;
    }

    public String getRejectTypeOpt() {
        return rejectTypeOpt;
    }

    public void setRejectTypeOpt(String rejectTypeOpt) {
        this.rejectTypeOpt = rejectTypeOpt;
    }

    public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

	public float getRetirementCost() {
        return retirementCost;
    }

    public void setRetirementCost(float retirementCost) {
        this.retirementCost = retirementCost;
    }

    public float getImpairReserve() {
        return impairReserve;
    }

    public void setImpairReserve(float impairReserve) {
        this.impairReserve = impairReserve;
    }

    public float getDeprnCost() {
        return deprnCost;
    }

    public void setDeprnCost(float deprnCost) {
        this.deprnCost = deprnCost;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public AssetsTransLineDTO() {
		super();
		this.assignedDate = new SimpleCalendar();
		this.confirmDate = new SimpleCalendar();
		this.receivedDate = new SimpleCalendar();
		this.lineTransDate = new SimpleCalendar();
        setPrimaryKeyName("lineId");
	}

	/**
	 * ���ܣ�����AMS_ASSETS_TRANS_LINE���� ������ID
	 * @param transId String
	 */
	public void setTransId(String transId) {
		this.transId = transId;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_TRANS_LINE���� ���к�
	 * @param lineId String
	 */
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_TRANS_LINE���� ���䵽�ص�
	 * @param assignedToLocation String
	 */
	public void setAssignedToLocation(String assignedToLocation) {
		this.assignedToLocation = assignedToLocation;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public void setHrDeptOption(String hrDeptOption) {
		this.hrDeptOption = hrDeptOption;
	}

	public void setHrDeptId(String hrDeptId) {
		this.hrDeptId = hrDeptId;
	}

	public void setOldLocation(String oldLocation) {
		this.oldLocation = oldLocation;
	}

	public void setOldResponsibilityDept(String oldResponsibilityDept) {
		this.oldResponsibilityDept = oldResponsibilityDept;
	}

	public void setOldResponsibilityUser(String oldResponsibilityUser) {
		this.oldResponsibilityUser = oldResponsibilityUser;
	}

	public void setAssignedToLocationName(String assignedToLocationName) {
		this.assignedToLocationName = assignedToLocationName;
	}

	public void setOldResponsibilityDeptName(String oldResponsibilityDeptName) {
		this.oldResponsibilityDeptName = oldResponsibilityDeptName;
	}

	public void setOldResponsibilityUserName(String oldResponsibilityUserName) {
		this.oldResponsibilityUserName = oldResponsibilityUserName;
	}

	public void setOldLocationName(String oldLocationName) {
		this.oldLocationName = oldLocationName;
	}

	public void setToOrganizationId(int toOrganizationId) {
		this.toOrganizationId = toOrganizationId;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public void setReceivedUser(int receivedUser) {
		this.receivedUser = receivedUser;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public void setLineTransDate(String lineTransDate) throws CalendarException {
		this.lineTransDate.setCalendarValue(lineTransDate);
	}

	public void setOldDepreciationAccount(String oldDepreciationAccount) {
		this.oldDepreciationAccount = oldDepreciationAccount;
	}

	public void setOldFaCategoryCode(String oldFaCategoryCode) {
		this.oldFaCategoryCode = oldFaCategoryCode;
	}

	public void setOldFaCategoryName(String oldFaCategoryName) {
		this.oldFaCategoryName = oldFaCategoryName;
	}

	public void setFaContentCode(String faContentCode) {
		this.faContentCode = faContentCode;
	}

	public void setFaContentName(String faContentName) {
		this.faContentName = faContentName;
	}

	public void setLineReason(String lineReason) {
		this.lineReason = lineReason;
	}

	public void setNetValue(String netValue) {
		this.netValue = netValue;
	}

	public void setPrepareDevalue(float prepareDevalue) {
		this.prepareDevalue = prepareDevalue;
	}

	public void setSoftDevalueVersion(String softDevalueVersion) {
		this.softDevalueVersion = softDevalueVersion;
	}

	public void setSoftInuseVersion(String softInuseVersion) {
		this.softInuseVersion = softInuseVersion;
	}

	public void setSumDepreciation(String sumDepreciation) {
		this.sumDepreciation = sumDepreciation;
	}

	/**
	 * ���ܣ�����AMS_ASSETS_TRANS_LINE���� ��������
	 * @param assignedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setAssignedDate(String assignedDate) throws CalendarException {
		this.assignedDate.setCalendarValue(assignedDate);
	}

	/**
	 * ���ܣ�����AMS_ASSETS_TRANS_LINE���� ��������
	 * @param receivedDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setReceivedDate(String receivedDate) throws CalendarException {
		this.receivedDate.setCalendarValue(receivedDate);
	}

	/**
	 * ���ܣ�����AMS_ASSETS_TRANS_LINE���� ȷ������
	 * @param confirmDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setConfirmDate(String confirmDate) throws CalendarException {
		this.confirmDate.setCalendarValue(confirmDate);
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_TRANS_LINE���� ������ID
	 * @return String
	 */
	public String getTransId() {
		return this.transId;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_TRANS_LINE���� ���к�
	 * @return String
	 */
	public String getLineId() {
		return this.lineId;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_TRANS_LINE���� ���䵽�ص�
	 * @return String
	 */
	public String getAssignedToLocation() {
		return this.assignedToLocation;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_TRANS_LINE���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getAssignedDate() throws CalendarException {
		assignedDate.setCalPattern(getCalPattern());
		return this.assignedDate;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_TRANS_LINE���� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getReceivedDate() throws CalendarException {
		receivedDate.setCalPattern(getCalPattern());
		return this.receivedDate;
	}

	/**
	 * ���ܣ���ȡAMS_ASSETS_TRANS_LINE���� ȷ������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getConfirmDate() throws CalendarException {
		confirmDate.setCalPattern(getCalPattern());
		return this.confirmDate;
	}

	public String getTransType() {
		return transType;
	}

	public String getHrDeptOption() {
		return hrDeptOption;
	}

	public String getHrDeptId() {
		return hrDeptId;
	}

	public String getOldLocation() {
		return oldLocation;
	}

	public String getOldResponsibilityDept() {
		return oldResponsibilityDept;
	}

	public String getOldResponsibilityUser() {
		return oldResponsibilityUser;
	}

	public String getAssignedToLocationName() {
		return assignedToLocationName;
	}

	public String getOldResponsibilityDeptName() {
		return oldResponsibilityDeptName;
	}

	public String getOldResponsibilityUserName() {
		return oldResponsibilityUserName;
	}

	public String getOldLocationName() {
		return oldLocationName;
	}

	public int getToOrganizationId() {
		return toOrganizationId;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public int getReceivedUser() {
		return receivedUser;
	}

	public String getTransNo() {
		return transNo;
	}

	public SimpleCalendar getLineTransDate() throws CalendarException {
		lineTransDate.setCalPattern(getCalPattern());
		return lineTransDate;
	}

	public String getOldDepreciationAccount() {
		return oldDepreciationAccount;
	}

	public String getOldFaCategoryCode() {
		return oldFaCategoryCode;
	}

	public String getOldFaCategoryName() {
		return oldFaCategoryName;
	}

	public String getFaContentCode() {
		return faContentCode;
	}

	public String getFaContentName() {
		return faContentName;
	}

	public String getLineReason() {
		return lineReason;
	}

	public String getNetValue() {
		return netValue;
	}

	public float getPrepareDevalue() {
		return prepareDevalue;
	}

	public String getSoftDevalueVersion() {
		return softDevalueVersion;
	}

	public String getSoftInuseVersion() {
		return softInuseVersion;
	}

	public String getSumDepreciation() {
		return sumDepreciation;
	}

	public String getOldLocationCode() {
		return oldLocationCode;
	}

	public void setOldLocationCode(String oldLocationCode) {
		this.oldLocationCode = oldLocationCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResponsibilityUser() {
		return responsibilityUser;
	}

	public void setResponsibilityUser(String responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getNewBarcode() {
		return newBarcode;
	}

	public String getOldAddressId() {
		return oldAddressId;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setNewBarcode(String newBarcode) {
		this.newBarcode = newBarcode;
	}

	public void setOldAddressId(String oldAddressId) {
		this.oldAddressId = oldAddressId;
	}

	/**
	 * @return the recycleValue
	 */
	public String getRecycleValue() {
		return recycleValue;
	}

	/**
	 * @param recycleValue the recycleValue to set
	 */
	public void setRecycleValue(String recycleValue) {
		this.recycleValue = recycleValue;
	}

	/**
	 * @return the assetsStatusDes
	 */
	public String getAssetsStatusDes() {
		return assetsStatusDes;
	}

	/**
	 * @param assetsStatusDes the assetsStatusDes to set
	 */
	public void setAssetsStatusDes(String assetsStatusDes) {
		this.assetsStatusDes = assetsStatusDes;
	}

	/**
	 * @return the assetsStatus
	 */
	public int getAssetsStatus() {
		return assetsStatus;
	}

	/**
	 * @param assetsStatus the assetsStatus to set
	 */
	public void setAssetsStatus(int assetsStatus) {
		this.assetsStatus = assetsStatus;
	}
}
