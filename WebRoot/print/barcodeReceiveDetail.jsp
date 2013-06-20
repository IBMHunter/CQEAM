<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import = "com.sino.ams.print.dto.BarcodeReceiveDTO" %>
<%@ page import = "com.sino.ams.newasset.constant.AssetsLookUpConstant" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%--
  Author: ����
  Date: 2009-4-28
--%>
<html>
<head>
    <title>��ǩ����ά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
    <script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
</head>
<script type="text/javascript">
    printTitleBar("��ǩ����ά��");
</script>

<body leftmargin="0" topmargin="0">
<%
    BarcodeReceiveDTO brDTO = (BarcodeReceiveDTO) request.getAttribute(WebAttrConstant.BARCODE_RECEIVE_DTO);
	SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
	String orgaOption = (String) request.getAttribute(WebAttrConstant.CITY_OPTION);
	String deptOption = (String) request.getAttribute(WebAttrConstant.COUNTY_OPTION);
%>
<form action="/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet" method="post" name="mainFrm">
    <jsp:include page="/message/MessageProcess"/>
    <input type="hidden" name="act">
<%--    <input type="hidden" name="name" value="">--%>
<%--    <input type="hidden" name="userId" value="<%=user.getUserId()%>">--%>
    <table width="81%" border="0" align="center" class="queryTable">
        <tr>
        	<input type = "hidden" name = "barcodeReceiveId" value = "<%=brDTO.getBarcodeReceiveId() %>">
            <td width="20%" align="right">��ʼ��ǩ��</td>
            <td width="80%" colspan = "3"><input type="text" name="fromBarcode" class="input_style1" style="width:80%"
                                   value="<%=brDTO.getFromBarcode() %>"><font color="red">*</font>
                <br><label id="retMsg" style="color:red"></label>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">������ǩ��</td>
            <td width="80%" colspan = "3"><input type="text" name="toBarcode" class="input_style1" style="width:80%"
                                   value="<%=brDTO.getToBarcode()%>"><font color="red">*</font>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">��ǩ������</td>
            <td width="22%">
            	<input type="text" name = "barcodeQty" size = "40" readonly class="input_style1"  style = "width:80%" value="<%=brDTO.getBarcodeQty() %>" onclick = "do_getBarcodeQty();"><font color="red">*</font>
            </td>
            <%--<td width="15%" align="right">��ǩ��ӡ������</td>--%>
            <%--<td width="40%">--%>
            	<%--<input type="text" name = "barcodePrintNum" size = "20"  class="noEmptyInput"  style = "width:61%" value="<%=brDTO.getBarcodePrintNum() %>">--%>
            <%--</td>--%>
        </tr>
        
        <tr>
            <td width="20%" align="right">�������У�</td>
            <td width="80%" colspan = "3">
            	<select style="width:80%" name="organizationId" class="select_style1" onchange="getDeptOpt();"><%=orgaOption%></select>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">���ò��ţ�</td>
            <td width="80%" colspan = "3">
				<select style="width:80%" name="receiveDept" class="select_style1" onclick = "do_SelectDept(); return false;"><%=deptOption%></select>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">�����ˣ�</td>
            <td width="80%" colspan = "3">
            	<input type = "hidden" name = "receiveUser" value = "<%=brDTO.getReceiveUser() %>">
            	<input type="text" name = "receiveUserName" size = "40"class="input_style1" style = "width:80%"
                                   value="<%=brDTO.getReceiveUserName() %>" onclick="do_SelectUser(mainFrm.receiveUser, mainFrm.receiveUserName); return false;" title="���ѡ��������"><font color="red">*</font>
                            <a href="" onclick="do_SelectUser(mainFrm.receiveUser, mainFrm.receiveUserName); return false;" title="���ѡ��������">[...]</a>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">�������ڣ�</td>
            <td width="80%" colspan = "3">
            	<input  readonly name="receiveDate" style="width:80%" class="input_style2" value="<%=brDTO.getReceiveDate()  %>" onclick="gfPop.fPopCalendar(receiveDate)">
            	<img src="/images/calendar.gif" alt="���ѡ����������" onclick="gfPop.fPopCalendar(receiveDate)">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">��ӡ���ڣ�</td>
            <td width="80%" colspan = "3">
            	<input  readonly name="printDate" class="input_style2" style="width:80%" value="<%=brDTO.getPrintDate() %>" onclick="gfPop.fPopCalendar(printDate)">
            	<img src="/images/calendar.gif" alt="���ѡ���ӡ����" onclick="gfPop.fPopCalendar(printDate)">
			</td>
        </tr>
        <tr>						
            <td width="20%" align="right">��ӡ�ˣ�</td>
            <td width="80%" colspan = "3">
            	<input type = "hidden" name = "printUser"  value = "<%=user.getUserId() %>" >
            	<input name="printUserName" style="width:80%" value = "<%=user.getUsername() %>"  class="input_style2" readonly>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">����ԭ��</td>
            <td width="80%" colspan = "3">
            	<textarea name="receiveRemark" cols = ""class="input_style1" rows = "4" style="width:80%" ><%=brDTO.getReceiveRemark() %></textarea>
            </td>
        </tr>
    </table>
    <p align="center">
    	<img src="/images/eam_images/save.jpg" alt="����" onclick="do_Save(); return false;">&nbsp;
    	<%
    		if(!brDTO.getBarcodeReceiveId().equals("")){    		
    	%>
    		<img src="/images/eam_images/delete.jpg" alt="ɾ��" onclick="do_Delete(); return false;">&nbsp;
    	<%
    		}
    	%>
        <img src="/images/eam_images/back.jpg" alt="ȡ��" onclick="history.go(-1);">
    </p>
    
