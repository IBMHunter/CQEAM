<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.sino.ams.freeflow.FreeFlowDTO"%>
<%@ page import="com.sino.ams.system.user.dto.SfUserRightDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<%
	FreeFlowDTO headerDTO = (FreeFlowDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String status = headerDTO.getTransStatus();
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	String transId = headerDTO.getTransId();
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
	String orgId = userAccount.getOrganizationId();
	String userId = userAccount.getUserId();
	String provinceCode = headerDTO.getServletConfig().getProvinceCode();
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    String hiddenRight = StrUtil.nullToString(request.getParameter("hiddenRight"));//���ڵ��м���������±�ǩ�Ŀ���
	String producedNewBarcode = headerDTO.getProducedNewBarcode();
    String provOrgId = headerDTO.getServletConfig().getProvinceOrgId();
    boolean isDepAM = userAccount.isDptAssetsManager();
    DTOSet userDTO = userAccount.getUserRight();
    String roleName = "";
    Map  userRightMap = new HashMap();
    for (int i = 0;i<userDTO.getSize();i++) {
        SfUserRightDTO userRight = (SfUserRightDTO)userDTO.getDTO(i);
        roleName = userRight.getRoleName();
        userRightMap.put("roleName",roleName);
    }
    boolean isDptMgr = userRightMap.containsValue("���ž���");    //�ж��Ƿ��ž���
    boolean mtlAssetsManager =userAccount.isMtlAssetsManager();   //�Ƿ�רҵ�ʲ�����Ա
//    String currGroupName = userAccount.getCurrGroupName();
    String isGroupPID = request.getAttribute(AssetsWebAttributes.IS_GROUP_PID).toString();//�Ƿ��й�˾�ۺϲ������粿�������
    //EXCEL���
    RowSet rows = (RowSet) request.getAttribute(WebAttrConstant.WORKORDER_LOC_ROWSET);
%>
<head>
	<title><%=headerDTO.getTransTypeValue()%></title>
</head>
<body leftmargin="0" topmargin="0" onload="initPage();">
<form action="/servlet/com.sino.ams.newasset.servlet.AssetsShareFlowServlet" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<jsp:include page="/flow/include.jsp" flush="true"/>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			         <td align=right width=8%>�������ͣ�</td>
			        <td width=13%>
						<input name="transTypeValue" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getTransTypeValue()%>">
					</td>
                    <td align=right width=8%>�����ˣ�</td>
			        <td width=13%>
						<input type="text" name="created1" class="readonlyInput" readonly style="width:100%" value="<%=headerDTO.getCreated()%>" >
					</td>
			        <td align=right>��˾���ƣ�</td>
			        <td  width=25%>
						<input name="userCompanyName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�������ƣ�</td>
			        <td width=17%>
						<input name="userDeptName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
			    </tr>
			    <tr>
                    <td align=right width=8%>���ݺţ�</td>
			        <td>
						<input name="transNo" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getTransNo()%>">
					</td>
                    <td align=right width=8%>�ֻ����룺</td>
			        <td>
						<input name="phoneNumber1" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
			        <td align=right>�������䣺</td>
			        <td>
						<input name="email1" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20"></td>
			        <td height="20">
					<p align="right">�ʲ��˱���</td>
			        <td height="20">
						<input name="bookTypeName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getBookTypeName()%>" ></td>
			    </tr>
			    <tr>

                    <td align=right width=8%>�������ڣ�</td>
                    <td>
                    <input name="creationDate" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getCreationDate()%>" ></td>
                    <td align=right width=8%>�������</td>
			        <td>
						<input name="fromGroupName" class="readonlyInput" readonly style="width:100%; cursor:hand" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectCreateGroup();" ></td>
                      <td align=right width=8%>�����ţ�</td>
			        <td>
						<select name="fromDept" style="width:100%" class="noEmptyInput" onChange="do_ConfirmChange();"><%=headerDTO.getFromDeptOption()%></select>
					</td>
                     <td align=right >�ʲ����ࣺ</td>
			        <td width=17%>
			        	<select name="faContentCode" style="width:100%" class="noEmptyInput" onChange="do_ChangeContentCode();"><%=headerDTO.getFaContentOption()%></select>
			        </td>
			    </tr>

                <tr>
                    <td  height="40" align=right>����˵����</td>
			        <td colspan="7"  height="40"><textarea name="createdReason" style="width:100%;height:100%" class="noEmptyInput" rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea></td>

                </tr>

            </table>

		</td>
	</tr>
</table>
<input type="hidden" name="fromGroup" value="<%=headerDTO.getFromGroup()%>">
<input type="hidden" name="fromOrganizationId" value="<%=headerDTO.getFromOrganizationId()%>">
<input type="hidden" name="transType" value="<%=transType%>">
<input type="hidden" name="transferType" value="<%=headerDTO.getTransferType()%>">
<input type="hidden" name="created" value="<%=headerDTO.getCreated()%>">
<input type="hidden" name="createdBy" value="<%=headerDTO.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=transId%>">
<input type="hidden" name="procdureName" value="�ʲ������������">
<input type="hidden" name="toDept" value="<%=headerDTO.getToDept()%>">
<input type="hidden" name="provinceCode" value="<%=provinceCode%>">
<input type="hidden" name="flowCode" value="">
 <input type="hidden" name="excel" value="">
<input type="hidden" name="act" value="">
<fieldset style="border:1px solid #397DF3; position:absolute;top:123px;width:100%;height:70%">
    <legend>
        <img src="/images/eam_images/tmp_save.jpg" alt="�ݴ�" onClick="do_SaveOrder(); return false;">
        <img src="/images/eam_images/submit.jpg" alt="���" onClick="do_SubmitOrder(); return false;">

<%
	if(status.equals(AssetsDictConstant.SAVE_TEMP))	{
%>
        <img src="/images/eam_images/revoke.jpg" alt="����" onClick="do_CancelOrder();">
<%
	}
%>
        <img src="/images/eam_images/choose.jpg" alt="ѡ���ʲ�" onClick="do_SelectAssets(); return false;">
        <img src="/images/eam_images/delete_line.jpg" alt="ɾ����" onClick="deleteLine(); return false;">
<%
	if(transType.equals(AssetsDictConstant.ASS_RED)){
%>
        <img src="/images/button/pasteData.gif"  alt="ճ��EXCEL" onClick="doPaste(); return false;">
<%
	}
%>
        <span id="warn"></span>
<%
	if(!status.equals("") && !status.equals(AssetsDictConstant.SAVE_TEMP)){
%>
        <img src="/images/eam_images/view_opinion.jpg" alt="�����������" onClick="viewOpinion(); return false;">
        <img src="/images/eam_images/view_flow.jpg" alt="��������" onClick="viewFlow(); return false;">
<%
	}
%>
        <img src="/images/eam_images/close.jpg" id="img6" alt="�ر�" onClick="do_Close(); return false;">
        <img src="/images/eam_images/imp_from_excel.jpg" alt="Excel����"  onClick="do_excel();">
<%
	if(transType.equals(AssetsDictConstant.ASS_RED)){
%>
		ͳһ���ã�
<%
		if(!headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_INN_DEPT)){
%>
		<input type="checkbox" name="allDept" id="allDept" checked style="display:none"><label for="allDept" style="display:none">�����β���</label>
<%
    	}
%>
        <input type="checkbox" name="allLocation" id="allLocation"><label for="allLocation">����ص�</label>
		<input type="checkbox" name="allUser" id="allUser"><label for="allUser">��������</label>
<%
		if((!headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_INN_DEPT))&&(headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP))){
%>
		<input type="checkbox" name="allAccount" id="allAccount"><label for="allAccount">���۾��˻�</label>
		<input type="checkbox" name="allFaCategory" id="allFaCategory"><label for="allFaCategory">�����</label>
<%
		}
