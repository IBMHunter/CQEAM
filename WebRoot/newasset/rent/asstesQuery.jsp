<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
<head>
	<title>���ݲ�ѯ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/AppStandard.js"></script>
</head>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
	AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO)parser.getAttribute(QueryConstant.QUERY_DTO);
	String provinceCode = dto.getServletConfig().getProvinceCode();
	String disapleProp = "";
	if(provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)){
		disapleProp = "disabled";
	}
	String transferType = dto.getTransferType();
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    String transType = dto.getTransType();
	String pageTitle = "";
	String orderNoName = "";
	String deptNameDesc = "";
    if (transType.equals("ASS-WASTEFORECAST")) {
		pageTitle = "Ԥ���ϵ��ݲ�ѯ";
		orderNoName = "Ԥ���ϵ���";
		deptNameDesc = "Ԥ���ϲ���";
    } else if(transType.equals("ASS-REPEAL")){
    	pageTitle = "Ԥ���ϳ������ݲ�ѯ";
		orderNoName = "Ԥ���ϳ�������";
		deptNameDesc = "Ԥ���ϳ�������";
    }else if (transType.equals("ASS-REPAIR")) {
		pageTitle = "���޵��ݲ�ѯ";
		orderNoName = "���޵���";
		deptNameDesc = "���޲���";
    } else if (transType.equals("ASS-REPAIRRETURN")) {
		pageTitle = "���޷������ݲ�ѯ";
		orderNoName = "���޷�������";
		deptNameDesc = "���޷�������";
    } else if (transType.equals(AssetsDictConstant.ASS_SELL)){
    	pageTitle = "���۵��ݲ�ѯ";
		orderNoName = "���۵���";
		deptNameDesc = "���۲���";
    } else if (transType.equals(AssetsDictConstant.ASS_RENT)){
    	pageTitle = "���ⵥ�ݲ�ѯ";
		orderNoName = "���ⵥ��";
		deptNameDesc = "���ⲿ��";
    } else if (transType.equals("ASS-DONATE")){
    	pageTitle = "�������ݲ�ѯ";
		orderNoName = "��������";
		deptNameDesc = "��������";
    } else if (transType.equals("ASS-EXCHANGE") || transType.equals("ASS-REPLACEMENT")) {
    	pageTitle = "�û����ݲ�ѯ";
		orderNoName = "�û�����";
		deptNameDesc = "�û�����";
    } else if (transType.equals("ASS-BORROW")) {
    	pageTitle = "���õ��ݲ�ѯ";
    	orderNoName = "���õ���";
    	deptNameDesc = "���ò���";
    } else if (transType.equals("ASS-RETURN")) {
    	pageTitle = "�ͻ����ݲ�ѯ";
    	orderNoName = "�ͻ�����";
    	deptNameDesc = "�ͻ�����"; 
    } else {
    	pageTitle = "�������ݲ�ѯ";
    	orderNoName = "��������";
    	deptNameDesc = "��������"; 
    }
	String srcPage = parser.getParameter("srcPage");
%>

<body onkeydown="autoExeFunction('do_SearchOrder()');" onload="initPage();">
<%=WebConstant.WAIT_TIP_MSG%>
<form action="/servlet/com.sino.ams.newasset.rent.servlet.AssetsHeaderServlet" name="mainFrm" method="post">
    <script type="text/javascript">
        printTitleBar("<%=pageTitle%>")
    </script>
 <input type="hidden" name="act" value="">
 <input type="hidden" name="transType" value="<%=transType%>"> 
<table border="0" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr style="height:24px">
            <td width="10%" align="right"><%=orderNoName%>��</td>
            <td width="20%"><input class="input_style1" type="text" name="transNo" style="width:100%" value="<%=dto.getTransNo()%>"></td>
            <td width="10%" align="right">�������ڣ�</td>
            <td width="30%">
				<input class="input_style1" type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(startDate, endDate);">
				<input class="input_style1" type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(startDate, endDate);">
            </td>
            <td width="25%" align="right">
			<img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_SearchOrder();">
			<img src="/images/eam_images/export.jpg" title="�������" onclick="do_ExportOrder();">
			</td>
        </tr>
    </table>
</form>

<%
	if(!transType.equals(AssetsDictConstant.ASS_RED)){
%>
     <div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:49px;left:0px;width:100%">
   	  <table  border="1" width="100%" class="eamHeaderTable" cellpadding="0" cellspacing="0">
        <tr height="23px">
            <td align=center width="18%"><%=orderNoName%></td>
            <td align=center width="10%">����״̬</td>
            <td align=center width="26%"><%=deptNameDesc%></td>
            <td align=center width="12%">������</td>
            <td align=center width="10%">��������</td>
        </tr>
      </table>
    </div>

	<div id="dataDiv" style="overflow:scroll;height:72%;width:100%;position:absolute;top:71px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                if (rows != null && !rows.isEmpty()) {
                for (int i = 0; i < rows.getSize(); i++) {
                    Row row=rows.getRow(i);
            %>
            
            <tr class="dataTR" onclick="showDetail('<%=row.getValue("TRANS_ID")%>')">
                <td width="18%>"><input type="text" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
                <td width="10%>"><input type="text" class="finput" readonly value="<%=row.getValue("TRANS_STATUS_DESC")%>"></td>
                <td width="26%>"><input type="text" class="finput" readonly value="<%=row.getValue("FROM_DEPT_NAME")%>"></td>
                <td width="12%>"><input type="text" class="finput" readonly value="<%=row.getValue("CREATED")%>"></td>
                <td width="10%>"><input type="text" class="finput2" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>

<%
	} 
    if (rows != null && !rows.isEmpty()) {
%>
<div id="pageNaviDiv" style="position:absolute;top:87%;left:0;"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>

</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>

<script type="text/javascript">
function initPage(){
	do_SetPageWidth();
}

function do_SearchOrder() {
	if(mainFrm.transferType){
		mainFrm.transferType.disabled = false;
	}
    mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function showDetail(transId){
    var transType = mainFrm.transType.value;
    var url = "/servlet/com.sino.ams.newasset.rent.servlet.AssetsHeaderServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&transType=" + transType+"&transId="+transId;
    var factor = 0.92;
    var width = window.screen.availWidth * factor;
    var height = window.screen.availHeight * factor;
    var left = window.screen.availWidth * (1 - factor)/ 2;
    var top = window.screen.availHeight * (1 - factor)/ 10;
    var style = "width="
            + width
            + "px,height="
            + height
            + "px,top="
            + top
            + "px,left="
            + left
            + "px,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, 'transferWin', style);
}

function do_ExportOrder(){
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}
</script>