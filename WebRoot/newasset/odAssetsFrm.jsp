<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes"%>
<html>

<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<title>�ʲ�̨�˹���</title>
</head>
<%
	String treeCategory = (String)request.getAttribute(AssetsWebAttributes.TREE_CATEGORY);
	String transferType = (String)request.getAttribute(AssetsWebAttributes.TRANSFER_TYPE);
	String mainPage = "/servlet/com.sino.ams.newasset.servlet.OdEtsFaAssetsServlet";
	mainPage += "?act=";
	mainPage += "&treeCategory=" + treeCategory;
%>	

<frameset rows="0,*" framespacing="0" border="0" frameborder="0" onload="window.focus()">
	<frameset>
		<frame name="contents" target="main" src="" scrolling="auto">
		<frame name="assetsMain" src="<%=mainPage%>" scrolling="auto">
	</frameset>
	<noframes>
	<body>
	<p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>
	</body>
	</noframes>
</frameset>	
</html>
