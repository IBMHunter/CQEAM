<%@ page import="com.sino.ams.freeflow.FreeFlowDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	FreeFlowDTO headerDTO = (FreeFlowDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	if(transType.equals(AssetsDictConstant.ASS_FREE)){//�ʲ�����
%>

<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%>
						<input name="transNo" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getTransNo()%>">
					</td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%>
						<input name="transTypeValue" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getTransTypeValue()%>">
					</td>
					<td align=right width=8%>�������ڣ�</td>
			        <td width=17%>
						<input name="creationDate" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getCreationDate()%>" ></td>
			        <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" class="readonlyInput" readonly style="width:100%; cursor:hand" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectCreateGroup();" ></td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%>
						<input name="created" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getCreated()%>">
					</td>
			        <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�������ƣ�</td>
			        <td width=17%>
						<input name="userDeptName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
			        <td height="20">
					<p align="right">�ʲ��˲���</td>
			        <td height="20">
						<input name="bookTypeName" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getBookTypeName()%>" ></td>
			    </tr>
			    <tr>
					<td align=right width=8%>�ֻ����룺</td>
			        <td width=17%>
						<input name="phoneNumber1" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
			        <td align=right>�����ʼ���</td>
			        <td>
						<input name="email1" class="readonlyInput" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20"></td>
					<td align=right width=8%>���ò��ţ�</td>
			        <td width=17%>
						<select name="fromDept" style="width:100%" class="noEmptyInput" onChange="do_ConfirmChange()"><%=headerDTO.getFromDeptOption()%></select>
					</td>
			        <td align=right >�ʲ����ࣺ</td>
			        <td width=17%>
			        	<select name="faContentCode" style="width:100%" class="noEmptyInput" onChange="do_ChangeContentCode()"><%=headerDTO.getFaContentOption()%></select>
			        </td>
			    </tr>
			    <tr>
			        <td  height="40" align=right>����˵����</td>
			        <td colspan="7"  height="40"><textarea name="createdReason" style="width:100%;height:100%" class="noEmptyInput" rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	}
%>
