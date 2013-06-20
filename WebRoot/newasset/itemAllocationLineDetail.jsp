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
<div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:22px;left:0px;width:100%">
    <table class="eamHeaderTable" border="1" width="120%">
        <tr height="23px">
            <td align=center width="10%">�ʲ���ǩ</td>
            <td align=center width="10%">�ʲ�����</td>
            <td align=center width="10%">�ʲ��ͺ�</td>
            <td align=center width="3%">����</td>
            <td align=center width="13%">�����ص�</td>
            <td align=center width="7%">ԭ������</td>
            <td align=center width="13%">����ص�</td>
            <td align=center width="7%">��������</td>
            <td align=center width="7%">��������</td>
            <td align=center width="13%">ժҪ</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:60%;width:100%;position:absolute;top:42px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
        if (lineSet != null && !lineSet.isEmpty()) {
            for (int i = 0; i < lineSet.getSize(); i++) {
                lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
                barcode = lineDTO.getBarcode();
%>
        <tr class="dataTR" style="cursor:pointer" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
            <td width="10%" align="center"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
            <td width="10%" align="left"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
            <td width="10%" align="left"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
            <td width="3%" align="right"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
            <td width="13%" align="center"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
            <td width="7%" align="right"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
            <td width="13%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>"></td>
            <td width="7%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>"></td>
            <td width="7%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finput" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
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
%>
<div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:22px;left:0px;width:100%">
    <table class="headerTable" border="1" width="140%">
        <tr height="23px">
            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
            <td align=center width="10%">�ʲ���ǩ</td>
            <td align=center width="10%">�ʲ�����</td>
            <td align=center width="10%">�ʲ��ͺ�</td>
            <td align=center width="3%">����</td>
            <td align=center width="10%">�����ص�</td>
            <td align=center width="7%">ԭ������</td>
            <td align=center width="10%">���벿��</td>
            <td align=center width="10%">����ص�</td>
            <td align=center width="6%">��������</td>
            <td align="center" width="5%">��������</td>
            <td align=center width="9%">��ע</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:88%;width:100%;position:absolute;top:42px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
        if (lineSet != null && !lineSet.isEmpty()) {
            for (int i = 0; i < lineSet.getSize(); i++) {
                lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
                barcode = lineDTO.getBarcode();
%>
        <tr class="dataTR" style="cursor:pointer">
            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
            <td width="10%" align="center" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
            <td width="10%" align="left" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
            <td width="10%" align="left" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
            <td width="3%" align="right" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
            <td width="10%" align="center" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
            <td width="7%" align="right" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
            <td width="10%" align="right" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityDeptName()%>"></td>
<%
                if (headerDTO.getAttribute1().equals("DEPT")) {
%>
                    <td width="10%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getAssignedToLocationName()%>" title="���ѡ�����ĵ���ص�" onClick="do_SelectLocation(this)" style="width:85%"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
                    <td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getResponsibilityUserName()%>" title="���ѡ��������������" onClick="do_SelectPerson(this)"></td>
<%
                 } else {
%>
                    <td width="10%" align="right" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>"></td>
                    <td width="6%" align="left" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>"></td>
<%
                }
%>
			<td width="5%" align="center" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finput" readonly value="<%=lineDTO.getLineTransDate()%>"></td>
            <td width="9%" align="left" title="����鿴ʵ�<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
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
    }
%>
