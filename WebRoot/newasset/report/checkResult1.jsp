<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>
<html>

<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>����MIS�嵥</title>
</head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();');" onload="do_SetPageWidth();">
<%
    AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) request.getAttribute(QueryConstant.QUERY_DTO);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = (rows != null && !rows.isEmpty());
    String disabled = request.getParameter("disabled");
    if (disabled == null) {
        disabled = "";
    }
%>
<form name="mainFrm" id="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.CheckResultServletOne">
    <%=WebConstant.WAIT_TIP_MSG%>
    <table width="100%" border="0" class="queryTable">
        <tr>
            <td width="15%">��˾���ƣ�<%=dto.getCompanyName()%></td>
            <%--<td width="15%">��ֹ���ڣ�<%=dto.getEndDate()%>--%>
            <!--</td>-->
            <% if (!disabled.equals("")) { //����
            %>
            <td width="10%" align="center">�ɱ����ģ�</td>
            <%--<td width="25%"><select name="costCenterCode" style="width:100%" <%=dto.getDisabled()%>><%=dto.getCostCenterOpt()%></select></td>--%>
            <td width="25%"><input type="text" name="costCenterName" value="<%=dto.getCostCenterName()%>" readonly class="input_style2"></td>
            <% } %>
            <td width="10%" align="right">�ؼ��֣�</td>
            <td width="25%"><input class="input_style1" type="text" name="checkLocationName" value="<%=dto.getCheckLocationName()%>" style="width:80%"><%--<a href=""  title="���ѡ��ص�" onclick="do_SelectAddress(); return false;">[��]</a>--%>
            </td>
            <td width="10%" align="right"><img border="0" src="/images/eam_images/search.jpg" onclick="do_Search();"></td>
        </tr>
    </table>
    <% if (disabled.equals("")) { //����
            %>
    <input type="hidden" name="costCenterCode" value="<%=dto.getCostCenterCode()%>">
    <% } %>
    <input type="hidden" name="companyName" value="<%=dto.getCompanyName()%>">
    <input type="hidden" name="endDate" value="<%=dto.getEndDate()%>">
    <input type="hidden" name="analyseType" value="<%=AssetsDictConstant.CHECK_RESULT_1%>"><!--���ڿ��Ƶ�������-->
    <input name="act" type="hidden">
    <input name="disabled" type="hidden" value="<%=disabled%>">
    <input name="organizationId" type="hidden" value="<%=dto.getOrganizationId()%>">
    <input name="costCode" type="hidden" value="<%=dto.getCostCode()%>">

    <input type="hidden" name="startDate" value="<%=dto.getStartDate()%>">
    <input type="hidden" name="fromBarcode" value="<%=dto.getFromBarcode()%>">
    <input type="hidden" name="toBarcode" value="<%=dto.getToBarcode()%>">
    <input type="hidden" name="creationDate" value="<%=dto.getCreationDate()%>">
    <input type="hidden" name="lastUpdateDate" value="<%=dto.getLastUpdateDate()%>">
</form>

