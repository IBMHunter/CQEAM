<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String status = headerDTO.getTransStatus();
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	String transId = headerDTO.getTransId();
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
	String orgId = userAccount.getOrganizationId();
	String userId = userAccount.getUserId();
	String provinceCode = headerDTO.getServletConfig().getProvinceCode();
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	String provOrgId= headerDTO.getServletConfig().getProvinceOrgId();
	DTOSet lineSetPri2 = (DTOSet) request.getAttribute(AssetsWebAttributes.PRIVI_DATA);//EXCEL����ʱ���벻�ɹ���DTOSET
%>
<head>
	<title><%=headerDTO.getTransTypeValue()%></title>      
</head>
<body leftmargin="0" topmargin="0" onload="initPage();">
<form action="<%=AssetsURLList.ASSETS_SUB_SERVLET%>" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<jsp:include page="/flow/include.jsp" flush="true"/>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%>
						<input name="transNo" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getTransNo()%>">
					</td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%>
						<input name="transTypeValue" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getTransTypeValue()%>">
					</td>
					<td align=right width=8%>�������ڣ�</td>
			        <td width=17%>
						<input name="creationDate" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getCreationDate()%>" ></td>
			        <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" class="readonlyInput" readonly style="width:100%; cursor:hand" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectCreateGroup();" ></td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%>
						<input name="created" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getCreated()%>">
					</td>
			        <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�������ƣ�</td>
			        <td width=17%>
						<input name="userDeptName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
			        <td height="20">
					<p align="right">�ʲ��˲���</td>
			        <td height="20">
						<input name="bookTypeName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getBookTypeName()%>" ></td>
			    </tr>
			    <tr>
					<td align=right width=8%>�ֻ����룺</td>
			        <td width=17%>
						<input name="phoneNumber1" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
			        <td align=right>�����ʼ���</td>
			        <td>
						<input name="email1" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20"></td>
					<td align=right width=8%>��ֵ���ţ�</td>
			        <td colspan="3" width=17%>
						<select name="fromDept" style="width:100%" class="noEmptyInput" onChange="do_ConfirmChange()"><%=headerDTO.getFromDeptOption()%></select>
					</td>
			    </tr>
			    <tr>
			    <td align=right >�ʲ����ࣺ</td>
			        <td width=17%>
			        	<select name="faContentCode" style="width:100%" class="noEmptyInput" onChange="do_ChangeContentCode()"><%=headerDTO.getFaContentOption()%></select>
			        </td>
					<!-- <td width=8% height="40" align=right>������ƣ�</td> -->
			        <!-- <td  height="40"><input name="lossesName" class="noEmptyInput" style="width:100%; " value="<%=headerDTO.getLossesName()%>" size="20"></td>-->
			        <td  height="40" align=right>������ڣ�</td>
			        <td  height="40"><input name="lossesDate" class="noEmptyInput" readonly style="width:100%; " value="<%=headerDTO.getLossesDate()%>" onclick="gfPop.fPopCalendar(lossesDate)" title="���ѡ�������������"></td>
			        <td  height="40" align=right>��ֵ˵����</td>��
			        <td colspan="3"  height="40"><textarea name="createdReason" style="width:100%;height:100%" class="noEmptyInput" rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea></td>
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
<input type="hidden" name="procdureName" value="<%=headerDTO.getProcdureName()%>">
<input type="hidden" name="toDept" value="<%=headerDTO.getToDept()%>">
<input type="hidden" name="act" value="">
<fieldset style="border:1px solid #397DF3; position:absolute;top:140px;width:100%;height:70%">
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
    if (lineSetPri2 != null) {
        if (!lineSetPri2.isEmpty()) {
%>
        <img src="/images/eam_images/detail_info.jpg" alt="�鿴�ʲ�����δ����ɹ���ϸ��Ϣ"  onClick="do_Transfer();">
<%
        }
    }
