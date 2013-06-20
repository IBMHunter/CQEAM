<%@ page import="com.sino.ams.spare2.dto.AmsItemAllocateHDTO" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%--
  Created by HERRY.
  Date: 2008-7-30
  Time: 16:02:21
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>����ʵ����ó����ӡ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
</head>
<%
    AmsItemAllocateHDTO headerDto = (AmsItemAllocateHDTO) request.getAttribute("AIT_HEADER");
//      SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
%>
<body leftmargin="1" topmargin="1">
<h2 align="center">����ʵ����ó��ⵥ</h2>

<table width="100%" id="table2" cellspacing="1" border="1" bordercolor="#000000">
    <tr height="22">
        <td width="12%" align="center">��������</td>
        <td width="20%"><%=headerDto.getTransNo()%>
        </td>
        <td width="13%" align="center">�����ֿ�</td>
        <td width="20%"><%=headerDto.getFromObjectName()%>
        </td>

    </tr>
    <tr height="22">
        <td align="center">�������</td>
        <td><%=headerDto.getToOrganizationName()%>
        </td>
        <td align="center">Ԥ�ƹ黹����</td>
        <td><%=headerDto.getRespectReturnDate()%>
        </td>
    </tr>

    <tr height="22">
        <td align="center">���ⲿ��</td>
        <td><%=headerDto.getFreightDeptName()%>
        </td>
        <td align="center">���ⲿ�ű�������Ա</td>
        <td><%=headerDto.getFreightMisUserName()%>
        </td>


    </tr>
    <tr>
        <td align="center">���ⲿ��ʵ��ֹ�Ա</td>
        <td><%=headerDto.getFreightUserName()%>
        </td>
        <td></td>
        <td></td>
    </tr>

    <tr height="22">
        <td align="center">���ձ�����Ա</td>
        <td><%=headerDto.getReceiveUserName()%>
        </td>
        <td align="center">���ձ�����Ա��ϵ�绰</td>
        <td><%=headerDto.getReceiveUserTel()%>
        </td>

    </tr>

    <%--<tr height="30">
        <td align="center">��ע</td>
        <td colspan="3"><%=headerDto.getRemark()%>
        </td>
    </tr>--%>
</table>


<table width="100%" border="1" bordercolor="#000000" cellpadding="1">
    <tr height="22">
        <td align="center" width="">�豸����</td>
        <td align="center" width="">�ͺ�</td>
        <td align="center" width="">����</td>
        <td align="center" width="">����ʱ��</td>
        <td align="center" width="">״̬</td>
        <td align="center" width="">��ע</td>
    </tr>
    <%
        if (rows != null && rows.getSize() > 0) {
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
    %>
    <tr height="22" onMouseMove="style.backgroundColor='#EFEFEF'"
        onMouseOut="style.backgroundColor='#ffffff'"
            >

        <td width="18%" align="left"><%=row.getValue("ITEM_NAME")%>
        </td>
        <td width="15%"><%=String.valueOf(row.getValue("ITEM_SPEC"))%>
        </td>
        <td width="8%" align="right"><%=row.getValue("FREIGHT_QUANTITY")%>
        </td>
        <td width="8%" align="center"><%=row.getValue("FREIGHT_DATE")%>
        </td>
        <td width="7%" align="center"><%=row.getValue("STATUS")%>
        </td>
        <td width="7%" align="center"><%=row.getValue("REMARK")%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</body>
<script type="text/javascript">
    function beforePrint() {

    }
</script>
</html>