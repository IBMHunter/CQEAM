<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/sampling/headerInclude.jsp"%>
<%@ include file="/sampling/headerInclude.htm"%>
<html>
<head>
<title>�ʲ��ص��ѯ</title>
</head>
<body topmargin="0" leftmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('do_SearchTask()')">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>

<%
	AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO)request.getAttribute(QueryConstant.QUERY_DTO);
    DTOSet tasks = (DTOSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (tasks != null && !tasks.isEmpty());
	String pageTitle = "�ʲ�������>>�������ά��";
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
%>
<form name="mainFrm" action="<%=SamplingURLs.TASK_SERVLET%>" method="post">
<script>
    printTitleBar("<%=pageTitle%>")
</script>

<table border="0" class="queryTable" width="100%" style="TABLE-LAYOUT:fixed;word-break:break-all">
	<tr>
		<td width="10%" height="22" align="right">��鹫˾��</td>
		<td width="15%" height="22"><select class="select_style1" name="sampledOu" style="width:100%"><%=dto.getSampledOuOpt()%></select></td>
		<td width="10%" height="22" align="right">����״̬��</td>
		<td width="15%" height="22"><select class="select_style1" name="taskStatus" style="width:100%"><%=dto.getTaskStatusOpt()%></select></td>
		<td width="10%" height="22" align="right">�����ţ�</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:100%" name="taskNo" value="<%=dto.getTaskNo()%>"></td>
		<td width="10%" height="22" align="right">�������ƣ�</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:100%" name="taskName" value="<%=dto.getTaskName()%>"></td>
	</tr>
	<tr>
		<td width="10%" height="22" align="right">�������ڣ�</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:100%;cursor:hand" name="startDate" value="<%=dto.getStartDate()%>" title="���ѡ��ʼ����" readonly onclick="gfPop.fStartPop(startDate, endDate)"></td>
		<td width="10%" height="22" align="right">����</td>
		<td width="15%" height="22" align="right"><input class="input_style1" type="text" style="width:100%;cursor:hand" name="endDate" value="<%=dto.getEndDate()%>" title="���ѡ���ֹ����" readonly onclick="gfPop.fEndPop(startDate, endDate)"></td>
		<td width="10%" height="22" align="right">�������ڣ�</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:100%;cursor:hand" name="fromDate" value="<%=dto.getFromDate()%>" title="���ѡ��ʼ����" readonly onclick="gfPop.fStartPop(fromDate, toDate)"></td>
		<td width="10%" height="22" align="right">����</td>
		<td width="15%" height="22" align="right"><input class="input_style1" type="text" style="width:100%;cursor:hand" name="toDate" value="<%=dto.getToDate()%>" title="���ѡ���ֹ����" readonly onclick="gfPop.fEndPop(fromDate, toDate)"></td>
	</tr>
</table>
	<input type="hidden" name="act">
</form>
<fieldset style="border:0px; position:absolute;top:66px;width:100%;height:370">
    <legend>
        <img src="/images/eam_images/search.jpg" title="�����ѯ" onclick="do_SearchTask();">
		<img src="/images/eam_images/export.jpg" title="�������" onclick="do_ExportTask();">
        <img src="/images/eam_images/new_add.jpg" title="�������" onclick="do_CreateTask();">
        <img src="/images/eam_images/openTask.jpg" title="������" onclick="do_OpenTask();">
        <img src="/images/eam_images/closeTask.jpg" title="�ر�����" onclick="do_CloseTask();">
		<img src="/images/eam_images/publishTask.jpg" title="��������" onClick="do_PublishTask()">
		<img src="/images/eam_images/cancel.jpg" title="ȡ������" onClick="do_CancelTask()">
	</legend>
    <div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:20px;left:0px;width:100%" class="crystalScroll">
	<table class="eamHeaderTable" border="1" width="120%" style="text-align:center">
		<tr onClick="executeClick(this)" style="cursor:hand" title="���ѡ��ȫ����ȡ��ѡ��">
			<td width="3%" height="22"><input type="checkbox" name="titleCheck" onPropertyChange="checkAll('titleCheck', 'subCheck')"></td>
			<td width="18%" height="22">������</td>
			<td width="20%" height="22">��������</td>
			<td width="8%" height="22">��ʼ����</td>
			<td width="8%" height="22">��������</td>
			<td width="8%" height="22">���ٷֱ�(%)</td>
			<td width="8%" height="22">����״̬</td>
			<td width="8%" height="22">��������</td>
		</tr>
	</table>
</div>
<%
	if(hasData){
%>
<div style="overflow:scroll;height:300px;width:100%;position:absolute;top:44px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		int dataCount = tasks.getSize();
		AmsAssetsSamplingTaskDTO task = null;
		for(int i = 0; i < dataCount; i++){
			task = (AmsAssetsSamplingTaskDTO)tasks.getDTO(i);
%>
		<tr title="����鿴�ص���Ϣ�����ʲ���Ϣ">
			<td width="3%" align="center"><%=task.getCheckBoxProp()%></td>
			<td width="18%" height="22" onclick="do_ShowDetail('<%=task.getTaskId()%>')"><input class="finput2" readonly value="<%=task.getTaskNo()%>"></td>
			<td width="20%" height="22" onclick="do_ShowDetail('<%=task.getTaskId()%>')"><input class="finput" readonly value="<%=task.getTaskName()%>"></td>
			<td width="8%" height="22" onclick="do_ShowDetail('<%=task.getTaskId()%>')"><input class="finput2" readonly value="<%=task.getStartDate()%>"></td>
			<td width="8%" height="22" onclick="do_ShowDetail('<%=task.getTaskId()%>')"><input class="finput2" readonly value="<%=task.getEndDate()%>"></td>
			<td width="8%" height="22" onclick="do_ShowDetail('<%=task.getTaskId()%>')"><input class="finput3" readonly value="<%=task.getSamplingRatio()%>"></td>
			<td width="8%" height="22" onclick="do_ShowDetail('<%=task.getTaskId()%>')"><input class="finput" readonly value="<%=task.getTaskStatusValue()%>"></td>
			<td width="8%" height="22" onclick="do_ShowDetail('<%=task.getTaskId()%>')"><input class="finput2" readonly value="<%=task.getCreationDate()%>"></td>
		</tr>
<%
		}
%>
	</table>
</div>
<div style="position:absolute;top:350px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>
</fieldset>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>

</body>
</html>
<script>
function do_SearchTask(){
	mainFrm.act.value = "<%=SamplingActions.QUERY_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_ExportTask(){
	mainFrm.act.value = "<%=SamplingActions.EXPORT_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.submit();
	event.srcElement.disabled = true;
}

function do_ShowDetail(taskId){
	var url = "<%=SamplingURLs.TASK_SERVLET%>?act=<%=SamplingActions.DETAIL_ACTION%>&taskId=" + taskId;
<%
	if(userAccount.isProvinceUser()){
%>
	var style = "width=750,height=500,top=100,left=125,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,task=no,status=no";
<%
	} else {
%>
	var style = "width=750,height=350,top=150,left=125,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,task=no,status=no";
<%
	}
%>
	window.open(url, 'taskWin', style);
}

function do_CreateTask(){
<%
	if(userAccount.isProvinceUser()){
%>
	var style = "width=750,height=500,top=100,left=125,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,task=no,status=no";
<%
	} else {
%>
	var style = "width=750,height=350,top=150,left=125,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,task=no,status=no";
<%
	}
%>
	window.open("/public/waiting2.htm", "taskWin", style);
	mainFrm.act.value = "<%=SamplingActions.NEW_ACTION%>";
    mainFrm.target = "taskWin";
    mainFrm.submit();
}

function do_CloseTask(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ����ִ�б�����");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("����ѡ����Ӧ�������ִ�б�����");
		return;
	}
	if(confirm("ϵͳ���رտ����е����񣬲����˵�����Ȩ����Ĳ��֡�����رպ󲻿��ٴ�����鹤����ȷ�Ϲر���ȷ��������ȷ������ť������������ȡ������ť")){
		mainFrm.act.value = "<%=SamplingActions.CLOSE_SELECTED_TASK%>";
		mainFrm.submit();
	}
}

function do_OpenTask(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ����ִ�б�����");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("����ѡ����Ӧ�������ִ�б�����");
		return;
	}
	
	if (mainFrm.taskStatus.value == "OPENING") {
		alert("���ǿ���������");
		return;
	}
	var dataTable = document.getElementById("dataTable");
	var rows = dataTable.rows;
	var row = null;
	var cells = null;
	var cell = null;
	var checkObj = null;
	var checkedCount = getCheckedBoxCount("subCheck");
	for(var i = 0; i < rows.length; i++){
		if(checkedCount > 0){
			row = rows[i];
			cells = row.cells;
			checkObj = row.childNodes[0].childNodes[0];
			if(!checkObj.checked){
				continue;
			} else {
				cell = cells[6].childNodes[0].value;
				if (cell == "������") {
					alert("ѡ���������ǿ���������");
					return;
				}
			}
		}
	}
	
	if(confirm("ϵͳ�����ѹرյ����񣬲����˵�����Ȩ����Ĳ��֡�����򿪺�������¼���������鹤����ȷ�ϴ���ȷ��������ȷ������ť������������ȡ������ť")){
		mainFrm.act.value = "<%=SamplingActions.OPEN_SELECTED_TASK%>";
		mainFrm.submit();
	}
}


