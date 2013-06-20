<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.system.house.dto.AmsHouseInfoDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.ams.system.house.dto.AmsHouseUsesDTO" %>
<%--
  User: Zyun
  Date: 2007-10-15
  Time: 13:30:49
--%>
<html>
<head><title>��������ά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
</head>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);

    AmsHouseUsesDTO dto = (AmsHouseUsesDTO) request.getAttribute("AMSHOUSEUSESDTO");
    DTOSet set = (DTOSet) request.getAttribute("AMSHOUSEUSESDTODETAIL");
    if (dto == null) {
        dto = new AmsHouseUsesDTO();
    }
    AmsHouseInfoDTO housedto = (AmsHouseInfoDTO) request.getAttribute(WebAttrConstant.AMS_HOUSE_INFO_DTO);
    String houseUsage = (String) request.getAttribute(WebAttrConstant.HOUSE_USAGE_OPTION);
    String houseStatus = (String) request.getAttribute(WebAttrConstant.HOUSE_STATUS_OPTION);
    String action = parser.getParameter("act");
    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    String barcode1 = userAccount.getCompanyCode() + '-';
    System.out.println("(housedto.getIsRent()" + housedto.getIsRent());
%>
<script type="text/javascript">
    printTitleBar("����������Ϣ-�ǻ�վ-���غ�һ")
</script>
<body  topMargin=0 leftMargin=0 onload="set_value()" >
<jsp:include page="/message/MessageProcess"/>
<form action="/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet" name="mainForm" method="post">
<table border="0" width="100%">
<tr>
    <td align="right" width="15%">���룺</td>
    <td width="35%" align="left">
        <input name="barcode" type="text" class="readonlyInput" readonly value="<%=housedto.getBarcode()%>" style="width:80%">
    </td>
    <td align="right" width="15%">����֤�ţ�</td>
    <td width="35%" align="left"><input type="text" name="houseCertificateNo" style="width:80%"
                                        value="<%=housedto.getHouseCertificateNo()%>">
    </td>
</tr>
<tr>
    <td align="right" width="15%">���ƣ�</td>
    <td width="35%" align="left"><input name="itemName" type="text" readonly class="readonlyInput"
                                        value="<%=housedto.getItemName()%>" style="width:80%">
    </td>
    <td align="right" width="15%">�������(�O)��</td>
    <td width="35%" align="left"><input name="houseArea" type="text" value="<%=housedto.getHouseArea()%>" style="width:80%"
                           onblur="do_verify2();"></td>
</tr>
<tr>
    <td align="right" width="15%">�ͺţ�</td>
    <td width="35%" align="left"><input name="itemSpec" type="text" readonly class="readonlyInput"
                                        value="<%=housedto.getItemSpec()%>" style="width:80%"></td>
    <%
        String yesChecked = "checked";
        String noChecked = "";
        if (housedto.getIsRent().equals("N")) {
            yesChecked = "";
            noChecked = "checked";
        }
    %>
    <td align="right">����֤�ţ�</td>
    <td><input type="text" name="landCertficateNo" style="width:80%" value="<%=housedto.getLandCertficateNo()%>"></td>
</tr>
<tr>
    <td align="right" width="15%">���ڵص㣺</td>                                                  
    <td width="35%" align="left"><input type="text" name="houseAddress" style="width:80%" class="noEmptyInput" readonly
                                        value="<%=housedto.getHouseAddress()%>"><a class="linka" style="cursor:'hand'"
                                                                                   onclick="do_selectAddress();">[��]</a>
    </td>
        <td align="right" width="15%" id="rent1">�������(�O)��</td>
        <td width="35%" id="rent2"><input type="text" name="occupyArea" style="width:80%"
                                        value="<%=housedto.getOccupyArea()%>" onblur ="do_verifyLandArea();"></td>
</tr>
<tr>
    <td align="right" width="15%">�������ʣ�</td>
    <td width="35%" align="left"><select name="landType" style="width:80%">
                    <option value= "">--��ѡ��--</option>
                    <option value="����" <%=housedto.getLandType().equals("����")? "selected":""%>>����</option>
                    <option value="����" <%=housedto.getLandType().equals("����")? "selected":""%>>����</option>
                    <option value="����" <%=housedto.getLandType().equals("����")? "selected":""%>>����</option>
                </select></td>
    <td align="right" width="15%" ></td>
    <td width="35%"  rowspan ="5"><textarea name = "hremark" cols="30" rows="4"><%=housedto.getHremark()%></textarea> </td>
