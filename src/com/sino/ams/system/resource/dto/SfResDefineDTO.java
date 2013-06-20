package com.sino.ams.system.resource.dto;

import java.sql.Timestamp;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: SfResDefine</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfResDefineDTO extends CheckBoxDTO{
	private int systemId = 0;
	private String resId = "";
	private String resParId = "";
	private String resName = "";
	private String resUrl = "";
	private int sortNo;
	private String isPopup = "";
	private String principal = "";
	private String enabled = "";
	private String isInner = "";
	private String visible = "";
	private String popscript = "";
	private int levelNum ;
	private Timestamp creationDate = null;
	private int createdBy = -1;
	private Timestamp lastUpdateDate = null;
	private int lastUpdateBy = -1;
	private String firstChildId = "";
	private String hasChild = "0";
	private String businessDesc = "";
	private String isFinished = "";

	private String newResId = "";//��Դ�ṹ���������У����ɵ���ID
	private String newResParId = "";//��Դ�ṹ���������У����ɵ����ϼ���ĿID
	/**
	 * ���ܣ�����DTO���� systemId
	 * @param systemId String
	 */
	public void setSystemId(int systemId){
		this.systemId = systemId;
	}

	/**
	 * ���ܣ�����DTO���� resId
	 * @param resId String
	 */
	public void setResId(String resId){
		this.resId = resId;
	}

	/**
	 * ���ܣ�����DTO���� resParId
	 * @param resParId String
	 */
	public void setResParId(String resParId){
		this.resParId = resParId;
	}

	/**
	 * ���ܣ�����DTO���� resName
	 * @param resName String
	 */
	public void setResName(String resName){
		this.resName = resName;
	}

	/**
	 * ���ܣ�����DTO���� resUrl
	 * @param resUrl String
	 */
	public void setResUrl(String resUrl){
		this.resUrl = resUrl;
	}

	/**
	 * ���ܣ�����DTO���� sortNo
	 * @param sortNo String
	 */
	public void setSortNo(int sortNo){
		this.sortNo = sortNo;
	}

	/**
	 * ���ܣ�����DTO���� isPopup
	 * @param isPopup String
	 */
	public void setIsPopup(String isPopup){
		this.isPopup = isPopup;
	}

	/**
	 * ���ܣ�����DTO���� principal
	 * @param principal String
	 */
	public void setPrincipal(String principal){
		this.principal = principal;
	}

	/**
	 * ���ܣ�����DTO���� enabled
	 * @param enabled String
	 */
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}

	/**
	 * ���ܣ�����DTO���� isInner
	 * @param isInner String
	 */
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	/**
	 * ���ܣ�����DTO���� visible
	 * @param visible String
	 */
	public void setVisible(String visible){
		this.visible = visible;
	}

	/**
	 * ���ܣ�����DTO���� popscript
	 * @param popscript String
	 */
	public void setPopscript(String popscript){
		this.popscript = popscript;
	}

	/**
	 * ���ܣ�����DTO���� levelNum
	 * @param levelNum String
	 */
	public void setLevelNum(int levelNum){
		this.levelNum = levelNum;
	}

	/**
	 * ���ܣ�����DTO���� creationDate
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
	 * ���ܣ�����DTO���� createdBy
	 * @param createdBy String
	 */
	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	/**
	 * ���ܣ�����DTO���� lastUpdateDate
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
	 * ���ܣ�����DTO���� lastUpdateBy
	 * @param lastUpdateBy String
	 */
	public void setLastUpdateBy(int lastUpdateBy){
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setFirstChildId(String firstChildId) {
		this.firstChildId = firstChildId;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public void setNewResId(String newResId) {
		this.newResId = newResId;
	}

	public void setNewResParId(String newResParId) {
		this.newResParId = newResParId;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}

	/**
	 * ���ܣ���ȡDTO���� systemId
	 * @return String
	 */
	public int getSystemId(){
		return this.systemId;
	}

	/**
	 * ���ܣ���ȡDTO���� resId
	 * @return String
	 */
	public String getResId(){
		return this.resId;
	}

	/**
	 * ���ܣ���ȡDTO���� resParId
	 * @return String
	 */
	public String getResParId(){
		return this.resParId;
	}

	/**
	 * ���ܣ���ȡDTO���� resName
	 * @return String
	 */
	public String getResName(){
		return this.resName;
	}

	/**
	 * ���ܣ���ȡDTO���� resUrl
	 * @return String
	 */
	public String getResUrl(){
		return this.resUrl;
	}

	/**
	 * ���ܣ���ȡDTO���� sortNo
	 * @return String
	 */
	public int getSortNo(){
		return this.sortNo;
	}

	/**
	 * ���ܣ���ȡDTO���� isPopup
	 * @return String
	 */
	public String getIsPopup(){
		return this.isPopup;
	}

	/**
	 * ���ܣ���ȡDTO���� principal
	 * @return String
	 */
	public String getPrincipal(){
		return this.principal;
	}

	/**
	 * ���ܣ���ȡDTO���� enabled
	 * @return String
	 */
	public String getEnabled(){
		return this.enabled;
	}

	/**
	 * ���ܣ���ȡDTO���� isInner
	 * @return String
	 */
	public String getIsInner(){
		return this.isInner;
	}

	/**
	 * ���ܣ���ȡDTO���� visible
	 * @return String
	 */
	public String getVisible(){
		return this.visible;
	}

	/**
	 * ���ܣ���ȡDTO���� popscript
	 * @return String
	 */
	public String getPopscript(){
		return this.popscript;
	}

	/**
	 * ���ܣ���ȡDTO���� levelNum
	 * @return String
	 */
	public int getLevelNum(){
		return this.levelNum;
	}

	/**
	 * ���ܣ���ȡDTO���� creationDate
	 * @return Timestamp
	 */
	public Timestamp getCreationDate(){
		return this.creationDate;
	}

	/**
	 * ���ܣ���ȡDTO���� createdBy
	 * @return String
	 */
	public int getCreatedBy(){
		return this.createdBy;
	}

	/**
	 * ���ܣ���ȡDTO���� lastUpdateDate
	 * @return Timestamp
	 */
	public Timestamp getLastUpdateDate(){
		return this.lastUpdateDate;
	}

	/**
	 * ���ܣ���ȡDTO���� lastUpdateBy
	 * @return String
	 */
	public int getLastUpdateBy(){
		return this.lastUpdateBy;
	}

	public String getFirstChildId() {
		return firstChildId;
	}

	public String getHasChild() {
		return hasChild;
	}

	public String getNewResId() {
		return newResId;
	}

	public String getNewResParId() {
		return newResParId;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public String getIsFinished() {
		return isFinished;
	}

	/**
	 * ���ܣ���ȡָ��URL������Դ�ṹ�б�URL����ĸ��ס�
	 * ���ڸ���URL��Դʱ�ĵݹ���¡�
	 * @param resources DTOSet
	 * @return SfResDefineDTO
	 */
	public SfResDefineDTO getParent(DTOSet resources) {
		SfResDefineDTO parent = null;
		for(int i = 0; i < resources.getSize(); i++){
			parent = (SfResDefineDTO)resources.getDTO(i);
			if(parent.getResId().equals(getResParId())){
				break;
			}
		}
		return parent;
	}
}
