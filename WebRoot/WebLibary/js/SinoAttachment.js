var orderPk = null;
function AttachmentConfig(){
    this.setOrderPkName = function(orderPkName){
        this.orderPkName = orderPkName;
    };
}

/**
 * ���ܣ�Ҫ���и��������ҳ����Ҫ���Ǳ�������
 */
function setAttachmentConfig(){
    return null;
}

function do_CheckAttachmentConfig(){
    var checkResult = true;
    var attchConfig = setAttachmentConfig();
    if(attchConfig == undefined || attchConfig == null){
        alert("����ҳ���ṩ����setAttachmentConfig");
        checkResult =  false;
    } else {
        var orderPkName = attchConfig.orderPkName;
        orderPk = document.getElementById(orderPkName);
        if(orderPk == undefined || orderPk == null){
            var frm = document.forms[0];
            orderPk = frm.orderPkName;
        }
        if(orderPk == undefined || orderPk == null){
            alert("�������õ�������ID����");
            checkResult =  false;
        }
    }
    return checkResult;
}

function do_upload(){
    var checkResult = do_CheckAttachmentConfig();
    if(!checkResult){
        return;
    }
    var clickObj = event.srcElement;
    var innerHTML = clickObj.innerHTML;
    if(innerHTML.indexOf("����") > -1){
        do_ProcessAttachmentManage("MANAGE");
    } else if(innerHTML.indexOf("����") > -1){
        do_ProcessAttachmentSearch("SEARCH");
    }
}

function do_ProcessAttachmentManage(){
    var primaryKey = orderPk.value;
    if(primaryKey == ""){
        do_GeneratePrimaryKey();
    }
    do_ProcessAttachmentSearch("MANAGE");
}

function do_GeneratePrimaryKey(){
    var actionURL = "/servlet/com.sino.ams.newasset.servlet.AmsReqServlet?reqType=GET_HEADER_ID";
    var ajaxProcessor = new AjaxProcessor(actionURL, do_CallbackPrimaryKey, false);
    ajaxProcessor.performTask();
}

function do_CallbackPrimaryKey(resText){
    var arr = resText.split("$##$");
    if (arr.length == 2 && arr[0] == 'ERROR') {
        alert("����ʧ�ܣ���ϸԭ��:" + arr[1]);
    } else {
        orderPk.value = resText;
    }
}

function do_ProcessAttachmentSearch(frmAction){
    var factor = 0.75;
    var dialogWidth = window.screen.availWidth * factor;
    var dialogHeight = window.screen.availHeight * factor;
    var dialogLeft = window.screen.availWidth * (1 - factor) / 2;
    var dialogTop = window.screen.availHeight * (1 - factor) / 2;
    var url = "/servlet/com.sino.ams.adjunct.servlet.FileMaintenanceServlet?forward=EDIT_ACTION&orderPkName=" + orderPk.value + "&frmAction=" + frmAction;
    var style = "dialogLeft:"+dialogLeft+"px;" +
            "dialogTop:"+dialogTop+"px;" +
            "dialogWidth:"+dialogWidth+"px;" +
            "dialogHeight:"+dialogHeight+"px;" +
            "toolbar:no;" +
            "directories:no;" +
            "status:no;" +
            "menubar:no;" +
            "scrollbars:no;" +
            "revisable:no;" +
            "resizable:yes";
    window.showModalDialog(url, null, style);
}