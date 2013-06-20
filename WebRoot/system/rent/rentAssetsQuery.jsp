<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes" %>
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
<body leftmargin="0" topmargin="0" onload="initPage();" onkeydown="autoExeFunction('do_Search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<%
	AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String treeCategory = dto.getTreeCategory();
	RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = false;
	if(rows != null && !rows.isEmpty()){
		hasData = true;
	}
	String displayProp = "block";
	String divTop1 = "69";
	String divTop2 = "95";
	String divHeight = "75%";
	if(dto.getCompanyName().equals("")){
		displayProp = "none";
		divTop1 = "47";
		divTop2 = "72";
		divHeight = "78%";
	}
%>
<form name="mainFrm" method="post" action="">
	<table width="98%" border="0">
	    <tr style="display:<%=displayProp%>">
	    	<td width="100%" align="center" colspan="10">
	    		��˾��<input type="text" name="companyName" class="input_style1" value="<%=dto.getCompanyName()%>" readonly size="20" style="border:1px solid #FFFFFF; width:40%; text-align:left">
	    		���ţ�<input type="text" name="deptName" class="input_style1" value="<%=dto.getDeptName()%>" readonly size="20"  style="border:1px solid #FFFFFF; width:40%; text-align:left">
	    	</td>
	    </tr>
	    <tr>
            <td width="6%" align="right">�ʲ����룺</td>
            <td width="12%"><input type="text" name="barcode" class="input_style1" style="width:100%" value="<%=dto.getBarcode()%>"></td>
            <td width="6%" align="right">�ʲ����ƣ�</td>
	    	<td width="12%"><input type="text" name="assetsDescription" class="input_style1" style="width:100%" value="<%=dto.getAssetsDescription()%>"></td>
	    	<td width="8%" align="right">����ͺţ�</td>
	    	<td width="12%"><input type="text" name="modelNumber" class="input_style1" style="width:100%" value="<%=dto.getModelNumber()%>"></td>
	    	<td width="8%" align="right">�ʲ��ص㣺</td>
	    	<td width="12%"><input type="text" name="assetsLocation" class="input_style1" style="width:85%" value="<%=dto.getAssetsLocation()%>"></td>
	    	<!--<td width="8%" align="right"></td>-->
	    	<!--<td width="12%"></td>-->
	    </tr>
	    <tr>
	    	<td width="100%" colspan="10">
	    		<%--<img src="/images/eam_images/apply_allot.jpg" id="transImg" style="cursor:'hand'" onclick="do_Transfer();" alt="�������"><img src="/images/button/applyDiscard.gif" id="discardImg" style="cursor:'hand'" onclick="do_Discard();" alt="���뱨��"><img src="/images/eam_images/apply_deal.jpg" id="clearImg" style="cursor:'hand'" onclick="do_Clear();" alt="���봦��"><img src="/images/eam_images/confirm.jpg" id="confirmImg" style="cursor:'hand'" onclick="do_Confirm();" alt="ȷ���豸">--%>
            <img src="/images/eam_images/search.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Search();" alt="��ѯ"><img src="/images/eam_images/export.jpg" id="exportImg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">
            </td>
	    </tr>
	</table>
	<input type="hidden" name="act" value="">
	<input type="hidden" name="treeCategory" value="<%=treeCategory%>">
	<input type="hidden" name="companyName" value="<%=dto.getCompanyName()%>">
	<input type="hidden" name="faCategory1" value="<%=dto.getFaCategory1()%>">
	<input type="hidden" name="faCategory2" value="<%=dto.getFaCategory2()%>">
	<input type="hidden" name="exportType" value="">
