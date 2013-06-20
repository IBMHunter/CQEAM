<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	if(transType.equals(AssetsDictConstant.ASS_RED)){ //����
%>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%><%=headerDTO.getTransNo()%></td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%><%=headerDTO.getTransTypeValue()%></td>
			        <td align=right width=8%>�������</td>
			        <td width=17%><%=headerDTO.getFromGroupName()%></td>
			        <td align=right width=8%>�������ţ�</td>
			        <td width=17%><%=headerDTO.getFromDeptName()%></td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%><%=headerDTO.getCreated()%></td>
			        <td align=right>�������ڣ�</td>
			        <td><%=headerDTO.getCreationDate()%></td>
			        <td align=right>�����ˣ�</td>
			        <td><%=headerDTO.getApprovedUser()%></td>
			        <td align=right>�������ڣ�</td>
			        <td><%=headerDTO.getApprovedDate()%></td>
			    </tr>
			    <tr>
			        <td align=right width=8% height="60">����˵����</td>
			        <td height="60" colspan="7"><%=headerDTO.getCreatedReason()%></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	} else if(transType.equals(AssetsDictConstant.ASS_DIS)){  //����
%>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%><%=headerDTO.getTransNo()%></td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%><%=headerDTO.getTransTypeValue()%></td>
			        <td align=right width=8%>�������</td>
			        <td width=17%><%=headerDTO.getFromGroupName()%></td>
			        <td align=right width=8%>���ϲ��ţ�</td>
			        <td width=17%><%=headerDTO.getFromDeptName()%></td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%><%=headerDTO.getCreated()%></td>
			        <td align=right>�������ڣ�</td>
			        <td><%=headerDTO.getCreationDate()%></td>
			        <td align=right>�����ˣ�</td>
			        <td><%=headerDTO.getApprovedUser()%></td>
			        <td align=right>�������ڣ�</td>
			        <td><%=headerDTO.getApprovedDate()%></td>
			    </tr>
			    <tr>
			    	<td width="8%" height="60" align="right">����˵����</td>
			    	<td width="92%" height="60" colspan="7"><%=headerDTO.getCreatedReason()%></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	} else if(transType.equals(AssetsDictConstant.ASS_FREE)){  //����
%>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%>
						<input type="text" name="transNo" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getTransNo()%>">
					</td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%>
						<input type="text" name="transTypeValue" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getTransTypeValue()%>">
					</td>
			        <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectFrmGroup();" ></td>
			        <td align=right width=8%>���ò��ţ�</td>
			        <td width=17%>
						<input name="fromDeptName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromDeptName()%>">
			        </td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%>
						<input type="text" name="created1" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getCreated()%>" >
					</td>
			        <td align=right>�������ڣ�</td>
			        <td>
						<input name="creationDate" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getCreationDate()%>" ></td>

			        <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�ʲ����ࣺ</td>
			        <td width=17%>
			        	<input name="faContentName" class="readonlyInput" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFaContentName()%>" size="20">
			        </td>
			    </tr>
			    <tr>
			    	<td width="8%" height="60" align="right">����˵����</td>
			    	<td width="92%" height="60" colspan="7"><textarea name="createdReason" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" ><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	} else if(transType.equals(AssetsDictConstant.ASS_CLR)){  //����
%>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%><%=headerDTO.getTransNo()%></td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%><%=headerDTO.getTransTypeValue()%></td>
			        <td align=right width=8%>�������</td>
			        <td width=17%><%=headerDTO.getFromGroupName()%></td>
			        <td align=right width=8%>���ò��ţ�</td>
			        <td width=17%><%=headerDTO.getFromDeptName()%></td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%><%=headerDTO.getCreated()%></td>
			        <td align=right>�������ڣ�</td>
			        <td><%=headerDTO.getCreationDate()%></td>
			        <td align=right>�����ˣ�</td>
			        <td><%=headerDTO.getApprovedUser()%></td>
			        <td align=right>�������ڣ�</td>
			        <td><%=headerDTO.getApprovedDate()%></td>
			    </tr>
			    <tr>
			    	<td width="8%" height="60" align="right">����˵����</td>
			    	<td width="92%" height="60" colspan="7"><%=headerDTO.getCreatedReason()%></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	}
%>