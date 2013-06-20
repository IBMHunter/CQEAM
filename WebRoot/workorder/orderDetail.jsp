<%--
  Created by IntelliJ IDEA.
  User: V-jiachuanchuan
  Date: 2007-11-12
  Time: 17:23:36
  Function:��ʾ������ϸ��Ϣ
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<%@ page import="com.sino.ams.constant.DictConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.workorder.dto.EtsWorkorderDTO" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.ams.workorder.dto.EtsWorkorderBatchDTO" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%
    EtsWorkorderDTO order = (EtsWorkorderDTO) request.getAttribute(WebAttrConstant.ETS_WORKORDER_DTO);
    EtsWorkorderBatchDTO orderBatch = (EtsWorkorderBatchDTO) request.getAttribute(WebAttrConstant.ETS_WORKORDER_BATCH_DTO);

    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);

    RowSet prevDtlRows = (RowSet) request.getAttribute(WebAttrConstant.PRE_SCAN_DTL);
    RowSet currDtlRows = (RowSet) request.getAttribute(WebAttrConstant.CUR_SCAN_DTL);
    RowSet fixNewRows = (RowSet) request.getAttribute(WebAttrConstant.CUR_OBJ_SCHEME_RST);
    RowSet diffDtRows = (RowSet) request.getAttribute(WebAttrConstant.DIFF_SCAN_DTL);
    DTOSet diffDTOSet = (DTOSet) request.getAttribute(WebAttrConstant.DIFF_DTOSET);

    String isCheck = order.getWorkorderType().equals(DictConstant.ORDER_TYPE_CHECK) ? "Y" : "N"; //Ѳ��
    String isHdv = order.getWorkorderType().equals(DictConstant.ORDER_TYPE_HDV) ? "Y" : "N" ;//����

    String firstPendingOrder = (String) request.getAttribute("firstPendingOrder");

    if (StrUtil.isEmpty(firstPendingOrder)) {
        firstPendingOrder = "";
    }
    String boundenHtml = (String) request.getAttribute(WebAttrConstant.BOUNDEN);
    if (StrUtil.isEmpty(boundenHtml)) {
        boundenHtml = "";
    }
    String diffCount = (String) request.getAttribute("diffCount");

    boolean showScheme = false;
    String scheme_title = "";
    if (order.getWorkorderType().equals("") || order.getWorkorderType().equals("12") || order.getWorkorderType().equals("14")) {
        showScheme = false;
    } else if (order.getWorkorderType().equals("13")) {//ά��
        showScheme = true;
        scheme_title = "���üƻ�";
    } else {
        showScheme = true;
        scheme_title = "�ƻ�����";
    }

%>
<html>
<head>
    <title>������Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/tab.css">
    <script language="javascript" src="/WebLibary/js/tab.js"></script>
    <link href="/WebLibary/css/css.css" rel="stylesheet" type="text/css">
    <link href="/WebLibary/css/styles.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/datepicker.js"></script>
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/flow/flow.js"></script>
    <script type="text/javascript">
        var rightArr = new Array();
        var groupArr = new Array();
        var roleArr = new Array();
        var winstyle = "dialogWidth:20.1;dialogHeight:14.8;center:yes;status:no";


        var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "window.parent.close()");
        var ArrAction1 = new Array(false, "��������", "addpop.gif", "��������", "viewFlow");
        var ArrAction2 = new Array(false, "�豸��������", "t_stat.gif", "�豸��������", "showNum");
        var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2);

        var ArrSinoViews = new Array();
        var ArrSinoTitles = new Array();
        <%if(isCheck.equals("Y")){%>
        //ShowSinoButton(3);
        <%}%>

    </script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin=0 topmargin=0 style="overflow:auto" onload="initPage();">
<script>
    var tabBox = new TabBox("tab")
    tabBox.addtab("batchInfo", "������Ϣ")
    tabBox.addtab("orderInfo", "������Ϣ")
    tabBox.addtab("currDt", "����ɨ����")

    //<%if(showScheme){%>
    //tabBox.addtab("fixNew", "<%=scheme_title%>")
    //<%}%>
    <%
      if(isCheck.equals("Y") || isHdv.equals("Y")){
    	 if (isCheck.equals("Y")) {
    %>
    		tabBox.addtab("prevDt", "�ϴ�Ѳ����")
    	<%} else {%>
    	 	tabBox.addtab("prevDt", "�ϴν��ӽ��")
    	<%}%>
    	tabBox.addtab("diffDt", "�������")
   	<%}%>
    printTitleBar("<%=order.getWorkorderFlagDesc()%>����");
    printToolBar();
    tabBox.init();
