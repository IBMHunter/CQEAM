/**
 * ���ܣ�����Excel���Ƶ�����ճ�������ݱ�����dataTable
 * headerTable ���������(�䵥Ԫ������������Excel��Ԫ������һ��)
 * dataTable ���ݱ�����
 * uniqueField Ψһ���ֶΡ�������ֶ���Excel�е����ݳ����ظ��У����ظ����ݻᱻ���ˡ�
 * ���ߣ�����ʤ
 */
function pasteData(headerTable, dataTable, uniqueField){
	var text = window.clipboardData.getData("text");
	if (text == null || text == "") {
		alert("������EXCEL�����︴�ư������������ڵ����ݡ�Ȼ����ճ����");
		return;
	}
	if(!headerTable){
		alert("û��ָ�����������");
		return;
	}
	if(!dataTable){
		alert("û��ָ�����ݱ�����");
		return;
	}
	var rows = text.split('\n');

	var dataDescs = getDataDescs(headerTable);
	var fieldNames = getFieldNames(dataTable);
	var excelFields = getXLSFields(rows[0]);

	var rowCount = rows.length;
	var columnCount = excelFields.length;
	var index = -1;
//    alert("rowCount = " + rowCount);
    for(var i = 1; i < rowCount; i++){
		var rowData = rows[i];
//        alert("rowData = " + rowData);
        if(rowData == ""){
			continue;
		}
		var cols = rowData.split(String.fromCharCode(9));
		if(cols.length != columnCount){
			alert("���Ƶ����ݲ��Ϸ�������ճ��");
			return;
		}
		var valueDTO = new Object();
		for(var j = 0; j < columnCount; j++){
			index = findIndexOfArr(dataDescs, excelFields[j]);
//            alert("index = " + index);
            if(index != -1){
				valueDTO[fieldNames[index]] = cols[j];
			}
		}
        if(document.getElementById(uniqueField)){
            appendDTO2Table(dataTable, valueDTO, false, uniqueField);
        } else {
            appendDTORow(dataTable, valueDTO, false);
        }
    }
}

function getXLSFields(rowData){
	var excelFields = new Array();
	var colData = "";
	var cols = rowData.split(String.fromCharCode(9));
	var columnCount = cols.length;
	for(var i = 0; i < columnCount; i++){
		colData = cols[i];
		if(i == columnCount - 1){
			colData = colData.replace("\n", "");
			colData = colData.replace("\r", "");
		}
		excelFields[i] = colData;
    }
	return excelFields;
}

function getDataDescs(headerTable){
	var dataDescs = new Array();
	var rows = headerTable.rows;
	var rowCount = rows.length;
	var fieldRow = rows[rowCount - 1];
	var children = fieldRow.childNodes;
	var child = null;
	for(var i = 0; i < children.length; i++){
		child = children[i];
		dataDescs[i] = child.innerText;
    }
	return dataDescs;
}


function getFieldNames(dataTable){
	var fieldNames = new Array();
	var rows = dataTable.rows;
	var rowCount = rows.length;
	var fieldRow = rows[rowCount - 1];
	var children = fieldRow.childNodes;
	var child = null;
	var elementName = null;
	for(var i = 0; i < children.length; i++){
		child = children[i];
		var tagName = child.tagName;
		if(tagName.toUpperCase() != "TD"){
			continue;
		}
		elementName = child.name;
		if(elementName){//�����������
			fieldNames[i] = elementName;
		} else {//���������Ƿ��б���
			var fieldNodes = child.childNodes;
			for(var j = 0; j < fieldNodes.length; j++){//��Ԫ���ڱ���ѭ��
				var node = fieldNodes[j];
				elementName = node.name;
				if(elementName){
					fieldNames[i] = elementName;
				}
			}
		}
    }
	return fieldNames;
}