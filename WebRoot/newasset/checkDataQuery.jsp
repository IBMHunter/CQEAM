<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
<head>
<%
    AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
//    RequestParser parser = new RequestParser();
//    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = (rows != null && !rows.isEmpty());
    //String orgId = userAccount.getOrganizationId();
    String orgId = userAccount.getOrganization();
%>
    <title>�����̵��ʲ���Ϣ��ѯ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
</head>
<body leftmargin="0" topmargin="0" onload="initPage()" onkeydown="autoExeFunction('do_Search();')">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<form method="post" name="mainFrm" action="/servlet/com.sino.ams.newasset.servlet.CheckDataQueryServlet">
    <input type="hidden" name="act">
    <script type="text/javascript">
        printTitleBar("�����̵��ʲ���Ϣ��ѯ")
    </script>
    <table width="100%" border="1" class="queryHeadBg">
        <tr height="15">
            <td align="right" width="5%">��˾���ƣ�</td>
            <td width="8%"><select style="width:100%" type="text" name="organizationId"><%=request.getAttribute(AssetsWebAttributes.CITY_OPTION)%></select></td>
            <td align="right" width="5%">��ǩ�ţ�</td>
            <td width="8%"><input type="text" name="qryBarcode" style="width:100%" value="<%=dto.getQryBarcode()%>"></td>
            <td align="right" width="5%">������ţ�</td>
            <td width="8%"><input type="text" name="transNo" style="width:100%" value="<%=dto.getTransNo()%>"></td>
            <td align="right" width="5%">�µ����</td>
            <td width="15%"><select name="groupId" style="width:100%"><%=dto.getGroupOpt()%></select></td>
        </tr>
        <tr height="15">
            <td width="5%" align="right">�µ����ڣ�</td>
		    <td width="8%"><input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:100%;cursor:hand" title="���ѡ��ʼ����" readonly onclick="gfPop.fStartPop(startDate, endDate)"></td>
		    <td width="5%" align="right">����</td>
		    <td width="8%"><input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:100%;cursor:hand" title="���ѡ���ֹ����" readonly onclick="gfPop.fEndPop(startDate, endDate)"></td>
            <td width="5%" align="right">����״̬��</td>
		    <td width="8%"><select size="1" name="orderStatus" style="width:100%"><%=request.getAttribute(AssetsWebAttributes.ORDER_STATUS_OPT)%></select></td>
            <td align="right" width="5%">�ص���룺</td>
            <td width="15%"><input type="text" name="objectCode" style="width:88%" value="<%=dto.getObjectCode()%>"><a href="" onclick="do_SelectLocation(); return false;" title="���ѡ���̵�ص�">[��]</a></td>
        </tr>
        <tr height="15">
            <td width="5%" align="right">ִ���ˣ�</td>
		    <td width="8%"><input type="text" name="implementUser" style="width:75%" value="<%=dto.getImplementUser()%>"><a href="" onclick="do_SelectUser(mainFrm.implementUser); return false;" title="���ѡ��ִ����">[��]</a></td>
            <td width="5%" align="right">�鵵�ˣ�</td>
		    <td width="8%"><input type="text" name="archivedUser" style="width:75%" value="<%=dto.getArchivedUser()%>"><a href="" onclick="do_SelectUser(mainFrm.archivedUser); return false;" title="���ѡ��鵵��">[��]</a></td>
            <td align="right" colspan="4" align="right"><img src="/images/button/export.gif" alt="�������" onclick="do_Export();"><img src="/images/button/query.gif" alt="�����ѯ" onclick="do_Search();"></td>
        </tr>
    </table>
</form>
    <div id="headDiv" style="overflow:hidden;position:absolute;top:97px;left:1px;width:830px">
        <table border="1" width="180%" class="headerTable" cellpadding="0" cellspacing="0">
            <tr height="22">
                <td width="7%" align="center">�������</td>
                <td width="6%" align="center">��ǩ��</td>
                <td width="6%" align="center">�̵㹫˾</td>
                <td width="8%" align="center">�µ����</td>
                <td width="6%" align="center">�µ�����</td>
                <td width="7%" align="center">�ص����</td>
                <td width="10%" align="center">�̵�ص�</td>
                <td width="5%" align="center">��ʼ����</td>
                <td width="4%"  align=center>ִ����</td>
                <td width="4%"  align=center>�鵵��</td>
                <td width="3%"  align=center>ִ������(��)</td>
                <td width="3%"  align=center>����״̬</td>
            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;height:295px;width:847px;position:absolute;top:120px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="180%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
            <%
                Row row = null;
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="20" class="dataTR">
                <td width="7%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("TRANS_NO")%>"></td>
                <td width="6%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("BARCODE")%>"></td>
                <td width="6%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("COMPANY_NAME")%>"></td>
                <td width="8%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("GROUPNAME")%>"></td>
                <td width="6%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("CREATION_DATE")%>"></td>
                <td width="7%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("LOCATION_CODE")%>"></td>
                <td width="10%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("CHECK_LOCATION")%>"></td>
                <td width="5%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("START_TIME")%>"></td>
                <td width="4%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("IMPLEMENT_USER")%>"></td>
                <td width="4%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("ARCHIVED_USER")%>"></td>
                <td width="3%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("IMPLEMENT_DAYS")%>"></td>
                <td width="3%" align="center"><input type="text" class="finput2" readonly value="<%=row.getStrValue("ORDER_STATUS")%>"></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
<%
	if (hasData) {
%>
<div id="navigatorDiv" style="position:absolute;top:410px;left:0;"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script type="text/javascript">
    function initPage(){
        do_SetPageWidth();
    }
    function do_Search() {
        mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
	    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    }
    function do_Export(){
        mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainFrm.submit();
    }
    function do_SelectLocation(){
        with(mainFrm){
            var lookUpName = "LOOK_UP_ADDRESS";
            var dialogWidth = 55;
            var dialogHeight = 30;
            userPara = "organizationId=<%=orgId%>";
            var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
            if (objs) {
                var obj = objs[0];
                objectCode.value = obj["workorderObjectCode"];
            } else {
                objectCode.value = "";
            }
        }
    }
    function do_SelectUser(userBox){
        var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_USER%>";
        var dialogWidth = 44;
        var dialogHeight = 29;
        var userPara = "organizationId=<%=orgId%>";
        var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
        var boxName = userBox.name;
        if (objs) {
            var obj = objs[0];
            userBox.value = obj["userName"];
        } else {
            userBox.value = "";
        }
    }
</script>