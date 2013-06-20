<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsDictConstant"%>
<%@ page import="com.sino.ams.newasset.constant.AssetsLookUpConstant"%>
<%@ page import="com.sino.ams.system.user.dto.SfUserRightDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
    //String status = headerDTO.getTransStatus();
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	String transId = headerDTO.getTransId();
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    boolean isDepAM = userAccount.isDptAssetsManager();
    DTOSet userDTO = userAccount.getUserRight();
    String roleName = "";
    Map  userRightMap = new HashMap();
    for (int i = 0;i<userDTO.getSize();i++) {
        SfUserRightDTO userRight = (SfUserRightDTO)userDTO.getDTO(i);
        roleName = userRight.getRoleName();
        userRightMap.put(roleName,roleName);
    }
    boolean isDptMgr = userRightMap.containsValue("���ž���");
    int orgId = userAccount.getOrganizationId();
	int userId = userAccount.getUserId();
	String provinceCode = headerDTO.getServletConfig().getProvinceCode();
    String isGroupPID = request.getAttribute(AssetsWebAttributes.IS_GROUP_PID).toString();//�Ƿ��й�˾�ۺϲ��������
    String isFinanceGroup = request.getAttribute(AssetsWebAttributes.IS_FINANCE_GROUP).toString();
    //EXCEL���
    boolean isMtlMana = userAccount.isMtlAssetsManager();
    DTOSet lineSetPri2 = (DTOSet) request.getAttribute(AssetsWebAttributes.PRIVI_DATA);//EXCEL����ʱ���벻�ɹ���DTOSET
    String isSpecialityDept = request.getAttribute("isSpecialityDept").toString();
    String isTdProvinceUser = request.getAttribute("isTdProvinceUser").toString();//Tdʡ��˾�û�
    String transStatus = headerDTO.getTransStatus();
    boolean needBtnDiv = (((!"IN_PROCESS".equals(transStatus))) || (lineSetPri2 != null && !lineSetPri2.isEmpty()));
    int provinceOrgId = SessionUtil.getServletConfigDTO(session).getProvinceOrgId();
    int tdProvinceOrgId = SessionUtil.getServletConfigDTO(request).getTdProvinceOrgId();

%>
<head>
	<% 	
    	String titleText="";
    	String titleBar="";
    	if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
    		titleText=headerDTO.getTransTypeValue()+"(TD)";
    		titleBar=headerDTO.getTransTypeValue()+"(TD)";
    	} else {
    		titleText=headerDTO.getTransTypeValue();
    		titleBar=headerDTO.getTransTypeValue();
    	}
    %>
	<title><%=titleText%></title>
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
    <script type="text/javascript" src="/WebLibary/js/AjaxProcess.js"></script>
</head>
<script type="text/javascript">
    ArrActions[16][0] = <%=!transId.equals("")%>;
    printToolBar();
</script>
<%@ include file="/flow/flowNoButton.jsp"%>
<body leftmargin="0" topmargin="0" onload="initPage();" onbeforeunload="doBeforeUnload()" onunload="doUnLoad()">
<form action="<%=AssetsURLList.ASSETS_DEVALUE_SERVLET%>" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<%@ include file="/flow/flowPara.jsp" %>
<div id="searchDiv" style="position:absolute;top:25px;left:1px;width:100%">
<jsp:include page="/newasset/devalue/devalueAssetsHeader.jsp" flush="true"/>
</div>
<input type="hidden" name="fromGroup" value="<%=headerDTO.getFromGroup()%>">
<input type="hidden" name="fromOrganizationId" value="<%=headerDTO.getFromOrganizationId()%>">
<input type="hidden" name="transType" value="<%=transType%>">
<input type="hidden" name="transferType" value="<%=headerDTO.getTransferType()%>">
<input type="hidden" name="created" value="<%=headerDTO.getCreated()%>">
<input type="hidden" name="createdBy" value="<%=headerDTO.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=transId%>">
<input type="hidden" name="isGroupPID" value="<%=isGroupPID%>">
<input type="hidden" name="procdureName" value="<%=headerDTO.getProcdureNameIncludeTd(userAccount.getIsTd())%>">

