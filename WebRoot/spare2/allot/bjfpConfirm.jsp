<%--
  Created by HERRY.
  Date: 2008-3-12
  Time: 17:12:41
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.spare2.allot.dto.AmsBjsAllotDDto" %>
<%@ page import="com.sino.ams.spare2.allot.dto.AmsBjsAllotHDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<html>
<head><title>��������ȷ��(��)</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/RadioProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/LookUp.js"></script>
    <script type="text/javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/RadioProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/json.js"></script>
    <script language="javascript" src="/flow/flow.js"></script>

</head>

<body topMargin=0 leftMargin=0>
<jsp:include page="/message/MessageProcess"/>
<%
    AmsBjsAllotHDTO amsItemTransH = (AmsBjsAllotHDTO) request.getAttribute(WebAttrConstant.ALLOT_H_DTO);
    if (amsItemTransH == null) {
        amsItemTransH = new AmsBjsAllotHDTO();
    }
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);

    RequestParser rp = new RequestParser();
    rp.transData(request);
    DTOSet set = (DTOSet) request.getAttribute(WebAttrConstant.ALLOT_D_DTO);
    String sectionRight = rp.getParameter("sectionRight");
    String divHeight = "500";
    /*if (sectionRight.equals("OUT")) {
        divHeight = "200";
    }*/
    String itemCodes = "";
