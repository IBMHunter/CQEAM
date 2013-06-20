<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%
	AmsAssetsAddressVDTO dtoPara = (AmsAssetsAddressVDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
	String orgId = userAccount.getOrganizationId();

	RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = false;
	if(rows != null && !rows.isEmpty()){
		hasData = true;
	}
	String assProp = request.getParameter("assProp");
	if(assProp == null){
		assProp = "";
	}
%>
<html>
<body onload="initPage();" topmargin="0" leftmargin="0" onkeydown="autoExeFunction('do_Search()');">
<form method="POST" name="mainFrm" action="/servlet/com.sino.ams.newasset.servlet.AssignRightServlet">
<jsp:include page="/message/MessageProcess"/>
	<table border="0" width="100%" id="table1" style="border-collapse: collapse; "  bgcolor="#EFEFEF">
		<tr>
			<td width="10%" align="right">��ǩ�ţ�</td>
			<td width="20%" align="right" height="20"><input type="text" name="barcode" size="20" style="width:100%" value="<%=dtoPara.getBarcode()%>"></td>
			<td width="10%" align="right">�ʲ����ƣ�</td>
			<td width="20%" align="right"><input type="text" name="assetsDescription" size="20" style="width:100%" value="<%=dtoPara.getAssetsDescription()%>"></td>
			<td width="40%" align="right" rowspan="2">
			<img border="0" src="/images/eam_images/search.jpg" onClick="do_Search();">&nbsp;&nbsp;
			<img border="0" src="/images/button/distribute.gif" onClick="do_SaveAssign()">&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td width="10%" align="right">���ڵص㣺</td>
			<td width="20%" align="right" height="20"><input type="text" name="workorderObjectLocation" size="20" style="width:100%;cursor:hand" class="readonlyInput" readonly title="���ѡ���ʲ��ص�" onClick="do_SelectAddress()" value="<%=dtoPara.getWorkorderObjectLocation()%>"></td>
			<td width="10%" align="right">�������ԣ�</td>
			<td width="20%" align="right"><select name="assignProp" style="width:100%"><%=dtoPara.getAssignOptions()%></select></td>
		</tr>
	</table>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:48px;left:1px;width:743px">
		<table class="headerTable" border="1" width="140%" height="20px" onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	        <tr height="20px">
	            <td align=center width="3%"><input type="checkbox" name="mainCheck" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="9%">��ǩ��</td>
	            <td align=center width="9%">�ʲ����</td>
	            <td align=center width="14%">�ʲ�����</td>
	            <td align=center width="14%">�ʲ��ͺ�</td>
	            <td align=center width="6%">�ʲ�ԭֵ</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="6%">��ֵ</td>
	            <td align=center width="9%">������</td>
				<td align=center width="15%">��������</td>
	            <td align=center width="9%">ʹ����</td>
	        </tr>
	    </table>
	</div>
<%
	if(hasData){
%>
	<div id="dataDiv" style="overflow:scroll;height:84%;width:760px;position:absolute;top:70px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%
	}
%>    
<input type="hidden" name="act">	
<input type="hidden" name="responsibilityUser" value="<%=dtoPara.getResponsibilityUser()%>">
<input type="hidden" name="responsibilityDept" value="<%=dtoPara.getResponsibilityDept()%>">
<input type="hidden" name="maintainUser" value="<%=dtoPara.getMaintainUser()%>">
<input type="hidden" name="workorderObjectNo" value="">
<input type="hidden" name="assProp" value="<%=assProp%>">

</form>	
<%
	if(hasData){
%>
	    <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%		Row row = null;
		String barcode = "";
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
			barcode = row.getStrValue("BARCODE");
%>		
			<tr class="dataTR" onclick="executeClick(this)">
				<td width="3%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
				<td width="9%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=barcode%>" size="20"></td>
				<td width="9%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
				<td width="14%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
				<td width="14%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput3" readonly value="<%=row.getValue("COST")%>"></td>
				<td width="6%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput3" readonly value="<%=row.getValue("DEPRN_COST")%>"></td>
				<td width="9%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
				<td width="15%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
				<td width="9%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("MAINTAIN_USER_NAME")%>"></td>
			</tr>
<%
		}
%>
		</table>
	</div>
<div style="position:absolute;top:592px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%		
	}
