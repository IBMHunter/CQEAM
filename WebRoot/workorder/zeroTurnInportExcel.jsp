<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page import="com.sino.ams.workorder.dto.ZeroTurnHeaderDTO"%>
<%@ page import="com.sino.ams.workorder.dto.ZeroTurnLineDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>

<html>
<%
	
	RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    String action = reqParser.getParameter("act");
    ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) request.getAttribute(AssetsWebAttributes.ZERO_TURN_DATA);
    //DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
   DTOSet lineSet=(DTOSet)request.getSession().getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    // request.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA,lineSet);
	//String transId = headerDTO.getTransId();
	//String transStatus=headerDTO.getTransStatus();
%>
<head>
	<title>�㹺ת�����ݵ���</title>
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
    <script type="text/javascript" src="/WebLibary/js/AjaxProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/DefaultLocationProcess.js"></script>
</head>
<script type="text/javascript">
    printTitleBar("�㹺ת�����ݵ���");
</script>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="initPage();helpInit('4.4.4');">

<form action="/servlet/com.sino.ams.workorder.servlet.ZeroTurnInportExcelServlet" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<div id="searchDiv" style="position:absolute;top:29px;left:1px;width:100%">
  <table style="width:100%;">
        <input type="hidden" name="act" value="<%=action%>">
<input type="hidden" name="excel" value="">
<input type="hidden" name="excelPath"  value="" >
        <tr >
            <td width="20%" align="right">
            <img src="/images/eam_images/imp_from_excel.jpg" alt="Excel����"  onClick="do_excel();">
                <img  src="/images/eam_images/save.jpg" alt="����" onClick="do_save();">
                <img  src="/images/eam_images/export.jpg" alt="����EXCEL" onClick="do_export();">
            </td>
        </tr>
    </table>
</div>
  <div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:63px;left:0px;width:100%;">
     <table class=headerTable border=1 style="width:200%">
        <tr height="22px" onClick="executeClick(this)" style="cursor:pointer" title="���ȫѡ��ȡ��ȫѡ">
        	
            <td align=center width="4%">���������</td>
            <td align=center width="4%">MIS�빺������</td>
            <td align=center width="4%">��˾����</td>
            <td align=center width="4%">��ǩ��</td>
            <td align=center width="4%">�ʲ�����</td>
            <td align=center width="4%">����ͺ�</td>
         
            <td align=center width="4%">����</td>
            <td align=center width="4%">�ʲ����</td>
            <td align=center width="4%">����</td>
            <td align=center width="4%">ʹ������</td>
            <td align=center width="4%">�ʲ�ԭֵ</td>
           
            <td align=center width="4%">��������</td>
            <td align=center width="4%">ҵ��ƽ̨</td>
            <td align=center width="4%">������</td>
            <td align=center width="4%">�Ƿ񹲽�</td>
            <td align=center width="4%">�ɱ�����</td>
            
            <td align=center width="7%">�ʲ��ص�����</td>
            <td align=center width="5%">�ʲ����ڵص����</td>
            <td align=center width="4%">�ʲ������˱��</td>
            <td align=center width="4%">�ʲ�����������</td>
            <td align=center width="4%">�빺���</td>
            <td align=center width="4%">�ջ���</td>
            <td align=center width="4%">�ջ�����ϵ��ʽ</td>
            <td align=center width="4%">Ԥ�Ƶ�������</td>
        </tr>
    </table>
