<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ include file="/spare/headerInclude.htm"%>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2010-2-1
  Time: 16:50:09
  To change this template use File | Settings | File Templates.
--%>
<html>
<head><title>���ϸ�</title>
</head>
<body leftmargin="1" topmargin="1" onload="init();">
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsItemTransHDTO dto = (AmsItemTransHDTO) request.getAttribute("AIT_HEADER");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
	String status = dto.getTransStatus();
%>
<form name="mainForm" action="/servlet/com.sino.ams.spare.servlet.BjjchgServlet" method="post">
<input type="hidden" name="act" value="">
<input type="hidden" name="transId" value="<%=dto.getTransId()%>">
<input type="hidden" name="transType" value="<%=DictConstant.JCHG%>">
<input type="hidden" name="toObjectNo" value="<%=dto.getToObjectNo()%>">
<input type="hidden" name="fromObjectNo" value="<%=dto.getFromObjectNo()%>">
<input type="hidden" name="transStatus" value="<%=dto.getTransStatus()%>">
<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
<input type="hidden" name="toOrganizationId" value="<%=dto.getToOrganizationId()%>">
<input type="hidden" name="submitFlag" value="0">
<table border="1" bordercolor="#9FD6FF" class="detailHeader" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1">
                <tr height="22">
                    <td width="9%" align="right">���ݺţ�</td>
                    <td width="20%"><input type="text" name="transNo" value="<%=dto.getTransNo()%>" readonly style="width:100%" class="blueborderGray"></td>
                    <td width="9%" align="right">�ֿ����ƣ�</td>
                    <%if (status.equals(DictConstant.COMPLETED)) {%>
                    <td width="25%"><input type="text" name="fromObjectName" value="<%=dto.getFromObjectName()%>" readonly style="width:85%" class="blueborderGray"></td>
                    <%} else {%>
                    <td width="25%"><input type="text" id="objectCategory" name="fromObjectName" value="<%=dto.getFromObjectName()%>" style="width:85%" class="blueborderYellow"><a href="#" onClick="do_SelectObject();" title="���ѡ��ֿ�" class="linka">[��]</a></td>
                    <%}%>
                    <td width="9%" align="right">���ԭ��</td>
                    <td width="20%"><input type="text" name="spareReason" value="���޼���ϸ�" readonly style="width:100%" class="blueborderGray"></td>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td><input type="text" name="createdUser" value="<%=dto.getCreatedUser()%>" readonly style="width:100%" class="blueborderGray"></td>
                    <td align="right">�������ڣ�</td>
                    <td><input type="text" name="creationDate" readonly style="width:85%" value="<%=dto.getCreationDate()%>" class="blueborderGray"></td>
                    <td align="right">����״̬��</td>
                    <td><input type="text" name="transStatusName" readonly style="width:100%" value="<%=dto.getTransStatusName()%>" class="blueborderGray"></td>
                </tr>
                <tr>
                    <td align="right">��ע��</td>
                    <td colspan="7"><textarea name="remark" rows="3" cols="" style="width:100%" class="blueBorder"><%=dto.getRemark()%></textarea>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<fieldset>
<legend>
    <%
        //���ݷ����״̬���ҵ�ǰ�û��Ǵ����˲��в���Ȩ��
        if (!dto.getTransStatus().equals(DictConstant.COMPLETED) && dto.getCreatedBy().equals(user.getUserId())) {
    %>
    <img src="/images/button/addData.gif" alt="�������" onclick="do_SelectItem();">
    <img src="/images/button/deleteLine.gif" alt="ɾ����" onClick="deleteTableRow(document.getElementById('dataTable'),'subCheck');">
    <%--<img src="/images/button/saveTemp.gif" alt="����" id="img3" onClick="do_SavePo(1);">--%>
    <img src="/images/button/ok.gif" alt="ȷ��" id="img4" onClick="do_SavePo(2);">
    <%
        }
    %>
       <%if (!StrUtil.isEmpty(dto.getTransNo())){
               if(!dto.getTransNo().equals(WebAttrConstant.ORDER_NO_AUTO_PRODUCE)){
       %>

    <img src="/images/button/print.gif" alt="��ӡҳ��" onclick="do_print();">
     <%}
        }%>
    <img src="/images/button/close.gif" alt="�ر�" onClick="do_Close();">
