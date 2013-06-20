<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>


<%
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) request.getAttribute(QueryConstant.QUERY_DTO);
    String treeCategory = dto.getTreeCategory();
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = false;
    if (rows != null && !rows.isEmpty()) {
        hasData = true;
    }
    String displayProp = "block";
    //String divTop1 = "69";
    //String divTop2 = "92";
    String divTop1 = "89";
    String divTop2 = "112";
    String divHeight = "75%";
    if (dto.getCompanyName().equals("")) {
        displayProp = "none";
        //divTop1 = "47";
        //divTop2 = "70";
        divTop1 = "67";
        divTop2 = "90";
        divHeight = "78%";
    }
    String tabWidth = "300%";
    if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER) || treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)) {
        tabWidth = "180%";
    }
%>

<html>
<style>

FORM {
    margin-top: 0;
    margin-bottom: 0;
}
</style>
<head>
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
</head>
<body leftmargin="0" topmargin="0" onload="initPage();helpInit('2.1.1');" onkeydown="autoExeFunction('do_Search()');">
<script type="text/javascript">
    printTitleBar("���˴�ȷ���ʲ���ѯ");
</script> 
<form name="mainFrm" method="post" action="" > 
<jsp:include page="/message/MessageProcess"/>
<table width="100%" border="0" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
    <tr>
        <td width="8%" align="right">�ʲ����ƣ�</td>
        <td width="14%"><input class="input_style1" type="text" name="assetsDescription" style="width:100%" value="<%=dto.getAssetsDescription()%>"></td>
        <td width="8%" align="right">�ʲ��ͺţ�</td>
        <td width="14%"><input class="input_style1" type="text" name="modelNumber" style="width:100%" value="<%=dto.getModelNumber()%>"></td>
        <td width="8%" align="right">��ǩ�ţ�</td>
        <td width="14%"><input class="input_style1" type="text" name="barcode" style="width:100%" value="<%=dto.getBarcode()%>"></td>
        <td width="34%" align="right">
           <img src="/images/eam_images/search.jpg" id="queryImg" style="cursor:pointer" onclick="do_Search();" title="��ѯ">&nbsp;<img src="/images/eam_images/export.jpg" id="exportImg" style="cursor:pointer" onclick="do_Export();" title="������Excel"><img src="/images/eam_images/confirm.jpg" id="confirmImg" style="cursor:pointer" onclick="do_Confirm();" title="ȷ���豸">&nbsp;
        </td>
    </tr>
</table>
<input type="hidden" name="act" value="">
<input type="hidden" name="treeCategory" value="<%=treeCategory%>">
<input type="hidden" name="companyName" value="<%=dto.getCompanyName()%>">
<input type="hidden" name="faCategory1" value="<%=dto.getFaCategory1()%>">
<input type="hidden" name="faCategory2" value="<%=dto.getFaCategory2()%>">
<input type="hidden" name="exportType" value="">
</form>
<input type="hidden" name="helpId" value=""> 
<div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:48px;left:0px;width:100%">
    <table class="eamHeaderTable" border="1" width="<%=tabWidth%>">
        <tr height=23px onClick="executeClick(this)" style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
            <td align=center width="10%">��ǩ��</td>
            <td align=center width="12%">��������</td>
            <td align=center width="7%">�ʲ����</td>
            <td align=center width="10%">�ʲ�����</td>
            <td align=center width="10%">�ʲ��ͺ�</td>
            <td align=center width="10%">ԭ�ص�</td>
            <td align=center width="4%">ԭ������</td>
            <td align=center width="10%">ԭ���β���</td>
            <td align=center width="10%">����ص�</td>
            <td align=center width="4%">��������</td>
            <td align=center width="10%">���벿��</td>
			<td style="display:none">�������ֶ�</td>
        </tr>
    </table>
</div>

<% 
    if (hasData) {
%>
<div id="dataDiv" style="overflow:scroll;height:<%=divHeight%>;width:100%;position:absolute;top:71px;left:0px;height:485px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
 
	<table id="dataTable" width="<%=tabWidth%>" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
		Row row = null;
        String barcode = "";
        for (int i = 0; i < rows.getSize(); i++) {
            row = rows.getRow(i);
            barcode = row.getStrValue("BARCODE"); 
%>
    <tr class="dataTR" onclick="executeClick(this)">
        <td width="3%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
        <td width="10%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly name="newBarcode" value="<%=row.getValue("NEW_BARCODE")%>"></td>
        <td width="12%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="transNo" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
        <td width="7%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
        <td width="10%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_LOCATION_NAME")%>"></td>
        <td width="4%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_RESPONSIBILITY_USER_NAME")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_RESPONSIBILITY_DEPT_NAME")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSIGNED_TO_LOCATION_NAME")%>"></td>
        <td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_DEPT_NAME")%>"></td>
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
<div id="pageNaviDiv"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%>
</div>
<%
    }
%>

<%=WebConstant.WAIT_TIP_MSG%>
</body>
</html>
<iframe style="display:none" src="" name="downFrm"></iframe>
<script type="text/javascript">

function do_Export() {
    var exportType = "";
    if (confirm("�Ƿ񵼳��豸�������豸������ȷ������ť������������ȡ������ť")) {
        exportType = "<%=AssetsWebAttributes.EXPORT_QUERY_ASSETS%>";
    } else {
    	return false;
    	<%--
        if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$) {
            alert("û�����ݣ�����ִ��ָ���Ĳ�����");
            return;
        }
        if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value) {
            alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
            return;
        }
        exportType = "<%=AssetsWebAttributes.EXPORT_SELECTED_ASSETS%>";
        --%>
    }
    if (exportType == "") {
        return;
    }
    mainFrm.exportType.value = exportType;
    var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.target = "downFrm";
    mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
    mainFrm.submit();
}


function do_Search() {
    mainFrm.target = "_self";
    mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
    mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Confirm() {
    if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$) {
        alert("û�����ݣ�����ִ��ָ���Ĳ�����");
        return;
    }
    if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value) {
        alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
        return;
    }
	var resPerson = "<%=userAccount.getUsername()%>";
    if (confirm("ȷ��ѡ���ʲ���������Ϊ ��" + resPerson +"�� ��ȷ��������ȷ������ť������������ȡ������ť")) {
        mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.AssetsConfirmServlet";
        mainFrm.act.value = "<%=AssetsActionConstant.CONFIRM_ACTION%>";
        mainFrm.submit();
    }
}

function initPage() {
    do_SetPageWidth();
}
</script>
