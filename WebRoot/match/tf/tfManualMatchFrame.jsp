<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <% 
    	String titleText="";
    	String titleBar="";
    	SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    	if ("Y".equalsIgnoreCase(user.getIsTd())) { 
    		titleText="EAM-MIS�˹�ƥ��(TD)";
    		titleBar="EAM-MIS�˹�ƥ��(TD)";
    	} else {
    		titleText="ͨ���ʲ�ƥ��";
    		titleBar="ͨ���ʲ�ƥ��";
    	}
    %>	
	<title><%=titleText %></title>
</head>
<body leftmargin="0" topmargin="0" onload="amsInfo.mainForm.key.focus();">

<script>
    var ArrAction1 = new Array(true, "�˳�", "act_refresh.gif", "�˳�", "window.close()");
    var ArrAction2 = new Array(true, "ƥ��", "act_query.gif", "ƥ��", 'do_match()');
//    var ArrAction3 = new Array(true, "���Ի���ѯ", "act_query.gif", "���Ի���ѯ", "searchIt()");
    var ArrAction4 = new Array(false, "���ص�ƥ��", "act_query.gif", "���ص�ƥ��", "matchByLocation()");
    var ArrAction5 = new Array(false, "����ƥ��", "act_query.gif", "����ƥ��", "matchByCounty()");
    var ArrAction6 = new Array(false, "����ƥ��", "act_query.gif", "����ƥ��", "matchByCity()");
    <% if ("N".equalsIgnoreCase(user.getIsTd())) { %>
    var ArrAction7 = new Array(true, "ƥ����ʷ", "act_query.gif", "�鿴��ʷƥ���¼", "matchLog()");
    <%}%>
//    var ArrAction8 = new Array(true, "��ʽ2", "act_query.gif", "��ʽ2", "Method2()");
//    var ArrAction9 = new Array(false, "��ʽ1", "act_query.gif", "��ʽ1", "Method1()");
	<% if ("N".equalsIgnoreCase(user.getIsTd())) { %>
    var ArrActions = new Array(ArrAction1, ArrAction2, ArrAction4, ArrAction5, ArrAction6);
    <%} else {%>
        var ArrActions = new Array(ArrAction1, ArrAction2, ArrAction4, ArrAction5, ArrAction6);
     <%}%>
    var ArrSinoViews = new Array();
    printTitleBar("<%=titleBar%>");
    printToolBar();
</script>

<iframe name='amsInfo' src='/servlet/com.sino.ams.match.servlet.TFManualMatchAMS' height='93%' width='50%' scrolling="no" frameborder="1"></iframe>
<iframe name='misInfo' src='/servlet/com.sino.ams.match.servlet.TFManualMatchMIS' height='93%' width='50%' scrolling="no" frameborder="1"></iframe>
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
        var url = "/servlet/com.sino.ams.match.servlet.TFBatchMatchLogServlet";
        var popscript = "width=900,height=600";
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