<%
	//���ݷ����״̬���ҵ�ǰ�û��Ǵ����˲��в���Ȩ��
	if (!status.equals(DictConstant.COMPLETED) && dto.getCreatedBy().equals(user.getUserId())) {
%>
        ͳһ���ã�<label for="allTransCount">�������</label><input type="checkbox" name="allTransCount" id="allTransCount">
<%
	}
%>

</legend>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:130px;left:1px;width:990px">
		<table class="headerTable" border="1" width="100%">
			<tr height="22" onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
				<td width="3%" align="center"><input type="checkbox" name="mainCheck" onPropertyChange="checkAll('mainCheck', 'subCheck')"></td>
				<td width="10%" align="center">�豸����</td>
				<td width="15%" align="center">�豸�ͺ�</td>
				<td width="10%" align="center">�豸����</td>
				<td width="10%" align="center">��;</td>
                <td width="10%" align="center">����</td>
                <%if (!status.equals(DictConstant.COMPLETED)) {%>
                <td width="5%" align="center">������</td>
                <%}%>
                <td width="5%" align="center">����</td>
				<td width="10%" align="center">��ע</td>
				<td style="display:none">�������ֶ�</td>
			</tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:500px;width:1007px;position:absolute;top:153px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
	<%
		RowSet rows = (RowSet) request.getAttribute("AIT_LINES");
		if (rows == null || rows.isEmpty()) {
	%>
        <tr id="mainTr0" style="display:none" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#FFFFFF'">
            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" style="height:20px;margin:0;padding:0"></td>
            <td width="10%"><input type="text" name="itemName" id="itemName0" value="" class="finput" readonly></td>
            <td width="15%"><input type="text" name="itemSpec" id="itemSpec0" value="" class="finput" readonly></td>
            <td width="10%"><input type="text" name="itemCategory" id="itemCategory0" value="" class="finput" readonly></td>
            <td width="10%"><input type="text" name="spareUsage" id="spareUsage0" value="" class="finput" readonly></td>
            <td width="10%"><input type="text" name="vendorName" id="vendorName0" value="" class="finput" readonly></td>
            <%if (!status.equals(DictConstant.COMPLETED)) {%>
            <td width="5%" align="center"><input type="text" name="onhandQty" id="onhandQty0" value="" class="noborderGray" readonly style="width:100%;text-align:right"></td>
            <%}%>
            <td width="5%"><input type="text" name="quantity" id="quantity0" value="" class="finputNoEmpty3" onblur="checkQty(this);"></td>
            <td width="10%"><input type="text" name="remarkl" id="remarkl0" value="" class="finput"></td>
            <td style="display:none">
                <input type="hidden" name="lineId" id="lineId0" value="">
                <input type="hidden" name="barcode" id="barcode0" value="">
            </td>
        </tr>
<%
	} else {
		Row row = null;
		for (int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
%>
        <tr id="mainTr<%=i%>" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#FFFFFF'">
            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>" style="height:20px;margin:0;padding:0"></td>
            <td width="10%"><input type="text"  name="itemName" id="itemName<%=i%>" value="<%=row.getValue("ITEM_NAME")%>" class="finput" readonly></td>
            <td width="15%"><input type="text"  name="itemSpec" id="itemSpec<%=i%>" value="<%=row.getValue("ITEM_SPEC")%>" class="finput" readonly></td>
            <td width="10%"><input type="text"  name="itemCategory" id="itemCategory<%=i%>" value="<%=row.getValue("ITEM_CATEGORY")%>" class="finput" readonly></td>
            <td width="10%"><input type="text"  name="spareUsage" id="spareUsage<%=i%>" value="<%=row.getValue("SPARE_USAGE")%>" class="finput" readonly></td>
            <td width="10%"><input type="text"  name="vendorName" id="vendorName<%=i%>" value="<%=row.getValue("VENDOR_NAME")%>" class="finput" readonly></td>
            <%if (!status.equals(DictConstant.COMPLETED)) {%>
            <td width="5%" align="center"><input type="text" name="onhandQty" id="onhandQty<%=i%>" value="<%=row.getValue("ONHAND_QTY")%>" class="blueborderGray" style="width:100%;text-align:right"></td>
            <td width="5%"><input type="text" name="quantity" id="quantity<%=i%>" value="<%=row.getValue("QUANTITY")%>" class="finputNoEmpty3" onblur="checkQty(this);"></td>
            <td width="10%"><input type="text" name="remarkl" id="remarkl<%=i%>" value="<%=row.getValue("REMARK")%>" class="finput"></td>
            <%} else {%>
            <td width="5%"><input type="text" name="quantity" id="quantity<%=i%>" value="<%=row.getValue("QUANTITY")%>" class="finput" readonly></td>
            <td width="10%"><input type="text" name="remarkl" id="remarkl<%=i%>" value="<%=row.getValue("REMARK")%>" class="finput" readonly></td>
            <%}%>
            <td style="display:none">
                <input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=row.getValue("LINE_ID")%>">
                <input type="hidden" name="barcode" id="barcode<%=i%>" value="<%=row.getValue("BARCODE")%>">
            </td>
        </tr>
<%
		}
	}
