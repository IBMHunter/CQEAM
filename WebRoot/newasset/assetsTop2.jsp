<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
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
<body onload="initPage();" leftmargin="0" topmargin="0" background="/images/HeaderBack.png">
<%
	String treeCategory = request.getParameter("treeCategory");
%>
<table border="0" width="100%" height="42" bordercolor="#000000" id="table1" cellspacing="1">
	<tr>
		<td height="21" align="center"><span style="letter-spacing: 3pt"><font face="����" size="5" color="#FFFFFF">�ʲ�̨�˹���ƽ̨</font></span></td>
	</tr>
	<tr>
		<td height="21" align="center"><%=request.getAttribute(AssetsWebAttributes.ASSETS_RADIO)%>&nbsp;<a href="" onClick="do_Close(); return false;">�˳��ʲ�̨��</a></td>
	</tr>
</table>

</body>
</html>
<script>
var treeCategory = "<%=treeCategory%>";

function initPage(){
	var radios = document.getElementsByName("treeCategory");
	if(radios){
		if(radios.length){
			for(var i = 0; i < radios.length; i++){
				if(radios[i].value == treeCategory){
					radios[i].click();
					break;
				}
			}
		} else {
			radios.click();
		}
	}
}

function do_ChangeAssetsTree(obj){
	var treeCategory = obj.value; 
	if(treeCategory != "<%=AssetsWebAttributes.ASSETS_TREE_COMM_QUERY%>"){
		parent.location.href = "<%=AssetsURLList.ASSETS_FRM_SERVLET%>?treeCategory=" + treeCategory;
	} else {
		parent.assetsMain.location.href = "/servlet/com.sino.ams.newasset.servlet.CommonQueryServlet";
	}
}

function do_Close(){
	if(confirm("��ȷ�����Ѿ������������еĹ���������������ȷ������ť������������ȡ������ť��")){
		parent.close();
	}
}
</script>
