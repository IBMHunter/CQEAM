<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/inv/headerInclude.jsp"%>
<%@ include file="/inv/headerInclude.htm"%>
<%@ page import = "com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.inv.dzyh.dto.EamDhBillLDTO" %>

<html>
<head>
    <title>��ֵ�׺�Ʒ���⣭̨�ʲ�ѯ����</title>
    <style>
.finput {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;}
.finput2 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:center;}
.finput3 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:right;}
</style>
</head>

<body leftmargin = "0" rightmargin = "0" topmargin = "0">

<%

    RequestParser parser = new RequestParser();
    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = (rows != null && !rows.isEmpty());
    Row row = null;
	EamDhBillLDTO dto = (EamDhBillLDTO)request.getAttribute(QueryConstant.QUERY_DTO); //���Servlet���dto.setXXX()����д��  
	String workorderObjectCode = (String)request.getAttribute("workorderObjectCode");
	String workorderObjectNo = (String)request.getAttribute("workorderObjectNo");
%>
<form method="post" name="mainFrm">
    <script type = "text/javascript">
        printTitleBar("��ֵ�׺�Ʒ���⣭̨�ʲ�ѯ����")
    </script>
    <!--  
	<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
	-->
    <table width = "100%" border = "0" class = "queryHeadBg">
        <tr>
            <td width = "12%" align = "right">�ֿ⣺</td>
            <td width="16%" align="right">
				<input type="text" name="workorderObjectName" value="<%=dto.getWorkorderObjectName() %>" style="width:80%" class="blueborderYellow"><a href="#" title="���ѡ��ֿ�" class="linka" onclick="do_SelectStore();">[��]</a>
				<input type="hidden" name="workorderObjectNo" value="<%=workorderObjectNo %>"><input type="hidden" name="workorderObjectCode" value="<%=workorderObjectCode %>">
			</td>
            <td width = "14%" align = "right">Ŀ¼��ţ�</td>
            <td width = "16%">
            	<input type="text" name="catalogCode" value="<%=dto.getCatalogCode()%>" style="width:80%"><a href="#" title="���ѡ��Ŀ¼���" class="linka" onclick="do_SelectCatalogValueId();">[��]</a>
            </td>
            <td width = "14%" align = "right">Ʒ����</td>
            <td width = "18%">
            	<input type="text" name="itemName" value="<%=dto.getItemName()%>" style="width:80%">
            </td>
            <td width = "2%" align = "center">
                <img src = "/images/button/query.gif" style = "cursor:'hand'" onclick = "do_search();" alt = "��ѯ">
            </td>
            <td width = "2%" align = "center">
            <img src="/images/button/save.gif" style="cursor: 'hand'" onclick="do_save();" alt="����">
            </td>
        </tr>
    </table>
    
    <div style = "/*overflow-x:scroll;width:100%*/">
		
        <div style="overflow-y:scroll;overflow-x:scroll;left:0px;width:100%;height:390px">
        <!--  
        <script type="text/javascript">
        	var columnArr = new Array("Ŀ¼���","�����", "Ʒ��", "����ͺ�", "����", "����","ʹ�ò���","������","�ص�","������","��������","����","��ע");
        	var widthArr = new Array("6%", "10%", "8%", "8%", "5%","5%","8%","8%","8%","8%","8%","10%","12%");
        	printTableHead(columnArr, widthArr);
    	</script>
    	-->
			<table width="160%" border="1" bordercolor="#666666">
			    
			   <tr height = "20" class = "headerTable">
                    <td width = "6%" align = "center">Ŀ¼���</td>
                    <td width = "10%" align = "center">�����</td>
                    <td width = "8%" align = "center">Ʒ��</td>
                    <td width = "8%" align = "center">����ͺ�</td>
                    <td width = "5%" align = "center">����</td>
                    <td width = "5%" align = "center">����</td>
                    <td width = "8%" align = "center">ʹ�ò���</td>
                    <td width = "8%" align = "center">������</td>
                    <td width = "8%" align = "center">�ص�</td>
                    <td width = "8%" align = "center">������</td>
                    <!--
                    <td width = "8%" align = "center">��������</td> 
                    <td width = "10%" align = "center">����</td>
                    -->
                    <td width = "12%" align = "center">��ע</td>
               </tr>
               
