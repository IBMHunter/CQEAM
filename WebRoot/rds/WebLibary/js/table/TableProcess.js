function TableProcessor(tab) {
    this.setUniqueField = function(uniqueField) {
        if (uniqueField == undefined || uniqueField == null) {
            uniqueField = "";
        }
        this.uniqueField = uniqueField;
    };
    this.setNamePrefix = function(namePrefix) {
        if (namePrefix == undefined || namePrefix == null) {
            namePrefix = "";
        }
        this.namePrefix = namePrefix;
    };
    this.setClearContent = function(clearContent) {
        this.clearContent = clearContent;
    };
    this.addRowData = function(dtoObj) {
        if (!tab) {
            alert("�����󲻴��ڣ�ϵͳ�޷�����");
            return;
        }
        if (!tab.rows) {
            alert("������û�вο����ݣ�ϵͳ�޷�����");
            return;
        }
        if (!dtoObj) {
            alert("DTO���ݴ������Ϊ�գ�ϵͳ�޷�����");
            return;
        }
        if (this.uniqueField != undefined && this.uniqueField != null && this.uniqueField != "") {
            if (dtoObj[this.uniqueField] != undefined) {
                var newValue = dtoObj[this.uniqueField];
                var trs = tab.rows;
                for (i = 0; i < trs.length; i++) {
                    var field = getTrNode(trs[i], this.uniqueField);
                    if(field == null){
                        continue;
                    }
                    if (field.value == newValue) {
                        return;
                    }
                }
            }
        }
        var rows = tab.rows;
        var rowCount = rows.length;
        var row = rows[rowCount - 1];
        var newRow = null;
        if (rowCount == 1 && row.style.display == "none") {
            newRow = row;
        } else {
            newRow = row.cloneNode(true);
        }
        var children = newRow.childNodes;
        var nodeHtm = "";
        var startMarkIndex = -1;
        var endMarkIndex = -1;
        var fieldName = "";
        var fieldValue = "";
        var lastNumber = -1;
        var lastName = "";
        var thisName = "";
        for (var i = 0; i < children.length; i++) {//��Ԫ��ѭ��
            var child = children[i];
            tagName = child.tagName;
            if (tagName.toUpperCase() != "TD") {
                continue;
            }
            var elementName = child.name;
            if (elementName != null && elementName != undefined) { //�����Ԫ��
                if (elementName.indexOf("[") > - 1) {//��Ҫ��������ǰ׺
                    startMarkIndex = Number(elementName.indexOf("["));
                    endMarkIndex = Number(elementName.indexOf("]"));
                    lastNumber = Number(elementName.substring(startMarkIndex + 1, endMarkIndex));
                    nodeHtm = child.outerHTML;
                    lastName = "[" + lastNumber + "]";
                    thisName = "[" + (Number(lastNumber) + 1) + "]";
                    nodeHtm = replaceStr(nodeHtm, lastName, thisName);
                    child.outerHTML = nodeHtm;
                    fieldName = elementName.substring(endMarkIndex + 2);
                    fieldValue = dtoObj[fieldName];
                    if (fieldValue != null && fieldValue != undefined && fieldValue != "undefined") {
                        child.innerText = fieldValue;
                    } else {
                        child.innerText = "";
                    }
                } else {
                    fieldName = elementName;
                    fieldValue = dtoObj[fieldName];
                    if (fieldValue != null && fieldValue != undefined && fieldValue != "undefined") {
                        child.innerText = fieldValue;
                    } else {
                        child.innerText = "";
                    }
                    var id = child.id;
                    if (id != null && id != undefined && id != "") {
                        var idNumber = getNumFromText(id);
                        var idStr = id.substring(0, id.indexOf(idNumber));
                        if (idNumber == "") {
                            idNumber = 1;
                        } else {
                            idNumber = Number(idNumber) + 1;
                        }
                        idStr = idStr + idNumber;
                        child.setAttribute("id", idStr);
                    }
                }
            } else {//������еı�Ԫ��
                var fieldNodes = child.childNodes;
                if (fieldNodes.length == 1 && fieldNodes[0].nodeName == "#text") {
                    child.innerText = "";
                } else {
                    for (var j = 0; j < fieldNodes.length; j++) {//��Ԫ���ڱ���ѭ��
                        var node = fieldNodes[j];
                        if (node == null || node == undefined || node == "undefined") {
                            continue;
                        }
                        elementName = node.name;
                        if (elementName == null || elementName == undefined || elementName == "undefined") {
                            continue;
                        }
                        var tagName = node.tagName;
                        var nodeType = node.type;
                        if (elementName.indexOf("[") > - 1) {//��Ҫ��������ǰ׺
                            startMarkIndex = Number(elementName.indexOf("["));
                            endMarkIndex = Number(elementName.indexOf("]"));
                            lastNumber = Number(elementName.substring(startMarkIndex + 1, endMarkIndex));
                            fieldName = elementName.substring(endMarkIndex + 2);
                            fieldValue = dtoObj[fieldName];
                            if (nodeType == "checkbox" || nodeType == "radio") {
                                node.checked = false;
                            }
                            if (fieldValue != null && fieldValue != undefined) {
                                if (tagName == "SELECT") {
                                    selectSpecialOptionByItem(node, fieldValue);
                                } else {
                                    node.value = fieldValue;
                                }
                            } else {
                                if (tagName == "SELECT") {
                                    selectSpecialOptionByItem(node, "");
                                } else {
                                    node.value = "";
                                }
                            }
                            nodeHtm = node.outerHTML;
                            lastName = "[" + lastNumber + "]";
                            if(row.style.display != "none"){
                                thisName = "[" + (Number(lastNumber) + 1) + "]";
                                nodeHtm = replaceStr(nodeHtm, lastName, thisName);
                            }
                            node.outerHTML = nodeHtm;
                        } else {//����Ҫ��������ǰ׺
                            fieldName = elementName;
                            fieldValue = dtoObj[fieldName];
                            if (nodeType == "checkbox" || nodeType == "radio") {
                                node.checked = false;
                            }
                            if (fieldValue != null && fieldValue != undefined) {
                                if (tagName == "SELECT") {
                                    selectSpecialOptionByItem(node, fieldValue);
                                } else {
                                    node.value = fieldValue;
                                }
                            } else {
                                if (tagName == "SELECT") {
                                    selectSpecialOptionByItem(node, "");
                                } else {
                                    node.value = "";
                                }
                            }
                            nodeHtm = node.outerHTML;
                            id = node.id;
                            if (id != null && id != undefined && id != "") {
                                idNumber = getNumFromText(id);
                                idStr = id.substring(0, id.indexOf(idNumber));
                                if (idNumber == "") {
                                    idNumber = 1;
                                } else {
                                    idNumber = Number(idNumber) + 1;
                                }
                                idStr = idStr + idNumber;
                                nodeHtm = replaceStr(nodeHtm, lastName, thisName);
                            }
                            node.outerHTML = nodeHtm;
                        }
                    }
                }
            }
        }
        newRow.style.display = "block";
//        alert(newRow.outerHTML);
        row.parentNode.appendChild(newRow);
    };
    this.deleteAll = function() {
        if (tab != null && tab != undefined && tab != "undefined") {
            var rows = tab.rows;
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (rows.length > 1) {
                    tab.deleteRow(i);
                    i--;
                } else {
                    var cells = row.cells;
                    var cell = null;
                    for (var j = 0; j < cells.length; j++) {
                        cell = cells[j];
                        var fields = cell.childNodes;
                        if (fields.length != null && fields.length != undefined && fields.length > 0) {
                            var nodeType = "";
                            for (var k = 0; k < fields.length; k++) {
                                var field = fields[k];
                                nodeType = field.type;
                                if (nodeType == null && nodeType == undefined) {
                                    continue;
                                }
                                if (nodeType == "checkbox" || nodeType == "radio") {
                                    field.checked = false;
                                    field.value = "";
                                } else if (nodeType.indexOf("select") == -1) {
                                    field.value = "";
                                }
                            }
                        } else {
                            cell.innerText = "";
                        }
                    }
                    row.style.display = "none";
                }
            }
        }
    };
}