%>
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
	            <td align="center" width="2%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <!--<td align="center" width="4%">��Ԫ���</td>-->
	            <td align="center" width="4%">״̬</td>
			    <td align="center" width="6%">�ʲ���ǩ��</td>
                <td align="center" width="4%">�ʲ����</td>
                <td align="center" width="8%">�ʲ�˵��</td>
                <td align="center" width="2%">����</td>
                <td align="center" width="4%">�ʲ�ԭֵ</td>
	            <td align="center" width="4%">�ۼ��۾�</td>
	            <td align="center" width="4%">���ֵ׼��</td>
	            <td align="center" width="4%">�ʲ���ֵ</td>
	            <td align="center" width="4%">�ɻ��ս��</td>
	            <td align="center" width="4%">��ֵ��ʧ</td>
	            <!--<td align="center" width="8%">��Ӧ��</td>-->
	            <td align="center" width="4%">����ͺ�</td>
	            <td align="center" width="4%">��������</td>
	            <!--<td align="center" width="5%">������ð汾</td>-->
				<!--<td align="center" width="5%">�����ֵ�汾</td>-->
	            <!-- <td align="center" width="4%">�����ֵ</td> -->
	            <td align="center" width="8%">��ע</td>
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
				<td width="2%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
               <!--<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="netUnit" id="netUnit0" class="finput2"  value=""></td>-->
                <td width="4%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsStatusDes" id="assetsStatusDes0" class="finput" readonly value=""></td> 
				<td width="6%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput2" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput2" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
                <td width="2%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value=""></td>
                <td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost0" class="finput3" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="depreciation" id="depreciation0" class="finput3" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="impairReserve" id="impairReserve0" class="finput3" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="deprnCost" id="deprnCost0" class="finput3" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="recycleValue" id="recycleValue0" class="finput3"  onChange="do_checkRecycleValue(this)" value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="lostCostValue" id="lostCostValue0" class="finput3" readonly value=""></td>
				<!--<td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName0" class="finput" readonly value=""></td> -->
				<td width="4%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput3" readonly value=""></td>
				<!--<td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="softInuseVersion" id="softInuseVersion0" class="finput2"  value=""></td>-->
				<!--<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="softDevalueVersion" id="softDevalueVersion0" class="finput2"  value=""></td>-->
				<!-- <td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="prepareDevalue" id="prepareDevalue0" onkeydown="floatOnly(this.value);" class="finput2"  value=""></td> -->
				<td width="8%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="remark" id="remark0" class="finput"  value=""></td>
                <td style="display:none">
                    <input type="hidden" name="assetId" id="assetId0" value="">
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
				<td width="2%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
				<!-- <td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="netUnit" id="netUnit<%=i%>" class="finput2"  value="<%=lineDTO.getNetUnit()%>"></td>-->
				<td width="4%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsStatusDes" id="assetsStatusDes<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsStatusDes()%>"></td>
				<td width="6%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=lineDTO.getBarcode()%>"></td>
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="2%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost<%=i%>" class="finput2" readonly value="<%=lineDTO.getCost()%>"></td>
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="depreciation" id="depreciation<%=i%>" class="finput2" readonly value="<%=lineDTO.getDepreciation()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="impairReserve" id="impairReserve<%=i%>" class="finput3" readonly value="<%=lineDTO.getImpairReserve()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="recycleValue" id="recycleValue<%=i%>" class="finput3" onChange="do_checkRecycleValue(this)" value="<%=lineDTO.getRecycleValue()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="lostCostValue" id="lostCostValue<%=i%>" class="finput3" readonly value=""></td>
				<!--<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput" readonly value="<%=lineDTO.getVendorName()%>"></td>-->
				<td width="4%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput3" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<!-- <td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="softInuseVersion" id="softInuseVersion<%=i%>" class="finput2"  value="<%=lineDTO.getSoftInuseVersion()%>"></td> -->
				<!-- <td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="softDevalueVersion" id="softDevalueVersion<%=i%>" class="finput2"  value="<%=lineDTO.getSoftDevalueVersion()%>"></td> -->
				<!-- <td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="prepareDevalue" id="prepareDevalue<%=i%>" onkeydown="floatOnly(this.value);" class="finput2"  value="<%=lineDTO.getPrepareDevalue()%>"></td> -->
				<td width="8%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="remark" id="remark<%=i%>" class="finput"  value="<%=lineDTO.getRemark()%>"></td>
                <td style="display:none">
                    <input type="hidden" name="assetId" id="assetId0" value="<%=lineDTO.getAssetId()%>">
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
         <jsp:include page="/newasset/uploadInclude.jsp" flush="true"/>
        <%--&nbsp;&nbsp;&nbsp<input type="button" name="sub" value="EXCELģ������" onclick="do_exportToExcel();">--%>
    </div>
  
<%--<fieldset style="border:1px solid #397DF3; position:absolute;top:620px;width:100%;height:10%">--%>
<%--<legend>������ϸ����</legend>--%>
   <%--<% if (transType.equals(AssetsDictConstant.ASS_RED)){ %>--%>
    <%--&nbsp;&nbsp;&nbsp;&nbsp<input type="button" name="sub" value="EXCELģ������" onclick="do_exportToExcel();">--%>
    <%--<%} %>--%>
<%--</fieldset>--%>

<jsp:include page="/public/hintMessage.jsp" flush="true"/>
</form>
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

