<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.constant.*" %>
<%@ page import="com.sino.ams.system.rent.dto.RentDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2007-10-18
  Time: 15:57:18
  To change this template use File | Settings | File Templates.
--%>
<html>
<head><title>�����ʲ���ѯ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>

</head>
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    String action = reqParser.getParameter("act");
    String organizationId = (String) request.getAttribute(WebAttrConstant.CITY_OPTION);
    RentDTO dto = (RentDTO) request.getAttribute("RENT_QUERY_DTO");

//    String fromDate = StrUtil.nullToString(request.getParameter("fromDate"));
//    String toDate = StrUtil.nullToString(request.getParameter("toDate"));
%>

<body onkeydown="autoExeFunction('do_search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<form action="/servlet/com.sino.ams.system.rent.servlet.RentPriviQueryServlet" name="mainFrm" method="post">
    <script type="text/javascript">
        printTitleBar("�����ʲ���ѯ")
    </script>
    <table border="1" width="100%" class="queryHeadBg">
        <tr>
            <td  width="7%" align="right">��˾��</td>
            <td width="15%" ><select style="width:85%"  name="organizationId" ><%=organizationId%></select></td>
            <td width="6%" align="right">���룺</td>
            <td width="15%" align="left"><input type="text" name="barcode" style="width:85%" value="<%=dto.getBarcode()%>"></td>
            <td width="7%" align="right">�豸���ƣ�</td>
            <td width="15%" align="left"><input type="text" name="itemName" style="width:85%" value="<%=dto.getItemName()%>"></td>
            <td width="5%" align="right">����ͺţ�</td>
            <td width="15%" align="left"><input type="text" name="itemSpec" style="width:85%" value="<%=dto.getItemSpec()%>"></td>
        </tr>
        <tr>
            <td width="7%" align="right">���β��ţ�</td>
            <td width="15%" align="left"><input type="text" name="responsibilityDept" style="width:85%"
                                                value="<%=dto.getResponsibilityDept()%>">
            </td>
            <td width="5%" align="right">�����ˣ�</td>
            <td width="15%" align="left"><input type="text" name="username" style="width:85%"
                                                value="<%=dto.getUsername()%>">
            </td>
            <td width="5%" align="right">ʹ�ò��ţ�</td>
            <td width="15%" align="left"><input type="text" name="maintainDeptName" style="width:85%"
                                                value="<%=dto.getMaintainDeptName()%>">
           <td width="5%" align="right">���ڵص㣺</td>
            <td width="15%" align="left"><input type="text" name="addressloc" style="width:85%"
                                                value="<%=dto.getAddressloc()%>">
        </tr>
        <tr>
            <td width="7%" align="right">�������ڣ�</td>
            <td width="15%" align="left"><input type="text" name="fromDate" style="width:85%"   class="readonlyInput"
                                                value="<%=dto.getFromDate()%>"   onclick="gfPop.fStartPop(fromDate, toDate)"><img src="/images/calendar.gif" alt="���ѡ��ʼ����" onclick="gfPop.fStartPop(fromDate, toDate)">
            </td>
            <td width="5%" align="right">����</td>
            <td width="15%" align="left"><input type="text" name="toDate" style="width:80%"   class= "readonlyInput"
                                                value="<%=dto.getToDate()%>"  onclick="gfPop.fEndPop(fromDate, toDate)">
                                         <img src="/images/calendar.gif" alt="���ѡ���ֹ����" onclick="gfPop.fEndPop(fromDate, toDate)">
            <td width="5%" align="right"></td>
            <td width="15%"></td>
            <td width="5%" align="left"><img align="bottom" src="/images/eam_images/search.jpg" alt="��ѯ������Ϣ" onClick="do_search(); return false;"></td>
            <td width=15% align="bottom"><img src="/images/eam_images/export.jpg" alt="��������" onclick="do_export();"></td>
        </tr>
    </table>
    <input type="hidden" name="act" value="<%=action%>">
    <!--<script type="text/javascript">-->
        <!--var columnArr = new Array("����", "����", "�ͺ�", "��������", "��������", "������");-->
        <!--var widthArr = new Array("16%", "16%", "16%", "16%", "16%", "20%");-->
        <!--printTableHead(columnArr, widthArr);-->
    <!--</script>-->
     <div style="position:absolute;left:1px;width:834px;overflow:hidden" id="headDiv">
    <!--<table width="100%" class="headerTable" border="1">-->
    <table  border="1" width="2500" class="headerTable" cellpadding="0" cellspacing="0">
        <tr height="22">
            <!--<td width="1%" align="center">���</td>-->
            <td width="5%" align="center">�ʲ�����</td>
            <td width="5%" align="center">�ʲ�����</td>
            <td width="5%" align="center">����ͺ�</td>
            <td width="5%" align="center">�ʲ����</td>
            <td width="7%" align="center">�Է�ǩԼ��λ</td>
            <td width="7%" align="center">�ʲ��ص�</td>
            <td width="7%" align="center">ʹ�ò���</td>
            <td width="3%" align="center">���ڣ��꣩</td>
            <td width="3%" align="center">�����Ԫ��</td>
            <td width="3%" align="center">�����</td>
            <td width="5%" align="center">��ʼ����</td>
            <td width="5%" align="center">��ֹ����</td>
            <td width="3%" align="center">������</td>
            <td width="7%" align="center">���β���</td>
            <td width="5%" align="center">��ע</td>
        </tr>
    </table>
