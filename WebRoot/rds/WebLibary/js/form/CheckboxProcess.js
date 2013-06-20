/**
 * @author mshtang
 */

//===============================================================================================
//���岿�֣����´���ѡ�����
//===============================================================================================

/**
 * ���ܣ���һ�������Ը�ѡ��checkBoxName������һ�鸴ѡ��checkBoxGroup
 * checkBoxName�������Ը�ѡ�������

 * checkBoxGroup���������Ը�ѡ�������

 */
function checkAll(checkBoxName, checkBoxGroup) {
	var objs = document.all[checkBoxName];
	if(!objs){
		return;
	}
	var checkAttr = true;
	if(objs.length){
		var obj = null;
		for(var i = 0; i < objs.length; i++){
			obj = objs[i];
			if(obj.type == "checkbox"){
				checkAttr = obj.checked;
				break;
			}
		}
	} else {
		checkAttr = objs.checked;
	}
    try {
        if (!setCheckBoxState(checkBoxGroup, checkAttr)) {
            document.all[checkBoxName].checked = !checkAttr;
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
        var i = 0;
        var chkObj = null;
        if (groupCount > 200) {
            if (confirm("����������ȫѡ������Ҫ�ϳ�ʱ�䣬�Ƿ����������������ȷ������ť������������ȡ������ť��")) {
                for (i = 0; i < groupCount; i++) {
                    chkObj = checkGroup[i];
                    if (chkObj.type == "checkbox" && !chkObj.disabled) {
                        chkObj.checked = isChecked;
                    }
                }
            } else {
                retVal = false;
            }
        } else {
            for (i = 0; i < groupCount; i++) {
                chkObj = checkGroup[i];
                if (chkObj.type == "checkbox" && !chkObj.disabled) {
                    chkObj.checked = isChecked;
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
    		if(checkboxObj.checked){
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
    		if(allCheckObj.checked){
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