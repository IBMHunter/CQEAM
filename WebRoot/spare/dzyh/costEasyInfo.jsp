<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.instrument.dto.AmsInstrumentInfoDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.spare.dzyh.dto.CostEasyDTO" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes"%>
<%@ page import="com.sino.ams.newasset.constant.AssetsLookUpConstant"%>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-4-7
  Time: 16:34:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>��ֵ�׺�Ʒ��ϸ��Ϣҳ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
</head>
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet"/>
<body topMargin=0 leftMargin=0>
<script type="text/javascript">
    printTitleBar("��ֵ�׺�Ʒ��ϸ��Ϣҳ��")
</script>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    CostEasyDTO dto = (CostEasyDTO) request.getAttribute(WebAttrConstant.DZYH_DTO);
    String action = parser.getParameter("act");
    SfUserDTO sfUser = (SfUserDTO) SessionUtil.getUserAccount(request);
    String orgId = sfUser.getOrganizationId();
%>
<form action="/servlet/com.sino.ams.spare.dzyh.servlet.CostEasyServlet" name="mainForm" method="post">
    <table border="0" width="100%" hight = "800">
        <tr>
            <td align="center" colspan="2"  width="45%" id="barcodeNo11" style="color:red;visibility:hidden">�Բ��𣬸ñ�ǩ���Ѵ��ڣ�</td>
        </tr>
        <tr>
        <tr>
            <td align="right" width="15%">��ֵ�׺�Ʒ��ǩ�ţ�</td>
            <td width="45%" align="left">
                <input name="barcode" type="text" class="noEmptyInput" value="<%=dto.getBarcode()%>" onblur="do_verifybarcodeNo();" style="width:55%">
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">�豸���ͣ�</td>
            <td width="45%" align="left">
                <input name="itemCategoryDesc" type="text" readonly class="readonlyInput" value="<%=dto.getItemCategoryDesc()%>" style="width:55%">
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">��ֵ�׺�Ʒ���ƣ�</td>
            <td width="45%" align="left">
                <input type="text" name="itemName" style="width:55%" class="noEmptyInput" readonly value="<%=dto.getItemName()%>">
                <a class="linka" style="cursor:'hand'" onclick="do_SelectSystemItem();">[��]
                <%--<%if(dto.getSystemid().equals("")){%>[��]<%}%>--%>
                </a>
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">����ͺţ�</td>
            <td width="45%" align="left">
                <input type="text" name="itemSpec" class="noEmptyInput" style="width:55%" readonly value="<%=dto.getItemSpec()%>">
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">���̣�</td>
            <td width="45%">
                <input type="text" name="manufacturerName" style="width:55%" class="readonlyInput" readonly value="<%=dto.getManufacturerName()%>">
                <a class="linka" style="cursor:'hand'" onclick="do_selectNameManufacturer();">[��]</a>
                <input type="hidden" name="manufacturerId" value="">
            </td>
        </tr>
        <tr>
            <td align = "right" width = "15%">������</td>
             <td width="45%" align="left">
                 <input type="text" name="itemQty" class="noEmptyInput" style="width:55%" value="<%=dto.getItemQty()%>" onblur="do_verify();">
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">�ص����ƣ�</td>
            <td width="45%">
                <input type="text" style="width:55%" name="workorderObjectName" class="noEmptyInput" value="<%=dto.getWorkorderObjectName()%>">
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">���β��ţ�</td>
            <td width="45%">
                <select name="responsibilityDept" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.DEPT_OPTIONS)%></select>
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">�����ˣ�</td>
            <td width="45%">
                <input type="text" style="width:55%" name="responsibilityUserName" class="noEmptyInput" readonly value="<%=dto.getResponsibilityUserName()%>">
                <a class="linka" style="cursor:'hand'" onClick="do_SelectPerson();">[��]</a>
                <input type="hidden" name="responsibilityUser" value="">
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">רҵ���ţ�</td>
            <td width="45%">
                <select name="specialityDept" style="width:60%" class="noEmptyInput"><%=request.getAttribute("DEPT_OPTIONS2")%>
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">רҵ�����ˣ�</td>
            <td width="45%">
                <input type="text" style="width:55%" name="specialityUserName" class="noEmptyInput" readonly value="<%=dto.getSpecialityUserName()%>">
                <a class="linka" style="cursor:'hand'" onClick="do_selectSpecialityUser();">[��]</a>
                <input type="hidden" name="specialityUser" value="">
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">�Ƿ�TD��</td>
            <td width="45%">
                <select name="isTD" style="width:60%">
                   <option value="">--��ѡ��--</option>
                   <option value="Y" <%=dto.getIsTD().equals("Y")?"selected":""%>>��</option>
                   <option value="N" <%=dto.getIsTD().equals("N")?"selected":""%>>��</option>
             </select>
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">ʹ���ˣ�</td>
            <td width="45%">
                <input type="text" name="maintainUser" style="width:60%" value="<%=dto.getMaintainUser()%>">
            </td>
        </tr>
        <tr>
            <td align="right" width="15%">��ֵ�׺�Ʒ��ֵ��</td>
            <td width="45%">
                <input type="text" name="price" style="width:60%" value="<%=dto.getItemQty()%>" onblur="do_verify2();">
            </td>
        </tr>

        <tr>
            <td align="right" width="15%">�������ڣ�</td>
                <td width="45%"><input type="text" name="startDate" class="readonlyInput" value="<%=dto.getStartDate()%>"
                                   style="width:60%"
                                   title="���ѡ������" readonly  onclick="gfPop.fPopCalendar(startDate)">
                <img src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fPopCalendar(startDate)"></td>
        </tr>
        <tr>
            <td align="right" width="15%">�� ע��</td>
            <td width="45%"><textarea rows="4" cols="50" name="remark"><%=dto.getRemark()%></textarea></td>
        </tr>
        <tr>
            <td align="right" width="15%"></td>
            <td width="45%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <img src="/images/button/save.gif" alt="�����ֵ�׺�Ʒ��Ϣ"
                     onClick="do_savePlan(); return false;">
                <img src="/images/button/back.gif" alt="����"
                     onclick="do_concel();return false;"></td>
        </tr>
    </table>
    <input type="hidden" name="act" value="<%=action%>">
    <input type="hidden" name="vendorId" value="<%=dto.getVendorId()%>">
    <input type="hidden" name="systemid" value="<%=dto.getSystemid()%>">
    <input type="hidden" name="addressId" value="<%=dto.getAddressId()%>">
    <input type="hidden" name="prjId" value="<%=dto.getPrjId()%>">
    <input type="hidden" name="itemCode" value="<%=dto.getItemCode()%>">
    <input type="hidden" name="itemCategory" value="<%=dto.getItemCategory()%>">
    <input type="hidden" name="organizationId" value="<%=dto.getOrganizationId()%>">
