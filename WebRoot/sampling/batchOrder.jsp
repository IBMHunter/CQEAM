<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/sampling/headerInclude.jsp"%>
<%@ include file="/sampling/headerInclude.htm"%>
<html>
<head>
<title>��鹤��ά��</title>
</head>
<body topmargin="0" leftmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('do_SearchOrder()')">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>

<%
	AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	DTOSet orders = (DTOSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (orders != null && !orders.isEmpty());
	String pageTitle = "�ʲ�������>>��鹤��ά��";
%>
<form name="mainFrm" action="<%=SamplingURLs.BATCH_ORDER_SERVLET%>" method="post">
<table class="queryTable" border="0" width="100%" style="TABLE-LAYOUT:fixed;word-break:break-all">
	<tr>
		<td width="10%" height="22" align="right">�����ţ�</td>
		<td width="23%" height="22"><input class="input_style1" type="text" style="width:100%" name="taskNo" readonly value="<%=dto.getTaskNo()%>" ></td>
		<td width="10%" height="22" align="right">�������ƣ�</td>
		<td width="24%" height="22"><input class="input_style1" type="text" style="width:85%" name="taskName" readonly value="<%=dto.getTaskName()%>" ></td>
		<td width="10%" height="22" align="right">�������ţ�</td>
		<td width="23%" height="22"><input class="input_style1" type="text" style="width:85%" name="batchNo" readonly value="<%=dto.getBatchNo()%>" ></td>
	</tr>
	<tr>
		<td width="10%" height="22" align="right">������ţ�</td>
		<td width="23%" height="22"><input class="input_style1" type="text" style="width:100%" name="orderNo" value="<%=dto.getOrderNo()%>" ></td>
		<td width="10%" height="22" align="right">���ص㣺</td>
		<td width="24%" height="22"><input class="input_style1" type="text" style="width:85%" name="samplingLocationName" value="<%=dto.getSamplingLocationName()%>"><a href="" onclick="do_SelectLocation(); return false;" title="���ѡ����ص�">[��]</a></td>
		<td width="10%" height="22" align="right">ִ���ˣ�</td>
		<td width="23%" height="22"><input class="input_style1" type="text" style="width:85%" name="implementUser" value="<%=dto.getImplementUser()%>" ><a href="" onclick="do_SelectUser(); return false;" title="���ѡ��ִ����">[��]</a></td>
	</tr>
</table>
	<input type="hidden" name="act">
	<input type="hidden" name="createdOu" value="<%=dto.getCreatedOu()%>">
	<input type="hidden" name="createdOuName" value="<%=dto.getCreatedOuName()%>">
	<input type="hidden" name="sampledOu" value="<%=dto.getSampledOu()%>">
	<input type="hidden" name="sampledOuName" value="<%=dto.getSampledOuName()%>">
	
</form>
<fieldset style="border:0px; position:absolute;top:48px;width:100%;height:400">
    <legend>
        <img src="/images/eam_images/search.jpg" title="�����ѯ" onclick="do_SearchOrder();">
		<img src="/images/eam_images/export.jpg" title="�������" onclick="do_ExportOrder();">
        <img src="/images/eam_images/new_add.jpg" title="�������" onclick="do_CreateOrder();">
		<img src="/images/eam_images/cancel.jpg" title="ȡ������" onClick="do_CancelOrder()">
		<img src="/images/eam_images/close.jpg" title="�رմ���" onClick="parent.close()">
	</legend>
	<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:20px;left:0px;width:100%" class="crystalScroll">
		<table class="eamDbHeaderTable" border="1" width="200%" style="text-align:center">
			<tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ѡ��ȫ����ȡ��ѡ��">
				<td width="2%" height="44" rowspan="2"><input type="checkbox" name="titleCheck" onPropertyChange="checkAll('titleCheck', 'subCheck')"></td>

				<td width="50%" height="22" colspan="6">������Ϣ</td>
				<td width="48%" height="22" colspan="7">������Ϣ</td>
			</tr>
			<tr height=20px class="eamDbHeaderTr">
				<td width="11%" height="22">�������</td>
				<td width="14%" height="22">���ص�</td>
				<td width="7%" height="22">��������</td>
				
				<td width="7%" height="22">ִ�й�˾</td>
				<td width="6%" height="22">ִ����</td>
				<td width="5%" height="22">ִ������(��)</td>
				
				<td width="7%" height="22">������</td>
				<td width="7%" height="22">��������</td>
				<td width="7%" height="22">��������</td>
				
				<td width="7%" height="22">��ʼ����</td>
				<td width="7%" height="22">��ֹ����</td>
				<td width="7%" height="22">������˾</td>
				<td width="6%" height="22">������</td>
			</tr>
		</table>
	</div>
<%
	if(hasData){
%>    
	<div style="overflow:scroll;height:350px;width:100%;position:absolute;top:65px;left:1px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
	    <table id="dataTable" width="200%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		int dataCount = orders.getSize();
		AmsAssetsSamplingHeaderDTO order = null;
		for(int i = 0; i < dataCount; i++){
			order = (AmsAssetsSamplingHeaderDTO)orders.getDTO(i);
%>
			<tr title="����鿴�ص���Ϣ�����ʲ���Ϣ">
				<td width="2%" align="center"><%=order.getCheckBoxProp()%></td>
				<td width="11%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput2" readonly value="<%=order.getOrderNo()%>"></td>
				<td width="14%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput" readonly value="<%=order.getSamplingLocationName()%>"></td>
				<td width="7%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput2" readonly value="<%=order.getCreationDate()%>"></td>

				<td width="7%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput" readonly value="<%=order.getSampledOuName()%>"></td>
				<td width="6%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput" readonly value="<%=order.getImplementUser()%>"></td>
				<td width="5%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput3" readonly value="<%=order.getImplementDays()%>"></td>

				<td width="7%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput3" readonly value="<%=order.getTaskNo()%>"></td>
				<td width="7%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput" readonly value="<%=order.getTaskName()%>"></td>
				<td width="7%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput2" readonly value="<%=order.getTaskCreationDate()%>"></td>

				<td width="7%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput2" readonly value="<%=order.getStartDate()%>"></td>
				<td width="7%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput2" readonly value="<%=order.getEndDate()%>"></td>
				<td width="7%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput" readonly value="<%=order.getCreatedOuName()%>"></td>
				<td width="6%" height="22" onclick="do_ShowDetail('<%=order.getBatchId()%>')"><input class="finput" readonly value="<%=order.getTaskCreatedUser()%>"></td>
			</tr>
<%
		}
%>		
		</table>
	</div>	
	<div style="position:absolute;top:415px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>
</fieldset>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>

</body>
</html>
<script>
function do_SearchOrder(){
	mainFrm.action = "<%=SamplingURLs.BATCH_ORDER_SERVLET%>";
	mainFrm.act.value = "<%=SamplingActions.QUERY_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_ExportOrder(){
	mainFrm.action = "<%=SamplingURLs.BATCH_ORDER_SERVLET%>";
	mainFrm.act.value = "<%=SamplingActions.EXPORT_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.submit();
}

function do_ShowDetail(headerId){
	<%--var url = "<%=SamplingURLs.ORDER_SERVLET%>?act=<%=SamplingActions.DETAIL_ACTION%>&headerId=" + headerId;--%>
	var url = "<%=SamplingURLs.BATCH_SERVLET%>?act=<%=SamplingActions.DETAIL_ACTION%>&batchId=" + headerId;
	var style = "width=1016,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
	window.open(url, 'orderDtlWin', style);
}

function do_CreateOrder(){
	if(mainFrm.taskNo.value == ""){
		alert("�����������������ѡ�����������������");
		return;
	}
	var style = "width=1016,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open("/public/waiting2.htm", "orderWin", style);
	mainFrm.action="<%=SamplingURLs.BATCH_SERVLET%>";
	mainFrm.target = "orderWin";
	mainFrm.act.value = "<%=SamplingActions.NEW_ACTION%>";
    mainFrm.submit();
}

function do_CancelOrder(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ����ִ�б�����");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("����ѡ����Ӧ�������ִ�б�����");
		return;
	}
	if(confirm("ϵͳ��ȡ���ݴ�����񣬲����˵�����Ȩ����Ĳ��֡�����ȡ���󽫲��ɽ����κβ�����ȷ��ȡ����ȷ��������ȷ������ť������������ȡ������ť")){
		mainFrm.act.value = "<%=SamplingActions.CANCEL_ACTION%>";
		mainFrm.action="<%=SamplingURLs.ORDER_SERVLET%>";
		mainFrm.submit();
	}
}

function do_SelectLocation(){
	with(mainFrm){
		var lookUpName = "<%=SamplingLookUpConstant.LOOK_QRY_LOC%>";
		var dialogWidth = 55;
		var dialogHeight = 30;
		var userPara = "&organizationId=" + sampledOu.value;
		var objs = lookUpSamplingValues(lookUpName, dialogWidth, dialogHeight, userPara);
		if (objs) {
			var obj = objs[0];
			dto2Frm(obj, "mainFrm");
		}
	}
}

function do_SelectUser(){
	with(mainFrm){
		var lookUpName = "<%=SamplingLookUpConstant.LOOK_QRY_USER%>";
		var dialogWidth = 44;
		var dialogHeight = 29;
		var objs = lookUpSamplingValues(lookUpName, dialogWidth, dialogHeight);
		if (objs) {
			var obj = objs[0];
			dto2Frm(obj, "mainFrm");
		}
	}
}

</script>
