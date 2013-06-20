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
        printTitleBar("������Ϣά��>>Ͷ�ʷ�������ά��--����");
    </script>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:10%">   
	    <table border="0"  width="100%" id="table1" align = "center">
	    	<tr>
	            <td width="30%" align="right" height="22">Ͷ�ʴ��ࣺ&nbsp;</td>
	             <td width="50%" align="left" height="22">
	            	<input type="text" name = "investCategory1" size = "50" class="input_style1" >&nbsp;<font color="red">*</font>
	            </td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">Ͷ�����ࣺ&nbsp;</td>
	            <td width="50%" align="left" height="22">
	                <input type="text" name="investCategory2" class="input_style1" size="50">&nbsp;<font color="red">*</font>
	            </td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">Ͷ�ʷ�����룺&nbsp;</td>
	             <td width="50%" align="left" height="22">
	            	<input type="text" name = "investCatCode" size = "50" class="input_style1" onblur="do_ValidateCode();">&nbsp;<font color="red">*</font>
	            </td>
	        </tr>
	        <tr>
	            <td width="30%" align="right" height="22">Ͷ�ʷ������ƣ�&nbsp;</td>
	            <td width="50%" align="left" height="22">
	             <input type="text" name="investCatName" class="input_style1" size="50">&nbsp;<font color="red">*</font>
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
</body>
</html>
<script>
function do_Save() {
	if(mainFrm.investCategory1.value != ""){
		if(mainFrm.investCategory2.value != ""){
			if(mainFrm.investCatCode.value != ""){
				if(mainFrm.investCatName.value != ""){
					mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
					mainFrm.action = "/servlet/com.sino.ams.system.manydimensions.servlet.CexServlet";
					mainFrm.submit();
				}else{
					alert("Ͷ�ʷ������Ʋ���Ϊ�գ�");
				}			
			}else{
				alert("Ͷ�ʷ�����벻��Ϊ�գ�");
			}
		} else {
			alert("Ͷ�����಻��Ϊ�գ�");
		}
	} else {
		alert("Ͷ�ʴ��಻��Ϊ�գ�");
	}
}
var xmlHttp = null;

function do_ValidateCode() {
	if(mainFrm.investCatCode.value != ""){
		var url = "/servlet/com.sino.ams.system.manydimensions.servlet.CexServlet";
		url += "?act=VALIDATE_ACTION"
		url += "&investCatCode=" + mainFrm.investCatCode.value;
		do_ProcessSimpleAjax(url, null);
	}
}

function do_ProcessResponse(responseContent){
	with(document){
		if(responseContent == "Y"){
			alert("���������Ͷ�ʷ�������Ѵ��ڣ����������룡");
			mainFrm.investCatCode.value = "";
			mainFrm.investCatCode.focus();
		} 
	}
}
</script>