<% 
	if(hasData) {
		for(int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
%> 
				<tr height="20" style="cursor: 'hand'" onmousemove="style.backgroundColor='#EFEFEF'" onmouseout="style.backgroundColor='#ffffff'">			
					<td width="6%" align="right">
					<input type="text" name="itemCategory2" value="<%=row.getValue("ITEM_CATEGORY2")%>" class="finput">
					<input type="hidden" name="itemCategory" value="<%=row.getValue("ITEM_CATEGORY") %>">
					</td>
					<td width="10%" align="left"><%=row.getValue("BARCODE")%></td>
					<td width="8%" align="right">
					<input type="text" value="<%=row.getValue("ITEM_NAME")%>" readonly="readonly" class="finput">
					<input type="hidden" name="itemCode" value="<%=row.getValue("ITEM_CODE") %>">
					</td>
					<td width="8%" align="right">
					<input type="text" value="<%=row.getValue("ITEM_SPEC") %>" readonly="readonly" class="finput">
					</td>
					<td width="5%" align="right"><%=row.getValue("ITEM_QTY")%></td>
					<td width="5%" align="left"><%=row.getValue("PRICE")%></td>
					<td width="8%" align="left" onclick="do_SelectDept(this);">
					<input type="text" name="deptName" value="<%=row.getValue("DEPT_NAME")%>" 
						readonly="readonly" class="blueborderYellow" 
						alt="���ѡ��ʹ�ò���"/><input type="hidden" name="deptCode" 
						value="<%=row.getValue("DEPT_CODE") %>"><input type="hidden" name="catalogValueId2" 
						value="<%=row.getValue("CATALOG_VALUE_ID") %>"><input type="hidden" name="barcode" 
						value="<%=row.getValue("BARCODE") %>"><input type="hidden" name="systemid" 
						value="<%=row.getValue("SYSTEMID") %>">
					</td>
					<td width="8%" align="center" onclick="do_SelectUser(this);">
					<input type="text" name="userName" value="<%=row.getValue("USER_NAME")%>" readonly="readonly" 
							class="blueborderYellow" alt="���ѡ��������"><input type="hidden" name="employeeId" value="<%=row.getValue("EMPLOYEE_ID") %>">
					</td>
					<td width="8%" align="center" onclick="do_SelectAddressId(this);">
					<input type="text" name="workorderObjectNoName1" value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>" readonly="readonly" 
							class="blueborderYellow" alt="���ѡ��ص�"><input type="hidden" name="workorderObjectNo1" value="<%=row.getValue("WORKORDER_OBJECT_NO") %>">		
					</td>
					<td width="8%" align="center">
					<input type="text" name="maintainUser" value="<%=row.getValue("MAINTAIN_USER") %>" alt="��д������">
					</td>
					<!--  
					<td width="8%" align="center">
					<input type="text" id="lastLocChgDate<%=i%>" name="lastLocChgDate" class="blueborderYellow" onclick="gfPop.fPopCalendar(lastLocChgDate<%=i%>)" onchange="do_UpdateMaintainUser(this);" oldDate="<%=row.getValue("LAST_LOC_CHG_DATE") %>" value="<%=row.getValue("LAST_LOC_CHG_DATE") %>" title="���ѡ����������" style="width:100%;text-align:center">
					</td>
					-->
					<td width="12%" align="right">
					<input type="text" name="remark" value="" readonly="readonly" class="finput">
					</td>
				</tr>
<% 
		}
	}
%>
			</table>
		</div>
		
		</div>

		<input name="act" type="hidden">
		<!--  
		<input type="hidden" name="responsibilityDept" value="">
		<input type="hidden" name="responsibilityUser" value="">
		-->
	</form>
