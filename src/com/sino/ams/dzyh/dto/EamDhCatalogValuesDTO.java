package com.sino.ams.dzyh.dto;

/**
* <p>Title: ��ֵ�׺�ƷĿ¼ά����(EAM) EamDhCatalogValues</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class EamDhCatalogValuesDTO extends EamDhCatalogSetDTO{

	private String catalogValueId = "";
	private String catalogCode = "";
	private int catalogSetId = 0;
	private String catalogName = "";
	private int unit = 0;
	private String description = "";
	private int barcodeFlag = 0;
	private int commonFlag = 0;
	private String attribute1 = "";
	private String attribute2 = "";

	private String unitValue="";	//������λ

	public String getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� EAM_DH_CATALOG_VALUES_S.NEXTVAL
	 * @param catalogValueId String
	 */
	public void setCatalogValueId(String catalogValueId){
		this.catalogValueId = catalogValueId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� Ŀ¼���
	 * @param catalogCode String
	 */
	public void setCatalogCode(String catalogCode){
		this.catalogCode = catalogCode;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� EAM_DH_CATALOG_SET. CATLOG_SET_ID
	 * @param catalogSetId String
	 */
	public void setCatalogSetId(int catalogSetId){
		this.catalogSetId = catalogSetId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� Ʒ��
	 * @param catalogName String
	 */
	public void setCatalogName(String catalogName){
		this.catalogName = catalogName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� ������λ
	 * @param unit String
	 */
	public void setUnit(int unit){
		this.unit = unit;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� ע��
	 * @param description String
	 */
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� �����ʶ
	 * @param barcodeFlag String
	 */
	public void setBarcodeFlag(int barcodeFlag){
		this.barcodeFlag = barcodeFlag;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� ���ñ�ʶ
	 * @param commonFlag String
	 */
	public void setCommonFlag(int commonFlag){
		this.commonFlag = commonFlag;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� �����ֶ�1
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�ƷĿ¼ά����(EAM)���� �����ֶ�2
	 * @param attribute2 String
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� EAM_DH_CATALOG_VALUES_S.NEXTVAL
	 * @return String
	 */
	public String getCatalogValueId() {
		return this.catalogValueId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� Ŀ¼���
	 * @return String
	 */
	public String getCatalogCode() {
		return this.catalogCode;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� EAM_DH_CATALOG_SET. CATLOG_SET_ID
	 * @return String
	 */
	public int getCatalogSetId() {
		return this.catalogSetId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� Ʒ��
	 * @return String
	 */
	public String getCatalogName() {
		return this.catalogName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� ������λ
	 * @return String
	 */
	public int getUnit() {
		return this.unit;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� ע��
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� �����ʶ
	 * @return String
	 */
	public int getBarcodeFlag() {
		return this.barcodeFlag;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� ���ñ�ʶ
	 * @return String
	 */
	public int getCommonFlag() {
		return this.commonFlag;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� �����ֶ�1
	 * @return String
	 */
	public String getAttribute1() {
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�ƷĿ¼ά����(EAM)���� �����ֶ�2
	 * @return String
	 */
	public String getAttribute2() {
		return this.attribute2;
	}
}
