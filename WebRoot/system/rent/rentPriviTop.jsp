<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="com.sino.framework.security.dto.ServletConfigDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<base target="contents">
<style type="text/css">
A:LINK, A:VISITED, A:ACTIVE, A:HOVER
{
  color: #FFFFFF;
  font-size: 12px;
  padding-left: 3px;
  TEXT-DECORATION: NONE;
}
</style>
</head>
<body onload="initPage();" leftmargin="0" topmargin="0" rightmargin="1" background="/images/HeaderBack.png">
<%
    String roleId = request.getParameter("roleId");
    String roleName = request.getParameter("roleName");
    ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(application);
%>
<table border="0" width="100%" height="42" bordercolor="#000000" id="table1" cellspacing="1">
	<tr>
		<td height="21" align="center"><span style="letter-spacing: 3pt"><font face="����" size="5" color="#FFFFFF">�� �� �� �� �� �� Ȩ �� ά ��</font></span></td>
	</tr>
	<tr>
		<td height="21" align="center"><%=request.getAttribute(AssetsWebAttributes.PRIVI_RADIO)%>&nbsp;&nbsp;<a href="" onClick="do_Close(); return false;">�˳�Ȩ�޹���</a></td>
	</tr>
</table>
</body>
</html>
<script language="javascript">
function initPage(){
	var firstRole = "<%=roleId%>_<%=roleName%>";
	var radios = document.getElementsByName("roleId");
	if(radios){
		for(var i = 0; i < radios.length; i++){
			if(radios[i].value == firstRole){
				radios[i].click();
				return;
			}
		}
	}
}

function do_ChageRoleId(obj){
	var objValue = obj.value;
	var index = objValue.indexOf("_");
	var roleId = objValue.substring(0, index);
	var roleName = objValue.substring(index + 1);
//    alert(roleName);
	var url = "/servlet/com.sino.ams.system.rent.servlet.RentPriviLeftServlet?roleId=" + roleId + "&roleName=" + roleName;
//	var url = "/servlet/com.sino.ams.newasset.servlet.PriviLeftServlet?roleId=" + roleId + "&roleName=" + roleName;
	parent.contents.location.href = url;
	parent.priviMain.location.href = "about:blank";
}

function do_Close(){
	if(confirm("��ȷ�����Ѿ������������еĹ���������������ȷ������ť������������ȡ������ť��")){
		parent.close();
	}
}
</script>
