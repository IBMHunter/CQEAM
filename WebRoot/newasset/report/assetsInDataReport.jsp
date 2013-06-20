<%@ page import="com.sino.ams.newasset.report.dto.AssetsInDataReportDTO"%>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-5-16
  Time: 13:40:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
<link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
<script type="text/javascript" src="/WebLibary/js/LookUp.js"></script>
<title>����ָ���౨��</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="initPage();">
<%
	AssetsInDataReportDTO dto = (AssetsInDataReportDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
    String managerGuideType = dto.getManagerGuideType();
    String pageTitle= null; //SessionUtil.getPageTile(request);
    if (dto.getManagerGuideType().equals("CHECK_RATE")){
    	pageTitle = "�ʲ�ʵ����������������";
    }else if (dto.getManagerGuideType().equals("MATCH_CASE_RATE")){
    	pageTitle = "����̵��ʲ���ʵ�����";
    }else if (dto.getManagerGuideType().equals("TRUN_RATE")){
    	pageTitle = "ת����";
    } 
    else{
    	pageTitle = SessionUtil.getPageTile(request);
    }
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.AssetsInDataReportServlet">
<%=WebConstant.WAIT_TIP_MSG%>
    <script type="text/javascript">
        printTitleBar("<%=pageTitle%>")
    </script>
	<table width="100%" border="0">
		<tr>
<%
    if (managerGuideType.equals("TRUN_RATE") || managerGuideType.equals("CHECK_RATE") || managerGuideType.equals("MATCH_CASE_RATE") || managerGuideType.equals("COP_RATE") || managerGuideType.equals("COP_MATCH_RATE") || managerGuideType.equals("ACCOUNTING_ACCURATE")) {
%>
            <td width="10%" align="right">��˾��</td>
			<td width="20%" align="left">
                <select class="select_style1" name="organizationId" style="width:60%"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
<%
    }
%>
            <td width="10%" align="right">����ڼ䣺</td>
			<td width="20%" align="left">
                <input type="text" name="period" style="width:35%" value="<%="".equals(StrUtil.nullToString(dto.getPeriod())) ? "" : dto.getPeriod().substring(0,4) + "-" + dto.getPeriod().substring(4) + "-01"%>" title="���ѡ������" readonly class="input_style2" onclick="gfPop.fPopCalendar(period)">
            </td>
<%
    if (managerGuideType.equals("IN_TIME_RATE") || managerGuideType.equals("NICETY_RATE")) {
%>
            <td width="25%"></td>
<%
    }
%>
            <td width="15%" align="right">
                <img src="/images/eam_images/search.jpg"   onclick="do_Search();">
                <img src="/images/eam_images/export.jpg" onclick="do_Export();" alt="����Excel">
            </td>
		</tr>
	</table>
	<input type="hidden" name="act" value="">
    <input type="hidden" name="managerGuideType" value="<%=managerGuideType%>">
</form>


<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:840px">
	<table class="headerTable" border="1" width="100%">
        <tr height="22">
<%
    if (managerGuideType.equals("TRUN_RATE")) {
%>
            <td width="10%" align="center">��˾</td>
            <td width="10%" align="center">����ڼ�</td>
            <td width="10%" align="center">�������ڹ���ת�ʶ�</td>
            <td width="10%" align="center">�����ۼ�Ͷ���ܶ�</td>
            <td width="10%" align="center">ת����</td>
<%
    } else if (managerGuideType.equals("IN_TIME_RATE")) {
%>
            <td width="10%" align="center">����ڼ�</td>
            <td width="10%" align="center">δ��ʱ�ϱ�����</td>
            <td width="10%" align="center">������Ӧ�ϱ�����</td>
            <td width="10%" align="center">���߷��������ϱ���ʱ��</td>
<%
    } else if (managerGuideType.equals("NICETY_RATE")) {
%>
            <td width="10%" align="center">����ڼ�</td>
            <td width="10%" align="center">�������ڷ�����ת���ʲ���׼ȷ������</td>
            <td width="10%" align="center">��������ת���ʲ�����</td>
            <td width="10%" align="center">ת����Ϣ׼ȷ��</td>
<%
    } else if (managerGuideType.equals("CHECK_RATE")) {
%>
            <td width="10%" align="center">��˾</td>
            <td width="10%" align="center">����ڼ�</td>
            <td width="10%" align="center">����̵����񹤵�����</td>
            <td width="10%" align="center">�ƻ��涨�ĳ���̵����񹤵�����</td>
            <td width="10%" align="center">������������</td>
<%
    } else if (managerGuideType.equals("MATCH_CASE_RATE")) {
%>
            <td width="10%" align="center">��˾</td>
            <td width="10%" align="center">����ڼ�</td>
            <td width="10%" align="center">�������ʵ������ʲ�����</td>
            <td width="10%" align="center">����ʲ�������</td>
            <td width="10%" align="center">����̵��ʲ���ʵ�����</td>
<%
    } else if (managerGuideType.equals("COP_RATE")) {
%>
            <td width="10%" align="center">��˾</td>
            <td width="10%" align="center">����ڼ�</td>
            <td width="10%" align="center">����ɵ��ճ�Ѳ���̵�Ĺ�����</td>
            <td width="10%" align="center">�ƻ����ճ�Ѳ���̵㹤������</td>
            <td width="10%" align="center">�ճ�Ѳ���ʲ��̵������</td>
<%
    } else if (managerGuideType.equals("COP_MATCH_RATE")) {
%>
            <td width="10%" align="center">��˾</td>
            <td width="10%" align="center">����ڼ�</td>
            <td width="10%" align="center">�̵�����ʵ������ʲ�����</td>
            <td width="10%" align="center">�̵��ʲ�������</td>
            <td width="10%" align="center">�ճ�Ѳ���ʲ��̵���ʵ�����</td>
<%
    } else if (managerGuideType.equals("ACCOUNTING_ACCURATE")) {
%>
            <td width="10%" align="center">��˾</td>
            <td width="10%" align="center">����ڼ�</td>
            <td width="10%" align="center">�ʲ�������صĲ�����</td>
<%
    }
%>
        </tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:350px;width:857px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if(hasData){
		Row row = null;
        for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
		<tr height="22">
<%
            if (managerGuideType.equals("TRUN_RATE")) {
%>
                <td width="10%" align="center"><%=row.getValue("COMPANY")%></td>
                <td width="10%" align="center"><%=row.getValue("PERIOD")%></td>
                <td width="10%" align="center"><%=row.getValue("PROJECT_TRUN_ASSETS")%></td>
                <td width="10%" align="center"><%=row.getValue("PROJECT_SUM_ASSETS")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_RATE")%></td>
<%
            } else if (managerGuideType.equals("IN_TIME_RATE")) {
%>
                <td width="10%" align="center"><%=row.getValue("PERIOD")%></td>
                <td width="10%" align="center"><%=row.getValue("NO_TIMELY_REPORT_NUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETSMENT_REPORT_NUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_RATE")%></td>
<%
            } else if (managerGuideType.equals("NICETY_RATE")) {
%>
                <td width="10%" align="center"><%=row.getValue("PERIOD")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETSMENT_FALSE_NUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETSMENT_ASSETS_SUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_RATE")%></td>
<%
            } else if (managerGuideType.equals("CHECK_RATE")) {
%>
                <td width="10%" align="center"><%=row.getValue("COMPANY")%></td>
                <td width="10%" align="center"><%=row.getValue("PERIOD")%></td>
                <td width="10%" align="center"><%=row.getValue("COMPLETE_CHECK_NUM")%></td>
                <td width="10%" align="center"><%=row.getValue("PLAN_CHECK_NUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_RATE")%></td>
<%
            } else if (managerGuideType.equals("MATCH_CASE_RATE")) {
%>
                <td width="10%" align="center"><%=row.getValue("COMPANY")%></td>
                <td width="10%" align="center"><%=row.getValue("PERIOD")%></td>
                <td width="10%" align="center"><%=row.getValue("ACCOUNT_MATCH_CASE")%></td>
                <td width="10%" align="center"><%=row.getValue("CHECK_ASSETS_SUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_RATE")%></td>
<%
            } else if (managerGuideType.equals("COP_RATE")) {
%>
                <td width="10%" align="center"><%=row.getValue("COMPANY")%></td>
                <td width="10%" align="center"><%=row.getValue("PERIOD")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_COP_NUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_COP_SUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_RATE")%></td>
<%
            } else if (managerGuideType.equals("COP_MATCH_RATE")) {
%>
                <td width="10%" align="center"><%=row.getValue("COMPANY")%></td>
                <td width="10%" align="center"><%=row.getValue("PERIOD")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_MATCH_CASE")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_CHECK_SUM")%></td>
                <td width="10%" align="center"><%=row.getValue("ASSETS_RATE")%></td>
<%
            } else if (managerGuideType.equals("ACCOUNTING_ACCURATE")) {
%>
                <td width="10%" align="center"><%=row.getValue("COMPANY")%></td>
                <td width="10%" align="center"><%=row.getValue("PERIOD")%></td>
                <td width="10%" align="center"><%=row.getValue("ACCURATE_ERROR_NUMBER")%></td>
<%
            }
%>
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
<div style="position:absolute;top:430px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function initPage(){
	do_SetPageWidth();
}

function do_Search(){
	var period = mainFrm.period.value;
	if(period == null || period == ""){
		alert("����ڼ䲻��Ϊ�գ�");
		mainFrm.period.focus();
		return;
	}
	var dates=mainFrm.period.value.split("-",3);
	if (dates.length==3) {
		mainFrm.period.value=dates[0]+dates[1];
	}
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.AssetsInDataReportServlet";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Export() {
    var period = mainFrm.period.value;
	if(period == null || period == ""){
		alert("����ڼ䲻��Ϊ�գ�");
		mainFrm.period.focus();
		return;
	}
	var dates=mainFrm.period.value.split("-",3);
	if (dates.length==3) {
		mainFrm.period.value=dates[0]+dates[1];
	}
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.AssetsInDataReportServlet";
    mainFrm.submit();
}
</script>