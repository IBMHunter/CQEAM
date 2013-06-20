<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.soa.mis.srv.employee.dto.SBHRHRSrvEmployeeInfoDTO" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<head>
    <title>MISϵͳԱ����Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>


</head>

<body  onkeydown="autoExeFunction('do_Search()');" onload="initPage();">
<%
	SBHRHRSrvEmployeeInfoDTO dto = (SBHRHRSrvEmployeeInfoDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String pageTitle = "MISϵͳԱ����Ϣ";
%>
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form name="mainFrm" method="post" action="/servlet/com.sino.soa.mis.srv.employee.servlet.SBHRHRSrvEmployeeInfoServlet">
    <script type="text/javascript">
        printTitleBar("<%=pageTitle%>");
    </script>
    <table width="100%" topmargin="0" border="0" bgcolor="#EFEFEF" style="width:100%">
        <tr>
            <td align="right" width="10%">���¿�ʼ���ڣ�</td>
            <td align="left" width="20%">
                <input type="text" name="startLastUpdateDate" value="<%=dto.getStartLastUpdateDate()%>" id="startLastUpdateDate" class="noEmptyInput" title="���ѡ����¿�ʼ����" readonly class="readonlyInput" style="width:90%"><img src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fStartPop(startLastUpdateDate, endLastUpdateDate);">
            </td>
            <td align="right" width="10%">���½������ڣ�</td>
            <td align="left" width="20%">
                <input type="text" name="endLastUpdateDate" value="<%=dto.getEndLastUpdateDate()%>" id="endLastUpdateDate" class="noEmptyInput" title="���ѡ����½�������" readonly class="readonlyInput" style="width:90%"><img src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fEndPop(startLastUpdateDate, endLastUpdateDate);">
            </td>
            <td width="40%" align="right"><img src="/images/eam_images/synchronize.jpg" style="cursor:'hand'" onclick="do_syschronize();" alt="ͬ��"></td>
        </tr>
    </table>
    <input name="act" type="hidden">
    <input name="company" type="hidden">

</form>
<div style="position:absolute;top:92%;left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm"
        scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

<script type="text/javascript">

    function do_Search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.mainFrm.submit();
    }
    
   /**
	* ���ܣ�ͬ������
	*/
	function do_syschronize() {

		var ldate = document.getElementById("startLastUpdateDate").value;
		if(ldate ==""){
			alert("��ѡ����¿�ʼ���ڣ�");
			return false;
		}
        var ndate = document.getElementById("endLastUpdateDate").value;
		if(ndate ==""){
			alert("��ѡ����½������ڣ�");
			return false;
		}
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
		mainFrm.action = "/servlet/com.sino.soa.mis.srv.employee.servlet.SBHRHRSrvEmployeeInfoServlet";
		mainFrm.act.value = "<%=WebActionConstant.SYSCHRONIZE_ACTION%>";
		mainFrm.submit();
	}
	
    function initPage() {
        do_SetPageWidth();
    }
</script>
</html>

