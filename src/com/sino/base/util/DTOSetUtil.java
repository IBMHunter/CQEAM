package com.sino.base.util;

import java.util.List;

import com.sino.base.data.RowSet;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ReflectException;

/**
 * <p>Title: SinoCMS</p>
 * <p>Description: �����ƶ���ͬ����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class DTOSetUtil {

	/**
	 * �ж�DTOSet��ָ���ֶ�ֵ�Ƿ����
	 * @param dtos DTOSet
	 * @param fieldName String
	 * @return boolean true��ʾ��ȣ�false��ʾ����
	 */
	public static boolean equals(DTOSet dtos, String fieldName) {
		boolean equals = false;
		try {
			if (dtos != null && !dtos.isEmpty() && dtos.contains(fieldName)) {
				equals = true;
				int dtoCount = dtos.getSize();
				DTO dto = null;
				Object firstValue = null;
				boolean valueSeted = false;
				Object loopValue = null;
				for (int i = 0; i < dtoCount; i++) {
					dto = (DTO) dtos.getDTO(i);
					if (!valueSeted) {
						firstValue = ReflectionUtil.getProperty(dto, fieldName);
						valueSeted = true;
						continue;
					}
					loopValue = ReflectionUtil.getProperty(dto, fieldName);
					if (firstValue == null) {
						equals = (loopValue == null);
					} else {
						equals = (firstValue.equals(loopValue));
					}
					if (!equals) {
						break;
					}
				}
			}
		} catch (ReflectException ex) {
			ex.printLog();
		}
		return equals;
	}

	/**
	 * �ж�DTOSet��ָ���ֶ�ֵ�Ƿ����
	 * @param dtos DTOSet
	 * @param fieldNameArr String[]
	 * @return boolean true��ʾ��ȣ�false��ʾ����
	 */
	public static boolean equals(DTOSet dtos, String[] fieldNameArr) {
		boolean equals = false;
		if (fieldNameArr != null) {
			equals = true;
			int fieldCount = fieldNameArr.length;
			String fieldName = "";
			for (int i = 0; i < fieldCount; i++) {
				fieldName = fieldNameArr[i];
				equals = equals && equals(dtos, fieldName);
				if (!equals) {
					break;
				}
			}
		}
		return equals;
	}

	public static List getListFromRowSet(RowSet rows, String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
