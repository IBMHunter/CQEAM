<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>

<html>
<body leftmargin="0" topmargin="0" onload="initPage();helpInit('2.1.1');" onkeydown="autoExeFunction('do_Search()');">
<input type="hidden" name="helpId" value="">
<%=WebConstant.WAIT_TIP_MSG%>
<%
    AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) request.getAttribute(QueryConstant.QUERY_DTO);
    String treeCategory = dto.getTreeCategory();
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = false;
    if (rows != null && !rows.isEmpty()) {
        hasData = true;
    }
    String displayProp = "block";
    String divTop1 = "69";
    String divTop2 = "92";
    String divHeight = "75%";
    if (dto.getCompanyName().equals("")) {
        displayProp = "none";
        divTop1 = "47";
        divTop2 = "70";
        divHeight = "78%";
    }
    String tabWidth = "300%";
    if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER) || treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)) {
        tabWidth = "180%";
    }
%>
<form name="mainFrm" method="post" action="">
<table width="100%" border="0" class="queryTable">
    <tr style="display:<%=displayProp%>">
        <td width="100%" align="center" colspan="8">
            ��˾��<input type="text" name="companyName" value="<%=dto.getCompanyName()%>" readonly size="20" style="border:1px solid #FFFFFF; width:40%; text-align:left">
            ���ţ�<input type="text" name="deptName" value="<%=dto.getDeptName()%>" readonly size="20" style="border:1px solid #FFFFFF; width:40%; text-align:left">
        </td>
    </tr>
    <tr>
        <td width="12%" align="right">�ʲ����ƣ�</td>
        <td width="12%"><input class="input_style1" type="text" name="assetsDescription" style="width:100%" value="<%=dto.getAssetsDescription()%>"></td>
        <td width="12%" align="right">�ʲ��ͺţ�</td>
        <td width="12%"><input class="input_style1" type="text" name="modelNumber" style="width:100%" value="<%=dto.getModelNumber()%>"></td>
        <td width="12%" align="right">��ǩ�ţ�</td>
        <td width="12%"><input class="input_style1" type="text" name="barcode" style="width:100%" value="<%=dto.getBarcode()%>"></td>
        <td width="12%" align="right">�ʲ�״̬��</td>
        <td width="16%"><select class="select_style1" name="itemStatus" id="itemStatus" onchange="do_SetPageButton()"><%=dto.getItemStatusOption()%>
        </select></td>
    </tr>
    <tr>
        <td width="100%" colspan="8" align="right">
            <img src="/images/eam_images/apply_allot.jpg" id="transImg" style="cursor:hand;display:none" onclick="do_Transfer();" title="�������"><img src="/images/button/applyDiscard.gif" id="discardImg" style="cursor:hand;display:none" onclick="do_Discard();" title="���뱨��"><img src="/images/eam_images/apply_deal.jpg" id="clearImg" style="cursor:hand;display:none" onclick="do_Clear();" title="���봦��"><img src="/images/eam_images/confirm.jpg" id="confirmImg" style="cursor:hand;display:none" onclick="do_Confirm();" title="ȷ���豸">&nbsp;<img src="/images/eam_images/search.jpg" id="queryImg" style="cursor:hand" onclick="do_Search();" title="��ѯ">&nbsp;<img src="/images/eam_images/export.jpg" id="exportImg" style="cursor:hand" onclick="do_Export();" title="������Excel">
        </td>
    </tr>
</table>
<input type="hidden" name="act" value="">
<input type="hidden" name="treeCategory" value="<%=treeCategory%>">
<input type="hidden" name="companyName" value="<%=dto.getCompanyName()%>">
<input type="hidden" name="faCategory1" value="<%=dto.getFaCategory1()%>">
<input type="hidden" name="faCategory2" value="<%=dto.getFaCategory2()%>">
<input type="hidden" name="exportType" value="">

