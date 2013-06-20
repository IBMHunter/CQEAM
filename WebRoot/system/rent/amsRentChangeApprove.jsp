<%--
  Created by IntelliJ IDEA.
  User: yuyao
  Date: 2008-7-11
  Time: 11:15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsActionConstant" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsDictConstant" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsTransLineDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%--
  Created by IntelliJ IDEA.
  User: yuyao
  Date: 2008-7-10
  Time: 15:06:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.htm" %>
<html>
<%
    AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
    String transId = headerDTO.getTransId();
    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    String orgId = userAccount.getOrganizationId();
    String userId = userAccount.getUserId();
    String attribute1 = headerDTO.getAttribute1();
%>
<head>
    <title><%=headerDTO.getTransTypeValue()%>
    </title>
     <script type="text/javascript">
        var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "do_Cancel");
        var ArrAction2 = new Array(true, "�ύ", "action_sign.gif", "�ύ", "do_Div_Complete_Start");
        var ArrAction3 = new Array(true, "�˻�", "arrow_pleft.gif", "�˻�", "do_Back");
        var ArrAction5 = new Array(true, "��������", "actn023.gif", "��������", "do_ViewFlow") ;
        var ArrAction15 = new Array(true, "����������¼", "action_viewstatus.gif", "����������¼", "showOpinionDlg");
        var ArrActions = new Array(ArrAction0, ArrAction2, ArrAction3, ArrAction5, ArrAction15);
        var ArrSinoViews = new Array();
        printToolBar();
    </script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
</head>
<%@ include file="/flow/flowNoButton.jsp"%>
<body leftmargin="0" topmargin="0" onLoad="do_SetPageWidth();doLoad();" onbeforeunload="doBeforeUnload()" onunload="doUnLoad()">
<form action="/servlet/com.sino.ams.system.rent.servlet.AMSRentChangeServlet" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<%@ include file="/flow/flowPara.jsp" %>
<jsp:include page="/flow/include.jsp" flush="true"/>
<table border="0"  class="queryTable" width="100%" style="border-collapse: collapse" id="table1">
    <tr>
        <td>
            <table width=100% border="0">
                <tr>
                    <td align=right width=8%>���ݺţ�</td>
                    <td width=17%  class="input_style2">
                        <%=headerDTO.getTransNo()%>
                    </td>
                    <td align=right width=8%>����״̬��</td>
                    <td width=17%  class="input_style2">
                        <%=headerDTO.getTransStatusDesc()%>
                    </td>
                    <td align=right width=8%>�������ڣ�</td>
                    <td width=17%  class="input_style2">
                        <%=headerDTO.getCreationDate()%>
                    </td>

                </tr>
                <tr>
                    <td align=right width=8%>�����ˣ�</td>
                    <td width=17%  class="input_style2">
                        <%=headerDTO.getCreated()%>
                    </td>
                    <td align=right>��˾���ƣ�</td>
                    <td  class="input_style2">
                        <%=headerDTO.getFromCompanyName()%>
                    </td>
                    <td align=right width=8%>�������ƣ�</td>
                    <td width=17%  class="input_style2">
                        <%=headerDTO.getUserDeptName()%>
                    </td>
                </tr>

                <tr>
                    <td align=right width=8%>�ֻ����룺</td>
                    <td width=17%  class="input_style2">
                        <%=headerDTO.getPhoneNumber()%>
                    </td>
                    <td align=right>�����ʼ���</td>
                    <td  class="input_style2">
                        <%=headerDTO.getEmail()%>
                    </td>
                    <td align=right width=8%>���벿�ţ�</td>
                    <td width=17%>
                       <select name="toDept" style="width:100%" disabled="disabled"  class="input_style2"><%=headerDTO.getToDeptOption()%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align=right width=8% align="right" height="40">����˵������</td>
                    <td colspan="7" height="40"  class="input_style2"><%=headerDTO.getCreatedReason()%>
                    </td>
                </tr>
                <input type="hidden" name="toOrganizationId" value="<%=headerDTO.getToOrganizationId()%>">

            </table>
        </td>
    </tr>