</tr>
<tr>
    <td width="15%" align="right" height="22"><font color="" size="2"><b>����ļ���</b></font></td>
    <td width="35%"><select size="2" multiple style="width:80%" name="affix" id="affix"
                            ondblclick="onaffix(); return false;">
        <%=request.getAttribute(WebAttrConstant.ATTACH_FILES)%>
    </select><img src="/images/eam_images/attach.jpg" alt="�����ļ�" onClick="do_AttachFiles()">&nbsp;<img
            src="/images/eam_images/delete.jpg" alt="ɾ���ļ�" onclick="remove_AttachFiles()"></td>
    <td align="right" id="rent9">��ע��</td>
    <td width="35%" id="rent10"></td>
</tr>
</table>
<fieldset>
    <legend><font color="#0033FF" ><b>������;��Ϣ</b></font>
        <%--<img src="/images/button/addLine.gif" alt="�����" onclick=" do_addLine();" >--%>

        <%--<input type="button" name="" value="�����" onclick=" do_addLine();" class=button2>--%>
    </legend>
    <table id=tb_order width="100%" border="1">
    <%
        if (set == null || set.isEmpty()) {
    %>
        <tr>
            <td width=16% align="right">��;��</td>
            <td width=16%><select style="width:100%" name="usage"><option value="BUSINESS" >Ӫҵ</option>
            </select></td>
            <td align="right" width=16%>���(�O)��</td>
            <td width=16% align="left">
                <input type="text" name="area" value="<%=dto.getArea()%>">
            </td>
            <td align="right" width=16%>��ע��</td>
            <td width=16% align="left">
                <input type="text" name="remark" value="<%=dto.getRemark()%>">
            </td>
            <td style="display:none">
                <input type="hidden" name="usage1" value="<%=dto.getUsage()%>">
            </td>
        </tr>
        <tr>
            <td width=16% align="right">��;��</td>
            <td width=16%><select style="width:100%" name="usage"><option value="PRODUCE" >����</option>
            </select></td>
            <td align="right" width=16%>���(�O)��</td>
            <td width=16% align="left">
                <input type="text" name="area" value="<%=dto.getArea()%>" onblur="do_area();">
            </td>
            <td align="right" width=16%>��ע��</td>
            <td width=16% align="left">
                <input type="text" name="remark" value="<%=dto.getRemark()%>">
            </td>
            <td style="display:none">
                <input type="hidden" name="usage1" value="<%=dto.getUsage()%>">
            </td>
        </tr>
        <tr>
            <td width=16% align="right">��;��</td>
            <td width=16%><select style="width:100%" name="usage"><option value="WORK" >�칫</option>
            </select></td>
            <td align="right" width=16%>���(�O)��</td>
            <td width=16% align="left">
                <input type="text" name="area" value="<%=dto.getArea()%>" onblur="do_area();">
            </td>
            <td align="right" width=16%>��ע��</td>
            <td width=16% align="left">
                <input type="text" name="remark" value="<%=dto.getRemark()%>">
            </td>
            <td style="display:none">
                <input type="hidden" name="usage1" value="<%=dto.getUsage()%>">
            </td>
        </tr>
        <tr>
            <td width=16% align="right">��;��</td>
            <td width=16%><select style="width:100%" name="usage"><option value="OTHERS" >����</option>
            </select></td>
            <td align="right" width=16%>���(�O)��</td>
            <td width=16% align="left">
                <input type="text" name="area" value="<%=dto.getArea()%>" onblur="do_area();">
            </td>
            <td align="right" width=16%>��ע��</td>
            <td width=16% align="left">
                <input type="text" name="remark" value="<%=dto.getRemark()%>">
            </td>
            <td style="display:none">
                <input type="hidden" name="usage1" value="<%=dto.getUsage()%>">
            </td>
        </tr>

    <%
    } else {
        for (int i = 0; i < set.getSize(); i++) {
            AmsHouseUsesDTO dto1 = (AmsHouseUsesDTO) set.getDTO(i);
    %>
        <tr id="mainTr<%=i%>">
            <td width=16% align="right">��;��</td>
            <td width=16%><select style="width:100%" id="usage<%=i%>" name="usage"><%=houseUsage%>
            </select></td>
            <td align="right" width=16%>���(�O)��</td>
            <td width=16% align="left">
                <input type="text" name="area" value="<%=dto1.getArea()%>" onblur="do_area();">
            </td>
            <td align="right" width=16%>��ע��</td>
            <td width=16% align="left">
                <input type="text" name="remark" value="<%=dto1.getRemark()%>">
            </td>
            <td style="display:none">
                <input type="hidden" name="usage1" value="<%=dto1.getUsage()%>"><input type="hidden" name="id" value="<%=i%>">
            </td>
        </tr>
    <% }
    }%>
        </table>
