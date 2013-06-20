<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/td/newasset/headerIncludeTd.jsp"%>
<%
	TdAssetsTransHeaderDTO headerDTO = (TdAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
	TdAssetsTransLineDTO lineDTO = null;
	String barcode = "";
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
			lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
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
			lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
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
	} else if(!transType.equals(AssetsDictConstant.ASS_RED)){//���ϻ���
%>
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
<%
		for (int i = 0; i < lineSet.getSize(); i++) {
			lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
			barcode = lineDTO.getBarcode();
%>
            <tr style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="10%" align="center"><%=barcode%></td>
				<td width="10%" align="left"><%=lineDTO.getAssetNumber()%></td>
				<td width="10%" align="left"><%=lineDTO.getAssetsDescription()%></td>
				<td width="10%" align="left"><%=lineDTO.getModelNumber()%></td>
				<td width="9%" align="right"><%=lineDTO.getCost()%></td>
				<td width="9%" align="center"><%=lineDTO.getDatePlacedInService()%></td>
				<td width="9%" align="right"><%=lineDTO.getDeprnCost()%></td>
				<td width="9%" align="right"><%=lineDTO.getOldLocationName()%></td>
				<td width="10%" align="right"><%=lineDTO.getOldResponsibilityUserName()%></td>
				<td width="10%" align="left"><%=lineDTO.getOldResponsibilityDeptName()%></td>
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
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
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
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
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
		} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){//���м����
%>
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
        <table id="dataTable" width="100%" border="1" bordercolor="#666666">
<%
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (TdAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" onClick="do_ShowDetail('<%=barcode%>')">
				<td width="5%" align="center"><%=barcode%></td>
				<td width="5%" align="left"><%=lineDTO.getAssetNumber()%></td>
				<td width="5%" align="left"><%=lineDTO.getAssetsDescription()%></td>
				<td width="5%" align="left"><%=lineDTO.getModelNumber()%></td>
				<td width="3%" align="right"><%=lineDTO.getCurrentUnits()%></td>
				<td width="3%" align="right"><%=lineDTO.getCost()%></td>
				<td width="5%" align="right"><%=lineDTO.getDepreciation()%></td>
				<td width="3%" align="right"><%=lineDTO.getScrapValue()%></td>
				<td width="5%" align="right"><%=lineDTO.getDatePlacedInService()%></td>
				<td width="5%" align="right"><%=lineDTO.getOldResponsibilityDeptName()%></td>
				<td width="5%" align="center"><%=lineDTO.getOldLocationName()%></td>
				<td width="5%" align="right"><%=lineDTO.getOldResponsibilityUserName()%></td>
				<td width="5%" align="center"><%=lineDTO.getOldDepreciationAccount()%></td>
				<td width="5%" align="right"><%=lineDTO.getOldFaCategoryCode()%></td>
				<td width="5%" align="right"><%=lineDTO.getResponsibilityDeptName()%></td>
				<td width="5%" align="right"><%=lineDTO.getAssignedToLocationName()%></td>
				<td width="5%" align="left"><%=lineDTO.getResponsibilityUserName()%></td>
				<td width="5%" align="center"><%=lineDTO.getDepreciationAccount()%></td>
				<td width="5%" align="right"><%=lineDTO.getFaCategoryCode()%></td>
				<td width="5%" align="center"><%=lineDTO.getLineTransDate()%></td>
				<td width="7%" align="left"><%=lineDTO.getRemark()%></td>
            </tr>
<%
			}
		}
	}
%>
        </table>