function do_checkRecycleValue(object){
    var id=object.id.substring(12);
    var mem_value = object.value;
    for(var i=0; i<mem_value.length; i++)
	{
		if(mem_value.charAt(i)<'0' || mem_value.charAt(i)>'9')
		{
			alert("�ɻ��ս��ֻ��������");
			object.value=0;
			return ;
		}
	}
    
	var temp=document.getElementById("deprnCost"+id).value
	if(parseFloat(object.value)>parseFloat(temp)){
		alert("�ɻ��ս��ܴ����ʲ���ֵ");
		object.value=0;
		return
	}
//	alert(document.getElementById("deprnCost"+id).value);
    var lost=temp-object.value;
    document.getElementById("lostCostValue"+id).value=lost;
}
/**
  * ���ܣ�ѡ���ʲ�
 */
function do_SelectAssets() {
	var dialogWidth = 52;
	var dialogHeight = 29;
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_TRANSFER_ASSETS%>";
	var userPara = "transType=" + mainFrm.transType.value;
	userPara="";

    if(transferType != ""){
		//userPara += "&transferType=" + transferType;
	}
	if(transferType == "" || (transferType != "" && transferType != "<%=AssetsDictConstant.TRANS_BTW_COMP%>")){
		var fromDept = mainFrm.fromDept.value;
		if(fromDept == ""){
			alert("����ѡ���ţ���ѡ���ʲ���");
			mainFrm.fromDept.focus();
			return;
		}
		//userPara += "&deptCode=" + mainFrm.fromDept.value;
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
			appendDTO2Table(dataTable, data, true, "barcode");
		}
	}
}

function deleteLine() {
    if(deleteTableRow(dataTable, 'subCheck')){
		do_SaveOrder();
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

 
    document.getElementById("model").parentNode.appendChild(newRow);
}
function do_excel() {

		var fromDept = mainFrm.fromDept.value;
		if(fromDept == ""){

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
function pageVerifySegment10() {
    var warn = document.getElementById('warn');
    warn.innerText = '';
    doInitArray();                        
    xmlHttp = createXMLHttpRequest();
    var url = "/servlet/com.sino.ams.newasset.servlet.AmsAssetsTransHeaderServlet" ;
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
    var groupId = mainFrm.fromGroup.value;
    var procdureName = mainFrm.procdureName.value;
    var flowCode = mainFrm.faContentCode.value;  
       
    var paramObj = new Object();
    paramObj.orgId = orgId;
    paramObj.useId = userId;
    paramObj.groupId = groupId;
    paramObj.procdureName = procdureName;
    paramObj.flowCode = flowCode;
    paramObj.submitH = 'submitH()';
    assign(paramObj);
}
function do_ApproveOrder(flowCode) {
	
	if (!validate()) {
        return;
    }
	addApproveContent(flowCode);
	var transType = mainFrm.transType.value;
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

	paramObj.flowCode = mainFrm.faContentCode.value;
	if(flowCode == "<%=FlowConstant.FLOW_CODE_NEXT%>"){
		if(attribute2){
			orgId = document.getElementById(mainFrm.attribute2.value).value;
		}
		if(attribute3 != "" && document.getElementById(attribute3).value != ""){
			groupId = document.getElementById(attribute3).value;
		}
		
		if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
			paramObj.flowCode = mainFrm.faContentCode.value;
			if(attribute2 != ""){
				attribute2 = document.getElementById(attribute2).value;
				if(attribute2 == "<%=provOrgId%>"){
					paramObj.flowCode = attribute2;
				}
			} 
		}
		
//		paramObj.groupId = groupId;
//  	paramObj.orgId = orgId;
//		alert("paramObj.flowCode = " + paramObj.flowCode);
//		alert("paramObj.groupId = " + paramObj.groupId);
//		alert("paramObj.orgId = " + paramObj.orgId);
		paramObj.submitH = 'submitH()';
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


//ѡ����������
function do_SelectPerson(lineBox){
	var toOrganizationId = mainFrm.toOrganizationId.value;
	if(toOrganizationId == ""){
		alert("����ѡ�������˾����ѡ���������ˡ�");
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
			alert("����ѡ��������ţ���ѡ����������");
			var deptObj = document.getElementById("responsibilityDeptName" + idNumber);
			do_SelectDept(deptObj);
			return;
		}
	} 
	var userPara = "organizationId=" + toOrganizationId + "&transferType=" + transferType + "&deptCode=" + deptCode;
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_RECIIVER%>";
	var dialogWidth = 47;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	var userChk = mainFrm.allUser;
	if(!userChk.checked){
		if (objs) {
			var obj = objs[0];
			document.getElementById("responsibilityUser" + idNumber).value = obj["employeeId"];	
			lineBox.value = obj["userName"];		
		} else {
			document.getElementById("responsibilityUser" + idNumber).value = "";	
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
		var userNames = document.getElementsByName("responsibilityUserName");
		var users = document.getElementsByName("responsibilityUser");
		var count = userNames.length;
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
				userNames[i].value = "";
				users[i].value = "";
			} else {
				userNames[i].value = obj["userName"];
				users[i].value = obj["employeeId"];
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
            	return isValid;
              
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
		cell = cells[2];
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