</fieldset>
<table width="100%" border="0">
    <tr>
        <td align="center" width="15%"></td>
        <td width="35%"> &nbsp;&nbsp;&nbsp;
               <%if(!housedto.getTemp().equals("hased")){%>
            <img src="/images/eam_images/tmp_save.jpg" alt="�ݴ�" onclick="do_temp();return false;"> &nbsp;
            <%}%>
            <img src="/images/eam_images/save.jpg" alt="������Ϣ"
                 onclick="do_save();" <%--onClick="do_save(); return false;"--%>> &nbsp;
            <img src="/images/eam_images/close.jpg" alt="����" onclick="do_Back();return false;"></td>
    </tr>
</table>
<input type="hidden" name="act" value="<%=action%>">
<input type="hidden" name="isExist">
<input type="hidden" name="itemCode" value="<%=housedto.getItemCode()%>">
<input type="hidden" name="systemId" value="<%=housedto.getSystemId()%>">
<input type="hidden" name="has" value="<%=housedto.getHas()%>">
<input type="hidden" name="rentId" value="<%=housedto.getRentId()%>">
<input type="hidden" name ="officeUsage" value="�ǻ�վ">
<input type="hidden" name ="officeType" value="���غ�һ">
<input type="hidden" name="addressId">
<input type="hidden" name="saved">
<input type="hidden" name="isAttachFile" value="<%=parser.getAttribute(WebAttrConstant.ATTACH_FILE_ATTR)%>">
</form>
</body>
<%--<iframe name="downTarget" src="" style="display:none"></iframe>--%>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script type="text/javascript">
function do_save() {
    var barcode1 = "<%=barcode1%>";
    var barcode = document.mainForm.barcode.value;
        var fieldNames = "barcode;houseAddress";
        var fieldLabels = "����;���ڵص�";
        var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
        var systemId = mainForm.systemId.value;
      if(isValid){
        var has = mainForm.has.value;
            selectAll("affix");
             if (mainForm.barcode.value == "") {
                    mainForm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
                } else {
                    mainForm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
                }
                mainForm.action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet";
                window.top.opener.do_search();
                mainForm.submit();
}
   }

function do_temp() {
//   if(changed()){
    <%--var barcode1 = "<%=barcode1%>";--%>
    var barcode = document.mainForm.barcode.value;
//    var rety = do_check();
//    if (rety) {
        var fieldNames = "barcode;itemSpec";
        var fieldLabels = "����;�ͺ�";
        var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
        var systemId = mainForm.systemId.value;
        var has = mainForm.has.value;
           if(isValid){
            selectAll("affix");

           mainForm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
           mainForm.action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet?temp=Y";
           window.top.opener.do_search();
           mainForm.submit();
        <%--}else{--%>
       <%--if (confirm("�ı䡰��վ���͡������ط������͡����������ݶ�ʧ��ȷ����")) {--%>
           <%--var barcode = document.mainForm.barcode.value;--%>
           <%--var fieldNames = "barcode;itemSpec";--%>
           <%--var fieldLabels = "����;�ͺ�";--%>
           <%--var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);--%>
           <%--var systemId = mainForm.systemId.value;--%>
           <%--var has = mainForm.has.value;--%>
           <%--selectAll("affix");--%>
           <%--mainForm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";--%>
           <%--mainForm.action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet?temp=Y";--%>
           <%--mainForm.submit();--%>
       <%--}--%>
      }
}


function  changed(){
    var retVal = false;
    var bts = mainForm.bts.value;
    var groupId = mainForm.category.value;
    if((bts=="�ǻ�վ")&&(groupId=="���غ�һ")){
        retVal = true;
    }
    return retVal;
}

//function selectAll(dest, isSelectAll) {
//    alert(dest.options.length);
//    for (var i = 0; i < dest.options.length; i++) {
//
//        dest.options[i].selected = isSelectAll;
//
//    }
//}


