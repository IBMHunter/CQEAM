<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.print.dto.AmsBarcodePrintDTO" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-1-14
  Time: 10:21:26
  To change this template use File | Settings | File Templates.
--%>
<html>
<head><title>�����ӡ����</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/flow/flow.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
    <script type="text/javascript">
        var ArrAction0 = new Array(true, "ȷ��", "action_sign.gif", "ȷ��", "do_Complete");
        var ArrAction1 = new Array(false, "�˻�", "arrow_pleft.gif", "�˻�", "do_Back");
        var ArrAction2 = new Array(true, "���̲���", "actn023.gif", "���̲���", "viewFlow") ;
        var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2);
        var ArrSinoViews = new Array();
        var ArrSinoTitles = new Array();
    </script>
</head>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    AmsBarcodePrintDTO printdto = (AmsBarcodePrintDTO) request.getAttribute(WebAttrConstant.BARCODE_PRINT_DTO);
//    String first = request.getParameter("first");
    String first = printdto.getFirst();
//    SfUserDTO sfUser = (SfUserDTO) session.getAttribute(WebConstant.USER_INFO);
    String action = parser.getParameter("act");
    SfUserDTO sfUser = (SfUserDTO) SessionUtil.getUserAccount(request);
    System.out.println("sfUser.getCurrRoleIds()=" + sfUser.getCurrRoleIds());
    String sectionRight = parser.getParameter("sectionRight");
//    List
%>
<script type="text/javascript">
    printTitleBar("�����ӡ����")
    printToolBar();
</script>
<body topMargin=0 leftMargin=0  <%--onload="initPage();"--%>>
<jsp:include page="/message/MessageProcess"/>
<form action="/servlet/com.sino.ams.print.servlet.AmsBarcodePrintServlet" name="mainForm" method="post">
<table border="0" width="100%">
<tr>
    <td align="center" colspan="4" width="100%" id="barcode11" style="color:red;visibility:hidden">�Բ���</td>
</tr>
<tr height="24px">
    <td></td>
    <td></td>
    <td></td>
    <td></td>
</tr>
<tr>
    <td align="right" width="20%">��ǩ��</td>
    <td width="30%" align="left">
        <select name="tagType"
                <%
                    if (StrUtil.isEmpty(first)) {
                %>
                class="noEmptyInput"
                <%
                } else {
                %>
                readonly class="readonlyInput"
                <%
                    }
                %>
                style="width:90%">
            <option value="1" <%=printdto.getTagType().equals("1") ? "selected" : ""%>>��(60mmX38mm)</option>
            <option value="2" <%=printdto.getTagType().equals("0") ? "selected" : ""%>>С(60mmX10mm)</option>
        </select>
    </td>
    <td align="right" width="10%">������</td>
    <td width="40%" align="left"><input type="text"

    <%
        if (StrUtil.isEmpty(first)){
    %>
                                        class="noEmptyInput"
    <%
          }else{
    %>
                                        readonly class="readonlyInput"
    <%
      }
    %>
                                        name="tagNumber" style="width:80%"
                                        value="<%=printdto.getTagNumber()%>" onblur="do_verify();">
    </td>
</tr>
<tr>
<td align="right">��ǩ��ɫ��</td>
    <td  align="left" colspan="3" ><select
     <%
     if (StrUtil.isEmpty(first)) {
    %>
            class="noEmptyInput"
            <%
            } else {
            %>
            readonly class="readonlyInput"
            <%
                }
            %>
             name="tagColor" style="width:90%">
            <option value="����" <%=printdto.getTagColor().equals("����") ? "selected" : ""%>>����(�ʲ���ǩ)</option>
            <option value="��" <%=printdto.getTagColor().equals("��") ? "selected" : ""%>>��(�����ʲ�)</option>
            <option value="��" <%=printdto.getTagColor().equals("��") ? "selected" : ""%>>��(��ֵ�׺�)</option>
            <option value="��" <%=printdto.getTagColor().equals("��") ? "selected" : ""%>>��(�����Ǳ�)</option>
    </select></td>
</tr>
<tr>
    <td align="right">����ԭ��</td>
    <td  align="left" colspan="3" ><textarea
     <%
     if (StrUtil.isEmpty(first)) {
    %>
            class="noEmptyInput"   title="�ڴ�������Ҫ�����ǩ�Ľ�������"
            <%
            } else {
            %>
            readonly class="readonlyInput"
            <%
                }
            %>
            rows="2" cols="3" name="applyReason" style="width:90%"><%=printdto.getApplyReason()%></textarea></td>
