<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.newasset.dto.AssetsTagNumberQueryDTO" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%--
  User: srf
  Date: 2008-4-1
  Time: 14:55:40
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>EAM�豸�嵥��ѯ</title>
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
    <script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>
<body>
<jsp:include page="/message/MessageProcess"/>
<body leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_search()')"onload="do_SetPageWidth()">
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    AssetsTagNumberQueryDTO dto = (AssetsTagNumberQueryDTO) request.getAttribute("TAG_NUMBER");
//    out.print(dto);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    String assetsType = reqParser.getParameter("assetsType");
%>
<form action="/servlet/com.sino.ams.newasset.servlet.EquipmentInfoQueryServlet" name="mainForm" method="post">

    <script type="text/javascript">
        printTitleBar("EAM�豸�嵥��ѯ")
//         function printTableHeadInfo(columnArr,widthArr){
//          document.write("<div id=\"headDiv\" style=\"overflow:hidden;left:1px;width:985px\" class=\"crystalScroll\" align=\"left\">");
//    document.write("<table id=\"$$$headerTable$$$\" border=\"1\" width=\"320%\" class=\""+constTHeadClass+"\" cellpadding=\"0\" cellspacing=\"0\">");
//    document.write("<tr height=\"22\" onClick='executeClick(this)'>");
//    if(columnArr[0] == "checkbox"){
//         document.write("<td width=\""+widthArr[0]+"\" align=\"center\"><input type=\"checkBox\" name=\"titleCheck\" onclick=\"checkAll('titleCheck','subCheck');\" class=\"headCheckbox\"></td>");
//    } else if(columnArr[0] == "radio"){
//         document.write("<td width=\""+widthArr[0]+"\" align=\"center\"></td>");
//    } else{
//	     document.write("<td width=\""+widthArr[0]+"\" align=\"center\">"+columnArr[0]+"</td>")
//    }
//    for(var i=1;i<columnArr.length;i++){
//        document.write("<td width=\""+widthArr[i]+"\" align=\"center\">"+columnArr[i]+"</td>")
//    }
//    document.write("</tr></table></div>")
//}
    </script>
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" style="background-color:#efefef">
        <tr>
            <td width=10% align="right"> ��˾���ƣ�</td>
            <td width="22%"><select name="organizationId" id="organizationId"
                                    style="width:100%"><%=request.getAttribute("OU")%>
            </select></td>
            <td width=10% align="right"> �豸�ص㣺</td>
            <td width="22%"><input name="workorderObjectName"  style="width:100%" value="<%=dto.getWorkorderObjectName()%>"></td>
            <td width=10% align="right"> �豸���룺</td>
            <td width="22%"><input name="barcode"  style="width:100%" value="<%=dto.getBarcode()%>"></td>


        </tr>
        <tr>
            <td width="10%" align="right">���β��ţ�</td>
            <td><input type="text"  style="width:100%" name="deptName" value="<%=dto.getDeptName()%>"></td>
            <td width="10%" align="right">�����ˣ�</td>
            <td><input type="text" name="userName"  style="width:100%" value="<%=dto.getUserName()%>"></td>
            <td width=10% align="right"> �豸���ƣ�</td>
            <td width="22%"><input name="itemName"  style="width:100%" value="<%=dto.getItemName()%>"></td>


        </tr>
        <tr>
            <td width=10% align="right"> �豸�ͺţ�</td>
            <td width="22%"><input name="itemSpec"  style="width:100%" value="<%=dto.getItemSpec()%>"></td>
            <td width=10% align="right"> �������ԣ�</td>
            <td><select name="financeProp" style="width:100%"><%=request.getAttribute("PROP")%></select></td>
           <td width=10% align="right"> ʹ���ˣ�</td>
            <td width="22%"><input name="maintainUser"  style="width:100%" value="<%=dto.getMaintainUser()%>"></td>

        </tr>
        <tr>
          <td align="right">�Ƿ�����ࣺ</td>
          <td><select name="assetsType" id="assetsType"
                                    style="width:100%">
              <option >��ѡ��</option>
              <option value="Y"<%=dto.getAssetsType().equals("Y") ? "selected" : ""%>>��</option>
              <option value="N"<%=dto.getAssetsType().equals("N") ? "selected" : ""%>>��</option>
            </select></td>
         <td width=10% align="center"><img src="/images/button/query.gif" alt="��ѯ"
                                              onClick="do_search(); return false;"></td>
              <td width=10% align="center"><img src="/images/button/toExcel.gif" alt="����EXCEL" onclick="do_export()"></td>
        </tr>
    </table>
    <%--<input type="hidden" name="transId" value="<%=dto.getTransId()%>">--%>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="transType" value="">

    <script type="text/javascript">        
        var columnArr = new Array("�豸����", "�豸����", "�豸�ͺ�", "�豸�ص�", "������", "ʹ����", "���β���", "��������", "ͬ��", "����","MIS��ǩ", "MIS����", "MIS�ͺ�", "MIS������", "MIS�ص�", "MIS��������", "ԭֵ");
        var widthArr = new Array("6%", "6%", "6%", "6%", "6%", "6%", "10%", "4%", "4%", "4%", "6%", "6%", "6%", "6%", "6%", "3%", "3%");
