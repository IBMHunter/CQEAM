<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.instrument.dto.AmsInstrumentHDTO" %>
<%@ page import="com.sino.ams.instrument.dto.AmsInstrumentLDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%--
  Created by IntelliJ IDEA.
  User: yuyao
  Date: 2007-10-28
  Time: 23:25:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>���������Ǳ��̵㵥</title>
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

</head>

<style type="text/css" rel="stylesheet">
    .noneinput {
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
    }
</style>

<body topMargin=0 leftMargin=0>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    AmsInstrumentHDTO dto = (AmsInstrumentHDTO) request.getAttribute(WebAttrConstant.AMS_INSTRUMENTH_DTO);
    SfUserDTO user = (SfUserDTO) session.getAttribute(WebConstant.USER_INFO);
    String action = parser.getParameter("act");
    DTOSet set = (DTOSet) request.getAttribute(WebAttrConstant.AMS_INSTRUMENTL_DTO);
%>
<form action="/servlet/com.sino.ams.instrument.servlet.AmsInstrumentCheckServlet" name="mainForm" method="post">
<script type="text/javascript">
    printTitleBar("���������Ǳ��̵㵥")
</script>
<table width="100%" border="1" bordercolor="#9FD6FF" bgcolor="F2F9FF" id="table1" style="border-collapse: collapse">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1" bgcolor="#F2F9FF">
                <tr height="22">
                    <td width="10%" align="right">�̵㵥�ţ�</td>
                    <td width="18%"><input type="text" name="transNo" class="readonlyInput" readonly
                                           style="width:100%"
                                           value="<%=dto.getTransNo()%>"></td>
                    <td width="10%" align="right">�̵��ˣ�</td>
                    <td width="21%"><input type="text" name="checkName" class="readonlyInput" readonly
                                           value="<%=dto.getCheckName()%>"></td>
                    <td width="10%" align="right">�̵����ڣ�</td>
                    <td width="10%"><input type="text" name="checkDate" class="readonlyInput" readonly
                                           value="<%=dto.getCheckDate()%>"></td>
                    <td width="10%" align="right">����״̬��</td>
                    <td width="10%"><input type="text" name="transStatusName" class="readonlyInput" readonly
                                           value="<%=dto.getTransStatusName()%>"></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<fieldset style="width:100%;height:500px;border:2px groove">
    <legend>
        <img src="/images/eam_images/add_data.jpg" alt="�������" onclick="do_add();">
        <img src="/images/eam_images/delete_line.jpg" alt="ɾ����" onClick="do_Delete();">
        <img src="/images/button/ruturn.gif" alt="�̵�" onclick="do_check()">
        <img src="/images/eam_images/ok.jpg" alt="ȷ��" onClick="do_ok()">
        <img src="/images/eam_images/close.jpg" alt="�ر�" onclick="window.close()">
    </legend>
 <div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
        <table width="100%" border="1" class="headerTable" cellpadding="0" cellspacing="0"  class="headerTable">
            <tr height="20">
                <td align=center width=5%>
                    <input type="checkbox" name="mainCheck" value=""  class="headCheckbox"
                           onclick="checkAll('mainCheck','subCheck')"></td>
                <td width="10%" align="center">�����Ǳ�����</td>
                <td width="10%" align="center">�����Ǳ�����</td>
                <td width="12%" align="center">����ͺ�</td>
                <td width="8%" align="center">������</td>
                <td width="8%" align="center">��ǰʹ����</td>
                <td width="20%" align="center">��Ӧ��</td>
                <td width="25%" align="center">�����Ǳ���;</td>
            </tr>
        </table>
    </div>
    <div style="width:100%;overflow-y:scroll;height:500px">
        <table width="100%" border="1" bordercolor="#9FD6FF" id="mtlTable" cellpadding="0" cellspacing="0">
            <%
                if (set == null || set.isEmpty()) {
            %>
            <tr height=20px style="display:none" onclick="">
                <td align=center width=5%><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
                <td align=center width="10%"><input class="noneinput" readonly type="text" name="barcode" id="barcode0" style="width:100%">
                </td>
                <td align=center width="10%" name="itemName" id="itemName0"></td>
                <td align=center width="12%" name="itemSpec" id="itemSpec0"></td>
                <td align=center width="8%" name="cname" id="cname0"></td>
                <td align=center width="8%" onclick="do_selectName(this);"><input type="text" class="noneinput" readonly   style="width:100%"
                                                                                  name="dname" id="dname0"><input
                        type="hidden" name="currKeepUser" id="currKeepUser0"></td>
                <td align=center width="20%" name="vendorName" id="vendorName0"></td>
                <td align=center width="25%" name="instruUsage" id="instruUsage0"></td>
                <td style="display:none">
                </td>
            </tr>
            <%
            } else {
                for (int i = 0; i < set.getSize(); i++) {
                    AmsInstrumentLDTO dto1 = (AmsInstrumentLDTO) set.getDTO(i);


            %>
            <tr height=20px onclick="">
                <td align=center width=5%><input type="checkbox" name="subCheck" id="subCheck<%=i%>" value="<%=dto1.getBarcode()%>"></td>
                <td align=center width="10%"><input type="text" class="noneinput" name="barcode" id="barcode<%=i%>" readonly style="width:100%"  value="<%=dto1.getBarcode()%>"></td>
                <td align=center width="10%" name="itemName" id="itemName<%=i%>"><%=dto1.getItemName()%>
                </td>
                <td align=center width="12%" name="itemSpec" id="itemSpec<%=i%>"><%=dto1.getItemSpec()%>
                </td>
                <td align=center width="8%" name="cname" id="cname<%=i%>"><%=dto1.getCname()%>
                </td>
                <td align=center width="8%" onclick="do_selectName(this);"><input type="text" class="noneinput" readonly
                                                                                  name="dname" id="dname<%=i%>"     style="width:100%"
                                                                                  value="<%=dto1.getDname()%>"><input
                        type="hidden" name="currKeepUser" id="currKeepUser<%=i%>" value="<%=dto1.getCurrKeepUser()%>">
                </td>
                <td align=center width="20%" name="vendorName" id="vendorName<%=i%>"><%=dto1.getVendorName()%>
                </td>
                <td align=center width="25%" name="instruUsage" id="instruUsage<%=i%>"><%=dto1.getInstruUsage()%>
                </td>
                <td style="display:none">
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</fieldset>
<input type="hidden" name="type" value="INSTRUMENT">
<input type="hidden" name="transType" value="<%=dto.getTransType()%>">
<input type="hidden" name="act" value="<%=action%>">
<input type="hidden" name="checkUser" value="<%=dto.getCheckUser()%>">
<input type="hidden" name="transId" value="<%=dto.getTransId()%>">
<input type="hidden" name="transStatus" value="<%=dto.getTransStatus()%>">
</form>
</body>
</html>
<script type="text/javascript">
    function do_add() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_INSTR%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        /*var userPara = "itemCategory=" + mainForm.type.value;*/
        //LOOKUP������ �����DTO��һ��
        var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        var tab = document.getElementById("mtlTable")
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                user = users[i];
                if (!isItemExist(user)) {
                    appendDTORow(tab, user);
                    //����������Ϣʱ���õ��ķ���
                }
            }
        }
    }
    function do_selectName(obj) {
        var upName = "<%=LookUpConstant.LOOK_UP_USER%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        var userPara = "organizationId=" + "<%=user.getOrganizationId()%>";
        var users = getLookUpValues(upName, dialogWidth, dialogHeight, userPara);
        if (users) {
            //            obj.cells[5].childNodes[0].value = users[0].executeUserName;//����������д�¼����Ը����кͽڵ�����ȡ��ǰ����
            //            obj.cells[8].childNodes[0].value = users[0].executeUser;
            obj.childNodes[0].value = users[0].executeUserName;
            //������TD��д�¼� �Ϳ��Ի�õ�ǰ����Ľڵ���
            obj.childNodes[1].value = users[0].executeUser;
            //            obj.childNodes[0].value = users[0].executeUser;
        }
    }
    function do_Delete() {
        var tab = document.getElementById("mtlTable");
        deleteTableRow(tab, 'subCheck');
    }
    function do_ok() {
        if (!getvalues()) {
            return;
        }
        mainForm.transStatus.value = "<%=DictConstant.CREATE%>";
        mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
        mainForm.submit();

    }
    function do_check() {
        var checkCount = getCheckedBoxCount("subCheck");
        if (checkCount < 1) {
            alert("��ѡ��һ����¼��Ȼ���̵㣡");
        } else {
            mainForm.transStatus.value = "<%=DictConstant.COMPLETED%>";
            mainForm.act.value = "check";
            mainForm.submit();
        }
    }
    function do_save() {
        if (!getvalues()) {
            return;
        }
        mainForm.transStatus.value = "<%=DictConstant.SAVE_TEMP%>"
        mainForm.act.value = "<%=WebActionConstant.SAVE_ACTION%>";
        mainForm.submit();
    }
    function getvalues() {
        var tab = document.getElementById("mtlTable");
        if (tab.rows.length == 0 || (tab.rows[0].style.display == 'none' && tab.rows.length == 1)) {
            alert("û��ѡ�������ݣ���ѡ�������ݣ�");
            return false;
        }
        return true;
    }
    function isItemExist(itemObj) {
        var retVal = false;
        var tabObj = document.getElementById("mtlTable");
        if (tabObj.rows) {
            var trObjs = tabObj.rows;
            var trCount = trObjs.length;
            var trObj = null;
            var itemValue = itemObj.barcode;
            var rowValue = null;
            for (var i = 0; i < trCount; i++) {
                trObj = trObjs[i];
                rowValue = trObj.cells[1].childNodes[0].value;
                if (itemValue == rowValue) {
                    retVal = true;
                }
            }
        }
        return retVal;
    }
    function do_repal() {

    }
</script>