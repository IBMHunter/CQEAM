<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.newasset.scrap.constant.ScrapAppConstant"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Calendar" %>

<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	AmsAssetsTransLineDTO lineDTO = null; 
	String barcode = "";
	
	float TOTAL_COST = 0;
    float TOTAL_DEPRECIATION = 0;
    float TOTAL_NET_ASSET_VALUE = 0;
    float TOTAL_IMPAIR_RESERVE = 0;
    float TOTAL_DEPRN_COST = 0;
    float TOTAL_SCRAP_VALUE = 0;
	Calendar c = Calendar.getInstance();
	int month = c.get(Calendar.MONTH) + 1;
 	String curDate = c.get(Calendar.YEAR) + "��" + month + "��";
 	
	if(transType.equals(AssetsDictConstant.ASS_SUB)){//�ʲ���ֵ
%>
		<table class="headerTable" border="1" width="100%">
	        <tr height=20px>
	            <td align=center width="4%">��Ԫ���</td>
	            <td align=center width="4%">�ʲ����</td>
                <td align=center width="7%">�ʲ���ǩ��</td>
                <td align=center width="8%">�ʲ�����</td>
	            <td align=center width="8%">��Ӧ��</td>
	            <td align=center width="4%">����ͺ�</td>
	            <td align=center width="4%">��������</td>
	            <td align=center width="5%">������ð汾</td>
				<td align=center width="5%">�����ֵ�汾</td>
	            <td align=center width="4%">ԭֵ</td>
	            <td align=center width="4%">�ۼ��۾�</td>
	            <td align=center width="4%">��ֵ</td>
	            <td align=center width="4%">�����ֵ</td>
	            <td align=center width="6%">��ע</td>
            </tr>
		</table>
        <table id="dataTable" width="100%" border="1" bordercolor="#666666">
<%
		for (int i = 0; i < lineSet.getSize(); i++) {
			lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
			barcode = lineDTO.getBarcode();
%>
            <tr style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="4%" align="center"><%=lineDTO.getNetUnit()%></td>
				<td width="4%" align="center"><%=lineDTO.getAssetNumber()%></td>
				<td width="7%" align="center"><%=barcode%></td>
				<td width="8%" align="left"><%=lineDTO.getAssetsDescription()%></td>
				<td width="8%" align="left"><%=lineDTO.getVendorName()%></td>
				<td width="4%" align="left"><%=lineDTO.getModelNumber()%></td>
				<td width="4%" align="right"><%=lineDTO.getDatePlacedInService()%></td>
				<td width="5%" align="center"><%=lineDTO.getSoftInuseVersion()%></td>
				<td width="5%" align="center"><%=lineDTO.getSoftDevalueVersion()%></td>
				<td width="4%" align="right"><%=lineDTO.getCost()%></td>
				<td width="4%" align="right"><%=lineDTO.getDepreciation()%></td>
				<td width="4%" align="right"><%=lineDTO.getDeprnCost()%></td>
				<td width="4%" align="right"><%=lineDTO.getPrepareDevalue()%></td>
				<td width="6%" align="right"><%=lineDTO.getRemark()%></td>
            </tr>
<%
		}
	} else if(transType.equals(AssetsDictConstant.ASS_FREE)){//�ʲ�����
%>
		<table class="headerTable" border="1" width="100%">
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
        <table id="dataTable" width="100%" border="1" bordercolor="#666666">
<%
		for (int i = 0; i < lineSet.getSize(); i++) {
			lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
			barcode = lineDTO.getBarcode();
%>
            <tr style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="8%" align="center"><%=barcode%></td>
				<td width="6%" align="center"><%=lineDTO.getAssetNumber()%></td>
				<td width="10%" align="left"><%=lineDTO.getAssetsDescription()%></td>
				<td width="10%" align="left"><%=lineDTO.getModelNumber()%></td>
				<td width="6%" align="right"><%=lineDTO.getVendorName()%></td>
				<td width="6%" align="center"><%=lineDTO.getDatePlacedInService()%></td>
				<td width="4%" align="right"><%=lineDTO.getCurrentUnits()%></td>
				<td width="3%" align="center"><%=lineDTO.getUnitOfMeasure()%></td>
				<td width="16%" align="right"><%=lineDTO.getOldLocationName()%></td>
				<td width="8%" align="left"><%=lineDTO.getLineReason()%></td>
				<td width="8%" align="left"><%=lineDTO.getRemark()%></td>
            </tr>
<%
		}
	} else if( transType.equals( ScrapAppConstant.TRANS_TYPE )){//���������ʲ�
%>
		<table class="headerTable" border="1" width="100%">
			<tr height="20px">
				<td align=center width="10%">�ʲ���ǩ</td>
				<td align=center width="10%">�ʲ�����</td>
				<td align=center width="10%">�ʲ��ͺ�</td>
				<td align=center width="5%">��������</td>
				<td align=center width="15%">���ڵص�</td>
				<td align=center width="5%">������</td>
				<td align=center width="15%">���β���</td>
			</tr>
		</table>
        <table id="dataTable" width="100%" border="1" bordercolor="#666666">
<%
		if( null != lineSet ){
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="10%" align="center"><%=barcode%></td>
				<td width="10%" align="left"><%=lineDTO.getAssetsDescription()%></td>
				<td width="10%" align="left"><%=lineDTO.getModelNumber()%></td>
				<td width="5%" align="center"><%=lineDTO.getDatePlacedInService()%></td>
				<td width="15%" align="right"><%=lineDTO.getOldLocationName()%></td>
				<td width="5%" align="right"><%=lineDTO.getOldResponsibilityUserName()%></td>
				<td width="15%" align="left"><%=lineDTO.getOldResponsibilityDeptName()%></td>
            </tr>
            
<%
			}
		}
	} else if(!transType.equals(AssetsDictConstant.ASS_RED)){//���ϻ���	
	
%>
<!-- 
		<table class="headerTable" border="1" width="100%">
			<tr height="20px">
				<td align=center width="10%">�ʲ���ǩ</td>
				<td align=center width="10%">�ʲ����</td>
				<td align=center width="10%">�ʲ�����</td>
				<td align=center width="10%">�ʲ��ͺ�</td>
				<td align=center width="9%">�ʲ�ԭֵ</td>
				<td align=center width="9%">��������</td>
				<td align=center width="9%">��ֵ</td>
				<td align=center width="9%">���ڵص�</td>
				<td align=center width="10%">������</td>
				<td align=center width="10%">���β���</td>
			</tr>
		</table>
        <table id="dataTable" width="100%" border="1" bordercolor="#666666">
 -->       
		<table border="0" width="100%" >
		    <tr>
		        <td colspan=3 align=center width=100% height=30>�̶��ʲ������嵥</td>
		    </tr>
		    <tr>
		        <td colspan=3 align=center width=100% height=20><%=curDate%></td>
		    </tr>
		    <tr>
		        <td colspan=3 align=center width=100% height=20></td>
		    </tr>
		    <tr>
            	<td align=left width=20%>��λ���ƣ�<%=headerDTO.getFromCompanyName()%></td>
                <td align=left width=60%>���ϵ��ţ�<%=headerDTO.getTransNo()%></td>
                <td align=right width="20%">(��λ��Ԫ)</td>
		    </tr>
		</table> 
		
		<table class="headerTable4" border="1" width="100%" bordercolor="#666666" style="text-align:center;font-size:12px">
			<tr height="40px">
				<td width="2%">���</td>
				<td width="3%">��Ԫ<br>���</td>
				<td width="5%">�ʲ�<br>���</td>

				<td width="8%">�ʲ�<br>��ǩ</td>
				<td width="5%">�ʲ�<br>����</td>
				<td width="11%">����<br>Ŀ��</td>

				<td width="5%">�ʲ����<br>ĩ������</td>
				<td width="4%">��Ӧ��</td>
				<td width="7%">���<br>��ʽ</td>

				<td width="6%">����<br>����</td>
				<td width="4%">ʣ����<br>������</td>
				<td width="4%">�����<br>�ð汾</td>

				<td width="4%">�����<br>�ϰ汾</td>
				<td width="4%">�ʲ�<br>ԭֵ</td>
				<td width="5%">�ۼ�<br>�۾�</td>

				<td width="4%">�ʲ�<br>��ֵ</td>
				<td width="4%">��ֵ<br>׼��</td>
				<td width="4%">�ʲ�<br>����</td>
				<td width="2%">��ע</td>
			</tr>
		</table>
        <table id="dataTable" width="100%" border="1" bordercolor="#666666" >
<%
		if( null != lineSet ){
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
				TOTAL_COST += lineDTO.getCost();
    			TOTAL_DEPRECIATION += lineDTO.getDepreciation();
    			TOTAL_NET_ASSET_VALUE += Float.parseFloat(lineDTO.getNetAssetValue());
    			TOTAL_IMPAIR_RESERVE += lineDTO.getImpairReserve();
    			TOTAL_DEPRN_COST += lineDTO.getDeprnCost();
%>

            <tr style="height:23px;text-align:center;font-size:12px">
				<td width="2% " align="center"><%=i+1%></td>
				<td width="3% " align="left"><%=lineDTO.getNetUnit()%></td>
				<td width="5% " align="center"><%=lineDTO.getAssetNumber()%></td>

				<td width="8% " align="center"><%=barcode%></td>
				<td width="5% " align="left"  ><%=lineDTO.getAssetsDescription()%></td>
				<td width="11% " align="center"><%=lineDTO.getContentCode()%></td>

				<td width="5% " align="left"  ><%=lineDTO.getContentName()%></td>
				<td width="4% " align="left"  ><%=lineDTO.getManufacturerName()%></td>
				<td width="7% " align="left"  ><%=lineDTO.getModelNumber()%></td>

				<td width="6% " align="center" ><%=lineDTO.getDatePlacedInService()%></td>
				<td width="4% " align="right"><%=lineDTO.getDeprnLeftMonth()%></td>
				<td width="4% " align="left"  ><%=lineDTO.getSoftInuseVersion()%></td>

				<td width="4% " align="left"  ><%=lineDTO.getSoftDevalueVersion()%></td>
				<td width="4% " align="right" ><%=lineDTO.getCost()%></td>
                <td width="5% " align="right" ><%=lineDTO.getDepreciation()%></td>

                <td width="4% " align="right" ><%=lineDTO.getNetAssetValue()%></td>
				<td width="4% " align="right" ><%=lineDTO.getImpairReserve()%></td>
				<td width="4% " align="right" ><%=lineDTO.getDeprnCost()%></td>
				<td width="2% " align="right" ><%=lineDTO.getRemark()%></td>
            </tr>
<%
			}
%>
        <tr bgcolor="yellow" >
            <td colspan="13">&nbsp;��&nbsp;&nbsp;��&nbsp;</td>
			<td width="4%" align="right"><%=TOTAL_COST%></td>
			<td width="5%" align="right"><%=TOTAL_DEPRECIATION%></td>
			<td width="4%" align="right"><%=TOTAL_NET_ASSET_VALUE%></td>
			<td width="4%" align="right"><%=TOTAL_IMPAIR_RESERVE%></td>
			<td width="4%" align="right"><%=TOTAL_DEPRN_COST%></td>
            <td width="2%" align="right"></td>
        </tr>
<%
        }
	} else {//�ʲ�����
		if(transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)){//�����ڵ���
%>
		<table class="headerTable" border="1" width="100%">
			<tr height="20px">
				<td align=center width="9%">�ʲ���ǩ</td>
				<td align=center width="6%">�ʲ����</td>
				<td align=center width="8%">�ʲ�����</td>
				<td align=center width="8%">�ʲ��ͺ�</td>
				<td align=center width="4%">����</td>
				<td align=center width="20%">�����ص�</td>
				<td align=center width="6%">ԭ������</td>
				<td align=center width="20%">����ص�</td>
				<td align=center width="6%">��������</td>
				<td align=center width="7%">��������</td>
				<td align=center width="7%">ժҪ</td>
			</tr>
		</table>
        <table id="dataTable" width="100%" border="1" bordercolor="#666666">
<%
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="9%" align="center"><%=barcode%></td>
				<td width="6%" align="center"><%=lineDTO.getAssetNumber()%></td>
				<td width="8%" align="left"><%=lineDTO.getAssetsDescription()%></td>
				<td width="8%" align="left"><%=lineDTO.getModelNumber()%></td>
				<td width="4%" align="right"><%=lineDTO.getCurrentUnits()%></td>
				<td width="20%"><%=lineDTO.getOldLocationName()%></td>
				<td width="6%" align="right"><%=lineDTO.getOldResponsibilityUserName()%></td>
				<td width="20%"><%=lineDTO.getAssignedToLocationName()%></td>
				<td width="6%" align="left"><%=lineDTO.getResponsibilityUserName()%></td>
				<td width="7%" align="center"><%=lineDTO.getLineTransDate()%></td>
				<td width="7%" align="left"><%=lineDTO.getRemark()%></td>
            </tr>
<%
			}
		} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)){//���ż����
%>
		<table class="headerTable" border="1" width="100%">
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
        <table id="dataTable" width="100%" border="1" bordercolor="#666666">
<%
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="10%" align="center"><%=barcode%></td>
				<td width="7%" align="left"><%=lineDTO.getAssetNumber()%></td>
				<td width="10%" align="left"><%=lineDTO.getAssetsDescription()%></td>
				<td width="10%" align="left"><%=lineDTO.getModelNumber()%></td>
				<td width="3%" align="right"><%=lineDTO.getCurrentUnits()%></td>
				<td width="10%" align="center"><%=lineDTO.getOldLocationName()%></td>
				<td width="6%" align="right"><%=lineDTO.getOldResponsibilityUserName()%></td>
				<td width="10%" align="right"><%=lineDTO.getResponsibilityDeptName()%></td>
				<td width="10%" align="right"><%=lineDTO.getAssignedToLocationName()%></td>
				<td width="6%" align="left"><%=lineDTO.getResponsibilityUserName()%></td>
				<td width="5%" align="center"><%=lineDTO.getLineTransDate()%></td>
				<td width="10%" align="left"><%=lineDTO.getRemark()%></td>
            </tr>
<%
			}
		} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){//��˾�����
%>
<!-- 
	    <table width="100%" border="1" class="headerTable">
	        <tr height="20px">
	            <td align=center width="64%" colspan="14">������</td>
	            <td align=center width="25%" colspan="5">���뷽</td>
	            <td align=center width="5%" rowspan="2">��������</td>
	            <td align=center width="7%" rowspan="2">��ע</td>
	        </tr>
	        <tr height="20px">
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
 -->	    
		<table border="0" width="100%" >
		    <tr>
		        <td colspan=4 align=center width=100% height=30>�̶��ʲ������嵥(��˾��)</td>
		    </tr>
		    <tr>
		        <td colspan=43 align=center width=100% height=20></td>
		    </tr>
		    <tr>
            	<td align=left width=20%>������λ��<%=headerDTO.getFromCompanyName()%></td>
                <td align=left width=20%>���뵥λ��<%=headerDTO.getToCompanyName()%></td>
                <td align=left width=25%>�������ţ�<%=headerDTO.getTransNo()%></td>
                <td align=right width="35%">����ڣ�<%=curDate%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��λ��Ԫ)</td>
		    </tr>
		</table> 
	    <table width="100%" border="1" class="headerTable4">
	        <tr height="20px">
	            <td align=center width="2% "><font style="font-size:11px">���</font></td>
	            <td align=center width="7% "><font style="font-size:11px">ԭ�ʲ����</font></td>
	            <td align=center width="8% "><font style="font-size:11px">ԭ�ʲ���ǩ</font></td>
	            <td align=center width="12%"><font style="font-size:11px">�ʲ�����</font></td>
	            <td align=center width="11%"><font style="font-size:11px">����ͺ�</font></td>
	            <td align=center width="4% "><font style="font-size:11px">��λ</font></td>
	            <td align=center width="5% "><font style="font-size:11px">����</font></td>
	            <td align=center width="6% "><font style="font-size:11px">��������</font></td>
	            <td align=center width="5% "><font style="font-size:11px">�۾�����(��)</font></td>
	            <td align=center width="10%"><font style="font-size:11px">����Ŀ��</font></td>
	            <td align=center width="7% "><font style="font-size:11px">ԭֵ</font></td>
	            <td align=center width="7% "><font style="font-size:11px">�ۼ��۾�</font></td>
	            <td align=center width="3% "><font style="font-size:11px">��ֵ׼��</font></td>
	            <td align=center width="7% "><font style="font-size:11px">����</font></td>
	            <td align=center width="7% "><font style="font-size:11px">��ֵ</font></td>
	        </tr>
	    </table>
	    
        <table id="dataTable" width="100%" border="1" bordercolor="#666666">
