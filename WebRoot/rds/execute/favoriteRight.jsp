<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@ include file="/rds/public/listPageInclude.jsp"%>

<html>
<head>
    <title>�ղؼ��еı���</title>
</head>
<body bottomMargin="0" leftMargin="0" topMargin="0" rightMargin="0" onload="do_initPage()">
<html:form action="/rds/favoriteRight">
    <script type="text/javascript">
        printTitleBar("�ղؼ��еı���");
    </script>
    <html:hidden property="act" styleId="act"/>
</html:form>
    <div id="headDiv" style="overflow-x:hidden;overflow-y:scroll;position:absolute;top:20px;left:1px;width:100%">
        <table id="headTable" border="1" width="100%">
            <tr class="headerTR" style="text-align:center;cursor:pointer" title="���ȫѡ��ȡ��ȫѡ" onclick="executeClick(this)">
                <td width="5%"><input type="checkbox" name="controlChk" onPropertyChange="checkAll(this.name, 'subCheck')"></td>
                <td width="30%">�������</td>
                <td width="65%">��������</td>
            </tr>
        </table>
    </div>
<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && !rows.isEmpty()) {
 %>

    <div id="dataDiv" style="overflow:scroll;height:50%;width:100%;position:absolute;top:44px;left:1px" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#666666" id="dataTable" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
        for (int i = 0; i < rows.getSize(); i++) {
            Row frm = rows.getRow(i);
%>
            <tr onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#FFFFFF'" class="dataTR">
                <td width="5%" align="center"><%=frm.getStrValue(QueryConstant.CHECK_BOX_PROP)%></td>
                <td width="30%"><input type="text" name="reportCode" class="tableInput2" readonly="true" value="<%=frm.getValue("REPORT_CODE")%>"></td>
                <td width="65%"><input type="text" name="reportName" class="tableInput1" readonly="true" value="<%=frm.getValue("REPORT_NAME")%>"></td>
            </tr>
<%
        }
%>
        </table>
       </div>
<%
    }
%>
<div id="pageNaviDiv"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
</body>
</html>
<script type="text/javascript">
function do_initPage(){
    do_SetPageWidth();
}

function do_RunReport(){
    var checkedCount = getCheckedBoxCount("subCheck");
    if(checkedCount != "1"){
        alert("ֻ��ѡ��һ���������У����ܲ�ѡ��Ҳ���ܶ�ѡ��");
        return;
    }
    var reportCode = getCheckBoxValue("subCheck", ",");
    var index = reportCode.lastIndexOf(":");
    reportCode = reportCode.substring(index + 1);
    var pageConfig = new DataPageConfig();
    pageConfig.setActionURL("<%=contextPath%>/rds/reportRun.do");
    pageConfig.setAct("");
    pageConfig.setOpenWindow(true);
    pageConfig.setWindowName(reportCode);
    var userParameter = "reportCode=" + reportCode;
    pageConfig.setParameters(userParameter);
    do_ProcessDataPage(pageConfig);
}

function do_DeleteReport(){
    var checkedObj = document.getElementById("$$$CHECK_BOX_HIDDEN$$$");
    if(!checkedObj){
        alert("û��ѡ�д��Ƴ��ı������ܼ�����");
        return;
    }
    if(checkedObj.value == ""){
        alert("û��ѡ�д��Ƴ��ı������ܼ�����");
        return;
    }
    document.getElementById("act").value = "DELETE_ACTION";
    document.forms[0].submit();
}
</script>