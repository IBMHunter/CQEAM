<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="/WebLibary/css/tab.css">
	<script language="javascript" src="/WebLibary/js/tab.js"></script>
<title>�̵�������</title>
<script language="javascript">
	var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "window.parent.close()");
	var ArrAction1 = new Array(true, "��������MIS�嵥", "toexcel.gif", "��������MIS�嵥", "do_Export('<%=AssetsDictConstant.CHECK_RESULT_1%>')");
	var ArrAction3 = new Array(true, "����δ��MIS�嵥", "toexcel.gif", "��������MIS�嵥", "do_Export('<%=AssetsDictConstant.CHECK_RESULT_3%>')");
	var ArrAction6 = new Array(true, "��������PDAɨ���嵥", "toexcel.gif", "��������PDAɨ���嵥", "do_Export('<%=AssetsDictConstant.CHECK_RESULT_6%>')");
	var ArrAction4 = new Array(true, "���������޿�����", "toexcel.gif", "���������޿�����", "do_Export('<%=AssetsDictConstant.CHECK_RESULT_4%>')");
	var ArrAction5 = new Array(true, "�����п�����(���̵ص�)", "toexcel.gif", "�����п�����(���̵ص�)", "do_Export('<%=AssetsDictConstant.CHECK_RESULT_5%>')");
	var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction3,ArrAction6, ArrAction4, ArrAction5);
	var ArrSinoViews = new Array();
	var ArrSinoTitles = new Array();
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0 style="overflow:auto" onload="initPage();">
<%
	AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String costCenterCode = dto.getCostCode();
	String disabled = "";
	if(!costCenterCode.equals("")){
		disabled = "disabled";
	}
	String commonURL = "";
	commonURL += "?act=";
	commonURL += "&organizationId=" + dto.getOrganizationId();
	commonURL += "&companyName=" + dto.getCompanyName();
	commonURL += "&costCenterCode=" + costCenterCode;
	commonURL += "&disabled=" + disabled;
	commonURL += "&endDate=" + dto.getEndDate();
    commonURL += "&costCode=" + dto.getCostCode();
    commonURL += "&costCenterName=" + dto.getCostCenterName();

    commonURL += "&startDate=" + dto.getStartDate();
    commonURL += "&fromBarcode=" + dto.getFromBarcode();
    commonURL += "&toBarcode=" + dto.getToBarcode();
    commonURL += "&creationDate=" + dto.getCreationDate();
    commonURL += "&lastUpdateDate=" + dto.getLastUpdateDate();

    String checkResultURL1 = "/servlet/com.sino.ams.newasset.report.servlet.CheckResultServletOne";
	checkResultURL1 += commonURL;
	checkResultURL1 += "&analyseType=" + AssetsDictConstant.CHECK_RESULT_1;

	String checkResultURL3 = "/servlet/com.sino.ams.newasset.report.servlet.CheckResultServletThree";
	checkResultURL3 += commonURL;
	checkResultURL3 += "&analyseType=" + AssetsDictConstant.CHECK_RESULT_3;

    String checkResultURL6 = "/servlet/com.sino.ams.newasset.report.servlet.CheckResultServletSix";
	checkResultURL6 += commonURL;
	checkResultURL6 += "&analyseType=" + AssetsDictConstant.CHECK_RESULT_6;

	String checkResultURL4 = "/servlet/com.sino.ams.newasset.report.servlet.CheckResultServletFour";
	checkResultURL4 += commonURL;
	checkResultURL4 += "&analyseType=" + AssetsDictConstant.CHECK_RESULT_4;

	String checkResultURL5 = "/servlet/com.sino.ams.newasset.report.servlet.CheckResultServletFive";
	checkResultURL5 += commonURL;
	checkResultURL5 += "&analyseType=" + AssetsDictConstant.CHECK_RESULT_5;
%>
<script language="javascript">
	var tabBox = new TabBox("tab");
	tabBox.addtab("checkResult1", "����MIS�嵥");
	tabBox.addtab("checkResult3", "δ��MIS�嵥");
	tabBox.addtab("checkResult6", "����PDAɨ���嵥");
	tabBox.addtab("checkResult4", "�����޿�");
	tabBox.addtab("checkResult5", "�п�����(���̵ص�)");
	<%--printTitleBar("������<%=dto.getEndDate()%><%=dto.getCompanyName()%>�̵�����Ϣ");--%>
    printTitleBar("<%=dto.getCompanyName()%>�̵�����Ϣ");
	printToolBar();
	tabBox.init();