%>
    </table>
</div>
</fieldset>
</form>
</body>
</html>
<script type="text/javascript">
function init() {
	do_SetPageWidth();
}
function do_SelectObject() {
    if (document.mainForm.fromObjectNo.value != "") {
        var count=getCheckBoxCount("subCheck");
        if (count>0) {
            var tab = document.getElementById("dataTable");
             if(!(count==1&&tab.rows[0].style.display == 'none')){
                 alert("����ɾ�����ݣ�������ѡ��ֿ⣡");
                 return;
             }
        }
    }
    var projects = getLookUpValues("<%=LookUpConstant.LOOK_UP_DX_OBJECT%>", 48, 30, "organizationId=<%=user.getOrganizationId()%>&objectCategory=" + "72");
    if (projects) {
        mainForm.fromObjectName.value = projects[0].workorderObjectName;
        mainForm.fromObjectNo.value = projects[0].workorderObjectNo;
    }
}
function do_SelectItem() {
    if (document.mainForm.fromObjectNo.value == "") {
        alert("����ѡ��ֿ⣡");
        return false;
    }
    var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.BJ_SPARE_DX_CATEGORY%>"+"&objectNo="+document.mainForm.fromObjectNo.value;
    var popscript = "dialogWidth:65;dialogHeight:30;center:yes;status:no;scrollbars:no";
    var assets = window.showModalDialog(url, null, popscript);
    if (assets) {
        var data = null;
        var tab = document.getElementById("dataTable");
        for (var i = 0; i < assets.length; i++) {
            data = assets[i];
            if (!isItemExist(data)) {
                data["quantity"] = 1;
                appendDTO2Table(tab, data, false, "barcode");
            }
        }
    }
}

function isItemExist(itemObj) {
    var retVal = false;
    var tab = document.getElementById("dataTable");
    if (tab.rows) {
        var trObjs = tab.rows;
        var trCount = trObjs.length;
        var trObj = null;
        var itemValue = itemObj.barcode;
        var rowValue = null;
        for (var i = 0; i < trCount; i++) {
            trObj = trObjs[i];
            rowValue = trObj.cells[1].childNodes[0].value;
            if (itemValue == rowValue) {
                retVal = true;
            }
        }
    }
    return retVal;
}

