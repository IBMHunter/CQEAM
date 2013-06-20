<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	boolean hasData = (lineSet != null && !lineSet.isEmpty());
	AmsAssetsTransLineDTO lineDTO = null;
	String barcode = "";

	if(transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)){//�����ڵ���
%>
	<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:25px;left:0px;width:100%" class="crystalScroll">
		<table class="eamHeaderTable" border="1" width="140%">
	        <tr height="20px">
	            <td align=center width="10%">�ʲ���ǩ</td>
	            <td align=center width="7%">�ʲ����</td>
	            <td align=center width="10%">�ʲ�����</td>
	            <td align=center width="10%">�ʲ��ͺ�</td>
	            <td align=center width="3%">����</td>
	            <td align=center width="16%">�����ص�</td>
	            <td align=center width="6%">ԭ������</td>
	            <td align=center width="16%">����ص�</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="13%">ժҪ</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
	    </table>
	</div>
	<div style="overflow:scroll;height:88%;width:100%;position:absolute;top:48px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (hasData) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand">
				<td width="10%" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
				<td width="7%" onClick="do_ShowDetail('<%=barcode%>')"align="left" ><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" onClick="do_ShowDetail('<%=barcode%>')"align="left" ><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" onClick="do_ShowDetail('<%=barcode%>')"align="left" ><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" onClick="do_ShowDetail('<%=barcode%>')" align="right" ><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="16%" onClick="do_ShowDetail('<%=barcode%>')" ><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="6%" onClick="do_ShowDetail('<%=barcode%>')" align="right" ><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="16%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="noEmptyInput" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
				<td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="noEmptyInput" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>" onClick="do_SelectPerson(this)"  title="���ѡ��������������" style="cursor:hand"></td>
				<td width="13%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
					<input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>" value="<%=lineDTO.getAssignedToLocation()%>">
					<input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>">
					<input type="hidden"  name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>">
					<input type="hidden"  name="lineId" id="lineId<%=i%>" value="<%=lineDTO.getLineId()%>">
				</td>
            </tr>
<%
			}
		}
	} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)){//���ż����
%>
	<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:25px;left:0px;width:100%" class="crystalScroll">
		<table class="eamHeaderTable" border="1" width="140%">
	        <tr height="20px">
	            <td align=center width="10%">�ʲ���ǩ</td>
	            <td align=center width="7%">�ʲ����</td>
	            <td align=center width="10%">�ʲ�����</td>
	            <td align=center width="10%">�ʲ��ͺ�</td>
	            <td align=center width="3%">����</td>
	            <td align=center width="11%">�����ص�</td>
	            <td align=center width="6%">ԭ������</td>
	            <td align=center width="10%">���벿��</td>
				<td align=center width="11%">����ص�</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="13%">ժҪ</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
	    </table>
	</div>
	<div style="overflow:scroll;height:88%;width:100%;position:absolute;top:46px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (hasData) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand">
				<td width="10%" onClick="do_ShowDetail('<%=barcode%>')" ><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
				<td width="7%" onClick="do_ShowDetail('<%=barcode%>')" align="left" ><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="10%" onClick="do_ShowDetail('<%=barcode%>')" align="left" ><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" onClick="do_ShowDetail('<%=barcode%>')" align="left" ><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" onClick="do_ShowDetail('<%=barcode%>')" align="right" ><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="11%" onClick="do_ShowDetail('<%=barcode%>')" ><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="6%" onClick="do_ShowDetail('<%=barcode%>')" align="right" ><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="10%" onClick="do_ShowDetail('<%=barcode%>')" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityDeptName()%>"></td>
				<td width="11%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
				<td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getResponsibilityUserName()%>" onClick="do_SelectPerson(this)"  title="���ѡ��������������" style="cursor:hand"></td>
				<td width="13%" align="left" onClick="do_ShowDetail('<%=barcode%>')" ><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
					<input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>" value="<%=lineDTO.getAssignedToLocation()%>">
					<input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>">
					<input type="hidden" name="responsibilityDept" id="responsibilityDept<%=i%>" value="<%=lineDTO.getResponsibilityDept()%>">
					<input type="hidden"  name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>">
					<input type="hidden"  name="lineId" id="lineId<%=i%>" value="<%=lineDTO.getLineId()%>">
				</td>
            </tr>
<%
			}
		}
	} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){//���м����
%>
	<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:25px;left:0px;width:100%" class="crystalScroll">
	    <table width="350%" border="1" class="eamDbHeaderTable">
	        <tr height="20px">
	            <td align=center width="64%" colspan="14">������</t��������	            <td align=center width="25%" colspan="5">���뷽</td>
	            <td align=center width="5%" rowspan="2">��������</td>
	            <td align=center width="5%" rowspan="2">��ע</td>
	        </tr>
	        <tr height=20px onClick="headerTr.click()" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ" class="eamDbHeaderTr">
	            <td align=center width="5%">�ʲ���ǩ(��)</td>
	            <td align=center width="5%">�ʲ���ǩ(��)</td>
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
	<div style="overflow:scroll;height:88%;width:100%;position:absolute;top:66px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="350%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (hasData) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR">
				<td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="newBarcode" id="newBarcode<%=i%>" class="finput2" readonly value="<%=lineDTO.getNewBarcode()%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="5%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="depreciation" id="depreciation<%=i%>" class="finput3" readonly value="<%=lineDTO.getDepreciation()%>"></td>
				<td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="scrapValue" id="scrapValue<%=i%>" class="finput3" readonly value="<%=lineDTO.getScrapValue()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldDepreciationAccount" id="oldDepreciationAccount<%=i%>" class="finput2" readonly value="<%=lineDTO.getOldDepreciationAccount()%>"></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldFaCategoryCode" id="oldFaCategoryCode<%=i%>" class="finput2" readonly value="<%=lineDTO.getOldFaCategoryCode()%>"></td>
				<td width="5%" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getResponsibilityDeptName()%>"></td>
				<td width="5%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
				<td width="5%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getResponsibilityUserName()%>" onClick="do_SelectPerson(this)"  title="���ѡ��������������"></td>
				<td width="5%" align="center"><input type="text" name="depreciationAccount" id="depreciationAccount<%=i%>" class="finputNoEmpty2" readonly value="<%=lineDTO.getDepreciationAccount()%>" onClick="do_SelectDepreciationAccount(this)" title="���ѡ�������۾��˻�"></td>
				<td width="5%" align="right"><input type="text" name="faCategoryCode" id="faCategoryCode<%=i%>" class="finput2" readonly value="<%=lineDTO.getFaCategoryCode()%>" onClick="do_SelectFaCategoryCode(this)" title="���ѡ�����������"></td>
				<td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finput2" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
				<td width="5%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept<%=i%>" value="<%=lineDTO.getOldResponsibilityDept()%>">
					<input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
					<input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>" value="<%=lineDTO.getAssignedToLocation()%>">
					<input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>">
					<input type="hidden" name="responsibilityDept" id="responsibilityDept<%=i%>" value="<%=lineDTO.getResponsibilityDept()%>">
					<input type="hidden" name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>">
					<input type="hidden"  name="lineId" id="lineId<%=i%>" value="<%=lineDTO.getLineId()%>">
				</td>
            </tr>
<%
			}
		}
	}
%>
        </table>
    </div>