</script>

<form name="mainFrm" method="post" action="/servlet/com.sino.ams.workorder.servlet.OrderDetailServlet">
<input type="hidden" name="workorderNo" value="<%=order.getWorkorderNo()%>">
<input type="hidden" name="act" value="">
<input type="hidden" name="systemid" value="<%=order.getSystemid()%>">
<input type="hidden" name="isCheck" value="<%=isCheck%>">
<input type="hidden" name="firstPendingOrder" value="<%=firstPendingOrder%>">

<table align="center" width='98%' border="0" cellpadding="2" cellspacing="0" bordercolor="#666666"
       bordercolordark="#FFFFFF">
<tr>
<td>
<div id="batchInfo" style='display:none'>
    <table width="95%" border="1" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
        <tr>
            <td width="12%">��������:</td>
            <td width="12%" align="left"><input name="workorderBatch" type="text" size="60" readonly class="input_style2"
                                   value="<%=orderBatch.getWorkorderBatch()%>"></td>
            <td width="20%" align="right">��������:</td>
            <td width="30%" align="left"><input readonly class="input_style2" size="60" name="workorderBatchName" type="text"
                       value="<%=orderBatch.getWorkorderBatchName()%>"></td>
        </tr>
        <tr>
            <td >��������:</td>
            <td colspan="3" ><input readonly class="input_style2" size="60" type="text" name="prjId"
                                               value="<%=orderBatch.getPrjName()%>"></td>
        </tr>
        <tr>
            <td >��������:</td>
            <td colspan="3" ><textarea rows="3" cols="100" name="remark" readonly
                                                  class="input_style2"><%=orderBatch.getRemark()%></textarea>
            </td>
        </tr>
    </table>
</div>

<div id="orderInfo" style='display:none'>
<table width="95%" border="1" cellpadding="2" style="table-layout:fixed" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
<tr>
    <td width="12%">������:</td>
    <td><input name="workorderNo" type="text" readonly class="input_style2" style="width:300px"
               value="<%=order.getWorkorderNo()%>">
    </td>
    <td width="12%">��������:</td>
    <td><input readonly class="input_style2" name="workorderTypeDesc" type="text" style="width:300px"
               value="<%=order.getWorkorderTypeDesc()%>">
        <input readonly class="input_style2" name="workorderType" type="hidden"
               value="<%=order.getWorkorderType()%>">
    </td>
</tr>
<tr>
    <td>�ص���:</td>
    <td><input type="text" name="workorderObjectCode" readonly class="input_style2" style="width:300px"
               value="<%=order.getWorkorderObjectCode()%>">
    </td>
    <td>����״̬:</td>
    <td><input readonly class="input_style2" name="workorderFlagDesc" type="text" style="width:300px"
               value="<%=order.getWorkorderFlagDesc()%>">
        <input readonly class="input_style2" name="workorderFlag" type="hidden"
               value="<%=order.getWorkorderFlag()%>">
    </td>
</tr>
<tr>
    <td>�ص���:</td>
    <td colspan="3"><input style="width:300px" name="workorderObjectName" type="text" readonly
                           class="input_style2" value="<%=order.getWorkorderObjectName()%>"></td>
</tr>
<%if (order.getWorkorderType().equals("14")) {//��Ǩ%>
<tr>
    <td>��Ǩ���ص���:</td>
    <td colspan="3"><input readonly class="input_style2" name="transObjectCode" type="text" style="width:300px"
                           value="<%=order.getTransObjectCode()%>"></td>
</tr>
<tr>
    <td>��Ǩ���ص���:</td>
    <td colspan="3"><input readonly class="input_style2" style="width:300px" name="transObjectName" type="text" style="width:300px"
                           value="<%=order.getTransObjectName()%>"></td>
</tr>
<%}%>
<tr>
    <td>��ʼʱ��:</td>
    <td><input readonly class="input_style2" name="startDate" style="width:300px" type="text" value="<%=order.getStartDate()%>">
    </td>
    <td>ʵʩ����(��):</td>
    <td><input readonly class="input_style2" name="implementDays" type="text" style="width:300px"
               value="<%=order.getImplementDays()%>"></td>
