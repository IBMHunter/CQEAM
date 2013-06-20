/**
 * ���ܣ����������ָ�����У�ʹ����е����ݰ���������
 * tableId��Ҫ����ı��ID
 * startRowIndex������п�ʼ�������ʼ�кţ�1Ϊ��㣩
 * columnIndex�������Ҫ������кţ�1Ϊ��㣩
 * pDataType:��������  Ŀǰ֧��int��float, date ,currency
 */
var vDataType;
function autoSort(tableId, startRowIndex, columnIndex, pDataType) {
    var ascent;
    var targetRow;
    var fromRow;
    vDataType = pDataType;
    startRowIndex = startRowIndex - 1;
    columnIndex = columnIndex - 1;
    var table = document.getElementById(tableId);
    var rowCount = table.childNodes[0].childNodes.length;
    if (rowCount > 0) {
        setOtherStyle();
        var rows = table.childNodes[0].childNodes;
        if (getCellValue(rows[0].cells[columnIndex]) > getCellValue(rows[rowCount - 1].cells[columnIndex])) {
            ascent = true;
        } else {
            ascent = false;
        }
        if (ascent) {
            for (var i = startRowIndex; i < rowCount; i++) {
                for (var j = 0; j < rowCount - i - 1; j++) {
                    targetRow = rows[j + 1];
                    fromRow = rows[j];
                    if (getCellValue(fromRow.cells[columnIndex]) > getCellValue(targetRow.cells[columnIndex])) {
                        fromRow.swapNode(targetRow)
                    }
                }
            }
            event.srcElement.className = 'sortAscSpan';
        } else {
            for (var i = startRowIndex; i < rowCount; i++) {
                for (var j = 0; j < rowCount - i - 1; j++) {
                    targetRow = rows[j + 1];
                    fromRow = rows[j];
                    if (getCellValue(fromRow.cells[columnIndex]) < getCellValue(targetRow.cells[columnIndex])) {
                        fromRow.swapNode(targetRow)
                    }
                }
            }
            event.srcElement.className = 'sortDescSpan';
        }
    }

}
function setOtherStyle() {
    var obj = document.getElementsByName("sortSpan");
    if (obj && obj.length) {
        for (var i = 0; i < obj.length; i++) {
            obj[i].className = 'sortSpanStyle';
        }
    }
}
/**
 * ���ܣ��õ������ָ���е�ֵ
 * cellObj������е��ж���
 */
function getCellValue(cellObj) {
    var cellValue = '';
    if (cellObj.childNodes.length > 0) {
        if (typeof(cellObj.childNodes[0].value) == "undefined") {
            cellValue = cellObj.innerText;
        }
    }
    return convert(cellValue, vDataType);
}
//��������ת������
function convert(sValue, sDataType) {
    sValue = trim(sValue);
    switch (sDataType) {
        case "int":return parseInt(sValue);
        case "float": return parseFloat(sValue);
        case "date": return getDate(sValue);
        default:return sValue.toString();
    }
}
/**
 * ���ܣ����ַ���ǰ��ո�ȥ��
 * @param {Object} srcStr Դ�ַ���
 */
function trim(srcStr) {
    while (srcStr.charAt(0) == " ") {
        srcStr = srcStr.substring(1);
    }
    while (srcStr.charAt(srcStr.length - 1) == " ") {
        srcStr = srcStr.substring(0, srcStr.length - 1);
    }
    return srcStr;
}
function getDate(vStr) {
    // vStr = trim(vStr);
    if (vStr != '') {
        var cArr = vStr.split("-");
        if (cArr.length = 3) {
            return new Date(cArr[0], cArr[1], cArr[2]);
        }
    }
    return null;
}

