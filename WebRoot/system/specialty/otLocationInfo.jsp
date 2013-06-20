<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.system.project.dto.EtsPaProjectsAllDTO" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.calen.SimpleDate" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2007-10-11
  Time: 17:31:35
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>�ص���Ϣά��</title>
     <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
     <script language="javascript" src="/WebLibary/js/Constant.js"></script>
     <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
     <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
     <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
     <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
     <script language="javascript" src="/WebLibary/js/calendar.js"></script>
     <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
     <script language="javascript" src="/WebLibary/js/jslib.js"></script>
     <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
</head>

<%
    EtsObjectDTO spotDTO = (EtsObjectDTO) request.getAttribute(WebAttrConstant.ETS_OBJECT_DTO);
    String countyCode = (String) request.getAttribute(WebAttrConstant.COUNTY_OPTION);
    String areaType = (String) request.getAttribute(WebAttrConstant.AREA_OPTION);
    String isall = (String) request.getAttribute(WebAttrConstant.CHECK_OPTION);
%>
<script type="text/javascript">
    printTitleBar("�����ص�ά��")
</script>
<body  leftmargin="0">
<jsp:include page="/message/MessageProcess"/>
<form action="" method="post" name="mainFrm">
    <input type="hidden" name="act">
    <input type="hidden" name="isExist">
    <input type= "hidden" name= "isTempAddr" value = "0">
    <table width="60%" align="center">
        <tr align="center" id="barcodeNo11" style="color:red;visibility:hidden">�Բ��𣬸õص���Ѵ���!</tr>
        <tr>
            <td width="8%" align="right">�ص��ţ�</td>
            <td width="40%"><input  name="workorderObjectCode" type="text"   id="workorderObjectCode" value="<%=spotDTO.getWorkorderObjectCode()%>" class='noEmptyInput' style="width:80%"
                      <%
    if (spotDTO.getWorkorderObjectNo().equals("")) {
//        if (StrUtil.isEmpty(spotDTO.getDisableDate())) {
//            System.out.println("spotDTO.getDisableDate() = " + spotDTO.getDisableDate());
%>
                                    onblur="do_verifyworkNo();"
    <%}else{%>
            readonly
            <%}%>
                    ></td>
        </tr>
        <tr>
            <td align="right">�ص��ƣ�</td>
            <td><input name="workorderObjectName" type="text" id="workorderObjectName" value="<%=spotDTO.getWorkorderObjectName()%>" class='noEmptyInput' style="width:80%"></td>
        </tr>

        <tr>
            <td align="right">���ڵص㣺</td>
            <td><input name="workorderObjectLocation" type="text" id="workorderObjectLocation" value="<%=spotDTO.getWorkorderObjectLocation()%>"  style="width:80%" class="noEmptyInput"></td>
        </tr>

        <tr>
            <td align="right">�������أ�</td>
            <td>
                <select class='noEmptyInput' name="countyCode" style="width:80%"><%=countyCode%>
                        </select>
            </td>
        </tr>
        <tr>
            <td align="right">������ࣺ</td>
            <td>
                <select class='noEmptyInput' name="areaType" style="width:80%"><%=areaType%>
                        </select>
            </td>
        </tr>
        <tr>
            <td align="right">�������̣�</td>
            <td>
               <input name="projectName" readonly class = "readonlyInput" type="text" id="projectName" value="<%=spotDTO.getProjectName()%>"  style="width:80%"><a href= "#" onClick="choosePrj()" title = "���ѡ�񹤳�" class="linka" >[��]</a>
            </td>
        </tr>
        <tr>
            <td align="right">Ѳ��ģʽ��</td>
            <td>
               <select name="isall" style="width:80%"><%=isall%></select>
            </td>
        </tr>
        <tr>
            <td align="right">��ע��</td>
            <td>
                <textarea name="remark"  type="areatext" id="remark" value="<%=spotDTO.getRemark()%>"  style="width:80%"><%=spotDTO.getRemark()%></textarea>
            </td>
        </tr>
        <tr>
            <td align="center" height="22" colspan="3">
        <img src="/images/eam_images/save.jpg" alt="����" style="cursor:'hand'" onClick="do_submit();">
