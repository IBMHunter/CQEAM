<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-6-5
  Time: 19:21:24
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ͨ��̨��ά��--������ҳ</title></head>
<body leftmargin="0" topmargin="0" onload="initPage()">
<%
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(request);
	String provinceCode = servletConfig.getProvinceCode();
    String orgId = Integer.toString(userAccount.getOrganizationId());
%>

<form name="mainFrm">
    <div id="aa" style="overflow-x:hidden;overflow-y:scroll;position:absolute;top:0px;left:0px;width:100%" class="crystalScroll">
        <table border=1 width="300%" class="headerTable">
            <tr height="22px">
                <td width="5%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">���ڵص�</td>
                <td width="5%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">���β���</td>
                <td width="2%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">������</td>
                <td width="5%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">ʵ�ﲿ��</td>
                <td width="2%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">ʹ����</td>
                <td width="2%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">�豸״̬</td>
                <td width="2%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">�Ƿ�ʧЧ</td>
                <td width="3%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">�Ƿ���</td>
                <td width="3%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">�Ƿ񹲽�</td>
                <td width="5%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">��Ŀ</td>
                <td width="4%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">����</td>
                <td width="4%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">�ʲ�Ŀ¼</td>
                <td width="2%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">ʵ������</td>
                <td width="4%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">�豸����</td>
                <td width="3%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">��������</td>
                <td width="7%" align="center" style="color: #FFFFFF" background="/images/bg_01.gif">��ע</td>
            </tr>
        </table>
    </div>
    <div id="bb" style="overflow:scroll;width:100%;position:absolute;top:22px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="300%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
            <tr>
                <td width="5%"><input type="text" name="workorderObjectName" style="width:100%;cursor:hand" readonly class="input_style2" title="���ѡ���豸�ص�" onClick="do_SelectAddress();"></td>
                <td width="5%"><select name="responsibilityDept" class="select_style1" style="width:100%"><%=request.getAttribute(AssetsWebAttributes.DEPT_OPTIONS)%></select></td>
                <td width="2%"><input type="text" name="responsibilityUserName" style="width:100%;cursor:hand" readonly class="input_style2" title="���ѡ��������" onClick="do_SelectPerson();"></td>
                <td width="5%"><select name="specialityDept" class="select_style1" style="width:100%"><%=request.getAttribute("DEPT_OPTIONS2")%></select></td>
                <td width="2%"><input type="text" name="maintainUser" class="input_style1" style="width:100%"></td>
                <td width="2%"><select name="itemStatus" class="select_style1" style="width:100%"><%=request.getAttribute(AssetsWebAttributes.ITEM_STATUS_OPTIONS)%></select></td>
                <td width="2%">
                    <select name="isAbate" class="select_style1" style="width:100%">
                        <option value="">��ѡ��</option>
                        <option value="Y">ʧЧ</option>
                        <option value="N">��Ч</option>
                    </select>
                </td>
                <td width="3%"><select class="select_style1" name="isShare" style="width:100%"><%=request.getAttribute("SHARE_OPTION")%></select></td>
                <td width="3%"><select class="select_style1" name="constructStatus" style="width:100%"><%=request.getAttribute("CONSTRUCT_OPTION")%></select></td>
                <td width="5%"><input type="text" name="projectName" style="width:100%;cursor:hand" readonly class="input_style2" title="���ѡ����Ŀ" onClick="do_SelectProj();"></td>
                <td width="4%"><input type="text" name="manufacturerName" style="width:100%;cursor:hand" readonly class="input_style2" title="���ѡ����" onClick="do_selectNameManufacturer();"></td>
                <td width="4%"><input type="text" name="contentName" style="width:100%;cursor:hand" readonly class="input_style2" title="���ѡ���ʲ�Ŀ¼" onClick="do_SelectContent();"></td>
                <td width="2%"><input class="input_style1" type="text" name="actualQty" style="width:100%" onblur="checkQty(this);"></td>
                <td width="4%"><input class="input_style1" type="text" name="otherInfo" style="width:100%"></td>
                <td width="3%"><input class="input_style2" type="text" name="startDate" style="width:100%;cursor:hand" title="���ѡ������" readonly onclick="gfPop.fStartPop(startDate, endDate)"><input type="hidden" name="endDate"></td>
                <td width="7%"><input type="text" name="remark" class="input_style1" style="width:100%"></td>
            </tr>
        </table>
    </div>
    <%--<div style="overflow:hidden;position:absolute;top:50px;left:1px;width:98%">--%>
        <%--<table border="0" width="100%">--%>
            <%--<tr>--%>
                <%--<td width="3%" align="center">ѡ<br>��<br>��<br>��<br>��</td>--%>
                <%--<td width="97%" height="115">--%>
                <%--<textarea name="checkedData" id="checkedData" style="border:1px solid #3366EE; width:100%; height:100%" readonly></textarea></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</div>--%>
    <input type="hidden" name="addressId" value="">
	<input type="hidden" name="responsibilityUser" value="">
	<input type="hidden" name="employeeNumber" value="">
	<input type="hidden" name="itemCode" value="">
	<input type="hidden" name="responsibilityDeptName" value="">
    <input type="hidden" name="financePropName" value="">
	<input type="hidden" name="ROLL_BACK_DEPT" value="">
	<input type="hidden" name="workorderObjectCode" value="">
	<input type="hidden" name="projectid" value="">
	<input type="hidden" name="lneId" value="">
	<input type="hidden" name="cexId" value="">
	<input type="hidden" name="opeId" value="">
	<input type="hidden" name="nleId" value="">
	<input type="hidden" name="manufacturerId" value="">
    <input type="hidden" name="contentCode" value="">
