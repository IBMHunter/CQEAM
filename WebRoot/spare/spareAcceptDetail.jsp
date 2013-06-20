<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%--
  Created by HERRY.
  Date: 2008-2-18
  Time: 20:45:25
--%>
<html>
<head><title>����������ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/LookUp.js"></script>
</head>
<body leftmargin="1" topmargin="1">
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) request.getAttribute("AIT_HEADER");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    String transType = StrUtil.nullToString(request.getParameter("transType"));
%>
<form name="mainForm" action="/servlet/com.sino.ams.spare.accept.servlet.SpareAcceptServlet" method="post">
<input type="hidden" name="act" value="">
<input type="hidden" name="transId" value="<%=amsItemTransH.getTransId()%>">
<input type="hidden" name="transType" value="<%=transType%>">
<input type="hidden" name="toObjectNo" value="<%=amsItemTransH.getToObjectNo()%>">
<input type="hidden" name="transStatus" value="<%=amsItemTransH.getTransStatus()%>">
<input type="hidden" name="createdBy" value="<%=amsItemTransH.getCreatedBy()%>">
<table border="1" bordercolor="#9FD6FF" class="detailHeader" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1">
                <tr height="22">
                    <td width="9%" align="right">���ݺţ�</td>
                    <td width="20%"><input type="text" name="transNo" value="<%=amsItemTransH.getTransNo()%>" readonly
                                           class="detailHeader" style="width:100%;border:none">
                    </td>
                    <td width="9%" align="right">���ղֿ����ƣ�</td>
                    <td width="25%"><input type="text" name="toObjectName" value="<%=amsItemTransH.getToObjectName()%>"
                                           class="blueborderYellow" style="width:80%">
                        <a href="#" onClick="do_SelectObject();" title="���ѡ��ֿ�"
                           class="linka">[��]</a>
                    </td>
                    <td width="9%" align="right">���ղֿ�ص㣺</td>
                    <td width="25%"><input type="text" name="toObjectLocation"
                                           value="<%=amsItemTransH.getToObjectLocation()%>"
                                           class="blueborderGray">
                    </td>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td><%=amsItemTransH.getCreatedUser()%>
                    </td>
                    <td align="right">�������ڣ�</td>
                    <td><%=amsItemTransH.getCreationDate()%>
                    </td>
                    <td align="right">����״̬��</td>
                    <td><%=amsItemTransH.getTransStatusName()%>
            </table>
        </td>
    </tr>
</table>
<fieldset>
    <legend>
        <img src="/images/button/ok.gif" alt="ȷ��" id="img4" onClick="do_receive();">
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
        var columnArr = new Array( "������", "�豸����", "����ͺ�", "����", "��������");
        var widthArr = new Array( "12%", "15%", "15%", "8%", "8%");
        printTableHead(columnArr, widthArr);
    </script>
    <div style="overflow-y:scroll;height:580px;width:100%;left:1px;margin-left:0"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#9FD6FF" id="dataTable" cellpadding="1" cellspacing="0">
            <%
                RowSet rows = (RowSet) request.getAttribute("AIT_LINES");
                if (rows != null || !rows.isEmpty()) {
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr id="mainTr<%=i%>" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'">

                <td width="12%" align="center"><input type="text" name="barcode"
                                                      id="barcode<%=i%>"
                                                      value="<%=row.getValue("BARCODE")%>"
                                                      class="noborderGray"
                                                      style="width:100%;text-align:center">
                </td>
                <td width="15%" name="itemName" id="itemName<%=i%>"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td width="15%" name="itemSpec" id="itemSpec<%=i%>"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td width="8%" align="center"><input type="text" name="quantity" id="quantity<%=i%>"
                                                     value="<%=row.getValue("QUANTITY")%>" class="noborderGray"
                                                     style="width:100%;text-align:right">
                </td>
                <td width="8%" align="center"><input type="text" name="acceptQty" id="acceptQty<%=i%>"
                                                     value="<%=row.getValue("ACCEPT_QTY")%>" class="blueborderYellow"
                                                     style="width:100%;text-align:right">
                </td>
                <td style="display:none">
                    <input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=row.getValue("DETAIL_ID")%>">
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
</body>
<script type="text/javascript">
    function do_SelectObject() {
        var projects = getLookUpValues("<%=LookUpConstant.LOOK_UP_ASSETS_OBJECT%>", 48, 30, "organizationId=<%=user.getOrganizationId()%>&objectCategory=<%=DictConstant.INV_NORMAL%>");
        if (projects) {
            //            dto2Frm(projects[0], "form1");
            document.mainForm.toObjectName.value = projects[0].workorderObjectName;
            document.mainForm.toObjectNo.value = projects[0].workorderObjectNo;
            document.mainForm.toObjectLocation.value = projects[0].workorderObjectLocation;
        }
    }
    function do_receive(){
        var toObjectNo = document.mainForm.toObjectNo.value;
        if(toObjectNo == ""){
            alert("��ѡ����ղֿ�!");
            return;
        }
        mainForm.act.value = "<%=WebActionConstant.RECEIVE_ACTION%>";
        mainForm.submit();
    }
</script>
</html>