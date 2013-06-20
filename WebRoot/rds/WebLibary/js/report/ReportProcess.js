var headTable = null;
var dataTable = null;
var act = null;

var reportType = null;
var supportSubSummary = null;
var reportTypeList = null;
var leftCategorySize = null;
var drillStartColumn = null;
var sumPosition = null;
var sumPositionBottom = null;
var viewLocationAbove = null;
var viewLocationLeft = null;
var startColumn = 0;

/**
 * ���ܣ���ʼ��ȫ�ֱ���
 */
function do_initGlobalVariable(){
    headTable = document.getElementById("headTable");
    dataTable = document.getElementById("dataTable");
    act = document.getElementById("act");
}

/**
 * ���ܣ�ҳ���ʼ��
 */
function do_initPage(){
    window.focus();
    do_initGlobalVariable();
    do_ComputePageTotal();
    do_ComputeSubTotal();
    do_SetPageWidth();
    do_ProcessIntersectReport();
}

/**
 * ���ܣ����㽻�汨���С��
 */
function do_ComputeSubTotal(){
    if(act.value == "QUERY_ACTION" && reportType != reportTypeList && supportSubSummary == "Y"){
        var rows = dataTable.rows;
        if(rows && rows.length){
            var hiddenCount = getHiddenColumnCount();
            if(leftCategorySize < hiddenCount + 2){
                return;
            }
            startColumn = getStartColumnIndex();
            for(var currColumn = startColumn; currColumn > 0; currColumn--){
                var cells = rows[0].cells;
                var prevColumn = getPrevColumnIndex(cells, startColumn);
                var lastCellValue = rows[0].cells[prevColumn].innerHTML;
                var currCellValue = null;
                var sumStartRow = 0;
                var referRow = rows[0];
                for(var i = 1; i < rows.length; i++){
                    var row = rows[i];
                    currCellValue = row.cells[prevColumn].innerHTML;
                    if(currCellValue != lastCellValue){
                        var newRow = referRow.cloneNode(true);
                        do_ProcessNewRow(newRow, sumStartRow, (i - 1), currColumn);
                        row.parentNode.insertBefore(newRow, row);
                        lastCellValue = currCellValue;
                        i++;
                        sumStartRow = i;
                    }
                    referRow = row;
                }
                currColumn = prevColumn;
            }
        }
    }
}

/**
 * ���ܣ���ȡ��������е���������
 */
function getHiddenColumnCount(){
    var hiddenCount = 0;
    var rows = dataTable.rows;
    if(rows && rows.length > 0){
        var cells = rows[0].cells;
        for(var i = 0; i < leftCategorySize; i++){
            var cell = cells[i];
            if(cell.offsetWidth == 0){
                hiddenCount++;
            }
        }
    }
    return hiddenCount;
}

/**
 * ���ܣ���ȡС�ƿ�ʼ����������
 */
function getStartColumnIndex(){
    var startIndex = null;
    var rows = dataTable.rows;
    if(rows && rows.length > 0){
        var cells = rows[0].cells;
        for(var i = leftCategorySize - 1; i >= 0; i--){
            var cell = cells[i];
            if(cell.offsetWidth > 0){
                startIndex = i;
                break;
            }
        }
    }
    return startIndex;
}

/**
 * ���ܣ���ȡ��ǰ������ǰһ��������
 * @param cells �ж������
 * @param currColumnIndex ��ǰ����������
 */
function getPrevColumnIndex(cells, currColumnIndex){
    var prevColumnIndex = null;
    for(var i = currColumnIndex - 1; i >= 0; i--){
        var cell = cells[i];
        if(cell.offsetWidth > 0){
            prevColumnIndex = i;
            break;
        }
    }
    return prevColumnIndex;
}

/**
 * ���ܣ����������ӵ�С��������
 * @param newRow �������ж���
 * @param startRowNumber ��ʼ����������
 * @param endRowNumber ��������������
 * @param categoryIndex �����ֶ�������
 */
function do_ProcessNewRow(newRow, startRowNumber, endRowNumber, categoryIndex){
    var cells = newRow.childNodes;
    var cellCount = cells.length;
    for(var columnIndex = 0; columnIndex < cellCount; columnIndex++){
        var cell = cells[columnIndex];
        var cellNode = cell.childNodes[0];
        if(columnIndex >= leftCategorySize ){
            cellNode.value = do_ComputeSumValue(startRowNumber, endRowNumber, columnIndex, categoryIndex);
        } else if(columnIndex == categoryIndex ){
            cellNode.value = "С��";
        }
    }
}

