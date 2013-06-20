<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-3-18
  Time: 18:22:34
  Function; �����̵㹤������ϸ.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
  <head><title>�����̵������ϸ��</title>
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
      <script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
       <style>
.approveInput{WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 1px ridge;font-size: 12px;text-align:left;}
</style>
  </head>
  <body leftmargin="1" topmargin="1" >
<%
    AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) request.getAttribute("AIT_HEADER");
    RowSet rows = (RowSet) request.getAttribute("AIT_LINES");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
      int total = 0;
    String transNo = amsItemTransH.getTransNo();
%>
  <form name="mainForm" action="/servlet/com.sino.ams.spare.servlet.SpareOrderServlet" method="post">
<%--<jsp:include page="/flow/include.jsp" flush="true"/>--%>
<input type="hidden" name="act" value="">
<%--<input type="hidden" name="toOrganizationId" value="<%=amsItemTransH.getToOrganizationId()%>">--%>
<table border="1" bordercolor="#9FD6FF" class="detailHeader" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1">
                 <tr height="22">
                    <td width="9%" align="right"></td>
                    <td width="20%"></td>
                    <td width="9%" align="right"></td>
                    <td width="25%"><h3>�����̵㵥</h3></td>
                    <td width="9%" align="right"></td>
                    <td width="25%"></td>
                </tr>
                 <tr height="22">
                     <td width="9%" align="right"></td>
                    <td width="20%"></td>
                    <td width="9%" align="right"></td>
                    <td width="25%"><h3></h3></td>
                   <td width="9%" align="right"></td>
                    <td width="25%"></td>
                </tr>
                <tr height="22">
                    <td width="9%" align="right">���ݺţ�</td>
                    <td width="20%"><%=amsItemTransH.getTransNo()%> </td>
                    <td width="9%" align="right">�ֿ⣺</td>
                    <td width="25%"><%=amsItemTransH.getFromObjectName()%></td>
                   <td width="11%" align="right">�������ͣ�</td>
                    <td width="20%">�̵㵥��</td>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td><%=amsItemTransH.getCreatedUser()%></td>
                    <td align="right">���ڣ�</td>
                    <td><%=amsItemTransH.getCreationDate()%></td>
                    <td align="right"></td>
                    <td></td>
                </tr>
                 <tr height="22">
                    <td align="right">�� ע��</td>
                    <td colspan="3" align="left"><%=amsItemTransH.getRemark()%></td>
                    <td align="right"></td>
                    <td><%=amsItemTransH.getAttribute4()%></tr>
            </table>
        </td>
    </tr>
</table>
<fieldset>
        <DIV align=center id='hiddenDiv'>
            <img src="/images/button/pageSet.gif" alt="��ӡҳ������" onclick="printSetup();">
            <img src="/images/button/printView.gif" alt="��ӡԤ��" onclick="printView();">
            <img src="/images/button/print.gif" alt="��ӡҳ��" onclick="do_print();">
            <img src="/images/button/close.gif" alt="�ر�" onClick="window.close();">
        </DIV>
    <!--<script type="text/javascript">-->
        <!--var columnArr = new Array("checkbox","������","�豸����","����ͺ�","�豸����","��������","�̵�����","����","ԭ��");-->
        <!--var widthArr = new Array("2%","12%","10%","10%","10%","8%","8%","10%","10%");-->
        <!--printTableHead(columnArr,widthArr);-->
    <!--</script>-->
    <div style="left:1px;width:100%;overflow-y:visible" class="crystalScroll">
    <table  border="1" width="100%" cellpadding="0" cellspacing="0">
        <tr height="30">
            <td width="12%" align="center">������</td>
            <td width="10%" align="center">�豸����</td>
            <td width="10%" align="center">����ͺ�</td>
            <td width="10%" align="center">�豸����</td>
            <td width="8%" align="center">��������</td>
            <td width="8%" align="center">�̵�����</td>
            <td width="10%" align="center">����</td>
            <td width="10%" align="center">ԭ��</td>
        </tr>
    </table>
