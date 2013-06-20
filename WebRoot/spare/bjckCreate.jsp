<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%--
  Created by HERRY.
  Date: 2007-11-12
  Time: 10:28:12
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>

<html>
  <head><title>��������</title>
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
      <script language="javascript" src="/WebLibary/js/ajax.js"></script>
      <script language="javascript" src="/WebLibary/js/json.js"></script>
      <script language="javascript" src="/WebLibary/js/checkBarcode.js"></script>
  </head>
  <body leftmargin="1" topmargin="1">
<%
    AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) request.getAttribute("AIT_HEADER");
    RowSet rows = (RowSet) request.getAttribute("AIT_LINES");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
%>
  <form name="mainForm" action="/servlet/com.sino.ams.spare.servlet.BjckServlet" method="post">
<%--<jsp:include page="/flow/include.jsp" flush="true"/>--%>
<input type="hidden" name="act" value="">
<input type="hidden" name="flag" value="">
<input type="hidden" name="transId" value="<%=amsItemTransH.getTransId()%>">
<input type="hidden" name="transNo" value="<%=amsItemTransH.getTransNo()%>">
<input type="hidden" name="transType" value="<%=amsItemTransH.getTransType()%>">
<input type="hidden" name="createdBy" value="<%=amsItemTransH.getCreatedBy()%>">
<input type="hidden" name="fromObjectNo" value="<%=amsItemTransH.getFromObjectNo()%>">
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
                    <td width="9%" align="right">�ֿ⣺</td>
                    <td width="25%"><input type="text" name="fromObjectName" value="<%=amsItemTransH.getFromObjectName()%>" class="blueBorderYellow" style="width:80%">
                    <a href="#" onClick="do_SelectObject();" title="���ѡ��ֿ�" class="linka">[��]</a>

                    </td>
                    <%--<td width="9%" align="right">������У�</td>
                    <td width="25%"><%=amsItemTransH.getToOrganizationName()%>
                    </td>--%>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td><%=amsItemTransH.getCreatedUser()%>
                    </td>
                    <td align="right">�������ڣ�</td>
                    <td><%=String.valueOf(amsItemTransH.getCreationDate()).substring(0,10)%>
                    </td>
                    <td align="right">����״̬��</td>
                    <td><%=amsItemTransH.getTransStatusName()%></td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<fieldset>
    <legend>
        <%
            //���ݷ����״̬���ҵ�ǰ�û��Ǵ����˲��в���Ȩ��
            if(!amsItemTransH.getTransStatus().equals(DictConstant.COMPLETED)&& amsItemTransH.getCreatedBy().equals(user.getUserId())){
        %>
        <img src="/images/button/addData.gif" alt="�������" onclick="do_SelectItem();">
        <img src="/images/button/deleteLine.gif" alt="ɾ����" onClick="deleteTableRow(document.getElementById('dataTable'),'subCheck');">
        <img src="/images/button/save.gif" alt="����" id="img3" onClick="do_save(1);">
        <img src="/images/button/ok.gif" alt="ȷ��" id="img4" onClick="checkBarcode();">
        <%
            }
        %>
        <img src="/images/button/close.gif" alt="�ر�" onClick="window.close();">
    </legend>

    <script type="text/javascript">
        var columnArr = new Array("checkbox","������","�豸����","����ͺ�","������","��������");
        var widthArr = new Array("2%","12%","15%","15%","8%","8%");
        printTableHead(columnArr,widthArr);
    </script>
    <div style="overflow-y:scroll;height:550px;width:100%;left:1px;margin-left:0"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#9FD6FF" id="dataTable" cellpadding="0" cellspacing="0">
            <%if (rows == null || rows.isEmpty()) {%>
            <tr id="mainTr0" style="display:none">

                <td width="2%" align="center"><input type="checkbox" name="subCheck" id="subCheck0"
                                                     style="height:20px;margin:0;padding:0">
                </td>
                <td width="12%" align="center" class="readonlyInput"><input type="text" name="barcode" id="barcode0"
                                                                           value=""
                                                                           readonly class="noborderGray"
                                                                           style="width:100%;text-align:center">
                </td>
                <td width="15%" name="itemName" id="itemName0"></td>
                <td width="15%" name="itemSpec" id="itemSpec0"></td>

                <td width="8%" align="center"><input type="text" name="onhandQty" id="onhandQty0" readonly
                                                                           value="" class="noborderGray"
                                                                           style="width:100%;text-align:right">
                </td>
                <td width="8%" align="center"><input type="text" name="quantity" id="quantity0"   onblur="checkQty(this);"
                                                                           value="" class="blueborderYellow"
                                                                           style="width:100%;text-align:right">
                </td>
                <td style="display:none">
                    <input type="hidden" name="lineId" id="lineId0" value=""><input type="hidden" name="itemCode" id="itemCode0" value="">
                </td>
            </tr>
            <%

                }else{
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr id="mainTr<%=i%>">

                <td width="2%" align="center"><input type="checkbox" name="subCheck" id="subCheck<%=i%>"
                                                     style="height:20px;margin:0;padding:0">
                </td>
                <td width="12%" align="center" class="readonlyInput"><input type="text" name="barcode"
                                                                           id="barcode<%=i%>"
                                                                           value="<%=row.getValue("BARCODE")%>"
                                                                           readonly class="noborderGray"
                                                                           style="width:100%;text-align:center">
                </td>
                <td width="15%" name="itemName" id="itemName<%=i%>"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td width="15%" name="itemSpec" id="itemSpec<%=i%>"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td width="8%" align="center"><input type="text" name="onhandQty" id="onhandQty<%=i%>"  readonly
                                                                           value="<%=row.getValue("ONHAND_QTY")%>" class="noborderGray"
                                                                           style="width:100%;text-align:right">
                </td>
                <td width="8%" align="center"><input type="text" name="quantity" id="quantity<%=i%>"  onblur="checkQty(this);"
                                                                           value="<%=row.getValue("QUANTITY")%>" class="blueborderYellow"
                                                                           style="width:100%;text-align:right">
                </td>
                <td style="display:none">
                    <input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=row.getValue("LINE_ID")%>"><input type="hidden" name="itemCode" id="itemCode<%=i%>" value="<%=row.getValue("ITEM_CODE")%>">

                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</fieldset>
<div id="showMsg" style="color:red"><jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/></div>
  </form>
<%=WebConstant.WAIT_TIP_MSG%>
  </body>
<script type="text/javascript">
    function do_SelectObject() {
//        var objectCategory = getRadioValue("objectCategory");
        var projects = getLookUpValues("<%=LookUpConstant.LOOK_UP_ASSETS_OBJECT%>", 48, 30,"organizationId=<%=user.getOrganizationId()%>&objectCategory=<%=DictConstant.INV_NORMAL%>");
        if(projects){
//            dto2Frm(projects[0], "form1");
            document.mainForm.fromObjectName.value = projects[0].workorderObjectName;
            document.mainForm.fromObjectNo.value = projects[0].workorderObjectNo;
//            document.mainForm.toObjectLocation.value = projects[0].workorderObjectLocation;
        }
    }
    function do_SelectItem() {
        var fromObjectNo = document.mainForm.fromObjectNo.value;
        if(fromObjectNo == ""){
            alert("����ѡ��ֿ�!");
            return;
        }
        var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.BJSL_ITEM_INFO2%>&organizationId=<%=user.getOrganizationId()%>&objectNo="+fromObjectNo;
        var popscript = "dialogWidth:65;dialogHeight:30;center:yes;status:no;scrollbars:no";
        /*   window.open(url);*/
        var items = window.showModalDialog(url, null, popscript);
        if (items) {
            var data = null;
            var tab = document.getElementById("dataTable");
            for (var i = 0; i < items.length; i++) {
                data = items[i];
                if(!isItemExist(data)){
                appendDTORow(tab, data);
                    }
            }
        }
    }
            function isItemExist(itemObj) {
        var retVal = false;
        var tab = document.getElementById("dataTable");
        if (tab.rows) {
            var trObjs = tab.rows;
            var trCount = trObjs.length;
            var trObj = null;
            var itemValue = itemObj.itemCode;
            var rowValue = null;
            for (var i = 0; i < trCount; i++) {
                trObj = trObjs[i];
                rowValue = trObj.cells[6].childNodes[1].value;
                if (itemValue == rowValue) {
                    retVal = true;
                }
            }
        }
        return retVal;
    }
    function do_save(flag){
        if(flag == 1){
            document.mainForm.act.value = "<%=WebActionConstant.SAVE_ACTION%>";
        }else{
            document.mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
            document.mainForm.transStatus.value = "<%=DictConstant.COMPLETED%>";
        }
        document.mainForm.submit();
    }
    function do_submit(){
        document.mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
        document.mainForm.transStatus.value = "<%=DictConstant.COMPLETED%>";
        document.mainForm.submit();
    }

    function checkQty(obj){
        var id = obj.id.substring(8,obj.id.length);
        var qtyObj = document.getElementById("quantity"+id);
        var onhandQty = document.getElementById("onhandQty"+id).value;
        if(Number(qtyObj.value)>Number(onhandQty)){
            alert("�����������ܴ��������������������룡");
            qtyObj.focus();
        }
    }


</script>
</html>