<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.framework.security.dto.ServletConfigDTO" %>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant" %>
<%@ page import="com.sino.sinoflow.user.dto.SfUserBaseDTO" %>
<%@ page import="com.sino.base.util.ReflectionUtil" %>

<%
    SfUserBaseDTO user = (SfUserBaseDTO) request.getAttribute(WebAttrConstant.USER_ATTR);
    if (user == null) user = new SfUserBaseDTO();
    String str = StrUtil.nullToString(request.getAttribute("str"));
    String strDept = StrUtil.nullToString(request.getAttribute("strDept"));
    String strRole = StrUtil.nullToString(request.getAttribute("strRole"));
    ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(request);
    String sysAdminRole = servletConfig.getSysAdminRole();
    String startDate ="";
    String endDate ="";
    String startDateT ="";
    String endDateT ="";
    String userName = user.getUsername();
    if(user.getStartDate()!=null){
     	startDate = user.getStartDate().getCalendarValue();
     	if(!"1900-01-01".equals(startDate)){startDateT=startDate;}
   	}
    if(user.getEndDate()!=null){
        endDate =user.getEndDate().getCalendarValue();
        if(!"1900-01-01".equals(endDate)){endDateT=endDate;}
    }
    
%>

<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>�û���ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/clientRowSet.js"></script>
    <script type="text/javascript" src="/WebLibary/js/OperationProjectGroupRole.js"></script>
    <script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarVar.js"></script>
    <script language="javascript" src="/WebLibary/js/tab.js"></script>
    <script type="text/javascript" src="/WebLibary/js/help.js"></script>
    <script type="text/javascript">
        var winstyle = "dialogWidth:20.1;dialogHeight:14.8;center:yes;status:no;help:no";
    </script>
  	<script type="text/javascript">
    var rightArr = new Array();
       
    var winstyle = "dialogWidth:35.1;dialogHeight:17.8;center:yes;status:no";

    var ArrAction0 = new Array(true, "����", "action_save.gif", "����", "do_SaveUser");
    var ArrAction1 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "do_Back");
    var ArrActions = new Array( ArrAction0,ArrAction1);
    var ArrSinoViews = new Array();
    var ArrSinoTitles = new Array();
          </script>
</head>
 
<body onload="init();helpInit('8.1.3');">
<input type="hidden" name="helpId" value="">

<jsp:include page="/message/MessageProcess"/>
<script type="text/javascript">
    var tabBox = new TabBox("tttt")
    tabBox.addtab("baseInfo", "�û�������Ϣ")
    tabBox.addtab("deptInfo", "�����ʲ�Ȩ������")
    tabBox.addtab("catInfo", "רҵ�ʲ�Ȩ������")
    printTitleBar("ϵͳ�û�ά��");
    printToolBar();
//     tabBox.init();
</script>
<form name="mainFrm" method="post" action="">
    <div style="overflow-y:scroll;width:100%;left:1px;height:450px" align="center">
    <div id="deptInfo" style="display:none;">
      <table border = "0" width = "100%" id = "table3">
    <tr>
       <td width = "15%" align = "right">�����ʲ��鿴Ȩ�ޣ�</td>
            <td width = "45%"> <select name = "deptList" size = "2"
                    style = "height:200;width:100%">
            </select></td>
            <td width = "15%">
                <p><input name = "dept_add" type = "button" onclick = "add_dept()" value = "���Ӳ���"></p>
            <p><input name = "dept_delete" type = "button" onclick = "remove_dept()" value = "ɾ������"></p>
            </td>
        <td ></td>

    </tr>
          </table>
</div>
<div id="catInfo" style="display:none;">
    <table border = "0" width = "100%" id = "table2">
        <tr height="30"></tr>
        <tr></tr>
        <tr>
    <td width = "15%" align = "right">רҵ�ʲ��鿴Ȩ�ޣ�</td>
            <td width="25">
                <input type="radio" style="width:60%" name="manRadio" id="manRadio" title="�������ʲ�" value="1" <%=user.getCategoryCode().equals("MGR-ASSETS")?"checked":""%>>����
                <input type="radio" style="width:60%" name="manRadio" id="manRadio1" title="�������ʲ�" value="2" <%=user.getCategoryCode().equals("NET-ASSETS")?"checked":""%>>����
                </td>
          <td width="15">   </td>
             <td width="15">        </td>
        </tr>
        </table>
