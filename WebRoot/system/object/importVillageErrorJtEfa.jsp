<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>ͨ���ʲ�������´�����Ϣ</title>
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
%>
<body leftmargin="0" topmargin="0">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.object.servlet.ImportVillageAssetsServletJtEfa">
    <input type="hidden" name="act">
    <script type="text/javascript">
      printTitleBar("ͨ���ʲ�������´�����Ϣ")
    </script>
    <table width="100%" border="0" class="queryHeadBg">
        <tr>
            <td width="100%" colspan="15" align="right">
                <img src="/images/eam_images/back.jpg" alt="����" onclick="do_concel();return false;">
                <img src="/images/eam_images/export.jpg" id="exportImg" style="cursor:hand" onclick="do_Export();" title="������Excel">
            </td>
        </tr>
    </table>
    <div id="aa" style="overflow-x:scroll;overflow-y:scroll;position:absolute;top:40px;left:0px;width:100%">
        <table width="600%" class="headerTable" border="1">
            <tr height="20">         
                <td width="6%" align="center">������Ϣ</td>
                <td width="2%" align="center">��˾����</td>
                <td width="2%" align="center">�ʲ���ǩ��</td>
                <td width="4%" align="center">�ʲ�����</td>
                <td width="4%" align="center">����ͺ�</td>
                <td width="2%" align="center">�����˱��</td>
                <td width="2%" align="center">����������</td>
                <td width="4%" align="center">�ص���ϱ���</td>
                <td width="4%" align="center">�ʲ��ص�����</td>
                <td width="2%" align="center">����</td>
                <td width="4%" align="center">�ʲ����������</td>
                <td width="4%" align="center">�ʲ��������</td>
                <td width="2%" align="center">ͨ���ʲ�ԭֵ</td>
                <td width="4%" align="center">�ʲ���������</td>
                <td width="2%" align="center">�۾�����</td>
                <td width="2%" align="center">ͨ���ʲ���ֵ</td>
                <td width="2%" align="center">ͨ���ʲ�����</td>
                <td width="2%" align="center">��Ŀ���</td>
                <td width="3%" align="center">��Ŀ����</td>
                
                <td width="4%" align="center">�۾��˻�</td>
                <td width="4%" align="center">�ʲ�����ʱ��</td>
                <td width="4%" align="center">�۾�ʣ������</td>
                <td width="4%" align="center">��������</td>
                <td width="4%" align="center">��Ӧ��</td>

                <td width="2%" align="center">�ʲ����</td>
                <td width="2%" align="center">��ֵ</td>
                <td width="2%" align="center">�����۾ɶ�</td>
                <td width="2%" align="center">�����۾ɶ�</td>
                <td width="2%" align="center">�ۼ��۾ɶ�</td>
                <td width="2%" align="center">���ڼ�ֵ׼��</td>
                <td width="2%" align="center">�����ֵ׼��</td>
                <td width="2%" align="center">�ۼƼ�ֵ׼��</td>
                
                <td width="2%" align="center">�Ƿ񹲽��豸</td>
                <td width="2%" align="center">�Ƿ����豸</td>
                <td width="3%" align="center">��ע</td>

            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;height:400px;width:100%;position:absolute;top:61px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table width="600%" border="1" bordercolor="#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="6%" align="left"><%=row.getValue("ERROR")%></td>
                <td width="2%" align="left"><%=row.getValue("COMPANY_CODE")%></td>
                <td width="2%" align="left"><%=row.getValue("BARCODE")%></td>
                <td width="4%" align="left"><%=row.getValue("ITEM_NAME")%></td>
                <td width="4%" align="left"><%=row.getValue("ITEM_SPEC")%></td>
                <td width="2%" align="left"><%=row.getValue("EMPLOYEE_NUMBER")%></td>
                <td width="2%" align="left"><%=row.getValue("EMPLOYEE_NAME")%></td>
                <td width="4%" align="left"><%=row.getValue("WORKORDER_OBJECT_CODE")%></td>
                <td width="4%" align="left"><%=row.getValue("WORKORDER_OBJECT_NAME")%></td>
                <td width="2%" align="left"><%=row.getValue("ACTUAL_QTY")%></td>
                <td width="4%" align="left"><%=row.getValue("CONTENT_CODE")%></td>
                <td width="4%" align="left"><%=row.getValue("CONTENT_NAME")%></td>
                <td width="2%" align="left"><%=row.getValue("PRICE")%></td>
                <td width="4%" align="left"><%=row.getValue("VILLAGE_START_DATE")%></td>
                <td width="2%" align="left"><%=row.getValue("TF_DEPRECIATION")%></td>
                <td width="2%" align="left"><%=row.getValue("TF_NET_ASSET_VALUE")%></td>
                <td width="2%" align="left"><%=row.getValue("TF_DEPRN_COST")%></td>
                <td width="2%" align="left"><%=row.getValue("PROJECTID")%></td>
                <td width="3%" align="left"><%=row.getValue("PROJECT_NAME")%></td>
                
                <td width="4%" align="left"><%=row.getValue("DEPRECIATION_ACCOUNT")%></td>
                <td width="4%" align="left"><%=row.getValue("ASSETS_CREATE_DATE")%></td>
                <td width="4%" align="left"><%=row.getValue("DEPRN_LEFT_MONTH")%></td>
                <td width="4%" align="left"><%=row.getValue("DATE_RETIRED")%></td>
                <td width="4%" align="left"><%=row.getValue("MANUFACTURER_NAME")%></td>

                <td width="2%" align="left"><%=row.getValue("ASSET_ID")%></td>
                <td width="2%" align="left"><%=row.getValue("SCRAP_VALUE")%></td>
                <td width="2%" align="left"><%=row.getValue("DEPRN_AMOUNT")%></td>
                <td width="2%" align="left"><%=row.getValue("YTD_DEPRN")%></td>
                <td width="2%" align="left"><%=row.getValue("DEPRN_RESERVE")%></td>
                <td width="2%" align="left"><%=row.getValue("IMPAIR_AMOUNT")%></td>
                <td width="2%" align="left"><%=row.getValue("YTD_IMPAIRMENT")%></td>
                <td width="2%" align="left"><%=row.getValue("IMPAIR_RESERVE")%></td>

                <td width="2%" align="left"><%=row.getValue("CONSTRUCT_STATUS")%></td>
                <td width="2%" align="left"><%=row.getValue("IS_SHARE")%></td>
                <td width="3%" align="left"><%=row.getValue("REMARK")%></td>
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
        mainFrm.action = "/system/object/importVillageAssetsJtEfa.jsp";
        mainFrm.submit();
    }
</script>