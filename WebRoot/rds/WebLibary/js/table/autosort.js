/**
 * ���ܣ����������ָ�����У�ʹ����е����ݰ���������
 * tableId��Ҫ����ı��ID
 * startRowIndex������п�ʼ�������ʼ�кţ�1Ϊ��㣩
 * columnIndex�������Ҫ������кţ�1Ϊ��㣩
 */
function autoSort(tableId, startRowIndex, columnIndex) {
    var ascent;
    var targetRow;
    var fromRow;
    startRowIndex = startRowIndex - 1;
    columnIndex = columnIndex - 1;
    var table = document.getElementById(tableId);
    var rowCount = table.childNodes[0].childNodes.length;
    if(rowCount > 0){
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
        if (typeof(cellObj.childNodes[0].value) == undefined) {
            cellValue = cellObj.innerText;
        }
    }
    return cellValue;
}