</div>
    <div id="baseInfo" style ="width:100%;position: absolute;left:1px">
        <table border="1" bordercolor="#666666" width="98%" id="containTable" align="center" class="detailTb">
            <tr>
                <td>
                    <table border="0" width="100%" id="table1" align="center" class="detailTb">
                        <tr>
                            <td width="10%" align="right" height="22">��˾���ƣ�</td>
                            <td width="25%" align="left" height="22">
                                <select style="width:95%" name="orgId"><%=request.getAttribute("OU_OPTIONS")%></select><font color="#FF0000">*</font>
                            </td>
                            <td width="10%" align="right" height="22">�û�������</td>
                            <td width="25%" align="left" height="22">
                                <input type="text" name="username" class="input_style1"  style="width:95%" value="<%=user.getUsername()%>"><font color="#FF0000">*</font>
                            </td>
                            <td width="10%" align="right" height="22">��ʾ��ţ�</td>
                            <td width="20%" align="left" height="22">
                                <input type="text" name="displayOrder" class="input_style1"  style="width:95%" value="<%=user.getDisplayOrder()%>">
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" align="right" height="22">��¼�ʺţ�</td>
                            <td width="25%" align="left" height="22">
                                <input type="text" name="loginName" class="input_style1"  style="width:95%" value="<%=user.getLoginName()%>"><font color="#FF0000">*</font>
                            </td>
                            <td width="10%" align="right" height="22">��¼���룺</td>
                            <td width="25%" align="left" height="22">
                                <input type="password" name="password" class="input_style1"  style="width:95%" value="<%=user.getPassword()%>"><font color="#FF0000">*</font>
                            </td>
                            <td width="10%" align="right" height="22">OA�˺ţ�</td>
                            <td width="20%" align="left" height="22">
                                <input type="text" name="oaName" class="input_style1"  style="width:95%" value="<%=StrUtil.nullToString(ReflectionUtil.getProperty(user, "oaName"))%>">
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" align="right" height="22">Ա����ţ�</td>
                            <td width="25%" align="left" height="22">
                                <input type="text" name="employeeNumber" class="input_style1"  style="width:95%" value="<%=user.getEmployeeNumber()%>">
                            </td>
                            <td width="10%" align="right" height="22">�칫�绰��</td>
                            <td width="25%" align="left" height="22">
                                <input type="text" name="officeTel" class="input_style1" style="width:95%" value="<%=user.getOfficeTel()%>">
                            </td>
                            <td width="10%" align="right" height="22">E-Mail��</td>
                            <td width="20%" align="left" height="22">
                                <input type="text" name="email" class="input_style1" style="width:95%" value="<%=user.getEmail()%>">
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" align="right" height="22">������룺</td>
                            <td width="25%" align="left" height="22">
                                <input type="text" name="fax" class="input_style1" style="width:95%" value="<%=user.getFax()%>">
                            </td>
                            <td width="10%" align="right" height="22">�ƶ��绰��</td>
                            <td width="25%" align="left" height="22">
                                <input type="text" name="mobilePhone" class="input_style1" style="width:95%" value="<%=user.getMobilePhone()%>">
                            </td>
                            <td width="10%" align="right" height="22">����ʱ�䣺</td>
                            <td width="20%" align="left" height="22">
                                <select name="workScheduleId" style="width:95%">
                                    <option value="">--��ѡ��--</option>
                                    <%=request.getAttribute("workTime") %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" align="right" height="22">�Ƿ�ʧЧ��</td>
                            <td width="25%" align="left" height="22">
                                <select name="enabled" style="width:95%">
                                    <%=request.getAttribute("enabledOptionString") %>
                                </select><font color="#FF0000">*</font>
                            </td>
                            <td width="10%" align="right" height="22">��Чʱ�䣺</td>
                            <td width="25%" align="left" height="22">
                                <input type="text" name="startDate" class="input_style1" style="width:95%"  readonly="readonly"
                                onclick="gfPop.fStartPop(startDate, endDate)" value="<%=startDateT%>">
                            </td>
                            <td width="10%" align="right" height="22">ʧЧʱ�䣺</td>
                            <td width="20%" align="left" height="22">
                                <input type="text" name="endDate" class="input_style1" style="width:95%"  readonly="readonly"
                                onclick="gfPop.fEndPop(startDate,endDate)" value="<%=endDateT%>">
                            </td>
                        </tr>
                    </table>
                    <table width="100%" border="0">
                        <tr>
                            <td width="35%" colspan="2" align="center" class="erji">����</td>
                            <td width="35%" colspan="2" align="center" class="erji">���</td>
                            <td width="30%" colspan="2" align="center" class="erji">��ɫ</td>
                        </tr>
                        <tr style="height:220px">
                            <td width="10%" align="center">
                                <p><input  name="project_add" type="button" onclick="add_Project();" value="���ӹ���"></p>
                                <p><input  name="project_delete" type="button" onclick="remove_Project();" value="ɾ������"></p>
                            </td>
                            <td width="25%" align="left">
                                <select name="projectName" size="2" id="projectList" style="height:100%;width:95%" onchange="show_Group();">
                                </select>
                            </td>

                            <td  width="10%" align="center">
                                <p><input  name="group_add" type="button" onclick="add_Group();" value="�������"></p>
                                <p><input  name="group_delete" type="button" onclick="remove_Group();" value="ɾ�����"></p>
                            </td>

                            <td  width="25%" align="left">
                                <select name="groupName" size="2" id="groupList" onchange="show_Role();" style="height:100%;width:95%;">
                                </select>
                            </td>

                            <td  width="10%" align="center">
                                <p><input  name="role_add" type="button" onclick="add_Role();" value="���ӽ�ɫ"></p>
                                <p><input  name="role_delete" type="button" onclick="remove_Role();" value="ɾ����ɫ"></p>
                            </td>
                            <td  width="20%" align="left">
                                <select name="roleName" size="2" multiple id="roleList" style="height:100%;width:95%;">
                                </select>
                            </td>
                        </tr>
                       <tr align="center">
                          <td align="center" height="22" colspan="6"></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
        <input type="hidden" name="act" value="">
        <input type="hidden" name="userId" value="<%=user.getUserId()%>">
        <input type="hidden" name="rightChanged" value="false">
        <input type="hidden" name="str" value="<%= str %>"/>
        <input type="hidden" name="strDept" value="<%= strDept %>"/>
        <input type="hidden" name="strRole" value="<%= strRole %>"/>
        <input type="hidden" name="strRemark" value=""/>
    </div>
