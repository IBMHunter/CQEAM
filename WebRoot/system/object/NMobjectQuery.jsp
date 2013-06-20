<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil"%>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil"%>

<%@ include file="/newasset/headerInclude.htm" %>

<html>
<head>
    <title>�ص���Ϣ��ѯ</title>
</head>
<script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
<body leftmargin="0" topmargin="0" onload="initPage();" onkeydown="autoExeFunction('do_Search()')">
<jsp:include page="/message/MessageProcess"/>
<%=WebConstant.WAIT_TIP_MSG%>
<%
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	int orgId = userAccount.getOrganizationId();
	EtsObjectDTO dto = (EtsObjectDTO)request.getAttribute(QueryConstant.QUERY_DTO);
    boolean isNew= StrUtil.isEmpty(dto.getWorkorderObjectNo());
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
    Row row = null;
%>
<form method="post" name="mainFrm"  action="/servlet/com.sino.ams.system.object.servlet.NMCommonObjectServlet">

<input type="hidden" name="checkedObjectNo" id="checkedObjectNo" value="">
<script type="text/javascript">
    printTitleBar("�ص���Ϣ��ѯ");
    var ArrAction0 = new Array(true, "��ѯ", "action_view.gif", "��ѯ", "do_Search()");
    var ArrAction1 = new Array(true, "����", "toexcel.gif", "����", "do_Export()");
    var ArrAction2 = new Array(true, "��ӡ", "print.gif", "��ӡ", "doPrint()");
    //var ArrAction3 = new Array(true, "����", "action_draft.gif", "����", "do_CreateObject()");
    //var ArrAction4 = new Array(false, "ʧЧ", "action_cancel.gif", "ʧЧ", "do_disableObject()");
    //var ArrAction5 = new Array(false, "��Ч", "action_sign.gif", "��Ч", "do_EnableObject()");
    //var ArrAction6 = new Array(false, "ͬ��", "action_sign.gif", "ͬ��", "do_SYN()");
    //var ArrAction7 = new Array(true, "����", "copy.gif", "����" , "do_Transfer()");
    var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2
    //, ArrAction3, ArrAction4, ArrAction5,ArrAction6,ArrAction7
    );
    var ArrSinoViews = new Array();
    var ArrSinoTitles = new Array();
    printToolBar();

</script>
<table width="100%" border="0" bgcolor="#FFFFFF" id="table1">
    <tr>
        <td width="8%" align="right">��˾���ƣ�</td>
        <td width="17%"><select class="select_style1" style="width:100%" name="organizationId" <%if(isNew){%> onchange="do_ChangeCounty(this);" <%}%>><%=dto.getOrganizationOption()%></select></td>
        <td width="8%" align="right">�������ڣ�</td>
        <td width="17%"><input style="width:100%" type="text" class="input_style1"  name="startDate" value="<%=dto.getStartDate()%>" title="���ѡ����ʼ����" readonly  onclick="gfPop.fStartPop(startDate, endDate)"></td>
        <td width="8%" align="right">����</td>
        <td width="17%"><input style="width:100%" type="text" class="input_style1"  name="endDate"   value="<%=dto.getEndDate()%>"   title="���ѡ���������" readonly  onclick="gfPop.fEndPop(startDate, endDate)"></td>
        <td width="9%" align="right">���������գ�</td>
        <td width="16%" ><input type="checkbox" name="areaTypeIsNull" value="true" <%=dto.isAreaTypeIsNull() == true ? "checked" : " "%>></td>
    </tr>
    <tr>
		<td width="8%" align="right">��������</td>
		<td width="17%"><select class="select_style1"  style="width:100%" name="countyCode" ><%=dto.getCountyOption()%></select></td>
		<td width="8%" align="right">����������</td>
		<td width="17%"><select class="select_style1"  style="width:100%" name="areaType" ><%=dto.getAreaTypeOption() %></select></td>
		<td width="8%" align="right">�ص�רҵ��</td>
		<td width="17%">
			<select class="select_style1" style="width:100%" name="objectCategory"><%=dto.getObjCategoryOption()%></select>
        </td>
		<td width="9%" align="right"></td>
		<td width="16%"></td>
    </tr>
    <tr>
        <td width="8%" align="right">�ص���룺</td>
        <td width="17%"><input class="input_style1" style="width:100%" type="text" name="workorderObjectCode" value="<%=dto.getWorkorderObjectCode()%>"></td>
        <td width="8%" align="right">�ص����ƣ�</td>
        <td width="17%"><input class="input_style1" style="width:100%" type="text" name="workorderObjectName" value="<%=dto.getWorkorderObjectName()%>"></td>
        <td width="8%" align="right">��Ч״̬��</td>
        <td width="17%"><select class="select_style1" style="width:100%" name="enabled" id="enabled" onchange="do_SetControlBtnEnable();">
        <option value="">-��ѡ��-</option><option value="Y" selected>��Ч</option><option value="N">��Ч</option></select></td>
		<td width="9%" align="right"></td>
        <td width="16%" ></td>
    </tr>
