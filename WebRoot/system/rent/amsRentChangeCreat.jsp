<%@ page import="com.sino.ams.newasset.constant.*" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsTransLineDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%--
  Created by IntelliJ IDEA.
  User: yuyao
  Date: 2008-7-10
  Time: 15:06:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.htm" %>
<html>
<%
    AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
    String transStatus = headerDTO.getTransStatus();
    String transType = headerDTO.getTransType();
    String transferType = headerDTO.getTransferType();
    String transId = headerDTO.getTransId();
    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    String orgId = userAccount.getOrganizationId();
    String userId = userAccount.getUserId();
    String attribute1 = headerDTO.getAttribute1();
%>
<head>
    <title><%=headerDTO.getTransTypeValue()%></title>
    <script type="text/javascript">
     var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "do_Cancel");
//        var ArrAction1 = new Array(true, "����", "action_save.gif", "����", "do_Save");
        var ArrAction2 = new Array(true, "�ύ", "action_sign.gif", "�ύ", "do_Div_Complete_Start");
        var ArrAction12 = new Array(true, "����", "action_cancel.gif", "����", "do_CancelOrder");
        var ArrAction5 = new Array(true, "��������", "actn023.gif", "��������", "do_ViewFlow") ;
      <% 	if(!transId.equals("")){ %>
        var ArrActions = new Array(ArrAction0,  ArrAction2,ArrAction12, ArrAction5);
        <%}else{%>
                 var ArrActions = new Array(ArrAction0,  ArrAction2, ArrAction5);
                <% }%>
        var ArrSinoViews = new Array();
        printToolBar();
    </script>
</head>
<%@ include file="/flow/flowNoButton.jsp"%>
<body leftmargin="0" topmargin="0" onLoad="initPage1();doLoad();" onbeforeunload="doBeforeUnload();" onUnload="doUnLoad();">
<form action="/servlet/com.sino.ams.system.rent.servlet.AMSRentChangeServlet" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<%@ include file="/flow/flowPara.jsp" %>
<jsp:include page="/flow/include.jsp" flush="true"/>
<table border="0" class="queryTable" width="100%" style="border-collapse: collapse" id="table1">
    <tr>
        <td>
            <table width=100% border="0">
                <tr>
                    <td align=right width=8%>���ݺţ�</td>
                    <td width=22%>
                        <input name="transNo" class="input_style2" readonly style="width:95%"
                               value="<%=headerDTO.getTransNo()%>">
                    </td>
                    <td align=right width=8%>����״̬��</td>
                    <td width=22%>
                        <input name="transStatusDesc" class="input_style2" readonly style="width:95%"
                               value="<%=headerDTO.getTransStatusDesc()%>">
                    </td>
                    <td align=right width=8%>�������ڣ�</td>
                    <td width=22%>
                        <input name="creationDate" class="input_style2" readonly style="width:95%"
                               value="<%=headerDTO.getCreationDate()%>"></td>
                </tr>
                <tr>
                    <td align=right width=8%>�����ˣ�</td>
                    <td width=22%>
                        <input name="created" class="input_style2" readonly style="width:95%"
                               value="<%=headerDTO.getCreated()%>">
                    </td>
                    <td align=right>��˾���ƣ�</td>
                    <td>
                        <input name="userCompanyName" class="input_style2" readonly style="width:95%"
                               value="<%=headerDTO.getFromCompanyName()%>"></td>
                    <td align=right width=8%>�������ƣ�</td>
                    <td width=22%>
                        <input name="userDeptName" class="input_style2" readonly style="width:95%"
                               value="<%=headerDTO.getUserDeptName()%>" size="20"></td>
                </tr>
                <tr>
                    <td align=right width=8%>�ֻ����룺</td>
                    <td width=22%>
                        <input name="phoneNumber1" class="input_style2" readonly style="width:95%"
                               value="<%=headerDTO.getPhoneNumber()%>" size="20"></td>
                    <td align=right>�����ʼ���</td>
                    <td>
                        <input name="email1" class="input_style2" readonly style="width:95%"
                               value="<%=headerDTO.getEmail()%>" size="20"></td>
                    <td align=right width=8%>���벿�ţ�</td>
                    <td width=22%>
                        <select name="toDept" style="width:95%" class="select_style1"><%=headerDTO.getToDeptOption()%>
                        </select> <font color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <td align=right width=8% align="right" height="40">����˵������</td>
                    <td colspan="7" height="40"><textarea name="createdReason" style="width:98.5%;height:100%" class="input_style1" rows="1" cols="20"><%=headerDTO.getCreatedReason()%></textarea>
                    <font color="red">*</font></td>
                </tr>
                <input type="hidden" name="toOrganizationId" value="<%=userAccount.getOrganizationId()%>">
            </table>
        </td>
    </tr>
