<%--
  User: sunny song
  Date: 2007-11-19
  Time: 10:32:19
  Edited:jiachuanchuan
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<html>
<head><title>设备返修量-按名称</title>
    <%
        RequestParser parser = new RequestParser();
        parser.transData(request);
        String action = parser.getParameter("act");
        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        Row row = null;
        String itemName = parser.getParameter("itemName");
        String itemSpec = parser.getParameter("itemSpec");
        String repairQuery = parser.getParameter("repairQuery");
    %>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
</head>


<body onkeydown="autoExeFunction('do_SearchQuantity()');">

<jsp:include page="/message/MessageProcess"/>

<form name="workForm" method="post" action="/servlet/com.sino.ams.spare2.repair.servlet.AmsItemRepairServlet">
    <script type="text/javascript">
        printTitleBar("设备返修量-按名称");
    </script>
    <table border="0" width="100%" cellpadding="2" cellspacing="0" class="queryHeadBg">
        <input type="hidden" name="executeUser">
        <input type="hidden" name="planId" value="">
        <input type="hidden" name="act" value="<%=action%>">
        <input type="hidden" name="repairQuery" value="<%=repairQuery%>">
        <tr><td>
            <table width="99%" align='right' border="0">
                <tr>
                    <td align="right" width="8%">公司：</td>
                    <td width="12%"><select style="width:95%"
                                            name="orgId"><%=request.getAttribute(WebAttrConstant.OU_OPTION)%></select>
                    </td>
                    <td align="right" width="13%">设备名称：</td>
                    <td width="18%"><input style="width:80%" type="text" name="itemName"
                                           value="<%=itemName%>">
                        <a style="cursor:'hand' " onclick="do_SelectSpec()" title="点击选择设备名称" class="linka">[…]</a></td>
                    <td align="right" width="13%">规格型号：</td>
                    <td width="18%"><input style="width:80%" type="text" name="itemSpec"
                                           value="<%=itemSpec%>"></td>
                </tr>
                <tr>
                    <td align="right" width="10%">维修时间：</td>
                    <td width="18%"><input type="text" name="fromDate" value="<%=parser.getParameter("fromDate")%>"
                                           style="width:80%"       class="readonlyInput"
                                           title="点击选择日期" readonly 
                                           onclick="gfPop.fStartPop(fromDate, toDate)">
                        <img src="/images/calendar.gif" alt="点击选择日期"
                             onclick="gfPop.fStartPop(fromDate,toDate)"></td>
                    <td align="right" width="10%">到：</td>
                    <td width="18%"><input type="text" name="toDate" value="<%=parser.getParameter("toDate")%>"
                                           style="width:80%"
                                           title="点击选择日期" readonly class="readonlyInput"
                                           onclick="gfPop.fEndPop(fromDate, toDate)">
                        <img src="/images/calendar.gif" alt="点击选择日期"
                             onclick="gfPop.fEndPop(fromDate,toDate)"></td>
                    <td></td>
                    <td width=8%><img src="/images/eam_images/search.jpg" alt="查询"
                                      onClick="do_SearchQuantity(); return false;">
                        <img src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'"
                             onclick="do_exportToExcel()" alt="导出到Excel"></td>
                </tr>
            </table>
        </td>
        </tr>
    </table>


    <script type="text/javascript">
        var columnArr = new Array("公司","设备名称", "规格型号", "返修数量");
        var widthArr = new Array("20%","25%", "30%", "20%");
        printTableHead(columnArr, widthArr);
    </script>
    <%
        if (rows != null && rows.getSize() > 0) {
    %>
    <div style="overflow-y:scroll;height:362px;width:100%;left:1px;margin-left:0px" align="left">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
            %>
            <tr height="22" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'" >
                <td width="20%" align="left"><%=row.getValue("ORG_NAME")%></td>
                <td width="25%" align="left"><%=row.getValue("ITEM_NAME")%></td>
                <td width="30%" align="left"><%=row.getValue("ITEM_SPEC")%></td>
                <td width="20%" align="right"><%=row.getValue("QUANTITY")%></td>
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
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>

<script type="text/javascript">
    function do_SearchQuantity() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        workForm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        workForm.submit();
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
                dto2Frm(spec, "workForm");
            }
        }
    }
    function do_exportToExcel() {
        workForm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        workForm.submit();
    }

</script>