/**
 * ���ܣ�ִ�б�����е�checkbox����radio��click���ܡ�
 */
function executeClick(tr){
	var eleType = event.srcElement.type;
	if(eleType){
		if(eleType != "checkbox" && eleType != "radio"){
				return;
		}
	}
	if(tr){
		var cells = tr.cells;
		var cell = null;
		var children = null;
		var child = null;
		var childType = null;
		var htm = "";
		if(cells && cells.length){
			for(var i = 0; i < cells.length; i++){
				cell = cells[i];
				children = cell.childNodes;
				if(children){
					for(var j = 0; j < children.length; j++){
						child = children[j];
						childType = child.type;
						if(childType && (childType == "checkbox" || childType == "radio")){
							child.click();
							return;
						}
					}
				}
			}
		}
	}
}

/**
 * ���ܣ�������chkObj������ɾ��
 * tableId�����ID��
 * chkObj����ѡ�����
 *
 */
function delTableRow(tab, chkObj) {
	if(!tab || !chkObj){
		return;
	}
	var trObj = chkObj;
	var trHtm = "";
	for(var i = 0; ; i++){
		trHtm = trObj.outerHTML;
		var index = trHtm.indexOf("<TR");
		if(index > -1){
			tab.deleteRow(trObj.rowIndex);
			return;
		}
		trObj = trObj.parentNode;
	}
}