<%
	if(hasData){
%>
	<div style="position:absolute;top:470px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}	
%>

	<%=WebConstant.WAIT_TIP_MSG%>
	<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"></jsp:include>
</body>
</html>
<script type = "text/javascript">

    function do_search() {
    	var workorderObjectCode = document.forms[0].workorderObjectCode.value;
    	//var workorderObjectNo = document.forms[0].workorderObjectNo.value;
    	
    	if(workorderObjectCode != "null") {
	      mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	      mainFrm.action = "/servlet/com.sino.ams.inv.dzyh.servlet.EamDhBillLServlet?workorderObjectCode=" + workorderObjectCode;
	      mainFrm.submit();
	    } else {
	    	alert("����ѡ��һ���ֿ�!");
	    	return false;
	    }
    }
    
    
    function do_save(){   	
    	var workorderObjectCode = document.forms[0].workorderObjectCode.value;
    	var workorderObjectNo = document.forms[0].workorderObjectNo1.value;
    	var itemCategory = document.getElementsByName('itemCategory');
    	var length = itemCategory.length; 
    	if(workorderObjectCode != "null") {
    		if(length != 0){
    			if(workorderObjectNo != "" || workorderObjectNo != null){
			    	mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
			        mainFrm.action = "/servlet/com.sino.ams.inv.dzyh.servlet.EamDhBillLServlet?workorderObjectCode=" + workorderObjectCode;
			        mainFrm.submit();
		        } else {
		        	alert("�����εص㲻����Ϊ��!");
		        	return false;
		        }
		    } else {
		    	alert("û��Ҫ���������!");
		    	return false;
		    }
	    } else {
	    	alert("����ѡ��һ���ֿ�!");
	    	return false;
	    }
    }
    
    //ҳ���ɫ�򲿷ֵ�ѡ��ֿ�
    function do_SelectStore() {
    	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_WORKORDER_OBJECT_NO_DZYH_WARE%>";
    	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    	var vendorNames = window.showModalDialog(url, null, popscript);
    	if(vendorNames){
        	var vendorName = null;
        	document.forms[0].workorderObjectNo.value = vendorNames[0].workorderObjectNo;
        	document.forms[0].workorderObjectName.value = vendorNames[0].workorderObjectName;
        	document.forms[0].workorderObjectCode.value = vendorNames[0].workorderObjectCode;
    	}
    }
    
    function do_SelectCatalogValueId() {
    	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_CATALOG_VALUE_ID%>";
    	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    	var vendorNames = window.showModalDialog(url, null, popscript);
    	if(vendorNames){
        	var vendorName = null;
        	document.forms[0].catalogCode.value = vendorNames[0].catalogCode;
    	}
    }
    
    
    function do_SelectDept(obj) {
    	var parentNode = obj.nextSibling;
    	parentNode.childNodes[1].value = "";
    	parentNode.childNodes[0].value = "";
    	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_RESPONSIBILITY_DEPT%>";
    	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    	var vendorNames = window.showModalDialog(url, null, popscript);
    	if(vendorNames){
        	var vendorName = null;
        	obj.childNodes[0].value = vendorNames[0].deptName;
        	obj.childNodes[1].id = vendorNames[0].deptCode;
    	}
    }
    
    function do_SelectUser(obj) {
    
    	var parentNode = obj.previousSibling;
    	var deptCode = parentNode.childNodes[1].id;
    	
    	var userParam = "deptCode=" + deptCode;
    	
	    if (deptCode == "") {
	        alert("����ѡ��ʹ�ò��ţ���ѡ�������ˣ�");
	        return;
	    }
    
    	var lookUpUser = "<%=LookUpInvConstant.LOOK_UP_RESPONSIBILITY_USER%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        var users = getLookUpValues(lookUpUser, dialogWidth, dialogHeight, userParam);
        if (users) {
        	obj.childNodes[0].value = users[0].userName;
            obj.childNodes[1].value = users[0].employeeId;
        }
    
    }
    
    //ҳ���������ݵĵص�ѡ��
    function do_SelectAddressId(obj) {
    	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_WORKORDER_OBJECT_NO_DZYH%>";
    	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    	var vendorNames = window.showModalDialog(url, null, popscript);
    	if(vendorNames){
        	var vendorName = null;
        	obj.childNodes[0].value = vendorNames[0].workorderObjectName;
        	obj.childNodes[1].value = vendorNames[0].workorderObjectNo;
    	}
    }
    
    
    /*
    function changePrivi(chk) {
        var cbId = chk.id;
        cbId = cbId.replace("Box", "");
        var hiddenObj = document.getElementById(cbId);
        if (chk.checked) {
            hiddenObj.value = 1;
        } else {
            hiddenObj.value = 0;
        }
    }
    
    function do_checkNotNull() {
        var checkPoint = 0;
        if (document.mainFrm.executeUserName.value != null && document.mainFrm.executeUserName.value != "") {
            checkPoint = 1;

        }
        if (document.mainFrm.executeInv.value != null && document.mainFrm.executeInv.value != "") {
            checkPoint = 1;
        }
        return  checkPoint;
    }
    */


    
//---------------------------------------------------------------------------------------------------

var xmlHttp;

//---------------------------------------------------------------------------------------------------

//-- updateDept
function updateDept(catalogValueId, barcode, oldDept, newDept) {
    var url = "";
    //var dept = document.getElementById("responsibilityDept").value;
    xmlHttp = createXMLHttpRequest();
    if (newDept != "") {
        url = "/servlet/com.sino.ams.inv.dzyh.servlet.EamDhBillLServlet?act=UPDATEDEPT_ACTION&catalogValueId=" + catalogValueId + "&barcode=" + barcode + "&oldDept=" + oldDept + "&newDept=" + newDept;
        xmlHttp.onreadystatechange = handleReadyStateChangeDept;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}

//updateresponsibilityDept
function handleReadyStateChangeDept() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            //alert(resText);     
        } else{
        	alert(xmlHttp.status);
        }
    }
}

