<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.web.request.upload.RequestParser"%>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-4-26
  Time: 19:25:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@page import="com.sino.base.util.StrUtil"%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    
    String isNew = StrUtil.nullToString( request.getParameter( "isNew" ) );
    
    String forwardURL = "/system/object/importItem.jsp";
    String title = "ʵ���ʲ���������������Ϣ";
    
    if( "Y".equals( isNew ) ){
    	forwardURL = "/system/object/importItem.jsp?isNew=Y";
    	title = "ʵ���ʲ���������������Ϣ";
    }
%>
<html>
<head>
    <title><%= title %></title>
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
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.object.servlet.ImportItemServlet">
    <input type="hidden" name="act">
    <script type="text/javascript">
      printTitleBar("<%= title %>")
    </script>
    <table width="100%" border="0" class="queryHeadBg">
        <tr>
            <td width="100%" colspan="15" align="right">
                <img src="/images/eam_images/back.jpg" alt="����" onclick="do_concel();return false;">
                <img src="/images/eam_images/export.jpg" id="exportImg" style="cursor:hand" onclick="do_Export();" title="������Excel">
            </td>
        </tr>
    </table>
    <div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:40px;left:1px;width:100%" class="crystalScroll">
        <table width="300%" class="headerTable" border="1">
            <tr height="20">
                <td width="8%" align="center">������Ϣ</td>
                <td width="4%" align="center">�ʲ��ʲ�</td>
                <td width="4%" align="center">��ǩ��</td>
                <td width="6%" align="center">���ʲ�����</td>
                <td width="6%" align="center">�¹���ͺ�</td>
                <td width="6%" align="center">�µص�������</td>
                <td width="4%" align="center">�����β��Ŵ���</td>
                <td width="4%" align="center">��������Ա�����</td>
                <td width="4%" align="center">��ʵ�ﲿ�Ŵ���</td>
                <td width="4%" align="center">��ʹ�ò��Ŵ���</td>
                <td width="4%" align="center">��ʹ��������</td>
                <td width="4%" align="center">�³��̴���</td>
                <td width="4%" align="center">����Ԫ����</td>
                <td width="4%" align="center">��Ͷ�ʷ������</td>
                <td width="4%" align="center">��ҵ��ƽ̨����</td>
                <td width="4%" align="center">�������α���</td>
                <td width="4%" align="center">�±�עһ</td>
                <td width="4%" align="center">�¹���״̬</td>
                <td width="4%" align="center">�¹���״̬</td>
            </tr>
        </table>
    </div>
    <div style="overflow:scroll;height:400px;width:100%;position:absolute;top:61px;left:1px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table width="300%" border="1" bordercolor="#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="8%" align="left"><%=row.getValue("ERROR")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("BOOK_TYPE_CODE")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("BARCODE")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("NEW_ITEM_NAME")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("NEW_ITEM_SPEC")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("NEW_OBJECT_CODE")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_RESPONSIBILITY_DEPT")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_EMPLOYEE_NUMBER")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_SPECIALITY_DEPT")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_MAINTAIN_DEPT")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_MAINTAIN_USER")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_MANUFACTURER_ID")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_LNE_ID")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_CEX_ID")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_OPE_ID")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_NLE_ID")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_REMARK1")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_CONSTRUCT_STATUS")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("NEW_SHARE_STATUS")%>
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
        mainFrm.action = "<%= forwardURL %>";
        mainFrm.submit();
    }
</script>