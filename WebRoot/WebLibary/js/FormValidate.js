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
 */
function formValidate(fieldNames, fieldLabels, validateType) {
    var retVal = true;
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
		for(var i = 0; i < fieldNameArr.length; i++){
			fieldName = fieldNameArr[i];
			var fieldObjs = document.getElementsByName(fieldName);
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
	            fieldValue = fieldObj.value;
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