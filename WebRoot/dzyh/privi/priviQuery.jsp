<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.ams.dzyh.constant.DzyhActionConstant" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.dzyh.dto.EamDhPriviDTO" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.ams.dzyh.constant.DzyhLookUpConstant" %>
<%@ include file="/dzyh/privi/headerInclude.htm" %>


<body leftmargin="0" topmargin="0" onload="do_SetPageWidth()" onkeydown="autoExeFunction('do_Search()');">
<%=WebConstant.WAIT_TIP_MSG%>

<%
	EamDhPriviDTO dtoParameter = (EamDhPriviDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String srcPage = dtoParameter.getSrcPage();
	RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = false;
	if(rows != null && !rows.isEmpty()){
		hasData = true;
	}	
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	System.out.println(dtoParameter+"\n---------<<<<<<<<=======================");
	String pageTitle = "��ֵ�׺�Ʒά��>>Ȩ�޶���";
	
%>
<form name="mainFrm" method="post">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>

<jsp:include page="/message/MessageProcess"/>
	<input type="hidden" name="act" value=""/>
	<input type="hidden" name="srcPage" value="<%=srcPage%>"/>
	<input type="hidden" name="orgId" value="<%=dtoParameter.getOrgId()%>"/>
	<input type="hidden" name="deptCode" value="<%=dtoParameter.getDeptCode()%>"/>
	<input type="hidden" name="priviId" value="<%=dtoParameter.getPriviId()%>"/>
	<table width="100%" border="0" bgcolor="#E9EAE9">
	    <tr>
	    	<td width="6%" align="right" height="20">��˾��</td>
	    	<td width="15%" height="20">
<%
if(userAccount.isProvinceUser()){
%>
			<input type="text" name="companyName" class="readonlyInput" readonly style="width:100%;cursor:pointer" onclick="do_SelectCompany();" value="<%=dtoParameter.getCompanyName()%>" size="20">
<%
} else {
%>			
			<input type="text" name="companyName" class="readonlyInput" readonly style="width:100%" value="<%=dtoParameter.getCompanyName()%>" size="20">
<%
}
%>			
			</td>
	    	<td width="6%" height="20" align="right">���ţ�</td>
	    	<td width="15%" height="20"><input type="text" name="deptName" class="readonlyInput" readonly style="width:100%;cursor:pointer" onclick="do_SelectPriviDept();" value="<%=dtoParameter.getDeptName()%>"></td>
	    	<td width="6%" height="20" align="right">����Ա��</td>
	    	<td width="15%" height="20"><input type="text" name="userName" class="readonlyInput" readonly style="width:100%;cursor:pointer" onclick="do_SelectPriviUser();" value="<%=dtoParameter.getUserName()%>"></td>
	    	<td width="16%" align="right" height="20">
	    		<img src="/images/eam_images/search.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Search();" alt="��ѯ">&nbsp;
<%
if(!srcPage.equals(DzyhActionConstant.QUERY_ACTION)){
%>
	    		<img src="/images/eam_images/new_add.jpg" id="newImg" style="cursor:'hand'" onclick="do_CreatePrivis();" alt="����">&nbsp;
	    		<img src="/images/eam_images/delete.jpg" id="deleteImg" style="cursor:'hand'" onclick="do_DeletePrivis();" alt="ɾ��">&nbsp;
<%
}
%>
	    	</td>
	    </tr>
	</table>
</form>	
	<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:838px">
		<table class="headerTable" border="1" width="100%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
<%
if(!srcPage.equals(DzyhActionConstant.QUERY_ACTION)){
%>
	            <td align=center width="4%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
<%
}
%>
				<td align=center width="16%">��˾����</td>
	            <td align=center width="16%">��������</td>
	            <td align=center width="12%">����Ա</td>
	            <td align=center width="10%">�Ƿ���Ч</td>
	            <td align=center width="14%">��¼��</td>
	            <td align=center width="20%">��������</td>
            </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:68%;width:855px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
		row = rows.getRow(i);
%>
			<tr class="dataTR" onclick="executeClick(this)">
<%
if(!srcPage.equals(DzyhActionConstant.QUERY_ACTION)){
%>
				<td width="4%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
<%
}
%>
				<td width="16%" align="left">
				<input type="text" class="finput" readonly value="<%=row.getValue("COMPANY_NAME")%>"></td>
				<td width="16%" align="left">
				<input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
				<td width="12%" align="left">
				<input type="text" class="finput" readonly value="<%=row.getValue("USER_NAME")%>"></td>
				<td width="10%" align="left">
				<input type="text" class="finput" readonly value="<%=row.getValue("ENABLED")%>"></td>
				<td width="14%" align="left">
				<input type="text" class="finput" readonly value="<%=row.getValue("LOGIN_NAME")%>"></td>
				<td width="20%" align="left">
				<input type="text" class="finput" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
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
<div style="position:absolute;top:428px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
}
%>	
</body>
<script language="javascript">

function do_Search(){
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.ams.dzyh.servlet.EamDhPriviServlet";
    mainFrm.act.value="<%=DzyhActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}


function do_SelectCompany(){
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_COMPANY%>";
	var dialogWidth = 38;
	var dialogHeight = 30;
	var depts = lookUpPriviValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.companyName.value = "";
		mainFrm.orgId.value = "";
	}
}

/**
 * ����:ѡ����
 */
function do_SelectPriviDept() {
	var userProp = "<%=userAccount.isProvinceUser()%>";
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_PRI_DEPT%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var userPara = "orgId=" + mainFrm.orgId.value;
	var depts = lookUpPriviValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.deptName.value = "";
		mainFrm.deptCode.value = "";
		if(userProp == "true"){
			mainFrm.companyName.value = "";
			mainFrm.orgId.value = "";
		}
	}
}

/**
 * ����:ѡ���û�
 */
function do_SelectPriviUser() {
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_USER%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = lookUpPriviValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.userName.value = "";
	}
}

/**
 * ���ܣ�ɾ��Ȩ������
 */
function do_DeletePrivis(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ִ�иò�����");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("û��ѡ��Ҫɾ�������ݣ�����ִ�иò�����");
		return;
	}
	if(confirm("����׼��ɾ���û����ʲ�����Ȩ�ޣ�ȷ���𣿼���������ȷ������ť������������ȡ������ť")){
		with(mainFrm){
			target = "_self";
			action = "/servlet/com.sino.ams.dzyh.servlet.EamDhPriviServlet";
			act.value = "<%=DzyhActionConstant.DELETE_ACTION%>";
			submit();
		}
	}
}




function do_CreatePrivis(){
	mainFrm.priviId.value = "";
	mainFrm.companyName.value = "";
	mainFrm.deptName.value = "";
	mainFrm.userName.value="";
	mainFrm.act.value = "<%=DzyhActionConstant.NEW_ACTION%>";
	mainFrm.submit();
}
</script>
