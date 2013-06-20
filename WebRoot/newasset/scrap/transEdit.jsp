<%@ page contentType="text/html;charset=GBK" language="java" %>

<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<%@ page import="com.sino.ams.newasset.constant.AssetsLookUpConstant"%>
<%@ page import="com.sino.ams.newasset.scrap.constant.ScrapURLListConstant"%>
<html>
<%
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transId = headerDTO.getTransId();
    DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    String isGroupPID = request.getAttribute(AssetsWebAttributes.IS_GROUP_PID).toString();//�Ƿ��й�˾�ۺϲ��������
%>
<head>
	 
	<title>����ʵ�ﱨ������</title>
	<script type="text/javascript" src="/WebLibary/js/util.js"></script>
	<script type="text/javascript" src="/WebLibary/js/util2.js"></script>
	<script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
	<script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
    <script type="text/javascript" src="/WebLibary/js/AjaxProcess.js"></script>
    <script type="text/javascript">
        printToolBar();
    </script>
</head>
<body leftmargin="0" topmargin="0" onload="initPage();" onbeforeunload="doBeforeUnload()" onunload="doUnLoad()" style="overflow: auto;">
<%@ include file="/flow/flowNoButton.jsp" %>
<form action="<%= ScrapURLListConstant.SCRAP_SERVELT %>" method="post" name="mainFrm" >
<jsp:include page="/message/MessageProcess"/>
<%@ include file="/flow/flowPara.jsp"%>
    <table width=100% border="0">
        <tr>
            <td align=right width=8%>���ݺţ�</td>
            <td width=13%>
                <input type="text" name="transNo" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getTransNo()%>">
            </td>
            <td align=right width=8%>�������ͣ�</td>
            <td width=13%>
                <input type="text" name="transTypeValue" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getTransTypeValue()%>">
            </td>
            <td align=right width=8%>�������</td>
            <td width=13%>
                <input name="fromGroupName" class="input_style2" readonly style="width:100%; cursor:pointer" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������"  ></td>
            <td align=right width=8%>���ϲ��ţ�</td>
            <td width=29%>
                <input name="fromDeptName" class="input_style2" readonly style="width:100%; cursor:pointer" value="<%=headerDTO.getFromDeptName()%>" >
            </td>
        </tr>
        <tr>
            <td align=right width=8%>�����ˣ�</td>
            <td width=13%>
                <input type="text" name="created1" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getCreated()%>" >
            </td>
            <td width=8% align=right>�������ڣ�</td>
            <td width=13%>
                <input name="creationDate" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getCreationDate()%>" ></td>
            <td width=8% align=right>��˾���ƣ�</td>
            <td width=13%>
                <input name="userCompanyName" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getFromCompanyName()%>"></td>
            <td align=right width=8%>�ʲ����ࣺ</td>
            <td width=29%>
                <select name="faContentCode" style="width:100%" class="select_style1" onChange="do_ChangeContentCode"><%=headerDTO.getFaContentOption()%></select>
            </td>
        </tr>
        <tr style="height:46px">
            <td width=8% height="46px" align=right>�����̶ȣ�</td>
            <td width=13% height="46px">
                <select name="emergentLevel" class="select_style1" style="width:100%" ><%=headerDTO.getEmergentLevelOption()%></select>
            </td>
            <td width="8%" height="46px"  align="right">����˵����</td>
            <td width=71%  height="46px" colspan="5">
                <table style="width:100%;height:100%">
                    <tr style="width:100%;height:100%">
                        <td style="width:98%;height:46px"><textarea name="createdReason" class="selectNoEmpty"><%=headerDTO.getCreatedReason()%></textarea></td>
                        <td style="width:2%;height:46px"><font color="red">*</font></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