</script>

<table align="center" width='100%' border="0" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF" style="width:100%;height:87%">
	<tr>
		<td style="width:100%;height:100%">
			<div id="checkResult1" style='display:none'>
				<iframe id="checkResultFrm1" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL1%>"></iframe>
			</div>
			<div id="checkResult3" style="display:none">
				<iframe id="checkResultFrm3" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL3%>"></iframe>
			</div>
			<div id="checkResult4" style="display:none">
				<iframe id="checkResultFrm4" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL4%>"></iframe>
			</div>
            <div id="checkResult6" style="display:none">
				<iframe id="checkResultFrm6" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL6%>"></iframe>
			</div>
			<div id="checkResult5" style="display:none">
				<iframe id="checkResultFrm5" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL5%>"></iframe>
			</div>
		</td>
	</tr>
</table>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.CheckResultFrmServlet">
	<input type="hidden" name="organizationId" value="<%=dto.getOrganizationId()%>">
	<input type="hidden" name="endDate" value="<%=dto.getEndDate()%>">
	<input type="hidden" name="costCenterCode" value="">
    <input type="hidden" name="costCode" value="">
    <input type="hidden" name="checkLocationName" value="">
	<input type="hidden" name="analyseType" value=""><!--���ڿ��Ƶ�������-->
	<input type="hidden" name="act" value="<%=AssetsActionConstant.EXPORT_ACTION%>">
	<input type="hidden" name="disabled" value="<%=disabled%>">

    <input type="hidden" name="startDate" value="<%=dto.getStartDate()%>">
    <input type="hidden" name="fromBarcode" value="<%=dto.getFromBarcode()%>">
    <input type="hidden" name="toBarcode" value="<%=dto.getToBarcode()%>">
    <input type="hidden" name="creationDate" value="<%=dto.getCreationDate()%>">
    <input type="hidden" name="lastUpdateDate" value="<%=dto.getLastUpdateDate()%>">
</form>
</body>
</html>

<script language="javascript">
function initPage() {
	window.focus();
	document.all("checkResult1").style.display = "";
	document.all("checkResult3").style.display = "none";
	document.all("checkResult6").style.display = "none";
	document.all("checkResult4").style.display = "none";
	document.all("checkResult5").style.display = "none";
	tabBox.inithidetab(1);
}

function do_Export(analyseType){
	var strDiv = "";
	var tabName = "";
	var strFrm = "";
	if(analyseType == "<%=AssetsDictConstant.CHECK_RESULT_1%>"){
		strDiv = "checkResult1";
		strFrm = "checkResultFrm1";
		tabName = "����MIS�嵥";
	} else if(analyseType == "<%=AssetsDictConstant.CHECK_RESULT_3%>"){
		strDiv = "checkResult3";
		strFrm = "checkResultFrm3";
		tabName = "δ��MIS�嵥";
	} else if(analyseType == "<%=AssetsDictConstant.CHECK_RESULT_4%>"){
		strDiv = "checkResult4";
		strFrm = "checkResultFrm4";
		tabName = "�����޿�";
	} else if(analyseType == "<%=AssetsDictConstant.CHECK_RESULT_5%>"){
		strDiv = "checkResult5";
		strFrm = "checkResultFrm5";
		tabName = "�п�����(���̵ص�)";
	}else if(analyseType == "<%=AssetsDictConstant.CHECK_RESULT_6%>"){
		strDiv = "checkResult6";
		strFrm = "checkResultFrm6";
		tabName = "����PDAɨ���嵥";
	}
	tabName += "ѡ�";
	var divStyle = document.all(strDiv).style.display;
	if(divStyle == "none"){
		alert("��" + tabName + "��û�д��ڻ״̬������ִ��ָ����������");
		return;
	}
    var wind = window.frames[strFrm];
	var subFrm = wind.document.mainFrm;
    document.mainFrm.costCode.value = subFrm.costCode.value;
    document.mainFrm.checkLocationName.value = subFrm.checkLocationName.value;
	document.mainFrm.analyseType.value = analyseType;
//	document.mainFrm.submit();
    mainFrm.target = "_blank";
    mainFrm.submit();
    mainFrm.target = "_self";
}
</script>