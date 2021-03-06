<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.*" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  JSP Name :    discardedAssetsReport.jsp
  Author:		李轶
  Date: 2009-6-1
  Time: 18:32:55
--%>
<html>
<head><title>未处置资产统计</title>
</head>
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    String action = reqParser.getParameter("act");
    String organizationId = (String) request.getAttribute(WebAttrConstant.CITY_OPTION);
    AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) request.getAttribute(QueryConstant.QUERY_DTO);
    String yearOption = reqParser.getAttribute(WebAttrConstant.LAST_FIVE_YEAR_OPTION).toString();
    String monthOption = reqParser.getAttribute(WebAttrConstant.FULL_MONTH_OPTION).toString();
//    String fromDate = StrUtil.nullToString(request.getParameter("fromDate"));
//    String toDate = StrUtil.nullToString(request.getParameter("toDate"));
%>

<body onkeydown="autoExeFunction('do_search()');" onload="initPage();">
<%=WebConstant.WAIT_TIP_MSG%>
<form action="/servlet/com.sino.ams.newasset.report.servlet.DiscardedAssetsReportServlet" name="mainFrm" method="post">
    <script type="text/javascript">
        printTitleBar("资产基础报表>>未处置资产统计")
    </script>
<table bgcolor="#E9EAE9" border="0" style="width:100%;TABLE-LAYOUT:fixed&#59;word-break:break-all">
        <tr>
            <td width="4%" align="right">公司：</td>
            <td width="11%" >
            	<select name="organizationId" style="width:80%" onchange="getDeptOpt();"><%=request.getAttribute(AssetsWebAttributes.CITY_OPTION)%></select>
            </td>
            <td width="4%" align="right">责任部门：</td>
            <td width="17%" align="left">
           		<select name="responsibilityDept" style="width:90%"><%=request.getAttribute(AssetsWebAttributes.DEPT_OPTIONS)%></select>
            </td>
	        <%--<td width="4%" align="right">会计期间：</td>--%>
            <%--<td width="8%" align="left">--%>
                <%--<input style="width:100%" type="text" name="accountingPeriod" value="<%=StrUtil.nullToString(dto.getAccountingPeriod())%>" title="点击选择会计期" readonly class="noEmptyInput" onclick="gfPop.fPopCalendar(accountingPeriod)">--%>
            <%--</td>--%>
            <%--<td width="4%" align="right">年份：</td>
            <td width="3%"><select style="width:100%" name="year"><%=yearOption%></select></td>
            <td width="4%" align="right">月份：</td>
            <td width="3%"><select style="width:100%" name="month"><%=monthOption%></select></td>--%>
            <td width="5%" align="right">会计期间：</td>
            <td width="14%" colspan="3"><select style="width:40%" name="year"><%=yearOption%></select>—<select style="width:35%" name="month"><%=monthOption%></select></td>
        </tr>
        <tr>
        	<td width="5%" align="right">资产名称：</td>
            <td width="8%" align="left"><input type="text" name="itemName" style="width:80%" value="<%=dto.getItemName()%>"></td>
            <td width="5%" align="right">标签号：</td>
            <td width="8%" align="left"><input type="text" name="barcode" style="width:90%" value="<%=dto.getBarcode()%>"></td>
        
            <td width="10%" align="right" colspan = "4">
                <img src="/images/eam_images/search.jpg" onclick="do_Search();" alert = "点击查询">&nbsp;&nbsp;&nbsp;
                <img src="/images/eam_images/export.jpg" onclick="do_Export();" alt="导出到Excel">
            </td>
        </tr>
    </table>
    <input type="hidden" name="act" value="<%=action%>">
    <input type="hidden" name="itemStatus" value="TO_DISCARD">
</form>
     <div id="headDiv" style="overflow:hidden;position:absolute;top:67px;left:1px;width:150%">
   	  <table  border="1" width="150%" class="headerTable" cellpadding="0" cellspacing="0">
        <tr height="22">
        
            <td width="5%" align="center">所属公司</td>
            <td width="15%" align="center">责任部门</td>
            <td width="5%" align="center">资产名称</td>
            <td width="5%" align="center">规格型号</td>
            <td width="4%" align="center">资产编号</td>            
            <td width="5%" align="center">标签号</td>
            <td width="3%" align="center">资产数量</td>
            
            <td width="4%" align="center">价值</td>
            <td width="5%" align="center">占当期资产总数比重</td>
            <td width="5%" align="center">占当期资产总额比重</td>
            <td width="6%" align="center">较去年同期总量增长率</td>
            <td width="6%" align="center">较去年同期总额增长率</td>
            