//                printTableHeadInfo(columnArr, widthArr);
        var constTHeadClass = "headerTable";
        document.write("<div style=\"overflow:hidden;top:113px;left:1px;width:800px\"  id=\"headDiv\">");
        document.write("<table id=\"$$$headerTable$$$\" border=\"1\" width=\"300%\" class=\"" + constTHeadClass + "\" cellpadding=\"0\" cellspacing=\"0\">");
        document.write("<tr height=\"22\" onClick='executeClick(this)'>");
        if (columnArr[0] == "checkbox") {
            document.write("<td width=\"" + widthArr[0] + "\" align=\"center\"><input type=\"checkBox\" name=\"titleCheck\" onclick=\"checkAll('titleCheck','subCheck');\" class=\"headCheckbox\"></td>");
        } else if (columnArr[0] == "radio") {
            document.write("<td width=\"" + widthArr[0] + "\" align=\"center\"></td>");
        } else {
            document.write("<td width=\"" + widthArr[0] + "\" align=\"center\">" + columnArr[0] + "</td>")
        }
        for (var i = 1; i < columnArr.length; i++) {
            document.write("<td width=\"" + widthArr[i] + "\" align=\"center\">" + columnArr[i] + "</td>")
        }
        document.write("</tr></table></div>")
    </script>


    <div style="overflow:scroll;width:878px;height:360px;left:1px;top:148px "  align="left"  id="dataDiv"
     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table  width="300%" border="1" bordercolor="#666666" cellpadding="1" cellspacing="0" >
            <%
                if (rows != null && rows.getSize() > 0) {
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22"  >
                <td width="6%" align="center"><%=row.getValue("BARCODE")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("WORKORDER_OBJECT_NAME")%>
                </td>
                <td width="6%" align="left"><%=row.getValue("USER_NAME")%>
                </td><td width="6%" align="left"><%=row.getValue("MAINTAIN_USER")%>
                </td>
                <td width="10%" align="left"><%=row.getValue("DEPT_NAME")%>
                </td>
                <td width="4%" align="left"><%=row.getValue("FINANCE_PROP_NAME")%>
                </td>
                 <td width="4%" align="left"><%=row.getValue("SYN_STATUS")%>
                </td> <td width="4%" align="left"><%=row.getValue("TRANS_NAME")%>
                </td><td width="6%" align="left"><%=row.getValue("TAG_NUMBER")%>
                </td><td width="6%" align="left"><%=row.getValue("ASSETS_DESCRIPTION")%>
                </td><td width="6%" align="left"><%=row.getValue("MODEL_NUMBER")%>
                </td><td width="6%" align="left"><%=row.getValue("ASSIGNED_TO_NAME")%>
                </td><td width="6%" align="left"><%=row.getValue("ASSETS_LOCATION")%>
                </td><td width="3%" align="left"><%=row.getValue("DATE_PLACED_IN_SERVICE")%>
                </td><td width="3%" align="left"><%=row.getValue("COST")%>
                </td>

            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<div ><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
<%=WebConstant.WAIT_TIP_MSG%>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">
    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainForm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainForm.submit();
    }
    function do_export() {
        mainForm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainForm.submit();
    }
    function do_drop() {
        var transType = document.getElementById("transType")   ;
        var transStatus = document.getElementById("transStatus");
        dropSpecialOption(transType, 'BJBFS');
        //        dropSpecialOption(transStatus, 'APPROVED');
    }

</script>