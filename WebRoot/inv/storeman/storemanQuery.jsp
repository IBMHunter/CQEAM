<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/inv/headerInclude.jsp"%>
<%@ include file="/inv/headerInclude.htm"%>
<%@ page import="com.sino.ams.inv.storeman.base.constant.web.WebInvActionConstant" %>

<%--
  created by yu
  Date: 2008-12-12
  Time: 09:30:32
--%>

<html>
	<head>
		<title>Ĭ�ϲֹ�Աά��</title>
		<style>
			.finput {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;}
			.finput2 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:center;}
			.finput3 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:right;}
		</style>
	</head>
<body onkeydown="autoExeFunction('do_search()')"> 
<%    
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);   
	boolean hasData = (rows != null && !rows.isEmpty()); 
	EamInvStoremanDTO dto = (EamInvStoremanDTO)request.getAttribute(QueryConstant.QUERY_DTO);//���Servlet���dto.setXXX()����д��  
	Row row = null;   
%>
	<form action="" name="mainFrm" method="post">
		<script type="text/javascript">
			printTitleBar("Ĭ�ϲֹ�Աά��")
		</script>
		<%=WebInvConstant.WAIT_TIP_MSG%>
		<table width="100%" border="0" class="queryHeadBg">
			<tr>
				<td width="5%" align="right">��˾��</td>
				<td width="12%" align="right"><select name="organizationId" style="width: 100%"><%=dto.getOuOption()%></select></td>
				<td width="8%" align="right">�ֿ����ͣ�</td>
				<td width="10%" align="right"><select name="objectCategory" style="width: 100%" type="text"><%=dto.getInvCategoryOpt()%></select></td>
				<td width="8%" align="right">ҵ�����ͣ�</td>
				<td width="10%" align="right"><select name="businessCategory" style="width: 100%" type="text"><%=dto.getBizCategoryOpt()%></select></td>
				<td width="5%" align="right">�ֿ⣺</td>
				<td width="16%" align="right">
					<input type="text" name="workorderObjectName" value="<%=dto.getWorkorderObjectName()%>" style="width:80%"><a href="#" title="���ѡ��ֿ�" class="linka" onclick="do_SelectStore();">[��]</a>
				</td>
				<td align="right" width="26%">
					<img src="/images/eam_images/search.jpg" style="cursor: 'hand'" alt="��ѯ" onclick="do_search();">
					<img src="/images/eam_images/new.jpg" style="cursor: 'hand'" alt="����" onclick="do_add();">
					<img src="/images/eam_images/batch_update.jpg" style="cursor: 'hand'" alt="�������òֹ�Ա" onclick="do_BatchSet();">
				</td>
			</tr>
		</table>
		<div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
			<table width="100%" class="headerTable" border="1">
				<tr height="20"> 
					<td width="3%" align="center" style="padding: 0">
						<input type="checkbox" name="titleCheck" class="headCheckbox" id="controlBox" onclick="checkAll('titleCheck','storemanId')">
					</td>
					<td width="17%" align="center">�ֿ�����</td>
					<td width="10%" align="center">�ֿ�����</td>
					<td width="10%" align="center">ҵ������</td>
					<td width="15%" align="center">�ֹ�Ա</td>
					<td width="10%" align="center">������</td>
					<td width="10%" align="center">��������</td>
					<td width="10%" align="center">�޸���</td>
					<td width="10%" align="center">�޸�����</td>
				</tr>
			</table>
		</div>
		<div style="overflow-y:scroll;left:0px;width:100%;height:390px">
			<table width="100%" border="1" bordercolor="#666666">
