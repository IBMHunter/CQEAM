package com.sino.ams.log.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: �û������ղؼ�(EAM) EtsFavorites</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsFavoritesDTO extends CheckBoxDTO{

	private String systemid = "";
	private int sortNo;
	private int userId;
	private int sfResId;
	private SimpleCalendar creationDate = null;
	private int createdBy;
	private SimpleCalendar lastUpdateDate = null;
	private int lastUpdateBy;
    private String menu="";
    private String smallMenu="";


    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getSmallMenu() {
        return smallMenu;
    }

    public void setSmallMenu(String smallMenu) {
        this.smallMenu = smallMenu;
    }

    /**
	 * ���ܣ������û������ղؼ�(EAM)���� ��ˮ��
	 * @param systemid String
	 */
	public void setSystemid(String systemid){
		this.systemid = systemid;
	}

	/**
	 * ���ܣ������û������ղؼ�(EAM)���� �����
	 * @param sortNo String
	 */
	public void setSortNo(int sortNo){
		this.sortNo = sortNo;
	}

	/**
	 * ���ܣ������û������ղؼ�(EAM)���� �û�ID
	 * @param userId String
	 */
	public void setUserId(int userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ������û������ղؼ�(EAM)���� ��ĿID
	 * @param sfResId String
	 */
	public void setSfResId(int sfResId){
		this.sfResId = sfResId;
	}

	/**
	 * ���ܣ������û������ղؼ�(EAM)���� ��������
	 * @param creationDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setCreationDate(String creationDate) throws CalendarException{
		if(!StrUtil.isEmpty(creationDate)){
			this.creationDate = new SimpleCalendar(creationDate);
		}
	}

	/**
	 * ���ܣ������û������ղؼ�(EAM)���� ������
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ������û������ղؼ�(EAM)���� �ϴ��޸�����
	 * @param lastUpdateDate SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
		if(!StrUtil.isEmpty(lastUpdateDate)){
			this.lastUpdateDate = new SimpleCalendar(lastUpdateDate);
		}
	}

	/**
	 * ���ܣ������û������ղؼ�(EAM)���� �ϴ��޸���
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡ�û������ղؼ�(EAM)���� ��ˮ��
	 * @return String
	 */
	public String getSystemid(){
		return this.systemid;
	}

	/**
	 * ���ܣ���ȡ�û������ղؼ�(EAM)���� �����
	 * @return String
	 */
	public int getSortNo(){
		return this.sortNo;
	}

	/**
	 * ���ܣ���ȡ�û������ղؼ�(EAM)���� �û�ID
	 * @return String
	 */
	public int getUserId(){
		return this.userId;
	}

	/**
	 * ���ܣ���ȡ�û������ղؼ�(EAM)���� ��ĿID
	 * @return String
	 */
	public int getSfResId(){
		return this.sfResId;
	}

	/**
	 * ���ܣ���ȡ�û������ղؼ�(EAM)���� ��������
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡ�û������ղؼ�(EAM)���� ������
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡ�û������ղؼ�(EAM)���� �ϴ��޸�����
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡ�û������ղؼ�(EAM)���� �ϴ��޸���
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

}