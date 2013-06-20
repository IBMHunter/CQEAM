<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�̵�������</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.DeptDiffReportServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("�̵��ճ�����-->>�����̵����")
</script>
	<table width="100%" border="0" class="queryHeadBg">
		<tr>
			<td width="10%" align="right">�������ƣ�</td>
			<td width="30%" align="right"><select size="1" name="checkDept" style="width:100%"><%=dto.getDeptOpt()%></select></td>
			<td width="10%" align="right">�̵����ڣ�</td>
			<td width="20%"><input type="text" name="startDate" style="cursor:hand;width:100%" title="���ѡ��ʼ����" readonly class="readonlyInput" value="<%=dto.getStartDate()%>" onclick="gfPop.fStartPop(startDate,endDate);"></td>
			<td width="10%" align="center">��</td>
			<td width="20%"><input type="text" name="endDate" style="cursor:hand;width:100%" title="���ѡ���������" readonly class="readonlyInput" value="<%=dto.getEndDate()%>" onclick="gfPop.fEndPop(startDate,endDate);"></td>
		</tr>
		<tr>
			<td width="10%" align="right">�ص����ƣ�</td>
			<td width="30%"><input type="text" name="objectName" style="width:100%" value="<%=dto.getObjectName()%>"></td>
			<td width="10%" align="right">�ص���룺</td>
			<td width="20%" align="right"><input type="text" name="objectCode" style="width:85%" value="<%=dto.getObjectCode()%>"><a href=""  title="���ѡ��ص�" onclick="do_SelectAddress(); return false;">[��]</a></td>
			<td width="30%" align="right" colspan="2"><img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">&nbsp;<img border="0" src="/images/eam_images/search.jpg" onclick="do_Search();"></td>
		</tr>
	</table>
	<input name="act" type="hidden">
</form>


<div id="headDiv" style="overflow:hidden;position:absolute;top:67px;left:1px;width:840px">
	<table class="headerTable" border="1" width="100%">
		<tr height="22">
			<td width="8%" align="center">��˾����</td>
			<td width="12%" align="center">��˾����</td>
			<td width="40%" align="center">��������</td>
			<td width="10%" align="center">�ʲ�����</td>
			<td width="10%" align="center">���̵���</td>
			<td width="10%" align="center">δ�̵���</td>
			<td width="10%" align="center">��ɱ���</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:356px;width:857px;position:absolute;top:90px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
		<tr height="22" title="����鿴���š�<%=row.getValue("DEPT_NAME")%>�����̵����" style="cursor:hand" onClick="do_ShowDetail('<%=row.getValue("DEPT_CODE")%>', '<%=row.getValue("SCANED_COUNT")%>','<%=row.getValue("DEPT_NAME")%>')">
			<td width="8%"><%=row.getValue("COMPANY_CODE")%></td>
			<td width="12%"><%=row.getValue("COMPANY")%></td>
			<td width="40%"><%=row.getValue("DEPT_NAME")%></td>
			<td width="10%" align="right"><%=row.getValue("OWN_COUNT")%></td>
			<td width="10%" align="right"><%=row.getValue("SCANED_COUNT")%></td>
			<td width="10%" align="right"><%=row.getValue("NOT_SCANED_COUNT")%></td>
			<td width="10%" align="right"><%=row.getValue("SCAN_RATE")%></td>
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
<div style="position:absolute;top:456px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function do_Search(){
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_SelectAddress(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ADDRESS%>";
	var dialogWidth = 55;
	var dialogHeight = 30;
	var userPara = "organizationId=<%=userAccount.getOrganizationId()%>";
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	if (objs) {
		var obj = objs[0];
		mainFrm.objectCode.value = obj["workorderObjectCode"];
		mainFrm.objectName.value = obj["toObjectName"];
	}
}

function do_Export() {
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}

function do_ShowDetail(deptCode, scanCount,groupName){
//	if(scanCount == 0){
//		alert("�������̵�ص���Ϊ0���������Ϣ��");
//		return;
//	}
//	alert("���ڽ�����...");
//	return;
	var url = "/servlet/com.sino.ams.newasset.report.servlet.DeptCheckFrmServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>";
//	url += "&groupId=" + groupId;
	url += "&groupId=" + deptCode;
    url += "&groupName" + groupName;
    url += "&startDate=" + mainFrm.startDate.value;
	url += "&endDate=" + mainFrm.endDate.value;
	var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
	window.open(url, "responWin", style);
}
</script>