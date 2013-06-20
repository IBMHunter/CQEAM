<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<head>
<script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
</head>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth()" onkeydown="autoExeFunction('do_Search()');">
<%=WebConstant.WAIT_TIP_MSG%>

<%
	AmsAssetsPriviDTO dtoParameter = (AmsAssetsPriviDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String srcPage = dtoParameter.getSrcPage();
	RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = false;
	if(rows != null && !rows.isEmpty()){
		hasData = true;
	}	
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	String pageTitle = "�ʲ�Ȩ������>>�ʲ�Ȩ�޶���";
	if(srcPage.equals(AssetsActionConstant.QUERY_ACTION)){
		pageTitle = "�ʲ�Ȩ������>>�ʲ�Ȩ�޲�ѯ";
	}
%>
<form name="mainFrm" method="post">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>

<jsp:include page="/message/MessageProcess"/>
	<input type="hidden" name="act" value="">
	<input type="hidden" name="srcPage" value="<%=srcPage%>">
	<input type="hidden" name="companyCode" value="<%=dtoParameter.getCompanyCode()%>">
	<input type="hidden" name="deptCode" value="<%=dtoParameter.getDeptCode()%>">
	<input type="hidden" name="roleId" value="<%=dtoParameter.getRoleId()%>">
	<table width="100%" border="0" class="queryTable">
	    <tr>
	    	<td width="6%" align="right" height="20">��˾��</td>
	    	<td width="15%" height="20">
                <select name="organizationId" class="select_style1" style="width:100%"><%=request.getAttribute(AssetsWebAttributes.CITY_OPTION)%></select>
			<%--<input type="text" name="company" class="readonlyInput" readonly style="width:100%;cursor:pointer" onclick="do_SelectCompany();" value="<%=dtoParameter.getCompanyName()%>" size="20">--%>
			</td>
	    	<td width="6%" height="20" align="right">���ţ�</td>
	    	<td width="15%" height="20"><input type="text" name="deptName" class="input_style2"  readonly style="width:100%;cursor:pointer" onclick="do_SelectPriviDept();" value="<%=dtoParameter.getDeptName()%>"></td>
	    	<td width="6%" align="right" height="20">��ɫ��</td>
	    	<td width="15%" height="20"><input type="text" name="roleName" class="input_style2"  readonly style="width:100%;cursor:pointer" onclick="do_SelectPriviRole();" value="<%=dtoParameter.getRoleName()%>"></td>
	    	<td width="6%" height="20" align="right">�û���</td>
	    	<td width="15%" height="20"><input type="text" name="userName" class="input_style2"  readonly style="width:100%;cursor:pointer" onclick="do_SelectPriviUser();" value="<%=dtoParameter.getUserName()%>"></td>
	    	<td width="16%" align="right" height="20">
	    		<img src="/images/eam_images/search.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Search();" alt="��ѯ">&nbsp;
<%
	if(!srcPage.equals(AssetsActionConstant.QUERY_ACTION)){
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
	<div id="headDiv" style="overflow-x:hidden;overflow-y:scroll;position:absolute;top:45px;left:1px;width:838px">
		<table class="headerTable" border="1" width="100%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
<%
	if(!srcPage.equals(AssetsActionConstant.QUERY_ACTION)){
%>
	            <td align=center width="4%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
<%
	}
%>
				<td align=center width="16%">��˾����</td>
	            <td align=center width="32%">��������</td>
	            <td align=center width="24%">��ɫ����</td>
	            <td align=center width="10%">�û�����</td>
	            <td align=center width="14%">��¼��</td>
	            <td style="display:none">&nbsp;</td>
            </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:330px;width:855px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
			<tr class="dataTR" onclick="executeClick(this)">
<%
	if(!srcPage.equals(AssetsActionConstant.QUERY_ACTION)){
%>
				<td width="4%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
<%
	}
%>
				<td width="16%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("COMPANY")%>"></td>
				<td width="32%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
				<td width="24%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("ROLE_NAME")%>"></td>
				<td width="10%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("USER_NAME")%>">&nbsp;</td>
				<td width="14%" align="left"><input type="text" class="finput" readonly value="<%=row.getValue("LOGIN_NAME")%>">&nbsp;</td>
			<td style="display:none">&nbsp;</td>
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
<div style="position:absolute;top:400px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%		
	}
%>	
</body>
</html>
<script language="javascript">

function do_Search(){
	mainFrm.target = "_self";
	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsPriviServlet";
    mainFrm.act.value="<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}


function do_SelectCompany(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_COMPANY%>";
	var dialogWidth = 38;
	var dialogHeight = 30;
	var depts = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.companyName.value = "";
		mainFrm.companyCode.value = "";
	}
}

/**
 * ����:ѡ����
 */
function do_SelectPriviDept() {
	var userProp = "<%=userAccount.isProvinceUser()%>";
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_PRI_DEPT%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var userPara = "companyCode=" + mainFrm.companyCode.value;
	var depts = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.deptName.value = "";
		mainFrm.deptCode.value = "";
		if(userProp == "true"){
			mainFrm.companyName.value = "";
			mainFrm.companyCode.value = "";
		}
	}
}



/**
 * ����:ѡ���ɫ
 */
function do_SelectPriviRole() {
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_PRI_ROLE%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.roleName.value = "";
	}
}

/**
 * ����:ѡ���û�
 */
function do_SelectPriviUser() {
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_USER%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
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
			action = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsPriviServlet";
			act.value = "<%=AssetsActionConstant.DELETE_ACTION%>";
			submit();
		}
	}
}

function do_CreatePrivis(){
	var target = "detailWin";
	var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
	window.open("/public/waiting2.htm", target, style);
	mainFrm.target = target;
	mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.AssetsPriviFrmServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.NEW_ACTION%>";
	mainFrm.submit();
}
</script>
