<%@ page contentType="text/html; charset=gb2312" language="java"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/WebLibary/css/main.css" rel="stylesheet" type="text/css"/>    
<title>��ȷ��</title>
</head>
<body>
<form id="form1" name="getPriority" method="post" action="">
<script type="text/javascript">
    function radioValue(radio) {
        var len = radio.length;
        for(var i = 0; i < len; i++) {
            if(radio[i].checked)
                return radio[i].value;
        }
        return "";
    }

    function do_close() {
        var priority = radioValue(document.getElementsByName("priority"));
        var message = document.getElementById("userMessage").value;
        window.returnValue = "{priority:'" + priority + "',message:'" + message + "'}";
        window.close();
    }

    function do_cancel() {
        window.returnValue = "";
        window.close();
    }
</script>
  <table width=100% border="0">
  <tr align="top">
  <td width="5%"></td>
  <td width="90%" align="left">
  <p>�ļ����ͽ����̶�:</p>
  </td>
  <td width="5%" align="left">
  <input type="button" name="Submit" value="ȷ��" onClick="do_close()">
  </td>
  </tr>
  <tr align="top">
  <td width="5%"></td>
  <td align="left">
  <p>
    <input type="radio" name="priority" value="0" checked/>
    ����
      <input type="radio" name="priority" value="1" />
      ƽ��
      <input type="radio" name="priority" value="2" />
      ����
      <input type="radio" name="priority" value="3" />
      �ؼ�  </p>
  </td>
  <td align="left">
  <input type="button" name="Cancel" value="ȡ��" onClick="do_cancel()">
  </td>
  </tr>
  <tr>
  <td width="5%"></td>
  <td>
  <br/>������Ϣ:<br/>
    <textarea name="userMessage" cols="40" rows="8"></textarea>
    <br />
  </td>
  </tr>
  </table>
</form>
</body>
</html>