<input type="hidden" name="fromGroup" value="<%=headerDTO.getFromGroup()%>">
<input type="hidden" name="fromDept" class="input_style2" readonly style="width:100%; cursor:pointer" value="<%=headerDTO.getFromDept()%>" >
<input type="hidden" name="fromOrganizationId" value="<%=headerDTO.getFromOrganizationId()%>">
<input type="hidden" name="transType" value="ASS-DIS">
<input type="hidden" name="created" value="<%=headerDTO.getCreated()%>">
<input type="hidden" name="createdBy" value="<%=headerDTO.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=transId%>">
<input type="hidden" name="transStatus"   value="<%=headerDTO.getTransStatus() %>">
<input type="hidden" name="isGroupPID" value="<%=isGroupPID%>">
<input type="hidden" name="toDept" value="<%=headerDTO.getToDept()%>">
<input type="hidden" name="toGroup" value="<%=headerDTO.getToGroup()%>">
<input type="hidden" name="act" value="">
<input type="hidden" name="isThred" value="">

<input type="hidden" name="excel" value="">
<div id="searchDiv" style="position:absolute;top:125px;left:1px;width:100%">
        <img src="/images/eam_images/choose.jpg" alt="ѡ���ʲ�" onClick="do_SelectAssets(); return false;">
        <img src="/images/eam_images/delete_line.jpg" alt="ɾ����" onClick="deleteLine(); return false;"> 
         ͳһ���ã�
        <input type="checkbox" name="allRejectType" id="allRejectType"><label for="allRejectType">��������</label>
        <input type="checkbox" name="allRetirementCost" id="allRetirementCost"><label for="allRetirementCost">���ϳɱ�</label>
</div>
	<%
	String[] widthArr = {  "8%" , "10%", "10%", "10%", "6%",   "6%","15%","6%","15%"  }; 
	int arrIndex = 0;
	%>
    <div id="headDiv" style="overflow:hidden;position:absolute;top:155px;left:1px;width:100%">
		<table class="headerTable" border="1" width="140%">
	        <tr height="23px">
	            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">��ǩ��</td>
                <td align=center width="<%= widthArr[ arrIndex++ ] %>">��������<font color="red">*</font></td>
                <td align=center width="<%= widthArr[ arrIndex++ ] %>">�ʲ�����</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">�ʲ��ͺ�</td>
                <td align=center width="<%= widthArr[ arrIndex++ ] %>">���ϳɱ�</td>
                
                <td align=center width="<%= widthArr[ arrIndex++ ] %>">��������</td>
				<td align=center width="<%= widthArr[ arrIndex++ ] %>">���ڵص�</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">������</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">���β���</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:100%;position:absolute;top:178px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		
		if (lineSet == null || lineSet.isEmpty()) {
			arrIndex = 0;
%>
            <tr class="dataTR" onClick="executeClick(this)" style="display:none" title="����鿴�ʲ���ϸ��Ϣ">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput2" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" ><select name="rejectType" onBlur="do_SetRejectType(this)"  id="rejectType0" style="width:100%" class="selectNoEmpty" ><%=headerDTO.getRejectTypeHOpt()%></select></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
                <td width="<%= widthArr[ arrIndex++ ] %>" align="right"><input type="text" name="retirementCost" id="retirementCost0" onBlur="do_SetRetirementCost(this)" onchange="do_checkQty(this);" class="finput" value=""></td>
                
                <td width="<%= widthArr[ arrIndex++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput2" readonly value=""></td>
                <td width="<%= widthArr[ arrIndex++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName0" class="finput" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName0" class="finput" readonly value=""></td>
				<td style="display:none"> 
					<input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept0" value="">
					<input type="hidden" name="oldLocation" id="oldLocation0" value="">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser0" value="">
				</td>
            </tr>
<%
		} else {
			
			AmsAssetsTransLineDTO lineDTO = null;
			String barcode = "";
			for (int i = 0; i < lineSet.getSize(); i++) {
				arrIndex = 0;
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" onClick="executeClick(this)" style="cursor:pointer" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>" value="<%=barcode%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" ><select name="rejectType" id="rejectType<%=i%>" style="width:100%" class="selectNoEmpty"><%= lineDTO.getRejectTypeOpt()%></select></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
                <td width="<%= widthArr[ arrIndex++ ] %>" align="right"><input type="text" name="retirementCost" id="retirementCost<%=i%>" class="finputNoEmpty3" value="<%=lineDTO.getRetirementCost()%>" onBlur="do_SetRetirementCost(this)" onchange="do_checkQty(this);"></td>
                
                <td width="<%= widthArr[ arrIndex++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
                <td width="<%= widthArr[ arrIndex++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept<%=i%>" value="<%=lineDTO.getOldResponsibilityDept()%>">   
					<input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
				</td>
            </tr>
<%
			}
		}

