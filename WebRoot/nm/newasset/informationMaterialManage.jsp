<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page import="com.sino.nm.ams.newasset.dto.InformationMaterialManageDTO;" %>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>������Ϣ����</title>
 <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">

<%
    //RequestParser reqParser = new RequestParser();
    //reqParser.transData(request);
    InformationMaterialManageDTO dto = (InformationMaterialManageDTO) request.getAttribute(QueryConstant.QUERY_DTO);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = (rows != null && !rows.isEmpty());
    SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
    int orgId = userAccount.getOrganizationId();
      
%>



<form name="mainFrm" method="post" action="/servlet/com.sino.nm.ams.newasset.servlet.InformationMaterialManageServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("��Ϣ�����ʹ���-->>������Ϣ����")
</script>

	<table width="100%" border="0" class="queryHeadBg">
		<tr>
			<td width="6%" align="right">���ʱ�ţ�</td>
			<td width="16%"><input type="text" name="barcode" style="width:100%" value="<%=dto.getBarcode()%>"></td>
			<td width="6%" align="right">�������ƣ�</td>
			<td width="16%"><input type="text" name="itemName" style="width:100%" value="<%=dto.getItemName()%>"></td>
			<td width="6%" align="right">�����ͺţ�</td>
			<td width="16%"><input type="text" name="itemSpec" style="width:100%" value="<%=dto.getItemSpec()%>"></td>
		</tr>
		<tr>
			<td width="6%" align="right">�����ˣ�</td>
			<td width="16%"><input type="text" name="createBy" style="width:100%" value="<%=dto.getResponsibilityUser()%>"></td>
			<td width="4%" align="right">�������ڣ�</td>
			<td width="20%">
				<input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(startDate, endDate);">
				��<input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(startDate, endDate);">
			</td>
			<td width="22%" colspan="2" align="center">
				<img border="0" src="/images/button/query.gif" onclick="do_Search();">&nbsp;
				<img src="/images/button/ok.gif" style="cursor:'hand'" alt="ȷ��">&nbsp;
				<!-- <img src="/images/button/ok.gif" style="cursor:'hand'" onclick="do_match();" alt="ȷ��">&nbsp; -->
				<img border="0" src="/images/button/disabled.gif" onclick="do_Delete();">&nbsp;
				<img src="/images/button/toExcel.gif" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">
			</td>
		</tr>

    </table>
	
   <legend align="left"  height="30">
    ͳһ����:
    <input type="checkbox" name="allDept" id="allDept"><label for="allDept">����ʹ�ò���</label>
    <input type="checkbox" name="allUser" id="User"><label for="allUser">��ʹ����</label>
    <input type="checkbox" name="allUser" id="allUser"><label for="allUser">��������</label>
    <input type="checkbox" name="allLocation" id="allLocation"><label for="allLocation">����ص�</label>
    <input type="checkbox" name="allLocation" id="ocation"><label for="allLocation">��Ҫ�̶�</label>
</legend>
	
	
	<input readonly name="act" type="hidden">



</form>
<div id="headDiv" style="overflow:hidden;position:absolute;top:100px;left:1px;width:1060px">
	 <table class="headerTable" border="1" width="150%">
		<tr height="22">
			<td width="2%;" align="center">
				<input type="checkbox" name="titleCheck" id="subCheck0" onclick="checkAll('titleCheck','itemId')">
			</td>
			<td width="7%" align="center">���ʱ��</td>
			<td width="7%" align="center">�ʲ���ǩ</td>
			<td width="7%" align="center">��������</td>
			<td width="10%" align="center">�����ͺ�</td>
			<td width="5%" align="center">����״̬</td>
			<td width="7%" align="center">����Ʒ��</td>
			<td width="10%" align="center">�������к�</td>
			<td width="10%" align="center">��������</td>
			<td width="5%" align="center">������</td>
			<td width="7%" align="center">����ϵͳ</td>
			<td width="10%" align="center">��Ʒ��</td>
			<td width="5%" align="center">������</td>
			<td width="15%" align="center">���β���</td>

		</tr>
	</table>
</div>		

