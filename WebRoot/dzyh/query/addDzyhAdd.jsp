<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser"%>
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.ams.dzyh.constant.DzyhLookUpConstant"%>
<%@ page import="com.sino.ams.dzyh.dto.EamDhBillLDTO"%>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO"%>
<%@ page import="com.sino.framework.security.bean.SessionUtil"%>
<head>
  
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/DzyhChangeLookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/arrUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
	<script language="javascript" src="/WebLibary/js/calendar.js"></script>

</head>
<body leftmargin="1" topmargin="0" onkeydown="autoExeFunction('do_Search()');">
<% 
	RequestParser parser = new RequestParser(); 
    parser.transData(request); 
     
    EamDhBillLDTO eamBillLDto = (EamDhBillLDTO)request.getAttribute(WebAttrConstant.DZYH_DATA); 
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request); 
     
%>
<form name="mainFrm"  method="POST" action="/servlet/com.sino.ams.dzyh.servlet.EtsDhBillServlet">
<script type="text/javascript">
    printTitleBar("��ֵ�׺�Ʒά��>>���ŵ�ֵ�׺�Ʒ����");
</script>
	<input type="hidden" name="billLineId" value="<%=eamBillLDto.getBillLineId()%>">
	<input type="hidden" name="act" value="<%=parser.getParameter("act")%>">
    <table border="1" bordercolor="#9FD6FF" class="detailHeader" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1">
                <tr height="22">
                    <td width="15%" align="right">���ݱ�ţ�</td>
                    <td width="25%"><input type="text" style="width:60%" name="edblBillNo"
                                           value="<%=eamBillLDto.getEdblBillNo()%>" readonly
                                           class="blueborderGray">
                    </td>
                    <td width="15%" align="right">����״̬��</td>
                    <td width="25%"><input type="text" style="width:50%" name="edblBillStatus" readonly
                                           value="<%=eamBillLDto.getEdblBillStatus()%>"
                                           class="blueborderGray">
                    </td>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td><input type="text" style="width:60%" name="createUser" value="<%=eamBillLDto.getCreateUser()%>"
                               readonly
                               class="blueborderGray">
                    </td>
                    <td align="right">�������ڣ�</td>
                    <td><input type="text" name="creationDate" readonly style="width:50%"
                               value="<%=eamBillLDto.getCreationDate()%>"
                               class="blueborderGray">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<fieldset>
<legend>
    <%
        //���ݴ�����״̬���ҵ�ǰ�û��Ǵ����˲��в���Ȩ��
        if (eamBillLDto.getEdblBillStatus().equals("������") && eamBillLDto.getCreatedBy().equals(user.getUserId())) {
    %>
    <img src="/images/eam_images/imp_from_excel.jpg" alt="Excel��������" onClick="do_Create(); return false;">
    <img src="/images/button/addLine.gif" alt="�����" onClick="do_addLine();">
    <img src="/images/eam_images/delete_line.jpg" alt="ɾ����"
         onClick="deleteTableRow(document.getElementById('dataTable'),'subCheck');">
    <img src="/images/button/make-barcode.gif" alt="��������" onClick="do_Save();">

    <%
        }
    %>
      <span id="warn"></span>
    <img src="/images/eam_images/close.jpg" alt="�ر�" onClick="window.close();">
</legend>
<script type="text/javascript">
    var columnArr = new Array("checkbox", "Ŀ¼���", "Ʒ ��", "����ͺ�", "�� ��", "�� ��", "ʹ�ò���","������", "�� ��", "��������", "�� ��", "�� ע");
    var widthArr = new Array("2%", "6%", "7%", "10%", "4%", "4%", "11%", "6%","11%","7%","10%","15%");
    printTableHead(columnArr, widthArr);
</script>

    <div style="overflow-y:scroll;height:440px;width:100%;left:1px;margin-left:0"
     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	    <table width="100%" border="1" id="dataTable" bordercolor="#666666">

			<tr onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#FFFFFF'">
            	<td width="2%" align="center">
            		<input type="checkbox" name="subCheck" id="subCheck" style="height:20px;margin:0;padding:0"></td>
            		
				<td height="22" width="6%" align="center">
					<input type="text" name="edblCatalogCode" readonly="readonly" class="blueborderYellow" onclick="do_SelectCategory2();" 
				title="���ѡ��Ŀ¼���" style="width:100%;text-align:center"></td>
				
				<td height="22" width="7%" align="center">
					<input type="text" name="edblItemName" readonly="readonly" class="blueborderYellow" onclick="do_SelectItemName();" 
				title="���ѡ��Ʒ��" style="width:100%;text-align:center"></td>
				
				<td height="22" width="10%" align="center">
					<input type="text" name="edblItemSpec" readonly="readonly" class="blueborderYellow" onclick="do_SelectItemSpec();" 
				title="���ѡ��������" style="width:100%;text-align:center"></td>
				
				<td height="22" width="4%" align="center">
					<input type="text" name="quantity" class="blueborderYellow" style="width:100%;text-align:center"></td>
					
				<td height="22" width="4%" align="center">
					<input type="text" name="price" class="blueborderYellow" style="width:100%;text-align:center"></td>
					
				<td height="22" width="11%" align="center" onclick="do_SelectDeptName(this);" >
					<input type="text" name="edblDeptName" readonly="readonly" class="blueborderYellow" 
					title="���ѡ����" style="width:100%;text-align:center" id="edblDeptName" ><input type="hidden" 
					name="responsibilityDept" id="responsibilityDept" value="">
				</td>
				
				<td height="22" width="6%" align="center" onclick="do_SelectUserName(this);">
					<input type="text" name="edblUserName" readonly="readonly" class="blueborderYellow" 
				title="���ѡ��������" style="width:100%;text-align:center" id="edblUserName"><input type="hidden" 
				name="responsibilityUser" id="responsibilityUser" value="">
				</td>
				
				<td height="22" width="11%" align="center" onclick="do_SelectAddressName(this);">
					<input type="text" name="edblWorkorderObjectName" readonly="readonly" class="blueborderYellow" 
					title="���ѡ��ص�" style="width:100%;text-align:center" id="edblWorkorderObjectName"><input type="hidden" 
					name="workorderObjectNo" id="workorderObjectNo" value="">
				</td>
				
				<td height="22" width="7%" align="center">
					<input type="text" name="lastLocChgDate" readonly="readonly" class="blueborderYellow" onclick="gfPop.fPopCalendar(lastLocChgDate)" 
				title="���ѡ����������" style="width:100%;text-align:center"></td>
				
				<td height="22" width="10%" align="center">
					<input type="text" name="manufactory" readonly="readonly" class="blueborderYellow" onclick="do_SelectChangjia();" 
				title="���ѡ�񳧼�" style="width:100%;text-align:center"></td>
				<td height="22" width="15%" align="center">
					<input type="text" name="remark" class="blueborderYellow" style="width:100%"></td>
			</tr>
		</table>
	</div>
	</fieldset>
    </form>