</tr>
<tr>
    <td>�ӵ�����:</td>
    <td><input readonly class="input_style2" type="text" style="width:300px" name="groupName" value="<%=order.getGroupName()%>">
    </td>
    <td>ִ����:</td>
    <td><input readonly class="input_style2" name="implementUser" style="width:300px" type="text"
               value="<%=order.getImplementUser()%>">
    </td>
</tr>
<tr>
    <td width="12%">����״̬:</td>
    <td><input readonly class="input_style2" name="workorderFlagDesc" type="text" style="width:300px"
               value="<%=order.getWorkorderFlagDesc()%>">
        <input readonly class="input_style2" name="workorderFlag" type="hidden"
               value="<%=order.getWorkorderFlag()%>">
    </td>
    <td>����רҵ:</td>
    <td><input readonly class="input_style2" name="attribute4" style="width:300px" type="text" value="<%=order.getAttribute4()%>">
    </td>
</tr>
<tr>
    <td> ˵ ��:</td>
    <td colspan="3"><textarea readonly class="input_style2" rows="3" cols="80"
                              name="remark"><%=order.getRemark()%></textarea>
    </td>
</tr>
<tr >
    <td >�·�����:</td>
    <td ><input readonly class="input_style2" name="distributeDate" type="text" style="width:300px"
                           value="<%=order.getDistributeDate()%>">
    </td>
    <td>�������:</td>
    <td><input readonly class="input_style2" name="uploadDate" style="width:300px" type="text" value="<%=order.getUploadDate()%>">
    </td>
</tr>
<tr>
    <td>��������:</td>
    <td><input readonly class="input_style2" style="width:300px" name="downloadDate" type="text"
               value="<%=order.getDownloadDate()%>">
    </td>
    <td>�鵵����:</td>
    <td><input readonly class="input_style2" style="width:300px" name="checkoverDate" type="text"
               value="<%=order.getCheckoverDate()%>">
    </td>
</tr>
<tr>
    <td>ɨ������:</td>
    <td><input readonly class="input_style2" style="width:300px" name="scanoverDate" type="text"
               value="<%=order.getScanoverDate()%>">
    </td>
    <td>������:</td>
    <td><input readonly class="input_style2" style="width:300px" name="responsibilityUser" type="text"
               value="<%=order.getResponsibilityUser()%>">
    </td>
</tr>
<tr>
    <td width="12%">����ԭ�����:</td>
    <td><input readonly class="input_style2" name="differenceReason" type="text" id="differenceReason" style="width:300px"
               value="<%=order.getDifferenceReason()%>"></td>
    <td>�鵵��:</td>
    <td><input readonly class="input_style2" name="checkoverUser" type="text" id="checkoverUser" style="width:300px"
               value="<%=order.getCheckoverUser()%>"></td>