</div>
    <div style="overflow-y:visible;height:550px;width:100%;left:1px;margin-left:0" class="crystalScroll"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#666666" id="dataTable" cellpadding="0" cellspacing="0">
            <%if (rows == null || rows.isEmpty()) {%>
            <tr id="mainTr0" height="30" style="display:none">

                <td width="12%" align="center" class="readonlyInput"><input type="text" name="barcode" id="barcode0"
                                                                           value=""
                                                                           readonly class="noborderGray"
                                                                           style="width:100%;text-align:center">
                </td>
                <td width="10%" name="itemName" id="itemName0"></td>
                <td width="10%" name="itemSpec" id="itemSpec0"></td>
                <td width="10%" name ="vendorName" id = "vendorName0"></td>
                <td width="8%" align="center"><input type="text" name="onhandQty" id="onhandQty0" readonly
                                                                           value="" class="noborderGray"
                                                                           style="width:100%;text-align:right">
                </td>
                <td width="8%" align="center"><input type="text" name="quantity" id="quantity0"
                                                                           value="" class="blueborderYellow"
                                                                           style="width:100%;text-align:right">
                </td>
                <td width="10%" align="center"><input type="text" name="diffNum" id="diffNum0"
                                                                           value="" class="blueborderYellow"
                                                                           style="width:100%;text-align:right">
                </td>
                <td width="10%" align="left"><input type="text" name="remarkl" id="remarkl0"   value="" class="blueborderYellow"
                                                                           style="width:100%;text-align:left">
                </td>
                <td style="display:none">
                    <input type="hidden" name="lineId" id="lineId0" value="">
                </td>
            </tr>
            <%
                }else{
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr id="mainTr<%=i%>" height="30">

                <td width="12%" align="center" class="readonlyInput"><%=row.getValue("BARCODE")%>
                </td>
                <td width="10%" name="itemName" id="itemName<%=i%>"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td width="10%" name="itemSpec" id="itemSpec<%=i%>"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td width="10%" name="vendorName" id="vendorName<%=i%>"><%=row.getValue("VENDOR_NAME")%>
                </td>
                <td width="8%" align="center"><%=row.getValue("ONHAND_QTY")%>
                </td>
                <td width="8%" align="center"><%=row.getValue("QUANTITY")%>
                </td>
                <td width="10%" align="center"><%=row.getValue("DIFF_NUM")%>
                </td>
                <td width="10%" align="center"><%=row.getValue("REMARK")%>
                </td>
                <td style="display:none">
                    <input type="hidden" name="lineId" id="lineId<%=i%>" value="<%=row.getValue("LINE_ID")%>">
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
<%--<table border="0" bordercolor="#666666" bgcolor="#EFEFEF" style="position:absolute; bottom:4px; left:60px; width:950px; height:37px;">--%>
  <div style="left:1px;width:100%;position:relative;top:300px;">
<table border="0" bordercolor="#666666" bgcolor="#EFEFEF" style="left:1px;width:100%;">              
        <tr height="22">
                    <td width="8%" align="right">���Ա��</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <!--<td width="8%" align="right">�����ˣ�</td>-->
                    <%--<td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>--%>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><%if(user.getOrganizationId().equals("82")&& transNo.substring(0,4).equals("4110")) {%><img src="/images/qianming.JPG"><%}else{%><input type="text"  style="width:100%"  readonly class="approveInput" value=""><%}%></td>
                    <%--<td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>--%>
                    <!--<td width="12%"><img src="/images/qianming.JPG"></td>-->
                </tr>
</table>    
</fieldset>
<div id="showMsg" style="color:red"><jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/></div>
<OBJECT id=wb height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name=wb></OBJECT>
  </form>
<%=WebConstant.WAIT_TIP_MSG%>
  </body>
<script type="text/javascript">

  function do_print() {
        //       if (confirm('ȷ����ӡ��')) {
        document.getElementById('hiddenDiv').style.visibility = 'hidden';
        //      alert(document.getElementById("wb"));
        document.getElementById("wb").execwb(6, 6);
        document.getElementById('hiddenDiv').style.visibility = 'visible';
        //        }
    }
    function printSetup() {
        document.getElementById("wb").execwb(8, 1);
    }

    //��ӡԤ������
    function printView() {
        document.getElementById('hiddenDiv').style.visibility = 'hidden';
        document.getElementById("wb").execwb(7, 1);
        document.getElementById('hiddenDiv').style.visibility = 'visible';
    }
</script>
</html>