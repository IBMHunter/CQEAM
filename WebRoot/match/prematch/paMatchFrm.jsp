<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/match/prematch/headerInclude.jsp"%>
<%@ include file="/match/prematch/headerInclude.htm"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/WebLibary/css/tab.css">

<script language="javascript">
	var ArrAction0 = new Array(true, "�ر�", "close.gif", "�ر�", "do_Close()");
	var ArrAction1 = new Array(true, "ƥ��", "save.gif", "ƥ��", "do_PreMatch()");
	var ArrAction2 = new Array(true, "���ƥ��", "del.gif", "���ƥ��", "do_ReleaseMatch()");
	var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2);
	var ArrSinoViews = new Array();
	var ArrSinoTitles = new Array();
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0 style="overflow:auto" onload="initPage();">
<%
	String manualMatchURL = "/servlet/com.sino.ams.prematch.servlet.ManualMatchFrmServlet";
	String autoMatchURL = "/servlet/com.sino.ams.prematch.servlet.AutoMatchServlet";
	String matchedListURL = "/servlet/com.sino.ams.prematch.servlet.AmsPaMatchServlet?srcPage=" + AMSActionConstant.DELETE_ACTION;
%>
<script language="javascript">
	var tabBox = new TabBox("tab");
	tabBox.addtab("autoMatch", "�Զ�ƥ��");
	tabBox.addtab("manualMatch", "�ֹ�ƥ��");
	tabBox.addtab("matchedList", "ƥ���嵥");
	printTitleBar("EAM�豸��MISת���嵥Ԥƥ��");
	printToolBar();
	tabBox.init();
</script>

<table align="center" width='100%' border="0" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF" style="width:100%;height:88%">
	<tr>
		<td style="width:100%;height:100%">
			<div id="autoMatch" style='display:none'>
				<iframe id="autoMatchFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=autoMatchURL%>"></iframe>
			</div>
			<div id="manualMatch" style='display:none'>
				<iframe id="manualMatchFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=manualMatchURL%>"></iframe>
			</div>
			<div id="matchedList" style='display:none'>
				<iframe id="matchedListFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=matchedListURL%>"></iframe>
			</div>
		</td>
	</tr>
</table>
</body>
</html>

<script language="javascript">
function initPage() {
	window.focus();
	document.all("autoMatch").style.display = "";
	document.all("manualMatch").style.display = "none";
	document.all("matchedList").style.display = "none";
	tabBox.inithidetab(1);
}

function do_PreMatch(){
	var autoStyle = document.all("autoMatch").style.display;
	var manualStyle = document.all("manualMatch").style.display;
	if(autoStyle != "none"){
		do_AutoMatch();
	} else if(manualStyle != "none"){
		do_ManualMatch();
	} else {
		alert("���Զ�ƥ�䡱�͡��ֹ�ƥ�䡱�������ڻ״̬������ִ��ƥ�����");
	}
}

/**
 * ���ܣ��Զ�ƥ��
 */
function do_AutoMatch(){
	var wind = window.frames["autoMatchFrm"];
	var mainFrm = wind.document.mainFrm;
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ƥ��");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("û��ѡ�����ݣ�����ƥ��");
		return;
	}
	if(confirm("ȷ��ƥ��ѡ�е������𣿼���������ȷ������ť������������ȡ������ť")){
		wind.do_AutoMatch();
	}
}

/**
 * ���ܣ��ֹ�ƥ��
 */
function do_ManualMatch(){
	var manWind = window.frames["manualMatchFrm"];
	var amsWind = manWind.frames["amsFrm"];
	var misWind = manWind.frames["misFrm"];
	var amsDoc = amsWind.document;
	var misDoc = misWind.document;
	var sysObjs = amsDoc.getElementsByName("systemId");
	var tagObjs = misDoc.getElementsByName("tagNumber");

	if(!sysObjs){
		alert("û�����ݣ�����ƥ�䡣���Ȳ�ѯEAMʵ�����ݡ�");
		return;
	}
	if(!tagObjs){
		alert("û�����ݣ�����ƥ�䡣���Ȳ�ѯMISת���嵥���ݡ�");
		return;
	}
	var systemId = getRadioValueByObj(sysObjs);
	var tagNumber = getRadioValueByObj(tagObjs);
	if(systemId == ""){
		alert("û��ѡ��EAMʵ�����ݣ�����ƥ��");
		return;
	}
	if(tagNumber == ""){
		alert("û��ѡ��MISʵ�����ݣ�����ƥ��");
		return;
	}
	if(confirm("ȷ��ƥ��ѡ�е������𣿼���������ȷ������ť������������ȡ������ť")){
		manWind.do_ManualMatch(systemId, tagNumber);
	}
}

function do_ReleaseMatch(){
	var matchedStyle = document.all("matchedList").style.display;
	if(matchedStyle == "none"){
		alert("��ƥ���嵥��û�д��ڻ״̬������ִ��ָ������");
		return;
	}
	var wind = window.frames["matchedListFrm"];
	var mainFrm = wind.document.mainFrm;
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ����ܽ��ƥ��");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("û��ѡ�����ݣ����ܽ��ƥ��");
		return;
	}
	if(confirm("ȷ�����ѡ�е�ƥ���ϵ�𣿼���������ȷ������ť������������ȡ������ť")){
		wind.do_ReleaseMatch();
	}
}
</script>
