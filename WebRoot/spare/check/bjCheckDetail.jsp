<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.spare.check.dto.AmsItemCheckHeaderDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%--
  Created by IntelliJ IDEA.
  User: yuyao
  Date: 2007-11-27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>�����̵���ϸ��Ϣ</title>
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
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
</head>

<body topMargin=0 leftMargin=0>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    AmsItemCheckHeaderDTO dto = (AmsItemCheckHeaderDTO) request.getAttribute("AMSBJSALLOTHDTO");
    String action = parser.getParameter("act");
%>
<form action="/servlet/com.sino.ams.spare.check.servlet.AmsBjCheckServlet" name="mainForm" method="post">
<table width="100%" border="1" bordercolor="#9FD6FF" bgcolor="F2F9FF" id="table1" style="border-collapse: collapse">
    <tr>
        <td>
            <table width="100%" id="table2" border="0" cellspacing="1" bgcolor="#F2F9FF">
                <tr height="22">
                    <td width="10%" align="right">�̵㵥�ݣ�</td>
                    <td width="15%"><input type="text" name="transNo" class="readonlyInput" readonly
                                           style="width:100%"
                                           value="<%=dto.getTransNo()%>"></td>
                    <td width="10%" align="right">�����ˣ�</td>
                    <td width="10%"><input type="text" name="createdUser" class="readonlyInput" readonly
                                           value="<%=dto.getCreatedUser()%>"></td>
                    <td width="10%" align="right">����ʱ�䣺</td>
                    <td width="8%"><input type="text" name="creationDate" class="readonlyInput" readonly
                                          value="<%=dto.getCreationDate()%>"></td>
                    <td width="10%" align="right">����״̬��</td>
                    <td width="10%"><input type="text" name="orderStatusName" class="readonlyInput" readonly
                                           value="<%=dto.getOrderStatusName()%>"></td>
                    <td width="10%" align="right">��棺</td>
                    <td width="10%"><input type="text" name="checkLocationName" class="blueborderYellow" readonly
                                           value="<%=dto.getCheckLocationName()%>"><a href="#"
                            class="linka" style="cursor:'hand'" onclick="do_selectName();">[��]</a></td>
                </tr>
                <tr height="22">

                    <td align="right"> �Ƿ�ä�̣�</td>
                    <td><input type="radio" name="checkType" id="check1"
                               value="1" <%=dto.getCheckType().equals("1")?"checked":""%>>��
                        <input type="radio" name="checkType" id="check2"
                               value="0" <%=dto.getCheckType().equals("0")?"checked":""%>>��
                    </td>
                    <td align="right" width="5%">ִ���ˣ�</td>
                    <td width="10%" align="left"><input type="text" name="implementByName" style="width:80%" class="blueborderYellow"
                                                        readonly value="<%=dto.getImplementByName()%>"><a  href="#"
                            class="linka" style="cursor:'hand'" onclick="do_selectuser();">[��]</a></td>
                    <td align="right" width="5%">ִ��ʱ�䣺</td>
                    <td width="15%"><input type="text" name="startDate" value="<%=dto.getStartDate()%>"
                                           style="width:65%"
                                           title="���ѡ������" readonly class="blueborderYellow"
                                           onclick="gfPop.fPopCalendar(startDate)">
                        <img src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fPopCalendar(startDate)"></td>
                    <td align="right">ִ�����ڣ�</td>
                    <td><input type="text" name="implementDays"  class="blueborderYellow" value="<%=dto.getImplementDays()%>" style="width:65%">��</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<fieldset style="width:1015;height:500;">
    <legend>
        <img src="/images/button/ok.gif" alt="ȷ��" onClick="do_ok()">
        <img src="/images/button/toExcel.gif" alt="����" onclick="do_export()">
        <img src="/images/button/close.gif" alt="�ر�" onclick="window.close()">
    </legend>
    <table width="98%" border="1" class="headerTable" cellpadding="0" cellspacing="0">
        <tr height="20">
            <td width="15%" align="center">��������</td>
            <td width="20%" align="center">��������</td>
            <td width="25%" align="center">����ͺ�</td>
            <td width="20%" align="center">ϵͳ״̬</td>
            <td width="20%" align="center">�̵�״̬</td>
        </tr>
    </table>
    <div style="overflow-y:scroll;left:1px;width:100%;height:70%">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                RowSet rows = (RowSet) request.getAttribute("ITEM");
                if (rows != null && rows.getSize() > 0) {
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'">
                <td width="15%" align="center"><%=row.getValue("BARCODE")%>
                </td>
                <td width="20%" align="left"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td width="25%" align="center"><%=String.valueOf(row.getValue("ITEM_SPEC"))%>
                </td>
                <td width="20%" align="center"><%=String.valueOf(row.getValue("SYS_STATUS"))%>
                </td>
                <td width="20%" align="center"><%=String.valueOf(row.getValue("CHECK_STATUS"))%>
                </td>

            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>


</fieldset>
<input type="hidden" name="type" value="CHECK">
<input type="hidden" name="act" value="<%=action%>">
<input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
<input type="hidden" name="headerId" value="<%=dto.getHeaderId()%>">
<input type="hidden" name="orderStatus" value="<%=dto.getOrderStatus()%>">
<input type="hidden" name="implementBy" value="<%=dto.getImplementBy()%>">
<input type="hidden" name="checkLocation" value="<%=dto.getCheckLocation()%>">
<input type="hidden" name="checkType" value="<%=dto.getCheckType()%>">

</form>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script type="text/javascript">
    function do_selectName() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_KUCUN%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        //        var userdata = "type=" + mainForm.type.value;
        var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                mainForm.checkLocation.value = users[0].addressId;
                mainForm.checkLocationName.value = users[0].workorderObjectName;
            }
        }
    }
    function do_Delete() {
        var tab = document.getElementById("mtlTable");
        deleteTableRow(tab, 'subCheck');
    }
    function getvalues() {
        var tab = document.getElementById("mtlTable");
        if (tab.rows.length == 0 || (tab.rows[0].style.display == 'none' && tab.rows.length == 1)) {
            alert("û��ѡ�������ݣ���ѡ�������ݣ�");
            return false;
        }
        return true;
    }
    function do_selectuser() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_USER%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                mainForm.implementBy.value = users[0].executeUser;
                mainForm.implementByName.value = users[0].executeUserName;
            }
        }
    }
    function do_export() {
        mainForm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainForm.submit();
    }
    function do_ok() {
        if (mainForm.checkLocationName.value == "") {
            alert("��ѡ���̵�Ŀ��!");
        } else {
            mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";

            mainForm.submit();
        }
    }
</script>