%>
		<input type="checkbox" name="allTransDate" id="allTransDate"><label for="allTransDate">��������</label>
<%
	}
%>
	</legend>
<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="160%">
	        <tr height="20px" onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align="center" width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	             <td align=center width="8%">�ʲ���ǩ</td>
	            <td align=center width="6%">�ʲ����</td>
                <td align=center width="10%">�ʲ�˵��</td>
	            <td align=center width="10%">�ʲ��ͺ�</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="6%">Ͷ��ʹ������</td>
	            <td align=center width="4%">����</td>
				<td align=center width="3%">��λ</td>
	            <td align=center width="16%">���ڵص�</td>
                <td align=center width="8%">ʹ������</td>
                <td align=center width="8%">����ԭ��</td>

				<td style="display:none">�������ֶ�</td>
            </tr>

		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:48px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="160%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet == null || lineSet.isEmpty()) {
%>

           <tr class="dataTR" onClick="executeClick(this)" style="display:none" title="����鿴�ʲ���ϸ��Ϣ">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
				<td width="8%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput2" readonly value=""></td>
				<td width="6%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput2" readonly value=""></td>
				<td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
				<td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="6%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="manufacturerName" id="manufacturerName0" class="finput3" readonly value=""></td>
				<td width="6%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput2" readonly value=""></td>
				<td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value=""></td>
				<td width="3%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="unitOfMeasure" id="unitOfMeasure0" class="finput" readonly value=""></td>
				<td width="16%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
				<td width="8%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="lifeInYears" id="lifeInYears0" class="finput" readonly value=""></td>
               <td width="8%" align="left"><input type="text" name="lineReason" id="lineReason0" class="finput" value=""></td>
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
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();

