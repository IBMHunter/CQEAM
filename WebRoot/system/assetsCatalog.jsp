<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>�ʲ�Ŀ¼��ѯ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
</head>
<body leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_Search()')">
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);    
    String assetName = reqParser.getParameter("assetName");
    String enable = reqParser.getParameter("enable");
    String importantFlag = reqParser.getParameter("importantFlag");
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.system.assetcatalog.servlet.AssetCatalogServlet">
    <script language="javascript">
        printTitleBar("�ʲ�Ŀ¼��ѯ");
    </script>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="groupId" value="">
    <jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet"/>
    <table border="0" width="100%" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td width="12%" align="right">�ʲ����ƣ�</td>
            <td width="20%"><input type="text" name="assetName" class="input_style1"  style="width:100%" value="<%=assetName%>"></td>
            <td width="12%" align="right">�Ƿ���Ч��</td>
            <td width="10%">
            	<select name="enable" class="select_style1" style="width:100%">
            		<option value="">--��ѡ��--</option>
            		<option value="Y" <%if("Y".equals(enable)){%>selected<%}%>>��</option>
            		<option value="N" <%if("N".equals(enable)){%>selected<%}%>>��</option>
            	</select>
            </td>
            <td width="12%" align="right">�Ƿ���Ҫ�ʲ���</td>
            <td width="10%">
            	<select name="importantFlag" class="select_style1" style="width:100%">
            		<option value="">--��ѡ��--</option>
            		<option value="Y" <%if("Y".equals(importantFlag)){%>selected<%}%>>��</option>
            		<option value="N" <%if("N".equals(importantFlag)){%>selected<%}%>>��</option>
            	</select>
            </td>
            <td width="8%" align="center"><img src="/images/eam_images/search.jpg" alt="��ѯ�ʲ�Ŀ¼��Ϣ"
                                               onClick="do_Search(); return false;"></td>
        </tr>
    </table>

    <script type="text/javascript">
            var columnArr = new Array("�ʲ�����","�ʲ�Ŀ¼����","�ʲ�Ŀ¼����","�Ƿ���Ч","�Ƿ���Ҫ�ʲ�");
            var widthArr = new Array("20%","40%","20%","10%","10%");
            printTableHead(columnArr,widthArr);
        </script>
<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && !rows.isEmpty()) {
%>
<div style="overflow-y:scroll;height:362px;width:100%;left:1px;margin-left:0px">
    <table width="100%" border="1" bordercolor="#666666">

        <%
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
        %>
        <tr class="dataTR">
            <td style="word-wrap:break-word" height="22" align="center" width="20%"><%=row.getValue("ASSET_NAME")%></td>
            <td style="word-wrap:break-word" height="22"  width="40%"><%=row.getValue("CONTENT_NAME")%></td>
            <td style="word-wrap:break-word" height="22" align="center" width="20%"><%=row.getValue("CONTENT_CODE")%></td>
            <td style="word-wrap:break-word" height="22" width="10%"><%=row.getValue("ENABLE")%></td>
            <td style="word-wrap:break-word" height="22" width="10%"><%=row.getValue("IMPORTANT_FLAG")%></td>
        </tr>
        <%
            }  }
        %>
    </table>
</div>
 </form>
<%
    if (rows != null && !rows.isEmpty()) {
%>
<div id="pageNaviDiv" style="position:absolute;top:87%;left:0;"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>
<%=WebConstant.WAIT_TIP_MSG%>
</body>
</html>
<script language="javascript">
    function do_Search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
    }
</script>