//-- updateUser
function updateUser(catalogValueId, barcode, oldUser, newUser) {
    var url = "";
    //var dept = document.getElementById("responsibilityDept").value;
    xmlHttp = createXMLHttpRequest();
    if (newUser != "") {
        url = "/servlet/com.sino.ams.inv.dzyh.servlet.EamDhBillLServlet?act=UPDATEUSER_ACTION&catalogValueId=" + catalogValueId + "&barcode=" + barcode + "&oldUser=" + oldUser + "&newUser=" + newUser;
        xmlHttp.onreadystatechange = handleReadyStateChangeUser;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}

//updateresponsibilityDept
function handleReadyStateChangeUser() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            //alert(resText);     
        } else{
        	alert(xmlHttp.status);
        }
    }
}

//-- updateAddressId
function updateAddressId(catalogValueId, barcode, oldAddressId, newAddressId) {
    var url = "";
    //var dept = document.getElementById("responsibilityDept").value;
    xmlHttp = createXMLHttpRequest();
    if (newAddressId != "") {
        url = "/servlet/com.sino.ams.inv.dzyh.servlet.EamDhBillLServlet?act=UPDATEADDRESSID_ACTION&catalogValueId=" + catalogValueId + "&barcode=" + barcode + "&oldAddressId=" + oldAddressId + "&newAddressId=" + newAddressId;
        xmlHttp.onreadystatechange = handleReadyStateChangeAddressId;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}

//updateresponsibilityDept
function handleReadyStateChangeAddressId() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            //alert(resText);     
        } else{
        	alert(xmlHttp.status);
        }
    }
}

//-- updateMaintainUser
function updateMaintainUser(catalogValueId, barcode, oldMaintainUser, newMaintainUser) {
	var url = "";
	xmlHttp = createXMLHttpRequest();
    if (newMaintainUser != "") {
        url = "/servlet/com.sino.ams.inv.dzyh.servlet.EamDhBillLServlet?act=UPDATEMAINTAINUSER_ACTION&catalogValueId=" + catalogValueId + "&barcode=" + barcode + "&oldMaintainUser=" + oldMaintainUser + "&newMaintainUser=" + newMaintainUser;
        xmlHttp.onreadystatechange = handleReadyStateChangeMaintainUser;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    } else {
    	alert("�����˲�����Ϊ��!");
    }
}

//updateMaintainUser
function handleReadyStateChangeMaintainUser() {
	if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            //alert(resText);     
        } else{
        	alert(xmlHttp.status);
        }
    }
}

//-- updateLastLocChgDate
function updateLastLocChgDate(catalogValueId, barcode, oldDate, newDate) {
	var url = "";
	xmlHttp = createXMLHttpRequest();
	var validate = /(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)/;
	
    if (validate.test(newDate)) {   
    //(!/^19\d\d\-[0-1]\d\-[0-3]\d$/.test(birth) && !/^20[0-1]\d\-[0-1]\d\-[0-3]\d$/.test(birth))
    
        url = "/servlet/com.sino.ams.inv.dzyh.servlet.EamDhBillLServlet?act=UPDATELASTLOCCHGDATE_ACTION&catalogValueId=" + catalogValueId + "&barcode=" + barcode + "&oldDate=" + oldDate + "&newDate=" + newDate;
        xmlHttp.onreadystatechange = handleReadyStateChangeLastLocChgDate;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    } else {
    	alert("�밴��ȷ��ʱ���ʽ��д(YYYY-MM-DD)");
    }
}

//updateLastLocChgDate
function handleReadyStateChangeLastLocChgDate() {
	if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            //alert(resText);     
        } else{
        	alert(xmlHttp.status);
        }
    }
}
</script>