function do_select() {
    selectAll("affix");
    //   selectAll(document.mainForm.affix, true);
    //}
}
function do_Back() {
//    with (mainForm) {
//        act.value = "";
////        action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet";
//        action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet";
//        //        mainForm.target = "_self";
//        submit();
//    }
      window.close();
}


function do_SelectSystemItem() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_HOUSE%>";
    var dialogWidth = 35;
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

function do_AttachFiles() {
    //    if (mainForm.systemId.value == "") {
    //        var fieldNames = "barcode;itemSpec";
    //        var fieldLabels = "����;�ͺ�";
    //        var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
    //        if (isValid) {
    //            mainForm.isAttachFile.value = "true";
    //            do_save();
    //        }
    //    } else {
<%--var url = "/servlet/com.sino.ams.system.house.servlet.AmsItemFilesServlet?act=<%=WebActionConstant.NEW_ACTION%>&barcode=<%=housedto.getBarcode()%>"+ "&type=HOUSE";--%>
    //        var style = "height=300,width=400,top=200,left=400";
    //        var winName = "uploadWin";
    //        window.open(url, winName, style);
    //    }
    if (document.mainForm.barcode.value !== "") {
        //        alert(document.mainForm.barcode.value);
        var affix = document.getElementById("affix");
        var url = "/system/house/uploadItemFile.jsp?barcode=" + document.mainForm.barcode.value;
        var retVal = window.showModalDialog(url, "", "dialogWidth:20;dialogHeight:13;center:yes;status:no;scrollbars:no;help:no");
        if (retVal) {
            //            alert(retVal);
            var arr = retVal.split("$");
            //            alert(arr[0]);
            //            alert(arr[1]);
            if (cf1(arr[1])) {
                //                alert(999999999999999999999999999999999999999);
                var option = new Option(arr[0], retVal);
                affix.add(option)
            }
            //            var option = new Option(arr[0], retVal);
            //            affix.add(option)
        }
    } else {
        alert("���ȡ����ɡ����룡");
    }
}

function remove_AttachFiles() {
    //   var affix = document.getElementById("affix");
    dropSelectedOption("affix");
}

function do_DownLoad(filePath) {

    var url = "/servlet/com.sino.ams.system.house.servlet.AmsItemFilesServlet?act=<%=WebActionConstant.DOWNLOAD_ACTION%>&filePath=" + filePath;
    mainForm.action = url;
    //    mainForm.target = "downTarget";
    mainForm.submit();
}

function initPage() {
    var attachFile = mainForm.isAttachFile.value;
    if (attachFile == "true") {
        var url = "/servlet/com.sino.ams.system.house.servlet.AmsItemFilesServlet?act=<%=WebActionConstant.NEW_ACTION%>&barcode=<%=housedto.getBarcode()%>" + "&type=HOUSE";
        var style = "height=300,width=400,top=200,left=400";
        var winName = "uploadWin";
        window.open(url, winName, style);
    }
}


//function do_verify(obj) {
//    var barcode = obj.value ;
////    alert(barcode);
//    if (barcode.indexOf("-") != -1) {
//    } else {
//        alert("���������-��������");
//        obj.focus();
//        return false;
//    }
//}


function do_verify1() {
    var floorNumber = mainForm.floorNumber.value;
    var fieldNames = "floorNumber";
    var fieldLabels = "¥��";
    if (!formValidate(fieldNames, fieldLabels, NUMBER_VALIDATE)) {
        //        alert("¥�����Ϊ���֣�");
    } else if (floorNumber > 150) {
        alert("¥�㲻����Ϊ��" + floorNumber + "���ɣ�")
    }
}

function do_verify2() {
    var fieldNames = "houseArea";
    var fieldLabels = "���";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
        //        alert("�������Ϊ�����֣�");
    }
}


function do_verifyLandArea() {
    var fieldNames = "occupyArea";
    var fieldLabels = "�������";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
        //        alert("�������Ϊ�����֣�");
    }
}

function do_verify6() {
    var fieldNames = "businessArea";
    var fieldLabels = "Ӫҵ�����";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
        //        alert("�������Ϊ�����֣�");
    }
}

function do_verify4() {
    var fieldNames = "produceHosuseArea";
    var fieldLabels = "�������������";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
        //        alert("�������Ϊ�����֣�");
    }
}

function do_verify5() {
    var fieldNames = "produceBaseArea";
    var fieldLabels = "������վ�����";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
        //        alert("�������Ϊ�����֣�");
    }
}

