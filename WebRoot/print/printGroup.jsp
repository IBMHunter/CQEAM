<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-1-25
  Time: 11:58:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>


<html>
<head><title>��ѡ�����</title>
    <meta content="text/html; charset=GBK" http-equiv=Content-Type>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript">
//        function init() {
//            var mySelect = document.all('mySelect');
//            var optionArr = window.dialogArguments;
//            if (optionArr != null) {
//                for (i = 0; i < optionArr.length; i++) {
//                    index = mySelect.options.length;
//                    mySelect.options[index] = new Option(optionArr[i].text, optionArr[i].value);
//                    mySelect.options[index].selected = optionArr[i].selected;
//                }
//            }
//        }

        function do_cancel() {
            window.close();
        }

        function do_select() {
//            var returnValue = getSelectValue('mySelect', ';');
            var returnValue = document.form1.mySelect.value;
            window.returnValue = returnValue;
            window.close()
        }

        function do_check() {
            if (event.keyCode == 13) {
                do_select();
            } else if (event.keyCode == 27) {
                do_cancel();
            }
        }

        function do_submit(){
//            alert("1111111");
         var applyGroup = document.form1.mySelect.value;
//           alert("1111111");
           if (applyGroup!=="") {
//               alert(document.form1.mySelect.value);

//               alert("2222222222");
               var url = "/servlet/com.sino.ams.print.servlet.AmsBarcodePrintServlet?applyGroup=" + applyGroup;
               var winStyle = "height=530px,width=850px,top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=yes,help=no";
               window.open(url, "_blank", winStyle);
               window.close();
           } else {
               alert("��ѡ��������");
           }
        }
    </script>
</head>

<body bgColor="#E4E4E4" bottomMargin=0 leftMargin=10.5 topMargin=10  onkeydown="do_check();">
<form name="form1">
    <table width="100%" border="0">
        <tr valign="top">
            <td colspan="2" height="191">
                <select name="mySelect" size="12" style="width:250px" >
                    <%=request.getAttribute(WebAttrConstant.PRINT_OPTION)%>
                </select>
            </td>
            <td width="10%" height="191">
                <p>
                    <input type="button" name="Submit" value="ȷ��" onClick="do_submit()"><BR><br>
                    <input type="button" name="Submit2" value="ȡ��" onClick="do_cancel()">
                </p>
            </td>
        </tr>
    </table>
</form>
</BODY>
</HTML>