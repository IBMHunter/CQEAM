<%@ page contentType="text/html; charset=GBK" language="java"
	errorPage=""%>
<%@page import="com.sino.ams.workorder.dto.EtsItemDTO"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
		<%
			RequestParser reqParser = new RequestParser();
			reqParser.transData(request);
			DTOSet lines = (DTOSet) reqParser
					.getAttribute(QueryConstant.QUERY_DTO);

			//60,38
		%>
		<title>�㹺�ʲ�ת�������ӡ</title>
		<style>
		<!--
		.title{
			font-weight: bold;
		}
		.content{
			font-weight: bold;
			font-size: 8px;
		}
		//-->
		</style>
	</head>
	
	<body leftmargin="0" topmargin="0" style="overflow:auto">
	<jsp:include page="/public/print.jsp" flush="false" />
		<table align="center" width="99%" border="0">
			<%
				EtsItemDTO line = null;
				for (int i = 0; i < lines.getSize(); i++) {
					line = (EtsItemDTO) lines.getDTO(i);
			%>
			<tr height="22">
				<td  >
					<img src="/images/CMMC.JPG" style="vertical-align:middle;" width="22px" height="22px" />&nbsp;<font class="title" ><%=line.getCompanyName()%></font>
				</td>
			</tr>
			<tr>
				<td>
					<table width="220" border="1" align="left" bordercolor="#666666" >
						<tr height="22">
							<td width="60" >�ʲ����ƣ� </td><td><font class="content"><%=line.getItemName()%></font></td>
						</tr>
						<tr height="22">
							<td>����ͺţ� </td><td><font class="content"><%=line.getItemSpec()%></font></td>
						</tr>
						<tr height="22">
							<td>�������ڣ� </td><td><font class="content"><%=line.getStartDate2()%></font></td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<img src="<%=line.getBarcodeImg()%>" width="146px"
									height="28px" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		
		
	</body>
</html>
 
