<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.yj.constant.YJWebAttribute" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet"%>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ include file="/newasset/headerInclude.htm" %>
<html>
<head>
    <title>Ӧ��ͨ�ű������</title>
</head>
<body leftmargin="0" topmargin="0" onload="initPage();" onkeydown="autoExeFunction('do_Search()');" >
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%=WebConstant.WAIT_TIP_MSG%>
<%//=WebConstant.EXPORT_TIP_MSG%>
<% 
    RequestParser parser = new RequestParser();
    parser.transData(request);
    String formAction="/servlet/com.sino.ams.yj.ensure.servlet.AmsYjCommunicateEnsureServlet";
    String orgOpt = (String) request.getAttribute(YJWebAttribute.ORG_OPTION);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
    Row row = null;
    String deptName = parser.getParameter("deptName");
    String ensureDateFrom = parser.getParameter("ensureDateFrom");
    String ensureDateTo = parser.getParameter("ensureDateTo");
%>
<form method="post" name="mainFrm"   action="<%=formAction%>">
<script type="text/javascript">
    printTitleBar("Ӧ��ͨ�ű������");
</script>
<table width="100%" border="0" id="table1">
    <tr>
         <td width="12%"  align="right">��˾���ƣ�</td>
        <td width="12%"><select class="select_style1" name="organizationId" width="100%"><%=orgOpt%></select> </td>
        <td width="12%" align="right">��λ��</td>
        <td width="12%"><input class="input_style1" type="text" name="deptName" width="100%" value="<%=deptName%>" style="cursor:hand;width:100%" > </td>
		<td  align="right" >
			<img src="/images/eam_images/search.jpg" style="cursor:hand" onclick="do_search();" title="��ѯ">
			<img src="/images/eam_images/new.jpg" style="cursor:hand" onClick="do_new();" title="�½�">
			<img src="/images/eam_images/delete.jpg" style="cursor:hand" onClick="do_delete();" title="ɾ��">
            <img src="/images/eam_images/export.jpg" style="cursor:hand" onClick="do_exp();" title="����">
            <img src="/images/eam_images/export.jpg" style="cursor:hand" onClick="do_export();" title="����ͳ��">
		</td>
    </tr>
    <tr>
        <td width="12%"  align="right">����ʱ��ӣ�</td>
        <td width="12%">
           <input type="text" name="ensureDateFrom" value="<%=ensureDateFrom%>" style="width:100%;cursor:hand" title="���ѡ��ʼ����" readonly
                     class="input_style1"   onclick="gfPop.fStartPop(ensureDateFrom, ensureDateTo)"></td>
        <td width="12%" align="right">����ʱ�䵽��</td>
        <td width="12%">
           <input type="text" name="ensureDateTo" value="<%=ensureDateTo%>" style="cursor:hand;width:100%" title="���ѡ���������" readonly
                      class="input_style1"  onclick="gfPop.fEndPop(ensureDateFrom, ensureDateTo)"></td>
    </tr>
</table>

<input type="hidden" name="act">
<input type="hidden" name="isExp" value="0">
<div id="headDiv" style="overflow:hidden;position:absolute;top:70px;left:1px;width:150%">
    <%--<table class="headerTable" border="1" style="width:100%">--%>
        <table border="1" style="color: #FFFFFF" bgcolor="#2983CB" width="300%">
        <tr height="20">
            <td width="2%" align="center" rowspan="2">&nbsp;</td>
            <td width="2%" align="center" rowspan="2">���</td>
            <td width="6%" align="center" rowspan="2">��λ</td>
            <td width="7%" align="center" rowspan="2">ͨ�ű�������</td>
            <td width="4%" align="center" rowspan="2">�¼�����</td>
            <td width="3%" align="center" rowspan="2">����ʱ���</td>
            <td width="3%" align="center" rowspan="2">����ʱ�䵽</td>
            <td width="3%" align="center" rowspan="2">���ϵص�</td>
            <td width="8%" align="center" colspan="2">����Ͷ��</td>
            <td width="8%" align="center" colspan="2">Ӧ����Ͷ��</td>
            <td width="8%" align="center" colspan="2">����Ӧ��ͨ���豸Ͷ��</td>
            <td width="53%" align="center" colspan="8">ͨ����ʧ���������</td>
        </tr>
        <tr height="20">
            <td width="3%" align="center">Ͷ������</td>
            <td width="3%" align="center">Ͷ���˴�</td>
            
            <td width="3%" align="center">Ӧ��������</td>
            <td width="3%" align="center">Ӧ��������</td>
            
            <td width="4%" align="center">Ӧ��ͨ���豸��</td>
            <td width="4%" align="center">Ӧ��ͨ���豸�״�</td>
            
            <td width="3%" align="center">ͨ����ϳ̶�</td>
            <td width="4%" align="center">��ʧ���</td>
            <td width="8%" align="center">Ӧ�����ϴ�ʩ</td>
            <td width="8%" align="center">ͨ�Żָ������ʱ��</td>
            <td width="8%" align="center">�ط�������������</td>
            <td width="8%" align="center">�¼�ԭ��Ӱ�췶Χ</td>
            <td width="8%" align="center">���ڵ�����</td>
            <td width="6%" align="center">δ��������ʩ</td>

        </tr>
    </table>
    <%--</table>--%>
