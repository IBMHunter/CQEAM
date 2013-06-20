<%@ page contentType="text/html;charset=GBK" language="java"%>
<%@page import="com.sino.base.dto.DTOSet"%>
<%@page import="com.sino.ams.newasset.constant.AssetsWebAttributes"%>
<%@page import="com.sino.ams.newasset.lose.dto.LoseDTO"%>
<%@page import="com.sino.ams.newasset.lose.dto.LoseLineDTO"%>
 
<%   
	LoseDTO loseDTO = null;
	loseDTO = (LoseDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
     
    DTOSet lines = loseDTO.getLines(); 
    
    String mustStyle = "noEmptyInput";
    String pageType = request.getParameter( "pageType" );
    boolean hasCheckBox = ( !"1".equals( pageType ) ) ; 
%>

<%
	String[] widthArr = {  "8%" , "6%", "10%", "10%", "6%",   "6%","4%","3%","16%","8%"   }; 
	int arrIndex = 0;
	%>
	
    <div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:100%">

	
		<table class="headerTable" border="1" width="140%">
	        <tr height=23px onClick="executeClick(this)" style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
	        	<% 
	        		if( hasCheckBox ){
	        	%>
	            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <%}  %>
	            
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>" >��ǩ��</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">�ʲ����</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">�ʲ�����</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">�ʲ��ͺ�</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">��������</td>
	            
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">��������</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">����</td>
				<td align=center width="<%= widthArr[ arrIndex++ ] %>">��λ</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">���ڵص�</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">��ʧ˵��</td>
	            
				<td style="display:none">�������ֶ�</td> 
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:100%;position:absolute;top:48px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		arrIndex = 0;
		int alignArrIndex = 0;
		String[] alignArr = {  "center" , "center", "left", "center", "center",   "center","center","left","center","left"  }; 
		
		if (lines == null || lines.isEmpty()) {
%>
            <tr class="dataTR" style="display:none" title="����鿴�ʲ���ϸ��Ϣ">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
				
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode0" class="finput2" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber0" class="finput2" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName0" class="finput3" readonly value=""></td>
				
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" class="finput2" id="datePlacedInService0"  readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" class="finput3" id="currentUnits0" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="unitOfMeasure" class="finput2" id="unitOfMeasure0" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" class="finput2" id="oldLocationName0" readonly value=""></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>"><input type="text" class="finput" name="lineReason" id="lineReason0"  value=""></td>
    				 
				<td style="display:none">
					<input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept0" value="">
					<input type="hidden" name="oldLocation" id="oldLocation0" value="">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser0" value="">
				</td>
            </tr>
<%
		} else {
			LoseLineDTO lineDTO = null;
			String barcode = ""; 
			for (int i = 0; i < lines.getSize(); i++) {
				arrIndex = 0;
				alignArrIndex = 0;
				lineDTO = (LoseLineDTO) lines.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" style="cursor:pointer" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
				<% 
	        		if( hasCheckBox ){
	        	%>
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
				<%}  %>
				
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getModelNumber()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="vendorName" id="vendorName<%=i%>" class="finput2" readonly value="<%=lineDTO.getVendorName()%>"></td>
				
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput2" readonly value="<%=lineDTO.getUnitOfMeasure()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:pointer" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>"  class="finput2" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="<%= widthArr[ arrIndex++ ] %>" align="<%= alignArr[ alignArrIndex ++ ] %>"><input type="text" name="lineReason" class="finput" id="lineReason<%=i%>" value="<%=lineDTO.getLineReason()%>"></td>
				
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