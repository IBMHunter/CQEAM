<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="/WebLibary/css/tab.css">
	<script language="javascript" src="/WebLibary/js/tab.js"></script>
<title>�̵����</title>
<script language="javascript">
	var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "window.parent.close()");
	var ArrAction1 = new Array(true, "�������εص�", "toexcel.gif", "�������εص�", "do_Export('<%=DictConstant.EXPORT_RES_LOC%>')");
	var ArrAction2 = new Array(true, "�������̵�ص�", "toexcel.gif", "�������̵�ص�", "do_Export('<%=DictConstant.EXPORT_SCAN_LOC_Y%>')");
	var ArrAction3 = new Array(true, "����δ�̵�ص�", "toexcel.gif", "����δ�̵�ص�", "do_Export('<%=DictConstant.EXPORT_SCAN_LOC_N%>')");
	var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2, ArrAction3);
	var ArrSinoViews = new Array();
	var ArrSinoTitles = new Array();
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0 style="overflow:auto" onload="initPage();">
<%
	AmsMaintainCompanyDTO company = (AmsMaintainCompanyDTO)request.getAttribute(WebAttrConstant.MAINTAIN_CORP_ATTR);
	AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)request.getAttribute(WebAttrConstant.WORKORDER_DTO);

	String resLocURL = "/servlet/com.sino.ams.newasset.report.servlet.ResAllLocReportServlet";
	resLocURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
	resLocURL += "&maintainCompany=" + company.getCompanyId();
	resLocURL += "&startDate=" + dto.getStartDate();
	resLocURL += "&endDate=" + dto.getEndDate();
	resLocURL += "&exportType=" + DictConstant.EXPORT_RES_LOC;

	String scanLocURL = "/servlet/com.sino.ams.newasset.report.servlet.ResScanedReportServlet";
	scanLocURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
	scanLocURL += "&maintainCompany=" + company.getCompanyId();
	scanLocURL += "&startDate=" + dto.getStartDate();
	scanLocURL += "&endDate=" + dto.getEndDate();
	scanLocURL += "&exportType=" + DictConstant.EXPORT_SCAN_LOC_Y;


	String notScanLocURL = "/servlet/com.sino.ams.newasset.report.servlet.ResNotScanedRptServlet";
	notScanLocURL += "?act=" + AssetsActionConstant.DETAIL_ACTION;
	notScanLocURL += "&maintainCompany=" + company.getCompanyId();
	notScanLocURL += "&startDate=" + dto.getStartDate();
	notScanLocURL += "&endDate=" + dto.getEndDate();
	notScanLocURL += "&exportType=" + DictConstant.EXPORT_SCAN_LOC_N;

%>
<script language="javascript">
	var tabBox = new TabBox("tab");
	tabBox.addtab("totalLocs", "���εص�");
	tabBox.addtab("scanedLocs", "���̵�ص�");
	tabBox.addtab("notScanedLocs", "δ�̵�ص�");
	tabBox.addtab("company", "��ά��˾");
	printTitleBar("<%=company.getName()%>�̵���Ϣ");
	printToolBar();
	tabBox.init();
</script>

<table align="center" width='100%' border="0" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF" style="width:100%;height:87%">
	<tr>
		<td style="width:100%;height:100%">
			<div id="totalLocs" style='display:none'>
			<iframe id="respLocsFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=resLocURL%>"></iframe>
			</div>
			<div id="scanedLocs" style='display:none'>
			<iframe id="scanedLocsFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=scanLocURL%>"></iframe>
			</div>
			<div id="notScanedLocs" style="display:none">
			<iframe id="notScanedLocsFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=notScanLocURL%>"></iframe>
			</div>
			<div id="company" style='display:none'>
				<table border="1" width="100%" style="border-collapse: collapse; width:100%;height:100%" bordercolor="#245DD7" id="table1" bgcolor="#E4E4E4" cellpadding="0">
					<tr>
						<td style="width:100%;height:100%">
						    <table width="100%" border="1"  style="width:100%;height:100%" cellpadding="0" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
								<tr>
									<td width="20%" align="right" height="22">��˾���ƣ�</td>
									<td width="80%" height="22"><%=company.getName()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��˾��ַ��</td>
									<td width="80%" height="22"><%=company.getAddress()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">���˴���</td>
									<td width="80%" height="22"><%=company.getLegalRepresentative()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">�칫�绰��</td>
									<td width="80%" height="22"><%=company.getOfficeTelephone()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��ϵ��������</td>
									<td width="80%" height="22"><%=company.getContactPeople()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��ϵ�˵绰��</td>
									<td width="80%" height="22"><%=company.getContactTelephone()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">������룺</td>
									<td width="80%" height="22"><%=company.getFaxNumber()%></td>
								</tr>
								<tr>
									<td width="20%" height="22" align="right">��ע��Ϣ��</td>
									<td width="80%" height="22"><%=company.getRemark()%></td>
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
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.LocResReportServlet">
	<input type="hidden" name="startDate" value="<%=dto.getStartDate()%>">
	<input type="hidden" name="endDate" value="<%=dto.getEndDate()%>">
	<input type="hidden" name="maintainCompany" value="<%=company.getCompanyId()%>">
	<input type="hidden" name="exportType" value="">
	<input type="hidden" name="act" value="">
</form>
</body>
</html>

<script language="javascript">
function initPage() {
	window.focus();
	document.all("totalLocs").style.display = "";
	document.all("scanedLocs").style.display = "none";
	document.all("notScanedLocs").style.display = "none";
	document.all("company").style.display = "none";
	tabBox.inithidetab(1);
}

function do_Export(exportType){
	mainFrm.exportType.value = exportType;
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}

function do_ShowScanInfo(objectNo, objectName){
	var screenHeight = window.screen.height;
	var screenWidth = window.screen.width;
	var url = "/servlet/com.sino.ams.newasset.report.servlet.ItemResponReportServlet"
	url += "?act=<%=AssetsActionConstant.DETAIL_ACTION%>";
	url += "&organizationId=<%=dto.getOrganizationId()%>";
	url += "&maintainCompany=<%=company.getCompanyId()%>";
	url += "&objectCategory=<%=dto.getObjectCategory()%>";
	url += "&startDate=<%=dto.getStartDate()%>";
	url += "&endDate=<%=dto.getStartDate()%>";
	url += "&checkLocation=" + objectNo;
	url += "&checkLocationName=" + objectName;
	var winstyle = "width=" + screenWidth + ",height=" + screenHeight + ",top=0,left=0,help=no,status=no,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,center=yes";
	window.open(url, "itemScanWin", winstyle);
}	
</script>