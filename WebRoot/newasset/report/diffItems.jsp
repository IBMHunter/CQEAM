<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
	<script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
<title>��ά��˾�����εص�</title>
</head>

<body leftmargin="0" topmargin="0" onload="do_SetPageWidth()">
<%
    RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	Row row = null;
%>
<div id="headDiv" style="overflow:hidden;position:absolute;top:0px;left:1px;width:990px">
	<table class="headerTable" border="1" width="500%">
		<tr height="20">
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="34%" colspan="11">������Ϣ</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="32%" colspan="10">ϵͳ��Ϣ����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="32%" colspan="10">�̵���Ϣ</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%" colspan="2">״̬</td>
		</tr>
		<tr height="20">
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">��ǩ��</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">������λ</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="1%">����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">��������</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">�ص����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="5%">�ص���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="5%">����λ��</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">��������</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="5%">��Ŀ����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">��Ŀ���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">�ʲ�����</td>

			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="3%">�豸����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">�豸����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">�豸�ͺ�</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">��������</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="3%">���̴���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">���β���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">������</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">Ա����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">ʹ�ò���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">ʹ����</td>

			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="3%">�豸����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">�豸����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">�豸�ͺ�</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">��������</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="3%">���̴���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">���β���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">������</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">Ա����</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="4%">ʹ�ò���</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="2%">ʹ����</td>

			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="1%">ϵͳ</td>
			<td align=center background="/images/bg_011.gif" style="color: #FFFFFF" width="1%">�̵�</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:86%;width:1007px;position:absolute;top:41px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="500%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
	if(rows!= null && !rows.isEmpty()){
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
			<tr onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
				<td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("BARCODE")%>"></td>
				<td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_UNIT")%>"></td>
				<td width="1%"><input type="text" class="finput3" readonly value="<%=row.getValue("YEARS")%>"></td>
				<td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("START_DATE")%>"></td>
				<td width="4%"><input type="text" class="finput2" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
				<td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
				<td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_LOCATION")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("COUNTY_NAME")%>"></td>
				<td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("PROJECT_NAME")%>"></td>
				<td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("PROJECT_NUMBER")%>"></td>
				<td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("FINANCE_PROP_VALUE")%>"></td>

				<td width="3%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_CATEGORY_NAME")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("VENDOR_NAME")%>"></td>
				<td width="3%"><input type="text" class="finput" readonly value="<%=row.getValue("VENDOR_NUMBER")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_DEPT_NAME")%>"></td>
				<td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
				<td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("EMPLOYEE_NUMBER")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("MAINTAIN_DEPT_NAME")%>"></td>
				<td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("MAINTAIN_USER")%>"></td>
				
				<td width="3%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_ITEM_CATEGORY_NAME")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_ITEM_NAME")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_ITEM_SPEC")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_VENDOR_NAME")%>"></td>
				<td width="3%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_VENDOR_NUMBER")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_RESPONSIBILITY_DEPT_NAME")%>"></td>
				<td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_RESPONSIBILITY_USER_NAME")%>"></td>
				<td width="2%"><input type="text" class="finput2" readonly value="<%=row.getValue("SCAN_EMPLOYEE_NUMBER")%>"></td>
				<td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_MAINTAIN_DEPT_NAME")%>"></td>
				<td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("SCAN_MAINTAIN_USER")%>"></td>

				<td width="1%"><input type="text" class="finput2" readonly value="<%=row.getValue("SYS_STATUS")%>"></td>
				<td width="1%"><input type="text" class="finput2" readonly value="<%=row.getValue("SCAN_STATUS")%>"></td>
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
