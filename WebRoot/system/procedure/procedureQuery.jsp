<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>���̲�ѯ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
</head>
<body leftmargin="1" topmargin="0" onload="doChecked();" onkeydown="do_check()">
<%=WebConstant.WAIT_TIP_MSG%>
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    String procId = reqParser.getParameter("procId");
    String procName = reqParser.getParameter("procName");
    String description = reqParser.getParameter("description");
    String appTableName = reqParser.getParameter("appTableName");
    String appPkName = reqParser.getParameter("appPkName");
    String organizationId = reqParser.getParameter("organizationId");
    String disabled = reqParser.getParameter("disabled");
    String action = reqParser.getParameter("act");
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.procedure.servlet.SfProcedureDefServlet">
    <script type="text/javascript">
        printTitleBar("���̲�ѯ")
    </script>
    <input type="hidden" name="act" value="<%=action%>">
    <table border="0" width="100%"  id="table1"  class="queryHeadBg" >
        <tr>
            <td width="10%" align="right">Ӧ�ñ����ƣ�</td>
            <td width="20%"><input type="text"  name="appTableName" style="width:100%"
                                   value="<%=appTableName%>"></td>
            <td width="10%" align="right">Ӧ�ñ�������</td>
            <td width="20%"><input type="text"  name="appPkName" style="width:100%" value="<%=appPkName%>"></td>
            <td width="10%" align="right">��˾��ţ�</td>
            <td width="15%"><input type="text" name="organizationId" style="width:100%"
                                   value="<%=organizationId%>">
            </td>
            <td width="" align="center"></td>
  <%-- <td width="" align="center"><img src="/images/eam_images/new_add.jpg" alt="�������"
          onClick="do_CreateProcedure(); return false;"></td>--%>
        </tr>
        <tr>
            <td width="" align="right">�������ƣ�</td>
            <td width=""><input type="text" name="procName" style="width:100%" value="<%=procName%>"></td>
            <td width="" align="right">����������</td>
            <td width=""><input type="text" name="description"  style="width:100%" value="<%=description%>">
            </td>
            <td width="" align="right">�Ƿ�ʧЧ��</td>
            <td width="">
                <select name="disabled"  style="width:100%">
                    <option value="" selected>ȫ��</option>
                    <option value="N">��Ч</option>
                    <option value="Y">ʧЧ</option>
                </select></td>
            <td width="" align="center"><img src="/images/eam_images/search.jpg" alt="��ѯ����"
                                             onClick="do_SearchProcedure(); return false;"></td>
        </tr>
    </table>

<div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
    <table width="100%" align="left" border="1" cellpadding="2" cellspacing="0" class="headerTable">
        <tr>
            <td height="22" width="20%" align="center">��������</td>
            <td height="22" width="29%" align="center">��������</td>
            <td height="22" width="20%" align="center">Ӧ�ñ�����</td>
            <td height="22" width="14%" align="center">Ӧ�ñ�����</td>
            <td height="22" width="9%" align="center">��˾���</td>
            <td height="22" width="8%" align="center">�Ƿ�ʧЧ</td>
        </tr>
    </table>
</div>
<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && !rows.isEmpty()) {
%>
<div id="dataDiv" style="overflow-y:scroll;height:69%;width:100%;position:absolute;top:93px;left:0px" align="left">
    <table width="100%" border="1" bordercolor="#666666">
        <%
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
        %>
        <tr class="dataTR" onclick="do_ShowDetail('<%=row.getValue("PROC_ID")%>'); return false;">
            <td style="word-wrap:break-word" height="22" width="20%"><%=row.getValue("PROC_NAME")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="29%"><%=row.getValue("DESCRIPTION")%>
            </td>
            <td style="word-wrap:break-word" height="22" align="center" width="20%"><%=row.getValue("APP_TABLE_NAME")%>
            </td>
            <td style="word-wrap:break-word" height="22" align="center" width="14%"><%=row.getValue("APP_PK_NAME")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="9%"><%=row.getValue("ORGANIZATION_ID")%>
            </td>
            <td style="word-wrap:break-word" height="22" align="center" width="8%"><%=row.getValue("DISABLED")%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
    </form>
<%
    }
%>
<div style="position:absolute;top:91%;left:0; right:20"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
</html>
<jsp:include page="/message/MessageProcess"/>
<script language="javascript">

    function do_SearchProcedure() {
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.action = "/servlet/com.sino.ams.system.procedure.servlet.SfProcedureDefServlet";
        mainFrm.submit();
    }

    function do_CreateProcedure() {
        mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.system.procedure.servlet.SfProcedureDefServlet";
        mainFrm.submit();
    }

    function do_ShowDetail(procId) {
        mainFrm.action = "/servlet/com.sino.ams.system.procedure.servlet.SfProcedureDefServlet?act=<%=WebActionConstant.DETAIL_ACTION%>&procId=" + procId;
        mainFrm.submit();
    }

    function doChecked() {

    }

    function do_check() {
        if (event.keyCode == 13) {
            do_SearchProcedure();
        }
    }
</script>