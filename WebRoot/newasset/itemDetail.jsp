<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%
	AmsAssetsAddressVDTO itemDTO = (AmsAssetsAddressVDTO)request.getAttribute(AssetsWebAttributes.ITEM_INFO_DTO);
	String barcode = itemDTO.getBarcode();
	DTOSet logDatas = (DTOSet)request.getAttribute(AssetsWebAttributes.BARCODE_LOGS);
	boolean hasData = (logDatas != null && !logDatas.isEmpty());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style>
.finput {BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;}
</style>
</head>
<body onload="initPage();" leftmargin="0" topmargin="0">
<form action="<%=AssetsURLList.ITEM_LOG_SERVLET%>" method="post" name="mainFrm">
<script type="text/javascript">
    printTitleBar("�豸��<%=barcode%>����ά����ʷ")
</script>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table border="0" width="100%" bordercolor="#666666" id="table3">
				<tr>
					<td width="20%" align="right">��ǩ�ţ�</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="barcode" value ="<%=barcode%>"></td>
					<td width="20%" align="right">�豸רҵ��</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="itemCategoryName" value ="<%=itemDTO.getItemCategoryName()%>"></td>
				</tr>
				<tr>
					<td width="20%" align="right">�豸���ƣ�</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="itemName" value ="<%=itemDTO.getItemName()%>"></td>
					<td width="20%" align="right">�豸�ͺţ�</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="itemSpec" value ="<%=itemDTO.getItemSpec()%>"></td>
				</tr>
				<tr>
					<td width="20%" align="right">�������ڣ�</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="startDate" value ="<%=itemDTO.getStartDate()%>"></td>
					<td width="20%" align="right">���ڵص㣺</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="workorderObjectName" value ="<%=itemDTO.getWorkorderObjectName()%>"></td>
				</tr>
				<tr>
					<td width="20%" align="right">�����ˣ�</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="responsibilityUserName" value ="<%=itemDTO.getResponsibilityUserName()%>"></td>
					<td width="20%" align="right">���β��ţ�</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="responsibilityDeptName" value ="<%=itemDTO.getResponsibilityDeptName()%>"></td>
				</tr>
				<tr>
					<td width="20%" align="right">ʹ���ˣ�</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="maintainUserName" value ="<%=itemDTO.getMaintainUser()%>"></td>
					<td width="20%" align="right">ʹ�ò��ţ�</td>
					<td width="30%"><input style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" type="text" name="maintainDeptName" value ="<%=itemDTO.getMaintainDeptName()%>"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<fieldset style="border:1px solid #397DF3; position:absolute;top:140px;width:100%;height:78%">
    <legend>
	    <img src="/images/eam_images/export.jpg" alt="�������" onclick="do_Export();">
	    <img border="0" src="/images/eam_images/close.jpg" onclick="self.close()" width="63" height="18">
	</legend>
<div id="headDiv" style="overflow:hidden;position:absolute;top:21px;left:1px;width:828px">
	<table border=1 width="100%" class="headerTable">
		<tr class=headerTable height="20px">
			<td align=center width="10%">�޸���</td>
			<td align=center width="15%">�޸�ʱ��</td>
			<td align=center width="75%">�޸�����</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:90%;width:845px;position:absolute;top:42px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	<table id="dataTable" width="100%" border="1" bordercolor="#666666">
<%
	if (hasData) {
		AmsItemCorrectLogDTO logDTO = null;
		for (int i = 0; i < logDatas.getSize(); i++) {
			logDTO = (AmsItemCorrectLogDTO)logDatas.getDTO(i);
%>
        <tr class="dataTR">
          <td width="10%"><%=logDTO.getCreatedUser()%></td>
          <td width="15%" align="center"><%=logDTO.getCreationDate()%></td>
          <td width="75%"><%=StrUtil.htmlStrEncode(logDTO.getCorrectContent())%></td>
        </tr>
<%

		}
	}
%>
    </table>
</div>
</fieldset>
	<input type="hidden" name="barcode" value="<%=itemDTO.getBarcode()%>">
	<input type="hidden" name="act">
</form>
</body>
</html>
<script type="text/javascript">
function initPage(){
	window.focus();
	do_SetPageWidth();
}

function do_Export() {
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}
</script>