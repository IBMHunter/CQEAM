<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
</head>
<body topmargin="0" leftmargin="0" onload="init();" onkeydown="autoExeFunction('do_Search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form name="mainFrm" method="post" action="">
<script type="text/javascript">
    printTitleBar("�����ʲ��Ƿ�ȷ��");
    var ArrAction0 = new Array(true, "��ѯ", "action_view.gif", "��ѯ", "do_Search()");
    var ArrAction1 = new Array(true, "����", "toexcel.gif", "�ر�", "do_Export()");
    var ArrActions = new Array(ArrAction0, ArrAction1);
    var ArrSinoViews = new Array();
    var ArrSinoTitles = new Array();
    printToolBar();
</script>
<%
	AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = false;
	if(rows != null && !rows.isEmpty()){
		hasData = true;
	}	
	
%>
	<table border="0" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
	    <tr>
	    	<td width="8%" align="right">�ʲ����ƣ�</td>
	    	<td width="12%"><input class="input_style1" type="text" name="assetsDescription"style="width:100%" value="<%=dto.getAssetsDescription()%>"></td>
	    	<td width="8%" align="right">�������ţ�</td>
	    	<td width="12%"><input class="input_style1" type="text" name="transNo" style="width:100%" value="<%=dto.getTransNo()%>" size="20">
	    	<td width="8%" align="right">�������ڣ�</td>
	    	<td width="32%"><input class="input_style1" type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:45%" title="���ѡ��ʼ����" readonly  onclick="gfPop.fStartPop(startDate, endDate)">&nbsp;<input class="input_style1" type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:45%" title="���ѡ���������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)"></td>
	        <td width="8%" align="right">�Ƿ�ȷ�ϣ�</td>
	        <td width="12%">
	        	<select class="select_style1" name="confirmYN" >
	        		<option value="">--��ѡ��--</option>
	        		<option value="N" <%request.getAttribute("confirmYN"); if (request.getAttribute("confirmYN") == "N") out.print("selected"); %> >δȷ��</option>
	        		<option value="Y" <%request.getAttribute("confirmYN"); if (request.getAttribute("confirmYN") == "Y") out.print("selected"); %> >��ȷ��</option>
	        	</select>
	        </td>
	    </tr>
	</table>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="exportType" value="">
</form>

	<div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:80px;left:1px;width:100%">
		<table class="eamHeaderTable" border="1" width="200%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
	            <%--<td align=center width="2%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>--%>
	            <td align=center width="7%">�ʲ���ǩ</td>
	            <td align=center width="10%">��������</td>
	            <td align=center width="5%">�ʲ����</td>
	            <td align=center width="8%">�ʲ�����</td>
	            <td align=center width="8%">�ʲ��ͺ�</td>
	            <td align=center width="14%">ԭ�ص�</td>
	            <td align=center width="4%">ԭ������</td>
	            <td align=center width="11%">ԭ���β���</td>
				<td align=center width="14%">����ص�</td>
	            <td align=center width="5%">��������</td>
	            <td align=center width="11%">���벿��</td>
	            <td align=center width="5%">ȷ��״̬</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
	    </table>
	</div>
<%
	if(hasData){
%>
	<div id="dataDiv" style="overflow:scroll;height:75%;width:100%;position:absolute;top:103px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	    <table id="dataTable" width="200%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%		Row row = null;
		String barcode = "";
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
			barcode = row.getStrValue("BARCODE");
%>		
			<tr class="dataTR" onclick="executeClick(this)">
				<%--<td width="2%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>--%>
				<td width="7%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly name="newBarcode" value="<%=row.getValue("NEW_BARCODE")%>"></td>
				<td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
				<td width="14%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_LOCATION_NAME")%>"></td>
				<td width="4%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_RESPONSIBILITY_USER_NAME")%>"></td>
				<td width="11%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_RESPONSIBILITY_DEPT_NAME")%>"></td>
				<td width="14%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSIGNED_TO_LOCATION_NAME")%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
				<td width="11%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_DEPT_NAME")%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("CONFIRM_YN")%>"></td>
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
<div id="pageNaviDiv"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%		
	}
%>	
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<iframe style="display:none" src="" name="downFrm"></iframe>

<script language="javascript">

function init(){
    do_SetPageWidth();
    var t = '<%=request.getAttribute("confirmYN")%>';
    if (t == 'null' ) t = "";
    document.getElementById('confirmYN').value = t.toString();
}

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
	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.AATConfirmYNServlet";
	mainFrm.submit();
}

function do_Search(){
	var cfmYN = document.getElementById('confirmYN').value;
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.AATConfirmYNServlet?confirmYN=" + cfmYN;
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
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
	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.AATConfirmYNServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.CONFIRM_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}
</script>
