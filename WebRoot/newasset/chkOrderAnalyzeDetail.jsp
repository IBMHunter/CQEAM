<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%
	AmsAssetsCheckHeaderDTO chkOrder = (AmsAssetsCheckHeaderDTO) request.getAttribute(AssetsWebAttributes.CHECK_HEADER_DATA);
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.CHECK_LINE_DATAS);
%>
<head>
</head>
<body leftmargin="0" topmargin="0" onload="initPage();" onkeydown="autoExeFunction('do_ArchiveOrder()');">
<form name="mainFrm" action="/servlet/com.sino.ams.newasset.servlet.ChkOrderArchiveServlet" method="post">
<script type="text/javascript">
    printTitleBar("�豸�̵����>>�̵�������");
</script>
<input type="hidden" name="headerId" value="<%=chkOrder.getHeaderId()%>">
<table border="0" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="1" bordercolor="#226E9B">
			    <tr>
			        <td align=right width="8%" height="22">�̵㵥�ţ�</td>
			        <td width="15%" height="22"><input type="text" name="transNo" value="<%=chkOrder.getTransNo()%>" readonly style="width:100%; border-style: solid; border-width: 0;background-color: #F2F9FF"></td>
			        <td align=right width=10% height="22">����������</td>
			        <td width=17% height="22"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="taskDesc" value="<%=chkOrder.getTaskDesc()%>"></td>
			        <td align=right width=10% height="22">�̵㲿�ţ�</td>
			        <td width=17% height="22"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="checkDeptName" value="<%=chkOrder.getCheckDeptName()%>"></td>
			        <td align=right width="8%" height="22">�̵�ص㣺</td>
			        <td width=17% height="22"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="objectLocation" readonly value="<%=chkOrder.getObjectLocation()%>"></td>
			    </tr>
			    <tr>
			        <td align=right height="22" width="8%">��ʼ���ڣ�</td>
			        <td height="22" width="15%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="startTime" value="<%=chkOrder.getStartTime()%>"></td>
			        <td align=right height="22" width="10%">ִ�����ڣ�</td>
			        <td height="22" width="17%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="implementDays" value="<%=chkOrder.getImplementDays()%>"></td>
			        <td align=right height="22" width="8%">ִ���ˣ�</td>
			        <td height="22" width="17%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="implementUser" value="<%=chkOrder.getImplementUser()%>"></td>
			        <td align=right width="8%" height="22">����״̬��</td>
			        <td width="15%" height="22"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="statusName" value="<%=chkOrder.getStatusName()%>"></td>
			    </tr>
			    <tr>
			        <td align=right height="22" width="8%">����ʱ�䣺</td>
			        <td height="22" width="15%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="creationDate" value="<%=chkOrder.getCreationDate()%>"></td>
			        <td align=right height="22" width="10%">�����ˣ�</td>
			        <td height="22" width="17%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="createdUser" value="<%=chkOrder.getCreatedUser()%>"></td>
			        <td align=right height="22" width="8%">�·�ʱ�䣺</td>
			        <td height="22" width="17%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="distributeDate" value="<%=chkOrder.getDistributeDate()%>"></td>
			        <td align=right height="22" width="8%">�·��ˣ�</td>
			        <td height="22" width="15%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="distributeUser" value="<%=chkOrder.getDistributeUser()%>"></td>
			    </tr>
			    <tr>
			        <td align=right height="22" width="8%">����ʱ�䣺</td>
			        <td height="22" width="15%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="downloadDate" value="<%=chkOrder.getDownloadDate()%>"></td>
			        <td align=right height="22" width="10%">�����ˣ�</td>
			        <td height="22" width="17%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="downloadUser" value="<%=chkOrder.getDownloadUser()%>"></td>
			        <td align=right height="22" width="8%">�ϴ�ʱ�䣺</td>
			        <td height="22" width="17%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="uploadDate" value="<%=chkOrder.getUploadDate()%>"></td>
			        <td align=right height="22" width="8%">�ϴ��ˣ�</td>
			        <td height="22" width="15%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="uploadUser" value="<%=chkOrder.getUploadUser()%>"></td>
			    </tr>
			    <tr>
			        <td align=right height="22" width="8%">�鵵ʱ�䣺</td>
			        <td height="22" width="17%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="archivedDate" value="<%=chkOrder.getArchivedDate()%>"></td>
			        <td align=right height="22" width="8%"></td>
			        <td height="22" width="15%"></td>
			        <td align=right height="22" width="8%">�鵵�ˣ�</td>
			        <td height="22" width="17%"><input type="text" style="border-style:solid; border-width:0; width:100%; background-color:#F2F9FF" name="archivedUser" value="<%=chkOrder.getArchivedUser()%>"></td>
			        <td align=right height="22" width="8%"></td>
			        <td height="22" width="15%"></td>
			    </tr>
			    <tr>
			        <td align=right height="44" width="8%">����ԭ��</td>
			        <td height="44" width="92%" colspan="7"><%=StrUtil.htmlStrEncode(chkOrder.getDifferenceReason())%></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<fieldset style="border:1px solid #226E9B; position:absolute;top:194px;width:100%;height:68%">
    <legend>
		<img border="0" src="/images/eam_images/close.jpg" alt="�ر�" onclick="self.close()">
    </legend>
