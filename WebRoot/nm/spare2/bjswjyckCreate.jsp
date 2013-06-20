<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.nm.spare2.dto.AmsItemAllocateHDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%--
  Created by HERRY.
  Date: 2008-6-20
  Time: 11:16:43
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>�豸ʵ����ó���</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/RadioProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
    <script language="javascript" src="/flow/flow.js"></script>
</head>
<body leftmargin="1" topmargin="1" onload="init();">
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsItemAllocateHDTO headerDto = (AmsItemAllocateHDTO) request.getAttribute("AIT_HEADER");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    String divHeight = "480";
%>
<form name="mainForm" action="/servlet/com.sino.nm.spare2.servlet.BjswjyckServlet" method="post">
<jsp:include page="/flow/include.jsp" flush="true"/>
<input type="hidden" name="act" value="">
<input type="hidden" name="flag" value="">
<input type="hidden" name="transId" value="<%=headerDto.getTransId()%>">
<input type="hidden" name="transNo" value="<%=headerDto.getTransNo()%>">
<input type="hidden" name="transType" value="<%=headerDto.getTransType()%>">
<input type="hidden" name="createdBy" value="<%=headerDto.getCreatedBy()%>">
<input type="hidden" name="fromObjectNo" value="<%=headerDto.getFromObjectNo()%>">
<input type="hidden" name="transStatus" value="<%=headerDto.getTransStatus()%>">
<input type="hidden" name="toOrganizationId" value="<%=headerDto.getToOrganizationId()%>">
<input type="hidden" name="freightDeptCode" value="<%=headerDto.getFreightDeptCode()%>">
<input type="hidden" name="freightMisUser" value="<%=headerDto.getFreightMisUser()%>">
<input type="hidden" name="resendDetailId" value="">
<table border="1" bordercolor="#9FD6FF" class="detailHeader" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1">
                <tr height="22">
                    <td width="9%" align="right">�������ţ�</td>
                    <td width="20%"><%=headerDto.getTransNo()%>
                    </td>
                    <td width="13%" align="right">�����ֿ⣺</td>
                    <td width="20%"><%=headerDto.getFromObjectName()%>
                    </td>
                    <td width="12%" align="right">������У�</td>
                    <td width="22%"><%=headerDto.getToOrganizationName()%>
                    </td>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td><%=headerDto.getCreatedUser()%>
                    </td>
                    <td align="right">�������ڣ�</td>
                    <td><%=headerDto.getCreationDate()%>
                    </td>
                    <td align="right">Ԥ�ƹ黹���ڣ�</td>
                    <td><%=headerDto.getRespectReturnDate()%></td>
                </tr>

                <tr height="22">
                    <td align="right">���ⲿ�ţ�</td>
                    <td><input type="text" name="freightDeptName" value="<%=headerDto.getFreightDeptName()%>" readonly>
                        <a href="#" id="objectSelecter" onClick="do_selectDept();" title="���ѡ����ⲿ��"
                           class="linka">[��]</a>
                    </td>
                    <td align="right">���ⲿ�ű�������Ա��</td>
                    <td><input type="text" name="freightMisUserName" value="<%=headerDto.getFreightMisUserName()%>" readonly>
                        <a href="#" onClick="do_selectSpareManager();" title="���ѡ����ⲿ�ű�������Ա"
                           class="linka">[��]</a>
                    </td>
                    <td align="right">���ⲿ��ʵ��ֹ�Ա��</td>
                    <td><input type="text" name="freightUserName" value="<%=headerDto.getFreightUserName()%>"></td>
                </tr>

                <tr height="22">
                    <td align="right">���ձ�����Ա��</td>
                    <td><input type="text" name="receiveUserName" value="<%=headerDto.getReceiveUserName()%>">
                    </td>
                    <td align="right">���ձ�����Ա��ϵ�绰��</td>
                    <td><input type="text" name="receiveUserTel" value="<%=headerDto.getReceiveUserTel()%>">
                    </td>
                    <td align="right">����״̬��</td>
                    <td>������</td>
                </tr>

                <tr height="30">
                    <td align="right">��ע��</td>
                    <td colspan="3"><%=headerDto.getRemark()%>
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
        <img src="/images/button/ok.gif" alt="ȷ��" id="img3" onClick="do_submit();">
        <img src="/images/button/close.gif" alt="�ر�" onClick="window.close();">
    </legend>

    <script type="text/javascript">
        var columnArr = new Array("���ϱ���", "�豸����", "����ͺ�", "��������", "�ѽ��ձ�����", "��;��������", "δ�յ�������", "δ���ͱ�����", "���μ�������", "����");
        var widthArr = new Array("10%", "18%", "18%", "7%", "7%", "7%", "7%", "7%", "7%", "7%");
        printTableHead(columnArr, widthArr);
    </script>
    <div style="overflow-y:scroll;height:<%=divHeight%>px;width:100%;left:1px;margin-left:0"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#9FD6FF" id="xhTable" cellpadding="1" cellspacing="0">
            <%
                RowSet rows = (RowSet) request.getAttribute("AIT_LINES");
                if (rows != null && !rows.isEmpty()) {
                    Row row = null;
                    int quantity = 0;
                    int acceptQty = 0;
                    int quantityOnWay = 0;
                    int quantityNotReceived = 0;
                    int quantityNotSend = 0;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
                        quantity = Integer.parseInt(row.getValue("QUANTITY").toString());
                        if (row.getValue("ACCEPT_QTY").toString() == ""){
                            acceptQty = 0;
                        }else{
                        acceptQty = Integer.parseInt(row.getValue("ACCEPT_QTY").toString());
                        }
                        quantityOnWay = Integer.parseInt(row.getValue("QUANTITY_ON_WAY").toString());
                        quantityNotReceived = Integer.parseInt(row.getValue("QUANTITY_NOT_RECEIVED").toString());
                        quantityNotSend = quantity - acceptQty - quantityOnWay -quantityNotReceived;



            %>
            <tr id="xhTr<%=i%>" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'" onclick="this.cells[0].childNodes[0].checked=true;">
                <td width="10%" align="center"><%--<input type="radio" name="selectItemCode"
                                                     value="<%=row.getValue("ITEM_CODE")%>:<%=row.getValue("QUANTITY")%>">--%>
                    <input type="text" name="barcode" value="<%=row.getValue("BARCODE")%>" readonly class="noborderGray" style="width:100%">
                </td>
                <td width="18%"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td width="18%"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td width="7%"><input type="text" name="quantity" id="quantity<%=i%>"
                                       value="<%=quantity%>"
                                       class="noborderYellow" style="width:100%;text-align:right">
                </td>
                <td width="7%" align="right"><%=acceptQty%>
                </td>
                <td width="7%" align="right"><%=quantityOnWay%>
                </td>
                <td width="7%" align="right"><%=quantityNotReceived%>
                </td>
                <td width="7%" align="right"><%=quantityNotSend%>
                </td>
                <td width="7%"><input type="text" name="freightQty" style="width:100%;text-align:right" onblur="checkQty(this,'<%=quantityNotSend%>');">
                </td>
                <td width="7%" align="center"><%if(quantityNotReceived>0){%>
                    <a href="#" class="linka" onclick="do_resend('<%=row.getValue("DETAIL_ID")%>');">[���¼���]</a>
                    <%}%>
                </td>
                <td style="display:none">
                    <input type="hidden" name="detailId" id="detailId<%=i%>" value="<%=row.getValue("DETAIL_ID")%>">
                    <input type="hidden" name="itemCode" id="itemCode<%=i%>" value="<%=row.getValue("ITEM_CODE")%>">

                </td>
            </tr>
            <%
                    }
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
    function do_SelectObject() {
        var projects = getLookUpValues("<%=LookUpConstant.LOOK_UP_ASSETS_OBJECT%>", 48, 30, "organizationId=<%=user.getOrganizationId()%>");
        if (projects) {
            //            dto2Frm(projects[0], "form1");
            document.mainForm.toObjectName.value = projects[0].workorderObjectName;
            document.mainForm.toObjectNo.value = projects[0].workorderObjectNo;
            document.mainForm.toObjectLocation.value = projects[0].workorderObjectLocation;
        }
    }
    function do_SelectItem() {
        var selectedItemCode = getRadioValue("selectItemCode");
        if (selectedItemCode == null || selectedItemCode == "") {
            alert("����ѡ��һ���豸�����ͺţ�");
            return;
        }
        var ic = selectedItemCode.split(":");
        var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.BJSL_ITEM_INFO2%>&itemCodes=" + ic[0] + "&organizationId=<%=headerDto.getFromOrganizationId()%>";
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
    function do_SavePo(flag) {
        if (flag == 1) {
            document.mainForm.act.value = "<%=WebActionConstant.SAVE_ACTION%>";
        } else {
            document.mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
            document.mainForm.transStatus.value = "<%=DictConstant.IN_PROCESS%>";
        }
        document.mainForm.submit();
    }

    function do_submit() {
        document.mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
        document.mainForm.submit();
    }

    function checkQty(obj,quantityNotSend) {
        if(isNaN(obj.value)){
            alert("���������֣�");
            obj.focus();
            obj.value = "";
            return;
        }
        var freightQty = Number(obj.value);
        if(freightQty < 0){
            alert("��������������");
            obj.focus();
            obj.value = "";
            return;
        }
        if (freightQty > Number(quantityNotSend) ) {
            alert("���η����������ܳ���δ���ͱ����������������룡");
            obj.focus();
        }
    }
    function do_selectDept() {
        var retVal = getLookUpValues("<%=LookUpConstant.LOOK_UP_MIS_DEPT%>", 48, 30, "");
        if (retVal) {
            //            dto2Frm(projects[0], "form1");
            document.mainForm.freightDeptCode.value = retVal[0].deptCode;
            document.mainForm.freightDeptName.value = retVal[0].deptName;
        }
    }
    function do_selectSpareManager() {
        var retVal = getLookUpValues("<%=LookUpConstant.LOOK_UP_USER%>", 48, 30, "");
        if (retVal) {
            //            dto2Frm(projects[0], "form1");
            document.mainForm.freightMisUserName.value = retVal[0].executeUserName;
            document.mainForm.freightMisUser.value = retVal[0].executeUser;
        }
    }
    function do_resend(detailId){
        document.mainForm.resendDetailId.value = detailId;
        document.mainForm.act.value = "RESEND";
        document.mainForm.submit();
    }
</script>
</html>