<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.soa.common.SrvURLDefineList" %>
<%@ page import="com.sino.soa.common.SrvWebActionConstant" %>
<%@ page import="com.sino.soa.mis.srv.pagequiryassetdeprecation.dto.SBFIFAPageQuiryAssetDeprecationDTO" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-10-14
  Time: 16:26:59
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<%
    SBFIFAPageQuiryAssetDeprecationDTO dtoOpt= (SBFIFAPageQuiryAssetDeprecationDTO) request.getAttribute(QueryConstant.QUERY_DTO);
	String pageTitle = "MISϵͳ�ʲ��۾���Ϣ(��ҳ)";
%>
 <%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('query();')">
<form action="/servlet/com.sino.soa.mis.srv.pagequiryassetdeprecation.servlet.SBFIFAPageQuiryAssetDeprecationServlet" method="post" name="mainFrm">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>
    <input type="hidden" name="act" value="">
    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td width="20%" align="right">�ʲ��˲���</td>
            <td width="25%"><select name="bookTypeCode"  class="noEmptyInput" style="width:100%" size="1"><%=dtoOpt.getOrgOption()%></select></td>
            <td width="20%" align="right">�ʲ���ǩ�ţ�</td>
            <td width="25%"><input type="text"  name="tagNumber" value="<%=dtoOpt.getTagNumber()%>" style="width:100%"></td>
            <td width="10%"></td>
       </tr>
        <tr>
            <td width="20%" align="right">���¿�ʼ���ڣ�</td>
            <td width="25%"><input  name="startLastUpdateDate" style="width:100%" title="���ѡ��ʼ����" value="<%=dtoOpt.getStartLastUpdateDate()%>" readonly class="readonlyInput" onclick="gfPop.fStartPop(startLastUpdateDate, endLastUpdateDate);"></td>
            <td width="20%" align="right">���½������ڣ�</td>
            <td width="25%"><input  name="endLastUpdateDate" style="width:100%" title="���ѡ���������" value="<%=dtoOpt.getEndLastUpdateDate()%>" readonly class="readonlyInput" onclick="gfPop.fEndPop(startLastUpdateDate, endLastUpdateDate);"></td>
        	<td width="10%" align="right"><img align="bottom" src="/images/eam_images/synchronize.jpg" alt="���ͬ��" onclick="do_SubmitSyn();"></td>
        </tr>
    </table>

</form>

</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">

	function do_SearchOrder() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	    mainFrm.act.value = "<%=SrvWebActionConstant.QUERY_ACTION%>";
	    mainFrm.submit();
	}

	function do_SubmitSyn() {
        var bookTypeCode = document.mainFrm.bookTypeCode.value;
        var tagNumber = document.mainFrm.tagNumber.value;
        var startLastUpdateDate = document.mainFrm.startLastUpdateDate.value;
        var endLastUpdateDate = document.mainFrm.endLastUpdateDate.value;
        if (bookTypeCode == "") {
            alert("�ʲ��˲�����ѡ��");
            return false;
        }
        if (tagNumber == "" && (startLastUpdateDate == "" || endLastUpdateDate == "")) {
            alert("���ʲ���ǩ��Ϊ��ʱ����ѡ����¿�ʼ���ںͽ������ڣ�");
            return false;
        }
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	    mainFrm.act.value = "<%=SrvWebActionConstant.INFORSYN%>";
	    mainFrm.submit();
	}

</script>
</html>