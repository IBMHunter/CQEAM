<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<!-- JSP Name ��  misDeprnAssetsQuery.jsp -->
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>MIS�ʲ��۾ɲ�ѯ</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
	AmsDeprnAssetsDTO dto = (AmsDeprnAssetsDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
	String pageTitle = request.getParameter("pageTitle");
	if(pageTitle == null){
		pageTitle = "MISϵͳ�ӿ�-->>MIS�ʲ��۾ɲ�ѯ";
	}
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.servlet.MisDeprnAssetsQueryServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>")
</script>
	<table border="0" width="100%" bgcolor="#EFEFEF">
		<tr height="20">
			<td width="6%" align="right"> ��˾���ƣ�</td>
			<td width="15%">
				<select name="organizationId" id="organizationId" style="width:100%" size="1"><%=dto.getOrgOption()%></select>
			</td>
			<td width="6%" align="right"> �ʲ��˲����룺</td>
			<td width="15%">
				<input style="width:100%" name="bookTypeCode" value="<%=dto.getBookTypeCode() %>" type="text" ></td>
			<td width="7%" align="right"> �ʲ���ǩ�ţ�</td>
			<td width="20%">
				<input style="width:80%" name="tagNumber" value="<%=dto.getTagNumber() %>" type="text" size="20" ></td>
		</tr>
		<tr height="20">
			<td width="8%" align="right"> �ʲ����ƣ�</td>
			<td width="15%">
				<input style="width:100%" name="description" value="<%=dto.getDescription()%>" type="text" >
			</td>
			<td width="8%" align="right"> �۾��ڼ䣺</td>
			<td width="15%">
				<input style="width:100%" name="periodName" value="<%=dto.getPeriodName() %>" type="text" size="20" ></td>
			<td width="8%" align="right" colspan=2>
				<img src="/images/eam_images/search.jpg" alt="��ѯ" onClick="do_Search(); return false;">&nbsp;&nbsp;&nbsp;
				<img src="/images/eam_images/export.jpg" alt="����EXCEL" onclick="do_Export()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
	<input readonly name="act" type="hidden">
</form>
<div id="headDiv" style="overflow:hidden;position:absolute;top:70px;left:1px;width:100%">
	<table class="headerTable" border="1" width="390%">
		<tr height="22">
			<td width="3%" align="center">�ʲ��˲�����</td>
			<td width="3%" align="center">�ʲ��˲�����</td>
			<td width="3%" align="center">�ʲ�Ψһ��ʶ</td>
			<td width="3%" align="center">�ʲ���ǩ��</td>
			<td width="3%" align="center">�̶��ʲ����</td>
			<td width="3%" align="center">Ӧ������</td>
			<td width="5%" align="center">�ʲ����</td>
			<td width="3%" align="center">�̶��ʲ�����</td>
			<td width="3%" align="center">������˾</td>
<%--			<td width="3%" align="center">����һ������</td>--%>
			
			<td width="2%" align="center">�̶��ʲ�ԭֵ</td>
			<td width="2%" align="center">�ۼ��۾�</td>
			<td width="2%" align="center">��ֵ</td>
			<td width="2%" align="center">�ۼƼ�ֵ׼��</td>
			<td width="2%" align="center">����</td>
			<td width="2%" align="center">���¼�ֵ׼��</td>
			<td width="2%" align="center">�����ֵ׼��</td>
			
			
			<td width="2%" align="center">�����۾�</td>
			<td width="2%" align="center">�����۾�</td>
			<td width="2%" align="center">�۾��ڼ�</td>
			<td width="2%" align="center">�۾�ʣ������</td>
			<td width="2%" align="center">����������</td>
		</tr>
	</table>
</div>		
<div id="dataDiv" style="overflow:scroll;height:65%;width:100%;position:absolute;top:93px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="390%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>	
		<tr height="22">
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("BOOK_TYPE_CODE")%>"></td>
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("BOOK_TYPE_NAME")%>"></td>
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSET_ID")%>"></td>
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("TAG_NUMBER")%>"></td>
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
			
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("FA_CATEGORY1")%>"></td>
			<td width="5%" align="center"><input class="finput" readonly value="<%=row.getValue("FA_CATEGORY2")%>"></td>
			
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("DESCRIPTION")%>"></td>
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("SEGMENT1")%>"></td>
<%--			<td width="9%" align="center"><input class="finput" readonly value="<%=row.getValue("SEGMENT2")%>"></td>--%>

			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("COST")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("DEPRN_RESERVE")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("NET_BOOK_VALUE")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("IMPAIRMENT_RESERVE")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("LIMIT_VALUE")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("PTD_IMPAIRMENT")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("YTD_IMPAIRMENT")%>"></td>
			
			
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("PTD_DEPRN")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("YTD_DEPRN")%>"></td>		
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("PERIOD_NAME")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("DEPRN_LEFT_MONTH")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("LAST_UPDATE_DATE")%>"></td>
		</tr>
<%
		}
	}
%>	
	</table>
</div>
<%
	if(hasData){
%>
	<div style="position:absolute;top:450px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function do_Search(){
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

var num = 0;
function do_Export() {
	num += 1;
	if(num > 1){
		alert("��ִ�е�������벻Ҫ�ظ������");
		return;
	}
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";    
    mainFrm.submit();
}

</script>