<%@ page contentType="text/html; charset=gb2312" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/WebLibary/cms_css/cms_css.css" rel="stylesheet" type="text/css"/>
<title>��ȷ��</title>
<style type="text/css">
<!--
.STYLE1 {font-size: 12px}
-->
</style>
</head>
<body onbeforeunload="doUnload()" >
<form id="form1" name="getPriority" method="post" action="">
    <script type="text/javascript">
        function do_close() {
            window.returnValue = "ok";
            window.close();
        }

        function do_cancel() {
            window.returnValue = "";
            window.close();
        }

        function doUnload() {
            if(!window.returnValue)
              window.returnValue = "";
        }
        
    </script>
  <table border="0">
  <tr align="top">
  <td width="84%" align="left">
  <p class="STYLE1">���������������, �Ƿ���?</p>  </td>
  <td width="16%" align="left">
  <input type="button" class="but2" name="Submit" value="ȷ��" onClick="do_close()">
  </td>
  </tr>
  <tr align="top">
  <td align="left"><span class="STYLE1">�簴��ȷ������Ť, �������������, ϵͳ�鵵; �簴��ȡ������Ť, ���ص������������, �����Լ�������</span>  </td>
  <td align="left">
  <input type="button" class="but2" name="Cancel" value="ȡ��" onClick="do_cancel()">
  </td>
  </tr>
  </table>
</form>
</body>
</html>