</tr>
<tr>
    <td align="right" width="20%">�����ˣ�</td>
    <td width="30%" align="left"><input name="applyName" type="text" readonly class="readonlyInput"
 <%
      if(StrUtil.isEmpty(first)){
  %>
            value="<%=sfUser.getUsername()%>"
   <%
    }else{
   %>
            value="<%=printdto.getApplyName()%>"
    <%
        }
    %>
                                        style="width:90%"></td>
    <td align="right" width="10%">����ʱ�䣺</td>
    <td width="40%" align="left"><input type="text" name="applyDate" readonly class="readonlyInput" style="width:80%"
                                        value="<%=printdto.getApplyDate()%>">
    </td>
</tr>
<%--<tr>--%>
    <%--<td align="right" >����ʱ�䣺</td>--%>
    <%--<td  align="left"><input type="text" name="applyDate" readonly class="readonlyInput" style="width:90%"  value="<%=printdto.getApplyDate()%>"></td>--%>
    <%--<td align="right"  >�������ʱ��:</td>--%>
    <%--<td  align="left"><input type="text" name="endDate" readonly class="readonlyInput" style="width:80%"  value="<%=printdto.getEndDate()%>"><img src="/images/calendar.gif" alt="���ѡ������"  onclick="gfPop.fEndPop(applyDate,endDate);"></td>--%>
<%--</tr>--%>
<tr>
    <td align="right" width="20%">�����ˣ�</td>
    <td width="30%" align="left"><input type="text" name="approveName" readonly class="readonlyInput" style="width:90%"
  <%
      if(sectionRight.equals("1")){
  %>
            value="<%=sfUser.getUsername()%>"
  <%
     } else {
  %>
         value="<%=printdto.getApproveName()%>"
  <%
     }
  %>
            >
    </td>
    <td align="right" width="10%">����ʱ�䣺</td>
    <td width="40%" align="left"><input type="text" name="approveDate" readonly class="readonlyInput" style="width:80%"
                                        value="<%=printdto.getApproveDate()%>">
    </td>
</tr>
<tr>
    <td align="right" width="20%">��ӡ�ˣ�</td>
    <td width="30%" align="left"><input type="text" name="printName" readonly class="readonlyInput" style="width:90%"
   <%
      if(sectionRight.equals("2")){
  %>
            value="<%=sfUser.getUsername()%>"
  <%
     } else {
  %>                               
            value="<%=printdto.getPrintName()%>"
  <%
     }
  %>
            >
    </td>
    <td align="right" width="10%">��ӡʱ�䣺</td>
    <td width="40%" align="left"><input type="text" name="printDate" readonly class="readonlyInput" style="width:80%"
                                        value="<%=printdto.getPrintDate()%>">
    </td>
</tr>
<tr>
    <td align="right">�� ע��</td>
    <td colspan="3" align="left"><textarea cols="97" name="remark" style="width:90%" rows="4"><%=printdto.getRemark()%></textarea></td>
</tr>

<tr>
    <td align="right">���������</td>
    <td colspan="3" align="left"><textarea cols="97" name="approveResult"
            <%
                if (sectionRight.equals("1")) {
            %>
                                          
            <%
            } else {
            %>
                                           readonly class="readonlyInput"
            <%
                }
            %>
                                           style="width:90%" rows="6"><%=printdto.getApproveResult()%></textarea></td>
</tr>
</table>
<table width="100%" border="0">
    <tr>
        <td align="center" width="10%"></td>
        <td width="40%"> &nbsp;&nbsp;&nbsp;
            <%--<img src="/images/eam_images/save.jpg" alt="�����豸��Ϣ" onClick="do_save(); return false;"> &nbsp;--%>
            <%--<img src="/images/eam_images/back.jpg" alt="����" onclick="do_Back();return false;"></td>--%>
    </tr>
</table>
<input type="hidden" name="act" value="<%=action%>">
<input type="hidden" name="flowSaveType">
<input type="hidden" name="id" value="<%=printdto.getId()%>">

<input type="hidden" name="applyGroup"  value="<%=printdto.getApplyGroup()%>">

<%--<input type="text" name="groupId" value="<%=sfUser.getCurrGroupId()%>" >--%>

