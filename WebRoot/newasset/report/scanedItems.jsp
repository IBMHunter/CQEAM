<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��˾���̵��豸</title>
</head>

<body leftmargin="0" topmargin="0" onload="do_SetPageWidth()">
<%
    RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	Row row = null;
%>
<div id="headDiv" style="overflow:hidden;position:absolute;top:1px;left:0px;width:990px">
	<table class="headerTable" border="1" width="280%">
		<tr height="20">
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=5%>��ǩ��</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=3%>�豸����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=5%>�豸����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=8%>�豸�ͺ�</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=2%>��λ</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=2%>����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=4%>�ʲ�����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=6%>�ص����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=10%>�ص���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=10%>�����ص�</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=6%>��������</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=4%>������</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=9%>���β���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=4%>ʹ����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=5%>ʹ�ò���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=8%>��Ŀ����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width=8%>��Ӧ��</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:506px;width:1007px;position:absolute;top:21px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="280%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
	if(rows!= null && !rows.isEmpty()){
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
			<tr onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
				<td width=5%><input type="text" class="finput2" readonly value="<%=row.getValue("BARCODE")%>"></td>
		        <td width=3%><input type="text" class="finput2" readonly value="<%=row.getValue("ITEM_CATEGORY_NAME")%>"></td>
		        <td width=5%><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>
		        <td width=8%><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>
		        <td width=2%><input type="text" class="finput2" readonly value="<%=row.getValue("ITEM_UNIT")%>"></td>
		        <td width=2%><input type="text" class="finput3" readonly value="<%=row.getValue("YEARS")%>"></td>
		        <td width=4%><input type="text" class="finput2" readonly value="<%=row.getValue("FINANCE_PROP_VALUE")%>"></td>
		        <td width=6%><input type="text" class="finput2" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
		        <td width=10%><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
		        <td width=10%><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_LOCATION")%>"></td>
		        <td width=6%><input type="text" class="finput" readonly value="<%=row.getValue("COUNTY_NAME")%>"></td>
		        <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
		        <td width=9%><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_DEPT_NAME")%>"></td>
		        <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("MAINTAIN_USER")%>"></td>
		        <td width=5%><input type="text" class="finput" readonly value="<%=row.getValue("MAINTAIN_DEPT_NAME")%>"></td>
		        <td width=8%><input type="text" class="finput" readonly value="<%=row.getValue("PROJECT_NAME")%>"></td>
		        <td width=8%><input type="text" class="finput" readonly value="<%=row.getValue("VENDOR_NAME")%>"></td>
			</tr>
<%
		}
	}
%>
	</table>
</div>
<div style="position:absolute;top:564px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
</body>
</html>
