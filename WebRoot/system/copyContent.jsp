<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant" %>
<%--
    ���ĳ��͠�̬
  Created by wwb.
  User: demo
  Date: 2006-12-21
  Time: 11:56:30
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>��ʾ��̬</title>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <link href="/WebLibary/css/main.css" rel="stylesheet" type="text/css"/>
</head>

<body topmargin=0 leftmargin=0>
<script>
    printTitleBar("������ʾ��¼");
</script>
<table width=100%  class="tbborder">
    <tr class=headerTable>
        <td align=center>��ʾ��</td>
        <td align=center>��ʾ����</td>
        <td align=center>��ʾʱ��</td>
        <td align=center>��ʾ��̬</td>
    </tr>
    <%
        RowSet rs = (RowSet) request.getAttribute(WebAttrConstant.SINOFLOW_STATUS);
        if (rs != null && !rs.isEmpty()) {
            for (int i = 0; i < rs.getSize(); i++) {
                Row row = rs.getRow(i);

    %>
    <tr class="nohandTr">
        <td align=left><%=row.getValue("USERNAME")%>
        </td>
        <td align=left><%=row.getValue("DEPT_NAME")%></td>
        <td align=center><%=row.getValue("COMPLETE_DATE")%>
        </td>
        <td align=left><%=row.getValue("STATUS")%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>