<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.ams.expand.dto.EtsItemInfoExQueRenDTO"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.ams.dzyh.constant.DzyhLookUpConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>


<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>�ʲ���չ��ϸ��Ϣ(����ȷ��)</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>

</head>
<body onkeydown="autoExeFunction('do_SaveDzyh()');">
 <jsp:include page="/message/MessageProcess"/>
<%
    EtsItemInfoExQueRenDTO dto = (EtsItemInfoExQueRenDTO)request.getAttribute(WebAttrConstant.DZYH_DATA);
    
%>
<form name="mainFrm" method="POST" action="/servlet/com.sino.ams.expand.servlet.EtsItemInfoExQueRenServlet">
    <table border="0" width="100%" id="table1">
        <tr>
            <td width="20%" align="right" height="22">����ţ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="barcode" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=dto.getBarcode()%>">
				</td>
            <td width="20%" align="right" height="22">���ƣ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="itemName" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=dto.getItemName()%>">
				</td>
            <td width="20%" align="left" height="22"></td>
        </tr>
        <tr>
            <td width="20%" align="right" height="22">�ͺţ�</td>
            <td width="20%" align="left" height="22">
            <input type="text" name="itemSpec" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=dto.getItemSpec()%>">
                </td>
                
            <td width="20%" align="right" height="22">�������ԣ�</td>
            <td width="20%" align="left" height="22">
            <input type="text" name="financePropName" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=dto.getFinancePropName()%>">
                </td>
                
            <td width="20%" align="left" height="22"></td>
        </tr>

        <tr>
            <td width="20%" align="right" height="22">CPUƵ�ʣ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="attribute1" style="width:100%" class="noemptyInput" value="<%=dto.getAttribute1()%>">
				</td>
            <td width="20%" align="right" height="22">�豸���ͣ�</td>
            <td width="20%" align="left" height="22">
				<input type="text" name="itemType" style="width:100%" class="noemptyInput" value="<%=dto.getItemType()%>">
				</td>
            <td width="20%" align="left" height="22"></td>
        </tr>
        
        <tr>
            <td width="20%" align="right" height="22">�ڴ�������</td>
            <td width="20%" align="left" height="22">
            <input type="text" name="attribute2" style="width:100%" class="noemptyInput" value="<%=dto.getAttribute2()%>">
                </td>
                
            <td width="20%" align="right" height="22">��ʾ���ߴ磺</td>
            <td width="20%" align="left" height="22">
            <input type="text" name="attribute5" style="width:100%" class="noemptyInput" value="<%=dto.getAttribute5()%>">
                </td>
            <td width="20%" align="left" height="22"></td>
        </tr>

        <tr>
            <td width="20%" align="right" height="22">��ʾ�����ͣ�</td>
            <td width="20%" align="left" height="22">
            <input type="text" name="attribute4" style="width:100%" class="noemptyInput" value="<%=dto.getAttribute4()%>">
                </td>
            <td width="20%" align="right" height="22">Ӳ��������</td>
            <td width="20%" align="left" height="22">
            <input type="text" name="attribute3" style="width:100%" class="noemptyInput" value="<%=dto.getAttribute3()%>">
            </td>
            <td width="20%" align="left" height="22"></td>
        </tr>

        <tr>
            <td width="20%" align="right" height="22">���β��ţ�</td>
            <td width="20%" align="left" height="22">
            <input type="text" name="deptName" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=dto.getDeptName()%>">
            </td>
            <td width="20%" align="right" height="22">�����ˣ�</td>
            <td width="20%" align="left" height="22">
            <input type="text" name="employeeName" readonly="readonly" class="readonlyInput" style="width:100%" value="<%=dto.getEmployeeName()%>">
            </td>
            <td width="20%" align="left" height="22"></td>
        </tr>
        <tr>
        <td>&nbsp;</td></tr>      
        <tr>
            <td width="100%" align="center" height="22" colspan="5">
                <img src="/images/button/save.gif" alt="����" onClick="do_SaveIT(); return false;">&nbsp;
                <img src="/images/button/back.gif" alt="����" onClick="do_Back(); return false;">
                </td>
        </tr>

    </table>
	<input type="hidden" name="act" value="">
    <input type="hidden" name="systemid" value="<%=dto.getSystemid()%>">
    <input type="hidden" name="catalogSetName" value="">
</form>
<%=WebConstant.WAIT_TIP_MSG%>
</body>

<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script>
function do_SaveIT() {
	var fieldNames = "attribute1;attribute2;attribute3;attribute4;attribute5";
	var fieldLabels = "CPUƵ��;�ڴ�����;Ӳ������;��ʾ������;��ʾ���ߴ�";
	var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
	if (isValid) {
		var action = "<%=WebActionConstant.CREATE_ACTION%>";
   //     if (mainFrm.itemInfoExId.value != "") {
	//		action = "<%--=WebActionConstant.UPDATE_ACTION--%>";
	//	}
		document.all.itemType.value = "IT";
		mainFrm.act.value = action;
		mainFrm.submit();
	}
}

function do_Back(){
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	with(mainFrm){
		act.value = "<%=WebActionConstant.QUERY_ACTION%>";
		submit();
	}
}
</script>