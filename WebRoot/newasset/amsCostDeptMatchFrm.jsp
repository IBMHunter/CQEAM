<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-8-25
  Time: 14:51:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>����-�ɱ�����(ƥ��)</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>

</head>
<body leftmargin="0" topmargin="0" onload="window.focus();">
<script>
    var ArrAction1 = new Array(true, "�˳�", "act_refresh.gif", "�˳�", "window.close()");
    var ArrAction2 = new Array(true, "ƥ��", "act_query.gif", "ƥ��", 'do_match()');
    var ArrActions = new Array(ArrAction1, ArrAction2);
    var ArrSinoViews = new Array();
    printTitleBar("����-�ɱ�����(ƥ��)");
    printToolBar();
</script>

<iframe name='amsInfo' src='/servlet/com.sino.ams.newasset.servlet.AmsDeptMatch' height='93%' width='50%' scrolling="no" frameborder="1"></iframe>
<iframe name='misInfo' src='/servlet/com.sino.ams.newasset.servlet.AmsCostMatch' height='93%' width='50%' scrolling="no" frameborder="1"></iframe>
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
    function searchIt() {
        alert("Constructing...")
    }
</script>
</html>