package com.sino.ams.yj.comvan.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;

/**
* <p>Title: Ӧ��ͨ�ų���Ϣ�� AmsYjComvan</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ��ͨ�ų�
*/

public class AmsYjComvanDTO extends CheckBoxDTO{

	private String comvanId = "";
	private String deptName = "";
	private String manufacturer = "";
	private String model = "";
	private String refitFirm = "";
	private String length = "";                //int
	private String quality = "";              //int
	private String antennaMastForm = "";
	private String hasOilengine = "";
	private String licensePlate = "";
	private String frameNumber = "";
	private String lWH = "";
	private String originalCost = "";          //int
	private String btsManufacturer = "";
	private String btsModel = "";
	private String carrierFrequencyvAllocate = "";
	private String carrierFrequencyvQty = "";  //int
	private String installedBsc = "";
	private String otherGsmUnit = "";
	private String transForm = "";
	private String transItemModel = "";
	private String bandwidth = "";
	private String hasSatelliteTransmissions = "";
	private String typeOfTraffic = "";
	private String useTimes = "";              //int
	private String usedTraffic = "";
	private String useScene = "";
	private String remark = "";
    private int organizationId = -1;
    private SimpleCalendar creationDate = null;
    private String createUser = "";
    private SimpleCalendar lastUpdateDate = null;
    private String lastUpdateUser = "";
    private String resourceId= "";          //int
    private String equipmentName ="";
    private String orgOpt="";