</table>
<input type="hidden" name="fromGroup" value="<%=headerDTO.getFromGroup()%>">
<input type="hidden" name="toDept" value="<%=headerDTO.getToDept()%>">
<input type="hidden" name="created" value="<%=headerDTO.getCreated()%>">
<input type="hidden" name="createdBy" value="<%=headerDTO.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=transId%>">
<input type="hidden" name="transNo" value="<%=headerDTO.getTransNo()%>">
<%--<input type="hidden" name="procdureName" value="<%=headerDTO.getProcdureName()%>">--%>
<input type="hidden" name="act" value="">
<fieldset style="border:1px solid #397DF3; position:absolute;top:123px;width:100%;height:70%">
    <%--<legend>
        <img src="/images/button/pass.gif" alt="ͨ��" onClick="do_approve(); return false;">
        --%><%--<img src="/images/button/noPass.gif" alt="��ͨ��" onClick="do_reject(); return false;">--%><%--

        <span id="warn"></span>
        <img src="/images/button/viewOpinion.gif" alt="�����������" onClick="viewOpinion(); return false;">
        <img src="/images/button/viewFlow.gif" alt="��������" onClick="viewFlow(); return false;">

        <img src="/images/eam_images/close.jpg" id="img6" alt="�ر�" onClick="do_Close(); return false;">
    </legend>--%>
    <div id="headDiv" style="overflow:hidden;position:absolute;top:25px;left:1px;width:990px">
        <table class="headerTable" border="1" width="140%">
            <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">

                <td align=center width="8%">�ʲ���ǩ</td>
                <td align=center width="8%">�ʲ�����</td>
                <td align=center width="8%">�ʲ��ͺ�</td>
                <td align=center width="3%">����</td>
                <td align=center width="5%">ԭ������</td>
                <td align=center width="5%">��������</td>
                <td align=center width="11%">�µص�</td>
                <td align="center" width="5%">��������</td>
                <td align=center width="8%">��ע</td>
                <td style="display:none">�������ֶ�</td>
            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;height:88%;width:1007px;position:absolute;top:48px;left:1px" align="left"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table id="dataTable" width="140%" border="1" bordercolor="#666666">
            <%
                DTOSet lineSet = (DTOSet) request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
                if (lineSet == null || lineSet.isEmpty()) {
            %>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="display:none">
                <td width="8%" align="center" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)">
                    <input type="text" name="barcode" id="barcode0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input
                        type="text" name="itemName" id="itemName0" class="finput" readonly value=""></td>
                <td width="8%" align="left" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input
                        type="text" name="itemSpec" id="itemSpec0" class="finput" readonly value=""></td>
                <td width="3%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input
                        type="text" name="itemQty" id="itemQty0" class="finput3" readonly value=""></td>
                <td width="5%" align="right" title="����鿴�ʲ���ϸ��Ϣ" style="cursor:hand" onClick="do_ShowDetail(this)"><input
                        type="text" name="userName" id="userName0" class="finput" readonly value=""></td>
                <td width="5%" align="left"><input type="text" name="responsibilityUserName"
                                                   id="responsibilityUserName0" class="finput" readonly value=""
                                                   title="���ѡ��������������" style="cursor:hand"></td>
                <td width="11%"><input type="text" name="assignedToLocationName" id="assignedToLocationName0" class="finputNoEmpty" readonly value=""  style="cursor:hand"></td>
                <td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate0"
                                                     style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly
                                                     value="" title="���ѡ�����ĵ�������" onBlur="do_SetLineTransDate(this)">
                </td>
                <td width="8%" align="left"><input type="text" name="remark" id="remark0" class="finput" value=""></td>
                <td style="display:none"><input type="hidden" name="oldLocation" id="oldLocation0" value=""></td>
                <td style="display:none"><input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser0"
                                                value=""></td>
                <td style="display:none"><input type="hidden" name="responsibilityDept" id="responsibilityDept0"
                                                value=""></td>
                <td style="display:none"><input type="hidden" name="assignedToLocation" id="assignedToLocation0"
                                                value=""></td>
                <td style="display:none"><input type="hidden" name="responsibilityUser" id="responsibilityUser0"
                                                value=""></td>
                <td style="display:none"><input type="hidden" name="addressId" id="addressId0" value=""></td>
            </tr>
            <%
            } else {
                AmsAssetsTransLineDTO lineDTO = null;
                String barcode = "";
                for (int i = 0; i < lineSet.getSize(); i++) {
                    lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
                    barcode = lineDTO.getBarcode();
            %>
            <tr id="model" class="dataTR" onClick="executeClick(this)" style="cursor:hand">
                <%--<td width="3%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>"--%>
                                                     <%--value="<%=barcode%>"></td>--%>
                <td width="8%" align="center" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="barcode" id="barcode<%=i%>" class="finput"
                                                         readonly value="<%=barcode%>"></td>
                <td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="itemName" id="itemName<%=i%>" class="finput"
                                                         readonly value="<%=lineDTO.getItemName()%>"></td>
                <td width="8%" align="left" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="itemSpec" id="itemSpec<%=i%>" class="finput"
                                                         readonly value="<%=lineDTO.getItemSpec()%>"></td>
                <td width="3%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="itemQty" id="itemQty<%=i%>" class="finput3"
                                                         readonly value="<%=lineDTO.getItemQty()%>"></td>
                <td width="5%" align="right" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ" style="cursor:hand"
                    onClick="do_ShowDetail(this)"><input type="text" name="userName" id="userName<%=i%>" class="finput"
                                                         readonly value="<%=lineDTO.getUserName()%>"></td>

                <td width="5%" align="left"><input type="text" name="responsibilityUserName"
                                                   id="responsibilityUserName<%=i%>" class="finput" readonly
                                                   value="<%=lineDTO.getResponsibilityUserName()%>" title="���ѡ��������������"
                                                   style="cursor:hand"></td>
                <td width="11%"><input type="text" name="assignedToLocationName" id="assignedToLocationName<%=i%>" class="finput" readonly value="<%=lineDTO.getAssignedToLocationName()%>" onClick="do_SelectLocation(this)" title="���ѡ�����ĵ���ص�" style="cursor:hand"></td>
                <td width="5%" align="center"><input type="text" name="lineTransDate" id="lineTransDate<%=i%>"
                                                     style="width:100%; border: 1px solid #FFFFFF;cursor:hand" readonly
                                                     value="<%=lineDTO.getLineTransDate()%>" title="���ѡ�����ĵ�������"
                                                     onBlur="do_SetLineTransDate(this)"></td>
                <td width="8%" align="left"><input type="text" name="remark" id="remark<%=i%>" class="finput"
                                                   value="<%=lineDTO.getRemark()%>"></td>
               
                <td style="display:none">
                    <input type="hidden" name="oldLocation" id="oldLocation<%=i%>"
                           value="<%=lineDTO.getOldLocation()%>">
                    <input type="hidden" name="oldResponsibilityUser" id="oldResponsibilityUser<%=i%>"
                           value="<%=lineDTO.getOldResponsibilityUser()%>">
                    <input type="hidden" name="responsibilityDept" id="responsibilityDept<%=i%>"
                           value="<%=lineDTO.getResponsibilityDept()%>">
                    <input type="hidden" name="assignedToLocation" id="assignedToLocation<%=i%>"
                           value="<%=lineDTO.getAssignedToLocation()%>">
                    <input type="hidden" name="responsibilityUser" id="responsibilityUser<%=i%>"
                           value="<%=lineDTO.getResponsibilityUser()%>">
                    <input type="hidden" name="addressId" id="addressId<%=i%>" value="<%=lineDTO.getAddressId()%>">
                </td>
            </tr>
            <%
                    }
                }%>
        </table>
    </div>
