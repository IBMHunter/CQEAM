<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-5-27
  Time: 10:35:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.htm"%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>���ŵ�ֵ�׺��ʲ����������Ϣ</title>
</head>
<%
    RowSet rows = (RowSet) request.getAttribute(WebAttrConstant.ETS_SPARE_DTO);
    Row row = null;
%>
<body leftmargin="0" topmargin="0">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.object.servlet.ImportDzyhAssetsServletJt">
    <input type="hidden" name="act">
    <script type="text/javascript">
      printTitleBar("���ŵ�ֵ�׺��ʲ����������Ϣ")
    </script>
    <table width="100%" border="0" class="queryTable">
        <tr>
            <td width="100%" colspan="15" align="right">
                <img src="/images/eam_images/back.jpg" alt="����" onclick="do_concel();return false;">
                <img src="/images/eam_images/export.jpg" id="exportImg" style="cursor:hand" onclick="do_Export();" title="������Excel">
            </td>
        </tr>
    </table>
    <div id="aa" style="overflow-x:hidden;overflow-y:scroll;position:absolute;top:40px;left:0px;width:100%">
        <table width="350%" class="headerTable" border="1">
            <tr height="20">
                <td width="6%" align="center">������Ϣ</td>
                <td width="3%" align="center">��˾����</td>
                <td width="4%" align="center">��ֵ�׺�Ʒ��ǩ��</td>
                <td width="5%" align="center">��ֵ�׺�Ʒ����</td>
                <td width="5%" align="center">����ͺ�</td>
                <td width="2%" align="center">������λ</td>
                <td width="4%" align="center">�ʲ��ص�������ϱ���</td>
                <td width="5%" align="center">�ʲ��ص�����</td>
                <td width="3%" align="center">�ʲ�����ԱԱ�����</td>
                <td width="3%" align="center">����������</td>
                <td width="5%" align="center">��Ҫ�ͺĶ�ӦĿ¼�����ϴ���</td>
                <td width="5%" align="center">����</td>
                <td width="3%" align="center">�豸״̬����</td>
                <td width="3%" align="center">ʵ�ﲿ�Ŵ���</td>
                <td width="3%" align="center">רҵ������Ա�����</td>
                <td width="3%" align="center">ʵ��ʹ��������</td>
                <td width="3%" align="center">��ֵ�׺�Ʒ��ֵ</td>
                <td width="3%" align="center">�ʲ���������</td>
                <td width="6%" align="center">��ע</td>

            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;height:400px;width:100%;position:absolute;top:61px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table width="350%" border="1" bordercolor="#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="6%" align="left"><%=row.getValue("ERROR")%></td>
                <td width="3%" align="left"><%=row.getValue("COMPANY_CODE")%></td>
                <td width="4%" align="left"><%=row.getValue("BARCODE")%></td>
                <td width="5%" align="left"><%=row.getValue("ITEM_NAME")%></td>
                <td width="5%" align="left"><%=row.getValue("ITEM_SPEC")%></td>
                <td width="2%" align="left"><%=row.getValue("ITEM_UNIT")%></td>
                <td width="4%" align="left"><%=row.getValue("WORKORDER_OBJECT_CODE")%></td>
                <td width="5%" align="left"><%=row.getValue("WORKORDER_OBJECT_NAME")%></td>
                <td width="3%" align="left"><%=row.getValue("RESPONSIBILITY_USER")%></td>
                <td width="3%" align="left"><%=row.getValue("EMPLOYEE_NAME")%></td>
                <td width="5%" align="left"><%=row.getValue("CONTENT_CODE")%></td>
                <td width="5%" align="left"><%=row.getValue("MANUFACTURER_ID")%></td>
                <td width="3%" align="left"><%=row.getValue("ITEM_STATUS")%></td>
                <td width="3%" align="left"><%=row.getValue("SPECIALITY_DEPT")%></td>
                <td width="3%" align="left"><%=row.getValue("SPECIALITY_USER")%></td>
                <td width="3%" align="left"><%=row.getValue("MAINTAIN_USER")%></td>
                <td width="3%" align="left"><%=row.getValue("PRICE")%></td>
                <td width="3%" align="left"><%=row.getValue("DZYH_START_DATE")%></td>
                <td width="6%" align="left"><%=row.getValue("REMARK")%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<%--<div><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>--%>
</body>
</html>
<script type="text/javascript">
    function do_Export() {
        document.mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        document.mainFrm.submit();
    }
    function do_concel() {
        mainFrm.action = "/system/object/importDzyhAssetsJt.jsp";
        mainFrm.submit();
    }
</script>