<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%--
  User: zhoujs
  Date: 2007-10-30
  Time: 10:22:03
  Function:Excel�ϴ�����
--%>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>���鵵����</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>

    <script type="text/javascript">
            var ArrAction0 = new Array(true, "�ύ", "action_save.gif", "�ϴ�����", "doSub");
            var ArrAction1 = new Array(true, "ճ��", "action_sign.gif", "ճ��", "aa");
            var ArrActions = new Array(ArrAction0, ArrAction1);
            var ArrSinoViews = new Array();
            var ArrSinoTitles = new Array();
    </script>
    <style type="text/css" rel="stylesheet">
    .finput {BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 1px ridge;font-size: 12px;border-bottom-color:blue;}
    .noneinput {BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;}
    .bkinput {border:none;width:100%}
</style>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0>
<form name="mainFrm" action="/servlet/com.sino.pda.XlsOrderSubmint">
    <script type="text/javascript">
                                printTitleBar("Excel�ϴ�����")
                                        printToolBar();
    //    var columnArr = new Array("������", "��ǩ��", "״̬", "����", "�������", "�ص���", "�����", "��Ԫ���", "����ǩ��", "��ע");
    //    var widthArr = new Array("12%", "10%", "8%", "8%", "10%", "10%", "10%", "10%", "12%", "10%");
    //    printTableHead(columnArr, widthArr);
</script>
     <script type="text/javascript">
             var columnArr = new Array("������", "��ǩ��", "״̬", "����", "�������", "�ص���", "�����", "��Ԫ���", "����ǩ��", "��ע");
    var widthArr = new Array("14%", "12%", "6%", "6%", "10%", "10%", "10%", "10%", "12%", "10%");
    printTableHead(columnArr, widthArr);
     </script>
    <!--<table class="headerTable">-->
               <!--<tr >-->
            <!--<td height="22" width="14%" align="center">������</td>-->
            <!--<td height="22" width="12%" align="center">��ǩ��</td>-->
            <!--<td height="22" width="6%" align="center">״̬</td>-->
            <!--<td height="22" width="6%" align="center">����</td>-->
            <!--<td height="22" width="10%" align="center">�������</td>-->
            <!--<td height="22" width="10%" align="center">�ص���</td>-->
            <!--<td height="22" width="10%" align="center">�����</td>-->
            <!--<td height="22" width="10%" align="center">��Ԫ���</td>-->
            <!--<td height="22" width="12%" align="center">����ǩ��</td>-->
            <!--<td height="22" width="10%" align="center">��ע</td>-->
        <!--</tr>-->
    <!--</table>-->
<div style="overflow-y:scroll;height:362;width:100%;left:1px;margin-left:0px" align="left">
    <table width="100%" border="1" bordercolor="#666666" id="dtl">
       
    </table>
</div>
</form>

</body>
<script type="text/javascript">
     function aa() {
        var fieldNames = new Array("workorderNo", "barcode", "itemStatus", "itemQty", "itemCode", "addressId", "boxNo", "netUnit", "parentCode", "remark");
        var fieldWidths=new Array("14%","12%","6%","6%","10%","10%","10%","10%","12%","10%");
        var text = window.clipboardData.getData("text");
        if (text == null || text == "") {
            alert("������EXCEL�����︴�ƶ��������ݣ�Ȼ����ճ����");
            return;
        }
        var rows = text.split("\n");
        var tab = document.all("dtl");
        var count = tab.rows.length;
        for (var i = 0; i < rows.length - 1; i++) {
            var row = rows[i];
            var cl = row.split(String.fromCharCode(9));
            var newRow = tab.insertRow();
            for (var k = 0; k < cl.length; k++) {
                var newCell = newRow.insertCell();
                newCell.width=widthArr[k];
                newCell.innerHTML = "<input class='bkinput' type='text' name='" + fieldNames[k] + "' id='" + fieldNames[k] + count + "' value='" + cl[k] + "'>"
                count++;
            }
        }
    }
     function doSub() {
         mainFrm.submit();
     }
</script>
</html>
