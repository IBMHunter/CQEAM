<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-6-10
  Time: 9:20:17
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��ֵ�׺��ʲ�ͳ�ƣ����ţ�</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();');" onload="initPage();">
<%
	AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	String orgId = userAccount.getOrganizationId();
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.DzyhAssetsDeptReportServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("��ֵ�׺��ʲ�ͳ�ƣ����ţ�");
</script>
	<table width="100%" border="0" class="queryHeadBg">
		<tr>
            <td width="8%" align="right">��˾��</td>
            <td width="12%" align="left"><%=dto.getCompanyName()%></td>
            <td width="8%" align="right">���β��ţ�</td>
			<td width="24%" align="left"><select size="1" name="deptCode" style="width:100%"><%=request.getAttribute(AssetsWebAttributes.DEPT_OPTIONS)%></select></td>
            <td width="8%" align="right">�ؼ��֣�</td>
            <td width="20%" align="left"><input type="text" style="width:100%" name="key" value="<%=dto.getKey()%>"></td>
            <td width="20%" align="right"><img alt="�����ѯ" border="0" src="/images/eam_images/search.jpg" onclick="do_Search();">&nbsp;<img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel"></td>
		</tr>
	</table>
	<input name="act" type="hidden">
	<input name="companyName" type="hidden" value="<%=dto.getCompanyName()%>">
	<input name="organizationId" type="hidden" value="<%=dto.getOrganizationId()%>">
</form>


<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:840px">
	<table class="headerTable" border="1" width="200%" style="table-layout:fixed;word-break:keep-all;" >
		<tr height="22">
			<td width="5%" align="center">�ʲ���ǩ��</td>
			<td width="6%" align="center">�ʲ�����</td>
			<td width="6%" align="center">����ͺ�</td>
			<td width="8%" align="center">���β���</td>
			<td width="3%" align="center">������</td>
            <td width="3%" align="center">����Ա�����</td>
            <td width="8%" align="center">ʵ�ﲿ��</td>
            <td width="3%" align="center">רҵ������</td>
            <td width="3%" align="center">ʹ����</td>
            <td width="7%" align="center">�ص�</td>
            <td width="2%" align="center">�Ƿ�TD</td>
        </tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:570px;width:857px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="200%" border="1" bordercolor="#666666" style="table-layout:fixed;word-break:break-all">
<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
		<tr height="22">
			<td width="5%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("BARCODE")%>"></td>
			<td width="6%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("ITEM_NAME")%>"></td>
			<td width="6%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("ITEM_SPEC")%>"></td>
			<td width="8%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("RES_DEPT_NAME")%>"></td>
			<td width="3%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("RES_USER_NAME")%>"></td>
            <td width="3%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("EMPLOYEE_NUMBER")%>"></td>
            <td width="8%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("SPECIAL_DEPT_NAME")%>"></td>
			<td width="3%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("SPECIAL_USER_NAME")%>"></td>
			<td width="3%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("MAINTAIN_USER")%>"></td>
			<td width="7%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("DZYH_ADDRESS")%>"></td>
			<td width="2%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("IS_TD")%>"></td>
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
<div style="position:absolute;top:640px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
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
	document.mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	document.mainFrm.target = "_self";
	document.mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.DzyhAssetsDeptReportServlet";
	document.mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Export() {
    document.mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	document.mainFrm.target = "_self";
	document.mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.DzyhAssetsDeptReportServlet";
    document.mainFrm.submit();
}

function do_ShowDetail(organizationId, costCenter, costCenterName){
	document.mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.DzyhAssetsReportServlet";
     document.mainFrm.costCode.value = costCenter;
     document.mainFrm.costCenterName.value = costCenterName;
    document.mainFrm.act.value = "<%=AssetsActionConstant.DETAIL_ACTION%>";
    var selObj = document.mainFrm.costCenterCode;
    selectSpecialOptionByItem(selObj, costCenter);
    var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
    window.open("/public/waiting2.htm", "assWin", style);
    document.mainFrm.target = "assWin";
    document.mainFrm.submit();
}
</script>