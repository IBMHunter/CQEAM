<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant" %>
<%--
    �����������
  Created by wwb.
  User: demo
  Date: 2006-12-21
  Time: 11:56:30
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>�������</title>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <link href="/WebLibary/css/main.css" rel="stylesheet" type="text/css"/>
</head>

<body topmargin=0 leftmargin=0  >
<script>
    printTitleBar("�������");
    var ArrAction1 = new Array(true, "�ر�", "close.gif", "�ر�", "do_Close()");
    var ArrActions = new Array(ArrAction1);
    var ArrSinoViews = new Array();
    var ArrSinoTitles = new Array();
    printToolBar();

</script> 
<table style="display:block;width:96%;margin-left:2%;margin-right:2%">
    <tr>
        <td width="98%" class="font-lan"><strong>��ǰ������</strong></td>
    </tr>
</table>
<table id="currPersonTab" border="1" bordercolor="#46B5EC" cellspacing="0" style="display:block;width:100%;">
    <tr class="headerTable">
        <td align=center width="10%">������</td>
        <td align=center width="25%">������</td>
        <td align=center width="20%"></td>
        <!--<td align=center width="15%"></td>-->
        <td align=center width="30%"></td>
        <td align=center width="15%"></td>
    </tr>
    <%
        RowSet rs = (RowSet) request.getAttribute(WebAttrConstant.SINOFLOW_CURSTATUS);
        if (rs != null && !rs.isEmpty()) {
         	String sac9 = "";
            for (int i = 0; i < rs.getSize(); i++) {
                Row row = rs.getRow(i);
                sac9 = row.getValue("SFACT_APPL_COLUMN_9").toString();
//                if( sac9 != "" ){
    %>
    <tr class="nohandTr">
        <td align=left width="10%"><%=row.getValue("SFACT_COMPLETE_USER")%></td>
        <td align=left width="25%"><%=row.getValue("SFACT_TASK_GROUP")%></td>
        <td align=center width="20%"></td>
        <!--<td align=center width="15%"></td>-->
        <td align=center width="30%"></td>
        <td align=center width="15%"></td>
    </tr>
    <%
//    			}
            }
        }
    %>
</table>
<table style="display:block;width:100%;">
    <tr onclick="doShowOrHiddenOpinion()" style="cursor:pointer">
        <td align="right" width="2%"></td>
        <td width="98%" class="font-lan"><strong>����б�</strong></td>
    </tr>
</table>
<table id="opinionTab" border="1" bordercolor="#46B5EC" cellspacing="0" style="display:block;width:100%;">
    <tr class=headerTable>
        <td align=center width="10%">������</td>
        <td align=center width="25%">������</td>
        <td align=center width="20%">������̬</td>
        <!--<td align=center width="15%">ѡ��Q��</td>-->
        <td align=center width="30%">���</td>
        <td align=center width="15%">����ʱ��</td>
    </tr>
    <%
        rs = (RowSet) request.getAttribute(WebAttrConstant.SINOFLOW_STATUS);
        if (rs != null && !rs.isEmpty()) {
         	String sac9 = "";
            for (int i = 0; i < rs.getSize(); i++) {
                Row row = rs.getRow(i);
//                sac9 = row.getValue("SFACT_APPL_COLUMN_9").toString();
                sac9 = row.getValue("SFACT_OPINION") .toString();
//                if( sac9 != "" ){
                if(sac9.indexOf("[ͬ��]") == 0) {
                    sac9 = sac9.replace("[ͬ��]", "<font color=blue>[ͬ��]</font>");
                } else if(sac9.indexOf("[��ͬ��]") == 0) {
                    sac9 = sac9.replace("[��ͬ��]", "<font color=red>[��ͬ��]</font>");
                }
                String user;
                if(row.getStrValue("SFACT_SIGN_USER").equals(row.getStrValue("SFACT_COMPLETE_USER"))) {
                    user = row.getStrValue("SFACT_COMPLETE_USER");
                } else {
                    user = row.getStrValue("SFACT_SIGN_USER") + "(" + row.getStrValue("SFACT_COMPLETE_USER") + "����)";
                }
    %>
    <tr class="nohandTr">
        <td align=left width="10%"><%=user%></td>
        <td align=left width="25%"><%=row.getValue("SFACT_TASK_GROUP")%></td>
        <td align=left width="20%"><%=row.getStrValue("SFACT_TASK_NAME")%></td>
        <!--<td align=left width="15%"><%=row.getStrValue("SFACT_FLOW_HINT")%></td>-->
        <td align=left width="30%"><%= sac9 %></td>
        <td align=center width="15%"><%=row.getValue("SFACT_COMPLETE_DATE")%></td>
    </tr>
    <%			
//    			}
            }
        }
    %>
</table>
</body>
</html>
<script type="text/javascript">
function do_Close(){
    self.close();
}
</script>