</table>
    <input type="hidden" name="act">
    <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
<div id="headDiv" style="overflow:hidden;position:absolute;top:123px;left:1px;width:150%">
    <table class="headerTable" border="1" style="width:200%">
        <tr height="23px" onClick="executeClick(this)" style="cursor:pointer">
            <td width="2%" align="center"><input type="checkbox" name="titleCheck" class="headCheckbox" id="controlBox" onclick="checkAll('titleCheck','subCheck')"></td>

			<td width="7%" align="center">��˾����</td>
            <td width="10%" align="center">�ص����</td>
            <td width="10%" align="center">�ص�����</td>

            <td width="6%" align="center">��������</td>
            <td width="6%" align="center">�ص�רҵ</td>
            <%--<td width="5%" align="center">�Ƿ�TD</td>--%>
            <td width="10%" align="center">��վ��Ӫҵ�����</td>
            <%--<td width="6%" align="center">������</td>--%>

			<td width="6%" align="center">��������</td>
			<td width="6%" align="center">ʧЧ����</td>
			<%--<td width="6%" align="center">������</td>--%>
			<td width="6%" align="center">��������</td>
			<td width="6%" align="center">��������</td>
			<td width="8%" align="center">��γ��</td>
			
        </tr>
    </table>
</div>

<div id="dataDiv" style="overflow:scroll;height:66%;width:150%;position:absolute;top:115px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="200%" border="1" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
    if (hasData) {
		String objectNo = "";
        String hiddenObjectNo="";
        String canDisableHint="";
        for (int i = 0; i < rows.getSize(); i++) {
            row = rows.getRow(i);
			objectNo = row.getStrValue("WORKORDER_OBJECT_NO");
            boolean canDisable=row.getStrValue("CAN_DISABLE").equalsIgnoreCase("Y");
            hiddenObjectNo=canDisable?"":objectNo;
            canDisableHint=canDisable?"":"�õص������ʲ�,����ʧЧ��";

%>
        <tr height="22" style="cursor:pointer" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
<%
        canDisable=true;
        if(canDisable){
%>
            <td width="2%" title="<%=canDisableHint%>" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%>
            <input type="hidden" name="objectNo" value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>" />
            <input type="hidden" name="companyName" value="<%=row.getValue("ORG_NAME")%>" />
            <input type="hidden" name="hiddenObjectNo" value="<%=hiddenObjectNo%>" />
       		<input type="hidden" name="printNum" value="<%=row.getValue("PRINT_NUM")%>" /></td>
<%
        } else {
%>
				<td style="word-wrap:break-word" title="�õص������ʲ�,����ʧЧ��" height="22" width="2%" align="center" class="">
					<input  disabled="true" type="checkBox" name="workorderObjectNo"  value="<%=row.getValue("WORKORDER_OBJECT_NO")%>"> 
				</td>
<%            
        }
%>
            <td width="7%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("COMPANY")%>"></td>
            <td width="10%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
            <td width="10%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>

            <td width="6%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("COUNTY_NAME")%>"></td>
            <td width="6%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("OBJECT_CATEGORY_NAME")%>"></td>
            <%--<td width="5%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("IS_TD")%>"></td>--%>
            <td width="10%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("BTS_NO")%>"></td>
            <%--<td width="6%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("CREATION_USER")%>"></td>--%>

            <td width="6%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
            <td width="6%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("DISABLE_DATE")%>"></td>
            <%--<td width="6%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("UPDATED_USER")%>"></td>--%>
            <td width="6%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput2" readonly value="<%=row.getValue("LAST_UPDATE_DATE")%>"></td>
			<td width="6%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("VALUE")%>"></td>
			<td width="8%" onclick="show_Detail('<%=objectNo%>')"><input type="text" class="finput" readonly value="<%=row.getValue("LATITUDE_LONGITUDE")%>"></td>
		</tr>
<%
	    }   
	}
