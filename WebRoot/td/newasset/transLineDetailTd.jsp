<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/td/newasset/headerIncludeTd.jsp"%>
<%
	TdAssetsTransHeaderDTO headerDTO = (TdAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	TdAssetsTransLineDTO lineDTO = null;
	String barcode = "";

    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    String orgId = userAccount.getOrganizationId();
	String toOrgId = headerDTO.getToOrganizationId();
    ServletConfigDTO servletConfig = headerDTO.getServletConfig();
    String provinceCode = servletConfig.getProvinceCode();
    if(transType.equals(AssetsDictConstant.ASS_FREE)){//�ʲ�����
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px>
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
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:46px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="8%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="6%" align="center"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="6%" align="right"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput3" readonly value="<%=lineDTO.getVendorName()%>"></td>
				<td width="6%" align="center"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="4%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="center"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="16%" align="right"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="8%" align="left"><input type="text" name="lineReason" id="lineReason<%=i%>" class="finput" readonly value="<%=lineDTO.getLineReason()%>"></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
			</tr>

<%
			}
		}
	} else if(transType.equals(AssetsDictConstant.ASS_SHARE)){//�ʲ�����
%>

<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px>
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
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:46px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="8%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="6%" align="center"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="6%" align="right"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput3" readonly value="<%=lineDTO.getVendorName()%>"></td>
				<td width="6%" align="center"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="4%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="center"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="16%" align="right"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="8%" align="left"><input type="text" name="lineReason" id="lineReason<%=i%>" class="finput" readonly value="<%=lineDTO.getLineReason()%>"></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
			</tr>

<%
			}
		}
	} else if(transType.equals(AssetsDictConstant.ASS_SELL)){//�ʲ�����
%>

<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px>
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
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:46px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="8%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="6%" align="center"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="6%" align="right"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput3" readonly value="<%=lineDTO.getVendorName()%>"></td>
				<td width="6%" align="center"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="4%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="center"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="16%" align="right"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="8%" align="left"><input type="text" name="lineReason" id="lineReason<%=i%>" class="finput" readonly value="<%=lineDTO.getLineReason()%>"></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
			</tr>


<%
			}
		}
	} else if(transType.equals(AssetsDictConstant.ASS_RENT)){//�ʲ�����
%>

<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px>
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
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:46px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="8%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="6%" align="center"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="6%" align="right"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput3" readonly value="<%=lineDTO.getVendorName()%>"></td>
				<td width="6%" align="center"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="4%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="center"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="16%" align="right"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="8%" align="left"><input type="text" name="lineReason" id="lineReason<%=i%>" class="finput" readonly value="<%=lineDTO.getLineReason()%>"></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
			</tr>


<%
			}
		}
	} else if(transType.equals(AssetsDictConstant.ASS_DONA)){ //�ʲ�����
%>

<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px>
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
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:46px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="8%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="6%" align="center"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="6%" align="right"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput3" readonly value="<%=lineDTO.getVendorName()%>"></td>
				<td width="6%" align="center"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="4%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="center"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="16%" align="right"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="8%" align="left"><input type="text" name="lineReason" id="lineReason<%=i%>" class="finput" readonly value="<%=lineDTO.getLineReason()%>"></td>
				<td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
			</tr>


<%
			}
		}
	} else if(!transType.equals(AssetsDictConstant.ASS_RED)){//���ϻ���
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
<%
    String currRoleName = headerDTO.getCurrRoleName();
    String attribute4 = headerDTO.getAttribute4();
    if ((currRoleName.equals(servletConfig.getMtlAssetsMgr()) && userAccount.getOrganizationId().equals("82")) || (currRoleName.equals("�ʲ����") && userAccount.getOrganizationId().equals("82")) || attribute4.equals("FIND_ASSET")) {
%>
        <table class="headerTable" border="1" width="140%">
        <tr height="20px" onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
<%if (!attribute4.equals("FIND_ASSET")) {%>
            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
<%}%>
            <td align=center width="10%">�ʲ���ǩ</td>
            <td align=center width="10%">�ʲ����</td>
            <td align=center width="10%">�ʲ�����</td>
            <td align=center width="10%">�ʲ��ͺ�</td>
            <td align=center width="9%">�ʲ�ԭֵ</td>
            <td align=center width="5%">��ֵ</td>

            <td align=center width="9%">��������</td>
			<td align=center width="10%">���ڵص�</td>
            <td align=center width="5%">������</td>
            <td align=center width="10%">���β���</td>
            <td align=center width="9%">��ע</td>
        </tr>
		</table>
<%
    } else {
%>
       <table class="headerTable" border="1" width="140%">
        <tr height="20px">
            <td align=center width="10%">�ʲ���ǩ</td>
            <td align=center width="10%">�ʲ����</td>
            <td align=center width="10%">�ʲ�����</td>
            <td align=center width="10%">�ʲ��ͺ�</td>
            <td align=center width="7%">�ʲ�ԭֵ</td>
            <td align=center width="7%">��ֵ</td>
            <td align=center width="7%">���ϳɱ�</td>
            <td align=center width="9%">��������</td>
			<td align=center width="10%">���ڵص�</td>
            <td align=center width="5%">������</td>
            <td align=center width="10%">���β���</td>
            <td align=center width="9%">��ע</td>
        </tr>
		</table>
<%}%>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:46px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
                if ((currRoleName.equals(servletConfig.getMtlAssetsMgr()) && userAccount.getOrganizationId().equals("82")) || (currRoleName.equals("�ʲ����") && userAccount.getOrganizationId().equals("82")) || attribute4.equals("FIND_ASSET")) {
%>
            <tr class="dataTR">
<%if (!attribute4.equals("FIND_ASSET")) {%>
                <td width="3%" align="center"><input type="checkbox" name="subCheck" value="<%=barcode%>" <%=lineDTO.getRemark().equals("")?"checked":""%>></td>
<%}%>
                <td width="10%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
				<td width="10%" align="left"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
				<td width="7%" align="right"><input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
				<td width="7%" align="right"><input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
                <td width="7%" align="right"><input type="text" name="retirementCost" id="retirementCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getRetirementCost()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>             
                <td width="9%" align="center"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput" readonly value="<%=lineDTO.getDatePlacedInService()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
				<td width="10%" align="right"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput3" readonly value="<%=lineDTO.getOldLocationName()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
				<td width="5%" align="right"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
				<td width="10%" align="left"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
                <td width="9%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
            </tr>

<%
               } else {
%>
            <tr class="dataTR" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="10%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
				<td width="10%" align="left"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="7%" align="right"><input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>"></td>
				<td width="7%" align="right"><input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>"></td>
                <td width="7%" align="right"><input type="text" name="retirementCost" id="retirementCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getRetirementCost()%>"></td>
                <td width="9%" align="center"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="10%" align="right"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput3" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="5%" align="right"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="10%" align="left"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>"></td>
                <td width="9%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"></td>
            </tr>
<%
               }
			}
		}
	} else {
		if(transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)){//�����ڵ���
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="120%">
			<tr height="20px">
				<td align=center width="10%">�ʲ���ǩ</td>
				<td align=center width="7%">�ʲ����</td>
				<td align=center width="10%">�ʲ�����</td>
				<td align=center width="10%">�ʲ��ͺ�</td>
				<td align=center width="3%">����</td>
				<td align=center width="13%">�����ص�</td>
				<td align=center width="6%">ԭ������</td>
				<td align=center width="13%">����ص�</td>
				<td align=center width="6%">��������</td>
				<td align=center width="6%">��������</td>
				<td align=center width="13%">ժҪ</td>
			</tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:92%;width:1007px;position:absolute;top:46px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
			if (lineSet != null && !lineSet.isEmpty()) {
				for (int i = 0; i < lineSet.getSize(); i++) {
					lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
					barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="10%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
				<td width="7%" align="left"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="13%" align="center"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="6%" align="right"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="13%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>"></td>
				<td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>"></td>
				<td width="6%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0" class="finput" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
				<td width="13%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
            </tr>
<%
				}
			}
		} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)){//���ż����
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height="20px">
	            <td align=center width="10%">�ʲ���ǩ</td>
	            <td align=center width="7%">�ʲ����</td>
	            <td align=center width="10%">�ʲ�����</td>
	            <td align=center width="10%">�ʲ��ͺ�</td>
	            <td align=center width="3%">����</td>
	            <td align=center width="10%">�����ص�</td>
	            <td align=center width="6%">ԭ������</td>
	            <td align=center width="10%">���벿��</td>
				<td align=center width="10%">����ص�</td>
	            <td align=center width="6%">��������</td>
				<td align="center" width="5%">��������</td>
	            <td align=center width="10%">��ע</td>
	        </tr>
	    </table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:46px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
			if (lineSet != null && !lineSet.isEmpty()) {
				for (int i = 0; i < lineSet.getSize(); i++) {
					lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
					barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="10%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
				<td width="7%" align="left"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="10%" align="center"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="6%" align="right"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="10%" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityDeptName()%>"></td>
				<td width="10%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>"></td>
				<td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>"></td>
				<td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0" class="finput" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
				<td width="10%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
            </tr>
<%
				}
			}
		} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){//���м����
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
	    <table width="350%" border="1" class="headerTable">
	        <tr height="20px">
                <td align=center width="69%" colspan="17">������</td>
	            <td align=center width="20%" colspan="5">���뷽</td>
	            <td align=center width="5%" rowspan="2">��������</td>
	            <td align=center width="5%" rowspan="2">��ע</td>
            </tr>
	        <tr height="20px">
	            <td align=center width="5%">�ʲ���ǩ(��)</td>
	            <td align=center width="5%">�ʲ���ǩ(��)</td>
	            <td align=center width="5%">�ʲ�����</td>
	            <td align=center width="5%">�ʲ��ͺ�</td>
	            <td align=center width="3%">����</td>
	            <td align=center width="3%">ԭֵ</td>
	            <td align=center width="5%">�ۼ��۾�</td>
                <td align=center width="3%">��ֵ׼��</td>
                <td align=center width="3%">��ֵ</td>
                <td align=center width="3%">�ʲ�����</td>
                <td align=center width="5%">����</td>
                <td align=center width="3%">��������</td>
				<td align=center width="5%">��������</td>
	            <td align=center width="5%">�����ص�</td>
	            <td align=center width="3%">ԭ������</td>
	            <td align=center width="5%">ԭ�۾��˻�</td>
	            <td align=center width="3%">ԭ���</td>
	            <td align=center width="5%">���벿��</td>
				<td align=center width="5%">����ص�</td>
				<td align=center width="3%">��������</td>
				<td align=center width="5%">���۾��˻�</td>
	            <td align=center width="3%">�����</td>
            </tr>
	    </table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:66px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="350%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
			if (lineSet != null && !lineSet.isEmpty()) {
				String attribute4 = headerDTO.getAttribute4();
                String inputClass1 = "finput";
				String inputClass2 = "finput2";
				String inputClass3 = "finput";
				String currRoleName = headerDTO.getCurrRoleName();
				if(provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_SHAN) && transferType.equals(AssetsDictConstant.TRANS_BTW_COMP) && toOrgId.equals(orgId)){
					if(currRoleName.equals(servletConfig.getDeptAssetsMgr())){//�����ʲ�����Ա
						inputClass1 = "finputNoEmpty";
						inputClass2 = "finput2";
						inputClass3 = "finput";
					} else if(currRoleName.equals("�ʲ����")){//�ʲ����
						inputClass1 = "finput";
						inputClass2 = "finputNoEmpty2";
						inputClass3 = "finputNoEmpty";
					}
				}
                for (int i = 0; i < lineSet.getSize(); i++) {
					lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
					barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
				<td width="5%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="5%" align="left"><input type="text" name="newBarcode" id="newBarcode<%=i%>" class="finput2" readonly value="<%=lineDTO.getNewBarcode()%>"></td>
				<td width="5%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="5%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>"></td>
				<td width="5%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="depreciation" id="depreciation<%=i%>" class="finput3" readonly value="<%=lineDTO.getDepreciation()%>"></td>
                <td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="impairReserve" id="impairReserve<%=i%>" class="finput3" readonly value="<%=lineDTO.getImpairReserve()%>"></td>
                <td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="scrapValue" id="scrapValue<%=i%>" class="finput3" readonly value="<%=lineDTO.getScrapValue()%>"></td>
                <td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>"></td>
                <td width="5%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="manufacturerName" id="manufacturerName<%=i%>" class="finput3" readonly value="<%=lineDTO.getManufacturerName()%>"></td>
                <td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="5%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>"></td>
				<td width="5%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="5%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldDepreciationAccount" id="oldDepreciationAccount<%=i%>" class="finput" readonly value="<%=lineDTO.getOldDepreciationAccount()%>"></td>
				<td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldFaCategoryCode" id="oldFaCategoryCode<%=i%>" class="finput2" readonly value="<%=lineDTO.getOldFaCategoryCode()%>"></td>
				<td width="5%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityDeptName()%>"></td>
				<td width="5%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="<%=inputClass1%>" readonly value="<%=lineDTO.getAssignedToLocationName()%>" title="���ѡ�����ĵ���ص�" onClick="do_SelectLocation(this)"></td>
				<td width="3%" align="left" ><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="<%=inputClass1%>" readonly value="<%=lineDTO.getResponsibilityUserName()%>" title="���ѡ��������������" onClick="do_SelectPerson(this)"></td>
<%
				if(attribute4.indexOf(AssetsDictConstant.EDIT_ACCOUNT) == -1){
%>
				<%--<td width="5%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="depreciationAccount" id="depreciationAccount<%=i%>" class="finput2" readonly value="<%=lineDTO.getDepreciationAccount()%>"></td>--%>
                <td width="5%"  onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="depreciationAccount" id="depreciationAccount<%=i%>" class="<%=inputClass2%>" readonly value="<%=lineDTO.getDepreciationAccount()%>"></td>
<%
				} else {
%>
				<%--<td width="5%" align="center"><input type="text" name="depreciationAccount" id="depreciationAccount<%=i%>" onClick="do_SelectDepreciationAccount(this)" title="���ѡ�������۾��˻�" class="finput2" readonly value="<%=lineDTO.getDepreciationAccount()%>"></td>--%>
                <td width="5%" align="center"><input type="text" name="depreciationAccount" id="depreciationAccount<%=i%>" class="<%=inputClass2%>" readonly value="<%=lineDTO.getDepreciationAccount()%>" onClick="do_SelectDepreciationAccount(this)" title="���ѡ�������۾��˻�"></td>
<%
				}
%>

				<%--<td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="faCategoryCode" id="faCategoryCode<%=i%>" class="<%=inputClass3%>" readonly value="<%=lineDTO.getFaCategoryCode()%>"></td>--%>
                <td width="3%"><input type="text" name="faCategoryCode" id="faCategoryCode<%=i%>" class="finput" readonly value="<%=lineDTO.getFaCategoryCode()%>"  title="���ѡ�����������" onClick="do_SelectFaCategoryCode(this)"></td>
                <td width="5%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finput2" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
				<td width="5%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>

                <td style="display:none">
					<input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept<%=i%>" value="<%=lineDTO.getOldResponsibilityDept()%>">
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
		}
	}
%>
        </table>
    </div>
