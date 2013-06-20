<%@ page import="com.sino.ams.newasset.dto.AmsAssetsTransLineDTO" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    DTOSet lineSetPri = (DTOSet) request.getAttribute(AssetsWebAttributes.PRIVI_DATA);//EXCEL����ʱ���벻�ɹ���DTOSET
    List list = (List)request.getAttribute("REMARK_LIST");

    if(transType.equals(AssetsDictConstant.ASS_DEVALUE)){//�ʲ���ֵ
%>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
		<table class="headerTable" border="1" width="130%">
	        <tr height=23px onClick="executeClick(this)" style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="2%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <%-- <td align=center width="4%">��Ԫ���</td> --%>
	            <td align=center width="4%">�ʲ����</td>
                <td align=center width="5%">��ǩ��</td>
                <td align=center width="8%">�ʲ�����</td>
	            <td align=center width="8%">��Ӧ��</td>
	            <td align=center width="4%">�ʲ��ͺ�</td>
	            <td align=center width="4%">��������</td>
	            <%-- <td align=center width="5%">������ð汾</td>
				<td align=center width="5%">�����ֵ�汾</td> --%>
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
        <table id="dataTable" width="130%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (lineSet == null || lineSet.isEmpty()) {
%>
            <tr class="dataTR" onClick="executeClick(this)" style="display:none" title="����鿴�ʲ���ϸ��Ϣ">
				<td width="2%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
                <%-- <td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick=""><input type="text" name="netUnit" id="netUnit0" class="finput2"  value=""></td> --%> 
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput2" readonly value=""></td>
                <td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput2" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName0" class="finput" readonly value=""></td>
				<td width="4%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput3" readonly value=""></td>
				<%-- <td width="5%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick=""><input type="text" name="softInuseVersion" id="softInuseVersion0" class="finput2"  value=""></td>
				<td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick=""><input type="text" name="softDevalueVersion" id="softDevalueVersion0" class="finput2"  value=""></td> --%>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="cost" id="cost0" class="finput3" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="depreciation" id="depreciation0" class="finput3" readonly value=""></td>
				<td width="4%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="deprnCost" id="deprnCost0" class="finput3" readonly value=""></td>
				<td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick=""><input type="text" name="prepareDevalue" id="prepareDevalue0" onkeydown="floatOnly(this.value);" class="finputNoEmpty" value=""></td>
				<td width="8%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick=""><input type="text" name="remark" id="remark0" class="finput"  value=""></td>
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
            <tr class="dataTR" onClick="executeClick(this)" style="cursor:pointer" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
				<td width="2%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
				<%-- <td width="4%" align="center" style="cursor:pointer" onClick=""><input type="text" name="netUnit" id="netUnit<%=i%>" class="finput2" value="<%=lineDTO.getNetUnit()%>"></td> --%>
				<td width="4%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="5%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=lineDTO.getBarcode()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput" readonly value="<%=lineDTO.getVendorName()%>"></td>
				<td width="4%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail('<%=barcode%>')"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput3" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<%-- <td width="5%" align="center" style="cursor:pointer" onClick=""><input type="text" name="softInuseVersion" id="softInuseVersion<%=i%>" class="finput2"  value="<%=lineDTO.getSoftInuseVersion()%>"></td>
				<td width="5%" align="center" style="cursor:pointer" onClick=""><input type="text" name="softDevalueVersion" id="softDevalueVersion<%=i%>" class="finput2"  value="<%=lineDTO.getSoftDevalueVersion()%>"></td> --%>
				<td width="4%" align="center" style="cursor:pointer" ><input type="text" name="cost" id="cost<%=i%>" class="finput2" readonly value="<%=lineDTO.getCost()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" ><input type="text" name="depreciation" id="depreciation<%=i%>" class="finput2" readonly value="<%=lineDTO.getDepreciation()%>"></td>
				<td width="4%" align="right" style="cursor:pointer" ><input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" ><input type="text" name="prepareDevalue" id="prepareDevalue<%=i%>" onkeydown="floatOnly(this.value);" class="finputNoEmpty"  value="<%=lineDTO.getPrepareDevalue()%>"></td>
				<td width="8%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick=""><input type="text" name="remark" id="remark<%=i%>" class="finput"  value="<%=lineDTO.getRemark()%>"></td>
                <td style="display:none">
                    <input type="hidden" name="assetId" id="assetId0" value="<%=lineDTO.getAssetId()%>">
                </td>
            </tr>
<%	
			}
		}
    } 
%>
        </table>
    </div>

    <div id="transferDiv"
     style="position:absolute;bottom:0px;top:0px;left:0px;right:0px;z-index:10;visibility:hidden;width:100%;height:100%">
    <table width="50%" class="headerTable">
        <tr class="headerTable">
            <td height="10%" width="10%" align="center">�ʲ���ǩ��</td>
            <td height="10%" width="20%" align="center">���ܴ���ԭ��</td>
        </tr>
    </table>
    <table width="50%" bgcolor="#CCCCFF">
<%
    if (lineSetPri == null || lineSetPri.isEmpty()) {
%>
        <tr>
            <td height="10%" width="10%"></td>
            <td height="10%" width="20%"></td>
        </tr>
<%
    } else {
        int count=lineSetPri.getSize();
        if(list.size()>count){
            count=list.size();
        }
    for (int i = 0; i < count; i++) {
        AmsAssetsTransLineDTO lineDTO = null;
        lineDTO = (AmsAssetsTransLineDTO) lineSetPri.getDTO(i);
%>
        <tr>
<%
        if (lineSetPri.getSize() > i) {
%>
            <td height="10%" width="10%" align="center"><font size = "2" color="#FF0000"><%=lineDTO.getBarcode()%></font></td>
<%
        } else {
%>
            <td height="10%" width="10%" align="center"></td>
<%
        }
%>

<%
    if (list.size() > i) {
%>
            <td height="10%" width="25%" align="left"><font size = "2" color="#FF0000"><%=list.get(i)%></font></td>
<%
    } else {
%>
           <td height="10%" width="25%" align="center"></td>
<%
    }
%>
        </tr>
<%
    } }
%>
<tr>
        <td height="10%" width="50%" align="center" bgcolor="#CCCCFF" colspan="2">
            <img src="/images/eam_images/close.jpg" onClick="do_CloseDiv();">
        </td>
</tr>
    </table>
</div>
