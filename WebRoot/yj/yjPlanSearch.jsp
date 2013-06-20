<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.ams.yj.dto.AmsYjUserDTO" %>
<%@ page import="com.sino.ams.yj.constant.YJWebAttribute" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%--
  Created by IntelliJ IDEA.
  User: jialongchuan
  Date: 2010-7-6
  Time: 20:22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>Ӧ��Ԥ����ϵά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>

</head>

<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    Row row = null;
    String planName = parser.getParameter("planName");
    String planNo = parser.getParameter("planNo");
    String orgOption = (String) request.getAttribute(WebAttrConstant.OU_OPTION);
    String startDate = parser.getParameter("startDate");
    String endDate = parser.getParameter("endDate");
%>
<body onkeydown="autoExeFunction('do_search()')" onload="initPage();">
<%=WebConstant.WAIT_TIP_MSG%>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.yj.servlet.AmsYjPlanServlet">
<script type="text/javascript">
    printTitleBar("Ӧ��Ԥ����ϵά��")
</script>
<table width="100%" border="0" class="queryHeadBg">
    <input type="hidden" name="act">
    <tr>
        <td width="6%" align="right">��˾���ƣ�</td>
        <td width="11%"><select class="select_style1" style="width:100%" name="organizationId"><%=orgOption%>
        </select></td>
        <td width="6%" align="right">Ԥ�����ƣ�</td>
        <td width="10%"><input class="input_style1" style="width:100%" type="text" name="planName" value="<%=planName%>"></td>
        <td width="6%" align="right">Ԥ����ţ�</td>
        <td width="10%"><input class="input_style1" style="width:100%" type="text" name="planNo" value="<%=planNo%>"></td>
        <td width="15%" align="center"><img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ"><img
                src="/images/eam_images/new.jpg" alt="����" onClick="do_add();"><img src="/images/eam_images/export.jpg" style="cursor:'hand'"
                                                                               onclick="do_Export();" title="������Excel"><img
                src="/images/eam_images/export.jpg" style="cursor:'hand'" onclick="do_export_plan();" title="����Ԥ��ͳ��">
        </td>
    </tr>
    <tr>
        <td width="6%" align="right">Ԥ�����</td>
        <td width="9%">
            <select name="planType" id="planType" class="select_style1" style="width:100%">
                <option value="">--��ѡ��--</option>
                <%--<option value="��Ȼ�ֺ��ࣨA��" <%if (parser.getParameter("planType").equals("��Ȼ�ֺ��ࣨA��")) {%> selected <%}%>>��Ȼ�ֺ��ࣨA��</option>--%>
                <option value="��Ȼ�ֺ��ࣨA��" <%=parser.getParameter("planType").equals("��Ȼ�ֺ��ࣨA��") ? "selected" : ""%>>��Ȼ�ֺ��ࣨA��</option>
                <option value="�¹������ࣨB��" <%=parser.getParameter("planType").equals("�¹������ࣨB��") ? "selected" : ""%>>�¹������ࣨB��</option>
                <option value="���������¼���C��" <%=parser.getParameter("planType").equals("���������¼���C��") ? "selected" : ""%>>���������¼���C��</option>
                <option value="��ᰲȫ�¼���D��" <%=parser.getParameter("planType").equals("��ᰲȫ�¼���D��") ? "selected" : ""%>>��ᰲȫ�¼���D��</option>
                <option value="�ش��ࣨE��" <%=parser.getParameter("planType").equals("�ش��ࣨE��") ? "selected" : ""%>>�ش��ࣨE��</option>
            </select>
        </td>
        <td width="6%"  align="right">ӡ��ʱ�䣺</td>
            <td width="11%">
                <input type="text" name="startDate" value="<%=startDate%>" class="input_style1" style="width:100%" title="���ѡ��ʼ����" readonly 
                       onclick="gfPop.fStartPop(startDate, endDate)">
            </td>
            <td width="6%" align="right">����</td>
            <td width="10%">
                <input type="text" name="endDate" value="<%=endDate%>" style="width:100%" title="���ѡ���ֹ����" readonly class="input_style1"
                       onclick="gfPop.fEndPop(startDate, endDate)">
            </td>
    </tr>
</table>

<div id="headDiv" style="overflow:hidden;position:absolute;top:70px;left:1px;width:100%">
    <table border="1" style="color: #FFFFFF" bgcolor="#2983CB" width="350%">
        <tr height="20">
            <td width="3%" align="center">��˾����</td>
            <td width="2%" align="center">���</td>
            <td width="10%" align="center">Ԥ������</td>
            <td width="3%" align="center">Ԥ������</td>
            <td width="3%" align="center">ʡ�����</td>
            <td width="10%" align="center">Ԥ�����</td>
            <td width="3%" align="center">Ԥ�����</td>
            <td width="3%" align="center">ӡ��ʱ��</td>
            <td width="10%" align="center">֪����Χ(ְλ/��λ)</td>
            <td width="8%" align="center">֪���˵�����</td>
            <td width="6%" align="center">Ԥ�����������˵ĸ�λ/ְλ</td>
            <td width="4%" align="center">��Ԥ���Ƿ�������</td>
            <td width="10%" align="center">��ע</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:70%;width:100%;position:absolute;top:92px;left:1px" align="left"
     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="350%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
        <%
            if (rows != null && rows.getSize() > 0) {
                String planId = "";
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
        %>
        <tr style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'"
            onMouseOut="style.backgroundColor='#ffffff'"
            onclick="show_detail('<%=row.getValue("PLAN_ID")%>')">
            <td width="3%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                     value="<%=row.getValue("ORGANIZATION_NAME")%>"></td>
            <td width="2%" align="center" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                       value="<%=row.getValue("PLAN_ID")%>">
            </td>
            <td width="10%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                      value="<%=row.getValue("PLAN_NAME")%>">
            </td>
            <td width="3%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                     value="<%=row.getValue("PLAN_LEVEL")%>">
            </td>
            <td width="3%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                     value="<%=row.getValue("PRO_CITY")%>">
            </td>
            <td width="10%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                      value="<%=row.getValue("PLAN_NO")%>">
            </td>
            <td width="3%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                     value="<%=row.getValue("PLAN_TYPE")%>">
            </td>
            <td width="3%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                     value="<%=row.getValue("PRINT_DATE")%>">
            </td>
            <td width="10%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                      value="<%=row.getValue("KNOW_POST")%>">
            </td>
            <td width="8%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                     value="<%=row.getValue("QUANTITY")%>">
            </td>
            <td width="6%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                     value="<%=row.getValue("LEADER_POST")%>">
            </td>
            <td width="4%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                     value="<%=row.getValue("IS_DRILL")%>">
            </td>
            <td width="10%" align="left" onclick="show_detail('<%=planId%>');"><input type="text" class="finput" readonly
                                                                                      value="<%=row.getValue("REMARK")%>">
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
</form>
<div id="navigatorDiv" style="position:absolute;bottom:0px;left:0;"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>

</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>

<script type="text/javascript">

    function initPage() {
        do_SetPageWidth();
    }

    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
    }

    function show_detail(planId) {
        mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjPlanServlet?planId=" + planId;
        mainFrm.submit();
    }

    function do_add() {
        mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
        mainFrm.submit();
    }

    function do_Export() {                  //����execl
        document.mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        document.mainFrm.submit();
    }
    function do_export_plan() {                  //����������execl
        document.mainFrm.act.value = "PLAN";
        document.mainFrm.submit();
    }

</script>