    public AmsYjComvanDTO() {
		super();

        creationDate=new SimpleCalendar();
        lastUpdateDate=new SimpleCalendar();

	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ������λ
	 * @param deptName String
	 */
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ������
	 * @param manufacturer String
	 */
	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ �ͺ�
	 * @param model String
	 */
	public void setModel(String model){
		this.model = model;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ������װ��
	 * @param refitFirm String
	 */
	public void setRefitFirm(String refitFirm){
		this.refitFirm = refitFirm;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ��������(mm)
	 * @param length String
	 */
	public void setLength(String length){
		this.length = length;
	}



	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ����Φ����ʽ��Һѹ/��ѹ/�綯/�ֶ���
	 * @param antennaMastForm String
	 */
	public void setAntennaMastForm(String antennaMastForm){
		this.antennaMastForm = antennaMastForm;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ �Ƿ����ͻ�
	 * @param hasOilengine String
	 */
	public void setHasOilengine(String hasOilengine){
		this.hasOilengine = hasOilengine;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ���г�����
	 * @param licensePlate String
	 */
	public void setLicensePlate(String licensePlate){
		this.licensePlate = licensePlate;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ���ܺ�
	 * @param frameNumber String
	 */
	public void setFrameNumber(String frameNumber){
		this.frameNumber = frameNumber;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ���������(mm)
	 * @param lWH String
	 */
	public void setLWH(String lWH){
		this.lWH = lWH;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ �ʲ�ԭֵ(��Ԫ)
	 * @param originalCost String
	 */
	public void setOriginalCost(String originalCost){
		this.originalCost = originalCost;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ����
	 * @param btsManufacturer String
	 */
	public void setBtsManufacturer(String btsManufacturer){
		this.btsManufacturer = btsManufacturer;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������  �ͺ�
	 * @param btsModel String
	 */
	public void setBtsModel(String btsModel){
		this.btsModel = btsModel;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ��Ƶ����
	 * @param carrierFrequencyvAllocate String
	 */
	public void setCarrierFrequencyvAllocate(String carrierFrequencyvAllocate){
		this.carrierFrequencyvAllocate = carrierFrequencyvAllocate;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ����Ƶ��
	 * @param carrierFrequencyvQty String
	 */
	public void setCarrierFrequencyvQty(String carrierFrequencyvQty){
		this.carrierFrequencyvQty = carrierFrequencyvQty;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ �Ƿ�װBSC
	 * @param installedBsc String
	 */
	public void setInstalledBsc(String installedBsc){
		this.installedBsc = installedBsc;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ��װ������GSMϵͳ��Ԫ
	 * @param otherGsmUnit String
	 */
	public void setOtherGsmUnit(String otherGsmUnit){
		this.otherGsmUnit = otherGsmUnit;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ���䷽ʽ
	 * @param transForm String
	 */
	public void setTransForm(String transForm){
		this.transForm = transForm;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ �豸�ͺ�
	 * @param transItemModel String
	 */
	public void setTransItemModel(String transItemModel){
		this.transItemModel = transItemModel;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ �Ƿ������Ǵ���
	 * @param hasSatelliteTransmissions String
	 */
	public void setHasSatelliteTransmissions(String hasSatelliteTransmissions){
		this.hasSatelliteTransmissions = hasSatelliteTransmissions;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ���ṩҵ�����ࣨGSM/TD-SCDMA/WLAN/������ӵȣ�
	 * @param typeOfTraffic String
	 */
	public void setTypeOfTraffic(String typeOfTraffic){
		this.typeOfTraffic = typeOfTraffic;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ʹ�ô���
	 * @param useTimes String
	 */
	public void setUseTimes(String useTimes){
		this.useTimes = useTimes;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ʹ��ʱ�ṩ��ҵ��GSM/TD-SCDMA/WLAN/������ӵȣ�
	 * @param usedTraffic String
	 */
	public void setUsedTraffic(String usedTraffic){
		this.usedTraffic = usedTraffic;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ��Ҫʹ�ó������ص�
	 * @param useScene String
	 */
	public void setUseScene(String useScene){
		this.useScene = useScene;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ų���Ϣ������ ����˵��
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ������λ
	 * @return String
	 */
	public String getDeptName() {
		return this.deptName;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ������
	 * @return String
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ �ͺ�
	 * @return String
	 */
	public String getModel() {
		return this.model;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ������װ��
	 * @return String
	 */
	public String getRefitFirm() {
		return this.refitFirm;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ��������(mm)
	 * @return String
	 */
	public String getLength() {
		return this.length;
	}

	
	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ����Φ����ʽ��Һѹ/��ѹ/�綯/�ֶ���
	 * @return String
	 */
	public String getAntennaMastForm() {
		return this.antennaMastForm;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ �Ƿ����ͻ�
	 * @return String
	 */
	public String getHasOilengine() {
		return this.hasOilengine;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ���г�����
	 * @return String
	 */
	public String getLicensePlate() {
		return this.licensePlate;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ���ܺ�
	 * @return String
	 */
	public String getFrameNumber() {
		return this.frameNumber;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ���������(mm)
	 * @return String
	 */
	public String getLWH() {
		return this.lWH;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ �ʲ�ԭֵ(��Ԫ)
	 * @return String
	 */
	public String getOriginalCost() {
		return this.originalCost;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ����
	 * @return String
	 */
	public String getBtsManufacturer() {
		return this.btsManufacturer;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������  �ͺ�
	 * @return String
	 */
	public String getBtsModel() {
		return this.btsModel;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ��Ƶ����
	 * @return String
	 */
	public String getCarrierFrequencyvAllocate() {
		return this.carrierFrequencyvAllocate;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ����Ƶ��
	 * @return String
	 */
	public String getCarrierFrequencyvQty() {
		return this.carrierFrequencyvQty;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ �Ƿ�װBSC
	 * @return String
	 */
	public String getInstalledBsc() {
		return this.installedBsc;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ��װ������GSMϵͳ��Ԫ
	 * @return String
	 */
	public String getOtherGsmUnit() {
		return this.otherGsmUnit;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ���䷽ʽ
	 * @return String
	 */
	public String getTransForm() {
		return this.transForm;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ �豸�ͺ�
	 * @return String
	 */
	public String getTransItemModel() {
		return this.transItemModel;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ �Ƿ������Ǵ���
	 * @return String
	 */
	public String getHasSatelliteTransmissions() {
		return this.hasSatelliteTransmissions;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ���ṩҵ�����ࣨGSM/TD-SCDMA/WLAN/������ӵȣ�
	 * @return String
	 */
	public String getTypeOfTraffic() {
		return this.typeOfTraffic;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ʹ�ô���
	 * @return String
	 */
	public String getUseTimes() {
		return this.useTimes;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ʹ��ʱ�ṩ��ҵ��GSM/TD-SCDMA/WLAN/������ӵȣ�
	 * @return String
	 */
	public String getUsedTraffic() {
		return this.usedTraffic;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ��Ҫʹ�ó������ص�
	 * @return String
	 */
	public String getUseScene() {
		return this.useScene;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ų���Ϣ������ ����˵��
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrgOpt() {
        return orgOpt;
    }

    public void setOrgOpt(String orgOpt) {
        this.orgOpt = orgOpt;
    }

    public SimpleCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String  creationDate) {
        this.creationDate = new SimpleCalendar(creationDate);
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public SimpleCalendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String  lastUpdateDate) {
        this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getComvanId() {
		return comvanId;
	}

	public void setComvanId(String comvanId) {
		this.comvanId = comvanId;
	}
}