</form>
<%= WebConstant.WAIT_TIP_MSG%>
		<iframe width=174 height=189 name="gToday:normal:calendar.js"
			id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm"
			scrolling="no" frameborder="0"
			style="visibility: visible; z-index: 999; position: absolute; left: -500px; top: 0;">
		</iframe>
</body>
</html>
<script type="text/javascript">
var enabled = mainFrm.enabled.value;
var roleListOld = "";
function do_SaveUser() {// �����û���Ϣ
    formatStr();
    formatDeptStr();
    var date = new Date();
    var strRemark = "�����ߣ�" + '<%=userName %>' + "������ʱ�䣺" + date.toLocaleDateString() + "��";
    if (mainFrm.loginName.value != '<%=user.getLoginName() %>') {
    	strRemark += "��½�ʺš�ԭֵ��" + '<%=user.getLoginName() %>' + "����ֵ��" + mainFrm.loginName.value + "��";
    } 
    if (mainFrm.username.value != '<%=user.getUsername() %>') {
    	strRemark += "�û�������ԭֵ��" + '<%=user.getUsername() %>' + "����ֵ��" + mainFrm.username.value + "; ";
    } 
    if (mainFrm.oaName.value != '<%=StrUtil.nullToString(ReflectionUtil.getProperty(user, "oaName"))%>') {
    	strRemark += "OA�˺š�ԭֵ��" + '<%=StrUtil.nullToString(ReflectionUtil.getProperty(user, "oaName"))%>' + "����ֵ��" + mainFrm.oaName.value + "; ";
    }
    if (mainFrm.officeTel.value != '<%=user.getOfficeTel() %>') {
    	strRemark += "�칫�绰��ԭֵ��" + '<%=user.getOfficeTel() %>' + "����ֵ��" + mainFrm.officeTel.value + "; ";
    } 
    if (mainFrm.fax.value != '<%=user.getFax() %>') {
    	strRemark += "������롢ԭֵ��" + '<%=user.getFax() %>' + "����ֵ��" + mainFrm.fax.value + "; ";
    } 
    if (mainFrm.password.value != '<%=user.getPassword() %>') {
    	strRemark += "���롢ԭֵ��" + '<%=user.getPassword() %>' + "����ֵ��" + mainFrm.password.value + "; ";
    } 
    if (mainFrm.mobilePhone.value != '<%=user.getMobilePhone() %>') {
   		strRemark += "�ƶ��绰��ԭֵ��" + '<%=user.getMobilePhone() %>' + "����ֵ��" + mainFrm.mobilePhone.value + "; ";
    } 
    if (mainFrm.email.value != '<%=user.getEmail() %>') {
    	strRemark += "E-mail��ԭֵ��" + '<%=user.getEmail() %>' + "����ֵ��" + mainFrm.email.value + "; ";
    } 
    if (mainFrm.enabled.value != enabled) {
    	strRemark += "�Ƿ�ʧЧ����ֵ��" + mainFrm.enabled.value + "; ";
    }
    if (mainFrm.str.value != '<%=str%>') {
    	strRemark += "Ȩ�ޡ�ԭֵ��" + '<%=str %>' + "����ֵ��" + mainFrm.str.value + "; ";
    }
    mainFrm.strRemark.value = strRemark.substring(0,strRemark.length-1);
    if (validForm()) {
       if(validPassword()){
	        var action = "<%=WebActionConstant.CREATE_ACTION%>";
	        if ( patchCreateOrUpdate( mainFrm.userId.value ) ) {
	            if (confirm("ȷ�ϸ��¸��û��𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
	                action = "<%=WebActionConstant.UPDATE_ACTION%>";
	            } else {
	                return;
	            }
	        }
	        mainFrm.act.value = action;	
	        mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfUserServlet";
	
	        mainFrm.submit();
       }
    }
}


function validPassword() {
   var validate=true;
   if(mainFrm.password.value != '<%=user.getPassword() %>'){
		if(mainFrm.password.value.length>=8){
			var reg= /(?=[!-~]{8,20})(?=[!-~]*[^0-9]+)(?=[!-~]*\d+)/;
			if (!reg.exec(mainFrm.password.value)) {
				alert("�����б��������ĸ������!");
				validate=false;
			}
		}else{
			alert("���벻������8λ");
			validate=false;
		}
	}
	return validate;
}


function validForm() {
    var fieldNames = "orgId;loginName;username;password";
    var fieldLabels = "OU;��¼�˺�;�û�����;�û�����";
    return formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
}
function do_DeleteUser() {//ɾ���û�
    var userId = mainFrm.userId.value;
    if (userId != "") {
        mainFrm.userId.value = "";
        mainFrm.loginName.value = "";
        mainFrm.username.value = "";
        if (confirm("ȷ��ɾ����Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
            mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
            mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfUserServlet?userId=" + userId;
            mainFrm.submit();
        }
    }
}

function do_Back() {//����
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.action = "/servlet/com.sino.sinoflow.user.servlet.SfUserServlet";
    mainFrm.submit();
}

var proGroupArr = new Array();
var proGroupRoleArr = new Array();
var deptArr = new Array();
var deptCodeArr=new Array();
var project_Obj = document.getElementById("projectList");
var group_Obj = document.getElementById("groupList");
var dept_Obj = document.getElementById("deptList");
var role_Obj = document.getElementById("roleList");


function add_Project() {
    var retuenValue = window.showModalDialog("/system/showOptions.jsp", getProject(), winstyle);
    if (retuenValue) {
        arr = explode(retuenValue, ";");
        add_Option(project_Obj, arr);
    }
}

function add_Group() {
    var project = mainFrm.projectName.value;
    if (project == "") {
        alert("��ѡ�񹤳�");
        return;
    }
    var orgId = mainFrm.orgId.value;
    var retuenValue = window.showModalDialog("/system/showOptions.jsp", getGroup(project, orgId), winstyle);
    if (retuenValue) {
        arr = explode(retuenValue, ";");
        var addArr = add_Option(group_Obj, arr);
        add_Arr(project_Obj, null, addArr);
        show_Role();
    }
}
 function add_dept() {

    var orgId = mainFrm.orgId.value;
    var retuenValue = window.showModalDialog("/system/showOptions.jsp", getDept( orgId), winstyle);
    if (retuenValue) {
        arr = explode(retuenValue, ";");
        var addArr = add_Option(dept_Obj, arr);
         for (var i = 0; i < addArr.length; i++) {
            var tempArr = new Array(null, addArr[i]);
            deptArr[deptArr.length] = tempArr;
        }
//        add_Arr(null, null, addArr);
    }
}

function roleSelectAll(){
	for(var i=0; i < mainFrm.roleList.length; i++) {
		mainFrm.roleList.options[i].selected = true;
	}
}
function roleUnSelectAll(){
	for(var i=0; i< mainFrm.roleList.length; i++) {
		mainFrm.roleList.options[i].selected = false;
	}
}

function add_Role() {
	
    var project = mainFrm.projectName.value;
    var sysAdminRole = "<%=sysAdminRole%>";
    if (project == "" || mainFrm.groupName.value == "") {
        alert("δѡ�񹤳������");
        return;
    }

    var retuenValue = window.showModalDialog("/system/showOptions.jsp", getRole(project, sysAdminRole), winstyle);
    if (retuenValue) {
        arr = explode(retuenValue, ";");
        var addArr = add_Option(role_Obj, arr);
        add_Arr(project_Obj, group_Obj, addArr);
    }
}

function remove_Group() {//�Ƴ�ѡ�е����
    var del = delete_option(group_Obj);
    delete_Arr(null, group_Obj, null, del);
    group_Obj.onchange();
}
function remove_Project() {//�Ƴ�ѡ�е���Ŀ
    var del = delete_option(project_Obj);
    delete_Arr(project_Obj, null, null, del);
    project_Obj.onchange();
}

function remove_dept() {//�Ƴ�ѡ�е����
    var del = delete_option(dept_Obj);
    delete_Arr(null, dept_Obj, null, del);
    dept_Obj.onchange();
}

function remove_Role() {//�Ƴ�ѡ�еĽ�ɫ

    var del = delete_option(role_Obj);
    delete_Arr(null, null, role_Obj, del);
}


function show_Group() {//��ʾ���
    if (proGroupArr != null) {
        group_Obj.options.length = 0;
        role_Obj.options.length = 0;
        for (var i = 0; i < proGroupArr.length; i++) {
            if (proGroupArr[i][0] == project_Obj.value) {
                group_Obj.add(new Option(proGroupArr[i][1], proGroupArr[i][1]));
            }
        }
    }
}

function show_Role() {//��ʾ��ɫ
    if (proGroupRoleArr != null) {
        role_Obj.options.length = 0;
        for (var i = 0; i < proGroupRoleArr.length; i++) {
            if (proGroupRoleArr[i][0] == project_Obj.value &&
                proGroupRoleArr[i][1] == group_Obj.value) {
                role_Obj.add(new Option(proGroupRoleArr[i][2], proGroupRoleArr[i][2]));
            }
        }
    }
}

function delete_Arr(project, group, role, args) {//�Ƴ������������е�ѡ��

    if (project != null && group == null && role == null) {
        for (var i = 0; i < args.length; i++) {
            for (var j = 0; j < proGroupArr.length; j++) {
                if (proGroupArr[j][0] == args[i]) {
                    proGroupArr.splice(j, 1);
                    j--;
                }
            }

            for (var j = 0; j < proGroupRoleArr.length; j++) {
                if (proGroupRoleArr[j][0] == args[i]) {
                    proGroupRoleArr.splice(j, 1);
                    j--;
                }
            }
        }
    } else if (group != null && project == null && role == null) {
        for (var i = 0; i < args.length; i++) {
            for (var j = 0; j < proGroupRoleArr.length; j++) {
                if (proGroupRoleArr[j][0] == project_Obj.value &&
                    proGroupRoleArr[j][1] == args[i]) {
                    proGroupRoleArr.splice(j, 1);
                    j--;
                }
            }

            for (var j = 0; j < proGroupArr.length; j++) {
                if (proGroupArr[j][0] == project_Obj.value
                        && proGroupArr[j][1] == args[i]) {
                    proGroupArr.splice(j, 1);
                }
            }
        }
    } else if (role != null && group == null && project == null) {
        for (var i = 0; i < args.length; i++) {
            for (var j = 0; j < proGroupRoleArr.length; j++) {
                if (proGroupRoleArr[j][0] == project_Obj.value &&
                    proGroupRoleArr[j][1] == group_Obj.value &&
                    proGroupRoleArr[j][2] == args[i]) {
                    proGroupRoleArr.splice(j, 1);
                    break;
                }
            }
        }
    }
}

function add_Arr(project, group, arr) {//��ѡ�е������б���ӵ�������

	//roleListOld = mainFrm.roleList.value;

    if (group == null) {
        for (var i = 0; i < arr.length; i++) {
            var tempArr = new Array(project.value, arr[i]);
            proGroupArr[proGroupArr.length] = tempArr;
        }
    } else {
        for (var i = 0; i < arr.length; i++) {
            var tempArr = new Array(project.value, group.value, arr[i]);
            proGroupRoleArr[proGroupRoleArr.length] = tempArr;
        }
    }
}
 function formatDeptStr() {//�����������ʽ��Ϊ�ַ���������ֵ����������
    if (deptArr.length < 0) {
        return;
    }
    var str = "";
    for (var i in deptArr) {
        str += deptArr[i] + ";"
    }
    str = str.substring(0, str.length - 1);
    mainFrm.strDept.value = str;
}
function formatStr() {//�����������ʽ��Ϊ�ַ���������ֵ����������
    if (proGroupRoleArr.length < 0) {
        return;
    }
    var str = "";
    for (var i in proGroupRoleArr) {
        str += proGroupRoleArr[i][0] + "," + proGroupRoleArr[i][1] + "," + proGroupRoleArr[i][2] + ";"
    }
    str = str.substring(0, str.length - 1);
    mainFrm.str.value = str;
}
function unDeptFromatStr() {//���ַ�����ԭ������
    var str = mainFrm.strDept.value;
    var argArr = str.split(";");
    for (var i = 0; i < argArr.length; i++) {
         var arr = argArr[i].split(",");
       /* if (deptArr.length == 0) {
            deptArr[deptArr.length] = new Array(arr[0], arr[0]);
        } else {
             deptArr[i][0]=arr[i][0];
             deptArr[deptArr.length]= new Array(arr[i], arr[0]);
        }*/
        if (deptArr.length == 0) {
            deptArr[deptArr.length] = new Array(arr[0]);
        } else {
           for (var j = 0; j < deptArr.length; j++) {
                if (arr[0] == deptArr[j]) {
                    break;
                }
                if (j == deptArr.length - 1) {
                    deptArr[deptArr.length] = new Array(arr[0]);
                }
            }
        }


    }
}
function unFromatStr() {//���ַ�����ԭ������
    var str = mainFrm.str.value;
    var argArr = str.split(";");

    for (var i = 0; i < argArr.length; i++) {
        var arr = argArr[i].split(",");
        if (proGroupArr.length == 0) {
            proGroupArr[proGroupArr.length] = new Array(arr[0], arr[1]);
        } else {
            for (var j = 0; j < proGroupArr.length; j++) {
                if (arr[0] == proGroupArr[j][0] && arr[1] == proGroupArr[j][1]) {
                    break;
                }
                if (j == proGroupArr.length - 1) {
                    proGroupArr[proGroupArr.length] = new Array(arr[0], arr[1]);
                }
            }
        }
    }

    for (var i in argArr) {
        var temp2 = argArr[i].split(",");
        proGroupRoleArr[proGroupRoleArr.length] = new Array(temp2[0], temp2[1], temp2[2]);
    }
}

function init() {
    selectSpecialOptionByItem(mainFrm.orgId, "<%=user.getOrganizationId()%>");
    if (mainFrm.str.value == "") {
        return;
    }

    unFromatStr();
    unDeptFromatStr();
    for (var i = 0; i < deptArr.length; i++) {
        if (dept_Obj.length == 0) {
            dept_Obj.add(new Option(deptArr[i],null));
        } else {
            dept_Obj.add(new Option(deptArr[i],null));
        }
    }
    for (var i = 0; i < proGroupArr.length; i++) {
        if (project_Obj.length == 0) {
            project_Obj.add(new Option(proGroupArr[i][0], proGroupArr[i][0]));
        } else {
            for (var j = 0; j < project_Obj.length; j++) {
                if (proGroupArr[i][0] == project_Obj[j].value) {
                    break;
                }
                if (j == project_Obj.length - 1) {
                    project_Obj.add(new Option(proGroupArr[i][0], proGroupArr[i][0]));
                }
            }
        }
    }
}
</script>