%>
         </table>
   </div>

</form>
<jsp:include page="/public/hintMessage.jsp" flush="true"/>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<script type="text/javascript">

var dataTable = document.getElementById("dataTable");

function deleteLine() {
    deleteTableRow(dataTable, 'subCheck');
}

function do_isProfessionalGroup() {
	var fromGroup = mainFrm.fromGroup.value;
	if(fromGroup != null && fromGroup != ""){
		var url = "<%=ScrapURLListConstant.SCRAP_SERVELT%>";
		url += "?act=VALIDATE_ACTION";
		url += "&fromGroup=" + mainFrm.fromGroup.value;
        var ajaxProcessor = new AjaxProcessor(url, do_ProcessResponse, false);
		ajaxProcessor.performTask();
	}
}

function do_ProcessResponse(responseContent){
    mainFrm.isGroupPID.value = responseContent;
}


function initPage() {
    window.focus();
    doLoad();
    do_SetPageWidth();
    do_ControlProcedureBtn();
    otherInit();
}

var contentCode = mainFrm.faContentCode.value;
function do_ChangeContentCode() {
    var rows = dataTable.rows;
    var rowCount = rows.length;
    if (rowCount > 1 || (rowCount == 1 && rows[0].style.display != "none")) {
        if (confirm("�ı��ʲ����ཫɾ���Ѿ�ѡ����ʲ����ݣ��Ƿ����������������ȷ������ť������������ȡ������ť")) {
            contentCode = mainFrm.faContentCode.value;
            deleteRow(dataTable);
        } else {
            selectSpecialOptionByItem(mainFrm.faContentCode, contentCode);
        }
    }
}

function do_AppValidate() {
    var isValid = true;
    var transType = mainFrm.transType.value;
    if( isValid ){
        var fieldLabels = "�������;����˵��;��������";
        var fieldNames = "fromGroupName;createdReason;rejectType";
        var validateType = EMPTY_VALIDATE;
        isValid = formValidate(fieldNames, fieldLabels, validateType);
        //if(isValid){
          //  fieldLabels = "���ϳɱ�";
          //  fieldNames = "retirementCost";
           // validateType = POSITIVE_VALIDATE;
           // isValid = formValidate(fieldNames, fieldLabels, validateType);
        //}
    }
    return isValid;
}

function do_checkQty( checkObj ){
	var qtyValue = checkObj.value; 
	if( !isPositiveNumber( qtyValue ) ){
       	alert("���ϳɱ�ֻ��Ϊ���ֶ��� >= 0!");  
       	checkObj.value = ""; 
    }
}

function do_setQuantity() {
    //var length = document.getElementsByName("retirementCost").length;
    var objs = document.getElementsByName("retirementCost");
    var length = objs.length;
    for (var i = 0; i < length; i++) {
        var retirementCost = objs[i].value;
        
        if( !isPositiveNumber( retirementCost ) ){
        	alert("���ϳɱ�ֻ��Ϊ���ֶ��� >= 0!");  
        	objs[i].value = "";
            break;
	    }
        
        //if (retirementCost < 0) {
        //    alert("���ϳɱ�����>0");
        //    objs[i].value = "";
        //    break;
        //} 
    }
}

function do_ThredDept(fDept, tDept){
    var url = "<%=ScrapURLListConstant.SCRAP_SERVELT%>?act=DO_THRED_DEPT&fDept=" + fDept + "&tDept=" + tDept;
    var ajaxProcessor = new AjaxProcessor(url, pointerleReadyStateChange1, false);
    ajaxProcessor.performTask();
}


function pointerleReadyStateChange1(resText) {
    document.mainFrm.isThred.value = resText;
}

/**
 *  ����ֵ
 */
