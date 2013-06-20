<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import = "com.sino.sinoflow.constant.WebAttrConstant" %>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.sinoflow.dto.SfValidationDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<% SfValidationDTO svd = (SfValidationDTO)request.getAttribute(WebAttrConstant.VALIDATION_ATTR);
	if(svd == null){
		svd = new SfValidationDTO();
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
    		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfValidationServlet");
    	}
    	
    	function save(){
	   		if(!validate()){
	   			return;
	   		}

            mainFrm.validationType.value = plusValue('cb');
    		if(mainFrm.validationType.value == 0){
    			alert("����Ƿ���ԭ���ǣ���ƥ�䷽ʽ��δѡ��");
    			return;
    		}
    		
    		if(isNaN(mainFrm.checkSize.value)){
    			alert("����Ƿ���ԭ���ǣ�������ֵ����Ϊ����");
    			return;
    		}
    		
    		mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
    		
	    	if(mainFrm.validateId.value != "0"){
				if(confirm("ȷ�ϸ�����Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")){
					mainFrm.act.value ="<%= WebActionConstant.UPDATE_ACTION %>";
				}else{
					return;
				}	
			} 
    		changeValue(mainFrm.projectName);
    		changeValue(mainFrm.procedureName);
            mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfValidationServlet";
	        mainFrm.submit();
    	}
    	
    
    	 function printTool(){
	       var ArrAction = new Array("ȡ��", "action_cancel.gif","cancel","ȡ��");
		   var ArrAction1 = new Array("����", "action_save.gif","save","����");
	        var toolBar = new SinoPrintToolBar();            
	        toolBar.SinoActions = new Array(ArrAction,ArrAction1);
	        toolBar.imagePath = "../images/buttonbar/";
	        toolBar.titleStr = "�Ϸ��Լ��ά��";
	        toolBar.print();
	    }
	    printTool();
	 </script>
  </head>
  
	  <body>
	  <div>
	  	<form name="mainFrm" method="POST">
	  		<table align="left">
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
							<%= request.getAttribute(WebAttrConstant.PROJECT_PROCEDURE_OPTION) %>
						</select>
					</td>
				</tr>
				
				<tr>
					<td width="20%"></td>
					<td align="right">�������ƣ�</td>
					<td>
						<select style="width:100%" name="taskTid">
							<option>--��ѡ��--</option>
							<%= request.getAttribute(WebAttrConstant.PROCEDURE_TASK_OPTION_STR) %>
						</select>
					</td>
				</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right">�����ƣ�</td>
	  				<td>
	  					<input type="text" name="fieldName" size="60" value="<%=svd.getFieldName() %>"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right">��������</td>
	  				<td>
	  					<input type="text" name="fieldDesc" size="60" value="<%=svd.getFieldDesc() %>"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right">ƥ�䷽ʽ��</td>
	  				<td>
	  					<input type="checkbox" name="cb" value="1" <%if(list.contains(new Integer("1"))){%>checked<%}%>/>��Ϊ��&nbsp;&nbsp;
	  					<input type="checkbox" name="cb" value="2" onclick="checkboxClick('tr_model',this,'matchPattern')" <%if(list.contains(new Integer("2"))){%>checked<%}%>/>ƥ��ĳ��ģʽ&nbsp;&nbsp; 
	  					<input type="checkbox" name="cb" value="4" onclick="checkboxClick('tr_size',this,'checkSize')" <%if(list.contains(new Integer("4"))){%>checked<%}%>/>����С
	  				</td>
	  			</tr>
	  			
	  			<tr id="tr_model" style="<%if(!list.contains(new Integer("2"))){%>display:none<%}%>" >
	  				<td width="20%"></td>
	  				<td align="right">ƥ��ģʽ��</td>
	  				<td>
	  					<input type="text" name="matchPattern" id="matchPattern" size=60 value="<%=svd.getMatchPattern() %>"/><br/>
	  					������ͨ��� "*","?",�硱2008-*-???��
	  				</td>	
	  			</tr>
	  			<tr id="tr_size" style="<%if(!list.contains(new Integer("4"))){%>display:none<%}%>">
	  				<td width="20%"></td>
	  				<td align="right">��С��</td>
	  				<td>
	  					<input type="text" id="checkSize" name="checkSize" size=60 value="<%=svd.getCheckSize() %>"/><br/>
	  				</td>	
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td valign="top" align="right">��ע��</td>
	  				<td>
	  					<textarea name="memo" tabindex="0" cols="60" rows="10"><%=svd.getMemo() %></textarea>
	  				</td>
	  			</tr>
	  		  </table>
	  		  <input type="hidden" name="act" />
	  		  <input type="hidden" name="validationType" />
	  		  <input type="hidden" name="validateId"  value="<%=svd.getValidateId() %>"/>
	  		</form>
	  	</div>
	  		<%= WebConstant.WAIT_TIP_MSG%>
	  </body>
  </html>
  
  <script type="text/javascript">
  	function validate(){
	 	var fieldNames = "projectName;procedureName;taskTid;fieldName";
		var fieldLabels = "������;������;������;������";
		return formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
	}
  	
  	function checkboxClick(id,obj,txt){//ѡ�и�ѡ��
  		if(obj.checked){
  			document.getElementById(id).style.display="";
  		}else{
  			document.getElementById(id).style.display="none";
  			document.getElementById(txt).value="";
  		}
  	}
  	
  </script>