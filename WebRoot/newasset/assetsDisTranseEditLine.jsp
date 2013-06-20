<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
DTOSet lineSetPri = (DTOSet) request.getAttribute(AssetsWebAttributes.PRIVI_DATA);//EXCEL����ʱ���벻�ɹ���DTOSET
	String widthArr[] = { "7%" , "3%" , "7%" , "8%" , "6%"
						, "3%" , "3%" , "3%"  , "3%" , "3%" 
						, "3%" , "3%" , "3%" , "3%" , "3%" 
						, "5%" , "8%" , "6%" , "8%" , "4%"
						, "7%" };
	int widthIndex = 0;
%>
<div id="headDiv" style="overflow: hidden; left: 1px; width: 100%">
    <table id="headTable" class="headerTable" border="1" width="230%">
        <tr height=23px onClick="executeClick(this)" style="cursor: pointer" title="���ȫѡ��ȡ��ȫѡ">
            <td align=center width="1%">
                <input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')">
            </td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">��ǩ��</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">�ʲ����</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">��������<font color="red">*</font></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">�ʲ�����</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">�ʲ��ͺ�</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">��Ҫ�ʲ�</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">����</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">��λ</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">�ʲ�ԭֵ</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">�ۼ��۾�</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">�ۼƼ�ֵ</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">����</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">���ϳɱ�</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">ʣ������</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">��������</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">�ʲ�Ŀ¼</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">���ڵص�</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">��Ԫ���</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">���β���</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">������</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>">��ע</td>
            <td style="display: none">
                �������ֶ�
            </td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow: scroll; height: 400px; width: 100%;top: 48px; left: 1px;" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="230%" border="1" bordercolor="#666666" style="TABLE-LAYOUT: fixed; word-break: break-all">
<%
    if (lineSet == null || lineSet.isEmpty()) {
        widthIndex = 0;
%>
        <tr class="dataTR" onClick="executeClick(this)" style="display: none" title="����鿴�ʲ���ϸ��Ϣ">
            <td width="1%" align="center">
                <input type="checkbox" name="subCheck" id="subCheck0" value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="barcode" id="barcode0" class="finput2" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="assetNumber" id="assetNumber0" class="finput2" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer">
                <select name="rejectType" id="rejectType0" style="width: 100%"onchange=do_SetCheckCategory(this); class="selectNoEmpty"><%=headerDTO.getRejectTypeHOpt()%></select>
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="assetsDescription" id="assetsDescription0" class="finput" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="modelNumber" id="modelNumber0" class="finput" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="importantFlag" id="importantFlag0" class="finput2" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="currentUnits" id="currentUnits0" class="finput3" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="unitOfMeasure" id="unitOfMeasure0" class="finput2" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="cost" id="cost0" class="finput3" readonly value="">
            </td>
             <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="sumDepreciation" id="sumDepreciation0" class="finput3" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="impairReserve" id="impairReserve0" class="finput3" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="deprnCost" id="deprnCost0" class="finput3" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right">
                <input type="text" name="retirementCost" id="retirementCost0" class="finput" value="" onchange="do_setQuantity();">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="deprnLeftMonth" id="deprnLeftMonth0" class="finput3" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="datePlacedInService" id="datePlacedInService0" class="finput2" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="oldFaCategoryCode" id="oldFaCategoryCode0" class="finput" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="oldLocationName" id="oldLocationName0" class="finput" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left">
                <input type="text" name="netUnit" id="netUnit0" class="finput" value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName0" class="finput" readonly value="">
            </td>
             <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName0" class="finput" readonly value="">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left">
                <input type="text" name="remark" id="remark0" class="finput" value="">
            </td>
            <td style="display: none" width="0">
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
                widthIndex = 0;
    %>
        <tr class="dataTR" onClick="executeClick(this)" style="cursor: pointer" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
            <td width="1%" align="center">
                <input type="checkbox" name="subCheck" id="subCheck<%=i%>" value="<%=barcode%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="assetNumber" id="assetNumber<%=i%>" class="finput2" readonly value="<%=lineDTO.getAssetNumber()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer">
                <select name="rejectType" id="rejectType<%=i%>" onchange="do_SetCheckCategory(this);" class="finput" style="width: 100%"><%=lineDTO.getRejectTypeOpt()%></select>
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="importantFlag" id="importantFlag<%=i%>" class="finput2" readonly value="<%=lineDTO.getImportantFlag()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="currentUnits" id="currentUnits<%=i%>" class="finput3" readonly value="<%=lineDTO.getCurrentUnits()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="unitOfMeasure" id="unitOfMeasure<%=i%>" class="finput2" readonly value="<%=lineDTO.getUnitOfMeasure()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="cost" id="cost<%=i%>" class="finput3" readonly value="<%=lineDTO.getCost()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="sumDepreciation" id="sumDepreciation<%=i%>" class="finput3" readonly value="<%=lineDTO.getSumDepreciation()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="impairReserve" id="impairReserve<%=i%>" class="finput3" readonly value="<%=lineDTO.getImpairReserve()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="deprnCost" id="deprnCost<%=i%>" class="finput3" readonly value="<%=lineDTO.getDeprnCost()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="right">
                <input type="text" name="retirementCost" id="retirementCost<%=i%>" class="finputNoEmpty" value="<%=lineDTO.getDeprnCost()%>" onchange="do_setQuantity();">
            </td>
             <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="deprnLeftMonth" id="deprnLeftMonth<%=i%>" class="finput2" readonly value="<%=lineDTO.getDeprnLeftMonth()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="oldFaCategoryCode" id="oldFaCategoryCode<%=i%>" class="finput" readonly value="<%=lineDTO.getOldFaCategoryCode()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left"><input type="text" name="netUnit" id="netUnit<%=i%>" class="finput" value="<%=lineDTO.getNetUnit() %>"></td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>">
            </td>
              <td width="<%= widthArr[ widthIndex ++ ] %>" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor: pointer" onClick="do_ShowDetail(this)">
                <input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>">
            </td>
            <td width="<%= widthArr[ widthIndex ++ ] %>" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput" value="<%=lineDTO.getRemark()%>"></td>
            <td style="display: none" width="0">
                <input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept<%=i%>" value="<%=lineDTO.getOldResponsibilityDept()%>">
                <input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
                <input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
            </td>
        </tr>
<%
        }
    }
    widthIndex = 6;