<input type="hidden" name="toDept" value="<%=headerDTO.getToDept()%>">
<input type="hidden" name="toGroup" value="<%=headerDTO.getToGroup()%>">
<input type="hidden" name="act" value="">
<input type="hidden" name="isThred" value="">
<input type="hidden" name="isTd" value="<%=userAccount.getIsTd() %>">
<input type="hidden" name="isTdProvinceUser" value="<%=isTdProvinceUser%>">
<input type="hidden" name="isProvinceUser" id="isProvinceUser" value="<%=userAccount.isProvinceUser()%>">
<input type="hidden" name="isSpecialityDept" id="isSpecialityDept" value="<%=isSpecialityDept %>"> <!-- �Ƿ���ʵ�ﲿ�� -->
<input type="hidden" name="provinceOrgId" id="provinceOrgId" value="<%=provinceOrgId%>">
<input type="hidden" name="excel" value="">
<%
    if(needBtnDiv){
%>
<div id="buttonDiv" style="position:absolute;top:195px;left:1px;width:100%">
<%
    }
    if (!"IN_PROCESS".equals(transStatus)) {
%>
        <img src="/images/eam_images/choose.jpg" alt="ѡ���ʲ�" onClick="do_SelectAssets(); return false;">
        <img src="/images/eam_images/delete_line.jpg" alt="ɾ����" onClick="deleteLine(); return false;">
<%
    }
%>
        <span id="warn"></span>
<%
    if (lineSetPri2 != null && !lineSetPri2.isEmpty()) {
%>
        <img src="/images/eam_images/detail_info.jpg" alt="�鿴�ʲ���ǩ��δ����ɹ���ϸ��Ϣ"  onClick="do_Transfer();">
<%
    }
    if(needBtnDiv){
%>
</div>
<%
    }
%>
<jsp:include page="/newasset/devalue/devalueAssetsLine.jsp" flush="true"/>

</form>
<jsp:include page="/public/hintMessage.jsp" flush="true"/>
<div id="ddDiv" style="position: absolute; z-index: 2; top: 130px; left: 350px;background-color:#C0C0C0; border: 1px dotted red; width: 150px; height: 30px; text-align: center; visibility: hidden;">
		<form name="mainFrm2" action="<%=AssetsURLList.ASSETS_DEVALUE_SERVLET%>" method="post" enctype="multipart/form-data">
		   <table border = "0" width="100%">
		   <tr onmousedown="mmd.catchDiv(this)" onmouseup="mmd.releaseDiv(this)" style="cursor:move;background:#A0D4F7;color:white;font:bold;height:20">
	            <td>&nbsp;&nbsp;<span key="PleaseSelectFunction"/></td>
	    	    <td align="right"><div style="padding-right:10px"><font face="webdings" style="cursor:hand" onclick="do_excel();">r</font></div></td>
	        </tr>
		       <tr>
		           <td width="80%" nowrap="nowrap" valign="middle">
		           	<input class="input_style1" type="file" name="flName">
		           <img src="/images/eam_images/imp_from_excel.jpg" alt="�ύExcel" onClick="doSub(); return false;" align="center">&nbsp;&nbsp;&nbsp;
					 <a href="/document/template/tag.zip">
		              		<img style="border:none" src="/images/eam_images/download_template.jpg" alt="ģ������" align="center">
		           		</a>

		           </td>
		       </tr>
		   </table>
		</form>
	</div>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
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
    	String lun = AssetsLookUpConstant.LOOK_ASSETS_DEVALUE;
    %>
	var lookUpName = "<%=lun%>";
	var userPara = "transType=" + mainFrm.transType.value;
	var specialityDept = mainFrm.specialityDept.value;

    if(transferType != ""){
		userPara += "&transferType=" + transferType;
	}
    if(transferType == "" || (transferType != "" && transferType != "<%=AssetsDictConstant.TRANS_BTW_COMP%>")){
		var fromDept = mainFrm.fromDept.value;
		if(fromDept == ""){
			alert("����ѡ���ţ���ѡ���ʲ���");
			mainFrm.fromDept.focus();
			return;
		}
		userPara += "&deptCode=" + mainFrm.fromDept.value;
	}
	
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
			appendDTO2Table(dataTable, data, false, "barcode");
		}
	}
}

function deleteLine() {
    var tab = document.getElementById("dataTable");
    deleteTableRow(tab, 'subCheck');
//    if(deleteTableRow(dataTable, 'subCheck')){
//		do_SaveOrder();
//	}
}

//ճ��EXCEL        
var xmlHttp;
var segment10Array = new Array();
var projectNameArray = new Array();
var segment10Index = -1;
var projectNameIndex = -1;
var mark = -1;


