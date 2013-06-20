<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.yj.dto.AmsYjTeamDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%--
<%--
  Created by IntelliJ IDEA.
  User: jialongchuan
  Date: 2010-7-1
  Time: 16:39:30
  To change this template use File | Settings | File Templates.
--%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<HTML>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>Ӧ�����϶�����ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
</head>

<body>
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsYjTeamDTO amsYjTeamDTO = (AmsYjTeamDTO) request.getAttribute("AMS_YJ_TEAM");
    boolean isNew = StrUtil.isEmpty(amsYjTeamDTO.getTeamName());
%>
<script type="text/javascript">
    printTitleBar("Ӧ�����϶�����ϸ��Ϣ");
</script>

<form name="mainFrm" action="/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet" method="post">
    <input type="hidden" name="show" value="show">
    <input type="hidden" name="act" value="">
    <input type="hidden" name="teamId" value="<%=amsYjTeamDTO.getTeamId()%>">
    <input type="hidden" name="isExist">
    <table border="0" width="100%" id="table1">
        <tr>
            <td width="6%" colspan = "3" align="right">��˾���ƣ�</td>
            <td width="11%" ><select class="select_style1" style="width:50%" name="organizationId"><%=amsYjTeamDTO.getOrganizationOption()%>
            </select></td>
        </tr>
        <tr>
            <td width="6%"  colspan = "3" align="right">�������ƣ�</td>
            <td width="35%" align="left" height="22">
            <input type="text" name="teamName" size="40" class="input_style1" style="width:50%" value="<%=amsYjTeamDTO.getTeamName()%>">&nbsp;<font color="red">*</font>  </td>

        </tr>
        <tr>
            <td width="6%" colspan = "3" align="right">��ҵ�����ˣ�</td>
            <td width="35%"  align="left" height="22"><input type="text" name="responsibilityUser" size="40" class="input_style1"
                                                            style="width:50%" value="<%=amsYjTeamDTO.getResponsibilityUser()%>"></td>
        </tr>
        <tr>
            <td width="6%" colspan = "3" align="right">�ֻ���</td>
            <td width="35%" align="left" height="22"><input type="text" name="tel" size="40" class="input_style1"
                                                            style="width:50%" value="<%=amsYjTeamDTO.getTel()%>"></td>
        </tr>
        <tr>
            <td width="6%" colspan = "3" align="right">�������������ص㣺</td>
            <td width="35%"  align="left" height="22"><input type="text" name="situation" size="40" class="input_style1"
                                                            style="width:50%" value="<%=amsYjTeamDTO.getSituation()%>"></td>
        </tr>

        <tr>
            <td  width="6%" colspan = "3" align="right"></td>
            <td width="35%" align="left" height="22" >
                <img src="/images/eam_images/save.jpg" alt="�������" onClick="do_Save(); return false;">&nbsp;
                <%
                    if (!amsYjTeamDTO.getTeamName().equals("")) {
                %>
                <img src="/images/eam_images/disable.jpg" alt="ʧЧ" onClick="do_Delete(); return false;">&nbsp;
                <img src="/images/eam_images/enable.jpg" alt="��Ч" onClick="do_Enable(); return false;">&nbsp;
                <%
                    }
                %>
                <img src="/images/eam_images/back.jpg" alt="����ر�" onClick="do_Back(); return false;"></td>
        </tr>

    </table>
</form>
</body>
</html>
<script>
    function do_Save() {
        var teamName = document.getElementsByName("teamName")[0];
        if(teamName.value==null || teamName.value==""){
           alert("�����������!");
           teamName.focus();
           return false;
        }
        
        var fieldNames = "teamName";
        var fieldLabels = "��������";
        var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);

//        do_verifyItemName();       �ж϶��������Ƿ����ƣ�������Ҫ��֤��ֻ��Ҫ���ø÷���

        if (isValid) {
            if (mainFrm.isExist.value == "Y") {
                alert("�ö��������Ѵ��ڣ�");
                return;
            }
            var action = "<%=WebActionConstant.CREATE_ACTION%>";
        <%if(isNew){%>
            document.mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
        <%}else{%>
            document.mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
        <%}%>
            document.mainFrm.submit();
        }
    }

    function do_Delete() {
        var teamId = document.mainFrm.teamId.value;
        if (confirm("ȷ��ʧЧ�ö��������𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
            document.mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
            document.mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet?teamId=" + teamId;
            document.mainFrm.submit();
        }
    }

    function do_Enable() {
        var teamId = document.mainFrm.teamId.value;
        if (confirm("ȷ����Ч�ö��������𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
            document.mainFrm.act.value = "<%=AMSActionConstant.INURE_ACTION%>";
            document.mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet?teamId=" + teamId;
            document.mainFrm.submit();
        }
    }

    function do_Back() {
        document.mainFrm.teamId.value = "";
        document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet";
        document.mainFrm.submit();
    }

    var xmlHttp;
    function do_verifyItemName() {
        var url = "";
        createXMLHttpRequest();
        url = "/servlet/com.sino.ams.yj.servlet.AmsYjTeamServlet?act=verifyTeamName&teamName=" + document.mainFrm.teamName.value;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, false);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }

    function createXMLHttpRequest() {//����XMLHttpRequest����
        try {
            xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
        } catch(e) {
            try {
                xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
            } catch(e) {
                try {
                    xmlHttp = new XMLHttpRequest();
                } catch(e) {
                    alert("����XMLHttpRequest����ʧ�ܣ�");
                }
            }
        }
    }

    function handleReadyStateChange1() {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                if (xmlHttp.responseText == 'Y') {
                    document.mainFrm.isExist.value = 'Y';
                } else {
                    document.mainFrm.isExist.value = 'N';
                }
            } else {
                alert(xmlHttp.status);
            }
        }
    }

</SCRIPT>