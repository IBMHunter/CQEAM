<%@ include file="/inv/headerInclude.jsp"%>
<%@ include file="/inv/headerInclude.htm"%>
<%@ page import="com.sino.ams.inv.storeman.base.constant.web.WebInvActionConstant" %>
<%@ page contentType="text/html;charset=GB2312" language="java" %>
<%--
  created by YSB
  Date: 2008-12-12
  Time: 10:58:39
--%>
<html>
  <head>
    <title>�������²ֹ�Ա��Ϣ</title>
  </head>
  
  <body text="000000" bgcolor="ffffff" leftmargin="0" topmargin="0" class="STYLE1" onload="window.focus();">
    <%
    	EamInvStoremanDTO dto = (EamInvStoremanDTO)request.getAttribute(QueryConstant.QUERY_DTO);//���Servlet���dto.setXXX()����д�� 
  		SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
		String organizationId = userAccount.getOrganizationId();
		String[] gets = (String[])request.getAttribute("gets");
		for(int i=0;i<gets.length;i++){
			System.out.println(gets[i]);
		}
		request.setAttribute("gets", gets);
	%>
	<form action="" name="mainFrm" method="post">
		<jsp:include page="<%=URLDefineList.MESSAGE_PROCESS %>" flush="true"></jsp:include>
		<table width="100%" border="0" style="position:absolute;top:20px">
			<tr>
				<td width="25%" align="right" height="22">�ֹ�Ա��</td>
            	<td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="userName" size="40" class="noemptyInput" style="width:92%"
                       value="" readonly="readonly">&nbsp;&nbsp;<a href="#" title="���ѡ��ֹ�Ա" class="linka" onclick="do_SelectStoreman();">[��]</a>
            	</td>
            	<td width=" " align="left" height="22"></td>
			</tr>
			
			<tr>
				<td width="100%" align="center" height="22" colspan="5">
                <img src="/images/eam_images/save.jpg" alt="����" onclick="do_submit();">&nbsp;
                <img src="/images/eam_images/back.jpg" alt="ȡ��" onclick="do_close();"></td>
			</tr>
		</table>
		<input type="hidden" name="act" value="<%=dto.getAct()%>">
		<%--parser.getParameter("act")--%>
		<input type="hidden" name="userId" value="">
	</form>
  </body>
</html>
<script type="text/javascript">

    
function do_submit() {
    var fieldNames = "userName";
    var fieldLabels = "�ֹ�Ա";
    
    if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)) {
    	if (mainFrm.userName.value != "") {
            mainFrm.act.value = "<%=WebInvActionConstant.UPDATE_ACTION_USERS%>";
        }
    }
    
	var tempStr = "/servlet/com.sino.ams.inv.storeman.servlet.EamInvStoremanServlet?userId=" + document.forms[0].userId.value;
	var pStr=""; 
 	<%
 		String str = "";
 		for(int i=0;i<gets.length;i++){
    		str = str+("&gets="+gets[i]);		
    	} 	
    %>
    pStr = "<%=str%>";
    
    mainFrm.action = tempStr + pStr;
    mainFrm.submit();
    opener.location.href = "/servlet/com.sino.ams.inv.storeman.servlet.EamInvStoremanServlet?act=<%=WebActionConstant.QUERY_ACTION%>";   
}

function do_close() {
    window.close();
    opener.location.href = "/servlet/com.sino.ams.inv.storeman.servlet.EamInvStoremanServlet?act=<%=WebActionConstant.QUERY_ACTION%>";
}

function do_SelectStoreman() {
	/*
	var lookUpName = "<%=LookUpConstant.LOOK_UP_USER_ID%>";
	var dialogWidth = 50;
	var dialogHeight = 30;	
	var userPara = "organizationId=<%=organizationId%>";
	var objs = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
	if(objs.lenght > 0){
		//dto2Frm(objs[0], "mainFrm");
		mainFrm.userId.value = objs[0]["userId"];
		mainFrm.userName.value = objs[0]["userName"];
		alert("asdfasdsf::::" + mainFrm.userId.value + "----------" + mainFrm.userName.value);
	}
	*/

	var  url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_USER_ID%>";
    var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    var vendorNames = window.showModalDialog(url, null, popscript);
    if(vendorNames){
       var vendorName = null;
       document.forms[0].userId.value = vendorNames[0].userId;
       if(document.forms[0].userId.value == null) {
       		alert("�ֹ�Ա������Ϊ�գ�");
       		return false;
       } 
       document.forms[0].userName.value = vendorNames[0].userName;
    }
    //window.returnValue = "";
}
</script>