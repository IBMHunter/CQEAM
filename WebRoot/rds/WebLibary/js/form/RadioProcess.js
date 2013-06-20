/**
 * @author mshtang
 */

//===============================================================================================
//�������֣����´���ѡ�����
//===============================================================================================

/**
 * ���ܣ���ȡָ�������ĵ�ѡ���б�ѡ�����ֵ
 * radioName����ѡ�������
 */

//����ΰ 05-09-15 �޸�

function getRadioValue(radioName) {
    var retVal = "";
    var radioBox = document.getElementsByName(radioName);
    if (radioBox) {
        if (radioBox.length) {
            for (var i = 0; i < radioBox.length; i++) {
                if (radioBox[i].type == "radio" && radioBox[i].checked) {
                    retVal = radioBox[i].value;
                    break;
                }
            }
        } else {
            if (radioBox.type == "radio" && radioBox.checked) {
                retVal = radioBox.value;
            }
        }
    }
    return retVal;
}

function getCheckedRadio(radioName) {
    var obj = null;
    var radioBox = document.getElementsByName(radioName);
    if (radioBox) {
        if (radioBox.length) {
            for (var i = 0; i < radioBox.length; i++) {
                if (radioBox[i].type == "radio" && radioBox[i].checked) {
                    obj = radioBox[i];
                    break;
                }
            }
        } else {
            if (radioBox.type == "radio" && radioBox.checked) {
                obj = radioBox;
            }
        }
    }
    return obj;
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


/**
 * ���ܣ���ȡҳ����ָ�����ֵĵ�ѡ��ť�ĸ���
 * radioName����ѡ�������
 */
function getRadioCount(radioName) {
    var radioBox = document.getElementsByName(radioName);
    var retVal = 0;
    try {
        if (radioBox) {
            if (radioBox.length) {
                retVal = radioBox.length;
            }
        }
    } catch(exception) {
        alert(exception);
    }
    return retVal;
}

/**
 * ���ܣ�ʹָ��ֵ��ָ�����Ƶĵ���ѡ����ѡ��״̬��
 * radioName����ѡ�������
 * val��ָ��ֵ
 */
function makeRadioChecked(radioName, val) {
    var obj = document.getElementsByName(radioName);
    try {
        if (obj) {
            if (obj.length) {
                for (var i = 0; i < obj.length; i++) {
                    if (obj[i].type == "radio" && obj[i].value == val) {
                        obj[i].checked = true;
                        break;
                    }
                }
            } else {
                if (obj.type == "radio" && obj.value == val) {
                    obj.checked = true;
                }
            }
        }
    } catch(exception) {
        alert(exception);
    }
}

/**
 * ���ܣ������ѡ��ť�е�ѡ��״̬��
 * radioName����ѡ�������
 */
function clearRadioChecked(radioName) {
    var obj = document.getElementsByName(radioName);
    try {
        if (obj) {
            if (obj.length) {
                for (var i = 0; i < obj.length; i++) {
                    if (obj[i].type == "radio") {
                        obj[i].checked = false;
                    }
                }
            } else {
                if (obj.type == "radio") {
                    obj.checked = false;
                }
            }
        }
    } catch(exception) {
        alert(exception);
    }
}

/**
 * ���ܣ���ñ�ѡ�еĵ�ѡ���ڵ�ѡ�����е�λ�á�
 * radioName����ѡ�������
 */
function getCheckedRadioIndex(radioName) {
    var radioBox = document.getElementsByName(radioName);
    var retVal = 0;
    if (radioBox) {
        if (radioBox.length) {
            for (var i = 0; i < radioBox.length; i++) {
                if (radioBox[i].type == "radio" && radioBox[i].checked) {
                    retVal = i;
                    break;
                }
            }
        }
    }
    return retVal;
}

function disableRadio(radioName) {
    do_ProcessRadioDisableAttribute(radioName, true);
}

function enableRadio(radioName) {
    do_ProcessRadioDisableAttribute(radioName, false);
}

function do_ProcessRadioDisableAttribute(radioName, disableAttr){
    var radioBox = document.getElementsByName(radioName);
    if (radioBox) {
        for (var i = 0; i < radioBox.length; i++) {
            if (radioBox[i].type == "radio") {
                radioBox[i].disabled = disableAttr;
            }
        }
    }
}
