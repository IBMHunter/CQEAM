<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/match/prematch/headerInclude.jsp"%>
<%@ include file="/match/prematch/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>ת��׼���嵥</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
	AmsPaAssetsDTO dto = (AmsPaAssetsDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.prematch.servlet.AmsPaAssetsServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("ת��Ԥƥ��-->>ת���嵥��ѯ");
</script>
	<table width="100%" border="0" class="queryHeadBg">
		<tr>
			<td width="6%" align="right">��Ŀ��</td>
			<td width="19%"><input type="text" name="projectNumber" style="width:80%" value="<%=dto.getProjectNumber()%>"><a href="" title="���ѡ����Ŀ���" onclick="do_SelectProject(); return false;">[��]</a></td>
			<td width="6%" align="right">�ص㣺</td>
			<td width="19%"><input type="text" name="assetsLocationCode" style="width:80%" value="<%=dto.getAssetsLocationCode()%>"><a href="" title="���ѡ��ص����" onclick="do_SelectAssetsLocation(); return false;">[��]</a></td>
			<td width="6%" align="right">����</td>
			<td width="19%"><input type="text" name="taskNumber" style="width:100%" value="<%=dto.getTaskNumber()%>"></td>
			<td width="25%" align="right">
			<img border="0" src="/images/eam_images/search.jpg" onclick="do_Search();">&nbsp;<img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">&nbsp;<img src="/images/button/readBatch.gif" style="cursor:'hand'" onclick="do_ReadProjectAssets();" title="��ȡMIS��������">
			</td>
		</tr>
	</table>
	<input name="act" type="hidden">
</form>

<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:840px">
	<table class="headerTable" border="1" width="250%">
		<tr height="22">
			<td width="6%" align="center">��ǩ��</td>
			<td width="6%" align="center">�ʲ����</td>
			<td width="11%" align="center">�ʲ�����</td>
			<td width="11%" align="center">����ͺ�</td>
			<td width="5%" align="center">��Ŀ���</td>

			<td width="10%" align="center">��Ŀ����</td>
			<td width="8%" align="center">�ص����</td>
			<td width="11%" align="center">�ʲ��ص�</td>
			<td width="5%" align="center">Ա�����</td>

			<td width="5%" align="center">������</td>
			<td width="5%" align="center">��������</td>
			<td width="3%" align="center">�ʲ�����</td>
			<td width="6%" align="center">������</td>
			<td width="8%" align="center">��������</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:68%;width:857px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="250%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
		<tr height="22">
  			<td width="6%"><input type="text" class="finput2" readonly value="<%=row.getValue("TAG_NUMBER")%>"></td>
			<td width="6%"><input type="text" class="finput2" readonly value="<%=row.getValue("FA_CATEGORY_CODE")%>"></td>
			<td width="11%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
			<td width="11%"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
			<td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("PROJECT_NUMBER")%>"></td>

			<td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("PROJECT_NAME")%>"></td>
			<td width="8%"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSETS_LOCATION_CODE")%>"></td>
			<td width="11%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION")%>"></td>
			<td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSIGNED_TO_NUMBER")%>"></td>

			<td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSIGNED_TO_NAME")%>"></td>
			<td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>
			<td width="3%"><input type="text" class="finput3" readonly value="<%=row.getValue("ASSET_UNITS")%>"></td>
			<td width="6%"><input type="text" class="finput2" readonly value="<%=row.getValue("TASK_NUMBER")%>"></td>
			<td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("TASK_NAME")%>"></td>
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
<div style="position:absolute;top:448px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function do_Search(){
	mainFrm.act.value = "<%=AMSActionConstant.QUERY_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Export() {
    mainFrm.act.value = "<%=AMSActionConstant.EXPORT_ACTION%>";
	mainFrm.target = "_self";
    mainFrm.submit();
}

function do_SelectProject(){
	var lookUpName = "LOOKUP_PROJECT";
	var dialogWidth = 55;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
    }
}

function do_SelectAssetsLocation(){
	var lookUpName = "LOOK_UP_ASSETS_LOCATION";
	var dialogWidth = 55;
	var dialogHeight = 30;
	var objs = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		dto2Frm(objs[0], "mainFrm");
    }
}

function do_ReadProjectAssets(){
    mainFrm.act.value = "<%=AMSActionConstant.IMPORT_ACTION%>";
	mainFrm.target = "_self";
    mainFrm.submit();
}
</script>
