//��ťָ�ļ�BarVarSX.js�еİ�ť��

var needAttachMenu = true;
var usedInProcedure = true;
var firstNode = false;
var enableSign = false;//�Ƿ񿪷�ǩ�հ�ť,������Ҫ���Ÿð�ť��ʡ�ݣ���Ҫ����Ϊtrue����ʵʩ��Աע��

function do_ControlProcedureBtn() {
    var frm = document.forms[0];
    var fromPage = document.getElementById("fromPage");//�������⴦��Ѳ�칤����ť��
    if(usedInProcedure){
        var sfAttr = frm.sf_task_attribute3;
        var sfAttr5 = frm.sf_task_attribute5;
        if(fromPage && fromPage.value == "DETAIL_PAGE"){
            do_ControlOrderDetailPageBtn();
        } else {
            if (sfAttr) {
                var sfAttribute3 = sfAttr.value;
                var sfAttribute5 = sfAttr5.value;
                if (sfAttribute3 == "FILL_DATA") {//��һ���ڵ�
                    firstNode = true;
                    do_ControlFirstNodeBtn();
                } else if ( sfAttribute5.indexOf("ATTACHMENT") > -1 ) {//��Ҫ�������Ľڵ�
                    do_ControlOtherNodeBtn_ManageAttachment();
                } else {
                    do_ControlOtherNodeBtn();
                }
            } else {
                do_ControlOrderDetailPageBtn();
            }
        }
    } else {
        do_ControlCommonPageBtn();
    }
}

function do_ControlFirstNodeBtn() {
    var btnCount = ArrActions.length;
    var frm = document.forms[0];
    var primaryKey = frm.transId;
    if(!primaryKey){
        primaryKey = frm.batchId;
    }
    if(!primaryKey){
        primaryKey = frm.systemid;
    }
    var transId = primaryKey.value;
    for (var i = 0; i < btnCount; i++) {
        if (i <= 2 || i == 5) {
            ShowSinoButton(i);
        } else if (i == 13 || i == 16) {
            if (transId == "") {
                HideSinoButton(i);
            } else {
                ShowSinoButton(i);
            }
        } else if(i== 28){
            if(needAttachMenu){
                ShowSinoButton(i);
            } else {
                HideSinoButton(i);
            }
        } else {
            HideSinoButton(i);
        }
    }
}

/**
 * ��Ҫ�ϴ������Ľڵ�İ�ť����
 */
function do_ControlOtherNodeBtn_ManageAttachment(){
	var btnCount = ArrActions.length;
    for (var i = 0; i < btnCount; i++) {
        if (i == 0 || i == 2 || i == 3 || i == 5 || i == 16) {
            ShowSinoButton(i);
        } else if(i == 8){
            if(enableSign){
                ShowSinoButton(i);
            } else {
                HideSinoButton(i);
            }
        } else if(i == 28){
            if(needAttachMenu){
                ShowSinoButton(i);
            } else {
                HideSinoButton(i);
            }
        } else {
            HideSinoButton(i);
        }
    }
}

function do_ControlOtherNodeBtn() {
    var btnCount = ArrActions.length;
    for (var i = 0; i < btnCount; i++) {
        if (i == 0 || i == 2 || i == 3 || i == 5 || i == 16) {
            ShowSinoButton(i);
        } else if(i == 8){
            if(enableSign){
                ShowSinoButton(i);
            } else {
                HideSinoButton(i);
            }
        } else if(i == 29){
            if(needAttachMenu){
                do_ControlAttachViewBtn();
            } else {
                HideSinoButton(i);
            }
        } else {
            HideSinoButton(i);
        }
    }
}

function do_ControlOrderDetailPageBtn() {
    var btnCount = ArrActions.length;
    for (var i = 0; i < btnCount; i++) {
        if (i == 0 || i == 16) {
            ShowSinoButton(i);
        } else if(i == 29){
            if(needAttachMenu){
                do_ControlAttachViewBtn();
            } else {
                HideSinoButton(i);
            }
        } else {
            HideSinoButton(i);
        }
    }
}


function do_ControlCommonPageBtn() {
    var btnCount = ArrActions.length;
    for (var i = 0; i < btnCount; i++) {
        if (i == 0 || i == 1) {
            ShowSinoButton(i);
        } else if(i== 28){
            if(needAttachMenu){
                ShowSinoButton(i);
            } else {
                HideSinoButton(i);
            }
        } else {
            HideSinoButton(i);
        }
    }
}


