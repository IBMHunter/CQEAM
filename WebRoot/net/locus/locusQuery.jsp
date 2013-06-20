<%@ page contentType = "text/html;charset=GBK" language = "java" %>
<%@ page import = "com.sino.base.constant.db.QueryConstant" %>
<%@ page import = "com.sino.base.data.Row" %>
<%@ page import = "com.sino.base.data.RowSet" %>
<%@ page import = "com.sino.base.web.request.upload.RequestParser" %>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import = "com.sino.ams.constant.WebAttrConstant" %>
<%@ page import = "com.sino.ams.constant.DictConstant" %>
<%@ page import = "com.sino.ams.constant.URLDefineList" %>
<%@ page import = "com.sino.base.util.StrUtil" %>
<%@ page import = "com.sino.base.constant.web.WebConstant" %>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  created by YS
  Date: 2007-09-28
  Time: 8:23:36
--%>

<html>

<head>
    <title>�ص��ѯ</title>
</head>

<body  leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_search()')">
<%=WebConstant.WAIT_TIP_MSG%>

<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    Row row = null;
    String countyCode = (String) request.getAttribute(WebAttrConstant.COUNTY_OPTION);
    String objectCategory = parser.getParameter("objectCategory");

%>
<form method = "post" name = "mainFrm">
    <script type = "text/javascript">
        if (<%=objectCategory.equals(DictConstant.NETADDR_BTS)%>) {
            printTitleBar("��վרҵ--�ص���Ϣ")
        } else if (<%=objectCategory.equals(DictConstant.NETADDR_DATA)%>) {
            printTitleBar("����רҵ--�ص��ѯ")
        } else if (<%=objectCategory.equals(DictConstant.NETADDR_TRANS)%>) {
            printTitleBar("����רҵ--�ص��ѯ")
        } else if (<%=objectCategory.equals(DictConstant.NETADDR_EXCHG)%>) {
            printTitleBar("����רҵ--�ص��ѯ")
        } else if (<%=objectCategory.equals(DictConstant.NETADDR_BSC)%>) {
            printTitleBar("���רҵ--�ص��ѯ")
        } else if (<%=objectCategory.equals(DictConstant.NETADDR_NETOPT)%>) {
            printTitleBar("����רҵ--�ص��ѯ")
        } else if (<%=objectCategory.equals(DictConstant.NETADDR_ELE)%>) {
            printTitleBar("����רҵ--�ص��ѯ")
        }
    </script>
    <table width = "100%" border = "0" >
        <tr>
            <td width = "10%" align = "right">�ص��ţ�</td>
            <td width = "20%" colspan = 2><input name = "workorderObjectCode" class="input_style1" style = "width:100%" type = "text" value ="<%=StrUtil.nullToString( request.getParameter("workorderObjectCode"))%>"></td>
            <td width = "10%" align = "right">�ص��ƣ�</td>
            <td width = "20%"><input name = "workorderObjectName" class="input_style1" style = "width:100%" type = "text" value = "<%=StrUtil.nullToString(request.getParameter("workorderObjectName"))%>"></td>
            <td width = "10%" align = "right">�������أ�</td>
            <td width = "25%"><select style = "width:100%" class="select_style1" name = "countyCode"><%=countyCode%></select></td>
        </tr>
        <tr>
            <td align = "center"><img src = "/images/eam_images/search.jpg" style = "cursor:'hand'" onclick = "do_search();" alt = "��ѯ"></td>
            <td><img src = "/images/eam_images/export.jpg" id = "queryImg" style = "cursor:'hand'" onclick = "do_exportToExcel()" alt = "����Excel"></td>
            <td><img src = "/images/eam_images/detail_info.jpg" id = "particularImg" style = "cursor:'hand'" onclick = "do_showDetail()" alt = "�ص���ϸ��Ϣ"></td>
            <td><img src = "/images/button/showEqp.gif" id = "particularImg" style = "cursor:'hand'" onclick = "do_showEqp();" alt = "�ص��ŵ��豸��Ϣ"></td>
            <td></td>
        </tr>
    </table>

    <input type = "hidden" name = "act" value = "<%=parser.getParameter("act")%>">
    <input type = "hidden" name = "objectCategory" value = "<%=objectCategory%>">

    <%--<table width = "835" align = "left" border = "1" cellpadding = "2" cellspacing = "0" style = "" class = "headerTable">--%>
    <%--<tr><td width = "2%" align = "center" style = "padding:0">--%>
    <%--<td width = "12%" align = "center">�ص����</td>--%>
    <%--<td width = "22%" align = "center">�ص�����</td>--%>
    <%--<td width = "23%" align = "center">��ַ</td>--%>
    <%--<td width = "11%" align = "center">��������</td>--%>
    <%--<td width = "15%" align = "center">Ѳ��ģʽ</td>--%>
    <%--<td width = "15%" align = "center">ʧЧ����</td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <script type = "text/javascript">
        var columnArr = new Array("", "�ص���", "�ص���", "���ڵص�", "��������", "Ѳ��ģʽ", "ʧЧ����");
        var widthArr = new Array("4%", "12%", "22%", "23%", "10%", "14%", "15%");
        printTableHead(columnArr, widthArr);
    </script>


    <div style = "overflow-y:scroll;width:100%;left:1px;height:332px" align = "left">
        <table width = "100%" border = "1" bordercolor = "#666666">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr class = "dataTR">
                <td width = "4%" align = "center"><input type = "radio" id = "workorderObjectNo" name = "workorderObjectNo" value = "<%=row.getValue("WORKORDER_OBJECT_NO")%>"></td>
                <td style = "word-wrap:break-word" height = "22" width = "12%" align = "center"><%=row.getValue("WORKORDER_OBJECT_CODE")%></td>
                <td style = "word-wrap:break-word" height = "22" width = "22%" align = "center"><%=row.getValue("WORKORDER_OBJECT_NAME")%></td>
                <td style = "word-wrap:break-word" height = "22" width = "23%" align = "center"><%=row.getValue("WORKORDER_OBJECT_LOCATION")%></td>
                <td style = "word-wrap:break-word" height = "22" width = "10%" align = "center"><%=row.getValue("COUNTY_NAME")%></td>
                <td style = "word-wrap:break-word" height = "22" width = "14%" align = "center"><%=row.getValue("ISALL")%></td>
                <td style = "word-wrap:break-word" height = "22" width = "15%" align = "center"><%=row.getValue("DISABLE_DATE")%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>

