<%@ page import="com.sino.ams.newasset.constant.AssetsActionConstant" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<%--
    Function:    �û���¼���ͳ�Ʊ���
    Author:      ����
    Date:        2009-10-27
--%>

<html>

<head>
    <title>�û���¼���ͳ�Ʊ���</title>
    <link rel = "stylesheet" type = "text/css" href = "/WebLibary/css/main.css">
    <script language = "javascript" src = "/WebLibary/js/Constant.js"></script>
    <script language = "javascript" src = "/WebLibary/js/CommonUtil.js"></script>
    <script language = "javascript" src = "/WebLibary/js/FormProcess.js"></script>
    <script language = "javascript" src = "/WebLibary/js/SinoToolBar.js"></script>
    <script language = "javascript" src = "/WebLibary/js/SinoToolBarConst.js"></script>
    <script language = "javascript" src = "/WebLibary/js/jslib.js"></script>
    <script language = "javascript" src = "/WebLibary/js/CheckboxProcess.js"></script>
    <script language = "javascript" src = "/WebLibary/js/RadioProcess.js"></script>
    <script language = "javascript" src = "/WebLibary/js/DateProcess.js"></script>
    <script language = "javascript" src = "/WebLibary/js/TableProcess.js"></script>

</head>

<body onload = "autoSpan('dateTable',1); ">


<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = (rows != null && !rows.isEmpty());
    Row row = null;
    AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);

%>
<form method = "post" name = "mainFrm">
    <script type = "text/javascript">
        printTitleBar("�û���¼���ͳ�Ʊ���")
    </script>
    <table width = "100%" border = "0" class="queryHeadBg">
        <tr>
            <td width="10%" align="right">��˾���ƣ�</td>
			<td width="15%"><select size="1" name="organizationId" style="width:100%"><%=dto.getOrgOpt()%></select></td>
            <td width="13%" align="right">��¼ʱ�䣺</td>
			<td width="15%"><input type="text" name="startDate" style="cursor:hand;width:100%" title="���ѡ��ʼ����" readonly class="readonlyInput" value="<%=dto.getStartDate()%>" onclick="gfPop.fStartPop(startDate, endDate);" size="20"></td>
			<td width="10%" align="center">����</td>
			<td width="15%"><input type="text" name="endDate" style="cursor:hand;width:100%" title="���ѡ���������" readonly class="readonlyInput" value="<%=dto.getEndDate()%>" onclick="gfPop.fEndPop(startDate, endDate);"></td>
            <td width="30%" align = "right">
                <img src = "/images/eam_images/search.jpg" style = "cursor:'hand'" onclick = "do_search();" alt = "��ѯ">&nbsp;&nbsp;&nbsp;
                <%--<img src = "/images/eam_images/export.jpg" id = "queryImg" style = "cursor:'hand'" onclick = "do_exportToExcel()" alt = "������Excel">--%>
            </td>
        </tr>
    </table>

    <input type = "hidden" name = "act" value = "<%=parser.getParameter("act")%>">

    <script type = "text/javascript">
        var columnArr = new Array('��˾����', <%=dto.getDeptCategoryValues()%>);
        var widthArr = new Array("5%", <% for(int x = 0; x <= dto.getDeptCategoryCodes().size(); x++){
                                                if(x!= dto.getDeptCategoryCodes().size()){
                                       %>
                                           "3%",
                                       <%
                                                } else {
                                       %>
                                           "3%"
                                       <%
                                             }
                                          }   
                                        %>);
        printTableHead(columnArr, widthArr);
    </script>
    <div style = "overflow-y:scroll; left:0px;width:100%;height:400px">
        <table width = "100%" border = "1" bordercolor = "#666666" id = "dateTable">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr class = "dataTR" height = "22">
                <td width = "5%" align = "center"><%=row.getValue("COMPANY")%></td>
                <%
                    for(int y = 0;  y < dto.getDeptCategoryCodes().size(); y++){
                %>
                   <td width = "3%" align = "center"><%=row.getValue(dto.getDeptCategoryCodes().get(y).toString())%></td>
                <%
                    }
                %>
                <%--<td width = "3%" align = "center"><%=row.getValue("FINANCIAL_COUNT")%></td>--%>
                <%--<td width = "3%" align = "center"><%=row.getValue("NETWORK_COUNT")%></td>--%>
                <%--<td width = "3%" align = "center"><%=row.getValue("MARKET_COUNT")%></td>--%>
                <%--<td width = "3%" align = "center"><%=row.getValue("COMPREHENSIVE_COUNT")%></td>--%>
                <%--<td width = "3%" align = "center"><%=row.getValue("COUNTY_COUNT")%></td>--%>
                <td width = "3%" align = "center"><%=row.getValue("SUM_COUNT")%></td>
            </tr>
            <%
                }        }
            %>
        </table>
    </div>

</form>

<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page = "<%=URLDefineList.MESSAGE_PROCESS%>" flush = "true" />
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<%
	if(hasData){
%>
<div style="position:absolute;top:87%;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
</html>
<script type = "text/javascript">
    function do_search() {
//        var startDate =    mainFrm.startDate.value;
//        var endDate = mainFrm.endDate.value;
//        alert(startDate);
//        alert(endDate);
        if(mainFrm.endDate.value < mainFrm.startDate.value){
            alert("��½����ʱ�����С�ڿ�ʼʱ�䣡");
            return;
        }
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "<%=URLDefineList.LOGIN_STATISTICS_SERVLET%>";
        mainFrm.submit();
    }
    function do_exportToExcel() {
        mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainFrm.action = "<%=URLDefineList.LOGIN_STATISTICS_SERVLET%>";
        mainFrm.submit();

    }

</script>