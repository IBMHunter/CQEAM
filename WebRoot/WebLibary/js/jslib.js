/**
 * ��ע�⡿���ۺ��˽϶������У���Javascript�����⣬����ʱ��Ĺ�ϵ��Ŀǰ��Javascript������
 * ��δ�������������Ĳ��ԣ�һ�������ڽ϶಻�㣬ϣ����Ҷ��ʹ�ã��Է�����ȱ�����ڡ�����ʾ��
 * ���ܻ�û�У���ÿ�������ľ�����;�Ѿ�д�ý�Ϊ������������ʲô�õ��뷨��Ҳ�ɼ������У���
 * ��ͨ��mshtang@163.com����������ʤ��ϵ��
 * �ڱ������⾭�����Գɹ�֮���ڽ�����Ŀ���߲�Ʒ�����о�Ӧ���̶ȵ�ʹ�������Խ�ʡ���ǵ�
 * ����ʱ�䣬��������Ӧ�еĹ���ȡ�
 * ��������Ŀǰ��Ϊ�߸����֣�������򵥽��ܡ�������Ҫ�˽�ĳһ���ֵ���Ӧ��������ͨ�����������֡�
 * ����������λ����Ӧ��λ�ã���ȥ����鷳��
 * ��һ���֣���������
 * �ڶ����֣��ַ������������
 * �������֣����ڲ���
 * ���Ĳ��֣����������
 * ���岿�֣���ѡ�����
 * �������֣���ѡ�����
 * ���߲��֣���У��(�ò�������Ҫ���ƣ�Ŀǰ����˼·�����Ǻ�ȫ)
 * �ڰ˲��֣�Ϊ����ֵ

 * �ھŲ��֣�Ϊ����ֵ
 * CopyRight���Ϻ����ع�˾��Ȩ����2005~
 */


//===============================================================================================
//��һ���֣����¶��屾�������õ��ĳ���
//===============================================================================================

/**
 * 1.���屾Javascript���������ͨ����
 */
var EMPTY_SPACE = " ";
//�ո��ַ���
var NUM_STR = "0123456789";
//�����ַ���;

/**
 * 2.���屾Javascript���������ܽ��ܵ����ڸ�ʽ����
 */

var DATE_STANDARD_PATTERN = "YYYYMMDD";
var DATE_LINE_PATTERN = "YYYY-MM-DD";
var DATE_SLOPE_PATTERN = "YYYY/MM/DD";
var DATE_DOT_PATTERN = "YYYY.MM.DD";
var DATE_CHINESE_PATTERN = "YYYY��MM��DD��";
var LIMIT_DATE_PATTERN = new Array(DATE_STANDARD_PATTERN, DATE_LINE_PATTERN, DATE_SLOPE_PATTERN, DATE_DOT_PATTERN, DATE_CHINESE_PATTERN);

/**
 * 3.���屾Javascript����������֧�ֵ���ͨ��׼У�鷽ʽ���Լ���׼��ʾ��Ϣ
 */

var EMPTY_VALIDATE = "isEmpty";
var DATE_VALIDATE = "isFormatDate";
var EMAIL_VALIDATE = "isEmail";
var NUMBER_VALIDATE = "isNumber";
var INT_VALIDATE = "isInt";
var DOUBLE_VALIDATE = "isDouble";
var LENGTH_VALIDATE = "isLengthValid";
var POSITIVE_VALIDATE = "isPositiveNumber";
var POSITIVE_INT_VALIDATE = "isPositiveInteger";
var DISCOUNT_VALIDATE = "isDiscount";
var VALIDATE_TYPE_ARR = new Array(EMPTY_VALIDATE, DATE_VALIDATE, EMAIL_VALIDATE, NUMBER_VALIDATE, INT_VALIDATE, DOUBLE_VALIDATE, LENGTH_VALIDATE, POSITIVE_VALIDATE, POSITIVE_INT_VALIDATE, DISCOUNT_VALIDATE);

/**
 * 4.���屾Javascript�������ṩ�ı�׼��ʾ��Ϣ
 */

var ALERT_MAG_HEAD = "����Ƿ���ԭ���ǣ�";
//��ʾ��Ϣ��ͷ���֣�
var EMPTY_MESSAGE = "������д��ѡ�񣬲���Ϊ�գ�";
var DATE_MESSAGE = "Ҫ�������ʽΪ$�����ڡ������ʽ��ȷ����������������Ƿ񲻴��ڣ�";
var EMAIL_MESSAGE = "Ҫ�������ʽ�Ϸ���Email��";
var NUMBER_MESSAGE = "Ҫ������Ϸ����֣�";
var INT_MESSAGE = "Ҫ������Ϸ�������";
var DOUBLE_MESSAGE = "Ҫ���������֣�����С��λ��������$λ��";
var LENGTH_MESSAGE = "Ҫ���������ݵ��ַ�����";
var POSITIVE_MSG = "Ҫ�����������������";
var POSITIVE_INT_MSG = "Ҫ�������������������";
var DISCOUNT_MSG = "Ҫ�����������������(0,1]�ڣ�";
var ALERT_MSG_ARR = new Array(EMPTY_MESSAGE, DATE_MESSAGE, EMAIL_MESSAGE, NUMBER_MESSAGE, INT_MESSAGE, DOUBLE_MESSAGE, LENGTH_MESSAGE, POSITIVE_MSG, POSITIVE_INT_MSG, DISCOUNT_MSG);

/**
 * 5.���屾Javascript�������ڽ�������У��ʱ�����õ�Ĭ�����ڸ�ʽ
 */
var DEFAULT_DATE_PATTERN = DATE_LINE_PATTERN;

/**
 * 6.�����׼�ַ�����
 */
