<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.ams.spare.repair.dto.AmsCustomerInfoDTO" %>
<%@ page import="com.sino.ams.spare.repair.dto.AmsVendorInfoDTO" %>
<%@ page import="com.sino.base.util.CalendarUtil" %>
<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2007-11-12
  Time: 9:23:08
--%>
<html>
<head><title>�������޵�</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/flow/flow.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <style>
.approveInput{WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 1px ridge;font-size: 12px;text-align:left;}
</style>
</head>
<body leftmargin="1" topmargin="1">
<%--<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>--%>
<%
    SfUserDTO sfUser = (SfUserDTO) session.getAttribute(WebConstant.USER_INFO);
    AmsCustomerInfoDTO cust = (AmsCustomerInfoDTO) request.getAttribute("CUSTOMER_INFO");
    AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) request.getAttribute(WebAttrConstant.AMS_ITEMH_REPAIR);
    AmsVendorInfoDTO vendorDTO = (AmsVendorInfoDTO) request.getAttribute(WebAttrConstant.BARCODE_PRINT_DTO);
%>
<form name="mainForm" action="/servlet/com.sino.ams.spare.repair.servlet.BjSendRepairServlet" method="post">
<input type="hidden" name="act" value="">
<table width="100%" border="0" id="table1">
    <tr>
        <td>
            <table width="100%" id="table2" border="0" cellspacing="1">
                <tr valign="center">
                    <td colspan="4"><font color="" size="4"><b>
                        <center>ɽ��ʡ�ƶ�ͨ�Ź�˾ά���ͻ�ί����</center>
                    </b></font></td>
                </tr>
                <tr valign="center">
                    <td colspan="4"><font color="" size="3"><b>
                        <center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ͻ���</center>
                    </b></font></td>
                </tr>
                <tr height="30">
                    <td width="16%" align="right">�û����ƣ�</td>
                    <td width="15%"><strong>ɽ��ʡ�ƶ�ͨ�Ź�˾</strong></td>
                    <td width="12%" align="right"> ���񷽣�</td>
                    <td width="40%"><%=vendorDTO.getVendorName()%>
                    </td>
                </tr>
                <tr height="24">
                    <td width="16%" align="right">Customer��</td>
                    <td width="10%"><%=cust.getCustomer()%>
                    </td>
                    <td width="12%" align="right"> ���񷽣�</td>
                    <td width="40%"><%=vendorDTO.getVendorName()%>
                    </td>
                </tr>
                <tr height="24">
                    <td width="16%" align="right">���޷���ַ�ʱࣺ</td>
                    <td width="40%"><%=cust.getAddress()%>
                    </td>
                    <td width="12%" align="right"> ��ַ�ʱࣺ</td>
                    <td width="40%"><%=vendorDTO.getAddress()%>
                    </td>
                </tr>
                <tr height="24">
                    <td width="10%" align="right">���޷���ϵ�ˣ�</td>
                    <td width="40%"><%=cust.getContact()%>
                    </td>
                    <td width="12%" align="right">��ϵ�ˣ�</td>
                    <td width="40%"><%=vendorDTO.getContact()%>
                    </td>
                </tr>
                <tr height="24">
                    <td width="10%" align="right">���޷��绰��</td>
                    <td width="8%"><%=cust.getTel()%>
                    </td>
                    <td width="10%" align="right">��ϵ�绰��</td>
                    <td width="40%"><%=vendorDTO.getFax()%>
                    </td>
                </tr>
                <tr height="24">
                    <td width="10%" align="right">���޷����棺</td>
                    <td width="10%"><%=cust.getFax()%>
                    </td>
                    <td width="10%" align="right">���棺</td>
                    <td width="10%"><%=vendorDTO.getFax()%>
                    </td>
                </tr>
                <tr height="24">
                    <td width="10%" align="right"></td>
                    <td width="8%"></td>
                    <td width="10%" align="right">ί�����ţ�</td>
                    <td width="10%"><%=amsItemTransH.getAttribute1()%>
                    </td>
                </tr>
                <tr height="24">
                    <td width="10%" align="left">�����ˣ�<%=amsItemTransH.getAttribute2()%></td>
                    <td width="8%"> ��ֵ��<%=amsItemTransH.getAttribute3()%></td>
                    <td width="10%" align="left">ί�������ڣ�</td>
                    <td width="10%"><%=CalendarUtil.getCurrDate()%></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<DIV align=center id='hiddenDiv'>
    <img src="/images/button/pageSet.gif" alt="��ӡҳ������" onclick="printSetup();">
    <img src="/images/button/printView.gif" alt="��ӡԤ��" onclick="printView();">
    <img src="/images/button/print.gif" alt="��ӡҳ��" onclick="do_print();">
    <img src="/images/button/close.gif" alt="�ر�" onClick="window.close();">
</DIV>
<div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll" id="headDiv">
    <table id="$$$headerTable$$$" border="1" width="100%" cellpadding="0" cellspacing="0">
        <tr height="22">
            <td width="5%" align="center">���</td>
            <td width="15%" align="center">��Ʒ����</td>
            <td width="19%" align="center">�豸</td>
            <td width="12%" align="center">������</td>
            <td width="13%" align="center">������</td>
            <td width="10%" align="center">����ԭ��</td>
            <td width="10%" align="center">���ϵ�</td>
            <td width="10%" align="center">����ʱ��</td>
        </tr>
    </table>
</div>
<div style="overflow-y:scroll;left:0px;width:100%;height:360px" class="crystalScroll">
    <table width="100%" border="1" bordercolor="#666666">
        <%
            RowSet rows = (RowSet) request.getAttribute(WebAttrConstant.AMS_INSTRUMENTL_DTO);
            if (rows != null && rows.getSize() > 0) {
                for (int i = 0; i < rows.getSize(); i++) {
                    Row row = null;
                    row = rows.getRow(i);
        %>
        <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
            <td width="5%"><%=(i+1)%>
            </td>
            <td width="15%" align="center"><%=row.getValue("ITEM_NAME")%>
            </td>
            <td width="19%"><%=row.getValue("ITEM_SPEC")%>
            </td>
            <td width="12%" align="left"><%=row.getValue("BARCODE")%>
            </td>
            <td width="13%" align="center"><%=row.getValue("SERIAL_NO")%>
            </td>
            <td width="10%"><%=row.getValue("TROUBLE_REASON")%>
            </td>
            <td width="10%"><%=row.getValue("TROUBLE_LOC")%>
            </td>
            <td width="10%">
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
 <div style="left:1px;width:100%;" >
<table border="0" bordercolor="#666666" bgcolor="#EFEFEF" style="left:1px;width:100%;">
                <tr height="22">
                    <td width="8%" align="right">���Ա��</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                </tr>
</table>
 </div>
</form>
<script type="text/javascript">
    function do_print() {
        //       if (confirm('ȷ����ӡ��')) {
        document.getElementById('hiddenDiv').style.visibility = 'hidden';
        //      alert(document.getElementById("wb"));
//        document.getElementById("wb").printing.header ="";
//        document.getElementById("wb").printing.footer ="";
        document.getElementById("wb").execwb(6, 6);
//       window.print();
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

//      window.onload()   {
//      factory.printing.header   =   ""
//      factory.printing.footer   =   ""
//      }

</script>
</body>
</html>
<OBJECT id=wb height=0 width=0  classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name=wb></OBJECT>
