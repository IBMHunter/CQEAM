<%@ page import="com.sino.ams.newasset.constant.AssetsURLList"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
</head>
<%
    String mainPage = AssetsURLList.ITEM_MAINTAIN_SERVLET3;
    String financeReportURL = "/servlet/com.sino.ams.newasset.servlet.ItemFinanceReportServlet";
    //==========================����Ŀǰ���ݿ��ʲ��������ݲ�׼ȷ�����²�ѯ�����ʵ��̨�˲�ѯ��һ�£���ʱע�͵�������ٿ��ţ�����ʤ��ע������ɾ���öδ���financeReportURL===============================
%>
<frameset name="contentFrm" cols="270,9,*" framespacing="0" border="0" frameborder="0" onload="window.focus()">
	<frame name="leftTree" scrolling="no" target="orderMain" src="<%=AssetsURLList.ITEM_FRM_TREE%>">
	<frame name="bar" src="/newasset/splitor2.htm" scrolling="no" noresize>
	<frameset rows="*,15px">
		<frame name="orderMain" src="<%=mainPage%>">
		<%--<frame name="footnotes" src="<%=financeReportURL%>">--%>

		<frame name="footnotes" src="">
	</frameset>
</frameset>
</html>
