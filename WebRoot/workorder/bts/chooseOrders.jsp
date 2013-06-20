<%--
  User: zhoujs
  Date: 2007-9-22
  Time: 13:56:07
  Function: ѡ��Ҫ��������(��ǰ�û�)
--%>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO"%>
<%@ page import="com.sino.ams.workorder.dto.EtsWorkorderBatchDTO"%>
<%@ page import="com.sino.ams.workorder.dto.EtsWorkorderDTO"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.data.Row"%>
<%@ page import="com.sino.base.data.RowSet"%>
<%@ page import="com.sino.base.util.StrUtil"%>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    String action =StrUtil.nullToString(request.getParameter("act"));

    RowSet rows = (RowSet) request.getAttribute(WebAttrConstant.WORKORDER_LOC_ROWSET);
    String projectOpt = (String) request.getAttribute(WebAttrConstant.PROJECT_OPTION);
    EtsObjectDTO etsObject=(EtsObjectDTO)request.getAttribute(WebAttrConstant.ETS_OBJECT_DTO);
    EtsWorkorderBatchDTO etsWorkorderBatch=(EtsWorkorderBatchDTO)request.getAttribute(WebAttrConstant.ETS_WORKORDER_BATCH_DTO);
    EtsWorkorderDTO etsWorkorder=(EtsWorkorderDTO)request.getAttribute(WebAttrConstant.ETS_WORKORDER_DTO);

    boolean isFirstNode=StrUtil.nullToString(request.getParameter("isFirstNode")).equalsIgnoreCase("TRUE");
    String workorderTypeDesc = StrUtil.nullToString(request.getParameter("workorderTypeDesc"));
    String groupName = StrUtil.nullToString(request.getParameter("groupName"));

    String category = StrUtil.nullToString(request.getParameter("objectCategory"));
%>
<html>
<base target="_self">
<head>
    <title>�Զ��幤���ص�ѡ��ҳ��</title>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
</head>

<link href="/WebLibary/css/view.css" rel="stylesheet" type="text/css">
<link href="/WebLibary/css/css.css" rel="stylesheet" type="text/css">
<link href="/WebLibary/css/eam.css" rel="stylesheet" type="text/css">
<link href="/WebLibary/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
<script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
<script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
<script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
<script type="text/javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
<script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
<script type="text/javascript" src="/WebLibary/js/TableProcess.js"></script>
<script type="text/javascript" src="/WebLibary/js/datepicker.js"></script>
<script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
<script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_query()');">

<form name="mainFrm"  method="post" action="/servlet/com.sino.ams.workorder.servlet.WorkorderChooseSevrlet">
    <script type="text/javascript">
        var ArrAction1 = new Array(true, "ȡ��", "act_refresh.gif", "ȡ��", "do_Cancel");
        var ArrAction2 = new Array(true, "���ɹ���", "del.gif", "���ɹ���", "do_GenOrders");
        var ArrAction3 = new Array(false, "�½���վ", "act_refresh.gif", "���ɹ���", "newBS");
        var ArrActions = new Array(ArrAction1, ArrAction3, ArrAction2);
        var ArrSinoViews = new Array();
        printTitleBar("��ӹ���");
        printToolBar();
    </script>

    <input type="hidden" name="isFirstNode" value="<%=isFirstNode%>">
    <input type="hidden" name="act" value="<%=action%>">
    <input type="hidden" name="groupId" value="<%=etsWorkorder.getGroupId()%>">
    <input type="hidden" name="distributeGroup" value="<%=etsWorkorder.getDistributeGroup()%>">
    <input type="hidden" name="prjId" value="<%=etsObject.getProjectId()%>">
    <input type="hidden" name="workorderType" value="<%=etsWorkorderBatch.getWorkorderType()%>">
    <input type="hidden" name="workorderBatch" value="<%=etsWorkorderBatch.getWorkorderBatch()%>">
    <input type="hidden" name="objectCategory" value="<%=category%>">

