<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>MIS�ʲ����ϲ�ѯ</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
	AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
	String pageTitle = request.getParameter("pageTitle");
	if(pageTitle == null){
		pageTitle = "MISϵͳ�ӿ�-->>MIS�ʲ����ϲ�ѯ";
	}
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.servlet.MisDiscardedAssetsQueryServlet">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>")
</script>
	<table border="0" width="100%" bgcolor="#EFEFEF">
		<tr height="20">
			<td width="8%" align="right"> ��˾���ƣ�</td>
			<td width="15%">
				<select name="organizationId" id="organizationId" style="width:100%" size="1"><%=request.getAttribute(WebAttrConstant.ALL_ORG_OPTION) %></select>
			</td>
			<td width="8%" align="right"> �ʲ���ǩ��</td>
			<td width="15%">
				<input style="width:100%" name="tagNumber" value="<%=dto.getTagNumber()%>" type="text" ></td>
			<td width="8%" align="right"> ԭMIS��ǩ��</td>
			<td width="15%">
				<input style="width:100%" name="misTagNumber" value="<%=dto.getMisTagNumber()%>" type="text" size="20" ></td>
			<td width="8%" align="right"> �ʲ����ƣ�</td>
			<td width="15%">
				<input style="width:100%" name="assetsDescription" value="<%=dto.getAssetsDescription()%>" type="text" >
			</td>
			<td width="8%">
				<p align="right">
				<img src="/images/eam_images/search.jpg" alt="��ѯ" onClick="do_Search(); return false;"></td>
		</tr>
		<tr height="20">
			<td width="8%" align="right">�������ڣ�</td>
			<td width="15%">
				<input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:100%" title="���ѡ��ʼ����" readonly class="readonlyInput"  onclick="gfPop.fStartPop(startDate, endDate)" >
			</td>
			<td width="8%" align="right">����</td>
			<td width="15%">
				<input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:100%" title="���ѡ���ֹ����" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)" >
			</td>
			<td width="8%" align="right">�������ڣ�</td>
			<td width="15%">
				<input type="text" name="startCreationDate" value="<%=dto.getStartCreationDate()%>"  style="width:100%" title="���ѡ��ʼ����" readonly class="readonlyInput"  onclick="gfPop.fStartPop(startCreationDate, endCreationDate)" >
			</td>
			<td width="8%" align="right">����</td>
			<td width="15%">
				<input type="text" name="endCreationDate" value="<%=dto.getEndCreationDate()%>" style="width:100%" title="���ѡ���ֹ����" readonly class="readonlyInput" onclick="gfPop.fEndPop(startCreationDate, endCreationDate)" >
			</td>
			<td width="8%">
				��</td>
		</tr>
        <tr height="20">
			<td width="8%" align="right"> �ɱ����ģ�</td>
			<td width="15%">
				<input style="width:86%" name="costCenterCode" value="<%=dto.getCostCenterCode()%>" type="text" ><a href="" onclick="do_SelectCostCenter(); return false;" title="���ѡ��ɱ�����">[��]</a>
			</td>
			<td width="8%" align="right"> ��Ŀ���ƣ�</td>
			<td width="15%">
				<input style="width:86%" type="text" name="projectName" value="<%=dto.getProjectName()%>" ><a href="" onclick="do_SelectProject(); return false;" title="���ѡ����Ŀ">[��]</a>
			</td>
			<td width="8%" align="right"> �ص���룺</td>
			<td width="15%">
				<input type="text" name="assetsLocationCode" value="<%=dto.getAssetsLocationCode()%>" style="width:86%"><a href="" onclick="do_SelectAddress(); return false;" title="���ѡ��ص�">[��]</a>
			</td>
			<td width="8%" align="right">�����ˣ�</td>
			<td width="15%">
				<input style="width:86%" name="assignedToName" value="<%=dto.getAssignedToName()%>" type="text" ><a href="" onclick="do_SelectPerson(); return false;" title="���ѡ��������">[��]</a>
			</td>
			<td width="8%" align="right">
				<img src="/images/eam_images/export.jpg" alt="����EXCEL" onclick="do_Export()"></td>
		</tr>
        <tr height="20">
			<td width="8%" align="right">Ӧ������</td>
			<td width="15%">
				<input type="text" name="faCategory1" value="<%=dto.getFaCategory1()%>" style="width:100%">
			</td>
			<td width="8%" align="right">�ʲ����</td>
			<td width="15%">
				<input type="text" name="faCategory2" value="<%=dto.getFaCategory2()%>" style="width:100%">
			</td>
			<td width="8%" align="right">���ϳɱ���</td>
			<td width="15%">
				<input style="width:100%" name="costRetired" value="<%=dto.getCostRetired() %>" type="text" size="20" ></td>
			<td width="8%" align="right">�������ͣ�</td>
			<td width="15%">
				<input style="width:100%" name="retirementTypeCode" value="<%=dto.getRetirementTypeCode()%>" type="text" >
			</td>
			<td width="8%">
				��</td>
		</tr>
		 <tr height="20">
			<td width="8%" align="right">�������ڣ�</td>
			<td width="15%">
				<input type="text" name="dateRetiredStart" value="<%=dto.getDateRetiredStart()%>" style="width:100%" title="���ѡ�񱨷Ͽ�ʼ����" readonly class="readonlyInput"  onclick="gfPop.fStartPop(dateRetiredStart, dateRetiredEnd)" >
			</td>
			<td width="8%" align="right">����</td>
			<td width="15%">
				<input type="text" name="dateRetiredEnd" value="<%=dto.getDateRetiredEnd()%>" style="width:100%" title="���ѡ�񱨷Ͻ�ֹ����" readonly class="readonlyInput" onclick="gfPop.fEndPop(dateRetiredStart, dateRetiredEnd)" >
			</td>
			<td width="8%" align="right">������Ч���ڣ�</td>
			<td width="15%">
				<input type="text" name="dateEffectiveStart" value="<%=dto.getDateEffectiveStart()%>" style="width:100%" title="���ѡ�񱨷���Ч��ʼ����" readonly class="readonlyInput"  onclick="gfPop.fStartPop(dateEffectiveStart, dateEffectiveEnd)" >
			<td width="8%" align="right">����</td>
			<td width="15%">
				<input type="text" name="dateEffectiveEnd" value="<%=dto.getDateEffectiveEnd()%>" style="width:100%" title="���ѡ�񱨷���Ч��ֹ����" readonly class="readonlyInput" onclick="gfPop.fEndPop(dateEffectiveStart, dateEffectiveEnd)" >
			</td>
			<td width="8%">
				��</td>
		</tr>

	</table>
	<input readonly name="act" type="hidden">