</tr>
</table>
</div>
<input type="hidden" name="workorderNo" value="<%=order.getWorkorderNo()%>">
<input type="hidden" name="act" value="present">
<input type="hidden" name="systemId" value="<%=order.getSystemid()%>">
<input type="hidden" name="showAllDiv" value="<%=isCheck%>">
<input type="hidden" name="firstPendingOrder" value="<%=firstPendingOrder%>">
<div id="currDt" style="display:none">
    <table border="1" style="border-style:dotted;border:1px;border-color:white;" cellpadding="0"
           cellspacing="0" width="100%">
        <tr class="headerTR" height="22">
            <td width="8%" align="center"><font color="white">����</font></td>
            <td width="7%" align="center"><font color="white">ɨ��״̬</font></td>
            <td width="5%" align="center"><font color="white">����</font></td>
            <td width="18%" align="center"><font color="white">�豸����</font></td>
            <td width="10%" align="center"><font color="white">����ͺ�</font></td>
            <td width="10%" align="center"><font color="white">�豸רҵ</font></td>
            <!--<td width="7%" align="center"><font color="white">��Ӧ��</font></td>
            <td width="7%" align="center"><font color="white">��������</font></td>
            <td width="7%" align="center"><font color="white">������Ԫ</font></td>
            --><td width="20%" align="center"><font color="white">���β���</font></td>
            <td width="7%" align="center"><font color="white">������</font></td>
            <td width="7%" align="center"><font color="white">ʹ����</font></td>
        </tr>
        <%
            Row currDtRow = null;
            for (int i = 0; currDtlRows != null && i < currDtlRows.getSize(); i++) {
                currDtRow = currDtlRows.getRow(i);
        %>
        <tr class="dataTR">
            <td height="20"><%=currDtRow.getValue("BARCODE")%>
            </td>
            <td height="20"><%=currDtRow.getValue("ITEM_STATUS_NAME")%>
            </td>
            <td height="20"><%=currDtRow.getValue("ITEM_QTY")%>
            </td>
            <td height="20"><%=currDtRow.getValue("ITEM_NAME")%>
            </td>
            <td height="20"><%=currDtRow.getValue("ITEM_SPEC")%>
            </td>
            <td height="20"><%=currDtRow.getValue("ITEM_CATEGORY_DESC")%>
            </td>
            <!--<td height="20"><%=currDtRow.getValue("VENDOR_NAME")%>
            </td>
            <td height="20"><%=currDtRow.getValue("BOX_NO")%>
            </td>
            <td height="20"><%=currDtRow.getValue("NET_UNIT")%>
            </td>
            --><td height="20"><%=currDtRow.getValue("DEPT_NAME")%>
            </td>
            <td height="20"><%=currDtRow.getValue("USER_NAME")%>
            </td>
            <td height="20"><%=currDtRow.getValue("MAINTAIN_USER")%>
            </td>
        </tr>
        <%
            }
        %>
        <tr bgcolor="#829AD5" >
            <td height="0" colspan="7" align="center">
            </td>
        </tr>
    </table>
</div>
<%if (showScheme) {%>
<div id="fixNew" style="display:none">
    <table bgcolor="#829AD5" border="1" style="border-style:dotted;border:1px;border-color:white;" cellpadding="0"
           cellspacing="0" width="100%">
        <!--<tr>-->
        <%--<td colspan="4">�ƻ�����������<input style="background-color:'#EFEFEF';width:500px" name="desc"--%>
        <%--value="<%=scheme_desc%>"></td>--%>
        <!--</tr>-->
        <tr class="headerTR" height="22">
            <td width="20%"><font color="white">������</font></td>
            <td width="30%"><font color="white">�豸����</font></td>
            <td width="30%"><font color="white">����ͺ�</font></td>
            <td width="20%"><font color="white">����</font></td>
        </tr>
        <%

            Row fixNewRow = null;
            for (int i = 0; fixNewRows != null && i < fixNewRows.getSize(); i++) {
                fixNewRow = fixNewRows.getRow(i);
        %>
        <tr  class="dataTR" height="20">
            <td><%=fixNewRow.getValue("WORKORDER_NO")%>
            </td>
            <td><%=fixNewRow.getValue("ITEM_NAME")%>
            </td>
            <td><%=fixNewRow.getValue("ITEM_SPEC")%>
            </td>
            <td><%=fixNewRow.getValue("ITEM_QTY")%>
            </td>
        </tr>
        <%
            }
        %>
        <tr bgcolor="#829AD5" >
            <td height="0" colspan="7" align="center">
            </td>
        </tr>
    </table>
</div>
<%}%>