</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">
function initPage(){
	do_SetPageWidth();
	do_SetImageProp();
}
	
	function do_SelectDept(){
		var organizationId = document.getElementsByName("organizationId")[0].value;
		if(organizationId == ""){
			alert("����ѡ���������У�");
			mainFrm.organizationId.focus();
		}		
	}
	
	function do_Delete() {
	    var roleId = mainFrm.barcodeReceiveId.value;
	    if (confirm("ȷ��ɾ���ñ�ǩ���ü�¼�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
	        mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
	        document.mainFrm.submit();
	    }
	}
	

	//�õ���ӡ��ǩ����
	function do_getBarcodeQty(){
		var fieldNames = "fromBarcode;toBarcode";
        var fieldLabels = "��ʼ��ǩ;������ǩ";
        var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
        if (isValid) {
            var fromBarcode = mainFrm.fromBarcode.value;
            var toBarcode = mainFrm.toBarcode.value;
            var Expression = /^[0-9]{4}-[0-9]{8}$/;
            var objExp=new RegExp(Expression);
            if(!objExp.test(fromBarcode)){
                alert("����ʼ��ǩ�����Ϸ�����������ȷ��ʽ��");
                mainFrm.fromBarcode.focus();
                return;
            }
            if(!objExp.test(toBarcode)){
                alert("��������ǩ�����Ϸ�����������ȷ��ʽ��");
                mainFrm.toBarcode.focus();
                return;
            }
            var fromBarcodeCompanyCode = fromBarcode.substring(0,4);
            var toBarcodeCompanyCode = toBarcode.substring(0,4);
            if(fromBarcodeCompanyCode != toBarcodeCompanyCode){
                alert("����ʼ��ǩ���롰������ǩ����������˾��һ�£�");
                mainFrm.barcodeQty.value = "";
                return;
            }
            fromBarcode = fromBarcode.substring(7, fromBarcode.length);
            toBarcode = toBarcode.substring(7, toBarcode.length);
            var barcodeQty = parseFloat(toBarcode) - parseFloat(fromBarcode) + 1;
            mainFrm.barcodeQty.value = barcodeQty;
        }
	}

    var loginNameWrong = false;
    function do_Save() {
        var fieldNames = "fromBarcode;toBarcode;barcodeQty;receiveUserName;printUserName";
        var fieldLabels = "��ʼ��ǩ;������ǩ;��ǩ����;������;��ӡ��";
        var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
        if (isValid) {
            var fromBarcode = mainFrm.fromBarcode.value;
            var toBarcode = mainFrm.toBarcode.value;
            var Expression = /^[0-9]{4}-[0-9]{8}$/;
            var objExp=new RegExp(Expression);
            if(!objExp.test(fromBarcode)){
                alert("����ʼ��ǩ�����Ϸ�����������ȷ��ʽ��");
                mainFrm.fromBarcode.focus();
                return;
            }
            if(!objExp.test(toBarcode)){
                alert("��������ǩ�����Ϸ�����������ȷ��ʽ��");
                mainFrm.toBarcode.focus();
                return;
            }
            var fromBarcodeCompanyCode = fromBarcode.substring(0,4);
            var toBarcodeCompanyCode = toBarcode.substring(0,4);
            if(fromBarcodeCompanyCode != toBarcodeCompanyCode){
                alert("����ʼ��ǩ���롰������ǩ����������˾��һ�£�");
                mainFrm.barcodeQty.value = "";
                return;
            }
            if(mainFrm.barcodeReceiveId.value != ""){
                document.mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
            }else{
                document.mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
            }
            document.mainFrm.submit();
        }
	}

	/******************************************
	 *	Function: ����ָ����OU,��ѯ��OU�µ��û�	  *
	 ******************************************/
	function do_SelectUser(userId, userName){
	    if(mainFrm.receiveDept.value != ""){
	    	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_USER_BY_ORGANAZATION%>";
			var dialogWidth = 44;
			var dialogHeight = 31;
			var userPara = "organizationId=" + document.getElementsByName("organizationId")[0].value;
			var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		    if (users) {
		        var user = users[0];
		        userName.value = user["userName"];
		        userId.value = user["userId"];	        	        
		    } else {
		        userId.value = "";
		        userName.value = "";
		    }
		}else{
			alert("����ѡ�����ò���!");
		}
	}


var xmlHttp;
    function getDeptOpt() {
        var organizationId = document.getElementById("organizationId").value ;
        var url = "/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet?act=CHOOSE_GROUP&organizationId=" + organizationId;
       
        xmlHttp = GetXmlHttpObject(setDeptOpt);
        xmlHttp.open('POST', url, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
        xmlHttp.send("a=1");
    }

    function setDeptOpt() {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
            if (xmlHttp.status == 200) {
                var resText = xmlHttp.responseText;
                var resArray = resText.parseJSON();
                if (resArray == "ERROR") {
                    alert(resText);
                } else {
                    var littleCategoryObj = document.getElementById("receiveDept");
                    
                    littleCategoryObj.length = 0;
                    var emptyOption = new Option("--��ѡ��--", "");
                    littleCategoryObj.add(emptyOption)
                    if (resArray.length > 0) {
                        var retStr;
                        for (var i = 0; i < resArray.length; i++) {
                            retStr = resArray[i];
                            var arr = retStr.split("$");
                            var option = new Option(arr[1], arr[0]);
                            littleCategoryObj.add(option)
                        }
                    }
                }
                xmlHttp = null;
            }
        }
    }

    

/**
 * �����ص��б��������������
 * �޸���ɡ�
 */
function do_ProcessResponse(responseContent){
	mainFrm.countyCode.outerHTML = "<select style=\"width:100%\" name=\"countyCode\">" + responseContent + "</select>";
}
</script>