<table border="0" width="100%" align="left" class="queryTable" id="table1">
    <tr bgcolor="#ACCDFF">
        <td colspan="6"><span>&gt;&gt;&gt;����������Ϣ</span></td>
    </tr>
    <tr>
        <td width="3%">&nbsp;</td>
        <td width="15%" align="right">�������ͣ� </td>
        <td width="28%" align="left"><input type="text" name="workorderTypeDesc" style="width:70%" value="<%=etsWorkorder.getWorkorderTypeDesc()%>" class="input_style2"></td>
        <td width="15%" align="right">�ӵ�����:</td>
        <td width="28%"><input name="groupName" type="text" class="input_style2" style="width:70%" value="<%=etsWorkorder.getGroupName()%>" ></td>
        <td width="11%" align="center"></td>
    </tr>
    <tr>
        <td width="3%">&nbsp;</td>
        <td width="15%" align="right">��ʼʱ�䣺 </td>
        <td width="28%">
            <input class="input_style1" name="startDate" type="text" style="width:70%" readonly="true" value="<%=StrUtil.nullToString(etsWorkorder.getStartDate())%>"><font color="red">*</font>
            <img src="/images/calendar.gif" width="16" height="15" alt="ѡ��ʱ��" id="calendar1"  onClick="getDateTime('mainFrm.startDate');">
        </td>
        <td width="15%" align="right">ʵʩ����(��): </td>
        <td width="28%" align="left"><input name="implementDays" class="input_style1" style="width:70%" type="text" value="<%=etsWorkorder.getImplementDays()%>"> <font color="red">*</font> </td>
        <td width="11%" align="center"></td>
    </tr>
    <tr bgcolor="#BDD3FF">
        <td colspan="6"><span>&gt;&gt;&gt;�ص���Ϣ��ѯ</span></td>
    </tr>
    <tr>
        <td width="3%">&nbsp;</td>
        <td width="15%" align="right">�������̣�</td>
        <td width="28%" align="left"><input style="width:70%" name="prjName" value="<%=etsObject.getProjectName()%>" readonly="true" class="input_style2" > </td>
        <td width="15%" align="right">�ص��ţ�</td>
        <td width="28%" align="left"><input type="text" class="input_style1" name="workorderObjectCode" style="width:70%" value="<%=etsObject.getWorkorderObjectCode()%>"></td>
        <td width="11%" align="center"></td>
    </tr>
    <tr>
        <td width="3%">&nbsp;</td>
        <td width="15%" align="right">�ص����ƣ�</td>
        <td width="28%" align="left"><input type="text" class="input_style1" name="workorderObjectName" style="width:70%" value="<%=etsObject.getWorkorderObjectName()%>"></td>
        <td width="15%" align="right">�ص�λ�ã�</td>
        <td width="28%" align="left"><input type="text" class="input_style1" name="workorderObjectLocation" style="width:70%" value="<%=etsObject.getWorkorderObjectLocation()%>"></td>
        <td width="11%" align="center"><img src="/images/eam_images/search.jpg" alt="��ѯ"  onClick="do_query();"></td>
    </tr>
</table>

<div style="left:1px;width:100%;position:absolute;top:184px;overflow-y:scroll" class="crystalScroll" id="headDiv">
    <table class="headerTable" border=1 style="width:100%" cellpadding="0" cellspacing="0">
       <tr>
            <td height="22" width="4%" align="center"><input type="checkbox" class="headCheckbox" name="ctlBox" onclick="checkAll(this.name,'objNos_n')"></td>
            <td height="22" width="12%" align="center">�ص���</td>
            <td height="22" width="24%" align="center">�ص�����</td>
            <td height="22" width="25%" align="center">�ص�λ��</td>
            <td height="22" width="25%" align="center">��������</td>
            <td height="22" width="10%" align="center">�ӵ�����</td>
        </tr>
    </table>
</div>

<%

	if(rows != null && !rows.isEmpty()){
%>
<div style="overflow-y:scroll;height:362;width:100%;;position:absolute;top:207px;left:0px;margin-left:0px" align="left">
    <table width="100%" border="1" bordercolor="#9FD6FF" >
<%
        Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
            boolean canDistribute=row.getStrValue("CAN_DISTRIBUTE").equalsIgnoreCase("Y");
            if(canDistribute){
%>
			<tr class="dataTR" onclick="executeClick(this);">
				<td style="word-wrap:break-word" height="22" width="4%" align="center" class=""><input type="checkBox" name="objNos_n"  value="<%=row.getValue("WORKORDER_OBJECT_NO")%>"></td>
<%}else{%>
            <tr class="dataTR" title="�õص���δ���Ѳ�칤��,�뾡����ɣ�">
				<td style="word-wrap:break-word" height="22" width="4%" align="center" class=""><input  disabled="true" type="checkBox" name="objNos_n"  value="<%=row.getValue("WORKORDER_OBJECT_NO")%>"></td>
    <%}%>
                <td style="word-wrap:break-word" height="22" width="12%" ><%=row.getValue("WORKORDER_OBJECT_CODE")%></td>
				<td style="word-wrap:break-word" height="22" width="24%" ><%=row.getValue("WORKORDER_OBJECT_NAME")%></td>
				<td style="word-wrap:break-word" height="22" width="25%" ><%=row.getValue("WORKORDER_OBJECT_LOCATION")%></td>
				<td style="word-wrap:break-word" height="22" width="25%" ><%=row.getValue("PROJECT_NAME")%></td>
				<td style="word-wrap:break-word" height="22" width="10%" ><%=etsWorkorder.getGroupName()%></td>
			</tr>
<%
		}
%>
    </table>
</div>

<%
    }
%>
<%=WebConstant.WAIT_TIP_MSG%>
</form>
</body>
</html>

<script type="text/javascript">
function do_query() {
    if(document.mainFrm.workorderObjectCode.value==""&&document.mainFrm.workorderObjectName.value==""&&document.mainFrm.workorderObjectLocation.value==""){
       alert("�������ѯ����");
        return;
    }
    mainFrm.act.value = "myQuery";
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    mainFrm.submit();
}
function do_Cancel() {
    window.close();
}
function do_GenOrders() {
    var fieldNames = "implementDays;startDate";
    var fieldLabels = "ʵʩ����;��ʼʱ��";
    var emptyValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
    fieldNames = "implementDays";
    fieldLabels = "ʵʩ����";
    var numberValid = formValidate(fieldNames, fieldLabels, POSITIVE_INT_VALIDATE);
    var checkedCount = getCheckedBoxCount("objNos_n");
    var isValid = emptyValid && numberValid;
    if (isValid) {
        if (checkedCount < 1){
            alert("������ѡ��һ����¼!");
        }else {
        mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
        mainFrm.submit();}
    } else {
        return;
    }
}
</script>