</table>
<input type="hidden" name="fromGroup" value="<%=headerDTO.getFromGroup()%>">
<%
    if (!transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) {
%>
<input type="hidden" name="fromOrganizationId" value="<%=headerDTO.getFromOrganizationId()%>">
<%
    }
%>
<input type="hidden" name="transType" value="<%=transType%>">
<input type="hidden" name="transferType" value="<%=headerDTO.getTransferType()%>">
<input type="hidden" name="created" value="<%=headerDTO.getCreated()%>">
<input type="hidden" name="createdBy" value="<%=headerDTO.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=transId%>">
<%--<input type="hidden" name="procdureName" value="<%=headerDTO.getProcdureName()%>">--%>
<input type="hidden" name="act" value="">
<fieldset style="border:1px solid #397DF3; position:absolute;top:133px;width:100%;height:70%">
    <legend>
        <%
        if(headerDTO.getTransId().equals("")){
        %>
        <img src="/images/button/select.gif" alt="ѡ���ʲ�" onClick="do_SelectAssets();return false;">
        <img src="/images/eam_images/delete_line.jpg" alt="ɾ����" onClick="deleteLine(); return false;">
          <%}%>
        <% if(headerDTO.getTransStatus().equals("REJECTED")){%>
        <img src="/images/button/select.gif" alt="ѡ���ʲ�" onClick="do_SelectAssets(); return false;">
        <img src="/images/eam_images/delete_line.jpg" alt="ɾ����" onClick="deleteLine(); return false;">
        <%}%>
        <span id="warn"></span>
        <%
            if (!transStatus.equals("") && !transStatus.equals(AssetsDictConstant.SAVE_TEMP)) {
        %>
        <%--<img src="/images/button/viewOpinion.gif" alt="�����������" onClick="viewOpinion(); return false;">--%>
        <%--<img src="/images/button/viewFlow.gif" alt="��������" onClick="viewFlow(); return false;">--%>
        <%
            }
        %>

        ͳһ���ã�
        <input type="checkbox" name="allUser" id="allUser"><label for="allUser">��������</label>
        <input type="checkbox" name="allLocation" id="allLocation"><label for="allLocation">�µص�</label>
    </legend>
    <div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
        <table class="headerTable" border="1" width="140%">
            <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
                <td align=center width="3%"><input type="checkbox" name="mainCheck" value=""
                                                   onPropertyChange="checkAll('mainCheck','subCheck')"></td>
                <td align=center width="8%">�ʲ���ǩ</td>
                <td align=center width="8%">�ʲ�����</td>
                <td align=center width="8%">�ʲ��ͺ�</td>
                <td align=center width="3%">����</td>
                <td align=center width="4%">ԭ������</td>
                <td align=center width="4%">��������</td>
                 <td align=center width="11%">�µص�</td>
                <!--<td align=center width="10%">�²���</td>-->
                <td align="center" width="5%">��������</td>
                <td align=center width="8%">��ע</td>
                <td style="display:none">�������ֶ�</td>
            </tr>
        </table>
    </div>
    <input type="hidden" name="test1" value="1">

    <div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:48px;left:1px" align="left"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666">
            <%
                DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                if (lineSet == null || lineSet.isEmpty()) {
            %>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="display:none">
                <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value=""></td>
                <td width="8%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)">
                    <input type="text" name="barcode" id="barcode0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input
                        type="text" name="itemName" id="itemName0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input
                        type="text" name="itemSpec" id="itemSpec0" class="finput" readonly value=""></td>
                <td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input
                        type="text" name="itemQty" id="itemQty0" class="finput3" readonly value=""></td>
                <td width="4%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input
                        type="text" name="userName" id="userName0" class="finput3" readonly value=""></td>
                        
                <td width="4%" align="left"><input type="text" name="responsibilityUserName"
                                                   id="responsibilityUserName0" class="finputNoEmpty" readonly value=""
                                                   onClick="do_SelectPerson(this)" title="���ѡ��������������"
                                                   style="cursor:hand"></td>
               <td width="11%"><input type="text" name="assignedToLocationName" id="assignedToLocationName0" class="finputNoEmpty" readonly value="" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"></td>
                <!--<td width="10%"><input type="text" name="responsibilityDeptName"-->
                                                   <!--id="responsibilityDeptName0" class="finput3" readonly value="" ></td>-->
                
                <td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0"
                                                     style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly
                                                     value="" onclick="gfPop.fPopCalendar(lineTransDate0)"
                                                     title="���ѡ�����ĵ�������" onBlur="do_SetLineTransDate(this)"></td>
                <td width="8%" align="left"><input type="text" name="remark" id="remark0" class="finput" value=""></td>
                <td style="display:none"><input type="hidden" name="oldLocation" id="oldLocation0" value=""></td>
                <td style="display:none"><input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser0"
                                                value=""></td>
                <td style="display:none"><input type="hidden" name="responsibilityDept" id="responsibilityDept0"
                                                value=""></td>
                <td style="display:none"><input type="hidden" name="assignedToLocation" id="assignedToLocation0"
                                                value=""></td>
                <td style="display:none"><input type="hidden" name="responsibilityUser" id="responsibilityUser0"
                                                value=""></td>
                <td style="display:none"><input type="hidden" name="addressId" id="addressId0" value=""></td>
            </tr>
            <%
            } else {
                AmsAssetsTransLineDTO lineDTO = null;
                String barcode = "";
                for (int i = 0; i < lineSet.getSize(); i++) {
                    lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
                    barcode = lineDTO.getBarcode();
            %>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="cursor:hand">
                <td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>"
                                                     value="<%=barcode%>"></td>
                <td width="8%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput"
                                                         readonly value="<%=barcode%>"></td>
                <td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="itemName" id="itemName<%=i%>" class="finput"
                                                         readonly value="<%=lineDTO.getItemName()%>"></td>
                <td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="itemSpec" id="itemSpec<%=i%>" class="finput"
                                                         readonly value="<%=lineDTO.getItemSpec()%>"></td>
                <td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="itemQty" id="itemQty<%=i%>" class="finput3"
                                                         readonly value="<%=lineDTO.getItemQty()%>"></td>
                <td width="4%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="userName" id="userName<%=i%>" class="finput"
                                                         readonly value="<%=lineDTO.getUserName()%>"></td>
             <td width="4%" align="left"><input type="text" name="responsibilityUserName"
                                                   id="responsibilityUserName<%=i%>" class="finputNoEmpty" readonly
                                                   value="<%=lineDTO.getResponsibilityUserName()%>"
                                                   onClick="do_SelectPerson(this)" title="���ѡ��������������"
                                                   style="cursor:hand"></td>
              <td width="11%"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finputNoEmpty" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"></td>

                <td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>"
                                                     style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly
                                                     value="<%=lineDTO.getLineTransDate()%>"
                                                     onclick="gfPop.fPopCalendar(lineTransDate<%=i%>)" title="���ѡ�����ĵ�������"
                                                     onBlur="do_SetLineTransDate(this)"></td>
                <td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput"
                                                   value="<%=lineDTO.getRemark()%>"></td>
                <td style="display:none">
                    <input type="hidden" name="oldLocation" id="oldLocation<%=i%>"
                           value="<%=lineDTO.getOldLocation()%>">
                    <input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>"
                           value="<%=lineDTO.getOldResponsibilityUser()%>">
                    <input type="hidden" name="responsibilityDept" id="responsibilityDept<%=i%>"
                           value="<%=lineDTO.getResponsibilityDept()%>">
                    <input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>"
                           value="<%=lineDTO.getAssignedToLocation()%>">
                    <input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>"
                           value="<%=lineDTO.getResponsibilityUser()%>">
                    <input type="hidden" name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>">
                </td>
            </tr>
            <%
                    }
                }%>
        </table>
    </div>
