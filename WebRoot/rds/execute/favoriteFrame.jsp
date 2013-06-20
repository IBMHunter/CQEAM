<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>�ҵı����ղؼ�</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/rds/WebLibary/css/rds.css">
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/util/Constant.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/toolbar/SinoToolBar.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/toolbar/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/appEntry/AppStandard.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/appEntry/ActionButton.js"></script>
</head>
<body bottomMargin="0" leftMargin="0" topMargin="0" rightMargin="0" onload="window.focus()">
<script type="text/javascript">
    printTitleBar("�ҵı����ղؼ�");
    ArrActions[1] = new Array(true, "����", "application_xp.png", "����", "do_RunReport");
    ArrActions[2] = new Array(true, "ǰ����������", "turnPic.jpg", "ǰ����������", "do_GoReportQuery");
    ArrActions[3] = new Array(true, "ɾ���ղؼ�", "del.gif", "ɾ���ղؼ�", "do_DeleteFavorite");
    ArrActions[4] = new Array(true, "�Ƴ�����", "del.gif", "�Ƴ�����", "do_DeleteReport");
    ArrActions[15][0] = true;
    printToolBar();
</script>
<table style="border:0px; width:100%;height:100%">
    <tr>
        <td style="width:40%;height:100%">
            <iframe name="leftFrm" id="leftFrm" style="width:100%;height:93%" src="<%=contextPath%>/rds/favoriteLeft.do"></iframe>
        </td>
        <td style="width:60%;height:100%">
            <iframe name="rightFrm" id="rightFrm" style="width:100%;height:93%" src=""></iframe>
        </td>
    </tr>
</table>
</body>
</html>
<script type="text/javascript">
    function do_GoReportQuery() {
        window.location.href = "<%=contextPath%>/rds/reportQuery.do";
    }

    function do_CloseConfig() {
        var cfg = new CloseConfig();
        cfg.setEditPage(false);
        cfg.setRefreshOpener(false);
        return cfg;
    }

    function do_RunReport() {
        var frm = document.getElementById("rightFrm");
        var childWin = frm.contentWindow;
        childWin.do_RunReport();
    }

    function do_DeleteFavorite() {
        var frm = document.getElementById("leftFrm");
        var childWin = frm.contentWindow;
        childWin.do_DeleteFavorite();
    }

    function do_DeleteReport() {
        var frm = document.getElementById("rightFrm");
        var childWin = frm.contentWindow;
        childWin.do_DeleteReport();
    }

</script>