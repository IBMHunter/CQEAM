<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/sampling/headerInclude.jsp"%>
<%@ include file="/sampling/headerInclude.htm"%><html>

<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<title>��鹤��ά��</title>
</head>
<%
	AmsAssetsSamplingBatchDTO task = (AmsAssetsSamplingBatchDTO)request.getAttribute(SamplingWebAttributes.BATCH_DTO);
	if(task.getTaskName().equals(SamplingWebAttributes.NO_TASK_REMARK)){
%>
	<a href="" onclick="self.close(); return false"><%=task.getTaskName()%></a>
<%
	} else {
		String mainPage = SamplingURLs.BATCH_ORDER_SERVLET;
		mainPage += "?act=";
%>	
<frameset rows="23,*" framespacing="0" border="0" frameborder="0" onload="window.focus()">
	<frame name="banner" scrolling="no" noresize src="/sampling/orderTop.jsp">
	<frameset name="contentFrm" cols="220,9,*" framespacing="0" frameborder="no" border="0">
		<frame name="leftTree" target="orderMain" scrolling="auto" src="<%=SamplingURLs.TASK_BATCH_TREE%>">
	  	<frame name="bar" src="/sampling/splitor.htm" scrolling="no" noresize>
		<frame name="orderMain" src="<%=mainPage%>" scrolling="auto">
	</frameset>
	<noframes>
	<body>

	<p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

	</body>
	</noframes>
</frameset>	
<%
	}
%>
</html>
