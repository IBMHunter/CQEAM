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
    var attchConfig = setAttachmentConfig();
    var checkResult = true;
    if(attchConfig == null){
        alert("����ҳ���ṩ����setAttachmentConfig");
        checkResult = false;
    }
    var orderPkName = attchConfig.orderPkName;
    orderPk = document.getElementById(orderPkName);
    if(orderPk == undefined || orderPk == null){
        var frm = document.forms[0];
        orderPk = frm.orderPkName;
    }
    if(orderPk == undefined || orderPk == null){
        alert("�������õ�������ID����");
        checkResult = false;
    }
    return checkResult;
}

function do_upload(){
    if(!do_CheckAttachmentConfig()){
        return;
    }
    var clickObj = event.srcElement;
    var innerHTML = clickObj.innerHTML;
    if(innerHTML.indexOf("����") > -1){
        do_ProcessAttachmentManage();
    } else if(innerHTML.indexOf("����") > -1){
        do_ProcessAttachmentSearch();
    }
}

function do_ProcessAttachmentManage(){
    var primaryKey = orderPk.value;
    if(primaryKey == ""){
        do_GeneratePrimaryKey();
    }
    do_ProcessAttachmentSearch();
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

function do_ProcessAttachmentSearch(){
    var factor = 0.7;
    var dialogWidth = window.screen.availWidth * factor;
    var dialogHeight = window.screen.availHeight * factor;
    var dialogLeft = window.screen.availWidth * (1 - factor) / 2;
    var dialogTop = window.screen.availHeight * (1 - factor) / 2;
    var url = "/servlet/com.sino.ams.adjunct.servlet.FileMaintenanceServlet?forward=EDIT_ACTION&orderPkName=" + orderPk.value;
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