<div id="transferDiv" style="position:absolute;bottom:0px;top:0px;left:0px;right:0px;z-index:10;visibility:hidden;width:100%;height:100%">
	<table width="100%" height="120">
		<tr>
			<td height="25%" width="30%">��</td>
			<td height="25%" width="20%">��</td>
			<td height="25%" width="20%">��</td>
			<td height="25%" width="30%">��</td>
		</tr>
		<tr>
			<td height="25%" width="30%">��</td>
			<td height="25%" width="40%" colspan="2" align="center" bgcolor="#2984CB"><img src="/images/eam_images/ok.jpg" onClick="do_SubmitTransfer();">&nbsp;<img src="/images/eam_images/close.jpg" onClick="do_CloseDiv();"></td>
			<td height="25%" width="30%">��</td>
		</tr>
		<tr>
			<td height="25%" width="30%"></td>
			<td height="25%" width="20%" bgcolor="#2984CB" align="center"><font color="#FFFFFF">ѡ�����������</font></td>
			<td height="25%" width="20%" bgcolor="#2984CB" align="center"><font color="#FFFFFF">ѡ���ʲ����</font></td>
			<td height="25%" width="30%">��</td>
		</tr>
		<tr>
			<td height="25%" width="30%">��</td>
			<td height="25%" width="20%" bgcolor="#2984CB"><select style="width:100%" class="select_style1"  name="transferType" onChange="do_ChangeFaContent(this);"><%=dto.getTransferTypeOption()%></select></td>
			<td height="25%" width="20%" bgcolor="#2984CB"><select style="width:100%" class="select_style1"  name="faContentCode"><option value="">��ѡ��</option></select></td>
			<td height="25%" width="30%">��</td>
		</tr>
		<tr>
			<td height="25%" width="100%" colspan="4">
				<select name="INN_DEPT" id="INN_DEPT" class="select_style1" style="display:none"><%=dto.getInnDeptOpt()%></select>
				<select name="BTW_DEPT" id="BTW_DEPT" class="select_style1" style="display:none"><%=dto.getBtwDeptOpt()%></select>
				<select name="BTW_COMP" id="BTW_COMP" class="select_style1" style="display:none"><%=dto.getBtwCompOpt()%></select>
			</td>
		</tr>
	</table>
</div>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:<%=divTop1%>px;left:1px;width:790px">
		<table class="headerTable" border="1" width="280%">
	        <%--<tr height=20px onClick="executeClick(this)" style="cursor:'hand'" title="���ȫѡ��ȡ��ȫѡ">--%>
	        <tr height=23px >
	            <%--<td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>--%>
	        <td width="5%" align="center">�ʲ�����</td>
            <td width="5%" align="center">�ʲ�����</td>
            <td width="5%" align="center">����ͺ�</td>
            <td width="5%" align="center">�ʲ����</td>
            <td width="7%" align="center">�Է�ǩԼ��λ</td>
            <td width="7%" align="center">�ʲ��ص�</td>
            <td width="7%" align="center">ʹ�ò���</td>
            <td width="3%" align="center">���ڣ��꣩</td>
            <td width="3%" align="center">�����Ԫ��</td>
            <td width="3%" align="center">�����</td>
            <td width="5%" align="center">��ʼ����</td>
            <td width="5%" align="center">��ֹ����</td>
            <td width="3%" align="center">������</td>
            <td width="7%" align="center">���β���</td>
            <td width="5%" align="center">��ע</td>
	        </tr>
	    </table>
	</div>
<%
	if(hasData){
%>
	<div id="dataDiv" style="overflow:scroll;height:<%=divHeight%>;width:807px;position:absolute;top:<%=divTop2%>px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%
	}
