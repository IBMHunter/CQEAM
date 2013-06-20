package com.sino.sinoflow.todo.util; 

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;

/**
 * 
 * @ϵͳ����:  
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: wangwenbiaogy
 * @����ʱ��: Oct 24, 2011
 */ 
public class DateUtil {
	public static java.sql.Timestamp getSqlCommonDate(String date) throws ParseException {
		if (null == date || date.equals("")) {
			return null;
		} else {
			return string2Time(date);
		}
	}

	public static java.sql.Date getSqlDate(String date) {
		if (null == date || date.equals("")) {
			return null;
		} else {
			return java.sql.Date.valueOf(date);
		}
	}

	public static java.sql.Timestamp getSqlFromDate(String date) {
		if (null == date || date.equals("")) {
			return null;
		} else {
			try {
				return string2Time(date + " 00:00:00");
			} catch (ParseException e) {
				return null;
			}
		}
	}

	public static java.sql.Timestamp getSqlToDate(String date) {
		if (null == date || date.equals("")) {
			return null;
		} else {
			try {
				return string2Time(date + " 23:59:59");
			} catch (ParseException e) {
				return null;
			}
		}
	}

	/**
	 * method ���ַ������͵�����ת��Ϊһ��timestamp��ʱ�����java.sql.Timestamp��
	 * 
	 * @param dateString ��Ҫת��Ϊtimestamp���ַ���
	 * @return dataTime timestamp
	 * @throws ParseException
	 */
	public static java.sql.Timestamp string2Time(String dateString) throws ParseException {
		if (StrUtil.isEmpty(dateString)) {
			return new java.sql.Timestamp((new Date()).getTime());
		} else {
			DateFormat dateFormat;
			dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);// �趨��ʽ
			// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
			dateFormat.setLenient(false);
			java.util.Date timeDate = new Date();// util����
			try {
				timeDate = dateFormat.parse(dateString);
			} catch (ParseException e) {
				Logger.logError(e);
				throw e;
			}
			return new java.sql.Timestamp(timeDate.getTime());
		}
	}
	
	public static java.sql.Timestamp string2Date(String dateString) throws ParseException {
		if (StrUtil.isEmpty(dateString)) {
			return new java.sql.Timestamp((new Date()).getTime());
		} else {
			DateFormat dateFormat;
			dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);// �趨��ʽ
			// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
			dateFormat.setLenient(false);
			java.util.Date timeDate = new Date();// util����
			try {
				timeDate = dateFormat.parse(dateString);
			} catch (ParseException e) {
				Logger.logError(e);
				throw e;
			}
			return new java.sql.Timestamp(timeDate.getTime());
		}
	}

	public static String getCurDateStr() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	public static String getCurDateTimeStr() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return df.format(date);
	}

	/**
	 * ���ܣ��Դ��������ָ����ʽ��ʽ��ָ�������ֶΡ�
	 * 
	 * @param obj Object ����JavaBean�淶�Ķ���
	 * @param dateFields String ����������ֶΣ�����ֶ����÷ֺ�����
	 * @param datePattern String ������ʽ
	 * @see com.sino.base.constant.calen.CalendarConstant
	 * @return Object ���ظ�ʽ��֮��Ķ���
	 */
	public static Object formatCalendar(Object obj, String dateFields, String datePattern) {
		boolean isValidCalendar = CalendarUtil.isValidCalendar(datePattern);
		boolean isValidDatePattern = CalendarUtil.isValidDatePattern(datePattern);
		boolean isValidPattern = isValidCalendar || isValidDatePattern;
		if (obj != null && !StrUtil.isEmpty(dateFields) && isValidPattern) {
			String[] fieldArr = StrUtil.splitStr(dateFields);
			int fieldCount = fieldArr.length;
			String fieldName = "";
			Object fieldValue = null;
			Class cls = obj.getClass();
			SimpleCalendar cal = new SimpleCalendar();
			for (int i = 0; i < fieldCount; i++) {
				try {
					fieldName = fieldArr[i];
					if (ReflectionUtil.hasProperty(cls, fieldName)) {
						fieldValue = ReflectionUtil.getProperty(obj, fieldName);
					}
					if (StrUtil.isEmpty(fieldValue)) {
						continue;
					}
					cal.setCalendarValue(String.valueOf(fieldValue));
					cal.setCalPattern(datePattern);
					if (isValidCalendar) {
						fieldValue = cal.getCalendarValue();
					} else {
						fieldValue = cal.getSimpleDate().getDateValue();
					}
					ReflectionUtil.setProperty(obj, fieldName, fieldValue);
				} catch (Exception ex) {
					// Logger.logError(ex);//���쳣������¼
				}
			}
		}
		return obj;
	}
}