</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>

<script>
function initPage() {
    var itemStatus = document.getElementById("itemStatus");
    dropSpecialOption(itemStatus,'PRE_ASSETS;ON_WAY;TO_DISCARD;DISCARDED;CLEARED;DAMAGED;DISCARDED_TRANS;TO_DISCARD_TRANS');
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
		var deptCode = responsibilityDept.value;
		if(deptCode == ""){
			alert("����ѡ�����β��ţ���ѡ��������");
			return;
		}
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
		} else {
			responsibilityUserName.value = "";
			responsibilityUser.value = "";
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
		    maintainDeptName.value = obj.groupname;
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
        } else {
            mainFrm.manufacturerId.value = "";
            mainFrm.manufacturerName.value = "";
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
		mainFrm.lneName.value = obj["logNetEle"];
	} else {
        mainFrm.lneId.value = "";
        mainFrm.lneName.value = "";
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
		mainFrm.cexName.value = obj["investCatName"];
	} else {
        mainFrm.cexId.value = "";
        mainFrm.cexName.value = "";

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
		mainFrm.opeName.value = obj["opeName"];
	} else {
        mainFrm.opeId.value = "";
        mainFrm.opeName.value = "";
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
		mainFrm.nleName.value = obj["lneName"];
	} else {
        mainFrm.nleId.value = "";
        mainFrm.nleName.value = "";
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
function do_selectSpecialityUser() {
        var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_SPECIAL_USER%>";
        var dialogWidth = 48;
        var dialogHeight = 30;
        var userPara = "organizationId=" + <%=orgId%>;
        var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
        if(users){
            var user = users[0];
            mainFrm.specialityUser.value = user["employeeId"];
            mainFrm.specialityUserName.value = user["userName"];
        } else {
            mainFrm.specialityUser.value = "";
            mainFrm.specialityUserName.value = "";
        }
}

function do_SelectProj() {
        var lookUpProj = "<%=LookUpConstant.LOOK_UP_PROJECT%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        var projs = getLookUpValues(lookUpProj, dialogWidth, dialogHeight);
        if (projs) {
            var user = projs[0];
            mainFrm.projectid.value = user["prjId"];
            mainFrm.projectName.value = user["prjName"];
        } else {
            mainFrm.projectid.value = "";
            mainFrm.projectName.value = "";
        }
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
</script>