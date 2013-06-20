<%@ page import="com.sino.ams.freeflow.FreeFlowDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	FreeFlowDTO headerDTO = (FreeFlowDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	if(transType.equals(AssetsDictConstant.ASS_SUB)){//�ʲ���ֵ
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="160%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="2%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="4%">��Ԫ���</td>
	            <td align=center width="4%">�ʲ����</td>
                <td align=center width="5%">�ʲ���ǩ��</td>
                <td align=center width="8%">�ʲ�����</td>
	            <td align=center width="8%">��Ӧ��</td>
	            <td align=center width="4%">����ͺ�</td>
	            <td align=center width="4%">��������</td>
	            <td align=center width="5%">�������ð汾</td>
				<td align=center width="5%">������ֵ�汾</td>
	            <td align=center width="4%">ԭֵ</td>
	            <td align=center width="4%">�ۼ��۾�</td>
	            <td align=center width="4%">��ֵ</td>
	            <td align=center width="4%">�����ֵ</td>
	            <td align=center width="8%">��ע</td>
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
                <td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="netUnit" id="netUnit0" class="finput2"  value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput2" readonly value=""></td>
                <td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput2" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName0" class="finput" readonly value=""></td>
				<td width="4%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput" readonly value=""></td>
				<td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="softInuseVersion" id="softInuseVersion0" class="finput"  value=""></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="softDevalueVersion" id="softDevalueVersion0" class="finput"  value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost0" class="finput3" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="depreciation" id="depreciation0" class="finput3" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="deprnCost" id="deprnCost0" class="finput3" readonly value=""></td>
				<td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="prepareDevalue" id="prepareDevalue0" onkeydown="floatOnly(this.value);" class="finputNoEmpty"  value=""></td>
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
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="netUnit" id="netUnit<%=i%>" class="finput2"  value="<%=lineDTO.getNetUnit()%>"></td>
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=lineDTO.getBarcode()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput" readonly value="<%=lineDTO.getVendorName()%>"></td>
				<td width="4%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="softInuseVersion" id="softInuseVersion<%=i%>" class="finput"  value="<%=lineDTO.getSoftInuseVersion()%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="softDevalueVersion" id="softDevalueVersion<%=i%>" class="finput"  value="<%=lineDTO.getSoftDevalueVersion()%>"></td>
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>"></td>
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="depreciation" id="depreciation<%=i%>" class="finput3" readonly value="<%=lineDTO.getDepreciation()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>"></td>
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="prepareDevalue" id="prepareDevalue<%=i%>" onkeydown="floatOnly(this.value);" class="finputNoEmpty"  value="<%=lineDTO.getPrepareDevalue()%>"></td>
				<td width="8%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick=""><input type="text" name="remark" id="remark<%=i%>" class="finput"  value="<%=lineDTO.getRemark()%>"></td>
                <td style="display:none">
                    <input type="hidden" name="assetId" id="assetId0" value="<%=lineDTO.getAssetId()%>">
                </td>
            </tr>