<%
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
				TOTAL_COST += lineDTO.getCost();
    			TOTAL_DEPRECIATION += lineDTO.getDepreciation();
    			TOTAL_NET_ASSET_VALUE += Float.parseFloat(lineDTO.getNetAssetValue());
    			TOTAL_IMPAIR_RESERVE += lineDTO.getImpairReserve();
    			TOTAL_DEPRN_COST += lineDTO.getDeprnCost();
    			TOTAL_SCRAP_VALUE += Float.parseFloat(lineDTO.getScrapValue());
%>
            <tr style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
            	<td width="2% " align="center"><font style="font-size:11px"><%=i+1%></font></td>
            	<td width="7% " align="left"  ><font style="font-size:11px"><%=lineDTO.getAssetNumber()%></font></td>
				<td width="8% " align="left"  ><font style="font-size:11px"><%=barcode%></font></td>
				<td width="12%" align="left"  ><font style="font-size:11px"><%=lineDTO.getAssetsDescription()%></font></td>
				<td width="11%" align="left"  ><font style="font-size:11px"><%=lineDTO.getModelNumber()%></font></td>
				<td width="4% " align="left"  ><font style="font-size:11px"><%=lineDTO.getUnitOfMeasure()%></font></td>
				<td width="5% " align="right" ><font style="font-size:11px"><%=lineDTO.getCurrentUnits()%></font></td>
				<td width="6% " align="center"><font style="font-size: 9px"><%=lineDTO.getDatePlacedInService()%></font></td>
				<td width="5% " align="right" ><font style="font-size:11px"><%=lineDTO.getLifeInYears()%></font></td>
				<td width="10%" align="left"  ><font style="font-size: 9px"><%=lineDTO.getContentCode()%></font></td>
				<td width="7% " align="right" ><font style="font-size:11px"><%=lineDTO.getCost()%></font></td>
				<td width="7% " align="right" ><font style="font-size:11px"><%=lineDTO.getDepreciation()%></font></td>
				<td width="3% " align="right" ><font style="font-size:11px"><%=lineDTO.getImpairReserve()%></font></td>
				<td width="7% " align="right" ><font style="font-size:11px"><%=lineDTO.getDeprnCost()%></font></td>
				<td width="7% " align="right" ><font style="font-size:11px"><%=lineDTO.getScrapValue()%></font></td>
            </tr>
