<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.framework.security.dto.ServletConfigDTO" %>
<%@ page import="com.sino.soa.td.srv.assetsinfoupdate.dto.TDSrvEamSyschronizeDTO"%>
<%@ include file="/newasset/headerInclude.htm" %>
<%@ include file="/synchronize/headerInclude.jsp" %>
<html>
<head>
    <title>�ʲ�������Ϣͬ��</title>
    <link href="/WebLibary/css/css.css" type="text/css">
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
</head>
<%
	ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(application);
	String provinceCode = servletConfig.getProvinceCode();
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    TDSrvEamSyschronizeDTO  dto = (TDSrvEamSyschronizeDTO) request.getAttribute(WebAttrConstant.SYSCHRONIZE_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    Row row = null;
    
%>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('do_search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.soa.td.srv.assetsinfoupdate.servlet.TDAssetsUpdateServlet">
<jsp:include page="/message/MessageProcess"/>
    <script type="text/javascript">
        printTitleBar("TD�ʲ�������Ϣͬ��");
        var ArrAction0 = new Array(true, "��ѯ", "action_view.gif", "��ѯ", "do_search");
        var ArrAction1 = new Array(true, "ͬ��", "cancel.gif", "ͬ��", "do_syschronize");
        var ArrActions = new Array(ArrAction0, ArrAction1);
        printToolBar();

    </script>
	<input type="hidden" name="organizationName"  style="width:100%">
    <table width="100%" topmargin="0" border="0" bgcolor="#EFEFEF" style="width:100%">
        <tr>
            <td align="right" width="8%">��˾���ƣ�</td>
            <td width="40%"><select size="1" name="organizationId" style="width:80%"><%=dto.getOrganizationOpt()%></select></td>
            <td align="right" width="8%">EAM��ǩ��</td>
            <td align="left" width="12%"><input type="text" name="newBarcode" value="<%=dto.getNewBarcode()%>" style="width:100%"></td>
            <td align="right" width="10%">EAM�ʲ����ƣ�</td>
            <td align="left" width="12%"><input type="text" name="nameTo" value="<%=dto.getNameTo()%>" style="width:100%"></td>
        </tr>
        <tr>
            <td align="right" width="8%">EAM�ص㣺</td>
            <td width="20%"><input style="width:80%" type="text" name="newAssetsLocation" value="<%=dto.getNewAssetsLocation()%>"><a href="#" onclick="chooseSit();" title="���ѡ��ص�" class="linka">[��]</a></td>
            <td align="right" width="8%">ƥ���ˣ�</td>
            <td align="left" width="12%"><input class="readonlyInput" type="text" name="matchUserName" value="<%=dto.getMatchUserName()%>" style="width:100%" onclick="do_selectUser();" title="���ѡ���û�" readonly></td>
            <td align="right" width="10%">��Ŀ���ƣ�</td>
            <td align="left" width="12%"><input type="text" name="projectName" value="<%=dto.getProjectName()%>" style="width:100%" size="20"></td>
        </tr>
    </table>

    <div id="headDiv" style="position:absolute;width:100%;overflow:hidden;top:100px;padding:0px; margin:0px;">
        <table width="250%" class="headerTable" border="1" cellpadding="0" cellspacing="0">
            <tr height="22" onClick="executeClick(this)" style="cursor:hand">
                <td width="2%" align="center"><input type="checkbox" name="titleCheck" onPropertyChange="checkAll('titleCheck','subCheck')"></td>

				<td width="6%" align="center">��˾����</td>
				<td width="4%" align="center">�ʲ����</td>
                <td width="5%" align="center">MIS����</td>
                <td width="5%" align="center">EAM����</td>

                <td width="8%" align="center">MIS����</td>
                <td width="8%" align="center">EAM����</td>
                <td width="8%" align="center">MIS�ͺ�</td>
                <td width="8%" align="center">EAM�ͺ�</td>
				<td width="6%" align="center">MIS����</td>
				<td width="6%" align="center">EAM����</td>
				
                <td width="4%" align="center">��������</td>
                <td width="4%" align="center">�ʲ�ԭֵ</td>
                <td width="4%" align="center">ƥ����</td>
            </tr>
        </table>
    </div>

    <div id="dataDiv" style="overflow:scroll;height:368px;width:100%;position:absolute;top:93px;left:1px" align="left"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="250%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if (rows != null && rows.getSize() > 0) {
		for (int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
%>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="2%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>

				<td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("COMPANY_NAME")%>"></td>
                <td width="4%"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSET_ID")%>"></td>
                <td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("TAG_NUMBER")%>"></td>
                <td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("BARCODE")%>"></td>

				<td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
				<td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>
                <td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
                <td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>

				<td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("MANUFACTURER_NAME")%>"></td>
				<td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("EAMMANUFNAME")%>"></td>
				
				<td width="4%"><input type="text" class="finput2" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>
                <td width="4%"><input type="text"  class="finput3" readonly value="<%=row.getValue("COST")%>"></td>
                <td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("MATCH_USER_NAME")%>"></td>
            </tr>
<%
		}
	}
%>
        </table>
    </div>

	<input type="hidden" name="matchUserId" value="<%=dto.getMatchUserId()%>">
	<input type="hidden" name="act">
	<input type="hidden" name="flag" value="0">

</form>
<div id="pageNaviDiv" style="position:absolute;top:470px;left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>

<script type="text/javascript">

function do_search() {
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	document.mainFrm.submit();
}

function do_Export() {
	document.mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
	document.mainFrm.submit();
}

/**
* ���ܣ�ͬ������
*/
function do_syschronize() {
	if(!document.mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ�ٽ���ͬ��");
		return;
	}
	if(document.mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("����ѡ�������ٽ���ͬ��");
		return;
	}
	document.mainFrm.flag.value="1";
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	document.mainFrm.action = "/servlet/com.sino.soa.td.srv.assetsinfoupdate.servlet.TDAssetsUpdateServlet";
	document.mainFrm.act.value = "<%=WebActionConstant.SYSCHRONIZE_ACTION%>";
	document.mainFrm.submit();
}

function chooseSit() {
	var lookUpName = "<%=LookUpConstant.LOOK_UP_ASSETS_OBJECT%>";
	var dialogWidth = 47.5;
	var dialogHeight = 30;
	var projects = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
	if (projects) {
		mainFrm.newAssetsLocation.value = projects[0].workorderObjectLocation;
	}
}

function do_selectUser() {
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_USER%>";
	var dialogWidth = 44;
	var dialogHeight = 29;
	var userPara = "organizationId=<%=userAccount.getOrganizationId()%>";
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	if (objs) {
	var obj = objs[0];
		document.mainFrm.matchUserId.value = obj["userId"];
		document.mainFrm.matchUserName.value = obj["userName"];
	} else {
		document.mainFrm.matchUserId.value = "";
		document.mainFrm.matchUserName.value = "";
	}
}

</script>
</html>