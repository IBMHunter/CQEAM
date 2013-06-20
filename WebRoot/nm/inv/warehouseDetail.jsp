<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.nm.ams.inv.common.constant.CommonWebAttrConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page contentType="text/html;charset=GB2312" language="java" %>

<%--
  created by YSB
  Date: 2008-12-10
  Time: 17:33:30
--%>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<HTML>
<head>
    <title>�༭�ֿ���Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
</head>

<body TEXT="000000" BGCOLOR="FFFFFF" leftmargin=0 topmargin=0 class="STYLE1">
<%
    EtsObjectDTO warehouseDTO = (EtsObjectDTO) request.getAttribute(WebAttrConstant.WAREHOUSE_ATTR);
    String countyCode = (String) request.getAttribute(WebAttrConstant.COUNTY_OPTION);
    String warehouseType = (String) request.getAttribute(WebAttrConstant.WAREHOUSE_TYPE_OPTION);
    String busihouseType = (String) request.getAttribute(WebAttrConstant.BUSIHOUSE_TYPE_OPTION);
    String areaType = (String) request.getAttribute(CommonWebAttrConstant.AREA_TYPE_OPTION);
     String cityName =(String)request.getAttribute("CITY_NAME");
    String areaName=(String)request.getAttribute("AREA_NAME");
    String detail = (String)request.getAttribute("DETAIL_ACTION");
    String locationName = "";
    
    if(!("".equals(detail) || detail == null)){
    	String workorderObjectLocation = warehouseDTO.getWorkorderObjectLocation();
        String[] locationNames = workorderObjectLocation.split("\\.");
        //locationName = locationNames[1];
        locationName = locationNames[0];
    }
  
    RequestParser parser = new RequestParser();
    parser.transData(request);
%>

<form name="mainFrm" method="post">
    <jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>" flush="true"/>
    <table border="0" width="100%" style="position:absolute;top:20px">
    	<%
    		if(!("".equals(detail) || detail == null)){
    	%>
        <tr>
            <td width="25%" align="right" height="22">�ֿ���룺</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="workorderObjectCode" size="40" class="noemptyInput" style="width:100%"
                       value="<%=warehouseDTO.getWorkorderObjectCode()%>" readonly="readonly">
            </td>
            <!--  
            <td width="" align="left" height="22"><div id="tipMsg" style="visibility:hidden"><font color="red">�ֿ�����Ѵ���</font></div></td>
            -->
        </tr>
        <%} %>
        <tr>
            <td width="25%" align="right" height="22">�ֿ����ƣ�</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="workorderObjectName" size="40" class="noemptyInput" style="width:100%"
                       value="<%=warehouseDTO.getWorkorderObjectName()%>">
            </td>
            <td width=" " align="left" height="22"></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�ֿ����</td>
            <td width="50%" align="left" height="22" colspan="3">
                <select style="width:100%" type="text" name="objectCategory" id="objectCategory" onchange="checkFields()"><%=warehouseType%>
                </select></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">���ڵص㣺</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="locationName" size="40" class="noemptyInput" style="width:100%"
                       value="<%=locationName%>" onblur="changeText();"></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">Mis�ص㣺</td>
            <td width="50%" align="left" height="22" colspan="3">
                <input type="text" name="workorderObjectLocation" size="40" class="noemptyInput" style="width:100%"
                       value="<%=warehouseDTO.getWorkorderObjectLocation()%>" readonly="readonly"></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�����ɱ����ģ�</td>
            <td width="50%" align="left" height="22" colspan="3">
                <select style="width:100%" type="text" name="countyCode" id="sc" class="noemptyInput" onchange="returnCountyCode(this);"><%=countyCode%>
                </select></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
        	<td width="25%" align="right" height="22">ҵ�����ͣ�</td>
            <td width="50%" align="left" height="22" colspan="3">
                <select style="width:100%" type="text" name="businessCategory" id="businessCategory" class="noemptyInput" onchange="checkFields()"><%=busihouseType%>
                </select></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
        	<td width="25%" align="right" height="22">�������ͣ�</td>
            <td width="50%" align="left" height="22" colspan="3">
                <select style="width:100%" type="text" name="areaType" class="noemptyInput"><%=areaType%>
                </select></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
        <tr>
            <td align="right">���ڵ��У�</td>
            <td><select class='noEmptyInput' name="cityId" onchange="getAreaOption();" style="width:80%"><%=cityName%></select></td>
        </tr>
     	<tr>
            <td align="right">��������</td>
            <td><div id="area_id1"><select name="areaId" style="width:80%" class='noEmptyInput'><%=areaName%></select></div></td>
        </tr>
        <tr>
        <td></td>
        <td width="" align="center" height="22"><div id="tipMsg" style="visibility:hidden"><font color="red">һ����˾ֻ������һ�������Ǳ�������</font></div></td>
        </tr>
        <tr>
            <td width="100%" align="center" height="22" colspan="5">
                <img src="/images/button/save.gif" alt="����" onclick="do_submit();">&nbsp;
                <%--<%
                    if (!warehouseDTO.getWorkorderObjectNo().equals("")) {
                        if (StrUtil.isEmpty(warehouseDTO.getDisableDate())) {
                %>
                <img src="/images/button/disabled.gif" onClick="do_delete(); return false;" alt="ʧЧ" id="delete">
                <%
                } else {
                %>
                <img src="/images/button/efficient.gif" onClick="do_efficient(); return false;" alt="��Ч" id="delete">
                <%
                        }
                    }
                %>--%>
                <img src="/images/button/back.gif" alt="ȡ��" onclick="do_close();"></td>
        </tr>
    </table>
    <input type="hidden" name="act" value="<%=parser.getParameter("act")%>">
    <input type="hidden" name="codeExist" id="codeExist" value="N">
    <input type="hidden" name="categoryExist" id="categoryExist" value="N">
    <input type="hidden" name="fieldsExist" id="fieldsExist" value="N">
    <input type="hidden" name="workorderObjectNo" value="<%=warehouseDTO.getWorkorderObjectNo()%>">
