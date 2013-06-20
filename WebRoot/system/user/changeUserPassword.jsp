<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>

<%
String allResName = (String) request.getAttribute( WebAttrConstant.ALL_RES_NAME );
if( null == allResName ){
	allResName = "������Ϣά��";
}
%>
<%--
  User: Zyun
  Date: 2008-1-11
  Time: 14:50:00
--%>
<html>
<head>
    <title>������Ϣά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
</head>
<script type="text/javascript">
     printTitleBar("<%= allResName %>");
</script>

<body leftmargin="0" topmargin="0" onload="do_Init()" onkeydown="autoExeFunction('do_submit()');">
<%
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
	SfUserDTO sfuserDto = (SfUserDTO) request.getAttribute(WebAttrConstant.USER_ATTR);
%>
<form action="/servlet/com.sino.ams.system.user.servlet.ChangeUserPasswordServlet" method="post" name="mainFrm">
    <jsp:include page="/message/MessageProcess"/>
    <input type="hidden" name="act">
    <input type="hidden" name="name" value="">
    <input type="hidden" name="userId" value="<%=user.getUserId()%>">
    <table width="81%" border="0" align="center" class="">
        <tr>
            <td width="20%" align="right">��¼����</td>
            <td width="80%"><input type="text" name="loginName" class="input_style2" readonly style="width:80%"
                                   value="<%=user.getLoginName()%>">
                <br><label id="retMsg" style="color:red"></label>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">�û�����</td>
            <td width="80%"><input type="text" name="userName" class="input_style2" readonly style="width:80%"
                                   value="<%=user.getUsername()%>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">�칫�绰��</td>
            <td width="80%"><input type="text" name = "officeTel" class="input_style1" size = "40" style = "width:80%"
                                   value="<%=sfuserDto.getOfficeTel()%>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">������룺</td>
            <td width="80%"><input type="text" name = "fax" size = "40" class="input_style1" style = "width:80%"
                                   value="<%=sfuserDto.getFax() %>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">�ƶ��绰��</td>
            <td width="80%"><input type="text" name = "mobilePhone" size = "40" class="input_style1" style = "width:80%"
                                   value="<%=sfuserDto.getMobilePhone()%>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">E-mail��</td>
            <td width="80%"><input type="text" name = "email" size = "40" class="input_style1" style = "width:80%"
                                   value="<%=sfuserDto.getEmail() %>">
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td width="20%" align="right">����������ڣ�</td>--%>
            <%--<td width="80%">--%>
            	<%--<input  readonly name="passwordDate" style="width:80%" class="input_style2" value="<%=StrUtil.nullToString(sfuserDto.getPasswordOverdue()) %>" onclick = "gfPop.fPopCalendar(passwordDate)"><img src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fPopCalendar(passwordDate)">--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td width="20%" align="right">ԭ���룺</td>
            <td width="80%"><input type="password" readonly class="readonlyInput" name="oldPswd" style="width:80%" value = "<%=sfuserDto.getPassword()%>"><font color="red">*</font></td>
        </tr>
        <tr>
            <td width="20%" align="right">�����룺</td>
            <td width="80%"><input type="password" class="input_style1" value = "<%=sfuserDto.getPassword()%>" name="password" style="width:80%"
                                   onblur="checkPswd(this);"  value = ""><font color="red">*</font>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right"></td>
              <td width="80%">
                <font color="red">*���볤��Ӧ��С��8λ,������20λ,����ӦΪ���ֺ���ĸ�����</font>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">ȷ�����룺</td>
            <td width="80%"><input type="password" class="input_style1" value = "<%=sfuserDto.getPassword()%>" name="password" style="width:80%"  value = ""><font color="red">*</font></td>
        </tr>
        <tr>
            <td width="20%" align="right">�Ƿ���Ҫ�������ѣ�</td>
            <td width="80%"><select class="select_style1" name="isSms" style="width:80%"><option value="Y">��</option><option value="N" <%if("N".equals(sfuserDto.getIsSms())){%>selected<%}%>>��</option></select></td>
        </tr>
    </table>
    <p align="center"><a style="cursor:'hand'" onclick="do_submit();"><img src="/images/eam_images/ok.jpg" alt="ȷ��"></a></p>
</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">
    var loginNameWrong = false;
    function do_submit() {
        var password = document.getElementsByName("password");
        if (isEmpty(password[0].value) || isEmpty(password[1].value)) {
            alert("���������룡");
            return;
        }
        if (password[0].value != password[1].value) {
            alert("�����������벻һ�£�")
            return;
        }
        if (mainFrm.officeTel.value != "" && !isTelNumber(mainFrm.officeTel.value)) {
        alert("��������ȷ�İ칫�绰��")
        mainFrm.officeTel.focus();
        return;
    	}
	    if (mainFrm.fax.value != "" && !isTelNumber(mainFrm.fax.value)) {
	        alert("��������ȷ�Ĵ�����룡")
	        mainFrm.fax.focus();
	        return;
	    }
	    if (mainFrm.mobilePhone.value != "" && !isMobile(mainFrm.mobilePhone.value)) {
	        alert("��������ȷ���ƶ��绰��");
	        mainFrm.mobilePhone.focus();
	        return;
	    }
	    if (mainFrm.email.value != "" && !isEmail(mainFrm.email.value)) {
	        alert("��������ȷ��email��ַ��");
	        mainFrm.email.focus();
	        return;
	    }
	        document.mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
	        document.mainFrm.submit();
	}
    function do_Init() {
        document.oncontextmenu = function() {
            return false;
        }
    }
    function do_loginName_verify() {
        var loginName = document.mainFrm.loginName.value
        if (!isEmpty(loginName)) {
            var xmlhttp = createXMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState == 4) {
                    if (xmlhttp.status == 200) {
                        if (xmlhttp.responseText == "0") {
                            document.getElementById("retMsg").innerText = "�õ�¼����ϵͳ�в�����";
                            loginNameWrong = true;
                        } else if (xmlhttp.responseText == "1") {
                            document.getElementById("retMsg").innerText = "�õ�¼����Ч";
                            loginNameWrong = false;
                        } else if (xmlhttp.responseText == "2") {
                            document.getElementById("retMsg").innerText = "���û�ΪMIS�û������޷��ڱ�ϵͳ�޸�������";
                            loginNameWrong = true;
                            document.mainFrm.loginName.focus();
                        } else if (xmlhttp.responseText == "3") {
                            document.getElementById("retMsg").innerText = "���޷��޸����������û�������";
                            loginNameWrong = true;
                            document.mainFrm.loginName.focus();
                        } else {
                            document.getElementById("retMsg").innerText = "��֤��¼������������һ��";
                            loginNameWrong = true;
                            document.mainFrm.loginName.focus();
                        }
                    } else {
                        alert(xmlhttp.status);
                    }
                }
            }
            xmlhttp.open("post", "/servlet/com.sino.ies.systemsetup.user.servlet.UserVerifyServlet", false);
            xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            xmlhttp.send("loginName=" + loginName);
        } else {
            loginNameWrong = true;
            document.getElementById("retMsg").innerText = "��¼������Ϊ��";
        }
    }
    function checkPswd(pswd) {
        var Expression = /(?=[!-~]{8,20})(?=[!-~]*[^0-9]+)(?=[!-~]*\d+)/;
        var objExp = new RegExp(Expression);
        if (!isEmpty(pswd.value) && !objExp.test(pswd.value)) {
            alert("���븴�ӶȲ�����Ҫ����������д��");
            pswd.focus()
        }
    }
</script>