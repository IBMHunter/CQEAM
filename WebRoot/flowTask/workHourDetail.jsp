<%@ page contentType="text/html; charset=GBK" language="java"
	errorPage=""%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant"%>
<%@ page import="com.sino.sinoflow.user.dto.SfUserBaseDTO"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="com.sino.base.util.StrUtil"%>
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%@page import= "com.sino.sinoflow.constant.URLDefineList"%>
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
		<script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
		<script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
		<script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
		<script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
		<script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
		<script language="javascript" src="/WebLibary/js/clientRowSet.js"></script>
		<script language="javascript" src="/WebLibary/js/OperationProjectGroupRole.js"></script>
		<script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
		<script type="text/javascript">
		<!--  
		 function printTool(){
		       var ArrAction = new Array("ȡ��", "action_cancel.gif","cancel","c_t");
			   var ArrAction1 = new Array("����", "action_save.gif","save","s_t");
		        var toolBar = new SinoPrintToolBar();            
		        toolBar.SinoActions = new Array(ArrAction,ArrAction1);
		        toolBar.imagePath = "../images/buttonbar/";
		        toolBar.titleStr = "����ʱ�䶨��";
		        toolBar.print();
		    }
		    printTool();
		-->
		</script>
	</head>

	<body>
		<jsp:include
			page="/message/MessageProcess"
			flush="true" />

		<div>
			<form action="" method="post" name="mainFrm">
			<table width="70%">
				<tr>
					<td width="10%"></td>	
					<td align="right">����ʱ������</td>
					<td>
						<input name="workHourName" type="text" style="width: 50%;"/>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="right">
						��һ�ι���ʱ�䣺
					</td>
					<td>
						��
						<select name="bh1">
							<%for(int i=0;i<=23;i++){if(i<10){ %>
	  								<option value="<%=i%>"><%=i%></option>
	  						<%}else{ %>
	  								<option value="<%=i%>"><%=i%></option>
	  						<%}} %>
						</select>
						ʱ

						<select name="bm1">
							<% for(int i=0;i<=59;i++){ if(i<10){%>
	  								<option value="<%=i%>"><%=i%></option>
	  							<%}else{ %>
	  								<option value="<%=i%>"><%=i%></option>
	  							<%} }%>
						</select>
						��&nbsp;��


						<select name="eh1">
							<%for(int i=0;i<=23;i++){if(i<10){ %>
	  								<option value="<%=i%>"><%=i%></option>
	  						<%}else{ %>
	  								<option value="<%=i%>"><%=i%></option>
	  						<%}} %>
						</select>
						ʱ

						<select name="em1">
							<% for(int i=0;i<=59;i++){ if(i<10){%>
	  								<option value="<%=i%>"><%=i%></option>
	  							<%}else{ %>
	  								<option value="<%=i%>"><%=i%></option>
	  							<%} }%>
						</select>
						��
					</td>
				</tr>
				<tr>
					<td width="15%"></td>
					<td align="right">
						�ڶ��ι���ʱ�䣺
					</td>
					<td>
						��
						<select name="bh2">
							<%for(int i=0;i<=23;i++){if(i<10){ %>
	  								<option value="<%=i%>"><%=i%></option>
	  						<%}else{ %>
	  								<option value="<%=i%>"><%=i%></option>
	  						<%}} %>
						</select>
						ʱ

						<select name="bm2">
							<% for(int i=0;i<=59;i++){ if(i<10){%>
	  								<option value="<%=i%>"><%=i%></option>
	  							<%}else{ %>
	  								<option value="<%=i%>"><%=i%></option>
	  							<%} }%>
						</select>
						��&nbsp;��


						<select name="eh2">
							<%for(int i=0;i<=23;i++){if(i<10){ %>
	  								<option value="<%=i%>"><%=i%></option>
	  						<%}else{ %>
	  								<option value="<%=i%>"><%=i%></option>
	  						<%}} %>
						</select>
						ʱ

						<select name="em2">
						
							<% for(int i=0;i<=59;i++){ if(i<10){%>
	  								<option value="<%=i%>"><%=i%></option>
	  							<%}else{ %>
	  								<option value="<%=i%>"><%=i%></option>
	  							<%} }%>
						</select>
						��
					</td>
				</tr>
				<tr>
					<td width="15%"></td>
					<td align="right">
						�����գ�
					</td>
					<td>	
						<input type="checkbox" name="cb" value="1"/>
						����һ
						<input type="checkbox" name="cb" value="2"/>
						���ڶ�
						<input type="checkbox" name="cb" value="4"/>
						������
						<input type="checkbox" name="cb" value="8"/>
						������
						<input type="checkbox" name="cb" value="16"/>
						������
						<input type="checkbox" name="cb" value="32"/>
						������
						<input type="checkbox" name="cb" value="64"/>
						������
					</td>
				</tr>
			</table>
				<input type="hidden" name="workBegin1" />
	  			<input type="hidden" name="workEnd1" />
	  			<input type="hidden" name="workBegin2" />
	  			<input type="hidden" name="workEnd2" />
	  		  	<input type="hidden" name="workingDate" />
			</form>
		</div>

		<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
		<%= WebConstant.WAIT_TIP_MSG%>
	</body>
</html>

<script type="text/javascript">
<!--  
 	function cancel(){//ȡ���¼�
 		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfWorkScheduleServlet?act=<%= WebActionConstant.NEW_ACTION %>");
 	}
 	
 	function save(){//�����¼�
 	
 		var fieldNames = "workHourName";
	    var fieldLabels = "����ʱ����";
	    var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
		if (!isValid) {
			return;
		}
		
		var b1 = parseFloat(mainFrm.bh1.value+"."+mainFrm.bm1.value);
   		var e1 = parseFloat(mainFrm.eh1.value+"."+mainFrm.em1.value);
   		
   		var b2 = parseFloat(mainFrm.bh2.value+"."+mainFrm.bm2.value);
   		var e2 = parseFloat(mainFrm.eh2.value+"."+mainFrm.em2.value);
   		if(b1>=e1 || b2>=e2 || e1>=b2){
   			alert('ѡ��Ĺ���ʱ���г�ͻ');
   			return;
   		}
   		
   		mainFrm.workBegin1.value = mainFrm.bh1.value+":"+mainFrm.bm1.value;
   		mainFrm.workEnd1.value = mainFrm.eh1.value+":"+mainFrm.em1.value;
   		mainFrm.workBegin2.value = mainFrm.bh2.value+":"+mainFrm.bm2.value;
   		mainFrm.workEnd2.value = mainFrm.eh2.value+":"+mainFrm.em2.value;
   		
   		mainFrm.workingDate.value = plusValue("cb");
   		
        mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfWorkHourServlet?act=<%= WebActionConstant.CREATE_ACTION %>";
        mainFrm.submit();
 	}
 	
-->
</script>