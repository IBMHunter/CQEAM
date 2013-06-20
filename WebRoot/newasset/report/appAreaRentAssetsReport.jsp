<%@ page import="com.sino.ams.newasset.report.dto.SpecialAssetsReportDTO"%>
<%@ page import="java.math.BigDecimal" %>
<%--
  Author:	����
  Date: 2009-6-2
  Time: 11:42:08
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
<script language="javascript" src="/WebLibary/js/LookUp.js"></script>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    String yearOption = parser.getAttribute(WebAttrConstant.LAST_FIVE_YEAR_OPTION).toString();
    String monthOption = parser.getAttribute(WebAttrConstant.FULL_MONTH_OPTION).toString();
    SpecialAssetsReportDTO dto = (SpecialAssetsReportDTO)request.getAttribute(QueryConstant.QUERY_DTO);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
%>
<title>Ӧ������ͳ��(����)</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="initPage();">
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.AppAreaRentAssetsReportServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("�ʲ���������>>Ӧ������ͳ��(����)")
</script>
	<table width="100%" border="0" class="queryHeadBg">
		<tr>
			<td width="4%" align="right">��˾��</td>
			<td width="11%" align="left">
                <select name="organizationId" style="width:90%"><%=request.getAttribute(AssetsWebAttributes.CITY_OPTION)%></select>
            </td>
            <%--<td width="6%" align="right">����ڼ䣺</td>--%>
            <%--<td width="12%" align="left">--%>
				<%--<input style="width:75%" type="text" name="accountingPeriod" value="<%=StrUtil.nullToString(dto.getAccountingPeriod())%>" title="���ѡ������" readonly class="noEmptyInput" onclick="gfPop.fPopCalendar(accountingPeriod)">--%>
            <%--</td>--%>
            <%--<td width="4%" align="right">��ݣ�</td>
            <td width="4%"><select style="width:100%" name="year"><%=yearOption%></select></td>
            <td width="4%" align="right">�·ݣ�</td>
            <td width="3%"><select style="width:100%" name="month"><%=monthOption%></select></td>--%>
            <td width="6%" align="right">����ڼ䣺</td>
            <td width="14%" colspan="3"><select style="width:25%" name="year"><%=yearOption%></select>��<select style="width:20%" name="month"><%=monthOption%></select></td>
			<td width="10%"  align="right">
                <img src="/images/eam_images/search.jpg" onclick="do_Search();">&nbsp;&nbsp;&nbsp;
                <img src="/images/eam_images/export.jpg" onclick="do_Export();" alt="������Excel">
            </td>
		</tr>
	</table>
	<input name="act" type="hidden">
	<input name="companyName" type="hidden">
    <input name="specialAssetsType" value="<%=dto.getSpecialAssetsType()%>">
</form>


<div id="headDiv" style="overflow:hidden;position:absolute;top:44px;left:1px;width:200%">
	<table class="headerTable" border="1" width="200%">
        <tr height="22">
			<td width="8%" align="center" rowspan="2" height="10">��˾</td>
			<td width="10%" align="center" rowspan="2" height="10">Ӧ������</td>
			
            <td width="6%" align="center" height="10" rowspan="2">�ʲ�����</td>
			<td width="6%" align="center" height="10" rowspan="2">ԭֵ</td>
            <td width="6%" align="center" height="10" rowspan="2">�ۼ��۾�</td>
            <td width="6%" align="center" height="10" rowspan="2">��ֵ</td>
            <td width="6%" align="center" height="10" rowspan="2">�ۼƼ�ֵ׼��</td>
            <td width="6%" align="center" height="10" rowspan="2">����</td>
			
			<td width="6%" align="center" height="10" rowspan="2">�����۾�</td>
            <td width="6%" align="center" height="10" rowspan="2">ռ�����ʲ��ܶ����</td>
            <%--<td width="6%" align="center" height="10" rowspan="2">������������</td>--%>
            <td width="6%" align="center" height="10" rowspan="2">��ȥ��ͬ��������</td>
           
            <td width="10%" align="center" colspan="3" height="5">��3��������</td>
        </tr>
        <tr>
            <td width="4%" align="center" height="5"><%=dto.getYear().equals("") ? "2006" : Integer.parseInt(dto.getYear())-3 %></td>
            <td width="4%" align="center" height="5"><%=dto.getYear().equals("") ? "2007" : Integer.parseInt(dto.getYear())-2 %></td>
            <td width="4%" align="center" height="5"><%=dto.getYear().equals("") ? "2008" : Integer.parseInt(dto.getYear())-1 %></td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:72%;width:200%;position:absolute;top:79px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="200%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if(hasData){
		Row row = null;
        String ASSETS_SPECIES="";

        Double sumCost = 0d;
        Long sumCount = 0L;
        Double sumDeprnReserve = 0d;
        Double sumNetBookValue = 0d;
        Double sumImpairmentReserve = 0d;
        Double sumLimitValue = 0d;
        Double sumPtdDeprn = 0d;
        Float sumRate = 0f;

        boolean bkColor = false;
        boolean isNew = false;
        for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
            if (dto.getSpecialAssetsType().equals("")) {
            }
