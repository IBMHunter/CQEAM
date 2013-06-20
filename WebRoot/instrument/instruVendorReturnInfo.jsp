<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.instrument.dto.AmsInstrumentHDTO" %>
<%@ page import="com.sino.ams.instrument.dto.AmsInstrumentLDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-2-25
  Time: 11:50:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>�����Ǳ����޷�����</title>
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

<body topMargin=1 leftMargin=1>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    AmsInstrumentHDTO dto = (AmsInstrumentHDTO) request.getAttribute(WebAttrConstant.AMS_INSTRUMENTH_DTO);
    String action = parser.getParameter("act");
    DTOSet set = (DTOSet) request.getAttribute(WebAttrConstant.AMS_INSTRUMENTL_DTO);
%>
<form action="/servlet/com.sino.ams.instrument.servlet.AmsInstrVendorRetServlet" name="mainForm" method="post">
<script type="text/javascript">
    printTitleBar("�����Ǳ����޷�����")
</script>
<table width="100%" border="1" bordercolor="#9FD6FF" bgcolor="F2F9FF" id="table1" style="border-collapse: collapse">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1" bgcolor="#F2F9FF">
                <tr height="22">
                    <td width="10%" align="right">�������ţ�</td>
                    <td width="16%"><input type="text" name="transNo" class="readonlyInput" readonly style="width:100%"
                                           value="<%=dto.getTransNo()%>"></td>
                    <td width="10%" align="right">�������̣�</td>
                    <td width="25%"><input type="text" name="vendorName" class="noEmptyInput" readonly style="width:80%"
                                           value="<%=dto.getVendorName()%>"><a class="linka" href="#"
                                                                               onclick="do_SelectVendor()">[��]</a></td>
                    <td width="10%" align="right">�������ڣ�</td>
                    <td width="10%"><input type="text" name="returnDate" class="readonlyInput" readonly
                                           value="<%=dto.getReturnDate()%>"></td>
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
        <%
            if (StrUtil.isEmpty(dto.getTransId())) {
        %>
        <img src="/images/eam_images/add_data.jpg" alt="�������" onclick="do_add();">
        <img src="/images/eam_images/delete_line.jpg" alt="ɾ����" onClick="do_Delete();">
        <img src="/images/eam_images/ok.jpg" alt="���޷���" onclick="do_ok()">
        <%
            }
        %>
        <img src="/images/eam_images/close.jpg" alt="�ر�" onclick="window.close()">
    </legend>
    <div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
        <table width="100%" border="1" class="headerTable" cellpadding="0" cellspacing="0">
            <tr height="20">
                <td align=center width="3%">
                    <input type="checkbox" name="mainCheck" value="" class="headCheckbox"
                           onclick="checkAll('mainCheck','subCheck')"></td>
                <td width="10%" align="center">�����Ǳ�����</td>
                <td width="12%" align="center">�����Ǳ�����</td>
                <td width="12%" align="center">����ͺ�</td>
                <td width="8%" align="center">������</td>
                <!--<td width="20%" align="center">��Ӧ��</td>-->
                <td width="25%" align="center">�����Ǳ���;</td>
            </tr>
        </table>
    </div>
    <div style="width:100%;overflow-y:scroll;height:500px">
        <table width="100%" border="1" bordercolor="#9FD6FF" id="mtlTable" cellpadding="0" cellspacing="0">
            <%
                if (set == null || set.isEmpty()) {
            %>
            <tr height=20px style="display:none">
                <td align=center width="3%"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
                <td align=center width="10%"><input type="text" readonly name="barcode"
                                                    style="width:100%;border:none"
                                                    id="barcode0">
                </td>

                <!--<td align=center width="10%" name="barcode" id="barcode0"></td>-->
                <td align=center width="12%" name="itemName" id="itemName0"></td>
                <td align=center width="12%" name="itemSpec" id="itemSpec0"></td>
                <!--<td align=center width="8%" name="cname" id="cname0"></td>-->
                <td align=center width="8%" name="responsibilityName" id="responsibilityName0"></td>
                <!--<td align=center width="20%" name="vendorName" id="vendorName0"></td>-->
                <td align=center width="25%" name="instruUsage" id="instruUsage0"></td>
                <td style="display:none">
                </td>
            </tr>
            <%
            } else {
                for (int i = 0; i < set.getSize(); i++) {
                    AmsInstrumentLDTO dto1 = (AmsInstrumentLDTO) set.getDTO(i);
            %>
            <tr height=20px>
                <td align=center width="3%"><input type="checkbox" name="subCheck" id="subCheck<%=i%>" value=""></td>
                <td align=center width="10%"><input type="text" name="barcode" id="barcode<%=i%>"
                                                    readonly style="width:100%;border:none" value="<%=dto1.getBarcode()%>"></td>
                <%--<td align=center width="10%" name="barcode" id="barcode<%=i%>"><%=dto1.getBarcode()%></td>--%>
                <td align=center width="12%" name="itemName" id="itemName<%=i%>"><%=dto1.getItemName()%>
                </td>
                <td align=center width="12%" name="itemSpec" id="itemSpec<%=i%>"><%=dto1.getItemSpec()%>
                </td>
                <%--<td align=center width="8%" name="cname" id="cname<%=i%>"><%=dto1.getCname()%>--%>
                <!--</td>                     -->
                <td align=center width="8%" name="responsibilityName"
                    id="responsibilityName<%=i%>"><%=dto1.getResponsibilityName()%>
                </td>
                <%--<td align=center width="20%" name="vendorName" id="vendorName<%=i%>"><%=dto1.getVendorName()%>--%>
                <!--</td>-->
                <td align=center width="25%" name="instruUsage" id="instruUsage<%=i%>"><%=dto1.getInstruUsage()%>
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
<input type="hidden" name="userId">
<input type="hidden" name="username">
<input type="hidden" name="transType" value="<%=dto.getTransType()%>">
<input type="hidden" name="act" value="<%=action%>">
<input type="hidden" name="returnUser" value="<%=dto.getReturnUser()%>">
<input type="hidden" name="transId" value="<%=dto.getTransId()%>">
<input type="hidden" name="transStatus" value="<%=dto.getTransStatus()%>">
<!--�����Ǳ����� EO�� objectCategory=78-->
<input type="hidden" name="objectCategory" value="77">
<input type="hidden" name="vendorId">
</form>
</body>
</html>
<script type="text/javascript">
<%--function do_add1() {--%>
<%--var lookUpName = "<%=LookUpConstant.LOOK_UP_VENRETURN%>";--%>
<%--var dialogWidth = 49;--%>
<%--var dialogHeight = 30;--%>
<%--//        var userPara = "currKeepUser=" + mainForm.returnUser.value;--%>
<%--var userPara = "userId=" + mainForm.userId.value;--%>
<%--//LOOKUP������ �����DTO��һ��--%>
<%--var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);--%>
<%--var tab = document.getElementById("mtlTable")--%>
<%--if (users) {--%>
<%--var user = null;--%>
<%--for (var i = 0; i < users.length; i++) {--%>
<%--user = users[i];--%>
<%--if (!isItemExist(user)) {--%>
<%--appendDTORow(tab, user);--%>
<%--//����������Ϣʱ���õ��ķ���   }--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--}--%>
function isItemExist(itemObj) {      //��ֹ�����ظ���
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
function do_Delete() {
    var tab = document.getElementById("mtlTable");
    deleteTableRow(tab, 'subCheck');
}

function doDel() {

    document.getElementById('mainCheck').checked = true;
    checkAll('mainCheck', 'subCheck')
    var tab = document.getElementById("mtlTable");
    deleteTableRow1(tab, 'subCheck');
    document.getElementById('mainCheck').checked = false;
}

function deleteTableRow1(tab, checkboxName) {
    if (!tab || !checkboxName) {
        return;
    }
    var rowCount = tab.rows.length;
    if (rowCount == 0) {
        //	    alert("������Ҫɾ�����С�");
        return;
    }
    var boxArr = getCheckedBox(checkboxName);
    var chkCount = boxArr.length;
    if (chkCount < 1) {
        //        alert("����ѡ��Ҫɾ�����У�");
        return;
    }
    //	if(confirm("ȷ��Ҫɾ��ѡ�е����𣿼���������ȷ������ť������������ȡ������ť��")){
    var chkObj = null;
    for (var i = 0; i < chkCount; i++) {
        chkObj = boxArr[i];
        if (tab.rows.length > 1) {
            delTableRow(tab, chkObj);
        } else {
            clearContent(tab, chkObj);
            tab.rows[0].style.display = "none";
        }
    }
    //	}
}


function do_ok() {
    if (!getvalues()) {
        return;
    }
    if (mainForm.vendorName.value !== "") {
    <%--mainForm.transStatus.value = "<%=DictConstant.CREATE%>";--%>
        mainForm.transStatus.value = "<%=DictConstant.COMPLETED%>";
        mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
        mainForm.submit();
    } else {
        alert("��ѡ����!");
    }
}
function do_return() {
    if (!getvalues()) {
        return;
    }
    mainForm.transStatus.value = "<%=DictConstant.COMPLETED%>";
    //������
    mainForm.act.value = "return";
    mainForm.submit();
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
function do_repal() {

}

function do_selectUserName() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_USER1%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    //LOOKUP������ �����DTO��һ��
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    //       alert(users);
    if (users) {
        if (document.mainForm.userId.value !== users[0].userId) {
            doDel();
        }
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
            //            alert(user);
        }
        //        alert(users[0].userId) ;
    }
    document.mainForm.returnName.value = document.mainForm.username.value;
    //      if(){
    //      }
}

function dto2Frm(dto, formName) {
    var frmObj = eval("document." + formName);
    var children = frmObj.elements;
    var child = null;
    var childType = null;
    var fieldName = "";
    for (var i = 0; i < children.length; i++) {
        child = children[i];
        childType = child.type;
        if (childType == "text" || childType == "textarea" || childType == "hidden") {
            fieldName = child.name;
            for (prop in dto) {
                if (fieldName == String(prop)) {
                    child.value = dto[prop];
                }
            }
        }
    }
}

function do_add() {
    var vendorName = mainForm.vendorName.value;
    if(vendorName == ""){
        alert("����ѡ���̣�");
        return;
    }
    var lookUpName = "<%=LookUpConstant.LOOK_UP_VENRETURN%>";
    var dialogWidth = 61;
    var dialogHeight = 30;
    var userPara = "vendorName=" + vendorName;
    //LOOKUP������ �����DTO��һ��
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
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


function do_SelectVendor() {
    var lookUpSpec = "<%=LookUpConstant.LOOK_UP_VENDOR%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var specs = getLookUpValues(lookUpSpec, dialogWidth, dialogHeight);
    if (specs) {
        //             if (users) {
        if (document.mainForm.vendorName.value !== specs[0].vendorName) {
            doDel();
        }
        var spec = null;
        for (var i = 0; i < specs.length; i++) {
            spec = specs[i];
            //                document.mainForm.vendorName.value =spec.
            dto2Frm(spec, "mainForm");
        }
    }
}


function doDel() {
    document.getElementById('mainCheck').checked = true;
    checkAll('mainCheck', 'subCheck')
    var tab = document.getElementById("mtlTable");
    deleteTableRow1(tab, 'subCheck');
    document.getElementById('mainCheck').checked = false;
}

</script>