function otherInit(){
	try{
		if( null == document.forms[0].fromGroup.value || document.forms[0].fromGroup.value == "" || document.forms[0].fromGroup.value=="0" ||  document.forms[0].fromGroupName.value == ""  ){
	 		document.forms[0].fromGroup.value=document.getElementById("flow_group_id").value; //�����������
			document.forms[0].fromGroupName.value=document.getElementById("flow_group_name").value; //�����������
			document.forms[0].fromDept.value=document.getElementById("app_dept_code").value; //�����������
			document.forms[0].fromDeptName.value=document.getElementById("app_dept_name").value; //�����������
		}
	}catch(ex){
		alert( ex.message );
	}
}


/**
  * ���ܣ�ѡ���ʲ�
 */
function do_SelectAssets() {
    if(mainFrm.faContentCode.value == ""){
		alert("����ѡ���ʲ����࣬��ѡ���ʲ���");
		mainFrm.faContentCode.focus();
		return;
	}

	var dialogWidth = 52;
	var dialogHeight = 29;
	<%
    	String lun="";
    	if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
    		lun = AssetsLookUpConstant.LOOK_TRANSFER_ASSETS_TD;
    	} else {
    		lun = AssetsLookUpConstant.LOOK_TRANSFER_ASSETS_OTHER ;
    	}
    %>
	var lookUpName = "<%=lun%>";

	var userPara = "transType=" + mainFrm.transType.value;
	userPara += "&faContentCode=" + mainFrm.faContentCode.value;

	var assets = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	if (assets) {
		for (var i = 0; i < assets.length; i++) {
			var data = assets[i];
			appendDTO2Table(dataTable, data, false, "barcode");
		}
	}
}

function do_Save_app() {
    var actObj = document.getElementById("act");
    actObj.value = "SAVE_ACTION";
    document.forms[0].submit();
    document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}

//ͳһ���� -  
function do_SetRejectType( lineBox ){

try{
	if(!mainFrm.allRejectType ){
		return;
	}
	if(!mainFrm.allRejectType.checked){
		return;
	}
	do_SetLineDate(lineBox, "rejectType" );
}catch(ex){
	alert( ex.message );
}

}
//ͳһ���� -  
function do_SetRetirementCost( lineBox ){
    try{
        if(!mainFrm.allRetirementCost){
            return;
        }
        if(!mainFrm.allRetirementCost.checked){
            return;
        }
        do_SetLineDate( lineBox, "retirementCost" );
    } catch(ex){
        alert( ex.message );
    }
}

function do_ViewAssetsData(){
    var tab = document.getElementById("dataTable");
    var rows = tab.rows;
    var para = "";
    var effectiveTime = 0;
    for(var i = 0; i < rows.length; i++){
        var row = rows[i];
        var checkObj = row.childNodes[0].childNodes[0];
        if(!checkObj.checked){
            continue;
        }
        var checkLocation = getTrNode(row, "checkLocation");
        var checkCategory = getTrNode(row, "checkCategory");
        if(effectiveTime > 0){
            para += "$";
        }
        para += checkLocation.value;
        para += "_" + checkCategory.value;
        effectiveTime++;
    }
    if (para == "") {
        alert("����ѡҪ�鿴�ʲ���ϸ�ĵص㣡");
        return;
    }
    var actionURL = "/servlet/com.sino.ams.newasset.servlet.ObjectAssetsServlet";
    actionURL += "?checkLocation=" + para;
    window.open(actionURL, "objectAssetsWin", "fullscreen=yes");
}

//ͳһ���� - ���ô���
function do_SetLineDate(lineBox , fieldName ){  
	var id = lineBox.id;
	var lineDate = lineBox.value;
	
	var dateFields = document.getElementsByName( fieldName );
	  
	var rows = dataTable.rows;
	var row = null;
	var checkObj = null;
	var checkedCount = getCheckedBoxCount("subCheck");
	   
	for(var i = 0; i < dateFields.length; i++){
		if(checkedCount > 0){
			row = rows[i];
			checkObj = row.childNodes[0].childNodes[0];
			if(!checkObj.checked){
				continue;
			}
		}
		if(dateFields[i].id == id){
			continue;
		}
		dateFields[i].value = lineDate;
	}
}

function setAttachmentConfig(){
    var attachmentConfig = new AttachmentConfig();
    attachmentConfig.setOrderPkName("transId");
    return attachmentConfig;
}
</script>