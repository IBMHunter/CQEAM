<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transStatus = headerDTO.getTransStatus();
	if(transType.equals(AssetsDictConstant.ASS_DEVALUE)){
%>
<table border="1" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%>
						<input name="transNo" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getTransNo()%>">
					</td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%>
						<input name="transTypeValue" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getTransTypeValue()%>">
					</td>
					<td align=right width=8%>�������ڣ�</td>
			        <td width=17%>
						<input name="creationDate" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getCreationDate()%>" ></td>
			        <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" class="input_style2" readonly style="width:100%;" value="<%=headerDTO.getFromGroupName()%>" ></td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%>
						<input name="created" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getCreated()%>">
					</td>
			        <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�ʲ��ʲ���</td>
			        <td width=17%>
                        <input name="bookTypeName" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getBookTypeName()%>" ></td>
                    <td height="20">
                    <p align="right">����״̬��</td>
                    <td height="20">
                        <input name="transStatusDesc" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getTransStatusDesc()%>" size="20"></td>
                </tr>
			    <tr>
					<td align=right width=8%>�ֻ����룺</td>
			        <td width=17%>
						<input name="phoneNumber1" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
			        <td align=right>�����ʼ���</td>
			        <td>
						<input name="email1" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20"></td>
					<td align=right width=8%>��ֵ���ţ�</td>
			        <td width=17%>
			        	<input name="fromDeptName" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getFromDeptName()%>" size="20"/>
			        	<input type="hidden" name="fromDept" value="<%=headerDTO.getFromDept() %>"/>
						<!--<select name="fromDept" style="width:100%" class="select_style1" onChange="do_ConfirmChange()"><%=headerDTO.getFromDeptOption()%></select>  -->
					</td>
					<td align=right>�����̶ȣ�</td>
			        <td >
			        	<% if ("IN_PROCESS".equals(transStatus)) {%>
						<select name="emergentLevel" class="select_style1" disabled style="width:100%" ><%=headerDTO.getEmergentLevelOption()%></select>
						<%
				           } else {
				        %>
				        <select name="emergentLevel" class="select_style1" style="width:100%"  onchange="document.getElementById('sf_priority').value = this.value;"><%=headerDTO.getEmergentLevelOption()%></select>
						<%
				           }
				        %>
					</td>
			    </tr>
			    <tr>
			    	<% 	if (!"IN_PROCESS".equals(transStatus)) {%>
			    	<%  	if (!"APPROVED".equals(transStatus)) {  %>
			        <td align=right >ʵ�������<font color="red">*</font></td>
			        <td >
			        	<select name="specialityDept" style="width:100%" class="selectNoEmpty" onmouseover="do_ProcessOptionWidth(this)" ><%=headerDTO.getSpecialityDeptOption()%></select>
			        <% 	   	} 
			        	}
			        %>
			        </td>
			        <td height="40" align=right>��ֵ˵����</td>
					<% 	if (!"IN_PROCESS".equals(transStatus)) {%>
					<%  	if (!"APPROVED".equals(transStatus)) {  %>
			        <td colspan="5" height="40"><textarea name="createdReason" style="width:100%;height:100%" class="input_style1" rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea></td>
					<% 		} else { %>
			        <td colspan="7" height="40"><textarea name="createdReason" style="width:100%;height:100%" class="input_style1" rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea></td>
					<%		}
						} else { %>
			    	<td colspan="7" height="40"><textarea name="createdReason" style="width:100%;height:100%" class="input_style1" rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea></td>
			    	<% 	} %>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	}
%>