%>	
<jsp:include page="/public/hintMessage.jsp" flush="true"/>

</body>
</html>
<script>
function initPage(){
	if(mainFrm.assProp.value != ""){
		return;
	}
	var radios = parent.banner.document.getElementsByName("assProp");
	if(radios){
		if(radios.length){
			for(var i = 0; i < radios.length; i++){
				if(radios[i].checked){
					mainFrm.assProp.value = radios[i].value;
				}
			}
		} else {
			mainFrm.assProp.value = radios.value;
		}
	}
	do_SetPageWidth();
}

function do_Search(){
	var assProp = mainFrm.assProp.value;
	if(assProp == "<%=AssetsWebAttributes.ASSIGN_RESPONSIBLE_USER%>"){
		if(mainFrm.responsibilityDept.value == ""){
			alert("����ѡ���ţ���ִ�в�ѯ��");
			return;
		}
	}
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}

/**
 * ����:ѡ��ص�
 */
function do_SelectAddress() {
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ADDRESS%>";
	var dialogWidth = 55;
	var dialogHeight = 30;
	var userPara = "organizationId=<%=orgId%>";
	var locations = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (locations) {
		var address = locations[0];
		mainFrm.workorderObjectLocation.value = address["workorderObjectLocation"];
		mainFrm.workorderObjectNo.value = address["toObjectNo"];
	} else {
		mainFrm.workorderObjectLocation.value = "";
		mainFrm.workorderObjectNo.value = "";
	}
}

function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=860,height=495,left=100,top=130";
	window.open(url, winName, style);
}

function do_SaveAssign(){
	var assProp = mainFrm.assProp.value;
	var assignDept = mainFrm.responsibilityDept.value;
	var assignPerson = mainFrm.responsibilityUser.value;
	var assignUser = mainFrm.maintainUser.value;
//	alert("assProp = " + assProp );
//	alert("assignDept = " + assignDept);
//	alert("assignUser = " + assignUser );
//	alert("assignPerson = " + assignPerson );
	if(assProp == "<%=AssetsWebAttributes.ASSIGN_COST_CENTER%>"){
		if(assignDept == ""){
			if(!confirm("�����䲿��û��ѡ���������ѡ����ʲ��Ѿ�����ɱ����ģ�ԭ���ķ����ϵ��������Ƿ����������������ȷ������ť������������ȡ������ť��")){
				return;
			}
		}
	
	} else if(assProp == "<%=AssetsWebAttributes.ASSIGN_RESPONSIBLE_USER%>"){
		if(assignDept == ""){
			alert("δѡ���ţ���ѡ��ɱ����ĺ��ٷ��������ˡ�");
			return;
		}
		if(assignPerson == ""){
			if(!confirm("������������û��ѡ���������ѡ����ʲ��Ѿ����������ˣ�ԭ���ķ����ϵ��������Ƿ����������������ȷ������ť������������ȡ������ť��")){
				return;
			}
		}
	} else if(assProp == "<%=AssetsWebAttributes.ASSIGN_MAINTAIN_USER%>"){
		if(assignUser == ""){
			if(!confirm("������ʹ����û��ѡ���������ѡ����ʲ��Ѿ�����ʹ���ˣ�ԭ���ķ����ϵ��������Ƿ����������������ȷ������ť������������ȡ������ť��")){
				return;
			}
		}
	}
	with(mainFrm){
		if($$$CHECK_BOX_HIDDEN$$$ == "undefined"){
			alert("û���ʲ����ݣ�����ִ�иò�����");
			return;
		}
		if($$$CHECK_BOX_HIDDEN$$$.value == ""){
			alert("û��ѡ���ʲ�������ִ�иò�����");
			return;
		}
		action = "/servlet/com.sino.ams.newasset.servlet.AssetsAssignServlet";
		act.value = "<%=AssetsActionConstant.ASSIGN_ACTION%>";
		submit();
	}
}

function do_Close(){
	if(confirm("��ȷ���Ѿ���������Ĺ���������������ȷ������ť������������ȡ������ť��")){
		parent.window.close();
	}
}

</script>
