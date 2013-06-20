<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-3-31
  Time: 11:18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%
    AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
    String transType = headerDTO.getTransType();
    String transferType = headerDTO.getTransferType();
    DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    AmsAssetsTransLineDTO lineDTO = null;
    String barcode = "";

    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    int orgId = userAccount.getOrganizationId();
    int toOrgId = headerDTO.getToOrganizationId();
    ServletConfigDTO servletConfig = headerDTO.getServletConfig();
    String provinceCode = servletConfig.getProvinceCode();
%>
<%
    if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) {//�����ڵ���
%>
<div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:0px;left:0px;width:100%">
    <table class="eamHeaderTable" border="1" width="120%">
        <tr height="23px">
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
<div id="dataDiv" style="overflow:scroll;height:92%;width:100%;position:absolute;top:20px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
        <tr class="dataTR" style="cursor:pointer" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
            <td width="10%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
            <td width="7%" align="left"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
            <td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
            <td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
            <td width="3%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
            <td width="13%" align="center"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
            <td width="6%" align="right"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
            <td width="13%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>"></td>
            <td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>"></td>
            <td width="6%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finput" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
            <td width="13%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>
            <input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>">
        </tr>
<%
			}
		}
%>
    </table>
</div>
<%
	} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)){//���ż����
        if(headerDTO.getAttribute1().equals("DEPT")){
%>
        <div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:22px;left:0px;width:100%">
<%
        } else {
%>
        <div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:0px;left:0px;width:100%">
<%
        }
%>
            <table class="headerTable" border="1" width="140%" onClick="executeClick(this)" style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
                <tr height="23px">
            		<td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
                    <td align=center width="10%">�ʲ���ǩ</td>
                    <td align=center width="7%">�ʲ����</td>
                    <td align=center width="10%">�ʲ�����</td>
                    <td align=center width="10%">�ʲ��ͺ�</td>
                    <td align=center width="3%">����</td>
                    <td align=center width="10%">�����ص�</td>
                    <td align=center width="6%">ԭ������</td>
                    <td align=center width="10%">���벿��</td>
                    <td align=center width="12%">����ص�</td>
                    <td align=center width="6%">��������</td>
                    <td align="center" width="5%">��������</td>
                    <td align=center width="5%">��ע</td>
                </tr>
            </table>
        </div>
<%
    	if(headerDTO.getAttribute1().equals("DEPT")){
%>
        	<div id="dataDiv" style="overflow:scroll;height:88%;width:100%;position:absolute;top:42px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%
    	} else {
%>
        	<div id="dataDiv" style="overflow:scroll;height:88%;width:100%;position:absolute;top:20px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%
    	}
%>
            <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
				String attribute1 = headerDTO.getAttribute1();
%>
                <tr class="dataTR" style="cursor:pointer" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
                    <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
                    <td width="10%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
                    <td width="7%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
                    <td width="10%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
                    <td width="10%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
                    <td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
                    <td width="10%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
                    <td width="6%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
                    <td width="10%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityDeptName()%>"></td>
<%
				if (attribute1.equals("DEPT")) {
%>
                    <td width="12%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getAssignedToLocationName()%>" title="���ѡ�����ĵ���ص�" onClick="do_SelectLocation(this)" style="width:85%"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a> </td>
                    <td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getResponsibilityUserName()%>" title="���ѡ��������������" onClick="do_SelectPerson(this)"></td>
<%
                } else {
%>
                    <td width="12%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>"></td>
                    <td width="6%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>"></td>
<%
                }
%>
                    <td width="5%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="lineTransDate" id="lineTransDate0" class="finput" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
                    <td width="5%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>

                    <td style="display:none">
                        <input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
                        <input type="hidden" name="responsibilityDept" id="responsibilityDept<%=i%>" value="<%=lineDTO.getResponsibilityDept()%>">
                        <input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>" value="<%=lineDTO.getAssignedToLocation()%>">
                        <input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>">
                        <input type="hidden" name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>">
                    </td>
                </tr>
<%
			}
		}
%>
    </table>
