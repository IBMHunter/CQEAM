<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>

<html>
<head>
<title>��ά�̵㱨��</title>
<link rel="stylesheet" type="text/css" href="/WebLibary/css/tab.css">
<script language="javascript" src="/WebLibary/js/tab.js"></script>
<script language="javascript">
	var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "window.parent.close()");
	var ArrAction1 = new Array(true, "������ǰ�ص��豸�嵥", "toexcel.gif", "������ǰ�ص��豸�嵥", "do_Export('<%=AssetsDictConstant.EXPORT_LOC_ITEM%>')");
	var ArrAction2 = new Array(true, "�������Ѳ����", "toexcel.gif", "�������Ѳ����", "do_Export('<%=AssetsDictConstant.EXPORT_SCAN_ITEM%>')");
	var ArrAction3 = new Array(true, "�����������", "toexcel.gif", "�����������", "do_Export('<%=AssetsDictConstant.EXPORT_DIFF_ITEM%>')");
	var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2, ArrAction3);
	var ArrSinoViews = new Array();
	var ArrSinoTitles = new Array();
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0 style="overflow:auto" onload="initPage();">
<%
	AmsMaintainCompanyDTO mainCompany = (AmsMaintainCompanyDTO)request.getAttribute(WebAttrConstant.MAINTAIN_CORP_ATTR);
	EtsObjectDTO etsObject = (EtsObjectDTO)request.getAttribute(WebAttrConstant.ETS_OBJECT_DTO);
	AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)request.getAttribute(WebAttrConstant.WORKORDER_DTO);

	String locItemURL = "/servlet/com.sino.ams.newasset.report.servlet.LoctionItemServlet";
	locItemURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
	locItemURL += "&checkLocation=" + dto.getCheckLocation();
	locItemURL += "&exportType=" + AssetsDictConstant.EXPORT_LOC_ITEM;
	locItemURL += "&statDate=" + dto.getStartDate();
	locItemURL += "&endDate=" + dto.getEndDate();

	String scanItemURL = "/servlet/com.sino.ams.newasset.report.servlet.LocationScanedItemServlet";
	scanItemURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
	scanItemURL += "&checkLocation=" + dto.getCheckLocation();
	scanItemURL += "&exportType=" + AssetsDictConstant.EXPORT_SCAN_ITEM;
	scanItemURL += "&statDate=" + dto.getStartDate();
	scanItemURL += "&endDate=" + dto.getEndDate();

	String diffItemURL = "/servlet/com.sino.ams.newasset.report.servlet.LocItemDiffServlet";
	diffItemURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
	diffItemURL += "&checkLocation=" + dto.getCheckLocation();
	diffItemURL += "&exportType=" + AssetsDictConstant.EXPORT_DIFF_ITEM;
	diffItemURL += "&statDate=" + dto.getStartDate();
	diffItemURL += "&endDate=" + dto.getEndDate();
%>
<script language="javascript">
	var tabBox = new TabBox("tab");
	tabBox.addtab("respitems", "��ǰ�ص��豸�嵥");
	tabBox.addtab("scaneditems", "����̵���");
	tabBox.addtab("notScaneditems", "�������");
	tabBox.addtab("mainCompany", "��ά��˾");
	tabBox.addtab("etsObject", "�ص���Ϣ");
	printTitleBar("<%=mainCompany.getName()%>(<%=dto.getCheckLocationName()%>)��ά��Ϣ");
	printToolBar();
	tabBox.init();
</script>

