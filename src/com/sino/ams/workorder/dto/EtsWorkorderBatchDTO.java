package com.sino.ams.workorder.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: ��������(EAM) EtsWorkorderBatch</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsWorkorderBatchDTO extends CheckBoxDTO{

	private String systemid = "";
	private String workorderBatch = "";
	private String workorderBatchName = "";
	private String remark = "";
	private String prjId;
	private String workorderType = "";
	private int status;
	private int archflag;
	private String actid = "";
	private String caseid = "";
	private int distributeGroupId ;
	private Timestamp creationDate = null;
	private int createdBy;
    private int lastUpdateBy;
    private Timestamp lastUpdateDate = null;

    private String prjName ="";
    private String workorderTypeDesc="";
    private String distributeGroupName="";
    private String createUser="";


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getWorkorderTypeDesc() {
        return workorderTypeDesc;
    }

    public void setWorkorderTypeDesc(String workorderTypeDesc) {
        this.workorderTypeDesc = workorderTypeDesc;
    }

    public String getDistributeGroupName() {
        return distributeGroupName;
    }

    public void setDistributeGroupName(String distributeGroupName) {
        this.distributeGroupName = distributeGroupName;
    }

    /**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param systemid String
	 */
	public void setSystemid(String systemid){
		this.systemid = systemid;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param workorderBatch String
	 */
	public void setWorkorderBatch(String workorderBatch){
		this.workorderBatch = workorderBatch;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param workorderBatchName String
	 */
	public void setWorkorderBatchName(String workorderBatchName){
		this.workorderBatchName = workorderBatchName;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param prjId String
	 */
	public void setPrjId(String prjId){
		this.prjId = prjId;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param workorderType String
	 */
	public void setWorkorderType(String workorderType){
		this.workorderType = workorderType;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param status String
	 */
	public void setStatus(int status){
		this.status = status;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param archflag String
	 */
	public void setArchflag(int archflag){
		this.archflag = archflag;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param actid String
	 */
	public void setActid(String actid){
		this.actid = actid;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param caseid String
	 */
	public void setCaseid(String caseid){
		this.caseid = caseid;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param distributeGroupId String
	 */
	public void setDistributeGroupId(int distributeGroupId){
		this.distributeGroupId = distributeGroupId;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param creationDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		if(!StrUtil.isEmpty(creationDate)){
			SimpleCalendar cal = new SimpleCalendar(creationDate);
			this.creationDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param lastUpdateDate Timestamp
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		if(!StrUtil.isEmpty(lastUpdateDate)){
			SimpleCalendar cal = new SimpleCalendar(lastUpdateDate);
			this.lastUpdateDate = cal.getSQLTimestamp();
		}
	}

	/**
	 * ���ܣ����ù�������(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getSystemid(){
		return this.systemid;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getWorkorderBatch(){
		return this.workorderBatch;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getWorkorderBatchName(){
		return this.workorderBatchName;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getRemark(){
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getPrjId(){
		return this.prjId;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getWorkorderType(){
		return this.workorderType;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getStatus(){
		return this.status;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getArchflag(){
		return this.archflag;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getActid(){
		return this.actid;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public String getCaseid(){
		return this.caseid;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getDistributeGroupId(){
		return this.distributeGroupId;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ��������(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

}