</div>

 <div id="dataDiv" style="overflow:scroll;height:88%;width:100%;position:absolute;top:1px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
     <table id="dataTable" width="200%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
            <%
		if (lineSet == null || lineSet.isEmpty()) {
     %>
            <!--<tr>
                <td colspan="10"><font style="color: red;">���ڴ˴����Ӽ�¼!</font> </td>
            </tr>-->            
             <tr class="dataTR" style="display:none">
             	<td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            
	            <td  width="4%">
	            <td  width="7%">
	            <td  width="5%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
	            <td  width="4%">
           </tr>
<%
 	  } else {
 		 ZeroTurnLineDTO lineDTO = null;
		  for (int i = 0; i < lineSet.getSize(); i++) {
			lineDTO = (ZeroTurnLineDTO) lineSet.getDTO(i);
%>
             <tr class="dataTR" style="cursor:pointer" errorMessage="<%=StrUtil.htmlStrEncode(lineDTO.getErrorMessage())%>" onmouseover="showAltValue(this)" onmouseout="hideAltValue()">
				
				<input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=lineDTO.getLineId() %>">
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="misProcureCode" id="misProcureCode<%=i%>" class="finput" readonly value="<%=lineDTO.getMisProcureCode()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="procureCode" id="procureCode<%=i%>" readonly class="finput"  value="<%=lineDTO.getProcureCode()%>"></td>
				  <td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="companyCode" id="companyCode<%=i%>" class="finput" readonly value="<%=lineDTO.getCompanyCode()%>"></td>
				  <td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="barcode" id="barcode<%=i%>" class="finput" readonly value="<%=lineDTO.getBarcode()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="assetsDescription" id="assetsDescription<%=i%>" readonly class="finput"  value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="itemSpec" id="itemSpec<%=i%>" class="finput" readonly value="<%=lineDTO.getItemSpec()%>"></td>
				
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="manufacturerName" id="manufacturerName<%=i%>" readonly class="finput"  value="<%=lineDTO.getManufacturerName()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="contentCode" id="contentCode<%=i%>" class="finput" readonly value="<%=lineDTO.getContentCode()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="itemQty" id="itemQty<%=i%>" class="finput" readonly value="<%=lineDTO.getItemQty()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="years" id="years<%=i%>" class="finput" readonly value="<%=lineDTO.getYears()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="price" id="price<%=i%>" class="finput" readonly value="<%=lineDTO.getPrice()%>"></td>
				
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="startDate" id="startDate<%=i%>" class="finput"  readonly value="<%=lineDTO.getStartDate()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="opeId" id="opeId<%=i%>" readonly class="finput"  value="<%=lineDTO.getOpeId()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="nleId" id="nleId<%=i%>" readonly class="finput"  value="<%=lineDTO.getNleId()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="isBulid" id="isBulid<%=i%>" class="finput" readonly  value="<%=lineDTO.getIsBulid()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="costCenterCode" id="costCenterCode<%=i%>" readonly class="finput"  value="<%=lineDTO.getCostCenterCode()%>"></td>
				
				 <td width="7%" align="center" style="cursor:pointer" >
				  <input type="text" name="workorderObjectName" id="workorderObjectName<%=i%>" class="finput"  readonly value="<%=lineDTO.getWorkorderObjectName()%>"></td>
				<td width="5%" align="center" style="cursor:pointer" >
				  <input type="text" name="objectNo" id="objectNo<%=i%>" class="finput" readonly value="<%=lineDTO.getObjectNo()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="responsibilityUser" id="responsibilityUser<%=i%>" class="finput" readonly value="<%=lineDTO.getResponsibilityUser()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="responsibilityName" id="responsibilityName<%=i%>" readonly class="finput"  value="<%=lineDTO.getResponsibilityName()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="procureType" id="procureType<%=i%>" readonly class="finput"  value="<%=lineDTO.getProcureType()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="receiver" id="receiver<%=i%>" class="finput" readonly  value="<%=lineDTO.getReceiver()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="receiverContact" id="receiverContact<%=i%>" class="finput" readonly  value="<%=lineDTO.getReceiverContact()%>"></td>
				<td width="4%" align="center" style="cursor:pointer" >
				  <input type="text" name="expectedDate" id="expectedDate<%=i%>" class="finput" readonly value="<%=lineDTO.getExpectedDate()%>"></td>
				
				   <input type="hidden" name="errorMessage" id="errorMessage<%=i%>" class="finput" readonly value="<%=lineDTO.getErrorMessage()%>"></td>
            </tr>
            <%
            }}
             %>
     </table>
     
    </div>


</form>
<jsp:include page="/public/hintMessage.jsp" flush="true"/>
<div id="hiddenDiv" style="position: absolute; overflow:scroll;width:550px;height:180px; display:none;background-color:#C6EBF4;color:red"></div>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<script type="text/javascript">
function initPage(){
		do_SetPageWidth();
	}
