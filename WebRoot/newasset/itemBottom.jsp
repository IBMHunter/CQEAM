<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�豸̨��ά��--������ҳ</title>
<script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
</head>
<body leftmargin="0" topmargin="0" onload="initPage()">
<script type="text/javascript">
    printTitleBar("�ֶ�ά���޸���");
</script>
<%
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	Object obj=session.getAttribute("DWZCGLY");
	String type="";
	if(obj!=null){
		type=obj.toString();
		session.removeAttribute("DWZCGLY");
	}
%>

<form name="mainFrm">
    <div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:21px;left:0px;width:100%">
        <table border=1 width="400%" class="headerTable">
            <tr style="height:22px">
             <%
            	if(type.equals("dwzcgly")){
            		%>
 
                <td width="5%" align="center">ʵ�ﲿ��</td>
                <td width="5%" align="center">ʹ�ò���</td>
                <td width="5%" align="center">ʹ����</td>
                
                <td width="10%" align="center">��עһ</td>
                <td width="10%" align="center">��ע��</td>
                <td width="65%" align="center"></td>
                	
            		<%
            	}else{
            		%>
            		
                <td width="3%" align="center">�豸����</td>
                <td width="3%" align="center">����ͺ�</td>
                <td width="5%" align="center">�ص�����</td>
                <td width="5%" align="center">���β���</td>
                <td width="3%" align="center">������</td>
                <td width="5%" align="center">ʵ�ﲿ��</td>
                <td width="5%" align="center">ʹ�ò���</td>
                <td width="3%" align="center">ʹ����</td>
                <td width="4%" align="center">����</td>
                <td width="2%" align="center">������λ</td>
                <td width="2%" align="center">ʵ������</td>
                <td width="6%" align="center">��עһ</td>
                <td width="6%" align="center">��ע��</td>
                <td width="5%" align="center">Ŀ¼</td>
                <td width="3%" align="center">��������</td>
                <td width="3%" align="center">��������</td>
                <td width="3%" align="center">�豸״̬</td>
                <td width="4%" align="center">�߼�����Ԫ��</td>
                <td width="4%" align="center">Ͷ�ʷ���</td>
                <td width="4%" align="center">ҵ��ƽ̨</td>
                <td width="4%" align="center">������</td>
                
            <%
            	}
            %>
            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;width:100%;position:absolute;top:22px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="400%" border="1" style="TABLE-LAYOUT:fixed;word-break:break-all">
            <tr style="height:22px">
            <%
            	if(type.equals("dwzcgly")){
            		%>
            	
                <td width="5%"><select class="select_style1" name="specialityDept" style="width:100%" ><%=request.getAttribute("DEPT_OPTIONS2")%></select></td>
                <td width="5%"><input class="input_style2" type="text" name="maintainDeptName" style="width:100%;cursor:pointer" readonly  title="���ѡ��ʹ�ò���" onClick="do_SelectGroup();"></td>
                <td width="5%"><input class="input_style1" type="text" name="maintainUser" style="width:100%"></td>
               

                <td width="10%"><input class="input_style1" type="text" name="remark1" style="width:100%"></td>
                <td width="10%"><input class="input_style1" type="text" name="remark2" style="width:100%"></td>
                <td width="65%" align="center"></td>           		
            		<%
            	}else{
            		%>
            		 <td width="3%"><input class="input_style2" type="text" name="itemName" style="width:100%;cursor:pointer" readonly title="���ѡ���豸����" onClick="do_SelectItemCode();"></td>
                <td width="3%"><input class="input_style2" type="text" name="itemSpec" style="width:100%;cursor:pointer" readonly title="���ѡ���豸����" onClick="do_SelectItemCode();"></td>
                <td width="5%"><input class="input_style2" type="text" name="workorderObjectName" style="width:100%;cursor:pointer" readonly title="���ѡ���豸�ص�" onClick="do_SelectAddress();"></td>
                <td width="5%"><input class="input_style2" type="text" name="responsibilityDeptName" style="width:100%;cursor:pointer" readonly title="���ѡ�����β��ź�������" onClick="do_SelectPerson();"/></td>
                <td width="3%"><input class="input_style2" type="text" name="responsibilityUserName" style="width:100%;cursor:pointer" readonly title="���ѡ�����β��ź�������" onClick="do_SelectPerson();"></td>
                <td width="5%"><select class="select_style1" name="specialityDept" style="width:100%"><%=request.getAttribute("DEPT_OPTIONS2")%></select></td>
                <td width="5%"><input class="input_style2" type="text" name="maintainDeptName" style="width:100%;cursor:pointer" readonly title="���ѡ��ʹ�ò���" onClick="do_SelectGroup();"></td>
                <td width="3%"><input class="input_style1" type="text" name="maintainUser" style="width:100%"></td>
                <td width="4%"><input class="input_style2" type="text" name="manufacturerName" style="width:100%" style="width:100%;cursor:pointer" readonly title="���ѡ����" onClick="do_selectNameManufacturer();"></td>
                <td width="2%"><select class="select_style1" name="itemUnit" style="width:100%"><%=request.getAttribute(AssetsWebAttributes.ITEM_UNIT_OPTIONS)%></select></td>
                <td width="2%"><input class="input_style1" type="text" name="actualQty" style="width:100%" onblur="checkQty(this);"></td>
                <td width="6%"><input class="input_style1" type="text" name="remark1" style="width:100%"></td>
                <td width="6%"><input class="input_style1" type="text" name="remark2" style="width:100%"></td>
                <td width="5%"><input class="input_style2" type="text" name="contentName" style="width:100%;cursor:pointer" readonly title="���ѡ��Ŀ¼" onClick="do_SelectContent();"></td>
                <td width="3%"><select class="select_style1" name="isShare" style="width:100%"><%=request.getAttribute("SHARE_OPTION")%></select></td>
                <td width="3%"><select class="select_style1" name="constructStatus" style="width:100%"><%=request.getAttribute("CONSTRUCT_OPTION")%></select></td>
                <td width="3%"><select class="select_style1" name="itemStatus" style="width:100%"><%=request.getAttribute(AssetsWebAttributes.ITEM_STATUS_OPTIONS)%></select></td>
                <td width="4%"><input class="input_style2" type="text" name="logNetEle" style="width:100%;cursor:pointer" readonly title="���ѡ���߼�����Ԫ��" onClick="do_SelectLne();"></td>
                <td width="4%"><input class="input_style2" type="text" name="investCatName" style="width:100%;cursor:pointer" readonly title="���ѡ��Ͷ�ʷ���" onClick="do_SelectCex();"></td>
                <td width="4%"><input class="input_style2" type="text" name="opeName" style="width:100%;cursor:pointer" readonly title="���ѡ��ҵ��ƽ̨" onClick="do_SelectOpe();"></td>
                <td width="4%"><input class="input_style2" type="text" name="lneName" style="width:100%;cursor:pointer" readonly title="���ѡ��������" onClick="do_SelectNle();"></td>
            		
            		<%
            	}
            %>
			</tr>
        </table>
    </div>
    <input type="hidden" name="addressId" value="">
	<input type="hidden" name="responsibilityUser" value="">
	<input type="hidden" name="employeeNumber" value="">
	<input type="hidden" name="itemCode" value="">
	<input type="hidden" name="maintainDept" value="">
	<input type="hidden" name="responsibilityDept2" value="">
	<input type="hidden" name="responsibilityDept" value="">
	<input type="hidden" name="financePropName" value="">
	<input type="hidden" name="ROLL_BACK_DEPT" value="">
	<input type="hidden" name="workorderObjectCode" value="">
    <input type="hidden" name="manufacturerId" value="">
    <input type="hidden" name="contentCode" value="">
    <input type="hidden" name="lneId">
	<input type="hidden" name="cexId">
	<input type="hidden" name="opeId">
	<input type="hidden" name="nleId">

