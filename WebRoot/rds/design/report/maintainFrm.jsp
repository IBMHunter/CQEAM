<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@ page import="com.sino.rds.share.form.ReportDefineFrm" %>
<%
    String contextPath = request.getContextPath();
    ReportDefineFrm frm = (ReportDefineFrm) request.getAttribute("reportDefineFrm");
    String reportId = frm.getReportId();

    String modelListURL = "/rds/dataModelAction.do";

    String modelDataURL = "/rds/dataModelAction.do";
    modelDataURL += "?act=DETAIL_ACTION";

    String modelFieldURL = "/rds/modelFieldAction.do";
    modelFieldURL += "?act=DETAIL_ACTION";

    String title = "������ά��";

    String reportListURL = "/rds/reportDefineAction.do";

    String reportDataURL = "/rds/reportDefineAction.do";
    reportDataURL += "?act=DETAIL_ACTION";
    reportDataURL += "&modelId=" + frm.getModelId();
    reportDataURL += "&modelName=" + frm.getModelName();
    reportDataURL += "&reportId=" + frm.getReportId();

    String reportViewURL = "/rds/reportViewProcess.do";
    reportViewURL += "?act=DETAIL_ACTION";
    reportViewURL += "&reportId=" + reportId;

    String reportParameterURL = "/rds/reportParameterProcess.do";
    reportParameterURL += "?act=DETAIL_ACTION";
    reportParameterURL += "&reportId=" + reportId;

    String reportCategoryURL = "/rds/reportCategoryProcess.do";
    reportCategoryURL += "?act=DETAIL_ACTION";
    reportCategoryURL += "&reportId=" + reportId;

    String fixedCategoryURL = "/rds/fixedCategoryAction.do";
    fixedCategoryURL += "?act=DETAIL_ACTION";
    fixedCategoryURL += "&reportId=" + reportId;//���油�䣬��ʱ������ 2011-08-01

%>
<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title><%=title%></title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/rds/WebLibary/css/tab.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/rds/WebLibary/css/rds.css">
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/tab/tab.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/util/Constant.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/util/DateProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/util/CommonUtil.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/toolbar/SinoToolBar.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/toolbar/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/form/FormProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/form/RadioProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/form/CheckboxProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/form/SelectProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/table/TableProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/appEntry/AppStandard.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/appEntry/ActionButton.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/util/AjaxProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/validate/FormValidate.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/appEntry/ActionButton.js"></script>


</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0 style="overflow:auto" onload="do_initPage();">
<jsp:include page="/message/MessageProcess" flush="true"/>
<html:form action="/rds/maintainFrmAction" method="post">
<script type="text/javascript">

    var tabBox = new TabBox("tab");

    function do_initTabItem(){
        tabBox.addtab("modelListDiv", "�Ѷ���ģ��");
        tabBox.addtab("modelDataDiv", "ģ�ͻ�����Ϣ");
        tabBox.addtab("modelFieldDiv", "ģ���ֶ���Ϣ");

        tabBox.addtab("reportListDiv", "�Ѷ��屨��");
        tabBox.addtab("reportDataDiv", "���������Ϣ");

        tabBox.addtab("reportViewDiv", "���������ֶ�");
        tabBox.addtab("reportParameterDiv", "��������ֶ�");
        tabBox.addtab("reportCategoryDiv", "��������ֶ�");
        tabBox.init();
    }

    ArrActions[0][0] = true;
    ArrActions[2] = new Array(false, "����ģ��", "action_edit.gif", "����ģ��", "do_Create");
    ArrActions[3] = new Array(false, "��������", "action_edit.gif", "��������", "do_Create");
    ArrActions[4][0] = true;
    ArrActions[5] = new Array(false, "����Ԥ��", "application_xp.png", "����Ԥ��", "do_Preview");
    ArrActions[6] = new Array(false, "���Ʊ���", "PASTE.gif", "���Ʊ���", "do_CopyReport");
    ArrActions[15][0] = true;

	printTitleBar("<%=title%>");
	printToolBar();
    do_initTabItem();
</script>

<table id="divTable" align="center" width='100%' border="0" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF" style="width:100%;height:87%">
	<tr>
		<td style="width:100%;height:100%">
			<div id="modelListDiv" style='display:none'>
				<iframe id="modelListFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=modelListURL%>"></iframe>
			</div>
			<div id="modelDataDiv" style='display:none'>
				<iframe id="modelDataFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=modelDataURL%>"></iframe>
			</div>
			<div id="modelFieldDiv" style='display:none'>
				<iframe id="modelFieldFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=modelFieldURL%>"></iframe>
			</div>
			<div id="reportListDiv" style='display:none'>
				<iframe id="reportListFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=reportListURL%>"></iframe>
			</div>
			<div id="reportDataDiv" style='display:none'>
				<iframe id="reportDataFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=reportDataURL%>"></iframe>
			</div>
            <div id="reportViewDiv" style='display:none'>
                <iframe id="reportViewFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=reportViewURL%>"></iframe>
            </div>
			<div id="reportParameterDiv" style='display:none'>
				<iframe id="reportParameterFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=reportParameterURL%>"></iframe>
			</div>
			<div id="reportCategoryDiv" style="display:none">
				<iframe id="reportCategoryFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=reportCategoryURL%>"></iframe>
			</div>
		</td>
	</tr>