</div>
<div id="dataDiv" style="overflow:scroll;height:63%;width:150%;position:absolute;top:112px;left:1px" align="left"  onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="300%" border="1" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
    if (hasData) {
		String communicateId = "";
        for (int i = 0; i < rows.getSize(); i++) {
            row = rows.getRow(i);
            communicateId=row.getStrValue("COMMUNICATE_ID");
%>
        <tr height="22" style="cursor:hand" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#FFFFFF'">
            <td width="2%"><input type="checkbox" name="communicateIds" value="<%=row.getValue("COMMUNICATE_ID")%>"></td>
            <td width="2%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("COMMUNICATE_ID")%>"></td>
            <td width="6%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>

            <td width="7%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("ENSURE_NAME")%>"></td>
            <td width="4%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput2" readonly value="<%=row.getValue("EVENT_TYPE")%>"></td>
            <td width="3%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("ENSURE_DATE_FROM")%>"></td>
            <td width="3%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("ENSURE_DATE_TO")%>"></td>
            <td width="3%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("ENSURE_LOCATION")%>"></td>
            <td width="3%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("MANPOWER_QTY")%>"></td>
            <td width="3%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("MANPOWER_TIMES")%>"></td>
            <td width="3%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("COMVAN_QTY")%>"></td>
            <td width="3%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("COMVAN_TIMES")%>"></td>
            <td width="4%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("EQUIPMENT_QTY")%>"></td>
            <td width="4%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("EQUIPMENT_UNIT")%>"></td>

            <td width="3%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("BLOCK_DEGREE")%>"></td>
            <td width="4%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("LOSS_CONDITION")%>"></td>
            <td width="8%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("ENSURE_MEASURE")%>"></td>
            <td width="8%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("RECOVER_SITUATION")%>"></td>
            <td width="8%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("GOVERNMENT_EVALUATE")%>"></td>
            <td width="8%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("REASON_AFFECT")%>"></td>
            <td width="8%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput" readonly value="<%=row.getValue("QUESTION")%>"></td>
            <td width="6%" onclick="show_detail('<%=communicateId%>');"><input type="text" class="finput2" readonly value="<%=row.getValue("GUARD_MEASURE")%>"></td>
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
<div id="navigatorDiv" style="position:absolute;bottom:0px;left:0;"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>

<%--</fieldset>--%>
</body>

</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>

<script type="text/javascript">
function initPage(){
	do_SetPageWidth();
}


function do_search() {
    document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    document.mainFrm.submit();
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}



function show_detail(communicateId) {
    var url = "<%=formAction%>";
	url += "?act=<%=WebActionConstant.DETAIL_ACTION%>";
	url += "&communicateId=" + communicateId;
    var popscript = "width=800,height=480,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no";
    window.open(url, "ensure", popscript);
}

function do_new() {
    var url = "<%=formAction%>";
    url += "?act=<%=WebActionConstant.NEW_ACTION%>";
    var popscript = "width=800,height=480,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no";
    window.open(url, "ensure", popscript);
}

    function do_delete(){
        document.mainFrm.act.value="<%=WebActionConstant.DELETE_ACTION%>";
        document.mainFrm.submit();
    }

    function do_export(){
        document.mainFrm.act.value="<%=WebActionConstant.EXPORT_ACTION%>";
        document.mainFrm.submit();
    }
    function do_exp(){
        document.mainFrm.act.value="EXP";
        document.mainFrm.submit();
    }


</script>