<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.system.trust.dto.AmsMaintainPeopleDTO" %>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>��ά��˾��Ա��ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
</head>
<body>
<jsp:include page="/message/MessageProcess"/>
<%
    AmsMaintainPeopleDTO mainCopUsr = (AmsMaintainPeopleDTO) request.getAttribute(WebAttrConstant.MAINTAIN_CORP_USR_ATTR);
    String userId=mainCopUsr.getUserId();
    RequestParser parser = new RequestParser();
    parser.transData(request);
    
%>
<form name="mainFrm" method="POST">
    <script type = "text/javascript">
        printTitleBar("��ά��˾��Ա��ѯ")
    </script>
    <table border="0" width="100%" id="table1">
        <input type="hidden" name="act" value="<%=parser.getParameter("act")%>">
        <tr>
            <td width="25%" align="right" height="22">��&nbsp&nbsp����</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="userName" size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%=mainCopUsr.getUserName() %>"></td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font> </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�̶��绰��</td>
            <td width="50%" align="left" height="22" colspan="3">
            <input type="text" name="userTelephone" size="40"  class="input_style1" style="width:100%"  value="<%=mainCopUsr.getUserTelephone()  %>">
            </td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�ƶ��绰��</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="userMobilePhone"
                                                                        size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%= mainCopUsr.getUserMobilePhone()%>"
                    ></td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">EMAIL��</td>
            <td width="50%" align="left" height="22" colspan="3">
            <input type="text" name="email" size="40"  class="input_style1"  style="width:100%"  value="<%= mainCopUsr.getEmail()%>"></td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">������ά��˾��</td>
            <td width="50%" align="left" height="22" colspan="3">
                <select name="companyId"  class="select_style1"   style="width:100%"><%=request.getAttribute(WebAttrConstant.MAINTAIN_CORP_OPTION)%></select>
            </td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="100%" align="center" height="22" colspan="5">
                <img src="/images/eam_images/save.jpg" alt="����" onClick="do_SaveMaintainCompany(); return false;">&nbsp;
                <img src="/images/eam_images/delete.jpg" alt="ɾ��" onClick="do_DeleteMaintainCompany(); return false;">&nbsp;
                <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="do_Back(); return false;"></td>
        </tr>

    </table>

    <input type="hidden" name="userId" value="<%=userId %>">
</form>
</body>

<script>
    function do_SaveMaintainCompany() {
        //����У�� -- �ǿ�
        var fieldNames = "userName;userMobilePhone;companyId";
        var fieldLabels = "����;�ƶ��绰;������ά��˾";
        var validateType = EMPTY_VALIDATE;
        var isValid = formValidate(fieldNames, fieldLabels, validateType);
        var userMobilePhone = document.mainFrm.userMobilePhone.value;
        if (!isPositiveNumber(userMobilePhone)) {
            alert("�ƶ��绰����Ϊ������");
            document.mainFrm.userMobilePhone.focus();
            return false;
        }
        if (userMobilePhone.length != 11) {
            alert("�ƶ��绰����Ϊ11λ��");
            document.mainFrm.userMobilePhone.focus();
            return false;
        }
        if (isValid) {

            //����У�� -- ����
            fieldNames = "userName;userMobilePhone";
            fieldLabels = "0$����$20;0$�ƶ��绰$11";
            validateType = LENGTH_VALIDATE;
            isValid = formValidate(fieldNames, fieldLabels, validateType);
            if (isValid) {

                //����У�� -- ����
                fieldNames = "userTelephone;userMobilePhone";
                fieldLabels = "�̶��绰;�ƶ��绰";
                validateType = NUMBER_VALIDATE;
                isValid = formValidate(fieldNames, fieldLabels, validateType);
                
                 //����У�� --EMAIL
                fieldNames = "email";
                fieldLabels = "�����ʼ�";
                validateType = EMAIL_VALIDATE;
                isValid = formValidate(fieldNames, fieldLabels, validateType);

                if (isValid) {
                    var action = "<%=WebActionConstant.CREATE_ACTION%>";
                    if (mainFrm.userId.value!='') {
                        action = "<%=WebActionConstant.UPDATE_ACTION%>";
                    }
                    mainFrm.act.value = action;
                    mainFrm.action = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainPeopleServlet";
                    mainFrm.submit();
                }
            }
        }
    }

    function do_DeleteMaintainCompany() {

        if (confirm("ȷ��ɾ���ý�ɫ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
            mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
            mainFrm.action = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainPeopleServlet";
            mainFrm.submit();
        }
    }


    function do_Back() {

        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainPeopleServlet";
        mainFrm.submit();
    }

</script>
</html>