%>
</form>
<%
	if(hasData){
%>
	    <table id="dataTable" width="280%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%		Row row = null;
		String barcode = "";
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
			barcode = row.getStrValue("BARCODE");
%>
			<tr class="dataTR" onclick="executeClick(this)">
				<%--<td width="3%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>--%>
               <td width="5%" align= "center"><%=row.getValue("BARCODE")%></td>
                <td width="5%"><%=row.getValue("ITEM_NAME")%></td>
                <td width="5%"><%=row.getValue("ITEM_SPEC")%></td>
                <td width="5%"><%=row.getValue("FINANCE_PROP")%></td>
                <td width="7%"><%=row.getValue("RENT_PERSON")%></td>
                <td width="7%"><%=row.getValue("OBJECT_NAME")%></td>
                <td width="7%"><%=row.getValue("MAINTAIN_DEPT")%></td>
                <td width="3%" align="center"><%=row.getValue("TENANCY")%></td>
                <td width="3%" align="center"><%=row.getValue("YEAR_RENTAL")%> </td>
                <td width="3%" align="center"><%=row.getValue("MONTH_REANTAL")%></td>
                <td width="5%" align="center"><%=row.getValue("RENT_DATE")%></td>
                <td width="5%" align="center"><%=row.getValue("END_DATE")%></td>
                <td width="3%" align="center"><%=row.getValue("USER_NAME")%></td>
                <td width="7%" align="left"><%=row.getValue("GROUP_NAME")%></td>
                <td width="5%" align="left"><%=row.getValue("REMARK")%></td>
			</tr>
<%
		}
%>
		</table>
<div style="position:absolute;top:570px;left:0; right:20px"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<iframe style="display:none" src="" name="downFrm"></iframe>

<script language="javascript">
function do_Transfer(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
		alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	document.getElementById("transferDiv").style.visibility = "visible";
}

function do_SubmitTransfer(){
	var transferType = mainFrm.transferType.value;
	if(transferType == ""){
		alert("����ѡ�����������");
		mainFrm.transferType.focus();
		return;
	} else {
		var deptName = mainFrm.deptName.value;
		var treeCategory = mainFrm.treeCategory.value;
		if(treeCategory != "<%=AssetsWebAttributes.RENT_ASSETS_TREE_PERSON%>") {
			if(transferType != "<%=AssetsDictConstant.TRANS_BTW_COMP%>"){
				if(deptName == ""){
					alert("û��ѡ��������ţ����������ͽṹѡ��");
					return;
				}
			}
		}
//		mainFrm.faContentName.value = mainFrm.faContentCode.options[mainFrm.faContentCode.selectedIndex].text;
		alert("ϵͳ��������ѡ����ʲ�����𣬶���ѡ����ʲ����й���");
		var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
		window.open("_blank", "transWin", style);
		mainFrm.target = "transWin";
		mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
		mainFrm.act.value = "<%=AssetsActionConstant.TRANSFER_ACTION%>";
		mainFrm.submit();
		do_CloseDiv();
	}
}

function do_CloseDiv(){
	document.getElementById("transferDiv").style.visibility = "hidden";
}

function do_Discard(){
	if(mainFrm.deptName.value == ""){
		alert("û��ѡ�񱨷ϲ��ţ����������ͽṹѡ���ź���ִ�иò���");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
		alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	alert("ϵͳ��������ѡ����ʲ�����𣬶���ѡ����ʲ����й���");
	var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
	window.open("_blank", "transWin", style);
	mainFrm.target = "transWin";
	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.DISCARD_ACTION%>";
	mainFrm.submit();
}

function do_Clear(){
	if(mainFrm.deptName.value == ""){
		alert("û��ѡ���ò��ţ����������ͽṹѡ���ź���ִ�иò���");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
		alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	alert("ϵͳ��������ѡ����ʲ�����𣬶���ѡ����ʲ����й���");
	var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
	window.open("_blank", "transWin", style);
	mainFrm.target = "transWin";
	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.CLEAR_ACTION%>";
	mainFrm.submit();
}

function do_SelectDept(){
	var deptCode = mainFrm.deptCode.value;
	var url = "/servlet/com.sino.ams.newasset.servlet.PriviDeptSelectServlet?deptCode=" + deptCode;
	var popscript = "dialogWidth:20;dialogHeight:10;center:yes;status:no;scrollbars:no;help:no";
	var dept = window.showModalDialog(url, null, popscript);
	if(dept){
		mainFrm.deptCode.value = dept.deptCode;
	}
}


function do_Export(){
	var exportType = "";
//	if(confirm("�ǵ�����ѯ�������豸���ǵ���ѡ����豸��������ѯ���豸������ȷ������ť������ѡ����豸������ȡ������ť")){
		exportType = "<%=AssetsWebAttributes.EXPORT_QUERY_ASSETS%>";
	<%--} else {--%>
		<%--if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){--%>
			<%--alert("û�����ݣ�����ִ��ָ���Ĳ�����");--%>
			<%--return;--%>
		<%--}--%>
		<%--if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){--%>
			<%--alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");--%>
			<%--return;--%>
		<%--}--%>
		<%--exportType = "<%=AssetsWebAttributes.EXPORT_SELECTED_ASSETS%>";--%>
	<%--}--%>
	if(exportType == ""){
		return;
	}
	mainFrm.exportType.value = exportType;
	var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.target = "downFrm";
