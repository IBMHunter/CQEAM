<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ʲ�����ͳ��</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="initPage();">
<%
	AmsAssetsCheckBatchDTO dto = (AmsAssetsCheckBatchDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.EfaReportServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("�ʲ���������>>�ʲ�����ͳ��")
</script>
	<table width="100%" border="0" class="queryHeadBg">
		<tr>
			<td width="10%" align="right">��˾���ƣ�</td>
			<td width="20%"><select size="1" name="organizationId" style="width:100%"><%=dto.getOrgOpt()%></select></td>
			<td width="10%" align="right">���ڣ�</td>
			<td width="40%">
			<input type="text" name="startDate" style="cursor:hand;width:48%" title="���ѡ��ʼ����" readonly value="<%=dto.getStartDate()%>" class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)">
			<input type="text" name="endDate" style="cursor:hand;width:48%" title="���ѡ���������" readonly value="<%=dto.getEndDate()%>" class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate);"></td>
			<td width="20%"><img border="0" src="/images/eam_images/search.jpg" width="63" height="18" align="right" onclick="do_Search();">&nbsp;</td>
		</tr>
	</table>
	<input name="act" type="hidden">
	<input name="companyName" type="hidden">
	<input type="hidden" name="analyseType" value="<%=dto.getAnalyseType()%>">
</form>


<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:840px">
	<table class="headerTable" border="1" width="100%">
		<tr height="22">
			<td width="10%" align="center">��˾����</td>
			<td width="8%" align="center">MIS�ʲ�����</td>
			<td width="8%" align="center">����Ԫ��</td>
			<td width="8%" align="center">����Ԫ�ر���</td>
            <td width="8%" align="center">Ͷ�ʷ���</td>
            <td width="8%" align="center">Ͷ�ʷ������</td>
			<td width="8%" align="center">ҵ��ƽ̨</td>
			<td width="8%" align="center">ҵ��ƽ̨����</td>
			<td width="8%" align="center">������</td>
			<td width="8%" align="center">�����α���</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:300px;width:857px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
		<tr height="22" title="����鿴��˾��<%=row.getValue("COMPANY")%>���̵����" style="cursor:hand" >
			<td width="10%" align="right"><%=row.getValue("COMPANY")%></td>
			<td width="8%" align="right"><%=row.getValue("MIS_COUNT")%></td>
			<td width="8%" align="right"><%=row.getValue("LNE_ID")%></td>
			<td width="8%" align="right"><%=row.getValue("LNE_ID_RATE")%></td>
			<td width="8%" align="right"><%=row.getValue("CEX_ID")%></td>
			<td width="8%" align="right"><%=row.getValue("CEX_ID_RATE")%></td>
			<td width="8%" align="right"><%=row.getValue("OPE_ID")%></td>
			<td width="8%" align="right"><%=row.getValue("OPE_ID_RATE")%></td>
			<td width="8%" align="right"><%=row.getValue("NLE_ID")%></td>
			<td width="8%" align="right"><%=row.getValue("NLE_ID_RATE")%></td>
		</tr>
<%
		}
	}
%>
	</table>
</div>
<%
	if(hasData){
%>
<div style="position:absolute;top:380px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function initPage(){
	do_SetPageWidth();
}

function do_Search(){
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.EfaReportServlet";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Export() {
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.EfaReportServlet";
    mainFrm.submit();
}

function do_ShowDetail(organizationId, companyName, scanCount){
	var analyseType = mainFrm.analyseType.value;
	if(scanCount == 0){
		alert("��"+companyName+"���̵��ʲ���Ϊ0���������Ϣ��");
		return;
	}
	mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.EfaReportServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.DETAIL_ACTION%>";
	var selObj = mainFrm.organizationId;
	selectSpecialOptionByItem(selObj, organizationId);
	mainFrm.companyName.value = companyName;
    var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
    window.open("/public/waiting2.htm", "assWin", style);
    mainFrm.target = "assWin";
    mainFrm.submit();
}
</script>