</form>
<div id="headDiv" style="overflow:hidden;position:absolute;top:145px;left:1px;width:400%">
	<table class="headerTable" border="1" width="400%">
		<tr height="22">
			<td width="2%" align="center">�ʲ���ǩ</td>
			<td width="2%" align="center">ԭMIS��ǩ</td>
			<td width="2%" align="center">�ʲ����</td>
			<td width="2%" align="center">Ӧ������</td>
			<td width="3%" align="center">�ʲ�������</td>
			<td width="5%" align="center">�ʲ����</td>

			<td width="2%" align="center">�ʲ�����</td>
			<td width="5%" align="center">�ʲ��ͺ�</td>
			<td width="1%" align="center">����</td>
			<td width="1%" align="center">��λ</td>
			<td width="3%" align="center">�ص����</td>
			<td width="4%" align="center">�ص�λ��</td>
			
			<td width="2%" align="center">��������</td>
			<td width="2%" align="center">������Ч����</td>
			<td width="2%" align="center">���ϳɱ�</td>
			<td width="2%" align="center">��������</td>
			
			<td width="2%" align="center">������</td>
			<td width="2%" align="center">Ա����</td>
			<td width="2%" align="center">��Ŀ���</td>
			<td width="4%" align="center">��Ŀ����</td>

			<td width="2%" align="center">��Ŀ����</td>
			<td width="1%" align="center">����</td>
			<td width="2%" align="center">��������</td>
			<td width="2%" align="center">��������</td>
			<td width="2%" align="center">ԭʼ�ɱ�</td>

			<td width="2%" align="center">��ǰ�ɱ�</td>
			<td width="2%" align="center">�ۼ��۾�</td>
			<td width="2%" align="center">�ʲ���ֵ</td>
			<td width="2%" align="center">�ʲ���ֵ</td>
			<td width="2%" align="center">��ֵ׼���ۼ�</td>

			<td width="3%" align="center">��˾����</td>
			<td width="2%" align="center">�ʲ��˲�����</td>
			<td width="3%" align="center">�ʲ��˲�����</td>
			<td width="6%" align="center">�۾��˻�����</td>
			<td width="7%" align="center">�۾��˻�����</td>
		</tr>
	</table>