<%
    if (lineSet != null && !lineSet.isEmpty()) {
		AmsAssetsCheckLineDTO lineDTO = null;
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table background="/images/HeaderBack.png" border="1" style="width:140%; color=#FFFFFF">
			<tr>
				<td height="40" rowspan="2" align="center" width="6%">��ǩ��</td>
				<td height="20" colspan="5" align="center" width="28%">ϵͳ����</td>
				<td height="20" colspan="5" align="center" width="28%">ɨ������</td>
				<td height="20" align="center" width="12%" colspan="2">״̬</td>
				<td height="40" rowspan="2" align="center" width="6%">�鵵���</td>
				<td height="40" rowspan="2" align="center" width="3%">�̵�<br>����</td>
				<td height="40" rowspan="2" align="center" width="6%">������<br>������</td>
				<td height="40" rowspan="2" align="center" width="13%">������<br>�㹤��</td>
			</tr>
			<tr>
				<td height="20" align="center" width="4%">רҵ</td>
				<td height="20" align="center" width="6%">����</td>
				<td height="20" align="center" width="6%">�ͺ�</td>
				<td height="20" align="center" width="6%">������</td>
				<td height="20" align="center" width="6%">���β���</td>
				<td height="20" align="center" width="4%">רҵ</td>
				<td height="20" align="center" width="6%">����</td>
				<td height="20" align="center" width="6%">�ͺ�</td>
				<td height="20" align="center" width="6%">������</td>
				<td height="20" align="center" width="6%">���β���</td>
				<td height="20" align="center" width="6%">ϵͳ</td>
				<td height="20" align="center" width="6%">ɨ��</td>
			</tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" border="1" bordercolor="#666666" style="width:140%;TABLE-LAYOUT:fixed;word-break:break-all">
<%	    
		String barcode = "";
		for (int i = 0; i < lineSet.getSize(); i++) {
	        lineDTO = (AmsAssetsCheckLineDTO) lineSet.getDTO(i);
	        barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR">
                <td align="center" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly name="barcode" id="barcode<%=i%>" value="<%=barcode%>"></td>
                <td align="left" width="4%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="itemCategory" id="itemCategory<%=i%>" value="<%=lineDTO.getItemCategory()%>"></td>
                <td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="itemName" id="itemName<%=i%>" value="<%=lineDTO.getItemName()%>"></td>
                <td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="itemSpec" id="itemSpec<%=i%>" value="<%=lineDTO.getItemSpec()%>"></td>
                <td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="responsibilityUserName" id="responsibilityUserName<%=i%>" value="<%=lineDTO.getResponsibilityUserName()%>"></td>
                <td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" value="<%=lineDTO.getResponsibilityDeptName()%>"></td>
                <td align="left" width="4%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="scanItemCategory" id="scanItemCategory<%=i%>" value="<%=lineDTO.getScanItemCategory()%>"></td>
                <td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="scanItemName" id="scanItemName<%=i%>" value="<%=lineDTO.getScanItemName()%>"></td>
                <td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="scanItemSpec" id="scanItemSpec<%=i%>" value="<%=lineDTO.getScanItemSpec()%>"></td>
                <td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="scanResponsibilityUserName" id="scanResponsibilityUserName<%=i%>" value="<%=lineDTO.getScanResponsibilityUserName()%>"></td>
                <td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="scanResponsibilityDeptName" id="scanResponsibilityDeptName<%=i%>" value="<%=lineDTO.getScanResponsibilityDeptName()%>"></td>
                <td align="center" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="systemStatusName" id="systemStatusName<%=i%>" value="<%=lineDTO.getSystemStatusName()%>"></td>
                <td align="center" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="scanStatusName" id="scanStatusName<%=i%>" value="<%=lineDTO.getScanStatusName()%>"></td>
				<td align="left" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="archiveRemark" id="archiveRemark<%=i%>" value="<%=lineDTO.getArchiveRemark()%>"></td>
                <td align="center" width="3%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="chkTimes" id="chkTimes<%=i%>" value="<%=lineDTO.getChkTimes()%>"></td>
                <td align="center" width="6%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="lastChkNo" id="lastChkNo<%=i%>" value="<%=lineDTO.getLastChkDate()%>"></td>
				<td align="left" width="13%"><input type="text" style="width:100%;height:100%;font-family:����;font-size: 9pt;border-width: 0" readonly  name="lastChkDate" id="lastChkDate<%=i%>" value="<%=lineDTO.getLastChkNo()%>"></td>
            </tr>
<%
        }
%>
        </table>
    </div>
<%
	}
%>
</fieldset>
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
</script>
