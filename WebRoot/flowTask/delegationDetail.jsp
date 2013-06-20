<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import = "com.sino.sinoflow.constant.WebAttrConstant" %>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import = "com.sino.framework.security.bean.SessionUtil" %>
<%@ page import = "com.sino.sinoflow.user.dto.SfUserBaseDTO" %>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="com.sino.sinoflow.dto.SfDelegationDTO"%>
<%
	SfUserBaseDTO user = (SfUserBaseDTO)SessionUtil.getUserAccount(request);
	SfDelegationDTO sdd = (SfDelegationDTO)request.getAttribute(WebAttrConstant.DELEGATION_ATT);
	if(sdd==null)sdd = new SfDelegationDTO();
%>
<html>
  <head>
    <title>delegation</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/cms_css/cms_css.css">
    <script type="text/javascript" src="/WebLibary/js/clientRowSet.js"></script>
    <script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/OperationProjectGroupRole.js"></script>
    <script type="text/javascript" src="/WebLibary/js/calendar.js"></script>
    
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script> 
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarVar.js"></script>
    <script type="text/javascript"> 
    	<!-- 
        printTitleBar("ί�ɶ���");
        var myArrAction0 = new Array(true, "ȡ��", "action_cancel.gif", "ȡ��", "cancel");
        var myArrAction1 = new Array(true, "����", "action_save.gif", "����", "save");
        ArrActions = new Array(myArrAction0, myArrAction1);
        printToolBar(); 
        //-->
	 </script>
	 
    <script type="text/javascript">
    
    	function cancel(){
    		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfDelegationServlet");
    	}
    	
    	function save(){
    		var temp = mainFrm.statusCtl;
    		if(!temp[0].checked && !temp[1].checked){
    			alert("����Ƿ���ԭ���ǣ���ί�ɠ�̬��δѡ��");
    			return;
    		}
    		if(mainFrm.delegateTo.value==""){
    			alert("����Ƿ���ԭ���ǣ���ί�ɸ���δѡ��");
    			return;
    		}
    		mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
    		
    		if(mainFrm.delegationId.value != "0"){
				if(confirm("ȷ�ϸ�����Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")){
					mainFrm.act.value ="<%= WebActionConstant.UPDATE_ACTION %>";
				}else{
					return;
				}	
			} 
	        mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfDelegationServlet";
	        mainFrm.submit();
    	} 
	 </script>
  </head>
   <body>
   		<div>
   			<form action="" method="POST" name="mainFrm">
	 			<table class="detailTb"> 
	 				<tr>
	 					<td width="20%"></td>
	 					<td align="right">ί���ˣ�</td>
	 					<td><%= sdd.getDelegationId() <= 0 ? user.getUsername() : sdd.getSName()%></td>
	 				</tr>
	 				<tr>
	 					<td width="20%"></td>
	 					<td align="right">ί�ɠ�̬��</td>
	 					<td>
	 						<input type="radio" name="statusCtl" value="0" <%if(sdd.getStatusCtl() == 0){%> checked="checked" <%}%>/>����&nbsp;&nbsp;&nbsp;
	 						<input type="radio" name="statusCtl" value="1"<%if(sdd.getStatusCtl() == 1){%> checked="checked" <%}%>/>����
	 					</td>
	 				</tr>
	 				<tr>
	 					<td width="20%"></td>
	 					<td align="right">ί�ɸ���</td>
	 					<td>
	 						<select name="delegateTo" style="width:150px">
	 							<option value="">--��ѡ��--</option>
	 							<%=request.getAttribute(WebAttrConstant.USER_OPTION_STR)%>
	 						</select>
	 					</td>
	 				</tr>
	 				<tr>
	 					<td width="20%"></td>
	 					<td align="right">��ʼʱ�䣺</td>
	 					<td>
	 						<input type="text" name="startDate" size="25" value="<%=sdd.getStartDate()%>"
	 						 style="width:100%" title="���ѡ��ʼ����" readonly class="readonlyInput" onClick="gfPop.fStartPop(startDate, endDate)">
	 						
	 					</td>
	 				</tr>
	 				<tr>
	 					<td width="20%"></td>
	 					<td align="right">����ʱ�䣺</td>
	 					<td>
	 						<input type="text" name="endDate" size="25" value="<%=sdd.getEndDate() %>"
	 						style="width:100%" title="���ѡ���������" readonly class="readonlyInput" onClick="gfPop.fEndPop(startDate, endDate)">
	 					</td>
	 				</tr>
	 			</table>
	 			<input type="hidden" name="userId" value="<%= user.getUserId() %>"/>
	 			<input type="hidden" name="delegationId" value="<%=sdd.getDelegationId() %>"/>
	 			<input type="hidden" name="act"/>
  			</form>
  		</div>
  		<%=WebConstant.WAIT_TIP_MSG%>
  		
  		<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
  	</body>
  </html>