<% 
	if(hasData) {
		for(int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
%>
				<tr height="20" style="cursor: 'hand'" onmousemove="style.backgroundColor='#EFEFEF'" onmouseout="style.backgroundColor='#ffffff'">			  
					<td width="3%" align="center" style="padding: 0"><input type="checkbox" name="storemanId" id="storemanId" value="<%=row.getValue("STOREMAN_ID")%>"></td>			
					<td width="17%" align="left" onclick="show_detail('<%=row.getValue("STOREMAN_ID") %>')">
					<%=row.getValue("WORKORDER_OBJECT_NAME")%> 
					</td>
					<td width="10%" align="left" onclick="show_detail('<%=row.getValue("STOREMAN_ID") %>')">
					<%=row.getValue("INV_CATEGORY_NAME")%>
					</td>
					<td width="10%" align="left" onclick="show_detail('<%=row.getValue("STOREMAN_ID") %>')">
					<%=row.getValue("BIZ_CATEGORY_NAME")%>
					</td>
					<td width="15%" align="left" onclick="show_detail('<%=row.getValue("STOREMAN_ID") %>')">
					<!--  
					<input type="hidden" name="userId" value="<%--=row.getValue("USER_ID") --%>">
					-->
					<%=row.getValue("USER_NAME") %>
					</td>
					<td width="10%" align="left" onclick="show_detail('<%=row.getValue("STOREMAN_ID") %>')">
					<%=row.getValue("CREATED_USER")%>
					</td>
					<td width="10%" align="center" onclick="show_detail('<%=row.getValue("STOREMAN_ID") %>')">
					<%=row.getValue("CREATION_DATE")%>
					</td>
					<td width="10%" align="left" onclick="show_detail('<%=row.getValue("STOREMAN_ID") %>')">
					<%=row.getValue("UPDATED_USER")%>
					</td>
					<td width="10%" align="center" onclick="show_detail('<%=row.getValue("STOREMAN_ID") %>')">
					<%=row.getValue("LAST_UPDATE_DATE")%>
					</td>
				</tr>
<% 
		}
	}
%>
			</table>
		</div>

		<input name="act" type="hidden">
		<input type="hidden" name="userId" value="">
	</form>
<%
	if(hasData){
%>
	<div style="position:absolute;top:470px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}	
%>
	<jsp:include page="/message/MessageProcess" flush="true"></jsp:include>
</body>
</html>
<script type="text/javascript">

function do_search(){
	
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	mainFrm.action = "/servlet/com.sino.ams.inv.storeman.servlet.EamInvStoremanServlet";
	
	mainFrm.submit();
}

function show_detail(storemanId){
	var url = "/servlet/com.sino.ams.inv.storeman.servlet.EamInvStoremanServlet?act=<%=WebActionConstant.DETAIL_ACTION%>&storemanId=" + storemanId;
	var popscript = 'width=400,height=200,top=200,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=yes';
	window.open(url, 'basePot', popscript);
}

function do_SelectStore(){
	
	/*
	var lookUpStore = "<%=LookUpInvConstant.LOOK_UP_WORKORDER_OBJECT_NO%>";
	var dialogWidth = 50;
	var dialogHeight = 30;
	var stores = getLookUpValues(lookUpStore, dialogWidth, dialogHeight);
	if(stores){
		var store = null;
		for(var i=0; i<stores.length; i++){
			store = stores[i];
			dto2Frm(store, "mainFrm");
		}
	}
	*/
	var  url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_WORKORDER_OBJECT_NO%>";
    var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    var vendorNames = window.showModalDialog(url, null, popscript);
    if(vendorNames){
       var vendorName = null;
       document.forms[0].workorderObjectName.value = vendorNames[0].workorderObjectName;
    }
}

function do_add(){
    
    var url="/servlet/com.sino.ams.inv.storeman.servlet.EamInvStoremanServlet?act=<%=WebActionConstant.NEW_ACTION%>";
    //var url = "/inv/storeman/addStoreman.jsp";
    var popscript = 'width=400,height=200,top=200,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=yes';
	window.open(url, 'basePot', popscript);
    //opener.location.href = "/servlet/com.sino.ams.carddepot.servlet.YsCardDefineServlet?act=<%=WebActionConstant.QUERY_ACTION%>";	
}

function do_close() {
   window.close();
   opener.location.href = "/servlet/com.sino.ams.carddepot.servlet.YsDepotDefineServlet?act=<%=WebActionConstant.QUERY_ACTION%>";
}

function do_BatchSet(){
	var storemanIds = document.getElementsByName('storemanId');
	var gets = new Array();
	
	var k = 0;
	for(var i=0; i<storemanIds.length; i++){
		if(storemanIds[i].checked){
			//alert("��" + i + "��ֵ��" + storemanIds[i].value);
			gets[k] = storemanIds[i].value;
			k++;
		}
	}
	
	var url = "/servlet/com.sino.ams.inv.storeman.servlet.EamInvStoremanServlet?act=<%=WebInvActionConstant.NEW_ACTION_USERS%>&storemanIds=" + gets;	
	var popscript = 'width=400,height=200,top=200,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=yes';
	window.open(url, 'basePot', popscript);
	
}
</script>