/**
 * ���ܣ����������ӵ�С��������
 * @param startRowNumber ��ʼ����������
 * @param endRowNumber ��������������
 * @param columnIndex �����д������������
 * @param categoryIndex �����ֶ�������
 */
function do_ComputeSumValue(startRowNumber, endRowNumber, columnIndex, categoryIndex){
    var strTotalValue = "";
    var subTotalValue = 0;
    var dataRows = dataTable.rows;
    var containsComma = false;
    var containsRMB = false;
    var containPercent = false;
    var containPermillage = false;
    for(var i = startRowNumber; i <= endRowNumber; i++){
        var row = dataRows[i];
        var cell = row.cells[columnIndex];
        var cellNode = cell.childNodes[0];
        var nodeValue = cellNode.value;
        if(nodeValue.indexOf(",") > -1){
            containsComma = true;
        }
        if(nodeValue.indexOf("��") > -1){
            containsRMB = true;
        }
        if(nodeValue.indexOf("%") > -1){
            containPercent = true;
        }
        if(nodeValue.indexOf("��") > -1){
            containPermillage = true;
        }
        nodeValue = replaceStr(nodeValue, ",", "");
        nodeValue = replaceStr(nodeValue, "��", "");
        nodeValue = replaceStr(nodeValue, "%", "");
        nodeValue = replaceStr(nodeValue, "��", "");
        subTotalValue += new Number(nodeValue);
    }
    subTotalValue = formatNum(subTotalValue, 2);
    var minus = getColumnMinus(categoryIndex);
    var divedNumber = Math.pow(2, minus);
    subTotalValue = subTotalValue / divedNumber;
    if(containsRMB){
        subTotalValue = formatCurrency(subTotalValue, true);
    } else if(containsComma){
        subTotalValue = formatCurrency(subTotalValue, false);
    }
    if(containPercent){
        strTotalValue = subTotalValue + "%";
    } else if(containPermillage){
        strTotalValue = subTotalValue + "��";
    } else {
        strTotalValue = String(subTotalValue);
//        strTotalValue = do_AppendZero(String(subTotalValue));
    }
    return strTotalValue;
}

/**
 * ���ܣ���ȡ��ǰ����������ʼ�����м�ķ�������������ֵ
 * @param categoryIndex ��ǰ����������
 */
function getColumnMinus(categoryIndex){
    var minus = startColumn - categoryIndex;
    var cells = dataTable.rows[0].cells;
    for(var i = startColumn; i > categoryIndex; i--){
        var cell = cells[i];
        if(cell.offsetWidth == 0){
            minus--;
        }
    }
    return minus;
}

/**
 * ���ܣ������ݲ������0
 * @param strTotalValue
 */
function do_AppendZero(strTotalValue){
    var retStr = "";
    var dotIndex = strTotalValue.indexOf(".");
    var leftStr = "";
    var rightStr = "";
    if(dotIndex > -1){
        leftStr = strTotalValue.substring(0, dotIndex);
        rightStr = strTotalValue.substring(dotIndex);
    } else {
        leftStr = strTotalValue;
        rightStr = ".";
    }
    while(rightStr.length < 3){
        rightStr += "0";
    }
    retStr = leftStr + rightStr;
    return retStr;
}

/**
 * ���ܣ�����ҳ���¶˵��ܼ�
 */
