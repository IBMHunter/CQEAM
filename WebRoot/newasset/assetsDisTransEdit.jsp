<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.sino.ams.newasset.constant.AssetsDictConstant"%>
<%@ page import="com.sino.ams.newasset.constant.AssetsLookUpConstant"%>
<%@ page import="com.sino.ams.system.user.dto.SfUserRightDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
	<%
		try {
			AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
			String transId = headerDTO.getTransId();
			SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
			DTOSet userDTO = userAccount.getUserRight();
			String roleName = "";
			Map userRightMap = new HashMap();
			if (null != userDTO) {
				for (int i = 0; i < userDTO.getSize(); i++) {
					SfUserRightDTO userRight = (SfUserRightDTO) userDTO
							.getDTO(i);
					roleName = userRight.getRoleName();
					userRightMap.put(roleName, roleName);
				}
			}
			String isGroupPID = request.getAttribute(
					AssetsWebAttributes.IS_GROUP_PID).toString();//�Ƿ��й�˾�ۺϲ��������
			//EXCEL���
			//RowSet rows = (RowSet) request.getAttribute(WebAttrConstant.WORKORDER_LOC_ROWSET);
			DTOSet lineSetPri2 = (DTOSet) request
					.getAttribute(AssetsWebAttributes.PRIVI_DATA);//EXCEL����ʱ���벻�ɹ���DTOSET
					session.setAttribute("lineSetPri",lineSetPri2);	
	%>
	<head>
		<%
			String titleText = "";
				String titleBar = "";
				SfUserDTO userApp = (SfUserDTO) SessionUtil
						.getUserAccount(request);
				if ("Y".equalsIgnoreCase(userApp.getIsTd())) {
					titleText = headerDTO.getTransTypeValue() + "(TD)";
					titleBar = headerDTO.getTransTypeValue() + "(TD)";
				} else {
					titleText = headerDTO.getTransTypeValue();
					titleBar = headerDTO.getTransTypeValue();
				}
		%>
		<title><%=titleText%></title>
		<script type="text/javascript" src="/WebLibary/js/test.js"></script>
		<script type="text/javascript" src="/WebLibary/js/util.js"></script>
		<script type="text/javascript" src="/WebLibary/js/util2.js"></script>
		<script type="text/javascript" src="/WebLibary/js/BarVarSX.js"></script>
		<script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
		<script type="text/javascript" src="/WebLibary/js/AjaxProcess.js"></script>

		<script type="text/javascript">
        printToolBar();
    	</script>
	</head>
	<body leftmargin="0" topmargin="0" onload="initPage();helpInit('2.3.1');" onbeforeunload="doBeforeUnload()" onunload="doUnLoad()"
		style="overflow: auto;" >
	<input type="hidden" name="helpId" value="">
		<%@ include file="/flow/flowNoButton.jsp"%>
		<form
			action="/servlet/com.sino.ams.newasset.servlet.AmsAssetsDisTransHeaderServlet"
			method="post" name="mainFrm">
			<jsp:include page="/message/MessageProcess" flush="true" />
			<%@ include file="/flow/flowPara.jsp"%>
<div id="searchDiv" style="position:absolute;top:30px;left:1px;width:100%">
						<table width=100% border="0">
							<tr>
								<td align=right width=8%>���ݺţ�</td>
								<td width=13%>
									<input type="text" name="transNo" class="input_style2" readonly
										style="width: 100%" value="<%=headerDTO.getTransNo()%>">
								</td>
								<td align=right width=8%>�������ͣ�</td>
								<td width=13%>
									<input type="text" name="transTypeValue" class="input_style2"
										readonly style="width: 100%"
										value="<%=headerDTO.getTransTypeValue()%>">
								</td>
								<td align=right width=8%>�������</td>
								<td width=13%>
									<input name="fromGroupName" id="fromGroupName" class="input_style2" readonly
										style="width: 100%; cursor: pointer"
										value="<%=headerDTO.getFromGroupName()%>"
										onclick="do_SelectCreateGroup();" title="���ѡ�����ġ��������">
								</td>
								<td align=right width=8%>���ϲ��ţ�</td>
								<td width=28%>
									<input name="fromDeptName" id="fromDeptName" style="width: 100%"
										class="input_style2" READONLY onChange="do_ConfirmChange()"
										value="<%=headerDTO.getFromDeptName()%>" />
								</td>
							</tr> 
							<tr>
								<td align=right width=8%>�����ˣ�</td>
								<td width=13%>
									<input type="text" name="created1" class="input_style2"
										readonly style="width: 100%"
										value="<%=headerDTO.getCreated()%>">
								</td>
								<td align=right>�������ڣ�</td>
								<td>
									<input name="creationDate" class="input_style2" readonly
										style="width: 100%;" value="<%=headerDTO.getCreationDate()%>">
								</td>
								<td align=right>��˾���ƣ�</td>
								<td>
									<input name="userCompanyName" class="input_style2" readonly
										style="width: 100%;"
										value="<%=headerDTO.getFromCompanyName()%>">
								</td>
								<td align=right>�����̶ȣ�</td>
						        <td >
									<select name="emergentLevel" class="select_style1" style="width:100%"  onchange="document.getElementById('sf_priority').value = this.value;"><%=headerDTO.getEmergentLevelOption()%></select>
								</td>
							</tr>
							<tr>
								<td width="8%" height="40" align="right" valign="top">����˵����</td>
								<td width="92%" height="40" colspan="5">
                                    <table style="width:100%;height:100%">
                                        <tr style="width:100%;height:100%">
                                            <td style="width:99%;height:100%"><textarea name="createdReason" class="textareaNoEmpty"><%=headerDTO.getCreatedReason()%></textarea></td>
                                            <td style="width:1%;height:100%"><font color="red">*</font></td>
                                        </tr>
                                    </table>
								</td>
								
						        <td align=right >ʵ�������<font color="red">*</font></td>
						        <td >
							        <select name="specialityDept" style="width:100%" class="selectNoEmpty" ><%=headerDTO.getSpecialityDeptOption()%></select>
						        </td>
								
							</tr>
						</table>
</div>
			<input type="hidden" name="fromGroup" id="fromGroup"
				value="<%=headerDTO.getFromGroup()%>">
			<input type="hidden" name="isTd"
				value="<%=userApp.getIsTd()%>">
			<input type="hidden" name="fromDept" id="fromDept"
				value="<%=headerDTO.getFromDept()%>">
			<input type="hidden" name="fromOrganizationId"
				value="<%=headerDTO.getFromOrganizationId()%>">

			<input type="hidden" name="transType" value="ASS-DIS">
			<input type="hidden" name="transferType"
				value="<%=headerDTO.getTransferType()%>">
			<input type="hidden" name="created"
				value="<%=headerDTO.getCreated()%>">
			<input type="hidden" name="createdBy"
				value="<%=headerDTO.getCreatedBy()%>">
			<input type="hidden" name="transId" value="<%=transId%>">
			<input type="hidden" name="isGroupPID" value="<%=isGroupPID%>">
			<input type="hidden" name="toDept" value="<%=headerDTO.getToDept()%>">
			<input type="hidden" name="transStatus"   value="<%=headerDTO.getTransStatus() %>">
			<input type="hidden" name="toGroup"
				value="<%=headerDTO.getToGroup()%>">
			<input type="hidden" name="act" value="">
			<input type="hidden" name="isThred" value="">
			<input type="hidden" name="excel" value="">
			<input type="hidden" name="fromExcel" value="<%= StrUtil.nullToString( request.getParameter("fromExcel") ) %>">
			

<div id="buttonDiv" style="position:absolute;top:195px;left:1px;width:100%">
    <img src="/images/eam_images/choose.jpg" alt="ѡ���ʲ�"
						onClick="do_SelectAssets(); return false;">
					<img src="/images/eam_images/delete_line.jpg" alt="ɾ����"
						onClick="deleteLine(); return false;">
					<span id="warn"></span>
					<img src="/images/eam_images/imp_from_excel.jpg" alt="Excel����"
						onClick="do_excel();">
							<%
								if (lineSetPri2 != null) {
										if (!lineSetPri2.isEmpty()) {
							%>
					<img src="/images/eam_images/detail_info.jpg"
						alt="�鿴�ʲ���ǩ��δ����ɹ���ϸ��Ϣ" onClick="do_Transfer();">
					<%
						}
							}
					%>
				ͳһ���ã�
      		  	<input type="checkbox" name="setAllStatus" id="setAllStatus"><label for="setAllStatus">��������</label>
    </div>
					<jsp:include page="/newasset/assetsDisTranseEditLine.jsp" flush="true"/>


		</form>
<jsp:include page="/public/hintMessage.jsp" flush="true"/>
	</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js"
	id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm"
	scrolling="no" frameborder="0"
	style="visibility: visible; z-index: 999; position: absolute; left: -500px; top: 0;"></iframe>
<script type="text/javascript">
var srcToOrgValue = "";
var srcDeptValue = null;
var transferType = "";
if(mainFrm.toOrganizationId){
    srcToOrgValue = mainFrm.toOrganizationId.value;
}
if(mainFrm.fromDept){
	srcDeptValue = mainFrm.fromDept.value;
}
if(mainFrm.transferType){
	transferType = mainFrm.transferType.value;
}
var dataTable = document.getElementById("dataTable");

/**
  * ���ܣ�ѡ���ʲ�
 */
function do_SelectAssets() {
	var dialogWidth = 52;
	var dialogHeight = 29;

	<%
    	String lun="";
    	if ("Y".equalsIgnoreCase(userApp.getIsTd())) {
    		lun = AssetsLookUpConstant.LOOK_TRANSFER_ASSETS_TD;
    	} else {
    		lun = AssetsLookUpConstant.LOOK_TRANSFER_DIS_ASSETS;
    		//lun = AssetsLookUpConstant.LOOK_TRANSFER_ASSETS;
    	}
    %>
	var lookUpName = "<%=lun%>";
	var userPara = "transType=" + mainFrm.transType.value;
	
	var specialityDept = mainFrm.specialityDept.value;
	if (mainFrm.specialityDept.value == "") {
		alert("��ѡ��ʵ������ţ�Ȼ�����ѡ���ʲ�");
		return;
	}
	userPara += "&specialityDept="+specialityDept;
	
	var assets = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	if (assets) {
		var data = null;
		for (var i = 0; i < assets.length; i++) {
			data = assets[i];
            data["currentUnits"] = formatNum(data["currentUnits"], 0);
			appendDTO2Table(dataTable, data, false, "barcode");
		}
		do_ComputeSummary(); //�ϼ�
	}
}

function deleteLine() {
    var tab = document.getElementById("dataTable");
    deleteTableRow(tab, 'subCheck');
    do_ComputeSummary();
}


function do_isProfessionalGroup() {
	var fromGroup = mainFrm.fromGroup.value;
	if(fromGroup != null && fromGroup != ""){
		var url = "<%=AssetsURLList.ASSETS_TRANS_SERVLET%>";
		url += "?act=VALIDATE_ACTION";
		url += "&fromGroup=" + mainFrm.fromGroup.value;
		do_ProcessSimpleAjax(url, null);
	}
}

function do_ProcessResponse(responseContent){
    mainFrm.isGroupPID.value = responseContent;
}

function initPage() {
    window.focus();
    do_SetPageWidth();
    doLoad();
    do_ControlProcedureBtn();
	if(document.mainFrm.fromGroup.value=="0"||document.mainFrm.fromGroup.value==""){
	    document.mainFrm.fromGroup.value=document.getElementById("flow_group_id").value;
	    document.mainFrm.fromGroupName.value=document.getElementById("flow_group_name").value;
	    document.mainFrm.fromDept.value=document.getElementById("app_dept_code").value;
	    document.mainFrm.fromDeptName.value=document.getElementById("app_dept_name").value;
    }
	do_ComputeSummary();
    do_ProcessTableAlign();
}


function do_ComputeSummary(){
    var dataTable = document.getElementById("dataTable");
    var rows = dataTable.rows;
    if(rows != undefined){
        var rowCount = rows.length;
        var idArr = new Array("numValue", "yuanzhiValue", "ljzjValue", "ljjzalue", "jingeralue", "bfzbValue");
        var summaryCell = new Array("currentUnits", "cost", "sumDepreciation", "impairReserve", "deprnCost", "retirementCost");
        var idCount = idArr.length;
        var sumValueArr = new Array();
        for(var i = 0; i < rowCount; i++){
            var tr =  rows[i];
            for(var j = 0; j < idCount; j++){
                var node = getTrNode(tr, summaryCell[j]);
                if(!sumValueArr[j]){
                    sumValueArr[j] = 0;
                }
                sumValueArr[j] += Number(node.value);
            }
        }
        for(j = 0; j < idCount; j++){
            node = document.getElementById(idArr[j]);
            if(j == 0){
                node.value = sumValueArr[j];
            } else {
                node.value = formatNum(sumValueArr[j], 2);
            }
        }
    }
}

function do_SelectCreateGroup(){
	var sf_Group = document.getElementById('sf_group').value;
	var fromGroup = mainFrm.fromGroup.value;
	var sf_project = document.getElementById('sf_project').value;
	var sf_role = document.getElementById('sf_role').value;
	var url = "<%=AssetsURLList.GROUP_CHOOSE_SERVLET%>?fromGroup=" + fromGroup+"&sf_group="+sf_Group+"&sf_project="+sf_project+"&sf_role="+sf_role;
	var popscript = "dialogWidth:20;dialogHeight:10;center:yes;status:no;scrollbars:no;help:no";
	var group = window.showModalDialog(url, null, popscript);
	if(group){
		dto2Frm(group, "mainFrm");
		document.mainFrm.fromGroup.value=group.fromGroup;
		document.mainFrm.fromGroupName.value=group.fromGroupName;
		document.mainFrm.fromDept.value=group.deptCode;
		document.mainFrm.fromDeptName.value=group.deptName;
        do_isProfessionalGroup();
	}
}

function do_AppValidate() {
    var isValid = true;
    var transType = mainFrm.transType.value;
    var fieldLabels = "";
    var fieldNames = "";
    var validateType = "";
    if (dataTable.rows.length == 0 || (dataTable.rows[0].style.display == 'none' && dataTable.rows.length == 1)) {
        alert("û��ѡ�������ݣ���ѡ�������ݣ�");
        isValid = false;
    } else {
        fieldLabels = "�������;����˵��";
        fieldNames = "fromGroupName;createdReason";
        validateType = EMPTY_VALIDATE;
        isValid = formValidate(fieldNames, fieldLabels, validateType);
        if( isValid ){
            fieldLabels = "��������";
            fieldNames = "rejectType";
            isValid = formValidate(fieldNames, fieldLabels, validateType);
            if( isValid ){
                isValid = do_valiQuantity();
            }
        }
    }
    return isValid;
}


function do_excel() {
    if(transferType == "" || (transferType != "" && transferType != "<%=AssetsDictConstant.TRANS_BTW_COMP%>")){
		var fromDept = mainFrm.fromDept.value;
		if(fromDept == ""){
			alert("����ѡ���ţ���ѡ���ʲ���");
			mainFrm.fromDept.focus();
			return;
		}
	}
    if (mainFrm.specialityDept.value == "") {
		alert("��ѡ��ʵ������ţ�Ȼ����ܵ���");
		return;
	}
    
    var returnValue = do_ImportExcelData();
    if (returnValue) {
    	isSave=true;
        document.mainFrm.excel.value = returnValue;
        document.mainFrm.act.value = "excel";
        document.mainFrm.submit();
    }
}

function do_CloseDiv() {
    document.getElementById("transferDiv").style.visibility = "hidden";
     do_SetPageWidth();
}
function do_Transfer() {
    var width = screen.width-10;
    var height = screen.height-60;   
    window.open("/newasset/assetsDisTransImportErrorInfo.jsp","","left=0,top=0,width="+width+",height="+height+",title=yes,scrollbars=yes,resizable=no,location=no,toolbar=no, menubar=no");   
}

function do_setQuantity() {
    var length = document.getElementsByName("retirementCost").length;
    for (i = 0; i < length; i++) {
        var retirementCost = document.getElementsByName("retirementCost")[i].value;
        var deprnCost = document.getElementsByName("deprnCost")[i].value;
        //alert("retirementCost"+retirementCost);
        //alert("deprnCost"+deprnCost);
        if (retirementCost < 0) {
            alert("���ϳɱ�����>0");
            document.getElementsByName("retirementCost")[i].value = deprnCost;
            break;
        } else if (parseFloat(retirementCost) > parseFloat(deprnCost)) {
            alert("���ϳɱ�����<=�ʲ���ֵ");
            document.getElementsByName("retirementCost")[i].value = deprnCost;
            break;
        }
    }
    do_ComputeSummary(); //�ϼ�
}

function do_valiQuantity(){
    var isVilidate = true;
    var rows = dataTable.rows;
    var rowCount = rows.length;
    for (var i =0; i<rowCount; i++) {
        var row = rows[i];
        var costNode = getTrNode(row, "cost");
        var retirementCostNode = getTrNode(row, "retirementCost");
        var cost = costNode.value;
        var retirementCost = retirementCostNode.value;
        if (retirementCost < 0) {
            alert("���ϳɱ�����>0");
            retirementCostNode.value = "";
            retirementCostNode.focus();
            isVilidate = false;
            break;
        } else if (Number(retirementCost) > Number(cost)) {
            alert("���ϳɱ�����<=�ʲ�ԭֵ");
            retirementCostNode.value = "";
            retirementCostNode.focus();
            isVilidate = false;
            break;
        }
    }
    return isVilidate;
}

function do_SetCheckCategory(lineBox){
	if(!mainFrm.setAllStatus.checked){
		return;
	}
	var objs = document.getElementsByName("rejectType");
	var dataCount = objs.length;
	var selectedVal = lineBox.value;
	if(objs && objs.length){		
	
		var chkObj = null;
		var dataTable = document.getElementById("dataTable");
		var rows = dataTable.rows;
		var row = null;
		var checkObj = null;
		var checkedCount = getCheckedBoxCount("subCheck");
		for(var i = 0; i < dataCount; i++){
			if(checkedCount > 0){
				row = rows[i];
				checkObj = row.childNodes[0].childNodes[0];
				if(!checkObj.checked){
					continue;
				}
			}
			chkObj = objs[i];
			selectSpecialOptionByItem(chkObj, selectedVal);
		}
	}
}   

function setAttachmentConfig(){
    var attachmentConfig = new AttachmentConfig();
    attachmentConfig.setOrderPkName("transId");
    return attachmentConfig;
}

</script>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>