<input type="hidden" name="status" value="<%=printdto.getStatus()%>">
<input type="hidden" name="batchNo" value="<%=printdto.getBatchNo()%>">
<input type="hidden" name="first" value="<%=printdto.getFirst()%>">
 <%
    if(StrUtil.isEmpty(first)){
 %>
<input type="hidden" name="applyUser" value="<%=sfUser.getUserId()%>">
 <%
 }
 %>
<input type="hidden" name="approveUser" value="<%=printdto.getApproveUser()%>">
<input type="hidden" name="printUser" value="<%=printdto.getPrintUser()%>">

<input type="hidden" name="procName" value="�����ӡ����">
<jsp:include page="/flow/include.jsp" flush="true"/>
</form>
</body>
<iframe name="downTarget" src="" style="display:none"></iframe>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script type="text/javascript">
    function do_Complete() {
        if(document.mainForm.applyGroup.value!==""){
        var fieldNames = "tagType;tagNumber;tagColor;applyReason";
        var fieldLabels = "��ǩ����;��ӡ����;��ǩ��ɫ;����ԭ��";
        if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)) {
            //step1:����Ӧ��ҵ��
            //step2: �������̷���
            /*����˵����
                orgId:��ǰ�û���OU����Ҫ�������½�һ�����̼�¼ʱ���ҵ������̵Ŀ�ʼ�ڵ㡣
                userId:����ǰ�û���userId;
                deptId������½ڵ��Ƕಿ�ŵģ��ô��ͱ��봫ֵ����һ���ڵ�ȡ�˲��ŵİ����ˡ�
                procdureName:�������ƣ��¿�ʼһ������ʱ������orgId��������ȥ������ID��
                flowCode:���̷��������롣
                'do_ApproveApply()'���ύҳ��ķ�������assignȡ����Ҫ�Ĳ���ʱ�����ô˷����ύҳ�档
            */
            var paramObj = new Object();
            paramObj.orgId = "<%=sfUser.getOrganizationId()%>";
            paramObj.useId = "<%=sfUser.getUserId()%>";
            paramObj.groupId = document.mainForm.applyGroup.value;
            paramObj.procdureName = "�����ӡ����";
            paramObj.flowCode = "";
            paramObj.submitH = "submitH()";
            assign(paramObj);
               }
        }else{
            alert("����ѡ����飡");
        }
    }

    function submitH() {
        var first = document.mainForm.first.value
        if (first == "") {
            //            alert("1");
            document.mainForm.act.value = "<%=WebActionConstant.SUBMIT_ACTION%>";
            document.mainForm.submit();
        } else {
            document.mainForm.act.value = "<%=WebActionConstant.APPROVE_ACTION%>";
            document.mainForm.submit();
            //           parent.window.document.forms[0].act=
            var parent = window.opener.document.mainForm
            parent.submit();
            //            window.close();
        }
    }

    function do_Back() {
        with (mainForm) {
            act.value = "";
            action = "/servlet/com.sino.ams.print.servlet.AmsBarcodePrintServlet";
            mainForm.target = "_self";
            submit();
        }
    }

    function do_SelectSystemItem() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_HOUSE%>";
        var dialogWidth = 35;
        var dialogHeight = 30;
        //LOOKUP������ �����DTO��һ��
        var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                user = users[i];
                dto2Frm(user, "mainForm");
            }
        }
    }

    function do_verify() {
        var fieldNames = "tagNumber";
        var fieldLabels = "��ӡ����";
        if (!formValidate(fieldNames, fieldLabels, POSITIVE_INT_VALIDATE)) {
            //        alert("�����������Ϊ�����֣�");
        }
    }


    function initPage() {
        var first = "<%=first%>";
        if (first == "") {
            var url = "/servlet/com.sino.ams.print.servlet.AmsBarcodePrintServlet?act=CHECK_ACTION";
            var winstyle = "dialogWidth:20.1;dialogHeight:14.8;center:yes;status:no;help:no";
            var retVal = window.showModalDialog(url, null, winstyle);
                           alert(retVal);
            if (retVal) {

                document.mainForm.applyGroup.value = retVal;
            <%--mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";--%>
            }else{
                alert("��ѡ����飡");
            }
        }
    }
    //function do_verify(obj) {
    //    var barcode = obj.value ;
    ////    alert(barcode);
    //    if (barcode.indexOf("-") != -1) {
    //    } else {
    //        alert("���������-��������");
    //        obj.focus();
    //        return false;
    //    }
    //}


</script>