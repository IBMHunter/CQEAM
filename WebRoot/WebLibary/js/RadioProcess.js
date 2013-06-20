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
function getCheckedRadioIndex(radioName) {
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
