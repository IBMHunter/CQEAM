<%@ page import="com.sino.ams.freeflow.FreeFlowDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%
	FreeFlowDTO headerDTO = (FreeFlowDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	if(transType.equals(AssetsDictConstant.ASS_RED)){//�ʲ�������
		String transferType = headerDTO.getTransferType();
%>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%>
						<input name="transNo" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getTransNo()%>">
					</td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%>
						<input name="transTypeValue" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getTransTypeValue()%>">
					</td>
					<td align=right width=8%>�������ڣ�</td>
			        <td width=17%>
						<input name="creationDate" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getCreationDate()%>" >
					</td>
			        <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectFrmGroup();" >
					</td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%>
						<input name="created" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getCreated()%>">
					</td>
			        <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�������ƣ�</td>
			        <td width=17%>
						<input name="userDeptName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getUserDeptName()%>"></td>
			        <td height="20">
					<p align="right">�ʲ��˲���</td>
			        <td height="20">
						<input name="bookTypeName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getBookTypeName()%>" ></td>
			    </tr>
<%
		if(!transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){
%>
			    <tr>
					<td align=right width=8%>�ֻ����룺</td>
			        <td width=17%>
						<input name="phoneNumber" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getPhoneNumber()%>"></td>
			        <td align=right>�����ʼ���</td>
			        <td>
						<input name="email" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getEmail()%>"></td>
					<td align=right width=8%>�������ţ�</td>
			        <td width=17%>
						<input name="fromDeptName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromDeptName()%>">
					</td>
			        <td align=right >�ʲ����ࣺ</td>
			        <td><input name="faContentName" class="readonlyInput" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFaContentName()%>" size="20"></td>
			    </tr>
			    <tr>
					<td align=right width=8% align="right" height="40">����˵������</td>
			        <td colspan="7"  height="40"><textarea name="createdReason" style="width:100%;height:100%" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF"  rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
<%
		} else {
%>
			    <tr>
					<td align=right width=8%>�ֻ����룺</td>
			        <td width=17%>
						<input name="phoneNumber" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getPhoneNumber()%>"></td>
			        <td align=right>�����ʼ���</td>
			        <td>
						<input name="email" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getEmail()%>"></td>
			        <td align=right>������˾��</td>
			        <td>
						<input name="fromCompanyName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>���빫˾����</td>
			        <td width=17%>
						<input name="toCompanyName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getToCompanyName()%>">
					</td>
			    </tr>
			    <tr>
					<td width=8% height="40" align=right>�ʲ����ࣺ</td>
			        <td><input name="faContentName" class="readonlyInput" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFaContentName()%>" size="20"></td>
			        <td width=8% height="40"align=right>����˵����</td>
			        <td width=67% height="40" colspan="6"><textarea name="createdReason" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
<%
		}
%>
			</table>
		</td>
	</tr>
</table>
<input type="hidden" name="fromDept" value="<%=headerDTO.getFromDept()%>">
<%
	} else if(transType.equals(AssetsDictConstant.ASS_DIS)){//�ʲ����ϵ�
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
						<input name="fromGroupName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectCreateGroup();" ></td>
			        <td align=right width=8%>���ϲ��ţ�</td>
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
			    	<td width="92%" height="60" colspan="7"><textarea name="createdReason" readonly style="border-style:solid; border-width:0px; width:100%;height:100%; background-color:#F2F9FF"><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	} else if(transType.equals(AssetsDictConstant.ASS_CLR)){//�ʲ����õ�
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
						<input name="fromGroupName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectCreateGroup();" ></td>
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
			    	<td width="92%" height="60" colspan="7"><textarea name="createdReason" readonly style="border-style:solid; border-width:0px; width:100%;height:100%; background-color:#F2F9FF"><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	} else if(transType.equals(AssetsDictConstant.ASS_FREE)){//�ʲ����õ�
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
                    <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromCompanyName()%>"></td>
                    <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectFrmGroup();" ></td>

			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%>
						<input type="text" name="created1" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getCreated()%>" >
					</td>
   			        <td align=right>�������䣺</td>
			        <td>
						<input name="email1" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getEmail()%>" size="20"></td>
                    <td align=right width=8%>�������ƣ�</td>
			        <td width=17%>
						<input name="userDeptName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
                    <td align=right>�������ڣ�</td>
			        <td>
						<input name="creationDate" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getCreationDate()%>" ></td>
			    </tr>
                <tr>
                    <td align="right">�ʲ��˱���</td>
			        <td >
						<input name="bookTypeName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF"value="<%=headerDTO.getBookTypeName()%>" ></td>
                    <td align=right width=8%>�ʲ����ࣺ</td>
			        <td width=17%>
			        	<input name="faContentName" class="readonlyInput" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFaContentName()%>" size="20">
			        </td>
                    <td align=right width=8%>���ò��ţ�</td>
			        <td width=17%>
						<input name="fromDeptName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromDeptName()%>">
			        </td>
                    <td align=right width=8%>����������</td>
			        <td width=17%>
						<input name="accessSheet" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getAccessSheet()%>">
			        </td>
                </tr>
                <tr>
			    	<td width="8%" height="55" align="right">����˵����</td>
			    	<td width="92%" height="55" colspan="7"><textarea name="createdReason" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" ><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	} else if(transType.equals(AssetsDictConstant.ASS_SHARE)){//�ʲ�����
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
                    <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromCompanyName()%>"></td>
                    <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������" onclick="do_SelectFrmGroup();" ></td>

			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%>
						<input type="text" name="created1" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getCreated()%>" >
					</td>
   			        <td align=right>�������䣺</td>
			        <td>
						<input name="email1" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getEmail()%>" size="20"></td>
                    <td align=right width=8%>�������ƣ�</td>
			        <td width=17%>
						<input name="userDeptName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
                    <td align=right>�������ڣ�</td>
			        <td>
						<input name="creationDate" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getCreationDate()%>" ></td>
			    </tr>
                <tr>
                    <td align="right">�ʲ��˱���</td>
			        <td >
						<input name="bookTypeName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF"value="<%=headerDTO.getBookTypeName()%>" ></td>
                    <td align=right width=8%>�ʲ����ࣺ</td>
			        <td width=17%>
			        	<input name="faContentName" class="readonlyInput" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFaContentName()%>" size="20">
			        </td>
                    <td align=right width=8%>�����ţ�</td>
			        <td width=17%>
						<input name="fromDeptName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromDeptName()%>">
			        </td>
                    <td align=right width=8%>����������</td>
			        <td width=17%>
						<input name="accessSheet" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getAccessSheet()%>">
			        </td>
                </tr>
                <tr>
			    	<td width="8%" height="55" align="right">����˵����</td>
			    	<td width="92%" height="55" colspan="7"><textarea name="createdReason" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" ><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	} else if(transType.equals(AssetsDictConstant.ASS_SUB)){//�ʲ���ֵ��
%>
<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=17%>
						<input name="transNo" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getTransNo()%>">
					</td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=17%>
						<input name="transTypeValue" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getTransTypeValue()%>">
					</td>
					<td align=right width=8%>�������ڣ�</td>
			        <td width=17%>
						<input name="creationDate" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getCreationDate()%>" ></td>
			        <td align=right width=8%>�������</td>
			        <td width=17%>
						<input name="fromGroupName" class="fDtlInput" readonly style="width:100%" value="<%=headerDTO.getFromGroupName()%>"></td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=17%>
						<input name="created" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getCreated()%>">
					</td>
			        <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�������ƣ�</td>
			        <td width=17%>
						<input name="userDeptName" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
			        <td height="20">
					<p align="right">�ʲ��˲���</td>
			        <td height="20">
						<input name="bookTypeName" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getBookTypeName()%>" ></td>
			    </tr>
			    <tr>
					<td align=right width=8%>�ֻ����룺</td>
			        <td width=17%>
						<input name="phoneNumber1" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
			        <td align=right>�����ʼ���</td>
			        <td>
						<input name="email1" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20"></td>
					<td align=right width=8%>��ֵ���ţ�</td>
			        <td width=17%>
						<input name="fromDeptName" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFromDeptName()%>">
					</td>
			        <td align=right >�ʲ����ࣺ</td>
			        <td width=17%>
			        	<input name="faContentName" class="readonlyInput" readonly style="border-style:solid; border-width:0px; width:100%; background-color:#F2F9FF" value="<%=headerDTO.getFaContentName()%>" size="20">
			        </td>
			    </tr>
			    <tr>
					<td width=8% height="40" align=right>������ƣ�</td>
			        <td  height="40"><input name="lossesName" class="fDtlInput" style="width:100%; " value="<%=headerDTO.getLossesName()%>" size="20"></td>
			        <td  height="40" align=right>������ڣ�</td>
			        <td  height="40"><input name="lossesDate" class="fDtlInput" readonly style="width:100%; " value="<%=headerDTO.getLossesDate()%>" size="20"></td>
			        <td  height="40" align=right>��ֵ˵����</td>
			        <td colspan="3"  height="40"><textarea name="createdReason" style="width:100%;height:100%" class="fDtlInput" readonly><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
<%
	}
%>