/**
 * ���ܣ�������chkObj�������������
 * tab�������
 * chkObj����ѡ�����
 *
 */
function clearContent(tab, chkObj) {
	if(!tab || !chkObj){
		return;
	}
	var trObj = chkObj.parentNode.parentNode;
	var cells = trObj.cells;
	var cell = null;
	var fields = null;
	for(var i = 0; i < cells.length; i++){
		cell = cells[i];
		fields = cell.childNodes;
		if(fields.length){
			var field = null;
			var nodeType = "";
			for(var j = 0; j < fields.length; j++){
				field = fields[j];
				nodeType = field.type;
				if(!nodeType){
					continue;
				}
				if(nodeType == "checkbox" || nodeType == "radio"){
					field.checked = false;
					field.value = "";
				} else if(nodeType.indexOf("select") == -1){
					field.value = "";
				}
			}
		} else {
			cell.innerText = "";
		}
	}
}

/**
 * ���ܣ�������ѡ����ɾ����
 * tab��������
 * checkboxName����ѡ�����ơ�
 */
function deleteTableRow(tab, checkboxName){
	var deleteResult = false;
	if(tab && checkboxName){
		var rowCount = tab.rows.length;
		if (rowCount == 0) {
			alert("������Ҫɾ�����С�");
		} else {
			var boxArr = getCheckedBox(checkboxName);
			var chkCount = boxArr.length;
			if(chkCount < 1){
				alert("����ѡ��Ҫɾ�����У�");
			} else {
				if(confirm("ȷ��Ҫɾ��ѡ�е����𣿼���������ȷ������ť������������ȡ������ť��")){
					var chkObj = null;
					for(var i = 0; i < chkCount; i++){
						chkObj = boxArr[i];
						if(tab.rows.length > 1){
							delTableRow(tab, chkObj);
						} else {
							clearContent(tab, chkObj);
							tab.rows[0].style.display = "none";
						}
					}
					deleteResult = true;
				}
			}
		}
	}
	return deleteResult;
}

/**
 * ���ܣ�������ѡ����ɾ����������ʾ�����ڳ�����Ҫ���п���ʱ.
 * tab��������
 * checkboxName����ѡ�����ơ�
 * <B>ǰ�᣺���и��б��뺬�и�ѡ��</B>
 */
