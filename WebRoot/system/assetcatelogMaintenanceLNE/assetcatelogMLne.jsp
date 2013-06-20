<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@page import="com.sino.ams.system.user.dto.SfUserDTO"%>
<%@page import="com.sino.framework.security.bean.SessionUtil"%>
<%@page import="com.sino.ams.system.assetcatelogMaintenanceLNE.dto.AssetcatelogMLneDTO"%>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ include file="/newasset/headerInclude.htm" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <title>�ʲ�Ŀ¼�Ӷ�ά�ȶ�ӦLNE</title>
</head>
 <jsp:include page="/message/MessageProcess"/>
 <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
 <body leftmargin="0" topmargin="0" onload="initPage();" >
<%
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
	Row row = null;
%>

  <script type="text/javascript">
    printTitleBar("�ʲ�Ŀ¼�Ӷ�ά�ȶ�ӦLNE");
    var ArrAction0 = new Array(true, "��ѯ", "action_view.gif", "��ѯ", "do_Search()");  
    var ArrAction1 = new Array(true, "����", "action_edit.gif", "����", "do_Create()");
    var ArrAction2 = new Array(true, "ɾ��", "action_cancel.gif", "ɾ��", "do_Delete()");
    var ArrActions = new Array(ArrAction0, ArrAction1,ArrAction2);
    var ArrSinoViews = new Array();
    var ArrSinoTitles = new Array();
    printToolBar();

</script>
<form method="post" name="mainFrm"  action="/servlet/com.sino.ams.system.assetcatelogMaintenanceLNE.servlet.AssetcatelogMLneServlet">
	<input type="hidden" name="act">
	<div id="headDiv" style="overflow:hidden;position:absolute;top:50px;left:1px;width:150%">
	    <table class="headerTable" border="1" style="width:100%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
            <td width="2%" align="center"><input type="checkbox" name="subCheck" class="headCheckbox" id="controlBox" onclick="checkAll('subCheck','id')"></td>
				<td width="25%" align="center">����Ŀ������</td>
				<td width="15%" align="center">�ʲ�Ŀ¼����</td>
	            <td width="15%" align="center">��ά������</td>
	            <td width="10%" align="center">��ά�ȱ���</td> 
	            <td width="20%" align="center">�߼�����Ԫ�ص�����</td> 
	            <td width="15%" align="center">�߼�����Ԫ�صı���</td> 
	        </tr>
	    </table>
	</div>
	
	<div id="dataDiv" style="overflow:hidden;position:absolute;top:70px;left:1px;width:150%">
	    <table id="dataTable" width="100%" border="1" style="TABLE-LAYOUT:fixed;word-break:break-all">
	 <%
			
	 if (rows != null && !rows.isEmpty()) {
			for (int i = 0; i < rows.getSize(); i++) {
				 row=rows.getRow(i);
	%>
			<tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
            	<td width="2%" align="center"><input type="checkbox" name="coutentCode" value="<%=row.getValue("CONTENT_CODE")%>|<%=row.getValue("MATCH_CODE")%>"></td>
	            <td width="25%" ><input type="text" class="finput2" readonly value="<%=row.getValue("CONTENT_NAME")%>"></td>
	            <td width="15%"><input type="text" class="finput2" readonly value="<%=row.getValue("CONTENT_CODE")%>"></td>
	            <td width="15%"><input type="text" class="finput2" readonly value="<%=row.getValue("MATCH_DESC")%>"></td>
	            <td width="10%" ><input type="text" class="finput2" readonly value="<%=row.getValue("MATCH_CODE")%>"></td>
	            <td width="20%"><input type="text" class="finput2" readonly value="<%=row.getValue("NLE_NAME")%>"></td>
	            <td width="15%"><input type="text" class="finput2" readonly value="<%=row.getValue("NLE_CODE")%>"></td>
			</tr>
	<%  
		}
	 }
	%>
	</table>
	</div>
	</form>
<% 
    if (rows != null && !rows.isEmpty()) {
%>
<div style="position: absolute; bottom:1px; left: 0; right: 20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>

<%
    }
%>
  </body>
</html>
<script type="text/javascript">
function initPage(){
	do_SetPageWidth();
}

function initPage(){
	do_SetPageWidth();
	do_SetControlBtnEnable();
}

function do_SetControlBtnEnable(){
	var enPic = 5;
	var disPic = 4;
        ShowSinoButton(disPic);
        HideSinoButton(enPic);
}

function do_Search() {
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Create() {
    mainFrm.act.value = "create";
    mainFrm.submit();
}

function do_Delete() {
    var checkedCount = getCheckedBoxCount("coutentCode");
    if (checkedCount < 1) {
        alert("������ѡ��һ�����ݣ�");
        return;
    } else {
    	if(confirm("�Ƿ�ȷ���߼�����Ԫ������ʧЧ������������ȷ����������������ȡ������ť")){
    		mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION %>";
    		mainFrm.submit();
    	}       
    }
}
</script>