<%
    if (!spotDTO.getWorkorderObjectNo().equals("")) {
        if (StrUtil.isEmpty(spotDTO.getDisableDate())) {
            System.out.println("spotDTO.getDisableDate() = " + spotDTO.getDisableDate());
%>
    <%--<img src="/images/eam_images/disable.jpg" onClick="do_delete(); return false;" alt="ʧЧ" id="delete">--%>
<%
       } else{
%>
  <img src="/images/button/efficient.gif" onClick="do_efficient(); return false;" alt="��Ч" id="delete">
<%
      }
    }
%>
     <img src="/images/eam_images/back.jpg" onClick="do_back();" alt="����">
            </td>
        </tr>
    </table>
<input name="workorderObjectNo" type="hidden" id="workorderObjectNo" value="<%=spotDTO.getWorkorderObjectNo()%>">
<input name="projectId" type="hidden" id="projectId" value="<%=spotDTO.getProjectId()%>">
<%--<input name="categoryName" type="hidden" id="categoryName" value="<%=spotDTO.getCategoryName()%>">--%>
<input type="hidden" name="objectCategory" value="<%=spotDTO.getObjectCategory()%>">
<input type = "hidden" name = "locationCode" value = "">
</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">
function do_back() {
    mainFrm.action = "/servlet/com.sino.ams.system.specialty.servlet.OtLocsVindicateServlet";
    mainFrm.submit();
}

function do_submit() {
        var fieldNames = "workorderObjectCode;workorderObjectName;workorderObjectLocation;countyCode;areaType";
        var fieldLabels = "�ص���;�ص���;���ڵص�;��������;��������";
        if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)){
            var locationCode = document.mainFrm.countyCode.value+"."+document.mainFrm.workorderObjectCode.value+".000";
            document.mainFrm.locationCode.value =  locationCode;
                 with(mainFrm){
		         act.value = "<%=WebActionConstant.CREATE_ACTION%>";
		         if(workorderObjectNo.value!=""){
			     act.value="<%=WebActionConstant.UPDATE_ACTION%>";
		                }
		action="/servlet/com.sino.ams.system.specialty.servlet.OtLocsVindicateServlet";
		submit();
                   }
         }
}

function choosePrj() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_PROJECT2%>";
    var dialogWidth = 50.6;
    var dialogHeight = 30;
    var projects = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if(projects){
        dto2Frm(projects[0], "mainFrm");
    }
//    if (projects) {
//            var project = null;
//            for (var i = 0; i < projects.length; i++) {
//                project = projects[i];
//                dto2Frm(project, "mainFrm");
//            }
//        }
}


function do_delete() {
    document.mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
    document.mainFrm.action = "/servlet/com.sino.ams.system.specialty.servlet.OtLocsVindicateServlet";
    document.mainFrm.submit();
}

function do_efficient(){
    document.mainFrm.act.value = "<%=AMSActionConstant.INURE_ACTION%>";
    document.mainFrm.action = "/servlet/com.sino.ams.system.specialty.servlet.OtLocsVindicateServlet";
    document.mainFrm.submit();
}

var xmlHttp;
function do_verifyworkNo() {
    var url = "";
//    var workorderObjectCode = document.form1.workorderObjectCode.value;
    createXMLHttpRequest();
    if (document.mainFrm.workorderObjectCode.value) {
        url = "/servlet/com.sino.ams.system.basepoint.servlet.EtsObjectServlet?act=verifyworkNo&workorderObjectCode=" + document.mainFrm.workorderObjectCode.value;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}



function do_contrancode() {
    var countrycode22 = document.mainFrm.countyCode.value+"."+document.mainFrm.workorderObjectCode.value+".000";
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
                document.mainFrm.isExist.value = 'Y';
                document.getElementById("barcodeNo11").style.visibility = "visible"
                document.mainFrm.workorderObjectCode.style.color = "red";
                document.mainFrm.workorderObjectCode.focus();
            } else {
                document.mainFrm.isExist.value = 'N'; //black
                document.mainFrm.workorderObjectCode.style.color = "black";
                document.getElementById("barcodeNo11").style.visibility = "hidden";
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}

</script>