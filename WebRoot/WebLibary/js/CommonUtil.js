/**
 * @author mshtang
 */
//===============================================================================================
//�ڶ����֣����������ַ�������ز���
//===============================================================================================

/**
 * ���ܣ������µ��ַ�������ֵΪcount��str����
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} count Դ�ַ����ĸ���
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
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} subStr ���ַ���
 */
function getContainNum(srcStr, subStr) {
    var tempStr = "";
    var strCount = 0;
    if (srcStr != "" && subStr != "" && srcStr.length >= subStr.length) {
        var index = -1;
        while ((index = srcStr.indexOf(subStr)) != -1) {
            strCount++;
            srcStr = srcStr.substring(index + subStr.length);
        }
    }
    return strCount;
}

/**
 * ���ܣ��ж�srcStr���Ƿ����subStr
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} subStr ���ַ���
 */
function contains(srcStr, subStr) {
    return (containNum(srcStr, subStr) > 0);
}

/**
 * ���ܣ����ַ���ǰ��ո�ȥ��
 * @param {Object} srcStr Դ�ַ���
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
 * @param {Object} srcStr Դ�ַ���
 */

function isEmpty(srcStr) {
	var retVal = true;
	if(srcStr != "undefined"){
	    srcStr = unescape(srcStr);
		retVal = (srcStr == "" || trim(srcStr) == "");
	}
    return retVal;
}

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�����ȷ��Email.
 * @param {Object} srcStr Դ�ַ���
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
 * ���ܣ��ж�ĳ�ַ����Ƿ�����ȷ���ƶ��绰
 * @param {Object} srcStr Դ�ַ���
 */
function isMobile(srcStr){
    var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
    if (!patrn.exec(srcStr)) return false
    return true
}

 /**
 * ���ܣ�У����ͨ�绰��������룺���ԡ�+����ͷ���������⣬�ɺ��С�-��
 * @param {Object} srcStr Դ�ַ���
 */
function isTelNumber(srcStr)
{
//var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?(\d){1,12})+$/;
var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
if (!patrn.exec(srcStr)) return false
return true
}

/**
 * ���ܣ��ж�ĳ�ַ���str�Ƿ���subStr��ʼ
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} subStr ���ַ���
 * @param {Object} ignoreCase �Ƿ���Դ�Сд
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
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} subStr ���ַ���
 * @param {Object} ignoreCase �Ƿ���Դ�Сд
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
 * @param {Object} srcStr Դ�ַ���
 */
function isNumber(srcStr) {
    return (srcStr != "" && !isNaN(srcStr));
}

/**
 * ���ܣ����ĳ�ַ���srcStr�Ƿ������֣���С������λ����ָ����λ��dotNum֮��
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} dotNum С������λ��
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
 * @param {Object} srcStr Դ�ַ���
 */
function isInt(srcStr) {
    return (isNumber(srcStr) && srcStr % 1 == 0);
}

/**
 * ���ܣ���ʽ��С��,���������빦��
 * @param {Object} srcNum Դ�ַ���
 * @param {Object} pos ����С�����λ����2
 */
function formatNum(srcNum, pos) {
    return Math.round(srcNum * Math.pow(10, pos)) / Math.pow(10, pos);
}


/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�����
 * @param {Object} srcStr Դ�ַ���
 */
function isPositiveNumber(srcStr) {
    return (isNumber(srcStr) && Number(srcStr) > 0);
}

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�������
 * param {Object} srcStr��Դ�ַ���
 */
function isPositiveInteger(srcStr) {
    return (isPositiveNumber(srcStr) && isInt(srcStr));
}


/**
 * ���ܣ��ж�ĳ�ַ����Ƿ���ĳ������
 * @param {Object} srcArr Դ����
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} ignoreCase �Ƿ���Դ�Сд
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
 * @param {Object} srcArr Դ����
 * @param {Object} srcStr Դ�ַ���
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
 * @param {Object} srcStr Դ����
 */
function getNumFromText(srcStr) {
    var retStr = "";
    if (!isEmpty(srcStr)) {
        var strLength = srcStr.length;
        var thisChar = "";
        for (var i = 0; i < strLength; i++) {
            thisChar = srcStr.charAt(i);
            if (isInt(thisChar)) {
                retStr += thisChar;
            }
        }
    }
    return retStr;
}


/**
 * ���ܣ���srcStr�е�����oldStrȫ���滻��newStr
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} oldStr ԭ���ַ���
 * @param {Object} newStr �����ַ���
 */
function replaceStr(srcStr, oldStr, newStr) {
    var retStr = srcStr;
    if(oldStr != ""){
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

/**
 * ���ܣ���srcStr�еĵ�һ��oldStrȫ���滻��newStr
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} oldStr ԭ���ַ���
 * @param {Object} newStr �����ַ���
 */
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
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} maxLength ���������
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
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} minLength ��С������
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
 * @param {Object} srcStr Դ�ַ���
 * @param {Object} minLength ��С������
 * @param {Object} maxLength ���������
 */
function isLengthValid(srcStr, minLength, maxLength) {
    srcStr = unescape(srcStr);
    return (!isLengthExtend(srcStr, maxLength) && !isLengthLess(srcStr, minLength));
}

/**
 * ���ܣ���ָ��ֵ��ӵ�����
 * @param {Object} arr Ŀ������
 * @param {Object} newValue ��ֵ
 */
function add2Arr(arr, newValue) {
    if (arr == null) {
        return;
    }
    arr[arr.length] = newValue;
    return arr;
}

/**
 * ���ܣ���������ָ������Ԫ��ɾ����������ɾ��ֵ
 * @param {Object} arr ����
 * @param {Object} index ����ֵ
 */
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

/**
 * ���ܣ��������е���ָ��ֵ�ĵ�һ��Ԫ��ɾ���������ظ�ֵ��
 * @param {Object} arr ����
 * @param {Object} item ָ��ֵ
 */
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

/**
 * ���ܣ���ָ���ַ����÷ָ��ַ����ָ������
 * @param {Object} srcStr ָ���ַ���
 * @param {Object} spanStr �ָ��ַ���
 */
function explode(srcStr, spanStr) {
    if(srcStr == undefined || srcStr == null || srcStr == ""){
        return null;
    }
    var str = trim(srcStr);
    if (str == "") {
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

/**
 * ���ܣ�������arr��Ԫ����ָ�������ַ�������
 * @param {Object} arr ����
 * @param {Object} spanStr �����ַ�
 */
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
 * @param {Object} array Դ����
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

/**
 * ���ܣ��ж�ĳ�ַ����Ƿ�0��1֮������֡�
 * val��Դ�ַ���
 */
function isDiscount(val) {
    return (isNumber(val) && val > 0 && val <= 1);
}

/**
 * ���ܣ����ı�������onkeyup�¼����ո��ı���ֻ�����븡����
 * value ��ֵ
 * ��Ӧ�������û�����ɾ������.
 */
function floatOnly(obj, value) {
    var result = value;
    var strTemp = "0123456789.";
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

function patchCreateOrUpdate( id ){
	return ( id != "" && id >0 );
}

