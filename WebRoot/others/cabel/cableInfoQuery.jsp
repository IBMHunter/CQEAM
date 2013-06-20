<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>�����ʲ�ά��</title>
</head>

<body leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_SearchResource()');">
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
%>
<form name="mainFrm" method="POST">
    <script type="text/javascript">
        printTitleBar("�����ʲ�ά��");
    </script>
    <table border="0" width="100%" class="queryTable" id="table1">
        <tr>

            <td width="12%" align="center">��ǩ�ţ�</td>
            <td width=""><input type="text" name="barcode" value="<%=reqParser.getParameter("barcode")%>"
                                style="width:100%" class="input_style1"></td>
            <td width="12%" align="center">���跽ʽ��</td>
            <td width="12%"><select class="select_style1"
                    name="spreadType"><%=request.getAttribute(WebAttrConstant.SPREAD_TYPE_OPTION)%></select>
            </td>
            <td width="12%" align="center">������;��</td>
            <td width="12%"><select class="select_style1"
                    name="cabelUsage"><%=request.getAttribute(WebAttrConstant.CABEL_USAGE_OPTION)%></select>
            </td>
            <td width="12%" align="center"><img src="/images/eam_images/search.jpg" alt="��ѯ�豸"
                                                onClick="do_Search(); return false;"></td>
            <td width="12%" align="center"><%--<img src="/images/eam_images/new_add.jpg" alt="�������"
                                                onClick="do_Create(); return false;">--%></td>
        </tr>
    </table>
    <input type="hidden" name="act" value="<%=reqParser.getParameter("act")%>">


  <script type="text/javascript">
        var columnArr = new Array("��ǩ��", "�豸����", "����ͺ�", "ʼ��ַ", "ֹ��ַ","������;","���跽ʽ","����");
        var widthArr = new Array("10%", "15%", "10%", "20%", "20%","10%","10%","5%");
        printTableHead(columnArr, widthArr);
    </script>

<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && !rows.isEmpty()) {
%>
<div style="overflow-y:scroll;height:362px;width:100%;left:1px;margin-left:0px" align="left">
    <table width="100%" border="1" bordercolor="#666666">

        <%
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
        %>
        <tr class="dataTR" onclick="do_ShowDetail('<%=row.getValue("BARCODE")%>'); return false;">
            <td style="word-wrap:break-word" height="22" width="10%"
                align="center"><%=row.getValue("BARCODE")%></td>
            <td style="word-wrap:break-word" height="22" width="15%"
                align="center"><%=row.getValue("ITEM_NAME")%></td>
            <td style="word-wrap:break-word" height="22" width="10%"
                align="center"><%=row.getValue("ITEM_SPEC")%></td>
            <td style="word-wrap:break-word" height="22" width="20%"
                align="center"><%=row.getValue("FROM_ADDRESS")%></td>
            <td style="word-wrap:break-word" height="22" width="20%"
                align="center"><%=row.getValue("TO_ADDRESS")%></td>
            <td style="word-wrap:break-word" height="22" width="10%"
                align="center"><%=row.getValue("CABEL_USAGE_NAME")%></td>
            <td style="word-wrap:break-word" height="22" width="10%"
                align="center"><%=row.getValue("SPREAD_TYPE_NAME")%></td>
            <td style="word-wrap:break-word" height="22" width="5%"
                align="center"><%=row.getValue("CABEL_DEPTH")%></td>

        </tr>
        <%
            }         }
        %>
    </table>
</div>
</form>
<div> <%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
 <%=WebConstant.WAIT_TIP_MSG%>

</body>
</html>

<script language="javascript">

    function do_Search() {        //��ѯ������ѯ
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "<%= URLDefineList.CABEL_INFO_SERVLET%>"
        mainFrm.submit();
    }

    function do_Create() {        //������˾
        mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
        mainFrm.action = "<%= URLDefineList.CABEL_INFO_SERVLET%>"
        mainFrm.submit();
    }

    function do_ShowDetail(barcode) {       //��ѯ��ϸ��Ϣ
        mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";
        mainFrm.action = "<%= URLDefineList.CABEL_INFO_SERVLET%>?barcode=" + barcode;
        mainFrm.submit();
    }
</script>