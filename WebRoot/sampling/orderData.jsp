<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/sampling/headerInclude.jsp"%>
<%@ include file="/sampling/headerInclude.htm"%>

<%
	AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) request.getAttribute(SamplingWebAttributes.ORDER_DTO);
	DTOSet orderLines = (DTOSet) request.getAttribute(SamplingWebAttributes.ORDER_LINES);
%>
<body leftmargin="0" topmargin="0" onload="window.focus();">
<form name="mainFrm" method="post">
<script type="text/javascript">
    printTitleBar("�ʲ��̵����>>�̵㹤����ϸ��Ϣ");
</script>
<jsp:include page="/message/MessageProcess"/>
<input type="hidden" name="headerId" value="<%=dto.getHeaderId()%>">
<table border="0" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="1" bordercolor="#226E9B">
			    <tr>
			        <td align=right height="22" width="7%">�����ţ�</td>
			        <td height="22" width="17%"><input type="text" name="taskNo" class="input_style2" style="width:100%" readonly value="<%=dto.getTaskNo()%>"></td>
			        <td align=right width="8%" height="22">�������ƣ�</td>
			        <td width="25%" height="22"><input type="text" name="taskName" class="input_style2" style="width:100%" readonly value="<%=dto.getTaskName()%>"></td>
			        <td align=right height="22" width="7%">��ʼ���ڣ�</td>
			        <td height="22" width="17%"><input type="text" name="startDate" class="input_style2" style="width:100%" readonly value="<%=dto.getStartDate()%>"></td>
			        <td align=right width="8%" height="22">��ֹ���ڣ�</td>
			        <td width="25%" height="22"><input type="text" name="endDate" class="input_style2" style="width:100%" readonly value="<%=dto.getEndDate()%>"></td>
			    </tr>
			    <tr>
			        <td align=right width="8%" height="40">��������</td>
			        <td width="34%" height="40" colspan="3"><textarea name="taskDesc" class="input_style2" style="width:100%" readonly style="width:100%; height:100%"><%=dto.getTaskDesc()%></textarea></td>
			        <td align=right height="40" width="8%">��������ע</td>
			        <td height="40" width="45%" colspan="3"><textarea name="batchRemark" class="input_style2" style="width:100%" readonly style="width:100%; height:100%"><%=dto.getBatchRemark()%></textarea></td>
			    </tr>
			    <tr>
			        <td align=right width="8%" height="22">��鵥�ţ�</td>
			        <td width="17%" height="22"><input type="text" name="orderNo" class="input_style2" style="width:100%" readonly value="<%=dto.getOrderNo()%>"></td>
			        <td align=right width="8%" height="22">�������ţ�</td>
			        <td width="9%" height="22"><input type="text" name="batchNo" class="input_style2" style="width:100%" readonly value="<%=dto.getBatchNo()%>"></td>
			        <td align=right height="22" width="7%">�ص��ţ�</td>
			        <td height="22" width="17%"><input type="text" name="samplingLocationCode" class="input_style2" style="width:100%" readonly value="<%=dto.getSamplingLocationCode()%>"></td>
			        <td align=right width="8%" height="22">�ص����ƣ�</td>
			        <td width="25%" height="22"><input type="text" name="samplingLocationName" class="input_style2" style="width:100%" readonly value="<%=dto.getSamplingLocationName()%>"></td>
			    </tr>
			    <tr>
			        <td align=right height="22" width="8%">��ʼ���ڣ�</td>
			        <td height="22" width="17%"><input type="text" name="startTime" class="input_style2" style="width:100%" readonly value="<%=dto.getStartTime()%>"></td>
			        <td align=right height="22" width="8%">ִ�����ڣ�</td>
			        <td height="22" width="9%"><input type="text" name="implementDays" class="input_style2" style="width:100%" readonly value="<%=dto.getImplementDays()%>"></td>
			        <td align=right height="22" width="7%">ִ���ˣ�</td>
			        <td height="22" width="17%"><input type="text" name="implementUser" class="input_style2" style="width:100%" readonly value="<%=dto.getImplementUser()%>"></td>
			        <td align=right width="8%" height="22">����״̬��</td>
			        <td width="25%" height="22"><input type="text" name="orderStatusValue" class="input_style2" style="width:100%" readonly value="<%=dto.getOrderStatusValue()%>"></td>
			    </tr>
			    <tr>
			        <td align=right height="22" width="8%">�����ˣ�</td>
			        <td height="22" width="17%"><input type="text" name="createdUser" class="input_style2" style="width:100%" readonly value="<%=dto.getCreatedUser()%>"></td>
			        <td align=right height="22" width="8%">�·��ˣ�</td>
			        <td height="22" width="9%"><input type="text" name="distributeUser" class="input_style2" style="width:100%" readonly value="<%=dto.getDistributeUser()%>"></td>
			        <td align=right height="22" width="7%">�����ˣ�</td>
			        <td height="22" width="17%"><input type="text" name="downloadUser" class="input_style2" style="width:100%" readonly value="<%=dto.getDownloadUser()%>"></td>
			        <td align=right height="22" width="8%">�ϴ��ˣ�</td>
			        <td height="22" width="25%"><input type="text" name="uploadUser" class="input_style2" style="width:100%" readonly value="<%=dto.getUploadUser()%>"></td>
			    </tr>
			    <tr>
			        <td align=right height="22" width="8%">����ʱ�䣺</td>
			        <td height="22" width="17%"><input type="text" name="creationDate" class="input_style2" style="width:100%" readonly value="<%=dto.getCreationDate()%>"></td>
			        <td align=right height="22" width="8%">�·�ʱ�䣺</td>
			        <td height="22" width="9%"><input type="text" name="distributeDate" class="input_style2" style="width:100%" readonly value="<%=dto.getDistributeDate()%>"></td>
			        <td align=right height="22" width="7%">����ʱ�䣺</td>
			        <td height="22" width="17%"><input type="text" name="downloadDate" class="input_style2" style="width:100%" readonly value="<%=dto.getDownloadDate()%>"></td>
			        <td align=right height="22" width="8%">�ϴ�ʱ�䣺</td>
			        <td height="22" width="25%"><input type="text" name="uploadDate" class="input_style2" style="width:100%" readonly value="<%=dto.getUploadDate()%>"></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<fieldset style="border:1px solid #226E9B; position:absolute;top:185;width:100%;height:490px">
    <legend>
        <img src="/images/eam_images/close.jpg" id="img6" alt="�ر�" onClick="self.close()">
    </legend>
    <div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:23px;left:0px;width:100%" class="crystalScroll">
	    <table class="eamDbHeaderTable" border=1 style="width:140%">
	        <tr height="20px">
	            <td align=center width="10%" rowspan="2">�豸����</td>
	            <td align=center width="42%" colspan="7">ϵͳ����</td>
	            <td align=center width="42%" colspan="7">ɨ������</td>
	            <td align=center width="6%" colspan="2">״̬</td>
	        </tr>
	        <tr height="20px" class="eamDbHeaderTr">
	            <td align=center width="6%">�豸רҵ</td>
	            <td align=center width="6%">�豸����</td>
	            <td align=center width="6%">�豸�ͺ�</td>
	            
	            <td align=center width="6%">������</td>
	            <td align=center width="6%">���β���</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="6%">ʹ����</td>
	            
	            <td align=center width="6%">�豸רҵ</td>
	            <td align=center width="6%">�豸����</td>
	            <td align=center width="6%">�豸�ͺ�</td>
	            
	            <td align=center width="6%">������</td>
	            <td align=center width="6%">���β���</td>
	            <td align=center width="6%">��������</td>
	            <td align=center width="6%">ʹ����</td>
	            
	            <td align=center width="3%">ϵͳ</td>
	            <td align=center width="3%">ɨ��</td>
	        </tr>
	    </table>
	</div>
	<div style="overflow:scroll;height:440px;width:100%;position:absolute;top:65px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	int itemCount = orderLines.getSize();
	AmsAssetsSamplingLineDTO item = null;
	for(int i = 0; i < itemCount; i++){
		item = (AmsAssetsSamplingLineDTO)orderLines.getDTO(i);
%>
            <tr class="dataTR">
                <td width="10%"><input type="text" readonly class="finput2" value="<%=item.getBarcode()%>"></td>

                <td width="6%"><input type="text" readonly class="finput2" value="<%=item.getItemCategoryValue()%>"></td>
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getItemName()%>"></td>
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getItemSpec()%>"></td>
                
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getResponsibilityUserName()%>"></td>
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getResponsibilityDeptName()%>"></td>
                <td width="6%"><input type="text" readonly class="finput2" value="<%=item.getStartDate()%>"></td>
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getMaintainUser()%>"></td>
                
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getScanItemCategoryValue()%>"></td>
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getScanItemName()%>"></td>
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getScanItemSpec()%>"></td>
                
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getScanResponsibilityUserName()%>"></td>
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getScanResponsibilityDeptName()%>"></td>
                <td width="6%"><input type="text" readonly class="finput2" value="<%=item.getScanStartDate()%>"></td>
                <td width="6%"><input type="text" readonly class="finput" value="<%=item.getScanMaintainUser()%>"></td>
                
                <td width="3%"><input type="text" readonly class="finput" value="<%=item.getSystemStatus()%>"></td>
                <td width="3%"><input type="text" readonly class="finput" value="<%=item.getScanStatus()%>"></td>
            </tr>
<%
	}
%>
        </table>
    </div>
	<input type="hidden" name="act">
</fieldset>	


</form>
</body>
</html>
<script>
function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=SamplingActions.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=860,height=495,left=100,top=130";
	window.open(url, winName, style);
}
</script>