function do_isProfessionalGroup() {
	var fromGroup = mainFrm.fromGroup.value;
	if(fromGroup != null && fromGroup != ""){
		var url = "<%=AssetsURLList.ASSETS_TRANS_SERVLET%>";
		url += "?act=VALIDATE_ACTION"
		url += "&fromGroup=" + mainFrm.fromGroup.value;
		do_ProcessSimpleAjax(url, null);
	}
}

function do_ProcessResponse(responseContent){
    mainFrm.isGroupPID.value = responseContent;
}

function doInitArray() {
    segment10Array = new Array();
    projectNameArray = new Array();
    projectNameIndex = -1;
    var segment10 = document.getElementsByName("segment10");
    for (var i = 2; i < segment10.length; i++) {
        segment10Array[i - 2] = segment10[i].value;
    }
    var projectName = document.getElementsByName("projectName");
    for (var i = 2; i < projectName.length; i++) {
        if (!isEmpty(projectName[i].value)) {
            projectNameIndex++;
            projectNameArray[projectNameIndex] = projectName[i].value;
        }
    }
}

function do_SaveOrder() {
    mainFrm.act.value = "<%=AssetsActionConstant.SAVE_ACTION%>";
    mainFrm.submit();
	document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}


function submitH() {
    mainFrm.act.value = "<%=AssetsActionConstant.SUBMIT_ACTION%>";
    mainFrm.submit();
}


function initPage() {
    window.focus();
    do_SetPageWidth();
	var transStatus2 = "<%= transStatus %>";
    doLoad();
	do_ControlProcedureBtn();
    if(document.mainFrm.fromGroup.value=="0"){
	    document.mainFrm.fromGroup.value=document.getElementById("flow_group_id").value;
	    document.mainFrm.fromGroupName.value=document.getElementById("flow_group_name").value;
	    document.mainFrm.fromDept.value=document.getElementById("app_dept_code").value;
	    document.mainFrm.fromDeptName.value=document.getElementById("app_dept_name").value;
    }
}


function do_SelectCreateGroup(){
	var fromGroup = mainFrm.fromGroup.value;
	var url = "<%=AssetsURLList.GROUP_CHOOSE_SERVLET%>?fromGroup=" + fromGroup;
	var popscript = "dialogWidth:20;dialogHeight:10;center:yes;status:no;scrollbars:no;help:no";
	var group = window.showModalDialog(url, null, popscript);
	if(group){
		dto2Frm(group, "mainFrm");
        do_isProfessionalGroup();
	}
}

<%--var fileType;--%>
<%--var transId;--%>
<%--var transStatus="<%=transStatus%>";--%>
<%--var dType = "<%=AssetsDictConstant.DOC_TYPE%>";--%>
<%--var transType;--%>
<%--function do_upload() {--%>
    <%--transId = document.mainFrm.transId.value;--%>
    <%--fileType = "<%=AssetsDictConstant.FILE_TYPE_OTHERS%>";--%>
    <%--if (transId == '') {--%>
        <%--requestAjax("GET_HEADER_ID", getId, null, null);--%>
    <%--} else {--%>
<%--//        openFilePage();--%>
        <%--editAttach(document.mainFrm.transId,'AMS_INFO_PUBLISH','AMS_INFO_PUBLISH_S') ;--%>
    <%--}--%>
<%--}--%>
<%--function getId() {--%>
    <%--if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {--%>
        <%--var ret = getRet(xmlHttp);--%>
        <%--if (ret != 'ERROR' && ret != '') {--%>
            <%--transId = ret;--%>
            <%--document.mainFrm.transId.value = ret;--%>
<%--//            openFilePage();--%>
            <%--editAttach(document.mainFrm.transId,'AMS_INFO_PUBLISH','AMS_INFO_PUBLISH_S') ;--%>
        <%--}--%>
    <%--}--%>
<%--}--%>
<%--function openFilePage() {--%>
    <%--transType=mainFrm.transType.value;--%>
    <%--var url = "/servlet/com.sino.ams.newasset.servlet.FileServlet?transId=" +--%>
              <%--transId + "&fileType=" + fileType + "&docType=" + dType+"&transType="+transType+"&transStatus="+transStatus;--%>
    <%--var style = "width=620px,height=380,top=100,left=100,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=no";--%>
    <%--window.open(url, "pickMtlWin", style);--%>
<%--}--%>

/**
 * ���ܣ�������������š�������ʱ�������ò�����
 */
