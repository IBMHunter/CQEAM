<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@ include file="/rds/public/listPageInclude.jsp"%>

<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.rds.share.constant.RDSDictionaryList" %>
<%
    String lovDataURL = contextPath + "/rds/lovProcess.do";
    lovDataURL += "?act=DETAIL_ACTION";
%>
<html>
<head>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/form/SelectProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/util/AjaxProcess.js"></script>

</head>
<body leftmargin="0" topmargin="0">
<%=WebConstant.WAIT_TIP_MSG%>
<html:form action="/rds/lovFrm" method="post">
<script type="text/javascript">
    ArrActions[2][0] = true;
    ArrActions[4][0] = true;
    ArrActions[15][0] = true;
    printTitleBar("LOVֵ�б�ά��");
    printToolBar();
</script>
</html:form>
<table border="0" width="100%" height="100%">
	<tr style="height:22px;text-align:center">
		<td>�Ѷ����LOVֵ�б�</td>
		<td>LOVֵ�б���ϸ��Ϣ</td>
	</tr>    
	<tr>
		<td width="65%"><iframe id="lovListFrm" style="width:100%;height:100%" border="0" frameborder="0" src=""></iframe></td>
		<td width="35%"><iframe id="lovDataFrm" style="width:100%;height:100%" border="0" frameborder="0" src="<%=lovDataURL%>"></iframe></td>
	</tr>
</table>
</body>
</html>
<script type="text/javascript">
function do_Save(){
    var frm = document.getElementById("lovDataFrm");
    var childWin = frm.contentWindow;
    childWin.do_Save();
}

function do_Create(){
    var frm = document.getElementById("lovDataFrm");
    var childWin = frm.contentWindow;
    childWin.do_Create();
}

function do_Close(){
    var ajaxProcessor = new RDSAjaxProcessor(do_SetLovList2Parent, false);
    ajaxProcessor.setServiceClass("com.sino.rds.share.util.RDSOptionCreateService");
    ajaxProcessor.setMethodName("getAllLovOptions");
    ajaxProcessor.performTask();
}

function do_SetLovList2Parent(resText){
    var doc = window.opener.document;
    var thatLov = doc.getElementById("referenceLov");
    thatLov.outerHTML = "<select name=\"referenceLov\" id=\"referenceLov\" style=\"display:none\">"+resText+"</select>";
    var tab = doc.getElementById("dataTable");
    var rows = tab.rows;
    if(rows){
        var rowCount = rows.length;
        for(var i = 0; i < rowCount; i++){
            var tr = rows[i];
            var inputType = getTrNode(tr, "inputType").value;
            if(inputType != "<%=RDSDictionaryList.INPUT_TYPE_LOV%>"){
                continue;
            }
            var node = getTrNode(tr, "lovId");
            var nodeName = node.name;
            var selectedValue = getSelectedValue(node);
            node.outerHTML = "<select name=\""+nodeName+"\" style=\"width:100%\" onchange=\"do_ProcessLOVList(this)\">"+resText+"</select>";
            node = getTrNode(tr, "lovId");
            selectSpecialOptionByItem(node, selectedValue);
        }
    }
    window.close();
}
</script>