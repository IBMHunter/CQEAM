<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.ams.dzyh.dto.EamDhCatalogValuesDTO"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>


<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>�����ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>

</head>
<body onload="initPage();" onkeydown="autoExeFunction('do_SaveDzyh()');">
 <jsp:include page="/message/MessageProcess"/>
<%

	String barcodeFlag = (String)request.getAttribute(WebAttrConstant.DZYH_BARCODE_OPT);
	String commonFlag = (String)request.getAttribute(WebAttrConstant.DZYH_COMMON_OPT);
	String catelogSet=(String)request.getAttribute(WebAttrConstant.DZYH_PARENT_OPT);
	String unit=(String)request.getAttribute(WebAttrConstant.DZYH_UNIT_OF_MEASURE_OPT);
	
    EamDhCatalogValuesDTO eamDhValues = (EamDhCatalogValuesDTO)request.getAttribute(WebAttrConstant.DZYH_DATA);
%>
<form name="mainFrm" method="POST" action="/servlet/com.sino.ams.dzyh.servlet.EamDhCatalogValuesServlet">
    <table border="0" width="100%" id="table1">
        <tr>
            <td width="20%" align="right" height="22">Ŀ¼��ţ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="catalogCode" class="noemptyInput" style="width:100%" value="<%=eamDhValues.getCatalogCode()%>">
				</td>
            <td width="20%" align="right" height="22">Ʒ&nbsp;&nbsp;����</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="catalogName" class="noemptyInput" style="width:100%" value="<%=eamDhValues.getCatalogName()%>">
				</td>
            <td width="20%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="20%" align="right" height="22">������λ��</td>
            <td width="20%" align="left" height="22">
                <select name="unit" style="width:100%"><%=unit%></select>
                </td>
            <td width="20%" align="right" height="22">ע&nbsp;&nbsp;����</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="description" style="width:100%" value="<%=eamDhValues.getDescription()%>">
				</td>
            <td width="20%" align="left" height="22"></td>
        </tr>

        <tr>
            <td width="20%" align="right" height="22">�����ʶ��</td>
            <td width="20%" align="left" height="22">
                <select name="barcodeFlag" style="width:100%"><%=barcodeFlag%></select>
                </td>
            <td width="20%" align="right" height="22">���ñ�ʶ��</td>
            <td width="20%" align="left" height="22">
                <select name="commonFlag" style="width:100%"><%=commonFlag%></select>
                </td>
            <td width="20%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="20%" align="right" height="22">�Ƿ���Ч��</td>
            <td width="20%" align="left" height="22">
                <%=request.getAttribute("ENABLED_RADIO")%>
            <td width="20%" align="right" height="22">�������ࣺ</td>
            <td width="20%" align="left" height="22">
                <select name="catalogSetId" style="width:100%"><%=catelogSet%></select>
            </td>
            <td width="20%" colspan="3" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="20%" align="right" height="22">�����ˣ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="createdBy" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=eamDhValues.getCreatedBy()%>">
				</td>
            <td width="20%" align="right" height="22">�������ڣ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="creationDate" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=eamDhValues.getCreationDate()%>">
				</td>
            <td width="20%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="20%" align="right" height="22">�����ˣ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="lastUpdateBy" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=eamDhValues.getLastUpdateBy()%>">
				</td>
            <td width="20%" align="right" height="22">�������ڣ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="lastUpdateDate" style="width:100%" value="<%=eamDhValues.getLastUpdateDate()%>"
				title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fStartPop(lastUpdateDate,'')">
				</td>
            <td width="20%" align="left" height="22">
            </td>
        </tr>
        <tr><td width="100%" align="center" height="22" colspan="4">
        </td></tr>
        <tr>
            <td width="100%" align="center" height="22" colspan="4">

                <img src="/images/eam_images/save.jpg" alt="����" onClick="do_SaveDzyh(); return false;">&nbsp;

                <img src="/images/eam_images/back.jpg" alt="����" onClick="do_Back(); return false;">
                </td>
        </tr>

    </table>
	<input type="hidden" name="act" value="">
    <input type="hidden" name="catalogValueId" value="<%=eamDhValues.getCatalogValueId()%>">
    <input type="hidden" name="catalogSetName" value="">
</form>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script>

function initPage(){
	var selObj = mainFrm.catalogSetId;
	var catalogSetName = selObj.options[selObj.selectedIndex].text;
	var startIndex = catalogSetName.indexOf("(") + 1;
	var endIndex = catalogSetName.length - 1;
	catalogSetName = catalogSetName.substring(startIndex, endIndex);
	mainFrm.catalogSetName.value = catalogSetName;
}

function do_SaveDzyh() {
	var fieldNames = "catalogCode;catalogName;unit;description;barcodeFlag;commonFlag";
	var fieldLabels = "Ŀ¼���;Ʒ��;������λ;ע��;�����ʶ;���ñ�ʶ";
	var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
	if (isValid) {
		var action = "<%=WebActionConstant.CREATE_ACTION%>";
        if (mainFrm.catalogValueId.value != "") {
			action = "<%=WebActionConstant.UPDATE_ACTION%>";
		}
		mainFrm.act.value = action;
		mainFrm.submit();
	}
}

function do_Back(){
	with(mainFrm){
		catalogCode.value = "";
		catalogName.value = "";
		unit.value = "";
		description.value = "";
		barcodeFlag.value = "";
		commonFlag.value = "";
		act.value = "<%=WebActionConstant.QUERY_ACTION%>";
		submit();
	}
}
</script>