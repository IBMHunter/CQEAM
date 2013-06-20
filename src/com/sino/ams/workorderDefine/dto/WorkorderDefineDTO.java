package com.sino.ams.workorderDefine.dto;

import com.sino.ams.appbase.dto.AMSBaseDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.util.StrUtil;

/**
 * Ѳ���Զ��崫�����
 * ��Ӧ��: ETS_WORKORDER_DEFINE
 * @author ����
 *  
 */
public class WorkorderDefineDTO extends AMSBaseDTO{
	private String workorderDefineId = "";  //Ѳ���Զ����ʶ
	private int organizationId ;  //��˾OU
	private String objectCategory = "";  // �ص�רҵ
	private String city = ""; //��
	private String county = "";  //��������
	private String costCenterCode = "";//�ɱ����Ĵ���	
    private int implementBy; //ִ����
    private int checkoverBy;    //�鵵��
    private String implementByName = ""; //ִ����
    private String checkoverByName = ""; //�鵵��
    private int workorderCycle = -1; //Ѳ������
    private String enabled = ""; //�Ƿ���Ч
    private SimpleCalendar workorderExecDate = null; //�ϴ�ִ������
    private SimpleCalendar creationDate = null; //��������
    private SimpleCalendar lastUpdateDate = null; //�޸�����
    private int groupId = -1;//���
    private int workorderTiggerTime ;  //Ѳ���Զ���������ʱ��
    
    private String organizationName = ""; //��˾����
    private String objectCategoryOption = "";
    private String cityOption = "";
    private String countyOption = "";
    private String costCenterName = "";//�ɱ���������
    private String workorderCycleOption = ""; //Ѳ����������ѡ���
    private String implementByOption = ""; //ִ����ѡ������ѡ���
    private String checkoverByOption = ""; //�鵵��ѡ������ѡ���
    
    private String auxiliaryInfo = ""; //������Ϣ
    
    public String getAuxiliaryInfo() {
		return auxiliaryInfo;
	}

	public void setAuxiliaryInfo(String auxiliaryInfo) {
		this.auxiliaryInfo = auxiliaryInfo;
	} 
    
    public String getImplementByOption() {
		return implementByOption;
	}

	public void setImplementByOption(String implementByOption) {
		this.implementByOption = implementByOption;
	}

	public String getCheckoverByOption() {
		return checkoverByOption;
	}

	public void setCheckoverByOption(String checkoverByOption) {
		this.checkoverByOption = checkoverByOption;
	}

	public WorkorderDefineDTO() {
        this.workorderExecDate = new SimpleCalendar();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }

	public String getWorkorderDefineId() {
		return workorderDefineId;
	}

	public void setWorkorderDefineId(String workorderDefineId) {
		this.workorderDefineId = workorderDefineId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getObjectCategory() {
		return objectCategory;
	}

	public void setObjectCategory(String objectCategory) {
		this.objectCategory = objectCategory;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public int getImplementBy() {
		return implementBy;
	}

	public void setImplementBy(int implementBy) {
		this.implementBy = implementBy;
	}

	public int getCheckoverBy() {
		return checkoverBy;
	}

	public void setCheckoverBy(int checkoverBy) {
		this.checkoverBy = checkoverBy;
	}

	public int getWorkorderCycle() {
		return workorderCycle;
	}

	public void setWorkorderCycle(int workorderCycle) {
		this.workorderCycle = workorderCycle;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getObjectCategoryOption() {
		return objectCategoryOption;
	}

	public void setObjectCategoryOption(String objectCategoryOption) {
		this.objectCategoryOption = objectCategoryOption;
	}

	public String getCountyOption() {
		return countyOption;
	}

	public void setCountyOption(String countyOption) {
		this.countyOption = countyOption;
	}

	public String getWorkorderCycleOption() {
		return workorderCycleOption;
	}

	public void setWorkorderCycleOption(String workorderCycleOption) {
		this.workorderCycleOption = workorderCycleOption;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityOption() {
		return cityOption;
	}

	public void setCityOption(String cityOption) {
		this.cityOption = cityOption;
	}

	public SimpleCalendar getWorkorderExecDate() {
		return workorderExecDate;
	}

	public void setWorkorderExecDate(String workorderExecDate) {
		if (StrUtil.isNotEmpty(workorderExecDate)) {
			this.workorderExecDate = new SimpleCalendar(workorderExecDate);
		}
	}

	public SimpleCalendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		if (StrUtil.isNotEmpty(creationDate)) {
			this.creationDate = new SimpleCalendar(creationDate);
		}
	}

	public SimpleCalendar getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		if (StrUtil.isNotEmpty(lastUpdateDate)) {
			this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
		}
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getWorkorderTiggerTime() {
		return workorderTiggerTime;
	}

	public void setWorkorderTiggerTime(int workorderTiggerTime) {
		this.workorderTiggerTime = workorderTiggerTime;
	}

	public String getImplementByName() {
		return implementByName;
	}

	public void setImplementByName(String implementByName) {
		this.implementByName = implementByName;
	}

	public String getCheckoverByName() {
		return checkoverByName;
	}

	public void setCheckoverByName(String checkoverByName) {
		this.checkoverByName = checkoverByName;
	}
    
}
