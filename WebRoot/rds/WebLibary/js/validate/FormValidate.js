/**
 * @author mshtang
 */

//===============================================================================================
//�ھŲ��֣�������Ϊ����֤����Ҫ�ĺ���
//===============================================================================================

/**
 * ���ܣ����Ҫ�Ա����������У�飬�ɲ��ô˷�������ϣ�������ڸ�ʽ��������һ�����ڸ�ʽ��Ч��
 * datePattern�����ڸ�ʽ
 */
function setDatePattern(datePattern) {
    if (isValidDatePattern(datePattern)) {
        DEFAULT_DATE_PATTERN = datePattern.toUpperCase();
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
 * ���ܣ���Ч�Լ�飬����true��ʾͨ��У�飬false��ʾ�зǷ�������ڡ����������ڶ������ݱ�������ͬ�������
 * @param {Object} fieldNames У��������ƣ����֮������԰�Ƿֺ�����
 * @param {Object} fieldLabels У��������ı�ʶ�����֮������԰�Ƿֺ����ӡ�
 * @param {Object} validateType У�����ͣ�һ�ν���ִ��һ��У�飬Ŀǰ֧�ֵ�У�����ͼ�ǰ�泣�����岿��˵����
 * @param {Object} namePrefix ���ӶԶ���ͬ������У���֧�֡�
 */
function formValidate(fieldNames, fieldLabels, validateType, namePrefix) {
    var retVal = true;
    if(namePrefix == null || namePrefix == undefined){
        namePrefix = "";
    }
    var fieldNameArr = fieldNames.split(";");
    var fieldLabelArr = fieldLabels.split(";");
    if(!exceptionValidate(fieldNameArr, fieldLabelArr)){
		retVal = false;
		return retVal;
	}
	var fieldName = "";
    var fieldType = "";
    var fieldValue = "";
    var fieldObj = null;
    var minLength = -1;
    var maxLength = -1;
    var alertMsg = "";
    var dotNum = -1;
	var hasBreaked = false;
	var hasError = false;
	var index = findIndexOfArr(VALIDATE_TYPE_ARR, validateType);
	if(index != -1){
		var funName = VALIDATE_TYPE_ARR[index];
        var fieldObjs = null;
        for(var i = 0; i < fieldNameArr.length; i++){
            fieldName = fieldNameArr[i];
            if(namePrefix == ""){
                fieldObjs = document.getElementsByName(fieldName);
            } else {
                fieldObjs = getElementsByName(fieldName, namePrefix);
            }
			if(!fieldObjs || !fieldObjs.length || fieldObjs.length == 0){
				hasError = true;
				retVal = false;
				alertMsg = "�����ı�������"
				 	+ fieldName
				  	+ "�������ڣ�����ҳ�桰"
				   	+ location.href
					+ "�����Servlet�����ҳ�档";
					break;
			}
			for(var j = 0; j < fieldObjs.length; j++){
                fieldObj = fieldObjs[j];
	            fieldType = fieldObj.type;
//	            fieldValue = fieldObj.value;
	            fieldValue = getFieldValue(fieldObj);
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
                        fieldObj.value = "";
	                    fieldObj.select();
	                    fieldObj.focus();
	                } else {
//                        fieldObj.select();
	                    fieldObj.focus();
	                }
					hasBreaked = true;
	                break;
	            } else {
                    fieldObj.value = trim(fieldValue);
                }
			}
			if(hasBreaked){
				break;
			}
		}
		if(hasError){
			alert(alertMsg);
		}
	}
    return retVal;
}


/**
 * ���ܣ������ڲ��������쳣��飬���ṩ������Աʹ��
 *
 */
function exceptionValidate(arr1, arr2) {
	var isValid = true;
	if(arr1 == "undefined"){
		isValid = false;
	} else if(arr2 == "undefined"){
		isValid = false;
	} else if(arr1 == null || arr2 == null) {
        isValid = false;
    } else if(arr1.length != arr2.length){
		alert("����Ĳ�������ԣ�����㿴���˶Ի����뽫����Ϣ���߿�����Ա");
		isValid = false;
	}
	return isValid;
}

/**
 *��������ı��ĳ���,����ľ�Ϊ����,һ������Ҳ���봫������
**/
function valdateLength(fieldNames, fieldLabels, fieldLength){
    var isValid = true;
    for (var i = 0; i < fieldNames.length; i++) {
        if (document.getElementById(fieldNames[i]).value.length > fieldLength[i]) {
            alert(fieldLabels[i] + "�ĳ��Ȳ��ܳ���" + fieldLength[i] + "�ַ�!");
            isValid = false;
            break;
        }
    }
    return isValid;
}