<%
			}
%>
	        <tr bgcolor="yellow" >
	            <td colspan=10 width="69%" >&nbsp;��&nbsp;&nbsp;��&nbsp;</td>
				<td width="7%" align="right"><%=TOTAL_COST%></td>
				<td width="7%" align="right"><%=TOTAL_DEPRECIATION%></td>
				<td width="3%" align="right"><%=TOTAL_IMPAIR_RESERVE%></td>
				<td width="7%" align="right"><%=TOTAL_DEPRN_COST%></td>
				<td width="7%" align="right"><%=TOTAL_SCRAP_VALUE%></td>
	        </tr>
        </table>
        
        <table id="signTable" width="100%" border="0" bordercolor="#666666">
			<tr height=40 >
				<td width="5%" ></td>
				<td width="55%" align="left">������λ��������ǩ�֣�</td>
				<td width="40%" align="left">���뵥λ��������ǩ�֣�</td>
			</tr>
			<tr height=40 >
				<td width="5%" ></td>
				<td width="55%" align="left">������λרҵ����ǩ�֣�</td>
				<td width="40%" align="left">���뵥λרҵ����ǩ�֣�</td>
			</tr>
			<tr><td colspan=3 width="100%" ></td>&nbsp;</tr>
			<tr height=40 >
				<td width="5%" ></td>
				<td width="55%" align="left">������λ������ǩ�£�</td>
				<td width="40%" align="left">���뵥λ������ǩ�£�</td>
			</tr>
        	<tr><td colspan=3 width="100%" ></td>&nbsp;</tr>
			<tr><td colspan=3 width="100%" ></td>&nbsp;</tr>
			<tr height=80 >
				<td width="5%" ></td>
				<td width="55%" align="left">������λ���£�</td>
				<td width="40%" align="left">���뵥λ���£�</td>
			</tr>
        </table>
<%
		}
	}
%>


        