</form>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script type="text/javascript">
function do_savePlan() {
    var fieldNames = "barcode;itemName;itemQty;workorderObjectName;responsibilityDept;responsibilityUserName;specialityDept;specialityUserName";
    var fieldLabels = "��ǩ��;����;����;���ڵص�;���β���;������;רҵ����;רҵ������";
    var validateType = EMPTY_VALIDATE;
    var isValid = formValidate(fieldNames, fieldLabels, validateType);
    if (isValid) {
        var barCode = "<%=dto.getBarcode()%>"
        if (mainForm.systemid.value == "") {
            mainForm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
        } else {
            mainForm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
        }
        mainForm.submit();
    }

}
function do_concel() {
    with (mainForm) {
        window.close();
        act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        submit();
    }
}

function do_selectSpecialityUser() {
    with(mainForm){
		var deptCode = document.mainForm.specialityDept.value;
		if(deptCode == ""){
			alert("����ѡ��רҵ���ţ���ѡ��רҵ������");
            document.mainForm.specialityDept.focus();
            return;
		}
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_SPECIAL_USER%>";
		var dialogWidth = 48;
		var dialogHeight = 30;
		var userPara = "deptCode=" + deptCode;
		var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		if(users){
            var user = users[0];
            mainForm.specialityUser.value = user["employeeId"];
            mainForm.specialityUserName.value = user["userName"];
        } else {
            mainForm.specialityUser.value = "";
            mainForm.specialityUserName.value = "";
        }
	}
}

function do_selectName() {
    //alert(mainForm.itemCategory.value);
    var lookUpName = "<%=LookUpConstant.LOOK_UP_SYS_ITEM%>";
    var dialogWidth = 50;
    var dialogHeight = 30;
    var userPara = "itemCategory=" + mainForm.itemCategory.value;
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
        }
    }
}
function do_selectVerdorName() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_PURVEY%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
        }
    }
}

function do_save() {
    var fieldNames = "barcode;itemName;itemQty";
    var fieldLabels = "��ǩ��;����;����";
    var validateType = EMPTY_VALIDATE;
    var isValid = formValidate(fieldNames, fieldLabels, validateType);
    if (isValid) {
        if (mainForm.systemid.value == "") {
            mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
        }
        else {
            mainForm.act.value = "<%=AMSActionConstant.INSTEAD_ACTION%>";
        }
        mainForm.submit();
    }
}

