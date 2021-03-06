<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.ams.workorder.dto.EtsWorkorderDTO" %>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>巡检监控报表</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
	EtsWorkorderDTO dto = (EtsWorkorderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.workorder.servlet.ScanMonitorRptServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("巡检常用报表-->>代维监控报表");
</script>

	<table width="100%" border="0" class="queryTable">
		<tr>
			<td width="10%" align="right">代维公司：</td>
			<td width="30%"><select size="1" name="maintainCompany" class="select_style1" style="width:100%"><%=dto.getMaintainComOpt()%></select></td>
			<td width="10%" align="right">巡检时间：</td>
			<td width="14%"><input type="text" name="startDate" class="input_style2" style="cursor:hand;width:100%" title="点击选择开始日期" readonly class="readonlyInput" value="<%=dto.getStartDate()%>" onclick="gfPop.fStartPop(startDate,endDate);"></td>
			<td width="4%" align="center">到</td>
			<td width="13%"><input type="text" name="endDate" class="input_style2" style="cursor:hand;width:100%" title="点击选择截至日期" readonly class="readonlyInput" value="<%=dto.getEndDate()%>" onclick="gfPop.fEndPop(startDate,endDate);"></td>
			<td width="20%"><img border="0" src="/images/eam_images/search.jpg" width="63" height="18" align="left" onclick="do_Search();">&nbsp;<img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="导出到Excel"></td>
		</tr>
	</table>
	<input name="act" type="hidden">
</form>


<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:840px">
	<table class="headerTable" border="1" width="100%">
		<tr height="22">
			<td width="40%" align="center">代维公司名称</td>
			<td width="15%" align="center">责任地点数量</td>
			<td width="15%" align="center">已巡检地点数</td>
			<td width="15%" align="center">未巡检地点数</td>
			<td width="15%" align="center">巡检完成率</td>
		</tr>
	</table>
</div>		
<div id="dataDiv" style="overflow:scroll;height:68%;width:857px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>	
		<tr height="22" onclick="do_ShowDetail('<%=row.getValue("COMPANY_ID")%>')" title="点击查看该公司巡检情况" style="cursor:hand">
			<td width="40%"><%=row.getValue("MAINTAIN_COMPANY_NAME")%></td>
			<td width="15%" align="right"><%=row.getValue("RESPONSIBILITY_COUNT")%></td>
			<td width="15%" align="right"><%=row.getValue("SCAN_OVER_COUNT")%></td>
			<td width="15%" align="right"><%=row.getValue("NOT_SCAN_COUNT")%></td>
			<td width="15%" align="right"><%=row.getValue("SCAN_OVER_RATE")%></td>
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
<div style="position:absolute;top:428px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function do_Search(){
	mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Export() {
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}

function do_ShowDetail(companyId){
	var url = "/servlet/com.sino.ams.workorder.servlet.LocResponReportServlet?act=<%=WebActionConstant.DETAIL_ACTION%>";
	url += "&maintainCompany=" + companyId
	url += "&startDate=" + mainFrm.startDate.value;
	url += "&endDate=" + mainFrm.endDate.value;
	var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
	window.open(url, "responWin", style);
}
</script>