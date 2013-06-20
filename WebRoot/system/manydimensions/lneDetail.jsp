<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <title>�߼�����Ԫ����������</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>
<body>
 <jsp:include page="/message/MessageProcess"/>
<%
	AmsElementMatchDTO aemDTO = (AmsElementMatchDTO) request.getAttribute(WebAttrConstant.AMS_ELEMENT_MATCH_DTO);
%>

<form name="mainFrm" method="POST">
 	<script language="javascript">
        printTitleBar("������Ϣά��>>�߼�����Ԫ������ά��--����");
        var ArrAction0 = new Array(true, "����", "action_save.gif", "����", "do_Save()");
	    var ArrAction1 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "history.go(-1);");
	    var ArrActions = new Array(ArrAction0,ArrAction1);
	    var ArrSinoViews = new Array();
	    printToolBar();
    </script>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:15%">   
	    <table border="0"  width="100%" id="table1" align = "center">
	    	<tr>
	            <td width="30%" align="right" height="22">����רҵ1��&nbsp;</td>
	             <td width="50%" align="left" height="22">
	            	<input type="text" name = "netCategory1" size = "50" class="input_style1">
	            </td>
	            <td><font color="red">*</font></td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">רҵ1���룺&nbsp;</td>
	             <td width="50%" align="left" height="22">
	            	<input type="text" name = "netCategory1Code" size = "50" class="input_style1">
	            </td>
	            <td><font color="red">*</font></td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">����רҵ2��&nbsp;</td>
	            <td width="50%" align="left" height="22">
	              <input type="text" name="netCategory2" class="input_style1" size="50">
	            </td>
	            <td><font color="red">*</font></td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">רҵ2���룺&nbsp;</td>
	             <td width="50%" align="left" height="22">
	            	<input type="text" name = "netCategory2Code" size = "50" class="input_style1">
	            </td>
	            <td><font color="red">*</font></td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">��Ԫ���룺&nbsp;</td>
	             <td width="50%" align="left" height="22">
	            	<input type="text" name = "netUnitCode" size = "50" class="input_style1" onblur="do_ValidateCode();">
	            </td>
	            <td><font color="red">*</font></td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">�߼�����Ԫ�أ�&nbsp;</td>
	            <td width="50%" align="left" height="22"><input type="text" name="logNetEle" class="input_style1" size="50"></td>
	        	<td><font color="red">*</font></td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">Ӣ����д��&nbsp;</td>
	            <td width="50%" align="left" height="22"><input type="text" class="input_style1" name="engAb" size="50"></td>
	        </tr>
	        <tr>
	        	<td align="right" width="15%">�ɱ����ԣ�&nbsp;</td>
				<td><select name="costType" >
				<option value="">-��ѡ��-</option>
				<option value="ƫ���������ɱ�">ƫ���������ɱ�</option>
				<option value="ƫ���������ɱ�">ƫ���������ɱ�</option>
				<option value="ƫ�̶��ɱ�">ƫ�̶��ɱ�</option>
				</select>
			</td>
	        </tr>
	        <tr>
	        	<td width="15%" align="right">�Ƿ���Ч��&nbsp;</td>
	        	<td width="20%"><select class="select_style1" name="enabled" style="width:20%"><option value="Y">��</option><option value="N" >��</option></select></td>
	        </tr>
	     </table>
		<input type="hidden" name="act" value="">
	</div>
</form>
</body>
</html>
<script>
function do_Save() {
	var fieldNames = "netCategory1;netCategory2;netUnitCode;logNetEle";
        var fieldLabels = "����רҵ1;����רҵ2;��Ԫ����;�߼�����Ԫ��";
        if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)) {
            with (mainFrm) {
				mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
				mainFrm.action = "/servlet/com.sino.ams.system.manydimensions.servlet.LneServlet";
				mainFrm.submit();
            }
   	 	}
}
var xmlHttp = null;

function do_ValidateCode() {
	if(mainFrm.netUnitCode.value != ""){
		var url = "/servlet/com.sino.ams.system.manydimensions.servlet.LneServlet";
		url += "?act=VALIDATE_ACTION"
		url += "&netUnitCode=" + mainFrm.netUnitCode.value;
		do_ProcessSimpleAjax(url, null);
	}
}

function do_ProcessResponse(responseContent){
	with(document){
		if(responseContent == "Y"){
			alert("�����������Ԫ�����Ѵ��ڣ����������룡");
			mainFrm.netUnitCode.value = "";
			mainFrm.netUnitCode.focus();
		} 
	}
}
</script>

