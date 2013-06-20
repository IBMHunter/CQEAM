package com.sino.nm.ams.inv.storeman.bean;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.constant.WorldConstant;
import com.sino.base.exception.StrException;
import com.sino.base.log.Logger;
import com.sino.base.validate.StrValidator;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class StrUtilHave {
	private StrUtilHave() {
	}

	/**
	 * ���ܣ�����ĳ�ַ���������һ�ַ����ĸ�����
	 * @param srcStr  ԭ�ַ�����
	 * @param subStr  �����ַ�����
	 * @param ignCase �Ƿ���Դ�Сд��
	 * @return ���ذ�����Ŀ����������Ϊ0��
	 */
	public static int containNum(String srcStr, String subStr, boolean ignCase) {
		int containNum = 0;
		if (!isEmpty(srcStr) && !isEmpty(subStr)) {
			if (ignCase) {
				srcStr = srcStr.toLowerCase();
				subStr = subStr.toLowerCase();
			}
			int index = -1;
			while ((index = srcStr.indexOf(subStr)) != -1) {
				containNum++;
				srcStr = srcStr.substring(index + subStr.length());
			}
		}
		return containNum;
	}

	/**
	 * ���ܣ�ת���ַ���Ϊ������
	 * @param str String
	 * @return int �ɹ��򷵻ض�Ӧ���������򷵻�-1��
	 */
	public static int strToInt(String str) {
		int retValue = -1;
		try {
			retValue = Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			Logger.logError(ex);
		}
		return retValue;
	}

	/**
	 * ���ܣ��������ء�����ĳ�ַ���������һ�ַ����ĸ�����ԭ�����ң������Դ�Сд��
	 * @param srcStr ԭ�ַ�����
	 * @param subStr �����ַ�����
	 * @return ���ذ�����Ŀ����������Ϊ0��
	 */
	public static int containNum(String srcStr, String subStr) {
		return containNum(srcStr, subStr, false);
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ����Ƿ�����һ�ַ�����
	 * ע�� �����п��ַ������жϣ�����ҿ��ַ�����һ�ɷ���true��
	 * @param srcStr  ԭ�ַ�����
	 * @param subStr  �����ַ�����
	 * @param ignCase �Ƿ���Դ�Сд��
	 * @return ����true��false��
	 */
	public static boolean contains(String srcStr, String subStr, boolean ignCase) {
		boolean contains = false;
		if (isEmpty(subStr)) {
			contains = true;
		} else {
			contains = containNum(srcStr, subStr, ignCase) > 0;
		}
		return contains;
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ����Ƿ�����һ�ַ�����ԭ�����ң������Դ�Сд��
	 * ע�� �����п��ַ������жϣ�����ҿ��ַ�����һ�ɷ���false��
	 * @param srcStr ԭ�ַ�����
	 * @param subStr �����ַ�����
	 * @return ����true��false��
	 */
	public static boolean contains(String srcStr, String subStr) {
		return contains(srcStr, subStr, false);
	}

	/**
	 * ���ܣ�����ԭ�ַ���(subStr)��Ŀ���ַ���(srcStr)�г��ֵڼ���(existTime)���������粻���ڣ��򷵻�-1��
	 * @param srcStr    ԭ�ַ���
	 * @param subStr    Ŀ���ַ���
	 * @param existTime subStr��srcStr�г��ֵĴ�����
	 * @param ignCase   �Ƿ���Դ�Сд��
	 * @return ����������������Ҫ���򷵻�-1��
	 */
	public static int findIndex(String srcStr, String subStr, int existTime,
								boolean ignCase) {
		int retIndex = -1;
		if (existTime > 0 &&
				containNum(srcStr, subStr, ignCase) >= existTime) {
			if (ignCase) {
				srcStr = srcStr.toLowerCase();
				subStr = subStr.toLowerCase();
			}
			int containNum = 0;
			StringBuffer tempStr = new StringBuffer();
			while ((retIndex = srcStr.indexOf(subStr)) != -1) {
				if (containNum == existTime) {
					break;
				}
				tempStr.append(srcStr.substring(0, retIndex + subStr.length()));
				srcStr = srcStr.substring(retIndex + subStr.length());
				containNum++;
			}
			retIndex = tempStr.length() - subStr.length();
		}
		return retIndex;
	}

	/**
	 * ���ܣ��������ء�����ԭ�ַ���(subStr)��Ŀ���ַ���(srcStr)��ĳһ��(existTime)���ֵ��������粻���ڣ��򷵻�-1��
	 * ע�� ԭ�����ң������Դ�Сд��
	 * @param srcStr    ԭ�ַ���
	 * @param subStr    Ŀ���ַ���
	 * @param existTime subStr��srcStr�еڳ��ֵĴ�����
	 * @return ����������������Ҫ���򷵻�-1��
	 */
	public static int findIndex(String srcStr, String subStr, int existTime) {
		return findIndex(srcStr, subStr, existTime, false);
	}

	/**
	 * ���ܣ��������ء�����ԭ�ַ���(subStr)��Ŀ���ַ���(srcStr)�е�һ�γ��ֵ��������粻���ڣ��򷵻�-1��
	 * @param srcStr  ԭ�ַ���
	 * @param subStr  Ŀ���ַ���
	 * @param ignCase �Ƿ���Դ�Сд��
	 * @return ����������������Ҫ���򷵻�-1��
	 */
	public static int findIndex(String srcStr, String subStr,
								boolean ignCase) {
		return findIndex(srcStr, subStr, 1, ignCase);
	}

	/**
	 * ���ܣ��������ء�����ԭ�ַ���(subStr)��Ŀ���ַ���(srcStr)�е�һ��(existTime)���ֵ��������粻���ڣ��򷵻�-1��
	 * ע�� ԭ�����ң������Դ�Сд��
	 * @param srcStr ԭ�ַ���
	 * @param subStr Ŀ���ַ���
	 * @return ����������������Ҫ���򷵻�-1��
	 */
	public static int findIndex(String srcStr, String subStr) {
		return findIndex(srcStr, subStr, 1, false);
	}

	/**
	 * ���ܣ���ĳһ�ַ����ָ�����顣
	 * @param srcStr   ԭ�ַ���
	 * @param splitStr �ָ��ַ���
	 * @return ���طָ�����ɵ������ֻ����ԭ�ַ���һ��Ԫ�ص����顣
	 */
	public static String[] splitStr(String srcStr, String splitStr) {
		String[] returnArr = {srcStr};
		if (srcStr != null && splitStr != null && !srcStr.equals("")) {
			if (!splitStr.equals("")) {
				int strLength = srcStr.length();
				int index = -1;
				List strList = new ArrayList();
				String currStr = "";
				boolean isPrevNull = true;
				int splitCount = splitStr.length();
				while ((index = srcStr.indexOf(splitStr)) != -1) {
					currStr = srcStr.substring(0, index);
					if (index == 0) {
						index += splitCount;
						if (index > strLength) {
							index = strLength;
						}
						if (isPrevNull) {
							strList.add(null);
						}
						isPrevNull = true;
					} else {
						strList.add(currStr);
						isPrevNull = false;
					}
					srcStr = srcStr.substring(index);
				}
				if(!srcStr.equals("")){
					strList.add(srcStr);
				} else {
					strList.add(null);
				}
				returnArr = new String[strList.size()];
				strList.toArray(returnArr);
			}
		}
		return returnArr;
	}


	/**
	 * ���ܣ��������ء��ָ��ַ���Ϊ���飬Ĭ�Ϸָ��ַ���";"��
	 * @param srcStr String
	 * @return String[]
	 */
	public static String[] splitStr(String srcStr) {
		return splitStr(srcStr, WorldConstant.SPLITOR);
	}

	/**
	 * ���ܣ��������ء���ĳһ�ַ����ָ�ɶ�ά���顣��һά�ָ��ַ���ΪSplitStr1���ڶ�ά�ָ��ַ���ΪSplitStr2
	 * ���� �ַ��� str = "abd;sfgd;wgfd@@@fsdg;sgfgf;weter;@@@dgfdh;wgfg;@@@gfhgh"������strSplit(str, "@@@", ";")�����ָ����������
	 * arr[0][0] = "abd"<br>
	 * arr[0][1] = "sfgd"<br>
	 * arr[0][2] = "wgfd"<br>
	 * arr[1][0] = "fsdg"<br>
	 * arr[1][1] = "fsdg"<br>
	 * arr[1][2] = "weter"<br>
	 * arr[3][0] = "dgfdh"<br>
	 * arr[3][1] = "wgfg"<br>
	 * arr[4][0] = "gfhgh"
	 * @param srcStr    ԭ�ַ�����
	 * @param splitStr1 ��һά�ָ��ַ�����
	 * @param splitStr2 �ڶ�ά�ָ��ַ�����
	 * @return �ɹ��򷵻ض�ά���飬���򷵻�null��
	 */
	public static String[][] splitStr(String srcStr, String splitStr1,
									  String splitStr2) {
		String[][] returnArr = {{srcStr}
		};
		if (srcStr != null && !srcStr.equals("") && splitStr1 != null &&
				!splitStr1.equals("") && splitStr2 != null && !splitStr2.equals("")) {
			String[] tempArr = splitStr(srcStr, splitStr1);
			if (tempArr != null && tempArr.length > 0) {
				String[] tempArr2 = null;
				int length = 1;
				ArrayList tempList = new ArrayList();
				for (int i = 0; i < tempArr.length; i++) {
					tempArr2 = splitStr(tempArr[i], splitStr2);
					length = (length > tempArr2.length) ? length :
							tempArr2.length;
					tempList.add(tempArr2);
				}
				returnArr = new String[tempArr.length][length];
				for (int i = 0; i < tempList.size(); i++) {
					returnArr[i] = (String[]) tempList.get(i);
				}
			}
		}
		return returnArr;
	}

	/**
	 * ���ܣ��ж�ĳһ�ַ����Ƿ������һ�ַ����ɷָ���(splitStr)�ֿ���ȫ���Ӵ���ȫ��������֮һ��
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr     ���ַ�����
	 * @param splitStr   ���ַ����ָ�����
	 * @param allOrOneOf �ж�ȫ���������ǰ������ַ����ɷָ����ָ��ɵ��ַ����е��κ�һ����true���ж�ȫ����false���ж�����֮һ��
	 * @param ignCase    �Ƿ���Դ�Сд��
	 * @return ����ֵ��
	 * @throws StrException �ַ�����ʽ���쳣
	 */
	private static boolean Contains(String srcStr, String subStr,
									String splitStr, boolean allOrOneOf,
									boolean ignCase) throws StrException {
		StrValidator.validateEmpty(srcStr);
		StrValidator.validateEmpty(subStr);
		StrValidator.validateEmpty(splitStr);
		boolean retVal = allOrOneOf;
		String[] tempArr = splitStr(subStr, splitStr);
		if (tempArr != null && tempArr.length > 0) {
			for (int i = 0; i < tempArr.length; i++) {
				if ((allOrOneOf && !contains(srcStr, tempArr[i], ignCase)) ||
						(!allOrOneOf && contains(srcStr, tempArr[i], ignCase))) {
					retVal = !retVal;
					break;
				}
			}
		}
		return retVal;
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ���(srcStr)�Ƿ�����һ�ַ���(subStr)���ɷָ���(splitStr)�ֿ���ȫ���Ӵ���
	 * @param srcStr      ԭ�ַ�����
	 * @param subStr      Ŀ���ַ�����
	 * @param splitStr    Ŀ���ַ����ָ�����
	 * @param ignorceCase �Ƿ���Դ�Сд��
	 * @return ����true��false��
	 * @throws StrException
	 */
	public static boolean containsAll(String srcStr, String subStr,
									  String splitStr, boolean ignorceCase) throws
			StrException {
		return (Contains(srcStr, subStr, splitStr, true, ignorceCase));
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ���(srcStr)�Ƿ�����һ�ַ���(subStr)���ɷָ���(splitStr)�ֿ���ȫ���Ӵ��������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr   ԭ�ַ�����
	 * @param subStr   Ŀ���ַ�����
	 * @param splitStr Ŀ���ַ����ָ�����
	 * @return ����true��false��
	 * @throws StrException
	 */
	public static boolean containsAll(String srcStr, String subStr,
									  String splitStr) throws StrException {
		return Contains(srcStr, subStr, splitStr, true, false);
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ���(srcStr)�Ƿ�����һ�ַ���(subStr)���ɷָ���(��;��)�ֿ���ȫ���Ӵ��������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr      ԭ�ַ�����
	 * @param subStr      Ŀ���ַ�����
	 * @param ignorceCase �Ƿ���Դ�Сд
	 * @return ����true��false��
	 * @throws StrException
	 */
	public static boolean containsAll(String srcStr, String subStr,
									  boolean ignorceCase) throws StrException {
		return Contains(srcStr, subStr, WorldConstant.SPLITOR, true,
				ignorceCase);
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ���(srcStr)�Ƿ�����һ�ַ���(subStr)���ɷָ���(��;��)�ֿ���ȫ���Ӵ���
	 * @param srcStr ԭ�ַ�����
	 * @param subStr Ŀ���ַ�����
	 * @return ����true��false��
	 * @throws StrException
	 */
	public static boolean containsAll(String srcStr, String subStr) throws
			StrException {
		return Contains(srcStr, subStr, WorldConstant.SPLITOR, true, false);
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ���(srcStr)�Ƿ�����һ�ַ���(subStr)���ɷָ���(splitStr)�ֿ���ȫ���Ӵ��е��κ�һ����
	 * @param srcStr      ԭ�ַ�����
	 * @param subStr      Ŀ���ַ�����
	 * @param splitStr    Ŀ���ַ����ָ�����
	 * @param ignorceCase �Ƿ���Դ�Сд
	 * @return ����true��false��
	 * @throws StrException
	 */
	public static boolean containsOneOf(String srcStr, String subStr,
										String splitStr, boolean ignorceCase) throws
			StrException {
		return Contains(srcStr, subStr, splitStr, false, ignorceCase);
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ���(srcStr)�Ƿ�����һ�ַ���(subStr)���ɷָ���(splitStr)�ֿ���ȫ���Ӵ��е��κ�һ���������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr   ԭ�ַ�����
	 * @param subStr   Ŀ���ַ�����
	 * @param splitStr Ŀ���ַ����ָ�����
	 * @return ����true��false��
	 * @throws StrException
	 */
	public static boolean containsOneOf(String srcStr, String subStr,
										String splitStr) throws StrException {
		return Contains(srcStr, subStr, splitStr, false, false);
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ���(srcStr)�Ƿ�����һ�ַ���(subStr)���ɷָ���(��;��)�ֿ���ȫ���Ӵ��е��κ�һ����
	 * @param srcStr      ԭ�ַ�����
	 * @param subStr      Ŀ���ַ�����
	 * @param ignorceCase �Ƿ���Դ�Сд
	 * @return ����true��false��
	 * @throws StrException
	 */
	public static boolean containsOneOf(String srcStr, String subStr,
										boolean ignorceCase) throws
			StrException {
		return Contains(srcStr, subStr, WorldConstant.SPLITOR, false,
				ignorceCase);
	}

	/**
	 * ���ܣ��������ء��ж�ĳ�ַ���(srcStr)�Ƿ�����һ�ַ���(subStr)���ɷָ���(��;��)�ֿ���ȫ���Ӵ��е��κ�һ����
	 * @param srcStr ԭ�ַ�����
	 * @param subStr Ŀ���ַ�����
	 * @return ����true��false��
	 * @throws StrException
	 */
	public static boolean containsOneOf(String srcStr, String subStr) throws
			StrException {
		return Contains(srcStr, subStr, WorldConstant.SPLITOR, false, false);
	}

	/**
	 * ���ܣ���ԭ�ַ���srcStr�е�startTime���͵�endTime��֮�䣨����β��������oldStr�滻��newStr�������滻��������ַ���
	 * @param srcStr    ԭ�ַ�����
	 * @param oldStr    ԭ���ַ���
	 * @param startTime ��ʼ������
	 * @param endTime   ����������
	 * @param newStr    ���滻�ɵ����ӷ�����
	 * @param ignCase   �Ƿ���Դ�Сд��true���ʾ���Դ�Сд������ԭ�����ң�
	 * @return �滻����ַ�������û���滻�򷵻�ԭ�ַ�����
	 */
	public static String replaceStr(String srcStr, String oldStr, int startTime,
									int endTime, String newStr,
									boolean ignCase) {
		String retStr = srcStr;
		String finalStr = srcStr;
		if (!isEmpty(srcStr) && !isEmpty(oldStr)) {
			if (ignCase) {
				srcStr = srcStr.toLowerCase();
				oldStr = oldStr.toLowerCase();
			}
			int fromIndex = findIndex(srcStr, oldStr, startTime, ignCase);
			int toIndex = findIndex(srcStr, oldStr, endTime, ignCase);
			if (fromIndex != -1 && toIndex != -1) {
				retStr = finalStr.substring(0, fromIndex);
				String finalTail = finalStr.substring(toIndex + oldStr.length());
				srcStr = srcStr.substring(fromIndex, toIndex + oldStr.length());
				finalStr = finalStr.substring(fromIndex,
						toIndex + oldStr.length());
				int index = -1;
				while ((index = srcStr.indexOf(oldStr)) != -1) {
					retStr += finalStr.substring(0, index) + newStr;
					srcStr = srcStr.substring(index + oldStr.length());
					finalStr = finalStr.substring(index + oldStr.length());
				}
				if (!isEmpty(finalStr)) {
					retStr += finalStr;
				}
				retStr += finalTail;
			}
		}
		return retStr;
	}

	/**
	 * ���ܣ��������ء���ԭ�ַ���srcStr�е�startTime���͵�endTime��֮�䣨����β��������oldStr�滻��newStr�������в����Դ�Сд�������滻��������ַ�����
	 * @param srcStr    ԭ�ַ�����
	 * @param oldStr    ԭ���ַ���
	 * @param startTime ��ʼ������
	 * @param endTime   ����������
	 * @param newStr    ���滻�ɵ����ӷ�����
	 * @return �滻����ַ�������û���滻�򷵻�ԭ�ַ�����
	 */
	public static String replaceStr(String srcStr, String oldStr, int startTime,
									int endTime, String newStr) {
		return replaceStr(srcStr, oldStr, startTime, endTime, newStr, false);
	}

	/**
	 * ���ܣ��������ء���ԭ�ַ���srcStr�дӵ�startTime��֮�󣨺�startTime��������oldStr�滻��newStr�������滻��������ַ�����
	 * @param srcStr    ԭ�ַ�����
	 * @param oldStr    ԭ���ַ���
	 * @param startTime ��ʼ������
	 * @param newStr    ���滻�ɵ����ӷ�����
	 * @param ignCase   �Ƿ���Դ�Сд��true���ʾ���Դ�Сд������ԭ�����ң�
	 * @return �滻����ַ�������û���滻�򷵻�ԭ�ַ�����
	 */
	public static String replaceStr(String srcStr, String oldStr, int startTime,
									String newStr, boolean ignCase) {
		int endTime = containNum(srcStr, oldStr, ignCase);
		return replaceStr(srcStr, oldStr, startTime, endTime, newStr,
				ignCase);
	}

	/**
	 * ���ܣ��������ء���ԭ�ַ���srcStr�е�endTime֮ǰ����endTime��������oldStr�滻��newStr�������滻��������ַ���
	 * @param srcStr  ԭ�ַ�����
	 * @param oldStr  ԭ���ַ���
	 * @param endTime ����������
	 * @param newStr  ���滻�ɵ����ӷ�����
	 * @param ignCase �Ƿ���Դ�Сд��true���ʾ���Դ�Сд������ԭ�����ң�
	 * @return �滻����ַ�������û���滻�򷵻�ԭ�ַ�����
	 */
	public static String replaceStr(String srcStr, String oldStr, String newStr,
									int endTime, boolean ignCase) {
		return replaceStr(srcStr, oldStr, 1, endTime, newStr, ignCase);
	}

	/**
	 * ���ܣ��������ء���ԭ�ַ���srcStr�дӵ�startTime��֮�󣨺�startTime��������oldStr�滻��newStr���滻�����У�ԭ�����ң������Դ�Сд�������滻��������ַ���
	 * @param srcStr    ԭ�ַ�����
	 * @param oldStr    ԭ���ַ���
	 * @param startTime ��ʼ������
	 * @param newStr    ���滻�ɵ����ӷ�����
	 * @return �滻����ַ�������û���滻�򷵻�ԭ�ַ�����
	 */
	public static String replaceStr(String srcStr, String oldStr, int startTime,
									String newStr) {
		int endTime = containNum(srcStr, oldStr, false);
		return replaceStr(srcStr, oldStr, startTime, endTime, newStr, false);
	}

	/**
	 * ���ܣ��������ء���ԭ�ַ���srcStr�е�endTime֮ǰ����endTime��������oldStr�滻��newStr���滻�����У�ԭ�����ң������Դ�Сд�������滻��������ַ���
	 * @param srcStr  ԭ�ַ�����
	 * @param oldStr  ԭ���ַ���
	 * @param endTime ����������
	 * @param newStr  ���滻�ɵ����ӷ�����
	 * @return �滻����ַ�������û���滻�򷵻�ԭ�ַ�����
	 */
	public static String replaceStr(String srcStr, String oldStr, String newStr,
									int endTime) {
		return replaceStr(srcStr, oldStr, 1, endTime, newStr, false);
	}

	/**
	 * ���ܣ��������ء��ַ����滻����ԭ�ַ���(srcStr)�е����ַ���(oldStr)�滻Ϊ���ַ���(newStr)���������滻����ַ�����
	 * @param srcStr  ԭ�ַ���
	 * @param oldStr  ԭ���ַ���
	 * @param newStr  ���ַ���
	 * @param ignCase �Ƿ���Դ�Сд
	 * @return �滻����ַ���
	 */
	public static String replaceStr(String srcStr, String oldStr, String newStr,
									boolean ignCase) {
		int endTime = containNum(srcStr, oldStr, ignCase);
		return replaceStr(srcStr, oldStr, 1, endTime, newStr, ignCase);
	}

	/**
	 * ���ܣ��������ء��ַ����滻����ԭ�ַ���(srcStr)�е����ַ���(oldStr)�滻Ϊ���ַ���(newStr)���������滻����ַ�����
	 * ע�� ԭ�����ң������Դ�Сд��
	 * @param srcStr ԭ�ַ���
	 * @param oldStr ԭ���ַ���
	 * @param newStr ���ַ���
	 * @return �滻����ַ���
	 */
	public static String replaceStr(String srcStr, String oldStr, String newStr) {
		int endTime = containNum(srcStr, oldStr, false);
		return replaceStr(srcStr, oldStr, 1, endTime, newStr, false);
	}

	/**
	 * ���ܣ��滻ԭ�ַ���srcStr�е�һ�����ַ���oldStrΪ���ַ���newStr��
	 * @param srcStr String
	 * @param oldStr String
	 * @param newStr String
	 * @return String �������ַ���ʱ�����滻����ַ���������ԭ�����ء�
	 */
	public static String replaceFirst(String srcStr, String oldStr,
									  String newStr) {
		String retStr = srcStr;
		if (!isEmpty(srcStr) && !isEmpty(oldStr) && !isEmpty(newStr)) {
			int index = srcStr.indexOf(oldStr);
			if (index > -1) {
				retStr = srcStr.substring(0, index);
				retStr += newStr;
				retStr += srcStr.substring(index + oldStr.length());
			}
		}
		return retStr;
	}

	/**
	 * ���ܣ���ȡ�ַ������ڲ����á�
	 * @param srcStr        ԭ�ַ�����
	 * @param subStr        ���ַ�����
	 * @param existTime     ���ַ������µĴ�����
	 * @param afterOrBefore ȡ���ַ���֮ǰ����֮����ַ�����Ϊ�����ַ�����
	 * @param ignCase       �Ƿ���Դ�Сд��
	 * @return ���ػ�ȡ���ַ��������򷵻ؿ��ַ���""��
	 */
	private static String getStr(String srcStr, String subStr, int existTime,
								 boolean afterOrBefore, boolean ignCase) {
		String retStr = "";
		if (!isEmpty(srcStr) && !isEmpty(subStr) &&
				containNum(srcStr, subStr, ignCase) >= existTime && existTime > 0) {
			int index = findIndex(srcStr, subStr, existTime, ignCase);
			if (afterOrBefore) {
				retStr = srcStr.substring(subStr.length() + index);
			} else {
				retStr = srcStr.substring(0, index);
			}
		}
		return retStr;
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr���Ӵ�subStr��existTime��֮����ֵ��ַ�����
	 * @param srcStr    ԭ�ַ���
	 * @param subStr    �Ӵ�
	 * @param existTime �Ӵ���ԭ�ַ����г��ֵĴ���
	 * @param ignCase   �Ƿ���Դ�Сд��
	 * @return �ɹ��򷵻�ԭ�ַ���srcStr���Ӵ�subStr��existTime֮����ֵ��ַ��������򷵻�""��
	 */
	public static String getStrAfter(String srcStr, String subStr,
									 int existTime, boolean ignCase) {
		return getStr(srcStr, subStr, existTime, true, ignCase);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr���Ӵ�subStr��1��֮����ֵ��ַ�����
	 * @param srcStr  ԭ�ַ���
	 * @param subStr  �Ӵ�
	 * @param ignCase �Ƿ���Դ�Сд��
	 * @return �ɹ��򷵻�ԭ�ַ���srcStr���Ӵ�subStr��1�κ���ֵ��ַ��������򷵻�""��
	 */
	public static String getStrAfter(String srcStr, String subStr,
									 boolean ignCase) {
		return getStr(srcStr, subStr, 1, true, ignCase);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr���Ӵ�subStr��existTime��֮����ֵ��ַ����������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr    ԭ�ַ���
	 * @param subStr    �Ӵ�
	 * @param existTime �Ӵ���ԭ�ַ����г��ֵĴ���
	 * @return �ɹ��򷵻�ԭ�ַ���srcStr���Ӵ�subStr��existTime֮����ֵ��ַ��������򷵻�""��
	 */
	public static String getStrAfter(String srcStr, String subStr,
									 int existTime) {
		return getStr(srcStr, subStr, existTime, true, false);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr���Ӵ�subStr��1��֮����ֵ��ַ����������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr ԭ�ַ���
	 * @param subStr �Ӵ�
	 * @return �ɹ��򷵻�ԭ�ַ���srcStr���Ӵ�subStr��existTime֮����ֵ��ַ��������򷵻�""��
	 */
	public static String getStrAfter(String srcStr, String subStr) {
		return getStr(srcStr, subStr, 1, true, false);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr���Ӵ�subStr��existTime��֮ǰ���ֵ��ַ�����
	 * @param srcStr    ԭ�ַ�����
	 * @param subStr    �Ӵ���
	 * @param existTime �Ӵ���ԭ�ַ����г��ֵĴ�����
	 * @param ignCase   �Ƿ���Դ�Сд��
	 * @return �ɹ��򷵻�ԭ�ַ���srcStr���Ӵ�subStr��existTime��֮ǰ���ֵ��ַ��������򷵻�""��
	 */
	public static String getBeforeStr(String srcStr, String subStr,
									  int existTime, boolean ignCase) {
		return getStr(srcStr, subStr, existTime, false, ignCase);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr���Ӵ�subStr��1��֮ǰ���ֵ��ַ�����
	 * @param srcStr  ԭ�ַ�����
	 * @param subStr  �Ӵ���
	 * @param ignCase �Ƿ���Դ�Сд��
	 * @return �ɹ��򷵻�ԭ�ַ���srcStr���Ӵ�subStr��1��֮ǰ���ֵ��ַ��������򷵻�""��
	 */
	public static String getBeforeStr(String srcStr, String subStr,
									  boolean ignCase) {
		return getStr(srcStr, subStr, 1, false, ignCase);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr���Ӵ�subStr��existTime��֮ǰ���ֵ��ַ����������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr    ԭ�ַ�����
	 * @param subStr    �Ӵ���
	 * @param existTime �Ӵ���ԭ�ַ����г��ֵĴ�����
	 * @return �ɹ��򷵻�ԭ�ַ���srcStr���Ӵ�subStr��existTime��֮ǰ���ֵ��ַ��������򷵻�""��
	 */
	public static String getBeforeStr(String srcStr, String subStr,
									  int existTime) {
		return getStr(srcStr, subStr, existTime, false, false);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr���Ӵ�subStr��1��֮ǰ���ֵ��ַ����������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr ԭ�ַ�����
	 * @param subStr �Ӵ���
	 * @return �ɹ��򷵻�ԭ�ַ���srcStr���Ӵ�subStr��1��֮ǰ���ֵ��ַ��������򷵻�""��
	 */
	public static String getBeforeStr(String srcStr, String subStr) {
		return getStr(srcStr, subStr, 1, false, false);
	}

	/**
	 * ���ܣ�ȡԭ�ַ���srcStr�У���existTime1���Ӵ�subStr1�͵�existTime2���Ӵ�subStr2֮����ַ�����
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr1    ��һ���Ӵ���
	 * @param existTime1 Ҫ���һ���Ӵ����ֵĴ�����
	 * @param subStr2    �ڶ����Ӵ���
	 * @param existTime2 Ҫ��ڶ����Ӵ����ֵĴ�����
	 * @param ignCase    �Ƿ���Դ�Сд
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr1,
									int existTime1, String subStr2,
									int existTime2, boolean ignCase) {
		String retStr = "";
		if (!isEmpty(srcStr) && !isEmpty(subStr1) && !isEmpty(subStr2) &&
				existTime1 > 0 && existTime2 > 0 &&
				containNum(srcStr, subStr1, ignCase) >= existTime1 &&
				containNum(srcStr, subStr2, ignCase) >= existTime2) {
			int length1 = subStr1.length();
			int length2 = subStr2.length();
			int index1 = findIndex(srcStr, subStr1, existTime1, ignCase);
			int index2 = findIndex(srcStr, subStr2, existTime2, ignCase);
			if (index1 + length1 < index2) {
				retStr = srcStr.substring(index1 + length1, index2);
			}
			if (index2 + length2 < index1) {
				retStr = srcStr.substring(index2 + length2, index1);
			}
		}
		return retStr;
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���existTime1���Ӵ�subStr1�͵�existTime2���Ӵ�subStr2֮����ַ����������Դ�Сд��ԭ������
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr1    ��һ���Ӵ���
	 * @param existTime1 Ҫ���һ���Ӵ����ֵĴ�����
	 * @param subStr2    �ڶ����Ӵ���
	 * @param existTime2 Ҫ��ڶ����Ӵ����ֵĴ�����
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr1,
									int existTime1, String subStr2,
									int existTime2) {
		return getBetwStr(srcStr, subStr1, existTime1, subStr2, existTime2, false);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���1���Ӵ�subStr1�͵�existTime2���Ӵ�subStr2֮����ַ�����
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr1    ��һ���Ӵ���
	 * @param subStr2    �ڶ����Ӵ���
	 * @param existTime2 Ҫ��ڶ����Ӵ����ֵĴ�����
	 * @param ignCase    �Ƿ���Դ�Сд
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr1,
									String subStr2, int existTime2,
									boolean ignCase) {
		return getBetwStr(srcStr, subStr1, 1, subStr2, existTime2, ignCase);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���1���Ӵ�subStr1�͵�existTime2���Ӵ�subStr2֮����ַ�����������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr1    ��һ���Ӵ���
	 * @param subStr2    �ڶ����Ӵ���
	 * @param existTime2 Ҫ��ڶ����Ӵ����ֵĴ�����
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */

	public static String getBetwStr(String srcStr, String subStr1,
									String subStr2, int existTime2) {
		return getBetwStr(srcStr, subStr1, 1, subStr2, existTime2, false);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���existTime1���Ӵ�subStr1�͵�1���Ӵ�subStr2֮����ַ�����
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr1    ��һ���Ӵ���
	 * @param existTime1 Ҫ���һ���Ӵ����ֵĴ�����
	 * @param subStr2    �ڶ����Ӵ���
	 * @param ignCase    �Ƿ���Դ�Сд
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr1,
									int existTime1, String subStr2,
									boolean ignCase) {
		return getBetwStr(srcStr, subStr1, existTime1, subStr2, 1, ignCase);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���existTime1���Ӵ�subStr1�͵�1���Ӵ�subStr2֮����ַ����������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr1    ��һ���Ӵ���
	 * @param existTime1 Ҫ���һ���Ӵ����ֵĴ�����
	 * @param subStr2    �ڶ����Ӵ���
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr1,
									int existTime1, String subStr2) {
		return getBetwStr(srcStr, subStr1, existTime1, subStr2, 1, false);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���1���Ӵ�subStr1�͵�1���Ӵ�subStr2֮����ַ�����
	 * @param srcStr  ԭ�ַ�����
	 * @param subStr1 ��һ���Ӵ���
	 * @param subStr2 �ڶ����Ӵ���
	 * @param ignCase �Ƿ���Դ�Сд
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr1,
									String subStr2, boolean ignCase) {
		return getBetwStr(srcStr, subStr1, 1, subStr2, 1, ignCase);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���1���Ӵ�subStr1�͵�1���Ӵ�subStr2֮����ַ����������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr  ԭ�ַ�����
	 * @param subStr1 ��һ���Ӵ���
	 * @param subStr2 �ڶ����Ӵ���
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr1,
									String subStr2) {
		return getBetwStr(srcStr, subStr1, 1, subStr2, 1, false);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���existTime1���Ӵ�subStr�͵�existTime2���Ӵ�subStr֮����ַ�����
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr     �Ӵ���
	 * @param existTime1 Ҫ���һ���Ӵ����ֵĴ�����
	 * @param existTime2 Ҫ��ڶ����Ӵ����ֵĴ�����
	 * @param ignCase    �Ƿ���Դ�Сд
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr,
									int existTime1, int existTime2,
									boolean ignCase) {
		return getBetwStr(srcStr, subStr, existTime1, subStr, existTime2,
				ignCase);
	}

	/**
	 * ���ܣ��������ء�ȡԭ�ַ���srcStr�У���existTime1���Ӵ�subStr�͵�existTime2���Ӵ�subStr֮����ַ����������Դ�Сд��ԭ�����ҡ�
	 * @param srcStr     ԭ�ַ�����
	 * @param subStr     �Ӵ���
	 * @param existTime1 Ҫ���һ���Ӵ����ֵĴ�����
	 * @param existTime2 Ҫ��ڶ����Ӵ����ֵĴ�����
	 * @return �ɹ��򷵻������Ӵ�֮����ַ��������򷵻�""��
	 */
	public static String getBetwStr(String srcStr, String subStr,
									int existTime1, int existTime2) {
		return getBetwStr(srcStr, subStr, existTime1, subStr, existTime2, false);
	}

	/**
	 * ���ܣ�����count��srcStr������������ַ���splitStr���ӣ���splitStr������β��
	 * @param srcStr   ����Ŀ���ַ����������ַ�����
	 * @param splitStr �����ַ���
	 * @param count    ��������
	 * @return ������Ŀ���ַ���
	 */
	public static String getStrByCount(String srcStr, String splitStr,
									   int count) {
		StringBuffer strBuff = new StringBuffer("");
		if (!isEmpty(srcStr) && splitStr != null && count > 0) {
			while (count-- > 0) {
				strBuff.append(srcStr);
				strBuff.append(splitStr);
			}
		}
		String retStr = strBuff.toString();
		if (!isEmpty(retStr)) {
			retStr = retStr.substring(0, retStr.length() - splitStr.length());
		}
		return retStr;
	}

	/**
	 * ���ܣ��������ء�����Ҫ�ָ��ַ�������
	 * @param srcStr String
	 * @param count  int
	 * @return String
	 */
	public static String getStrByCount(String srcStr, int count) {
		return getStrByCount(srcStr, "", count);
	}

	/**
	 * ���ܣ�ISO-8859-1�����GBK����֮���ת��
	 * @param srcStr             ԭ�ַ���
	 * @param toChineseOrUniCode ת�뷽ʽ��true��ʾ���ַ�����ISO-8859-1ת��ΪGBK����֮���෴��
	 * @return ת�����ַ�����
	 */
	private static String UnicodeChineseTransfer(String srcStr,
												 boolean toChineseOrUniCode) {
		String retStr = srcStr;
		try {
			if (srcStr != null && !srcStr.equals("")) {
				retStr = (toChineseOrUniCode) ?
						new String(srcStr.getBytes("ISO-8859-1"), "GBK") :
						new String(srcStr.getBytes("GBK"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			Logger.logError(e);
		}
		return retStr;
	}

	/**
	 * ���ܣ��������ַ�����GBK����ת��ΪISO-8859-1���롣
	 * @param srcStr ԭ�ַ���
	 * @return ת�����ַ���
	 */
	public static String ChineseToUnicode(String srcStr) {
		return UnicodeChineseTransfer(srcStr, false);
	}

	/**
	 * ���ܣ��������ַ�����ISO-8859-1����ת��ΪGBK���롣
	 * @param srcStr ԭ�ַ���
	 * @return ת�����ַ���
	 */
	public static String UnicodeToChinese(String srcStr) {
		return UnicodeChineseTransfer(srcStr, true);
	}

	/**
	 * ���ܣ��ж�ĳһ�ַ���srcStr�Ƿ�����Ӵ�subStr�����Ӵ�subStr�������ڿ�ͷ�ͽ�β��
	 * @param srcStr String
	 * @param subStr String
	 * @return boolean
	 */
	private static boolean isMiddleWith(String srcStr, String subStr) {
		boolean retVal = false;
		if (srcStr != null && subStr != null && !srcStr.equals("") &&
				!subStr.equals("")) {
			retVal = (contains(srcStr, subStr) && !srcStr.startsWith(subStr) &&
					!srcStr.endsWith(subStr));
		}
		return retVal;
	}

	/**
	 * ���ܣ��ж�ĳ�ַ����Ƿ�����һ�ַ�����ͷ�����Դ�Сд��
	 * @param srcStr String
	 * @param subStr String
	 * @return boolean
	 */
	public static boolean startsWith(String srcStr, String subStr) {
		boolean retVal = false;
		if (srcStr != null && !isEmpty(subStr)) {
			srcStr = srcStr.toLowerCase();
			subStr = subStr.toLowerCase();
			retVal = srcStr.startsWith(subStr);
		}
		return retVal;
	}

	/**
	 * ���ܣ��ж�ĳ�ַ����Ƿ�����һ�ַ�����β�����Դ�Сд��
	 * @param srcStr String
	 * @param subStr String
	 * @return boolean
	 * @throws StrException
	 */
	public static boolean endsWith(String srcStr, String subStr) throws
			StrException {
		StrValidator.validateEmpty(srcStr);
		StrValidator.validateEmpty(subStr);
		srcStr = srcStr.toLowerCase();
		subStr = subStr.toLowerCase();
		return srcStr.endsWith(subStr);
	}

	/**
	 * ���ܣ��ж��ַ���srcStr�Ƿ�Ϊ���֣������ٷ������жϡ����ڷ�����ʾ���ڸ��ӣ��ڴ˲����жϣ�����Ϊ�䲻�����ӡ�
	 * @param srcStr ԭ�ַ�����
	 * @return �������򷵻�true�����򷵻�false��
	 */
	public static boolean isNumber(String srcStr) {
		boolean retVal = false;
		String checkStr = "0123456789+-%.";
		if (srcStr != null && !srcStr.equals("") &&
				containNum(srcStr, "+") <= 1 && containNum(srcStr, "-") <= 1 &&
				containNum(srcStr, "%") <= 1 && containNum(srcStr, ".") <= 1) {
			String tempStr = "";
			int containNum = 0;
			for (int i = 0; i < srcStr.length(); i++) {
				tempStr = srcStr.substring(i, i + 1);
				if (contains(checkStr, tempStr)) {
					containNum++;
				}
			}
			if (containNum == srcStr.length()) {
				tempStr = srcStr;
				if (contains(srcStr, "+") && srcStr.startsWith("+")) {
					tempStr = srcStr.substring(1);
				}
				if (contains(srcStr, "-") && srcStr.startsWith("-")) {
					tempStr = srcStr.substring(1);
				}
				if (contains(tempStr, "%") && tempStr.endsWith("%")) {
					tempStr = tempStr.substring(0, tempStr.length() - 1);
				}
				if (contains(tempStr, ".") && isMiddleWith(tempStr, ".")) {
					tempStr = tempStr.substring(0, tempStr.indexOf(".")) +
							tempStr.substring(tempStr.indexOf(".") + 1);
				}
				retVal = isInteger(tempStr);
			}

		}
		return retVal;
	}

	/**
	 * ���ܣ��ж�ĳһ�ַ����Ƿ���������
	 * @param srcStr �ַ���
	 * @return ���������򷵻�true�����򷵻�false��
	 */
	public static boolean isInteger(String srcStr) {
		boolean retVal = true;
		if (!isEmpty(srcStr)) {
			String numStr = "0123456789";
			String tempStr = "";
			for (int i = 0; i < srcStr.length(); i++) {
				tempStr = srcStr.substring(i, i + 1);
				if (numStr.indexOf(tempStr) == -1) {
					retVal = false;
					break;
				}
			}
		} else {
			retVal = false;
		}
		return retVal;
	}

	/**
	 * ���ܣ���ʽ���ַ��������ַ�����ǰ���ǰ���ַ�toChar�����Ӻ�׺�ַ�toChar���Բ�������Ҫ��ĳ��ȡ�
	 * ������Ҫ����ַ���������ͬ���ȵĳ��ϡ�
	 * @param srcStr    ����ʽ�����ַ�����
	 * @param objLength Ŀ�곤�ȣ�
	 * @param toChar    ����ӵ��ַ���
	 * @param preOrPost ǰ�����Ǻ󵼣�true��ʾ��srcStrǰ�����toChar�ַ�����֮���ں���
	 * @return ���ظ�ʽ������ַ���������ԭ�����ء�
	 */
	public static String formatStr(String srcStr, int objLength, char toChar,
								   boolean preOrPost) {
		srcStr = (srcStr == null) ? "" : srcStr;
		int count = objLength - srcStr.length();
		char[] tempArr = {toChar};
		if (count > 0) {
			if (preOrPost) {
				srcStr = getStrByCount(new String(tempArr), count) + srcStr;
			} else {
				srcStr = srcStr + getStrByCount(new String(tempArr), count);
			}
		}
		return srcStr;
	}

	/**
	 * ���ܣ��������ء�ǰ���ַ����׺�ַ�Ϊ0��������Ҫ
	 * ����ֱ�ž�����ͬ���ȵĳ��ϣ��������ݿ��е���ˮ�š�
	 * @param srcStr       String
	 * @param objectLength int
	 * @param preOrPost    boolean
	 * @return String ����ֵ �ɹ��򷵻ظ�ʽ������ַ��������򷵻�null��
	 */
	public static String formatStr(String srcStr, int objectLength,
								   boolean preOrPost) {
		return formatStr(srcStr, objectLength, '0', preOrPost);
	}

	/**
	 * ���ܣ��������ء����ǰ���ַ������ӡ�����ֵ �ɹ���Ϊ��ʽ������ַ��������򷵻�null��
	 * @param srcStr       String
	 * @param objectLength int
	 * @param toChar       char
	 * @return String
	 */
	public static String formatStr(String srcStr, int objectLength, char toChar) {
		return formatStr(srcStr, objectLength, toChar, true);
	}

	/**
	 * ���ܣ��������ء����ǰ��0�����ӡ�����ֵ �ɹ���Ϊ��ʽ������ַ��������򷵻�null��
	 * @param srcStr       String
	 * @param objectLength int
	 * @return String
	 */
	public static String formatStr(String srcStr, int objectLength) {
		return formatStr(srcStr, objectLength, '0', true);
	}

	/**
	 * ����:��������ҳ���ύ�����ݺ���JSP�����������������ݿ��ж���ʱ������������н���
	 * @param srcStr String
	 * @return String
	 */
	public static String htmlStrEncode(String srcStr) {
		StringBuffer strBuffer = new StringBuffer();
		if (srcStr == null) {
			return srcStr;
		}
		int j = srcStr.length();
		for (int i = 0; i < j; i++) {
			char c = srcStr.charAt(i);
			switch (c) {
				case 60:
					strBuffer.append("&lt;");
					break;
				case 62:
					strBuffer.append("&gt;");
					break;
				case 38:
					strBuffer.append("&amp;");
					break;
				case 34:
					strBuffer.append("&quot;");
					break;
				case 169:
					strBuffer.append("&copy;");
					break;
				case 174:
					strBuffer.append("&reg;");
					break;
				case 165:
					strBuffer.append("&yen;");
					break;
				case 8364:
					strBuffer.append("&euro;");
					break;
				case 8482:
					strBuffer.append("&#153;");
					break;
				case 13:
					if (i < j - 1 && srcStr.charAt(i + 1) == 10) {
						strBuffer.append("<br>");
						i++;
					}
					break;
				case 32:
					if (i < j - 1 && srcStr.charAt(i + 1) == ' ') {
						strBuffer.append(" &nbsp;");
						i++;
						break;
					}
				default:
					strBuffer.append(c);
					break;
			}
		}
		return new String(strBuffer.toString());
	}

	/**
	 * ���ܣ����ַ����н�������ȡ����
	 * @param srcStr String
	 * @return String
	 */
	public static String getNumFromText(String srcStr) {
		String retStr = "";
		if (!isEmpty(srcStr)) {
			int strLength = srcStr.length();
			String currChar = "";
			for (int i = 0; i < strLength; i++) {
				currChar = srcStr.substring(i, i + 1);
				if (isInteger(currChar)) {
					retStr += currChar;
				}
			}
		}
		return retStr;
	}

	/**
	 * ���ܣ���ȡ�ַ���ȥ�����ֺ���ַ���
	 * @param srcStr String
	 * @return String
	 */
	public static String trimNum(String srcStr) {
		String retStr = "";
		if (!isEmpty(srcStr)) {
			int strLength = srcStr.length();
			String currChar = "";
			for (int i = 0; i < strLength; i++) {
				currChar = srcStr.substring(i, i + 1);
				if (!isInteger(currChar)) {
					retStr += currChar;
				}
			}
		}
		return retStr;
	}

	/**
	 * ���ܣ��ж�ָ�������Ƿ�Ϊ�ա�
	 * @param obj Object
	 * @return boolean
	 */
	public static boolean isEmpty(Object obj) {
		return (obj == null || obj.toString().equals(""));
	}

	/**
	 * ���ܣ��ж�ָ�������Ƿ�Ϊ��
	 * @param obj Object
	 * @return boolean
	 */
	public static boolean  isNotEmpty(Object obj){
		return !isEmpty(obj);
	}

	/**
	 * ���ܣ���srcStr��ǰ����subStrȥ������
	 * @param srcStr     String
	 * @param subStr     String
	 * @param startOrEnd boolean true��ʾȥ��ǰ�沿�֣�false��ʾȥ�����沿�֡�
	 * @return String
	 */
	public static String trim(String srcStr, String subStr, boolean startOrEnd) {
		if (!isEmpty(subStr)) {
			if (startOrEnd) {
				while (srcStr.startsWith(subStr)) {
					srcStr = srcStr.substring(subStr.length());
				}
			} else {
				while (srcStr.endsWith(subStr)) {
					srcStr = srcStr.substring(0, srcStr.length() - subStr.length());
				}
			}
		}
		return srcStr;
	}

	/**
	 * ���ܣ���srcStr��ǰ���subStrȥ������
	 * @param srcStr String
	 * @param subStr String
	 * @return String
	 */
	public static String trim(String srcStr, String subStr) {
		srcStr = trim(srcStr, subStr, false);
		return trim(srcStr, subStr, true);
	}

	/**
	 * ���ܣ���srcStr��ǰ��Ŀո�ȥ������
	 * @param srcStr String
	 * @return String
	 */
	public static String trim(String srcStr) {
		return trim(srcStr, WorldConstant.EMPTY_SPACE);
	}

	/**
	 * ���ܣ����б�����ת��Ϊ�ַ�����
	 * @param list List
	 * @return String
	 */
	public static String list2String(List list) {
		StringBuffer str = new StringBuffer();
		String strVal = "";
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				strVal = list.get(i).toString().trim();
			}
			if (str.length() == 0) {
				str.append("'" + strVal + "'");
			} else {
				str.append(",'" + strVal + "'");
			}
			strVal = "";
		}
		return str.toString();
	}

	/**
	 * ���ܣ������ݿ��ֶ���ת��Ϊ���Ϲ����Java�ֶ���������������ʽ����JavaBean����
	 * @param dbField String
	 * @return String
	 */
	public static String getJavaField(String dbField) {
		String retStr = dbField;
		if (!isEmpty(dbField) && isAllUpperCase(dbField)) {
			StringBuffer tmpBuff = new StringBuffer();
			String[] splitArr = splitStr(dbField, "_");
			String arrEle = "";
			for (int i = 0; i < splitArr.length; i++) {
				arrEle = splitArr[i];
				if (i > 0) {
					tmpBuff.append(arrEle.substring(0, 1).toUpperCase());
					tmpBuff.append(arrEle.substring(1).toLowerCase());
				} else {
					tmpBuff.append(arrEle.toLowerCase());
				}
			}
			retStr = tmpBuff.toString();
		}
		return retStr;
	}

	/**
	 * ���ܣ���webҳ��ı��ֶ���ת��Ϊ���Ϲ�������ݿ��ֶ��������Դ�д��ʽ���ء�
	 * @param javaField String
	 * @return String
	 */
	public static String getDbField(String javaField) {
		String retStr = "";
		if (!isEmpty(javaField) && !isAllUpperCase(javaField)) {
			int strLength = javaField.length();
			char thisChar = 1;
			for (int i = 0; i < strLength; i++) {
				thisChar = javaField.charAt(i);
				if (thisChar >= 97 && thisChar <= 122) {
					retStr += (char) (thisChar - 32);
				} else if (thisChar >= 65 && thisChar <= 90) { //��д,����ǰ���һ��"_"
					if (i == 0) {
						retStr += thisChar;
					} else {
						retStr += "_" + thisChar;
					}
				} else {
					retStr += thisChar;
				}
			}
		} else {
			retStr = javaField;
		}
		return retStr;
	}

	/**
	 * ���ܣ���ȡ�����е���srcStr��Ԫ�ء�
	 * @param srcStr String
	 * @param strArr String[]
	 * @param igCase boolean
	 * @return String ���ض�ӦԪ��ֵ����ַ�����
	 */
	public static String getStrInArr(String srcStr, String[] strArr,
									 boolean igCase) {
		String retStr = "";
		if (!isEmpty(srcStr) && strArr != null && strArr.length > 0) {
			for (int i = 0; i < strArr.length; i++) {
				if (contains(srcStr, strArr[i], igCase)) {
					retStr = strArr[i];
					break;
				}
			}
		}
		return retStr;
	}

	/**
	 * ���ܣ��������ء���ȡ�����е���srcStr��Ԫ�ء�
	 * @param srcStr String
	 * @param strArr String[]
	 * @return String
	 */
	public static String getStrInArr(String srcStr, String[] strArr) {
		return getStrInArr(srcStr, strArr, false);
	}

	/**
	 * ���ܣ�ת���ַ���Ϊ����xmlҪ����ַ���
	 * @param strValue String
	 * @return String
	 */
	public static String formatXML(String strValue) {
		strValue = replaceStr(strValue, "&", "&amp;");
		strValue = replaceStr(strValue, "<", "&lt;");
		strValue = replaceStr(strValue, ">", "&gt;");
		strValue = replaceStr(strValue, "\"", "&quot;");
		strValue = replaceStr(strValue, "'", "&apos;");
		return strValue;
	}

	public static String nullToString(Object val) {
		if (val == null) {
			val = "";
		}
		return val.toString();
	}

	/**
	 * ���ܣ�����̨�����鸳��ǰ̨��JavaScript���飬JavaScript������ΪjsArrName��
	 * @param srcVal    String[]
	 * @param jsArrName String
	 * @return String
	 */
	public static String dataToJsArr(String[] srcVal, String jsArrName) {
		String retStr = "";
		if (srcVal != null && srcVal.length > 0) {
			retStr = "var " + jsArrName + " = new Array(";
			int arrLength = srcVal.length;
			for (int i = 0; i < arrLength; i++) {
				retStr += "'" + srcVal[i] + "', ";
			}
			retStr = retStr.substring(0, retStr.length() - 2);
			retStr += ");\n";
		}
		return retStr;
	}

	public static String dataToJsArr(String[] srcVal, String jsArrName,
									 String defaultValue) {
		for (int i = 0; i < srcVal.length; i++) {
			srcVal[i] = defaultValue;
		}
		return dataToJsArr(srcVal, jsArrName);
	}

	/**
	 * ���ܣ�����̨�����鸳��ǰ̨��JavaScript���飬JavaScript������ΪjsArrName��
	 * @param srcVal    String[][]
	 * @param jsArrName String
	 * @return String
	 */
	public static String dataToJsArr(String[][] srcVal, String jsArrName) {
		String retStr = "";
		if (srcVal != null && srcVal.length > 0) {
			retStr = "var " + jsArrName + " = new Array()\n";
			int arrLength = srcVal.length;
			for (int i = 0; i < arrLength; i++) {
				retStr += jsArrName + "[" + i + "] = new Array(";
				for (int j = 0; j < srcVal[i].length; j++) {
					retStr += "\"" + srcVal[i][j] + "\", ";
				}
				retStr = retStr.substring(0, retStr.length() - 2);
				retStr += ");\n";
			}
		}
		return retStr;
	}

	/**
	 * ���ܣ������ڵ��º����Զ�����
	 * @param srcStr ԭ�����ַ���
	 * @return ����֮�������
	 */
	public static String formatDate(String srcStr) {
		String[] strArr = splitStr(srcStr, "-");
		if (strArr[1] != null && strArr[1].length() == 1) {
			strArr[1] = "0" + strArr[1];
		}
		if (strArr[2] != null && strArr[2].length() == 1) {
			strArr[2] = "0" + strArr[2];
		}
		return strArr[0] + "-" + strArr[1] + "-" + strArr[2];
	}

	/**
	 * ���� ��������
	 * @param srcStr ����Ľ���ַ���
	 * @return �����Ľ��
	 */
	public static String getFormatCurrence(String srcStr) {
		if (srcStr.equals("")) {
			srcStr = "0";
		}
		String pattern = "###0.00";
		double src = Double.parseDouble(srcStr);
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(src);
	}

	/**
	 * ���� ��������
	 * @param srcStr ����Ľ��
	 * @return �����Ľ��
	 */
	public static String getFormatCurrence(double srcStr) {
		String pattern = "###0.00";
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(srcStr);
	}

	/**
	 * ���ܣ��ж�ĳ�ַ����Ƿ���������ĳһԪ�ؽ�����
	 * @param srcStr String
	 * @param strArr String[]
	 * @return boolean
	 */
	public static boolean endsWith(String srcStr, String[] strArr) {
		boolean endsWith = false;
		try {
			if (!isEmpty(srcStr) && strArr != null && strArr.length > 0) {
				for (int i = 0; i < strArr.length; i++) {
					if (isEmpty(strArr[i])) {
						continue;
					}
					if (endsWith(srcStr, strArr[i])) {
						endsWith = true;
						break;
					}
				}
			}
		} catch (StrException ex) {
			ex.printLog();
		}
		return endsWith;
	}

	/**
	 * ���ܣ��������ַ�������ĸ��д�󷵻ء�
	 * @param passValue String
	 * @return String
	 */
	public static String upperCapital(String passValue) {
		String strVal = "";
		if (!isEmpty(passValue)) {
			strVal = passValue.substring(0, 1).toUpperCase();
			strVal += passValue.substring(1);
		}
		return strVal;
	}

	/**
	 * ���ܣ��������ַ�������ĸСд�󷵻ء�
	 * @param passValue String
	 * @return String
	 */
	public static String lowerCapital(String passValue) {
		String strVal = "";
		if (!isEmpty(passValue)) {
			strVal = passValue.substring(0, 1).toLowerCase();
			strVal += passValue.substring(1);
		}
		return strVal;
	}

	/**
	 * ���ܣ���CSV����ʾ�ַ���ת��ΪCSV�洢��ʽ���ַ�����
	 * @param csvStr String
	 * @return String
	 */
	public static String encodeCSVStr(String csvStr) {
		String retStr = csvStr;
		boolean hasProcessed = false;
		if (contains(retStr, WorldConstant.QUOTE_MARK)) { //����������ţ�����ת�崦����ǰ���Ȳ����ӷֺš�
			retStr = replaceStr(retStr, WorldConstant.QUOTE_MARK, WorldConstant.DOUBLE_QUOTE_MARK);
			hasProcessed = true;
		}
		if (contains(retStr, WorldConstant.COMMA_CHAR) || hasProcessed) { //ǰ�����˫���š�
			retStr = WorldConstant.QUOTE_MARK + retStr + WorldConstant.QUOTE_MARK;
		}
		return retStr;
	}

	/**
	 * ���ܣ���CSV�洢���ַ�������ת��Ϊ��ʾ�ַ�����
	 * @param csvStr String
	 * @return String
	 * @throws StrException
	 */
	public static String decodeCSVStr(String csvStr) throws StrException {
		StringBuffer strB = new StringBuffer();
		if (!isEmpty(csvStr)) {
			int quoteCount = containNum(csvStr, WorldConstant.QUOTE_MARK);
			if (quoteCount % 2 != 0) {
				throw new StrException("������ַ�����" + csvStr + "�����ǺϷ���CSV�洢��ʽ��");
//				csvStr = replaceFirst(csvStr, WorldConstant.QUOTE_MARK, "");
			}
			int startIndex = csvStr.indexOf(WorldConstant.QUOTE_MARK);
			int lastIndex = csvStr.lastIndexOf(WorldConstant.QUOTE_MARK);
			if (startIndex == 0 && lastIndex == csvStr.length() - 1) {
				csvStr = csvStr.substring(1, lastIndex);
			}
			int strLength = csvStr.length();
			StringBuffer quoteBuf = new StringBuffer();
			String quoteStr = "";
			String thisChar = "";
			for (int i = 0; i < strLength; i++) {
				thisChar = csvStr.substring(i, i + 1);
				if (!thisChar.equals(WorldConstant.QUOTE_MARK)) {
					quoteStr = quoteBuf.toString();
					quoteStr = quoteStr.substring(0, quoteStr.length() / 2);
					strB.append(quoteStr);
					quoteBuf = new StringBuffer();
				}
				if (thisChar.equals(WorldConstant.QUOTE_MARK)) {
					quoteBuf.append(thisChar);
				} else {
					strB.append(thisChar);
				}
			}
			if (quoteBuf.length() > 0) {
				quoteStr = quoteBuf.toString();
				quoteStr = quoteStr.substring(0, quoteStr.length() / 2);
				strB.append(quoteStr);
			}
		}
		return strB.toString();
	}

	/**
	 * ���ܣ���CSV��һ�д洢�ַ�����������ʾ���ַ����б�
	 * @param csvStr String
	 * @return List
	 * @throws StrException
	 */
	public static List decodeCSVRow(String csvStr) throws StrException {
		List csvList = new ArrayList();
		if (!isEmpty(csvStr)) {
			int strLength = csvStr.length();
			String cellStr = "";
			String thisChar = "";
			int quoteNumber = 0;
			for (int i = 0; i < strLength; i++) {
				thisChar = csvStr.substring(i, i + 1);
				if (thisChar.equals(WorldConstant.COMMA_CHAR)) {
					quoteNumber = containNum(cellStr, WorldConstant.QUOTE_MARK);
					if (quoteNumber % 2 == 0) {
						csvList.add(decodeCSVStr(cellStr));
						cellStr = "";
					} else {
						cellStr += thisChar;
					}
				} else {
					cellStr += thisChar;
				}
			}
			if (cellStr.length() != 0) {
				csvList.add(decodeCSVStr(cellStr));
			}
		}
		return csvList;
	}

	/**
	 * ���ܣ�����ѧ��������ʾ������ת��Ϊ�ַ���ʵ��
	 * @param scienceNum String
	 * @return String
	 */
	public static String formatScienceNum(String scienceNum) {
		String retValue = scienceNum;
		if (!isEmpty(scienceNum)) {
			int index = scienceNum.indexOf("E");
			if (index > -1 && index != scienceNum.length() - 1) {
				String strHeader = scienceNum.substring(0, index);
				String strTail = scienceNum.substring(index + 1);
				if (isNumber(strHeader) && isNumber(strTail)) {
					double matter = Double.parseDouble(strHeader); //����
					double exponent = Double.parseDouble(strTail); //ָ��
					BigDecimal bigNum = new BigDecimal(matter * Math.pow(10, exponent));
					retValue = bigNum.toString();
				}
			}
		}
		return retValue;
	}

	/**
	 * ���ܣ��ж�ĳ�ַ����Ƿ�ȫ�Ǵ�д��ĸ��
	 * @param strValue String
	 * @return boolean
	 */
	public static boolean isAllUpperCase(String strValue) {
		boolean isAllUpperCase = true;
		if (!isEmpty(strValue)) {
			int strLength = strValue.length();
			char singleChar = (char) -1;
			for (int i = 0; i < strLength; i++) {
				singleChar = strValue.charAt(i);
				if (singleChar == '_') {
					continue;
				}
				if (singleChar == '-') {
					continue;
				}
				if (singleChar > -1 && singleChar < 10) {
					continue;
				}
				if (singleChar >= 48 && singleChar <= 57) { //0~9
					continue;
				}
				if (singleChar > 90 || singleChar < 65) { //65:A  , 90:Z
					isAllUpperCase = false;
					break;
				}
			}
		}
		return isAllUpperCase;
	}

	/**
	 * ���ܣ��ж�ĳ�ַ����Ƿ�ȫ�Ǵ�д��ĸ��
	 * @param strValue String
	 * @return boolean
	 */
	public static boolean isAllLowerCase(String strValue) {
		boolean isAllLowerCase = true;
		if (!isEmpty(strValue)) {
			int strLength = strValue.length();
			char singleChar = (char) -1;
			for (int i = 0; i < strLength; i++) {
				singleChar = strValue.charAt(i);
				if (singleChar == '_') {
					continue;
				}
				if (singleChar == '-') {
					continue;
				}
				if (singleChar > -1 && singleChar < 10) {
					continue;
				}
				if (singleChar > 122 || singleChar < 97) {
					isAllLowerCase = false;
					break;
				}
			}
		}
		return isAllLowerCase;
	}

	/**
	 * ���ܣ��жϴ����ַ����Ƿ�ʮ�����Ʊ�ʾ��RGB��ɫ������
	 * @param color String
	 * @return boolean
	 */
	public static boolean isColorStr(String color) {
		boolean isColor = false;
		if (!isEmpty(color)) {
			color = color.toLowerCase();
			if (color.startsWith("#") && containNum(color, "#") == 1) {
				color = color.substring(1);
				String standardStr = "0123456789abcdef";
				isColor = true;
				String singleChar = "";
				for (int i = 0; i < color.length(); i++) {
					singleChar = color.substring(i, i + 1);
					if (standardStr.indexOf(singleChar) == -1) {
						isColor = false;
						break;
					}
				}
			}
		}
		return isColor;
	}

	public static String xmlFormat(String str) {
		String m = str;
		//& < > " '����Ҫ�ֱ�ת���� &amp; &lt; &gt; &quot; &apos;
		m = replaceStr(m, "&", "&amp;");
		m = replaceStr(m, "<", "&lt;");
		m = replaceStr(m, ">", "&gt;");
		m = replaceStr(m, "\"", "&quot;");
		m = replaceStr(m, "'", "&apos;");
		return m;
	}

	public static void main(String[] args){
		String a = "userAge;;userName;;userPwd;;;;";//null ,gf null
		String b = ";;";
		StrUtilHave.splitStr(a, b);
	}
}

