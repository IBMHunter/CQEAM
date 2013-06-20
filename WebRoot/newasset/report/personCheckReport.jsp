<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�̵�������</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
	AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	String orgId = userAccount.getOrganizationId();
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.report.servlet.PersonChkReportServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("�̵��ճ�����-->>�����̵㱨��")
</script>
	<table width="100%" border="0" class="queryHeadBg">
		<tr>
			<td width="8%" align="right">���β��ţ�</td>
			<td width="23%"><select size="1" name="deptCode" style="width:100%"><%=dto.getDeptOpt()%></select></td>
			<td width="6%" align="right">�����ˣ�</td>
			<td width="14%" align="right"><input type="text" name="responsibilityUser" style="width:80%" value="<%=dto.getResponsibilityUser()%>"><a href="" title="���ѡ��������" onclick="do_SelectPerson(); return false;">[��]</a></td>
			<td width="8%" align="right">��ǩ�ţ�</td>
			<td width="14%" align="center"><input type="text" name="tagNumber" style="width:100%" value="<%=dto.getTagNumber()%>" ></td>
			<td width="8%" align="right">�豸���ƣ�</td>
			<td width="17%"><input type="text" name="assetsDescription" style="width:80%" value="<%=dto.getAssetsDescription()%>"><a href=""  title="���ѡ���豸����" onclick="do_SelectItemName(); return false;">[��]</a></td>
		</tr>
		<tr>
			<td width="8%" align="right">�̵�ص㣺</td>
			<td width="23%"><input type="text" name="checkLocationName" style="width:80%" value="<%=dto.getCheckLocationName()%>"><a href=""  title="���ѡ��ص�" onclick="do_SelectAddress(); return false;">[��]</a></td>
			<td width="6%" align="right">�̵��ˣ�</td>
			<td width="14%" align="right"><input type="text" name="implementUser" style="width:80%" value="<%=dto.getImplementUser()%>"><a href=""  title="���ѡ��ص�" onclick="do_SelectUser(); return false;">[��]</a></td>
			<td width="8%" align="right">�̵�ʱ�䣺</td>
			<td width="22%" align="center" colspan="2"><input type="text" name="startDate" style="cursor:hand;width:45%" title="���ѡ��ʼ����" readonly class="readonlyInput" value="<%=dto.getStartDate()%>" onclick="gfPop.fStartPop(startDate,endDate);" >&nbsp;��<input type="text" name="endDate" style="cursor:hand;width:45%" title="���ѡ���������" readonly class="readonlyInput" value="<%=dto.getEndDate()%>" onclick="gfPop.fEndPop(startDate,endDate);"></td>
			<td width="17%" align="right"><img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">&nbsp;&nbsp;<img border="0" src="/images/eam_images/search.jpg" onclick="do_Search();"></td>
		</tr>
	</table>
	<input name="act" type="hidden">
</form>


<div id="headDiv" style="overflow:hidden;position:absolute;top:68px;left:1px;width:840px">
	<table class="headerTable" border="1" width="200%">
		<tr height="22">
			<td width="7%" align="center">��ǩ��</td>
			<td width="8%" align="center">�豸רҵ</td>
			<td width="10%" align="center">�豸����</td>

			<td width="10%" align="center">�豸�ͺ�</td>
			<td width="10%" align="center">�ص����</td>
			<td width="14%" align="center">�ص�����</td>

			<td width="10%" align="center">��������</td>
			<td width="5%" align="center">������</td>
			<td width="5%" align="center">Ա����</td>

			<td width="5%" align="center">�̵���</td>
			<td width="10%" align="center">�������</td>
			<td width="7%" align="center">�̵�����</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:360px;width:857px;position:absolute;top:91px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="200%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>
		<tr height="22">
			<td width="7%"><input type="text" class="finput2" readonly value="<%=row.getValue("BARCODE")%>"></td>
			<td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_CATEGORY")%>"></td>
			<td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>

			<td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>
			<td width="10%"><input type="text" class="finput2" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
			<td width="14%"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>

			<td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
			<td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("USER_NAME")%>"></td>
			<td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("EMPLOYEE_NUMBER")%>"></td>

			<td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("CHECK_USER")%>"></td>
			<td width="10%"><input type="text" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
			<td width="7%"><input type="text" class="finput2" readonly value="<%=row.getValue("ARCHIVED_DATE")%>"></td>
		</tr>
<%
		}
	}
%>
	</table>
</div>
<%
	if(hasData){
%>
<div style="position:absolute;top:468px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function do_Search(){
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Export() {
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}


function do_SelectAddress(){
	var lookUpName = "LOOK_UP_ADDRESS";
	var dialogWidth = 55;
	var dialogHeight = 30;
	var userPara = "organizationId=<%=orgId%>";
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	if (objs) {
		var obj = objs[0];
		mainFrm.checkLocationName.value = obj["toObjectName"];
	}
}


function do_SelectPerson(){
	var lookUpName = "LOOK_UP_PERSON";
	var dialogWidth = 47;
	var dialogHeight = 30;
	var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if(users){
		var user = users[0];
		mainFrm.responsibilityUser.value = user["userName"];
	}
}

function do_SelectItemName(){
	var lookUpName = "LOOK_UP_ITEMNAME";
	var dialogWidth = 35;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
//		dto2Frm(obj, "mainFrm");
		mainFrm.assetsDescription.value = obj["itemName"];
    }
}



/**
  * ���ܣ�ѡ��ִ���ˡ��鵵��
 */
function do_SelectUser(){
	var lookUpName = "LOOK_UP_USER";
	var dialogWidth = 44;
	var dialogHeight = 29;
	var userPara = "organizationId=<%=orgId%>"
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	if (objs) {
		var obj = objs[0];
		mainFrm.implementUser.value = obj["userName"];
	}
}
</script>