<div id="transferDiv"
     style="position:absolute;bottom:0px;top:0px;left:0px;right:0px;z-index:10;visibility:hidden;width:100%;height:100%">
    <table width="100%" height="120">
        <tr>
            <td height="25%" width="30%">��</td>
            <td height="25%" width="20%">��</td>
            <td height="25%" width="20%">��</td>
            <td height="25%" width="30%">��</td>
        </tr>
        <tr>
            <td height="25%" width="30%">��</td>
            <td height="25%" width="40%" colspan="2" align="center" bgcolor="#2984CB">
				<img src="/images/eam_images/ok.jpg" onClick="do_SubmitTransfer();">&nbsp;
				<img src="/images/eam_images/close.jpg" onClick="do_CloseDiv();">
			</td>
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
            <td height="25%" width="20%" bgcolor="#2984CB">
				<select style="width:100%" name="transferType"id="transferType" onChange="do_ChangeFaContent(this);"><%=dto.getTransferTypeOption()%></select>
			</td>
            <td height="25%" width="20%" bgcolor="#2984CB"><select style="width:100%" name="faContentCode">
                <option value="">��ѡ��</option>
            </select></td>
            <td height="25%" width="30%">��</td>
        </tr>
        <tr>
            <td height="25%" width="100%" colspan="4">
                <select name="INN_DEPT" id="INN_DEPT" style="display:none"><%=dto.getInnDeptOpt()%>
                </select>
                <select name="BTW_DEPT" id="BTW_DEPT" style="display:none"><%=dto.getBtwDeptOpt()%>
                </select>
                <select name="BTW_COMP" id="BTW_COMP" style="display:none"><%=dto.getBtwCompOpt()%>
                </select>
            </td>
        </tr>
    </table>
</div>

<%
    if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER) || treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)) {
%>
<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:<%=divTop1%>px;left:0px;width:100%" class="crystalScroll">
    <table class="eamHeaderTable" border="1" width="<%=tabWidth%>">
        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
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
	} else {
%>
<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:<%=divTop1%>px;left:0px;width:100%" class="crystalScroll">
    <table class="eamHeaderTable" border="1" width="<%=tabWidth%>">
        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
            <td align=center width="2%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
            <td align=center width="4%">��ǩ��</td>
            <td align=center width="3%">�ʲ����</td>
            <td align=center width="7%">�ʲ�����</td>

            <td align=center width="8%">�ʲ��ͺ�</td>
            <td align=center width="7%">�ص����</td>
            <td align=center width="8%">�ص�����</td>
            <td align=center width="3%">�ʲ�����</td>
            <td align=center width="3%">�ʲ�ԭֵ</td>

			<td align=center width="3%">��������</td>
            <td align=center width="3%">��������</td>
			<td align=center width="3%">�ʲ���ֵ</td>
            <td align=center width="3%">������</td>

			<td align=center width="3%">Ա����</td>
			<td align=center width="8%">���β���</td>
            <td align=center width="3%">ʹ����</td>
            <td align=center width="3%">�ʲ�״̬</td>

			<td align=center width="10%">�۾��˻�����</td>
            <td align=center width="12%">�۾��˻�����</td>
        </tr>
    </table>
</div>
<%
    }
    if (hasData) {
%>
<div style="overflow:scroll;height:<%=divHeight%>;width:100%;position:absolute;top:<%=divTop2%>px;left:0px;height:485px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
<%
	}
%>
</form>
<%
    if (hasData) {
%>
<div>
	<table id="dataTable" width="<%=tabWidth%>" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
		Row row = null;
        String barcode = "";
        for (int i = 0; i < rows.getSize(); i++) {
            row = rows.getRow(i);
            barcode = row.getStrValue("BARCODE");
            if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER) || treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)) {
%>
    <tr class="dataTR" onclick="executeClick(this)">
        <td width="3%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
        <td width="10%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly name="newBarcode" value="<%=row.getValue("NEW_BARCODE")%>"></td>
        <td width="12%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="transNo" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
        <td width="7%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
        <td width="10%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_LOCATION_NAME")%>"></td>
        <td width="4%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_RESPONSIBILITY_USER_NAME")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_RESPONSIBILITY_DEPT_NAME")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSIGNED_TO_LOCATION_NAME")%>"></td>
        <td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
        <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_DEPT_NAME")%>"></td>
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
		} else {
%>
    <tr class="dataTR" onclick="executeClick(this)">
        <td width="2%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
        <td width="4%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=barcode%>"></td>
        <td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
        <td width="7%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>

		<td width="8%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
        <td width="7%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
        <td width="8%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
        <td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput3" readonly value="<%=row.getValue("CURRENT_UNITS")%>"></td>
        <td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput3" readonly value="<%=row.getValue("COST")%>"></td>
        
		<td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSETS_CREATE_DATE")%>"></td>
        <td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>
        <td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput3" readonly value="<%=row.getValue("DEPRN_COST")%>"></td>
        <td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
        
		<td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("EMPLOYEE_NUMBER")%>"></td>
        <td width="8%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
        <td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("MAINTAIN_USER_NAME")%>"></td>
        <td width="3%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_STATUS")%>"></td>
        
		<td width="10%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("DEPRECIATION_ACCOUNT")%>"></td>
        <td width="12%" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" class="finput3" readonly value="<%=row.getValue("DEPRECIATION_ACCOUNT_NAME")%>"></td>
    </tr>