<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
<%=WebConstant.WAIT_TIP_MSG%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script language="javascript">

function do_Search(){
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
}

function do_ShowDetail(primaryKey){
	mainFrm.systemid.value = primaryKey;
	mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";
	mainFrm.submit();
}
/**
 * ����:�����
 */
function do_addLine() {
    var rowCount = document.getElementById("dataTable").rows.length - 1 ;
    var tbObj = document.getElementById("dataTable");
    var rs = tbObj.rows;
    var count = rs.length;
    var row0 = rs[count - 1];
    var newRow = row0.cloneNode(true);
    row0.appendChild(newRow);
}
/**
 * ����:ѡ��Ŀ¼���
 */
function do_SelectCategory2() {
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_CATEGORY2_DZYH%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = lookUpChangeValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.edblCatalogCode.value = "";
	}
}

/**
 * ����:ѡ��Ʒ��
 */
function do_SelectItemName() {
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_ITEMNAME_DZYH%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = lookUpChangeValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.edblItemName.value = "";
	}
}

/**
 * ����:ѡ�����ͺ�
 */
function do_SelectItemSpec() {
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_ITEMSPEC_DZYH%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = lookUpChangeValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.edblItemSpec.value = "";
	}
}

function do_SelectUserName(obj) {

    obj.childNodes[0].value = "";
    obj.childNodes[1].value = "";
    obj.childNodes[0].style.color="black";

	var edblDeptName = document.getElementById("edblDeptName").value;
    if (edblDeptName == "") {
        alert("����ѡ��ʹ�ò��ţ���ѡ�������ˣ�");
        return;
    }

    var upName = "<%=DzyhLookUpConstant.LOOK_UP_MIS_USER%>";
    var dialogWidth = 50;
    var dialogHeight = 30;

    var userPara = "deptCode=" + document.getElementById("responsibilityDept").value;
    
    var users = getLookUpValues(upName, dialogWidth, dialogHeight, userPara);
    if (users) {
        obj.childNodes[0].value = users[0].userName;
        obj.childNodes[1].value = users[0].employeeId;
    }
}

function do_SelectDeptName(obj) {
    obj.childNodes[0].value = "";
    obj.childNodes[1].value = "";
    obj.childNodes[0].style.color="black";
    var responsibilityDeptId=obj.childNodes[2].id;
   
    var lookUpSpec = "<%=DzyhLookUpConstant.LOOK_UP_MIS_DEPT%>";
    var dialogWidth = 50;
    var dialogHeight = 30;
    var specs = getLookUpValues(lookUpSpec, dialogWidth, dialogHeight);
    if (specs) {
		obj.childNodes[0].value = specs[0].deptName;
        obj.childNodes[1].value = specs[0].deptCode;
    }
}

/**
 * ����:ѡ��ص�
 */
function do_SelectAddressName(obj) {
    obj.childNodes[0].value = "";
    obj.childNodes[1].value = "";
    obj.childNodes[0].style.color="black";
    var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_ADDRESS%>";
    var dialogWidth = 55;
    var dialogHeight = 30;
    var userPara = "organizationId=" +<%=user.getOrganizationId()%>;
    var locations = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (locations) {
        obj.childNodes[0].value = locations[0].toObjectName;
        obj.childNodes[1].value = locations[0].addressId;
    }
}
/**
 * ����:ѡ��ʹ�ò���
 
function do_SelectDeptName() {
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_MIS_DEPT%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.edblDeptName.value = "";
	}
	
}*/

/**
 * ����:ѡ��������
 
function do_SelectUserName() {
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_MIS_USER%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.edblUserName.value = "";
	}
}
*/
/**
 * ����:ѡ��ص�
 
function do_SelectAddressName() {
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_ADDRESS%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.edblWorkorderObjectName.value = "";
	}
}
*/
/**
 * ����:ѡ�񳧼�
 */
function do_SelectChangjia() {
	var lookUpName = "<%=DzyhLookUpConstant.LOOK_UP_CHANGE_DZYH%>";
	var dialogWidth = 44;
	var dialogHeight = 30;
	var depts = lookUpPriviValues(lookUpName, dialogWidth, dialogHeight);
    if (depts) {
		dto2Frm(depts[0], "mainFrm");
	} else {
		mainFrm.manufactory.value = "";
	}
}

</script>