</form>
</BODY>

<script type="text/javascript">

<%
    if(!("".equals(detail) || detail == null)){
%>
	var objectCategory = document.getElementById('objectCategory');
	var objectCategoryValue = objectCategory[objectCategory.selectedIndex].value;
	var businessCategory = document.getElementById('businessCategory');
	var businessCategoryValue = businessCategory[businessCategory.selectedIndex].value;
	document.body.onload = function(){
										window.focus();
										if(objectCategoryValue == 71 && businessCategoryValue == "INV_BIZ_INSTRU") {
											checkFields();
										}
									 }
<%} else {%>
	document.body.onload = window.focus();
<%}%>

//ҳ��ı�workorderObjectLocation(Mis�ص�)����
var selectObject = document.getElementById('sc');
var objValue = selectObject[selectObject.selectedIndex].value; //��һ�ν�ȥҳ���ֵ
var objText = selectObject[selectObject.selectedIndex].text;  //��һ�ν���ҳ���ֵ

function returnCountyCode(objOption) {
	objValue = objOption.options[objOption.selectedIndex].value;
	objText = objOption.options[objOption.selectedIndex].text;
	changeText();
}

function changeText() {
	document.forms[0].workorderObjectLocation.value = objText + "." + document.forms[0].locationName.value + "." + "000";
}


//���水ť����
function do_submit() {
    var fieldNames = "workorderObjectName;objectCategory;workorderObjectLocation;countyCode;businessCategory";
    var fieldLabels = "�ֿ�����;�ֿ����;���ڵص�;�����ɱ�����;ҵ������";
    var objectCode = "<%=warehouseDTO.getWorkorderObjectCode()%>" ;

    if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)) {
    	<%
    		if(("".equals(detail) || detail == null)){
    	%>
	    	
	        mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
	       
	    <%}%>

        if (mainFrm.workorderObjectNo.value != "") {
            mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
        }
        var codeExist = document.getElementById("codeExist").value;
        if (codeExist == "N") {
            var categoryExist = document.getElementById("categoryExist").value;
            if (categoryExist == "N") {
            	var fieldsExist = document.getElementById("fieldsExist").value;
            	
            	if(fieldsExist == "N") {
	                mainFrm.action = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?objValue=" + objValue;
	                mainFrm.submit();
	                opener.location.href = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?act=<%=WebActionConstant.QUERY_ACTION%>";
                } else {
                	//alert("һ����˾ֻ������һ�������Ǳ�������!");
                	return false;
                }
            } else {
                alert("�����Ͳֿ��Ѵ���");
            }
        } else {
            alert("�òֿ�����Ѵ���");
        }
    }
}

//ȡ����ť����
function do_close() {
    window.close();
    opener.location.href = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?act=<%=WebActionConstant.QUERY_ACTION%>";
}