</fieldset>
</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<script type="text/javascript">
var srcToOrgValue = "";
var srcDeptValue = null;
var transferType = "";
var attribute1 = "<%=attribute1%>";

if (mainFrm.toOrganizationId) {
    srcToOrgValue = mainFrm.toOrganizationId.value;
}
if (mainFrm.fromDept) {
    srcDeptValue = mainFrm.fromDept.value;
}
if (mainFrm.transferType) {
    transferType = mainFrm.transferType.value;
}
var dataTable = document.getElementById("dataTable");

/**
 * ���ܣ�ѡ���ʲ�
 */
function do_SelectAssets() {
    var dialogWidth = 52;
    var dialogHeight = 29;
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_RENT%>";
    var userPara = "&deptCode=" +<%=userAccount.getDeptCode()%>;
    var assets = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (assets) {
        var data = null;
        for (var i = 0; i < assets.length; i++) {
            data = assets[i];
            appendDTO2Table(dataTable, data, false, "barcode");
        }
    }
}

function deleteLine() {
    deleteTableRow(dataTable, 'subCheck');
}

function doInitArray() {
    segment10Array = new Array();
    projectNameArray = new Array();
    projectNameIndex = -1;
    var segment10 = document.getElementsByName("segment10");
    for (var i = 2; i < segment10.length; i++) {
        segment10Array[i - 2] = segment10[i].value;
    }
    var projectName = document.getElementsByName("projectName");
    for (var i = 2; i < projectName.length; i++) {
        if (!isEmpty(projectName[i].value)) {
            projectNameIndex++;
            projectNameArray[projectNameIndex] = projectName[i].value;
        }
    }
}

