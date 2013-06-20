<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2007-9-20
  Time: 13:06:49
  Function:��������ص���Ϣ����ҳ��.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>�ص㵼�������Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/AppStandard.js"></script>
    <style type="text/css">
    .finput {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;padding-left:4px}
    .finput2 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:center;;padding-left:4px}
    .finput3 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:right;padding-right:4px;padding-left:4px}
    .fDtlInput{WIDTH:100%;border-style:solid; background-color:#F2F9FF;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:left;padding-left:4px};
    .finputNoEmpty {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:left;BACKGROUND-COLOR: #FFFF99;padding-left:4px};
    .finputNoEmpty2 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:center;BACKGROUND-COLOR: #FFFF99;padding-left:4px};
    .finputNoEmpty3 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:right;BACKGROUND-COLOR: #FFFF99;padding-right:4px};
    .textareaNoEmpty {WIDTH:100%;height:100%;BACKGROUND-COLOR: #FFFF99;}
    .inputNoEmptySelect {WIDTH:100%;height:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;BACKGROUND-COLOR: #FFFF99;cursor:pointer;;padding-left:4px};
    .selectNoEmpty {
        width:100%;
        background-color:#FFFF99;
        border:1px solid #2769a7;
    }

    </style>

</head>
<%
    RowSet rows = (RowSet) request.getAttribute(WebAttrConstant.ETS_SPARE_DTO);
    Row row = null;
%>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth()">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.object.servlet.ImportObjectServlet">
    <script type="text/javascript">
        printTitleBar("�ص㵼�������Ϣ")
    </script>
    <div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:1095px">
        <table width="300%" class="headerTable" border="1">
            <tr height="23px">
                <td width="2%" align="center">�к�</td>
                <td width="5%" align="center">�ص����</td>
                <td width="3%" align="center">����</td>

                <td width="3%" align="center">����</td>
                <td width="5%" align="center">�ص�����</td>
                <td width="2%" align="center">��������</td>

                <td width="2%" align="center">רҵ����</td>
                <td width="5%" align="center">��վ��Ӫҵ�����</td>
                <td width="2%" align="center">��������</td>

                <td width="3%" align="center">ά������</td>
                
                <td width="8%" align="center">�ص���������Ϣ</td>
                <td width="10%" align="center">���д�����Ϣ</td>
                <td width="10%" align="center">���ش�����Ϣ</td>

                <td width="10%" align="center">���ڵص�����������Ϣ</td>
                <td width="10%" align="center">�������������Ϣ</td>
                <td width="10%" align="center">רҵ���������Ϣ</td>
                <td width="10%" align="center">��������������Ϣ</td>
            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;height:68%;width:1111px;position:absolute;top:40px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="300%" border="1" bordercolor="#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22px" style="cursor:pointer" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="2%" align="right"><%=i + 1%></td>
                <td width="5%"><input type="text" readonly="true" class="finput2" value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
                <td width="3%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("CITY")%>"></td>

                <td width="3%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("COUNTY")%>"></td>
                <td width="5%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
                <td width="2%"><input type="text" readonly="true" class="finput2" value="<%=row.getValue("COUNTY_CODE")%>"></td>

                <td width="2%"><input type="text" readonly="true" class="finput2" value="<%=row.getValue("OBJECT_CATEGORY")%>"></td>
                <td width="5%"><input type="text" readonly="true" class="finput2" value="<%=row.getValue("BTS_NO")%>"></td>
                <td width="2%"><input type="text" readonly="true" class="finput2" value="<%=row.getValue("AREA_TYPE")%>"></td>

                <td width="3%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("REMARK")%>"></td>
                <td width="8%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("OBJECT_CODE_ERROR")%>"></td>
                <td width="10%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("CITY_ERROR")%>"></td>

                <td width="10%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("COUNTY_ERROR")%>"></td>
                <td width="10%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("OBJECT_NAME_ERROR")%>"></td>
                <td width="10%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("COUNTY_CODE_ERROR")%>"></td>
                <td width="10%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("OBJECT_CATEGORY_ERROR")%>"></td>
                <td width="10%"><input type="text" readonly="true" class="finput" value="<%=row.getValue("AREA_TYPE_ERROR")%>"></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
</body>
</html>