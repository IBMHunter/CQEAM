<%@ page contentType="text/html; charset=GBK" language="java" errorPage=""%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant" %>
<%@ page import="com.sino.sinoflow.user.dto.SfGroupDTO"%>
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>�����ϸ��Ϣ</title>
	<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">		
	<script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
	<script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
	<script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
	<script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
	<script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
	<script type="text/javascript" src="/WebLibary/js/clientRowSet.js"></script>
	<script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script> 
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarVar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/help.js"></script>
</head>
 <script type="text/javascript">
    printTitleBar("ϵͳ���ά��")
 </script>
<body onload="helpInit('8.1.2');">
<input type="hidden" name="helpId" value="">

	<jsp:include page="/message/MessageProcess" flush="true" />
	<%
		SfGroupDTO group = (SfGroupDTO) request.getAttribute(WebAttrConstant.GROUP_ATTR);
	%>
	<form name="mainFrm" method="POST">
		<input type="hidden" name="show" value="show">
		<input type="hidden" name="groupId" value="<%=group.getGroupId()%>">
		<table border="0" width="100%" id="table1"  align="center">
			<tr>
				<td width="25%" align="right" height="22">
					�������ƣ�
				</td>
				<td width="50%" align="left" height="22" >	
					<select name="projectName"  class="select_style1" style="width:100%" onchange="do_OptionStr()">
						<option value="">--��ѡ��--</option>
						<%= request.getAttribute(WebAttrConstant.PROJECT_OPTION_STR) %>
					</select> 
				</td>
				<td width="25%" align="left" height="22">&nbsp;<font color="red">*</font></td>
			</tr>
			
			<tr>
				<td width="25%" align="right" height="22">
					������ƣ�
				</td>
				<td width="50%" align="left" height="22" >
					<input type="text" name="groupName" size="40"
						 class="input_style1" style="width:100%" value="<%= group.getGroupName() %>">
				</td>
				<td width="25%" align="left" height="22">&nbsp;<font color="red">*</font></td>
			</tr>
			
			
			<tr>
				<td width="25%" align="right" height="22">
					���������
				</td>
				<td width="50%" align="left" height="22" >
					<input type="text" name="groupDesc" size="40"
						 class="input_style1" style="width:100%" value="<%= group.getGroupDesc() %>">
				</td>
				<td width="25%" align="left" height="22"></td>
			</tr>
	
			<tr>
				<td width="25%" align="right" height="22">
					�ϼ����
				</td>
				<td width="50%" align="left" height="22" id="gp">
					<select name="parentId"  class="select_style1" style="width:100%">
						<option value="">--��ѡ��--</option>
						<%= request.getAttribute(WebAttrConstant.GROUP_OPTION_STR) %>
					</select>
				<td width="25%" align="left" height="22">&nbsp;<font color="red">*</font></td>
			</tr>
			
			<tr>
				<td width="25%" align="right" height="22">
					�Ƿ���Ч��
				</td>
				<td width="50%" align="left" height="22" >
					<select name="enabled"  class="select_style1" style="width:100%">
						<option value="Y" checked>
							��
						</option>
						<option value="N" <%if (group.getEnabled().equals("N")) {%>
							selected <%}%>>
							��
						</option>
					</select>
				</td>
				<td width="25%" align="left" height="22">&nbsp;<font color="red">*</font></td>
			</tr>
			<br>
            <tr align="center">
              <td align="center" height="22" colspan="3">
                <img src="/images/eam_images/save.jpg"    onClick="do_SaveGroup();return false;">&nbsp;&nbsp;&nbsp;
                <img src="/images/eam_images/cancel.jpg"  onClick="do_Back();return false;">&nbsp;&nbsp;&nbsp;
                <img src="/images/eam_images/delete.jpg"  onClick="do_DeleteGroup();return false;">&nbsp;&nbsp;&nbsp;
              </td>
            </tr>
		</table>
		<input type="hidden" name="act" value="">
	</form>
	<%=WebConstant.WAIT_TIP_MSG%>
 </body>
</html>
<script type="text/javascript">
function do_SaveGroup() {
    var fieldNames = "projectName;groupName";
    var fieldLabels = "����;�������";
    var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);

    if (isValid) {
        var action = "<%=WebActionConstant.CREATE_ACTION%>";
        if (mainFrm.groupId.value > 0 ) {
        	if(confirm("ȷ�ϸ�����Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")){
            	action = "<%=WebActionConstant.UPDATE_ACTION%>";
            }else{
            	return;
            }
        }
        
        var tv = mainFrm.parentId;
		var text = tv.options[tv.selectedIndex].text;
		var gn = mainFrm.groupName;
		
		if(gn.value.indexOf('.')==-1){
			if(text != "--��ѡ��--"){
				gn.value = text +"." + gn.value; 	
			} 
		}
        
        mainFrm.act.value = action;
        mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfGroupServlet";
        mainFrm.submit();
    }
}

function do_DeleteGroup() {
    var groupId = mainFrm.groupId.value;
    if (mainFrm.groupId.value != "") {
	    if (confirm("ȷ��ʧЧ������𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
	        mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
	        mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfGroupServlet?groupId=" + groupId;
	        mainFrm.enabled.value = "N";
	        mainFrm.groupName.value="";
	    	mainFrm.groupDesc.value="";
	    	mainFrm.enabled.value="";
	        mainFrm.submit();
	    }
    }
}
function do_HistoryBack() {
    if (mainFrm.resId.value == "") {
        if (!isEmpty(mainFrm.resName.value) || !isEmpty(mainFrm.resUrl.value)) {
            if (confirm("�������β����𣿼�������ȷ����ť����������ȡ����ť��")) {
                do_Back();
            }
        } else {
            do_Back();
        }
    } else {
        do_Back();
    }
}

function do_Back() {
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfGroupServlet";
    mainFrm.submit();
}

function do_DeleteResource() {
    var resId = mainFrm.resId.value;
    if (confirm("ʧЧ����Ŀ������ʧЧ������������Ŀ��ȷ��ʧЧ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
        mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
        mainFrm.action = "/servlet/com.sino.sinoflow.resource.servlet.ResourceProcessServlet?resId=" + resId;
        mainFrm.submit();
    }
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


function do_Change() {
    var radio = getRadioValue("isPopup");
    if (radio == 'Y') {
        document.mainFrm.popscript.disabled = false;
    } else {
        document.mainFrm.popscript.disabled = true;
    }
}

function do_OptionStr(){
	var pName = document.mainFrm.projectName.value;
	var str = "<select name='parentId' style='width:100%'><option value=''>--��ѡ��--</option>";
	var str2 = "</select>";
	if(pName == ""){
		document.getElementById("gp").innerHTML = str+str2;
		return;
	}
	
	try{
          var crs = new clientRowSet();
          crs.modelClassName = "com.sino.sinoflow.user.model.SfGroupModel";
          crs.methodName = "getOptionGroupModel";
          crs.methodParameterName = new Array(mainFrm.projectName.value);
          var res = crs.send_request();
	    	var optionStr = "";
	   		for(var i = 0;i<res.length;i++){
			optionStr += "<option value='"+res[i].GROUP_ID+"'>"+res[i].GROUP_NAME+"</option>"; 
			}
	       	optionStr = str+optionStr+str2;
	       	document.getElementById("gp").innerHTML = optionStr;
         	
       }catch(e){
           alert(e);
       }
}
</script>
