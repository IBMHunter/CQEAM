/**
 * @author mshtang
 */

//===============================================================================================
//���߲��֣�����Ϊ������ͨУ����ۺϴ�����
//===============================================================================================

/**
 * ���ܣ���ָ�����Ƶı��ĸ������е�ֵ���и�ʽ������ȥ������ֵǰ��Ŀո�
 * @param {Object} formName ������
 */
function trimForm(formName) {
    var frmObj = eval('document.' + formName);;
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
            }
        }
    }
}

/**
 * ����:���ñ�������Ԫ�ز�����
 * @param {Object} frm ����
 */
function setFrmDisable(frm) {
    var frmObj = eval('document.' + frm);
    for (var i = 0; i < frmObj.length; i++) {
        frmObj.elements[i].disabled = true;
    }
}

/**
 * ����:���ñ�������Ԫ�ؿ���
 * @param {Object} frm ����
 */
function setFrmEnable(frm) {
    var frmObj = eval('document.' + frm);
    for (var i = 0; i < frmObj.length; i++) {
        frmObj.elements[i].disabled = false;
    }
}

/**
 * ���ܣ����ڼ����ϸ��Ϣչʾ���޸�ʱѡ�еļ�¼����
 * @param {Object} checkboxName �����¼�ĸ�ѡ������
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
 * @param {Object} checkboxName �����¼�ĸ�ѡ������
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
 * @param {Object} srcForm Դ��
 * @param {Object} objForm Ŀ���
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
 * ���ܣ����ı�������onkeydown�¼����ո��ı���ֻ�����븡����
 * @param {Object} value ��ֵ
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
 * ���ܣ�����enter��ʱ�Զ�ִ��ָ���޲κ��������磺autoExeFunction(do_selectVendors());
 * @param {Object} functionName
 */
function autoExeFunction(functionName) {
    if (event.keyCode == 13) {
        eval(functionName);
    }
}

/**
 * ���ܣ������¼�ʱ�Զ������������һ�����󡣵�����м���ɾ���������ɾ���е�λ�ò������ã��д��Ľ�
 * @param {Object} objName
 * @param {Object} id
 */
function moveCursor(objName, id){
    var obj = document.getElementById(objName+id);
    var nextId = -1;
    var tempObj;
    if(event.keyCode==38){ //up
        nextId = Number(id)-1;
    }else if(event.keyCode==40){ //down
        nextId = Number(id)+1;
    }
    if(nextId >= 0 ){
        tempObj = document.getElementById(objName+nextId);
        if(tempObj){
            tempObj.focus();
        }
    }
}

/**
 * ���ܣ���ָ��������ʾҳ��Ԫ��pageObj��altֵ��
 * @param {Object} divId ���ID�ţ�
 * @param {Object} pageObj ҳ��Ԫ�ض���
 * @param {Object} addText ������ʾ�ı�
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
 * ���ܣ���DTO������Ӧ���Ե�ֵ����ָ�����Ʊ��Ķ�Ӧ��
 * @param {Object} dto DTO����
 * @param {Object} formName ����
 */
function dto2Frm(dto, formName){
var frmObj = eval("document." + formName);
var children = frmObj.elements;
var child = null;
var childType = null;
var fieldName = "";

    for(var i = 0; i < children.length; i++){

        child = children[i];
				childType = child.type;
				fieldName = child.name;

        if(childType!=undefined)
        {
            if(childType == "text" || childType == "textarea" || childType== "hidden"){
                for(prop in dto){
                    if(fieldName == String(prop)){
                        child.value = dto[prop];
                    }
                }
            } else if(childType.indexOf("select") > -1){
                for(prop in dto){
                    if(fieldName == String(prop)){
                        selectSpecialOptionByItem(child, dto[prop]);
                    }
                }
            }
        }
    }
}

/**
 * ���ܣ��ж�ָ���ı���ֵ�Ƿ�ȫ��
 * @param {Object} fieldNames ����������������ð�Ƿֺ�����
 * @return ȫ���򷵻�true�����򷵻�false
 */
function isAllEmapty(fieldNames){
	var retValue = true;
	if(!fieldNames){
		alert("û��ָ������");
		return false;
	}
	var fieldArr = fieldNames.split(";");
	if(fieldArr){
		var fieldValue = ""
		var fieldName = "";
		var fields = null;
		var fieldCount = 0;
		var hasBreaked = false;
		for(var i = 0; i < fieldArr.length; i++){
			fieldName = fieldArr[i];
			fields = document.getElementsByName(fieldName);
			if(!fields){
				continue;
			}
			if(fields.length){
				fieldCount = fields.length;
				for(var j = 0; j < fieldCount; j++){
					fieldValue += fields[j].value;
					if(fieldValue != ""){
						retValue = false;
						break;
					}
				}
				if(!retValue){
					break;
				}
			} else {
				fieldValue += fields.value;
				if(fieldValue != ""){
					retValue = false;
					break;
				}
			}
		}
	}
	return retValue;
}

/**
 * ���ܣ������ָ�����ֵ
 * @param {Object} fieldNames ����������������ð�Ƿֺ�����
 */
function clearFieldValue(fieldNames){
	if(!fieldNames){
		alert("û��ָ������");
		return;
	}
	var fieldArr = fieldNames.split(";");
	if(fieldArr){
		var fieldName = "";
		var fields = null;
		var fieldCount = 0;
		for(var i = 0; i < fieldArr.length; i++){
			fieldName = fieldArr[i];
			fields = document.getElementsByName(fieldName);
			if(!fields){
				continue;
			}
			if(fields.length){
				fieldCount = fields.length;
				for(var j = 0; j < fieldCount; j++){
					fields[j].value = "";
				}
			} else {
				fields.value = "";
			}
		}
	}
}



/**
 *����:���ñ�������Ԫ�ز�����
 *����:����
 */
function setFrmReadonly(frm) {
    var frmObj = eval('document.' + frm);
    for (var i = 0; i < frmObj.length; i++) {
    	var obj = frmObj.elements[i];
    	var objType = obj.type;
        var fieldType = obj.type;
        obj.readOnly = true; 
        if( objType == "text" || objType == "password" || objType == "textarea" ){
        	obj.onclick = null;
        } 
        
        if( fieldType == "checkbox" ){
        	obj.disabled = true;
        }
    }
} 