function do_ComputePageTotal(){
    if(act.value == "QUERY_ACTION"
            && reportType != reportTypeList
            && sumPosition != sumPositionBottom
            && sumPosition != ""
            && dataTable != null){
        var dataRows = dataTable.rows;
        var dataCount = dataRows.length;
        if(dataCount > 0){
            var lastRow = dataRows[dataCount - 1];
            var cells = lastRow.cells;
            var cellCount = cells.length;
            for(var j = 0; j < cellCount; j++){
                var cell = cells[j];
                var cellNode = cell.childNodes[0];
                if(cellNode.value != ""){
                    continue;
                }
                var cellSumValue = 0;
                var containsComma = false;
                var containsRMB = false;
                var containPercent = false;
                var containPermillage = false;
                for(var i = 0; i < dataCount - 1; i++){
                    var dataRow = dataRows[i];
                    var dataCell = dataRow.cells[j];
                    var dataNode = dataCell.childNodes[0];
                    var dataValue = dataNode.value;
                    if(dataValue.indexOf(",") > -1){
                        containsComma = true;
                    }
                    if(dataValue.indexOf("��") > -1){
                        containsRMB = true;
                    }
                    if(dataValue.indexOf("%") > -1){
                        containPercent = true;
                    }
                    if(dataValue.indexOf("��") > -1){
                        containPermillage = true;
                    }
                    dataValue = replaceStr(dataValue, ",", "");
                    dataValue = replaceStr(dataValue, "��", "");
                    dataValue = replaceStr(dataValue, "%", "");
                    dataValue = replaceStr(dataValue, "��", "");

                    cellSumValue += new Number(dataValue);
                }
                cellSumValue = formatNum(cellSumValue, 2);
                if(containsRMB){
                    cellSumValue = formatCurrency(cellSumValue, true);
                } else if(containsComma){
                    cellSumValue = formatCurrency(cellSumValue, false);
                }
                if(containPercent){
                    cellNode.value = String(cellSumValue + "%");
                } else if(containPermillage){
                    cellNode.value = String(cellSumValue + "��");
                } else {
                    cellNode.value = do_AppendZero(String(cellSumValue));
                }
            }
        }
    }
}

/**
 * ���ܣ���ѯǰ�ļ�顣��׼����
 */
function do_Check_Search() {
    var isValid = true;
    var frm = document.forms["searchParameterFrm"];
    var fields = frm.elements;
    if(fields){
        for(var i = 0; i < fields.length; i++){
            var field = fields[i];
            var required = field.getAttribute("required");
            if(required == null){
                continue;
            }
            if(required == "true"){
                var fieldValue = field.value;
                if(fieldValue == ""){
                    var fieldLabel = field.getAttribute("fieldLabel");
                    var fieldType = field.type;
                    if(fieldType== "hidden"){
                        alert("��"+fieldLabel+"��������Ϊ��,�ò����ڱ�������������趨Ϊ�����Ͳ������������Ҫ������������̽���������ҳ�����ӽ��뱾����");
                    } else {
                        alert("��"+fieldLabel+"��������Ϊ��,�������ѯ������");
                        field.focus();
                    }
                    isValid = false;
                    break;
                }
            }
        }
    }
    return isValid;
}

/**
 * ���ܣ��رյ����á���׼����
 */
function do_CloseConfig() {
    var cfg = new CloseConfig();
    cfg.setEditPage(false);
    cfg.setRefreshOpener(false);
    return cfg;
}

/**
 * ���ܣ��ϲ���Ԫ�񡣱��ڽ��汨���չʾ��
 */
function do_ProcessIntersectReport(){
    if(act.value == "QUERY_ACTION"
            && reportType != reportTypeList
            && dataTable != null){
        if(sumPosition.indexOf("BOT") > -1){
            do_ColumnSpanSumPosition();
        }
        do_ColSpanHeadTable();
        do_RowSpanDataTable();
    }
}

/**
 * ���ܣ��ϲ����ϼơ���Ԫ��
 */
function do_ColumnSpanSumPosition(){
    var dataRows = dataTable.rows;
    var dataCount = dataRows.length;
    if(dataCount > 0){
        var lastRow = dataRows[dataCount - 1];
        var cells = lastRow.cells;
        var cell1 = cells[0];
        for(var j = 1; j < leftCategorySize; j++){
            var cell2 = cells[j];
            if(cell2 == undefined || cell2 == null){
                break;
            }
            if(valueEquals(cell1, cell2)){
                cell1.colSpan++;
                do_ProcessCellWidth(cell1, cell2);
                lastRow.deleteCell(j);
                j--;
            } else {
                cell1 = cell2;
            }
        }
        cell1 = cells[0];
        cell1.childNodes[0].className = "tableInput2";
        cell1.style.height = "23px";
    }
}

/**
 * ���ܣ��ϲ���ͷ
 */
function do_ColSpanHeadTable(){
    var headRows = headTable.rows;
    if(headRows.length > 0){
        var endColumn = headRows[0].cells.length;
        do_AutoColSpan(headTable, leftCategorySize, endColumn);
        do_AutoRowSpan(headTable, leftCategorySize);
    }
}

