var ArrAction0 = new Array(true, "ȡ��", "action_cancel.gif", "ȡ��", "do_Cancel");
var ArrAction1 = new Array(true, "�ݴ�", "action_save.gif", "�ݴ�", "do_Save");
var ArrAction2 = new Array(true, "�ύ", "action_sign.gif", "�ύ", "do_Complete");
var ArrAction3 = new Array(true, "�˻�", "arrow_pleft.gif", "�˻�", "do_Back");
var ArrAction4 = new Array(true, "����", "action_guiview.gif", "����", "do_SpecialSend");
var ArrAction5 = new Array(true, "���̲���", "actn023.gif", "���̲���", "do_ViewFlow") ;
var ArrAction6 = new Array(true, "��ʾ�趨", "download.gif", "��ʾ�趨", "do_SetReview");
var ArrAction7 = new Array(true, "��ʾ״̬", "year.gif", "��ʾ״̬", "do_ReviewStatus");
var ArrAction8 = new Array(true, "ǩ��", "checkin.gif", "ǩ��", "do_Sign");
var ArrAction9 = new Array(true, "������Ϣ", "bbsdoc2.gif", "������Ϣ", "do_ViewComment");

var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2, ArrAction3, ArrAction4, ArrAction5, ArrAction6,
        ArrAction7, ArrAction8, ArrAction9);
var ArrSinoViews = new Array();
var ArrSinoTitles = new Array();

var AV_SENDBACK_MASK = 0x01;
var AV_SPECIALSEND_MASK = 0x02;
var AV_COMPLETE_MASK = 0x04;
var AV_SAVE_MASK = 0x08;
var AV_SIGN_MASK = 0x10;

var OP_SENDBACK_MASK = 0x04;
var OP_SPECIALSEND_MASK = 0x02;
var OP_VIEWFLOW_MASK = 0x08;
var OP_CONFIRM_MASK = 0x800;
var OP_FINISH_MASK = 0x400;

function doUnLoad() {
    if(!isSave) {
        isSave = true;
        document.forms[0].action = "/servlet/com.sino.sinoflow.servlet.CaseCancel?sf_actID="+
                           document.getElementById("sf_actID").value;
        document.forms[0].submit();
    }

//    opener.location.reload(true);
    if(opener) {
        opener.doReload();
    } else {
        if((buttonMask & SIGN_MASK) == SIGN_MASK) {
             window.location = "/servlet/com.sino.sinoflow.servlet.PendingTray";
        } else {
             window.location = "/servlet/com.sino.sinoflow.servlet.InTray";
        }
    }
}

function do_SetReview() {
    var resText = selectReviewDlg(document.getElementById("sf_taskid").value);
    eval("reviewArray = " + resText);
    var type = 0;
    type = new Number(reviewArray[0].type) + new Number(reviewArray[0].waitType) * 2;
    document.getElementById("sf_reviewType").value = type;
    Launch_ReviewUsers = reviewArray[0].users;
    document.getElementById("sf_reviewUsers").value = Launch_ReviewUsers;
    document.getElementById("sf_reviewQty").value = getCharCount(Launch_ReviewUsers, ';');
//                document.getElementById("sf_sendtouser").value = Launch_ReviewUsers.substr(0, Launch_ReviewUsers.indexOf("/"));
    setReviewUsers(document.getElementById("sf_actID").value, document.getElementById("sf_reviewType").value,
            document.getElementById("sf_reviewQty").value, Launch_ReviewUsers);
}

function do_ReviewStatus() {
    var url;
    url = "/servlet/com.sino.sinoflow.servlet.GetReviewStatus?sf_actID="+
            document.getElementById("sf_actID").value;
    var popscript;
    popscript = "dialogWidth:450px"
            + ";dialogHeight:250px"
            + ";center:yes;status:no;scrollbars:no;help:no";
    return window.showModalDialog(url, null, popscript);
}