<%
			}
		}
%>
</table>
</div>
<div style="position:absolute;top:390px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%>
</div>
<%
    }
%>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<iframe style="display:none" src="" name="downFrm"></iframe>

<script language="javascript">
function do_Transfer() {
    if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$) {
        alert("û�����ݣ�����ִ��ָ���Ĳ�����");
        return;
    }
    if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value) {
        alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
        return;
    }
    document.getElementById("transferDiv").style.visibility = "visible";
}
function do_drop() {
    var type = document.getElementById("transferType");
     dropSpecialOption(type,'BTW_COMP');
//    var optionObj = document.all[type].options;
//    var values = 'BTW_COMP';
//    var selectedValueArr = values.split(";");
//    if (selectedValueArr) {
//        var valueCount = selectedValueArr.length;
//        var tempValue = "";
//        for (var i = 0; i < optionObj.length; i++) {
//            tempValue = optionObj[i].value;
//            for (var j = 0; j < valueCount; j++) {
//                if (tempValue == selectedValueArr[j]) {
//                    optionObj[i] = null;
//                    i--;
//                    break;
//                }
//            }
//        }
//    }
}
function do_SubmitTransfer() {
    var transferType = mainFrm.transferType.value;
    if (transferType == "") {
        alert("����ѡ�����������");
        mainFrm.transferType.focus();
        return;
    } else {
        var deptName = mainFrm.deptName.value;
        var treeCategory = mainFrm.treeCategory.value;
        if (treeCategory != "<%=AssetsWebAttributes.ASSETS_TREE_PERSON%>") {
            if (transferType != "<%=AssetsDictConstant.TRANS_BTW_COMP%>") {
                if (deptName == "") {
                    alert("û��ѡ��������ţ����������ͽṹѡ��");
                    return;
                }
            }
        }
        alert("ϵͳ��������ѡ����ʲ�����𣬶���ѡ����ʲ����й���");
        var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
        window.open("/public/waiting.htm", "transWin", style);
        mainFrm.target = "transWin";
        mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
        mainFrm.act.value = "<%=AssetsActionConstant.TRANSFER_ACTION%>";
        mainFrm.submit();
        do_CloseDiv();
    }
}

function do_CloseDiv() {
    document.getElementById("transferDiv").style.visibility = "hidden";
}

function do_Discard() {
    if (mainFrm.deptName.value == "") {
        alert("û��ѡ�񱨷ϲ��ţ����������ͽṹѡ���ź���ִ�иò���");
        return;
    }
    if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$) {
        alert("û�����ݣ�����ִ��ָ���Ĳ�����");
        return;
    }
    if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value) {
        alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
        return;
    }
    alert("ϵͳ��������ѡ����ʲ�����𣬶���ѡ����ʲ����й���");
    var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
    window.open("/public/waiting2.htm", "transWin", style);
    mainFrm.target = "transWin";
    mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
    mainFrm.act.value = "<%=AssetsActionConstant.DISCARD_ACTION%>";
    mainFrm.submit();
}

function do_Clear() {
    if (mainFrm.deptName.value == "") {
        alert("û��ѡ���ò��ţ����������ͽṹѡ���ź���ִ�иò���");
        return;
    }
    if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$) {
        alert("û�����ݣ�����ִ��ָ���Ĳ�����");
        return;
    }
    if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value) {
        alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
        return;
    }
    alert("ϵͳ��������ѡ����ʲ�����𣬶���ѡ����ʲ����й���");
    var style = 'width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no';
    window.open("/public/waiting2.htm", "transWin", style);
    mainFrm.target = "transWin";
    mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet";
    mainFrm.act.value = "<%=AssetsActionConstant.CLEAR_ACTION%>";
    mainFrm.submit();
}