function do_SaveOrder() {
    mainFrm.act.value = "<%=AssetsActionConstant.SAVE_ACTION%>";
    mainFrm.submit();
    document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}
function validate() {
	var tab = document.getElementById("dataTable") ;
    var rowcount = tab.rows.length;
    if (tab.rows.length == 0 || (tab.rows[0].style.display == 'none' && tab.rows.length == 1)) {
        alert("û��ѡ�������ݣ���ѡ�������ݣ�");
        return;
    }
    for (var i = 0; i < rowcount; i++) {
        var name = tab.rows[i].cells[7].childNodes[0].value;
        var lineDat = tab.rows[i].cells[9].childNodes[0].value;
        if (name == "" || lineDat == "") {
            alert("��ѡ���������˺�ʱ�䣡");
            return;
        }
    }
    var res=mainFrm.createdReason.value;
    if(res==""){
      	alert("����д����˵����");
      	mainFrm.createdReason.focus();
        return;
    }
}
        function do_AppValidate() {
	var tab = document.getElementById("dataTable") ;
    var rowcount = tab.rows.length;
    if (tab.rows.length == 0 || (tab.rows[0].style.display == 'none' && tab.rows.length == 1)) {
        alert("û��ѡ�������ݣ���ѡ�������ݣ�");
        return;
    }
    for (var i = 0; i < rowcount; i++) {
        var name = tab.rows[i].cells[6].childNodes[0].value;
        var lineDat = tab.rows[i].cells[7].childNodes[0].value;
        if (name == "" || lineDat == "") {
            alert("��ѡ���������˺͵ص㣡");
            return;
        }
    }
    var res=mainFrm.createdReason.value;
    if(res==""){
      	alert("����д����˵����");
      	mainFrm.createdReason.focus();
        return;
    }
}
function do_SubmitOrder() {
    if (!validate()) {
        return;
    }
    var orgId = "<%=orgId%>";
    var userId = "<%=userId%>";
    var procdureName = "���ʲ���������";
    var flowCode = "";
    var paramObj = new Object();

	paramObj.orgId = orgId;
    paramObj.useId = userId;
    paramObj.procdureName = procdureName;
    paramObj.flowCode = flowCode;
    paramObj.submitH = 'submitH()';
    //	alert("flowCode = " + flowCode);
    assign(paramObj);
}


function submitH() {
    mainFrm.act.value = "<%=AssetsActionConstant.SUBMIT_ACTION%>";
    mainFrm.submit();
}

