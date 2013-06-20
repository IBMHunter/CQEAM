<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import = "com.sino.sinoflow.constant.WebAttrConstant" %>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sino.sinoflow.dto.SfAutoValueDTO"%>

   <%
	SfAutoValueDTO sav = (SfAutoValueDTO)request.getAttribute(WebAttrConstant.AUTOVALUE_ATTR);
	if(sav == null){
		sav = new SfAutoValueDTO();
	}
	List list = (List)request.getAttribute("nl");
	if(list == null)list = new ArrayList();
%>
<html>
  <head>
    <title>validation</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <jsp:include page="/message/MessageProcess"/>
    <script type="text/javascript" src="/WebLibary/js/clientRowSet.js"></script>
    <script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/OperationProjectGroupRole.js"></script>
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript">
    
    	function cancel(){
    		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfAutoValueServlet");
    	}
    	
    	function save(){
    		if(!validate()){
    			return;
    		}
    		if(mainFrm.fieldName.value == ""){
    			alert("����Ƿ���ԭ���ǣ��������ơ�����Ϊ��");
    			return;
    		}
    		mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
    		changeValue(mainFrm.projectName);
    		changeValue(mainFrm.procedureName);
    		mainFrm.assignOn.value = plusValue('cb');
    		
    		if(mainFrm.autoValueId.value != "0"){
				if(confirm("ȷ�ϸ�����Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")){
					mainFrm.act.value ="<%= WebActionConstant.UPDATE_ACTION %>";
				}else{
					return;
				}	
			}
            mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfAutoValueServlet";
	        mainFrm.submit();
    	}
    	
    
    	 function printTool(){
	       var ArrAction = new Array("ȡ��", "action_cancel.gif","cancel","ȡ��");
		   var ArrAction1 = new Array("����", "action_save.gif","save","����");
	        var toolBar = new SinoPrintToolBar();            
	        toolBar.SinoActions = new Array(ArrAction,ArrAction1);
	        toolBar.imagePath = "../images/buttonbar/";
	        toolBar.titleStr = "�Զ���ֵά��";
	       	toolBar.print();
	    }
	    printTool();
	 </script>
  </head>
  
 <jsp:include page="/message/MessageProcess"/>
  <body>
	<form method="POST" name="mainFrm">
		<div>
			<TABLE>
				<tr>
	  				<td width="20%"></td>
	  				<td align="right">�������ƣ�</td>
					<td>
						<select style="width:100%" name="projectName" onchange="getProcedure2()">
							<option>--��ѡ��--</option>
							<%=request.getAttribute(WebAttrConstant.PROJECT_OPTION_STR)%>
						</select>
					</td>
				</tr>	
		
				<tr>
					<td width="20%"></td>
					<td align="right">�������ƣ�</td>
					<td>
						<select style="width:100%" onchange="getTask()" name="procedureName">
							<option>--��ѡ��--</option>
							<%=request.getAttribute(WebAttrConstant.PROJECT_PROCEDURE_OPTION) %>
						</select>
					</td>
				</tr>
				
				<tr>
					<td width="20%"></td>
					<td align="right">�������ƣ�</td>
					<td>
						<select style="width:100%" name="taskTid">
							<option>--��ѡ��--</option>
							<%= request.getAttribute(WebAttrConstant.PROCEDURE_TASK_OPTION_STR)%>
						</select>
					</td>
				</tr>
				<tr>
	  				<td width="20%"></td>
	  				<td align="right">�����ƣ�</td>
	  				<td>
	  					<input type="text" name="fieldName" size="60" value="<%= sav.getFieldName() %>"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right">��������</td>
	  				<td>
	  					<input type="text" name="fieldDesc" size="60" value="<%= sav.getFieldDesc() %>"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right">��ʱ��ֵ��</td>
	  				<td>
	  					<input type="checkbox" name="cb" value="1" <%if(list.contains(new Integer(1))){%>checked<%}%>/>�˻�
	  					<input type="checkbox" name="cb" value="2" <%if(list.contains(new Integer(2))){%>checked<%}%>/>����
	  					<input type="checkbox" name="cb" value="4" <%if(list.contains(new Integer(4))){%>checked<%}%>/>���
	  					<input type="checkbox" name="cb" value="8" <%if(list.contains(new Integer(8))){%>checked<%}%>/>�ݴ�
	  					<input type="checkbox" name="cb" value="16" <%if(list.contains(new Integer(16))){%>checked<%}%>/>ǩ��
	  				</td>
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right">��ֵ��ʽ��</td>
	  				<td>
	  					<input type="radio" name="assignType" value="0" <%if(sav.getAssignType() == 0){%>checked<%}%>/>׷��
	  					<input type="radio" name="assignType" value="1" <%if(sav.getAssignType() == 1){%>checked<%}%>/>����
	  				</td>
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right">��ֵ���ݣ�</td>
	  				<td>
	  					<input type="radio" name="assignValue" value="0" <%if(sav.getAssignValue() == 0){%>checked<%}%>/>��ǰ�û�
	  					<input type="radio" name="assignValue" value="1" <%if(sav.getAssignValue() == 1){%>checked<%}%>/>��ǰʱ��
	  					<input type="radio" name="assignValue" value="2" <%if(sav.getAssignValue() == 2){%>checked<%}%>/>��ǰ����
	  				</td>
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right" valign="top">��ע��</td>
	  				<td>
	  					<textarea name="meno" rows="8" cols="60"><%=sav.getMeno() %></textarea>
	  				</td>
	  			</tr>
			</TABLE>
		</div> 
		
		<input type="hidden" name="act" />
	  	<input type="hidden" name="assignOn" />
	  	<input type="hidden" name="autoValueId" value="<%=sav.getAutoValueId() %>"/>
	</form>
	
	<%= WebConstant.WAIT_TIP_MSG%>
  </body>
 
</html>
  
  <script type="text/javascript">
 
 	function validate(){
	 	var fieldNames = "projectName;procedureName;taskTid;fieldName";
		var fieldLabels = "������;������;������;������";
		return formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
	}

  </script>