function do_ConfirmChange(){
	var rows = dataTable.rows;
	if(rows){
		var rowCount = rows.length;
		var tr = rows[0];
		if(!(rows.length == 0 || (rows.length == 1 && tr.style.display == "none"))){
			if(confirm("�ı䲿�Ž�����Ѿ�ѡ������ݣ��Ƿ����������������ȷ������ť������������ȡ������ť��")){
				deleteRow(dataTable);
				srcDeptValue = mainFrm.fromDept.value;
				setCheckBoxState("mainCheck", false);
			} else {
				selectSpecialOptionByItem(mainFrm.fromDept, srcDeptValue);
			}
		}
	}
}

function validate() {
    var isValid = true;
    var transType = mainFrm.transType.value;
    if (dataTable.rows.length == 0 || (dataTable.rows[0].style.display == 'none' && dataTable.rows.length == 1)) {
        alert("û��ѡ�������ݣ���ѡ�������ݣ�");
        isValid = false;
    } else {
        var fieldLabels = "�������;����˵��";
        var fieldNames = "fromGroupName;createdReason";
        var validateType = EMPTY_VALIDATE;
        if (transferType) {
            if (transferType != "<%=AssetsDictConstant.TRANS_INN_DEPT%>") {
                fieldLabels += ";���벿��";
                fieldNames += ";responsibilityDeptName";
            } else {
                fieldLabels += ";����ص�;��������";
                fieldNames += ";assignedToLocationName;responsibilityUserName";
            }
        } else {
            if (transType == "<%=AssetsDictConstant.ASS_SUB%>") {
                fieldLabels = "�������;�������";
                fieldNames = "lossesName;lossesDate";
            }
        }
    }
    isValid = formValidate(fieldNames, fieldLabels, validateType);
    return isValid;
}

function do_ShowDetail(td){
	var transType = mainFrm.transType.value;
	tr = td.parentNode;
	cells = tr.cells;
	var cell = cells[1];
	if(transType == "<%=AssetsDictConstant.ASS_DEVALUE%>"){
		cell = cells[2];
	}
	var barcode = cell.childNodes[0].value;
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=800,height=360,left=70,top=100";
	window.open(url, winName, style);
}

function do_CancelOrder() {
	if(confirm("����׼�����������ݣ�ȷ���𣿼���������ȷ������ť������������ȡ������ť!")){
		mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
		mainFrm.submit();
	}
}

function do_ClearLineData(){
	var rows = dataTable.rows;
	if(rows){
		var tr = rows[0];
		if(!(rows.length == 0 || (rows.length == 1 && tr.style.display == "none"))){
			var fieldNames = "responsibilityDeptName;assignedToLocationName;responsibilityUserName;depreciationAccount;faCategoryCode;assignedToLocation;responsibilityUser;responsibilityDept;addressId";
			if(!isAllEmapty(fieldNames)){
				if(confirm("�ı���빫˾����������벿�š���������ص㡱�����������ˡ��������۾��˻�����������𡱵����ݣ��Ƿ����������������ȷ������ť������������ȡ������ť��")){
					clearFieldValue(fieldNames);
				} else {
					selectSpecialOptionByItem(mainFrm.toOrganizationId, srcToOrgValue);
				}
			}
		}
	}
	srcToOrgValue = mainFrm.toOrganizationId.value;
}

var al=0;
function do_exportToExcel() {
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}

var contentCode = "";//mainFrm.faContentCode.value;
function do_ChangeContentCode() {
    var rows = dataTable.rows;
    var rowCount = rows.length;
    if (rowCount > 1 || (rowCount == 1 && rows[0].style.display != "none")) {
        if (confirm("�ı��ʲ����ཫɾ���Ѿ�ѡ����ʲ����ݣ��Ƿ����������������ȷ������ť������������ȡ������ť")) {
            deleteRow(dataTable);
            contentCode = mainFrm.fromDept.value;
            setCheckBoxState("mainCheck", false);
			do_SaveOrder();
        } else {
            selectSpecialOptionByItem("", contentCode);
        }
    }
}

function do_excel() {
    var _d = document.getElementById("ddDiv");

    if (_d.style.visibility == "hidden") {
        _d.style.visibility = "visible";
    } else {
        _d.style.visibility = "hidden";
    }
}

function do_CloseDiv() {
    document.getElementById("transferDiv").style.visibility = "hidden";
}
function do_Transfer() {
    document.getElementById("transferDiv").style.visibility = "visible";
}