function getElementsByName(fieldName, namePrefix){
    var frmObjs = new Array();
    var objs1 = document.getElementsByTagName("input");
    var objs2 = document.getElementsByTagName("textarea");
    var objs3 = document.getElementsByTagName("select");
    var objName = "";
    var tmpRightName = "";
    var fieldIndex = -1;
    if(objs1 != null && objs1 != undefined && objs1.length > 0){
        for(var i = 0; i < objs1.length; i++){
            if(objs1[i].type == "hidden"){
                continue;
            }
            objName = objs1[i].name;
            fieldIndex = objName.indexOf(fieldName);
            if(fieldIndex > -1){
                tmpRightName = objName.substr(fieldIndex, fieldName.length);
                if (objName.indexOf(namePrefix) == 0 && tmpRightName == fieldName){
                    frmObjs.push(objs1[i]);
                } else if(objName.indexOf(namePrefix) == -1 && objName == fieldName){
                    frmObjs.push(objs1[i]);
                }
            }
        }
    }
    if(objs2 != null && objs2 != undefined && objs2.length > 0){
        for(i = 0; i < objs2.length; i++){
            objName = objs2[i].name;
            fieldIndex = objName.indexOf(fieldName);
            if(fieldIndex > -1){
                tmpRightName = objName.substr(fieldIndex, fieldName.length);
                if (objName.indexOf(namePrefix) == 0 && tmpRightName == fieldName){
                    frmObjs.push(objs2[i]);
                } else if(objName.indexOf(namePrefix) == -1 && objName == fieldName){
                    frmObjs.push(objs2[i]);
                }
            }
        }
    }
    if(objs3 != null && objs3 != undefined && objs3.length > 0){
        for(i = 0; i < objs3.length; i++){
            objName = objs3[i].name;
            fieldIndex = objName.indexOf(fieldName);
            if(fieldIndex > -1){
                tmpRightName = objName.substr(fieldIndex, fieldName.length);
                if (objName.indexOf(namePrefix) == 0 && tmpRightName == fieldName){
                    frmObjs.push(objs3[i]);
                } else if(objName.indexOf(namePrefix) == -1 && objName == fieldName){
                    frmObjs.push(objs3[i]);
                }
            }
        }
    }
   return frmObjs;
}

/**
 * ���ܣ�������и��������Ƿ�����ظ�
 * @param tab ������
 * @param fieldNames �����ı�����������ǰ׺
 * @param fieldLabels �����ı�������
 * @param checkUnion �Ƿ���Ϊ�����������
 */
function valiadteUnique(tab, fieldNames, fieldLabels, checkUnion){
    if (!tab) {
        alert("�����󲻴��ڣ�ϵͳ�޷�����");
        return false;
    }
    if (!tab.rows) {
        alert("������û�вο����ݣ�ϵͳ�޷�����");
        return false;
    }
    if(fieldNames == null || fieldNames == undefined){
        alert("δ���ô���֤��������ϵͳ�޷�����");
        return false;
    }
    if(fieldLabels == null || fieldLabels == undefined){
        alert("δ���ô���֤����������ϵͳ�޷�����");
        return false;
    }
    var fieldNameArr = fieldNames.split(";");
    var fieldLabelArr = fieldLabels.split(";");
    if(fieldNameArr.length != fieldLabelArr.length){
        alert("�������������������������һ�£�ϵͳ�޷�����");
        return false;
    }
    if(checkUnion == null || checkUnion == undefined){
        checkUnion = false;
    }
    var rows = tab.rows;
    if(checkUnion){
        for(var i = 0; i < rows.length; i++){
            var firstTr = rows[i];
            for(var j = i + 1; j < rows.length; j++){
                var secondTr = rows[j];

                var firstValue = "";
                var secondValue = "";
                var labelValue = "";
                for(var k = 0; k < fieldNameArr.length; k++){
                    var fieldName = fieldNameArr[k];
                    labelValue += fieldLabelArr[k] + ";";
                    var firstField = getTrNode(firstTr, fieldName);
                    var secondField = getTrNode(secondTr, fieldName);
                    firstValue += firstField.value + ";";
                    secondValue += secondField.value + ";";
                }
                if(firstValue == secondValue){
                    alert("����Ƿ���ԭ���ǵ�" + Number(i + 1) + "�е�" + labelValue + "��" + "��" + Number(j + 1) + "�е�" + labelValue + "�ظ���");
                    firstTr.style.backgroundColor = "#336699";
                    secondTr.style.backgroundColor = "#336699";
                    return false;
                }
            }
        }
    } else {
        for(i = 0; i < rows.length; i++){
            firstTr = rows[i];
            for(j = i + 1; j < rows.length; j++){
                secondTr = rows[j];

                firstValue = "";
                secondValue = "";
                labelValue = "";
                for(k = 0; k < fieldNameArr.length; k++){
                    fieldName = fieldNameArr[k];
                    labelValue = fieldLabelArr[k] + ";";
                    firstField = getTrNode(firstTr, fieldName);
                    secondField = getTrNode(secondTr, fieldName);
                    firstValue = firstField.value + ";";
                    secondValue = secondField.value + ";";
                    if(firstValue == secondValue){
                        alert("����Ƿ���ԭ���ǵ�" + Number(i + 1) + "�е�" + labelValue + "��" + "��" + Number(j + 1) + "�е�" + labelValue + "�ظ���");
                        firstTr.style.backgroundColor = "#336699";
                        secondTr.style.backgroundColor = "#336699";
                        return false;
                    }
                }
            }
        }
    }
    return true;
}

function getFieldValue(fieldObj){
    var returnValue = "";
    if(fieldObj){
        var fieldType = fieldObj.type;
        if(fieldType == "text" || fieldType == "hidden" || fieldType == "password" || fieldType == "submit"){
            returnValue = fieldObj.value;
        } else if (fieldType == "checkbox"){
            returnValue = getCheckBoxValue(fieldObj.name, ",");
        } else if (fieldType == "radio"){
            returnValue = getRadioValue(fieldObj.name);
        } else if (fieldType.indexOf('select') > -1) {
//            returnValue = getSelectedValue(fieldObj);
            returnValue = fieldObj.value;
        } else if(fieldObj.tagName.toLowerCase() == "textarea"){
            returnValue = fieldObj.value;
        }
    }
    return returnValue;
}