%>
                <tr height="22">
                    <td width="8%" align="right"><%=row.getValue("COMPANY")%></td>
                    <td width="10%" align="right"><%=row.getValue("FA_DESCRIPTION")%></td>
                    
                    <td width="6%" align="right"><%=row.getValue("SUM_COUNT")%></td>                    
                    <td width="6%"  align="right" ><%=row.getValue("SUM_COST")%></td>		<!-- ԭֵ -->
					<td width="6%" align="right"><%=row.getValue("DEPRN_RESERVE")%></td>	<!-- �ۼ��۾� -->
					<td width="6%" align="right"><%=row.getValue("NET_BOOK_VALUE")%></td>   <!-- �ʲ���ֵ -->
					<td width="6%" align="right"><%=row.getValue("IMPAIRMENT_RESERVE")%></td>   <!-- �ۼƼ�ֵ׼�� -->
		            <td width="6%" align="right"><%=row.getValue("LIMIT_VALUE")%></td>		<!-- �ʲ����� -->
		            <td width="6%" align="right"><%=row.getValue("PTD_DEPRN")%></td>
                    <td width="6%" align="right"><%=row.getValue("ASSETS_RATE")%></td>
                    <%--<td width="6%" align="right"><%=row.getValue("LAST_MONTH_RATE")%></td>--%>
                    <td width="6%" align="right"><%=row.getValue("LAST_YEAR_RATE")%></td>
                    <td width="4%" align="right"><%=row.getValue("THREE_YEER_THREE_RATE")%></td>
                    <td width="4%" align="right"><%=row.getValue("THREE_YEER_TWO_RATE")%></td>
                    <td width="4%" align="right"><%=row.getValue("THREE_YEER_ONE_RATE")%></td>
                </tr>
<%
                sumCount += Long.parseLong(row.getValue("SUM_COUNT").toString());
                if(row.getValue("SUM_COST") != null && !"".equals(row.getValue("SUM_COST"))){
                    sumCost += Double.parseDouble(row.getValue("SUM_COST").toString());
                }
                if(row.getValue("DEPRN_RESERVE") != null && !"".equals(row.getValue("DEPRN_RESERVE"))){
                    sumDeprnReserve += Double.parseDouble(row.getValue("DEPRN_RESERVE").toString());
                }
                if(row.getValue("NET_BOOK_VALUE") != null && !"".equals(row.getValue("NET_BOOK_VALUE"))){
                    sumNetBookValue += Double.parseDouble(row.getValue("NET_BOOK_VALUE").toString());
                }
                if(row.getValue("IMPAIRMENT_RESERVE") != null && !"".equals(row.getValue("IMPAIRMENT_RESERVE"))){
                    sumImpairmentReserve += Double.parseDouble(row.getValue("IMPAIRMENT_RESERVE").toString());
                }
                if(row.getValue("LIMIT_VALUE") != null && !"".equals(row.getValue("LIMIT_VALUE"))){
                    sumLimitValue += Double.parseDouble(row.getValue("LIMIT_VALUE").toString());
                }
                if(row.getValue("PTD_DEPRN") != null && !"".equals(row.getValue("PTD_DEPRN"))){
                    sumPtdDeprn += Double.parseDouble(row.getValue("PTD_DEPRN").toString());
                }
                if(!row.getValue("ASSETS_RATE").equals("") && !row.getValue("ASSETS_RATE").equals("%")){
                    sumRate += Float.parseFloat(row.getValue("ASSETS_RATE").toString().substring(0, row.getValue("ASSETS_RATE").toString().indexOf("%")));
                }
		}
