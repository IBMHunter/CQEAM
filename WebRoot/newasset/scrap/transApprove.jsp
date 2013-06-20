
<%@page import="java.util.HashMap"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsDictConstant" %>
<%@ page import="com.sino.ams.newasset.scrap.constant.ScrapURLListConstant"%>
<%@ page import="com.sino.ams.system.user.dto.SfUserRightDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<%  
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
	
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
    String appstatus = headerDTO.getTransStatus();
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
	String transId = headerDTO.getTransId();
    DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    DTOSet lineSetPri = (DTOSet) request.getAttribute(AssetsWebAttributes.PRIVI_DATA);//EXCEL����ʱ���벻�ɹ���DTOSET
    
    List list = (List)request.getAttribute("REMARK_LIST");
    
    boolean isDepAM = userAccount.isDptAssetsManager();
    DTOSet userDTO = userAccount.getUserRight();
    String roleName = "";
    Map  userRightMap = new HashMap();
    if( null != userDTO){
	    for (int i = 0;i<userDTO.getSize();i++) {
	        SfUserRightDTO userRight = (SfUserRightDTO)userDTO.getDTO(i);
	        roleName = userRight.getRoleName();
	        userRightMap.put(roleName,roleName);
	    }
    }
    boolean isDptMgr = userRightMap.containsValue("���ž���");
    int orgId = userAccount.getOrganizationId();
	int userId = userAccount.getUserId();
	String provinceCode = headerDTO.getServletConfig().getProvinceCode();
	int provinceOrgId = headerDTO.getServletConfig().getProvinceOrgId();
	int tdProvinceOrgId = headerDTO.getServletConfig().getTdProvinceOrgId();
    String isGroupPID = request.getAttribute(AssetsWebAttributes.IS_GROUP_PID).toString();//�Ƿ��й�˾�ۺϲ��������
    String isFinanceGroup = request.getAttribute(AssetsWebAttributes.IS_FINANCE_GROUP).toString();
    
    //EXCEL���
    //RowSet rows = (RowSet) request.getAttribute(WebAttrConstant.WORKORDER_LOC_ROWSET);
    boolean isMtlMana = userAccount.isMtlAssetsManager();
     
    DTOSet lineSetPri2 =  (DTOSet) request.getAttribute(AssetsWebAttributes.PRIVI_DATA);//EXCEL����ʱ���벻�ɹ���DTOSET
    
    String titleText="";
   	String titleBar="";
   	SfUserDTO userApp = (SfUserDTO) SessionUtil.getUserAccount(request);
   	if ("Y".equalsIgnoreCase(userApp.getIsTd())) {
   		titleText=headerDTO.getTransTypeValue()+"(TD)";
   		titleBar=headerDTO.getTransTypeValue()+"(TD)";
   	} else {
   		titleText=headerDTO.getTransTypeValue();
   		titleBar=headerDTO.getTransTypeValue();
   	}
    
%>
<head>
	 
	<title><%=titleText%></title>
    <script type="text/javascript" src="/WebLibary/js/test.js"></script>
    <script type="text/javascript" src="/WebLibary/js/json.js"></script>
	<script type="text/javascript" src="/WebLibary/js/util.js"></script>
	<script type="text/javascript" src="/WebLibary/js/util2.js"></script>
	<script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
	<script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
	<script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
	<script type="text/javascript" src="/WebLibary/js/api.js"></script>
	<script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
	<script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
	<script type="text/javascript" src="/WebLibary/js/AjaxProcess.js"></script>
	
	 <script type="text/javascript">
	 	/**
        var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "do_Cancel");
        var ArrAction2 = new Array(true, "��������", "action_sign.gif", "��������",  "doAppSubmit");         
        var ArrAction12 = new Array(true, "����", "action_cancel.gif", "����", "do_CancelOrder");
        var ArrAction3 = new Array(true, "����������¼", "action_viewstatus.gif", "����������¼",  "showOpinionDlg");
        var ArrAction5 = new Array(true, "��������", "actn023.gif", "��������", "do_ViewFlow") ;
        var ArrActions = new Array(ArrAction0, ArrAction2, ArrAction3 ,ArrAction5); 
        var ArrSinoViews = new Array();
        **/
        printToolBar();
    </script>
     