/**
 * ���ܣ��ϲ�������ʼ�е�������
 * @param tab ������
 * @param startColumn ��ʼ������
 * @param endColumn ����������
 */
function do_AutoColSpan(tab, startColumn, endColumn){
    var rows = tab.rows;
    if(rows){
        var rowCount = rows.length;
        for(var i = 0; i < rowCount; i++){
            var row = rows[i];
            var cells = row.cells;
            var cell1 = cells[startColumn];
            for(var j = startColumn + 1; j < endColumn; j++){
                var cell2 = cells[j];
                if(cell2 == undefined || cell2 == null){
                    break;
                }
                if(valueEquals(cell1, cell2)){
                    cell1.colSpan++;
                    do_ProcessCellWidth(cell1, cell2);
                    row.deleteCell(j);
                    j--;
                } else {
                    cell1 = cell2;
                }
            }
        }
    }
}

/**
 * ���ܣ����������Ԫ������е�ֵ�Ƿ����
 * @param cell1 ��Ԫ�����1
 * @param cell2 ��Ԫ�����2
 */
function valueEquals(cell1, cell2){
    var equals = false;
    var childNodes1 = cell1.childNodes;
    var childNodes2 = cell2.childNodes;
    if(childNodes1 && childNodes2){
        var field1 = getHtmlNode(childNodes1, "INPUT");
        var field2 = getHtmlNode(childNodes2, "INPUT");
        if(field1 != null && field2 != null){
            equals = (field1.value == field2.value);
        } else {
            field1 = getHtmlNode(childNodes1, "#text");
            field2 = getHtmlNode(childNodes2, "#text");
            if(field1 != null && field2 != null){
                equals = (field1.nodeValue == field2.nodeValue);
            } else {
                equals = (cell1.innerText == cell2.innerText);
            }
        }
    } else {
        equals = (cell1.innerText == cell2.innerText);
    }
    return equals;
}


/**
 * ���ܣ���ȡHTMLԪ���б���ָ�����Ƶ�Ԫ��
 * @param fields HTMLԪ���б�
 * @param nodeName ��������
 */
function getHtmlNode(fields, nodeName){
    var field = null;
    for(var i = 0; i < fields.length; i++){
        var field1 = fields[i];
        if(field1.nodeName == nodeName){
            field = field1;
            break;
        }
    }
    return field;
}

/**
 * ���ܣ�����Ԫ��Ŀ��
 * @param cell1
 * @param cell2
 */
function do_ProcessCellWidth(cell1, cell2){
    var width1 = cell1.width;
    var width2 = cell2.width;
    cell1.width = Number(width1) + Number(width2);
}

/**
 * ���ܣ��ϲ����ݱ��ķ����ֶ���
 */
function do_RowSpanDataTable(){
    if(dataTable == undefined || dataTable == null){
        return;
    }
    if(leftCategorySize > 1){
        do_AutoRowSpan(dataTable, leftCategorySize - 1);
    }
}

/**
 *  ���ܣ��ϲ������
 * @param tab ������
 * @param columnNumber ���ϲ���������
 */
function do_AutoRowSpan(tab, columnNumber){
    var head = new Array(columnNumber);
    var rows = tab.rows;
    var rowCount = rows.length;
    if (rowCount > 1) {
        var i = 0;
        var j = 0;
        var cell = null;
        var cellNode = null;
        var firstRow = tab.rows[0];
        var cells = firstRow.cells;
        for (j = 0; j < columnNumber; j++) {
            cell = cells[j];
            var nodes = cell.childNodes;
            cellNode = getHtmlNode(nodes, "INPUT");
            if(cellNode != null){
                head[j] = cellNode.value;
            } else {
                cellNode = getHtmlNode(nodes, "#text");
                if(cellNode != null){
                   head[j] = cellNode.nodeValue;
                } else {
                    head[j] = cells[j].innerText;
                }
            }
        }
        for (i = 1; i < rowCount; i++) {
            for (j = 0; j < columnNumber; j++) {
                nodes = rows[i].cells[j].childNodes;
                cellNode = getHtmlNode(nodes, "INPUT");
                if(cellNode != null){
                    if(head[j] == cellNode.value){
                        rows[i].cells[j].innerHTML = "";
                    } else {
                        head[j] = cellNode.value;
                    }
                } else {
                    cellNode = getHtmlNode(nodes, "#text");
                    if(cellNode != null){
                        if(head[j] == cellNode.nodeValue){
                            rows[i].cells[j].innerHTML = "";
                        } else {
                            head[j] = cellNode.nodeValue;
                        }
                    } else {
                        if(head[j] == cells[j].innerText){
                            rows[i].cells[j].innerHTML = "";
                        } else {
                            head[j] = cells[j].innerText;
                        }
                    }
                }
            }
        }
        for (j = columnNumber; j >= 0; j--) {
            cell = rows[0].cells[j];
            for (i = 1; i < rowCount; i++) {
                var loopCell = tab.rows[i].cells[j];
                if (loopCell.innerHTML == "") {
                    cell.rowSpan++;
                    cell.height = cell.height + loopCell.height;
                    rows[i].deleteCell(j);
                } else {
                    cell = loopCell;
                }
            }
        }
    }
}

