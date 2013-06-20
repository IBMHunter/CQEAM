<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.ams.carddepot.dto.YsCardDefineDTO" %>
<%@ page import="com.sino.ams.carddepot.dto.YsDepotDefineDTO"%>
<%@ page contentType="text/html;charset=GB2312" language="java" %>
<%--
  created by YS
  Date: 2007-09-27
  Time: 18:23:30
--%>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<HTML>
<head>
    <title>�༭�ӿ���Ϣ</title>
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

<body TEXT="000000" BGCOLOR="FFFFFF" leftmargin=0 topmargin=0 class="STYLE1"
      onload="window.focus();">
<%
    YsDepotDefineDTO ysDepotDefineDTO = (YsDepotDefineDTO) request.getAttribute("YsDepotDefineDTO");
    RequestParser parser = new RequestParser();
    parser.transData(request);
%>

<form name="mainFrm" method="post">
    <jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>" flush="true"/>
    <table border="0" width="100%" style="position:absolute;top:20px">
        <tr>
            <td width="25%" align="right" height="22">�ӿ���룺</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="depotCode" id="depotCode" size="40" class="noemptyInput" style="width:100%"
                       value="<%=ysDepotDefineDTO.getDepotCode()%>" onblur="do_checkCode()">
            </td>
            <td width="" align="left" height="22"></td>
        </tr><tr>
            <td width="25%" align="right" height="22">�ӿ����ƣ�</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="depotName"id="depotName" size="40" class="noemptyInput" style="width:100%"
                       value="<%=ysDepotDefineDTO.getDepotName()%>" onblur="do_checkName()">
            </td>
            <td width="" align="left" height="22"></td>
        </tr><tr>
            <td width="25%" align="right" height="22">�ӿ����ͣ�</td>
            <td width="50%" align="left" height="22" colspan="3">
                <select  name="depotType"id="depotType"  class="noemptyInput" style="width:100%"  >
                   <option value = 1>�ֲ�</option>
                   <option value = 0>�ֿ�</option>
                    </select>
            </td>
            <td width="" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="100%" align="center" height="22" colspan="5">
                <img src="/images/eam_images/save.jpg" alt="����" onclick="do_submit()">&nbsp;
                <img src="/images/eam_images/back.jpg" alt="ȡ��" onclick="do_close();"></td>
        </tr>
    </table>
    <input type="hidden" name="act" value="<%=parser.getParameter("act")%>">
    <input type="hidden" name="depotId" value="<%=ysDepotDefineDTO.getDepotId()%>">
</form>
</BODY>

<script type="text/javascript">
    function do_submit() {

        var fieldNames = "depotCode;depotName";
        var fieldLabels = "����;����";
        if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)) {
            if (mainFrm.depotId.value != "") {
                mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
            } else {
                mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
            }
            mainFrm.action = "/servlet/com.sino.ams.carddepot.servlet.YsDepotDefineServlet";
            mainFrm.submit();
        }
        opener.location.href = "/servlet/com.sino.ams.carddepot.servlet.YsDepotDefineServlet?act=<%=WebActionConstant.QUERY_ACTION%>";
    }

    function do_close() {
        window.close();
        opener.location.href = "/servlet/com.sino.ams.carddepot.servlet.YsDepotDefineServlet?act=<%=WebActionConstant.QUERY_ACTION%>";
    }   //  ������
    function do_checkCode() {

        var url = "";
        var depotCode = document.getElementById("depotCode").value;
        createXMLHttpRequest();
        if (depotCode != "") {
            url = "/servlet/com.sino.ams.carddepot.servlet.YsDepotDefineServlet?act=CHECK_CODE" +
                  "&depotCode=" + depotCode;
            xmlHttp.onreadystatechange = handleReadyStateChange1;
            xmlHttp.open("post", url, true);
            xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xmlHttp.send(null);
        }
    }
    //������
    function handleReadyStateChange1() {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                var resText = xmlHttp.responseText;
                if (resText == "Y") {
                    alert("�����Ѵ��ڣ�");
                    document.getElementById("depotCode").value = "";
                } else if (resText == "N") {
                } else {
                    alert("error!");
                }
            } else {
                alert(xmlHttp.status);
            }
        }
    }//  �������
    function do_checkName() {

        var url = "";
        var depotName = document.getElementById("depotName").value;
        createXMLHttpRequest();
        if (depotName != "") {
            url = "/servlet/com.sino.ams.carddepot.servlet.YsDepotDefineServlet?act=CHECK_NAME" +
                  "&depotName=" + depotName;
            xmlHttp.onreadystatechange = handleReadyStateChange2;
            xmlHttp.open("post", url, true);
            xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xmlHttp.send(null);
        }
    }
    //�������
    function handleReadyStateChange2() {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                var resText = xmlHttp.responseText;
                if (resText == "Y") {  //Y����N������
                    alert("�����Ѵ��ڣ�");
                    document.getElementById("depotName").value = "";
                } else if (resText == "N") {
                } else {
                    alert("error!");
                }
            } else {
                alert(xmlHttp.status);
            }
        }
    }
    function createXMLHttpRequest() {     //����XMLHttpRequest����
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
</SCRIPT>
</HTML>