<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-3-4
  Time: 16:46:19
  Function:�ص���������.
--%>
<%
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "No-cache");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>Excel�ص���Ϣ����</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript">
        var ArrAction0 = new Array(true, "�ύ", "action_save.gif", "�ϴ��ص�", "doSub");
        var ArrAction1 = new Array(true, "ճ��", "action_sign.gif", "ճ��", "aa");
        var ArrActions = new Array(ArrAction0, ArrAction1);
        var ArrSinoViews = new Array();
        var ArrSinoTitles = new Array();
    </script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0>
<form name="mainFrm" action="/servlet/com.sino.ams.system.object.servlet.ImportObjectServlet" method="post" enctype="multipart/form-data">
    <script type="text/javascript">
        printTitleBar("Excel�ص���Ϣ����")
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
            <input type="file" name="flName" class="input_style1" style="width:50%"><input type="button" name="sub" value="�ύExcel" onclick="doSub();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <img src="/images/right-3.jpg" width="5" height="9"/>
            <a href="/document/template/Location Importing Template.zip"  style="cursor:pointer;text-decoration:underline;color:blue"><FONT COLOR="0000ff" size ="2">�ص㵼��ģ��</FONT></a><td width="20%">
            <td></td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">
    function doSub() {
        if (document.mainFrm.flName.value !== "") {
            document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
            mainFrm.submit();
        } else {
            alert("�������ļ���");
        }
    }
</script>
</html>

