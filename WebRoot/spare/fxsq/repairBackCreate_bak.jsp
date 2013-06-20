<%--
  Created by IntelliJ IDEA.
  User: srf
  Date: 2008-3-18
  Function:�������루NEW��
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%

%>
<html>
<head><title>�����������루NEW��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/flow/flow.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
</head>
<body leftmargin="1" topmargin="1">

<%
    AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) request.getAttribute("AIT_HEADER");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
%>
<form name="mainForm" action="/servlet/com.sino.ams.spare.servlet.RepairBackServlet" method="post">
<jsp:include page="/flow/include.jsp" flush="true"/>

<div id="fxsqHideDiv" style="position:absolute;bottom:0px;top:0px;left:0px;right:0px;z-index:10;visibility:hidden;width:100%;height:100%">
    <table width=100% height=100%  style="background-color:#777;filter:progid:DXImageTransform.Microsoft.Alpha(opacity=50,finishOpacity=50,style=2)">
        <tr><td></td></tr>
    </table>
</div>

<div id="fxsqNextTaskDiv" style="position:absolute; bottom:220px; left:20px; z-index:100; visibility:hidden;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr align=center>
            <td width="20%" style="border:none"></td>
            <td align=center style="border:none">
                <table width="250px" border="1" cellspacing="1" cellpadding="0" bgcolor="#eeeeee"
                       style="border-bottom-color:#ff9900;border-left-color:#ff9900;border-right-color:#ff9900;border-top-color:#ff9900">
                    <tr height=20px><td bgcolor="#5BBBEF" style="border:none"><strong>��ѡ����һ����</strong></td></tr>
                    <tr height=20px>
                        <td style="border:none">
                            <select id="fxsqNextTasksSelect" style="width:100%;">
                                <option value="">---��ѡ��--</option>
                                <option value="1">�ύPDAɨ��</option>
                                <option value="2">�ύ���������쵼����</option>
                            </select>
                        </td>
                    </tr>
                    <tr height=20px align=right>
                        <td style="border:none">
                            <a href="#" onclick="subPanduan();"
                               style="cursor:pointer;text-decoration:underline;color:blue">[ȷ��]</a>
                            <a href="#" onclick="hideFxsqTasksDiv();cancelFxsqDisable();"
                               style="cursor:pointer;text-decoration:underline;color:blue">[�ر�]</a>
                        </td></tr>
                </table>
            </td>
            <td width="50%"></td>
        </tr>
    </table>
</div>

<input type="hidden" name="act" value="">
<input type="hidden" name="transId" value="<%=amsItemTransH.getTransId()%>">
<input type="hidden" name="transType" value="<%=amsItemTransH.getTransType()%>">
<input type="hidden" name="createdBy" value="<%=amsItemTransH.getCreatedBy()%>">
<input type="hidden" name="toObjectNo" value="<%=amsItemTransH.getToObjectNo()%>">
<input type="hidden" name="transStatus" value="<%=amsItemTransH.getTransStatus()%>">
<input type="hidden" name="fromOrganizationId" value="<%=amsItemTransH.getFromOrganizationId()%>">
<input type="hidden" name="groupId" value="<%=user.getCurrGroupId()%>">
<input type="hidden" name="fromDept" value="<%=amsItemTransH.getFromDept()%>">
<table border="1"  bordercolor="#9FD6FF" class="detailHeader" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1">
                <tr height="22">
                    <td width="9%" align="right">���ݺţ�</td>
                    <td width="20%">
                        <input type="text" name="transNo" value="<%=amsItemTransH.getTransNo()%>" readonly style="width:80%" class="blueborderGray">
                    </td>
                    <td width="9%" align="right">���빫˾��</td>
                    <td width="25%">
                        <input type="text" name="fromOrganizationName" value="<%=amsItemTransH.getFromOrganizationName()%>" class="blueborderGray">
                    </td>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td>
                        <input type="text" name="createdUser" value="<%=amsItemTransH.getCreatedUser()%>" readonly   style="width:80%" class="blueborderGray">
                    </td>
                    <td align="right">�������ڣ�</td>
                    <td><input type="text" name="creationDate" readonly
                               value="<%=amsItemTransH.getCreationDate()%>"
                               class="blueborderGray">
                    </td>
                    <td align="right">����״̬��</td>
                    <td colspan="2"><input type="text" name="transStatusName" readonly
                                           value="<%=amsItemTransH.getTransStatusName()%>"
                                           class="blueborderGray"></td>
                </tr>
                <tr>
                    <td align="right">��ע��</td>
                    <td colspan="11"><textarea name="remark" rows="3" cols="" style="width:90%"
                                               class="blueBorder"><%=amsItemTransH.getRemark()%></textarea>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<fieldset>
