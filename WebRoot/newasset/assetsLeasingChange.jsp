<%--
  Created by IntelliJ IDEA.
  User: srf
  Date: 2008-3-17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>����ת�ʴ���</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>

</head>
<body leftmargin="0" topmargin="0">
<script>
    var ArrAction1 = new Array(true, "�˳�", "act_refresh.gif", "�˳�", "window.close()");
    var ArrAction2 = new Array(true, "ת�ʴ���", "act_query.gif", "ת�ʴ���", 'do_match()');
    var ArrActions = new Array(ArrAction1, ArrAction2);
    var ArrSinoViews = new Array();
    printTitleBar("����ת�ʴ���");
    printToolBar();
</script>

<iframe name='amsInfo' src='/servlet/com.sino.ams.match.servlet.AmsAssetsLeasingServlet' height='93%' width='50%'
        scrolling="auto" border="1" frameborder="1"></iframe>
<iframe name='misInfo' src='/servlet/com.sino.ams.match.servlet.AmsAssetsChangeServlet' height='93%' width='50%'
        ></iframe>
<input type="hidden" name="working">
</body>
<script type="text/javascript">
    function do_match() {
        if (document.getElementById("working").value == '1')
        {
            alert('���ڴ����У����Ժ�...');
            return false;
        }

        amsInfo.matchIt();
        document.getElementById("working").value = 0;
    }

    function matchByLocation() {
        amsInfo.matchByLocation();
    }
    function matchByCounty() {
        amsInfo.matchByCounty();
    }
    function matchByCity() {
        amsInfo.matchByCity();
    }
    function searchIt() {
        alert("Constructing...")
    }
    function matchLog() {
        var url = "/servlet/com.sino.ams.match.servlet.BatchMatchLogServlet";
        var popscript = "width=800,height=600";
        window.open(url,"matchLog",popscript);
    }
    function Method2() {
        alert("Constructing...")
    }
    function Method1() {
        alert("Constructing...")
    }

</script>
</html>