function do_setQuantity() {
    var length = document.getElementsByName("retirementCost").length;
    for (i = 0; i < length; i++) {
        var retirementCost = document.getElementById("retirementCost" + i).value;
        var cost = document.getElementById("cost" + i).value;
        if (retirementCost < 0) {
            alert("���ϳɱ�����>0");
            document.getElementById("retirementCost" + i).value = "";
            break;
        } else if (retirementCost > cost) {
            alert("���ϳɱ�����<=�ʲ�ԭֵ");
            document.getElementById("retirementCost" + i).value = "";
            break;
        }
    }
}


function do_ThredDept(fDept, tDept){
    createXMLHttpRequest();
    url = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsTransHeaderServlet?act=DO_THRED_DEPT&fDept=" + fDept + "&tDept=" + tDept;
    xmlHttp.onreadystatechange = handleReadyStateChange1;
    xmlHttp.open("post", url, true);
    xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlHttp.send(null);
}
function createXMLHttpRequest() {
    try {
        xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
    } catch(e) {
        try {
            xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
        } catch(e) {
            try {
                xmlHttp = new XMLHttpRequest();
            } catch(e) {
                alert("����XMLHttpRequest����ʧ�ܣ�");
            }
        }
    }
}
function handleReadyStateChange1() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            document.mainFrm.isThred.value = resText;
        } else {
            alert(xmlHttp.status);
        }
    }
}

	 function doSub() {
        if (document.mainFrm2.flName.value !== "") {
        	mainFrm2.action ="<%=AssetsURLList.ASSETS_DEVALUE_SERVLET%>?act=<%=AMSActionConstant.EXCEL_IMP%>";
            mainFrm2.submit();
//            mmd.closeDiv();
//            document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
        } else {
            alert("�������ļ���");
        }
    }
function do_Save_app () {
    mainFrm.act.value = "<%=AssetsActionConstant.SAVE_ACTION%>";
    mainFrm.submit();
    document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}

function do_Complete_app_yy() {
    if(transferType != ""){
		if(transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>"){
			mainFrm.toDept.value = mainFrm.fromDept.value;
		} else {
			var depts = document.getElementsByName("responsibilityDept");
			mainFrm.toDept.value = depts[0].value;
		}
	}
    mainFrm.act.value = "<%=AssetsActionConstant.SUBMIT_ACTION%>";
    mainFrm.submit();
    document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}


function do_ProcessProSpecialGroup(){
    var actionURL = "/servlet/com.sino.ams.newasset.servlet.ProcedureGroupSelectServlet";
    var resHandler = do_SetProSpecialGroup;
    var ajaxProcessor = new AjaxProcessor(actionURL, resHandler, false);
    ajaxProcessor.performTask();
}

function do_SetProSpecialGroup(resText){
    Launch_HandleGroup = resText;
}

function do_AppValidate() {
	var isValid = false;
	var fieldLabels = "�����ֵ";
	var fieldNames = "prepareDevalue";
	var validateType = EMPTY_VALIDATE;
    if (dataTable.rows.length == 0 || (dataTable.rows[0].style.display == 'none' && dataTable.rows.length == 1)) {
        alert("��ѡ�����ֵ��������");
        return false;
    } else {
    	isValid = formValidate(fieldNames, fieldLabels, validateType);
    	if( isValid ){
        	isValid = do_valiQuantity();
        }
    }
    return isValid;
}

function do_valiQuantity(){
    var isVilidate = true;
    var rows = dataTable.rows;
    var rowCount = rows.length;
    var j = 0;
    for (var i = 0; i < rowCount; i++) {
    	j = i + 1;
        var deprnCost = document.getElementById("dataTable").rows[i].cells[9].childNodes[0].value;
        var prepareDevalue = document.getElementById("dataTable").rows[i].cells[10].childNodes[0].value;
        if (prepareDevalue <= 0) {
            alert("��" + j + "�У������ֵ Ӧ ���� 0");
            document.getElementById("dataTable").rows[i].cells[10].childNodes[0].focus();
            isVilidate = false;
        } else if (parseFloat(prepareDevalue) > parseFloat(deprnCost)) {
            alert("��" + j + "�У������ֵ Ӧ С�ڻ���� ��ֵ");
            document.getElementById("dataTable").rows[i].cells[10].childNodes[0].focus();
            isVilidate = false;
        }
    }
    return isVilidate;
}

function setAttachmentConfig(){
    var attachmentConfig = new AttachmentConfig();
    attachmentConfig.setOrderPkName("transId");
    return attachmentConfig;
}
</script>