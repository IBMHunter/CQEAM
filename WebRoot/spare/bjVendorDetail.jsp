<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%@ page import="com.sino.ams.spare.repair.dto.AmsVendorInfoDTO"%>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-12-02
  Time: 00:00:00
  To change this template use File | Settings | File Templates.
--%>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>����ά�޹�˾ά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
<style type="text/css">
    <!--
    .STYLE1 {
        color: #0033FF
    }
    -->
</style>
</head>
<body>
 <jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsVendorInfoDTO dto = (AmsVendorInfoDTO)request.getAttribute(QueryConstant.QUERY_DTO);
    String action = dto.getAct();
%>
<form name="mainFrm" method="POST">
<fieldset style="margin-left:0;height:450px">
<legend><span class="STYLE1">����ά�޹�˾ά��ҳ��</span></legend>
    <table width="50%" align="center">
        <tr>
            <td width="10%" align="right" height="22">ά�޹�˾���ƣ�</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="vendorName" class="noEmptyInput" style="width:100%" value="<%=StrUtil.nullToString(dto.getVendorName())%>">
            </td>
        </tr>
        <tr>
            <td width="10%" align="right" height="22">ά�޹�˾��ַ��</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="address" style="width:100%" value="<%=StrUtil.nullToString(dto.getAddress())%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">��ϵ�ˣ�</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="contact" style="width:100%" value="<%=dto.getContact()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">�绰��</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="phone" style="width:100%"  value="<%=dto.getPhone()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">���棺</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="fax" style="width:100%"  value="<%=dto.getFax()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">���̣�</td>
            <td width="60%" align="left" height="22">
                <select name="vendorId" class="noEmptyInput" style="width:100%"><%=request.getAttribute(WebAttrConstant.SPARE_VENDOR_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="50%" align="center" height="22" colspan="5">
                <img src="/images/eam_images/save.jpg" alt="����" onClick="do_SaveData(); return false;">&nbsp;
<%
    if (!action.equals(WebActionConstant.NEW_ACTION)) {
%>
                <img src="/images/eam_images/delete.jpg" alt="ɾ��" onClick="do_DeleteData(); return false;">&nbsp;
<%
    }
%>
                <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="do_Back(); return false;"></td>
        </tr>
    </table>
</fieldset>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="vendorCode" value="<%=dto.getVendorCode()%>">
    <input type="hidden" name="isExist">
</form>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script>
function do_SaveData() {
    var fieldNames = "vendorName;vendorId";
    var fieldLabels = "ά�޹�˾����;����";
    var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
    do_verifyworkNo();
    if (isValid) {
        if (mainFrm.vendorCode.value != "") {
            if (mainFrm.vendorId.value != "<%=dto.getVendorId()%>" || mainFrm.vendorName.value != "<%=dto.getVendorName()%>") {
                if (mainFrm.isExist.value == "Y") {
                    alert("�ó��̶�Ӧ��ά�޹�˾�Ѵ��ڣ������ٴ���ӣ�");
                    return;
                }
            }
            action = "<%=WebActionConstant.UPDATE_ACTION%>";
        } else {
            if (mainFrm.isExist.value == "Y") {
                alert("�ó��̶�Ӧ��ά�޹�˾�Ѵ��ڣ������ٴ���ӣ�");
                return;
            }
            var action = "<%=WebActionConstant.CREATE_ACTION%>";
        }
		mainFrm.act.value = action;
		mainFrm.action = "/servlet/com.sino.ams.spare.servlet.BjVendorServlet";
		mainFrm.submit();
	}
}

function do_DeleteData() {
    var vendorCode = mainFrm.vendorCode.value;
    if (confirm("ȷ��ɾ���𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
        mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.spare.servlet.BjVendorServlet?vendorCode=" + vendorCode;
        mainFrm.submit();
    }
}


function do_Back() {
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	mainFrm.action = "/servlet/com.sino.ams.spare.servlet.BjVendorServlet";
	mainFrm.submit();
}

var xmlHttp;
function do_verifyworkNo() {
    var url = "";
    createXMLHttpRequest();
    url = "/servlet/com.sino.ams.spare.servlet.BjVendorServlet?act=verifyworkNo&vendorId=" + document.mainFrm.vendorId.value+"&vendorName="+document.mainFrm.vendorName.value;
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
</script>