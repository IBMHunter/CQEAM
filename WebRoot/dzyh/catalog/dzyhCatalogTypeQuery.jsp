<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.base.data.*" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%--<%@ page import="com.sino.shxf.constant.WebAttrConstant" %>--%>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>

</head>

<body leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_Search()');">
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
%>
<form name="mainFrm" method="POST" action="/servlet/com.sino.ams.dzyh.servlet.EamDhCatalogSetServlet">
    <script type="text/javascript">
        printTitleBar("��ֵ�׺�Ʒ����ά��");
    </script>
    <table border="0" width="100%" class="queryHeadBg" id="table1" bgcolor="#E9EAE9">
        <tr>
            <td width="15%" align="right">����ţ�</td>
            <td width="15%" colspan="2">
                <input type="text" name="setCode" style="width:100%" value="<%=parser.getParameter("setCode")%>">
            </td>
            <td width="15%" align="right">������ƣ�</td>
            <td width="15%" align="right" colspan="2">
                <input type="text" name="setName" style="width:100%" value="<%=parser.getParameter("setName")%>"></td>
            <td width="15%" align="right">�Ƿ���Ч��</td>
            <td width="15%">
            <%=request.getAttribute("ENABLED_RADIO")%>
            </td>
            <td width="10%"><img src="/images/eam_images/search.jpg" alt="��ѯ����" onClick="do_Search(); return false;"></td>
            <td width="10%"><img src="/images/eam_images/new_add.jpg" alt="��������" onClick="do_Create(); return false;"></td>
        </tr>
    </table>
    <div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
        <table width="100%" align="left" border="1" cellpadding="2" cellspacing="0"
               class="headerTable">
            <input type="hidden" name="catlogSetId" value="<%=parser.getParameter("catlogSetId")%>">
            <input type="hidden" name="act" value="<%=parser.getParameter("act")%>">

            <tr>
                <td height="22" width="10%" align="center">�����</td>
                <td height="22" width="15%" align="center">�������</td>
                <td height="22" width="15%" align="center">ʧЧ����</td>
                <td height="22" width="15%" align="center">������</td>
                <td height="22" width="15%" align="center">��������</td>
                <td height="22" width="15%" align="center">������</td>
                <td height="22" width="15%" align="center">��������</td>
            </tr>
        </table>
    </div>
    <%
        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        if (rows != null && !rows.isEmpty()) {
            Row row = null;
    %>
    <div style="overflow-y:scroll;height:362px;width:100%;left:1px;margin-left:0"
         align="left">
        <table width="100%" border="1" bordercolor="#666666">

            <%
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
            %>
            <tr class="dataTR" onclick="do_ShowDetail('<%=row.getValue("CATLOG_SET_ID")%>'); return false;">
                <td height="22" width="10%"><%=row.getValue("SET_CODE")%>
                </td>
                <td height="22" width="15%"><%=row.getValue("SET_NAME")%>
                </td>
                <td height="22" width="15%"><%=row.getValue("END_DATE")%>
                </td>
                <td height="22" width="15%"><%=row.getValue("CREATED_BY")%>
                </td>
                <td height="22" width="15%"><%=row.getValue("CREATION_DATE")%>
                </td>
                <td height="22" width="15%"><%=row.getValue("LAST_UPDATE_BY")%>
                </td>
                <td height="22" width="15%" align="center"><%=row.getValue("LAST_UPDATE_DATE")%>
                </td>
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

<%=WebConstant.WAIT_TIP_MSG%>
</body>

<script language="javascript">

    function do_Search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
    }

    function do_Create() {
        mainFrm.catlogSetId.value = "";
        mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
        mainFrm.submit();
    }

    function do_ShowDetail(primaryKey) {
        mainFrm.catlogSetId.value = primaryKey;
        mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";
        mainFrm.submit();
    }
</script>