function initPage() {
    window.focus();
    if (attribute1 == "<%=AssetsDictConstant.TRANS_EDIT_YES%>") {//���������н����ҳ��
        document.getElementById("table1").disabled = true;
        //		alert(document.getElementById("table1").disabled);
    } else {
        var fromGroup = mainFrm.fromGroup.value;
        if (fromGroup == "") {
            do_SelectCreateGroup();
        }
    }
}


function do_SelectCreateGroup() {
    var fromGroup = mainFrm.fromGroup.value;
    var url = "<%=AssetsURLList.GROUP_CHOOSE_SERVLET%>";
    url += "?transType=<%=transType%>";
    url += "&transferType=<%=transferType%>";
    var popscript = "dialogWidth:20;dialogHeight:10;center:yes;status:no;scrollbars:no;help:no";
    var group = window.showModalDialog(url, null, popscript);
    if (group) {
        dto2Frm(group, "mainFrm");
    }
}


/**
 * ���ܣ�������������š�������ʱ�������ò�����
 */
function do_ConfirmChange() {
    var rows = dataTable.rows;
    if (rows) {
        var rowCount = rows.length;
        var tr = rows[0];
        if (!(rows.length == 0 || (rows.length == 1 && tr.style.display == "none"))) {
            if (confirm("�ı䲿�Ž�����Ѿ�ѡ������ݣ��Ƿ����������������ȷ������ť������������ȡ������ť��")) {
                deleteRow(dataTable);
                srcDeptValue = mainFrm.fromDept.value;
                setCheckBoxState("mainCheck", false);
            } else {
                selectSpecialOptionByItem(mainFrm.fromDept, srcDeptValue);
            }
        }
    }
}


/**
 * ����:ѡ����ղ���
 */
function do_SelectDept(lineBox) {
    var toOrganizationId = mainFrm.toOrganizationId.value;
    if (toOrganizationId == "") {
        alert("����ѡ�������˾����ѡ�������β��š�");
        return;
    }
    var userPara = "organizationId=" + toOrganizationId + "&transferType=" + transferType;
    var deptCode = "";
    if (transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>") {
        deptCode = mainFrm.fromDept.value;
    }
    userPara += "&deptCode=" + deptCode;

    var objName = lineBox.name;
    var objId = lineBox.id;
    var idNumber = objId.substring(objName.length);
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_DEPT%>";
    var dialogWidth = 44;
    var dialogHeight = 30;
    var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    var deptChk = mainFrm.allDept;
    if (!deptChk.checked) {
        if (objs) {
            obj = objs[0];
            document.getElementById("responsibilityDept" + idNumber).value = obj["toDept"];
            lineBox.value = obj["toDeptName"];
        } else {
            lineBox.value = "";
            document.getElementById("responsibilityDept" + idNumber).value = "";
            document.getElementById("responsibilityUserName" + idNumber).value = "";
            document.getElementById("responsibilityUser" + idNumber).value = "";
            document.getElementById("assignedToLocationName" + idNumber).value = "";
            document.getElementById("assignedToLocation" + idNumber).value = "";
            document.getElementById("addressId" + idNumber).value = "";
        }
    } else {
        var obj = null;
        var emptyData = false;
        if (objs) {
            obj = objs[0];
        } else {
            emptyData = true;
        }
        var deptNames = document.getElementsByName("responsibilityDeptName");
        var depts = document.getElementsByName("responsibilityDept");
        var userNames = document.getElementsByName("responsibilityUserName");
        var users = document.getElementsByName("responsibilityUser");
        var addressNames = document.getElementsByName("assignedToLocationName");
        var addressNos = document.getElementsByName("assignedToLocation");
        var addressIds = document.getElementsByName("addressId");
        var count = addressNames.length;
        for (var i = 0; i < count; i++) {
            if (emptyData) {
                deptNames[i].value = "";
                depts[i].value = "";
                addressNames[i].value = "";
                addressNos[i].value = "";
                addressIds[i].value = "";
                userNames[i].value = "";
                users[i].value = "";
            } else {
                deptNames[i].value = obj["toDeptName"];
                depts[i].value = obj["toDept"];
            }
        }
    }
}

