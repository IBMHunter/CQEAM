<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-1-2
  Time: 10:53:50
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>����ͺ�ȷ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">  
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
	<script language="javascript" src="/WebLibary/js/LookUp.js"></script>
<style>
.finput {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;}
</style>
</head>
<script type="text/javascript">
    printTitleBar("����ͺ�ȷ��")
</script>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	String itemCode1 = request.getParameter("itemCode1");
	String itemType1 = request.getParameter("itemType1");
	String itemName1 = request.getParameter("itemName1");
	String itemSpec1 = request.getParameter("itemSpec1");
	String items = request.getParameter("items");
%>
<body topmargin="0" leftmargin="0">
<jsp:include page="/message/MessageProcess"/>
<form action="/servlet/com.sino.ams.system.part.servlet.PartConfirmServlet" method="post" name="mainFrm">
		<div align="center">
		<table width="60%" border="1" style="border-collapse: collapse" bordercolor="#666666">
	         <tr>
				<td width="15%" align="right" rowspan="3">��ʱ���ݣ�</td>
				<td width="15%" align="right">�豸���ࣺ</td>
				<td width="67%" ><input  class="input_style1" name="itemType1"  readonly style="width:100%" value="<%=itemType1%>"></td>
			</tr>
	         <tr>
				<td width="15%" align="right">�豸���ƣ�</td>
				<td width="67%" ><input  class="input_style1" name="itemName1"  readonly style="width:100%" value="<%=itemName1%>"></td>
			</tr>
			<tr>
				<td align="right" width="15%">����ͺţ�</td>
				<td width="67%"><input class="input_style1"  type="text" name="itemSpec1" readonly value="<%=itemSpec1%>" style="width:100%"></td>
			</tr>
	        <tr>
	            <td width="30%" colspan="2" align="center"><b>ԭ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</b></td>
	            <td align="left" width="67%" ><select name="reason" class="select_style1" style="width:100%" onChange="do_Change(this);"><option value="">��ѡ��ԭ��</option><option value="2" >�ظ�����</option><option value="3">
				�������淶</option></select></td>
	        </tr>
	         <tr>
				<td width="15%" align="right" rowspan="3">��ʽ���ݣ�</td>
				<td width="15%" align="right">�豸���ࣺ</td>
				<td width="67%" >
					<input type="text" name="itemType" class="readonlyInput" readonly value="" style="width:100%">
					<select name="itemCategory" class="input_style1" style="width:100%;display:none" onChange="do_PassText(this)"><%=request.getAttribute(WebAttrConstant.EQUIPMENT_OPTION)%></select>
				</td>
			</tr>
	         <tr>
	            <td width="15%" align="right" >�豸���ƣ�</td>
				<td width="67%"><input type="text" name="itemName" class="input_style1" readonly value="" style="width:100%" ><a id="selectEle" href="#" style="display:none" onClick="do_SelectItem(); " title="���ѡ��" class="linka">[��]</a></td>
			</tr>
				<tr>
	            <td align="right" width="15%">����ͺţ�</td>
				<td width="67%"><input type="text" name="itemSpec" class="input_style1" readonly value="" style="width:100%"></td>
				</tr>
			<tr>
	            <td colspan="3" align="center"><img src="/images/eam_images/confirm.jpg" onClick="do_unite(); return false;" alt="�滻" id="delete"><a style="cursor:'hand'" onClick="do_back();">&nbsp; <img src="/images/eam_images/back.jpg" alt="����"></a></td>
			</tr>
	    </table>		

    	</div>

    <input type="hidden" name="act">
    <input type="hidden" name="itemCode" value="">
    <input type="hidden" name="itemCode1" value="<%=itemCode1%>">
    <input type="hidden" name="items" value="<%=items%>">

</form>
</body>
</html>
<script type="text/javascript">
var orgReason = mainFrm.reason.value;
function do_Change(obj){
	do_ClearData();
	with(mainFrm){
		var val = obj.value;
		if(val == "3"){
			itemType.style.display = "none";
			itemCategory.style.display = "";
			itemName.className = "finput";
			itemSpec.className = "finput";
			itemType.readOnly = false;
			itemName.readOnly = false;
			itemSpec.readOnly = false;
			itemName.focus();
		} else {
			itemType.style.display = "";
			itemCategory.style.display = "none";
			itemName.className = "readonlyInput";
			itemSpec.className = "readonlyInput";
			itemType.readOnly = true;
			itemName.readOnly = true;
			itemSpec.readOnly = true;
		}
		if(val != ""){
			selectEle.style.display = "";
			itemName.style.width = "93%";
		} else {
			selectEle.style.display = "none";
			itemName.style.width = "100%";
		}
	}
}

function do_PassText(obj){
	mainFrm.itemType.value = obj.options[obj.options.selectedIndex].text;
}

function do_back() {
	with(mainFrm){
		itemCode.value = "";
		itemCode1.value ="";
		itemName.value = "";
		itemSpec.value = "";
		act.value = "<%=WebActionConstant.QUERY_ACTION%>";
		submit();
	}
}

function do_SelectItem(){
	var lookUpName = "<%=LookUpConstant.LOOK_UP_EXACT_EQUIP%>";
	var dialogWidth = 45;
	var dialogHeight = 29;
    var objs = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if(objs){
		dto2Frm(objs[0], "mainFrm");
		with(mainFrm){
			itemName.className = "readonlyInput";
			itemSpec.className = "readonlyInput";
			itemCategory.disabled = true;
			itemType.readOnly = true;
			itemName.readOnly = true;
			itemSpec.readOnly = true;
		}
    }
}

function do_ClearData(){
	with(mainFrm){
		var alreadyData = itemCode.value + itemType.value + itemName.value + itemSpec.value;
		if(alreadyData != ""){
			if(confirm("�ı�ԭ������Ѿ�ѡ������ݣ��Ƿ����������������ȷ������ť������������ȡ������ť��")){
				itemCode.value = "";
				itemType.value = "";
				itemName.value = "";
				itemSpec.value = "";
				orgReason = reason.value;
				selectSpecialOptionByItem(itemCategory, "");
				itemCategory.disabled = false;
			} else {
				selectSpecialOptionByItem(reason, orgReason);
			}
		} else {
			itemCode.value = "";
			itemType.value = "";
			itemName.value = "";
			itemSpec.value = "";
			selectSpecialOptionByItem(itemCategory, "");
			itemCategory.disabled = false;
			orgReason = reason.value;
		}
	}
}

function do_unite() {
	if(do_Validate()){
		with(mainFrm){
			if(reason.value =="2"){//�ظ�����
				act.value = "<%=AMSActionConstant.REJIGGER_ACTION%>";
			} else if(reason.value =="3") {//�������淶
				act.value = "<%=AMSActionConstant.INSTEAD_ACTION%>";
			}
			act.value = "<%=AMSActionConstant.INSTEAD_ACTION%>";
			itemCategory.disabled = false;
			submit();
		}
	}
}

function do_Validate(){
	var fieldNames = "reason;itemType;itemName";
	if(mainFrm.itemType.style.display == "none"){
		fieldNames = "reason;itemCategory;itemName";
	}
	var fieldLabels = "ԭ��;�豸����;�豸����";
	return formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
}
</script>