<%@ page import="com.sino.ams.system.fixing.dto.EtsItemInfoDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
	<title>�����豸��Ϣ</title>
</head>
<%
    EtsItemInfoDTO dto = (EtsItemInfoDTO) request.getAttribute("AMS_HEADER");
    String countyOption = (String) request.getAttribute("COUNTY_OPTION");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    String act = StrUtil.nullToString(request.getParameter("act"));
%>
<body topmargin="0" leftmargin="0" style="overflow:auto;width:700px">
<form action="/servlet/com.sino.ams.match.servlet.AmsAssetsLeasingServlet" name="mainForm" method="post">
    <script type="text/javascript">
        printTitleBar("�����豸��Ϣ")
    </script>
    <%=WebConstant.WAIT_TIP_MSG%>
    <input type="hidden" name="act" value="<%=act%>">
    <input type="hidden" name="tempRadio">
    <input type="hidden" name="tempRadio1">
    <input type="hidden" name="tempRadio2">

    <input type="hidden" name="workorderObjectNo" value="<%=dto.getWorkorderObjectNo()%>">
    <table width="100%" class="queryTable">
        <tr>
            <td width="10%" align="right">���룺</td>
            <td width="30%"><input type="text" name="barcode" value="<%=dto.getBarcode()%>" style="width:80%"
                                   class="input_style1"></td>
            <td width="10%" align="right">���ƣ�</td>
            <td width="30%"><input type="text" name="itemName" value="<%=dto.getItemName()%>" style="width:80%"
                                   class="input_style1"></td>
        </tr>
        <tr>
           <td width="10%" align="right">�ͺţ�</td>
            <td width="30%"><input type="text" name="itemSpec" value="<%=dto.getItemSpec()%>" style="width:80%"
                                   class="input_style1"></td>
            <td></td>
            <td><img src="/images/eam_images/search.jpg" alt="��ѯ" onclick="do_query()"></td>
        </tr>
    </table>
    <div id="headDiv" style="overflow-y:scroll;width:100%" class="crystalScroll">
        <table width="100%" border="1" class="headerTable">
            <tr height="22">
                <td align="center" width="5%"></td>
                <td align="center" width="15%">��ǩ��</td>
                <td align="center" width="15%">�豸����</td>
                <td align="center" width="15%">����ͺ�</td>
                <td align="center" width="15%">��������</td>
                <td align="center" width="15%">��������</td>
                <td align="center" width="15%">������</td>
            </tr>
        </table>
    </div>
    <div style="overflow-y:scroll;height:490;width:100%" align="left"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
                Row row = null;
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
//                        Logger.logInfo("size = " + rows.getSize());
                        row = rows.getRow(i);

            %>
            <tr class="hei" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                 <td height="22" width="5%" align="center"><input type="radio" name="systemid"
                                                                 value="<%=row.getValue("SYSTEMID")%>">
                </td>
                <td height="22" width="15%"><%=row.getValue("BARCODE") %></td>
                <td height="22" width="15%"><%=row.getValue("ITEM_NAME") %></td>
                <td height="22" width="15%"><%=row.getValue("ITEM_SPEC") %> </td>
                <td height="22" width="15%"><%=row.getValue("RENT_DATE") %> </td>
                <td height="22" width="15%"><%=row.getValue("END_DATE") %> </td>
                <td height="22" width="15%"><%=row.getValue("RENT_PERSON") %> </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
<jsp:include page="/message/MessageProcess"/>
</body>
<script type="text/javascript">
function do_query() {
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    document.forms[0].act.value = "<%=WebActionConstant.QUERY_ACTION%>"
    document.forms[0].submit();
}

function do_selectObject() {
    var projects = getLookUpValues("LOOK_UP_ASSETS_OBJECT", 48, 30, "organizationId=<%=user.getOrganizationId()%>");
    if (projects) {
        document.mainForm.workorderObjectName.value = projects[0].workorderObjectName;
        document.mainForm.workorderObjectNo.value = projects[0].workorderObjectNo;
    }
}

function matchIt() {
    var success = false;
   var barcodeobj= window.parent.amsInfo.getRadioValue("systemid");

    if (barcodeobj == null || barcodeobj == '')
    {
        alert('��ѡ�������ʲ����ٲ�����');
        return false;
    }
    var radioObj = parent.misInfo.mainForm.assetId;
    if (radioObj == null || radioObj == '')
    {
        alert('��ѡ��δƥ���ʲ����ٲ�����');
        return false;
    }
    var radioCode;
    if (radioObj.length) {
        for (var i = 0; i < radioObj.length; i++) {
            if (radioObj[i].checked) {
                radioCode = radioObj[i].value;
                break;
            }
        }
    } else {
        if (radioObj.checked) {
            radioCode = radioObj.value;
        }
    }
    if (!radioCode) {
        alert("��ѡ��δƥ���ʲ����ٲ�����");
        return false;
    }
    var radioObjArr = radioCode.split(";");
    var num = radioObjArr[1];
    /*var j = getCheckedBoxCount("subCheck");
    j = (j == null) ? "0" : j;
    var radioObjArr = radioCode.split(";");
    var num = radioObjArr[1];
    if (j > num) {
        alert("�Բ������ѡ�����豸���������ұ�ѡ�����豸�������޷�ƥ�䣡");
        return false;
    } else {*/
        document.forms[0].tempRadio.value = radioObjArr[0];
         document.forms[0].tempRadio1.value= window.parent.amsInfo.getRadioValue("systemid");
         document.forms[0].tempRadio2.value= window.parent.misInfo.getRadioValue("assetId");
        document.forms[0].act.value = "<%=WebActionConstant.SAVE_ACTION%>";
        //            alert("starting match")
        document.forms[0].submit();

    return true;
}

function validateData() {
    if (parent.document.getElementById("working").value == '1') {
        alert('���ڴ����У����Ժ�...');
        return false;
    }
    var j = getCheckedBoxCount("subCheck");
    //systemid
    if (j != 1) {
        alert("���Ӧѡ��ֻ��ѡ��һ����¼��");
        return false;
    }

    var assetId = window.parent.misInfo.getRadioValue("assetId");
    //    var sysid = window.parent.itemInfoView.manualL.getCheckBoxValue('sysid');
    //    var radioValue = window.parent.systemInfoView.manualR.getRadioValue('assid');
    if (assetId == "") {
        alert("�ұ�Ӧѡ��һ����¼��");
        return false;
    }
    return true;
}

</script>
</html>