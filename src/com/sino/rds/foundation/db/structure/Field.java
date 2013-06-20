package com.sino.rds.foundation.db.structure;

import com.sino.base.SinoBaseObject;
import com.sino.base.constant.db.DataTypeConstant;
import com.sino.base.util.ArrUtil;
import com.sino.base.util.StrUtil;

public class Field extends SinoBaseObject {
	private String name = "";
	private String type = "";
	private boolean nullAble = true;
	private int length = -1;
	private int precision = -1;
	private boolean foreignKey = false;
	private String referField = "";
	private String referTable = "";
	private String comment = "";
	private String catalogName = "";
	private String className = "";

	/**
	 * ���ܣ������ֶ�����
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���ܣ������ֶ�����
	 * @param name �ֶ���
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���ܣ������ֶ����͡�
	 * @return �ֶ�����
	 */
	public String getType() {
		return type;
	}

	/**
	 * ���ܣ������ֶ����͡�
	 * @param type �ֶ�����
	 */
	public void setType(String type){
		this.type = type;
	}

	/**
	 * ���ܣ��жϸ��ֶ��Ƿ�����Ϊ�ա�
	 * @return true��ʾ����գ�false��ʾ������
	 */
	public boolean isNullAble() {
		return nullAble;
	}

	/**
	 * ���ܣ������Ƿ�����Ϊ�ա�
	 * @param nullAble boolean
	 */
	public void setNullAble(boolean nullAble) {
		this.nullAble = nullAble;
	}

	/**
	 * ���ܣ���ȡ�ֶ����������ַ�����
	 * @return int
	 */
	public int getLength() {
		return length;
	}

	public boolean isForeignKey() {
		return foreignKey;
	}

	public String getReferField() {
		return referField;
	}

	public String getReferTable() {
		return referTable;
	}

	public String getComment() {
		return comment;
	}

	public int getPrecision() {
		return precision;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public String getClassName() {
		return className;
	}

	/**
	 * ���ܣ������ֶ����������ַ�����
	 * @param length int
	 */
	public void setLength(int length) {
		this.length = length;
	}

	public void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	public void setReferField(String referField) {
		this.referField = referField;
	}

	public void setReferTable(String referTable) {
		this.referTable = referTable;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * �жϸ��ֶ��Ƿ���ֶ�
	 * @return boolean
	 */
	public boolean isLobField() {
		boolean isLobField = false;
		if (!StrUtil.isEmpty(name)) {
			isLobField = ArrUtil.isInArr(DataTypeConstant.LIMIT_LOB_TYPE, type);
		}
		return isLobField;
	}

	/**
	 * ���ܣ��ж��ֶ��Ƿ������ֶ�
	 * @return boolean
	 */
	public boolean isNumberField() {
		return ArrUtil.isInArr(DataTypeConstant.LIMIT_NUMBER_TYPE, type);
	}
}

