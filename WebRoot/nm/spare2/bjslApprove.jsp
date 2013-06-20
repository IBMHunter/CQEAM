<%--
  Created by HERRY.
  Date: 2007-10-23
  Time: 16:30:03
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.nm.spare2.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<html>
  <head><title>������������</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
    <script language="javascript" src="/flow/flow.js"></script>
  </head>
  <body leftmargin="1" topmargin="1"  onload="init();">
 <jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) request.getAttribute("BJSL_HEADER");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    RequestParser rp = new RequestParser();
    rp.transData(request);
    String attribute1 = amsItemTransH.getAttribute1(); //�Ƿֹ�˾�ڲ����컹��������˾����
    String str2 = "������˾����";
    String procName = "������������";
    if(attribute1.equals("S")){
        str2 = "�ֹ�˾�ڲ�����";
        procName = "������������(��)";
    }
    String itemCodes = "";
%>
<form name="mainForm" action="/servlet/com.sino.nm.spare2.servlet.BjslApproveServlet" method="post">
<jsp:include page="/flow/include.jsp" flush="true"/>
<input type="hidden" name="act" value="">
<input type="hidden" name="flag" value="">
<input type="hidden" name="transId" value="<%=amsItemTransH.getTransId()%>">
<input type="hidden" name="transNo" value="<%=amsItemTransH.getTransNo()%>">
<input type="hidden" name="transType" value="<%=amsItemTransH.getTransType()%>">
<input type="hidden" name="createdBy" value="<%=amsItemTransH.getCreatedBy()%>">
<input type="hidden" name="toObjectNo" value="<%=amsItemTransH.getToObjectNo()%>">
<input type="hidden" name="transStatus" value="<%=amsItemTransH.getTransStatus()%>">
<input type="hidden" name="toOrganizationId" value="<%=amsItemTransH.getToOrganizationId()%>">
<table border="1" bordercolor="#9FD6FF" class="detailHeader" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1">
                <tr height="22">
                    <td width="9%" align="right">���ݺţ�</td>
                    <td width="20%"><%=amsItemTransH.getTransNo()%>
                    </td>
                    <%--<td width="9%" align="right">�ֿ����ƣ�</td>
                    <td width="25%"><input type="text" name="toObjectName" value="<%=amsItemTransH.getToObjectName()%>"
                                           class="blueborderYellow" style="width:80%">
                        <a href="#" onClick="do_SelectObject();" title="���ѡ��ֿ�"
                           class="linka">[��]</a>
                    </td>
                    <td width="9%" align="right">�ֿ�ص㣺</td>
                    <td width="25%"><input type="text" name="toObjectLocation" value="<%=amsItemTransH.getToObjectLocation()%>"
                                           class="blueborderGray">
                    </td>--%>
                    <td width="9%" align="right">���빫˾��</td>
                    <td width="25%"><%=amsItemTransH.getFromOrganizationName()%>
                    </td>
                    <td align="right">���ͣ�</td>
                    <td><%=str2%>
                    </td>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td><%=amsItemTransH.getCreatedUser()%>
                    </td>
                    <td align="right">�������ڣ�</td>
                    <td><%=amsItemTransH.getCreationDate()%>
                    </td>
                    <td align="right" width="9%">����״̬��</td>
                    <td><%=amsItemTransH.getTransStatusName()%></td>
                </tr>
                <tr>
                    <td align="right" height="50">��ע��</td>
                    <td colspan="3"><%=amsItemTransH.getRemark()%>
                    </td>
                    <td align="right">Ԥ�ƹ黹���ڣ�</td>
                    <td><%=amsItemTransH.getRespectReturnDate()%>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<fieldset>
    <legend>����ͺ�
        <%--<img src="/images/button/addData.gif" alt="�������" onclick="do_SelectItem();">--%>
        <%--<img src="/images/button/deleteLine.gif" alt="ɾ����" onClick="deleteTableRow(document.getElementById('dataTable'),'subCheck');">--%>
        <%--<img src="/images/button/ok.gif" alt="ȷ������" id="img2" onClick="do_Approve(3);">--%>
        <img src="/images/button/pass.gif" alt="ͨ��" id="img3" onClick="do_Approve(1);">
        <img src="/images/button/noPass.gif" alt="��ͨ��" id="img4" onClick="do_reject();">
        <img src="/images/button/viewFlow.gif" alt="��������" onClick="viewFlow();">
        <img src="/images/button/viewOpinion.gif" alt="�������" onClick="viewOpinion();">
        <img src="/images/button/close.gif" alt="�ر�" onClick="window.close();">
    </legend>
   <%-- <div style="left:1px;width:100%;overflow-y:scroll" id="headDiv">
        <table id="headerTable" border="1" width="100%" class="headerTable" cellpadding="0" cellspacing="0">
            <tr height="22">
                <td width="2%" align="center"><input type="checkBox" name="titleCheck"
                                                     onclick="checkAll('titleCheck','subCheck');" class="headCheckbox">
                </td>
                <td width="12%" align="center">�豸����</td>
                <td width="15%" align="center">�豸����</td>
                <td width="15%" align="center">����ͺ�</td>
            </tr>
        </table>
    </div>--%>
    <script type="text/javascript">
        var columnArr = new Array("�豸����","����ͺ�","����");
        var widthArr = new Array("12%","15%","8%");
        printTableHead(columnArr,widthArr);
    </script>
    <div style="overflow-y:scroll;height:500px;width:100%;left:1px;margin-left:0"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#9FD6FF" id="xhTable" cellpadding="2" cellspacing="0">
            <%
                RowSet rows = (RowSet) request.getAttribute("BJSL_LINES");
                if (rows != null && !rows.isEmpty())  {
                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
                    if(row.getValue("BARCODE").equals("")){
                        itemCodes += row.getValue("ITEM_CODE") + ",";
            %>
            <tr id="xhTr<%=i%>" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'">
                <td width="12%"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td width="15%"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td width="8%"><input type="text" name="quantity" id="quantity<%=i%>" value="<%=row.getValue("QUANTITY")%>"
                                       class="noborderYellow" style="width:100%;text-align:center">
                </td>
                <td style="display:none">
                    <input type="hidden" name="xhlineId" id="xhlineId<%=i%>" value="<%=row.getValue("LINE_ID")%>">
                    <input type="hidden" name="xhItemCode" id="xhItemCode<%=i%>" value="<%=row.getValue("ITEM_CODE")%>">

                </td>
            </tr>
            <%
                    }
                }
                itemCodes = itemCodes.substring(0,itemCodes.length()-1);
            }
            %>
        </table>
    </div>
