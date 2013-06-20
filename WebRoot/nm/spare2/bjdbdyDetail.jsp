<%--
  Created by HERRY.
  Date: 2008-7-30
  Time: 17:54:57
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.nm.spare2.dto.AmsItemAllocateHDTO" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<html>
<head><title>�����������ݴ�ӡ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
</head>
<%
    AmsItemAllocateHDTO headerDto = (AmsItemAllocateHDTO) request.getAttribute("AIT_HEADER");
%>
<body leftmargin="1" topmargin="1">
<h2 align="center">����������</h2>
<table width="100%">
<tr>
    <td>
        <table width="100%" id="table2" cellspacing="1" border="1" bordercolor="#000000">
            <tr height="22">
                <td width="13%" align="center">��������</td>
                <td width="20%"><%=headerDto.getTransNo()%>
                </td>
                <td width="13%" align="center">�����ֿ�</td>
                <td width="23%"><%=headerDto.getFromObjectName()%>
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
                <td align="center">������</td>
                <td><%=headerDto.getCreatedUser()%>
                </td>
                <td align="center">��������</td>
                <td><%=headerDto.getCreationDate()%>
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
            <tr height="22">
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

            <tr height="22">
                <td align="center">��ע</td>
                <td colspan="3"><%=headerDto.getRemark()%>
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr>
    <td>
        <table width="100%" border="1" bordercolor="#000000" cellspacing="1">
                    <tr height="22">
                        <td align="center">���ϱ���</td>
                        <td align="center">�豸����</td>
                        <td align="center">����ͺ�</td>
                        <td align="center">��������</td>
                    </tr>
                    <%
                        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
                        if (rows != null && !rows.isEmpty()) {
                            Row row = null;
                            String quantity = "";
                            for (int i = 0; i < rows.getSize(); i++) {
                                row = rows.getRow(i);
                                //quantity = Integer.parseInt(row.getValue("QUANTITY").toString());
                                quantity = (row.getValue("QUANTITY").toString()).equals("")?"0":(row.getValue("QUANTITY").toString());
                                
                    %>
                    <tr height="22" id="xhTr<%=i%>" onMouseMove="style.backgroundColor='#EFEFEF'"
                        onMouseOut="style.backgroundColor='#ffffff'">
                        <td width="10%" align="center"><%=row.getValue("BARCODE")%>
                        </td>
                        <td width="18%"><%=row.getValue("ITEM_NAME")%>
                        </td>
                        <td width="18%"><%=row.getValue("ITEM_SPEC")%>
                        </td>
                        <td width="7%" align="right"><%=quantity%>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
        </table>
    </td>
</tr>
</table>
<%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</body>
<script type="text/javascript">
    function beforePrint() {
    }
</script>
</html>