function do_SelectDept() {
    var deptCode = mainFrm.deptCode.value;
    var url = "/servlet/com.sino.ams.newasset.servlet.PriviDeptSelectServlet?deptCode=" + deptCode;
    var popscript = "dialogWidth:20;dialogHeight:10;center:yes;status:no;scrollbars:no;help:no";
    var dept = window.showModalDialog(url, null, popscript);
    if (dept) {
        mainFrm.deptCode.value = dept.deptCode;
    }
}


function do_Export() {
    var exportType = "";
    if (confirm("�ǵ�����ѯ�������豸���ǵ���ѡ����豸��������ѯ���豸������ȷ������ť������ѡ����豸������ȡ������ť")) {
        exportType = "<%=AssetsWebAttributes.EXPORT_QUERY_ASSETS%>";
    } else {
        if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$) {
            alert("û�����ݣ�����ִ��ָ���Ĳ�����");
            return;
        }
        if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value) {
            alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
            return;
        }
        exportType = "<%=AssetsWebAttributes.EXPORT_SELECTED_ASSETS%>";
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


function do_ShowDetail(barcode) {
    var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
    var winName = "assetsWin";
    var style = "width=860,height=495,left=100,top=130";
    window.open(url, winName, style);
}

function do_Close() {
    parent.close();
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
    mainFrm.target = "_self";
    mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.AssetsConfirmServlet";
    mainFrm.act.value = "<%=AssetsActionConstant.CONFIRM_ACTION%>";
    mainFrm.submit();
}

function do_ChangeFaContent(sel) {
    var transferType = sel.value;
    dropAllOption("faContentCode");
    copyObjOptions(document.getElementById(transferType), mainFrm.faContentCode);
}

function initPage() {
    do_SetPageWidth();
    do_SetPageButton();
    do_drop();
}

function do_SetPageButton() {
    var treeCategory = mainFrm.treeCategory.value;
    var itemStatus = mainFrm.itemStatus.value;

    var transImg = document.getElementById('transImg');
    var confirmImg = document.getElementById('confirmImg');
    var clearImg = document.getElementById('clearImg');
    var discardImg = document.getElementById('discardImg');
    if (treeCategory == "<%=AssetsWebAttributes.ASSETS_TREE_PERSON%>") {  //�����ʲ�
        if (itemStatus == "<%=AssetsDictConstant.ITEM_STATUS_NORMAL%>") {//����״̬
            transImg.style.display = "";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_DISCARDED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_CLEARED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_FREED%>") {//����״̬
            transImg.style.display = "";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else {
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        }
    } else if (treeCategory == "<%=AssetsWebAttributes.ASSETS_TREE_DEPART%>") {//�����ʲ�
        if (itemStatus == "<%=AssetsDictConstant.ITEM_STATUS_NORMAL%>") {//����״̬
            transImg.style.display = "";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_DISCARDED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_CLEARED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_FREED%>") {//����״̬
            transImg.style.display = "";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else {
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        }
    } else if (treeCategory == "<%=AssetsWebAttributes.ASSETS_TREE_COMPAN%>") {//��˾�ʲ�
        if (itemStatus == "<%=AssetsDictConstant.ITEM_STATUS_NORMAL%>") {//����״̬
            transImg.style.display = "";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_DISCARDED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_CLEARED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_FREED%>") {//����״̬
            transImg.style.display = "";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else {
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        }
    } else if (treeCategory == "<%=AssetsWebAttributes.ASSETS_TREE_PROVIN%>") {//ȫʡ�ʲ�
        transImg.style.display = "none";
        confirmImg.style.display = "none";
        discardImg.style.display = "none";
        clearImg.style.display = "none";
    } else if (treeCategory == "<%=AssetsWebAttributes.ASSETS_TREE_CONFIRM%>") {//��ȷ���ʲ�
        if (itemStatus == "<%=AssetsDictConstant.ITEM_STATUS_NORMAL%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_DISCARDED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_CLEARED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else if (itemStatus == "<%=AssetsDictConstant.ASSETS_STATUS_FREED%>") {//����״̬
            transImg.style.display = "none";
            confirmImg.style.display = "none";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        } else {
            transImg.style.display = "none";
            confirmImg.style.display = "";
            discardImg.style.display = "none";
            clearImg.style.display = "none";
        }
    } else if (treeCategory == "<%=AssetsWebAttributes.ASSETS_TREE_TRANSFER%>") {//�����ʲ�
        transImg.style.display = "none";
        confirmImg.style.display = "none";
        clearImg.style.display = "none";
        discardImg.style.display = "none";
    }
}
</script>
