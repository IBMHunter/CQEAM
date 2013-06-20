<%--
  Created by HERRY.
  Date: 2008-10-14
  Time: 15:33:14
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>

<%@ page import="com.sino.ams.newasset.constant.AssetsDictConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>

<html>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
%>
<head><title>��ӡ���뷽�±�ǩ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
</head>
<body onkeydown="autoExeFunction('do_Search()')">
<form name="mainFrm" method="POST" action="/servlet/com.sino.ams.newasset.servlet.NewAssetsTagPrintServlet">
    <script type="text/javascript">
        printTitleBar("��ӡ���뷽�±�ǩ��");
    </script>
    <input type="hidden" name="transType" value="ASS-RED">
    <input type="hidden" name="transferType" value="<%=AssetsDictConstant.TRANS_BTW_COMP%>">
    <input type="hidden" name="transId" value="">
    <input type="hidden" name="act" value="">
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" class="queryHeadBg">
        <tr>
            <td width="10%" height="22" align="right">���ݱ�ţ�</td>
            <td width="17%" height="22"><input type="text" name="transNo" style="width:100%"
                                               value="<%=parser.getParameter("transNo")%>"></td>
            <td width="10%" height="22" align="right">������˾��</td>
            <td width="17%" height="22"><input type="text" name="fromOrganizationName" style="width:100%" value="<%=parser.getParameter("fromOrganizationName")%>"></td>
            <td width="10%" height="22" align="right">���빫˾��</td>
            <td width="17%" height="22"><input type="text" name="toOrganizationName" style="width:100%" value="<%=parser.getParameter("toOrganizationName")%>"></td>
        </tr>
        <tr>
            <td height="22" align="right">�������ڣ�</td>
            <td height="22"><input type="text" name="startDate" value="<%=parser.getParameter("startDate")%>"
                                   style="width:80%" title="���ѡ��ʼ����" readonly class="readonlyInput"
                                   onclick="gfPop.fStartPop(startDate, endDate)">
                <img src="/images/calendar.gif" alt="���ѡ��ʼ����" onclick="gfPop.fStartPop(startDate, endDate)">
            </td>
            <td height="22" align="right">��</td>
            <td height="22"><input type="text" name="endDate" value="<%=parser.getParameter("endDate")%>"
                                   style="width:80%" title="���ѡ���ֹ����" readonly class="readonlyInput"
                                   onclick="gfPop.fEndPop(startDate, endDate)">
                <img src="/images/calendar.gif" alt="���ѡ���ֹ����" onclick="gfPop.fEndPop(startDate, endDate)">
            </td>
            <td height="22" colspan="2" align="right"><img src="/images/eam_images/search.jpg" onclick="do_Search();" alt="��ѯ">
                <img src="/images/eam_images/export.jpg" onclick="do_export();" alt="����Excel��ʹ�ñ�ǩ��ӡ�����ӡ��ǩ"></td>
        </tr>
    </table>

    <script type="text/javascript">
        var columnArr = new Array("checkbox","�±�ǩ��","���ݱ��", "������˾","���빫˾", "������", "��������");
        var widthArr = new Array("5%","15%","20%", "15%","15%", "10%", "15%");
        printTableHead(columnArr, widthArr);

    </script>
    <div style="overflow-y:scroll;left:1px;width:100%;height:70%">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'">
                <td width="5%" align="center"><input type="checkbox" name="subCheck" value="<%=row.getValue("TAG_NUMBER_TO")%>"></td>
                <td width="15%" align="center"><%=row.getValue("TAG_NUMBER_TO")%>
                </td>
                <td width="20%" align="center"><%=row.getValue("TRANS_NO")%>
                </td>
                <td width="15%"><%=row.getValue("FROM_ORGANIZATION_NAME")%>
                </td>
                <td width="15%"><%=row.getValue("TO_ORGANIZATION_NAME")%></td>
                <td width="10%" align="left"><%=row.getValue("CREATED_BY")%>
                </td>
                <td width="15%" align="center"><%=String.valueOf(row.getValue("CREATION_DATE"))%>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<div style="left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
<%=WebConstant.WAIT_TIP_MSG%>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

<script language="javascript">

    function do_Search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.mainFrm.submit();
    }

    function do_ShowDetail(primaryKey) {
        //	document.mainFrm.flexValueId.value = primaryKey;
    <%--document.mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";--%>
        var url = "/servlet/com.sino.ams.newasset.servlet.AmsMisTagChgServlet?act=<%=WebActionConstant.DETAIL_ACTION%>&transNo=" + primaryKey;
        var popscript = "width=1020,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes";
        window.open(url, "amsMisTagChg", popscript);
    }
    function do_export(){
        var count = getCheckedBoxCount("subCheck");
        if(count < 1){
            alert("������ѡ��һ����ǩ�ţ�");
            return;
        }
        document.mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        document.mainFrm.submit();
    }
</script>