<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-3-25
  Time: 14:04:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head><title>EAM�ص���Ϣ</title>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/RadioProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/datepicker.js"></script>
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <%
        RequestParser reqParser = new RequestParser();
        reqParser.transData(request);
        String action = reqParser.getParameter("act");
//        String workorderObjectNo = reqParser.getParameter(request.getParameter("workorderObjectNo"));
        String showAll = request.getParameter("showAll");
        String EAMworkObjectLocation = reqParser.getParameter("EAMworkObjectLocation");
        String workorderObjectCode = reqParser.getParameter("workorderObjectCode");
        showAll = showAll == null ? "N" : showAll;
    %>
</head>
<base target="_self">
<jsp:include page="/message/MessageProcess"/>
<body leftmargin=0 topmargin=0>
<form name="mainForm" action="/servlet/com.sino.ams.match.amsMisLocMatch.servlet.amsMisLocMatchServlet?act=<%=WebActionConstant.QUERY_ACTION%>" method="post">
    <script language="javascript">
        printTitleBar("EAM�ص���Ϣ");
    </script>
    <table width="100%" border="0" cellpadding="2" cellspacing="0" class="queryHeadBg">
        <input type="hidden" name="act" value="<%=action%>">
        <input type="hidden" name="organizationId" value="">
                    <input type="hidden" name="action" value="">
                    <input type="hidden" name="tempRadio" value="">
                    <%--<input type="hidden" name="workorderObjectNo" value="<%=workorderObjectNo%>">--%>
                    <input type="hidden" name="showAll" value="<%=showAll%>">
                    <input type="hidden" name="projectId">
                    <tr>
                        <td align="center" width="45%">�ص��ţ�<input style="width:60%" type="text" name="workorderObjectCode" value="<%=workorderObjectCode%>">
                        </td>
                        <td align="center" width="45%">���ڵص㣺<input style="width:60%" type="text" name="EAMworkObjectLocation" value="<%=EAMworkObjectLocation%>">
                        </td>
                        <td align="right" width="10%"><a style="cursor:'hand'"><img src="/images/eam_images/search.jpg"
                                                                                   onClick="do_Search(); return false;"></a>
                        </td>
                    </tr>
                </table>
    <script type="text/javascript">
        var columnArr = new Array("", "�ص���", "���ڵص�");
        var widthArr = new Array("5%", "30%", "45%");
        printTableHead(columnArr, widthArr);
    </script>
    <%
        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        if (rows != null && !rows.isEmpty()) {
    %>
    <div style="overflow-y:scroll;height:480px;width:100%;left:1px;margin-left:0px" align="left">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
            %>
            <tr class="dataTR" height="22">
                <td width="5%" align="center"><input type="radio" id="workorderObjectNo" name="workorderObjectNo"  value="<%=row.getValue("WORKORDER_OBJECT_NO")%>"></td>
                <td width="30%"><%=row.getValue("WORKORDER_OBJECT_CODE")%></td>
                <td width="45%"><%=row.getValue("WORKORDER_OBJECT_LOCATION")%></td>
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
</html>
<script language="javascript">
    function showLocation() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_BTS%>";
        var dialogWidth = 48;
        var dialogHeight = 30;
        var Locations = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (Locations) {
            var Location = null;
            for (var i = 0; i < Locations.length; i++) {
                Location = Locations[i];
                dto2Frm(Location, "mainForm");
            }
        }
    }
    function do_SelectProj() {
        var lookUpProj = "<%=LookUpConstant.LOOK_UP_PROJECT3%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        var projs = getLookUpValues(lookUpProj, dialogWidth, dialogHeight);
        if (projs) {
            var proj = null;
            for (var i = 0; i < projs.length; i++) {
                proj = projs[i];
                dto2Frm(proj, "mainForm");
            }
        }
    }
    function do_SelectSpec() {
        var lookUpSpec = "<%=LookUpConstant.LOOK_UP_ITEM_SIMPLE%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        var specs = getLookUpValues(lookUpSpec, dialogWidth, dialogHeight);
        if (specs) {
            var spec = null;
            for (var i = 0; i < specs.length; i++) {
                spec = specs[i];
                dto2Frm(spec, "mainForm");
            }
        }
    }
    function do_Search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainForm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainForm.submit();
    }

</script>