%>
            <tr class="dataTR" onClick="executeClick(this)" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
				<td width="8%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="6%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="manufacturerName" id="manufacturerName<%=i%>" class="finput3" readonly value="<%=lineDTO.getManufacturerName()%>"></td>
				<td width="6%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="16%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="8%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="lifeInYears" id="lifeInYears<%=i%>" class="finput" readonly value="<%=lineDTO.getLifeInYears()%>"></td>
                <td width="8%" align="left"><input type="text" name="lineReason" id="lineReason<%=i%>" class="finput" value="<%=lineDTO.getLineReason()%>"></td>

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
</fieldset>


    <div style="position:absolute;bottom:0px;top:645px;left:0px;right:0px;width:100%;height:100%">
         <jsp:include page="/freeflow/uploadFile.jsp" flush="true"/>
    </div>
</form>

<jsp:include page="/public/hintMessage.jsp" flush="true"/>

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
	
	function do_ApproveOrder(flowCode) {
		if(!do_ValidateNewBarcode(flowCode)){
			return false;
		}
		addApproveContent(flowCode);
		var transType = mainFrm.transType.value;
		mainFrm.flowCode.value = flowCode;
		var transferType = mainFrm.transferType.value;
		var sectionRight = mainFrm.sectionRight.value;
		var attribute1 = mainFrm.attribute1.value;
		if(attribute1 != ""){
			attribute1 = document.getElementById(mainFrm.attribute1.value).value;
		}
		var attribute2 = mainFrm.attribute2.value;//���ݴ���OU��֯ID
		var attribute3 = mainFrm.attribute3.value;//���
		var attribute4 = mainFrm.attribute4.value;//�������ݵĽ���OU��֯ID
		var attribute5 = mainFrm.attribute5.value;//����ģ���Ҫ�޸�
		var hiddenValue = mainFrm.hiddenRight.value;
		var fromOrganizationId = mainFrm.fromOrganizationId.value;
	
		var userId = "<%=userId%>";
		var groupId = mainFrm.fromGroup.value;
		var orgId = "<%=orgId%>";
		var procdureName = mainFrm.procdureName.value;
		var paramObj = new Object();
		paramObj.orgId = orgId;
		paramObj.useId = userId;
		paramObj.groupId = groupId;
		paramObj.procdureName = procdureName;
		paramObj.flowCode = "";
		if(flowCode == "<%=FlowConstant.FLOW_CODE_NEXT%>"){
			if(attribute2){
				orgId = document.getElementById(mainFrm.attribute2.value).value;
			}
			if(attribute3 != "" && document.getElementById(attribute3).value != ""){
				groupId = document.getElementById(attribute3).value;
			}
			if(transType == "<%=AssetsDictConstant.ASS_RED%>"){//����
				paramObj.flowCode = "<%=AssetsDictConstant.OTHER_FLOW%>";
				if(transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>"){//�����ڵ���
					if(sectionRight == "<%=AssetsDictConstant.END_INN_DEPT%>"){
						paramObj.flowCode = "<%=AssetsDictConstant.TRANS_INN_DEPT%>";
					} else if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
						paramObj.flowCode = mainFrm.faContentCode.value;
					}
					if(mainFrm.provinceCode.value == "<%=AssetsDictConstant.PROVINCE_CODE_SX%>"){
						paramObj.flowCode = "";
					}
				} else if(transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>"){//���ż����
					if(sectionRight == "<%=AssetsDictConstant.END_BTW_DEPT%>"){
						paramObj.flowCode = "<%=AssetsDictConstant.TRANS_BTW_DEPT%>";
					} else if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
						if(attribute1 != "<%=AssetsDictConstant.GROUP_PROP_SPEC%>"){
							paramObj.flowCode = mainFrm.faContentCode.value;
						}
					}
					if(mainFrm.provinceCode.value == "<%=AssetsDictConstant.PROVINCE_CODE_SX%>"){
						if(attribute3 == "groupPid"){
							paramObj.flowCode = "";
						}
					}
				} else if(transferType == "<%=AssetsDictConstant.TRANS_BTW_COMP%>"){//��˾�����
					if(sectionRight == "<%=AssetsDictConstant.END_BTW_COMP%>"){
						paramObj.flowCode = "<%=AssetsDictConstant.TRANS_BTW_COMP%>";
					} else if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
						paramObj.flowCode = mainFrm.faContentCode.value;
					}
					if(mainFrm.provinceCode.value == "<%=AssetsDictConstant.PROVINCE_CODE_SX%>"){
						if(attribute3 == "groupPid"){
							paramObj.flowCode = "";
							if(mainFrm.groupPid.value != ""){
								groupId = mainFrm.groupPid.value;
								orgId = mainFrm.fromOrganizationId.value;
							} else {
								groupId = mainFrm.toGroup.value;
								orgId = mainFrm.toOrganizationId.value;
							}
						}
					}
					if(fromOrganizationId == "<%=provOrgId%>" && attribute5 == "Y"){//�����ʡ��˾����
						attribute4 = document.getElementById(attribute4).value;
						paramObj.flowCode = fromOrganizationId;
						orgId = attribute4;
					}
				}
			} else if(transType == "<%=AssetsDictConstant.ASS_DIS%>"){//����
				if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
					paramObj.flowCode = mainFrm.faContentCode.value;
					if(attribute2 != ""){
						attribute2 = document.getElementById(attribute2).value;
						if(attribute2 == "<%=provOrgId%>"){
							paramObj.flowCode = attribute2;
						}
					}
				}
			} else if(transType == "<%=AssetsDictConstant.ASS_CLR%>"){//����
				if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
					paramObj.flowCode = mainFrm.faContentCode.value;
					if(attribute2 != ""){
						attribute2 = document.getElementById(attribute2).value;
						if(attribute2 == "<%=provOrgId%>"){
							paramObj.flowCode = attribute2;
						}
					}
				}
			} else if(transType == "<%=AssetsDictConstant.ASS_FREE%>"){//����
				if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
					paramObj.flowCode = mainFrm.faContentCode.value;
					if(attribute2 != ""){
						attribute2 = document.getElementById(attribute2).value;
						if(attribute2 == "<%=provOrgId%>"){
							paramObj.flowCode = attribute2;
						}
					}
				}
			}
			paramObj.groupId = groupId;
			paramObj.orgId = orgId;
	//		alert("paramObj.flowCode = " + paramObj.flowCode);
	//		alert("paramObj.groupId = " + paramObj.groupId);
	//		alert("paramObj.orgId = " + paramObj.orgId);
			if(mainFrm.approveContent.value == "<%=FlowConstant.APPROVE_CONTENT_REJECT%>"){
				mainFrm.approveContent.value = "";
			}
			paramObj.submitH = 'submitH()';
			if(mainFrm.approveContent.value == ""){
				mainFrm.approveContent.value = "<%=FlowConstant.APPROVE_CONTENT_AGREE%>";
			}
			assign(paramObj);
		} else {
			if(confirm("ȷ��Ҫ�˻��𣿼���������ȷ������ť������������ȡ������ť��")){
				if(mainFrm.approveContent.value == ""){
					alert("�˻�����ʱ������д�������");
					return;
				}
				submitH();
			}
		}
	}
	function do_excel() {
	
			var fromDept = mainFrm.fromDept.value;
			if(fromDept == ""){
	            alert(0);
	            alert("����ѡ���ţ���ѡ���ʲ���");
				mainFrm.fromDept.focus();
				return;
			}
	
	    if(mainFrm.faContentCode.value == ""){
			alert("����ѡ���ʲ����࣬��ѡ���ʲ���");
			mainFrm.faContentCode.focus();
			return;
		}
	    var url="/workorder/bts/upFile.jsp";
	    var dialogStyle = "dialogWidth=18;dialogHeight=6;help=no;status=no;center=yes;toolbar=no;menubar=no;resizable=no;scroll=no";
	    var aa=window.showModalDialog(url,"",dialogStyle);
	    if (aa) {
	        document.mainFrm.excel.value = aa;
	        document.mainFrm.act.value = "excel";
	        document.mainFrm.submit();
	    }
	}
	
	function do_ValidateNewBarcode(flowCode){
		var isValid = true;
		var hiddenRight = "<%=hiddenRight%>";
		var producedNewBarcode = "<%=producedNewBarcode%>";
		if(flowCode == "<%=FlowConstant.FLOW_CODE_NEXT%>" && hiddenRight == "<%=AssetsDictConstant.NEW_TAG_NODE%>" && producedNewBarcode == "N"){
			var newTagObjs = document.getElementsByName("newBarcode");
			for(var i = 0; i < newTagObjs.length; i++){
				if(newTagObjs[i].value == ""){
					isValid = false;
					alert("���������±�ǩ�ţ�");
					return;
				}
			}
		}
		return isValid;
	}
	
	/**
	  * ���ܣ�ѡ���ʲ�
	 */
	function do_SelectAssets() {
		var dialogWidth = 52;
		var dialogHeight = 29;
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_TRANSFER_ASSETS%>";
		var userPara = "transType=" + mainFrm.transType.value;
	
	
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
		if(mainFrm.faContentCode.value == ""){
	        alert("����ѡ���ʲ����࣬��ѡ���ʲ���");
			mainFrm.faContentCode.focus();
			return;
		}
		userPara += "&faContentCode=" + mainFrm.faContentCode.value;
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
	    if(deleteTableRow(dataTable, 'subCheck')){
<%--			do_SaveOrder();--%>
			return;
		}
	}
	
	//ճ��EXCEL
	var xmlHttp;
	var segment10Array = new Array();
	var projectNameArray = new Array();
	var segment10Index = -1;
	var projectNameIndex = -1;
	var mark = -1;
	function doPaste() {
	    try {
	        if(transferType != "" && transferType != "<%=AssetsDictConstant.TRANS_BTW_COMP%>"){
			var fromDept = mainFrm.fromDept.value;
			if(fromDept == ""){
				alert("����ѡ���ţ���ճ����");
				mainFrm.fromDept.focus();
				return;
			 }
		   } else if(transferType != "" && transferType == "<%=AssetsDictConstant.TRANS_BTW_COMP%>"){
	        var toOrganizationId = mainFrm.toOrganizationId.value;
		    if(toOrganizationId == ""){
		       alert("����ѡ�������˾����ճ����");
	           mainFrm.toOrganizationId.focus();
	           return;
		    }
	      }
	
	        if (confirm("ȷ��ճ������ǰҳ�棿")) {
	            var text = window.clipboardData.getData("text");
	            if (text == null || text == "") {
	                alert("������EXCEL�����︴�ƶ��������ݣ�Ȼ����ճ����");
	                return;
	            }
	            var rows = text.split('\n');
	            for (var i = 0; i < rows.length - 1; i++) {
	                mark ++;
	                var row = rows[i];
	                insertRow(row);
	            }
	            pageVerifySegment10();
	        }
	    } catch(e) {
	        alert(e.description);
	        alert("ճ��ʧ��!");
	    }
	}
	
	function insertRow(row) {
	    var cols;
	    if (typeof(row) == 'string') {
	        cols = row.split('\t');
	    } else {
	        cols = row;
	    }
	    var newRow = document.getElementById("model").cloneNode(true);
	    newRow.style.display = 'block';
	    newRow.childNodes[0].childNodes[0].value = mark;
	    newRow.childNodes[1].childNodes[0].value = cols[0];
	    newRow.childNodes[2].childNodes[0].value = cols[1];
	    newRow.childNodes[3].childNodes[0].value = cols[2];
	    newRow.childNodes[4].childNodes[0].value = cols[3];
	    newRow.childNodes[5].childNodes[0].value = cols[4];
	<%
		if(headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_INN_DEPT)){
	%>
	    newRow.childNodes[6].childNodes[0].value = cols[6];
	    newRow.childNodes[7].childNodes[0].value = cols[8];
	    newRow.childNodes[8].childNodes[0].value = cols[10];
	    newRow.childNodes[9].childNodes[0].value = cols[12];
	    newRow.childNodes[10].childNodes[0].value = cols[13];
	    newRow.childNodes[11].childNodes[0].value = cols[14];
	    newRow.childNodes[12].childNodes[0].value = cols[5];
	    newRow.childNodes[13].childNodes[0].value = cols[7];
	    newRow.childNodes[14].childNodes[0].value = cols[9];
	    newRow.childNodes[15].childNodes[0].value = cols[11];
	 <%
	 	} else if(headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_BTW_DEPT)){
	%>
	    newRow.childNodes[6].childNodes[0].value = cols[6];
	    newRow.childNodes[7].childNodes[0].value = cols[8];
	    newRow.childNodes[8].childNodes[0].value = cols[10];
	    newRow.childNodes[9].childNodes[0].value = cols[12];
	    newRow.childNodes[10].childNodes[0].value = cols[14];
	    newRow.childNodes[11].childNodes[0].value = cols[15];
	    newRow.childNodes[12].childNodes[0].value = cols[16];
	    newRow.childNodes[13].childNodes[0].value = cols[5];
	    newRow.childNodes[14].childNodes[0].value = cols[7];
	    newRow.childNodes[15].childNodes[0].value = cols[9];
	    newRow.childNodes[16].childNodes[0].value = cols[11];
	    newRow.childNodes[17].childNodes[0].value = cols[13];
	<%
		} else if(headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP)){
	%>
	    newRow.childNodes[6].childNodes[0].value = cols[5];
	    newRow.childNodes[7].childNodes[0].value = cols[6];
	    newRow.childNodes[8].childNodes[0].value = cols[7];
	    newRow.childNodes[9].childNodes[0].value = cols[8];
	    newRow.childNodes[10].childNodes[0].value = cols[10];
	    newRow.childNodes[11].childNodes[0].value = cols[12];
	    newRow.childNodes[12].childNodes[0].value = cols[14];
	    newRow.childNodes[13].childNodes[0].value = cols[15];
	    newRow.childNodes[14].childNodes[0].value = cols[16];
	    newRow.childNodes[15].childNodes[0].value = cols[18];
	    newRow.childNodes[16].childNodes[0].value = cols[20];
	    newRow.childNodes[17].childNodes[0].value = cols[22];
	    newRow.childNodes[18].childNodes[0].value = cols[23];
	    newRow.childNodes[19].childNodes[0].value = cols[24];
	    newRow.childNodes[20].childNodes[0].value = cols[25];
	    newRow.childNodes[21].childNodes[0].value = cols[26];
	    newRow.childNodes[22].childNodes[0].value = cols[9];
	    newRow.childNodes[23].childNodes[0].value = cols[11];
	    newRow.childNodes[24].childNodes[0].value = cols[13];
	    newRow.childNodes[25].childNodes[0].value = cols[17];
	    newRow.childNodes[26].childNodes[0].value = cols[19];
	    newRow.childNodes[27].childNodes[0].value = cols[21];
	<%
		}
	%>
	    document.getElementById("model").parentNode.appendChild(newRow);
	}
	
	function pageVerifySegment10() {
	    var warn = document.getElementById('warn');
	    warn.innerText = '';
	    doInitArray();
	    xmlHttp = createXMLHttpRequest();
	    var url = "/servlet/com.sino.ams.freeflow.FreeFlowServlet" ;
	    xmlHttp.open('POST', url, true);
	    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    xmlHttp.send(segment10Array.toJSONString());
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
	
	function do_SubmitOrder() {
	
	    if (!validate()) {
	        return;
	    }
	    
	    var paramObj = new Object();
	    
		var transType = "<%=transType%>";
		var transferType = "<%=transferType%>";
		var provinceCode = "<%=provinceCode%>";
		if(transferType != ""){
			if(transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>"){
				mainFrm.toDept.value = mainFrm.fromDept.value;
			} else {
				var depts = document.getElementsByName("responsibilityDept");
				mainFrm.toDept.value = depts[0].value;
			}
		}
	    var orgId = "<%=orgId%>";
		var userId = "<%=userId%>";
	    var isDepAM = "<%=isDepAM%>";
	    var mtlAssetsManager="<%=mtlAssetsManager%>";
	    var isGroupPID = "<%=isGroupPID%>";
	    var isDptMgr ="<%=isDptMgr%>";
	    var groupId = mainFrm.fromGroup.value;
	    var procdureName = mainFrm.procdureName.value;
	    var faContentCode=mainFrm.faContentCode.value;
	    var flowCode ="";
     	if(transType == "<%=AssetsDictConstant.ASS_SHARE %>"){
			if(isDptMgr=="true" ){
	     		 if(orgId != "<%=provOrgId%>"  && faContentCode=="NET-ASSETS" && isGroupPID == "false"){
		             flowCode="C-NET";
		         }else if(orgId == "<%=provOrgId%>" && isGroupPID == "true"){
		         	 flowCode="END";
		         } else if(orgId != "<%=provOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "false"){
		             flowCode="C-MAR" ;
		         } else if (orgId != "<%=provOrgId%>" && faContentCode=="MGR-ASSETS" && isGroupPID == "false"){
		             flowCode="C-MGR";
		         }else if((orgId != "<%=provOrgId%>" && faContentCode=="NET-ASSETS" && isGroupPID == "true") || (orgId == "<%=provOrgId%>" && faContentCode=="NET-ASSETS" && isGroupPID == "false")) {
		             flowCode="P-NET" ;
		         } else if((orgId != "<%=provOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "true") || (orgId == "<%=provOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "false")) {
		             flowCode="P-MAR";
		         }else if((orgId != "<%=provOrgId%>" && faContentCode=="MGR-ASSETS" && isGroupPID == "true") || (orgId == "<%=provOrgId%>" && faContentCode=="MGR-ASSETS" && isGroupPID == "false")){
		             flowCode="P-MGR";
		         }       	         
	        }
	    }
	    paramObj.orgId = orgId;
	    paramObj.useId = userId;
	    paramObj.groupId = groupId;
	    paramObj.procdureName = procdureName;
	    paramObj.submitH = 'submitH()';
	    assign(paramObj);  //���õ���flow.js�еķ���
	}
	
	
	function submitH() {
	    mainFrm.act.value = "<%=AssetsActionConstant.SUBMIT_ACTION%>";
	    mainFrm.submit();
	}
	
	
	function initPage() {
	    window.focus();
		do_SetPageWidth();
		var fromGroup = mainFrm.fromGroup.value;
		if(fromGroup == ""){
			do_SelectCreateGroup();
		}
	}
	
	
	function do_SelectCreateGroup(){
		var fromGroup = mainFrm.fromGroup.value;
		var url = "<%=AssetsURLList.GROUP_CHOOSE_SERVLET%>?fromGroup=" + fromGroup;
		var popscript = "dialogWidth:20;dialogHeight:10;center:yes;status:no;scrollbars:no;help:no";
		var group = window.showModalDialog(url, null, popscript);
		if(group){
			dto2Frm(group, "mainFrm");
		}
	}
	
	
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
	
	
	/**
	 * ����:ѡ����ղ���
	 */
	function do_SelectDept(lineBox) {
		var toOrganizationId = mainFrm.toOrganizationId.value;
		if(toOrganizationId == ""){
			alert("����ѡ�������˾����ѡ�������β��š�");
			return;
		}
		var userPara = "organizationId=" + toOrganizationId + "&transferType=" + transferType;
		var deptCode = "";
		if(transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>"){
			deptCode = mainFrm.fromDept.value;
		}
		userPara += "&deptCode=" + deptCode;
	
		var objName = lineBox.name;
		var objId = lineBox.id;
		var idNumber = objId.substring(objName.length);
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_DEPT%>";
		var dialogWidth = 44;
		var dialogHeight = 30;
		var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		var deptChk = mainFrm.allDept;
		if(!deptChk.checked){
			if (objs) {
				obj = objs[0];
				document.getElementById("responsibilityDept" + idNumber).value = obj["toDept"];
				lineBox.value = obj["toDeptName"];
			} else {
				lineBox.value = "";
				document.getElementById("responsibilityDept" + idNumber).value = "";
				document.getElementById("responsibilityUserName" + idNumber).value = "";
				document.getElementById("responsibilityUser" + idNumber).value = "";
				document.getElementById("assignedToLocationName" + idNumber).value = "";
				document.getElementById("assignedToLocation" + idNumber).value = "";
				document.getElementById("addressId" + idNumber).value = "";
			}
		} else {
			var obj = null;
			var emptyData = false;
			if (objs) {
				obj = objs[0];
				mainFrm.toDept.value = obj["toDept"];//Ϊɽ�����ı䣬׼��Ӧ��������
			} else {
				emptyData = true;
				mainFrm.toDept.value = "";//Ϊɽ�����ı䣬׼��Ӧ��������
			}
			var deptNames = document.getElementsByName("responsibilityDeptName");
			var depts = document.getElementsByName("responsibilityDept");
			var userNames = document.getElementsByName("responsibilityUserName");
			var users = document.getElementsByName("responsibilityUser");
			var addressNames = document.getElementsByName("assignedToLocationName");
			var addressNos = document.getElementsByName("assignedToLocation");
			var addressIds = document.getElementsByName("addressId");
			var count = addressNames.length;
			for(var i = 0; i < count; i++){
				if(emptyData){
					deptNames[i].value = "";
					depts[i].value = "";
					addressNames[i].value = "";
					addressNos[i].value = "";
					addressIds[i].value = "";
					userNames[i].value = "";
					users[i].value = "";
				} else {
					deptNames[i].value = obj["toDeptName"];
					depts[i].value = obj["toDept"];
				}
			}
		}
	}
	
	//ѡ�����ص�
	function do_SelectLocation(lineBox){
		var toOrganizationId = mainFrm.toOrganizationId.value;
		if(toOrganizationId == ""){
			alert("����ѡ�������˾����ѡ��ص㡣");
			return;
		}
		var deptCode = "";
		var objName = lineBox.name;
		var objId = lineBox.id;
		var idNumber = objId.substring(objName.length);
		if(transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>"){
			deptCode = mainFrm.fromDept.value;
		} else {
			deptCode = document.getElementById("responsibilityDept" + idNumber).value;
			if(deptCode == ""){
				alert("����ѡ��������ţ���ѡ��ص㡣");
				var deptObj = document.getElementById("responsibilityDeptName" + idNumber);
				do_SelectDept(deptObj);
				return;
			}
		}
		var userPara = "organizationId=" + toOrganizationId + "&transferType=" + transferType + "&deptCode=" + deptCode;
	
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ADDRESS%>";
		var dialogWidth = 55;
		var dialogHeight = 30;
		var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		var addressChk = mainFrm.allLocation;
		if(!addressChk.checked){
			if (objs) {
				var obj = objs[0];
				document.getElementById("assignedToLocation" + idNumber).value = obj["toObjectNo"];
				document.getElementById("addressId" + idNumber).value = obj["addressId"];
				lineBox.value = obj["toObjectName"];
			} else {
				document.getElementById("assignedToLocation" + idNumber).value = "";
				document.getElementById("addressId" + idNumber).value = "";
				lineBox.value = "";
			}
		} else {
			var obj = null;
			var emptyData = false;
			if (objs) {
				obj = objs[0];
			} else {
				emptyData = true;
			}
			var addressNames = document.getElementsByName("assignedToLocationName");
			var addressNos = document.getElementsByName("assignedToLocation");
			var addressIds = document.getElementsByName("addressId");
			var count = addressNames.length;
			var dataTable = document.getElementById("dataTable");
			var rows = dataTable.rows;
			var row = null;
			var checkObj = null;
			var checkedCount = getCheckedBoxCount("subCheck");
			for(var i = 0; i < count; i++){
				if(checkedCount > 0){
					row = rows[i];
					checkObj = row.childNodes[0].childNodes[0];
					if(!checkObj.checked){
						continue;
					}
				}
				if(emptyData){
					addressNames[i].value = "";
					addressNos[i].value = "";
					addressIds[i].value = "";
				} else {
					addressNames[i].value = obj["toObjectName"];
					addressNos[i].value = obj["toObjectNo"];
					addressIds[i].value = obj["addressId"];
				}
			}
		}
	}
	
	
	function do_SelectFaCategoryCode(lineBox) {
		var objName = lineBox.name;
		var objId = lineBox.id;
		var idNumber = objId.substring(objName.length);
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_FACAT_CODE%>";
		var dialogWidth = 54;
		var dialogHeight = 30;
		var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
		var faCatChk = mainFrm.allFaCategory;
		if(!faCatChk.checked){
			if (objs) {
				obj = objs[0];
				document.getElementById("faCategoryCode" + idNumber).value = obj["faCategoryCode"];
			} else {
				lineBox.value = "";
			}
		} else {
			var obj = null;
			if (objs) {
				obj = objs[0];
			} else {
				obj = new Object();
				obj.faCategoryCode = "";
				obj.faCategoryName = "";
			}
			var catCodes = document.getElementsByName("faCategoryCode");
			var count = catCodes.length;
			var dataTable = document.getElementById("dataTable");
			var rows = dataTable.rows;
			var row = null;
			var checkObj = null;
			var checkedCount = getCheckedBoxCount("subCheck");
			for(var i = 0; i < count; i++){
				if(checkedCount > 0){
					row = rows[i];
					checkObj = row.childNodes[0].childNodes[0];
					if(!checkObj.checked){
						continue;
					}
				}
				catCodes[i].value = obj["faCategoryCode"];
			}
		}
	}
	
	
	function do_SelectDepreciationAccount(lineBox){
		var toOrganizationId = mainFrm.toOrganizationId.value;
		if(toOrganizationId == ""){
			alert("����ѡ�������˾����ѡ���۾��˻���");
			return;
		}
		var objName = lineBox.name;
		var objId = lineBox.id;
		var idNumber = objId.substring(objName.length);
		var deptCode = "";
		if(transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>"){
			deptCode = mainFrm.fromDept.value;
		} else {
			deptCode = document.getElementById("responsibilityDept" + idNumber).value;
			if(deptCode == ""){
				alert("����ѡ��������ţ���ѡ���۾��˻���");
				var deptObj = document.getElementById("responsibilityDeptName" + idNumber);
				do_SelectDept(deptObj);
				return;
			}
		}
		var userPara = "organizationId=" + toOrganizationId;
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ACCOUNT%>";
		var dialogWidth = 51;
		var dialogHeight = 29;
		var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		var accountChk = mainFrm.allAccount;
		if(!accountChk.checked){
			if (objs) {
				obj = objs[0];
				lineBox.value = obj["accountCode"];
			} else {
				lineBox.value = "";
			}
		} else {
			var obj = null;
			var emptyData = false;
			if (objs) {
				obj = objs[0];
			} else {
				emptyData = true;
			}
			var accounts = document.getElementsByName("depreciationAccount");
			var count = accounts.length;
			var dataTable = document.getElementById("dataTable");
			var rows = dataTable.rows;
			var row = null;
			var checkObj = null;
			var checkedCount = getCheckedBoxCount("subCheck");
			for(var i = 0; i < count; i++){
				if(checkedCount > 0){
					row = rows[i];
					checkObj = row.childNodes[0].childNodes[0];
					if(!checkObj.checked){
						continue;
					}
				}
				if(emptyData){
					accounts[i].value = "";
				} else {
					accounts[i].value = obj["accountCode"];
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
	        var fieldLabels = "�������;ԭ��";
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
		if(transType == "<%=AssetsDictConstant.ASS_SUB%>"){
			cell = cells[3];
		}
		var barcode = cell.childNodes[0].value;
		var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
		var winName = "assetsWin";
	    var style = "width=860,height=495,left=100,top=130";
		window.open(url, winName, style);
	}
	
	function do_CancelOrder() {
		if(confirm("����׼�����������ݣ�ȷ���𣿼���������ȷ������ť������������ȡ������ť!")){
			mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
			mainFrm.submit();
		}
	}
	
	function do_SetLineTransDate(lineBox){
		if(!mainFrm.allTransDate){
			return;
		}
		if(!mainFrm.allTransDate.checked){
			return
		}
		var id = lineBox.id;
		var lineDate = lineBox.value;
		var dateFields = document.getElementsByName("lineTransDate");
		var dataTable = document.getElementById("dataTable");
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
	
	 var  al=0;
	function do_exportToExcel() {
		mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
		mainFrm.submit();
	}
	
	var contentCode = mainFrm.faContentCode.value;
	
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
	            selectSpecialOptionByItem(mainFrm.faContentCode, contentCode);
	        }
	    }
	}
</script>