//ѡ�����ص�
function do_SelectLocation(lineBox) {
    var toOrganizationId = mainFrm.toOrganizationId.value;
    if (toOrganizationId == "") {
        alert("����ѡ�������˾����ѡ��ص㡣");
        return;
    }
    var deptCode = "";
    var objName = lineBox.name;
    var objId = lineBox.id;
    var idNumber = objId.substring(objName.length);
    <%--if (transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>") {
        deptCode = mainFrm.fromDept.value;
    } else {
        deptCode = document.getElementById("responsibilityDept" + idNumber).value;
        if (deptCode == "") {
            alert("����ѡ��������ţ���ѡ��ص㡣");
            var deptObj = document.getElementById("responsibilityDeptName" + idNumber);
            do_SelectDept(deptObj);
            return;
        }
    }--%>
    var userPara = "organizationId=" + toOrganizationId + "&transferType=" + transferType + "&deptCode=" + deptCode;

    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ADDRESS%>";
    var dialogWidth = 55;
    var dialogHeight = 30;
    var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    var addressChk = mainFrm.allLocation;
    if (!addressChk.checked) {
        if (objs) {
            var obj = objs[0];
            document.getElementById("assignedToLocation" + idNumber).value = obj["toObjectNo"];
            document.getElementById("addressId" + idNumber).value = obj["addressId"];
            lineBox.value = obj["toObjectName"];
        } else {
            document.getElementById("assignedToLocation" + idNumber).value = "";
            document.getElementById("addressId" + idNumber).value = "";
            lineBox.value = "";
        }
    } else {
        var obj = null;
        var emptyData = false;
        if (objs) {
            obj = objs[0];
        } else {
            emptyData = true;
        }
        var addressNames = document.getElementsByName("assignedToLocationName");
        var addressNos = document.getElementsByName("assignedToLocation");
        var addressIds = document.getElementsByName("addressId");
        var count = addressNames.length;
        for (var i = 0; i < count; i++) {
            if (emptyData) {
                addressNames[i].value = "";
                addressNos[i].value = "";
                addressIds[i].value = "";
            } else {
                addressNames[i].value = obj["toObjectName"];
                addressNos[i].value = obj["toObjectNo"];
                addressIds[i].value = obj["addressId"];
            }
        }
    }
}

//ѡ����������
function do_SelectPerson(lineBox) {
    var objName = lineBox.name;
    var objId = lineBox.id;
    var idNumber = objId.substring(objName.length);
    var deptCode = "";

    deptCode = mainFrm.toDept.value;
    		if(deptCode == ""){
    			alert("����ѡ��������ţ���ѡ����������");
    			return;
    		}

    var userPara = "organizationId=" + <%=userAccount.getOrganizationId()%> + "&deptCode=" + deptCode;
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_RECIIVER%>";
    var dialogWidth = 47;
    var dialogHeight = 30;
    var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    var userChk = mainFrm.allUser;
    if (!userChk.checked) {
        if (objs) {
            var obj = objs[0];
            document.getElementById("responsibilityUser" + idNumber).value = obj["employeeId"];
            lineBox.value = obj["userName"];
        } else {
            document.getElementById("responsibilityUser" + idNumber).value = "";
            lineBox.value = "";
        }
    } else {
        var obj = null;
        var emptyData = false;
        if (objs) {
            obj = objs[0];
        } else {
            emptyData = true;
        }
        var userNames = document.getElementsByName("responsibilityUserName");
        var users = document.getElementsByName("responsibilityUser");
        var count = userNames.length;
        for (var i = 0; i < count; i++) {
            if (emptyData) {
                userNames[i].value = "";
                users[i].value = "";
            } else {
                userNames[i].value = obj["userName"];
                users[i].value = obj["employeeId"];
            }
        }
    }
}

function do_SelectFaCategoryCode(lineBox) {
    var objName = lineBox.name;
    var objId = lineBox.id;
    var idNumber = objId.substring(objName.length);
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_FACAT_CODE%>";
    var dialogWidth = 54;
    var dialogHeight = 30;
    var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    var faCatChk = mainFrm.allFaCategory;
    if (!faCatChk.checked) {
        if (objs) {
            obj = objs[0];
            document.getElementById("faCategoryCode" + idNumber).value = obj["faCategoryCode"];
        } else {
            lineBox.value = "";
        }
    } else {
        var obj = null;
        if (objs) {
            obj = objs[0];
        } else {
            obj = new Object();
            obj.faCategoryCode = "";
            obj.faCategoryName = "";
        }
        var catCodes = document.getElementsByName("faCategoryCode");
        var count = catCodes.length;
        for (var i = 0; i < count; i++) {
            catCodes[i].value = obj["faCategoryCode"];
        }
    }
}


