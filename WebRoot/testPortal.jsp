<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
  <head><title>����PORTAL��¼</title>
  <link rel="stylesheet" type="text/css" href="/style/css/main.css">
  </head>
  <%
      String strPortalUsername= "DUJUANJUAN";   //��¼��
  	  String oatodoUrl = ""; //����·��
  	  String etsLink =  "http://localhost:8080/portalLogin.jsp";  //�ʲ����Ի���
  	  
  	  //10.87.9.82
  	  //etsLink =  "http://localhost:80/portalLogin.jsp"; 
  %>
  <body>
  current user name : <%=strPortalUsername%>
  <form name="frmJump" method="post" action="<%=etsLink%>">
    <input type="hidden" name="source" value="oa">
    <input type="hidden" name="is_td" value="Y">
    <input type="hidden" name="todo_url" value="<%= oatodoUrl %>">
	<input type="hidden" name="emp_num" value="<%=com.sino.base.util.SinoEncryptor.encode("SNB_EAM_SSO", strPortalUsername)%>">
</form>

  <input type="button" value="Login" onclick="do_login();"> </body>
<script type="text/javascript">
    function do_login(){
        frmJump.submit();
    }
</script>
</html>