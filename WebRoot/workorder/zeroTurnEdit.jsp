<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page import="com.sino.ams.workorder.dto.ZeroTurnHeaderDTO"%>
<%@ page import="com.sino.ams.workorder.dto.ZeroTurnLineDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>

<html>
<%
	ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) request.getAttribute(AssetsWebAttributes.ZERO_TURN_DATA);
    DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	String transId = headerDTO.getTransId();
	String transStatus=headerDTO.getTransStatus();
%>
<head>
	<title>�㹺ת��ά��</title>
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
    <script type="text/javascript" src="/WebLibary/js/AjaxProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/DefaultLocationProcess.js"></script>
</head>
<script type="text/javascript">
    printToolBar();
</script>
<%@ include file="/flow/flowNoButton.jsp"%>
<body leftmargin="0" topmargin="0" onload="initPage();helpInit('3.M.1')" onbeforeunload="doBeforeUnload()" onunload="doUnLoad()">
<input type="hidden" name="helpId" value="">
<form action="/servlet/com.sino.ams.workorder.servlet.ZeroTurnServlet" method="post" name="mainFrm">
<%@ include file="/flow/flowPara.jsp" %>
<jsp:include page="/message/MessageProcess"/>
<div id="searchDiv" style="position:absolute;top:29px;left:1px;width:100%">

<table border="0" width="100%" style="border-collapse: collapse;top:0px;" id="table1">
	<tr>
		<td>
			<table width=100% border="0" align="center">
			    <tr align="center">
			        <td align=right width="6%" height="18">���ݱ�ţ�</td>
			        <td width="15%" height="18">
			        	<input type="text" name="transNo" class="input_style2" readonly style="width:100%" 	value="<%=headerDTO.getTransNo() %>" size="20"></td>
			        <td align=right width="6%" height="18">�������ͣ�</td>
			        <td width="20%" height="18">
			          <input type="text" name="transType" class="input_style2" readonly style="width:100%"  value="<%=headerDTO.getTransType()%>" size="20">
			        <td align=right width="6%" height="18">�����̶ȣ�</td>
			        <td width="15%" height="18">
				        <select name="emergentLevel" class="select_style1" style="width:100%"  onchange="document.getElementById('sf_priority').value = this.value;"><%=headerDTO.getEmergentLevelOption()%></select>
				        <input type="text" name="groupName"  class="input_style2" readonly style="width:100%;cursor:pointer;display:none;"  value="<%=headerDTO.getDeptName()%>"  onClick="do_SelectAppGroup()" size="20"></td>
			    </tr>
			    <tr>
			        <td align=right width="6%" height="18">��˾���ƣ�</td>
			        <td width="15%" height="18">
			        	<input type="text" name="organizationName" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getOrganizationName() %>" size="20"></td>
                    <td align=right width="10%" height="18">�������ƣ�</td>
			        <td width="20%" height="18">
			         <input type="text" name="deptName" class="input_style2" readonly style="width:100%;cursor:pointer"  value="<%=headerDTO.getDeptName() %>" size="20"></td>
                    <td align=right width="10%" height="18">�������ڣ�</td>
                    <td width="15%" height="18">
			         <input type="text" name="creationDate" class="input_style2" readonly style="width:100%;cursor:pointer" value="<%=headerDTO.getCreationDate()%>"  size="20"></td>
			    </tr>
			    <tr>
			    	<td align=right width="6%" height="18">�����ˣ�</td>
			        <td width="15%" height="18">
					   <input type="text" name="createdByName" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getCreatedByName() %>">
					</td>
                    <td align=right width="10%" height="40">����ԭ��</td>
			        <td width="40%" height="40" colspan="3">
				        <textarea name="createdReason" style="width:100%; height:100%"><%=headerDTO.getCreatedReason()%></textarea>
					</td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
</div>
<input type="hidden" name="groupId" value="<%=headerDTO.getGroupId()%>">
<input type="hidden" name="deptCode" value="<%=headerDTO.getDeptCode()%>">
<input type="hidden" name="act" value="">
<input type="hidden" name="createdBy" value="<%=headerDTO.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=transId%>">
<input type="hidden" name="excel" value="">
<input type="hidden" name="excelPath"  value="" >
<input type="hidden" name="orderNo"  value="<%=headerDTO.getOrderNo()%>" >
<input type="hidden" name="organizationId"  value="<%=headerDTO.getOrganizationId()%>" >


