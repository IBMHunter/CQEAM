<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(application);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<base target="contents">
<style type="text/css">
.style1 {
	font-family: ����;
	font-size: 16pt;
	text-align: center;
	color: #FFFFFF;
}
A:LINK, A:VISITED, A:ACTIVE, A:HOVER
{
  color: #FFFFFF;
  font-size: 12px;
  padding-left: 3px;
  TEXT-DECORATION: NONE;
}
</style>
</head>
<body topmargin="0" leftmargin="0"  background="/images/HeaderBack.png">
<table border="0" width="100%" height="42" bordercolor="#000000" id="table1" cellspacing="1">
	<tr>
		<td height="21" align="center"><span style="letter-spacing: 3pt"><font face="����" size="5" color="#FFFFFF">�ʲ���Ա����</font></span></td>
	</tr>
	<tr>
		<td height="21" align="center"><%=request.getAttribute(AssetsWebAttributes.ASSIGN_RADIO)%>&nbsp;<a href="" onClick="do_Close(); return false;">�˳���Ա����</a></td>
	</tr>
</table>
</body>
</html>
<script>
function do_ChangeAssignProp(obj){
	var radioVal = obj.value;
	var url = "";
	if(radioVal == "<%=AssetsWebAttributes.ASSIGN_COST_CENTER%>"){
		url = "<%=AssetsURLList.ASSIGN_DEPT_SERVLET%>";
	} else if(radioVal == "<%=AssetsWebAttributes.ASSIGN_RESPONSIBLE_USER%>"){
		url = "<%=AssetsURLList.ASSIGN_LEFT_SERVLET%>";
	} else if(radioVal == "<%=AssetsWebAttributes.ASSIGN_MAINTAIN_USER%>"){
		url = "<%=AssetsURLList.ASSIGN_USER_SERVLET%>";
	}
	parent.contents.location.href = url + "?assProp=" + obj.value;
	parent.AssignMain.location.href = "/servlet/com.sino.ams.newasset.servlet.AssignRightServlet?assProp=" + radioVal;
	if(radioVal == "<%=AssetsWebAttributes.ASSIGN_COST_CENTER%>"){
		parent.AssignMain.mainFrm.assProp.value = obj.value;
		parent.AssignMain.do_Search();
	}
}

function do_Close(){
	if(confirm("��ȷ�����Ѿ������������еĹ���������������ȷ������ť������������ȡ������ť��")){
		parent.close();
	}
}
</script>