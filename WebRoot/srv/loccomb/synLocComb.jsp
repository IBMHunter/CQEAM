<%--
User: wangzhipeng
Date: 2011-09-15-11 
Function:�ص���Ϣͬ��(SOA)
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.soa.mis.srv.assetLocComb.dto.SrvAssetLocCombDTO" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%@ page import="com.sino.soa.common.SrvURLDefineList" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>EAM�ʲ��ص�ͬ��</title>
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
</head>
<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    SrvAssetLocCombDTO dto = (SrvAssetLocCombDTO) request.getAttribute(WebAttrConstant.SYSCHRONIZE_DTO);
    Row row = null;
%>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_search()');" onload="do_SetPageWidth();">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<jsp:include page="/public/hintMessage.jsp" flush="true">
	<jsp:param name="showMsg" value="����ͬ���У���ȴ�"/>
</jsp:include>
<form name="mainFrm" method="post" action="<%=SrvURLDefineList.SRV_ASSET_LOC_COMB_SERVLET%>">
    <script type="text/javascript">
        printTitleBar("�ص�䶯ͬ��");
        var ArrAction0 = new Array(true, "��ѯ", "action_view.gif", "��ѯ", "do_search");
        var ArrAction1 = new Array(true, "����", "toexcel.gif", "����", "do_Export");
        var ArrAction2 = new Array(true, "ͬ��", "cancel.gif", "ͬ��", "do_syschronize");
        var ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2);
        printToolBar();
    </script>
    <input type="hidden" name="act">
    <input type="hidden" name="orgIds" value="">
    <input type = "hidden" name = "isFirst" value = "0">
    <table width="100%" border="0" bgcolor="#EFEFEF" style="width:100%">
        <tr>
            <td align="right" width="10%">��˾��</td>
            <td align="left" width="23%"><select name="organizationId" style="width:100%"><%=dto.getOrganizationOpt()%></select> </td>
            <td align="right" width="10%">�ص���룺</td>
            <td align="left" width="23%"><input type="text" name="workorderObjectCode"
                                                value="<%=dto.getWorkorderObjectCode()%>" style="width:80%"></td>
            <td align="right" width="10%">�ص�������</td>
            <td align="left" width="23%"><input type="text" name="newAssetsLocation"
                                                value="<%=dto.getNewAssetsLocation()%>" style="width:80%"></td>
        </tr>
        <tr>
            <td align="right" width="10%">�����ˣ�</td>
            <td align="left" width="23%"><input type="text" name="createUser" value="<%=dto.getCreateUser()%>" style="width:100%"></td>
            <td align="right" width="10%">�������ڣ�</td>
            <td align="left" width="23%"><input  type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:80%;cursor:hand" title="���ѡ������" readonly onclick="gfPop.fStartPop(startDate, endDate)"></td>
            <td align="right" width="10%">����</td>
            <td align="left" width="23%"><input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:80%;cursor:hand" title="���ѡ������" readonly onclick="gfPop.fEndPop(startDate, endDate)"></td>
        </tr>
    </table> 
    <div id="headDiv" style="position:absolute;width:100%;overflow:hidden;top:98px;padding:0px; margin:0px;">
        <table width="100%" class="headerTable" border="1" cellpadding="0" cellspacing="0">
            <tr height="22">
                <td width="3%" align="center" style="padding:0"><input type="checkbox" name="titleCheck" class="headCheckbox" onclick="checkAll('titleCheck','systemids')"></td>
                <td width="10%" align="center">EAM�ص����</td>
                <td width="20%" align="center">EAM�ص�����</td>
                <td width="10%" align="center">ԭMIS�ص����</td>
                <td width="20%" align="center">ԭMIS�ص�����</td>
                <td width="7%" align="center">������ʱ��</td>
                <td width="7%" align="center">��������</td>
                <td width="7%" align="center">�ص����</td>
                <td width="7%" align="center">��������</td>
            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;height:72%;width:100%;position:absolute;top:90px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
            <%
                if (rows != null && rows.getSize() > 0) {
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>
            <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'">
                <td width="3%" align="center" style="padding:0"><input type="checkbox" name="systemids" value="<%=row.getValue("WORKORDER_OBJECT_NO")%>"></td>
                <td width="10%"><input class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
                <td width="20%"><input class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_LOCATION")%>"></td>
                <td width="10%"><input class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION_CODE")%>"></td>
                <td width="20%"><input class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION")%>"></td>
                <td width="7%"><input class="finput2" readonly value="<%=row.getValue("LAST_UPDATE_DATE")%>"></td>
                <td width="7%"><input class="finput2" readonly value="<%=row.getValue("USERNAME")%>"></td>
                <td width="7%"><input class="finput2" readonly value="<%=row.getValue("WORKORDER_CATEGORY")%>"></td>
                <td width="7%"><input class="finput2" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
            </tr>
            <%
                    }
                }
            %>

        </table>
    </div>
</form>
<%
    if (rows != null && !rows.isEmpty()) {
%>
<div id="pageNaviDiv" style="position:absolute;top:90%;left:0"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
<%
    }
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm"
        scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
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
    /**
     * ���ܣ�ͬ������
     */
    function do_syschronize() {
    	var isFirst = document.mainFrm.isFirst.value;
	    if (isFirst == "1") {
	        alert("����ͬ�����ݣ�����ִ�д˲�����");
	        return false;
	    }
        var count=getCheckBoxCount("systemids");
        if (count==0) {
            alert("����ѡ�������ڽ���ͬ����");
            return;
        }
	    document.mainFrm.isFirst.value = "1";
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainFrm.action = "/servlet/com.sino.soa.mis.srv.assetLocComb.servlet.SrvAssetLocCombServlet";
        document.mainFrm.act.value = "<%=WebActionConstant.SYSCHRONIZE_ACTION%>";
        document.mainFrm.submit();
       
    }
</script>