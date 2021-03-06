<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.ams.synchronize.dto.EamSyschronizeDTO"%>
<%@ include file="/newasset/headerInclude.htm"%>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>EAM新增地点同步</title>
</head>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    EamSyschronizeDTO dto = (EamSyschronizeDTO)request.getAttribute(WebAttrConstant.SYSCHRONIZE_DTO);
    String action = parser.getParameter("act");
    String syn_msg=(String)request.getAttribute("syn_msg");
    Row row = null;
%>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_search()')" onload="do_SetPageWidth()">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.synchronize.servlet.EamNewLocusServlet">
<script type="text/javascript">
   printTitleBar("EAM新增地点同步");
</script>
<input type="hidden" name="act">
<input type="hidden" name="orgIds" value="">
<table width="100%" border="0" style="width:100%">
	<tr>
		<td align="right" width="8%">地点代码：</td>
		<td align="left" width="12%"><input type="text" class="input_style1" name="workorderObjectCode" value="<%=dto.getWorkorderObjectCode()%>" style="width:80%"></td>
		<td align="right" width="8%">地点描述：</td>
		<td align="left" width="20%"><input type="text" class="input_style1" name="newAssetsLocation" value="<%=dto.getNewAssetsLocation()%>" style="width:90%"></td>
        <td width="8%" align="right">创建日期：</td>
        <td width="24%">
        	<input style="width:40%" type="text"  name="startDate" value="<%=StrUtil.nullToString(dto.getStartDate()) %>" title="点击选择起始日期" readonly class="input_style2" onclick="gfPop.fStartPop(startDate, endDate)"> -
            <input style="width:40%" type="text" name="endDate" value="<%=StrUtil.nullToString(dto.getEndDate())%>" title="点击选择结束日期" readonly class="input_style2" onclick="gfPop.fEndPop(startDate, endDate)">
        </td>
		<td align="right" width="20%">
			<img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="查询">
			<img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="导出">
			<img src="/images/eam_images/synchronize.jpg" style="cursor:'hand'" onclick="do_syschronize();" alt="同步">
		</td>
	</tr>
</table>
<div id ="headDiv" style="position:absolute;width:100%;overflow:hidden;top:68px;padding:0px; margin:0px;">
    <table width="100%" class="headerTable" border="1" cellpadding="0" cellspacing="0">
        <tr height="22">
            <td width="3%" align="center" style="padding:0"><input type="checkbox" name="titleCheck" class="headCheckbox" onclick="checkAll('titleCheck','systemids')"></td>
            <td width="10%" align="center">EAM地点代码</td>
            <td width="20%" align="center">EAM地点描述</td>
            <td width="10%" align="center">原MIS地点代码</td>
            <td width="20%" align="center">原MIS地点描述</td>
            <td width="7%" align="center">最后更新时间</td>
            <td width="7%" align="center">最后更新人</td>
            <td width="7%" align="center">地点类别</td>
            <td width="7%" align="center">创建日期</td>
        </tr>
    </table>
</div>

<div id="dataDiv" style="overflow:scroll;height:300px;width:847px;position:absolute;top:88px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
    if (rows != null && rows.getSize() > 0) {
        for (int i = 0; i < rows.getSize(); i++) {
            row = rows.getRow(i);
%>
        <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'" >
            <td width="3%" align="center" style="padding:0"><input type="checkbox" name="systemids" value="<%=row.getValue("WORKORDER_OBJECT_NO")%>"></td>
            <td width="10%"><input class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
            <td width="20%"><input class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_LOCATION")%>"></td>
            <td width="10%"><input class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION_CODE")%>"></td>
            <td width="20%"><input class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION")%>"></td>
            <td width="7%"><input class="finput2" readonly value="<%=row.getValue("LAST_UPDATE_DATE")%>"></td>
            <td width="7%"><input class="finput2" readonly value="<%=row.getValue("USERNAME")%>"></td>
            <td width="7%"><input class="finput2" readonly value="<%=row.getValue("WORKORDER_CATEGORY")%>"></td>
            <td width="7%"><input class="finput2" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
        </tr>
<%
        }
    }
%>

  </table>
</div>
</form>
<%
	if(rows != null && !rows.isEmpty()){
%>
<div style="position:absolute;top:400px;left:0"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
<%
	}	
%>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</body>
</html>

<script type="text/javascript">

function do_search() {
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
}

function do_Export(){
	mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}
/**
 * 功能：同步数据
 */
function do_syschronize(){
    mainFrm.action = "/servlet/com.sino.ams.synchronize.servlet.EamNewLocusServlet";
	mainFrm.act.value = "<%=WebActionConstant.SYSCHRONIZE_ACTION%>";
	mainFrm.submit();
}
syn_msg='<%=syn_msg%>';
if(syn_msg!="null" & syn_msg!="")
    {
        alert(syn_msg) ;
    }
</script>