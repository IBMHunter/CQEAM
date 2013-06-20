/**
 * @author mshtang
 */

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

function copySelectedOptionNm(fromSelect, toSelect) {
    processOptionNm(fromSelect, toSelect, true, true);
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
/**��ע��������processOption���������������ظ�Ԫ��ֱ�������������Ҳ���ʾ��Ԫ���Ѿ�����*/
function processOptionNm(fromSelect, toSelect, allOrSelected, copyOrMove) {
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
            /*
            if (haveChild(toObj, option)) {
                alert("��ѡ���Ѿ����ڣ��������ظ���ӣ�");
                return;
            }
            */
            if (!copyOrMove) {
                fromObj.remove(i);
                i--;
            }
            if (!haveChild(toObj, option)) {
            	toObj.options[toObj.length] = option;
            }
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
    if(splitor == "undefined" || splitor == null){
		splitor = ";";
	}

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

/**
 * ���ܣ�ʹѡ�е���Ŀ����
 * @param {Object} oSelect Դ�б��
 * @param {Object} isToTop �Ƿ�����ѡ������ˣ������������ơ�trueΪ�ƶ������ˣ�false��֮��Ĭ��Ϊfalse
 */	
function moveUp(oSelect,isToTop){   
	if(!isToTop){//Ĭ��״̬�����ƶ�������
		isToTop = false;
	}
    var strTempValue = "";
    var strTempText = "";
	if(oSelect.multiple){//����Ƕ�ѡ
		for(var i = 0; i < oSelect.options.length; i++){   
			if(isToTop){//����������ƶ������˱�־   
				if(oSelect.options[i].selected){   
					var transferIndex = i;
					while(transferIndex > 0 && !oSelect.options[transferIndex - 1].selected){
						strTempValue = oSelect.options.item(transferIndex).value;
						strTempText = oSelect.options.item(transferIndex).text;
				        oSelect.options.item(transferIndex).value = oSelect.options.item(transferIndex - 1).value;
				        oSelect.options.item(transferIndex).text = oSelect.options.item(transferIndex - 1).text;
				        oSelect.options.item(transferIndex - 1).value = strTempValue;
				        oSelect.options.item(transferIndex - 1).text = strTempText;
						oSelect.options.item(transferIndex - 1).selected = true;
						oSelect.options.item(transferIndex).selected = false;
						transferIndex--;   
					}
				}
			} else {//û�������ƶ������˱�־   
				if(oSelect.options[i].selected){   
					if(i > 0){   
						if(!oSelect.options[i - 1].selected){
							strTempValue = oSelect.options.item(i).value;
							strTempText = oSelect.options.item(i).text;
					        oSelect.options.item(i).value = oSelect.options.item(i - 1).value;
					        oSelect.options.item(i).text = oSelect.options.item(i - 1).text;
					        oSelect.options.item(i - 1).value= strTempValue;
					        oSelect.options.item(i - 1).text    = strTempText;
					        oSelect.options.item(i - 1).selected = true;
					        oSelect.options.item(i).selected = false;
						}
					}
				}
			}
		}
	} else {//����ǵ�ѡ
		var i = oSelect.selectedIndex;   
		if(i <= 0){ 
			return;
		}
		if(isToTop){//����������ƶ������˱�־
			while(i > 0){   
				strTempValue = oSelect.options.item(i).value;
				strTempText = oSelect.options.item(i).text;
		        oSelect.options.item(i).value = oSelect.options.item(i - 1).value;
		        oSelect.options.item(i).text = oSelect.options.item(i - 1).text;
		        oSelect.options.item(i - 1).value = strTempValue;
		        oSelect.options.item(i - 1).text = strTempText;
		        oSelect.options.item(i - 1).selected = true;
		        oSelect.options.item(i).selected = false;
			}   
		} else{//û�������ƶ������˱�־
			strTempValue = oSelect.options.item(i).value;
			strTempText = oSelect.options.item(i).text;
	        oSelect.options.item(i).value    = oSelect.options.item(i - 1).value;
	        oSelect.options.item(i).text        = oSelect.options.item(i - 1).text;
	        oSelect.options.item(i - 1).value= strTempValue;
	        oSelect.options.item(i - 1).text    = strTempText;
	        oSelect.options.item(i - 1).selected = true;
	        oSelect.options.item(i).selected = false;
		}
	}   
}   

/**
 * ���ܣ�ʹѡ�е���Ŀ����
 * @param {Object} oSelect Դ�б��
 * @param {Object} isToBottom �Ƿ�����ѡ����׶ˣ������������ơ�trueΪ�ƶ����׶ˣ�false��֮��Ĭ��Ϊfalse
 */	
  function moveDown(oSelect,isToBottom){   
	if(!isToBottom){//Ĭ��״̬�����ƶ����ײ�
		isToBottom = false;   
	}
    var strTempValue = "";
    var strTempText = "";
	var selLength = oSelect.options.length - 1;
	if(oSelect.multiple){//����Ƕ�ѡ   
		for(var i = oSelect.options.length - 1; i >= 0; i--){   
			if(isToBottom){//����������ƶ������˱�־   
				if(oSelect.options[i].selected){   
					var transferIndex = i;   
					while(transferIndex < selLength && !oSelect.options[transferIndex + 1].selected){
						strTempValue = oSelect.options.item(transferIndex).value;
						strTempText = oSelect.options.item(transferIndex).text;
				        oSelect.options.item(transferIndex).value = oSelect.options.item(transferIndex + 1).value;
				        oSelect.options.item(transferIndex).text = oSelect.options.item(transferIndex + 1).text;
				        oSelect.options.item(transferIndex + 1).value = strTempValue;
				        oSelect.options.item(transferIndex + 1).text = strTempText;
						oSelect.options.item(transferIndex + 1).selected = true;
						oSelect.options.item(transferIndex).selected = false;
					
//						oSelect.options[transferIndex].swapNode(oSelect.options[transferIndex + 1]);   
						transferIndex++;
					}   
				}   
			} else {//û�������ƶ������˱�־
				if(oSelect.options[i].selected){  
					if(i < selLength){
						if(!oSelect.options[i + 1].selected){
							strTempValue = oSelect.options.item(i).value;
							strTempText = oSelect.options.item(i).text;
					        oSelect.options.item(i).value = oSelect.options.item(i + 1).value;
					        oSelect.options.item(i).text = oSelect.options.item(i + 1).text;
					        oSelect.options.item(i + 1).value= strTempValue;
					        oSelect.options.item(i + 1).text    = strTempText;
					        oSelect.options.item(i + 1).selected = true;
					        oSelect.options.item(i).selected = false;
						
//							oSelect.options[i].swapNode(oSelect.options[i + 1]);
						}
					}
				}
			}
		}   
	} else {//����ǵ�ѡ   
		var i = oSelect.selectedIndex;   
		if(i >= selLength - 1){   
			return;
		}
		if(isToBottom) {//����������ƶ������˱�־   
			while(i < elLength - 1){   
//				oSelect.options[i].swapNode(oSelect.options[i + 1]);   
				strTempValue = oSelect.options.item(i).value;
				strTempText = oSelect.options.item(i).text;
		        oSelect.options.item(i).value = oSelect.options.item(i + 1).value;
		        oSelect.options.item(i).text = oSelect.options.item(i + 1).text;
		        oSelect.options.item(i + 1).value = strTempValue;
		        oSelect.options.item(i + 1).text = strTempText;
		        oSelect.options.item(i + 1).selected = true;
		        oSelect.options.item(i).selected = false;
				i++;   
			}   
		} else{//û�������ƶ������˱�־                   
			strTempValue = oSelect.options.item(i).value;
			strTempText = oSelect.options.item(i).text;
	        oSelect.options.item(i).value = oSelect.options.item(i + 1).value;
	        oSelect.options.item(i).text = oSelect.options.item(i + 1).text;
	        oSelect.options.item(i + 1).value = strTempValue;
	        oSelect.options.item(i + 1).text = strTempText;
	        oSelect.options.item(i + 1).selected = true;
	        oSelect.options.item(i).selected = false;
//			oSelect.options[i].swapNode(oSelect.options[i + 1]);
		}
	}   
}

function addText2Value(oSelect, splitor){
	if(!splitor){
		splitor = ";";
	}
	var optObjs = oSelect.options;
	var optObj = null;
	for(var i = 0; i < optObjs.length; i++){
		optObj = optObjs[i];
		if(optObj.selected){
			optObj.value = optObj.value + splitor + optObj.text;
		}
	}
}

/**
 * ���ܣ�����ѡ�������б��Text �� value
 * @param select
 */	
function getSelected(obj){
	var arr = new Array();
   	var tp = obj.options;
   	for(var i=0;i<tp.length;i++){
   		if(tp[i].selected){
   			arr[arr.length] = new Array(tp[i].text,tp[i].value);
   		}
   	}
   	return arr;
}

/**
 * ���ܣ����������б����е�Text �� value
 * @param obj
 */	
function selectedAll(obj){
	var arr = new Array();
	if(obj.length == 0){
		return arr;
	}
	var tp = obj.options;
   	for(var i=0;i<obj.length;i++){
   		arr[arr.length] = new Array(tp[i].text,tp[i].value);
   	}
   	return arr;
}

function do_ProcessOptionWidth(selectObj){
    var newSelectObj = document.createElement("select");
    newSelectObj = selectObj.cloneNode(true);
    newSelectObj.selectedIndex = selectObj.selectedIndex;
    newSelectObj.onmouseover = null;
    var e = selectObj;
    var absTop = e.offsetTop;
    var absLeft = e.offsetLeft;
    while(e = e.offsetParent){
        absTop += e.offsetTop;
        absLeft += e.offsetLeft;
    }
    newSelectObj.style.position = "absolute";
    newSelectObj.style.top = absTop + "px";
    newSelectObj.style.left = absLeft + "px";
    newSelectObj.style.width = "auto";

    var rollback = function(){
		rollbackWidth(selectObj, newSelectObj);
	};
    if(window.addEventListener){
        newSelectObj.addEventListener("blur", rollback, false);
        newSelectObj.addEventListener("change", rollback, false);
    } else{
        newSelectObj.attachEvent("onblur", rollback);
        newSelectObj.attachEvent("onchange", rollback);
    }
    selectObj.style.visibility = "hidden";
    document.body.appendChild(newSelectObj);
    newSelectObj.focus();
}

function rollbackWidth(selectObj, newSelectObj){
    selectObj.selectedIndex = newSelectObj.selectedIndex;
    selectObj.style.visibility = "visible";
    document.body.removeChild(newSelectObj);
}


function disabledSelect(){
	var selects = document.getElementsByTagName("SELECT"); 
	for( var i =0 ; i, selects.length; i++){
		selects[i].disabled = true;
	}
}