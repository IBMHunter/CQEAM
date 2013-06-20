<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <title>ҵ��ƽ̨����--����</title>
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
        printTitleBar("������Ϣά��>>ҵ��ƽ̨����ά��--����");
    </script>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:10%">   
	    <table border="0"  width="100%" id="table1" align = "center">
	        <tr>
	            <td width="30%" align="right" height="22">ҵ��ƽ̨���룺&nbsp;</td>
	             <td width="50%" align="left" height="22">
	            	<input type="text" name = "opeCode" size = "50" class="input_style1" onblur="do_ValidateCode();">&nbsp;<font style="color: red">*</font>
	            </td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">ҵ��ƽ̨���ƣ�&nbsp;</td>
	            <td width="50%" align="left" height="22"><input type="text" name="opeName" class="input_style1" size="50">&nbsp;<font style="color: red">*</font></td>
	        </tr>
	     </table>
	    <p align = "center">
	    	<img src="/images/eam_images/save.jpg" alt="����" onClick="do_Save(); return false;">&nbsp;&nbsp;
	        <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="history.go(-1);">
	    </p>
		<input type="hidden" name="act" value="">
	</div>
</form>
</body>
</html>
<script>
function do_Save() {
		if(mainFrm.opeCode.value != ""){
			if(mainFrm.opeName.value != ""){
				mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
				mainFrm.action = "/servlet/com.sino.ams.system.manydimensions.servlet.OpeServlet";
				mainFrm.submit();
			}else{
				alert("ҵ��ƽ̨���Ʋ���Ϊ��!");
			}			
		}else{
			alert("ҵ��ƽ̨���벻��Ϊ��!");
		}
}
var xmlHttp = null;

function do_ValidateCode() {
	if(mainFrm.opeCode.value != ""){
		var url = "/servlet/com.sino.ams.system.manydimensions.servlet.OpeServlet";
		url += "?act=VALIDATE_ACTION"
		url += "&opeCode=" + mainFrm.opeCode.value;
		do_ProcessSimpleAjax(url, null);
	}
}

function do_ProcessResponse(responseContent){
	with(document){
		if(responseContent == "Y"){
			alert("���������ҵ��ƽ̨�����Ѵ��ڣ����������룡");
			mainFrm.opeCode.value = "";
			mainFrm.opeCode.focus();
		} 
	}
}
</script>

