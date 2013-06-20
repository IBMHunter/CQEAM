<%@ page contentType="text/html;charset=GBK" language="java"%>
<%@page import="com.sino.ams.newasset.constant.AssetsWebAttributes"%>
<%@page import="com.sino.ams.newasset.urgenttrans.dto.UrgentDTO"%>
<%@page import="com.sino.ams.newasset.urgenttrans.dto.UrgentHeaderDTO"%>
<%@page import="com.sino.ams.system.user.dto.SfUserDTO"%>
<%@page import="com.sino.framework.security.bean.SessionUtil"%>
<%
	SfUserDTO userAccount = (SfUserDTO) SessionUtil
			.getUserAccount(request);

	UrgentHeaderDTO headerDTO = null;
	UrgentDTO urgentDTO = null;
	urgentDTO = (UrgentDTO) request
			.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);

	headerDTO = urgentDTO.getHeaderDTO();
	
	String mustStyle = "noEmptyInput"; 
%>
<table width=100% border="0">
	<tr>
		<td align=right width=8%>
			���ݺţ�
		</td>
		<td width=13%>
			<input type="text" name="transNo" class="input_style2" readonly
				style="width: 85%" value="<%=headerDTO.getTransNo()%>">
		</td>
		<td align=right width=8%>
			�������ͣ�
		</td>
		<td width=13%>
			<input type="text" name="transTypeValue" class="input_style2"
				readonly style="width: 85%"
				value="<%=headerDTO.getTransTypeValue()%>">
		</td>   
		<td align=right width=8%>
			����״̬��
		</td>
		<td width=20%>
			<input type="text" name="transStatusName" class="input_style2"
				readonly style="width: 90%"
				value="<%=headerDTO.getTransStatusName()%>">
		</td>  
	</tr>
	<tr>
		<td align=right width=8%>
			�������ţ�
		</td>
		<td width=13%>
			<input name="fromDeptName" class="input_style2" readonly
				style="width: 85%;" value="<%=headerDTO.getFromDeptName()%>">
		</td> 
		<td align=right width=8%>
			�������ڣ�
		</td>
		<td width=13%>
			<input name="creationDate" class="input_style2" readonly
				style="width: 85%;" value="<%=headerDTO.getCreationDate()%>">
		</td>
		<td align=right width=8%>
			�����ˣ�
		</td>
		<td width=13%>
			<input type="text" name="created" class="input_style2" readonly
				style="width: 90%" value="<%=headerDTO.getCreated()%>">
		</td> 
	</tr>
	<tr>
		<td align=right width=8%>
			�����ص㣺
		</td>
		<td width=13%>
			<input type="text" name="fromObjectName" class="<%= mustStyle %>" readonly onClick="do_SelectFromLocation()" title="���ѡ�����ĵ����ص�" 
				style="width: 85%" value="<%=headerDTO.getFromObjectName()%>">
		</td> 
		<td align=right>
			����ִ���ˣ� 
		</td>
		<td>
			<input name="implementByName" class="<%= mustStyle %>" readonly onClick="do_SelectImplement()" title="���ѡ�����ĵ���ִ����" 
				style="width: 85%;" value="<%=headerDTO.getImplementByName()%>">
		</td>
		<td align=right width=8%>
			�鵵�ˣ�
		</td>
		<td >
			<input name="archivedByName" class="<%= mustStyle %>" readonly onClick="do_SelectUser(3)" title="���ѡ�����Ĺ鵵��" 
				style="width: 90%;" value="<%=headerDTO.getArchivedByName()%>">
		</td> 
	</tr>
	<tr>
		<td align=right width=8%>
			����ص㣺 
		</td>
		<td width=13%>
			<input type="text" name="toObjectName" class="<%= mustStyle %>" readonly onClick="do_SelectToLocation()" title="���ѡ�����ĵ���ص�" style="width: 85%" value="<%=headerDTO.getToObjectName()%>">
			<a href= "#" onClick="do_ChoseLocDesc()" title = "���ѡ��������ϵ���ص�" class="linka" >[��]</a>
		</td> 
		<td align=right>
			����ִ���ˣ� 
		</td>
		<td>
			<input name="toImplementByName" class="<%= mustStyle %>" readonly onClick="do_SelectToImplement()" title="���ѡ�����ĵ���ִ����"
				style="width: 85%;" value="<%=headerDTO.getToImplementByName()%>">
		</td>
	</tr>
	<tr> 
		<td width="8%" height="60" align="right">
			��ע��
		</td>
		<td  height="60" colspan="7">
			<textarea name="createdReason" style="width: 100%; height: 100%"
				class="input_style1"><%=headerDTO.getCreatedReason()%></textarea>
		</td>
	</tr>
</table>