%>
    </table>
</div>
</form>
<%
	if(hasData){
%>
<div id="pageNaviDiv"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}	
%>

</body>
</html>
<iframe width=174 height=179 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>

<script type="text/javascript">
function initPage(){
	do_SetPageWidth();
	do_SetControlBtnEnable();
}

function do_SetControlBtnEnable(){
	var enabled = mainFrm.enabled.value;
	var enPic = 5;
	var disPic = 4;
	if(enabled == "Y" || enabled == ""){
        ShowSinoButton(disPic);
        HideSinoButton(enPic);
	} else if(enabled == "N"){
        HideSinoButton(disPic);
        ShowSinoButton(enPic);
	}
}

function do_SelectUser(){
	var lookUpName = "LOOK_UP_USER";
	var dialogWidth = 44;
	var dialogHeight = 29;
	var userPara = "organizationId=" + mainFrm.organizationId.value;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	if (objs) {
		var obj = objs[0];
		mainFrm.createdBy.value = obj["userId"];
		mainFrm.createdByName.value = obj["userName"];
	}
}


function do_Search() {
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}


function do_Export(){                  //����execl
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}
function do_TagNumber(){                  //����execl
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}


function show_Detail(workorderObjectNo) {
    var url = "/servlet/com.sino.ams.system.object.servlet.NMCommonObjectServlet";
	url += "?act=<%=WebActionConstant.DETAIL_ACTION%>";
	url += "&workorderObjectNo=" + workorderObjectNo;
    var factor = 0.7;
    var width = window.screen.availWidth * factor;
    var height = window.screen.availHeight * factor;
    var left = window.screen.availWidth * (1 - factor)/ 2;
    var top = window.screen.availHeight * (1 - factor)/ 2;
    var popscript = "width=" + width + "px,height=" + height + "px,top=" + top + "px,left=" + left + "px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no";
    window.open(url, 'commObj', popscript);
}

function do_CreateObject() {
    var url = "/servlet/com.sino.ams.system.object.servlet.NMCommonObjectServlet";
	url += "?act=<%=WebActionConstant.DETAIL_ACTION%>";
	url += "&workorderObjectNo=";
    var popscript = "width=650,height=540,top=100,left=150,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no";
    window.open(url, 'commObj', popscript);
}

function do_disableObject(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ��ִ�иò���");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
		alert("������ѡ��һ����¼,��ִ�иò���");
		return;
	}
    var count=0;
    var objectNos= getCheckedBoxValue("subCheck");
    var hiddenObjects=document.getElementsByName("hiddenObjectNo");
    for(var k=0;k<objectNos.length;k++){
        if(objectNos[k].indexOf(":")>0){
            var objectNo=objectNos[k].split(":")[1];
             for(var x=0;x<hiddenObjects.length;x++){
                 if(hiddenObjects[x].value==objectNo){
                    count++;
                    break;
                }
            }
        }
    }
    if(count>0){
        alert("��ѡ�ص������ʲ�,���ܽ���ʧЧ��");
        return ;
    }

	document.mainFrm.act.value = "<%=AMSActionConstant.DISABLED_ACTION%>";
	document.mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_EnableObject(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ��ִ�иò���");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
		alert("������ѡ��һ����¼,��ִ�иò���");
		return;
	}
	mainFrm.act.value = "<%=AMSActionConstant.ENABLE_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