<div id="buttonDiv" style="position:absolute;top:100px;width:100%;height:30px">
         <img src="/images/eam_images/choose.jpg" alt="ѡ���ʲ�" onClick="do_SelectAssets(); return false;">
          <img src="/images/eam_images/delete_line.jpg" alt="ɾ��" onClick="do_DeleteLines(); return false;">
</div>


				  
 <div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:25px;left:0px;width:100%;">
    <table class=headerTable border=1 style="width:250%">
       <tr height=23px style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
            <td align=center width="4%">��ǩ��</td>
            <td align=center width="4%">�ɹ�����</td>
            <td align=center width="5%">Ԥ�Ƶ�������</td>
            <td align=center width="4%">�ʲ�Ŀ¼</td>
            <td align=center width="4%">�ʲ�Ŀ¼����</td>
            <td align=center width="4%">�ʲ�����</td>
            <td align=center width="4%">����ͺ�</td>
            <td align=center width="4%">����</td>
            <td align=center width="4%">������λ</td>
            <td align=center width="4%">����</td>
            <td align=center width="4%">�ص���</td>
            <td align=center width="4%">�ص�����</td>
            <td align=center width="4%">���β���</td>
            <td align=center width="4%">������</td>
            <td align=center width="4%">רҵ����</td>
            <td align=center width="4%">���</td>
           <!--  <td align=center width="4%">�ɹ�����</td> -->
            <td align=center width="4%">������</td>
            <td align=center width="4%">��������</td>
            <td align=center width="4%">����״̬</td>
            <td align=center width="4%">��ע</td>
            <td align=center width="4%">�ɱ�����</td>
            <td align=center width="4%">��ǩ��(��¼)</td>
            <td align=center width="4%">��˾����(��¼)</td>
        </tr>
    </table>