</head>
<body leftmargin="0" topmargin="0" onload="doLoad();initPage();" onbeforeunload="doBeforeUnload()" onunload="doUnLoad()">
<%@ include file="/flow/flowNoButton.jsp" %>
<form action="<%= ScrapURLListConstant.SCRAP_SERVELT %>" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<jsp:include page="/flow/include.jsp" flush="true"/>
 <%@ include file="/flow/flowPara.jsp"%>
<table border="0" class="queryTable" width="100%" style="border-collapse: collapse" id="table1">
	<tr>
		<td>
			<table width=100% border="0">
			    <tr>
			        <td align=right width=8%>���ݺţ�</td>
			        <td width=13%>
						<input type="text" name="transNo" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getTransNo()%>">
					</td>
			        <td align=right width=8%>�������ͣ�</td>
			        <td width=13%>
						<input type="text" name="transTypeValue" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getTransTypeValue()%>">
					</td>
			        <td align=right width=8%>�������</td>
			        <td width=13%> <!-- onclick="do_SelectCreateGroup();" -->
						<input name="fromGroupName" class="input_style2" readonly style="width:100%; cursor:hand" value="<%=headerDTO.getFromGroupName()%>" title="���ѡ�����ġ��������"  ></td>
			        <td align=right width=8%>���ϲ��ţ�</td>
			        <td width=28%>
			        	<input name="fromDeptName" class="input_style2" readonly style="width:100%; cursor:hand" value="<%=headerDTO.getFromDeptName()%>" >
						<input type="hidden" name="fromDept" class="input_style2" readonly style="width:100%; cursor:hand" value="<%=headerDTO.getFromDept()%>" >
						
			        </td>
			    </tr>
			    <tr>
			        <td align=right width=8%>�����ˣ�</td>
			        <td width=13%>
						<input type="text" name="created1" class="input_style2" readonly style="width:100%" value="<%=headerDTO.getCreated()%>" >
					</td>
			        <td align=right>�������ڣ�</td>
			        <td>
						<input name="creationDate" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getCreationDate()%>" ></td>
			        <td align=right>��˾���ƣ�</td>
			        <td>
						<input name="userCompanyName" class="input_style2" readonly style="width:100%; " value="<%=headerDTO.getFromCompanyName()%>"></td>
					<td align=right width=8%>�ʲ����ࣺ</td>
			        <td width=17%> 
			        	<input name="faContentName"  readonly style="width:100%;" class="input_style2" value="<%=headerDTO.getFaContentName()%>" size="20" /> 
			        </td>
			    </tr>
			     <tr>
			     	<td align=right>�����̶ȣ�</td>
			        <td ><input name="emergentLevel" readonly style="width:100%;" class="input_style2" value="<%=headerDTO.getEmergentLevel()%>"></td>
                    <td align=right width=8%>����������</td>
			        <td width=17%><input name="accessSheet" readonly style="width:100%;" class="input_style2" value="<%=headerDTO.getAccessSheet()%>"></td>
                    <td width="8%" height="60" align="right">����˵����</td>
			    	<td width="92%" height="60" colspan="4"><textarea name="createdReason" readonly style="width:100%;" class="input_style2"><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>
			    <%-- 
			    <tr>
			    	<td width="8%" height="60" align="right">����˵����</td>
			    	<td width="92%" height="60" colspan="7"><textarea name="createdReason" style="width:100%;height:100%" class="input_style1"><%=headerDTO.getCreatedReason()%></textarea></td>
			    </tr>--%>
			</table>
		</td>
	</tr>
