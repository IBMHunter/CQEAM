<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�ʲ�����ѯ</title>
</head>
<body topmargin="0" leftmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
	AmsFaCategoryVDTO queryArgs = (AmsFaCategoryVDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String mtlPrivi = queryArgs.getMtlPrivi();
	DTOSet dtos = (DTOSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (dtos != null && !dtos.isEmpty());
%>
<form action="<%=AssetsURLList.FA_CAT_QRY_SERVLET%>" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="userId" value="<%=queryArgs.getUserId()%>">
    <table width=100% bgcolor="#E9EAE9">
        <tr>
            <td width="10%" align="right">�ؼ��֣�</td>
            <td width="20%"><input type="text" name="faCategoryName" style="width:100%" value="<%=queryArgs.getFaCategoryName()%>"></td>
            <td width="10%" align="right">�û�������</td>
            <td width="15%"><input type="text" name="userName" class="readonlyInput" readonly style="width:100%" value="<%=queryArgs.getUserName()%>"></td>
            <td width="25%"><%=request.getAttribute(AssetsWebAttributes.MTL_PRIVI_RADIO)%></td>
            <td width="20%" align="right">
			<img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_Search();">
<%
	if(mtlPrivi.equals(AssetsWebAttributes.MTL_PRIVI_N)){
%>			
			<img src="/images/eam_images/save.jpg" alt="�������" onclick="do_SavePrivi();">
<%
	} else if(mtlPrivi.equals(AssetsWebAttributes.MTL_PRIVI_Y)){
%>			
			<img src="/images/eam_images/delete.jpg" alt="���ɾ��" onclick="do_DeletePrivi();">
<%
	}
%>			
			</td>
        </tr>
    </table>
	<div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:673px">
		<table class="headerTable" border="1" width="160%">
			<tr height="20px" onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
				<td align=center width="2%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','faCategoryCode')"></td>
				<td align="left" width="6%">������1</td>
				<td align="left" width="14%">�������1</td>
				<td align="left" width="12%">������2</td>
				<td align="left" width="42%">�������2</td>
				<td align="left" width="8%">������3</td>
				<td align="left" width="16%">�������3</td>
			</tr>
		</table>
	</div>
<%
	if (hasData) {
%>
	<div id="dataDiv" style="overflow:scroll;height:86%;width:690px;position:absolute;top:48px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
<%	
	}
%>
<%
	if (hasData) {
%>
        <table id="dataTable" width="160%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		AmsFaCategoryVDTO dto = null;
		for (int i = 0; i < dtos.getSize(); i++) {
			dto = (AmsFaCategoryVDTO)dtos.getDTO(i);
%>
	        <tr class="dataTR">
	          <td width="2%" align="center"><%=dto.getCheckBoxProp()%></td>
	          <td width="6%" align="center"><input type="text" class="finput" readonly value="<%=dto.getFaCatCode1()%>"></td>
	          <td width="14%" align="center"><input type="text" class="finput" readonly value="<%=dto.getFaCatName1()%>"></td>
	          <td width="12%" align="center"><input type="text" class="finput" readonly value="<%=dto.getFaCatCode2()%>"></td>
	          <td width="42%" align="center"><input type="text" class="finput" readonly value="<%=dto.getFaCatName2()%>"></td>
	          <td width="8%" align="center"><input type="text" class="finput" readonly value="<%=dto.getFaCatCode3()%>"></td>
	          <td width="16%" align="center"><input type="text" class="finput" readonly value="<%=dto.getFaCatName3()%>"></td>
	        </tr>
<%
		}
%>
	    </table>
	</div>
<%
	}
%>	
</form>
<%		
	if(hasData){
%>
<div style="position:absolute;top:580px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%	
	}
%>
<jsp:include page="/public/hintMessage.jsp" flush="true"/>

</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">
function do_Search() {
	mainFrm.userId.value = parent.contents.mainFrm.userId.value;
	if(mainFrm.userId.value == ""){
		alert("����ѡ���û�����ִ�в�ѯ");
		return;
	}
    mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
	document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}

function do_SavePrivi(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û���ʲ����ɹ�ѡ�����Ȳ�ѯ�ʲ����");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("û��ѡ���ʲ���𣬲��ܱ���Ȩ��");
		return;
	}
	mainFrm.action = "<%=AssetsURLList.ASSETS_PRIVI_SERVLET%>";
    mainFrm.act.value = "<%=AssetsActionConstant.SAVE_MTL_PRIVI%>";
    mainFrm.submit();
}

function do_DeletePrivi(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û���ʲ����ɹ�ѡ�����Ȳ�ѯ�ʲ����");
		return;
	}
	if(mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("û��ѡ�����ݣ�����ɾ��Ȩ��");
		return;
	}
	mainFrm.action = "<%=AssetsURLList.ASSETS_PRIVI_SERVLET%>";
    mainFrm.act.value = "<%=AssetsActionConstant.DEL_MTL_PRIVI%>";
    mainFrm.submit();
}

</script>
</html>