function do_ControlAttachViewBtn(){
    var primaryKey = do_getPrimaryKey();
    if(primaryKey != ""){
        var actionURL = "/servlet/com.sino.ams.adjunct.servlet.FileMaintenanceServlet";
        actionURL += "?forward=GET_ATTACH_COUNT";
        actionURL += "&orderPkName=" + primaryKey;
        var ajaxProcessor = new AjaxProcessor(actionURL, do_CallbackAttachViewBtn, false);
        ajaxProcessor.performTask();
    } else {
        HideSinoButton(29);
    }
}


function do_CallbackAttachViewBtn(resText){
    if(Number(resText) > 0){
        var divId="sinoDiv29";
        var btnDiv = document.getElementById(divId);
        var btnStr = btnDiv.innerHTML;
        var newLabel = "��������(" + resText + ")";
        btnStr = replaceStr(btnStr, "��������", newLabel);
        btnDiv.innerHTML = btnStr;
        ShowSinoButton(29);
    }
}

function do_CancelApply() {
    if (confirm("ȷ�������������𣿼���������ȷ������ť������������ȡ������ť��")) {
        isSave = true;
        var frm = document.forms[0];
        if(frm.act){
            frm.act.value = "CANCEL_ACTION";
        } else {
            frm.flowSaveType.value="FLOW_CANCEL";
        }
        frm.sf_opinion.value = "�����˳�������";
        frm.sf_flowDesc.value = frm.sf_opinion.value;
        frm.submit();
        var transTypeValue = "";
        var transType = frm.transTypeValue;
        if(!transType){
            transType = frm.workorderTypeDesc;
        }
        if(!transType){
            transType = frm.orderTypeName;
        }
        if (transType) {
            transTypeValue = transType.value;
        } else {
            transTypeValue = "���̵���";
        }
        var orderNo = "";
        var transNo = frm.transNo;
        if(!transNo){
            transNo = frm.workorderBatch;
        }
        if(!transNo){
            transNo = frm.batchNo;
        }
        if (transNo) {
            orderNo = transNo.value;
        }
        var messageDiv = document.getElementById("$$$publicMessage$$$");
        if (messageDiv) {
            messageDiv.style.visibility = "hidden";
        }
        document.getElementById("hintTD").innerHTML = "<font color=\"#008000\" size=\"2\">���ڳ���"
            + transTypeValue
            + orderNo
            + "�����Ժ�......</font><img src=\"/images/wait.gif\">";
        document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
    }
}

function do_ViewOpinion() {
	var primaryKey = do_getPrimaryKey_Obj();
    if(!primaryKey){
        alert("ҳ��û��ָ��Ӧ�õ���������֪ͨ����֧����Ա��");
        return;
    }
    var tableName = "AMS_ASSETS_TRANS_HEADER";
    viewOpinion(primaryKey.value, tableName);
}

function do_Back_app() {
    isSave = true;
    var frm = document.forms[0];
    setFrmEnable(frm.name);
    if(frm.act){
        frm.act.value = "REJECT_ACTION";
    } else if(frm.flowSaveType){
        frm.flowSaveType.value="FLOW_BACK";
    }
    frm.submit();
    var transTypeValue = "";
    var transType = frm.transTypeValue;
    if(!transType){
        transType = frm.workorderTypeDesc;
    }
    if(!transType){
        transType = frm.orderTypeName;
    }
    if (transType) {
        transTypeValue = transType.value;
    } else {
        transTypeValue = "���̵���";
    }
    var orderNo = "";
    var transNo = frm.transNo;
    if(!transNo){
        transNo = frm.workorderBatch;
    }
    if(!transNo){
        transNo = frm.batchNo;
    }
    if (transNo) {
        orderNo = transNo.value;
    }
    var messageDiv = document.getElementById("$$$publicMessage$$$");
    if (messageDiv) {
        messageDiv.style.visibility = "hidden";
    }
    document.getElementById("hintTD").innerHTML = "<font color=\"#008000\" size=\"2\">�����˻�"
        + transTypeValue
        + orderNo
        + "�����Ժ�......</font><img src=\"/images/wait.gif\">";
    document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}

