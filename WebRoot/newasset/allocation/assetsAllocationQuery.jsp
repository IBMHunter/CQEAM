<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-3-28
  Time: 16:26:53
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%
        RequestParser parser = new RequestParser();
        parser.transData(request);
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) parser.getAttribute(QueryConstant.QUERY_DTO);
        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        String provinceCode = dto.getServletConfig().getProvinceCode();
        String transferType = dto.getTransferType();
        String pageTitle = "";
        String orderNoName = "";
        String deptNameDesc = "";
        SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
        if ("Y".equalsIgnoreCase(user.getIsTd())) {
            pageTitle = "TD��������>>������������(TD)";
        } else {
            pageTitle = "�ʲ���������>>������������";
        }
        orderNoName = "��������";
        deptNameDesc = "��������";
    %>
</head>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('do_SearchOrder();')">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form action="<%=AssetsURLList.ASSETS_ALLOCATION_SERVLET%>" method="post" name="mainFrm">
    <script type="text/javascript">
        printTitleBar("<%=pageTitle%>");
    </script>
    <input type="hidden" name="act" value="">

    <table class="queryTable" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td width="8%" align="right">�������ͣ�</td>
            <td width="12%"><select class="select_style1" name="transferType" style="width:90%"><%=dto.getTransferTypeOption()%></select>&nbsp;<font color="red">*</font></td>
            <td width="8%" align="right"><%=orderNoName%>��</td>
            <td width="12%"><input class="input_style1" type="text" name="transNo" style="width:100%" value="<%=dto.getTransNo()%>"></td>
            <td width="8%" align="right">�������ڣ�</td>
            <td width="23%">
                <input class="input_style2" type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:35%;cursor:hand" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)">
                ����<input class="input_style2" type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:35%;cursor:hand" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)">
            </td>
            <td width="15%" align="right">
                <img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_SearchOrder();">
                <img src="/images/eam_images/new.jpg" alt="�������" onclick="do_CreateOrder();">
            </td>
        </tr>
    </table>

    <div id="transferDiv" style="position:absolute;bottom:0px;top:0px;left:0px;right:0px;z-index:10;visibility:hidden;width:100%;height:100%">
        <table width="100%" height="120">
            <tr>
                <td height="60%" width="40%"></td>
                <td height="60%" width="20%"></td>
                <td height="60%" width="40%"></td>
            </tr>
            <tr>
                <td height="60%" width="40%"></td>
                <td bgcolor="#2984CB" width="20%" align="center"><font color="#FFFFFF">ѡ���ʲ����</font></td>
                <td height="60%" width="40%"></td>
            </tr>
            <tr>
                <td height="60%" width="40%"></td>
                <td bgcolor="#2984CB" width="20%" align="center"><img src="/images/eam_images/ok.jpg" onClick="do_SubmitTransfer();">&nbsp;<img src="/images/eam_images/close.jpg" onClick="do_CloseDiv();"></td>
                <td height="60%" width="40%"></td>
            </tr>
            <tr>
                <td height="60%" width="40%"></td>
                <td bgcolor="#2984CB" width="20%"><select style="width:100%" name="faContentCode"><%=dto.getFaContentOption()%></select></td>
                <td height="60%" width="40%"></td>
            </tr>
        </table>
    </div>
</form>

<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:50px;left:0px;width:100%"
     class="crystalScroll">
    <table border=1 width="100%" class="eamHeaderTable">
        <tr height="20px">
            <td align=center width="18%">��������</td>
            <td align=center width="10%">��������</td>
            <td align=center width="16%">������λ</td>
            <td align=center width="12%">����������</td>
            <td align=center width="10%">������������</td>
            <td align=center width="10%">������״̬</td>
        </tr>
    </table>
</div>
<div style="overflow:scroll;height:68%;width:100%;position:absolute;top:71px;left:0px" align="left"
     onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
    <table width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
        <%
            String transId = "";
            if (rows != null && !rows.isEmpty()) {
                for (int i = 0; i < rows.getSize(); i++) {
                    Row row = rows.getRow(i);
                    transferType = row.getStrValue("TRANSFER_TYPE");
                    int index = ArrUtil.getArrIndex(AssetsDictConstant.TRANS_OPT_VALUES, transferType);
                    transferType = AssetsDictConstant.TRANS_OPT_LABELS[index];
                    transId = row.getStrValue("TRANS_ID");
        %>
        <tr class="dataTR">
            <td width="18%" onclick="showDetail('<%=transId%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
            <td width="10%" onclick="showDetail('<%=transId%>')"><input type="text" class="finput2" readonly value="<%=transferType%>"></td>
            <td width="16%" onclick="showDetail('<%=transId%>')"><input type="text" class="finput" readonly value="<%=row.getValue("FROM_DEPT_NAME")%>"></td>
            <td width="12%" onclick="showDetail('<%=transId%>')"><input type="text" class="finput" readonly value="<%=row.getValue("CREATED")%>"></td>
            <td width="10%" onclick="showDetail('<%=transId%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
            <td width="10%" onclick="showDetail('<%=transId%>')"><input type="text" class="finput" readonly value="<%=row.getValue("TRANS_STATUS_DESC")%>"></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
<%
    if (rows != null && !rows.isEmpty()) {
%>
<div style="position: absolute; bottom:1px; left: 0; right: 20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">
    function do_SearchOrder() {
        if (mainFrm.transferType) {
            mainFrm.transferType.disabled = false;
        }
        mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    }

    function do_CreateOrder() {
        if (mainFrm.transferType) {
            if (mainFrm.transferType.value == "") {
                alert("����ѡ����������ͣ�");
                mainFrm.transferType.focus();
                return;
            }
            do_SubmitTransfer();
        } else {
            var url = "<%=AssetsURLList.ASSETS_TRANS_SERVLET%>?act=<%=AssetsActionConstant.NEW_ACTION%>";
            var style = "width=" + screen.availWidth + ",height=" + screen.availHeight + ",top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
            window.open(url, "transferWin", style);
        }
    }

    function do_SubmitTransfer() {
        var url = "/servlet/com.sino.sinoflow.servlet.NewCase?sf_appName=innerDept";
        url += "&transferType=" + mainFrm.transferType.value;
        var style = "width=" + screen.availWidth + ",height=" + screen.availHeight + ",top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
        window.open(url, "transferWin", style);
    }

    function do_CloseDiv() {
        document.getElementById("transferDiv").style.visibility = "hidden";
    }

    function showDetail(transId) {
        var transType = mainFrm.transType.value;
        var url = "<%=AssetsURLList.ASSETS_TRANS_SERVLET%>?act=<%=AssetsActionConstant.EDIT_ACTION%>&transType=" + transType + "&transId=" + transId;
        var style = "width=" + screen.availWidth + ",height=" + screen.availHeight + ",top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
        window.open(url, 'transferWin', style);
    }

    function do_CancelOrder() {
        if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$) {
            alert("û�����ݣ�����ִ��ָ���Ĳ�����");
            return;
        }
        if (!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value) {
            alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
            return;
        }
        if (confirm("ȷ��Ҫ����ѡ��ĵ����𣿼���������ȷ������ť������������ȡ������ť��")) {
            mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
            mainFrm.submit();
        }
    }
</script>
</html>