</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>

<script type="text/javascript">
function initPage() {
    do_SetPageWidth();
    var itemStatus = document.getElementById("itemStatus");
    dropSpecialOption(itemStatus,'PRE_ASSETS;ON_WAY;TO_DISCARD;DISCARDED;CLEARED;DAMAGED;DISCARDED_TRANS;TO_DISCARD_TRANS');
}

function checkQty(obj){
    var actualQty = document.mainFrm.actualQty.value;
    if (actualQty) {
        if (isNaN(actualQty)) {
            alert("ʵ����������������");
            document.mainFrm.actualQty.focus();
        } else if (!(Number(actualQty)>0)) {
            alert("ʵ�������������0��");
            document.mainFrm.actualQty.focus();
        } else if (actualQty.indexOf(".")!== -1){
            alert("ʵ������������С����");
            document.mainFrm.actualQty.focus();
        }
    }
}

function do_SelectItemCode(){
	with(mainFrm){
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_SYS_ITEM%>";
		var dialogWidth = 48;
		var dialogHeight = 30;
		var userPara = "itemCategory=" + parent.dataFrm.document.mainFrm.itemCategory.value;
		var objs = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
		if (objs) {
		    var obj = objs[0];
			dto2Frm(obj, "mainFrm");
		} else {
			itemCode.value = "";
			itemName.value = "";
			itemSpec.value = "";
		}
	}
}

