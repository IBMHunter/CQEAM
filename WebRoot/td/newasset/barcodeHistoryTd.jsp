<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/td/newasset/headerIncludeTd.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
</head>
<body onload="initPage();" topmargin="0" leftmargin="0">
<%
	String barcode = request.getParameter("barcode");
	DTOSet historys = (DTOSet) request.getAttribute(AssetsWebAttributes.BARCODE_HISTORY_DATA);
    String pageTitle = "��ǩ�ű䶯��ʷ��" + barcode;
%>
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>
<form action="/servlet/com.sino.td.newasset.servlet.TdItemInfoHistoryServlet" method="post" name="mainFrm">
<input type="hidden" name="act">
<div id="headDiv" style="overflow:hidden;position:absolute;top:19px;left:1px;width:994px">
	<table class="headerTable" border="1" width="120%" style="text-align:center">
		<tr>
			<td width="8%" height="22">��ǩ��</td>
			<td width="12%" height="22">�䶯ʱ��</td>
			<td width="14%" height="22">�䶯����</td>
			<td width="6%" height="22">�豸רҵ</td>
			<td width="8%" height="22">�豸����</td>
			<td width="8%" height="22">�豸�ͺ�</td>
			<td width="15%" height="22">���ڵص�</td>
			<td width="8%" height="22">�ص����</td>
			<td width="6%" height="22">������</td>
			<td width="15%" height="22">���β���</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:88%;width:1011px;position:absolute;top:42px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%
	if(historys != null && !historys.isEmpty()){
%>    
    <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		int dataCount = historys.getSize();
		TdItemInfoHistoryDTO history = null;
        TdItemInfoHistoryDTO hdto = (TdItemInfoHistoryDTO)historys.getDTO(0);//���������䶯��ʷ����
        for(int i = 0; i < dataCount; i++){
			history = (TdItemInfoHistoryDTO)historys.getDTO(i);
%>
		<tr>
			<td width="8%" height="22" align="center"><input type="text" class="finput" readonly value="<%=history.getBarcode()%>"></td>
            <td width="12%" height="22" align="center"><input type="text" class="finput" readonly value="<%=history.getCreationDate()%>"></td>
			<td width="14%" height="22"><a href="<%=history.getOrderDtlUrl()%>"><%=history.getOrderNo()%></a></td>
			<td width="6%" height="22"><input type="text" class="finput" readonly value="<%=history.getItemCategoryName()%>"></td>
			<td width="8%" height="22"><input type="text" class="finput" readonly value="<%=history.getItemName()%>"></td>
			<td width="8%" height="22"><input type="text" class="finput" readonly value="<%=history.getItemSpec()%>"></td>
			<td width="15%" height="22"><input type="text" class="finput" readonly value="<%=history.getWorkorderObjectName()%>"></td>
			<td width="8%" height="22"><input type="text" class="finput" readonly value="<%=history.getAddressNo()%>"></td>
			<td width="6%" height="22"><input type="text" class="finput" readonly value="<%=history.getResponsibilityUserName()%>"></td>
			<td width="15%" height="22"><input type="text" class="finput" readonly value="<%=history.getResponsibilityDeptName()%>"></td>
		</tr>
<%
		}
%>
<input type="hidden" name="barcode" value="<%=hdto.getBarcode()%>">
    </table>
<%
	}
%>
<p align="center"><img border="0" src="/images/eam_images/close.jpg" onclick="self.close()"><img src="/images/eam_images/export.jpg" alt="�������" onclick="do_Export();"></p>
</div>
</form>
</body>
</html>
<script>
function initPage(){
	window.focus();
	do_SetPageWidth();
}
function do_Export() {
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}
</script>
