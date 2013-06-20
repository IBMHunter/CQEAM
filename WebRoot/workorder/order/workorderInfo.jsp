<%--
  User: zhoujs
  Date: 2007-11-8
  Time: 11:34:28
  Function: ������Ϣ
--%>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK"%>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.ams.workorder.dto.EtsWorkorderDTO"%>
<%@ page import="com.sino.framework.security.bean.SessionUtil"%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<%
    EtsWorkorderDTO order = (EtsWorkorderDTO) request.getAttribute("order");
    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);

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


<form name="mainFrm"  method="post" action="/servlet/SinoETS.WorkOrder.order.OrderDiffServlet">
<input type="hidden" name="systemid" value="<%=order.getSystemid()%>">
<table align="center" width='98%' border="0"  cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
    <tr><td>
       <table id="p1" width="95%"   border="1"  cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
          <tr >
              <td width="12%" scope="col">��������:</td>
              <td scope="col"><input name="workorder_batch_no" type="text" readonly class="readonlyInput"  value="<%=order.getWorkorderBatch()%>"></td>
              <td >��������:</td>
              <td><input readonly class="readonlyInput" name="workorder_batch_name" type="text" value="<%=order.getWorkorderBatchName()%>"></td>
          </tr>
          <tr>
            <td width="12%" scope="col">�������̣�</td>
            <td colspan="3" scope="col"><input readonly class="readonlyInput" size="55" type="text" name="batchPrj" value="<%=order.getPrjId()%>"></td>
          </tr>
          <tr>
            <td width="12%" scope="col" >˵��:</td>
            <td colspan="3" scope="col" ><textarea rows="3" cols="97" name="workorder_batch_remark"  readonly="true"> <%=order.getRemark()%></textarea> </td>
          </tr>
        </table>
       <table id="p2" width="95%" border="1" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">
            <tr class="hei">
                <td width="12%">������:</td>
                <td><input readonly class="readonlyInput" name="workorderNo" type="text"  value="<%=order.getWorkorderNo()%>">
                </td>
                <td>��������:</td>
                <td><input readonly class="readonlyInput" name="workorderTypeDesc" type="text"  value="<%=order.getWorkorderTypeDesc()%>">
                    <input readonly class="readonlyInput" name="workorderType" type="hidden"  value="<%=order.getWorkorderType()%>">
                </td>
            </tr>
            <tr>
                <td width="12%">�ص���:</td>
                <td><input readonly class="readonlyInput" type="text" name="workorderObjectNo"  value="<%=order.getWorkorderObjectNo()%>">
                </td>
                <td>����״̬:</td>
                <td><input readonly class="readonlyInput" name="workorderFlagDes" type="text"  value="<%=order.getWorkorderFlag()%>">
                    <input readonly class="readonlyInput" name="workorderFlag" type="hidden" value="<%=order.getWorkorderFlagDesc()%>">
                </td>
            </tr>
            <tr>
                <td width="12%">�ص���:</td>
              <td colspan="3"><input readonly class="readonlyInput" style="width:300px" name="workorderObjectName" type="text"   value="<%=order.getWorkorderObjectName()%>"></td>
            </tr>
           <%if(order.getWorkorderType().equals("14")){//��Ǩ%>
            <tr>
              <td width="12%">��Ǩ���ص���:</td>
              <td colspan="3"><input readonly class="readonlyInput" name="transCode" type="text" value="<%=order.getTransObjectCode()%>"></td>
            </tr>
           <tr>
                <td width="12%">��Ǩ���ص���:</td>
                <td colspan="3"><input readonly class="readonlyInput" style="width:300px" name="transLoc" type="text" value="<%=order.getTransObjectName()%>"></td>
            </tr>
           <%}%>
            <tr>
                <td width="12%">��ʼʱ��:</td>
              <td><input readonly class="readonlyInput" name="startDate" type="text" value="<%=order.getStartDate()%>"></td>
                <td>ʵʩ����(��):</td>
                <td><input readonly class="readonlyInput" name="deadlineDate" type="text" value="<%=order.getImplementDays()%>"></td>
            </tr>
            <tr>
                <td width="12%">�ӵ�����:</td>
                <td><input readonly class="readonlyInput" type="text" name="group"  value="<%=order.getGroupName()%>"></td>
                <td>ִ����:</td>
                <td><input readonly class="readonlyInput" name="implementBy" type="text"  value="<%=order.getImplementUser()%>">
                </td>
            </tr>
            <tr>
                <td width="12%">����״̬:</td>
                <td><input readonly class="readonlyInput" name="workorderFlagDes" type="text"  value="<%=order.getWorkorderFlagDesc()%>">
                    <input readonly class="readonlyInput" name="workorderFlag" type="hidden" value="<%=order.getWorkorderFlag()%>">
                </td>
                <td>����רҵ:</td>
                <td><input readonly class="readonlyInput" name="category" type="text"  value="<%=order.getAttribute4()%>">
                </td>
            </tr>
            <tr>
                <td width="12%"> ˵ ��:</td>
                <td colspan="3"><textarea rows="3" cols="97" name="remark" ><%=order.getRemark()%></textarea>
                </td>
            </tr>
        </table>


  <table id="p3" width="95%" border="1" cellpadding="2" cellspacing="0" bordercolor="#666666" bordercolordark="#FFFFFF">

      <tr class="hei">
          <td  width="12%" scope="col">�·�����:</td>
          <td scope="col"><input readonly class="readonlyInput" name="distribudate" type="text"  value="<%=order.getDistributeDate()%>">
          </td>
          <td>�������:</td>
          <td><input readonly class="readonlyInput" name="uploadDate" type="text"  value="<%=order.getUploadDate()%>">
          </td>
      </tr>
      <tr>
          <td width="12%">��������:</td>
          <td><input readonly class="readonlyInput" name="downloadDate" type="text"  value="<%=order.getDownloadDate()%>">
          </td>
          <td>�鵵����:</td>
          <td><input readonly class="readonlyInput" name="checkoverDate" type="text"  value="<%=order.getCheckoverDate()%>">
          </td>
      </tr>
      <tr>
          <td width="12%">ɨ������:</td>
          <td><input readonly class="readonlyInput" name="scanoverDate" type="text"  value="<%=order.getScanoverDate()%>">
          </td>
          <td>������:</td>
          <td><input readonly class="readonlyInput" name="responsibilityUser" type="text" value="<%=order.getResponsibilityUser()%>">
          </td>
      </tr>
      <tr>
          <td width="12%">����ԭ�����:</td>
          <td><input readonly class="readonlyInput" name="differenceReason" type="text" id="differenceReason" value="<%=order.getDifferenceReason()%>"></td>
          <td>�鵵��:</td>
          <td><input readonly class="readonlyInput" name="checkoverUser" type="text" id="checkoverUser"
               value="<%=order.getCheckoverUser()%>"></td>
      </tr>
  </table>
      </td>
    </tr>

  </table>