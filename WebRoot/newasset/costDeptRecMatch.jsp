<%@ page import="com.sino.ams.synchronize.dto.EamSyschronizeDTO" %>
<%@ page import="com.sino.ams.system.fixing.dto.EtsItemInfoDTO"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-8-26
  Time: 17:01:00
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>����-�ɱ����Ľ��ƥ��</title>
    <link href="/WebLibary/css/css.css" type="text/css">
</head>
<%
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    EtsItemInfoDTO dto = (EtsItemInfoDTO) request.getAttribute(WebAttrConstant.ETS_ITEM_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    Row row = null;
%>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('do_search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.servlet.AmsCostDeptRecServlet">
<jsp:include page="/message/MessageProcess"/>
    <script type="text/javascript">
        printTitleBar("����-�ɱ����Ľ��ƥ��");
    </script>
    <table width="100%" topmargin="0" border="0" bgcolor="#EFEFEF" style="width:100%">
        <tr>
            <td align="right" width="8%">���ţ�</td>
            <td align="left" width="22%"><input type="text" class="blueBorder" name="responsibilityDept" style="width:80%" value="<%=dto.getResponsibilityDept()%>"><a href="#" onClick="SelectDeptName();" class="linka">[��]</a></td>
            <td align="right" width="8%">�ɱ����ģ�</td>
            <td align="left" width="22%"><input type="text" class="blueBorder" name="prjName" style="width:80%" value ="<%=dto.getPrjName()%>"><a href="#" onClick="SelectCountyCode();" class="linka">[��]</a></td>
            <td width="40%" align="right">
                <img src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_Export();" alt="����">
                <img src="/images/eam_images/unmatch.jpg" style="cursor:'hand'" onclick="do_unmatch();" alt="���ƥ��">
                <img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ">
            </td>
        </tr>
    </table>
    <div id="headDiv" style="position:absolute;width:840px;overflow:hidden;top:45px;padding:0px; margin:0px;">
        <table width="100%" class="headerTable" border="1" cellpadding="0" cellspacing="0">
            <tr height="22" onClick="executeClick(this)" style="cursor:hand">
                <td width="1%" align="center"><input type="checkbox" name="titleCheck" onPropertyChange="checkAll('titleCheck','subCheck')"></td>
				<td width="5%" align="center">��˾</td>
                <td width="3%" align="center">�ɱ����Ĵ���</td>
                <td width="10%" align="center">�ɱ���������</td>
                <td width="3%" align="center">���Ŵ���</td>
				<td width="10%" align="center">��������</td>
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
                <td width="5%"><input type="text" class="finput" readonly value="<%=row.getValue("COMPANY")%>"></td>
                <td width="3%"><input type="text" class="finput" readonly value="<%=row.getValue("COUNTY_CODE")%>"></td>
                <td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("COUNTY_NAME")%>"></td>
				<td width="3%"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_CODE")%>"></td>
				<td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("DEPT_NAME")%>"></td>
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

function do_unmatch() {
    var checkCount = getCheckedBoxCount("subCheck")
    if (checkCount < 1) {
        alert("������ѡ��һ�����ݣ�");
        return;
    } else {
        if (document.mainFrm.flag.value == "1") {
            alert("���ڳ���ƥ�䣬��ȴ�������");
            return;
        }
        if (confirm("ȷ������ƥ����?")) {
            document.mainFrm.flag.value="1";
            document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
            mainFrm.act.value = "<%=AMSActionConstant.DELETE_ACTION%>";
            mainFrm.submit();
        }
    }
}

function SelectDeptName() {
    var url = "/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.LOOK_UP_RESPONSIBILITY_DEPT%>";
    var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    var vendorNames = window.showModalDialog(url, null, popscript);
    if (vendorNames) {
        document.forms[0].responsibilityDept.value = vendorNames[0].deptName;
    }
}

function SelectCountyCode(){
    var  url="/servlet/com.sino.ams.bean.AMSLookUpServlet?lookUpName=<%=LookUpConstant.LOOK_UP_COUNTY%>";
    var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
    var vendorNames = window.showModalDialog(url, null, popscript);
    if(vendorNames){
       document.forms[0].prjName.value = vendorNames[0].countyName;
    }
}
</script>