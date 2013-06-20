<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <title>��������������</title>
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
        printTitleBar("������Ϣά��>>����������ά��--����");
    </script>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:10%">   
	    <table border="0"  width="100%" id="table1" align = "center">
	        <tr>
	            <td width="30%" align="right" height="22">�����α��룺&nbsp;</td>
	             <td width="50%" align="left" height="22">
	            	<input type="text" name = "lneCode" size = "50" class="input_style1" onblur="do_ValidateLneCode();">&nbsp;<font color="red">*</font>
	            </td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">���������ƣ�&nbsp;</td>
	            <td width="50%" align="left" height="22">
	               <input type="text" name="lneName" class="input_style1" size="50">&nbsp;<font color="red">*</font>
	            </td>
	        </tr>
	     </table>
	    <p align = "center">
	    	<img src="/images/eam_images/save.jpg" alt="����" onClick="do_Save(); return false;">&nbsp;&nbsp;
	        <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="history.go(-1);">
	    </p>
		<input type="hidden" name="act" value="">
	</div>
</form>
<div align="center" id="warnExist" style="color:blue;visibility:hidden">�Բ��𣬸������α����Ѵ���!</div>
</body>
</html>
<script>
function do_Save() {
		if(mainFrm.lneCode.value != ""){
			if(mainFrm.lneName.value != ""){
				mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
				mainFrm.action = "/servlet/com.sino.ams.system.manydimensions.servlet.NleServlet";
				mainFrm.submit();
			}else{
				alert("���������Ʋ���Ϊ��!");
			}			
		}else{
			alert("�����α��벻��Ϊ��!");
		}
}
var xmlHttp = null;

function do_ValidateLneCode() {
	if(mainFrm.lneCode.value != ""){
		var url = "/servlet/com.sino.ams.system.manydimensions.servlet.NleServlet";
		url += "?act=VALIDATE_ACTION"
		url += "&lneCode=" + mainFrm.lneCode.value;
		do_ProcessSimpleAjax(url, null);
	}
}

function do_ProcessResponse(responseContent){
	with(document){
		if(responseContent == "Y"){
			alert("��������������α����Ѵ��ڣ����������룡");
			mainFrm.lneCode.value = "";
			mainFrm.lneCode.focus();
		} 
	}
}
</script>

