<%--
  Created by Herry.
  Date: 2008-7-22
  Time: 10:10:33
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>�״ε�¼ϵͳ�������ѹ���Ч�ڣ���������</title>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <style type="text/css">
        BODY, TD, INPUT {
            FONT-SIZE: 12px;
        }
    </style>
</head>
<%
    String loginName = request.getParameter("loginName");
%>
<body onload="document.getElementById('password').focus();" onkeydown="autoExeFunction('do_submit()');">

<form action="/servlet/com.sino.ams.system.user.servlet.FirstLoginServlet" name="mainForm" id="mainForm" method="post">
    <input type="hidden" name="loginName" value="<%=loginName%>">
    <table border="1" width="502" cellspacing="1" id="table1" style="border-collapse: collapse" bordercolor="#9FD6FF"
           bgcolor="#F2F9FF" align="center">
        <tr>
            <td>
                <table width="500" align="center">
                    <tr height="40">
                        <td colspan="3">�����״ε�¼ϵͳ�������ѹ���Ч�ڣ����޸����룺</td>
                    </tr>
                    <tr height="30">
                        <td align="right" width="30%">
                            �û�����
                        </td>
                        <td width="30%">
                            <%=loginName%>
                        </td>
                        <td width="40%"></td>
                    </tr>
                    <tr height="30">
                        <td align="right">
                            <font color="red">*</font>�����룺
                        </td>
                        <td>
                            <input type="password" name="password" onblur="checkPswd();">
                        </td>
                        <td><label id="label1" style="color:red"></label></td>
                    </tr>
                    <tr height="30">
                        <td align="right">
                            <font color="red">*</font>ȷ�����룺
                        </td>
                        <td>
                            <input type="password" name="cpassword" onblur="confirmPswd();">
                        </td>
                        <td><label id="label2" style="color:red;display:none">����������ĵ�¼���벻һ��</label></td>
                    </tr>
                    <tr height="30">
                        <td colspan="1" align="right"><input type="button" value="ȷ��" onclick="do_submit();"></td>
                        <td colspan="1" align="center"><input type="reset" value="����" ></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color:red" align="center">ע�����벻����Ϊ�ա�</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">
    var flag = "N";
    function checkPswd() {
        var pswd = mainForm.password.value;
        var label1 = document.getElementById("label1");
        if (pswd == "eamprd") {
            label1.innerText = "�����벻��Ϊ��ʼ����";
        } else {
            label1.innerText = "";
            var Expression = /(?=[!-~]{6,20})(?=[!-~]*[^0-9]+)(?=[!-~]*\d+)/;
            var objExp=new RegExp(Expression);
            if(objExp.test(pswd)){
                flag = "Y";
            }else{
                label1.innerText = "���볤�Ȳ�С��6λ,������20λ,����ӦΪ���ֺ���ĸ�����";
            }
        }
    }
    function confirmPswd() {
        var pswd = mainForm.password.value;
        var cpswd = mainForm.cpassword.value;
        if (pswd != cpswd) {
            document.getElementById("label2").style.display = "block";
        } else {
            document.getElementById("label2").style.display = "none";
        }
        window.setTimeout("confirmPswd();", 2000);
    }
    function do_submit() {
        if(flag == "Y"){
            var pswd = mainForm.password.value;
            var cpswd = mainForm.cpassword.value;
            if (pswd != "" && pswd == cpswd) {
                mainForm.submit();
            }
        }
    }

</script>
</html>