%>
		<tr>
			<td colspan = "2" height= "22" align = "left">�ϼƣ�</td>

			<td align="right"><%=sumCount %></td>
			<td align="right"><%=BigDecimal.valueOf(sumCost).toString().length() - BigDecimal.valueOf(sumCost).toString().indexOf(".") >3 ? BigDecimal.valueOf(sumCost).toString().substring(0, BigDecimal.valueOf(sumCost).toString().indexOf(".") + 3) : BigDecimal.valueOf(sumCost) %></td>
			<td align="right" ><%=BigDecimal.valueOf(sumDeprnReserve).toString().length() - BigDecimal.valueOf(sumDeprnReserve).toString().indexOf(".") >3 ? BigDecimal.valueOf(sumDeprnReserve).toString().substring(0, BigDecimal.valueOf(sumDeprnReserve).toString().indexOf(".") + 3) : BigDecimal.valueOf(sumDeprnReserve) %></td>
			<td align="right" ><%=BigDecimal.valueOf(sumNetBookValue).toString().length() - BigDecimal.valueOf(sumNetBookValue).toString().indexOf(".") >3 ? BigDecimal.valueOf(sumNetBookValue).toString().substring(0, BigDecimal.valueOf(sumNetBookValue).toString().indexOf(".") + 3) : BigDecimal.valueOf(sumNetBookValue) %></td>
			<td align="right" ><%=BigDecimal.valueOf(sumImpairmentReserve).toString().length() - BigDecimal.valueOf(sumImpairmentReserve).toString().indexOf(".") >3 ? BigDecimal.valueOf(sumImpairmentReserve).toString().substring(0, BigDecimal.valueOf(sumImpairmentReserve).toString().indexOf(".") + 3) : BigDecimal.valueOf(sumImpairmentReserve) %></td>
			<td align="right" ><%=BigDecimal.valueOf(sumLimitValue).toString().length() - BigDecimal.valueOf(sumLimitValue).toString().indexOf(".") >3 ? BigDecimal.valueOf(sumLimitValue).toString().substring(0, BigDecimal.valueOf(sumLimitValue).toString().indexOf(".") + 3) : BigDecimal.valueOf(sumLimitValue) %></td>
			<td align="right" ><%=BigDecimal.valueOf(sumPtdDeprn).toString().length() - BigDecimal.valueOf(sumPtdDeprn).toString().indexOf(".") >3 ? BigDecimal.valueOf(sumPtdDeprn).toString().substring(0, BigDecimal.valueOf(sumPtdDeprn).toString().indexOf(".") + 3) : BigDecimal.valueOf(sumPtdDeprn) %></td>
			<td align="right"><%=sumRate.toString().length() - sumRate.toString().indexOf(".") > 3 ? sumRate.toString().substring(0, sumRate.toString().indexOf(".")+4) :sumRate %>%</td>
		</tr>
<%
	}
%>
	</table>
</div>
<%
	if(hasData){
%>
<div style="position:absolute;top:91%;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function initPage(){
	do_SetPageWidth();
}

function do_Search(){
//	var accountingPeriod = mainFrm.accountingPeriod.value;
//	if(accountingPeriod != null && accountingPeriod != ""){
		mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
		mainFrm.target = "_self";
		mainFrm.submit();
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
//	} else {
//		alert("����ڼ䲻��Ϊ�գ�");
//		mainFrm.periodName.focus();
//	}
}

function do_Export() {
//	var accountingPeriod = mainFrm.accountingPeriod.value;
//	if(accountingPeriod != null && accountingPeriod != ""){
	    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
		mainFrm.target = "_self";
	    mainFrm.submit();
//	} else {
//		alert("����ڼ䲻��Ϊ�գ�");
//		mainFrm.periodName.focus();
//	}
}
</script>