function do_selectDept() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_RESPUSER%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    //LOOKUP������ �����DTO��һ��
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
        }
    }
}

function selectSysitem() {
    //alert(mainForm.itemCategory.value);
    var lookUpName = "<%=LookUpConstant.LOOK_UP_INSTR_ITEM%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var userPara = "itemCategory=" + mainForm.itemCategory.value;
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
        }
    }
}

function do_verify() {
    var fieldNames = "itemQty";
    var fieldLabels = "����";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_INT_VALIDATE)) {
    }
}
function do_verify2() {
    var fieldNames = "price";
    var fieldLabels = "��ֵ";
    if (!formValidate(fieldNames, fieldLabels, NUMBER_VALIDATE)) {
    }
}


function do_selectUserName() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_USER1%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    //LOOKUP������ �����DTO��һ��
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    //       alert(users);
    if (users) {
        var user = null;
        //        for (var i = 0; i < users.length; i++) {
        //            user = users[i];
        //            dto2Frm(user, "mainForm");
        document.mainForm.maintainUser.value = users[0].username;
        document.mainForm.userId2.value = users[0].userId;
        //        }
    } else {
        document.mainForm.maintainUser.value = "";
        document.mainForm.userId2.value = "";
    }

}

function do_selectNameAddress() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_ADDRESS%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
        }
    }
}

function do_selectNameVendor() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_PURVEY%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
        }
    }
}

function do_SelectSystemItem() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_SYS_ITEM%>";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var userPara = "itemCategory=DZYH" ;
    //LOOKUP������ �����DTO��һ��
    var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (users) {
        var user = null;
        for (var i = 0; i < users.length; i++) {
            user = users[i];
            dto2Frm(user, "mainForm");
        }
    }
}



var xmlHttp;
function do_verifybarcodeNo() {
//    isBar();
    var url = "";
    var segment1 = document.mainForm.barcode.value;
    if (segment1 != '') {
        var bar = segment1.substring(5,7);
        for (var i = 0; i<2; i++) {
            ch=bar.charCodeAt(i);
            if (ch>=65 && ch<=90) {
            } else {
               alert("��ǩ�ŷǷ�");
               document.mainForm.barcode.focus();
               return;
            }
        }
        if (segment1.length != 13) {
            alert("��ǩ�ŷǷ�");
               document.mainForm.barcode.focus();
               return;
        }
    }
    createXMLHttpRequest();
    if (document.mainForm.barcode.value) {
        url = "/servlet/com.sino.ams.system.specialty.servlet.OtEqVindicateServlet?act=verifyBarcode&barcode=" + document.mainForm.barcode.value;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
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
            document.mainForm.barcode.style.color = "red";
            document.getElementById("barcodeNo11").style.visibility = "visible"
            document.mainForm.barcode.focus();
        } else {
            document.mainForm.barcode.style.color = "black";
            document.getElementById("barcodeNo11").style.visibility = "hidden";
        }
    } else {
        alert(xmlHttp.status);
    }
}
}


function isBar() {
    var str = document.mainForm.barcode.value;
    var patrn = /^(\d{4}\-)?\d{8}$/;
    if (patrn.exec(str)) {
        return true;
    } else{
        alert("��������ȷ�ı�ǩ�ţ�");
        document.mainForm.barcode.focus();
        return false;
    }
}


function do_selectNameManufacturer() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_MANUFACTURER%>";
        var dialogWidth = 48;
        var dialogHeight = 30;
        var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                user = users[i];
                dto2Frm(user, "mainForm");
            }
        } else {
            mainForm.manufacturerId.value = "";
            mainForm.manufacturerName.value = "";
        }
}

function do_SelectPerson(){
	with(mainForm){
		var deptCode = responsibilityDept.value;
		if(deptCode == ""){
			alert("����ѡ�����β��ţ���ѡ��������");
            document.mainForm.responsibilityDept.focus();
            return;
		}
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_PERSON%>";
		var dialogWidth = 47;
		var dialogHeight = 30;
		var userPara = "deptCode=" + deptCode;
		var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		if(users){
			var user = users[0];
			mainForm.responsibilityUserName.value = user["userName"];
			mainForm.responsibilityUser.value = user["employeeId"];
		} else {
			mainForm.responsibilityUserName.value = "";
			mainForm.responsibilityUser.value = "";
		}
	}
}
</script>