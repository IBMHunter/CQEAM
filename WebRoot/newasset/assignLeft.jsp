<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<%
	String act = request.getParameter("act");
	if(act == null){
		act = "";
	}
	String assProp = request.getParameter("assProp");
	if(assProp == null){
		assProp = "";
	}	
%>
<frameset rows="47%,*" framespacing="0" border="0" frameborder="0">
	<frame name="deptContent" src="<%=AssetsURLList.ASSIGN_DEPT_SERVLET%>?act=<%=act%>&assProp=<%=assProp%>">
	<frame name="personContent" src="<%=AssetsURLList.ASSIGN_PERSON_SERVLET%>?act=<%=act%>&assProp=<%=assProp%>" scrolling="auto">
	<noframes>
	<body>

	<p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

	</body>
	</noframes>
</frameset>

</html>
