<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsNoMatchDTO" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsCJYCDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: yuyao
  Date: 2007-12-16
  Time: 20:52:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>�ʲ��۾�����Ԥ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>
<body>
<jsp:include page="/message/MessageProcess"/>
<body leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_search()')">
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    AmsAssetsCJYCDTO dto = (AmsAssetsCJYCDTO) request.getAttribute("AMSBJTRANSNOHDTO");
//    out.print(dto);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
%>
<form action="/servlet/com.sino.ams.newasset.servlet.AmsAssetsCJYCServlet" name="mainForm" method="post">

    <script type="text/javascript">
        printTitleBar("�ʲ��۾�����Ԥ��")
    </script>
    <table border="1" width="100%" id="table1" cellspacing="0" cellpadding="0" style="background-color:#efefef">
        <tr>
            <td width=13% align="right"> �ʲ��˲���</td>
            <td width="22%"> <select name="bookTypeCode" id="bookTypeCode"
                                    style="width:100%"><%=request.getAttribute("BOOK_TYPE_CODE")%>
                            </select></td>
            <td width="15%" align="right">�ʲ�Ӧ������</td>
            <td width="22%" ><select name="catSegment1" style="width:100%">
                                    style="width:100%"><%=request.getAttribute("CATSEGMENT1")%>
              </select></td>
            <td width="15%" align="right">�ʲ�����Ŀ¼��</td>
            <td><input type="text" name="catSegment2" value="<%=dto.getCatSegment2()%>"></td>
            <td width=10% align="center"><img src="/images/eam_images/search.jpg" alt="��ѯ"
                                              onClick="do_search(); return false;"></td>
            <td  width=20% align="left"><img src="/images/eam_images/export.jpg" alt="����EXCEL" onclick="do_export()"></td>
        </tr>
    </table>
    <%--<input type="hidden" name="transId" value="<%=dto.getTransId()%>">--%>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="transType" value="">

    <div id="headDiv" style="overflow:hidden;position:absolute;top:46px;left:1px;width:833px">
		<table class="headerTable" border="1" width="500%">
			<tr height="20px" >
				<td align=center width="3%">Ӧ������</td>
				<td align=center width="4%">����Ŀ¼</td>
				<td align=center width="3%">����</td>
				<td align=center width="1%">1��</td>
				<td align=center width="1%">2��</td>
				<td align=center width="1%">3��</td>
				<td align=center width="1%">4��</td>
				<td align=center width="1%">5��</td>
				<td align=center width="1%">6��</td>
				<td align=center width="1%">7��</td>
				<td align=center width="1%">8��</td>
				<td align=center width="1%">9��</td>
				<td align=center width="1%">10��</td>
				<td align=center width="1%">11��</td>
				<td align=center width="1%">12��</td>
				<td align=center width="1%">13��</td>
				<td align=center width="1%">14��</td>
				<td align=center width="1%">15��</td>
				<td align=center width="1%">16��</td>
				<td align=center width="1%">17��</td>
				<td align=center width="1%">18��</td>
				<td align=center width="1%">19��</td>
				<td align=center width="1%">20��</td>
				<td align=center width="1%">21��</td>
				<td align=center width="1%">22��</td>
				<td align=center width="1%">23��</td>
				<td align=center width="1%">24��</td>
				<td align=center width="1%">25��</td>
				<td align=center width="1%">26��</td>
				<td align=center width="1%">27��</td>
				<td align=center width="1%">28��</td>
				<td align=center width="1%">29��</td>
				<td align=center width="1%">30��</td>
				<td align=center width="1%">31��</td>
				<td align=center width="1%">32��</td>
				<td align=center width="1%">33��</td>
				<td align=center width="1%">34��</td>
				<td align=center width="1%">35��</td>
				<td align=center width="1%">36��</td>
				<td align=center width="1%">37��</td>
				<td align=center width="1%">38��</td>
				<td align=center width="1%">39��</td>
				<td align=center width="1%">40��</td>
                <td align=center width="1%">41��</td>
				<td align=center width="1%">42��</td>
				<td align=center width="1%">43��</td>
				<td align=center width="1%">44��</td>
				<td align=center width="1%">45��</td>
				<td align=center width="1%">46��</td>
				<td align=center width="1%">47��</td>
				<td align=center width="1%">48��</td>
				<td align=center width="1%">49��</td>
				<td align=center width="1%">50��</td>
                <td align=center width="1%">51��</td>
				<td align=center width="1%">52��</td>
				<td align=center width="1%">53��</td>
				<td align=center width="1%">54��</td>
				<td align=center width="1%">55��</td>
				<td align=center width="1%">56��</td>
				<td align=center width="1%">57��</td>
				<td align=center width="1%">58��</td>
				<td align=center width="1%">59��</td>
				<td align=center width="1%">60��</td>
            </tr>

        </table>
	</div>
    <div style="overflow:scroll;height:70%;width:850px;position:absolute;top:66px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="500%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
               <%
                if (rows != null && rows.getSize() > 0) {
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>


            <tr  >
                        <td width="3%" align="center"><%=row.getValue("FA_CAT_NAME_1")%> </td>
                        <td width="4%" align="left"><%=row.getValue("FA_CAT_NAME_2")%></td>
                        <td width="3%" align="center"><%=String.valueOf(row.getValue("FA_CAT_NAME_3"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_1"))%> </td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_2"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_2"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_4"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_5"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_6"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_7"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_8"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_9"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_10"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_11"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_12"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_13"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_14"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_15"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_16"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_17"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_18"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_19"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_20"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_21"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_23"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_24"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_25"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_26"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_27"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_28"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_29"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_30"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_31"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_32"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_34"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_35"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_36"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_37"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_38"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_39"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_40"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_41"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_42"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_43"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_44"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_45"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_46"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_47"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_48"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_49"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_50"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_51"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_52"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_53"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_54"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_55"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_56"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_57"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_58"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_59"))%></td>
                        <td width="1%" align="center"><%=String.valueOf(row.getValue("DEPRN_COST_60"))%></td>
            </tr>
         <%
                    }
                }
            %>
        </table>
    </div>

</form>

</body>
<div style="position:absolute;top:480px;left:0; right:20"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
<%=WebConstant.WAIT_TIP_MSG%>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">
    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainForm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainForm.submit();
    }
    function do_export() {
        mainForm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainForm.submit();
    }
    function do_drop() {
        var transType = document.getElementById("transType")   ;
        var transStatus = document.getElementById("transStatus");
        dropSpecialOption(transType, 'BJBFS');
        //        dropSpecialOption(transStatus, 'APPROVED');
    }
    function do_ShowDetail(transId, transType) {
        mainForm.transId.value = transId;
        mainForm.transType.value = transType;
        var url = "/servlet/com.sino.ams.spare.query.servlet.AmsBjTransQueryServlet?act=<%=WebActionConstant.DETAIL_ACTION%>&transId=" + transId + "&transType=" + transType;
        var popscript = "width=1020,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes";
        window.open(url, "instrum", popscript);
    }
</script>