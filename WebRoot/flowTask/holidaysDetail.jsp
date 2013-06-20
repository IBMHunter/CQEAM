<%@ page contentType = "text/html; charset=GBK" language = "java" errorPage = "" %>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.Map" %>
<%@ page import = "com.sino.sinoflow.constant.WebAttrConstant" %>
<%@ page import = "com.sino.sinoflow.user.dto.SfUserBaseDTO" %>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import = "com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@page import= "com.sino.sinoflow.constant.URLDefineList"%>

<html>
	<head>
		<title>�ڼ���</title>
		<link rel = "stylesheet" type = "text/css" href = "/WebLibary/css/main.css">
		<script type = "text/javascript" src = "/WebLibary/js/Constant.js"></script>
		<script type = "text/javascript" src = "/WebLibary/js/CommonUtil.js"></script>
		<script type = "text/javascript" src = "/WebLibary/js/FormValidate.js"></script>
		<script type = "text/javascript" src = "/WebLibary/js/FormProcess.js"></script>
		<script type = "text/javascript" src = "/WebLibary/js/SelectProcess.js"></script>
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

	<body onkeypress="keyDown()">
	<jsp:include page = "/message/MessageProcess" flush = "true" />

		<div>
			<form name="mainFrm" method="post">
				<table width="60%">
					<tr>
						<td align="right">�ڼ������ƣ�</td>
						<td>
							<input type="text" name="name" style="width: 40%;"/>
						</td>
					</tr>
					<tr>
						<td align="right">�ڼ�����ݣ�</td>
						<td>
							<input type="text" name="year" style="width: 40%;"/>
						</td>
					</tr>
					<tr>
	  					<td valign="top" align="right">�ڼ��գ�</td>
	  					<td>
	  						<select name="tempHolidays" multiple="multiple" size="12" style="width:40%;">
	  							
	  						</select>
	  					</td>
		  			</tr>
		  			<tr>
		  				<td></td>
	  					<td>
	  						<input type="text" name="holidaysText" size="30" style="width: 40%;"/>
	  					</td>
	  				</tr>
	  				<tr>
	  					<td></td>
	  					<td>
	  						˵�������ʽΪXX.XX����10.01
	  					</td>
	  				</tr>
		  			<tr>
		  				<td></td>
		  				<td>&nbsp;&nbsp;&nbsp;
	  						<input type="button" value="���" onclick="addValue()"/>
	  						&nbsp;
	  						<input type="button" value="ɾ��" onclick="delValue()"/>
	  					</td>
	  				</tr>
				</table>
				<input type="hidden" name="holidays" value=""/>
			</form>
		</div>
		
		

		<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
		<%=WebConstant.WAIT_TIP_MSG%>
	</body>
</html>

<script type="text/javascript">
<!--
	function addValue(){//���ı�ֵ��ӵ������б�
		var temp = mainFrm.holidaysText.value;
		if(temp == ""){
			return;
		}
		if(temp.indexOf(".") == -1){
			alert("����Ƿ���ԭ���ǣ��������ʽ����");
			return;
		}
		if(isNaN(temp)){
			alert("����Ƿ���ԭ���ǣ������벻����Ч���ڡ�");
			return;
		}
		
		mainFrm.tempHolidays.add(new Option(temp,temp));
		mainFrm.holidaysText.value = "";
		mainFrm.holidaysText.focus();
	}
	function delValue(){//ɾ��ֵ
  		dropOption("tempHolidays", true);
  	}
  		
	function keyDown(){
		if (event.keyCode == 13) {
         	addValue();
     	}
	}
  		
 	function cancel(){//ȡ���¼�
 		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfWorkScheduleServlet?act=<%= WebActionConstant.NEW_ACTION %>");
 	}
 	
 	function save(){//�����¼�
	 	var fieldNames = "name;year";
	    var fieldLabels = "�ڼ�������;�ڼ������";
	    var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
		if (!isValid) {
			return;
		}
 		if(isNaN(mainFrm.year.value) || mainFrm.year.value.length != 4){
			alert("����Ƿ���ԭ���ǣ������벻����Ч��ݡ�");
			return;
		}
		
	    	assignValue();
 			mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfHolidaysServlet?act=<%=WebActionConstant.CREATE_ACTION%>";
	   	 	mainFrm.submit();
 	}
 	
	function assignValue(){//ƴ��ʱ���ַ���ֵ
		var str = "";
		for(var i=0;i<mainFrm.tempHolidays.length;i++){
			str += mainFrm.tempHolidays.options[i].value+";";	
		}
		str = str.substring(0,str.length-1);
		mainFrm.holidays.value = str;
	}
-->
</script>