var sflag = null;
function do_SavePo(flag) {
    if (flag == 2) {//submit
        var tb = document.getElementById("dataTable");
        if (tb.rows.length == 0 || (tb.rows[0].style.display == 'none' && tb.rows.length == 1)) {
            alert("��ѡ���豸��");
            return false;
        }
        if (!validateData()) {
            return false;
        }
//        if (validateData2()) {
//            alert("�����������ܴ��ڿ�������");
//            return false;
//        }
    }
    if (document.mainForm.submitFlag.value == "1") {
        alert("���ڽ��в�������ȴ���");
        return;
    }
    document.mainForm.submitFlag.value="1";
    sflag = flag;
    if (sflag == 1) {
        mainForm.act.value = "<%=WebActionConstant.SAVE_ACTION%>";
    } else {
        mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
        mainForm.transStatus.value = "<%=DictConstant.COMPLETED%>";
    }
    mainForm.submit();
}

function validateData() {
    var validate = false;
    var fieldNames = "quantity";
    var fieldLabels = "����";
    var validateType = EMPTY_VALIDATE;
    validate = formValidate(fieldNames, fieldLabels, validateType);
    if (validate) {
        validateType = POSITIVE_INT_VALIDATE;
        validate = formValidate(fieldNames, fieldLabels, validateType);
    }
    return validate;
}

function validateData2() {
    var validate = false;
//    var tb = document.getElementById("dataTable");
//    for (var j = 0; j<tb.rows.length; j++) {
//        alert(tb.rows[j].cells[7].childNodes[0].value);
//    }
    var quantitys = document.getElementsByName("quantity");
    for (var i = 0; i < quantitys.length; i++) {
        var quantity = document.getElementById("quantity"+i).value;
        var onhandQty = document.getElementById("onhandQty"+i).value;
        if (Number(quantity) > Number(onhandQty)) {
            validate = true;
            document.getElementById("quantity"+i).focus();
            break;
        }
    }
    return validate;
}

function do_print() {
    var headerId=mainForm.transId.value;
    var url="/servlet/com.sino.ams.spare.servlet.BjjchgServlet?act=print&transId="+headerId;
    var style = 'width=1024,height=670,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=yes';
    window.open(url, "", style);
}


function checkQty(obj){
	var id = obj.id.substring(8,obj.id.length);
	var qtyObj = document.getElementById("quantity"+id);
    var onhandQty = document.getElementById("onhandQty"+id);
    if (isNaN(obj.value)) {
		alert("��������������");
		obj.focus();
		return;
	}
	if (!(Number(obj.value)>0)) {
		alert("�����������0��");
		obj.focus();
		return;
	}
    if (Number(qtyObj.value) > Number(onhandQty.value)) {
        alert("�����������ܴ��ڿ�������");
		obj.focus();
		return;
    }
    do_SetLineCount(obj);
}

function do_SetLineCount(lineBox){
	if(!mainForm.allTransCount){
		return;
	}
	if(!mainForm.allTransCount.checked){
		return
	}
	var id = lineBox.id;
	var lineValue = lineBox.value;
	var fields = document.getElementsByName("quantity");
	var dataTable = document.getElementById("dataTable");
	var rows = dataTable.rows;
	var row = null;
	var checkObj = null;
	var checkedCount = getCheckedBoxCount("subCheck");
	for(var i = 0; i < fields.length; i++){
		if(checkedCount > 0){
			row = rows[i];
			checkObj = row.childNodes[0].childNodes[0];
			if(!checkObj.checked){
				continue;
			}
		}
		if(fields[i].id == id){
			continue;
		}
		fields[i].value = lineValue;
	}
}

function do_Close(){
	var status = "<%=status%>";
	if(status == "" ||status != "<%=DictConstant.COMPLETED%>"){
		if(confirm(CANCEL_MSG)){
			self.close();
		}
	} else {
		self.close();
	}
}
</script>
