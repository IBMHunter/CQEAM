<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.soa.common.SrvURLDefineList" %>
<%@ page import="com.sino.soa.common.SrvWebActionConstant" %>
<%@ page import="com.sino.soa.td.srv.pagequiryaccountbalance.dto.SBFIGLTdPageQuiryAccountBalanceDTO" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-10-15
  Time: 19:20:02
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<%
    SBFIGLTdPageQuiryAccountBalanceDTO dtoOpt= (SBFIGLTdPageQuiryAccountBalanceDTO) request.getAttribute(QueryConstant.QUERY_DTO);
	String pageTitle = "TDϵͳ��Ŀ���(��ҳ)";
%>
 <%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('query();')">
<form action="/servlet/com.sino.soa.td.srv.pagequiryaccountbalance.servlet.SBFIGLTdPageQuiryAccountBalanceServlet" method="post" name="mainFrm">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>
    <input type="hidden" name="act" value="">
    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td width="20%" align="right">���ף�</td>
            <td width="25%"><input type="text" name="setOfBooks" value="<%=dtoOpt.getSetOfBooks()%>" class="noEmptyInput" style="width:100%"></td>
            <td width="20%" align="right">������ͣ�</td>
            <td width="25%"><input type="text" name="actualFlag" value="<%=dtoOpt.getActualFlag()%>" class="noEmptyInput" style="width:100%"></td>
            <td width="10%"></td>
       </tr>
        <tr>
            <td width="20%" align="right">��ʼ�ڼ䣺</td>
            <td width="25%"><input type="text" name="periodNameFrom" value="<%=dtoOpt.getPeriodNameFrom()%>" class="noEmptyInput" style="width:100%"></td>
            <td width="20%" align="right">�����ڼ䣺</td>
            <td width="25%"><input type="text" name="periodNameTo" value="<%=dtoOpt.getPeriodNameTo()%>" class="noEmptyInput" style="width:100%"></td>
        	<td width="10%" align="right"><img align="bottom" src="/images/eam_images/synchronize.jpg" alt="���ͬ��" onclick="do_SubmitSyn();"></td>
        </tr>
    </table>

</form>

</body>

<script type="text/javascript">

	function do_SearchOrder() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	    mainFrm.act.value = "<%=SrvWebActionConstant.QUERY_ACTION%>";
	    mainFrm.submit();
	}

	function do_SubmitSyn() {
        var setOfBooks = document.mainFrm.setOfBooks.value;
        var actualFlag = document.mainFrm.actualFlag.value;
        var periodNameFrom = document.mainFrm.periodNameFrom.value;
        var periodNameTo = document.mainFrm.periodNameTo.value;
        if (setOfBooks == "") {
            alert("���ױ������룡");
            return false;
        }
        if (actualFlag == "") {
            alert("������ͱ������룡");
            return false;
        }
        if (periodNameFrom == "") {
            alert("��ʼ�ڼ�������룡");
            return false;
        }
        if (periodNameTo == "") {
            alert("�����ڼ�������룡");
            return false;
        }
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	    mainFrm.act.value = "<%=SrvWebActionConstant.INFORSYN%>";
	    mainFrm.submit();
	}

</script>
</html>