</table>
    <html:hidden property="modelId" styleId="modelId"/>
    <html:hidden property="modelName" styleId="modelName"/>
    <html:hidden property="reportId" styleId="reportId"/>
    <html:hidden property="act" styleId="act"/>
</html:form>
</body>
</html>

<script type="text/javascript">

function do_initPage() {
    window.focus();
    do_initTabBox();

}


function do_ShowReportCopyBtn(){
    ShowSinoButton(6);
}

function do_HideReportCopyBtn(){
    HideSinoButton(6);
}

function do_initTabBox(){
    tabBox.addItemProcessor("reportListDiv", do_ShowReportCopyBtn, null);

    tabBox.addItemProcessor("modelListDiv", do_HideReportCopyBtn, null);
    tabBox.addItemProcessor("modelDataDiv", do_HideReportCopyBtn, null);
    tabBox.addItemProcessor("modelFieldDiv", do_HideReportCopyBtn, null);
    tabBox.addItemProcessor("reportDataDiv", do_HideReportCopyBtn, null);
    tabBox.addItemProcessor("reportViewDiv", do_HideReportCopyBtn, null);
    tabBox.addItemProcessor("reportParameterDiv", do_HideReportCopyBtn, null);
    tabBox.addItemProcessor("reportCategoryDiv", do_HideReportCopyBtn, null);

    tabBox.inithidetab(1);
}

var currentFrame = "";
var currDataFrmId = "";
var listFieldName = "";
var divName = "";


function do_ClearGlobalValue(){
    currentFrame = "";
    currDataFrmId = "";
    listFieldName = "";
    divName = "";
}

function do_Confirm(){
    var isValid = false;
    if(confirm("ȷ���㵱ǰ�������ǡ�" + divName + "����ȷ��������ȷ������ť������������ȡ������ť��")){
        isValid = true;
    }
    return isValid;
}


function do_ProduceCurrentTab(){
    var divs = document.getElementsByTagName("div");
    for(var i = 0; i < divs.length; i++){
        var divObj = divs[i];
        do_ClearGlobalValue();
        if(divObj.style.display != "none"){
            if(divObj.id == "modelListDiv"){
                divName = "�Ѷ���ģ��";
                currentFrame = "modelListFrm";
            } else if(divObj.id == "modelDataDiv"){
                divName = "ģ�ͻ�����Ϣ";
                currentFrame = "modelDataFrm";
            } else if(divObj.id == "modelFieldDiv"){
                divName = "ģ���ֶ���Ϣ";
                currentFrame = "modelFieldFrm";
            } else if(divObj.id == "reportListDiv"){
                divName = "�Ѷ��屨��";
                currentFrame = "reportListFrm";
            } else if(divObj.id == "reportDataDiv"){
                divName = "���������Ϣ";
                currentFrame = "reportDataFrm";
            } else if(divObj.id == "reportViewDiv"){
                divName = "���������ֶ�";
                currentFrame = "reportViewFrm";
            } else if(divObj.id == "reportParameterDiv"){
                divName = "��������ֶ�";
                currentFrame = "reportParameterFrm";
            } else if(divObj.id == "reportCategoryDiv"){
                divName = "��������ֶ�";
                currentFrame = "reportCategoryFrm";
            } else if(divObj.id == "lovDefineDiv"){
                divName = "LOVֵ�б���";
                currentFrame = "lovDefineFrm";
            } else if(divObj.id == "lookUpDefineDiv"){
                divName = "LOOKUP���Ҷ���";
                currentFrame = "lookUpDefineFrm";
            }
            if(divName == ""){
                continue;
            }
            break;
        }
    }
}

function do_Check_currentFrm(act){
    var isValid = true;
    if(act == "SAVE_ACTION"){
        if(currentFrame != "modelDataFrm"
                && currentFrame != "modelFieldFrm"
                && currentFrame != "reportDataFrm"
                && currentFrame != "reportViewFrm"
                && currentFrame != "reportParameterFrm"
                && currentFrame != "reportCategoryFrm"
                ){
            isValid = false;
        }
    } else if(act == "QUERY_ACTION"){
        if(currentFrame != "modelListFrm"&& currentFrame != "reportListFrm"){
            isValid = false;
        }
    }
    return isValid;
}

function do_Save(){
    do_ProduceCurrentTab();
    if(!do_Check_currentFrm("SAVE_ACTION")){
        if(divName == ""){
            alert("��δ������ϣ�����ִ�б��������");
        } else {
            alert("��ǰѡ��ǡ�" + divName + "��������ִ�б��������");
        }
    } else if(do_Confirm()){
        var frm = document.getElementById(currentFrame);
        var childWin = frm.contentWindow;
        childWin.do_Save();
        setTimeout(do_RefreshURLs, 3000);
    }
}

