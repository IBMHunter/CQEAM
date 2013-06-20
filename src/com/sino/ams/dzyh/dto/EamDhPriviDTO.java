package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.ams.bean.SyBaseSQLUtil;

/**
* <p>Title: Ȩ�޶����(EAM) EamDhPrivi</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class EamDhPriviDTO extends CommonRecordDTO{

	private String priviId = "";
	private int userId = SyBaseSQLUtil.NULL_INT_VALUE;
	private String deptCode = "";
	private int orgId = SyBaseSQLUtil.NULL_INT_VALUE ;
	private int enabled = 1;
	private String defaultflag = "N";

    private String srcPage = "";
    private String companyName = "";
    private String deptName = "";
    private String userName = "";
    private String loginName = "";
    private String employeeNumber = "";
    private String groupId = "";

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSrcPage() {
		return srcPage;
	}

	public void setSrcPage(String srcPage) {
		this.srcPage = srcPage;
	}

	/**
	 * ���ܣ�����Ȩ�޶����(EAM)���� EAM_DH_PRIVI_S.NEXTVAL
	 * @param priviId String
	 */
	public void setPriviId(String priviId){
		this.priviId = priviId;
	}

	/**
	 * ���ܣ�����Ȩ�޶����(EAM)���� �û�ID
	 * @param userId String
	 */
	public void setUserId(int userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ�����Ȩ�޶����(EAM)���� ���Ŵ���AMS_MIS_DEPT
	 * @param deptCode String
	 */
	public void setDeptCode(String deptCode){
		this.deptCode = deptCode;
	}

	/**
	 * ���ܣ�����Ȩ�޶����(EAM)���� ETS_ORGANIZATION_DEF. ORG_ID
	 * @param orgId String
	 */
	public void setOrgId(int orgId){
		this.orgId = orgId;
	}

	/**
	 * ���ܣ�����Ȩ�޶����(EAM)���� �Ƿ���Ч
	 * @param enabled String
	 */
	public void setEnabled(int enabled){
		this.enabled = enabled;
	}

	/**
	 * ���ܣ�����Ȩ�޶����(EAM)���� �Ƿ�Ĭ�ϵ�ֵ�׺Ĺ���Ա
	 * @param defaultflag String
	 */
	public void setDefaultflag(String defaultflag){
		this.defaultflag = defaultflag;
	}


	/**
	 * ���ܣ���ȡȨ�޶����(EAM)���� EAM_DH_PRIVI_S.NEXTVAL
	 * @return String
	 */
	public String getPriviId() {
		return this.priviId;
	}

	/**
	 * ���ܣ���ȡȨ�޶����(EAM)���� �û�ID
	 * @return String
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * ���ܣ���ȡȨ�޶����(EAM)���� ���Ŵ���AMS_MIS_DEPT
	 * @return String
	 */
	public String getDeptCode() {
		return this.deptCode;
	}

	/**
	 * ���ܣ���ȡȨ�޶����(EAM)���� ETS_ORGANIZATION_DEF. ORG_ID
	 * @return String
	 */
	public int getOrgId() {
		return this.orgId;
	}

	/**
	 * ���ܣ���ȡȨ�޶����(EAM)���� �Ƿ���Ч
	 * @return String
	 */
	public int getEnabled() {
		return this.enabled;
	}

	/**
	 * ���ܣ���ȡȨ�޶����(EAM)���� �Ƿ�Ĭ�ϵ�ֵ�׺Ĺ���Ա
	 * @return String
	 */
	public String getDefaultflag() {
		return this.defaultflag;
	}

}