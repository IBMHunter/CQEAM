<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%@page import="com.sino.ams.constant.WebAttrConstant"%>
<%@page import="com.sino.base.util.StrUtil"%>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-4-26
  Time: 10:27:56
  To change this template use File | Settings | File Templates.
--%>
<%
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "No-cache");

String isNew = StrUtil.nullToString( request.getParameter( "isNew" ) );
String allResName = (String) request.getAttribute( WebAttrConstant.ALL_RES_NAME );

if( null == allResName ){
	allResName = "ʵ���������";
	if( "Y".equals( isNew ) ){
		allResName = "ʵ���������";
	}
} 


String action = "/servlet/com.sino.ams.system.object.servlet.ImportItemServlet";
String templateFile = "/document/template/ItemImporting.zip";
String resSimpleName = "ʵ���������";
if( "Y".equals( isNew ) ){
	resSimpleName = "ʵ���������";
	action = "/servlet/com.sino.ams.system.object.servlet.ImportNewItemServlet";
	templateFile = "/document/template/ItemNewImporting.zip";
}

%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title><%= resSimpleName %></title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript">
        var ArrAction0 = new Array(true, "�ύ", "action_save.gif", "�ϴ�", "doSub");
        var ArrAction1 = new Array(true, "ճ��", "action_sign.gif", "ճ��", "aa");
        var ArrActions = new Array(ArrAction0, ArrAction1);
        var ArrSinoViews = new Array();
        var ArrSinoTitles = new Array();
    </script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0>
<form name="mainFrm" action="<%= action %>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="flag" value="0">
    <script type="text/javascript">
        printTitleBar("<%= allResName %>"); 
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
            <input type="file" name="flName" style="width:45%"><input type="button" name="sub" value="�ύExcel" onclick="doSub();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="/images/right-3.jpg" width="5" height="9"/>
            <a href="<%= templateFile %>"  style="cursor:pointer;text-decoration:underline;color:blue"><FONT COLOR="0000ff" size ="2"><%= resSimpleName %>ģ��</FONT></a><td width="20%">
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