<%--            <td width="3%" align="center">累计折旧</td>--%>
<%--            <td width="3%" align="center">净值</td>--%>
<%--            <td width="4%" align="center">累计减值准备</td>--%>
<%--            <td width="3%" align="center">净额</td>--%>
<%--            <td width="3%" align="center">当期折旧</td>--%>
        </tr>
      </table>
    </div>

	<div id="dataDiv" style="overflow:scroll;height:72%;width:150%;position:absolute;top:90px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="150%" border="1" bordercolor="#666666" id="dataTab" >
            <%
                RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        	    boolean hasData = (rows != null && !rows.isEmpty());
                if (rows != null && !rows.isEmpty()) {
                    Row row = null;
                    
                    Double sumCost = 0d;
                    Long sumCount = 0L;
                    Float sumCountRate = 0f;
                    Float sumCostRate = 0f;
                    Float sumLastYearCountRate = 0f;
                    Float sumLastYearCostRate = 0f;
                    
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr class="dataTR" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="5%" align= "center"><%=row.getValue("COMPANY")%></td>
                <td width="15%" align= "center"><%=row.getValue("DEPT_NAME")%></td>
                <td width="5%" align = "center"><%=row.getValue("ITEM_NAME")%></td>
                <td width="5%" align = "center"><%=row.getValue("ITEM_SPEC")%></td>
                <td width="4%" align= "center"><%=row.getValue("ASSET_ID")%></td>
                
                <td width="5%" align= "center"><%=row.getValue("BARCODE")%></td>
                <td width="3%" align= "right"><%=row.getValue("ITEM_QTY")%></td>                
                <td width="4%" align= "right"><%=row.getValue("COST")%></td>
                <td width="5%" align= "right"><%=row.getValue("ASSETS_RATE_COUNT")%></td>
                <td width="5%" align= "right"><%=row.getValue("ASSETS_RATE")%></td>
                <td width="6%" align= "right"><%=row.getValue("SUM_LAST_YEAR_RATE_COUNT")%></td>
                <td width="6%" align= "right"><%=row.getValue("SUM_LAST_YEAR_RATE")%>
<%--                <td width="3%" align= "right"><%=row.getValue("DEPRN_RESERVE")%></td>--%>
<%--                <td width="3%" align= "right"><%=row.getValue("NET_BOOK_VALUE")%></td>--%>
<%--                <td width="4%" align= "right"><%=row.getValue("IMPAIRMENT_RESERVE")%></td>--%>
<%--                <td width="3%" align= "right"><%=row.getValue("LIMIT_VALUE")%></td>--%>
<%--                <td width="3%" align = "right"><%=row.getValue("PTD_DEPRN")%></td>--%>
            </tr>
            <%
				sumCount += Long.parseLong(row.getValue("ITEM_QTY").toString());
				if(row.getValue("COST") != null && !"".equals(row.getValue("COST"))){
					sumCost += Double.parseDouble(row.getValue("COST").toString());
				}
				if(!row.getValue("ASSETS_RATE_COUNT").equals("") && !row.getValue("ASSETS_RATE_COUNT").equals("%")){
					sumCountRate += Float.parseFloat(row.getValue("ASSETS_RATE_COUNT").toString().substring(0, row.getValue("ASSETS_RATE_COUNT").toString().indexOf("%")));
				}
				if(!row.getValue("ASSETS_RATE").equals("") && !row.getValue("ASSETS_RATE").equals("%")){
					sumCostRate += Float.parseFloat(row.getValue("ASSETS_RATE").toString().substring(0, row.getValue("ASSETS_RATE").toString().indexOf("%")));
				}
				//if(!row.getValue("ASSETS_RATE").equals("") && !row.getValue("ASSETS_RATE").equals("%")){
				//	sumLastYearCountRate += Float.parseFloat(row.getValue("ASSETS_RATE").toString().substring(0, row.getValue("ASSETS_RATE").toString().indexOf("%")));
				//}
				//if(!row.getValue("ASSETS_RATE").equals("") && !row.getValue("ASSETS_RATE").equals("%")){
				//	sumLastYearCostRate += Float.parseFloat(row.getValue("ASSETS_RATE").toString().substring(0, row.getValue("ASSETS_RATE").toString().indexOf("%")));
				//}
		}