<%--   function do_delete() {
   document.mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
   document.mainFrm.action = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet";
   document.mainFrm.submit();
}
function do_efficient() {
   document.mainFrm.act.value = "<%=AMSActionConstant.INURE_ACTION%>";
   document.mainFrm.action = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet";
   document.mainFrm.submit();
} --%>

// ------------------------------------------------------------------------
var xmlHttp;

//-- checkObjectCode
function checkObjectCode() {

    var url = "";
    var objCode = document.getElementById("workorderObjectCode").value;
    xmlHttp = createXMLHttpRequest();
    if (objCode != "") {
        url = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?act=CHECK_CODE_ACTION&workorderObjectCode=" + objCode;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}
//-- checkObjectCategory
function checkObjectCategory() {

    var url = "";
    var objCategory = document.getElementById("objectCategory").value;
    xmlHttp = createXMLHttpRequest();
    if (objCategory != "") {
        url = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?act=CHECK_CATEGORY_ACTION&objectCategory=" + objCategory;
        xmlHttp.onreadystatechange = handleReadyStateChange2;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}
//-- ��һ����˾ֻ��һ�������Ǳ���������м��

function checkFields() {
	var objectCategory = document.getElementById('objectCategory');
	var objectCategoryValue = objectCategory[objectCategory.selectedIndex].value;
	var businessCategory = document.getElementById('businessCategory');
	var businessCategoryValue = businessCategory[businessCategory.selectedIndex].value;
	//alert(objectCategoryValue); alert(businessCategoryValue);
	
	<%
    	if(!("".equals(detail) || detail == null)){
    %>
    	var objCode = document.getElementById("workorderObjectCode").value;
    	if(objCode != "" || objCode != null) {
	    	var url = "";
		    xmlHttp = createXMLHttpRequest();
		    url = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?act=CHECK_FIELDS_ACTION&workorderObjectCode=" + objCode + "&objectCategory=" + objectCategoryValue + "&businessCategory=" + businessCategoryValue;
		} else {
			alert("�ֿ���벻����Ϊ��!");
		}
    <%} else {%> 
	    var url = "";
	    xmlHttp = createXMLHttpRequest();
	    url = "/servlet/com.sino.nm.ams.inv.common.servlet.EtsObjectServlet?act=CHECK_FIELDS_ACTION&workorderObjectCode=" + "&objectCategory=" + objectCategoryValue + "&businessCategory=" + businessCategoryValue;
    <%}%>
    xmlHttp.onreadystatechange = handleReadyStateChange3;
    xmlHttp.open("post", url, true);
    xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlHttp.send(null);
}


//checkCode
function handleReadyStateChange1() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            if (resText == '<%=WebAttrConstant.CODE_EXIST%>') {
                document.getElementById("codeExist").value = "Y";
                document.getElementById("tipMsg").style.visibility = "visible";
            } else if (resText == '<%=WebAttrConstant.CODE_NOT_EXIST%>') {
                document.getElementById("codeExist").value = "N";
                document.getElementById("tipMsg").style.visibility = "hidden";
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}
//checkCategory
function handleReadyStateChange2() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            var objectCode = "<%=warehouseDTO.getWorkorderObjectCode()%>";
            if ((resText != "") && (resText != objectCode) && (resText != '<%=WebAttrConstant.CATEGORY_NOT_EXIST%>')) {
                document.getElementById("categoryExist").value = "Y";
                //alert("�����Ͳֿ��Ѵ���");
            } else if (resText == '<%=WebAttrConstant.CATEGORY_NOT_EXIST%>' || (resText == objectCode)) {
                document.getElementById("categoryExist").value = "N";
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}
//-- ��һ����˾ֻ��һ�������Ǳ���������м��

function handleReadyStateChange3() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            var resText = xmlHttp.responseText;
            if (resText == '<%=WebAttrConstant.FIELDS_EXIST%>') {
            	//alert(resText);
                document.getElementById("fieldsExist").value = "Y";
                document.getElementById("tipMsg").style.visibility = "visible";
            } else if (resText == '<%=WebAttrConstant.FIELDS_NOT_EXIST%>') {
            	//alert(resText);
                document.getElementById("fieldsExist").value = "N";
                document.getElementById("tipMsg").style.visibility = "hidden";
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}

//-- ���ֿ�����Ƿ��Ѿ�����
function do_comeForm() {
    var oc = "<%=warehouseDTO.getWorkorderObjectCode()%>"  ;
    if (oc == "") {
        checkObjectCode();
    }
}
</SCRIPT>
</HTML>