<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-12-02
  Time: 00:00:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>Excel�������ϴ�</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript">
            var ArrAction0 = new Array(true, "�ύ", "action_save.gif", "�ϴ�����", "doSub");
            var ArrAction1 = new Array(true, "ճ��", "action_sign.gif", "ճ��", "aa");
            var ArrActions = new Array(ArrAction0, ArrAction1);
            var ArrSinoViews = new Array();
            var ArrSinoTitles = new Array();
    </script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0>
<form name="mainFrm" action="/servlet/com.sino.ams.onnet.servlet.ExcelOnNetSubmit" method="post" enctype="multipart/form-data">
    <script type="text/javascript">
        printTitleBar("Excel�������ϴ�")
    </script>
   <input type="file" name="flName"><input type="button" name="sub" value="�ύExcel" onclick="doSub();"><br>
    <%=WebConstant.WAIT_TIP_MSG%>
</form>
</body>
<script type="text/javascript">
     function doSub() {
         document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
         mainFrm.submit();
     }
</script>
</html>