function do_RefreshURLs(){
    if(currentFrame == "modelDataFrm"){
        do_RefreshModelField();
    } else if(currentFrame == "reportDataFrm"){
        do_RefreshReportURL();
    } else if(currentFrame == "reportViewFrm"){
        do_RefreshReportCategoryURL();
    }
}

function do_RefreshModelField(){
    var modelFrm = document.getElementById("modelDataFrm");
    var doc = modelFrm.contentWindow.document;
    var actionURL = "/rds/modelFieldAction.do";
    actionURL += "?act=DETAIL_ACTION";
    actionURL += "&modelId=" + doc.getElementById("modelId").value;
    var frm = window.frames["modelFieldFrm"];
    frm.location = actionURL;
}

function do_RefreshReportURL(){
    do_RefreshReportViewURL();
    do_RefreshReportParameterURL();
    do_RefreshReportCategoryURL();
}

function do_RefreshReportViewURL(){
    var frm = document.getElementById("reportDataFrm");
    var doc = frm.contentWindow.document;
    var actionURL = "/rds/reportViewProcess.do";
    actionURL += "?act=DETAIL_ACTION";
    actionURL += "&reportId=" + doc.getElementById("reportId").value;
    frm = window.frames["reportViewFrm"];
    frm.location = actionURL;
}

function do_RefreshReportParameterURL(){
    var frm = document.getElementById("reportDataFrm");
    var doc = frm.contentWindow.document;
    var actionURL = "/rds/reportParameterProcess.do";
    actionURL += "?act=DETAIL_ACTION";
    actionURL += "&reportId=" + doc.getElementById("reportId").value;
    frm = window.frames["reportParameterFrm"];
    frm.location = actionURL;
}

function do_RefreshReportCategoryURL(){
    var frm = document.getElementById("reportDataFrm");
    var doc = frm.contentWindow.document;
    var actionURL = "/rds/reportCategoryProcess.do";
    actionURL += "?act=DETAIL_ACTION";
    actionURL += "&reportId=" + doc.getElementById("reportId").value;
    frm = window.frames["reportCategoryFrm"];
    frm.location = actionURL;
}

function do_Search(){
    do_ProduceCurrentTab();
    if(!do_Check_currentFrm("QUERY_ACTION")){
        if(divName == ""){
            alert("��δ������ϣ�����ִ�в�ѯ������");
        } else {
            alert("��ǰѡ��ǡ�" + divName + "��������ִ�в�ѯ������");
        }
    } else if(do_Confirm()){
        var frm = document.getElementById(currentFrame);
        var childWin = frm.contentWindow;
        childWin.do_Search();
    }
}

function do_CloseConfig(){
    var cfg = new CloseConfig();
    cfg.setEditPage(false);
    cfg.setRefreshOpener(false);
    return cfg;
}

function do_Create(){
    var obj = event.srcElement;
    if(obj.innerHTML == "��������"){
        do_ProcessReportURL();
    } else if(obj.innerHTML == "����ģ��"){
        do_ProcessModelURL();
    }
}

function do_ProcessModelURL(){
    var actionURL = "/rds/dataModelAction.do";
    actionURL += "?act=DETAIL_ACTION";
    var frm = window.frames["modelDataFrm"];
    frm.location = actionURL;

    actionURL = "/rds/modelFieldAction.do";
    actionURL += "?act=DETAIL_ACTION";
    frm = window.frames["modelFieldFrm"];
    frm.location = actionURL;

    HideSinoButton(2);
    tabBox.doclick(2, "");
}


function do_ProcessReportURL(){
    var actionURL = "/rds/reportDefineAction.do";
    actionURL += "?act=DETAIL_ACTION";
    actionURL += "&modelId=" + document.getElementById("modelId").value;
    var frm = window.frames["reportDataFrm"];
    frm.location = actionURL;

    actionURL = "/rds/reportViewProcess.do";
    actionURL += "?act=DETAIL_ACTION";
    frm = window.frames["reportViewFrm"];
    frm.location = actionURL;

    actionURL = "/rds/reportParameterProcess.do";
    actionURL += "?act=DETAIL_ACTION";
    frm = window.frames["reportParameterFrm"];
    frm.location = actionURL;

    actionURL = "/rds/reportCategoryProcess.do";
    actionURL += "?act=DETAIL_ACTION";
    frm = window.frames["reportCategoryFrm"];
    frm.location = actionURL;

    HideSinoButton(3);
    HideSinoButton(5);
    tabBox.doclick(5, "");
}

function do_Preview(){
    var frm = document.getElementById("reportDataFrm");
    var reportDoc = frm.contentWindow.document;
    var reportId = reportDoc.getElementById("reportId").value;
    if(reportId != ""){
        var actionURL = "/rds/reportRun.do";
        actionURL += "?act=";
        actionURL += "&preview=Y";
        actionURL += "&reportId=" + reportId;
        var winName = reportId + "_prevWin";
        window.open(actionURL, winName, "fullscreen=yes");
    } else {
        alert("û��ѡ����������Ԥ����");
    }
}

function do_Close(){
    self.close();
}

function do_CopyReport(){
    var frm = document.getElementById("reportListFrm");
    var childWin = frm.contentWindow;
    childWin.do_CopyReport();
}
</script>
