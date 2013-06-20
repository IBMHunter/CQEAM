<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-5-22
  Time: 15:40:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>�����ʲ����������Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
</head>
<%
    RowSet rows = (RowSet) request.getAttribute(WebAttrConstant.ETS_SPARE_DTO);
    Row row = null;
//    String qryType = request.getParameter("qryType");
%>
<body leftmargin="0" topmargin="0">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.object.servlet.ImportLastingAssetsServlet">
    <input type="hidden" name="act">
    <script type="text/javascript">
      printTitleBar("�����ʲ����������Ϣ")
    </script>
    <table width="100%" border="0" class="queryHeadBg">
        <tr>
            <td width="100%" colspan="15" align="right">
                <img src="/images/eam_images/back.jpg" alt="����" onclick="do_concel();return false;">
                <img src="/images/eam_images/export.jpg" id="exportImg" style="cursor:hand" onclick="do_Export();" title="������Excel">
            </td>
        </tr>
    </table>
    <div id="headDiv" style="overflow:hidden;position:absolute;top:40px;left:1px;width:1083px">
        <table width="400%" class="headerTable" border="1">
            <tr height="20">
                <td width="6%" align="center">������Ϣ</td>
                <td width="3%" align="center">��˾����</td>
                <td width="3%" align="center">�����ʲ���ǩ��</td>
                <td width="5%" align="center">�ʲ�����</td>
                <td width="5%" align="center">����ͺ�</td>
                <td width="3%" align="center">�����˱��</td>
                <td width="3%" align="center">����������</td>
                <td width="6%" align="center">�ص���ϱ���</td>
                <td width="3%" align="center">�����</td>
                <td width="3%" align="center">�豸����</td>
                <td width="4%" align="center">�ʲ����������</td>
                <td width="4%" align="center">�ʲ��������</td>
                <td width="6%" align="center">�ʲ��ص�����</td>
                <td width="3%" align="center">ʵ�ﲿ�Ŵ���</td>
                <td width="3%" align="center">ʹ��������</td>
                <td width="3%" align="center">ʹ�ò��Ŵ���</td>
                <td width="3%" align="center">��ʼ����</td>
                <td width="3%" align="center">��ֹ����</td>
                <td width="3%" align="center">ǩԼ��λ</td>
                <td width="3%" align="center">����</td>
                <td width="3%" align="center">�����</td>
                <td width="3%" align="center">�����</td>
                <td width="6%" align="center">��ע</td>

            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;height:450px;width:1100px;position:absolute;top:61px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="400%" border="1" bordercolor="#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="6%" align="left"><%=row.getValue("ERROR")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("COMPANY_CODE")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("BARCODE")%>
                </td>
                <td width="5%" align="left"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td width="5%" align="left"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("EMPLOYEE_NUMBER")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("EMPLOYEE_NAME")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("WORKORDER_OBJECT_CODE")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("POWER")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("EQUIPMENT_PERFORMANCE")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("CONTENT_CODE")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("CONTENT_NAME")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("WORKORDER_OBJECT_NAME")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("SPECIALITY_DEPT")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("MAINTAIN_USER")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("MAINTAIN_DEPT")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("START_DATE")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("END_DATE")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("RENT_PERSON")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("TENANCY")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("YEAR_RENTAL")%>
                </td>
                <td width="3%" align="left"><%=row.getValue("MONTH_REANTAL")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("REMARK")%>
                </td>
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
        mainFrm.action = "/system/object/importLastingAssets.jsp";
        mainFrm.submit();
    }
</script>