</table>
<input type="hidden" name="fromGroup" value="<%=headerDTO.getFromGroup()%>">
<input type="hidden" name="fromOrganizationId" value="<%=headerDTO.getFromOrganizationId()%>">
<input type="hidden" name="transType" value="ASS-DIS">
<input type="hidden" name="transferType" value="<%=headerDTO.getTransferType()%>">
<input type="hidden" name="created" value="<%=headerDTO.getCreated()%>">
<input type="hidden" name="createdBy" value="<%=headerDTO.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=transId%>">
<input type="hidden" name="isGroupPID" value="<%=isGroupPID%>">
<input type="hidden" name="toDept" value="<%=headerDTO.getToDept()%>">
<input type="hidden" name="toGroup" value="<%=headerDTO.getToGroup()%>">
<input type="hidden" name="act" value="">
<input type="hidden" name="isThred" value="">

<input type="hidden" name="excel" value="">
	<%
	String[] widthArr = {  "8%" , "10%", "10%", "10%", "6%",   "6%","15%","6%","15%"  }; 
	int arrIndex = 0;
	%>
	
    <div id="headDiv" style="overflow:hidden;position:absolute;top:165px;left:1px;width:990px">
		<table class="headerTable" border="1" width="140%">
	        <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
	            <td align=center width="3%"><input type="checkbox" name="mainCheck" value="" onPropertyChange="checkAll('mainCheck','subCheck')"></td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">��ǩ��</td>
                <td align=center width="<%= widthArr[ arrIndex++ ] %>">��������</td>
                <td align=center width="<%= widthArr[ arrIndex++ ] %>">�ʲ�����</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">�ʲ��ͺ�</td>
                <td align=center width="<%= widthArr[ arrIndex++ ] %>">���ϳɱ�</td>
                
                <td align=center width="<%= widthArr[ arrIndex++ ] %>">��������</td>
				<td align=center width="<%= widthArr[ arrIndex++ ] %>">���ڵص�</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">������</td>
	            <td align=center width="<%= widthArr[ arrIndex++ ] %>">���β���</td>
				<td style="display:none">�������ֶ�</td>
	        </tr>
		</table>
	</div>
	<div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:187px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if ( null != lineSet && !lineSet.isEmpty()) { 
			AmsAssetsTransLineDTO lineDTO = null;
			String barcode = "";
			for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				barcode = lineDTO.getBarcode();
%>
            <tr class="dataTR" onClick="executeClick(this)" style="cursor:hand" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
				<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck0" value="<%=barcode%>"></td>
				<td width="8%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput2" readonly value="<%=barcode%>"></td>
				<td width="10%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" ><select name="rejectType" id="rejectType<%=i%>" style="width:100%" disabled="disabled" class="select_style1" onChange="do_SetDisType(this)"><%= lineDTO.getRejectTypeOpt()%></select></td>
				<td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="assetsDescription" id="assetsDescription<%=i%>" class="finput" readonly value="<%=lineDTO.getAssetsDescription()%>"></td>
				<td width="10%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="modelNumber" id="modelNumber<%=i%>" class="finput" readonly value="<%=lineDTO.getModelNumber()%>"></td>
                <td width="6%" align="right"><input type="text" name="retirementCost" id="retirementCost<%=i%>" class="finputNoEmpty" value="<%=lineDTO.getRetirementCost()%>" onchange="do_setQuantity();"></td>
                
                <td width="6%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="datePlacedInService" id="datePlacedInService<%=i%>" class="finput2" readonly value="<%=lineDTO.getDatePlacedInService()%>"></td>
                <td width="15%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldLocationName" id="oldLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldLocationName()%>"></td>
				<td width="6%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityUserName" id="oldResponsibilityUserName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityUserName()%>"></td>
				<td width="15%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input type="text" name="oldResponsibilityDeptName" id="oldResponsibilityDeptName<%=i%>" class="finput" readonly value="<%=lineDTO.getOldResponsibilityDeptName()%>"></td>
				<td style="display:none">
					<input type="hidden" name="oldResponsibilityDept" id="oldResponsibilityDept<%=i%>" value="<%=lineDTO.getOldResponsibilityDept()%>">
					<input type="hidden" name="oldLocation" id="oldLocation<%=i%>" value="<%=lineDTO.getOldLocation()%>">
					<input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>" value="<%=lineDTO.getOldResponsibilityUser()%>">
				</td>
            </tr>
<%
			}
		}