</fieldset>
</form>
</body>
<script type="text/javascript">
    function init() {
    }

    function do_SelectItem() {
        var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.BJSL_ITEM_INFO%>&itemCodes=<%=itemCodes%>&organizationId=82";
        var popscript = "dialogWidth:51;dialogHeight:33;center:yes;status:no;scrollbars:no";
        /*   window.open(url);*/
        var items = window.showModalDialog(url, null, popscript);
        if (items) {
            var data = null;
            var tab = document.getElementById("dataTable");
            for (var i = 0; i < items.length; i++) {
                data = items[i];
                appendDTORow(tab, data);
            }
        }
    }

    function do_Approve(flag){
        document.mainForm.flag.value = flag;
        var paramObj = new Object();
        paramObj.orgId = "<%=user.getOrganizationId()%>";
        paramObj.useId = "<%=user.getUserId()%>";
        paramObj.groupId = "<%=user.getCurrGroupId()%>";
        paramObj.procdureName = "<%=procName%>";
        paramObj.flowCode = "";
        paramObj.submitH = "submitH()";
        assign(paramObj);
    }
    function submitH(){
        var flag = Number(document.mainForm.flag.value);
        var actVal = "";
        switch(flag){
            case 1: actVal = "<%=WebActionConstant.APPROVE_ACTION%>"; break;
            case 2: actVal = "<%=WebActionConstant.REJECT_ACTION%>"; break;
            case 3: actVal = "<%=WebActionConstant.RECEIVE_ACTION%>"; break;
            default :actVal = "<%=WebActionConstant.APPROVE_ACTION%>";
        }
        document.mainForm.act.value = actVal;
        document.mainForm.submit();
    }
    function do_reject(){
        mainForm.act.value = "<%=WebActionConstant.REJECT_ACTION%>";
        addApproveContent();
        mainForm.submit();
    }
    function do_SelectObject() {
        var projects = getLookUpValues("<%=LookUpConstant.LOOK_UP_ASSETS_OBJECT%>", 48, 30,"organizationId=<%=user.getOrganizationId()%>&objectCategory=<%=DictConstant.INV_NORMAL%>");
        if(projects){
//            dto2Frm(projects[0], "form1");
            document.mainForm.toObjectName.value = projects[0].workorderObjectName;
            document.mainForm.toObjectNo.value = projects[0].workorderObjectNo;
//            document.mainForm.toObjectLocation.value = projects[0].workorderObjectLocation;
        }
    }

</script>
</html>