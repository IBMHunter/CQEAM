<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO" %>
<%@ page import="com.sino.ams.net.reportforms.dto.SitusStatisticsDTO" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2007-11-13
  Time: 8:40:11
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>������ɼ�ʱ��ͳ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>

</head>
<body onkeydown="autoExeFunction('do_search()')">
<%=WebConstant.WAIT_TIP_MSG%>
<%
    //	EtsObjectDTO etsObject = (EtsObjectDTO)request.getAttribute(WebAttrConstant.ETS_OBJECT_DTO);
    SitusStatisticsDTO situsdto = (SitusStatisticsDTO)request.getAttribute(WebAttrConstant.ETS_WORKORDER_DTO);
    String organizationId = (String) request.getAttribute(WebAttrConstant.OU_OPTION);
//    String fromDate = request.getParameter("fromDate");
//    String toDate = request.getParameter("toDate");
%>
<form method="post" name="mainFrm"  action="/servlet/com.sino.ams.net.reportforms.servlet.CityCompleteServlet">
    <iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<input type="hidden" name="objectCategory" value="">
<input type="hidden" name="objectCategory" value="">
<input type="hidden" name="categoryName"  id="categoryName" value="">
<script type="text/javascript">
    printTitleBar("������ɼ�ʱ��ͳ��")
</script>
<table width="100%" border="0" bgcolor="#EFEFEF">
        <tr>
            <td width="10%" align="right">��˾��</td>
            <td width="22%"><select style="width:100%" type="text" name="organizationId"><%=organizationId%></select></td>
            <td width="18%" align="right">�����·����� ��</td>
            <td width="20%"><input style="width:80%" type="text" name="fromDate" readonly class="readOnlyInput" onclick="gfPop.fStartPop(fromDate,toDate);" value="<%=situsdto.getFromDate()%>"><img src="/images/calendar.gif" alt="���ѡ������"  onclick="gfPop.fStartPop(fromDate,toDate);"></td>
            <td width="6%" align="right">����</td>
            <td width="24%"><input name="toDate" style="width:80%" readonly class="readOnlyInput" onclick="gfPop.fEndPop(fromDate,toDate);" value="<%=situsdto.getToDate()%>"><img src="/images/calendar.gif" alt="���ѡ������"  onclick="gfPop.fEndPop(fromDate,toDate);"></td>
            <td><img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ"><img src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel"></td>
        </tr>
</table>
      <script type="text/javascript">
            var columnArr = new Array("��˾","��ʱ��ɹ�����","��ʱ��ɹ�����","��ʱδ��ɹ�����","���������й�������","��ɼ�ʱ��");
            var widthArr = new Array("20%","20%","15%","15%","15%","15%");
            printTableHead(columnArr,widthArr);
        </script>
<input type="hidden" name="act">

<div style="overflow-y:scroll;left:0px;width:100%;height:360px">
    <table width="100%" border="1" bordercolor="#666666">
<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && rows.getSize() > 0) {
	    Row row = null;
        for (int i = 0; i < rows.getSize(); i++) {
            row = rows.getRow(i);
%>
        <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
            <td width="20%" align="center" ><%=row.getValue("COMPANY")%></td>
            <td width="20%" align="center"><%=row.getValue("IN_TIME_COUNT")%></td>
            <td width="15%" align="center" ><%=row.getValue("OVER_TIME_COUNT1")%></td>
            <td width="15%" align="center" ><%=row.getValue("OVER_TIME_COUNT2")%></td>
            <td width="15%" align="center"><%=row.getValue("NORMAL_PROCESS_COUNT")%></td>
            <td width="15%" align="center" ><%=row.getValue("RATE")%></td>
        </tr>
<%
	    }
%>
    </table>
</div>
</form>
<div style="left:0; right:20"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
<%
    }
%>
<jsp:include page="/message/MessageProcess"/>

</body>

</html>
<script type="text/javascript">
function do_search() {
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.net.reportforms.servlet.CityCompleteServlet";
    mainFrm.submit();
}

function do_Export(){                  //����execl
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.net.reportforms.servlet.CityCompleteServlet";
    mainFrm.submit();
}
</script>