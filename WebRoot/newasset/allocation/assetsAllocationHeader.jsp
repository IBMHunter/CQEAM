<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
    String transferType = headerDTO.getTransferType();
%>
<table border="0" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=13%>
						<input name="transNo" class="input_style2" readonly style="width:90%; " value="<%=headerDTO.getTransNo()%>">
					</td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=13%>
						<input name="transTypeValue" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getTransTypeValue()%>">
					</td>
					<td align=right width=8%>�������ڣ�</td>
			        <td width=25%>
						<input name="creationDate" class="input_style2" readonly style="width:95%; " value="<%=headerDTO.getCreationDate()%>" ></td>
			        <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" class="input_style2" readonly style="width:100%;" value="<%=headerDTO.getFromGroupName()%>"></td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=13%>
						<input name="created" class="input_style2" readonly style="width:90%; " value="<%=headerDTO.getCreated()%>">
					</td>
			        <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�������ƣ�</td>
			        <td width=17%>
						<input name="userDeptName" class="input_style2" readonly style="width:95%; " value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
			        <td height="20">
					<p align="right">�ʲ��ʲ���</td>
			        <td height="20">
						<input name="bookTypeName" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getBookTypeName()%>" ></td>
			    </tr>
<%
		if(!transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){
			if(transferType.equals(AssetsDictConstant.TRANS_INN_DEPT_RFU)){
%>
			    <tr>
					<td align=right>�ֻ����룺</td>
			        <td>
						<input name="phoneNumber1" class="input_style2" readonly style="width:90%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
			        <td align=right>�����ʼ���</td>
			        <td>
						<input name="email1" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20">
					</td>
					<td align=right>�������ţ�</td>
			        <td >
						<select disabled="true" name="fromDept" style="width:95%" class="select_style1" ></select>
					</td>
			    	<td align=right>�����̶ȣ�</td>
			        <td >
						<select name="emergentLevel" class="select_style1" style="width:100%" onchange="document.getElementById('sf_priority').value = this.value;"><%=headerDTO.getEmergentLevelOption()%></select>
					</td>
			    </tr>
                <tr>
					<td align=right width=8% height="40" valign="top">����˵����</td>
			        <td colspan="7" height="40"><textarea name="createdReason" style="width:100%;height:100%"><%=headerDTO.getCreatedReason()%></textarea></td>
			        <td><font color="red">*</font></td>
                </tr>
			    <input type="hidden" name="toOrganizationId" value="<%=headerDTO.getToOrganizationId()%>">
<%
			} else {
%>
			    <tr>
					<td align=right>�ֻ����룺</td>
			        <td>
						<input name="phoneNumber1" class="input_style2" readonly style="width:90%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
			        <td align=right>�����ʼ���</td>
			        <td>
						<input name="email1" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20"></td>
					<td align=right>�������ţ�</td>
			        <td >
						<select disabled="true" name="fromDept" style="width:95%" class="input_style2" onChange="do_ConfirmChange()"><%=headerDTO.getFromDeptOption()%></select>
					</td>
					<td align=right>�����̶ȣ�</td>
			        <td >
						<select name="emergentLevel" class="select_style1" style="width:100%"  onchange="document.getElementById('sf_priority').value = this.value;"><%=headerDTO.getEmergentLevelOption()%></select>
					</td>
			    </tr>
                <tr>
                	<% if(!transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)){ %>
			        <td align=right >ʵ�������<font color="red">*</font></td>
			        <td >
				        <select name="specialityDept" style="width:90%" class="selectNoEmpty" onmouseover="do_ProcessOptionWidth(this)" ><%=headerDTO.getSpecialityDeptOption()%></select>
			        </td>
			        <% } %>
                    <td align="right" valign="top">����˵��:<font color="red">*</font></td>
                    <td colspan="5" height="40"><textarea name="createdReason" class="textareaNoEmpty"><%=headerDTO.getCreatedReason()%></textarea></td>
                </tr>
			    <input type="hidden" name="toOrganizationId" value="<%=headerDTO.getToOrganizationId()%>">
<%
			}
		} else {
%>
			    <tr>
					<td align=right width=8%>�ֻ����룺</td>
			        <td width=13%>
						<input name="phoneNumber" class="input_style2" readonly style="width:90%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
			        <td align=right width=8%>�����ʼ���</td>
			        <td  width=13%>
						<input name="email" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20"></td>
			        <td align=right width=8%>������˾��</td>
			        <td width=17%>
						<input name="fromCompanyName" class="input_style2" readonly style="width:95%; " value="<%=headerDTO.getFromCompanyName()%>">
						<input type="hidden" name="fromDept" class="input_style2" readonly style="width:95%; " value="<%=headerDTO.getFromDept()%>"></td>
					<td align=right width=8%>���빫˾����</td>
			        <td width=17%>
						<select name="toOrganizationId" style="width:100%" class="selectNoEmpty" onChange="do_ClearLineData()"><%=headerDTO.getToCompanyOption()%></select>
					</td>
			    </tr>
			    <tr>
			    	<td align=right>�����̶ȣ�</td>
			        <td >
						<select name="emergentLevel" class="select_style1" style="width:90%"  onchange="document.getElementById('sf_priority').value = this.value;"><%=headerDTO.getEmergentLevelOption()%></select>
					</td>
					<% if(!transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)){ %>
			        <td align=right >ʵ�������<font color="red">*</font></td>
			        <td >
				        <select name="specialityDept" style="width:100%" class="selectNoEmpty" onmouseover="do_ProcessOptionWidth(this)" ><%=headerDTO.getSpecialityDeptOption()%></select>
			        </td>
			        <% } %>
					<!-- 
                    <td colspan="6" width="100%" height="40px">
                        <table style="width:100%;height:100%">
                            <tr style="width:100%;height:100%">
                     -->
                    <td height="40px" align="right" valign="top">����˵��:<font color="red">*</font></td>
                    <td colspan="3"><textarea name="createdReason" class="textareaNoEmpty"><%=headerDTO.getCreatedReason()%></textarea></td>
					
			    </tr>
<%
		}
%>
			</table>
		</td>
	</tr>
</table>