%>
             </table>
       </div>
     
<%--<jsp:include page="/newasset/transferLine.jsp" flush="true"/>--%>
    
</form>
<jsp:include page="/public/hintMessage.jsp" flush="true"/>

</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<script type="text/javascript">
     
function do_SubmitOrder() {
    if (!validate()) {
        return;
    }
	var transType = "<%=transType%>";
	var transferType = "<%=transferType%>";
	var provinceCode = "<%=provinceCode%>";
    //�жϱ��ϳɱ�
    if (transType == "<%=AssetsDictConstant.ASS_DIS%>") {
        var isVilidate = do_valiQuantity();
        if (!isVilidate) {
            return;
        }
    }
    if(transferType != ""){
		if(transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>"){
			mainFrm.toDept.value = mainFrm.fromDept.value;
		} else {
			var depts = document.getElementsByName("responsibilityDept");
			mainFrm.toDept.value = depts[0].value;
		}
	}
    var orgId = "<%=orgId%>";
	var userId = "<%=userId%>";
    var groupId = mainFrm.fromGroup.value;
    var procdureName = mainFrm.procdureName.value;
    var flowCode = "";
	if(transferType == "<%=AssetsDictConstant.TRANS_BTW_COMP%>"){
		flowCode = mainFrm.faContentCode.value;//�������������
        flowCode = "OTHER";
    }
    if(transType == "<%=AssetsDictConstant.ASS_SUB%>"){
        flowCode = mainFrm.faContentCode.value;
    }
    var isDepAM = "<%=isDepAM%>";
//    do_isProfessionalGroup();
    var isGroupPID = mainFrm.isGroupPID.value;
    var isDptMgr ="<%=isDptMgr%>";
//-------���ż�����ж��Ƿ�������������--------
    if (transferType != "" && transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>") {
        var fDept = mainFrm.fromDept.value;
        var tDepts = document.getElementsByName("responsibilityDept");
        var tDept = tDepts[0].value;
        do_ThredDept(fDept, tDept);
        if(confirm("ȷ��������Ϣ����ȷ���ύ��")){
		} else {
			return;
		}
    }
    var isThred = document.mainFrm.isThred.value;                                                                                                             //--------------------------------------
//-----------------------------------------
    if (transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>" || (transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>" && isThred == "N")) {
        <%--document.mainFrm.transferType.value = "<%=transferType%>";--%>
        if ((isDepAM == "true" && isGroupPID == "false")  || (isDptMgr == "true" && isGroupPID == "false" && transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>")){
            flowCode = "MANAGER";
        }
        if ((isGroupPID == "false" && isDepAM == "false" && isDptMgr == "false") || (isGroupPID == "true" && isDepAM == "false" && isDptMgr == "false" && transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>")){
            flowCode = "OTHER";
        }
        if ( ( (isDptMgr == "true" && isGroupPID == "false") || (isGroupPID == "true" && isDepAM == "false" && isDptMgr == "false") ) && (transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>")) {
            flowCode = "MULTI";
        }
        var faContentCode = mainFrm.faContentCode.value;

//        alert("isDepAM--" + isDepAM + "        isGroupPID--" + isGroupPID + "     isDptMgr--" + isDptMgr + "    faContentCode--" + faContentCode);


        if (isDepAM == "true" && isGroupPID == "true" && faContentCode == "MGR-ASSETS" && transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>") {
            flowCode = "MUL-MANA";
        }

        if (isDepAM == "true" && isGroupPID == "true" && transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>") {
            if(faContentCode == "NET-ASSETS" || faContentCode == "MAR-ASSETS"){
                flowCode = "MANAGER";
            }
        }
        if (isDepAM == "true" && isGroupPID == "true" && transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>") {
            flowCode = "MANAGER";
        }
        if (isDptMgr == "true" && isGroupPID == "true"&& transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>") {
            flowCode = "FINANCE";
        }
         if (isDptMgr == "true" && isGroupPID == "true"&& transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>") {
            flowCode = "MANAGER";
        }
        <%--if (transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>" && isDptMgr == "true") {--%>
//            flowCode = "OTHER";
//        }
    } else if (transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>" && isThred == "Y") {
        <%--document.mainFrm.transferType.value = "<%=AssetsDictConstant.TRANS_INN_DEPT%>";--%>
        if (isDepAM == "true" || isDptMgr == "true"){
            flowCode = "MANAGER";
        }
        if ((isGroupPID == "false" && isDepAM == "false" && isDptMgr == "false") || (isGroupPID == "true" && isDepAM == "false" && isDptMgr == "false")){
            flowCode = "OTHER";
        }
    }
    if (transType == "<%=AssetsDictConstant.ASS_DIS%>") {
        var faContentCode = mainFrm.faContentCode.value;
        var isMtlMana = "<%=isMtlMana%>";
        <% if ("Y".equalsIgnoreCase(userApp.getIsTd())) { %>
        	if (orgId == "<%=tdProvinceOrgId%>" && faContentCode == "MGR-ASSETS" && isGroupPID == "true") {
            flowCode = "P_MTL_MGR";
        } else if (orgId == "<%=tdProvinceOrgId%>" && faContentCode == "NET-ASSETS" && isGroupPID == "true") {
            flowCode = "P_MTL_NET";
        } else if (orgId == "<%=tdProvinceOrgId%>" && faContentCode == "MAR-ASSETS" && isGroupPID == "true") {
            flowCode = "P_MTL_MAR";
        } else if (orgId != "<%=tdProvinceOrgId%>" && faContentCode == "MGR-ASSETS" && isMtlMana == "true") {
            flowCode = "C_MTL_MGR";
        } else if (orgId != "<%=tdProvinceOrgId%>" && faContentCode == "NET-ASSETS" && isMtlMana == "true") {
            flowCode = "C_MTL_NET";
        } else if (orgId != "<%=tdProvinceOrgId%>" && faContentCode == "MAR-ASSETS" && isMtlMana == "true") {
            flowCode = "C_MTL_MAR";
        } else {
            flowCode = "OTHER";
        }
        <%} else { %>

        if (orgId == "<%=provinceOrgId%>" && faContentCode == "MGR-ASSETS" && isMtlMana == "true") {
            flowCode = "P_MTL_MGR";
        } else if (orgId == "<%=provinceOrgId%>" && faContentCode == "NET-ASSETS" && isMtlMana == "true") {
            flowCode = "P_MTL_NET";
        } else if (orgId == "<%=provinceOrgId%>" && faContentCode == "MAR-ASSETS" && isMtlMana == "true") {
            flowCode = "P_MTL_MAR";
        } else if (orgId == "<%=provinceOrgId%>" && faContentCode == "MGR-ASSETS" && isMtlMana == "true") {
            flowCode = "C_MTL_MGR";
        } else if (orgId == "<%=provinceOrgId%>" && faContentCode == "NET-ASSETS" && isMtlMana == "true") {
            flowCode = "C_MTL_NET";
        } else if (orgId == "<%=provinceOrgId%>" && faContentCode == "MAR-ASSETS" && isMtlMana == "true") {
            flowCode = "C_MTL_MAR";
        } else {
            flowCode = "OTHER";
        }
        <% } %>
    }
    if(transType == "<%=AssetsDictConstant.ASS_SHARE %>" || transType == "<%=AssetsDictConstant.ASS_SELL %>"  || transType == "<%=AssetsDictConstant.ASS_RENT %>" || transType == "<%=AssetsDictConstant.ASS_DONA %>" ){
			if(isDptMgr=="true" ){
	     		 if(orgId != "<%=provinceOrgId%>"  && faContentCode=="NET-ASSETS" && isGroupPID == "false"){
		             flowCode="C-NET";
		         }else if(orgId == "<%=provinceOrgId%>" && isGroupPID == "true"){
		         	 flowCode="END";
		         } else if(orgId != "<%=provinceOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "false"){
		             flowCode="C-MAR" ;
		         } else if (orgId != "<%=provinceOrgId%>"  && faContentCode=="MGR-ASSETS" && isGroupPID == "false"){
		             flowCode="C-MGR";
		         }else if((orgId != "<%=provinceOrgId%>"  && faContentCode=="NET-ASSETS" && isGroupPID == "true") || (orgId == "<%=provinceOrgId%>" && faContentCode=="NET-ASSETS" && isGroupPID == "false")) {
		             flowCode="P-NET" ;
		         } else if((orgId != "<%=provinceOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "true") || (orgId == "<%=provinceOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "false")) {
		             flowCode="P-MAR";
		         }else if((orgId != "<%=provinceOrgId%>" && faContentCode=="MGR-ASSETS" && isGroupPID == "true") || (orgId == "<%=provinceOrgId%>" && faContentCode=="MGR-ASSETS" && isGroupPID == "false")){
		             flowCode="P-MGR";
		         }
	        }
	 }
    if(provinceCode == "<%=AssetsDictConstant.PROVINCE_CODE_SX%>"){//ɽ��
		if(transType == "<%=AssetsDictConstant.ASS_DIS%>"){//����
			flowCode = mainFrm.faContentCode.value;
		} else if(transType == "<%=AssetsDictConstant.ASS_CLR%>"){//����
			flowCode = mainFrm.faContentCode.value;
		} else if(transType == "<%=AssetsDictConstant.ASS_FREE%>"){//����
			flowCode = mainFrm.faContentCode.value;
		}
	}
    var paramObj = new Object();
    
    alert( paramObj.groupId );
    paramObj.orgId = orgId;
    paramObj.useId = userId;
    paramObj.groupId = groupId;
    paramObj.procdureName = procdureName;
    paramObj.flowCode = flowCode;
//    alert(flowCode);
//    alert(mainFrm.transferType.value);
    paramObj.submitH = 'submitH()';
    assign(paramObj);
}
 

function initPage() {
    window.focus();
    do_SetPageWidth();
    setFrmReadonly("mainFrm");
    do_ControlProcedureBtn();
    /**
	var fromGroup = mainFrm.fromGroup.value;
	if(fromGroup == ""){
		do_SelectCreateGroup();
	}
	**/
}

      
function do_AppValidate() {
    var isValid = true;
    var transType = mainFrm.transType.value;
    
    //isValid = validateLine();
     
    if( isValid ){
        var fieldLabels = "�������;ԭ��";
        var fieldNames = "fromGroupName;createdReason";
        var validateType = EMPTY_VALIDATE;
        if (transferType) {
            if (transferType != "<%=AssetsDictConstant.TRANS_INN_DEPT%>") {
                fieldLabels += ";���벿��";
                fieldNames += ";responsibilityDeptName";
            } else {
                fieldLabels += ";����ص�;��������";
                fieldNames += ";assignedToLocationName;responsibilityUserName";
            }
        } else {
            if (transType == "<%=AssetsDictConstant.ASS_SUB%>") {
                fieldLabels = "�������;�������";
                fieldNames = "lossesName;lossesDate";
            }
        }
    }
    isValid = formValidate(fieldNames, fieldLabels, validateType);
    
    //alert( isValid );
    return isValid;
}
function validate() {
	alert( 1 );
	return true; 
}

function do_ShowDetail(td){
	var transType = mainFrm.transType.value;
	tr = td.parentNode;
	cells = tr.cells;
	var cell = cells[1];
	if(transType == "<%=AssetsDictConstant.ASS_SUB%>"){
		cell = cells[3];
	}
	var barcode = cell.childNodes[0].value;
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=800,height=360,left=70,top=100";
	window.open(url, winName, style);
}

function do_CancelOrder() {
	if(confirm("����׼�����������ݣ�ȷ���𣿼���������ȷ������ť������������ȡ������ť!")){
		mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
		mainFrm.submit();
	}
}
 

 var  al=0;
function do_exportToExcel() {
	mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
	mainFrm.submit();
}
   
function do_setQuantity() {
    var length = document.getElementsByName("retirementCost").length;
    for (i = 0; i < length; i++) {
        var retirementCost = document.getElementById("retirementCost" + i).value;
        var cost = document.getElementById("cost" + i).value;
        if (retirementCost < 0) {
            alert("���ϳɱ�����>0");
            document.getElementById("retirementCost" + i).value = "";
            break;
        } else if (retirementCost > cost) {
            alert("���ϳɱ�����<=�ʲ�ԭֵ");
            document.getElementById("retirementCost" + i).value = "";
            break;
        }
    }
}
function do_valiQuantity(){
    var isVilidate = true;
    var rows = dataTable.rows;
    var rowCount = rows.length;
    for (var i =0; i<rowCount; i++) {
        var cost = document.getElementById("dataTable").rows[i].cells[5].childNodes[0].value;
        var retirementCost = document.getElementById("dataTable").rows[i].cells[7].childNodes[0].value;
        if (retirementCost<0) {
            alert("���ϳɱ�����>0");
            document.getElementById("dataTable").rows[i].cells[7].childNodes[0].value = "";
            isVilidate = false;
        } else if (retirementCost > cost) {
            alert("���ϳɱ�����<=�ʲ�ԭֵ");
            document.getElementById("dataTable").rows[i].cells[7].childNodes[0].value = "";
            isVilidate = false;
        }
    }
    return isVilidate;
}
     
function do_excel() {
    if(transferType == "" || (transferType != "" && transferType != "<%=AssetsDictConstant.TRANS_BTW_COMP%>")){
		var fromDept = mainFrm.fromDept.value;
		if(fromDept == ""){
			alert("����ѡ���ţ���ѡ���ʲ���");
			mainFrm.fromDept.focus();
			return;
		}
	}
	
    if(mainFrm.faContentCode.value == ""){
		alert("����ѡ���ʲ����࣬��ѡ���ʲ���");
		mainFrm.faContentCode.focus();
		return;
	}
	
	/**
	if(mainFrm.financeProp.value == ""){
		alert("����ѡ���ʲ����࣬��ѡ���ʲ���");
		mainFrm.financeProp.focus();
		return;
	} 
	**/
	
    var url="/workorder/bts/upFile.jsp";
    var dialogStyle = "dialogWidth=18;dialogHeight=6;help=no;status=no;center=yes;toolbar=no;menubar=no;resizable=no;scroll=no";
    var aa=window.showModalDialog(url,"",dialogStyle);
    alert(aa)
    if (aa) {
        document.mainFrm.excel.value = aa;
        document.mainFrm.act.value = "excel";
        document.mainFrm.submit();
    }
}

//��������֤ - sj add 
function validateLine(){
	var isValid = true;
	if (dataTable.rows.length == 0 || (dataTable.rows[0].style.display == 'none' && dataTable.rows.length == 1)) {
        alert("û��ѡ�������ݣ���ѡ�������ݣ�");
        isValid = false;
    } 
    return isValid;
}

function do_Save_app() {
	//alert( "save app" ); 
	try{ 
		var actObj = document.getElementById("act");
		actObj.value = "SAVE_ACTION"; 
		document.forms[0].submit();
		document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
	}catch(ex){
		alert( ex.message );
	}finally{ 
	} 
}


/**
 *����:���ñ�������Ԫ�ز�����
 *����:����
 */
function setFrmReadonly(frm) {
    var frmObj = eval('document.' + frm);
    for (var i = 0; i < frmObj.length; i++) {
    	var obj = frmObj.elements[i];
    	var objType = obj.type;
        var fieldType = obj.type;
        obj.readOnly = true; 
        if( objType == "text" || objType == "password" || objType == "textarea" ){
        	obj.onclick = null;
        } 
        
        if( fieldType == "checkbox" ){
        	obj.disabled = true;
        }
        
         if( fieldType == "select" ){
        	obj.disabled = true;
        }
    }
} 

function doAppSubmit(){ 
	do_Div_Complete_Start(); 
	document.getElementById("$$$disableMsg$$$").style.visibility = "visible";
}


function setAttachmentConfig(){
    var attachmentConfig = new AttachmentConfig();
    attachmentConfig.setOrderPkName("transId");
    return attachmentConfig;
}

</script>