<%@ page import="com.sino.flow.constant.FlowConstant" %>
<%--
  Created by wwb.
  User: demo
  Date: 2007-1-7
  Time: 17:05:59
  ����ʱ������������ҳ��
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>

<html>
<head>
    <title>����������</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
</head>
<body topmargin=0 leftmargin=0>
<form name="mainForm" action="">
    <table width=100% align="center">
        <tr>
            <td>
                <script>
                    printTitleBar("��������")
                </script>
            </td>
        </tr>
        <tr>
            <td align=left>
                <img src="/images/eam_images/ok.jpg" alt="ȷ��" onclick="javascript:add()">
            </td>
        </tr>
        <tr>
            <td>
                <textarea name="content" rows="4" style="width:100%"></textarea>
            </td>
        </tr>

    </table>
</form>
<script language="javascript">
	if(window.dialogArguments){
		mainForm.content.value = window.dialogArguments;
	}
			
	function add(){
		window.returnValue = document.mainForm.content.value;
		window.close();
	}
</script>
</body>

</html>