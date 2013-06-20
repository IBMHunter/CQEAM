<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.plan.dto.AmsWorkPlanDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%--
  Created by IntelliJ IDEA.
  User: yuyao
  Date: 2007-9-20
  Time: 10:51:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>�����ƻ���ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
</head>
<script type="text/javascript">
    printTitleBar("�����ƻ���ϸ��Ϣ")
</script>
<body topMargin=0 leftMargin=0>
<jsp:include page="/message/MessageProcess"/>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    SfUserDTO user = (SfUserDTO) session.getAttribute(WebConstant.USER_INFO);
    AmsWorkPlanDTO dto = (AmsWorkPlanDTO) request.getAttribute(WebAttrConstant.WORK_PLAN_DTO);
    String action = parser.getParameter("act");

%>
<form action="/servlet/com.sino.ams.plan.servlet.AmsWorkPlanServlet" name="mainForm" method="post">
    <table border="0" width="100%">
        <tr>
            <td align="right" width="5%">�ƻ�����</td>
            <td width="35%"><input name="planName" type="text" class="noEmptyInput" value="<%=dto.getPlanName()%>"
                                   style="width:46%"></td>
        </tr>
        <tr>
            <td align="right" width="5%">��������</td>
            <td width="35%"><textarea rows="10" cols="55" name="planDesc"><%=dto.getPlanDesc()%>
            </textarea></td>
        </tr>
        <tr>
            <td align="right" width="5%">ִ����</td>
            <td width="15%"><input type="text" name="executeUserName" readonly class="noEmptyInput"
                                   value="<%=dto.getExecuteUserName()%>"><a
                    class="linka"
                    style="cursor:'hand'"
                    onclick="do_selectName();">[��]</a>
            </td>


        </tr>
        <tr>
            <td align="right" width="5%">ִ��ʱ��</td>
            <td width="15%"><input type="text" name="executeTime" value="<%=dto.getExecuteTime()%>" style="width:20%"
                                   title="���ѡ������" readonly class="input2" onclick="gfPop.fPopCalendar(executeTime)">
                <img src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fPopCalendar(executeTime)"></td>

        </tr>
        <tr>
            <td align="right"></td>
            <td align="left">

                <img src="/images/eam_images/save.jpg" alt="����ƻ�"
                     onClick="do_savePlan(); return false;">

                <%-- <img src="/images/eam_images/revoke.jpg" alt="�����ƻ�" onclick="do_back();return false">--%>
                <%
                    if (dto.getPlanId().equals("")) {


                %>
                <img src="/images/eam_images/close.jpg" alt="�ر�"
                     onclick="do_concel();return false;">

                <%
                } else {
                %>
                <img src="/images/eam_images/ok.jpg" alt="��ɹ����ƻ�" onclick="do_ok()">
                <img src="/images/eam_images/back.jpg" alt="����"
                     onclick="do_concel();return false;">
                <%
                    }
                %>
            </td>
        </tr>
    </table>

    <input type="hidden" name="act" value="<%=action%>">
    <input type="hidden" name="executeUser" value="<%=dto.getExecuteUser()%>">
    <input type="hidden" name="planId" value="<%=dto.getPlanId()%>">
    <input type="hidden" name="planStatus" value="<%=dto.getPlanStatus()%>">

</form>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script type="text/javascript">
    function do_savePlan() {
        var fieldNames = "planName;planDesc;executeUserName;executeTime";
        var fieldLabels = "�ƻ�����;��������;ִ����;ִ��ʱ��";
        var validateType = EMPTY_VALIDATE;
        var isValid = formValidate(fieldNames, fieldLabels, validateType);
        if (isValid) {
            with (mainForm) {
                if (planId.value == "") {
                    act.value = "<%=WebActionConstant.CREATE_ACTION%>";
                } else {
                    if (planStatus.value == 1) {
                        act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
                    } else {
                        alert("ֻ���ڼƻ�״̬Ϊ�½���ʱ��������޸ģ�");
                    }
                }
                action = "/servlet/com.sino.ams.plan.servlet.AmsWorkPlanServlet";
                submit();
            }
        }
    }

    function do_concel() {
        with (mainForm) {
            window.close();
            act.value = "<%=WebActionConstant.QUERY_ACTION%>";
            submit();
        }

    }
    function do_ok() {
        var cuser = mainForm.executeUser.value;
        var duser = "<%=user.getUserId()%>";
        if (cuser == duser) {
            if (mainForm.planStatus.value != 1) {
                alert("��ȷ����ļƻ�״̬���½���ֻ���½��������!!");
            } else
            {
                var planId = mainForm.planId.value;
                mainForm.act.value = "ok";
                mainForm.action = "/servlet/com.sino.ams.plan.servlet.AmsWorkPlanServlet?planId=" + planId;
                alert(planId);
                mainForm.submit();
            }
        } else {
            alert("��ȷ��ִ����!");
        }
    }
    function do_selectName() {
        var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.LOOK_UP_USER%>";
        var popscript = "dialogWidth:50;dialogHeight:30;center:yes;status:no;scrollbars:no";
        var users = window.showModalDialog(url, null, popscript);
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                user = users[i];
                dto2Frm(user, "mainForm");
            }
        }
    }
    function do_back() {
        var planId = mainForm.planId.value;
        mainForm.act.value = "repeal";
        mainForm.action = "/servlet/com.sino.ams.plan.servlet.AmsWorkPlanServlet?planId=" + planId;
        alert(planId);
        mainForm.submit();
    }
</script>