function do_verify7() {
    var fieldNames = "officeArea";
    var fieldLabels = "�칫�����";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
        //        alert("�������Ϊ�����֣�");
    }
}
function do_verify3() {
    var fieldNames = "rental";
    var fieldLabels = "���";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
        //        alert("������Ϊ�����֣�");
    }
}


function do_area() {
    var fieldNames = "area";
    var fieldLabels = "���";
    if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
        //        alert("�������Ϊ�����֣�");
    }
}

function do_verify9() {
    var barcode1 = "<%=barcode1%>";
    var barcode = document.mainForm.barcode.value;
    var barcode2 = barcode.substring(0, 5);
    //    alert(barcode1) ;
    //    alert(barcode2);
    if (barcode2 == barcode1) {
        var barcode3 = barcode.substring(5);
        //        alert(barcode3);
        //        alert(barcode3.length);
        if (barcode3.length == 8) {
            //            alert(barcode3.length);
            for (var i = 0; i < barcode3.length; i++)
            {
                if (barcode3.charAt(i) < '0' || barcode3.charAt(i) > '9')
                {
                    alert("��" + barcode1 + "������8λֻ���ǡ����֡���");
                    document.mainForm.barcode.focus();
                    return false;
                }
            }

            //                 alert(isNaN(barcode3));
            //            if (isNaN(barcode3)) {
            //
            //            } else {
            //                alert("����"+barcode1+"������8λ�����֡���");
            ////                document.mainForm.barcode.focus();
            //                return false;
            //            }
        } else {
            alert("����" + barcode1 + "�����롰8λ�������֣�");
            document.mainForm.barcode.focus();
            return false;
        }
    } else {
        alert("������" + barcode1 + "����8λ���֣�");
        document.mainForm.barcode.focus();
        return false;

    }
}

//           var fieldNames = "barcode";
//    var fieldLabels = "����";
//    if (!formValidate(fieldNames, fieldLabels, POSITIVE_INT_VALIDATE)) {
//    } else {
//        var fieldNames = "barcode";
//        var fieldLabels = "0$����7$";
//        if (!formValidate(fieldNames, fieldLabels, LENGTH_VALIDATE)) {
//        }
//    }
var xmlHttp;
function do_verifybarcode() {
    //    alert(document.mainForm.barcode.value);
    do_verify9()
    var url = "";
    var segment1 = document.mainForm.barcode.value;
    createXMLHttpRequest();
    if (document.mainForm.barcode.value) {
        url = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet?act=verifyBarcode&barcode=" + document.mainForm.barcode.value;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}


function handleReadyStateChange1() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            if (xmlHttp.responseText == 'Y') {
                document.mainForm.isExist.value = 'Y';
                document.getElementById("barcode11").style.visibility = "visible"
                document.mainForm.barcode.focus();
            } else {
                document.mainForm.isExist.value = 'N';
                document.getElementById("barcode11").style.visibility = "hidden";
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}

function do_Selectbarcode() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_BARCODENO%>";
    var dialogWidth = 51;
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


function do_show() {
    var show = document.mainForm.isRent
    for (var i = 0; i < show.length; i++) {
        if (show[0].checked) {
             document.getElementById("rent1").style.display="" ;
             document.getElementById("rent2").style.display="" ;
             document.getElementById("rent3").style.display="" ;
             document.getElementById("rent4").style.display="" ;
             document.getElementById("rent5").style.display="" ;
             document.getElementById("rent6").style.display="" ;
             document.getElementById("rent7").style.display="" ;
             document.getElementById("rent8").style.display="" ;
             document.getElementById("rent9").style.display="" ;
             document.getElementById("rent10").style.display="" ;
            document.getElementById("rent").style.visibility = "visible";
        } else {
             document.getElementById("rent1").style.display="none" ;
             document.getElementById("rent2").style.display="none" ;
             document.getElementById("rent3").style.display="none" ;
             document.getElementById("rent4").style.display="none" ;
             document.getElementById("rent5").style.display="none" ;
             document.getElementById("rent6").style.display="none" ;
             document.getElementById("rent7").style.display="none" ;
             document.getElementById("rent8").style.display="none" ;
             document.getElementById("rent9").style.display="none" ;
             document.getElementById("rent10").style.display="none" ;
            document.getElementById("rent").style.visibility = "hidden"
        }
    }


}


function do_Selectbarcode() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_BARCODENO%>";
    var dialogWidth = 51;
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

function do_selectAddress() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_ASSETS_ADDRESS%>";
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

function onaffix() {
    var filePathm = document.getElementById("affix").value ;
    var filePath = filePathm.split("$");
    //      alert(filePath[0]);
    //      alert(filePath[1]);
  if(filePath[1]){
    do_DownLoad(filePath[1]);
   }
}

function addBarcode() {
    do_creatBarcode();
}

var xmlHttp;
function do_creatBarcode() {
    var url = "";
    xmlHttp = createXMLHttpRequest();
    url = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet?act=creatBarcode";
    xmlHttp.onreadystatechange = handleReadyStateChange1;
    xmlHttp.open("post", url, true);
    xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlHttp.send(null);
}

function handleReadyStateChange1() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            unescape(xmlHttp.responseText);
            document.mainForm.barcode.value = xmlHttp.responseText;
        } else {
            alert(xmlHttp.status);
        }
    }
}