</fieldset>
</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>
<script type="text/javascript">
    function do_SubmitOrder() {

        var orgId = "<%=orgId%>";
        var userId = "<%=userId%>";
        var groupId = mainFrm.toGroup.value;
        var procdureName = "�����ʲ���������";
        var flowCode = "";
        var paramObj = new Object();

        paramObj.orgId = orgId;
        paramObj.useId = userId;
        paramObj.groupId = groupId;
        paramObj.procdureName = procdureName;
        paramObj.flowCode = flowCode;
        paramObj.submitH = 'submitH()';
        //	alert("flowCode = " + flowCode);
        assign(paramObj);
    }


    function submitH() {
        document.getElementById("table1").disabled = false;
        mainFrm.act.value = "<%=AssetsActionConstant.APPROVE_ACTION%>";
        mainFrm.submit();
        	var frm = opener.document.forms[0];
			if(frm){
				if(frm.act){
					frm.act.value = "QUERY_ACTION";
				}
				frm.submit();
			}
    }
    function do_approve() {
        var orgId = "<%=orgId%>";
        var userId = "<%=userId%>";
        var groupId = mainFrm.toGroup.value;
        var procdureName = "�����ʲ���������";
        var flowCode = "";
        var paramObj = new Object();

        paramObj.orgId = orgId;
        paramObj.useId = userId;
        paramObj.groupId = groupId;
        paramObj.procdureName = procdureName;
        paramObj.flowCode = flowCode;
        paramObj.submitH = 'submitH()';
        //	alert("flowCode = " + flowCode);
        assign(paramObj);
    }
    function do_reject() {
        document.getElementById("table1").disabled = false;
        mainFrm.act.value = "<%=AssetsActionConstant.REJECT_ACTION%>";
        mainFrm.submit();
    }
    function do_ShowDetail(td) {
        var transType = mainFrm.transType.value;
        tr = td.parentNode;
        cells = tr.cells;
        var cell = cells[1];
        if (transType == "<%=AssetsDictConstant.ASS_SUB%>") {
            cell = cells[3];
        }
        var barcode = cell.childNodes[0].value;
        var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
        var winName = "assetsWin";
        var style = "width=800,height=360,left=70,top=100";
        window.open(url, winName, style);
    }

    function do_CancelOrder() {
        if (confirm("����׼�����������ݣ�ȷ���𣿼���������ȷ������ť������������ȡ������ť!")) {
            mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
            mainFrm.submit();
        }
    }
 function do_Complete_app_yy() {
	if(true){
        try{
//			disabledBtn();
            var actObj = document.getElementById("act");
			actObj.value = "APPROVE_ACTION";
            //setFrmEnable("mainFrm");
//			document.forms[0].action="/servlet/com.sino.ies.inv.ywypmgr.servlet.CtmsForwardServlet";
			document.forms[0].submit();
		}catch(ex){
			alert( ex.message );
		}finally{
			enableBtn();
		}
	}
}

</script>