</div>

   <div id="dataDiv" style="overflow:scroll;height:70%;width:100%;" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">

        <table id="dataTable" width="250%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
   
    <%
		if (lineSet == null || lineSet.isEmpty()) {
	%>
             <tr class="dataTR" style="display:none">
             <td align="center" width="3%"><input type="checkbox" name="subCheck" id="subCheck0"></td>
             <td width="4%"><input type="text" readonly class="finput" name="barcode"    id="barcode0" readonly></td> 
             <td width="4%"><input type="text" readonly class="finput" name="procureCode"    id="procureCode0" readonly></td> 
             <td width="5%"><input type="text" readonly class="finput" name="expectedDate"    id="expectedDate0" readonly></td> 
			 <td width="4%" ><input type="text" name="contentCode" id="contentCode0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="contentName" id="contentName0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="itemSpec" id="itemSpec0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="itemQty" id="itemQty0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="unitOfMeasure" id="unitOfMeasure0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="manufacturerName" id="manufacturerName0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="objectNo" id="objectNo0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="workorderObjectName" id="workorderObjectName0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="responsibilityDept" id="responsibilityDept0" class="finput" readonly </td>
				<td width="4%" >
				  <input type="text" name="responsibilityUser" id="responsibilityUser0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="specialityDept" id="specialityDept0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="price" id="price0" class="finput" readonly ></td>
				<!-- <td width="4%" >
				  <input type="text" name="procureCode" id="procureCode0" class="finput" readonly ></td>
				   -->
				<td width="4%" >
				  <input type="text" name="createdBy" id="createdBy0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="createdDate" id="createdDate0" class="finput" readonly ></td>
				<td width="4%" >
				  <input type="text" name="transStatusVal" id="transStatusVal0" class="finput" readonly ></td>
				   <input type="hidden" name="transStatus" >
				   <input type="hidden" name="unitManager">
				   <input type="hidden" name="isShare" >
				   <input type="hidden" name="isBulid" >
				   <input type="hidden" name="lneId"  >
				   <input type="hidden" name="opeId"  >
				   <input type="hidden" name="cexId"  >
				   <input type="hidden" name="nleId" >
				   <input type="hidden" name="manufacturerId" >
				   <input type="hidden" name="addressId" >
				   <input type="hidden" name="itemCode" >
				 <td width="4%" >
				  <input type="text" name="remark" id="remark0" class="finput" readonly ></td>
				  <td width="4%" >
				  <input type="text" name="costCenterCode" id="costCenterCode0" class="finput" readonly ></td>
				  <td width="4%" >
				  <input type="text" name="record" id="record0" class="finput" readonly ></td>
				  <td width="4%" >
				  <input type="text" name="companyCode" id="companyCode0" class="finput" readonly ></td>
            </tr>
           </tr>
<%
 	  } else {
 		 ZeroTurnLineDTO lineDTO = null;
		  for (int i = 0; i < lineSet.getSize(); i++) {
			lineDTO = (ZeroTurnLineDTO) lineSet.getDTO(i);
			String barcode=lineDTO.getBarcode();
			String recod=lineDTO.getRecord();
			if(barcode.equals("")){
				barcode=recod;
				lineDTO.setBarcode(barcode);
			}
%>
            <tr class="dataTR" style="cursor:pointer">
				<!-- <input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=lineDTO.getLineId() %>"> -->
				<td align="center" width="3%"><input type="checkbox" name="subCheck" id="subCheck<%=i%>" value=""></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=lineDTO.getBarcode()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="procureCode" id="procureCode<%=i%>" class="finput" readonly value="<%=lineDTO.getProcureCode()%>"></td>
				<td width="5%" align="center" style="cursor:pointer" >
				  <input type="text" name="expectedDate" id="expectedDate<%=i%>" class="finput" readonly value="<%=lineDTO.getExpectedDate()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="contentCode" id="contentCode<%=i%>" class="finput" readonly value="<%=lineDTO.getContentCode()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="contentName" id="contentName<%=i%>" class="finput" readonly value="<%=lineDTO.getContentName()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="itemSpec" id="itemSpec<%=i%>" class="finput" readonly value="<%=lineDTO.getItemSpec()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="itemQty" id="itemQty<%=i%>" class="finput" readonly value="<%=lineDTO.getItemQty()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="manufacturerName" id="manufacturerName<%=i%>" class="finput" readonly value="<%=lineDTO.getManufacturerName()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="objectNo" id="objectNo<%=i%>" class="finput" readonly value="<%=lineDTO.getObjectNo()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="workorderObjectName" id="workorderObjectName<%=i%>" class="finput" readonly value="<%=lineDTO.getWorkorderObjectName()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="responsibilityDept" id="responsibilityDept<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityDept()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="responsibilityUser" id="responsibilityUser<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUser()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="specialityDept" id="specialityDept<%=i%>" class="finput" readonly value="<%=lineDTO.getSpecialityDept()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="price" id="price<%=i%>" class="finput" readonly value="<%=lineDTO.getPrice()%>"></td>
				<!-- <td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="procureCode" id="procureCode<%=i%>" class="finput" readonly value="<%=lineDTO.getProcureCode()%>"></td>
				 -->
				 <td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="createdBy" id="createdBy<%=i%>" class="finput" readonly value="<%=headerDTO.getCreatedByName()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="createdDate" id="createdDate<%=i%>" class="finput" readonly value="<%=lineDTO.getCreateDate()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="transStatusVal" id="transStatusVal<%=i%>" class="finput" readonly value="<%=lineDTO.getTransStatusVal()%>"></td>
				   <input type="hidden" name="transStatus" value="<%=lineDTO.getTransStatus()%>">
				  <input type="hidden" name="unitManager" value="<%=lineDTO.getUnitManager()%>">
				  <input type="hidden" name="isShare" value="<%=lineDTO.getIsShare()%>">
				  <input type="hidden" name="isBulid" value="<%=lineDTO.getIsBulid()%>">
				  <input type="hidden" name="lneId"  value="<%=lineDTO.getLneId()%>">
				  <input type="hidden" name="opeId"  value="<%=lineDTO.getOpeId()%>">
				  <input type="hidden" name="cexId"  value="<%=lineDTO.getCexId()%>">
				  <input type="hidden" name="nleId" value="<%=lineDTO.getNleId()%>">
				  <input type="hidden" name="manufacturerId" value="<%=lineDTO.getManufacturerId()%>">
				  <input type="hidden" name="addressId" value="<%=lineDTO.getAddressId()%>">
				  <input type="hidden" name="itemCode" value="<%=lineDTO.getItemCode()%>">
				  <input type="hidden" name="bqcode" value="<%=lineDTO.getBarcode()%>">
				 <td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
				 <td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="costCenterCode" id="costCenterCode<%=i%>" class="finput" readonly value="<%=lineDTO.getCostCenterCode()%>"></td>
				 <td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="record" id="record<%=i%>" class="finput" readonly value="<%=lineDTO.getRecord()%>"></td>
				 <td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="companyCode" id="companyCode<%=i%>" class="finput" readonly value="<%=lineDTO.getCompanyCode()%>"></td>
            </tr>
   <%
			}
		}
   %>
     </table>
    </div>
	