function do_Sign() {
    SFQuerySign();
    if(!Launch_Continue) {
        if(Error_Msg != "")
            alert(Error_Msg);
        return;
    }
    if(signAct() != "") {
        autoValue(AV_SIGN_MASK);
        alert("ǩ�ճɹ�������ת���ڰ��䣡")
        ShowSinoButton(1);
        ShowSinoButton(2);
        var type = document.getElementById("sf_reviewType").value;
        if((completeMask & OP_SENDBACK_MASK) == OP_SENDBACK_MASK && (type == "0" || type == "")) {
            ShowSinoButton(3);
        }
        if((completeMask & OP_SPECIALSEND_MASK) == OP_SPECIALSEND_MASK && (type == "0" || type == "")) {
            ShowSinoButton(4);
        }
        if((completeMask & OP_SPECIALSEND_MASK) == OP_SPECIALSEND_MASK && (type == "0" || type == "")) {
            ShowSinoButton(4);
        }

        HideSinoButton(8);
    }
    SFPostSign();
    if(Error_Msg != "") {
        alert(Error_Msg);
    }
}

var isSave = false;

function do_Complete() {
    SFQueryComplete();
    if(!Launch_Continue) {
        if(Error_Msg != "")
            alert(Error_Msg);
        return;
    }
    var str = getNextTask();
    if(str != "") {
        autoValue(AV_COMPLETE_MASK);
        if(!validation())
            return;
        SFQuerySave();
        if(!Launch_Continue) {
            if(Error_Msg != "")
                alert(Error_Msg);
            return;
        }
        clearDivRight();

        document.getElementById("sf_nextTaskData").value = str;
        if(finishMessage() == "") {
            restoreNextTaskData();
            return;
        }
        saveInfo();
        SFPostSave();
        isSave = true;
        document.forms[0].action = "/servlet/com.sino.sinoflow.servlet.CaseComplete";
        document.forms[0].submit();
    }
}

function do_Save() {
    autoValue(AV_SAVE_MASK);
    if(!validation())
        return;
    SFQuerySave();
    if(!Launch_Continue) {
        if(Error_Msg != "")
            alert(Error_Msg);
        return;
    }
    clearDivRight();
    isSave = true;
    document.forms[0].action = "/servlet/com.sino.sinoflow.servlet.CaseSave";
    document.forms[0].submit();
}

function do_SaveReload() {
    autoValue(AV_SAVE_MASK);
    if(!validation())
        return;
    SFQuerySave();
    if(!Launch_Continue) {
        if(Error_Msg != "")
            alert(Error_Msg);
        return;
    }
    clearDivRight();
    isSave = true;
    document.forms[0].action = "/servlet/com.sino.sinoflow.servlet.CaseSaveReload";
    document.forms[0].submit();
}

function do_Cancel() {
    if(opener) {
        window.close();
    } else {
        if((buttonMask & SIGN_MASK) == SIGN_MASK) {
             window.location = "/servlet/com.sino.sinoflow.servlet.PendingTray";
        } else {
             window.location = "/servlet/com.sino.sinoflow.servlet.InTray";
        }        
    }
}

function doBeforeUnload() {
    if(!isSave) {
//        isSave = true;
        window.event.returnValue = "�_��ȡ�����и���?";
    }
}

function do_appInit() {
    //�պ���, Ӧ���� jsp �п�дͬһ����ȡ��
}

function doLoad() {
//    fillData(document.getElementById("sf_fillApiData").value);
    if(document.getElementById("sf_lock").value == "1")
        alert("�������ѱ������Ñ���, �����ѱ�����, ֻ����ֻ����ʽ������!");
//    setDivVisibility();
    init_groups();
    do_SelectGroup();
    fillData(document.getElementById("sf_fillApiData").value);
//    setDivRight();
    if(document.getElementById("sf_comment").value != "") {
        alert(document.getElementById("sf_comment").value);
    } else {
        HideSinoButton(9);    
    }
    do_appInit();
    SFQueryOpen();
    if(!Launch_Continue) {
        alert(Error_Msg);
        doUnLoad();
        return;
    }
    var tst;
    tst = document.getElementById("sinoflow_load_data").value;
    if(tst != null && tst != "")
        fillData(tst);

    SFPostOpen();
}

