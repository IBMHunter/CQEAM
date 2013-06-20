<%@ page contentType="text/html;charset=GBK" language="java"%>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes"%>
<%@ page import="com.sino.ams.newasset.rolequery.constant.RoleQueryConstant"%>
<%@ page import="com.sino.ams.newasset.rolequery.dto.SfRoleQueryDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<%
		SfRoleQueryDTO dto = (SfRoleQueryDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
		String searchType="SEARCHUSER";
		if(request.getAttribute("searchType")!=null)
		{
			searchType=request.getAttribute("searchType").toString();
		}

	    String contextPath = request.getContextPath();
%>
	<head>
		<title>��ɫ�嵥��ѯ</title>
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
   		 <link rel="stylesheet" type="text/css" href="/WebLibary/css/tab.css">
        <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
		<script language="javascript" src="/WebLibary/js/Constant.js"></script>
		<script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
		<script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
		<script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
		<script language="javascript" src="/WebLibary/js/AppStandard.js"></script>
	    <script type="text/javascript" src="/WebLibary/js/tab.js"></script>
	</head>
	<body onkeydown="autoExeFunction('do_Search()');" onload="initPage();">
		<%=WebConstant.WAIT_TIP_MSG%>
		<form action="<%=RoleQueryConstant.ROLE_QUERY_SERVLET%>"
			name="mainFrm" method="post">
			<script type="text/javascript">
       			 printTitleBar("��ɫ�嵥��ѯ");
		      	var myArrAction1 = new Array(true, "��ѯ�û�", "action_draft.gif", "��ѯ�û�", "do_Search_user");
				var myArrAction4 = new Array(true, "��ѯ��Ŀ", "action_draft.gif", "��ѯ��Ŀ","do_Search_column");
				ArrActions = new Array(myArrAction1, myArrAction4);
				printToolBar();
  			</script>
    
			<input name="searchType" type="hidden" value=""/>
			<input type="hidden" name="act" value="">
			<%
			if(searchType.equals("SEARCHUSER")){
			%>
			<table class="queryTable" border="0"
				style="width: 100%; TABLE-LAYOUT: fixed; word-break: break-all">
				<tr height='30px'>
					<td width="8%" align="center">
						��������
					</td>
					<td width="13%" align="center">
						<input type="text" name="company" style="width: 80%" value="<%=dto.getCompany() %>"><a href="" title="���ѡ�����" onclick="do_SelectCompany(); return false;">[��]</a>
						<input type="hidden" value="<%=dto.getOrganizationId() %>" name="organizationId">
					</td>
					<td width="8%" align="right">
						��Ŀ���ƣ�
					</td>
					<td width="13%" align="right">
						<input type="text" name="resName" style="width: 80%" value="<%=dto.getResName() %>"><a href="" title="���ѡ����Ŀ" onclick="do_SelectRes(); return false;">[��]</a>
					</td>
					<td width="8%" align="right">
						��ɫ���ƣ�
					</td>
					<td width="13%">
						<input type="text" name="roleName" style="width: 100%" value="<%=dto.getRoleName() %>">
					</td>
					<td width="8%" align="right">
						������ƣ�
					</td>
					<td width="13%">
						<input type="text" name="groupName" style="width: 100%" value="<%=dto.getGroupName() %>">
					</td>
					<td width="20%" align="right">
						<img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_Search();"  >&nbsp;
						<img alt="�������û�" src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Export('<%=RoleQueryConstant.ROLE_EXPORT_USER %>')">&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			<%}else if(searchType.equals("SEARCHCOLUMN")){
			 %>
			 	<table class="queryTable" border="0"
				style="width: 100%; TABLE-LAYOUT: fixed; word-break: break-all">
				<tr height='30px'>
					<td width="8%" align="right">
						�û����ƣ�
					</td>
					<td width="13%" align="right">
						<input type="text" name="userName" style="width: 80%" value="<%=dto.getUserName() %>"><a href="" title="���ѡ���û�" onclick="do_SelectUser(); return false;">[��]</a>
					</td>
					<td width="8%" align="right">
						��ɫ���ƣ�
					</td>
					<td width="13%">
						<input type="text" name="roleName" style="width: 100%" value="<%=dto.getRoleName() %>">
					</td>
					<td width="8%" align="right">
						��ɫ������
					</td>
					<td width="13%">
						<input type="text" name="roleDesc" style="width: 100%" value="<%=dto.getRoleDesc() %>">
					</td>
					<td width="8%" align="right">
						������ƣ�
					</td>
					<td width="13%">
						<input type="text" name="groupName" style="width: 100%" value="<%=dto.getGroupName() %>">
					</td>
					<td width="20%" align = "right">
						<img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_Search();"  >&nbsp;
						<img alt="��������Ŀ" src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Export('<%=RoleQueryConstant.ROLE_EXPORT_RES %>')">&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			<%} %>
		</form>
		<% 
			Object obj=request.getAttribute("DTO");
			request.setAttribute("DTO",obj);
			if(searchType.equals("SEARCHCOLUMN")){
			
			%>
			<jsp:include page="queryres.jsp" flush="true"/>
			<%
			} 
			
			if(dto.getAct().equals(RoleQueryConstant.ROLE_QUERY_USER)){
			%>
			<jsp:include page="queryuser.jsp" flush="true"/>
			<%
			}
	
		%>
		
		
		<jsp:include page="/message/MessageProcess" flush="true" />
	</body>
	<iframe width=174 height=189 name="gToday:normal:calendar.js"
		id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm"
		scrolling="no" frameborder="0"
		style="visibility: visible; z-index: 999; position: absolute; left: -500px; top: 0;">
	</iframe>
</html>
<script type="text/javascript">
function initPage(){
	do_SetPageWidth();
}

function do_SearchOrder(action) {
    mainFrm.act.value =action
    mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

//����EXCEL
function do_Export(action){
    mainFrm.act.value = action;
    mainFrm.submit();
}
//��ѯ�û�
function do_Search_user()
{
	 document.getElementById("searchType").value="SEARCHUSER";
	 mainFrm.submit()
} 
//��ѯ��Ŀ
function do_Search_column()
{
	document.getElementById("searchType").value="SEARCHCOLUMN";
	mainFrm.submit();
}
//���ѡ��������
function do_SelectPerson() {
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_PERSON%>";
    var dialogWidth = 47;
    var dialogHeight = 30;
    var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = users[0];
        var pos = user["userName"].indexOf(",");
        var username = "";
        if( pos > -1 ){
        	username =  user["userName"].substring(0, pos );
        }else{
        	username =  user["userName"];
        }
        mainFrm.userName.value = username;
    } else {
        mainFrm.userName.value = "";
    }
}


function do_SelectUser() {
    var lookUpName = "LOOK_UP_ALL_USER";
    var dialogWidth = 47;
    var dialogHeight = 30;
    var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = users[0];
        var pos = user["userName"].indexOf(",");
        var username = "";
        if( pos > -1 ){
        	username =  user["userName"].substring(0, pos );
        }else{
        	username =  user["userName"];
        }
        mainFrm.userName.value = username;
    } else {
        mainFrm.userName.value = "";
    }
}
//ѡ����Ŀ
function do_SelectRes() {
    var lookUpName = "SF_RES_DEFINE";
    var dialogWidth = 47;
    var dialogHeight = 30;
    var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = users[0];
        mainFrm.resName.value =user["resName"];
    } else {
        mainFrm.resName.value = "";
    }
}
//���ѡ�����
function do_SelectCompany() {
    var lookUpName = "OU_CITY_MAP";
    var dialogWidth = 47;
    var dialogHeight = 30;
    var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = users[0];
        mainFrm.company.value =user["company"];
        mainFrm.organizationId.value=user["organizationId"];
    } else {
        mainFrm.company.value = "";
        mainFrm.organizationId.value="";
    }
}
function do_Search()
{
	var str='<%=searchType %>'
	if(str=="SEARCHUSER"){
		document.getElementById("searchType").value="SEARCHUSER";
		mainFrm.act.value='<%=RoleQueryConstant.ROLE_QUERY_USER %>';
	}else if(str=="SEARCHCOLUMN"){
		//mainFrm.action="/servlet/com.sino.ams.newasset.rolequery.servlet.TestServlet";
		document.getElementById("searchType").value="SEARCHCOLUMN";
		mainFrm.act.value='<%=RoleQueryConstant.ROLE_QUERY_RES %>';
	}
	mainFrm.submit();	
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}
</script>