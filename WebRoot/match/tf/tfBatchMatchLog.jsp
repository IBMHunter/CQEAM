<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>ͨ���ʲ�ƥ����ʷ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/RadioProcess.js"></script>
</head>
<body leftmargin="0" topmargin="0">
<script>
    var ArrAction1 = new Array(true, "ȡ��ƥ��", "act_refresh.gif", "ȡ��ƥ��", "unMatch()");
    var ArrAction2 = new Array(true, "���ص�ƥ��", "act_query.gif", "���ص�ƥ��", "matchByLocation()");
    var ArrAction3 = new Array(true, "����ƥ��", "act_query.gif", "����ƥ��", "matchByCounty()");
    var ArrAction4 = new Array(true, "����ƥ��", "act_query.gif", "����ƥ��", "matchByCity()");
    var ArrAction5 = new Array(true, "ˢ��", "act_refresh.gif", "ˢ��ҳ��", "refreshPage()");
    var ArrAction6 = new Array(true, "�ر�", "del.gif", "�ر�", "window.close()");
    var ArrActions = new Array(ArrAction1, ArrAction2, ArrAction3, ArrAction4, ArrAction5, ArrAction6);
    var ArrSinoViews = new Array();
    printTitleBar("ƥ����ʷ��¼");
    printToolBar();
    var columnArr = new Array("", "����", "EAM�豸����", "EAM����ͺ�", "MIS�豸����", "MIS����ͺ�", "ƥ�����", "ִ����", "ִ��ʱ��");
    var widthArr = new Array("3%", "6%", "15%", "15%", "15%", "15%", "6%", "6%", "7%");
    printTableHead(columnArr, widthArr);
</script>
<div style="overflow-y:scroll;height:490px;width:100%" align="left"
     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <form action="/servlet/com.sino.ams.match.servlet.TFBatchMatchLogServlet" name="mainForm" method="post">
        <input type="hidden" name="act">
        <table width="100%" border="1" bordercolor="#9FD6FF">
            <%
                RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
                Row row = null;
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
//                        Logger.logInfo("size = " + rows.getSize());
                        row = rows.getRow(i);
                        String matchType = String.valueOf(row.getValue("MATCH_TYPE"));
                        String matchTypeName = "";
                        if (matchType.equals("0")) {
                            matchTypeName = "���ص�";
                        } else if (matchType.equals("1")) {
                            matchTypeName = "����";
                        } else if (matchType.equals("2")) {
                            matchTypeName = "����";
                        }

            %>
            <tr class="hei" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td height="22" width="3%" align="center"><input type="radio" name="batchId"
                                                                 value="<%=row.getValue("ID")%>">
                </td>
                <td height="22" width="6%"><%=row.getValue("ID") %>
                </td>
                <td height="22" width="15%"><%=row.getValue("ETS_NAME") %>
                </td>
                <td height="22" width="15%"><%=row.getValue("ETS_SPEC") %>
                </td>
                <td height="22" width="15%"><%=row.getValue("MIS_NAME") %>
                </td>
                <td height="22" width="15%"><%=row.getValue("MIS_SPEC") %>
                </td>
                <td height="22" width="6%"><%=matchTypeName%>
                </td>
                <td height="22" width="6%"><%=row.getValue("CREATED_USER") %>
                </td>
                <td height="22" width="7%"><%=String.valueOf(row.getValue("CREATION_DATE")).substring(0, 10) %>
                </td>

            </tr>

            <%
                    }
                }
            %>
        </table>
    </form>
</div>
<%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
<div id="showMsg"><%=StrUtil.nullToString(request.getAttribute("showMsg"))%>
    <jsp:include page="/message/MessageProcess"/>
</div>
<%=WebConstant.WAIT_TIP_MSG%>
</body>
<script type="text/javascript">
    function unMatch() {
        performTask("UN_MATCH");
    }
    function matchByLocation() {
        performTask("matchByLocation");
    }
    function matchByCounty() {
        performTask("matchByCounty");
    }
    function matchByCity() {
        performTask("matchByCity");
    }
    function refreshPage(){
        document.mainForm.act.value = "";
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainForm.submit();
    }
    function performTask(condition) {
        if (checkData()) {
            document.mainForm.act.value = condition;
            document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
            document.mainForm.submit();
        }
    }
    function checkData() {
        var rs = true;
        var batchId = getRadioValue("batchId");
        if (batchId == "") {
            alert("����ѡ��һ�����ݣ�");
            rs = false;
        }
        return rs;
    }
</script>
</html>