var STANDARD_STR = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.+-*/`~!@#$%^&*()_=|\\;:'\"<>,?/";

/**
 * 7.Ϊ����ֵ�ĺ�����Ҫ�ı���
 */
var FORM_FIELD_ENDUE_STR = "";
var FIELD_NAME_VALUE_SPLITOR = "$$$$$";
var FIELD_SPLITOR = "$$$$$$$";
/**
 * 8.��ҳ�����޸Ļ�ɾ�����ݿ��¼ʱ����ʾ��Ϣ
 */
var UPDATE_MSG = "һ��ֻ����ʾ���޸�һ����¼�����ܲ�ѡ���ѡ��";
var DELETE_CHECK_MSG = "û��ѡ��Ҫɾ���ļ�¼��һ�ο���ɾ��һ���������¼�������ܲ�ѡ��";
var DELETE_MSG = "����ɾ��������޷��ָ���ȷ��Ҫɾ����Ҫ����������ȷ������ť����������ȡ������ť��";
var SAVE_MSG = "���ݱ��������޷��ָ���ȷ��Ҫ������Ҫ����������ȷ������ť����������ȡ������ť��";
var CANCEL_MSG = "ȷ���������ι����𣿼���������ȷ������ť������������ȡ������ť��";

//===============================================================================================
//�ڶ����֣����������ַ�������ز���
//===============================================================================================
/**
 * ���ܣ������µ��ַ�������ֵΪcount��str����
 * srcStr��Դ�ַ���
 * count��Դ�ַ����ĸ���
 */
function getStrByCount(srcStr, count) {
    var retStr = "";
    for (var i = 0; i < count; i++) {
        retStr += srcStr;
    }
    return retStr;
}
/**
 * ���ܣ���ȡsrcStr�к������ַ���subStr�ĸ���
 * srcStr��Դ�ַ���
 * subStr�����ַ���
 */
function getContainNum(srcStr, subStr) {
    var tempStr = "";
    var containNumber = 0;
    if (srcStr != "" && subStr != "" && srcStr.length >= subStr.length) {
        var index = -1;
        while ((index = srcStr.indexOf(subStr)) != -1) {
            containNumber++;
            srcStr = srcStr.substring(index + subStr.length);
        }
    }
    return containNumber;
}
/**
 * ���ܣ��ж�srcStr���Ƿ����subStr
 * srcStr��Դ�ַ���
 * subStr�����ַ���
 */
function contains(srcStr, subStr) {
    return (containNum(srcStr, subStr) > 0);
}
/**
 * ���ܣ����ַ���ǰ��ո�ȥ��
 * srcStr��Դ�ַ���
 */
function trim(srcStr) {
    while (srcStr.charAt(0) == " ") {
        srcStr = srcStr.substring(1);
    }
    while (srcStr.charAt(srcStr.length - 1) == " ") {
        srcStr = srcStr.substring(0, srcStr.length - 1);
    }
    return srcStr;
}
/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�Ϊ�ջ�ȫ�ո�������Ҫ�ǿ�У��ĵط�
 * srcStr��Դ�ַ���
 */
function isEmpty(srcStr) {
    srcStr = unescape(srcStr);
    return (srcStr == "" || trim(srcStr) == "");
}
/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�����ȷ��Email.
 * srcStr��Դ�ַ���
 */
function isEmail(srcStr) {
    var retVal = false;
    srcStr = trim(srcStr);
    if (!isEmpty(srcStr) && srcStr.length > 5 && getContainNum(srcStr, "@") == 1) {
        var index = srcStr.indexOf(".");
        if (index > 0) {
            retVal = true;
        }
    }
    return retVal;
}
/**
 * ���ܣ��ж�ĳ�ַ���str�Ƿ���subStr��ʼ
 * srcStr��Դ�ַ���
 * subStr�����ַ���
 * ignoreCase���Ƿ���Դ�Сд
 */
function startsWith(srcStr, subStr, ignoreCase) {
    if (ignoreCase) {
        srcStr = srcStr.toLowerCase();
        subStr = subStr.toLowerCase();
    }
    return (srcStr.length >= subStr.length && srcStr.substring(0, subStr.length) == subStr);
}
/**
 * ���ܣ��ж�ĳ�ַ���str�Ƿ���subStr����
 * srcStr��Դ�ַ���
 * subStr�����ַ���
 * ignoreCase���Ƿ���Դ�Сд
 */
function endsWith(srcStr, subStr, ignoreCase) {
    if (ignoreCase) {
        srcStr = srcStr.toLowerCase();
        subStr = subStr.toLowerCase();
    }
    return (srcStr.length >= subStr.length && srcStr.substring(srcStr.length - subStr.length) == subStr);
}

/**
 * ���ܣ����ĳ�ַ����Ƿ�������
 * srcStr��Դ�ַ���
 */
function isNumber(srcStr) {
    return (srcStr != "" && !isNaN(srcStr));
}
/**
 * ���ܣ����ĳ�ַ���srcStr�Ƿ������֣���С������λ����ָ����λ��dotNum֮��
 * srcStr��Դ�ַ���
 * dotNum��С������λ��
 */
function isDouble(srcStr, dotNum) {
    var retVal = false;
    srcStr = unescape(srcStr);
    if (isNumber(srcStr)) {
        retVal = true;
        var index = srcStr.indexOf(".");
        if (index != -1) {
            srcStr = srcStr.substring(index + 1);
            retVal = (srcStr.length <= dotNum);
        }
    }
    return retVal;
}
/**
 * ���ܣ����ĳ�ַ����Ƿ�����
 * srcStr��Դ�ַ���
 */
function isInt(srcStr) {
    return (isNumber(srcStr) && srcStr % 1 == 0);
}
/**
 * ���ܣ���ʽ������
 * srcStr��Դ�ַ���
 * pattern��Ŀ���ʽ������00.00
 */
function formatNum(srcNum, pattern) {
    var returnNum = srcNum;
    if (getContainNum(pattern, ".") <= 1) {
        var tempStr = new String(srcNum);
        var sourceLen = tempStr.length;
        var patterLen = pattern.length;
        var patternIndex = pattern.indexOf(".");
        var sourceIndex = tempStr.indexOf(".");
        var leftObjectLen = (patternIndex == -1) ? patterLen : patternIndex;
        var rightObjectLen = (patternIndex == -1) ? 0 : patterLen - patternIndex - 1;
        var leftNum = (sourceIndex != -1) ? tempStr.substring(0, sourceIndex) : tempStr;
        var rightNum = (sourceIndex != -1) ? tempStr.substring(sourceIndex + 1) : "";
        var minusLen = 0;
        if (patternIndex == -1) {
            minusLen = leftObjectLen - leftNum.length;
            tempStr = (minusLen > 0) ? getStrByCount("0", minusLen) + tempStr : tempStr
        } else {
            if (sourceIndex != -1) {
                minusLen = leftObjectLen - leftNum.length;
                leftNum = (minusLen > 0) ? getStrByCount("0", minusLen) + leftNum : leftNum;
                minusLen = rightObjectLen - rightNum.length;
                rightNum = (minusLen > 0) ? rightNum + getStrByCount("0", minusLen) : rightNum;
                rightNum = (minusLen < 0) ? rightNum.substring(0, rightObjectLen) : rightNum;
            } else {
                minusLen = leftObjectLen - leftNum.length;
                leftNum = (minusLen > 0) ? getStrByCount("0", minusLen) + leftNum : leftNum;
                minusLen = rightObjectLen;
                rightNum = getStrByCount("0", minusLen);
            }
            tempStr = leftNum + "." + rightNum;
        }
        returnNum = tempStr;
    }
    return tempStr;
}

/**
 * ���ܣ���ʽ��С��,���������빦��
 * src��Դ�ַ���
 * pos������С�����λ����2
 */
function formatFloat(src, pos) {
    return Math.round(src * Math.pow(10, pos)) / Math.pow(10, pos);
}

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�����
 * srcStr��Դ�ַ���
 */
function isPositiveNumber(srcStr) {
    return (isNumber(srcStr) && Number(srcStr) > 0);
}

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�������
 * srcStr��Դ�ַ���
 */
function isPositiveInteger(srcStr) {
    return (isPositiveNumber(srcStr) && isInt(srcStr));
}


/**
 * ���ܣ��ж�ĳ�ַ����Ƿ���ĳ������
 * srcArr��Դ����
 * srcStr��Դ�ַ���
 * ignoreCase���Ƿ���Դ�Сд
 */
function isExistInArr(srcArr, srcStr, ignoreCase) {
    var retVal = false;
    var tempLength = srcArr.length;
    if (ignoreCase) {
        srcStr = srcStr.toLowerCase();
    }
    for (var i = 0; i < tempLength; i++) {
        if (ignoreCase) {
            srcArr[i] = srcArr[i].toLowerCase();
        }
        if (srcArr[i] == srcStr) {
            retVal = true;
            break;
        }
    }
    return retVal;
}
/**
 * ���ܣ�����ĳ�ַ�����ĳ�����е�����ֵ���������򷵻�-1
 * srcArr��Դ����
 * srcStr��Դ�ַ���
 */
function findIndexOfArr(srcArr, srcStr) {
    var retVal = -1;
    var tempLength = srcArr.length;
    for (var i = 0; i < tempLength; i++) {
        if (srcArr[i] == srcStr) {
            retVal = i;
            break;
        }
    }
    return retVal;
}
/**
 * ���ܣ���ĳ�ַ����л�ȡ������ɵ��ַ�
 * srcArr��Դ����
 */
function getNumFromText(srcStr) {
    var retStr = "";
    if (!isEmpty(srcStr)) {
        var strLength = srcStr.length;
        var thisChar = "";
        for (var i = 0; i < strLength; i++) {
            thisChar = srcStr.charAt(i);
            if (!isNaN(thisChar)) {
                retStr += thisChar;
            }
        }
    }
    return retStr;
}
/**
 * ���ܣ���srcStr�е�����oldStrȫ���滻��newStr
 * srcStr��Դ�ַ���
 * oldStr��ԭ���ַ���
 * newStr�������ַ���
 */
function replaceStr(srcStr, oldStr, newStr) {
    var retStr = srcStr;
    if (oldStr != "") {
        var finalStr = srcStr
        var tempStr = "";
        var index = -1;
        while ((index = srcStr.indexOf(oldStr)) != -1) {
            tempStr += finalStr.substring(0, index) + newStr;
            finalStr = finalStr.substring(index + oldStr.length);
            srcStr = srcStr.substring(index + oldStr.length);
        }
        if (finalStr != "") {
            tempStr += finalStr;
        }
        retStr = (tempStr != "") ? tempStr : retStr;
    }
    return retStr;
}


function replaceFirst(srcStr, oldStr, newStr) {
    var retStr = "";
    var index = srcStr.indexOf(oldStr);
    if (index > -1) {
        retStr = srcStr.substring(0, index) + newStr;
        retStr += srcStr.substring(index + oldStr.length);
    } else {
        retStr = srcStr;
    }
    return retStr;
}
/**
 * ���ܣ����ĳ�ַ����ĳ����Ƿ񳬳���󳤶�
 * srcStr��Դ�ַ���
 * maxLength�����������
 */
function isLengthExtend(srcStr, maxLength) {
    var subStr = "";
    var doubleByteCount = 0;
    for (var i = 0; i < srcStr.length; i++) {
        subStr = srcStr.charAt(i);
        if (STANDARD_STR.indexOf(subStr) == -1) {
            doubleByteCount++;
        }
    }
    return (srcStr.length > (maxLength - doubleByteCount));
}
/**
 * ���ܣ����ĳ�ַ����ĳ����Ƿ�С����С����
 * srcStr��Դ�ַ���
 * minLength����С������
 */
function isLengthLess(srcStr, minLength) {
    var subStr = "";
    var doubleByteCount = 0;
    for (var i = 0; i < srcStr.length; i++) {
        subStr = srcStr.charAt(i);
        if (STANDARD_STR.indexOf(subStr) == -1) {
            doubleByteCount++;
        }
    }
    return (srcStr.length < (minLength - doubleByteCount));
}
/**
 * ���ܣ����ĳ�ַ��������Ƿ�������ֵ��Χ��
 * srcStr��Դ�ַ���
 * minLength����С������
 * maxLength�����������
 */
function isLengthValid(srcStr, minLength, maxLength) {
    srcStr = unescape(srcStr);
    return (!isLengthExtend(srcStr, maxLength) && !isLengthLess(srcStr, minLength));
}

/**
 * ���ܣ���ָ��ֵ��ӵ�����
 * arr��Ŀ������
 * item����ֵ
 */
function add2Arr(arr, item) {
    if (arr == null) {
        return;
    }
    arr[arr.length] = item;
    return arr.length;
}


function removeFromArrByIndex(arr, index) {
    if (arr == null || arr.length == 0) {
        return null;
    }
    if (arr.length - 1 < index) {
        return null;
    }
    returnValue = arr[index];
    arr.splice(index, 1);
    return returnValue;
}


function removeFromArr(arr, item) {
    if (arr == null || arr.length == 0) {
        return null;
    }
    index = -1;
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] == item) {
            index = i;
            break;
        }
    }
    if (index < 0) {
        return null;
    }
    return removeFromArrByIndex(arr, index);
}

function explode(srcStr, spanStr) {
    var str = trim(srcStr);
    if (str == '') {
        return new Array();
    }
    if (spanStr == null) {
        return new Array();
    }

    var ArrDes = new Array();
    var i = 0;
    var find = str.indexOf(spanStr);
    var spanlen = spanStr.length;
    while (find >= 0) {
        ArrDes[i] = str.substring(0, find);
        str = str.substr(find + spanlen);
        find = str.indexOf(spanStr);
        i++;
    }
    if (trim(str) != '') {
        ArrDes[i] = str;
    }
    return ArrDes;
}

function implode(arr, spanStr) {
    var str = "";
    if (arr == null || arr.length <= 0) {
        return "";
    }
    for (i = 0; i < arr.length - 1; i++) {
        str += arr[i] + spanStr;
    }
    str += arr[i];
    return str;
}
/**
 * ���ܣ����������ظ���ֵ���������ʽ����
 * array��Դ����
 */
function retRepeatInArray(array) {
    var retArray = new Array();
    var arrayLength = -1;
    for (var i = 0; i < array.length; i++) {
        var sign = array[i];
        for (var j = i + 1; j < array.length; j++) {
            if (sign == array[j]) {
                arrayLength++;
                retArray[arrayLength] = sign;
                break;
            }
        }
    }
    return retArray;
}
//===============================================================================================
//�������֣����´������ڲ���
//===============================================================================================

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�8λ���ֱ�׼����
 * srcStr��Դ�ַ���
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
 * srcDate��Դ�ַ���
 */
function getDatePattern(srcDate) {
    var retPattern = "";
    var numberDate = getNumFromText(srcDate);
    if (isStandardDate(numberDate)) {
        var year = numberDate.substring(0, 4);
        var month = numberDate.substring(4, 6);
        var date = numberDate.substring(6);
        retPattern = replaceStr(srcDate, year, "YYYY");
        retPattern = replaceFirst(retPattern, month, "MM");
        retPattern = replaceStr(retPattern, date, "DD");
    }
    return retPattern;
}
/**
 * ���ܣ��ж����ڸ�ʽ�Ƿ�Ϸ�
 * datePattern�����ڸ�ʽ
 */
function isValidDatePattern(datePattern) {
    return isExistInArr(LIMIT_DATE_PATTERN, datePattern, true);
}
/**
 * ���ܣ��ж�ĳ�ַ����Ƿ����ڱ�ϵͳ�Ͽɵĸ�ʽ������
 * srcDate�������ַ���
 */
function isValidDate(srcDate) {
    var tempDate = getNumFromText(srcDate);
    var datePattern = getDatePattern(srcDate);
    return (isStandardDate(tempDate) && isValidDatePattern(datePattern));
}
/**
 * ���ܣ��ж�ĳ�ַ����Ƿ����ڱ�ϵͳ�Ͽɵĸ�ʽ������,�����ʽΪָ���ĸ�ʽ
 * srcDate�������ַ���
 * datePattern��ָ�������ڸ�ʽ
 */
function isFormatDate(srcDate, datePattern) {
    var retVal = false;
    srcDate = unescape(srcDate);
    var tempDate = getNumFromText(srcDate);
    var tempPattern = getDatePattern(srcDate);
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
 * formatDate�����ϱ�ϵͳ�涨�������ʽ�����ַ���
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
 * srcStr�������ַ���
 */
function getYearStr(srcStr) {
    return srcStr.substring(0, 4);
}
/**
 * ���ܣ���8λ���������л�ȡ�·�����
 * srcStr�������ַ���
 */
function getMonthStr(srcStr) {
    return srcStr.substring(4, 6);
}
/**
 * ���ܣ���8λ���������л�ȡ��������
 * srcStr�������ַ���
 */
function getDateStr(srcStr) {
    return srcStr.substring(6);
}
/**
 * ���ܣ���ԭ����ת����Ŀ���ʽ����
 * srcDate�������ַ���
 * datePattern��Ŀ�����ڸ�ʽ
 */
function getFormatDate(srcDate, datePattern) {//�˷���������������
    var retStr = srcDate;
    srcDate = getStandardDate(srcDate);
    if (isStandardDate(srcDate) && isValidDatePattern(datePattern)) {
        var year = getYearStr(srcDate);
        var month = getMonthStr(srcDate);
        var date = srcDate.substring(6);
        retStr = replaceStr(datePattern, "YYYY", year);
        retStr = replaceStr(retStr, "MM", month);
        retStr = replaceStr(retStr, "DD", date);
    }
    return retStr;
}


/**
 * ���ܣ������������֮�������
 * dateStr1�������ַ���1
 * dateStr2�������ַ���2
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
 * ���ܣ��������ڵĵ���
 * dateStr�������ַ���
 * datePattern������������ڸ�ʽ
 * datePattern����������
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
 * datePattern��ָ�������ڸ�ʽ
 */
function getToday(datePattern) {
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
    var today = year + month + day;
    if (isValidDatePattern(datePattern)) {
        today = datePattern.toLowerCase();
        ;
        today = replaceStr(today, "YYYY", year);
        today = replaceStr(today, "YYYY", month);
        today = replaceStr(today, "DD", day);
    }
    return today;
}
/**
 * ���ܣ�����ĳ��ĳ�µ�����
 * year�����
 * month���·�
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

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�0��1֮������֡�
 * val��Դ�ַ���
 */
function isDiscount(val) {
    return (isNumber(val) && val > 0 && val <= 1);
}

//===============================================================================================
//���Ĳ��֣����´������������
//===============================================================================================
/**
 * ���ܣ���ĳ��select����µ�����Ԫ�ظ��Ƶ��ڶ���select�����
 * fromSelect��Դselect�������
 * toSelect��Ŀ��select�������
 */
function copyAllOption(fromSelect, toSelect) {
    processOption(fromSelect, toSelect, false, true);
}

/**
 * ���ܣ��Զ������ʽ���и��Ʋ���������һ��select����µ�����Ԫ�ظ��Ƶ��ڶ���select�����
 * fromSelect��Դselect���
 * toSelect��Ŀ��select���
 */
function copyObjOptions(fromObj, toObj) {
    if (fromObj.hasChildNodes()) {
        var option = null;
        for (var i = 0; i < fromObj.length; i++) {
            option = new Option(fromObj.options[i].text, fromObj.options[i].value);
            if (haveChild(toObj, option)) {
                alert("��ѡ���Ѿ����ڣ��������ظ���ӣ�");
                continue;
            }
            toObj.options[toObj.length] = option;
        }
    }
}
/**
 * ���ܣ���ĳ��select����µ�ѡ��Ԫ�ظ��Ƶ��ڶ���select�����
 * fromSelect��Դselect�������
 * toSelect��Ŀ��select�������
 */
function copySelectedOption(fromSelect, toSelect) {
    processOption(fromSelect, toSelect, true, true);
}
/**
 * ���ܣ���ĳ��select����µ�����Ԫ���ƶ����ڶ���select�����
 * fromSelect��Դselect�������
 * toSelect��Ŀ��select�������
 */
function moveAllOption(fromSelect, toSelect) {
    processOption(fromSelect, toSelect, false, false);
}

/**
 * ���ܣ���ĳ��select����µ�ѡ��Ԫ���ƶ����ڶ���select�����
 * fromSelect��Դselect�������
 * toSelect��Ŀ��select�������
 */
function moveSelectedOption(fromSelect, toSelect) {
    processOption(fromSelect, toSelect, true, false);
}

/**
 *��ע������������Ϊ�����ĸ�������д�ģ�һ�����ʱ����Ҫ���ñ���������������ĺ�������
 * ���ܣ���ĳ��select����µ�Ԫ���ƶ����߸��Ƶ��ڶ���select�����
 * fromSelect��Դselect�������
 * toSelect��Ŀ��select�������
 * allOrSelected��true��ʾѡ��Ԫ�أ�false��ʾ����Ԫ��
 * copyOrMove��true��ʾ����Ԫ�أ�false��ʾ�ƶ�Ԫ��
 */
function processOption(fromSelect, toSelect, allOrSelected, copyOrMove) {
    var selects = document.getElementsByTagName("select");
    var fromObj = null;
    var toObj = null;
    for (var i = 0; i < selects.length; i++) {
        if (selects[i].name == fromSelect) {
            fromObj = selects[i];
        } else if (selects[i].name == toSelect) {
            toObj = selects[i];
        }
        if (fromObj != null && toObj != null) {
            break;
        }
    }
    if (fromObj.hasChildNodes()) {
        var option = null;
        for (var i = 0; i < fromObj.length; i++) {
            if (allOrSelected && !fromObj.options[i].selected) {
                continue;
            }
            option = new Option(fromObj.options[i].text, fromObj.options[i].value);
            if (haveChild(toObj, option)) {
                alert("��ѡ���Ѿ����ڣ��������ظ���ӣ�");
                return;
            }
            if (!copyOrMove) {
                fromObj.remove(i);
                i--;
            }
            toObj.options[toObj.length] = option;
        }
    }
}
/**
 * ���ܣ��ж�ĳ��select����Ƿ���ĳ��Optionѡ��
 * selObj��select�������
 * optObj��option�������
 */
function haveChild(selObj, optObj) {
    var retVal = false;
    var optValue = optObj.value;
    var optText = optObj.text;
    var opt = null;
    for (var i = 0; i < selObj.length; i++) {
        opt = selObj.item(i);
        if (opt.value == optValue && opt.text == optText) {
            retVal = true;
            break;
        }
    }
    return retVal;
}
/**
 * ���ܣ�select��ĳָ��ѡ����±�
 * select��select���
 * code�������ҵ�ѡ��ֵ
 */
function indexInOptions(select, code) {
    var selObj = select;
    for (var i = 0; i < selObj.length; i++) {
        if (selObj.options[i].value == code) {
            return i;
        }
    }
    return -1;
}
/**
 * ���ܣ�ɾ��ĳ��select����µ�Ԫ��
 * select��select�����
 * allOrSelected��true��ʾѡ��Ԫ�أ�false��ʾ����Ԫ��
 */
function dropOption(selectName, allOrSelected) {
    var selObj = document.getElementById(selectName);
    if (selObj) {
        for (var i = 0; i < selObj.length; i++) {
            if (allOrSelected) {
                if (selObj.options[i].selected) {
                    selObj.remove(i);
                    i--;
                }
            } else {
                selObj.remove(i);
                i--;
            }
        }
    }

}

/**
 * ���ܣ�ɾ��ĳ��select����µ�ѡ��Ԫ��
 * selectName��select�����
 */
function dropSelectedOption(selectName) {
    dropOption(selectName, true);
}
/**
 * ���ܣ�ɾ��ĳ��select����µ�����Ԫ��
 * selectName��select�����
 */
function dropAllOption(selectName) {
    dropOption(selectName, true);
}
/**
 * ���ܣ���������б��ж�Ӧֵѡ��
 * selectName��select�����
 * optionValue���ض���ֵ
 */
function dropSpecialOption(selectName, optionValue) {
    var optionObj = document.all[selectName].options;
    var selectedValueArr = optionValue.split(";");
    if (selectedValueArr) {
        var valueCount = selectedValueArr.length;
        var tempValue = "";
        for (var i = 0; i < optionObj.length; i++) {
            tempValue = optionObj[i].value;
            for (var j = 0; j < valueCount; j++) {
                if (tempValue == selectedValueArr[j]) {
                    optionObj[i] = null;
                    i--;
                    break;
                }
            }
        }
    }
}
/**
 * ���ܣ���������б��ж�Ӧֵѡ��
 * selectName��select�����
 * optionValue���ض���ֵ
 */
function dropSpecialOption(select, optionValue) {
    var optionObj = select.options;
    var selectedValueArr = optionValue.split(";");
    if (selectedValueArr) {
        var valueCount = selectedValueArr.length;
        var tempValue = "";
        for (var i = 0; i < optionObj.length; i++) {
            tempValue = optionObj[i].value;
            for (var j = 0; j < valueCount; j++) {
                if (tempValue == selectedValueArr[j]) {
                    optionObj[i] = null;
                    i--;
                    break;
                }
            }
        }
    }
}
/**
 * ���ܣ���ĳ��select����µ�����ѡ�ѡ��
 * selectName��select�����
 */
function selectAll(selectName) {
    var selects = document.getElementsByTagName("select");
    var selObj = null;
    for (var i = 0; i < selects.length; i++) {
        if (selects[i].name == selectName) {
            selObj = selects[i];
            break;
        }
    }
    for (var i = 0; i < selObj.length; i++) {
        selObj.options[i].selected = true;
    }
}

/**
 * ���ܣ���ĳ��select����µ�����ѡ�����ڷ�ѡ��״̬
 * selectName��select�����
 */
function unSelectAll(selectName) {
    var selects = document.getElementsByTagName("select");
    var selObj = null;
    for (var i = 0; i < selects.length; i++) {
        if (selects[i].name == selectName) {
            selObj = selects[i];
            break;
        }
    }
    for (var i = 0; i < selObj.length; i++) {
        selObj.options[i].selected = false;
    }
}

function unSelectAllById(selectId) {
    var select = document.getElementById(selectId);
    for (var i = 0; i < select.length; i++) {
        select.options[i].selected = false;
    }
}

/**
 * ���ܣ���ĳ��select����µ�����ѡ���ѡ��״̬�÷�
 * selectName��select�����
 */
function reverseSeletStatus(selectName) {
    var selects = document.getElementsByTagName("select");
    var selObj = null;
    for (var i = 0; i < selects.length; i++) {
        if (selects[i].name == selectName) {
            selObj = selects[i];
            break;
        }
    }
    for (var i = 0; i < selObj.length; i++) {
        selObj.options[i].selected = !selObj.options[i].selected;
    }
}
/**
 * ���ܣ���ȡ�����б��б�ѡ�����ֵ��Ӧ����������ɶ�ѡ�͵�ѡ�ĳ��ϡ�
 * selectName��select�����
 * splitor���ָ���
 */

function getSelectValue(selectName, splitor) {
    var retVal = "";
    var optionObj = document.all[selectName].options;
    var optionCount = optionObj.length;
    splitor = (splitor == null) ? ";" : splitor;
    if (optionCount) {
        for (var i = 0; i < optionCount; i++) {
            if (optionObj[i].selected && !isEmpty(optionObj[i].value)) {
                retVal += optionObj[i].value + splitor;
            }
        }
        retVal = retVal.substring(0, retVal.length - splitor.length);
    }
    return retVal;
}
/**
 * ���ѡ�е���Ŀ��
 * shaffer
 */
function getSelectedCount(selectName) {
    var retVal = 0;
    var optionObj = document.all[selectName].options;
    var optionCount = optionObj.length;
    if (optionCount) {
        for (var i = 0; i < optionCount; i++) {
            if (optionObj[i].selected) {
                retVal++;
            }
        }
    }
    return retVal;
}
/**
 * ���ܣ���ȡ�����б��б�ѡ������ı���Ӧ����������ɶ�ѡ�͵�ѡ�ĳ��ϡ�
 * selectName��select�����
 * splitor���ָ���
 */
function getSelectText(selectName, splitor) {
    var retVal = "";
    var optionObj = document.all[selectName].options;
    var optionCount = optionObj.length;
    splitor = (splitor == null) ? ";" : splitor;
    for (var i = 0; i < optionCount; i++) {
        if (optionObj[i].selected && !isEmpty(optionObj[i].value)) {
            retVal += optionObj[i].text + splitor;
        }
    }
    return (endsWith(retVal, splitor)) ? retVal.substring(0, retVal.length - splitor.length) : retVal;
}

/**
 * ���ܣ���ȡ�����б����ض�����ı�
 * selectName��select�����
 * optionValue���ض���ֵ
 */
function getSpecialOptionText(selectName, optionValue) {
    var retVal = "";
    var optionObj = document.all[selectName].options;
    var optionCount = optionObj.length;
    for (var i = 0; i < optionCount; i++) {
        if (optionObj[i].value == optionValue) {
            retVal += optionObj[i].text;
            break;
        }
    }
    return retVal;
}
function getSpecialOptionTextByObj(select, optionValue) {
    var retVal = "";
    var optionObj = select.options;
    var optionCount = optionObj.length;
    for (var i = 0; i < optionCount; i++) {
        if (optionObj[i].value == optionValue) {
            retVal += optionObj[i].text;
            break;
        }
    }
    return retVal;
}
/**
 * ���ܣ�ʹ�����б��ж�Ӧֵ����ѡ��״̬
 * selectName��select�����
 * optionValue���ض���ֵ
 */
function selectSpecialOption(selectName, optionValue) {
    var optionObj = document.all[selectName].options;
    var optionCount = optionObj.length;
    var selectedValueArr = optionValue.split(";");
    var valueCount = selectedValueArr.length;
    var tempValue = "";
    for (var i = 0; i < optionCount; i++) {
        tempValue = optionObj[i].value;
        for (var j = 0; j < valueCount; j++) {
            if (tempValue == selectedValueArr[j]) {
                optionObj[i].selected = true;
                break;
            }
        }
    }
}

function selectSpecialOptionByItem(select, optionValue) {
    var optionObj = select.options;
    var optionCount = optionObj.length;
    var selectedValueArr = optionValue.split(";");
    var valueCount = selectedValueArr.length;
    var tempValue = "";
    for (var i = 0; i < optionCount; i++) {
        tempValue = optionObj[i].value;
        for (var j = 0; j < valueCount; j++) {
            if (tempValue == selectedValueArr[j]) {
                optionObj[i].selected = true;
                break;
            }
        }
    }
}

/**
 * ���ܣ�ʹ�����б��ж�ӦText��Option����ѡ��״̬
 * selectName��select�������
 * optionValue���ض���ֵ
 */
function makeOptionsSelected(select, optionText) {
    var optionObj = select.options;
    var optionCount = optionObj.length;
    var selectedTextArr = optionText.split(";");
    var textCount = selectedTextArr.length;
    var tempText = "";
    for (var i = 0; i < optionCount; i++) {
        tempText = optionObj[i].text;
        for (var j = 0; j < textCount; j++) {
            if (tempText == selectedTextArr[j]) {
                optionObj[i].selected = true;
                break;
            }
        }
    }
}
function makeOptionSelected(select, optionText) {
    var optionObj = select.options;
    var optionCount = optionObj.length;
    var tempText = "";
    for (var i = 0; i < optionCount; i++) {
        tempText = optionObj[i].text;
        if (tempText == optionText) {
            optionObj[i].selected = true;
            break;
        }
    }
}
function makeOptionSelectedByValue(select, optionValue) {
    var optionObj = select.options;
    var optionCount = optionObj.length;
    var tempText = "";
    for (var i = 0; i < optionCount; i++) {
        tempText = optionObj[i].value;
        if (tempText == optionValue) {
            optionObj[i].selected = true;
            break;
        }
    }
}

//===============================================================================================
//���岿�֣����´���ѡ�����
//===============================================================================================

/**
 * ���ܣ���һ�������Ը�ѡ��checkBoxName������һ�鸴ѡ��checkBoxGroup
 * checkBoxName�������Ը�ѡ�������
 * checkBoxGroup���������Ը�ѡ�������
 */
function checkAll(checkBoxName, checkBoxGroup) {
    var checkAttr = document.all[checkBoxName].checked;
    try {
        isChecked = checkAttr;
        if (!setCheckBoxState(checkBoxGroup, isChecked)) {
            document.all[checkBoxName].checked = !isChecked;
        }
    } catch(exception) {
        alert(exception);
    }
}
function setCheckBoxState(checkBoxGroup, isChecked) {
    var retVal = true;
    var checkGroup = document.all[checkBoxGroup];
    if (!checkGroup) {
        return retVal;
    }
    var groupCount = checkGroup.length;
    if (groupCount) {
        if (groupCount > 200) {
            if (confirm("����������ȫѡ������Ҫ�ϳ�ʱ�䣬�Ƿ����������������ȷ������ť������������ȡ������ť��")) {
                for (var i = 0; i < groupCount; i++) {
                    if (checkGroup[i].type == "checkbox") {
                        checkGroup[i].checked = isChecked;
                    }
                }
            } else {
                retVal = false;
            }
        } else {
            for (var i = 0; i < groupCount; i++) {
                if (checkGroup[i].type == "checkbox") {
                    checkGroup[i].checked = isChecked;
                }
            }
        }
    } else {
        checkGroup.checked = isChecked;
    }
    return retVal;
}
/**
 * ���ܣ���ȡһ�鸴ѡ��checkBoxName�б�ѡ�е�ֵ��ֵ֮����splitor�ֿ�
 * checkBoxName����ѡ�������
 * splitor���ָ���
 */
function getCheckBoxValue(checkBoxName, splitor) {
    var checkBox = document.all[checkBoxName];
    splitor = (splitor == null) ? ";" : splitor;
    var retVal = "";
    var multipleCheck = 0;
    try {
        if (checkBox) {
            if (checkBox.type == "checkbox" && checkBox.checked) {
                retVal = checkBox.value;
            }
            for (var i = 0; i < checkBox.length; i++) {
                if (checkBox[i].type == "checkbox" && checkBox[i].checked) {
                    retVal += checkBox[i].value + splitor;
                    multipleCheck++;
                }
            }
        }
    } catch(exception) {
    }
    if (multipleCheck > 0) {
        retVal = retVal.substring(0, retVal.length - splitor.length);
    }
    return retVal;
}


/**
 * ���ܣ���ȡĳһ�鸴ѡ��checkBoxName�е���Ŀ
 * checkBoxName����ѡ�������
 * splitor���ָ���
 */
function getCheckBoxCount(checkBoxName) {
    var checkBox = document.all[checkBoxName];
    var checkedCount = 0;
    try {
        if (checkBox) {
            checkedCount = (checkBox.type == "checkbox") ? 1 : 0;
            for (var i = 0; i < checkBox.length; i++) {
                if (checkBox[i].type == "checkbox") {
                    checkedCount++;
                }
            }
        }
    } catch(exception) {
    }
    return checkedCount;
}


/**
 * ���ܣ���ȡĳһ�鸴ѡ��checkBoxName�б�ѡ�е���Ŀ
 * checkBoxName����ѡ�������
 * splitor���ָ���
 */
function getCheckedBoxCount(checkBoxName) {
    var checkBox = document.all[checkBoxName];
    var checkedCount = 0;
    try {
        if (checkBox) {
            checkedCount = (checkBox.type == "checkbox" && checkBox.checked) ? 1 : 0;
            for (var i = 0; i < checkBox.length; i++) {
                if (checkBox[i].type == "checkbox" && checkBox[i].checked) {
                    checkedCount++;
                }
            }
        }
    } catch(exception) {
    }
    return checkedCount;
}

/**
 * ���ܣ���ȡĳһ�鸴ѡ��groupCheckboxName�б�ѡ�е��������ֵ
 * groupCheckboxName����ѡ�������
 * return ����
 */
function getCheckedBoxValue(groupCheckboxName) {
    var selectedValue = new Array();
    var checkboxObj = document.all[groupCheckboxName];
    var checkboxLength = checkboxObj.length;
    if (checkboxLength) {
        if (checkboxLength) {
            for (var i = 0; i < checkboxLength; i++) {
                if (checkboxObj[i].type == "checkbox" && checkboxObj[i].checked) {
                    selectedValue.push(checkboxObj[i].value);
                }
            }
        }
    } else {
        if (checkboxObj.checked) {
            selectedValue.push(checkboxObj.value);
        }
    }
    return selectedValue;
}

/**
 * ���ܣ���ȡĳһ�鸴ѡ��groupCheckboxName�б�ѡ�е�������ж���
 * groupCheckboxName����ѡ�������
 * return ����
 */
function getCheckedBox(groupCheckboxName) {
    var checkedBox = new Array();
    var allCheckObj = document.all[groupCheckboxName];
    var checkboxLength = allCheckObj.length;
    if (checkboxLength) {
        if (checkboxLength) {
            for (var i = 0; i < checkboxLength; i++) {
                if (allCheckObj[i].type == "checkbox" && allCheckObj[i].checked) {
                    checkedBox.push(allCheckObj[i]);
                }
            }
        }
    } else {
        if (allCheckObj.checked) {
            checkedBox.push(allCheckObj);
        }
    }
    return checkedBox;
}

/**
 * ���ܣ�ʹָ��ֵ��ָ�����Ƶĸ�ѡ����ѡ��״̬�����ֵ֮���÷ֺŸ���
 * checkBoxName����ѡ�������
 * val��ָ��ֵ
 */
function makeBoxChecked(checkBoxName, val) {
    var checkBox = document.all[checkBoxName];
    var valueArr = val.split(";");
    try {
        if (checkBox) {
            if (checkBox.type == "checkbox" && checkBox.value == val) {
                checkBox.checked = true;
            }
            for (var i = 0; i < checkBox.length; i++) {
                for (var j = 0; j < valueArr.length; j++) {
                    if (checkBox[i].type == "checkbox" && checkBox[i].value == valueArr[j]) {
                        checkBox[i].checked = true;
                        break;
                    }
                }
            }
        }
    } catch(exception) {
    }
}
/**
 *����:����ѡ��ָ����ѡ��
 * checkBoxName����ѡ�������
 */
function reverseCheck(checkBoxName) {
    var obj = eval('document.' + checkBoxName);
    for (var i = 0; i < obj.length; i++) {
        obj[i].checked = !obj[i].checked;
    }
}

/**
 *����:����ѡ��ָ����ѡ�� (һ��)
 * checkBoxName����ѡ�����ID,�����NAME,�������Ψһֵ
 */
function simpleRevCheck(checkBoxName) {
    var obj = document.getElementById(checkBoxName);
    obj.checked = !obj.checked;
}
/**
 * ���ܣ��õ�ѡ�и�ѡ���ֵ������Ψһֵ���Ȱ��ظ�ֵ���ˣ�
 * checkBoxName����ѡ�����NAME
 */
function getDistinctCheckedBoxValue(checkBoxName) {
    var returnValues = new Array();
    var checkedValues = new Array();
    var checkedBoxes = document.getElementsByName(checkBoxName);
    var returnIndex = -1;
    var checkedIndex = -1;
    for (var i = 0; i < checkedBoxes.length; i++) {
        if (checkedBoxes[i].checked) {
            checkedValues[++checkedIndex] = checkedBoxes[i].value;
        }
    }
    for (var i = 0; i < checkedValues.length; i++) {
        if (i == 0) {
            returnValues[++returnIndex] = checkedValues[i];
        } else {
            if (checkedValues[i] == returnValues[returnIndex]) {
                continue;
            } else {
                returnValues[++returnIndex] = checkedValues[i];
            }
        }
    }
    return returnValues;
}
//===============================================================================================
//�������֣����´���ѡ�����
//===============================================================================================

/**
 * ���ܣ���ȡָ�������ĵ�ѡ���б�ѡ�����ֵ
 * radioName����ѡ�������
 */

//����ΰ 05-09-15 �޸�
function getRadioValue(radioName) {
    var radioBox = document.getElementsByName(radioName);
    var retVal = "";
    for (var i = 0; i < radioBox.length; i++) {
        if (radioBox[i].type == "radio" && radioBox[i].checked) {
            retVal = radioBox[i].value;
            break;
        }
    }
    return retVal;
}
/**
 * ���ܣ���ȡָ����ѡ������б�ѡ�����ֵ
 * radioObj����ѡ����
 */
function getRadioValueByObj(radioObj) {
    var retVal = "";
    if (radioObj.length) {
        for (var i = 0; i < radioObj.length; i++) {
            if (radioObj[i].type == "radio" && radioObj[i].checked) {
                retVal = radioObj[i].value;
                break;
            }
        }
    } else {
        if (radioObj.checked) {
            retVal = radioObj.value;
        }
    }
    return retVal;
}
/*
function getRadioValue(radioName){
    var radioBox = document.all[radioName];
    var retVal = "";
    try{
        retVal = (radioBox.type == "radio" && radioBox.Validateed) ? radioBox.value : retVal;
        for(var i = 0; i < radioBox.length; i++){
            if(radioBox[i].type == "radio" && radioBox[i].Validateed){
                retVal = radioBox[i].value;
                break;
            }
        }
    }catch(exception){}
    return retVal;
}
*/
/**
 * ���ܣ���ȡҳ����ָ�����ֵĵ�ѡ��ť�ĸ���
 * radioName����ѡ�������
 */
function getRadioCount(radioName) {
    var radioBox = document.all[radioName];
    var retVal = 0;
    try {
        retVal = (radioBox[0] == null) ? 1 : radioBox.length;
    } catch(exception) {
    }
    return retVal;
}

/**
 * ���ܣ�ʹָ��ֵ��ָ�����Ƶĵ���ѡ����ѡ��״̬��
 * radioName����ѡ�������
 * val��ָ��ֵ
 */
function makeRadioChecked(radioName, val) {
    var obj = document.all[radioName];
    try {
        if (obj) {
            if (obj.type == "radio" && obj.value == val) {
                obj.checked = true;
            }
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].type == "radio" && obj[i].value == val) {
                    obj[i].checked = true;
                    break;
                }
            }
        }
    } catch(exception) {
        alert("error");
    }
}

/**
 * ���ܣ���ñ�ѡ�еĵ�ѡ���ڵ�ѡ�����е�λ�á�
 * radioName����ѡ�������
 */
function getSelectedRadioIndex(radioName) {
    var radioBox = document.getElementsByName(radioName);
    var retVal = 0;
    for (var i = 0; i < radioBox.length; i++) {
        if (radioBox[i].type == "radio" && radioBox[i].checked) {
            retVal = i;
            break;
        }
    }
    return retVal;
}

//===============================================================================================
//���߲��֣�����Ϊ������ͨУ����ۺϴ�����
//===============================================================================================
/**
 *��ע������ʱ�����ı���������δ����
 * ���ܣ����ƶ����Ƶı��ĸ������е�ֵ���и�ʽ������ȥ������ֵǰ��Ŀո�
 * formName��������
 */
function trimForm(formName) {
    var formList = document.forms;
    var frmObj = null;
    for (var i = 0; i < formList.length; i++) {
        frmObj = formList.item(i);
        if (frmObj.name == formName) {
            break;
        }
    }
    var allObj = frmObj.all;
    var len = allObj.length;
    var tmpVal = "";
    var objType = "";
    for (var i = 0; i < len; i++) {
        objType = allObj[i].type;
        if (objType == "text" || objType == "password" || objType == "textarea") {
            tmpVal = allObj[i].value;
            tmpVal = trim(tmpVal);
            allObj[i].value = tmpVal;
        }
    }
}

/**
 * ���ܣ����Ҫ�Ա����������У�飬�ɲ��ô˷�������ϣ�������ڸ�ʽ��������һ�����ڸ�ʽ��Ч��
 * datePattern�����ڸ�ʽ
 */
function setDatePattern(datePattern) {
    if (isValidDatePattern(datePattern)) {
        DEFAULT_DATE_PATTERN = datePattern;
    } else {
        alert("�����˱������ⲻ��ʶ������ڸ�ʽ��ע�⣬��ϵͳ����֧�ֵ����ڸ�ʽΪ����" + DATE_STANDARD_PATTERN + "���� ��" + DATE_LINE_PATTERN + "������" + DATE_SLOPE_PATTERN + "������" + DATE_DOT_PATTERN + "���Լ���" + DATE_CHINESE_PATTERN + "��");
    }
}
/**
 * ע���������£�
 * 1.�������ڵ�У�飬Ӧ�����������ڸ�ʽ����������ã���Ĭ��У���ʽΪDATE_LINE_PATTERN��
 * 2.����˫�����������ֵ�У�鷽ʽ��fieldLabels���뷽ʽʾ����"���$2;����$3"����ʾ���򡰽������������λС�����ڵ����֣�
 * ����������������λС�����ڵ����֣�
 * 3.���ڳ��ȵ�У�鷽ʽ��fieldLabels���뷽ʽʾ����"6$�û�����$20;0$�û�סַ$100;"����ʾ�����û����������ַ����ȱ�����6��
 * 20֮�䣬���߽磻�����û�סַ�����ַ����ȱ�����0��100֮�䣬����������û�סַ�����������ݣ�����������ݣ��򳤶ȱ���
 * ��100���ַ�(��)���ڣ�
 */

/**
 * ���ܣ���Ч�Լ�飬����true��ʾͨ��У�飬false��ʾ�зǷ��������
 * fieldNames��У��������ƣ����֮������԰�Ƿֺ�����
 * fieldLabels��У��������ı�ʶ�����֮������԰�Ƿֺ����ӡ�
 * validateType��У�����ͣ�һ�ν���ִ��һ��У�飬Ŀǰ֧�ֵ�У�����ͼ�ǰ�泣�����岿��˵����
 */
function formValidate(fieldNames, fieldLabels, validateType) {
    var fieldNameArr = fieldNames.split(";");
    var fieldLabelArr = fieldLabels.split(";");
    exceptionValidate(fieldNameArr, fieldLabelArr);
    var retVal = true;
    var fieldName = "";
    var fieldType = "";
    var fieldValue = "";
    var items = null;
    var minLength = -1;
    var maxLength = -1;
    var nameCount = fieldNameArr.length;
    var alertMsg = "";
    var hasError = false;
    //�Ƿ���������������
    var dotNum = -1;
    if (index != -1) {
        var index = findIndexOfArr(VALIDATE_TYPE_ARR, validateType);
        var funName = VALIDATE_TYPE_ARR[index];
        for (var i = 0; i < nameCount; i++) {
            fieldName = fieldNameArr[i];
            if (isEmpty(fieldName)) {
                continue;
            }
            if (!document.all[fieldName]) {
                hasError = true;
            }
            if (hasError) {
                alertMsg = "�����д��" + location.href + "��ҳ����Servlet�����ҳ����ڴ���������Ϊ��" + fieldName + "���ı����Ƿ���ڣ�";
                retVal = false;
                break;
            }
            items = document.all[fieldName];
            var isArr = false;
            try {
                isArr = eval('items.length && items[0].type');
            } catch(Exception) {
            }
            if (isArr) {
                fieldType = items[0].type;
            } else {
                fieldType = items.type;
            }
            if (fieldType == "radio") {
                fieldValue = getRadioValue(fieldName);
            } else if (fieldType == "checkbox") {
                fieldValue = getCheckBoxValue(fieldName);
            } else if (fieldType.indexOf("select") > -1) {
                fieldValue = getSelectValue(fieldName, ";");
            } else {
                fieldValue = document.all[fieldName].value
            }
            if (funName == "isLengthValid") {
                var tempArr = fieldLabelArr[i].split("$");
                if (tempArr.length == 3) {
                    minLength = tempArr[0];
                    maxLength = tempArr[2];
                    fieldLabelArr[i] = tempArr[1];
                    if (minLength > 0 || !isEmpty(fieldValue)) {
                        retVal = eval(funName + "('" + escape(fieldValue) + "', '" + minLength + "', '" + maxLength + "')");
                    }
                } else {
                    hasError = true;
                    alertMsg = "�����ҳ��д�ĳ���Ա���÷������ڴ���";
                    retVal = false;
                }
            } else if (funName == "isDouble") {
                var tempArr = fieldLabelArr[i].split("$");
                if (tempArr.length == 2) {
                    fieldLabelArr[i] = tempArr[0];
                    dotNum = Number(tempArr[1]);
                    if (!isEmpty(fieldValue)) {
                        retVal = eval(funName + "('" + escape(fieldValue) + "', '" + dotNum + "')");
                    }
                } else {
                    hasError = true;
                    alertMsg = "�����ҳ��д�ĳ���Ա���÷������ڴ���";
                    retVal = false;
                }
            } else if (funName == "isFormatDate") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "', '" + DEFAULT_DATE_PATTERN + "')");
                }
            } else if (funName == "isPositiveNumber") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else if (funName == "isPositiveInteger") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else if (funName == "isDiscount") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else {
                if (retVal) {
                    if (funName == "isEmpty") {//
                        retVal = !eval(funName + "('" + escape(fieldValue) + "')")
                    } else {
                        if (!isEmpty(fieldValue)) {
                            retVal = eval(funName + "('" + escape(fieldValue) + "')");
                        }
                    }
                }
            }
            if (!retVal) {
                if (!hasError) {
                    if (funName == "isLengthValid") {
                        if (minLength == 0) {
                            alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index] + "��󲻳�����" + maxLength + "��";
                        } else {
                            alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index] + "��СΪ��" + minLength + "�������Ϊ��" + maxLength + "��";
                        }
                    } else {
                        if (funName == "isDouble") {
                            ALERT_MSG_ARR[index] = replaceStr(ALERT_MSG_ARR[index], "$", "��" + dotNum + "��");
                        } else if (funName == "isFormatDate") {
                            ALERT_MSG_ARR[index] = replaceStr(ALERT_MSG_ARR[index], "$", "��" + DEFAULT_DATE_PATTERN + "��");
                        }
                        alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index];
                    }
                    if (funName == "isEmpty") {
                        if (fieldType.indexOf("select") == -1) {
                            alertMsg = alertMsg.replace("��ѡ��", "");
                        } else {
                            alertMsg = alertMsg.replace("��д��", "");
                        }
                    }
                }
                alert(alertMsg);
                if (fieldType != "checkbox" && fieldType != "radio" && fieldType.indexOf('select') == -1) {
                    if (!document.all[fieldName].readOnly && !document.all[fieldName].disabled) {
                        document.all[fieldName].select();
                        document.all[fieldName].focus();
                    }
                } else {
                    var fieldCount = null;
                    try {
                        fieldCount = document.all[fieldName].length;
                    } catch(Exception) {
                    }
                    if (fieldCount) {
                        if (!document.all[fieldName][0].readOnly && !document.all[fieldName][0].disabled) {
                            document.all[fieldName][0].focus();
                        }
                    } else {
                        if (!document.all[fieldName].readOnly && !document.all[fieldName].disabled) {
                            document.all[fieldName].focus();
                        }
                    }
                }
                break;
            } else {
                document.all[fieldName].value = trim(fieldValue);
            }
        }
    }
    return retVal;
}


/**
 * ע���������£�
 * 1.�������ڵ�У�飬Ӧ�����������ڸ�ʽ����������ã���Ĭ��У���ʽΪDATE_LINE_PATTERN��
 * 2.����˫�����������ֵ�У�鷽ʽ��fieldLabels���뷽ʽʾ����"���$2;����$3"����ʾ���򡰽������������λС�����ڵ����֣�
 * ����������������λС�����ڵ����֣�
 * 3.���ڳ��ȵ�У�鷽ʽ��fieldLabels���뷽ʽʾ����"6$�û�����$20;0$�û�סַ$100;"����ʾ�����û����������ַ����ȱ�����6��
 * 20֮�䣬���߽磻�����û�סַ�����ַ����ȱ�����0��100֮�䣬����������û�סַ�����������ݣ�����������ݣ��򳤶ȱ���
 * ��100���ַ�(��)���ڣ�
 */

/**
 * ���ܣ���Ч�Լ�飬����true��ʾͨ��У�飬false��ʾ�зǷ�������ڡ����������ڶ������ݱ�������ͬ�������
 * fieldIds��У�����ID�����֮������԰�Ƿֺ�����
 * fieldLabels��У��������ı�ʶ�����֮������԰�Ƿֺ����ӡ�
 * validateType��У�����ͣ�һ�ν���ִ��һ��У�飬Ŀǰ֧�ֵ�У�����ͼ�ǰ�泣�����岿��˵����
 */

function formValidateById(fieldIds, fieldLabels, validateType) {
    var fieldIdArr = fieldIds.split(";");
    var fieldLabelArr = fieldLabels.split(";");
    exceptionValidate(fieldIdArr, fieldLabelArr);
    var retVal = true;
    var fieldId = "";
    var fieldType = "";
    var fieldValue = "";
    var items = null;
    var minLength = -1;
    var maxLength = -1;
    var idCount = fieldIdArr.length;
    var alertMsg = "";
    var dotNum = -1;
    if (index != -1) {
        var index = findIndexOfArr(VALIDATE_TYPE_ARR, validateType);
        var funName = VALIDATE_TYPE_ARR[index];
        for (var i = 0; i < idCount; i++) {
            fieldId = fieldIdArr[i];
            if (isEmpty(fieldId)) {
                continue;
            }
            items = document.getElementById(fieldId);
            if (!items) {
                continue;
            }
            fieldType = items.type;
            fieldValue = items.value;
            if (funName == "isLengthValid") {
                var tempArr = fieldLabelArr[i].split("$");
                if (tempArr.length == 3) {
                    minLength = tempArr[0];
                    maxLength = tempArr[2];
                    fieldLabelArr[i] = tempArr[1];
                    if (minLength > 0 || !isEmpty(fieldValue)) {
                        retVal = eval(funName + "('" + escape(fieldValue) + "', '" + minLength + "', '" + maxLength + "')");
                    }
                } else {
                    alert("�����ҳ��д�ĳ���Ա���÷������ڴ���");
                    retVal = false;
                }
            } else if (funName == "isDouble") {
                var tempArr = fieldLabelArr[i].split("$");
                if (tempArr.length == 2) {
                    fieldLabelArr[i] = tempArr[0];
                    dotNum = Number(tempArr[1]);
                    if (!isEmpty(fieldValue)) {
                        retVal = eval(funName + "('" + escape(fieldValue) + "', '" + dotNum + "')");
                    }
                } else {
                    alert("�����ҳ��д�ĳ���Ա���÷������ڴ���");
                    retVal = false;
                }
            } else if (funName == "isFormatDate") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "', '" + DEFAULT_DATE_PATTERN + "')");
                }
            } else if (funName == "isPositiveNumber") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else if (funName == "isPositiveInteger") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else if (funName == "isDiscount") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else {
                if (funName == "isEmpty") {//
                    retVal = !eval(funName + "('" + escape(fieldValue) + "')")
                } else {
                    if (!isEmpty(fieldValue)) {
                        retVal = eval(funName + "('" + escape(fieldValue) + "')");
                    }
                }
            }
            if (!retVal) {
                if (funName == "isLengthValid") {
                    if (minLength == 0) {
                        alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index] + "��󲻳�����" + maxLength + "��";
                    } else {
                        alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index] + "��СΪ��" + minLength + "�������Ϊ��" + maxLength + "��";
                    }
                } else {
                    if (funName == "isDouble") {
                        ALERT_MSG_ARR[index] = replaceStr(ALERT_MSG_ARR[index], "$", "��" + dotNum + "��");
                    } else if (funName == "isFormatDate") {
                        ALERT_MSG_ARR[index] = replaceStr(ALERT_MSG_ARR[index], "$", "��" + DEFAULT_DATE_PATTERN + "��");
                    }
                    alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index];
                }
                if (funName == "isEmpty") {
                    if (fieldType.indexOf("select") == -1) {
                        alertMsg = alertMsg.replace("��ѡ��", "");
                    } else {
                        alertMsg = alertMsg.replace("��д��", "");
                    }
                }
                alert(alertMsg);
                if (fieldType != "checkbox" && fieldType != "radio" && fieldType.indexOf('select') == -1) {
                    items.value = "";
                    items.select();
                    items.focus();
                } else {
                    items.focus();
                }
                break;
            } else {
                items.value = trim(fieldValue);
            }
        }
    }
    return retVal;
}


/**
 * ���ܣ���Ч�Լ�飬����true��ʾͨ��У�飬false��ʾ�зǷ�������ڡ����������ڶ������ݱ�������ͬ�������
 * fieldIds��У��������ƣ����֮������԰�Ƿֺ�����
 * fieldLabels��У��������ı�ʶ�����֮������԰�Ƿֺ����ӡ�
 * validateType��У�����ͣ�һ�ν���ִ��һ��У�飬Ŀǰ֧�ֵ�У�����ͼ�ǰ�泣�����岿��˵����
 */

function formValidate2(fieldIds, fieldLabels, validateType) {
    var fieldIdArr = fieldIds.split(";");
    var fieldLabelArr = fieldLabels.split(";");
    exceptionValidate(fieldIdArr, fieldLabelArr);
    var retVal = true;
    var fieldId = "";
    var fieldType = "";
    var fieldValue = "";
    var items = null;
    var minLength = -1;
    var maxLength = -1;
    var idCount = fieldIdArr.length;
    var alertMsg = "";
    var dotNum = -1;
    if (index != -1) {
        var index = findIndexOfArr(VALIDATE_TYPE_ARR, validateType);
        var funName = VALIDATE_TYPE_ARR[index];
        for (var i = 0; i < idCount; i++) {
            fieldId = fieldIdArr[i];
            if (isEmpty(fieldId)) {
                continue;
            }
            items = document.getElementById(fieldId);
            if (!items) {
                continue;
            }
            fieldType = items.type;
            fieldValue = items.value;
            if (funName == "isLengthValid") {
                var tempArr = fieldLabelArr[i].split("$");
                if (tempArr.length == 3) {
                    minLength = tempArr[0];
                    maxLength = tempArr[2];
                    fieldLabelArr[i] = tempArr[1];
                    if (minLength > 0 || !isEmpty(fieldValue)) {
                        retVal = eval(funName + "('" + escape(fieldValue) + "', '" + minLength + "', '" + maxLength + "')");
                    }
                } else {
                    alert("�����ҳ��д�ĳ���Ա���÷������ڴ���");
                    retVal = false;
                }
            } else if (funName == "isDouble") {
                var tempArr = fieldLabelArr[i].split("$");
                if (tempArr.length == 2) {
                    fieldLabelArr[i] = tempArr[0];
                    dotNum = Number(tempArr[1]);
                    if (!isEmpty(fieldValue)) {
                        retVal = eval(funName + "('" + escape(fieldValue) + "', '" + dotNum + "')");
                    }
                } else {
                    alert("�����ҳ��д�ĳ���Ա���÷������ڴ���");
                    retVal = false;
                }
            } else if (funName == "isFormatDate") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "', '" + DEFAULT_DATE_PATTERN + "')");
                }
            } else if (funName == "isPositiveNumber") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else if (funName == "isPositiveInteger") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else if (funName == "isDiscount") {
                if (!isEmpty(fieldValue)) {
                    retVal = eval(funName + "('" + escape(fieldValue) + "')");
                }
            } else {
                if (funName == "isEmpty") {//
                    retVal = !eval(funName + "('" + escape(fieldValue) + "')")
                } else {
                    if (!isEmpty(fieldValue)) {
                        retVal = eval(funName + "('" + escape(fieldValue) + "')");
                    }
                }
            }
            if (!retVal) {
                if (funName == "isLengthValid") {
                    if (minLength == 0) {
                        alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index] + "��󲻳�����" + maxLength + "��";
                    } else {
                        alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index] + "��СΪ��" + minLength + "�������Ϊ��" + maxLength + "��";
                    }
                } else {
                    if (funName == "isDouble") {
                        ALERT_MSG_ARR[index] = replaceStr(ALERT_MSG_ARR[index], "$", "��" + dotNum + "��");
                    } else if (funName == "isFormatDate") {
                        ALERT_MSG_ARR[index] = replaceStr(ALERT_MSG_ARR[index], "$", "��" + DEFAULT_DATE_PATTERN + "��");
                    }
                    alertMsg = ALERT_MAG_HEAD + "��" + fieldLabelArr[i] + "��" + ALERT_MSG_ARR[index];
                }
                if (funName == "isEmpty") {
                    if (fieldType.indexOf("select") == -1) {
                        alertMsg = alertMsg.replace("��ѡ��", "");
                    } else {
                        alertMsg = alertMsg.replace("��д��", "");
                    }
                }
                alert(alertMsg);
                if (fieldType != "checkbox" && fieldType != "radio" && fieldType.indexOf('select') == -1) {
                    items.value = "";
                    items.select();
                    items.focus();
                } else {
                    items.focus();
                }
                break;
            } else {
                items.value = trim(fieldValue);
            }
        }
    }
    return retVal;
}

/**
 * ���ܣ������ڲ��������쳣��飬���ṩ������Աʹ��
 *
 */
function exceptionValidate(arr1, arr2) {
    if (arr1.length != arr2.length || arr2 == null) {
        alert("����Ĳ�������ԣ�����㿴���˶Ի����뽫����Ϣ���߿�����Ա");
        return false;
        //�׸�����Ա����Ϣ�����쳣���
    }
}

//===============================================================================================
//�ڰ˲��֣�Ϊ����ֵ
//===============================================================================================

/**
 * ���ܣ�����Ϊ����ֵ�����ó��ϣ���Ҫ�鿴ĳ����¼��ϸ��Ϣʱ��
 * ��̨��������com.sino.base.web.FormData����������Ҫ�÷���ִ��
 * ��ҳ�棬����Ա��Ҫִ�����ֲ�����һ���Ǻ�̨���ݵĲ����࣬��FormData��
 * ��һ�ֲ���������Ӧ��ҳ�浼�뱾�����⡣
 *
 */
function initFrmValue() {
    var fieldArr = FORM_FIELD_ENDUE_STR.split(FIELD_SPLITOR);

    var totalFields = fieldArr.length;
    var tmpArr = null;
    var fieldName = "";
    var fieldValue = "";
    var fieldType = "";
    var fieldObj = null;

    for (var i = 0; i < totalFields; i++) {

        tmpArr = fieldArr[i].split(FIELD_NAME_VALUE_SPLITOR);
        if (tmpArr.length >= 2) {
            fieldName = tmpArr[0];
            fieldValue = tmpArr[1];
            //����ΰ11.20�޸ģ����Ԫ����������
            fieldObj = document.getElementsByName(fieldName);

            if (fieldObj.length > 0) {
                if (fieldObj.length && fieldObj[0].type) {
                    fieldType = fieldObj[0].type;
                } else {
                    fieldType = fieldObj.type;
                }
                if (fieldType == "checkbox") {
                    makeBoxChecked(fieldName, fieldValue);
                } else if (fieldType == "radio") {
                    makeRadioChecked(fieldName, fieldValue);
                } else if (fieldType.indexOf("select") > -1) {
                    selectSpecialOption(fieldName, fieldValue);
                } else {
                    fieldObj[0].value = fieldValue;
                }
                //alert(fieldName+"--->"+fieldValue);
                //fieldObj.value = fieldValue;
            }
        }
    }
}

/**
 *����:���ñ�������Ԫ�ز�����
 *����:����
 */
function setFrmDisable(frm) {
    var frmObj = eval('document.' + frm);
    for (var i = 0; i < frmObj.length; i++) {
        frmObj.elements[i].disabled = true;
    }
}

/**
 *����:���ñ�������Ԫ�ؿ���
 *����:����
 */
function setFrmEnable(frm) {
    var frmObj = eval('document.' + frm);
    for (var i = 0; i < frmObj.length; i++) {
        document.form1.elements[i].disabled = false;
    }
}
/**
 * ���ܣ���ָ��������ʾҳ��Ԫ��pageObj��altֵ��
 * divId�����ID�ţ�
 * pageObj��ҳ��Ԫ�ض���
 *
 */
function showObjValue(divId, pageObj, addText) {
    var divObj = document.getElementById(divId);
    var top = pageObj.offsetTop;
    //TT�ؼ��Ķ�λ���
    var height = pageObj.clientHeight;
    //TT�ؼ�����ĸ�
    var left = pageObj.offsetLeft;
    //TT�ؼ��Ķ�λ���
    var objType = pageObj.type;
    //TT�ؼ�������
    while (pageObj = pageObj.offsetParent) {
        top += pageObj.offsetTop;
        left += pageObj.offsetLeft;
    }
    divObj.style.top = top + height;
    if (objType != "image") {
        divObj.style.top = top + height + 6;
    }
    if (left > 400) {
        left = 400;
    }
    divObj.style.left = left;
    //��� X ����
    divObj.style.display = "block";
    //����ʾ
    if (addText != "") {
        divObj.innerText = addText + "��" + event.srcElement.alt;
    } else {
        divObj.innerText = event.srcElement.alt;
    }
}


/**
 * ���ܣ�������ѡ����ɾ����
 * tableId�����ID��
 * radioName����ѡ��ť���ơ�
 *
 */
function delTableLine(tableId, radioName) {
    var radioCount = getRadioCount(radioName);
    var hasChecked = false;
    if (radioCount > 1) {
        var radioObj = null;
        for (var i = 0; i < radioCount; i++) {
            radioObj = document.all[radioName][i];
            if (radioObj.checked) {
                document.all(tableId).deleteRow(i);
                if (i > 0 && document.all[radioName][i - 1]) {
                    document.all[radioName][i - 1].checked = true;
                }
                hasChecked = true;
                break;
            }
        }
    } else if (radioCount == 1) {
        if (document.all[radioName].checked || document.all[radioName][0].checked || document.getElementById(radioName).checked) {
            document.all[tableId].deleteRow(0);
            hasChecked = true;
        }
    }
    if (!document.all[radioName]) {         //���ɾ����ȫ�����У��򽫼������声
        count1 = 0;
    }
    if (!hasChecked) {
        alert("û��ѡ����ɾ�����С�");
    }
}


/**
 * ���ܣ�������ѡ����ɾ����
 * tableId�����ID��
 * trName:  ��ID��ǰ׺
 * checkboxName����ѡ�����ơ�
 */
function deleteLines(tableId, trName, checkboxName) {
    var mtlTable = document.getElementById(tableId);
    var count = mtlTable.rows.length;
    if (count == 0) {
        alert("������Ҫɾ�����С�");
        return;
    }
    var selectedRows = getCheckedBoxCount(checkboxName);
    if (selectedRows < 1) {
        alert("����ѡ��Ҫɾ�����У�");
        return;
    }
    if (confirm("ȷ��Ҫɾ��ѡ�е�����")) {
        for (var i = count - 1; i >= 0; i--) {
            var rowObj = mtlTable.rows[i];
            var trId = rowObj.id;
            var lastNumOfId = trId.substring(trName.length, trId.length);
            if (document.getElementById(checkboxName + lastNumOfId).checked) {
                mtlTable.deleteRow(i);
            }
        }
    }
}
/**
 * ���ܣ����ڼ����ϸ��Ϣչʾ���޸�ʱѡ�еļ�¼����
 * checkboxName�������¼�ĸ�ѡ������
 *
 */
function validateDetailCheck(checkboxName) {
    var retVal = true;
    var checkedCount = getCheckedBoxCount(checkboxName);
    if (checkedCount != 1) {
        alert(UPDATE_MSG);
        retVal = false;
    }
    return retVal;
}

/**
 * ���ܣ����ڼ��ɾ��ʱѡ�еļ�¼����
 * checkboxName�������¼�ĸ�ѡ������
 *
 */
function validaeDeleteCheck(checkboxName) {
    var retVal = true;
    var checkedCount = getCheckedBoxCount(checkboxName);
    if (checkedCount == 0) {
        alert(DELETE_CHECK_MSG);
        retVal = false;
    }
    return retVal;
}

/**
 * ���ܣ�����ȷ���Ƿ�ɾ����
 *
 */
function confirmDelete() {
    var retVal = false;
    if (confirm(DELETE_MSG)) {
        retVal = true;
    }
    return retVal;
}

/**
 * ���ܣ�����ȷ���Ƿ�������ι�����
 *
 */
function confirmCancel() {
    var retVal = false;
    if (confirm(CANCEL_MSG)) {
        retVal = true;
    }
    return retVal;
}

/**
 * ���ܣ���һ�����е���ֵ���Ƶ���һ��ͬ�����С�
 *
 */
function copyFrmValue(srcForm, objForm) {
    var srcFrm = srcForm;
    if (!srcFrm.name) {
        srcFrm = document.forms[srcForm];
    }
    if (!srcFrm.name) {
        srcFrm = document.getElementById[srcForm];
    }
    var objFrm = objForm;
    if (!objFrm.name) {
        objFrm = document.forms[objForm];
    }
    if (!objFrm.name) {
        objFrm = document.getElementById[objForm];
    }
    if (!srcFrm || !objFrm) {
        alert("����Ĳ����Ƿ���������ָ���ı�����");
        return;
    }
    var srcFields = srcFrm.all;
    var objFields = objFrm.all;
    var srcField = null;
    var srcType = "";
    var fieldName = "";
    var standardCopyType = new Array("text", "textarea", "password", "hidden", "select");
    for (var i = 0; i < srcFields.length; i++) {
        srcField = srcFields[i];
        srcType = srcField.type;
        fieldName = srcField.name;
        if (!srcType || !isExistInArr(standardCopyType, srcType, true) && srcType.indexOf("select") == 1) {
            continue;
        }
        if (!objFrm.elements(fieldName)) {
            continue;
        }
        objFrm.elements(fieldName).value = srcField.value;
    }
}

/**
 * ���ܣ��Զ��ϲ����������е�text��ͬ��cells��
 * tableID ���ID
 * columnNumber ��Ҫ�ϲ�������������ʼ
 */
function autoSpan(tableID, columnNumber) {
    var obj = document.all[tableID];
    var head = new Array(columnNumber);
    var rows = obj.rows.length; 
    if (rows > 1) {
        var cols = obj.rows[0].cells.length;
        for (j = 0; j < columnNumber; j++) {
            head[j] = obj.rows[0].cells[j].innerText;
        }
        for (i = 1; i < rows; i++) {
            var flag = 0;
            for (j = 0; j < columnNumber; j++) {
                if (head[j] == obj.rows[i].cells[j].innerText && flag == 0) {
                    obj.rows[i].cells[j].innerText = "";
                } else {
                    flag = 1;
                    head[j] = obj.rows[i].cells[j].innerText;
                    //continue;
                }
            }
        }
        for (j = columnNumber; j >= 0; j--) {
            var cell = obj.rows[0].cells[j];
            var span = 1;
            for (i = 1; i < rows; i++) {
                if (obj.rows[i].cells[j].innerText == "") {
                    span++;
                    obj.rows[i].deleteCell(j);
                    cell.rowSpan = span;
                } else {
                    cell = obj.rows[i].cells[j];
                    span = 1;
                }
            }
        }
    }
}

/**
 * ���ܣ��Զ��ϲ����������е�text��ͬ��cells��
 * tableID ���ID
 * columnNumber ��Ҫ�ϲ�������������ʼ
 */
function autoSpan2(tableID, columnNumber , rows ) {
    var obj = document.all[tableID];
    var head = new Array(columnNumber);
    //var rows = obj.rows.length; 
    if (rows > 1) {
        var cols = obj.rows[0].cells.length;
        for (j = 0; j < columnNumber; j++) {
            head[j] = obj.rows[0].cells[j].innerText;
        }
        for (i = 1; i < rows; i++) {
            var flag = 0;
            for (j = 0; j < columnNumber; j++) {
                if (head[j] == obj.rows[i].cells[j].innerText && flag == 0) {
                    obj.rows[i].cells[j].innerText = "";
                } else {
                    flag = 1;
                    head[j] = obj.rows[i].cells[j].innerText;
                    //continue;
                }
            }
        }
		for (j = 0; j < columnNumber; j++) {
        //for (j = columnNumber; j >= 0; j--) {
            var cell = obj.rows[0].cells[j];
            var span = 1;
            for (i = 1; i < rows; i++) {
                if (obj.rows[i].cells[j].innerText == "") {
                    span++;
                    obj.rows[i].deleteCell(j);
                    cell.rowSpan = span;
                } else {
                    cell = obj.rows[i].cells[j];
                    span = 1;
                }
            }
        }
    }
}

/**
 * ���ܣ��Զ��ϲ����������е�text��ͬ��cells��
 * tableID ���ID
 * fromNumber �ϲ���ʼ��������
 * toNumber �ϲ�������������
 */
function autoFromToSpan(tableId, fromNumber, toNumber) {
    var tab = document.getElementById(tableId);
    var head = new Array(toNumber - fromNumber + 1);
    var rows = tab.rows.length;
    if (rows > 1) {
        var cols = tab.rows[0].cells.length;
        for (var j = fromNumber; j <= toNumber; j++) {
            head[j - fromNumber] = getCellValue(tab.rows[0].cells[j - 1]);
            
        }
        for (var i = 1; i < rows; i++) {
            var flag = 0;
            for (var j = fromNumber; j <= toNumber; j++) {
                if (head[j - fromNumber] == getCellValue(tab.rows[i].cells[j - 1]) && flag == 0) {
                    tab.rows[i].cells[j - 1].innerHTML = "";
                } else {
                    flag = 1;
                    head[j - fromNumber] = getCellValue(tab.rows[i].cells[j - 1]);
                }
            }
        }
        
        for (var j = toNumber; j >= fromNumber; j--) {
            var cell = tab.rows[0].cells[j - 1];
            var span = 1;
            for (var i = 1; i < rows; i++) {
                if (tab.rows[i].cells[j - 1].innerHTML == "") {
                    span++;
                    tab.rows[i].deleteCell(j - 1);
                    cell.rowSpan = span;
                } else {
                    cell = tab.rows[i].cells[j - 1];
                    span = 1;
                }
            }
        }
        document.write(tab.innerHTML);
    }
}
/**
 * ���ܣ��õ������ָ���е�ֵ
 * cellObj������е��ж���
 */
function getCellValue(cellObj) {
    var cellValue = '';
    if (cellObj.childNodes.length > 0) {
        if (typeof(cellObj.childNodes[0].value) == "undefined") {
            cellValue = cellObj.innerText;
        } else {
            cellValue = cellObj.childNodes[0].value;
        }
    }
    return cellValue;
}
/**
 * ���ܣ����ı�������onkeydown�¼����ո��ı���ֻ�����븡����
 * value ��ֵ
 * ��Ӧ�������û�����ɾ������.
 */
function floatOnly(value) {
    if ((event.keyCode == 110) | (event.keyCode == 190)) {
        if (value.match(/\.\d*/g, '.')) {
            event.returnValue = false;
        }
    }
    if (event.keyCode == 45) {
        event.returnValue = false;
    }
    if (((event.keyCode >= 48) & (event.keyCode <= 57)) | ((event.keyCode <= 105) & (event.keyCode >= 96)) | (event.keyCode == 110) | (event.keyCode == 190) | (event.keyCode == 8) | (event.keyCode == 37) | (event.keyCode == 39))
    //               0             9               9             0               .             .            backspace           left         right
    {
    } else if (event.keyCode == 46) {
        event.returnValue = true;
    } else {
        event.returnValue = false;
    }
}

/**
 * ���ܣ����ı�������onkeyup�¼����ո��ı���ֻ�����븡����
 * value ��ֵ
 * ��Ӧ�������û�����ɾ������.
 */
function floatOnly(obj, value) {
    var result = value;
    var strTemp = "0123456789,.";
    for (var i = 0; i < result.length; i++)
    {
        var j = strTemp.indexOf(result.charAt(i));
        if (j == -1)
        {
            result = result.substring(0, i) + result.substring(i, result.length - 1);
            i--;
        }
    }
    obj.value = result
}

/**
 * ���ܣ����ı���onkeyup/onblur�¼������ı��������븡����������, ��֧��ǧλ�ָ���","��ʽ
 * bFlag��true--��ǧλ�ָ�����ʽ��ʾ��false--����ǧλ�ָ�����ʽ��ʾ
 * �������ݿ�ʱ��StrUtil.replaceStr(value,",","",true) ��ǧλ�ָ���ȥ��
 */


function autoSplit(obj, value, bFlag) {   
    var result = value;
    var strTemp = "0123456789.,";
    var num = 0;
    for (var i = 0; i < result.length; i++){
         var j = strTemp.indexOf(result.charAt(i));
         if (j == -1){
             result = result.substring(0, i) + result.substring(i, result.length - 1);
             i--;
         }
         if(result.charAt(i)==","){
                result = result.substring(0, i) + result.substring(i + 1, result.length);
         }
         if(result.charAt(i)=="."){    //С�����������С�����ǧλ�ָ���
            if(i==0){
               result = "";
            }
            var n = result.substring(i + 1, result.length).indexOf(".");
            if (n != -1){
                result = result.substring(0, i + n + 1);
            }
            n = result.substring(i + 1, result.length).indexOf(",");
            if (n != -1){
                result = result.substring(0, i + n + 1);
            }
            for (var j = i+1; j < result.length; j++){         //ȥ��С�������ķǷ�����
                 var n = strTemp.indexOf(result.charAt(j));
                 if (n == -1){
                   result = result.substring(0, j) + result.substring(j, result.length - 1);
                   j--;
                 }
               }
            if((i==result.length-1)&&(event.type=="blur")){     // ����������С���������onblur�¼�
                result = result.substring(0,i);
            }
            num = i;
            break;
        } else{
                num = result.length;
          }
    }

    if(bFlag){         //��Ҫǧλ�ָ���
        if((num > 3)&&(num < 7)){
             result = result.substring(0,num - 3) + "," + result.substring(num - 3,num) + result.substring(num,result.length);
        } else if((num > 6)&&(num < 10)){
             result = result.substring(0,num - 6) + "," + result.substring(num - 6,num - 3) + "," + result.substring(num - 3,num) + result.substring(num,result.length);
        } else if((num > 9)&&(num < 13)){
             result = result.substring(0,num - 9) + "," + result.substring(num - 9,num - 6) + "," + result.substring(num - 6, num - 3) + "," + result.substring(num - 3,num) + result.substring(num,result.length);
        } else if((num > 12)&&(num < 16)){
             result = result.substring(0,num - 12) + "," + result.substring(num - 12,num - 9) + "," + result.substring(num - 9,num - 6) + "," + result.substring(num - 6, num - 3) + "," + result.substring(num - 3,num) + result.substring(num,result.length);
        }
   }
    obj.value = result
}
/**
 * ���ܣ�����enter��ʱ�Զ�ִ��ָ���޲κ�����
 * ���磺autoExeFunction(do_selectVendors());
 */
function autoExeFunction(functionName) {
    if (event.keyCode == 13) {
        eval(functionName);
    }
}

/**
 * ���ܣ������¼�ʱ�Զ������������һ������
 *      ������м���ɾ���������ɾ���е�λ�ò������ã��д��Ľ�
 */
function moveCursor(objName, id) {
    var obj = document.getElementById(objName + id);
    var nextId = -1;
    var tempObj;
    //    alert("event.keycode="+event.keyCode+"!")
    if (event.keyCode == 38) { //up
        nextId = Number(id) - 1;
    } else if (event.keyCode == 40) { //down
        nextId = Number(id) + 1;
    }
    //    alert("nextId="+nextId)
    if (nextId >= 0) {
        tempObj = document.getElementById(objName + nextId);
        if (tempObj) {
            tempObj.focus();
        }
    }
}

/**************************************************���(�����)��Сдת��*******************************************/

/**
 * ���ܣ������ֽ��ת��Ϊ��д���Ľ�
 */
function toChineseCapital(num)
{
    if (isNaN(num) || num > Math.pow(10, 12))   return   ""
    var cn = "��Ҽ��������½��ƾ�"
    var unit = new Array("ʰ��Ǫ", "�ֽ�")
    var unit1 = new Array("����", "")
    var numArray = num.toString().split(".")
    var start = new Array(numArray[0].length - 1, 2)

    function toChinese(num, index)
    {
        var num = num.replace(/\d/g, function   ($1)
        {
            return   cn.charAt($1) + unit[index].charAt(start-- % 4 ? start % 4 : -1)
        })
        return   num
    }

    for (var i = 0; i < numArray.length; i++)
    {
        var tmp = ""
        for (var j = 0; j * 4 < numArray[i].length; j++)
        {
            var strIndex = numArray[i].length - (j + 1) * 4
            var str = numArray[i].substring(strIndex, strIndex + 4)
            var start = i ? 2 : str.length - 1
            var tmp1 = toChinese(str, i)
            tmp1 = tmp1.replace(/(��.)+/g, "��").replace(/��+$/, "")
            tmp1 = tmp1.replace(/^Ҽʰ/, "ʰ")
            tmp = (tmp1 + unit1[i].charAt(j - 1)) + tmp
        }
        numArray[i] = tmp
    }

    numArray[1] = numArray[1] ? numArray[1] : ""
    numArray[0] = numArray[0] ? numArray[0] + "Բ" : numArray[0],numArray[1] = numArray[1].replace(/^��+/, "")
    numArray[1] = numArray[1].match(/��/) ? numArray[1] : numArray[1] + "��"
    return   numArray[0] + numArray[1]
}

function toNumberCase(num)
{
    var numArray = new Array()
    var unit = "����Բ$"
    for (var i = 0; i < unit.length; i++)
    {
        var re = eval("/" + (numArray[i - 1] ? unit.charAt(i - 1) : "") + "(.*)" + unit.charAt(i) + "/")
        if (num.match(re))
        {
            numArray[i] = num.match(re)[1].replace(/^ʰ/, "Ҽʰ")
            numArray[i] = numArray[i].replace(/[��Ҽ��������½��ƾ�]/g, function   ($1)
            {
                return   "��Ҽ��������½��ƾ�".indexOf($1)
            })
            numArray[i] = numArray[i].replace(/[�ֽ�ʰ��Ǫ]/g, function   ($1)
            {
                return   "*" + Math.pow(10, "�ֽ�   ʰ��Ǫ   ".indexOf($1) - 2) + "+"
            }).replace(/^\*|\+$/g, "").replace(/��/, "0")
            numArray[i] = "(" + numArray[i] + ")*" + Math.ceil(Math.pow(10, (2 - i) * 4))
        }
        else   numArray[i] = 0
    }
    return   eval(numArray.join("+"))
}

function getpopscript(widthParm,heightParm)
 {
     var width=800;
     var height=580;
     if (arguments.length==2) {
        width=widthParm;
        height=heightParm
     }
     var left = (window.screen.availWidth-10-width )/2;
     var top = (window.screen.availHeight-30-height )/2;
     var popscript ='width=' +width+ ',height=' +height+ ',left=' +left+ ',top=' +top+','+'toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=yes';
     return popscript;
 }