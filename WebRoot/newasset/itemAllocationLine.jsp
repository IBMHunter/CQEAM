<%@ page import="com.sino.ams.newasset.dto.AmsAssetsTransLineDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-3-29
  Time: 15:55:53
  To change this template use File | Settings | File Templates.
--%>
<%
    AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
    String transType = headerDTO.getTransType();
    String transferType = headerDTO.getTransferType();
    DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    DTOSet lineSetPri = (DTOSet) request.getAttribute(AssetsWebAttributes.PRIVI_DATA);//EXCEL����ʱ���벻�ɹ���DTOSET
    List list = (List) request.getAttribute("REMARK_LIST");
    if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) {//�����ڵ���
%>
<div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:25px;left:0px;width:100%">
    <table class="eamHeaderTable" border="1" width="120%">
        <tr height="23px" onClick="executeClick(this)" style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
            <td align=center width="10%">��ǩ��</td>
            <td align=center width="10%">ʵ������</td>
            <td align=center width="10%">ʵ���ͺ�</td>
            <td align=center width="3%">����</td>
            <td align=center width="13%">�����ص�</td>
            <td align=center width="6%">ԭ������</td>
            <td align=center width="13%">����ص�<font color="red">*</font></td>
            <td align=center width="6%">��������<font color="red">*</font></td>
            <td align=center width="8%">��������<font color="red">*</font></td>
            <td align=center width="11%">ժҪ</td>
            <td style="display:none">�������ֶ�</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:88%;width:100%;position:absolute;top:45px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="120%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
        if (lineSet == null || lineSet.isEmpty()) {
%>
        <tr id="model" class="dataTR" onClick="executeClick(this)" style="display:none">
            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
            <td width="10%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput" readonly value=""></td>
            <td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
            <td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
            <td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value=""></td>
            <td width="13%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
            <td width="6%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName0" class="finput" readonly value=""></td>
            <td width="13%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName0" class="finputNoEmpty" readonly value="" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:pointer;width:85%"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
            <td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName0" class="finputNoEmpty" readonly value="" onClick="do_SelectPerson(this)" title="���ѡ��������������" style="cursor:pointer"></td>
            <td width="8%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0" class="finputNoEmpty2" style="cursor:pointer" readonly value="" onclick="gfPop.fPopCalendar(lineTransDate0)" title="���ѡ�����ĵ�������"></td>
            <td width="11%" align="left"><input type="text" name="remark" id="remark0" class="finput" value=""></td>
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
        <tr id="model" class="dataTR" onClick="executeClick(this)" style="cursor:pointer">
            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>" value="<%=barcode%>"></td>
            <td width="10%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
            <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
            <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
            <td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
            <td width="13%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
            <td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
            <td width="13%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:pointer;width:85%"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
            <td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getResponsibilityUserName()%>" onClick="do_SelectPerson(this)" title="���ѡ��������������" style="cursor:pointer"></td>
            <td width="8%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finputNoEmpty2" style="cursor:pointer" readonly value="<%=lineDTO.getLineTransDate()%>" onclick="gfPop.fPopCalendar(lineTransDate<%=i%>)" title="���ѡ�����ĵ�������"></td>
            <td width="11%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
            <td style="display:none"><input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>"></td>
            <td style="display:none"><input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>"></td>
            <td style="display:none"><input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>" value="<%=lineDTO.getAssignedToLocation()%>"></td>
            <td style="display:none"><input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>" value="<%=lineDTO.getResponsibilityUser()%>"></td>
            <td style="display:none"><input type="hidden" name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>"></td>
        </tr>
<%
            }
        }
%>
    </table>
</div>
<%
    } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)) {//���ż����
%>
<div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:25px;left:0px;width:100%">
    <table class="eamHeaderTable" border="1" width="140%">
        <tr height=23px onClick="executeClick(this)" style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
            <td align=center width="10%">��ǩ��</td>
            <td align=center width="10%">ʵ������</td>
            <td align=center width="10%">ʵ���ͺ�</td>
            <td align=center width="3%">����</td>
            <td align=center width="10%">�����ص�</td>
            <td align=center width="6%">ԭ������</td>
            <td align=center width="10%">���벿��<font color="red">*</font></td>
            <td align=center width="10%">����ص�</td>
            <td align=center width="6%">��������</td>
            <td align="center" width="5%">��������<font color="red">*</font></td>
            <td align=center width="10%">��ע</td>
            <td style="display:none">�������ֶ�</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:88%;width:100%;position:absolute;top:45px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
        <%
            if (lineSet == null || lineSet.isEmpty()) {
        %>
        <tr id="model" class="dataTR" onClick="executeClick(this)" style="display:none">
            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
            <td width="10%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput" readonly value=""></td>
            <td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
            <td width="10%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
            <td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value=""></td>
            <td width="10%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value=""></td>
            <td width="6%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName0" class="finput" readonly value=""></td>
            <td width="10%" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName0" class="finputNoEmpty" readonly value="" onClick="do_SelectDept(this)" title="���ѡ�����ĵ��벿��" style="cursor:pointer"></td>
            <td width="10%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName0" class="finput" readonly value="" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:pointer;width:85%"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
            <td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName0" class="finput" readonly value="" onClick="do_SelectPerson(this)" title="���ѡ��������������" style="cursor:pointer"></td>
            <td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0" class="finputNoEmpty2" style="cursor:pointer" readonly value="" onclick="gfPop.fPopCalendar(lineTransDate0)" title="���ѡ�����ĵ�������"></td>
            <td width="10%" align="left"><input type="text" name="remark" id="remark0" class="finput" value=""></td>
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
        <tr id="model" class="dataTR" onClick="executeClick(this)" style="cursor:pointer">
            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>" value="<%=barcode%>"></td>
            <td width="10%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=barcode%>"></td>
            <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
            <td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
            <td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
            <td width="10%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
            <td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
            <td width="10%" align="right"><input type="text" name="responsibilityDeptName" id="responsibilityDeptName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getResponsibilityDeptName()%>" onClick="do_SelectDept(this)" title="���ѡ�����ĵ��벿��" style="cursor:pointer"></td>
            <td width="13%" align="right"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:pointer;width:85%"><a href= "#" onClick="do_ChoseLocDesc(this)" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a></td>
            <td width="6%" align="left"><input type="text" name="responsibilityUserName" id="responsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUserName()%>" onClick="do_SelectPerson(this)" title="���ѡ��������������" style="cursor:pointer"></td>
            <td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>" class="finputNoEmpty2" style="cursor:pointer" readonly value="<%=lineDTO.getLineTransDate()%>" onclick="gfPop.fPopCalendar(lineTransDate<%=i%>)" title="���ѡ�����ĵ�������"></td>
            <td width="10%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
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
%>
    </table>
</div>
<%
    }
%>
