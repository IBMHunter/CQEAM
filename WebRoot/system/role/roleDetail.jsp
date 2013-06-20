<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant"%>
<%@ page import="com.sino.sinoflow.user.dto.SfRoleDTO"%>
<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>��ɫ��ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">		
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>   
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script> 
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarVar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/help.js"></script>
</head>
<script type="text/javascript">
    printTitleBar("ϵͳ��ɫά��")
</script>
<body onload="helpInit('8.1.1');">
<input type="hidden" name="helpId" value="">

<jsp:include page="/message/MessageProcess"/>
<%
    SfRoleDTO role = (SfRoleDTO) request.getAttribute(WebAttrConstant.ROLE_ATTR);
    if(role == null){
    	role = new SfRoleDTO();
    }
%>
<form name="mainFrm" method="POST">
    <input type="hidden" name="show" value="show">
    <input type="hidden" name="roleId" value="<%=role.getRoleId()%>">
    <input type="hidden" name="isInner" value="<%=role.getIsInner()%>">
    <table border="0" width="100%" id="table1" class="detailTb">
    	<tr>
            <td width="25%" align="right" height="22">�������ƣ�</td>
            	<td width="50%" align="left" height="22" >    	
            		<select name="projectName"  class="select_style1" style="width:100%">
		            	<option value="">--��ѡ��--</option>
            			<%= request.getAttribute(WebAttrConstant.PROJECT_OPTION_STR) %>
            		</select>
            	</td>
            <td width="25%" align="left" height="22">&nbsp;<font color="red">*</font></td>
        </tr> 
        <tr>
            <td width="25%" align="right" height="22">��ɫ���ƣ�</td>
            	<td width="50%" align="left" height="22" >
            		<input type="text" name="roleName" size="40"  class="input_style1" style="width:100%" value="<%=role.getRoleName()%>">
            	</td>
            <td width="25%" align="left" height="22">&nbsp;<font color="red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">��ɫ������</td>
            <td width="50%" align="left" height="22" >
              <input type="text" name="roleDesc" size="40"  class="input_style1" style="width:100%" value="<%=role.getRoleDesc()%>">
            </td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
        	<td width="25%" align="right" height="22">�Ƿ�ɼ���</td>
				<td width="50%" align="left" height="22" >
					<select name="enabled"  class="select_style1" style="width:100%">
						<option value="Y">
							��
						</option>
						<option value="N" <%if (role.getEnabled().equals("N")) {%>
							selected <%}%>>
							��
						</option>
					</select>
				</td>
				<td width="25%" align="left" height="22">&nbsp;<font color="red">*</font></td>
			</tr>
        <tr>
            <td align="center" height="22" colspan="3">
                <img src="/images/eam_images/save.jpg"    onClick="do_SaveRole();return false;">&nbsp;&nbsp;&nbsp;
                <img src="/images/eam_images/cancel.jpg"  onClick="do_Back();return false;">&nbsp;&nbsp;&nbsp;
                <img src="/images/eam_images/delete.jpg"  onClick="do_DeleteRole();return false;">&nbsp;&nbsp;&nbsp;
            </td>
        </tr>
		</table>
	<input type="hidden" name="act" value="">
</form>
<%=WebConstant.WAIT_TIP_MSG%>
</body>

<script type="text/javascript">
function do_SaveRole() {
	var fieldNames = "projectName;roleName";
	var fieldLabels = "����;��ɫ����";
	var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
	var roleId = mainFrm.roleId.value;
	
	if(mainFrm.roleName.value.indexOf('*') != -1){
		alert("����Ƿ���ԭ�򣺡���ɫ�����������ַ� * ��");
		return;
	}
	
	if (isValid) {
		var action = "<%=WebActionConstant.CREATE_ACTION%>";
        if (roleId != "" && roleId !=0) {
        	if(confirm("ȷ�ϸ�����Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")){
				action = "<%=WebActionConstant.UPDATE_ACTION%>";
			}else{
				return;
			}
		}
		mainFrm.act.value = action;
		mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfRoleServlet";
		mainFrm.submit();
	}
}

function do_DeleteRole() {
    var roleId = mainFrm.roleId.value;
	if (mainFrm.roleId.value != "") {
	    if (confirm("ȷ��ɾ���ý�ɫ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
	        mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
	        mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfRoleServlet?roleId=" + roleId;
	        mainFrm.roleId.value="";
	    	mainFrm.roleName.value="";
	    	mainFrm.roleDesc.value="";
	        mainFrm.submit();
	    }
	}
}


function do_Back() {
    mainFrm.roleId.value="";
    mainFrm.roleName.value="";
    mainFrm.roleDesc.value="";
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfRoleServlet";
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	mainFrm.submit();
}


function do_Check(object) {
	if (object.value != '') {
		if (!isNumber(object.value)) {
			alert("������Ϸ����֣�");
			object.value = "";
			object.focus();
		} else {
			return true;
		}
	}
}

</script>
</html>