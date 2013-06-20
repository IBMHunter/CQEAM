/**
 * @author mshtang
 */

//===============================================================================================
//�������֣����´������ڲ���
//===============================================================================================

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�8λ���ֱ�׼����
 * @param {Object} srcStr Դ�ַ���
 */
function isStandardDate(srcStr) {
    var retVal = false;
    if (srcStr.length == 8 && isInt(srcStr)) {
        var year = srcStr.substring(0, 4);
        var month = srcStr.substring(4, 6);
        var date = srcStr.substring(6, 8);
        var maxDate = 31;
        if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                maxDate = 29;
            } else {
                maxDate = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDate = 30;
        }
        if (month > 0 && month < 13 && date > 0 && date <= maxDate) {
            retVal = true;
        }
    }
    return retVal;
}

/**
 * ���ܣ��������л�ø�ʽ
 * @param {Object} strDate �ַ�������
 */
function getDatePattern(strDate) {
    var retPattern = "";
    var numberDate = getNumFromText(strDate);
    if (isStandardDate(numberDate)) {
        var year = numberDate.substring(0, 4);
        var month = numberDate.substring(4, 6);
        var date = numberDate.substring(6);
        retPattern = replaceStr(strDate, year, "YYYY");
        retPattern = replaceFirst(retPattern, month, "MM");
        retPattern = replaceStr(retPattern, date, "DD");
    }
    return retPattern;
}

/**
 * ���ܣ��ж����ڸ�ʽ�Ƿ�Ϸ�
 * @param {Object} datePattern ���ڸ�ʽ
 */
function isValidDatePattern(datePattern) {
    return isExistInArr(LIMIT_DATE_PATTERN, datePattern, true);
}

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ����ڱ�ϵͳ�Ͽɵĸ�ʽ������
 * @param {Object} strDate �ַ�������
 */
function isValidDate(strDate) {
    var tempDate = getNumFromText(strDate);
    var datePattern = getDatePattern(strDate);
    return (isStandardDate(tempDate) && isValidDatePattern(datePattern));
}

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ����ڱ�ϵͳ�Ͽɵĸ�ʽ������,�����ʽΪָ���ĸ�ʽ
 * @param {Object} strDate �ַ�������
 * @param {Object} datePattern ָ�������ڸ�ʽ
 */
function isFormatDate(strDate, datePattern) {
    var retVal = false;
    strDate = unescape(strDate);
    var tempDate = getNumFromText(strDate);
    var tempPattern = getDatePattern(strDate);
    retVal = isStandardDate(tempDate);
    if (retVal) {
        retVal = isValidDatePattern(datePattern);
        if (retVal) {
            retVal = (tempPattern == datePattern);
        }
    }
    return retVal;
}

/**
 * ���ܣ���ԭ����ת��Ϊ8λ���ֱ�׼��ʽ����
 * @param {Object} formatDate ���ϱ�ϵͳ�涨�������ʽ�����ַ���
 */
function getStandardDate(formatDate) {
    var retStr = getNumFromText(formatDate);
    if (!isStandardDate(retStr)) {
        retStr = "";
    }
    return retStr;
}

/**
 * ���ܣ���8λ���������л�ȡ�������
 * @param {Object} strDate �ַ�������
 */
function getYearStr(strDate) {
    return strDate.substring(0, 4);
}

/**
 * ���ܣ���8λ���������л�ȡ�·�����
 * @param {Object} strDate �ַ�������
 */
function getMonthStr(strDate) {
    return strDate.substring(4, 6);
}

/**
 * ���ܣ���8λ���������л�ȡ��������
 * @param {Object} strDate �ַ�������
 */
function getDateStr(strDate) {
    return strDate.substring(6);
}

/**
 * ���ܣ���ԭ����ת����Ŀ���ʽ����
 * @param {Object} strDate �ַ�������
 * @param {Object} datePattern ���ڸ�ʽ
 */
function getFormatDate(strDate, datePattern) {
    var retDate = getStandardDate(strDate);
	datePattern = datePattern.toUpperCase();
    if (isStandardDate(retDate) && isValidDatePattern(datePattern)) {
        var year = getYearStr(retDate);
        var month = getMonthStr(retDate);
        var date = retDate.substring(6);
        retDate = replaceStr(datePattern, "YYYY", year);
        retDate = replaceStr(retDate, "MM", month);
        retDate = replaceStr(retDate, "DD", date);
    }
    return retDate;
}

/**
 * ���ܣ������������֮�������
 * @param {Object} dateStr1 �����ַ���1
 * @param {Object} dateStr2 �����ַ���2
 */
function getInterValDays(dateStr1, dateStr2) {
    var interDays = 0;
    dateStr1 = getStandardDate(dateStr1);
    dateStr2 = getStandardDate(dateStr2);
    if ((isStandardDate(dateStr1) && isStandardDate(dateStr2))) {
        var startDate = new Date(getYearStr(dateStr1), getMonthStr(dateStr1) - 1, getDateStr(dateStr1));
        var endDate = new Date(getYearStr(dateStr2), getMonthStr(dateStr2) - 1, getDateStr(dateStr2));
        interDays = Math.abs(startDate.getTime() - endDate.getTime());
        interDays = Math.floor(daysBetween / (1000 * 60 * 60 * 24));
    }
    return interDays;
}

/**
 * ���ܣ���������
 * @param {Object} dateStr �����ַ���
 * @param {Object} datePattern Ŀ�����ڸ�ʽ
 * @param {Object} adjustDays ��������
 */
function adjustDate(dateStr, datePattern, adjustDays) {
    dateStr = getStandardDate(dateStr);
    if (isStandardDate(dateStr) && isInt(adjustDays)) {
        var date = new Date(getYearStr(dateStr), getMonthStr(dateStr) - 1, getDateStr(dateStr));
        adjustDays *= (24 * 60 * 60 * 1000);
        date = new Date(date.getTime() + adjustDays * 1);
        var tempMonth = date.getMonth() + 1;
        tempMonth = (tempMonth < 10) ? "0" + tempMonth : tempMonth;
        var tempDate = date.getDate();
        tempDate = (tempDate < 10) ? "0" + tempDate : tempDate;
        dateStr = date.getFullYear() + tempMonth + tempDate;
        if (isValidDatePattern(datePattern)) {
            dateStr = getFormatDate(dateStr, datePattern);
        }
    }
    return dateStr;
}

/**
 * ���ܣ���ָ����ʽ���ؽ�������
 * @param {Object} datePattern ָ�������ڸ�ʽ
 */
function getToday(datePattern) {
	var today = "";
    if (isValidDatePattern(datePattern)) {
	    var date = new Date();
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1;
	    var day = date.getDate();
	    if (month < 10) {
	        month = "0" + month;
	    }
	    if (day < 10) {
	        day = "0" + day;
	    }
	    today = String(year) + String(month) + String(day);
		today = getFormatDate(today, datePattern);
    }
    return today;
}

/**
 * ���ܣ�����ĳ��ĳ�µ�����
 * @param {Object} year ���
 * @param {Object} month �·�
 */
function getDaysInMonth(year, month) {
    var days = 31;
    year = Number(year);
    month = Number(month);
    if (month < 1 || month > 12) {
        days = -1;
    }
    if (month == 4 || month == 6 || month == 9 || month == 11) {
        days = 30;
    }
    if (month == 2) {
        days = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) ? 29 : 28;
    }
    return days;
}