function do_ShowDetail(obj){
    var barcode = "";
    if(obj){
        if(obj.tagName){
            if(obj.tagName == "INPUT"){
                barcode = obj.value;
            } else {
                while(obj.tagName != "TR"){
                    obj = obj.parentNode;
                }
                if(obj.tagName == "TR"){
                    obj = getTrNode(obj, "barcode");
                    if(obj){
                        barcode = obj.value;
                    }
                }
            }
        } else {
            barcode = obj;
        }
    }
    if(barcode && barcode != ""){
        var factor = 0.8;
        var width = window.screen.availWidth * factor;
        var height = window.screen.availHeight * factor;
        var left = window.screen.availWidth * (1 - factor) / 2;
        var top = window.screen.availHeight * (1 - factor) / 2;
        var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=DETAIL_ACTION&barcode=" + barcode;
        var winName = "assetsWin";
        var style = "width="
            + width
            + ",height="
            + height
            + ",left="
            + left
            + ",top="
            + top;
        window.open(url, winName, style);
    }
}

var excelType = null;

function do_ImportExcelData(){
    if(!excelType){
        excelType = "1";
    }
    var url = "";
    if(excelType == "1")  {
        url = "/workorder/bts/upFile.jsp";
    } else if(excelType == "2")  {
        url = "/newasset/newSite/upFile.jsp";
    } else if(excelType == "3")  {
    	url = "/workorder/bts/upFile2.jsp";
    } else if(excelType == "4")  {
    	url = "/newasset/newSite/upFileSecond.jsp";
    } else if(excelType=="5"){
    	url = "/workorder/bts/upFile3.jsp";//�㹺ת��
    } else if(excelType=="6"){
    	url = "/workorder/bts/upFile4.jsp";//�㹺ת��
    }
    
    var factor = 0.5;
    var dialogWidth = window.screen.availWidth * factor;
    var dialogHeight = window.screen.availHeight * factor;
    var dialogStyle = "dialogWidth:"
            + dialogWidth
            + "px;dialogHeight:"
            + dialogHeight
            + "px;center:yes;status:no;scrollbars:no;help:no;status=no;center=yes;toolbar=no;menubar=no;resizable=no;scroll=no";
    return window.showModalDialog(url,"",dialogStyle);
}


function do_getPrimaryKey(){
    var primaryKeyValue = "";
    var primaryKey = do_getPrimaryKey_Obj();
    if(primaryKey){
        primaryKeyValue = primaryKey.value;
    }
    return primaryKeyValue;
}

function do_getPrimaryKey_Obj(){
	var primaryKey = null;
    var frm = document.forms[0];
    if( frm ){
    	primaryKey = frm.transId;
	    if(!primaryKey){
	        primaryKey = frm.batchId;
	    }
	    if(!primaryKey){
	        primaryKey = frm.systemid;
	    }
	    if(!primaryKey){
	        primaryKey = frm.systemId;
	    }
	    if(!primaryKey){
	        primaryKey = frm.publishId;
	    }
    }else{
        primaryKey = document.getElementById("transId");
         if(!primaryKey){
	        primaryKey = document.getElementById("batchId");
	    }
	    if(!primaryKey){
	        primaryKey = document.getElementById("systemid");
	    }
	    if(!primaryKey){
	        primaryKey = document.getElementById("systemId");
	    }
	    if(!primaryKey){
	        primaryKey  = document.getElementById("publishId");
	    }
    }
    return primaryKey;
}

function do_ProcessTableAlign(){
    var summaryTable = document.getElementById("summaryTable");
    var headTable = document.getElementById("headTable");
    var rows = headTable.rows;
    var lastRow = rows[rows.length - 1];
    var cells = lastRow.cells;
    var cellCount = cells.length;
    var summaryRow = summaryTable.rows[0];
    var summaryCells = summaryRow.cells;
    var firstSummaryCell = summaryCells[0];
    var colSpan = firstSummaryCell.colSpan;
    var firstWidth = 0;
    summaryTable.style.width = headTable.style.width;
    for(var i = 0; i < cellCount; i++){
        var cell = cells[i];
        if(i < colSpan){
            firstWidth += cell.offsetWidth;
            if(i == colSpan - 1){
                firstSummaryCell.style.width = firstWidth;
            }
        } else {
            if(summaryCells[i - colSpan + 1]){
                summaryCells[i - colSpan + 1].style.width = cell.style.width;
            }
        }
    }
}
