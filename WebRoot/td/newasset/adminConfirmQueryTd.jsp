<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/td/newasset/headerIncludeTd.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<body topmargin="0" leftmargin="0" onload="do_SetPageWidth();mainFrm.assetsDescription.focus();" onkeydown="autoExeFunction('do_Search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form name="mainFrm" method="post" action="">
<script type="text/javascript">
    printTitleBar("����Ա��ȷ���ʲ���ѯ");
</script>
<%
	TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = false;
	if(rows != null && !rows.isEmpty()){
		hasData = true;
	}	
%>
	<table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
	    <tr>
	    	<td width="8%" align="right">�ʲ����ƣ�</td>
	    	<td width="14%"><input type="text" name="assetsDescription"style="width:100%" value="<%=dto.getAssetsDescription()%>"></td>
	    	<td width="8%" align="right">�������ţ�</td>
	    	<td width="14%">
			<input type="text" name="transNo" style="width:100%" value="<%=dto.getTransNo()%>" size="20">
	    	<td width="8%" align="right">�������ڣ�</td>
	    	<td width="18%"><input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:48%" title="���ѡ��ʼ����" readonly class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)"><input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:48%" title="���ѡ���������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)"></td>
	    	<td width="30%" align="right">
				<img src="/images/eam_images/search.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Search();" alt="��ѯ">&nbsp;
				<img src="/images/eam_images/export.jpg" id="exportImg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">&nbsp;
	    		<img src="/images/eam_images/confirm.jpg" id="confirmImg" style="cursor:'hand'" onclick="do_Confirm();" alt="ȷ���ʲ�">&nbsp;
	    		
	    	</td>
	    </tr>
	</table>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:833px">
		<table class="headerTable" border="1" width="200%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="2%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="7%">�ʲ���ǩ</td>
	            <td align=center width="10%">��������</td>
	            <td align=center width="5%">�ʲ����</td>
	            <td align=center width="8%">�ʲ�����</td>
	            <td align=center width="8%">�ʲ��ͺ�</td>
	            <td align=center width="14%">ԭ�ص�</td>
	            <td align=center width="4%">ԭ������</td>
	            <td align=center width="11%">ԭ���β���</td>
				<td align=center width="14%">����ص�</td>
	            <td align=center width="4%">��������</td>
	            <td align=center width="11%">���벿��</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
	    </table>
	</div>
	<input type="hidden" name="act" value="">
	<input type="hidden" name="exportType" value="">
</form>	
<%
	if(hasData){
%>
	<div id="dataDiv" style="overflow:scroll;height:75%;width:850px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	    <table id="dataTable" width="200%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%		Row row = null;
		String barcode = "";
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
			barcode = row.getStrValue("BARCODE");
%>		
			<tr class="dataTR" onclick="executeClick(this)">
				<td width="2%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
				<td width="7%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly name="newBarcode" value="<%=row.getValue("NEW_BARCODE")%>"></td>
				<td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
				<td width="14%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_LOCATION_NAME")%>"></td>
				<td width="4%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_RESPONSIBILITY_USER_NAME")%>"></td>
				<td width="11%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_RESPONSIBILITY_DEPT_NAME")%>"></td>
				<td width="14%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSIGNED_TO_LOCATION_NAME")%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
				<td width="11%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_DEPT_NAME")%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldAddressId" value="<%=row.getValue("OLD_ADDRESS_ID")%>">
					<input type="hidden" name="addressId" value="<%=row.getValue("ADDRESS_ID")%>">
					<input type="hidden" name="oldLocation" value="<%=row.getValue("OLD_LOCATION")%>">
					<input type="hidden" name="assignedToLocation" value="<%=row.getValue("ASSIGNED_TO_LOCATION")%>">
					<input type="hidden" name="oldResponsibilityUser" value="<%=row.getValue("OLD_RESPONSIBILITY_USER")%>">
					<input type="hidden" name="responsibilityUser" value="<%=row.getValue("RESPONSIBILITY_USER")%>">
					<input type="hidden" name="oldResponsibilityDept" value="<%=row.getValue("OLD_RESPONSIBILITY_DEPT")%>">
					<input type="hidden" name="responsibilityDept" value="<%=row.getValue("RESPONSIBILITY_DEPT")%>">

					<input type="hidden" name="transferType" value="<%=row.getValue("TRANSFER_TYPE")%>">
					<input type="hidden" name="barcode" value="<%=barcode%>">
					<input type="hidden" name="fromOrganizationId" value="<%=row.getValue("FROM_ORGANIZATION_ID")%>">
					<input type="hidden" name="toOrganizationId" value="<%=row.getValue("TO_ORGANIZATION_ID")%>">
				</td>
			</tr>
<%
		}
%>
		</table>
	</div>
<div style="position:absolute;bottom:1px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%		
	}
%>	
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<iframe style="display:none" src="" name="downFrm"></iframe>

<script language="javascript">

function do_Export(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	var exportType = "";
	if(confirm("�ǵ�����ѯ�������豸���ǵ���ѡ����豸��������ѯ���豸������ȷ������ť������ѡ����豸������ȡ������ť")){
		exportType = "<%=AssetsWebAttributes.EXPORT_QUERY_ASSETS%>";
	} else {
		if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
			alert("û�����ݣ�����ִ��ָ���Ĳ�����");
			return;
		}
		if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
			alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
			return;
		}
		exportType = "<%=AssetsWebAttributes.EXPORT_SELECTED_ASSETS%>";
	}
	if(exportType == ""){
		return;
	}
	mainFrm.exportType.value = exportType;
	var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.target = "downFrm";
	mainFrm.action = "/servlet/com.sino.td.newasset.servlet.TdAdminConfirmServlet";
	mainFrm.submit();
}


function do_Search(){
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.td.newasset.servlet.TdAdminConfirmServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.td.newasset.servlet.EtsFaTdAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=860,height=495,left=100,top=130";
	window.open(url, winName, style);
}

function do_Confirm(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
		alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.td.newasset.servlet.TdAdminConfirmServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.CONFIRM_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}
</script>
