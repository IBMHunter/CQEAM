<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-3-30
  Time: 14:22:04
  Function:�����������뵥��ӡ
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.spare.dto.AmsItemTransHDTO" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO"%>
<%@ page import="com.sino.framework.security.bean.SessionUtil"%>
<html>
<head><title>�����������뵥</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/flow/flow.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
     <style>
.approveInput{WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 1px ridge;font-size: 12px;text-align:left;}
</style>
</head>
<body leftmargin="1" topmargin="1">
<%
    AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) request.getAttribute("AIT_HEADER");
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    String transNo = amsItemTransH.getTransNo();
%>
<form name="mainForm" action="/servlet/com.sino.ams.spare.servlet.SpareDiffServlet" method="post">
<input type="hidden" name="act" value="">
<input type="hidden" name="transId" value="<%=amsItemTransH.getTransId()%>">
<input type="hidden" name="transType" value="<%=amsItemTransH.getTransType()%>">
<input type="hidden" name="createdBy" value="<%=amsItemTransH.getCreatedBy()%>">
<input type="hidden" name="toObjectNo" value="<%=amsItemTransH.getToObjectNo()%>">
<input type="hidden" name="transStatus" value="<%=amsItemTransH.getTransStatus()%>">
<input type="hidden" name="fromOrganizationId" value="<%=amsItemTransH.getFromOrganizationId()%>">
<input type="hidden" name="groupId" value="">
<table border="0" width = "100%">
    <tr>
        <td>
            <table width="100%" id="table2" cellspacing="1" border="0">
                <tr height="22">
                    <td width="9%" align="right"></td>
                    <td width="20%"></td>
                    <td width="12%" align="right"></td>
                    <td width="22%"><h3>�����������뵥</h3></td>
                    <td width="12%" align="right"></td>
                    <td width="22%"></td>
                </tr>
                 <tr height="22">
                     <td width="9%" align="right"></td>
                    <td width="20%"></td>
                    <td width="12%" align="right"></td>
                    <td width="22%"><h3></h3></td>
                   <td width="12%" align="right"></td>
                    <td width="22%"></td>
                </tr>
                <tr height="22">
                    <td width="9%" align="right">���ݺţ�</td>
                    <td width="20%"><%=amsItemTransH.getTransNo()%></td>
                    <td width="12%" align="right">���빫˾��</td>
                    <td width="22%"><%=amsItemTransH.getFromOrganizationName()%></td>
                </tr>
                <tr height="22">
                    <td align="right">�����ˣ�</td>
                    <td><%=amsItemTransH.getCreatedUser()%></td>
                    <td align="right">�������ڣ�</td>
                    <td><%=amsItemTransH.getCreationDate()%></td>
                    <td align="right"></td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td align="right">��ע��</td>
                    <td colspan="11"><%=amsItemTransH.getRemark()%> </td>
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
  <div style="left:1px;width:100%;overflow-y:visible" class="crystalScroll">
    <table  border="1" width="100%"  cellpadding="0" cellspacing="0" bordercolor="#666666">
        <tr height="30">
            <td width="10%" align="center">�豸����</td>
            <td width="10%" align="center">�豸�ͺ�</td>
            <!--<td width="8%" align="center">���к�</td>--> <!--����SU-->
            <td width="10%" align="center">�豸����</td>
            <td width="10%" align="center">��;</td>
            <td width="10%" align="center">����</td>
            <td width="10%" align="center">���ϵص�</td>
            <!--<td width="12%" align="center">����ԭ��</td>-->
            <td width="5%" align="center">��������</td>
            <td width="6%" align="center">���̴�����</td>
            <td width="6%" align="center">����������</td>
            <td width="6%" align="center">ʵ������</td>
        </tr>
    </table>
</div>
<div style="width:100%;left:1px;margin-left:0;overflow-y:visible;" class="crystalScroll">
    <table width="100%" border="1" bordercolor="#9FD6FF" id="dataTable" cellpadding="1" cellspacing="0">
        <%
            RowSet rows = (RowSet) request.getAttribute("AIT_LINES");
            if (rows != null && !rows.isEmpty()) {
                int k = 0;

                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
        %>
        <tr id="mainTr<%=i%>" height="30" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#FFFFFF'">
            <td width="10%" align="center"><%=row.getValue("ITEM_NAME")%>
            </td>
            <td width="10%" align="center"><%=row.getValue("ITEM_SPEC")%>
            </td>
            <%--<td width="8%" align="center"><%=row.getValue("SERIAL_NO")%>--%>
            <!--</td>-->
            <td width="10%" align="center"><%=row.getValue("ITEM_CATEGORY")%>
            </td>
            <td width="10%" align="center"><%=row.getValue("SPARE_USAGE")%>
            </td>
            <td width="10%" align="center"><%=row.getValue("VENDOR_NAME")%>
            </td>
            <td width="10%" align="center"><%=row.getValue("REASONS")%>
            </td>
            <td width="5%" align="center"><%=row.getValue("QUANTITY")%>
            </td>
            <td width="6%" align="center"><%=row.getValue("PRO_QTY")%>
            </td>
            <td width="6%" align="center"><%=row.getValue("ORD_QTY")%>
            </td>
            <td width="6%" align="center"><%=row.getValue("ACTUAL_QTY")%>
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
  <!--�ɷ���-->


</div>

 
<%--<table border="0" bordercolor="#666666" bgcolor="#EFEFEF" style="position:absolute; bottom:4px; left:60px; width:950px; height:37px;">--%>
 <div style="left:1px;width:100%;position:relative;top:300px;">
<table border="0" bordercolor="#666666" style="left:1px;width:100%;">           
                <tr height="22">
                    <td width="8%" align="right">���Ա��</td>
                    <%--<td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>--%>
                    <td width="12%"><%if(user.getOrganizationId().equals("82")/*&& transNo.substring(0,4).equals("4110")*/) {%><img src="/images/fanjianqiang.jpg"><%}else{%><input type="text"  style="width:100%" readonly  class="approveInput" value=""><%}%></td>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>
                    <!--<td width="8%" align="right">�����ˣ�</td>-->
                    <%--<td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>--%>
                    <td width="8%" align="right">�����ˣ�</td>
                    <td width="12%"><%if(user.getOrganizationId().equals("82")/*&& transNo.substring(0,4).equals("4110")*/) {%><img src="/images/qianming.JPG"><%}else{%><input type="text"  style="width:100%"  readonly class="approveInput" value=""><%}%></td>
                    <%--<td width="12%"><input type="text"  style="width:100%" readonly  class="approveInput" value=""></td>--%>
                    <!--<td width="12%"><img src="/images/qianming.JPG"></td>-->
                </tr>
</table>
 </div>
</form>
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
</body>
<script type="text/javascript">
function do_print(){
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
<OBJECT id=wb height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name=wb></OBJECT>