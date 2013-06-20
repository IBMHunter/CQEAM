<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>
<html>

<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>�����޿�</title>
</head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
    AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) request.getAttribute(QueryConstant.QUERY_DTO);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = (rows != null && !rows.isEmpty());
    String disabled = request.getParameter("disabled");
    if (disabled == null) {
        disabled = "";
    }
%>
<form name="mainFrm" id="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.CheckResultServletFour">
    <%=WebConstant.WAIT_TIP_MSG%>
    <table width="100%" border="0" class="queryTable">
        <tr>
            <td width="15%">��˾���ƣ�<%=dto.getCompanyName()%>
            </td>
            <%--<td width="15%">��ֹ���ڣ�<%=dto.getEndDate()%>--%>
            <!--</td>-->
            <% if (!disabled.equals("")) { //����
            %>
            <td width="10%" align="center">�ɱ����ģ�</td>
            <%--<td width="25%"><select name="costCenterCode" style="width:100%" <%=disabled%>><%=dto.getCostCenterOpt()%></select></td>--%>
            <td width="25%"><input type="text" name="costCenterName" value="<%=dto.getCostCenterName()%>" readonly class="input_style2"></td>
            <% } %>
            <td width="10%" align="center">�ؼ��֣�</td>
            <td width="15%"><input class="input_style1" type="text" name="checkLocationName" value="<%=dto.getCheckLocationName()%>" style="width:80%"><%--<a href=""  title="���ѡ��ص�" onclick="do_SelectAddress(); return false;">[��]</a>--%>
            </td>
            <td width="10%" align="right"><img border="0" src="/images/eam_images/search.jpg" onclick="do_Search();"></td>
        </tr>
    </table>
    <input type="hidden" name="organizationId" value="<%=dto.getOrganizationId()%>">
     <% if (disabled.equals("")) { //����
            %>
    <input type="hidden" name="costCenterCode" value="<%=dto.getCostCenterCode()%>">
       <% } %>
    <input type="hidden" name="companyName" value="<%=dto.getCompanyName()%>">
    <input type="hidden" name="endDate" value="<%=dto.getEndDate()%>">
    <input type="hidden" name="analyseType" value="<%=AssetsDictConstant.CHECK_RESULT_4%>"><!--���ڿ��Ƶ�������-->
    <input name="act" type="hidden">
    <input name="disabled" type="hidden" value="<%=disabled%>">
    <input name="costCode" type="hidden" value="<%=dto.getCostCode()%>">

    <input type="hidden" name="startDate" value="<%=dto.getStartDate()%>">
    <input type="hidden" name="fromBarcode" value="<%=dto.getFromBarcode()%>">
    <input type="hidden" name="toBarcode" value="<%=dto.getToBarcode()%>">
    <input type="hidden" name="creationDate" value="<%=dto.getCreationDate()%>">
    <input type="hidden" name="lastUpdateDate" value="<%=dto.getLastUpdateDate()%>">
</form>

<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:26px;left:0px;width:100%" class="crystalScroll">

    <table class="eamHeaderTable" border="1" width="150%">
        <tr height="22">
            <td width="6%" align="center">�豸����</td>
            <td width="9%" align="center">ʵ�ﲿ��</td>
            <td width="4%" align="center">ԭʼ����</td>
            <td width="4%" align="center">ʵ������</td>

            <td width="8%" align="center">�豸����</td>
            <td width="8%" align="center">�豸�ͺ�</td>
            <td width="7%" align="center">�ص����</td>

            <td width="12%" align="center">�ص�����</td>
            <td width="6%" align="center">Ա����</td>
            <td width="6%" align="center">������</td>
            <td width="12%" align="center">�ɱ�����</td>

            <td width="8%" align="center">��Ŀ���</td>
            <td width="10%" align="center">��Ŀ����</td>
        </tr>
    </table>

</div>
<div style="overflow:scroll;height:510px;width:100%;position:absolute;top:49px;left:0px" align="left"
     onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="150%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
        <%
            if (hasData) {
                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
        %>
        <tr height="22">
            <td width="6%"><input type="text" class="finput2" readonly value="<%=row.getValue("BARCODE")%>"></td>
            <td width="9%"><input type="text" class="finput2" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
            <td width="4%"><input type="text" class="finput2" readonly value="<%=row.getValue("ITEM_QTY")%>"></td>
            <td width="4%"><input type="text" class="finput2" readonly value="<%=row.getValue("ACTUAL_QTY")%>"></td>

            <td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>
            <td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>
            <td width="7%"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>

            <td width="12%"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
            <td width="6%"><input type="text" class="finput2" readonly value="<%=row.getValue("EMPLOYEE_NUMBER")%>"></td>
            <td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("USER_NAME")%>"></td>
            <td width="12%"><input type="text" class="finput" readonly value="<%=row.getValue("COST_CENTER_NAME")%>"></td>

            <td width="8%"><input type="text" class="finput2" readonly value="<%=row.getValue("PROJECT_NUMBER")%>"></td>
            <td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("PROJECT_NAME")%>"></td>
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
<div style="position:absolute;top:580px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%>
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