/**
 * ���ܣ���̽��Ŀ�걨��
 */
function do_DrillDownReport(){
    var drillDownParameters = document.getElementById("drillDownParameters").value;
    if(drillDownParameters == ""){
        return;
    }
    var obj = event.srcElement;
    if(!obj.tagName){
        return;
    }
    while(obj.tagName != "TD"){
        obj = obj.parentNode;
    }
    var cellIndex = obj.cellIndex;
    if(cellIndex < drillStartColumn){
        return;
    }
    var rowIndex = obj.parentNode.rowIndex;
    var headRows = headTable.rows;
    var dataRows = dataTable.rows;
    var parameterArr = null;
    if(drillDownParameters.indexOf("&") > -1){
        parameterArr = drillDownParameters.split("&");
    } else {
        parameterArr = new Array(drillDownParameters);
    }
    var userParameter = "";
    for(var i = 0; i < parameterArr.length; i++){
        var parameter = parameterArr[i];
        var index = parameter.indexOf("=");
        if(index == -1){
            continue;
        }
        var fieldName = parameter.substring(index);
        fieldName = fieldName.substring(2, fieldName.length - 1);
        var fields = document.getElementsByName(fieldName);
        if(!fields){
            continue;
        }
        var fieldCount = fields.length;
        var field = null;
        var viewLocation = null;
        for(var fIndex = 0; fIndex < fieldCount; fIndex++){
            viewLocation = fields[i].getAttribute("viewLocation");
            if(viewLocation){
                field = fields[i];
                break;
            }
        }
        if(!field){
            return;
        }
        var parameterName = parameter.substring(0, index);
        if(i > 0){
            parameterName = "&" + parameterName;
        }
        userParameter += parameterName;
        var categoryLevel = field.getAttribute("categoryLevel");
        if(viewLocation == viewLocationAbove){
            var headRow = headRows[Number(categoryLevel) - 1];
            var headCells = headRow.cells;
            var colSpan = headRows[0].cells[0].colSpan;
            for(var j = 0; j < headCells.length; j++){
                var headCell = headCells[j];
                colSpan += headCell.colSpan;
                if(colSpan > cellIndex){
                    userParameter += "=" + headCell.childNodes[0].value;
                    break;
                }
            }
        } else if(viewLocation == viewLocationLeft){
            var rowSpan = 0;
            for(var k = 0; k < dataRows.length; k++){
                var dataCell = dataRows[k].cells[Number(categoryLevel) - 1];
                rowSpan += dataCell.rowSpan;
                if(rowSpan > rowIndex){
                    userParameter += "=" + dataCell.childNodes[0].value;
                    break;
                }
            }
        }
    }
    var reportId = document.getElementById("drillDownReport").value;
    var actionURL = "";
    var pageConfig = new DataPageConfig();
    if(reportId != ""){
        actionURL = "/rds/reportRun.do";
        userParameter += "&reportId=" + reportId;
        pageConfig.setAct("");
        pageConfig.setWindowName(reportId);
    } else {
        actionURL = document.getElementById("drillDownUrl").value;
    }
    if(actionURL != ""){
        pageConfig.setActionURL(actionURL);
        pageConfig.setParameters(userParameter);
        do_ProcessDataPage(pageConfig);
    }
}