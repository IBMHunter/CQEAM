<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.yj.dto.AmsYjUserDTO" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>Ӧ��������Աά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>

</head>

<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    Row row = null;
    String userId = parser.getParameter("userId");
    String teamId = parser.getParameter("teamId");
    String teamName = parser.getParameter("teamName");
    String userName = parser.getParameter("userName");
    String orgOption = (String) request.getAttribute(WebAttrConstant.OU_OPTION);
%>
<body onkeydown="autoExeFunction('do_search()')">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.yj.servlet.AmsYjUserServlet">
    <script type="text/javascript">
        printTitleBar("Ӧ��������Աά��")
    </script>
    <table width="100%" border="1" class="queryHeadBg">
        <input type="hidden" name="act">
        <tr>
            <td width="6%" align="right">��˾���ƣ�</td>
            <td width="11%"><select class="select_style1" style="width:100%" name="organizationId"><%=orgOption%>
            </select></td>
            <!--<td width="6%" align="right">����ţ�</td>-->
            <%--<td width="10%"><input style="width:100%" type="text" name="teamId" value="<%=teamId%>"></td>--%>
            <td width="6%" align="right">�������ƣ�</td>
            <td width="10%"><input class="input_style1" style="width:100%" type="text" name="teamName" value="<%=teamName%>"></td>
            <td width="6%" align="right">������</td>
            <td width="10%"><input class="input_style1" style="width:100%" type="text" name="userName" value="<%=userName%>"></td>
            <td width="15%" align="center"><img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ"><img
                    src="/images/eam_images/new.jpg" alt="����" onClick="do_add();"><img src="/images/eam_images/export.jpg" style="cursor:'hand'"
                                                                                   onclick="do_Export();" title="������Excel">
            </td>
        </tr>
    </table>

      <div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
        <table width="100%" class="headerTable" border="1">
            <tr height="20">
                <td width="8%" align="center">��˾����</td>
                <td width="4%" align="center">�����</td>
                <td width="10%" align="center">��������</td>
                <td width="6%" align="center">����</td>
                <td width="8%" align="center">ְ��</td>
                <td width="6%" align="center">רҵ</td>
                <td width="6%" align="center">�ֻ�</td>
                <td width="5%" align="center">����</td>
                <td width="15%" align="center">��ע</td>
                <!--<td width="6%" align="center">������</td>-->
                <!--<td width="10%" align="center">��������</td>-->
                <!--<td width="6%" align="center">������</td>-->
                <!--<td width="10%" align="center">��������</td>-->
            </tr>
        </table>
    </div>
    <div style="overflow-y:scroll;left:0px;width:100%;height:350px">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'"
                onclick="show_detail('<%=row.getValue("USER_ID")%>')">
                <td width="8%" align="left"><%=row.getValue("ORGANIZATION_NAME")%>
                </td>
                <td width="4%" align="center"><%=row.getValue("TEAM_ID")%>
                </td>
                <td width="10%" align="left"><%=row.getValue("TEAM_NAME")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("USER_NAME")%>
                </td>
                <td width="8%" align="left"><%=row.getValue("POST")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("CATEGORY")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("TEL")%>
                </td>
                <td width="5%" align="left"><%=row.getValue("ATTRIBUTE")%>
                </td>
                <td width="15%" align="left"><%=row.getValue("REMARK")%>
                </td>
                <%--<td width="6%" align="left"><%=row.getValue("CREATE_USER")%>--%>
                <!--</td>-->
                <%--<td width="10%" align="left"><%=row.getValue("CREATION_DATE")%>--%>
                <!--</td>-->
                <%--<td width="6%" align="left"><%=row.getValue("LAST_UPDATE_USER")%>--%>
                <!--</td>-->
                <%--<td width="10%" align="left"><%=row.getValue("LAST_UPDATE_DATE")%>--%>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>

</body>
</html>

<script type="text/javascript">

    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
    }

    function show_detail(userId) {
        mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjUserServlet?userId=" + userId;
        mainFrm.submit();
    }

    function do_add() {
        mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
        mainFrm.submit();
    }

    function do_Export() {                  //����execl
        document.mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        document.mainFrm.submit();
    }

</script>