<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
<jsp:include page = "<%=URLDefineList.MESSAGE_PROCESS%>" flush = "true" />
</body>
</html>
<script type = "text/javascript">
    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible"
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "<%=URLDefineList.LOCUS_SERVLET%>";
        //        alert(mainFrm.action);
        mainFrm.submit();
    }

    function do_showDetail() {
        var workorderObjectNo = getRadioValue("workorderObjectNo");
        if (workorderObjectNo != "") {
            var url = "<%=URLDefineList.LOCUS_SERVLET%>?act=<%=WebActionConstant.DETAIL_ACTION%>&workorderObjectNo=" + workorderObjectNo + "&objectCategory=<%=objectCategory%>";
            //        alert(getRadioValue("workorderObjectNo"));
            //        alert(url);
            var popscript = "";
            if (<%=objectCategory.equals(DictConstant.NETADDR_BTS)%>) {
                popscript = 'width=870,height=700,top=1,left=100,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=no, status=yes';
            } else {
                popscript = 'width=400,height=300,top=1,left=100,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=no, status=yes';
            }
            window.open(url, 'basePot', popscript);
        }
        else {
            alert("��ѡ��һ���ص�");
        }
    }

    function do_exportToExcel() {
        mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainFrm.action = "<%=URLDefineList.LOCUS_SERVLET%>";
        mainFrm.submit();
        //        alert(getRadioValue("workorderObjectNo"));
    }
    function do_getObjNo() {
        alert(getRadioValue("workorderObjectNo"));
    }
    function do_showEqp() {
        var workorderObjectNo = getRadioValue("workorderObjectNo");
        if (workorderObjectNo != "") {
            var url = "<%=URLDefineList.LOCUS_SERVLET%>?act=SHOW_EQP&workorderObjectNo=" + workorderObjectNo ;

            var popscript = "";
            popscript = 'width=800,height=500,top=100,left=100,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=yes';
            window.open(url, 'basePot', popscript);
        }
        else {
            alert("��ѡ��һ���ص�");
        }


    }
</script>