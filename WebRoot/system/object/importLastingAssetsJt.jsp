<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-5-26
  Time: 11:24:20
  To change this template use File | Settings | File Templates.
--%>
<%
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "No-cache");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>Excel���������ʲ�����</title>
    <script type="text/javascript">
        var ArrAction0 = new Array(true, "�ύ", "action_save.gif", "�ϴ�", "doSub");
        var ArrAction1 = new Array(true, "ճ��", "action_sign.gif", "ճ��", "aa");
        var ArrActions = new Array(ArrAction0, ArrAction1);
        var ArrSinoViews = new Array();
        var ArrSinoTitles = new Array();
    </script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0>
<form name="mainFrm" action="/servlet/com.sino.ams.system.object.servlet.ImportLastingAssetsServletJt" method="post" enctype="multipart/form-data">
    <input type="hidden" name="flag" value="0">
    <script type="text/javascript">
        printTitleBar("Excel���������ʲ�����")
    </script>
    <%=WebConstant.WAIT_TIP_MSG%>
    <table border = "0" width="100%">
        <tr>
            <td width="80%">
            <td width="20%">
            <td></td>
        </tr>
        <tr>
            <td width="80%">
            <input type="file" name="flName" class="select_style1" style="width:45%"><input type="button" name="sub" value="�ύExcel" onclick="doSub();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="/images/right-3.jpg" width="5" height="9"/>
            <a href="/document/template/LastingImportingJt.zip"  style="cursor:pointer;text-decoration:underline;color:blue"><FONT COLOR="0000ff" size ="2">Excel���������ʲ�����ģ��</FONT></a><td width="20%">
            <td></td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">
    function doSub() {
        if (document.mainFrm.flag.value == "1") {
            alert("�����ύ���ݣ���ȴ�......");
            return;
        }
        if (document.mainFrm.flName.value !== "") {
            document.mainFrm.flag.value="1";
            document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
            mainFrm.submit();
        } else {
            alert("�������ļ���");
        }
    }
</script>
</html>