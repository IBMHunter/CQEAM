package com.sino.rds.foundation.db.structure;

import com.sino.base.SinoBaseObject;
import com.sino.base.db.sql.model.SQLModel;

import java.util.ArrayList;
import java.util.List;

public class SQLObject extends SinoBaseObject {
	private SQLModel sqlModel = null;
	protected List<Field> fields = null;

	/**
	 * ���ܣ����캯�������ڹ�����StructureParserʹ�á�
	 */
	public SQLObject() {
		fields = new ArrayList<Field>();
	}

	/**
	 * ���ܣ����ò�ѯSQL������StructureParserʹ�á�
	 * @param sqlModel SQL��ѯ��
	 */
	public void setSQL(SQLModel sqlModel) {
		this.sqlModel = sqlModel;
	}

	/**
	 * ���ܣ������ֶ����顣
	 * @param fields List<Field>
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void addField(Field field) {
		if(!fields.contains(field)){
			fields.add(field);
		}
	}

	/**
	 * ���ܣ����SQL��
	 * @return SQLModel
	 */
	public SQLModel getSqlModel() {
		return sqlModel;
	}

	/**
	 * ���ܣ����������ֶ���ɵ��б�
	 * @return List<Field>
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * ���ܣ��жϸ�SQL�Ƿ����ָ�����͵��ֶΡ�
	 * @param fieldType String
	 * @return boolean
	 */
	public boolean hasGivenTypeField(String fieldType) {
		boolean hasField = false;
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).getType().equals(fieldType)) {
				hasField = true;
				break;
			}
		}
		return hasField;
	}

	/**
	 * ���ܣ���ȡ�����ֶ������б�
	 * @return List
	 */
	public List<String> getFieldNames() {
		int size = fields.size();
		List<String>  fieldNames = new ArrayList<String> (size);
		for (int i = 0; i < size; i++) {
			fieldNames.add(fields.get(i).getName());
		}
		return fieldNames;
	}

	/**
	 * ���ܣ���ȡ���д��ֶ����ƹ��ɵ��б�
	 * @return List
	 */
	public List getLobFieldNames() {
		int size = fields.size();
		List fieldNames = new ArrayList();
		Field field = null;
		for (int i = 0; i < size; i++) {
			field = fields.get(i);
			if (field.isLobField()) {
				fieldNames.add(field.getName());
			}
		}
		return fieldNames;
	}

	/**
	 * ���ܣ��ж��Ƿ��и��ֶ�
	 * @param fieldName String
	 * @return boolean
	 */
	public boolean hasField(String fieldName) {
		int fieldCount = fields.size();
		for (int i = 0; i < fieldCount; i++) {
			String field = fields.get(i).getName();
			if (field.equalsIgnoreCase(fieldName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ͨ���ֶ�����ȡ�ֶζ���
	 * @param fieldName String
	 * @return Field
	 */
	public Field getField(String fieldName) {
		int fieldCount = fields.size();
		for (int i = 0; i < fieldCount; i++) {
			Field field = fields.get(i);
			if (field.getName().equalsIgnoreCase(fieldName)) {
				return field;
			}
		}
		return null;
	}
}