<%if (isCheck.equals("Y") || isHdv.equals("Y")) {%>
<div id="prevDt" style='display:none'>
    <table bgcolor="#829AD5" border="1" style="border-style:dotted;border:1px;border-color:white;" cellpadding="0"
           cellspacing="0" width="100%">
        <tr class="headerTR" height="22">
            <td width="8%" align="center"><font color="white">����</font></td>
            <td width="7%" align="center"><font color="white">ɨ��״̬</font></td>
            <td width="5%" align="center"><font color="white">����</font></td>
            <td width="18%" align="center"><font color="white">�豸����</font></td>
            <td width="10%" align="center"><font color="white">����ͺ�</font></td>
            <td width="10%" align="center"><font color="white">�豸רҵ</font></td>
            <!--<td width="7%" align="center"><font color="white">��Ӧ��</font></td>
            <td width="7%" align="center"><font color="white">��������</font></td>
            <td width="7%" align="center"><font color="white">������Ԫ</font></td>
            --><td width="20%" align="center"><font color="white">���β���</font></td>
            <td width="7%" align="center"><font color="white">������</font></td>
            <td width="7%" align="center"><font color="white">ʹ����</font></td>
        </tr>
        <%
            Row prevDtRow = null;
            for (int i = 0; prevDtlRows != null && i < prevDtlRows.getSize(); i++) {
                prevDtRow = prevDtlRows.getRow(i);
        %>
        <tr class="dataTR" height="20">
            <td><%=prevDtRow.getValue("BARCODE")%></td>
            <td><%=prevDtRow.getValue("ITEM_STATUS_NAME")%></td>
            <td><%=prevDtRow.getValue("ITEM_QTY")%></td>
            <td><%=prevDtRow.getValue("ITEM_NAME")%></td>
            <td><%=prevDtRow.getValue("ITEM_SPEC")%></td>
            <td><%=prevDtRow.getValue("ITEM_CATEGORY_DESC")%></td>
            <!--<td><%=prevDtRow.getValue("VENDOR_NAME")%></td>
            <td><%=prevDtRow.getValue("BOX_NAME")%></td>
            <td><%=prevDtRow.getValue("NET_UNIT_NAME")%></td>
            -->
            <td><%=prevDtRow.getValue("DEPT_NAME")%></td>
            <td><%=prevDtRow.getValue("USER_NAME")%></td>
            <td><%=prevDtRow.getValue("MAINTAIN_USER")%></td>
        </tr>
        <%}%>
        <tr bgcolor="#829AD5" >
            <td height="0" colspan="7" align="center">
            </td>
        </tr>
    </table>
</div>

<div id="diffDt" style='display:none'>
<%if (order.getWorkorderFlag().equals(DictConstant.WORKORDER_STATUS_ACHIEVED)) {%>
<table border="0" cellpadding="1" width="100%">
    <tr>
        <td height="22" colspan="7">
            <fieldset style="border:1px solid #226E9B;width:auto">
                <legend>�������</legend>
                <table width="100%" border="0" align="left" cellspacing="" bgcolor="#FFFFFF">
                    <tr>
                        <td><span>������:</span></td>
                        <td>
                            <input type="text" id="responsibilityUser" name="responsibilityUser" style="width:275" class="input_style2" readonly
                                   value="<%=order.getResponsibilityUser()%>">
                        </td>
                    </tr>
                    <tr>
                        <td><span>����ԭ��:</span></td>
                        <td><span>
                        <textarea readonly name="diffReason" rows="3" class="input_style2" cols="51"><%=order.getDifferenceReason()%></textarea></span></td>
                    </tr>
                </table>
            </fieldset>
        </td>
    </tr>
    <tr>
        <td height="22" colspan="9">
            <filedset style="border:1px solid #226E9B;width:auto">
                <legend>�豸��ϸ����</legend>
                <table border="1" style="border-style:dotted;border:1px;border-color:white;" cellpadding="0"
          				 cellspacing="0" width="100%">
                    <tr class="eamDbHeaderTable" bgcolor="#FEFFE8" height="22">
                        <td width="8%" align="center" rowspan="2"><font color="white">����</font></td>
                        <td width="8%" align="center" rowspan="2"><font color="white">��ʵ���</font></td>
                        <td width="12%" align="center" colspan="2"><font color="white">�豸״̬</font></td>
                        <td width="32%" align="center" colspan="4"><font color="white">ɨ������</font></td>
                        <td width="32%" align="center" colspan="4"><font color="white">ϵͳ����</font></td>
                        <td width="8%" align="center" rowspan="2"><font color="white">��ע</font></td>
                    </tr>
                    <tr class="eamDbHeaderTable" bgcolor="#FEFFE8" height="22">
                        <%--<td width="12%" align="center"><font color="white">����</font></td>--%>
                        <%--<td width="12%" align="center"><font color="white">��ʵ���</font></td>--%>
                        <td width="6%" align="center"><font color="white">ɨ��״̬</font></td>
                        <td width="6%" align="center"><font color="white">ϵͳ״̬</font></td>
                        <td width="8%" align="center"><font color="white">�豸����</font></td>
                        <td width="8%" align="center"><font color="white">����ͺ�</font></td>
                        <td width="8%" align="center"><font color="white">���β���</font></td>
                        <td width="8%" align="center"><font color="white">�� �� ��</font></td>
                        <td width="8%" align="center"><font color="white">�豸����</font></td>
                        <td width="8%" align="center"><font color="white">����ͺ�</font></td>
                        <td width="8%" align="center"><font color="white">���β���</font></td>
                        <td width="8%" align="center"><font color="white">�� �� ��</font></td>
                    </tr>
                    <%

                        Row diffDtRow = null;
                        for (int i = 0; diffDtRows != null && i < diffDtRows.getSize(); i++) {
                            diffDtRow = diffDtRows.getRow(i);
                    %>
                    <tr class="dataTR" height="20">
                        <td><%=diffDtRow.getValue("BARCODE")%></td>
                        <td><%=diffDtRow.getValue("VERIFY_RESULT")%></td>
                        <td><%=diffDtRow.getValue("SCAN_STATUS_DESC")%></td>
                        <td><%=diffDtRow.getValue("ITEM_STATUS_DESC")%></td>
                        <td><%=diffDtRow.getValue("ITEM_NAME")%></td>
                        <td><%=diffDtRow.getValue("ITEM_SPEC")%></td>
                        <td><%=diffDtRow.getValue("RESPONSIBILITY_DEPT")%></td>
                        <td><%=diffDtRow.getValue("RESPONSIBILITY_USER")%></td>
                        <td><%=diffDtRow.getValue("SYSTEM_ITEM_NAME")%></td>
                        <td><%=diffDtRow.getValue("SYSTEM_ITEM_SPEC")%></td>
                        <td><%=diffDtRow.getValue("SYSTEM_RESPONSIBILITY_DEPT")%></td>
                        <td><%=diffDtRow.getValue("SYSTEM_RESPONSIBILITY_USER")%></td>
                        <td><%=diffDtRow.getValue("REMARK")%></td>
                    </tr>
                    <%}%>
                    <tr bgcolor="#829AD5" >
                        <td height="0" colspan="13" align="center">
                        </td>
                    </tr>
                </table>
            </filedset>
        </td>
    </tr>
</table>

<%--<div id="diffDt" style="display:none">--%>
<!--<iframe height="600" name="woDtl" frameborder="0" width="100%" scrolling="auto"-->
<%--src="/WorkOrder/order/wait_c.jsp?val=<%=order.getWorkorderNo()%>">--%>
<%----%>
<%--<!--src="/servlet/SinoETS.WorkOrder.order.OrderDiffViewServlet?workorderNo=<%=order.getWorkorderNo()%>" -->--%>
<%----%>
<!--</iframe>-->

<%} else {%>
<table width="95%" border="0" cellpadding="2" cellspacing="0" bordercolor="#666666"
       bordercolordark="#FFFFFF">
    <tr>
        <td height='100' width='20%'></td>
        <td width='20%'>��������δ�鵵��
        </td>
        <td width='20%'></td>
        <td width='20%'>
        </td>
    </tr>
</table>
<%}%>
</div>


<%}%>
</td>

</tr>
</table>

</form>
</body>
</html>

<script language="javascript">
    function initPage() {
        document.all("batchInfo").style.display = "";
        document.all("orderInfo").style.display = "none";
        document.all("currDt").style.display = "none";
//            document.all("prevDt").style.display = "none";
        //            document.all("diffDt").style.display = "none";
    //<%if(showScheme){%>
    //   document.all("fixNew").style.display = "none";
    //<%}%>
    <%
      if(isCheck.equals("Y") || isHdv.equals("Y")){ %>
        document.all("prevDt").style.display = "none";
        document.all("diffDt").style.display = "none";
    <%
      }
    %>
        tabBox.inithidetab(1);
    }
    function showNum() {
        var screenHeight = window.screen.height;
        var screenWidth = window.screen.width;
        url = "/WorkOrder/order/deaDiff/NumDiff.jsp?systemid=<%=order.getSystemid()%>";
        var winstyle = "width=" + screenWidth + ",height=" + screenHeight + ",top=0,left=0,help=no,status=no,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,center=yes";
        window.open(url, "", winstyle);
        /*comment by wangwenbin 2006-5-23
        num.style.display="";
        HideSinoButton(3);
        ShowSinoButton(4);
        */
    }
</script>
