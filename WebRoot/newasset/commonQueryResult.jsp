<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<body leftmargin="1" topmargin="0" onload="do_SetPageWidth();">
<%=WebConstant.WAIT_TIP_MSG%>
<%
	AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String treeCategory = dto.getTreeCategory();
	RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = false;
	if(rows != null && !rows.isEmpty()){
		hasData = true;
	}	
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:0px;left:1px;width:990px">
		<table class="headerTable" border="1" width="120%">
			<tr height="20px">
				<td align=center width="13%">�ʲ���ǩ</td>
				<td align=center width="9%">�ʲ����</td>
				<td align=center width="14%">�ʲ�����</td>
				<td align=center width="12%">�ʲ��ͺ�</td>
				<td align=center width="8%">�ʲ�ԭֵ</td>
				<td align=center width="10%">��������</td>
				<td align=center width="8%">��ֵ</td>
				<td align=center width="8%">������</td>
				<td align=center width="15%">���β���</td>
			</tr>
		</table>
	</div>
<%
	if(hasData){
%>
	<div id="dataDiv" style="overflow:scroll;height:90%;width:1007px;position:absolute;top:22px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%		Row row = null;
		String barcode = "";
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
			barcode = row.getStrValue("BARCODE");
%>		
			<tr class="dataTR" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="13%" align="center"><input type="text" class="finput" readonly value="<%=barcode%>"></td>
				<td width="9%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
				<td width="14%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
				<td width="12%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
				<td width="8%" align="right"><input type="text" class="finput" readonly value="<%=row.getValue("COST")%>"></td>
				<td width="10%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>
				<td width="8%" align="right"><input type="text" class="finput" readonly value="<%=row.getValue("DEPRN_COST")%>"></td>
				<td width="8%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
				<td width="15%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
			</tr>
<%
		}
%>
		</table>
	</div>
<div style="position:absolute;top:586px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%		
	}
%>	
<a href="" onclick="do_TurnBack(); return false">����</a>
</body>
</html>
<script>
function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=860,height=495,left=100,top=130";
	window.open(url, winName, style);
}

function do_TurnBack(){
	location.href = "/";
}
</script>
