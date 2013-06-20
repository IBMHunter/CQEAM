<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.system.LocationInfoConfirm.dto.LocationInfoConfirmDTO" %>

<html>
<head>
    <title>�豸����ȷ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
	<script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
	<script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
</head>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    LocationInfoConfirmDTO dto = (LocationInfoConfirmDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	boolean hasData = (rows != null && rows.getSize() > 0);
    Row row = null;
%>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_search()')" onload="initPage();">
<jsp:include page="/message/MessageProcess"/>
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.LocationInfoConfirm.servlet.LocationInfoConfirmServlet">
<input type="hidden" name="act">
<script type="text/javascript">
    printTitleBar("�ʲ��ص���Ϣȷ��");
</script>
<table width="100%" topmargin="0" border="0" >
        <tr>
            <td width="10%" align="right">�ص���룺</td>
            <td width="20%"><input class="input_style1" style="width:90%" type="text" name="workorderObjectNo" value="<%=dto.getWorkorderObjectNo() %>"></td>
            <td width="10%" align="right">�ص����ƣ�</td>
            <td width="25%"><input  class="input_style1" style="width:90%" type="text" name="workorderObjectName" value="<%=dto.getWorkorderObjectName() %>"></td>
            <td width="10%" align="center"><img src="/images/eam_images/search.jpg" align="middle" onclick="do_search();" alt="��ѯ"></td>
            <td width="10%" align="center"><img src="/images/eam_images/confirm.jpg" alt="ȷ��" onClick="do_confirm();"></td>
            <td width="10%" align="center"><img src="/images/eam_images/cancel.jpg" alt="ȡ��" onClick="do_cancel();"></td>
        </tr>
</table>
<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:830px">
	<table class="headerTable" border="1" width="100%">
        <tr height="22" onClick="executeClick(this)"title="���ȫѡ��ȡ��ȫѡ" style="cursor:hand">
            <td width="3%" align="center"><input type="checkbox" name="titleCheck" class="headCheckbox" id="controlBox" onclick="checkAll('titleCheck','workorderObjectNos')"></td>
            <td width="10%" align="center">�ص����</td>
            <td width="25%" align="center">�ص�����</td>
            <td width="25%" align="center">�µص�����</td>
            <td width="10%" align="center">���ݺ�</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:320px;width:847px;position:absolute;top:69px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="100%" border="1" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if (hasData) {
		String workorderObjectNo = "";
		for (int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
			workorderObjectNo = row.getValue("WORKORDER_OBJECT_NO").toString();
%>
        <tr style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
            <td width="3%" align="center"><input type="checkbox" name="workorderObjectNos" value="<%=workorderObjectNo%>"></td>
            <td width="10%" align="center"><%=row.getValue("WORKORDER_OBJECT_CODE")%></td>
            <td width="25%" align="left"><%=row.getValue("WORKORDER_OBJECT_NAME")%></td>
            <td width="25%" align="left"><%=row.getValue("NEW_LOCATION")%></td>
            <td width="10%" align="left"><input type="hidden" name="transNos" value="<%=row.getValue("TRANS_NO")%>"><%=row.getValue("TRANS_NO")%></td>
        </tr>
<%
		}
	}
%>
    </table>
</div>
</form>
<%
	if(hasData){
%>
<div style="position:absolute;top:400px;left:0; right:20">
<%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
</html>

<script type="text/javascript">

    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
    }

    function do_confirm() {
        var checkedCount = getCheckedBoxCount("workorderObjectNos");
        if (checkedCount < 1) {
            alert("������ѡ��һ�����ݣ�");
            return;
        } else {
            mainFrm.act.value = "<%=AMSActionConstant.CONFIRM_ACTION%>";
            mainFrm.submit();
        }
    }
    
   	function do_cancel() {
   		var checkedCount = getCheckedBoxCount("workorderObjectNos");
        if (checkedCount < 1) {
            alert("������ѡ��һ�����ݣ�");
            return;
        } else {
            mainFrm.act.value = "<%=AMSActionConstant.CANCEL_ACTION%>";
            mainFrm.submit();
        }
   	}

    function initPage() {
		do_SetPageWidth();
    }

</script>