function do_Back() {
    SFQueryComplete();
    if(!Launch_Continue) {
        if(Error_Msg != "")
            alert(Error_Msg);
        return;
    }

    autoValue(AV_SENDBACK_MASK);
    if(!validation())
        return;

    var str = getBackTask(document.getElementById("sf_actID").value);
    if(str.indexOf("SPLIT") >= 0) {
/*
        var back = confirm("�˰����Ѳ�������ͬ���Ñ���, ��ʹ���˻،�ͬʱ�˻���Щ�˴����еİ���, �˻���?");
        alert(back);
        if(back == false)
            return;
*/

        alert("�˰����Ѳ�������ͬ���Ñ���, �����˻�!");
        return;        
    }
    if(str != "") {
        SFQuerySave();
        if(!Launch_Continue) {
            if(Error_Msg != "")
                alert(Error_Msg);
            return;
        }
        clearDivRight();
        isSave = true;
        document.forms[0].action = "/servlet/com.sino.sinoflow.servlet.CaseBack";
        document.forms[0].submit();
    }
}

function do_SpecialSend() {
    SFQueryComplete();
    if(!Launch_Continue) {
        if(Error_Msg != "")
            alert(Error_Msg);
        return;
    }

    autoValue(AV_SENDBACK_MASK);
    if(!validation())
        return;
    var sendTask = checkSpecialSend();

    if(sendTask != "") {
        SFQuerySave();
        if(!Launch_Continue) {
            if(Error_Msg != "")
                alert(Error_Msg);
            return;
        }
        clearDivRight();
        isSave = true;
        document.forms[0].action = "/servlet/com.sino.sinoflow.servlet.ToTask";
        document.forms[0].submit();
    } else {

    }
}

function do_Message() {
/*
    var openDocObj = new ActiveXObject("SharePoint.OpenDocuments.2");
    alert("openDocObj = " + openDocObj);
    openDocObj.EditDocument("/app/Validation Rules.doc");
*/
/*
    var wApp = new ActiveXObject("Word.Application.11");
    wApp.Visible = true ;

    wApp.Documents.Open("http://localhost:8080/app/Validation Rules.doc");

//    if( trackRevisions ){ //����ʵ�ֺۼ�������
         wApp.ActiveDocument.TrackRevisions = true ;
         wApp.ActiveDocument.ShowRevisions = false  ;
//    }else
//    {
//         wApp.ActiveDocument.TrackRevisions = false ;
//         wApp.ActiveDocument.ShowRevisions = false  ;           
//    }
*/
    window.open("http://localhost:8080/app/Validation Rules.doc");
}

function finishMessage() {
    if(document.getElementById("sf_reviewQty").value == "1")
        return "ok";
    var priority = "0";
    var comment = "";
    if((completeMask & OP_FINISH_MASK) == OP_FINISH_MASK) {
        var priorityStr = getPriorityDlg();
        if(priorityStr == "")
            return "";
        var priorityStruct;
        eval("priorityStruct = " + priorityStr);
        priority = priorityStruct.priority;
        comment = priorityStruct.message;
    }
    if((completeMask & OP_CONFIRM_MASK) == OP_CONFIRM_MASK) {
        if(isFlowEnd()) {
            document.getElementById("sfEnd").value = "1";
            if(deliveryStatusEndDlg() == "")
                return "";
        } else {
            if(deliveryStatusDlg() == "")
                return "";
        }
    }
    document.getElementById("sf_comment").value = comment;
    document.getElementById("sf_priority").value = priority;
    return "ok";
}

function isFlowEnd() {
    var prop;
    eval ("prop = " + document.getElementById("sf_nextTaskData").value);
    if(prop.length == 1) {
        if(prop[0].taskName == "STOP")
            return true;
    }
    return false;
}

function do_ViewComment() {
    alert(document.getElementById("sf_comment").value);
}
