<%@ page import="com.sino.ams.synchronize.dto.EamSyschronizeDTO" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>

<html>
<head>
    <title>�ʲ��䶯ֱ��ͬ��</title>
    <link href="/WebLibary/css/main.css" type="text/css">
    <link href="/WebLibary/css/eam.css" type="text/css">
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
</head>
<%
	ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(application);
	String provinceCode = servletConfig.getProvinceCode();
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    EamSyschronizeDTO dto = (EamSyschronizeDTO) request.getAttribute(WebAttrConstant.SYSCHRONIZE_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    Row row = null;
    
%>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('do_search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.synchronize.servlet.AssetsUpdateServlet">
<jsp:include page="/message/MessageProcess"/>
    <script type="text/javascript">
        printTitleBar("�ʲ��䶯ֱ��ͬ��");
//        var ArrAction0 = new Array(true, "��ѯ", "action_view.gif", "��ѯ", "do_search");
//        var ArrAction1 = new Array(true, "ͬ��", "cancel.gif", "ͬ��", "do_syschronize");
//        var ArrActions = new Array(ArrAction0, ArrAction1);
//        printToolBar();

    </script>
<%
	if(provinceCode.equals(DictConstant.PROVINCE_CODE_JIN)){
%>
    <table width="100%" border="0" style="width:100%;top:0">
        <tr>
            <td align="right" width="8%">��˾���ƣ�</td>
            <td colspan="3" width="40%"><select size="1" class="select_style1" name="organizationId" style="width:100%"><%=dto.getOrganizationOpt()%></select></td>
            <td align="right" width="8%">EAM��ǩ��</td>
            <td align="left" width="12%"><input type="text" class="input_style1" name="newBarcode" value="<%=dto.getNewBarcode()%>" style="width:100%"></td>
            <td align="right" width="10%">EAM�ʲ����ƣ�</td>
            <td align="left" width="12%"><input type="text" class="input_style1" name="nameTo" value="<%=dto.getNameTo()%>" style="width:100%"></td>

        </tr>
        <tr>
            <td align="right" width="8%">EAM�ص㣺</td>
            <td width="20%"><input style="width:80%" class="input_style1" type="text" name="newAssetsLocation" value="<%=dto.getNewAssetsLocation()%>"><a href="#" onclick="chooseSit();" title="���ѡ��ص�" class="linka">[��]</a></td>
            <td align="right" width="8%">�ʲ����</td>
            <td align="left" width="12%">
                <select size="1" name="netManger" style="width:100%" class="select_style1">
                    <option>��ѡ��
                    <option value="NET" <%=dto.getNetManger().equals("NET") ? "selected" : ""%> >�������ʲ�</option>
                    <option value="MANG" <%=dto.getNetManger().equals("MANG") ? "selected" : ""%>>�������ʲ�</option>
                </select>
			</td>
            <td align="right" width="8%">ƥ����</td>
            <td align="left" width="12%"><input class="input_style1" type="text" name="matchUserName" value="<%=dto.getMatchUserName()%>" style="width:100%" onclick="do_selectUser();" title="���ѡ���û�" readonly></td>
            <td align="right" width="10%">��Ŀ���ƣ�</td>
            <td align="left" width="12%"><input type="text" class="input_style1" name="projectName" value="<%=dto.getProjectName()%>" style="width:100%" size="20"></td>

        </tr>
        <tr>
             <td colspan="8" width="100%" align="right">
                <img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="����">
                <img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ">
                <img src="/images/eam_images/synchronize.jpg" style="cursor:'hand'" onclick="do_syschronize();" alt="ͬ��">
             </td>
        </tr>
    </table>
<%	
	} else {
%>
    <table width="100%" border="0" style="width:100%;top:0">
        <tr>
            <td align="right" width="8%">��˾���ƣ�</td>
            <td width="12%"><select size="1" class="select_style1" name="organizationId" style="width:100%"><%=dto.getOrganizationOpt()%></select></td>
            <td align="right" width="8%">���β��ţ�</td>
            <td width="30%"><select size="1" class="select_style1" name="deptCode" style="width:80%"><%=dto.getDeptNameOption()%></select></td>
            <td align="right" width="8%">EAM��ǩ��</td>
            <td align="left" width="12%"><input type="text" class="input_style1" name="newBarcode" value="<%=dto.getNewBarcode()%>" style="width:100%"></td>
            <td align="right" width="10%">EAM�ʲ����ƣ�</td>
            <td align="left" width="12%"><input type="text" class="input_style1" name="nameTo" value="<%=dto.getNameTo()%>" style="width:100%"></td>
        </tr>
        <tr>
            <td align="right" width="8%">�ʲ����</td>
            <td align="left" width="12%">
                <select size="1" name="netManger" class="select_style1" style="width:100%">
                    <option>��ѡ��
                    <option value="NET" <%=dto.getNetManger().equals("NET") ? "selected" : ""%> >�������ʲ�</option>
                    <option value="MANG" <%=dto.getNetManger().equals("MANG") ? "selected" : ""%>>�������ʲ�</option>
                </select>
            </td>
            <td align="right" width="8%">EAM�ص㣺</td>
            <td width="30%"><input style="width:80%" type="text" class="input_style1" name="newAssetsLocation" value="<%=dto.getNewAssetsLocation()%>"><a href="#" onclick="chooseSit();" title="���ѡ��ص�" class="linka">[��]</a></td>
            <td align="right" width="8%">ƥ����</td>
            <td align="left" width="12%"><input  type="text" class="input_style2" name="matchUserName" value="<%=dto.getMatchUserName()%>" style="width:100%" onclick="do_selectUser();" title="���ѡ���û�" readonly></td>
            <td align="right" width="10%">��Ŀ���ƣ�</td>
            <td align="left" width="12%"><input type="text" class="input_style1" name="projectName" value="<%=dto.getProjectName()%>" style="width:100%" size="20"></td>
        </tr>
        <tr>
           <td colspan="8" width="100%" align="right" class="queryTableButton">
                <img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="����">
                <img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ">
                <img src="/images/eam_images/synchronize.jpg" style="cursor:'hand'" onclick="do_syschronize();" alt="ͬ��">
           </td>
        </tr>
    </table>
<%
	}
%>
    <div id="headDiv" style="position:absolute;width:840px;overflow:hidden;top:96px;padding:0px; margin:0px;">
        <table width="240%" class="headerTable" border="1" cellpadding="0" cellspacing="0">
            <tr height="22" onClick="executeClick(this)" style="cursor:hand">
                <td width="2%" align="center"><input type="checkbox" name="titleCheck" onPropertyChange="checkAll('titleCheck','subCheck')"></td>

				<td width="4%" align="center">�ʲ����</td>
                <td width="5%" align="center">MIS����</td>
                <td width="5%" align="center">EAM����</td>
				<td width="6%" align="center">��˾����</td>

                <td width="8%" align="center">MIS����</td>
                <td width="8%" align="center">EAM����</td>
                <td width="8%" align="center">MIS�ͺ�</td>
                <td width="8%" align="center">EAM�ͺ�</td>

                <td width="12%" align="center">MIS�ص�</td>
                <td width="12%" align="center">EAM�ص�</td>
                <td width="4%" align="center">MIS������</td>
                <td width="4%" align="center">EAM������</td>

                <td width="4%" align="center">��������</td>
                <td width="4%" align="center">�ʲ�ԭֵ</td>
                <td width="4%" align="center">ƥ����</td>
            </tr>
        </table>
    </div>

    <div id="dataDiv" style="overflow:scroll;height:368px;width:857px;position:absolute;top:119px;left:1px" align="left"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="240%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if (rows != null && rows.getSize() > 0) {
		for (int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
%>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="2%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>

                <td width="4%"><input type="text" class="finput2" readonly value="<%=row.getValue("ASSET_ID")%>"></td>
                <td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("TAG_NUMBER")%>"></td>
                <td width="5%"><input type="text" class="finput2" readonly value="<%=row.getValue("BARCODE")%>"></td>
				<td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("COMPANY_NAME")%>"></td>

				<td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
				<td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>
                <td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
                <td width="8%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>

				<td width="12%"><input type="text" class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION")%>"></td>
				<td width="12%"><input type="text" class="finput" readonly value="<%=row.getValue("LOCATION_CODE")%>"></td>
                <td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("OLD_ASSIGNED_TO_NAME")%>"></td>
                <td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("NEW_USER_NAME")%>"></td>

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
</html>

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
	document.mainFrm.action = "/servlet/com.sino.ams.synchronize.servlet.AssetsUpdateServlet";
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