<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:26px;left:0px;width:100%" class="crystalScroll">

    <table border="1" class="eamDbHeaderTable" width="280%">
        <tr height=20px>
            <td width="4%" align="center" rowspan="2">�ʲ���ǩ</td>
            <td width="7%" align="center" rowspan="2">ʵ�ﲿ��</td>
            <td width="2%" align="center" rowspan="2">ԭʼ����</td>
            <td width="2%" align="center" rowspan="2">ʵ������</td>
            <td width="12%" align="center" colspan="2">�ʲ�����</td>
            <td width="12%" align="center" colspan="2">�ʲ��ͺ�</td>
            <td width="6%" align="center" colspan="2">��������</td>
            <td width="10%" align="center" colspan="2">�ص����</td>
            <td width="18%" align="center" colspan="2">�ص�����</td>
            <td width="6%" align="center" colspan="2">Ա�����</td>
            <td width="4%" align="center" colspan="2">������</td>
            <%
                if (disabled.equals("")) {
            %>
            <td width="4%" align="center" colspan="2">�ɱ����Ĵ���</td>
            <td width="10%" align="center" colspan="2">�ɱ���������</td>
            <%
                }
            %>
            <td width="5%" align="center">�������</td>

        </tr>
        <tr height=20px class="eamDbHeaderTr">
            <td width="6%" align="center">EAMϵͳ</td>
            <td width="6%" align="center">MISϵͳ</td>

            <td width="7%" align="center">EAMϵͳ</td>
            <td width="7%" align="center">MISϵͳ</td>
            <td width="3%" align="center">EAMϵͳ</td>
            <td width="3%" align="center">MISϵͳ</td>

            <td width="5%" align="center">EAMϵͳ</td>
            <td width="5%" align="center">MISϵͳ</td>
            <td width="8%" align="center">EAMϵͳ</td>
            <td width="8%" align="center">MISϵͳ</td>

            <td width="3%" align="center">EAMϵͳ</td>
            <td width="3%" align="center">MISϵͳ</td>
            <td width="2%" align="center">EAMϵͳ</td>
            <td width="2%" align="center">MISϵͳ</td>
            <%
                if (disabled.equals("")) {
            %>
            <td width="2%" align="center">EAMϵͳ</td>
            <td width="2%" align="center">MISϵͳ</td>
            <td width="5%" align="center">EAMϵͳ</td>
            <td width="5%" align="center">MISϵͳ</td>
            <%
                }
            %>
            <td width="5%" align="center">��</td>
        </tr>
    </table>


</div>
<div style="overflow:scroll;height:510px;width:100%;position:absolute;top:67px;left:0px" align="left"
     onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="280%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
        <%
            if (hasData) {
                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
        %>
        <tr height="22">
            <td width="4%"><input type="text" class="finput2" readonly value="<%=row.getValue("MIS_TAG_NUMBER")%>"></td>
            <td width="7%"><input type="text" class="finput2" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
            <td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("ITEM_QTY")%>"></td>
            <td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("ACTUAL_QTY")%>"></td>

            <td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>
            <td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>

            <td width="7%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>
            <td width="7%"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
            <td width="3%"><input type="text" class="finput2" readonly value="<%=row.getValue("START_DATE")%>"></td>
            <td width="3%"><input type="text" class="finput2" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>

            <td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
            <td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSETS_LOCATION_CODE")%>"></td>
            <td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
            <td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION")%>"></td>

            <td width="3%"><input type="text" class="finput2" readonly value="<%=row.getValue("EMPLOYEE_NUMBER")%>"></td>
            <td width="3%"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSIGNED_TO_NUMBER")%>"></td>
            <td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("USER_NAME")%>"></td>
            <td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSIGNED_TO_NAME")%>"></td>
            <%
                if (disabled.equals("")) {
            %>
            <td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("AMS_COST_CODE")%>"></td>
            <td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("MIS_COST_CODE")%>"></td>
            <td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("AMS_COST_NAME")%>"></td>
            <td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("MIS_COST_NAME")%>"></td>
            <%
                }
            %>
            <td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("CHANGED_CONTENT")%>"></td>
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
<div style="position:absolute;top:581px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%>
</div>
<%
    }
%>
</body>

</html>
<script>
    function do_Search() {
        mainFrm.act.value = "<%=AMSActionConstant.QUERY_ACTION%>";
    <%
            if (!disabled.equals("")) { //����
    %>
//        mainFrm.costCenterCode.disabled = false;
    <%
    }
    %>
        mainFrm.submit();
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    }

    function do_SelectAddress() {
        var lookUpName = "LOOK_UP_ADDRESS";
        var dialogWidth = 55;
        var dialogHeight = 30;
        var userPara = "organizationId=<%=dto.getOrganizationId()%>";
        var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
        if (objs) {
            var obj = objs[0];
            mainFrm.checkLocationName.value = obj["toObjectName"];
        }
    }
</script>
