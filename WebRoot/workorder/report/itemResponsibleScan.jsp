<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%@ page import="com.sino.ams.constant.*" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.system.trust.dto.AmsMaintainCompanyDTO" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO" %>
<%@ page import="com.sino.ams.workorder.dto.EtsWorkorderDTO" %>
<html>
<head>
<title>��άѲ������</title>
<link rel="stylesheet" type="text/css" href="/WebLibary/css/tab.css">
<script language="javascript" src="/WebLibary/js/tab.js"></script>
<link href="/WebLibary/css/css.css" rel="stylesheet" type="text/css">
<link href="/WebLibary/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
<script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
<script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
<script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
<script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
<script language="javascript">
	var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "window.parent.close()");
	var ArrAction1 = new Array(true, "������ǰ�ص��豸�嵥", "toexcel.gif", "������ǰ�ص��豸�嵥", "do_Export('<%=DictConstant.EXPORT_LOC_ITEM%>')");
	var ArrAction2 = new Array(true, "�������Ѳ����", "toexcel.gif", "�������Ѳ����", "do_Export('<%=DictConstant.EXPORT_SCAN_ITEM%>')");
	var ArrAction3 = new Array(true, "�����������", "toexcel.gif", "�����������", "do_Export('<%=DictConstant.EXPORT_DIFF_ITEM%>')");
	var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2, ArrAction3);
	var ArrSinoViews = new Array();
	var ArrSinoTitles = new Array();
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0 style="overflow:auto" onload="initPage();">
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    
	AmsMaintainCompanyDTO mainCompany = (AmsMaintainCompanyDTO)request.getAttribute(WebAttrConstant.MAINTAIN_CORP_ATTR);
	EtsObjectDTO etsObject = (EtsObjectDTO)request.getAttribute(WebAttrConstant.ETS_OBJECT_DTO);
	EtsWorkorderDTO dto = (EtsWorkorderDTO)request.getAttribute(WebAttrConstant.WORKORDER_DTO);
	
	String locItemURL = "/servlet/com.sino.ams.workorder.servlet.LoctionItemServlet";
	locItemURL += "?act=" + WebActionConstant.DETAIL_ACTION;
	locItemURL += "&workorderObjectNo=" + dto.getWorkorderObjectNo();
	locItemURL += "&exportType=" + DictConstant.EXPORT_LOC_ITEM;
	locItemURL += "&statDate=" + dto.getStartDate();
	locItemURL += "&endDate=" + dto.getEndDate();
	
	String scanItemURL = "/servlet/com.sino.ams.workorder.servlet.LocationScanedItemServlet";
	scanItemURL += "?act=" + WebActionConstant.DETAIL_ACTION;
	scanItemURL += "&workorderObjectNo=" + dto.getWorkorderObjectNo();
	scanItemURL += "&exportType=" + DictConstant.EXPORT_SCAN_ITEM;
	scanItemURL += "&statDate=" + dto.getStartDate();
	scanItemURL += "&endDate=" + dto.getEndDate();
	
	String diffItemURL = "/servlet/com.sino.ams.workorder.servlet.LocItemDiffServlet";
	diffItemURL += "?act=" + WebActionConstant.DETAIL_ACTION;
	diffItemURL += "&workorderObjectNo=" + dto.getWorkorderObjectNo();
	diffItemURL += "&exportType=" + DictConstant.EXPORT_DIFF_ITEM;
	diffItemURL += "&statDate=" + dto.getStartDate();
	diffItemURL += "&endDate=" + dto.getEndDate();
	
%>
<script language="javascript">
	var tabBox = new TabBox("tab");
	tabBox.addtab("respitems", "��ǰ�ص��豸�嵥");
	tabBox.addtab("scaneditems", "���Ѳ����");
	tabBox.addtab("notScaneditems", "�������");
	tabBox.addtab("mainCompany", "��ά��˾");
	tabBox.addtab("etsObject", "�ص���Ϣ");
	printTitleBar("<%=mainCompany.getName()%>��ά��Ϣ");
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
				<iframe id="notScaneditemsFrm" style="width:100%;height:100%"  border="0" frameborder="0"" src="<%=diffItemURL%>"></iframe>
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
	<input type="hidden" name="workorderObjectNo" value="<%=dto.getWorkorderObjectNo()%>">
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
	mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}

</script>