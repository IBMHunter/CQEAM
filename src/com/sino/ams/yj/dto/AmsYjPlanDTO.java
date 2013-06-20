package com.sino.ams.yj.dto;

import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.exception.CalendarException;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.EtsFaAssetsDTO;

/**
* <p>Title: Ӧ��Ԥ����ϵ�� AmsYjPlan</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author jialongchuan
* @version 1.0
*/

public class AmsYjPlanDTO extends EtsFaAssetsDTO {

	private String planId = "";
	private String planLevel = "";
	private String proCity = "";
	private String planName = "";
	private String planNo = "";
	private String planType = "";
	private SimpleCalendar printDate = null;    //ӡ��ʱ��
	private String knowPost = "";
	private String leaderPost = "";
	private String isDrill = "";
	private String remark = "";
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar creationDate = null;           
	private String createUser = "";                          
	private SimpleCalendar lastUpdateDate = null;         
	private String lastUpdateUser = null;
	private String quantity = "";
    private String organizationOption = "";

    public AmsYjPlanDTO() {
		super();
		this.printDate = new SimpleCalendar();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();

	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ ���
	 * @param planId AdvancedNumber
	 */
	public void setPlanId(String planId){
		this.planId = planId;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ Ԥ������
	 * @param planLevel String
	 */
	public void setPlanLevel(String planLevel){
		this.planLevel = planLevel;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ ʡ�����
	 * @param proCity String
	 */
	public void setProCity(String proCity){
		this.proCity = proCity;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ Ԥ������
	 * @param planName String
	 */
	public void setPlanName(String planName){
		this.planName = planName;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ Ԥ�����
	 * @param planNo String
	 */
	public void setPlanNo(String planNo){
		this.planNo = planNo;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ Ԥ�����
	 * @param planType String
	 */
	public void setPlanType(String planType){
		this.planType = planType;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ ӡ��ʱ��
	 * @param printDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setPrintDate(String printDate) throws CalendarException{
		this.printDate.setCalendarValue(printDate);
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ ֪����Χ(ְλ/��λ)
	 * @param knowPost String
	 */
	public void setKnowPost(String knowPost){
		this.knowPost = knowPost;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ Ԥ�����������˵ĸ�λ/ְλ
	 * @param leaderPost String
	 */
	public void setLeaderPost(String leaderPost){
		this.leaderPost = leaderPost;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ ��Ԥ���Ƿ�������
	 * @param isDrill String
	 */
	public void setIsDrill(String isDrill){
		this.isDrill = isDrill;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ ORGANIZATION_ID
	 * @param organizationId AdvancedNumber
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ CREATION_DATE
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ CREATE_USER
	 * @param createUser AdvancedNumber
	 */
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ LAST_UPDATE_DATE
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ LAST_UPDATE_USER
	 * @param lastUpdateUser AdvancedNumber
	 */
	public void setLastUpdateUser(String lastUpdateUser){
		this.lastUpdateUser = lastUpdateUser;
	}

	/**
	 * ���ܣ�����Ӧ��Ԥ����ϵ������ ֪���˵�����
	 * @param quantity String
	 */
	public void setQuantity(String quantity){
		this.quantity = quantity;
	}


	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ ���
	 * @return AdvancedNumber
	 */
	public String getPlanId() {
		return this.planId;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ Ԥ������
	 * @return String
	 */
	public String getPlanLevel() {
		return this.planLevel;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ ʡ�����
	 * @return String
	 */
	public String getProCity() {
		return this.proCity;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ Ԥ������
	 * @return String
	 */
	public String getPlanName() {
		return this.planName;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ Ԥ�����
	 * @return String
	 */
	public String getPlanNo() {
		return this.planNo;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ Ԥ�����
	 * @return String
	 */
	public String getPlanType() {
		return this.planType;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ ӡ��ʱ��
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getPrintDate() throws CalendarException {
		printDate.setCalPattern(getCalPattern());
		return this.printDate;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ ֪����Χ(ְλ/��λ)
	 * @return String
	 */
	public String getKnowPost() {
		return this.knowPost;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ Ԥ�����������˵ĸ�λ/ְλ
	 * @return String
	 */
	public String getLeaderPost() {
		return this.leaderPost;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ ��Ԥ���Ƿ�������
	 * @return String
	 */
	public String getIsDrill() {
		return this.isDrill;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ ORGANIZATION_ID
	 * @return AdvancedNumber
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ CREATION_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ CREATE_USER
	 * @return AdvancedNumber
	 */
	public String getCreateUser() {
		return this.createUser;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ LAST_UPDATE_DATE
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ LAST_UPDATE_USER
	 * @return AdvancedNumber
	 */
	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	/**
	 * ���ܣ���ȡӦ��Ԥ����ϵ������ ֪���˵�����
	 * @return String
	 */
	public String getQuantity() {
		return this.quantity;
	}
    public String getOrganizationOption() {
           return organizationOption;
       }

       public void setOrganizationOption(String organizationOption) {
           this.organizationOption = organizationOption;
       }

}