function do_PublishTask(){
    if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ����ִ�б�����");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("����ѡ����Ӧ�������ִ�б�����");
		return;
	}
    do_verify();

//    if(confirm("ϵͳ�������ݴ�����񣬲����˵�����Ȩ����Ĳ��֡����񷢲���������´�����鹤����ȷ�Ϸ�����ȷ��������ȷ������ť������������ȡ������ť")){
		<%--mainFrm.act.value = "<%=SamplingActions.PUBLISH_SELECTED_TASK%>";--%>
//		mainFrm.submit();
//	}
}

function do_CancelTask(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ����ִ�б�����");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("����ѡ����Ӧ�������ִ�б�����");
		return;
	}
	if(confirm("ϵͳ��ȡ���ݴ�����񣬲����˵�����Ȩ����Ĳ��֡�����ȡ���󽫲��ɽ����κβ�����ȷ��ȡ����ȷ��������ȷ������ť������������ȡ������ť")){
		mainFrm.act.value = "<%=SamplingActions.CANCEL_SELECTED_TASK%>";
		mainFrm.submit();
	}
}

var xmlHttp;
function do_verify() {
    var url = "";
    createXMLHttpRequest();
    var selectTaskId = getCheckedBoxValue("subCheck");
    url = "/servlet/com.sino.ams.sampling.servlet.AmsAssetsSamplingTaskServlet?act=VERIFY&selectTaskId=" + selectTaskId;
    xmlHttp.onreadystatechange = handleReadyStateChange;
    xmlHttp.open("post", url, true);
    xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlHttp.send(null);
}

function createXMLHttpRequest() {     //����XMLHttpRequest����
    try {
        xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
    } catch(e) {
        try {
            xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
        } catch(e) {
            try {
                xmlHttp = new XMLHttpRequest();
            } catch(e) {
                alert("����XMLHttpRequest����ʧ�ܣ�");
            }
        }
    }
}

function handleReadyStateChange() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            if (xmlHttp.responseText == 'Y') {
                match();
            } else {
                alert("ѡ��������г�鹫˾����Ϊ�գ����ܷ�������");
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}

function match() {
    if(confirm("ϵͳ�������ݴ�����񣬲����˵�����Ȩ����Ĳ��֡����񷢲���������´�����鹤����ȷ�Ϸ�����ȷ��������ȷ������ť������������ȡ������ť")){
		mainFrm.act.value = "<%=SamplingActions.PUBLISH_SELECTED_TASK%>";
		mainFrm.submit();
	}
}
</script>