%>
		<tr height="22">
			<td width="8%" colspan = "6" height= "22" align = "left">合计：</td>			
			<td align="right"><%=sumCount %></td>
			<td align="right"><%=BigDecimal.valueOf(sumCost).toString().length() - BigDecimal.valueOf(sumCost).toString().indexOf(".") >3 ? BigDecimal.valueOf(sumCost).toString().substring(0, BigDecimal.valueOf(sumCost).toString().indexOf(".") + 3) : BigDecimal.valueOf(sumCost) %></td>	
			<td align="right"><%=sumCountRate.toString().length() - sumCountRate.toString().indexOf(".") > 3 ? sumCountRate.toString().substring(0, sumCountRate.toString().indexOf(".")+4) :sumCountRate %>%</td>
            <td align="right"><%=sumCostRate.toString().length() - sumCostRate.toString().indexOf(".") > 3 ? sumCostRate.toString().substring(0, sumCostRate.toString().indexOf(".")+4) :sumCostRate %>%</td>
		</tr>
<%        
	}
%>
        </table>
    </div>


<%
    if (hasData) {
%>
<div id="navigatorDiv" style="position:absolute;top:92%;left:0;"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>

<jsp:include page="/message/MessageProcess"/>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<%--<iframe name="downFrm" src="" style="display:none"></iframe>--%>

<script type="text/javascript">
	function initPage(){
		do_SetPageWidth();
		do_TransData();
	}

	function do_TransData(){
		var transTarget = null;
		if(!parent.updateDataFrm.document){
			return;
		}
		if(!parent.updateDataFrm.document.mainFrm){
			return;
		}
		if(!parent.updateDataFrm.document.mainFrm.checkedData){
			return;
		}
		transTarget = parent.updateDataFrm.document.mainFrm.checkedData;
		if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
			transTarget.value = "";
		} else {
			var barcodes = new String(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value);
			barcodes = replaceStr(barcodes, "BARCODE:", "");
			barcodes = replaceStr(barcodes, "$$$", "  ");
			parent.updateDataFrm.document.mainFrm.checkedData.value = barcodes;
		}
	}

    function do_Search() {
//    	var accountingPeriod = mainFrm.accountingPeriod.value;
//    	if(accountingPeriod == null || accountingPeriod == ""){
//    		alert("会计期间不能为空！");
//    		return;
//    	}
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
    }

	var clickNum = 0;
    function do_Export() {
//    	var accountingPeriod = mainFrm.accountingPeriod.value;
//    	if(accountingPeriod == null || accountingPeriod == ""){
//    		alert("会计期间不能为空！");
//    		return;
//    	}
    	clickNum++;
    	if(clickNum > 1){
    		alert("已执行导出命令，请您不要重复点击！");
    		return;
    	}
        mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainFrm.submit();
    }
	var xmlHttp;
	function getDeptOpt() {
	    var organizationId = document.getElementById("organizationId").value ;
	    var url = "/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet?act=CHOOSE_GROUP&organizationId=" + organizationId;
	    xmlHttp = GetXmlHttpObject(setDeptOpt);
	    xmlHttp.open('POST', url, true);
	    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
	    xmlHttp.send("a=1");
	}
	
	function setDeptOpt() {
	    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
	        if (xmlHttp.status == 200) {//成功
	            var resText = xmlHttp.responseText;
	            var resArray = resText.parseJSON();
	            if (resArray == "ERROR") {
	                alert(resText);
	            } else {
	                var littleCategoryObj = document.getElementById("responsibilityDept");
	                littleCategoryObj.length = 0;
	                var emptyOption = new Option("--请选择--", "");
	                littleCategoryObj.add(emptyOption)
	                if (resArray.length > 0) {
	                    var retStr;
	                    for (var i = 0; i < resArray.length; i++) {
	                        retStr = resArray[i];
	                        var arr = retStr.split("$");
	                        var option = new Option(arr[1], arr[0]);
	                        littleCategoryObj.add(option)
	                    }
	                }
	            }
	            xmlHttp = null;
	        }
	    }
	}
</script>