<%
			}
		}
	} else if(transType.equals(AssetsDictConstant.ASS_FREE)){//�ʲ�����
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="8%">�ʲ���ǩ</td>
	            <td align=center width="6%">�ʲ����</td>
	            <td align=center width="10%">�ʲ�����</td>
	            <td align=center width="10%">�ʲ��ͺ�</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="4%">����</td>
				<td align=center width="3%">��λ</td>
	            <td align=center width="16%">���ڵص�</td>
	            <td align=center width="8%">����˵��</td>
	            <td align=center width="8%">����˵��</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:48px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet == null || lineSet.isEmpty()) {
%>
            <tr class="dataTR" onClick="executeClick(this)" style="display:none" title="����鿴�ʲ���ϸ��Ϣ">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
				<td width="8%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput2" readonly value=""></td>
				<td width="6%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput2" readonly value=""></td>
				<td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
				<td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="6%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName0" class="finput3" readonly value=""></td>
				<td width="6%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput2" readonly value=""></td>
				<td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value=""></td>
				<td width="3%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="unitOfMeasure" id="unitOfMeasure0" class="finput" readonly value=""></td>
				<td width="16%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
				<td width="8%" align="left"><input type="text" name="lineReason" id="lineReason0" class="finput" value=""></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark0" class="finput" value=""></td>
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
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput3" readonly value="<%=lineDTO.getVendorName()%>"></td>
				<td width="6%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="16%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="8%" align="left"><input type="text" name="lineReason" id="lineReason<%=i%>" class="finput" value="<%=lineDTO.getLineReason()%>"></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept<%=i%>" value="<%=lineDTO.getOldResponsibilityDept()%>">
					<input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
				</td>
            </tr>
<%
			}
		}
	} else if(!transType.equals(AssetsDictConstant.ASS_RED)){//���ϻ���
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="8%">�ʲ���ǩ</td>
	            <td align=center width="6%">�ʲ����</td>
	            <td align=center width="10%">�ʲ�����</td>
	            <td align=center width="10%">�ʲ��ͺ�</td>
	            <td align=center width="6%">�ʲ�ԭֵ</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="6%">��ֵ</td>
				<td align=center width="25%">���ڵص�</td>
	            <td align=center width="6%">������</td>
	            <td align=center width="15%">���β���</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:48px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet == null || lineSet.isEmpty()) {
%>
            <tr class="dataTR" onClick="executeClick(this)" style="display:none" title="����鿴�ʲ���ϸ��Ϣ">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
				<td width="8%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput2" readonly value=""></td>
				<td width="6%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput2" readonly value=""></td>
				<td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
				<td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="6%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost0" class="finput3" readonly value=""></td>
				<td width="6%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput2" readonly value=""></td>
				<td width="6%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="deprnCost" id="deprnCost0" class="finput3" readonly value=""></td>
				<td width="25%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
				<td width="6%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName0" class="finput" readonly value=""></td>
				<td width="15%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName0" class="finput" readonly value=""></td>
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
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>"></td>
				<td width="6%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>"></td>
				<td width="25%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="15%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept<%=i%>" value="<%=lineDTO.getOldResponsibilityDept()%>">
					<input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
				</td>
            </tr>
<%
			}
		}
	} else {
		if(transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)){//�����ڵ���
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="120%">
			<tr height="20px" onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
				<td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
				<td align=center width="8%">�ʲ���ǩ</td>
				<td align=center width="7%">�ʲ����</td>
				<td align=center width="8%">�ʲ�����</td>
				<td align=center width="8%">�ʲ��ͺ�</td>
                <td align=center width="8%">ԭ�ʲ�����</td>
                <td align=center width="8%">ԭ�ʲ��ͺ�</td>
				<td align=center width="3%">����</td>
				<td align=center width="10%">�����ص�</td>
				<td align=center width="6%">ԭ������</td>
				<td align=center width="10%">����ص�</td>
				<td align=center width="6%">��������</td>
				<td align=center width="8%">��������</td>
				<td align=center width="7%">ժҪ</td>
				<td style="display:none">�������ֶ�</td>
			</tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:48px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
			if (lineSet == null || lineSet.isEmpty()) {
%>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="display:none">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
				<td width="8%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput" readonly value=""></td>
				<td width="7%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="itemName" id="itemName0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="itemSpec" id="itemSpec0" class="finput" readonly value=""></td>
				<td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
				<td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value=""></td>
				<td width="10%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
				<td width="6%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName0" class="finput" readonly value=""></td>
				<td width="10%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName0" class="noEmptyInput" class="finput" readonly value="" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"></td>
				<td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName0" class="noEmptyInput" class="finput" readonly value="" onClick="do_SelectPerson(this)" title="���ѡ��������������" style="cursor:hand"></td>
				<td width="8%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="" onclick="gfPop.fPopCalendar(lineTransDate0)" title="���ѡ�����ĵ�������" onBlur="do_SetLineTransDate(this)"></td>
				<td width="7%" align="left"><input type="text" name="remark" id="remark0" class="finput" value=""></td>
                <td style="display:none"><input type="hidden" name="oldLocation" id="oldLocation0" value=""></td>
			    <td style="display:none"><input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser0" value=""></td>
				<td style="display:none"><input type="hidden" name="assignedToLocation" id="assignedToLocation0" value=""></td>
				<td style="display:none"><input type="hidden" name="responsibilityUser" id="responsibilityUser0" value=""></td>
				<td style="display:none"><input type="hidden" name="addressId" id="addressId0" value=""></td>
            </tr>
<%
			} else {
				AmsAssetsTransLineDTO lineDTO = null;
				String barcode = "";
				for (int i = 0; i < lineSet.getSize(); i++) {
					lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
					barcode = lineDTO.getBarcode();
%>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="cursor:hand">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
				<td width="8%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
				<td width="7%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
                <td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="itemName" id="itemName<%=i%>" class="finput" readonly value="<%=lineDTO.getItemName()%>"></td>
                <td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="itemSpec" id="itemSpec<%=i%>" class="finput" readonly value="<%=lineDTO.getItemSpec()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="10%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="10%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" class="noEmptyInput" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"></td>
				<td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" class="noEmptyInput" readonly value="<%=lineDTO.getResponsibilityUserName()%>" onClick="do_SelectPerson(this)"  title="���ѡ��������������" style="cursor:hand"></td>
				<td width="8%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="<%=lineDTO.getLineTransDate()%>" onclick="gfPop.fPopCalendar(lineTransDate0)" title="���ѡ�����ĵ�������" onBlur="do_SetLineTransDate(this)"></td>
				<td width="7%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
                <td style="display:none"><input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>"></td>
				<td style="display:none"><input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>"></td>
				<td style="display:none"><input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>" value="<%=lineDTO.getAssignedToLocation()%>"></td>
				<td style="display:none"><input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>"></td>
				<td style="display:none"><input type="hidden" name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>"></td>
            </tr>
<%
				}
			}
		} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)){//���ż����
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="8%">�ʲ���ǩ</td>
	            <td align=center width="7%">�ʲ����</td>
	            <td align=center width="8%">�ʲ�����</td>
                <td align=center width="8%">�ʲ��ͺ�</td>
                <td align=center width="8%">ԭ�ʲ�����</td>
	            <td align=center width="8%">ԭ�ʲ��ͺ�</td>
	            <td align=center width="3%">����</td>
	            <td align=center width="8%">�����ص�</td>
	            <td align=center width="5%">ԭ������</td>
	            <td align=center width="8%">���벿��</td>
				<td align=center width="8%">����ص�</td>
	            <td align=center width="5%">��������</td>
				<td align="center" width="5%">��������</td>
	            <td align=center width="8%">��ע</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
	    </table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:48px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
			if (lineSet == null || lineSet.isEmpty()) {
%>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="display:none">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
				<td width="8%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput" readonly value=""></td>
				<td width="7%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="itemName" id="itemName0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="itemSpec" id="itemSpec0" class="finput" readonly value=""></td>
				<td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
				<td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value=""></td>
				<td width="8%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName0" class="finput" readonly value=""></td>
				<td width="8%" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName0" readonly value="" class="noEmptyInput" class="finput" onClick="do_SelectDept(this)" class="noEmptyInput" title="���ѡ�����ĵ��벿��" style="cursor:hand"></td>
				<td width="8%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName0"  readonly value="" class="noEmptyInput" class="finput" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"></td>
				<td width="5%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName0" class="finput" readonly value="" onClick="do_SelectPerson(this)" title="���ѡ��������������" style="cursor:hand"></td>
				<td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="" onclick="gfPop.fPopCalendar(lineTransDate0)" title="���ѡ�����ĵ�������" onBlur="do_SetLineTransDate(this)"></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark0" class="finput" value=""></td>
				<td style="display:none"><input type="hidden" name="oldLocation" id="oldLocation0" value=""></td>
				<td style="display:none"><input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser0" value=""></td>
                <td style="display:none"><input type="hidden" name="responsibilityDept" id="responsibilityDept0" value=""></td>
                <td style="display:none"><input type="hidden" name="assignedToLocation" id="assignedToLocation0" value=""></td>
			    <td style="display:none"><input type="hidden" name="responsibilityUser" id="responsibilityUser0" value=""></td>
				<td style="display:none"><input type="hidden" name="addressId" id="addressId0" value=""></td>
            </tr>
<%
			} else {
				AmsAssetsTransLineDTO lineDTO = null;
				String barcode = "";
				for (int i = 0; i < lineSet.getSize(); i++) {
					lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
					barcode = lineDTO.getBarcode();
%>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="cursor:hand">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
				<td width="8%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
				<td width="7%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
                <td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="itemName" id="itemName<%=i%>" class="finput" readonly value="<%=lineDTO.getItemName()%>"></td>
                <td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="itemSpec" id="itemSpec<%=i%>" class="finput" readonly value="<%=lineDTO.getItemSpec()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="8%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="8%" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>"  readonly value="<%=lineDTO.getResponsibilityDeptName()%>" class="noEmptyInput" class="finput" onClick="do_SelectDept(this)" title="���ѡ�����ĵ��벿��" style="cursor:hand"></td>
				<td width="8%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="noEmptyInput" class="finput"  readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"></td>
				<td width="5%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>" onClick="do_SelectPerson(this)"  title="���ѡ��������������" style="cursor:hand"></td>
				<td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="<%=lineDTO.getLineTransDate()%>" onclick="gfPop.fPopCalendar(lineTransDate0)" title="���ѡ�����ĵ�������" onBlur="do_SetLineTransDate(this)"></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
					<input type="hidden" name="responsibilityDept" id="responsibilityDept<%=i%>" value="<%=lineDTO.getResponsibilityDept()%>">
                    <input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>" value="<%=lineDTO.getAssignedToLocation()%>">
					<input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>">
					<input type="hidden" name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>">
				</td>
            </tr>
<%
				}
			}
		} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){//���м����
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
	    <table width="250%" border="1" class="headerTable">
	        <tr height=20px onClick="executeClick(this)" id="headerTr" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="1%" rowspan="2"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="64%" colspan="14">������</td>
	            <td align=center width="25%" colspan="5">���뷽</td>
	            <td align=center width="5%" rowspan="2">��������</td>
	            <td align=center width="5%" rowspan="2">��ע</td>
				<td style="display:none" rowspan="2">�������ֶ�</td>
	        </tr>
	        <tr height=20px onClick="headerTr.click()" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="5%">�ʲ���ǩ</td>
	            <td align=center width="5%">�ʲ����</td>
	            <td align=center width="5%">�ʲ�����</td>
	            <td align=center width="5%">�ʲ��ͺ�</td>
	            <td align=center width="3%">����</td>
	            <td align=center width="3%">ԭֵ</td>
	            <td align=center width="5%">�ۼ��۾�</td>
	            <td align=center width="3%">��ֵ</td>
	            <td align=center width="5%">��������</td>
				<td align=center width="5%">��������</td>
	            <td align=center width="5%">�����ص�</td>
	            <td align=center width="5%">ԭ������</td>
	            <td align=center width="5%">ԭ�۾��˻�</td>
	            <td align=center width="5%">ԭ���</td>
	            <td align=center width="5%">���벿��</td>
				<td align=center width="5%">����ص�</td>
				<td align=center width="5%">��������</td>
				<td align=center width="5%">���۾��˻�</td>
	            <td align=center width="5%">�����</td>
	        </tr>
	    </table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:66px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="250%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
			if (lineSet == null || lineSet.isEmpty()) {
%>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="display:none">
				<td width="1%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
				<td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput" readonly value=""></td>
				<td width="5%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput" readonly value=""></td>
				<td width="5%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
				<td width="5%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value=""></td>
				<td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost0" class="finput3" readonly value=""></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="depreciation" id="depreciation0" class="finput3" readonly value=""></td>
				<td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="scrapValue" id="scrapValue0" class="finput3" readonly value=""></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput2" readonly value=""></td>
				<td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName0" class="finput" readonly value=""></td>
				<td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName0" class="finput" readonly value=""></td>
				<td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldDepreciationAccount" id="oldDepreciationAccount0" class="finput" readonly value=""></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldFaCategoryCode" id="oldFaCategoryCode0" class="finput" readonly value=""></td>
				<td width="5%" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName0" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="" class="noEmptyInput" onClick="do_SelectDept(this)" title="���ѡ�����ĵ��벿��"></td>
				<td width="5%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName0" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�"></td>
				<td width="5%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName0" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="" onClick="do_SelectPerson(this)" title="���ѡ��������������"></td>
				<td width="5%" align="center"><input type="text" name="depreciationAccount" id="depreciationAccount0" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="" onClick="do_SelectDepreciationAccount(this)" title="���ѡ�������۾��˻�"></td>
				<td width="5%" align="right"><input type="text" name="faCategoryCode" id="faCategoryCode0" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly onClick="do_SelectFaCategoryCode(this)" title="���ѡ�����������"></td>
				<td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0" class="finput2" readonly value="" onclick="gfPop.fPopCalendar(lineTransDate0)" title="���ѡ�����ĵ�������" onBlur="do_SetLineTransDate(this)"></td>
				<td width="5%" align="left"><input type="text" name="remark" id="remark0" class="finput" value=""></td>
				<td style="display:none"><input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept0" value=""></td>
				<td style="display:none"><input type="hidden" name="oldLocation" id="oldLocation0" value=""></td>
				<td style="display:none"><input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser0" value=""></td>
                <td style="display:none"><input type="hidden" name="responsibilityDept" id="responsibilityDept0" value=""></td>
                <td style="display:none"><input type="hidden" name="assignedToLocation" id="assignedToLocation0" value=""></td>
				<td style="display:none"><input type="hidden" name="responsibilityUser" id="responsibilityUser0" value=""></td>
				<td style="display:none"><input type="hidden" name="addressId" id="addressId0" value=""></td>
            </tr>
<%
			} else {
				AmsAssetsTransLineDTO lineDTO = null;
				String barcode = "";
				for (int i = 0; i < lineSet.getSize(); i++) {
					lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
					barcode = lineDTO.getBarcode();
%>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="cursor:hand">
				<td width="1%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="depreciation" id="depreciation<%=i%>" class="finput3" readonly value="<%=lineDTO.getDepreciation()%>"></td>
				<td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="scrapValue" id="scrapValue<%=i%>" class="finput3" readonly value="<%=lineDTO.getScrapValue()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldDepreciationAccount" id="oldDepreciationAccount<%=i%>" class="finput" readonly value="<%=lineDTO.getOldDepreciationAccount()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldFaCategoryCode" id="oldFaCategoryCode<%=i%>" class="finput" readonly value="<%=lineDTO.getOldFaCategoryCode()%>"></td>
				<td width="5%" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="<%=lineDTO.getResponsibilityDeptName()%>" class="noEmptyInput" onClick="do_SelectDept(this)" title="���ѡ�����ĵ��벿��"></td>
				<td width="5%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�"></td>
				<td width="5%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="<%=lineDTO.getResponsibilityUserName()%>" onClick="do_SelectPerson(this)"  title="���ѡ��������������"></td>
				<td width="5%" align="center"><input type="text" name="depreciationAccount" id="depreciationAccount<%=i%>" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="<%=lineDTO.getDepreciationAccount()%>" onClick="do_SelectDepreciationAccount(this)" title="���ѡ�������۾��˻�"></td>
				<td width="5%" align="right"><input type="text" name="faCategoryCode" id="faCategoryCode<%=i%>" style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly value="<%=lineDTO.getFaCategoryCode()%>" onClick="do_SelectFaCategoryCode(this)" title="���ѡ�����������"></td>
				<td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finput2" readonly value="<%=lineDTO.getLineTransDate()%>" onclick="gfPop.fPopCalendar(lineTransDate<%=i%>)" title="���ѡ�����ĵ�������"  onBlur="do_SetLineTransDate(this)"></td>
				<td width="5%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
				<td style="display:none">
				<td style="display:none"><input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept<%=i%>" value="<%=lineDTO.getOldResponsibilityDept()%>"></td>
				<td style="display:none"><input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>"></td>
				<td style="display:none"><input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>"></td>
				<td style="display:none"><input type="hidden" name="responsibilityDept" id="responsibilityDept<%=i%>" value="<%=lineDTO.getResponsibilityDept()%>"></td>
                <td style="display:none"><input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>" value="<%=lineDTO.getAssignedToLocation()%>"></td>
				<td style="display:none"><input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>"></td>
				<td style="display:none"><input type="hidden" name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>"></td>
            </tr>
<%
				}
			}
		}
	}
%>
        </table>
    </div>