</form>
<jsp:include page="/public/hintMessage.jsp" flush="true"/>
<div id="hiddenDiv" style="position: absolute; overflow:scroll;width:550px;height:180px; display:none;background-color:#C6EBF4;color:red"></div>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<script type="text/javascript">

function initPage() {
    window.focus();
    do_SetPageWidth();
    doLoad();
    do_ControlProcedureBtn();
    if (mainFrm.groupId.value == "0") {
        mainFrm.groupName.value = document.getElementById("flow_group_id").value;
        mainFrm.groupName.value = sf_group;
    }
    
    var appDeptCode = document.getElementById("app_dept_code").value;
    var appDeptName = document.getElementById("app_dept_name").value;
    if (appDeptCode) {
		document.getElementById("groupId").value = document.getElementById("app_dept_code").value;
    }
    if (appDeptName) {
    	document.getElementById("DeptName").value = document.getElementById("app_dept_name").value;
    }
    
}

//�ύ
function do_AppValidate() {
	var isValid = true;
	if (dataTable.rows.length == 0 || (dataTable.rows[0].style.display == 'none' && dataTable.rows.length == 1)) {
        alert("û�������ݣ�����������ݣ�");
        isValid = false;
	 }else{
		 var tab1 = document.getElementById("dataTable");
		 var rows = tab1.rows;
	     var rowCount = rows.length;
	     var costConetC = null;
	     for (var i =0; i<rowCount; i++) {
	        var cost = document.getElementById("dataTable").rows[i].cells[20].childNodes[0].value;
	        if(costConetC==null){
	        	costConetC=cost;
			}else{
				if(cost!=costConetC){
                     alert("��ѡ��ͬһ�ɱ������ʲ�!");
                     isValid = false;
			    }
			}
		 }
	}
    return isValid;
}



function setAttachmentConfig(){
    var attachmentConfig = new AttachmentConfig();
    attachmentConfig.setOrderPkName("transId");
    return attachmentConfig;
}

/**
  * ���ܣ�ѡ���ʲ�
 */
function do_SelectAssets() {
	var dialogWidth = 52;
	var dialogHeight = 29;

	<%
    	String lun = AssetsLookUpConstant.LOOK_ZEROTURN_ASSETS;
    %>
	var lookUpName = "<%=lun%>";
	var userPara = "";

	userPara += "&deptCode=" + mainFrm.deptCode.value;
	
	var assets = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);

	if (assets) {
			var data = null;
			for (var i = 0; i < assets.length; i++) {
				data = assets[i];
				var tab = document.getElementById("dataTable");
				appendDTO2Table(tab, data, false, "barcode");
			}
			var tab1 = document.getElementById("dataTable");
			var rows = tab1.rows;
		    var rowCount = rows.length;
		    var costConetC = null;
		    for (var i =0; i<rowCount; i++) {
		        var cost = document.getElementById("dataTable").rows[i].cells[20].childNodes[0].value;
		        if(costConetC==null){
		        	costConetC=cost;
				}else{
					if(cost!=costConetC){
 	                     alert("��ѡ��ͬһ�ɱ������ʲ�!");
				    }
				}
		    }
		}
	}

function do_DeleteLines() {
    var tab = document.getElementById("dataTable");
    deleteTableRow(tab, 'subCheck');
}



</script>