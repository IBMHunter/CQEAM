<%@ page contentType="text/html;charset=GBK" language="java"%>
<%@page import="com.sino.ams.system.user.dto.SfUserDTO"%>
<%@page import="com.sino.ams.newasset.lose.dto.LoseHeaderDTO"%>
<%@page import="com.sino.ams.newasset.lose.dto.LoseDTO"%>
<%@page import="com.sino.ams.newasset.constant.AssetsWebAttributes"%>
<%@page import="com.sino.framework.security.bean.SessionUtil"%>
<%
	SfUserDTO userAccount = (SfUserDTO) SessionUtil
			.getUserAccount(request);

	LoseHeaderDTO headerDTO = null;
	LoseDTO loseDTO = null;
	loseDTO = (LoseDTO) request
			.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);

	headerDTO = loseDTO.getHeaderDTO();
	String transStatus = headerDTO.getTransStatus();
%>
<table width=100% border="0">
	<tr>
		<td align=right width=8%>
			���ݺţ�
		</td>
		<td width=13%>
			<input type="text" name="transNo" class="input_style2" readonly
				style="width: 100%" value="<%=headerDTO.getTransNo()%>">
		</td>
		<td align=right width=8%>
			�������ͣ�
		</td>
		<td width=13%>
			<input type="text" name="transTypeValue" class="input_style2"
				readonly style="width: 100%"
				value="<%=headerDTO.getTransTypeValue()%>">
		</td>  
		<td align=right width=8%>
			��˾���ƣ�
		</td>
		<td width=13%>
			<input name="fromCompanyName" class="input_style2" readonly
				style="width: 100%;" value="<%=headerDTO.getFromCompanyName()%>">
		</td> 
        <td align=right width=8%>��ʧ���ţ�</td>
        <td width=28%>
        	<input name="fromDeptName" class="input_style2" readonly style="width:100%; cursor:hand" value="<%=headerDTO.getFromDeptName()%>" >
			 
        </td>  
	</tr>
	<tr> 
		
		<td align=right width=8%>
			�����ˣ�
		</td>
		<td width=13%>
			<input type="text" name="created" class="input_style2" readonly
				style="width: 100%" value="<%=headerDTO.getCreated()%>">
		</td> 
		<td align=right>
			�ֻ����룺
		</td>
		<td>
			<input name="phoneNumber" class="input_style2" readonly
				style="width: 100%;" value="<%=headerDTO.getPhoneNumber()%>">
		</td>
		<td align=right width=8%>
			�������ڣ�
		</td>
		<td width=13%>
			<input name="creationDate" class="input_style2" readonly style="width: 100%;" value="<%=headerDTO.getCreationDate()%>">
		</td> 
		<td align=right>�����̶ȣ�</td>
        <td >
			<% if ("IN_PROCESS".equals(transStatus)||"APPROVED".equals(transStatus)) {%>
			<select name="emergentLevel" class="select_style1" disabled style="width:100%" ><%=headerDTO.getEmergentLevelOption()%></select>
			<%
	          } else {
	        %>
	        <select name="emergentLevel" class="select_style1" style="width:100%"><%=headerDTO.getEmergentLevelOption()%></select>
			<%
	          }
	        %>
		</td> 
	</tr>
	<tr> 
    <tr>
    	<% if (!"IN_PROCESS".equals(transStatus)) {%>
        <td align=right >ʵ�������<font color="red">*</font></td>
        <td >
        	<select name="specialityDept" style="width:100%" class="selectNoEmpty" onmouseover="do_ProcessOptionWidth(this)" ><%=headerDTO.getSpecialityDeptOption()%></select>
	    <% } %>
        </td>
        <td align="right" valign="top">����˵��:<font color="red">*</font></td>
        <% if (!"IN_PROCESS".equals(transStatus)) {%>
        <td colspan="5" height="40"><textarea name="createdReason" class="textareaNoEmpty"><%=headerDTO.getCreatedReason()%></textarea></td>
    	<% } else { %>
    	<td colspan="7" height="40"><textarea name="createdReason" class="textareaNoEmpty"><%=headerDTO.getCreatedReason()%></textarea></td>
    	<% } %>
    </tr>

	<tr> 
		<td >&nbsp;
		</td>
	</tr>
</table>