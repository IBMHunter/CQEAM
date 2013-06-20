<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transStatus = headerDTO.getTransStatus();
	String sfAttribute3 = headerDTO.getSf_task_attribute3();
	if(transType.equals(AssetsDictConstant.ASS_FREE)){
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
					<td align=right width=8%>���ò��ţ�</td>
			        <td width=17%>
			        	<input name="fromDeptName" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getFromDeptName()%>" size="20"/>
			        	<input type="hidden" name="fromDept" value="<%=headerDTO.getFromDept() %>"/>
					</td>
					<td align=right>�����̶ȣ�</td>
			        <td >
					<%
					    if (!sfAttribute3.equals("FILL_DATA") && !sfAttribute3.equals("")) {
					%>
						<select name="emergentLevel" class="select_style1" disabled style="width:100%" ><%=headerDTO.getEmergentLevelOption()%></select>
					<%
					    } else {
					%>
				        <select name="emergentLevel" class="select_style1" style="width:100%" ><%=headerDTO.getEmergentLevelOption()%></select>
					<%
					    }
					%>
					</td>
			    </tr>
			    <tr>
			    <%	if ("".equals(transStatus) || "SAVE_TEMP".equals(transStatus)) {%>
			        <td align=right >ʵ�������<font color="red">*</font></td>
			        <td >
				        <select name="specialityDept" style="width:100%" class="selectNoEmpty" onmouseover="do_ProcessOptionWidth(this)" ><%=headerDTO.getSpecialityDeptOption()%></select>
		        <%
		           	}
		        %>
			        </td>
			        <td width="8%" height="40px" align=right valign="top">����˵����</td>
				<%	if ("".equals(transStatus) || "SAVE_TEMP".equals(transStatus)) {%>
			        <td width="92%" height="40px" colspan="5">
			    <%  } else { %>
			        <td width="92%" height="40px" colspan="7">
			    <%  } %>
                        <table style="width:100%;height:100%">
                            <tr style="width:100%;height:100%">
                                <td style="width:99%;height:100%"><textarea name="createdReason" class="selectNoEmpty"><%=headerDTO.getCreatedReason()%></textarea></td>
                                <td style="width:1%;height:100%"><font color="red">*</font></td>
                            </tr>
                        </table>
                    </td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	}
%>