<table align="center"  style="width:100%;height:87%" border="0" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
	<tr>
		<td style="width:100%;height:100%">
			<div id="respitems" style='display:none'  style="display:none;width:100%;height:100%">
				<iframe id="respitemsFrm" style="width:100%;height:100%"  border="0" frameborder="0" src="<%=locItemURL%>"></iframe>
			</div>
			<div id="scaneditems"  style="display:none;width:100%;height:100%">
				<iframe id="scaneditemsFrm" style="width:100%;height:100%"  border="0" frameborder="0" src="<%=scanItemURL%>"></iframe>
			</div>
			<div id="notScaneditems"  style="display:none;width:100%;height:100%">
				<iframe id="notScaneditemsFrm" style="width:100%;height:100%"  border="0" frameborder="0" src="<%=diffItemURL%>"></iframe>
			</div>
			<div id="mainCompany" style='display:none'>
				<table border="1" width="100%" style="border-collapse: collapse; width:100%;height:100%" bordercolor="#245DD7" id="table1" bgcolor="#E4E4E4" cellpadding="0">
					<tr>
						<td style="width:100%;height:100%">
						    <table width="100%" border="1"  style="width:100%;height:100%" cellpadding="0" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
								<tr>
									<td width="20%" align="right" height="22">��˾���ƣ�</td>
									<td width="80%" height="22"><%=mainCompany.getName()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��˾��ַ��</td>
									<td width="80%" height="22"><%=mainCompany.getAddress()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">���˴���</td>
									<td width="80%" height="22"><%=mainCompany.getLegalRepresentative()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">�칫�绰��</td>
									<td width="80%" height="22"><%=mainCompany.getOfficeTelephone()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��ϵ��������</td>
									<td width="80%" height="22"><%=mainCompany.getContactPeople()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��ϵ�˵绰��</td>
									<td width="80%" height="22"><%=mainCompany.getContactTelephone()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">������룺</td>
									<td width="80%" height="22"><%=mainCompany.getFaxNumber()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��ע��Ϣ��</td>
									<td width="80%" height="22"><%=mainCompany.getRemark()%></td>
								</tr>
								<tr>
									<td colspan="2" height="80%"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div id="etsObject" style='display:none'>
				<table border="1" width="100%" style="border-collapse: collapse; width:100%;height:100%" bordercolor="#245DD7" id="table1" bgcolor="#E4E4E4" cellpadding="0">
					<tr>
						<td style="width:100%;height:100%">
						    <table width="100%" border="1"  style="width:100%;height:100%" cellpadding="0" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
								<tr>
									<td width="20%" align="right" height="22">
									�ص���룺</td>
									<td width="80%" height="22"><%=etsObject.getWorkorderObjectCode()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">
									�ص��ƣ�</td>
									<td width="80%" height="22"><%=etsObject.getWorkorderObjectName()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">
									����λ�ã�</td>
									<td width="80%" height="22"><%=etsObject.getWorkorderObjectLocation()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">
									�������أ�</td>
									<td width="80%" height="22"><%=etsObject.getCountyName()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">
									�ص���ࣺ</td>
									<td width="80%" height="22"><%=etsObject.getObjectCategoryName()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��ע��Ϣ��</td>
									<td width="80%" height="22"><%=etsObject.getRemark()%>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" height="80%"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

		</td>
	</tr>
</table>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.workorder.servlet.ItemResponReportServlet">
	<input type="hidden" name="startDate" value="<%=dto.getStartDate()%>">
	<input type="hidden" name="endDate" value="<%=dto.getEndDate()%>">
	<input type="hidden" name="maintainCompany" value="<%=mainCompany.getCompanyId()%>">
	<input type="hidden" name="workorderObjectNo" value="<%=dto.getCheckLocation()%>">
		<input type="hidden" name="workorderObjectLocation" value="<%=etsObject.getWorkorderObjectLocation()%>">
	<input type="hidden" name="exportType" value="">
	<input type="hidden" name="act" value="">
</form>
</body>
</html>
<script language="javascript">
function initPage() {
	window.focus();
	document.all("respitems").style.display = "";
	document.all("scaneditems").style.display = "none";
	document.all("notScaneditems").style.display = "none";
	document.all("mainCompany").style.display = "none";
	document.all("etsObject").style.display = "none";
	tabBox.inithidetab(1);
}


function do_Export(exportType){
	mainFrm.exportType.value = exportType;
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}

</script>