<legend>
    <img src="/images/button/addData.gif" alt="�������" onclick="do_SelectItem();">
    <img src="/images/button/deleteLine.gif" alt="ɾ����"  onClick="deleteTableRow(document.getElementById('dataTable'),'subCheck');">
    <img src="/images/button/submit.gif" alt="�ύ" id="img4" onClick="do_selectFlow1();">
    <%
        if (amsItemTransH.getTransStatus().equals(DictConstant.REJECTED)) {
    %>
    <img src="/images/button/viewOpinion.gif" alt="�������" onClick="viewOpinion();">
    <%}%>
    <img src="/images/button/close.gif" alt="�ر�" onClick="window.close();">
</legend>
<script type="text/javascript">
    var columnArr = new Array("checkbox", "������", "�豸����", "����ͺ�", "�豸���", "�豸����", "���ϵص�", "��������", "��������");
    var widthArr = new Array("3%", "10%", "15%", "8%", "15%", "15%", "20%", "6%", "6%");
    printTableHead(columnArr, widthArr);
</script>
<div style="overflow-y:scroll;height:500px;width:100%;left:1px;margin-left:0"
     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table width="100%" border="1" bordercolor="#9FD6FF" id="dataTable" cellpadding="1" cellspacing="0" style="table-layout:fixed;word-break:break-all">
        <%
            RowSet rows = (RowSet) request.getAttribute("AIT_LINES");
            if (rows == null || rows.isEmpty()) {
                int k = 0;
        %>
        <tr id="mainTr0" style="display:none" onMouseMove="this.style.backgroundColor='#EFEFEF'"
            onMouseOut="this.style.backgroundColor='#FFFFFF'">

            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0"
                                                 style="height:20px;margin:0;padding:0">
            </td>
            <td width="10%" align="center"><input type="text" name="barcode" id="barcode0"
                                                  value="" class="blueborderGray" readonly
                                                  style="width:100%">
            </td>
            <td width="15%"><input  name="itemName" id="itemName0"></td>
            <td width="8%"><input name="itemSpec" id="itemSpec0"></td>
            <td width="15%"><input name="spareUsage" id="spareUsage0"></td>
            <td width="15%"><input name="vendorName" id="vendorName0"></td>
            <td width="20%"><input type="text" name="reasons" id="reasons0"
                                   value="" class="blueBorder"
                                   style="width:100%;text-align:left">
            </td>
            <td width="6%" align="center"><input type="text" name="onhandQty" id="onhandQty"
                                                 value="" class="noborderGray" readonly
                                                 style="width:100%;text-align:right">
            </td>
            <td width="6%" align="center"><input type="text" name="quantity" id="quantity0"
                                                 value="" class="blueborderYellow" onblur="validateData2(this);"
                                                 style="width:100%">
            </td>
            <td style="display:none">
                <input type="hidden" name="lineId" id="lineId0" value="">
            </td>
        </tr>
        <%
        } else {
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
        %>
        <tr id="mainTr<%=i%>" onMouseMove="style.backgroundColor='#EFEFEF'"
            onMouseOut="style.backgroundColor='#FFFFFF'">
            <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>"
                                                 style="height:20px;margin:0;padding:0">
            </td>
            <td width="10%" align="center"><input type="text" name="barcode"
                                                  id="barcode<%=i%>" readonly
                                                  value="<%=row.getValue("BARCODE")%>"
                                                  class="blueborderGray"
                                                  style="width:100%">
            </td>
            <td width="15%" name="itemName" id="itemName<%=i%>"><%=row.getValue("ITEM_NAME")%>
            </td>
            <td width="8%" name="itemSpec" id="itemSpec<%=i%>"><%=row.getValue("ITEM_SPEC")%>
            </td>
            <td width="15%" name="spareUsage" id="spareUsage<%=i%>"><%=row.getValue("SPARE_USAGE")%>
            </td>
            <td width="15%" name="vendorName" id="vendorName<%=i%>"><%=row.getValue("VENDOR_NAME")%>
            </td>
            <td width="20%" align="center"><input type="text" name="reasons" id="reasons<%=i%>"
                                                  value="<%=row.getValue("REASONS")%>" class="blueBorder"
                                                  style="width:100%;text-align:left"></td>
            <td width="6%" align="center"><input type="text" name="onhandQty" id="onhandQty<%=i%>"
                                                 value="<%=row.getValue("ONHAND_QTY")%>" class="blueborderGray"
                                                 style="width:100%;text-align:right">
            </td>
            <%-- <td width="6%" align="center"><input type="text" name="quantity" id="quantity<%=i%>"
                                                 value="<%=row.getValue("QUANTITY")%>" class="blueborderYellow" readonly
                                                 style="width:100%;text-align:right">
            </td>--%>

            <td width="6%" align="center"><input type="text" name="quantity" id="quantity<%=i%>"
                                                 value="<%=row.getValue("QUANTITY")%>" class="blueborderYellow"
                                                 onblur="validateData2(this);"
                                                 style="width:100%;text-align:right">
            </td>
            <td style="display:none">
                <%--<input type="hidden" name="barcode" id="barcode1<%=i%>" value="<%=row.getValue("BARCODE")%>" >--%>
                <input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=row.getValue("LINE_ID")%>">

            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
</fieldset>
</form>
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
</body>
<script type="text/javascript">

function do_SelectItem() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_FXSQ%>";
    var dialogWidth = 65;
    var dialogHeight = 30;
    var userPara = "organizationId=" +<%=user.getOrganizationId()%>;

    //LOOKUP������ �����DTO��һ��
    var assets = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);

