<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
</head>
<body leftmargin="0" topmargin="0" onload="initPage();" topmargin="0" leftmargin="0">
<%
	AmsAssetsAddressVDTO locDTO = (AmsAssetsAddressVDTO)request.getAttribute(AssetsWebAttributes.LOCATION_DATA);
	DTOSet historys = (DTOSet) request.getAttribute(AssetsWebAttributes.LOCATION_ASSETS_DATA);
	String pageTitle = "�ص�" + locDTO.getWorkorderObjectCode() + "��ϸ��Ϣ�����ʲ���Ϣ";
%>
<form name="mainFrm" action="/servlet/com.sino.ams.newasset.servlet.LoctionQueryServlet" method="post">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>
<table border="1" bordercolor="#226E9B" width="100%" id="table1" class="detailHeader">
	<tr>
		<td width="10%" align="right" height="22">�ص���룺</td>
		<td width="15%" height="22"><%=locDTO.getWorkorderObjectCode()%></td>
		<td width="10%" align="right" height="22">�ص��ƣ�</td>
		<td width="40%" height="22"><%=locDTO.getWorkorderObjectName()%></td>
		<td width="10%" align="right" height="22">�ص����</td>
		<td width="15%" height="22"><%=locDTO.getObjectCategoryName()%></td>
	</tr>
	<tr>
		<td width="10%" align="right" height="22">�������ƣ�</td>
		<td width="15%" height="22"><%=locDTO.getCountyName()%></td>
		<td width="10%" align="right" height="22">����λ�ã�</td>
		<td width="40%" height="22"><%=locDTO.getWorkorderObjectLocation()%></td>
		<td width="10%" align="right" height="22">��˾���ƣ�</td>
		<td width="15%" height="22"><%=locDTO.getCompanyName()%></td>
	</tr>
</table>
<fieldset style="border:1px solid #397DF3; position:absolute;top:73px;width:100%;height:88%">
    <legend>
        <img src="/images/eam_images/export.jpg" title="������Excel" onClick="do_Export();">
        <img src="/images/eam_images/close.jpg" id="img6" alt="�ر�" onClick="window.close(); return false;">
    </legend>
<div id="headDiv" style="overflow:hidden;position:absolute;left:1px;width:992px">
	<table class="headerTable" border="1" width="120%" style="text-align:center">
		<tr>
			<td width="10%" height="22">�ʲ���ǩ</td>
			<td width="10%" height="22">�ʲ����</td>
			<td width="10%" height="22">�ʲ�����</td>
			<td width="10%" height="22">�ʲ��ͺ�</td>
			<td width="10%" height="22">�ʲ�ԭֵ</td>
			<td width="10%" height="22">��������</td>
			<td width="10%" height="22">�ʲ���ֵ</td>
			<td width="10%" height="22">������</td>
			<td width="10%" height="22">���β���</td>
			<td width="10%" height="22">ʹ����</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:90%;width:1009px;position:absolute;top:45px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%
	if(historys != null && !historys.isEmpty()){
%>    
    <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		int dataCount = historys.getSize();
		AmsAssetsAddressVDTO assetDTO = null;
		String barcode = "";
		for(int i = 0; i < dataCount; i++){
			assetDTO = (AmsAssetsAddressVDTO)historys.getDTO(i);
			barcode = assetDTO.getBarcode();
%>
		<tr title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')">
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=barcode%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getAssetNumber()%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getAssetsDescription()%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getModelNumber()%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getCost()%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getDatePlacedInService()%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getDeprnCost()%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getResponsibilityUserName()%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getDeptName()%>"></td>
			<td width="10%" align="center"><input type="text" class="finput" readonly value="<%=assetDTO.getMaintainUserName()%>"></td>
		</tr>
<%
		}
%>		
	</table>
<%
	}
%>	
</div>	
</fieldset>
<input type="hidden" name="workorderObjectName" value="<%=locDTO.getWorkorderObjectName()%>">
<input type="hidden" name="workorderObjectCode" value="<%=locDTO.getWorkorderObjectCode()%>">
<input type="hidden" name="workorderObjectNo" value="<%=locDTO.getWorkorderObjectNo()%>">
<input type="hidden" name="act">
</form>
</body>
</html>
<script>
function initPage(){
	window.focus();
	do_SetPageWidth();
}
function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=860,height=495,left=100,top=130";
	window.open(url, winName, style);
}

function do_Export(){
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}
</script>
