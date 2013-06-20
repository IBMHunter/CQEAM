package com.sino.ams.expand.dto;

import com.sino.ams.bean.CommonRecordDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.exception.CalendarException;

/**
* <p>Title: �ʲ���չ��Ϣ��ETS_ITEM_INFO_EX EtsItemInfoEx</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsItemInfoExDTO extends CommonRecordDTO{

	private String itemInfoExId = "";	
	private String systemId = "";	
	private String barcode = "";	//����
	private String itemType = "";	//�豸���ͣ���CAR������IT����
	private String attribute1 = "";	//���ƺ�			��CPUƵ�ʣ�
	private String attribute2 = "";	//���֤				���ڴ�������
	private String attribute3 = "";	//��;				��Ӳ��������
	private String attribute4 = "";	//Ĭ�ϼ�ʻԱ				����ʾ�����ͣ�
	private String attribute5 = "";	//						����ʾ���ߴ磩					
	private String attribute6 = "";
	private String attribute7 = "";
	private String attribute8 = "";
	private String attribute9 = "";
	private String attribute10 = "";
	private String attribute11 = "";
	private String attribute12 = "";
	private String attribute13 = "";
	private String attribute14 = "";
	private String attribute15 = "";
	private String attribute16 = "";
	private String attribute17 = "";
	private String attribute18 = "";
	private String attribute19 = "";
	private String attribute20 = "";
	private String nAttribute1 = "";
	private String nAttribute2 = "";
	private String nAttribute3 = "";
	private String nAttribute4 = "";
	private String nAttribute5 = "";
	private SimpleCalendar dAttribute1 = null;
	private SimpleCalendar dAttribute2 = null;
	private SimpleCalendar dAttribute3 = null;
	private SimpleCalendar dAttribute4 = null;
	private SimpleCalendar dAttribute5 = null;

	//------------------------------------------ETS_SYSTEM_ITEM-------------------------------------------------------

	private String itemName = "";	//����
	private String itemSpec = "";	//�ͺ�
	
	//------------------------------------------ETS_FA_ASSETS-------------------------------------------------------
	
	private SimpleCalendar datePlacedInService=null;	//��������
	private String lifeInYears="";	//�۾�����
	
	//------------------------------------------ETS_ITEM_INFO-------------------------------------------------------
	private String responsibilityUser="";	//������
	private String employeeName="";	//������
	
	public EtsItemInfoExDTO(){
		super();
		this.dAttribute1=new SimpleCalendar();
		this.dAttribute2=new SimpleCalendar();
		this.dAttribute3=new SimpleCalendar();
		this.dAttribute4=new SimpleCalendar();
		this.dAttribute5=new SimpleCalendar();
		this.datePlacedInService=new SimpleCalendar();
	}
	
	public SimpleCalendar getDatePlacedInService() throws CalendarException {
		datePlacedInService.setCalPattern(getCalPattern());
		return datePlacedInService;
	}

	public void setDatePlacedInService(String datePlacedInService) throws CalendarException {
		this.datePlacedInService.setCalendarValue(datePlacedInService);
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ETS_ITEM_INFO_EX_S.NEXTVAL
	 * @param itemInfoExId String
	 */
	public void setItemInfoExId(String itemInfoExId){
		this.itemInfoExId = itemInfoExId;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ETS_ITEM_INFO.barcode
	 * @param barcode String
	 */
	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� �豸���ͣ���CAR����
	 * @param itemType String
	 */
	public void setItemType(String itemType){
		this.itemType = itemType;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ���ƺ�
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ���֤
	 * @param attribute2 String
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ��;
	 * @param attribute3 String
	 */
	public void setAttribute3(String attribute3){
		this.attribute3 = attribute3;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� Ĭ�ϼ�ʻԱ
	 * @param attribute4 String
	 */
	public void setAttribute4(String attribute4){
		this.attribute4 = attribute4;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE5
	 * @param attribute5 String
	 */
	public void setAttribute5(String attribute5){
		this.attribute5 = attribute5;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE6
	 * @param attribute6 String
	 */
	public void setAttribute6(String attribute6){
		this.attribute6 = attribute6;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE7
	 * @param attribute7 String
	 */
	public void setAttribute7(String attribute7){
		this.attribute7 = attribute7;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE8
	 * @param attribute8 String
	 */
	public void setAttribute8(String attribute8){
		this.attribute8 = attribute8;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE9
	 * @param attribute9 String
	 */
	public void setAttribute9(String attribute9){
		this.attribute9 = attribute9;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE10
	 * @param attribute10 String
	 */
	public void setAttribute10(String attribute10){
		this.attribute10 = attribute10;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE11
	 * @param attribute11 String
	 */
	public void setAttribute11(String attribute11){
		this.attribute11 = attribute11;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE12
	 * @param attribute12 String
	 */
	public void setAttribute12(String attribute12){
		this.attribute12 = attribute12;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE13
	 * @param attribute13 String
	 */
	public void setAttribute13(String attribute13){
		this.attribute13 = attribute13;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE14
	 * @param attribute14 String
	 */
	public void setAttribute14(String attribute14){
		this.attribute14 = attribute14;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE15
	 * @param attribute15 String
	 */
	public void setAttribute15(String attribute15){
		this.attribute15 = attribute15;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE16
	 * @param attribute16 String
	 */
	public void setAttribute16(String attribute16){
		this.attribute16 = attribute16;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE17
	 * @param attribute17 String
	 */
	public void setAttribute17(String attribute17){
		this.attribute17 = attribute17;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE18
	 * @param attribute18 String
	 */
	public void setAttribute18(String attribute18){
		this.attribute18 = attribute18;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE19
	 * @param attribute19 String
	 */
	public void setAttribute19(String attribute19){
		this.attribute19 = attribute19;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE20
	 * @param attribute20 String
	 */
	public void setAttribute20(String attribute20){
		this.attribute20 = attribute20;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE1
	 * @param nAttribute1 String
	 */
	public void setNAttribute1(String nAttribute1){
		this.nAttribute1 = nAttribute1;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE2
	 * @param nAttribute2 String
	 */
	public void setNAttribute2(String nAttribute2){
		this.nAttribute2 = nAttribute2;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE3
	 * @param nAttribute3 String
	 */
	public void setNAttribute3(String nAttribute3){
		this.nAttribute3 = nAttribute3;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE4
	 * @param nAttribute4 String
	 */
	public void setNAttribute4(String nAttribute4){
		this.nAttribute4 = nAttribute4;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE5
	 * @param nAttribute5 String
	 */
	public void setNAttribute5(String nAttribute5){
		this.nAttribute5 = nAttribute5;
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE1
	 * @param dAttribute1 String
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDAttribute1(String dAttribute1) throws CalendarException{
		this.dAttribute1.setCalendarValue(dAttribute1);
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE2
	 * @param dAttribute2 String
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDAttribute2(String dAttribute2) throws CalendarException{
		this.dAttribute2.setCalendarValue(dAttribute2);
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE3
	 * @param dAttribute3 String
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDAttribute3(String dAttribute3) throws CalendarException{
		this.dAttribute3.setCalendarValue(dAttribute3);
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE4
	 * @param dAttribute4 String
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDAttribute4(String dAttribute4) throws CalendarException{
		this.dAttribute4.setCalendarValue(dAttribute4);
	}

	/**
	 * ���ܣ������ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE5
	 * @param dAttribute5 String
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setDAttribute5(String dAttribute5) throws CalendarException{
		this.dAttribute5.setCalendarValue(dAttribute5);
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ETS_ITEM_INFO_EX_S.NEXTVAL
	 * @return String
	 */
	public String getItemInfoExId() {
		return this.itemInfoExId;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ETS_ITEM_INFO.barcode
	 * @return String
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� �豸���ͣ���CAR����
	 * @return String
	 */
	public String getItemType() {
		return this.itemType;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ���ƺ�
	 * @return String
	 */
	public String getAttribute1() {
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ���֤
	 * @return String
	 */
	public String getAttribute2() {
		return this.attribute2;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ��;
	 * @return String
	 */
	public String getAttribute3() {
		return this.attribute3;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� Ĭ�ϼ�ʻԱ
	 * @return String
	 */
	public String getAttribute4() {
		return this.attribute4;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE5
	 * @return String
	 */
	public String getAttribute5() {
		return this.attribute5;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE6
	 * @return String
	 */
	public String getAttribute6() {
		return this.attribute6;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE7
	 * @return String
	 */
	public String getAttribute7() {
		return this.attribute7;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE8
	 * @return String
	 */
	public String getAttribute8() {
		return this.attribute8;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE9
	 * @return String
	 */
	public String getAttribute9() {
		return this.attribute9;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE10
	 * @return String
	 */
	public String getAttribute10() {
		return this.attribute10;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE11
	 * @return String
	 */
	public String getAttribute11() {
		return this.attribute11;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE12
	 * @return String
	 */
	public String getAttribute12() {
		return this.attribute12;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE13
	 * @return String
	 */
	public String getAttribute13() {
		return this.attribute13;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE14
	 * @return String
	 */
	public String getAttribute14() {
		return this.attribute14;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE15
	 * @return String
	 */
	public String getAttribute15() {
		return this.attribute15;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE16
	 * @return String
	 */
	public String getAttribute16() {
		return this.attribute16;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE17
	 * @return String
	 */
	public String getAttribute17() {
		return this.attribute17;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE18
	 * @return String
	 */
	public String getAttribute18() {
		return this.attribute18;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE19
	 * @return String
	 */
	public String getAttribute19() {
		return this.attribute19;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� ATTRIBUTE20
	 * @return String
	 */
	public String getAttribute20() {
		return this.attribute20;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE1
	 * @return String
	 */
	public String getNAttribute1() {
		return this.nAttribute1;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE2
	 * @return String
	 */
	public String getNAttribute2() {
		return this.nAttribute2;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE3
	 * @return String
	 */
	public String getNAttribute3() {
		return this.nAttribute3;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE4
	 * @return String
	 */
	public String getNAttribute4() {
		return this.nAttribute4;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� N_ATTRIBUTE5
	 * @return String
	 */
	public String getNAttribute5() {
		return this.nAttribute5;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE1
	 * @return String
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDAttribute1() throws CalendarException {
		dAttribute1.setCalPattern(getCalPattern());
		return this.dAttribute1;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE2
	 * @return String
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDAttribute2() throws CalendarException {
		dAttribute2.setCalPattern(getCalPattern());
		return this.dAttribute2;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE3
	 * @return String
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDAttribute3() throws CalendarException {
		dAttribute3.setCalPattern(getCalPattern());
		return this.dAttribute3;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE4
	 * @return String
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDAttribute4() throws CalendarException {
		dAttribute4.setCalPattern(getCalPattern());
		return this.dAttribute4;
	}

	/**
	 * ���ܣ���ȡ�ʲ���չ��Ϣ��ETS_ITEM_INFO_EX���� D_ATTRIBUTE5
	 * @return String
	 * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
	 */
	public SimpleCalendar getDAttribute5() throws CalendarException {
		dAttribute5.setCalPattern(getCalPattern());
		return this.dAttribute5;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemSpec() {
		return itemSpec;
	}
	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}
	public String getLifeInYears() {
		return lifeInYears;
	}
	public void setLifeInYears(String lifeInYears) {
		this.lifeInYears = lifeInYears;
	}
	public String getResponsibilityUser() {
		return responsibilityUser;
	}
	public void setResponsibilityUser(String responsibilityUser) {
		this.responsibilityUser = responsibilityUser;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

}