<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-3-28
  Time: 18:15:26
  To change this template use File | Settings | File Templates.
--%>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
    String transferType = headerDTO.getTransferType();
%>

<table width=100% border="0">
    <tr>
        <td align=right width=8%>���ݺţ�</td>
        <td width=13%>
            <input name="transNo" class="input_style2" readonly style="width:90%; " value="<%=headerDTO.getTransNo()%>">
        </td>
        <td align=right width=8%>�������ͣ�</td>
        <td width=13%>
            <input name="transTypeValue" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getTransTypeValue()%>">
            <input type="hidden" name="toOrganizationId" value="<%=headerDTO.getToOrganizationId()%>">
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
            <input name="userDeptName" class="input_style2" id="fromDeptName" readonly style="width:95%; " value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
        <td height="20">
        <p align="right">�ʲ��ʲ���</td>
        <td height="20">
            <input name="bookTypeName" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getBookTypeName()%>" ></td>
    </tr>

    <tr>
        <td align=right>�ֻ����룺</td>
        <td>
            <input name="phoneNumber1" class="input_style2" readonly style="width:90%; " value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
        <td align=right>�����ʼ���</td>
        <td>
            <input name="email1" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getEmail()%>" size="20"></td>
        <td align=right>�������ţ�</td>
        <td >        	
            <select name="fromDept" style="display:none;width:95%" class="input_style2" onChange="do_ConfirmChange()"><%=headerDTO.getFromDeptOption()%></select>
            <input style="width:95%" class="input_style2" readonly name="fromDeptData" value="">
        </td>
        <td align=right>�����̶ȣ�</td>
        <td >
            <select name="emergentLevel" class="select_style1" style="width:100%"  onchange="document.getElementById('sf_priority').value = this.value;"><%=headerDTO.getEmergentLevelOption()%></select>
        </td>
    </tr>
    <tr style="height:60px">
        <td style="width:8%;height:100%" align=right >ʵ����ࣺ</td>
        <td style="width:13%;height:100%" >
            <select name="faContentCode" style="width:90%" class="selectNoEmpty" onChange="do_ChangeContentCode()"><%=headerDTO.getFaContentOption()%></select>&nbsp;<font color="red">*</font>
        </td>
        <td style="width:8%;height:100%" align=right valign="top">����˵����</td>
        <td colspan="5" style="width:71%;height:100%">
            <table style="width:100%;height:100%">
                <tr style="width:100%;height:100%">
                    <td style="width:98%"><textarea name="createdReason" class="selectNoEmpty"><%=headerDTO.getCreatedReason()%></textarea></td>
                    <td style="width:2%"><font color="red">*</font></td>
                </tr>
            </table>
        </td>
    </tr>
</table>