function deleteRow(tab){
	if(!(tab && tab.rows)){
		return;
	}
	var tr = tab.rows[0];
	var checkboxName = "";
	var cells = tr.cells;
	var cell = null;
	var fields = null;
	for(var i = 0; i < cells.length; i++){
		cell = cells[i];
		fields = cell.childNodes;
		var hasFound = false;
		if(fields.length){
			var field = null;
			var nodeType = "";
			for(var j = 0; j < fields.length; j++){
				field = fields[j];
				nodeType = field.type;
				if(!nodeType){
					continue;
				}
				if(nodeType == "checkbox"){
					field.checked = false;
					checkboxName = field.name;
					hasFound = true;
					break;
				}
			}
		} else {
			checkboxName = fields.name;
		}
		if(hasFound){
			break;
		}
	}
	setCheckBoxState(checkboxName, true);
	var boxArr = getCheckedBox(checkboxName);
	var chkCount = boxArr.length;
	var chkObj = null;
	for(var i = 0; i < chkCount; i++){
		chkObj = boxArr[i];
		if(tab.rows.length > 1){
			delTableRow(tab, chkObj);
		} else {
			clearContent(tab, chkObj);
			tab.rows[0].style.display = "none";
		}
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
 * fromNumber �ϲ���ʼ��������
 * toNumber �ϲ�������������
 */
function autoFromToSpan(tableID, fromNumber, toNumber) {
    var obj = document.all[tableID];
    var head = new Array(toNumber - fromNumber);
    var rows = obj.rows.length;
    if (rows > 1) {
        var cols = obj.rows[0].cells.length;
        for (j = fromNumber; j < toNumber; j++) {
            head[j - fromNumber] = obj.rows[0].cells[j].innerText;
        }
        for (i = 1; i < rows; i++) {
            var flag = 0;
            for (j = fromNumber; j < toNumber; j++) {
                if (head[j - fromNumber] == obj.rows[i].cells[j].innerText && flag == 0) {
                    obj.rows[i].cells[j].innerText = "";
                } else {
                    flag = 1;
                    head[j - fromNumber] = obj.rows[i].cells[j].innerText;
                    //continue;
                }
            }
        }
        for (j = toNumber; j >= fromNumber; j--) {
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

function getNodeObject(trObj, inputName){
	var nodeObj = null;
	if(trObj && inputName){
		var trNodes = trObj.children;
		var trNode = null;
		var nodes = null;
		var node = null;
		for(var i = 0; i < trNodes.length; i++){
			trNode = trNodes[i];
			nodes = trNode.children;
			for(var j = 0; j < nodes.length; j++){
				node = nodes[j];
				if(node.name == inputName){
					nodeObj = node;
					break;
				}
			}
			if(nodeObj){
				break;
			}
		}
	}
	return nodeObj;
}


/**
 * ���ܣ���ȡָ����ָ�����Ƶı�Ԫ�ض���
 * @param tr
 * @param objName
 */
function getTrNode(tr, objName) {
    var obj = null;
    var cells = tr.cells;
    var index = -1;
    for (var i = 0; i < cells.length; i++) {
        var cell = cells[i];
        var nodes = cell.childNodes;
        var breaked = false;
        for (var j = 0; j < nodes.length; j++) {
            var node = nodes[j];
            var nodeName = node.name;
            if (!nodeName) {
                continue;
            }
            index = nodeName.indexOf("###");
            var offset = 3;
            if (index == -1) {
                offset = 2;
                index = nodeName.indexOf("]");
            }
            if(index == -1){
                continue;
            }
            nodeName = nodeName.substring(index + offset);
            if (nodeName == objName) {
                obj = node;
                breaked = true;
                break;
            }
        }
        if (breaked) {
            break;
        }
    }
    return obj;
}


/**
 * ���ܣ�����ɾ�����һ�С��������������
 * @param tab
 */
function deleteLastRow(tab){
	if(	!tab){
		return;
	}
	var rows = tab.rows;
	if(!rows){
		return;
	}
	if(rows.length == 1 && rows[0].style.display == "none"){
		tab.deleteRow(rows[0].rowIndex);
	}
}
    
/**
 * ���ܣ��Ա��tab��cellIndex�н�������
 */
function sortTable(tab, cellIndex){//������
	var temp;
	var values = [];
	var keys = [];
	var tbl_rows = [];
	var len = tab.rows.length;
	for(var i = 0; i < len; i++){        
		values.push(tab.rows[i].cells[cellIndex].innerText);
		keys.push(i);
		tbl_rows.push(tab.rows[i].cloneNode(1));
	}
	for(var i = 1; i < len - 1; i++){
		for(var j = i+1; j < len; j++){
			if(values[i] > values[j]){
				//value ����
				temp = values[j];
				values[j] = values[i];
				values[i] = temp;
				//key ����
				temp = keys[j];
				keys[j] = keys[i];
				keys[i] = temp;
			}
		}
	}
	//�����±��
	var new_tbl = tab.cloneNode(0);
	new_tbl.appendChild(tab.tBodies[0].cloneNode(0));
	for(var i = 0; i < len; i++){
		var key = keys[i];
		new_tbl.tBodies[0].appendChild(tbl_rows[key]);
	}
	tab.replaceNode(new_tbl);
}

function needValidate(tab){
    if(tab == null || tab == undefined){
        return false;
    }
    var rows = tab.rows;
    if(rows != null && rows != undefined){
        if(rows.length == 1){
            if(rows[0].style.display == "none" || rows[0].style.visibility == "hidden"){
                return false;
            } else {
                return true;
            }
        } else if(rows.length > 1){
            return true;
        } else {
            return false;
        }
    }
    return false;
}