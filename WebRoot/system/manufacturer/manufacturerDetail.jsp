<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO"%>
<%@ page import="com.sino.ams.system.manufacturer.EtsManufacturerDTO" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <title>������Ϣά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>
<body>
 <jsp:include page="/message/MessageProcess"/>
<%
	EtsManufacturerDTO dto = (EtsManufacturerDTO)request.getAttribute(WebAttrConstant.MANUFACTURER_DTO);
%>

<form name="mainFrm" method="POST">
 	<script language="javascript">
        printTitleBar("������Ϣ����");
    </script>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:10%">
        <table border="0"  width="100%" id="table1" align = "center">
            <tr>
                <td width="30%" align="right" height="22">����������&nbsp;</td>
                <td width="50%" align="left" height="22">
                    <!-- <input type="text" name = "manufacturerCode" size = "51"  class="input_style1" value="<%//=dto.getManufacturerId() < 0  ? "" : dto.getManufacturerCode()%>"> -->
                	<input type="text" name = "manufacturerCode" size = "51"  class="input_style1" value="<%=dto.getManufacturerCode()%>">
                </td>
            </tr>
            <%--<tr>--%>
                <%--<td width="30%" align="right" height="22">��Ч״̬��&nbsp;</td>--%>
                <%--<td width="50%" align="left" height="22">--%>
                    <%--<select style="width:40%" name="enable" id="enable" onchange="do_SetImageProp();">--%>
                        <%--<option value="Y" <%=dto.getEnable().equals("Y") ? "selected": ""%>>��Ч</option>--%>
                        <%--<option value="N" <%=dto.getEnable().equals("N") ? "selected" : ""%>>��Ч</option>--%>
                    <%--</select>--%>
                <%--</td>--%>
            <%--</tr>--%>
        </table>
        <p align = "center">
            <img src="/images/eam_images/save.jpg" alt="����" onClick="do_ValidateCode(); return false;">&nbsp;&nbsp;
            <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="history.go(-1);">
        </p>
        <input type="hidden" name="act" value="">
        <input type="hidden" name="manufacturerId">
    </div>
</form>
</body>
</html>
<script>
function do_Save() {
    var fieldNames = "manufacturerCode";
    var fieldLabels = "��������";
    if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)) {
        with (mainFrm) {
            var manuId = "<%=dto.getManufacturerId()%>";
            if(manuId == "" || manuId == 0){
                mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
                mainFrm.action = "<%=URLDefineList.MANUFACTURER_QUERY_SERVLET%>";
                mainFrm.submit();
            } else {
                mainFrm.manufacturerId.value = manuId; 
                mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>"
                mainFrm.action = "<%=URLDefineList.MANUFACTURER_QUERY_SERVLET%>";
                mainFrm.submit();
            }
        }
    }
}
var xmlHttp = null;

function do_ValidateCode() {
	if(mainFrm.manufacturerCode.value != ""){
		var url = "<%=URLDefineList.MANUFACTURER_QUERY_SERVLET%>";
		url += "?act=VALIDATE_ACTION"
		url += "&manufacturerCode=" + mainFrm.manufacturerCode.value;
        url += "&manufacturerId=" + mainFrm.manufacturerId.value;
		do_ProcessSimpleAjax(url, null);
	}
}

function do_ProcessResponse(responseContent){
	with(document){
		if(responseContent == "Y"){
			alert("��������ĳ�����Ϣ�Ѵ��ڣ����������룡");
			mainFrm.manufacturerCode.focus();
            return false;
		}
        do_Save();
	}
}
</script>

