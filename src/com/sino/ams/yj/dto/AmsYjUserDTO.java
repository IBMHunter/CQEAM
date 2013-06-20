package com.sino.ams.yj.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: Ӧ��ͨ�ű��϶���� AmsYjUser</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author wzp
* @version 1.0
*/

public class AmsYjUserDTO extends AmsYjTeamDTO{

	private String teamId = null;
    private String userId = "";
	private String userName = "";
	private String post = "";
	private String tel = "";
	private String category = "";
	private String attribute = "";
	private int organizationId = SyBaseSQLUtil.NULL_INT_VALUE;
	private SimpleCalendar creationDate = null;
	private SimpleCalendar lastUpdateDate = null;
	private String createUser = null;
	private String lastUpdateUser = null;
	private String  remark = "";

	public AmsYjUserDTO() {
		super();
		this.creationDate = new SimpleCalendar();
		this.lastUpdateDate = new SimpleCalendar();

	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� ����ID
	 * @param teamId AdvancedNumber
	 */
	public void setTeamId(String teamId){
		this.teamId = teamId;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� ����
	 * @param userName String
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� ְ��
	 * @param post String
	 */
	public void setPost(String post){
		this.post = post;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� �ֻ���
	 * @param tel String
	 */
	public void setTel(String tel){
		this.tel = tel;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� רҵ
	 * @param category String
	 */
	public void setCategory(String category){
		this.category = category;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� ����
	 * @param attribute String
	 */
	public void setAttribute(String attribute){
		this.attribute = attribute;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� ��֯ID
	 * @param organizationId AdvancedNumber
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		this.creationDate.setCalendarValue(creationDate);
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� ������
	 * @param createUser AdvancedNumber
	 */
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		this.lastUpdateDate.setCalendarValue(lastUpdateDate);
	}

	/**
	 * ���ܣ�����Ӧ��ͨ�ű��϶�������� �ϴ��޸���
	 * @param lastUpdateUser AdvancedNumber
	 */
	public void setLastUpdateUser(String lastUpdateUser){
		this.lastUpdateUser = lastUpdateUser;
	}


	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� ����ID
	 * @return AdvancedNumber
	 */
	public String getTeamId() {
		return this.teamId;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� ����
	 * @return String
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� ְ��
	 * @return String
	 */
	public String getPost() {
		return this.post;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� �ֻ���
	 * @return String
	 */
	public String getTel() {
		return this.tel;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� רҵ
	 * @return String
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� ����
	 * @return String
	 */
	public String getAttribute() {
		return this.attribute;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� ��֯ID
	 * @return AdvancedNumber
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� ��������
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getCreationDate() throws CalendarException {
		creationDate.setCalPattern(getCalPattern());
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� ������
	 * @return AdvancedNumber
	 */
	public String getCreateUser() {
		return this.createUser;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� �ϴ��޸�����
	 * @return SimpleCalendar
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getLastUpdateDate() throws CalendarException {
		lastUpdateDate.setCalPattern(getCalPattern());
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡӦ��ͨ�ű��϶�������� �ϴ��޸���
	 * @return AdvancedNumber
	 */
	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

       public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}