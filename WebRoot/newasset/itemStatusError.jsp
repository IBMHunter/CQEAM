<%@ page import="com.sino.ams.synchronize.dto.EamSyschronizeDTO" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-8-20
  Time: 13:40:34
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>MIS�����ʲ���EAM�豸״̬��һ�����</title>
    <link href="/WebLibary/css/css.css" type="text/css">
</head>
<%
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    EamSyschronizeDTO dto = (EamSyschronizeDTO) request.getAttribute(WebAttrConstant.SYSCHRONIZE_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    Row row = null;
%>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('do_search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.servlet.ItemStatusErrorServlet">
<jsp:include page="/message/MessageProcess"/>
    <script type="text/javascript">
        printTitleBar("MIS�����ʲ���EAM�豸״̬��һ�����");
    </script>
    <table width="100%" topmargin="0" border="0" bgcolor="#EFEFEF" style="width:100%">
        <tr>
            <td align="right" width="8%">�豸���룺</td>
            <td align="left" width="12%"><input type="text" name="barCode" value="<%=dto.getBarCode()%>" style="width:100%"></td>
            <td width="80%" align="right">
                <img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="����">
                <img src="/images/eam_images/ok.jpg" style="cursor:'hand'" onclick="do_disabled();" alt="����">
                <img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ">
            </td>
        </tr>
    </table>
    <div id="headDiv" style="position:absolute;width:840px;overflow:hidden;top:45px;padding:0px; margin:0px;">
        <table width="100%" class="headerTable" border="1" cellpadding="0" cellspacing="0">
            <tr height="22" onClick="executeClick(this)" style="cursor:hand">
                <td width="1%" align="center"><input type="checkbox" name="titleCheck" onPropertyChange="checkAll('titleCheck','subCheck')"></td>
				<td width="4%" align="center">��˾</td>
                <td width="4%" align="center">�豸����</td>
                <td width="5%" align="center">�豸����</td>
                <td width="5%" align="center">����ͺ�</td>
				<td width="6%" align="center">�ص�</td>
                <td width="6%" align="center">���β���</td>
                <td width="3%" align="center">������</td>
                <td width="2%" align="center">�豸״̬</td>
            </tr>
        </table>
    </div>

    <div id="dataDiv" style="overflow:scroll;height:368px;width:857px;position:absolute;top:68px;left:1px" align="left"
         onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
	if (rows != null && rows.getSize() > 0) {
		for (int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
%>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
                <td width="1%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>

                <td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("COMPANY")%>"></td>
                <td width="4%"><input type="text" class="finput" readonly value="<%=row.getValue("BARCODE")%>"></td>
                <td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>
				<td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>
				<td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
				<td width="6%"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
                <td width="3%"><input type="text" class="finput" readonly value="<%=row.getValue("USER_NAME")%>"></td>
                <td width="2%"><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_STATUS_NAME")%>"></td>
            </tr>
<%
		}
	}
%>
        </table>
    </div>
	<input type="hidden" name="act">
	<input type="hidden" name="flag" value="0">
</form>
<div style="position:absolute;top:440px;left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
</html>

<script type="text/javascript">

function do_search() {
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	document.mainFrm.submit();
}

function do_Export() {
	document.mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
	document.mainFrm.submit();
}

function do_disabled() {
	if(!document.mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("����ִ�в�ѯ�ٽ��б��ϲ���");
		return;
	}
	if(document.mainFrm.$$$CHECK_BOX_HIDDEN$$$.value == ""){
		alert("����ѡ�������ٽ��б��ϲ���");
		return;
	}
    if(confirm("ȷ��Ҫ����ѡ�е��豸�𣿱��Ϻ���豸��������ִ���κβ���������������ȷ������ť������������ȡ������ť��")){
        document.mainFrm.flag.value="1";
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainFrm.action = "/servlet/com.sino.ams.newasset.servlet.ItemStatusErrorServlet";
        document.mainFrm.act.value = "<%=WebActionConstant.DISABLED_ACTION%>";
        document.mainFrm.submit();
    }
}
</script>