<%--var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.LOOK_UP_FXSQ%>";
var popscript = "dialogWidth:51;dialogHeight:33;center:yes;status:no;scrollbars:no";
/*   window.open(url);*/
var assets = window.showModalDialog(url, null, popscript);--%>
    if (assets) {
        var data = null;
        var tab = document.getElementById("dataTable");
        for (var i = 0; i < assets.length; i++) {
            data = assets[i];
//            if (!isItemExist(data)) {
                appendDTO2Table(tab, data, false, "barcode");
//            }
        }
    }
}
function isItemExist(itemObj) {
    var retVal = false;
    var tab = document.getElementById("dataTable");
    if (tab.rows) {
        var trObjs = tab.rows;
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
function do_edit() {
    var va = false;
    var tab = document.getElementById("dataTable");
    var trcount;
    if (mainForm.transStatus.value == "SAVE_TEMP") {
        trcount = tab.rows.length
        for (var i = 0; i < trcount; i++) {
            var onqty = document.getElementById("onhandQty" + i).value;
            var qty = document.getElementById("quantity" + i).value;
            if (qty == "" || qty == null) {
                alert("��ȷ�Ϸ�����������Ϊ��!");
                return va;
            }

            alert(qty);
            alert(onqty);
            alert(qty - onqty);
            if (Number(qty) > Number(onqty)) {
                alert("��ȷ�Ϸ�������!");
                return va;
            } else {
                va = true;
                return va;
            }
        }
    }
    else
    {
        trcount = tab.rows.length + 1;
        for (var i = 1; i < trcount; i++) {
            var onqty = document.getElementById("onhandQty" + i).value;
            var qty = document.getElementById("quantity" + i).value
            if (qty == "" || qty == null) {
                alert("��ȷ�Ϸ�����������Ϊ��!");
                return va;
            }
            alert(qty)
            alert(onqty)
            var cc = qty - onqty;
            if (cc > 0) {
                alert("��ȷ�Ϸ�������!");
                return;
            } else {
                va = true;
            }
        }
    }

    return va;
}
function getvalues() {
    var tab = document.getElementById("dataTable");
    if (tab.rows.length == 0 || (tab.rows[0].style.display == 'none' && tab.rows.length == 1)) {
        alert("û��ѡ�������ݣ���ѡ�������ݣ�");
        return false;
    }
    return true;
}
function do_SavePo(flag) {
    if (!getvalues()) {
        return;
    }
    if (validateData()) {
        if (flag == 1) {
            document.mainForm.act.value = "<%=WebActionConstant.SAVE_ACTION%>";
            mainForm.transStatus.value = "<%=DictConstant.SAVE_TEMP%>"
            document.mainForm.submit();
        } else {
            var orgId = "<%=amsItemTransH.getFromOrganizationId()%>";
            var userId = "<%=amsItemTransH.getCreatedBy()%>";
//            var groupId = document.mainForm.groupId.value;
            var groupId = mainForm.fromDept.value;
            var procdureName = "����������������";
            var flowCode = "";
            var paramObj = new Object();
            paramObj.orgId = orgId;
            paramObj.useId = userId;
            paramObj.groupId = groupId;
            paramObj.procdureName = procdureName;
            paramObj.flowCode = flowCode;
            paramObj.submitH = "submitH()";
            assign(paramObj);
        }
    }
}

function submitH() {

    document.mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
    document.mainForm.transStatus.value = "<%=DictConstant.IN_PROCESS%>";
    document.mainForm.submit();
}

function validateData() {
    var validate = false;
    var fieldNames = "quantity";
    var fieldLabels = "����";
    var validateType = EMPTY_VALIDATE;
    validate = formValidate(fieldNames, fieldLabels, validateType);
    if (validate) {
        validateType = POSITIVE_INT_VALIDATE;
        validate = formValidate(fieldNames, fieldLabels, validateType);
    }
    return validate;
}

function validateData2(obj) {
    var validate = false;
    var fieldNames = "quantity";
    var fieldLabels = "����";
    var validateType = POSITIVE_INT_VALIDATE;
    validate = formValidate(fieldNames, fieldLabels, validateType);
//    if (validate) {
//        validateType = POSITIVE_INT_VALIDATE;
//        validate = formValidate(fieldNames, fieldLabels, validateType);
//    }
    do_change(obj);
    return validate;
}

function do_change(obj) {
    var id = obj.id.substring(8, obj.id.length);
    var qtyObj = document.getElementById("quantity" + id);
    var onhandQty = document.getElementById("onhandQty" + id).value;
//    if (isNaN(qtyObj.value)) {
//        alert("������������������");
//        qtyObj.focus();
//    }
//    if (!(Number(qtyObj.value) > 0)) {
//        alert("������������0��");
//        qtyObj.focus();
//    }
//    if (qtyObj.value.indexOf(".") !== -1) {
//        alert("��������������С����");
//        qtyObj.focus();
//    }
    if (Number(qtyObj.value) > Number(onhandQty)) {
        alert("���������ܴ��ڴ������������������룡");
        qtyObj.focus();
    }
}


    function do_selectFlow1() {
        if (validateData()) {
            disablefxsqDocument();
            showNextfxsqTasksDiv();
        }
    }


function  subPanduan(){    //�ж��Ƿ��ύʡ��˾��Ʒ��������Ա����
   var reval = document.getElementById("fxsqNextTasksSelect").value;
    if (reval == "") {
        alert("����ѡ����һ��������Ȼ����ȷ����");
        return;
    }
       if(reval=="1"){   //�ύpdaɨ��
       var paramObj = new Object();
        paramObj.orgId = "<%=user.getOrganizationId()%>";
        paramObj.useId = "<%=user.getUserId()%>";
        paramObj.groupId = mainForm.fromDept.value;
        paramObj.procdureName = "����������������";
        paramObj.flowCode = "SCANING";
        paramObj.submitH = "submitH()";
        assign(paramObj);
       }else if(reval=="2"){  //�ύ���������쵼����
        var paramObj = new Object();
        paramObj.orgId = "<%=user.getOrganizationId()%>";
        paramObj.useId = "<%=user.getUserId()%>";
        <%--paramObj.groupId = "<%=user.getCurrGroupId()%>";--%>
        paramObj.groupId = mainForm.fromDept.value;
        paramObj.procdureName = "����������������";
        paramObj.flowCode = "2";
        paramObj.submitH = "submitH()";
        assign(paramObj);
       }else if(reval=="3"){   //�ύʡ��˾��������Ա����
           do_verifyTransNo();
       }
      hideFxsqTasksDiv();
      cancelFxsqDisable();
   }



var xmlHttp;
function do_verifyTransNo() {
//    alert("222");
    var url = "";
//    var workorderObjectCode = document.form1.workorderObjectCode.value;
    createXMLHttpRequest();
//    if (document.mainForm.transId.value) {
//        alert("3333");
        url = "/servlet/com.sino.ams.spare.servlet.BjfxsqServlet?act=DO_EQUALS&transId=" + document.mainForm.transId.value;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
//    }
}

function createXMLHttpRequest() {     //����XMLHttpRequest����
    try {
        xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
    } catch(e) {
        try {
            xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
        } catch(e) {
            try {
                xmlHttp = new XMLHttpRequest();
            } catch(e) {
                alert("����XMLHttpRequest����ʧ�ܣ�");
            }
        }
    }
}

function handleReadyStateChange1() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            if (xmlHttp.responseText == 'Y') {
                alert("��ȷ������������ɨ��������");
            } else {
//                  alert("yes");
//                  province();

            }
        } else {
            alert(xmlHttp.status);
        }
    }
}

function hideFxsqTasksDiv() {
    var nextTaskDiv = document.getElementById("fxsqNextTaskDiv");
    nextTaskDiv.style.visibility = 'hidden';
}

  function cancelFxsqDisable() {
    var hideDiv = document.getElementById("fxsqHideDiv");
    hideDiv.style.visibility = 'hidden';
}

function disablefxsqDocument() {
    //disable����ҳ��
    var hideDiv = document.getElementById("fxsqHideDiv");
    hideDiv.style.visibility = 'visible';
}

//��ʾ��һ�����DIV
function showNextfxsqTasksDiv() {
    var nextTaskDiv = document.getElementById("fxsqNextTaskDiv");
    nextTaskDiv.style.position = 'absolute';

    if (event) {//�п�����û�����ֵ
        mouse_x = document.body.scrollLeft + event.clientX;
        mouse_y = document.body.scrollTop + event.clientY;
    } else {
        mouse_x = document.body.scrollLeft + 50;
        mouse_y = document.body.scrollTop + 50;
    }
    nextTaskDiv.style.left = mouse_x;
    nextTaskDiv.style.top = mouse_y;
    nextTaskDiv.style.visibility = 'visible';
}
</script>
</html>