</div>


     <div style="position:absolute;left:1px;top:124px;width:850px;height:360px;overflow:scroll"
     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="2500" border="1" bordercolor="#666666" id="dataTab">
            <%
                RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
                if (rows != null && !rows.isEmpty()) {
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr class="dataTR"  onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <%--<td width="5%" align="center"><%=row.getValue("ROWNUM")%></td>--%>
                <td width="5%" align= "center"><%=row.getValue("BARCODE")%></td>
                <td width="5%"><%=row.getValue("ITEM_NAME")%></td>
                <td width="5%"><%=row.getValue("ITEM_SPEC")%></td>
                <td width="5%"><%=row.getValue("FINANCE_PROP")%></td>
                <td width="7%"><%=row.getValue("RENT_PERSON")%></td>
                <td width="7%"><%=row.getValue("OBJECT_NAME")%></td>
                <td width="7%"><%=row.getValue("MAINTAIN_DEPT")%></td>
                <td width="3%" align="center"><%=row.getValue("TENANCY")%></td>
                <td width="3%" align="center"><%=row.getValue("YEAR_RENTAL")%> </td>
                <td width="3%" align="center"><%=row.getValue("MONTH_REANTAL")%></td>
                <td width="5%" align="center"><%=row.getValue("RENT_DATE")%></td>
                <td width="5%" align="center"><%=row.getValue("END_DATE")%></td>
                <td width="3%" align="center"><%=row.getValue("USER_NAME")%></td>
                <td width="7%" align="left"><%=row.getValue("GROUP_NAME")%></td>
                <td width="5%" align="left"><%=row.getValue("REMARK")%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<div style="position:absolute;top:94%;left:0;right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>

<jsp:include page="/message/MessageProcess"/>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<iframe name="downFrm" src="" style="display:none"></iframe>

<script type="text/javascript">

    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.system.rent.servlet.RentPriviQueryServlet";
        mainFrm.submit();
    }

    function show_detail(rentId) {
        mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.system.rent.servlet.RentPriviQueryServlet?rentId=" + rentId;
        mainFrm.submit();
    }

    function do_add() {
        mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.system.rent.servlet.RentPriviQueryServlet";
        mainFrm.submit();
    }

      function do_export() {
        mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainFrm.submit();
    }
</script>