function showAltValue(tr){
    do_SetTrCheckedColor(tr);
    var errorMessage = tr.getAttribute("errorMessage");
    if(errorMessage == ""){
        return;
    }
    var bodyHeight = document.body.clientHeight;
    var screenHeight = window.screen.height;
    if(bodyHeight + 72 > screenHeight){
        bodyHeight = screenHeight - 72;
    }
    var rowNumber = new Number((tr.rowIndex + 1));
    var divObj = document.getElementById("hiddenDiv");
    var top = tr.offsetTop;
    var height = tr.clientHeight;
    while (tr = tr.offsetParent) {
        top += tr.offsetTop;
    }
    var left = event.clientX;
    left += document.documentElement.scrollLeft;    
    divObj.style.left = left;
    var divTop = top + height;
    divObj.style.top = divTop;
    var divHeight = divObj.offsetHeight;
    var searchHeight = searchDiv.offsetHeight;
    if(divHeight + divTop + searchHeight + 72> bodyHeight){
        divTop = bodyHeight - divHeight - searchHeight - 92;
        divObj.style.bottom = bodyHeight - searchHeight - 23;
        divObj.style.top = divTop;
    } else {
        divObj.style.top = divTop;
    }
    divObj.style.display = "block";
    errorMessage = "<p align='center'>��"
            + rowNumber
            + "�еĴ�����Ϣ"
            + "</p><hr color='white' width='100%' size='1'>"
            + errorMessage;
    divObj.innerHTML = errorMessage;
}

var checkedColor = "#FFFF99";

function do_SetTrCheckedColor(tr){
    var errorMessage = tr.getAttribute("errorMessage");
    if(errorMessage != ""){
        do_SetCellInputColor(tr, checkedColor);
    }
    var tab = document.getElementById("dataTable");
    var rows = tab.rows;
    for(var i = 0; i < rows.length; i++){
        var row = rows[i];
        if(row.rowIndex == tr.rowIndex){
            continue;
        }
        do_SetCellInputColor(row, "#FFFFFF");
    }
}


var checkedColor = "#FFFF99";

function do_SetTrCheckedColor(tr){
    var errorMessage = tr.getAttribute("errorMessage");
    if(errorMessage != ""){
        do_SetCellInputColor(tr, checkedColor);
    }
    var tab = document.getElementById("dataTable");
    var rows = tab.rows;
    for(var i = 0; i < rows.length; i++){
        var row = rows[i];
        if(row.rowIndex == tr.rowIndex){
            continue;
        }
        do_SetCellInputColor(row, "#FFFFFF");
    }
}

function hideAltValue(){
    var clientX = event.clientX;
    var clientY = event.clientY;
    var hiddenDiv = document.getElementById("hiddenDiv");
    var offsetLeft = hiddenDiv.offsetLeft;
    var offsetTop = hiddenDiv.offsetTop;
    var offsetWidth = hiddenDiv.offsetWidth;
    var offsetHeight = hiddenDiv.offsetHeight;
    if(clientX < offsetLeft ||  clientX > (offsetLeft + offsetWidth)){
        hiddenDiv.style.display = "none";
    } else if(clientY < offsetTop ||  clientY > (offsetTop + offsetHeight)){
        hiddenDiv.style.display = "none";
    }
}

function do_SetCellInputColor(tr, color){
    var cells = tr.cells;
    var cellCount = cells.length;
    for(var i = 0; i < cellCount; i++){
        var cell = cells[i];
        var nodes = cell.childNodes;
        if(nodes[0].tagName == "INPUT"){
            nodes[0].style.backgroundColor = color;
        }
    }
}

//excel����
function do_excel() {
	excelType = "5";
    var returnValue = do_ImportExcelData();
    if (returnValue) {
        isSave=true;
        mainFrm.excelPath.value = returnValue;
        mainFrm.excel.value = "1";
       	mainFrm.act.value = "excel";
        mainFrm.submit();
    }
}
function do_query() {
        mainFrm.act.value = "queryData";
        mainFrm.submit();
}
function do_save() {
        mainFrm.act.value = "insertData";
        mainFrm.submit();
}
function do_export() {
        mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainFrm.target = "_self";
        mainFrm.submit();
    }

</script>