function do_SelectDepreciationAccount(lineBox) {
    var toOrganizationId = mainFrm.toOrganizationId.value;
    if (toOrganizationId == "") {
        alert("����ѡ�������˾����ѡ���������ˡ�");
        return;
    }
    var objName = lineBox.name;
    var objId = lineBox.id;
    var idNumber = objId.substring(objName.length);
    var deptCode = "";
    if (transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>") {
        deptCode = mainFrm.fromDept.value;
    } else {
        deptCode = document.getElementById("responsibilityDept" + idNumber).value;
        if (deptCode == "") {
            alert("����ѡ��������ţ���ѡ���۾��˻���");
            var deptObj = document.getElementById("responsibilityDeptName" + idNumber);
            do_SelectDept(deptObj);
            return;
        }
    }
    var userPara = "organizationId=" + toOrganizationId + "&accountCode2=" + deptCode;
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ACCOUNT%>";
    var dialogWidth = 51;
    var dialogHeight = 29;
    var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    var accountChk = mainFrm.allAccount;
    if (!accountChk.checked) {
        if (objs) {
            obj = objs[0];
            lineBox.value = obj["accountCode"];
        } else {
            lineBox.value = "";
        }
    } else {
        var obj = null;
        var emptyData = false;
        if (objs) {
            obj = objs[0];
        } else {
            emptyData = true;
        }
        var accounts = document.getElementsByName("depreciationAccount");
        var count = accounts.length;
        for (var i = 0; i < count; i++) {
            if (emptyData) {
                accounts[i].value = "";
            } else {
                accounts[i].value = obj["accountCode"];
            }
        }
    }
}


function validate() {
    var isValid = true;
    var transType = mainFrm.transType.value;
    var fieldLabels = "";
    var fieldNames = "";
    var validateType = "";
    if (transferType == "<%=AssetsDictConstant.TRANS_BTW_COMP%>") {
        var fieldLabels = "������˾;���빫˾;�ʲ�����;����˵��";
        var fieldNames = "fromOrganizationId;toOrganizationId;createdReason";
        var validateType = EMPTY_VALIDATE;
        isValid = formValidate(fieldNames, fieldLabels, validateType);
        if (isValid) {
            if (mainFrm.fromOrganizationId.value == mainFrm.toOrganizationId.value) {
                alert("��˾�����ʱ��������˾�͵��빫˾����Ϊͬһ��˾");
                isValid = false;
            }
        }
    } else {
        if (dataTable.rows.length == 0 || (dataTable.rows[0].style.display == 'none' && dataTable.rows.length == 1)) {
            alert("û��ѡ�������ݣ���ѡ�������ݣ�");
            isValid = false;
        } else {
            var fieldLabels = "˵��";
            var fieldNames = "createdReason";
            var validateType = EMPTY_VALIDATE;
            if (transferType) {
                if (transferType != "<%=AssetsDictConstant.TRANS_INN_DEPT%>") {
                    fieldLabels += ";���벿��";
                    fieldNames += ";responsibilityDeptName";
                } else {
                    fieldLabels += ";����ص�;��������";
                    fieldNames += ";assignedToLocationName;responsibilityUserName";
                }
            }
        }
        isValid = formValidate(fieldNames, fieldLabels, validateType);
        if (isValid) {
            var rows = dataTable.rows;
            if (transType == "<%=AssetsDictConstant.ASS_SUB%>" && rows[0].style.display != 'none') {
                var row = null;
                for (var i = 0; i < rows.length; i++) {
                    row = rows[i];
                    var barcode = getNodeObject(row, "barcode");
                    var deprnCost = getNodeObject(row, "deprnCost");
                    var prepareDevalue = getNodeObject(row, "prepareDevalue");
                    if (prepareDevalue.value >= deprnCost.value) {
                        alert("����Ƿ���ԭ���ǣ��ʲ���" + barcode.value + "���ġ������ֵ" + prepareDevalue.value + "�����ڻ���ڡ���ֵ" + deprnCost.value + "����")
                        deprnCost.focus();
                        isValid = false;
                    }
                }
            }
        }
    }
    return isValid;
}

