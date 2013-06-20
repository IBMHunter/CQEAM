<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
	String srcPage = parser.getParameter("srcPage");
	AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO)parser.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
//	String provinceCode = dto.getServletConfig().getProvinceCode();
//	String disapleProp = "";
//	if(provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)){
//		disapleProp = "disabled";
//	}
//	String transferType = dto.getTransferType();
    String transType = dto.getTransType();
	String pageTitle = "";
	String orderNoName = "";
	String deptNameDesc = "";
    if (transType.equals(AssetsDictConstant.ASS_SUB)) {//��ֵ���ݲ�ѯ
		if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
			pageTitle = "�ʲ���ֵ����>>������ֵ����";
		} else {
			pageTitle = "�ʲ���ֵ����>>������ֵ����";
		}
		orderNoName = "��ֵ����";
		deptNameDesc = "��ֵ����";
    }
%>
</head>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('query();')">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form action="<%=AssetsURLList.ASSETS_SUB_SERVLET%>" method="post" name="mainFrm">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="transType" value="<%=transType%>">
	<input type="hidden" name="srcPage" value="<%=srcPage%>">
    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td width="10%" align="right"><%=orderNoName%>��</td>
            <td width="20%"><input type="text" name="transNo" style="width:100%" value="<%=dto.getTransNo()%>"></td>
            <td width="10%" align="right">�������ڣ�</td>
            <td width="30%">
				<input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(startDate, endDate);">
				<input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(startDate, endDate);">
            </td>
            <td width="25%" align="right">
			<img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_SearchOrder();">
<%
		if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
%>
            <img src="/images/eam_images/revoke.jpg" alt="�������" onclick="do_CancelOrder();">
<%
		} else {
%>
			<img src="/images/eam_images/new_add.jpg" alt="�������" onclick="do_CreateOrder();">
<%
		}
%>
			</td>


        </tr>
    </table>

</form>

<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:100%">
	<table border=1 width="100%" class="headerTable">
		<tr class=headerTable height="20px">
<%
	if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
%>    
			<td align=center width=4%><input type="checkbox" name="mainCheck" onClick="checkAll(this.name, 'subCheck')"></td>
<%
	}
%>        
			<td align=center width="18%"><%=orderNoName%></td>
			<td align=center width="10%">����״̬</td>
			<td align=center width="26%"><%=deptNameDesc%></td>
			<td align=center width="12%">������</td>
			<td align=center width="10%">��������</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:68%;width:855px;position:absolute;top:66px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	<table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (rows != null && !rows.isEmpty()) {
			for (int i = 0; i < rows.getSize(); i++) {
				Row row=rows.getRow(i);
%>
        <tr class="dataTR" onclick="showDetail('<%=row.getValue("TRANS_ID")%>')">
<%
	if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
%>        
          <td width=4% align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
<%
	}
%>          
			<td width="18%"><input type="text" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
			<td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("TRANS_STATUS_DESC")%>"></td>
			<td width="26%"><input type="text" class="finput" readonly value="<%=row.getValue("FROM_DEPT_NAME")%>"></td>
			<td width="12%"><input type="text" class="finput" readonly value="<%=row.getValue("CREATED")%>"></td>
			<td width="10%"><input type="text" class="finput2" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
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
<div style="position:absolute;top:428px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">

function do_SearchOrder() {
	if(mainFrm.transferType){
		mainFrm.transferType.disabled = false;
	}
    mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_CreateOrder() {
    var transType = mainFrm.transType.value;
	if(mainFrm.transferType){
		if(mainFrm.transferType.value == ""){
			alert("����ѡ����������ͣ�");
			mainFrm.transferType.focus();
			return;
		}
		do_SubmitTransfer();
	} else {
	    var url = "<%=AssetsURLList.ASSETS_SUB_SERVLET%>?act=<%=AssetsActionConstant.NEW_ACTION%>&transType=" + transType;
	    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
	    window.open(url, "transferWin", style);
    }
}


function do_SubmitTransfer(){
	var transType = mainFrm.transType.value;
	var url = "<%=AssetsURLList.ASSETS_SUB_SERVLET%>?act=<%=AssetsActionConstant.NEW_ACTION%>&transType=" + transType;
	url += "&transferType=" + mainFrm.transferType.value;
    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, "transferWin", style);
}

function do_CloseDiv(){
	document.getElementById("transferDiv").style.visibility = "hidden";
}


function showDetail(transId){
    var transType = mainFrm.transType.value;
    var url = "<%=AssetsURLList.ASSETS_SUB_SERVLET%>?act=<%=AssetsActionConstant.EDIT_ACTION%>&transType=" + transType+"&transId="+transId;
    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, 'transferWin', style);
}

function do_CancelOrder(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
		alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	if(confirm("ȷ��Ҫ����ѡ��ĵ����𣿼���������ȷ������ť������������ȡ������ť��")){
	    mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
	    mainFrm.submit();
    }
}
</script>
</html>