<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import = "com.sino.sinoflow.constant.WebAttrConstant" %>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<html>
  <head>
    <title>api</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/cms_css/cms_css.css">
    <script type="text/javascript" src="/WebLibary/js/clientRowSet.js"></script>
    <script type="text/javascript" src="/WebLibary/js/OperationProjectGroupRole.js"></script>
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script> 
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarVar.js"></script>
    <script type="text/javascript"> 
    	<!--  
    	printTitleBar("����ά��");
    	var myArrAction0 = new Array(true, "ȡ��", "action_cancel.gif","ȡ��","cancel");
		var myArrAction1 = new Array(true, "����", "action_save.gif","����","save");  
        ArrActions = new Array(myArrAction0, myArrAction1 );
        printToolBar(); 
        //-->
	 </script>
  
    <script type="text/javascript">
    
    	function cancel(){
    		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfAdminAuthorityServlet");
    	}
    	function save(){
            if(!validate()){
    			return;
    		}
            if(mainFrm.sel.length < 1){
    			alert('����Ƿ���ԭ���ǣ����������ơ� ����ѡ���񣬲���Ϊ��');
    		}
    		mainFrm.uId.value = formatStr();
    		mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
	        mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfAdminAuthorityServlet";
            mainFrm.submit();
    	} 
	 </script>
  </head>
  <body>
  	<div>
  		<form action="" name="mainFrm" method="POST">
	  		<table class="detailTb">
	  			<tr>
					<td width="20%" height="25pt">
					<td align="right">�������ƣ�</td>
					<td>
                        <span style="position:absolute;overflow:hidden;width:218px">
 		            	<select style="width:220px" class="input_1" name="projectNameS" id="projectNameS" onchange="proGroup()">
		            		<option value="">--��ѡ��--</option>
		            		<%= request.getAttribute(WebAttrConstant.PROJECT_OPTION_STR) %>
		            	</select>
                        </span>
                        <span style="position:absolute;width:200px;">
                        <input type="text" class="input_1" name="projectName" id="projectName" value="" style="width:200px;border:1pt;margin:1">
                        </span> -->
          			</td>
	  			</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td width="" align="right">������ƣ�</td>
	  				<td>
            			<select style="width:220px" class="input_1" name="groupName">
            				<option value="*">*</option>
            			</select>
            		</td>
            	</tr>
	  			<tr>
	  				<td width="20%"></td>
	  				<td align="right" valign="top">����Ա��</td>
	  				<td width="60%">
	  					<!--<select name="sel" size="12" style="width:220px" multiple="multiple"></select>-->
                        <textarea name="sel" class="input_1" style="width:220px" rows="5" class="blueBorder"></textarea>
                      </td>
	  			</tr>
	  			<!--<tr>
	  				<td width="20%"></td>                             <!
	  				<td></td>
	  				<td>
	  					&nbsp;&nbsp;&nbsp;&nbsp;
	  					<input type="button" value="���" onclick="addAdmin()"/>
	  					&nbsp;&nbsp;&nbsp;&nbsp;
	  					<input type="button" value="ɾ��" onclick="delAdmin()"/>
	  				</td>
	  			</tr>-->
	  		</table>
	  		<input type="hidden" name="act"/>
	  		<input type="hidden" name="uId"/>
	  	</form>
  	</div>
  
  <%= WebConstant.WAIT_TIP_MSG%>
  </body>
  </html>
  
<script type="text/javascript">
<!--
	var winstyle = "dialogWidth:20.1;dialogHeight:14.8;center:yes;status:no;help:no";
	function addAdmin(){
		var retuenValue = window.showModalDialog("/system/showOptions.jsp", getUser(), winstyle);
		var sel = mainFrm.sel;
		if (retuenValue) {
			var arr = explode(retuenValue, ";");
       	 	if(sel.length == 0){
	       	 	for(var i=0;i<arr.length;i++){
	       	 		var tv = arr[i].split(","); 
	       	 		sel.add(new Option(tv[1],tv[0]));
	       	 	}
       	 	}else{
       	 		for(var i=0;i<arr.length;i++){
       	 			var tv = arr[i].split(","); 
       	 			for(var j=0;j<sel.length;j++){
       	 				if(tv[0] == sel[j].value){
       	 					break;
       	 				}else if(j == sel.length-1){
	       	 				sel.add(new Option(tv[1],tv[0]));		
       	 				}
       	 			}
       	 		}
       	 	}
    	}
	}	
	function delAdmin(){
		delete_option(mainFrm.sel);
	}
	
	function formatStr(){
/*
        var str = "";
		for(var i=0;i<mainFrm.sel.length;i++){
			str += mainFrm.sel.options[i].value+";";
		}
		return str.substring(0,str.length-1);
*/
        return mainFrm.sel.value;
    }
	
	function proGroup(){
        document.getElementById('projectName').value=document.getElementById('projectNameS').options[document.getElementById('projectNameS').selectedIndex].value;
        var pn = mainFrm.projectName;
		var gn = mainFrm.groupName;
		gn.options.length = 1;
		if(pn.value == ""){
			return;
		}
        var arr = getGroup(pn.value);
        for(var i=0;i<arr.length;i++){
            gn.add(arr[i]);
        }
	}
	
	function validate(){
		var fieldNames = "projectName;groupName";
    	var fieldLabels = "��������;�������";
    	return formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
	}
//-->
</script>

  