<div id="dataDiv" style="overflow:scroll;height:300px;width:1060px;position:absolute;top:122px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="150%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed">
         <%
               if (rows != null && rows.getSize() > 0) {
                   Row row = null;
                   for (int i = 0; i < rows.getSize(); i++) {
                       row = rows.getRow(i);
         %>
         
         <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
         	   <td width="2%;" align="center">
					<input type="checkbox" name="itemId" id="subCheck0" value="<%=String.valueOf(row.getValue("ITEM_ID"))%>">
			   </td>
			        <!-- <a onclick="do_Update(<%=String.valueOf(row.getValue("ITEM_ID"))%>)" ></a>  -->
	               <td width="7%" align="left"><%=String.valueOf(row.getValue("MSS_BARCODE"))%></td>
	               <td width="7%" align="left"><%=String.valueOf(row.getValue("BARCODE"))%></td>
	               <td width="7%" align="left"><%=String.valueOf(row.getValue("ITEM_NAME"))%></td>
	               <td width="10%" align="left"><%=String.valueOf(row.getValue("ITEM_SPEC"))%></td>
	               <td width="5%" align="left"><%=String.valueOf(row.getValue("ITEM_STATUS"))%></td>
	               <td width="7%" align="left"><%=String.valueOf(row.getValue("ITEM_BRAND"))%></td>
	               <td width="10%" align="left"><%=String.valueOf(row.getValue("ITEM_SERIAL"))%></td>
	               <td width="10%" align="left"><%=String.valueOf(row.getValue("CREATION_DATE"))%></td>
	               <td width="5%" align="left"><%=String.valueOf(row.getValue("CREATED_BY"))%></td>
	               <td width="7%" align="left"><%=String.valueOf(row.getValue("USE_BY_SYSTEM"))%></td>
	               <td width="10%" align="left"><%=String.valueOf(row.getValue("PRODUCT_ID"))%></td>
	               <td width="5%" align="left"><%=String.valueOf(row.getValue("RESPONSIBILITY_USER"))%></td>
	               <td width="15%" align="left"><%=String.valueOf(row.getValue("RESPONSIBILITY_DEPT"))%></td> 
	       
          </tr>
          
          <%
                  }
              }
          %>
	</table>
 </div>
 <%if (hasData) {%>	
<div style="position:absolute;top:92%;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<% }%>
</body>

<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>

<script type="text/javascript">


function do_Search(){
	mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_CreateOrder() {
    //var url = "<%=AssetsURLList.ASSETS_TRANS_SERVLET%>?act=<%=AssetsActionConstant.NEW_ACTION%>&transType=" + transType;
    var url = "<%=AssetsURLList.INFORMATION_MATERIAL_SERVLET%>?act=<%=WebActionConstant.NEW_ACTION%>";
    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, "transferWin", style);
}

function do_Create() {
    var url = "<%=AssetsURLList.CHECK_HEADER_SERVLET%>?act=<%=AssetsActionConstant.NEW_ACTION%>";
    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, 'discardWin', style);
}

function showDetail(headerId){
    var url = "<%=AssetsURLList.CHECK_HEADER_SERVLET%>?act=<%=AssetsActionConstant.DETAIL_ACTION%>&headerId="+headerId;
    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, 'orderWin', style);
}

function do_Cancel(){
    mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
}


function do_Export() {
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}

function do_Delete(){
	mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

//function do_match(){
//	mainFrm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
//	mainFrm.submit();
//	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
//}

function do_Update(itemId){
	var url = "<%=AssetsURLList.INFORMATION_MATERIAL_SERVLET%>?act=<%=WebActionConstant.UPDATE_ACTION%>&itemId=" + itemId;
    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, "transferWin", style);
	
	//mainFrm.action = "<%=AssetsURLList.INFORMATION_MATERIAL_SERVLET%>?act=<%=WebActionConstant.UPDATE_ACTION%>&itemId=" + itemId;
	//mainFrm.submit();
}
function do_SelectUser(userBox){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_USER%>";
	var dialogWidth = 44;
	var dialogHeight = 29;
	var userPara = "organizationId=<%=orgId%>";
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	var boxName = userBox.name;
	if (objs) {
		var obj = objs[0];
		userBox.value = obj["userName"];
	} else {
		userBox.value = "";
	}
}


function do_SelectLocation(){
	with(mainFrm){
		var lookUpName = "LOOK_UP_ADDRESS";
		var dialogWidth = 55;
		var dialogHeight = 30;
		userPara = "organizationId=<%=orgId%>";
		var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		if (objs) {
			var obj = objs[0];
			objectCode.value = obj["workorderObjectCode"];
		} else {
			objectCode.value = "";	
		}
	}
}
</script>