function do_SelectAddress(){
	with(mainFrm){
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ADDRESS%>";
		var dialogWidth = 55;
		var dialogHeight = 30;
		userPara = "organizationId=<%=userAccount.getOrganizationId()%>";
		var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		if (objs) {
			var obj = objs[0];
			addressId.value = obj["addressId"];
			workorderObjectName.value = obj["toObjectName"];
			workorderObjectCode.value = obj["workorderObjectCode"];
		} else {
			addressId.value = "";
			workorderObjectName.value = "";
			workorderObjectCode.value = "";
		}
	}
}

function do_SelectPerson(){
	with(mainFrm){
		var deptCode = "";
//		if(deptCode == ""){
//			alert("����ѡ�����β��ţ���ѡ��������");
//			return;
//		}
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_PERSON%>";
		var dialogWidth = 47;
		var dialogHeight = 30;
		var userPara = "deptCode=" + deptCode;
		var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
		if(users){
			var user = users[0];
			responsibilityUserName.value = user["userName"];
			responsibilityUser.value = user["employeeId"];
			employeeNumber.value = user["employeeNumber"];
			responsibilityDeptName.value = user["deptName"];
			responsibilityDept.value = user["deptCode"];
		} else {
			responsibilityUserName.value = "";
			responsibilityUser.value = "";
            responsibilityDeptName.value = "";
			responsibilityDept.value = "";
		}
	}
}

function do_SelectGroup(){
	with(mainFrm){
		var lookUpName = "<%=AssetsLookUpConstant.LOOK_MAINTAIN_DEPT%>";
		var dialogWidth = 47;
		var dialogHeight = 30;
		var objs = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
		if(objs){
		   var obj = objs[0];
		    maintainDept.value = obj.groupId;
		    maintainDeptName.value = obj.groupName;
		} else {
			maintainDept.value = "";
			maintainDeptName.value = "";
		}
	}
}

function do_selectNameManufacturer() {
	var lookUpName = "<%=LookUpConstant.LOOK_UP_MANUFACTURER%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
	if (users) {
		var user = null;
		for (var i = 0; i < users.length; i++) {
			user = users[i];
			dto2Frm(user, "mainFrm");
		}
	}
}

function do_SelectLne(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_LNE%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
		mainFrm.lneId.value = obj["amsLneId"];
	} else {
        mainFrm.logNetEle.value = "";
        mainFrm.lneId.value = "";
    }
}

function do_SelectCex(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_CEX%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
		mainFrm.cexId.value = obj["amsCexId"];
	} else {
        mainFrm.investCatName.value = "";
        mainFrm.cexId.value = "";
    }
}

function do_SelectOpe(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_OPE%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
		mainFrm.opeId.value = obj["amsOpeId"];
	} else {
        mainFrm.opeName.value = "";
        mainFrm.opeId.value = "";
    }
}

function do_SelectNle(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_NLE%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
		mainFrm.nleId.value = obj["amsLneId"];
	} else {
        mainFrm.lneName.value = "";
        mainFrm.nleId.value = "";
    }
}

function do_SelectContent() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_CONTENT%>";
        var dialogWidth = 48;
        var dialogHeight = 30;
        var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                user = users[i];
                dto2Frm(user, "mainFrm");
            }
        } else {
            mainFrm.contentName.value = "";
            mainFrm.contentCode.value = "";
        }
}
</script>