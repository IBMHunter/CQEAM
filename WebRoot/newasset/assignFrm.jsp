<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>

<html>

<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=GBK">
<title>�ʲ�����ҳ��</title>
</head>
<%
	String act = request.getParameter("act");
	if(act == null){
		act = "";
	}
	String leftURL = (String)request.getAttribute(AssetsWebAttributes.ASSETS_DYNAMIC_URL);
	String assProp = (String)request.getAttribute(AssetsWebAttributes.ASS_PROP);
%>

<frameset rows="86,*" framespacing="0" border="0" frameborder="0" onload="window.focus()">
	<frame name="banner" scrolling="no" noresize target="contents" src="/servlet/com.sino.ams.newasset.servlet.AssignTopServlet">
	<frameset cols="250,*">
		<frame name="contents" src="<%=leftURL%>?act=<%=act%>&assProp=<%=assProp%>" scrolling="auto">
		<frame name="AssignMain" src="/servlet/com.sino.ams.newasset.servlet.AssignRightServlet?act=<%=act%>&assProp=<%=assProp%>">
	</frameset>
	<noframes>
	<body>

	<p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

	</body>
	</noframes>
</frameset>

</html>