%>
<form action="/servlet/com.sino.ams.spare2.allot.servlet.BjfpApproveServlet" name="mainForm" method="post">
<jsp:include page="/flow/include.jsp" flush="true"/>
<table border="1" bordercolor="#9FD6FF" class="detailHeader" id="table1" width="100%">
    <tr height="24">
        <td>
            <table width="100%" id="table2" cellspacing="1" border="0">
                <tr height="22">
                    <td align="right">���ݺţ�</td>
                    <td width="20%"><input type="text" class="detailHeader" name="transNo" readonly style="width:100%;border:none"
                               value="<%=amsItemTransH.getTransNo()%>">
                    </td>
                    <td align="right">������У�</td>
                    <td><%=amsItemTransH.getToOrganizationName()%>
                    </td>
                    <td align="right">�����ֿ⣺</td>
                    <td><%=amsItemTransH.getFromObjectName()%>
                    </td>
                </tr>
                <tr height="24">
                    <td align="right">�����ˣ�</td>
                    <td><%=amsItemTransH.getCreatedUser()%>
                    </td>
                    <td align="right">�������ڣ�</td>
                    <td><%=amsItemTransH.getCreationDate()%>
                    </td>
                    <td align="right">����״̬��</td>
                    <td><%=amsItemTransH.getTransStatusName()%>
                    </td>
                </tr>
                <tr>
                    <td align="right" height="50">��ע��</td>
                    <td colspan="7"><%=amsItemTransH.getRemark()%>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<fieldset>
    <legend>
        <img src="/images/eam_images/ok.jpg" alt="ȷ��" id="img3" onClick="do_Approve(1);">
        <%--<img src="/images/button/noPass.gif" alt="��ͨ��" id="img4" onClick="do_Approve2();">--%>
        <%--<img src="/images/eam_images/view_flow.jpg" alt="��������" onClick="viewFlow(); return false;">--%>
        <%--<img src="/images/eam_images/view_opinion.jpg" alt="�����������" onClick="viewOpinion(); return false;">--%>
        <img src="/images/eam_images/close.jpg" alt="�ر�" onClick="window.close();">
    </legend>
    <%--<table id="itemTable" class="headertable" width="100%" border="1">
        <tr>
            <td width="5%" align="center"><input type="checkbox"  name="mainCheck" value=""
                                                 class="headCheckbox"
                                                 onclick="checkAll('mainCheck','subCheck')"></td>

            <td width="30%" align="center">�豸����</td>
            <td width="35%" align="center">����ͺ�</td>
            <td width="15%" align="center">��������</td>
            <td width="15%" align="center">��������</td>
            <td style="display:none">

            </td>
        </tr>
    </table>--%>
    <script type="text/javascript">
        var columnArr = new Array( "���ϱ���", "�豸����", "����ͺ�", "��������", "�ѷ�������");
        var widthArr = new Array("15%", "30%", "35%", "10%", "10%");
        printTableHead(columnArr, widthArr);
    </script>
    <div style="overflow-y:scroll;height:<%=divHeight%>px;width:100%;left:1px;margin-left:0"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#9FD6FF" id="mtlTable" cellpadding="1" cellspacing="0">
            <%
                if (set != null && !set.isEmpty()) {

                    for (int i = 0; i < set.getSize(); i++) {
                        AmsBjsAllotDDto dto1 = (AmsBjsAllotDDto) set.getDTO(i);
                        itemCodes += dto1.getItemCode() + ",";
            %>
            <tr height="20px" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'" onclick="this.cells[0].childNodes[0].checked=true;">
                <td width="15%"><%=dto1.getBarcode()%>
                </td>
                <td width="30%" name="itemName" id="itemName<%=i%>"><%=dto1.getItemName()%>
                </td>
                <td width="35%" name="itemSpec" id="itemSpec<%=i%>"><%=dto1.getItemSpec()%>
                </td>
                <td align=center width="10%"><input name="itemAmount" class="noborderGray" readonly
                                                    style="width:100%;text-align:right"
                                                    id="itemAmount<%=i%>" value="<%=dto1.getItemAmount()%>">
                </td>
                <td align=center width="10%"><input type="text" name="quantity" id="quantity<%=i%>"
                                                    class="noborderYellow"
                                                    style="width:100%;text-align:right" value="<%=dto1.getQuantity()%>">
                </td>
                <td style="display:none">
                    <%--<input type="hidden" name="itemCode" id="itemCode<%=i%>" value="<%=dto1.getItemCode()%>">--%><%--<input type="radio" name="tempRadio" id="tempRadio<%=i%>" value="<%=i%>">--%>
                </td>
                <td style="display:none">
                    <%--<input type="hidden" name="detailId" id="detailId<%=i%>" value="<%=dto1.getDetailId()%>">--%>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</fieldset>
<input type="hidden" name="act" value="">
<input type="hidden" name="transStatus" value="<%=amsItemTransH.getTransStatus()%>">
<input type="hidden" name="transType" value="<%=amsItemTransH.getTransType()%>">
<input type="hidden" name="transId" value="<%=amsItemTransH.getTransId()%>">
<input type="hidden" name="createdBy" value="<%=amsItemTransH.getCreatedBy()%>">
<input type="hidden" name="value1" id="value1" value="">
<input type="hidden" name="checkedIndex" value="">
<input type="hidden" name="groupId" value="">
<input type="hidden" name="detailIds" value="">
<input type="hidden" name="flag" value="">
</form>
</body>
</html>
<script type="text/javascript">

function isItemExist(itemObj) {
    var retVal = false;
    var tabObj = document.getElementById("mtlTable");
    if (tabObj.rows) {
        var trObjs = tabObj.rows;
        var trCount = trObjs.length;
        var trObj = null;
        var itemValue = itemObj.itemCode;
        var rowValue = null;
        for (var i = 0; i < trCount; i++) {
            trObj = trObjs[i];
            rowValue = trObj.cells[5].childNodes[0].value;
            if (itemValue == rowValue) {
                retVal = true;
            }
        }
    }
    return retVal;
}
function do_Approve(flag) {
    document.mainForm.flag.value = flag;
    var paramObj = new Object();
    paramObj.orgId = "<%=user.getOrganizationId()%>";
    paramObj.useId = "<%=user.getUserId()%>";
    paramObj.groupId = "<%=user.getCurrGroupId()%>";
    paramObj.procdureName = "������������";
    paramObj.flowCode = "";
    paramObj.submitH = "submitH()";
    assign(paramObj);
}
function do_Approve2() {
    mainForm.act.value = "<%=WebActionConstant.REJECT_ACTION%>";
    addApproveContent();
    mainForm.submit();
}
function submitH() {
    var flag = Number(document.mainForm.flag.value);
    var sessionR = "<%=sectionRight%>";
    var actVal = "";
    switch (flag) {
        case 1: actVal = "<%=WebActionConstant.APPROVE_ACTION%>"; break;
        case 2: actVal = "<%=WebActionConstant.REJECT_ACTION%>"; break;
        case 3: actVal = "<%=WebActionConstant.RECEIVE_ACTION%>"; break;
        default :actVal = "<%=WebActionConstant.APPROVE_ACTION%>";
    }
    mainForm.act.value = actVal;
    mainForm.submit();
    //        mainForm.action = "/servlet/com.sino.ams.spare2.allot.servlet.AmsBjsAllotouServlet";
    //        document.mainForm.act.value = actVal;
    //        document.mainForm.submit();
}
function do_SelectItem() {
    var selectedItemCode = getRadioValue("selectItemCode");
    if (selectedItemCode == null || selectedItemCode == "") {
        alert("����ѡ��һ���豸�����ͺţ�");
        return;
    }
    var ic = selectedItemCode.split(":");
    var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.BJSL_ITEM_INFO2%>&itemCodes=" + ic[0] + "&organizationId=<%=amsItemTransH.getFromOrganizationId()%>";
    var popscript = "dialogWidth:51;dialogHeight:33;center:yes;status:no;scrollbars:no";
    /*   window.open(url);*/
    var items = window.showModalDialog(url, null, popscript);
    if (items) {
        var data = null;
        var tab = document.getElementById("dataTable");
        for (var i = 0; i < items.length; i++) {
            data = items[i];
            if(!isItemExist(data)){
            appendDTORow(tab, data);
            }
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
function do_delete() {
    var tab = document.getElementById("mtlTable");
    //��ɾ������LINE_ID���е�LINE_ID�ӵ�lineIds��
    var rowCount = tab.rows.length;
    if (rowCount > 0) {
        var boxArr = getCheckedBox("subCheck");
        var chkCount = boxArr.length;
        if (chkCount > 0) {
            var chkObj = null;
            for (var i = 0; i < chkCount; i++) {
                chkObj = boxArr[i];
                var checkboxId = chkObj.id;
                var id = checkboxId.substring(8, checkboxId.length);
                var detailId = document.getElementById("detailId" + id).value;
                if (detailId != "") {
                    mainForm.detailIds.value += detailId + ",";
                }
            }
        }
    }
    //        alert("mainForm.detailIds.value="+mainForm.detailIds.value)

    deleteTableRow(tab, 'subCheck');

}
function checkValues() {
    var retVal = true;
    var tab = document.getElementById("mtlTable");
    if (tab.rows.length == 0 || (tab.rows[0].style.display == 'none' && tab.rows.length == 1)) {
        alert("û��ѡ�������ݣ���ѡ�������ݣ�");
        retVal = false;
    } else {
        var qtys = document.getElementsByName("allotQty");
        for (var i = 0; i < qtys.length; i++) {
            if (qtys[i].value == "") {
                alert("������������");
                qtys[i].focus();
                retVal = false;
                break;
            }
        }
    }

    return retVal;
}
function init() {

}
function selectItem(obj){
    var id = obj.id.substring(11, obj.id.length);
    var itemCode = document.getElementById("itemCode" + id).value;
    var allRadios = document.getElementsByName("selectItemCode");
    if(allRadios.length){
         for(var i=0;i<allRadios.length;i++){
             var radioItem = allRadios[i];
             var radioValue = radioItem.value;
             var splitValue = radioValue.split(":");
             var tempItemCode = splitValue[0];
             if(tempItemCode == itemCode){
                 radioItem.checked = true;
                 break;
             }
         }
    }else {
        allRadios.checked = true;
    }
}
function checkQty(obj) {
    var quantity;
    //���豸�ͺŷ�������ͨ��������
    var tempQty = 0;
    var selectedItemCode = getRadioValue("selectItemCode");
    var si = selectedItemCode.split(":");
    quantity = si[1];
//    alert("quantity=" + quantity);
    var itemCode = document.getElementById("itemCode" + obj.id.substring(11, obj.id.length)).value;
    var allocateQty = document.getElementById("allocateQty" + obj.id.substring(11, obj.id.length)).value;
    var itemCodes = document.getElementsByName("itemCode");
    var tempId;
    var tempObj;
    if (itemCodes.length) {
        tempObj = itemCodes[0];
        for (var i = 0; i < itemCodes.length; i++) {
            var item = itemCodes[i];
            if (item.value == itemCode) {
                tempId = item.id.substring(8, item.id.length);
                tempQty += Number(document.getElementById("allocateQty" + tempId).value);
                //                   alert("tempQty="+tempQty);
            }
        }
    } else {
        tempId = itemCodes.id.substring(8, item.id.length);
        //            alert("tempId="+tempId);
        tempQty += Number(document.getElementById("allocateQty" + tempId).value);
        //                   alert("tempQty="+tempQty);
        tempObj = itemCodes;
    }
//    alert("tempQty=" + tempQty)
    if (tempQty > Number(quantity)) {
        alert("ÿһ���豸�ķ�������֮�Ͳ��ܳ����ܷ������������������룡");
        document.getElementById("allocateQty" + tempId).focus();
    }
}
</script>