</div>
<%
	} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) {//���м����
		if(headerDTO.getAttribute2().equals(AssetsDictConstant.EDIT_ACCOUNT)){
%>
        <div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:22px;left:0px;width:100%">
<%		
		} else {
%>
        <div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:0px;left:0px;width:100%">
<%		
		}

%>
        <table width="300%" border="1" class="eamDbHeaderTable">
            <tr height="22px">
                <td align=center width="64%" colspan="17">������</td>
                <td align=center width="27%" colspan="5">���뷽</td>
                <td align=center width="3%" rowspan="2">��������</td>
                <td align=center width="7%" rowspan="2">��ע</td>
            </tr>
            <tr height="22px">
                <td align=center width="3%">�ʲ���ǩ(��)</td>
                <td align=center width="3%">�ʲ���ǩ(��)</td>
                <td align=center width="5%">�ʲ�����</td>

                <td align=center width="5%">����ͺ�</td>
                <td align=center width="2%">����</td>
                <td align=center width="2%">ԭֵ</td>

                <td align=center width="2%">�ۼ��۾�</td>
                <td align=center width="2%">��ֵ׼��</td>
                <td align=center width="2%">��ֵ</td>

                <td align=center width="2%">�ʲ�����</td>
                <td align=center width="5%">����</td>
                <td align=center width="3%">��������</td>

                <td align=center width="6%">��������</td>
                <td align=center width="6%">�����ص�</td>
                <td align=center width="3%">ԭ������</td>

                <td align=center width="6%">ԭ�۾��˻�</td>
                <td align=center width="6%">ԭ���</td>

                <td align=center width="6%">���벿��</td>

                <td align=center width="8%">����ص�</td>
                <td align=center width="3%">��������</td>
                <td align=center width="4%">���۾��˻�</td>
                <td align=center width="6%">�����</td>
            </tr>
        </table>
        </div>
<%
		if(headerDTO.getAttribute2().equals(AssetsDictConstant.EDIT_ACCOUNT)){
%>
		<div id="dataDiv" style="overflow:scroll;height:58%;width:100%;position:absolute;top:62px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%
		} else {
%>
        <div id="dataDiv" style="overflow:scroll;height:58%;width:100%;position:absolute;top:40px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%
		}
%>
        <table id="dataTable" width="300%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
        if (lineSet != null && !lineSet.isEmpty()) {
            String attribute2 = headerDTO.getAttribute2();
            String inputClass1 = "finput";
            String inputClass2 = "finput2";
            String inputClass3 = "finput";
            if (transferType.equals(AssetsDictConstant.TRANS_BTW_COMP) && toOrgId == orgId) {
                if (headerDTO.getAttribute1().equals("DEPT")) {//�����ʲ�����Ա
                    inputClass1 = "finputNoEmpty";
                    inputClass2 = "finput2";
                    inputClass3 = "finput";
                } else if (headerDTO.getAttribute1().equals("ACCOUNT")) {//�ʲ����
                    inputClass1 = "finput";
                    inputClass2 = "finputNoEmpty2";
                    inputClass3 = "finputNoEmpty";
                }
            }
            for (int i = 0; i < lineSet.getSize(); i++) {
                lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
                barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" style="cursor:pointer" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
                <td width="3%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
                <td width="3%" align="left"><input type="text" name="newBarcode" id="newBarcode<%=i%>" class="finput2" readonly value="<%=lineDTO.getNewBarcode()%>"></td>
                <td width="5%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>

                <td width="5%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
                <td width="2%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
                <td width="2%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>"></td>

                <td width="2%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="depreciation" id="depreciation<%=i%>" class="finput3" readonly value="<%=lineDTO.getDepreciation()%>"></td>
                <td width="2%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="impairReserve" id="impairReserve<%=i%>" class="finput3" readonly value="<%=lineDTO.getImpairReserve()%>"></td>
                <td width="2%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="scrapValue" id="scrapValue<%=i%>" class="finput3" readonly value="<%=lineDTO.getScrapValue()%>"></td>

                <td width="2%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>"></td>
                <td width="5%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="manufacturerName" id="manufacturerName<%=i%>" class="finput3" readonly value="<%=lineDTO.getManufacturerName()%>"></td>
                <td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>

                <td width="6%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>"></td>
                <td width="6%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
                <td width="3%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>

                <td width="6%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldDepreciationAccount" id="oldDepreciationAccount<%=i%>" class="finput" readonly value="<%=lineDTO.getOldDepreciationAccount()%>"></td>
                <td width="6%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldFaCategoryCode" id="oldFaCategoryCode<%=i%>" class="finput2" readonly value="<%=lineDTO.getOldFaCategoryCode()%>"></td>
                <td width="6%" align="right" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityDeptName()%>"></td>

                <td width="4%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="<%=inputClass1%>" readonly value="<%=lineDTO.getAssignedToLocationName()%>" title="���ѡ�����ĵ���ص�" onClick="do_SelectLocation(this)" style="width:85%"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
                <td width="3%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="<%=inputClass1%>" readonly value="<%=lineDTO.getResponsibilityUserName()%>" title="���ѡ��������������" onClick="do_SelectPerson(this)"></td>
<%
                    if (attribute2.indexOf(AssetsDictConstant.EDIT_ACCOUNT) == -1) {
%>
                <td width="4%" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="depreciationAccount" id="depreciationAccount<%=i%>" class="<%=inputClass2%>" readonly value="<%=lineDTO.getDepreciationAccount()%>"></td>
<%
                	} else {
%>
                <td width="4%" align="center"><input type="text" name="depreciationAccount" id="depreciationAccount<%=i%>" class="<%=inputClass2%>" readonly value="<%=lineDTO.getDepreciationAccount()%>" onClick="do_SelectDepreciationAccount(this)" title="���ѡ�������۾��˻�"></td>
<%
                    }
%>
                <td width="6%"><input type="text" name="faCategoryCode" id="faCategoryCode<%=i%>" class="finput" readonly value="<%=lineDTO.getFaCategoryCode()%>"></td>
                <td width="3%" align="center" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finput2" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
                <td width="7%" align="left" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="remark" id="remark<%=i%>" class="finput" readonly value="<%=lineDTO.getRemark()%>"></td>

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
%>
    </table>
    <table width="300%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
        <tr height="23px">
            <td align=right width="16%" colspan="4" rowspan="2">�ϼ�</td>
            <td align=center width="2%">����</td>
            <td align=center width="2%">ԭֵ</td>

            <td align=center width="2%">�ۼ��۾�</td>
            <td align=center width="2%">��ֵ׼��</td>
            <td align=center width="2%">��ֵ</td>

            <td align=center width="2%">�ʲ�����</td>
            <td align=center width="5%"></td>
            <td align=center width="3%"></td>

            <td align=center width="6%"></td>
            <td align=center width="6%"></td>
            <td align=center width="3%"></td>

            <td align=center width="6%"></td>
            <td align=center width="6%"></td>

            <td align=center width="8%"></td>

            <td align=center width="6%"></td>
            <td align=center width="3%"></td>
            <td align=center width="4%"></td>
            <td align=center width="6%"></td>
            <td align=center width="3%"></td>
            <td align=center width="7%"></td>
        </tr>
        <tr height="23px">
            <td align=center width="2%"><input readonly="readonly" class="finput2" id="numValue" /></td>
            <td align=center width="2%"><input readonly="readonly" class="finput2" id="yuanzhiValue" /></td>

            <td align=center width="2%"><input readonly="readonly" class="finput2" id="ljzjValue" /></td>
            <td align=center width="2%"><input readonly="readonly" class="finput2" id="jzzbValue" /></td>
            <td align=center width="2%"><input readonly="readonly" class="finput2" id="canzhiValue"/></td>

            <td align=center width="2%"><input readonly="readonly" class="finput2" id="jingerValue" /></td>
            <td align=center width="5%"></td>
            <td align=center width="3%"></td>

            <td align=center width="6%"></td>
            <td align=center width="6%"></td>
            <td align=center width="3%"></td>

            <td align=center width="6%"></td>
            <td align=center width="6%"></td>

            <td align=center width="8%"></td>

            <td align=center width="6%"></td>
            <td align=center width="3%"></td>
            <td align=center width="4%"></td>
            <td align=center width="6%"></td>
            <td align=center width="3%"></td>
            <td align=center width="7%"></td>
        </tr>        
    </table>
</div>
<%
	} else if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT_RFU)) {//��������(������)
%>
<div id="headDiv" style="overflow-y:hidden;overflow-x:hidden;position:absolute;top:0px;left:0px;width:100%">
    <table class="eamHeaderTable" border="1" width="120%">
        <tr height="23px">
            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
            <td align=center width="14%">��������</td>
            <td align=center width=" 8%">��ǩ��</td>
            <td align=center width=" 7%">�ʲ����</td>
            <td align=center width="12%">�ʲ�����</td>
            <td align=center width="10%">�����ͺ�</td>
            <td align=center width=" 8%">����</td>
            <td align=center width=" 5%">��λ</td>
            <td align=center width="18%">�����ص�</td>
            <td align=center width="18%">����ص�</td>
            <td style="display:none">�������ֶ�</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:92%;width:100%;position:absolute;top:20px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet != null && !lineSet.isEmpty()) {
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
        <tr id="model" class="dataTR" style="cursor:pointer">
            <td width=" 3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
            <td width="14%" align="left  " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="transNo" id="transNo<%=i%>" class="finput" readonly value="<%=lineDTO.getTransNo()%>"></td>
            <td width=" 8%" align="left  " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
            <td width=" 7%" align="left  " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
            <td width="12%" align="left  " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
            <td width="10%" align="left  " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
            <td width=" 8%" align="right " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
            <td width=" 5%" align="left  " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
            <td width="18%" align="left  " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="fromObjectName" id="fromObjectName<%=i%>" class="finput" readonly value="<%=lineDTO.getFromObjectName()%>"></td>
            <td width="18%" align="left  " title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="toObjectName" id="toObjectName<%=i%>" class="finput" readonly value="<%=lineDTO.getToObjectName()%>"></td>

            <td style="display:none"><input type="hidden" name="transTypeDefine" id="transTypeDefine<%=i%>" value="<%=lineDTO.getTransTypeDefine()%>"></td>
            <td style="display:none"><input type="hidden" name="transId" id="transId<%=i%>" value="<%=lineDTO.getTransId()%>"></td>
            <td style="display:none"><input type="hidden" name="assetId" id="assetId<%=i%>" value="<%=lineDTO.getAssetId()%>"></td>
            <td style="display:none"><input type="hidden" name="fromObjectNo" id="fromObjectNo<%=i%>" value="<%=lineDTO.getFromObjectNo()%>"></td>
            <td style="display:none"><input type="hidden" name="toObjectNo" id="toObjectNo<%=i%>" value="<%=lineDTO.getToObjectNo()%>"></td>
        </tr>
<%
			}
		}
%>
    </table>
    

    
</div>
<%
	}
%>