<%--
  User: zhoujs
  Date: 2007-9-27
  Time: 22:17:51
  Function: ת��ر�ҳ�棬�ɴ��뷵�ز���
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<html>
<script type="text/javascript">
    <%
 String retValue=StrUtil.nullToString(request.getParameter("retValue"));
 if(!retValue.equals("")){
    if(retValue.equals("order")){%> //�����ύʱ���ռ�����ڰ����ύʱˢ��
      window.top.opener.document.forms[0].submit();
    <%

    }else if(retValue.equals("refresh")){ %>
     window.top.opener.location.reload();
    <%}else{
    %>
    window.returnValue = "<%=retValue%>";
    <%
           }
           }
    %>
    window.top.close();
</script>
</html>