var xmlHttp = null;

function do_ChangeCounty(obj) {
	var url = "/servlet/com.sino.ams.system.object.servlet.NMCommonObjectServlet";
	url += "?act=CHANGE_COUNTY";
	url += "&organizationId=" + obj.value;
	do_ProcessSimpleAjax(url, null);
}

/**
 * �����ص��б���������������ɼ�����������
 * �޸���ɡ�
 */
function do_ProcessResponse(responseContent){
	mainFrm.countyCode.outerHTML = "<select class=\"select_style1\" style=\"width:100%\" name=\"countyCode\">" + responseContent + "</select>";
}

function do_SYN(){             //ͬ���ص�
    if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
        alert("����ִ�в�ѯ��ִ�иò���");
        return;
    }
    if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
        alert("������ѡ��һ����¼,��ִ�иò���");
        return;
    }
    if(confirm("ȷ��ͬ���𣿼�����㡰ȷ������ť��������㡰ȡ������ť��"))  {
        mainFrm.act.value = "SYN";
        mainFrm.action = "/servlet/com.sino.ams.system.object.servlet.NMCommonObjectServlet";
        mainFrm.submit();
    }
}

//����
function do_Transfer() {
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
        alert("����ִ�в�ѯ��ִ�иò���");
        return;
    }
    if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
        alert("������ѡ��һ����¼,��ִ�иò���");
        return;
    }
    if(confirm("ȷ�ϴ����𣿼�����㡰ȷ������ť��������㡰ȡ������ť��"))  {
        mainFrm.act.value = "TRANSFER_ACTION";
        mainFrm.action = "/servlet/com.sino.ams.system.object.servlet.NMCommonObjectServlet";
        mainFrm.submit();
    }
}

function getLinePrintCount( checkObj ){
 	var printNumObj = checkObj.parentElement.children[3]; 
 	return printNumObj;
}

function getLineObjectNo( checkObj ){
 	var objectNoObj = checkObj.parentElement.children[1]; 
 	return objectNoObj;
}

function doPrint(){  
   	var allCheckObj = document.getElementsByName("subCheck");
   	var printNum = 0;
   	var checkedValue = "";
   	var hasCheck = false;
   	var isPrint = false;
   	var printMsg = "";
   	for (var i = 0; i < allCheckObj.length; i++) {
   		if (allCheckObj[i].type == "checkbox" && allCheckObj[i].checked) { 
   			//alert( allCheckObj[i].value );
        	if( !hasCheck ){
       			hasCheck = true;
  			}
  			checkedValue += ","  + getLineObjectNo( allCheckObj[i] ).value ;
  			printNum = getLinePrintCount( allCheckObj[i] ).value;
          	//alert( printNum );
          	if( Number( printNum ) > 0  ){
          		isPrint = true;
         	  	printMsg = allCheckObj[i].value + "��ӡ��" + printNum + "��. " ; 
          	}   
       }
    } 
    
    if( !hasCheck ){
    	alert( "��ѡ����Ҫ��ӡ�ص��ǩ�ļ�¼!" );
   		return false;
    }
    document.getElementById("checkedObjectNo").value = checkedValue.substring(1);
       
    if( isPrint ){
     	if( !confirm( "��ѡ�����д����Ѵ�ӡ���룬�Ƿ����?" ) ){
     		//window.open( "print" );
    		return;
     	} 
    }  
    
    //window.open( 'about:blank', 'newwindow', 'height=500,width=800,top=120,left=120,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no, status=no');
    
	mainFrm.act.value = "PRINT_ACTION";
	mainFrm.target = "newwindow";
    mainFrm.submit();
    mainFrm.target = "_self"; 
}


</script>