<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/sampling/headerInclude.jsp"%>
<%@ include file="/sampling/headerInclude.htm"%>
<html>
<head>
<title>��鹤����ѯ</title>
</head>
<body topmargin="0" leftmargin="0" onload="do_SetPageWidth();" onKeyDown="autoExeFunction('do_SearchOrder()')">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>

<%
	AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	DTOSet orders = (DTOSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (orders != null && !orders.isEmpty());
	String pageTitle = "�ʲ�������>>��鹤����ѯ";
%>
<form name="mainFrm" action="<%=SamplingURLs.ORDER_SEARCH_SERVLET%>" method="post">
<script>
    printTitleBar("<%=pageTitle%>")
</script>

<table border="0" class="queryTable" width="100%" style="TABLE-LAYOUT:fixed;word-break:break-all">
	<tr>
		<td width="10%" height="22" align="right">������ţ�</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:100%" name="orderNo" value="<%=dto.getOrderNo()%>" ></td>
		<td width="10%" height="22" align="right">���ص㣺</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:80%" name="samplingLocationName" value="<%=dto.getSamplingLocationName()%>"><a href="" onclick="do_SelectLocation(); return false;" title="���ѡ����ص�">[��]</a></td>
		<td width="10%" height="22" align="right">ִ���ˣ�</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:80%" name="implementUser" value="<%=dto.getImplementUser()%>" ><a href="" onclick="do_SelectUser(); return false;" title="���ѡ��ִ����">[��]</a></td>
		<td width="10%" height="22" align="right">��鹫˾��</td>
		<td width="15%" height="22"><select class="select_style1" name="sampledOu" style="width:100%"><%=dto.getSampledOuOpt()%></select></td>
	</tr>
	<tr>
		<td width="10%" height="22" align="right">����״̬��</td>
		<td width="15%" height="22"><select class="select_style1" name="orderStatus" style="width:100%"><%=dto.getOrderStatusOpt()%></select></td>
		<td width="10%" height="22" align="right">�������ƣ�</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:80%" name="taskName" value="<%=dto.getTaskName()%>" ></td>
		<td width="10%" height="22" align="right">��ֹ���ڣ�</td>
		<td width="15%" height="22"><input class="input_style1" type="text" style="width:80%;cursor:hand" name="startDate" value="<%=dto.getStartDate()%>" title="���ѡ��ʼ����" readonly onclick="gfPop.fStartPop(startDate, endDate)" ></td>
		<td width="10%" height="22" align="right">����</td>
		<td width="15%" height="22" align="right"><input class="input_style1" type="text" style="width:100%;cursor:hand" name="endDate" value="<%=dto.getEndDate()%>" title="���ѡ���ֹ����" readonly onclick="gfPop.fEndPop(startDate, endDate)" ></td>
	</tr>
</table>
	<input type="hidden" name="act">
	<input type="hidden" name="fromPage" value="fromPage">
</form>
<fieldset style="border:0px; position:absolute;top:68px;width:100%;height:400">
    <legend>
        <img src="/images/eam_images/search.jpg" title="�����ѯ" onclick="do_SearchOrder();">
		<img src="/images/eam_images/export.jpg" title="�������" onclick="do_ExportOrder();">
	</legend>
	<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:20px;left:0px;width:100%">
		<table class="eamDbHeaderTable" border="1" width="200%" style="text-align:center">
			<tr>
				<td width="52%" height="22" colspan="7">������Ϣ</td>
				<td width="48%" height="22" colspan="7">������Ϣ</td>
			</tr>
			<tr height=20px class="eamDbHeaderTr">
				<td width="10%" height="22">�������</td>
				<td width="18%" height="22">���ص�</td>
				<td width="5%" height="22">��������</td>
				
				<td width="6%" height="22">ִ�й�˾</td>
				<td width="4%" height="22">ִ����</td>
				<td width="5%" height="22">ִ������(��)</td>
				<td width="4%" height="22">����״̬</td>
				
				<td width="10%" height="22">������</td>
				<td width="13%" height="22">��������</td>
				<td width="5%" height="22">��������</td>
				
				<td width="5%" height="22">��ʼ����</td>
				<td width="5%" height="22">��ֹ����</td>
				<td width="6%" height="22">������˾</td>
				<td width="4%" height="22">������</td>
			</tr>
		</table>
	</div>
<%
	if(hasData){
%>    
<div style="overflow:scroll;height:270px;width:100%;position:absolute;top:65px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="200%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		int dataCount = orders.getSize();
		AmsAssetsSamplingHeaderDTO order = null;
		for(int i = 0; i < dataCount; i++){
			order = (AmsAssetsSamplingHeaderDTO)orders.getDTO(i);
%>
		<tr title="����鿴������Ϣ" onclick="do_ShowDetail('<%=order.getHeaderId()%>')">
			<td width="10%" height="22"><input class="finput2" readonly value="<%=order.getOrderNo()%>"></td>
			<td width="18%" height="22"><input class="finput" readonly value="<%=order.getSamplingLocationName()%>"></td>
			<td width="5%" height="22"><input class="finput2" readonly value="<%=order.getCreationDate()%>"></td>
			
			<td width="6%" height="22"><input class="finput" readonly value="<%=order.getSampledOuName()%>"></td>
			<td width="4%" height="22"><input class="finput" readonly value="<%=order.getImplementUser()%>"></td>
			<td width="5%" height="22"><input class="finput3" readonly value="<%=order.getImplementDays()%>"></td>
			<td width="4%" height="22"><input class="finput" readonly value="<%=order.getOrderStatusValue()%>"></td>
			
			<td width="10%" height="22"><input class="finput2" readonly value="<%=order.getTaskNo()%>"></td>
			<td width="13%" height="22"><input class="finput" readonly value="<%=order.getTaskName()%>"></td>
			<td width="5%" height="22"><input class="finput2" readonly value="<%=order.getTaskCreationDate()%>"></td>
			
			<td width="5%" height="22"><input class="finput2" readonly value="<%=order.getStartDate()%>"></td>
			<td width="5%" height="22"><input class="finput2" readonly value="<%=order.getEndDate()%>"></td>
			<td width="6%" height="22"><input class="finput" readonly value="<%=order.getCreatedOuName()%>"></td>
			<td width="4%" height="22"><input class="finput" readonly value="<%=order.getTaskCreatedUser()%>"></td>
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
function do_SearchOrder(){
	mainFrm.act.value = "<%=SamplingActions.QUERY_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_ExportOrder(){
	mainFrm.act.value = "<%=SamplingActions.EXPORT_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.submit();
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


function do_ShowDetail(headerId){
	var url = "<%=SamplingURLs.ORDER_SERVLET%>?act=<%=SamplingActions.DETAIL_ACTION%>&headerId=" + headerId;
	var style = "width=1010,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
	window.open(url, 'orderDtlWin', style);
}
</script>
