<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page contentType="text/html;charset=GB2312" language="java" %>
<%--
  created by YSB
  Date: 2009-01-23
  Time: 14:56:48
--%>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<HTML>
<head>
    <title>�����Ǳ������������ҳ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/DateProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
</head>

<body TEXT="000000" BGCOLOR="FFFFFF" leftmargin=0 topmargin=0 class="STYLE1"
      onload="window.focus();">
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    //String company = (String) request.getAttribute(WebAttrConstant.OU_OPTION);
%>

<form name="mainFrm" method="post">
	<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
    <jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>" flush="true"/>
    <table border="0" width="100%" style="position:absolute;top:20px">
        <tr>
            <td width="25%" align="right" height="22">�������ƣ�</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="taskName" size="40" class="noemptyInput" style="width:100%"
                       value="" onblur="checkTaskName();">
            </td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="remark" size="40" class="noemptyInput" style="width:100%"
                       value=""></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <!--  
        <tr>
            <td width="25%" align="right" height="22">��&nbsp;&nbsp;&nbsp;&nbsp;˾��</td>
            <td width="50%" align="left" height="22" colspan="3">
                <select style="width:100%" type="text" name="company" class="noemptyInput"><%--=company--%>
                </select></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        -->
        <tr>
            <td width="25%" align="right" height="22">��ʼʱ�䣺</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="startDate" class="readonlyInput" 
                		onclick="gfPop.fPopCalendar(startDate)" value="" title="���ѡ��ʼ����" style="width:92%;text-align:center"><img src = "/images/calendar.gif" alt = "���ѡ������" onclick = "gfPop.fPopCalendar(startDate)">
            </td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">����ʱ�䣺</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="endDate" class="readonlyInput" 
                		onclick="gfPop.fPopCalendar(endDate)" value="" title="���ѡ���������" style="width:92%;text-align:center"><img src = "/images/calendar.gif" alt = "���ѡ������" onclick = "gfPop.fPopCalendar(endDate)">
            </td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="100%" align="center" height="22" colspan="5">
                <img src="/images/button/save.gif" alt="����" onclick="do_submit()">&nbsp;
                <img src="/images/button/back.gif" alt="ȡ��" onclick="do_close();"></td>
        </tr>
    </table>
    <input type="hidden" name="act" value="<%=parser.getParameter("act")%>">
    <input type="hidden" name="nameExist" id="nameExist" value="N">
</form>
</BODY>

<script type="text/javascript">
function do_submit() {
    var fieldNames = "taskName;remark;startDate;endDate";
    var fieldLabels = "��������;��ע;��ʼʱ��;����ʱ��";
	
    if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)) {
        mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
        var startDate = document.mainFrm.startDate.value;
        var endDate = document.mainFrm.endDate.value;
        var betweenDays = getBetweenValDays(endDate, startDate);
        if (betweenDays >= 0) {
	        var nameExist = document.getElementById("nameExist").value;
	        if (nameExist == "N") {
	           mainFrm.action = "/servlet/com.sino.nm.ams.instrument.servlet.AmsInstrumentEamYbChkTaskServlet";
	           mainFrm.submit();
	        } else {
	            alert("�����������Ѵ���");
	        }
        } else {
        	alert("��ʼʱ�䲻�ܴ��ڽ���ʱ��!");
        }
    }
}

function do_close() {
    window.close();
    opener.location.href = "/servlet/com.sino.nm.ams.instrument.servlet.AmsInstrumentEamYbChkTaskServlet?act=<%=WebActionConstant.QUERY_ACTION%>";
}

// ------------------------------------------------------------------------
var xmlHttp;

//-- checkTaskName
function checkTaskName() {

    var url = "";
    var objName = document.getElementById("taskName").value;
    xmlHttp = createXMLHttpRequest();
    if (objName != "") {
        url = "/servlet/com.sino.nm.ams.instrument.servlet.AmsInstrumentEamYbChkTaskServlet?act=CHECK_NAME_ACTION&taskName=" + objName;
        xmlHttp.onreadystatechange = handleReadyStateChange;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}

//checkName
function handleReadyStateChange() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            if (resText == '<%=WebAttrConstant.TASK_NAME_EXIST%>') {
                document.getElementById("nameExist").value = "Y";
                document.getElementById("tipMsg").style.visibility = "visible";
            } else if (resText == '<%=WebAttrConstant.TASK_NAME_NOT_EXIST%>') {
                document.getElementById("nameExist").value = "N";
                document.getElementById("tipMsg").style.visibility = "hidden";
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}
</SCRIPT>
</HTML>