</div>		
<div id="dataDiv" style="overflow:scroll;height:63%;width:400%;position:absolute;top:168px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="400%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">

<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>	
		<tr height="22">
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("TAG_NUMBER")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("MIS_TAG_NUMBER")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("FA_CATEGORY1")%>"></td>
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("SEGMENT2")%>"></td>
			<td width="5%" align="center"><input class="finput"  readonly value="<%=row.getValue("FA_CATEGORY2")%>"></td>

			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
			<td width="5%" align="center"><input class="finput"  readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
			<td width="1%" align="center"><input class="finput2" readonly value="<%=row.getValue("CURRENT_UNITS")%>"></td>
			<td width="1%" align="center"><input class="finput2" readonly value="<%=row.getValue("UNIT_OF_MEASURE")%>"></td>
			<td width="3%" align="center"><input class="finput"  readonly value="<%=row.getValue("ASSETS_LOCATION_CODE")%>"></td>
			<td width="4%" align="center"><input class="finput"  readonly value="<%=row.getValue("ASSETS_LOCATION")%>"></td>
			
			<td width="2%" align="center"><input class="finput2" readonly value="<%=StrUtil.nullToString(row.getValue("DATE_RETIRED"))%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=StrUtil.nullToString(row.getValue("DATE_EFFECTIVE"))%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("COST_RETIRED")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("RETIREMENT_TYPE_CODE")%>"></td>

			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSIGNED_TO_NAME")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSIGNED_TO_NUMBER")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("PROJECT_NUMBER")%>"></td>
			<td width="4%" align="center"><input class="finput" readonly value="<%=row.getValue("PROJECT_NAME")%>"></td>

			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("PROJECT_TYPE")%>"></td>
			<td width="1%" align="center"><input class="finput2" readonly value="<%=row.getValue("LIFE_IN_YEARS")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSETS_CREATE_DATE")%>"></td>
			<td width="2%" align="center"><input class="finput3" readonly value="<%=row.getValue("ORIGINAL_COST")%>"></td>

			<td width="2%" align="center"><input class="finput3" readonly value="<%=row.getValue("COST")%>"></td>
			<td width="2%" align="center"><input class="finput3" readonly value="<%=row.getValue("DEPRN_RESERVE")%>"></td>
			<td width="2%" align="center"><input class="finput3" readonly value="<%=row.getValue("DEPRN_COST")%>"></td>
			<td width="2%" align="center"><input class="finput3" readonly value="<%=row.getValue("SCRAP_VALUE")%>"></td>
			<td width="2%" align="center"><input class="finput3" readonly value="<%=row.getValue("IMPAIR_RESERVE")%>"></td>

			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("COMPANY")%>"></td>
			<td width="2%" align="center"><input class="finput2" readonly value="<%=row.getValue("BOOK_TYPE_CODE")%>"></td>
			<td width="3%" align="center"><input class="finput2" readonly value="<%=row.getValue("BOOK_TYPE_NAME")%>"></td>
			<td width="6%" align="center"><input class="finput2" readonly value="<%=row.getValue("DEPRECIATION_ACCOUNT")%>"></td>
			<td width="7%" align="center"><input class="finput" readonly value="<%=row.getValue("DEPRECIATION_ACCOUNT_NAME")%>"></td>
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
<div style="position:absolute;top:570px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
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


var num = 0;
function do_Export() {
	num += 1;
	if(num > 1){
		alert("��ִ�е�������벻Ҫ�ظ������");
		return;
	}
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}

function do_SelectAddress(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_MISLOC%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		mainFrm.assetsLocationCode.value = obj["assetsLocationCode"];
	}
}

function do_SelectPerson(){
	var lookUpName = "LOOK_UP_PERSON";
	var dialogWidth = 47;
	var dialogHeight = 28;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if(objs){
		var obj = objs[0];
		mainFrm.assignedToName.value = obj["userName"];
	}
}

function do_SelectProject() {
	var lookUpName = "LOOKUP_PROJECT2";
	var dialogWidth = 50;
	var dialogHeight = 30;
	var objs = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		mainFrm.projectName.value = obj["projectName"];
	}
}

function do_SelectCostCenter(){
	var lookUpName = "LOOK_UP_COST";
    var userPara="organizationId="+document.mainFrm.organizationId.value;
    var dialogWidth = 50;
	var dialogHeight = 28;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight,userPara);
	if (objs) {
		var obj = objs[0];
		document.mainFrm.costCenterCode.value = obj["costCode"];
	}
}
</script>