%>
    </table>

    <table id="summaryTable" width="230%" border="1" bordercolor="#666666" style="TABLE-LAYOUT: fixed; word-break: break-all">
        <tr height=23px>
            <td align=center width="35%" colspan="7">�ϼ�</td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"><input readonly="readonly" class="finput2" id="numValue" /></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"><input readonly="readonly" class="finput2" id="yuanzhiValue" /></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"><input readonly="readonly" class="finput2" id="ljzjValue" /></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"><input readonly="readonly" class="finput2" id="ljjzalue" /></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"><input readonly="readonly" class="finput2" id="jingeralue" /></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"><input readonly="readonly" class="finput2" id="bfzbValue" /></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"></td>
            <td align=center width="<%= widthArr[ widthIndex ++ ] %>"></td>
            <td align=center width="<%= widthArr[ widthIndex] %>"></td>
            <td style="display: none" width="0"></td>
        </tr>
    </table>
</div>
<div id="transferDiv" style="position:relative; bottom: 0px; top: 0px; left: 0px; right: 0px; z-index: 10; visibility: hidden; width: 100%; height: 100%">
    <table width="100%" class="headerTable">
        <tr class="headerTable">
            <td height="10%" width="10%" align="center">
                �к�
            </td>
            <td height="10%" width="10%" align="center">
                �ʲ���ǩ��
            </td>
            <td height="10%" width="30%" align="center">
                ���ܴ���ԭ��
            </td>
        </tr>
    </table>
    <div   style= "overflow:auto;height:70% "> 
    <table width="100%" bgcolor="#CCCCFF">
        <%
            if (lineSetPri == null || lineSetPri.isEmpty()) {
        %>
        <tr>
            <td height="10%" width="10%"></td>
            <td height="10%" width="10%"></td>
            <td height="10%" width="30%"></td>
        </tr>
        <%
            } else {
                int count = lineSetPri.getSize();
                for (int i = 0; i < count; i++) {
                    AmsAssetsTransLineDTO lineDTO = null;
                    lineDTO = (AmsAssetsTransLineDTO) lineSetPri.getDTO(i);
        %>
        <tr>
            <%
                if (lineSetPri.getSize() > i) {
            %>
            <td height="10%" width="10%" align="center">
                <font size="2" color="#FF0000"><%=lineDTO.getExcelLineId()%></font>
            </td>
            <td height="10%" width="10%" align="center">
                <font size="2" color="#FF0000"><%=lineDTO.getBarcode()%></font>
            </td>
            <td height="10%" width="30%" align="center">
                <font size="2" color="#FF0000"><%=lineDTO.getErrorMsg()%></font>
            </td>
            <%
                } else {
            %>
            <td height="10%" width="10%" align="center"></td>
            <td height="10%" width="10%" align="center"></td>
            <td height="10%" width="30%" align="center"></td>
            <%
                }
            %>
        </tr>
        <%
            }
            }
        %>
        <tr>
            <td height="10%" width="50%" align="center" bgcolor="#CCCCFF"
                colspan="2">
                <img src="/images/eam_images/close.jpg" onClick="do_CloseDiv();">
            </td>
        </tr>
    </table>
    </div>
</div>