function do_ShowDetail(td) {
    var transType = mainFrm.transType.value;
    tr = td.parentNode;
    cells = tr.cells;
    var cell = cells[1];
    if (transType == "<%=AssetsDictConstant.ASS_SUB%>") {
        cell = cells[3];
    }
    var barcode = cell.childNodes[0].value;
    var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
    var winName = "assetsWin";
    var style = "width=800,height=360,left=70,top=100";
    window.open(url, winName, style);
}

function do_CancelOrder() {
    if (confirm("����׼�����������ݣ�ȷ���𣿼���������ȷ������ť������������ȡ������ť!")) {
        mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
        mainFrm.submit();
    }
}

function do_SetLineTransDate(lineBox) {
    if (!mainFrm.allTransDate) {
        return;
    }
    if (!mainFrm.allTransDate.checked) {
        return
    }
    var id = lineBox.id;
    var lineDate = lineBox.value;
    var dateFields = document.getElementsByName("lineTransDate");
    for (var i = 0; i < dateFields.length; i++) {
        if (dateFields[i].id == id) {
            continue;
        }
        dateFields[i].value = lineDate;
    }
}

function do_ClearLineData() {
    var rows = dataTable.rows;
    if (rows) {
        var rowCount = rows.length;
        var tr = rows[0];
        if (!(rows.length == 0 || (rows.length == 1 && tr.style.display == "none"))) {
            var fieldNames = "responsibilityDeptName;assignedToLocationName;responsibilityUserName;depreciationAccount;faCategoryCode;assignedToLocation;responsibilityUser;responsibilityDept;addressId";
            if (!isAllEmapty(fieldNames)) {
                if (confirm("�ı���빫˾����������벿�š���������ص㡱�����������ˡ��������۾��˻�����������𡱵����ݣ��Ƿ����������������ȷ������ť������������ȡ������ť��")) {
                    clearFieldValue(fieldNames);
                } else {
                    selectSpecialOptionByItem(mainFrm.toOrganizationId, srcToOrgValue);
                }
            }
        }
    }
    srcToOrgValue = mainFrm.toOrganizationId.value;
}

var al = 0;
function do_exportToExcel() {
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}
function do_ChangeContentCode() {
    var rows = dataTable.rows;
    var rowCount = rows.length;
    if (rowCount > 1 || (rowCount == 1 && rows[0].style.display == "block")) {
        if (confirm("�ı��ʲ����ཫɾ���Ѿ�ѡ����ʲ����ݣ��Ƿ����������������ȷ������ť������������ȡ������ť")) {
            deleteRow(dataTable);
            contentCode = mainFrm.fromDept.value;
            setCheckBoxState("mainCheck", false);
        } else {
        }
    }
}
function initPage1() {
//    HideSinoButton(1);
//    HideSinoButton(3);
//    HideSinoButton(4);
//    HideSinoButton(6);
//    HideSinoButton(7);
//    HideSinoButton(8);
//    HideSinoButton(9);
//    HideSinoButton(10);
//    HideSinoButton(26);
//    HideSinoButton(27);
//    doLoad();
    window.focus();
    do_SetPageWidth();
}
function do_ChangeBookType(sel) {
    if (sel.value == "") {
        mainFrm.bookTypeName.value = "";
    } else {
        mainFrm.bookTypeName.value = getSpecialOptionText("bookTypeCode", sel.value);
    }
}
function do_Complete_app_yy() {
	if(true){
        try{
//			disabledBtn();
            var actObj = document.getElementById("act");
			actObj.value = "SUBMIT_ACTION";
            //setFrmEnable("mainFrm");
//			document.forms[0].action="/servlet/com.sino.ies.inv.ywypmgr.servlet.CtmsForwardServlet";
			document.forms[0].submit();
		}catch(ex){
			alert( ex.message );
		}finally{
			enableBtn();
		}
	}
}
function do_CancelOrder() {
	if(confirm("����׼�����������ݣ�ȷ���𣿼���������ȷ������ť������������ȡ������ť!")){
		mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
		mainFrm.submit();
	}
}
</script>