//	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
	mainFrm.action = "/servlet/com.sino.ams.system.rent.servlet.AmsRentAssetServlet";
	mainFrm.submit();
}


function do_Search(){
	mainFrm.target = "_self";
//	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
	mainFrm.action = "/servlet/com.sino.ams.system.rent.servlet.AmsRentAssetServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
}

function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=600,height=340,left=100,top=150";
	window.open(url, winName, style);
}

function do_Close(){
	parent.close();
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
	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.AssetsConfirmServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.CONFIRM_ACTION%>";
	mainFrm.submit();
}

function do_ChangeFaContent(sel){
	var transferType = sel.value;
	dropAllOption("faContentCode");
	copyObjOptions(document.getElementById(transferType), mainFrm.faContentCode);
}

function initPage(){
    do_SetPageWidth();
    do_SetPageButton();
}

function do_SetPageButton() {
    var transImg = document.getElementById('transImg');
    var confirmImg = document.getElementById('confirmImg');
    var clearImg = document.getElementById('clearImg');
    var discardImg = document.getElementById('discardImg');
    var treeCategory = '<%=treeCategory%>';
    if (treeCategory == '<%=AssetsWebAttributes.RENT_ASSETS_TREE_PERSON%>') {  //�����ʲ�
        confirmImg.style.display = 'none';
        discardImg.style.display = 'none';
        clearImg.style.display = 'none';
    } else if (treeCategory == '<%=AssetsWebAttributes.RENT_ASSETS_TREE_DEPART%>') {//�����ʲ�
        clearImg.style.display = 'none';
        discardImg.style.display = 'none';
        confirmImg.style.display = 'none';
    } else if (treeCategory == '<%=AssetsWebAttributes.RENT_ASSETS_TREE_COMPAN%>') {//��˾�ʲ�
        clearImg.style.display = 'none';
        confirmImg.style.display = 'none';
    } else if (treeCategory == '<%=AssetsWebAttributes.RENT_ASSETS_TREE_PROVIN%>') {//ȫʡ�ʲ�
        clearImg.style.display = 'none';
        confirmImg.style.display = 'none';
    } else if (treeCategory == '<%=AssetsWebAttributes.ASSETS_TREE_CONFIRM%>') {//��ȷ���ʲ�
        transImg.style.display = 'none';
        clearImg.style.display = 'none';
        discardImg.style.display = 'none';
    } else if (treeCategory == '<%=AssetsWebAttributes.ASSETS_TREE_TRANSFER%>') {//�����ʲ�
        transImg.style.display = 'none';
        confirmImg.style.display = 'none';
        clearImg.style.display = 'none';
        discardImg.style.display = 'none';
    } else if (treeCategory == '<%=AssetsWebAttributes.ASSETS_TREE_DEPART%>') {

    }
}
function do_SetPageButtionByStatus() {
    var itemStatus = document.getElementById('itemStatus').value;
    var transImg = document.getElementById('transImg');
    var confirmImg = document.getElementById('confirmImg');
    var clearImg = document.getElementById('clearImg');
    var discardImg = document.getElementById('discardImg');
    if(itemStatus=='DISCARDED'){                //�ѱ���

    }else if(itemStatus=='DISPOSED'){           //�Ѵ���

    }else if(itemStatus=='FREE'){               //������

    }else if(itemStatus=='NORMAL'){             //����

    }
}
</script>
