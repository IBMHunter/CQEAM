<%@ page import="com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-9-17
  Time: 18:11:31
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/WebLibary/css/tab.css">
	<script language="javascript" src="/WebLibary/js/tab.js"></script>
<script language="javascript">
	var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "window.parent.close()");
	var ArrAction1 = new Array(true, "������ʵһ�±���", "toexcel.gif", "������ʵһ�±���", "do_Export(1)");
    var ArrAction2 = new Array(true, "������ʵ��������", "toexcel.gif", "������ʵ��������", "do_Export(2)");
    var ArrAction3 = new Array(true, "���������޿�����", "toexcel.gif", "���������޿�����", "do_Export(3)");
    var ArrAction4 = new Array(true, "�����п����ﱨ��", "toexcel.gif", "���������޿�����", "do_Export(4)");
    var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2, ArrAction3, ArrAction4);
	var ArrSinoViews = new Array();
	var ArrSinoTitles = new Array();
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0 style="overflow:auto" onload="do_initPage();">
<%
	AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String batchNo = dto.getBatchNo();
	String company = dto.getCompany();

	String commonURL = "";
	commonURL += "?act=" + AssetsActionConstant.QUERY_ACTION;
	commonURL += "&batchNo=" + batchNo;
	commonURL += "&company=" + company;

    String checkResultURL1 = "/servlet/com.sino.ams.sampling.report.servlet.AssetsSamplingReportOneServlet";
	checkResultURL1 += commonURL;

	String checkResultURL2 = "/servlet/com.sino.ams.sampling.report.servlet.AssetsSamplingReportTwoServlet";
	checkResultURL2 += commonURL;

	String checkResultURL3 = "/servlet/com.sino.ams.sampling.report.servlet.AssetsSamplingReportThreeServlet";
	checkResultURL3 += commonURL;

    String checkResultURL4 = "/servlet/com.sino.ams.sampling.report.servlet.AssetsSamplingReportFourServlet";
	checkResultURL4 += commonURL;

    String pageTitle = company + "�ĳ�����";
%>
<script language="javascript">
	var tabBox = new TabBox("tab");
	tabBox.addtab("checkResult1", "��ʵһ��");
    tabBox.addtab("checkResult2", "��ʵ����");
    tabBox.addtab("checkResult3", "�����޿�");
    tabBox.addtab("checkResult4", "�п�����");
    printTitleBar("<%=pageTitle%>");
	printToolBar();
	tabBox.init();
</script>

<table align="center" width='100%' border="0" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF" style="width:100%;height:89%">
	<tr>
		<td style="width:100%;height:100%">
			<div id="checkResult1" style='display:none'>
				<iframe id="checkResultFrm1" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL1%>"></iframe>
			</div>
			<div id="checkResult2" style="display:none">
				<iframe id="checkResultFrm2" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL2%>"></iframe>
			</div>
			<div id="checkResult3" style="display:none">
				<iframe id="checkResultFrm3" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL3%>"></iframe>
			</div>
            <div id="checkResult4" style="display:none">
				<iframe id="checkResultFrm4" style="width:100%;height:100%" border="0" frameborder="0" src="<%=checkResultURL4%>"></iframe>
			</div>
        </td>
	</tr>
</table>
<form name="mainFrm" method="post" action="">
	<input type="hidden" name="batchNo" value="<%=batchNo%>">
	<input type="hidden" name="act" value="<%=AssetsActionConstant.EXPORT_ACTION%>">
</form>
</body>
</html>

<script language="javascript">
function do_initPage() {
	window.focus();
	document.all("checkResult1").style.display = "block";
	document.all("checkResult2").style.display = "none";
	document.all("checkResult3").style.display = "none";
	document.all("checkResult4").style.display = "none";
    tabBox.inithidetab(1);
}

function do_Export(exportType){
	var strDiv = "";
	var tabName = "";
	var strFrm = "";
	var action = "";
	if(exportType == "1"){
		strDiv = "checkResult1";
		strFrm = "checkResultFrm1";
		tabName = "��ʵһ��";
		action="/servlet/com.sino.ams.sampling.report.servlet.AssetsSamplingReportOneServlet"
	} else if(exportType == "2"){
		strDiv = "checkResult2";
		strFrm = "checkResultFrm2";
		tabName = "��ʵ����";
		action="/servlet/com.sino.ams.sampling.report.servlet.AssetsSamplingReportTwoServlet"
	} else if(exportType == "3"){
		strDiv = "checkResult3";
		strFrm = "checkResultFrm3";
		tabName = "�����޿�";
		action="/servlet/com.sino.ams.sampling.report.servlet.AssetsSamplingReportThreeServlet"
	} else if(exportType == "4"){
		strDiv = "checkResult4";
		strFrm = "checkResultFrm4";
		tabName = "�����޿�";
		action="/servlet/com.sino.ams.sampling.report.servlet.AssetsSamplingReportFourServlet"
	}
	tabName += "ѡ�";
	var divStyle = document.all(strDiv).style.display;
	if(divStyle == "none"){
		alert("��" + tabName + "��û�д��ڻ״̬������ִ��ָ����������");
		return;
	}
	var wind = window.frames[strFrm];
	var subFrm = wind.document.mainFrm;
	document.mainFrm.action = action;
	document.mainFrm.submit();
}
</script>