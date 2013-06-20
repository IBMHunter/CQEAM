<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
  <head>
  <title>��ѡ�������ϵص�</title>
      <base target="_self">
      <script type="text/javascript" src="/WebLibary/js/AjaxProcess.js"></script>
      <script type="text/javascript">
      	printTitleBar("��ѡ�������ϵص�");
        var ArrAction0 = new Array(true, "ȷ��", "action_save.gif", "ȷ��", "do_sub");
        var ArrAction1 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "do_Close");
      	var ArrActions = new Array(ArrAction0, ArrAction1);
        printToolBar();
      </script>
  </head>
  <%
    	EtsObjectDTO dto = (EtsObjectDTO) request.getAttribute(WebAttrConstant.ETS_OBJECT_DTO);	
  %>
  <body>
  <form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.object.servlet.CommonObjectServlet">
  	  <input type="hidden" name="act" id="act" value="">
  	  <input type="hidden" name="organizationId" value="<%=dto.getOrganizationId() %>">
  	  <input type="hidden" name="deptCode" value="<%=dto.getDeptCode() %>">
  	  <input type="hidden" name="companyCode" value="<%=dto.getCompanyCode() %>">
  	  <input type="hidden" name="objectNo" value="">
	  <p></p>
      <table width="90%" align="center" border="1" bordercolor="#666666" >
        <tr>
            <td width="30%" align="right">��������(��һ��)��</td>
            <td width="70%" colspan="3">
            	<input name="countyCode" type="hidden" id="countyCode" value="<%=dto.getCountyCode() %>"/>
            	<input name="countyName" type="text" id="countyName" value="<%=dto.getCountyName() %>" class='input_style1' readonly style="width:90%">
				<a href= "#" onClick="choseCounty()" title = "���ѡ����������(��һ��)" class="linka" >[��]</a>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">����ص�(�ڶ���)��</td>
            <td width="70%" colspan="3">
            	<input name="loc2Code" type="hidden" id="loc2Code" value="<%=dto.getLoc2Code() %>">
            	<input name="location" type="text" id="location" value="<%=dto.getLocation() %>" class='input_style1' readonly style="width:90%">
				<a href= "#" onClick="choseLocDesc()" title = "���ѡ������ص�(�ڶ���)" class="linka" >[��]</a>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">�ص�����Σ�</td>
            <td width="70%" colspan="3"><input type="text" class='input_style1' name="loc3Code" value="<%=dto.getLoc3Code() %>" style="width:90%" readOnly/></td>
        </tr>        
      </table>      
  </form>
  </body>
<script type="text/javascript">
    function do_sub(){
        var fieldNames = "countyName;location";
	    var fieldLabels = "��������;����ص�";
	    if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)){
       		var actionURL = "/servlet/com.sino.ams.system.object.servlet.CommonObjectServlet";
		    var userParameters = "act=CONFIRM_CHOSE_LOCDESC_ACTION";
		    userParameters += "&countyCode=" + mainFrm.countyCode.value;
        	userParameters += "&countyName=" + mainFrm.countyName.value;
        	userParameters += "&loc2Code=" + mainFrm.loc2Code.value;
        	userParameters += "&location=" + mainFrm.location.value;
        	userParameters += "&loc3Code=" + mainFrm.loc3Code.value;
        	
		    var ajaxProcessor = new AjaxProcessor(actionURL, do_ResponseSetValue, false);
		    ajaxProcessor.setSendData(encodeURI(encodeURI(userParameters)));
		    ajaxProcessor.performTask();
       		
       		window.returnValue = mainFrm.countyName.value + "." + mainFrm.location.value + "." + mainFrm.loc3Code.value+";"+mainFrm.objectNo.value;
       		self.close();
	    }
    }
    
    function do_ResponseSetValue(resText) {
    	mainFrm.objectNo.value = resText;
    }

    function do_Close(){
       self.close();
    }
    
    function choseCounty() {
    	var lookUpName = "<%=LookUpConstant.LOOK_UP_COUNTYCODE%>";
    	var userPara = "&organizationId=" + mainFrm.organizationId.value + "&deptCode=" + mainFrm.deptCode.value;;
	    var dialogWidth = 20;
	    var dialogHeight = 10;
	    var locCode = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
	    if(locCode){
	        dto2Frm(locCode[0], "mainFrm");
	    }
    }
    
    function choseLocDesc() {
		var lookUpName = "<%=LookUpConstant.LOOK_UP_LOC2DESC%>";
		var userPara = "&organizationId=" + mainFrm.organizationId.value;
	    var dialogWidth = 20;
	    var dialogHeight = 10;
	    var locCode = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
	    if(locCode){
	        dto2Frm(locCode[0], "mainFrm");
	    }
	}
</script>
</html>