<%@ page import="com.sino.ams.yj.dto.AmsYjPlanDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<HTML>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>Ӧ��Ԥ����ϵ��ϸ��Ϣ</title>
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
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>

<body>
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsYjPlanDTO amsYjPlanDTO = (AmsYjPlanDTO) request.getAttribute("AMS_YJ_PLAN");
    boolean isNew = StrUtil.isEmpty(amsYjPlanDTO.getPlanName());
%>
<script type="text/javascript">
    printTitleBar("Ӧ��Ԥ����ϵ��ϸ��Ϣ");
</script>

<form name="mainFrm" action="/servlet/com.sino.ams.yj.servlet.AmsYjPlanServlet" method="post">
<input type="hidden" name="show" value="show">
<input type="hidden" name="act" value="">
<input type="hidden" name="planId" value="<%=amsYjPlanDTO.getPlanId()%>">
<input type="hidden" name="isExist">
<table border="0" width="100%" id="table1">
<tr>
    <td width="6%" colspan="3" align="right">��˾���ƣ�</td>
    <td width="11%"><select class="select_style1" style="width:50%" name="organizationId"><%=amsYjPlanDTO.getOrganizationOption()%>
    </select></td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">Ԥ�����ƣ�</td>
    <td width="35%" align="left" height="22"><input class="input_style1" type="text" name="planName" size="40"
                                                    style="width:50%" value="<%=amsYjPlanDTO.getPlanName()%>">&nbsp;<font color="red">*</font> </td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">Ԥ������</td>
    <td width="35%" align="left" height="22">
        <select name="planLevel" class="select_style1" style="width:50%">
            <option value="����Ԥ��" <%=amsYjPlanDTO.getPlanLevel().equals("����Ԥ��") ? "selected" : ""%>>����Ԥ��</option>
            <option value="ר��Ԥ��" <%=amsYjPlanDTO.getPlanLevel().equals("ר��Ԥ��") ? "selected" : ""%>>ר��Ԥ��</option>
            <option value="Ӧ������" <%=amsYjPlanDTO.getPlanLevel().equals("Ӧ������") ? "selected" : ""%>>Ӧ������</option>
        </select>
    </td>

</tr>
<tr>
    <td width="6%" colspan="3" align="right">ʡ����У�</td>
    <td width="35%" align="left" height="22">
        <select name="proCity" class="select_style1" style="width:50%">
            <option value="ʡ" <%=amsYjPlanDTO.getProCity().equals("ʡ") ? "selected" : ""%>>ʡ</option>
            <option value="����" <%=amsYjPlanDTO.getProCity().equals("����") ? "selected" : ""%>>����</option>
        </select>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">Ԥ����ţ�</td>
    <td width="35%" align="left" height="22">
        <input type="text" name="planNo" size="40"
             class="input_style1" style="width:50%" value="<%=amsYjPlanDTO.getPlanNo()%>">
    </td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">Ԥ�����</td>
    <td width="35%" align="left" height="22">
        <select name="planType" class="select_style1" style="width:50%">
            <option value="��Ȼ�ֺ��ࣨA��" <%=amsYjPlanDTO.getPlanType().equals("��Ȼ�ֺ��ࣨA��") ? "selected" : ""%>>��Ȼ�ֺ��ࣨA��</option>
            <option value="�¹������ࣨB��" <%=amsYjPlanDTO.getPlanType().equals("�¹������ࣨB��") ? "selected" : ""%>>�¹������ࣨB��</option>
            <option value="���������¼���C��" <%=amsYjPlanDTO.getPlanType().equals("���������¼���C��") ? "selected" : ""%>>���������¼���C��</option>
            <option value="��ᰲȫ�¼���D��" <%=amsYjPlanDTO.getPlanType().equals("��ᰲȫ�¼���D��") ? "selected" : ""%>>��ᰲȫ�¼���D��</option>
            <option value="�ش��ࣨE��" <%=amsYjPlanDTO.getPlanType().equals("�ش��ࣨE��") ? "selected" : ""%>>�ش��ࣨE��</option>
        </select>
    </td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">ӡ��ʱ�䣺</td>
    <td width="35%" align="left" height="22">
        <input class="input_style1" readonly name="printDate" style="width:50%" value="<%=StrUtil.nullToString(amsYjPlanDTO.getPrintDate())%>"><img
            src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fPopCalendar(printDate)">

    </td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">֪����Χ(ְλ/��λ)��</td>
    <td width="35%" align="left" height="22"><input type="text" name="knowPost" size="40"
                                                    class="input_style1"  style="width:50%" value="<%=amsYjPlanDTO.getKnowPost()%>"></td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">֪���˵�������</td>
    <td width="35%" align="left" height="22"><input type="text" name="quantity" size="40"
                                                    class="input_style1"  style="width:50%" value="<%=amsYjPlanDTO.getQuantity()%>"></td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">Ԥ�����������˵ĸ�λ/ְλ��</td>
    <td width="35%" align="left" height="22"><input type="text" name="leaderPost" size="40"
                                                    class="input_style1"  style="width:50%" value="<%=amsYjPlanDTO.getLeaderPost()%>"></td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">��Ԥ���Ƿ���������</td>
    <td width="35%" align="left" height="22">
        <select name="isDrill" class="select_style1" style="width:50%">
            <option value="��" <%=amsYjPlanDTO.getIsDrill().equals("��") ? "selected" : ""%>>��</option>
            <option value="��" <%=amsYjPlanDTO.getIsDrill().equals("��") ? "selected" : ""%>>��</option>
        </select>
    </td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right">��ע��</td>
    <td width="35%" align="left" height="22"><input type="text" name="remark" size="40"
                                                    class="input_style1" style="width:50%" value="<%=amsYjPlanDTO.getRemark()%>"></td>
</tr>
<tr>
    <td width="6%" colspan="3" align="right"></td>
    <td width="35%" align="left" height="22">
        <img src="/images/eam_images/save.jpg" alt="�������" onClick="do_Save(); return false;">&nbsp;
        <%
            if (!amsYjPlanDTO.getPlanName().equals("")) {
        %>
        <img src="/images/eam_images/delete.jpg" alt="ɾ��" onClick="do_Delete(); return false;">&nbsp;
        <%
            }
        %>
        <img src="/images/eam_images/back.jpg" alt="���ȡ��" onClick="do_Back(); return false;"></td>
</tr>

</table>
</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script>
    function do_Save() {
        var fieldNames = "planName";
        var fieldLabels = "Ԥ������";
        var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);

//        do_verifyItemName();       �ж����������Ƿ��ظ���������Ҫ��֤��ֻ��Ҫ���ø÷����������ʵ������޸�

        if (isValid) {
            if (mainFrm.isExist.value == "Y") {
                alert("��Ԥ�������Ѵ��ڣ�");
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
        var planId = document.mainFrm.planId.value;
        if (confirm("ȷ��ɾ����������Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
            document.mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
            document.mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjPlanServlet?drillId=" + planId;
            document.mainFrm.submit();
        }
    }


    function do_Back() {
        document.mainFrm.planId.value = "";
        document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjPlanServlet";
        document.mainFrm.submit();
    }

    var xmlHttp;
    function do_verifyItemName() {
        var url = "";
        createXMLHttpRequest();
        url = "/servlet/com.sino.ams.yj.servlet.AmsYjPlanServlet?act=verifyTeamName&planId=" + document.mainFrm.planId.value;
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