<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%--
  Created by IntelliJ IDEA.
  User: Lijun
  Date: 2007-1-23
  Time: 11:52:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>ί�д���������</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    </head>
<body topmargin="0" leftmargin="0">
<script type="text/javascript">
    printTitleBar("ί�д���������");
</script>
<%
    RowSet rows = (RowSet) request.getAttribute("SPLIT_DATA_VIEW");
    Row row = null;
    if (rows.getSize() > 0) {
        row = rows.getRow(0);
%>
<font size="2px">����ǰ�Ĵ�����Ϊ��</font><a href="#" class="linka" onclick="show_Detail('<%=row.getValue("AGENT_USER_ID")%>')"><%=row.getValue("AGENT_USER_NAME")%></a>
<%
} else {
%>
<p align="center">����ǰ��û��ί�д�����</p>
<p align="center"><img src="/images/eam_images/new_add.jpg" alt="ί�д�����" onClick="do_addAgency();"></p>
<%
    }
%>
</body>
</html>
<script type="text/javascript">
    function do_addAgency(){
        url = "/servlet/com.sino.flow.servlet.UserAgencyServlet?forward=addNew";
        popscript = "dialogWidth:25;dialogHeight:30;center:yes;status:yes;scrollbars:no";
        var flag = window.showModalDialog(url, '', popscript);
//        alert(flag)
        if(flag == "Y"){
            window.location.href = "/servlet/com.sino.flow.servlet.UserAgencyServlet";;
        }
    }
    function show_Detail(agentUserId){
        url = "/servlet/com.sino.flow.servlet.UserAgencyServlet?forward=detail&agentUserId=" + agentUserId;
        popscript = "dialogWidth:25;dialogHeight:30;center:yes;status:yes;scrollbars:no";
        var flag = window.showModalDialog(url, '', popscript);
//    alert(flag)
        if(flag == "Y"){
            window.location.href = "/servlet/com.sino.flow.servlet.UserAgencyServlet";
        }
    }
</script>