function cf1(pathvalue) {
    var retVal = true;
    var values = new Array();
    var affix1 = document.getElementById("affix");
    for (var i = 0; i < affix1.length; i++) {
        var paths = (affix1.options[i].value);
        var path = paths.split("$");
        values[i] = path[1];
    }
    //     alert(values);
    for (var j = 0; j < values.length; j++) {
        if (values[j] == pathvalue) {
            alert("�����е��ļ�������ͬ��");
            retVal = false;
        }
    }
    return retVal;
}

function do_check() {
    var ret = true;
    var rentPerson = document.mainForm.rentPerson.value;
    var show = document.mainForm.isRent;
    for (var i = 0; i < show.length; i++) {
        if ((show[0].checked) && (rentPerson == "")) {
            //               alert("�������ر����������ˣ�");
            ret = false;
        }
    }
    //    alert(ret);
    return ret;
}
function set_value() {
    var tbObj = document.getElementById("tb_order");
    var rows = tbObj.rows;
    var rowlength = rows.length;
    var row ;
    var usage ;
    var selObj;
    for (var i = 0; i < rowlength; i++) {
        row = rows[i];
        usage = row.cells[6].childNodes[0].value;
        selObj = row.cells[1].childNodes[0];
        if (usage != "") {
            selectSpecialOptionByItem(selObj, usage);
        }
    }
}


function do_addLine() {
    var rowCount = document.getElementById("tb_order").rows.length - 1 ;
    if (rowCount < 6) {
        var tbObj = document.getElementById("tb_order");
        var rs = tbObj.rows;
        var count = rs.length
        var row0 = rs[count - 1]
        var newRow = row0.cloneNode(true);
        row0.appendChild(newRow)
    } else {
        alert("�������6��");
    }

<%--if (document.mainForm.barcode.value == "") {
    alert("������������");
}
else {
    //������
    var rowCount = tb_order.rows.length - 1 ;

    if (rowCount < 4) {
        //���һ��
        var newTr = tb_order.insertRow(rowCount);

        //�������
        var newTd0 = newTr.insertCell();
        var newTd1 = newTr.insertCell();
        var newTd2 = newTr.insertCell();
        var newTd3 = newTr.insertCell();

        //���������ݺ�����
        newTd0.width = "16%";
        newTd1.width = "16%";
        newTd2.width = "16%";
        newTd3.width = "16%";

        newTd0.innerHTML = /*<OPTION VALUE = PO_NUM@@@PO_HEADER_ID >*/
//                        "<select class = select2 name = houseUsage id = houseUsage" + rowCount +
//                               "  ><OPTION>--��ѡ��--</OPTION><OPTION>�칫</OPTION></select>";
               "<select  class = select2 name = houseUsage id = houseUsage" + rowCount +"><%=houseUsage%></select>";
        newTd1.innerHTML = '<input style="width:100%"  type = "text" name = "houseArea" id = "houseArea' + rowCount + '" value = "" class = input2>';
        newTd2.innerHTML = '<input  style="width:100%" type = "text" name = "areaUnit" id = "areaUnit' + rowCount + '" value = "" class = input2>';
        newTd3.innerHTML = '<input  style="width:100%" type = "text" name = "remark" id = "remark' + rowCount + '" value = "" class = input2>';
    } else {
        alert("�������4��");
    }
    //���¶����б�
  //  do_getPoListOption(rowCount);
}--%>
}
var focusRow = 0;
function do_getPoListOption(i) {

    focusRow = i;
    requestAjax("", do_setPoListOption, null, null);

}
</script>