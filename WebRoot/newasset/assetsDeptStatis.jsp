<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.ams.newasset.dto.EtsFaAssetsDTO"%>
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="com.sino.base.data.Row"%>
<%@ page import="com.sino.base.data.RowSet"%>
<%@ page import="com.sino.ams.newasset.constant.AssetsActionConstant" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
</head>
<body leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_Search()');">
<%
	EtsFaAssetsDTO dto = (EtsFaAssetsDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String assetsCategory = dto.getAssetsCategory();
	RowSet rows = (RowSet)request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = false;
	if(rows != null && !rows.isEmpty()){
		hasData = true;
	}	
%>
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="">
<script type="text/javascript">
    printTitleBar("�ʲ�ͳ�Ʊ���>>�����ʲ�ͳ��");
</script>
	<table width="100%" border="0">
	    <tr>
	    	<td width="10%" align="right">��˾���ƣ�</td>
	    	<td width="18%">
			<input type="text" name="companyName" size="20" class="readonlyInput" readonly style="width:100%" value="<%=dto.getCompanyName()%>">
	    	<td width="10%" align="right">�������ƣ�</td>
	    	<td width="18%"><input type="text" name="deptName" size="20" class="readonlyInput" readonly style="width:100%" value="<%=dto.getDeptName()%>"  title="���ѡ����" onclick="do_SelectDepartment();"></td>
	    	<td width="10%" align="right">�ʲ����ƣ�</td>
	    	<td width="18%">
			<input type="text" name="assetsDescription" size="20" style="width:100%" value="<%=dto.getAssetsDescription()%>"></td>
	    	<td width="14%" align="right" rowspan="2">
	    		<img src="/images/eam_images/search.jpg" title="�����ѯ" onClick="do_Search();">
	    		<img src="/images/eam_images/export.jpg" title="������Excel" onClick="do_Export();">
	    	</td>
	    </tr>
	    <tr>
	    	<td width="10%" align="right"><span lang="zh-cn">�ʲ��ͺ�</span>��</td>
	    	<td width="18%">
			<input type="text" name="modelNumber" size="20" style="width:100%" value="<%=dto.getModelNumber()%>"></td>
	    	<td width="10%" align="right">�������ڣ�</td>
	    	<td width="18%">
			<input type="text" name="startDate" size="20" style="width:86%" class="readonlyInput" readonly title="���ѡ��ʼ����" onclick="gfPop.fStartPop(startDate, endDate)" value="<%=dto.getStartDate()%>"><img src="/images/calendar.gif" alt="���ѡ��ʼ����" onclick="gfPop.fStartPop(startDate, endDate)"></td>
	    	<td width="10%" align="right">����</td>
	    	<td width="18%">
			<input type="text" name="endDate" size="20" style="width:86%" title="���ѡ���ֹ����" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)" value="<%=dto.getEndDate()%>"><img src="/images/calendar.gif" alt="���ѡ���ֹ����" onclick="gfPop.fEndPop(startDate, endDate)"></td>
	    </tr>
	</table>
	<input type="hidden" name="act" value="">
	<input type="hidden" name="assetsCategory" value="<%=assetsCategory%>">
	<input type="hidden" name="companyCode" value="<%=dto.getCompanyCode()%>">
	<input type="hidden" name="deptCode" value="<%=dto.getDeptCode()%>">

<script type="text/javascript">
    var columnArr = new Array("��������", "�ʲ����һ", "�ʲ�����", "�ʲ�����", "�ʲ��ͺ�", "������λ", "����", "ԭֵ(Ԫ)");
    var widthArr = new Array("12%","12%", "10%","23%","23%", "8%", "6%", "6%");
    printTableHead(columnArr,widthArr);
</script>
<%
	if(hasData){
%>
    <div style="overflow-y:scroll;height:360px;width:100%;left:1px;margin-left:0">
<%
	}
%>    
</form>	
<%
	if(hasData){
%>
	    <table width="100%" border="1" bordercolor="#666666">		

<%		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
%>		
			<tr class="dataTR">
				<td width="12%" align="left"><%=row.getValue("DEPT_NAME")%></td>
				<td width="12%" align="left"><%=row.getValue("FA_CATEGORY1")%></td>
				<td width="10%" align="left"><%=row.getValue("FA_CATEGORY2")%></td>
				<td width="23%" align="left"><%=row.getValue("ASSETS_DESCRIPTION")%></td>
				<td width="23%" align="left"><%=row.getValue("MODEL_NUMBER")%></td>
				<td width="8%" align="center" ><%=row.getValue("UNIT_OF_MEASURE")%></td>
				<td width="6%" align="right" ><%=row.getValue("ASSETS_COUNT")%>&nbsp;</td>
				<td width="6%" align="right" ><%=row.getValue("ASSETS_COST")%>&nbsp;</td>
			</tr>
<%
		}
%>
		</table>
	</div>
<div style="position:absolute;top:450px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%		
	}
%>	
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<iframe style="display:none" src="" name="downFrm"></iframe>

<script language="javascript">

function do_Search(){
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	mainFrm.action = "/servlet/com.sino.ams.assets.servlet.FaAssetsStatisServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
}



/**
 * ����:ѡ����
 */
function do_SelectDepartment() {
	var lookUpName = "LOOK_UP_PRI_DEPT";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var userPara = "companyCode=<%=dto.getCompanyCode()%>";
	var depts = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.deptName.value = "";
		mainFrm.deptCode.value = "";
		mainFrm.companyName.value = "";
		mainFrm.companyCode.value = "";
	}
}

function do_Export(){
	mainFrm.target = "downFrm";
	mainFrm.action = "/servlet/com.sino.ams.assets.servlet.FaAssetsStatisServlet";
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}


</script>
