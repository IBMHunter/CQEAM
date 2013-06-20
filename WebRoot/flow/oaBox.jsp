<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.flow.constant.ReqAttributeList" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.sso.constant.SSOURLDefineList" %>
<%@ page import="com.sino.sso.constant.SSOWebAttributes" %>
<%--
  Created by wwb.
  User: demo
  Date: 2006-12-6
  Time: 10:34:50
  Function:OA����
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>�ռ���</title>
    <link href="/WebLibary/css/main.css" rel="stylesheet" type="text/css"/>
    <link href="/WebLibary/css/comon.css" rel="stylesheet" type="text/css"/>
    <link href="/WebLibary/css/html.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="/flow/flow.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <%
        RequestParser rp = new RequestParser();
        rp.transData(request);
        SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
//        String userId = userAccount.getUserId();
        boolean hasAssetsUpdate = ((String) request.getAttribute(SSOWebAttributes.SYNC_CHANGES)).equalsIgnoreCase("TRUE");
        boolean hasAssetsCommit = ((String) request.getAttribute(SSOWebAttributes.SYNC_TRANS_RESULT)).equalsIgnoreCase("TRUE");
        boolean hasAssetsCommitCOMP = ((String) request.getAttribute(SSOWebAttributes.SYNC_TRANS_IN_COMP)).equalsIgnoreCase("TRUE");
        boolean hasSomethingToDO = true;
    %>
</head>

<body bgcolor="#ECF5FF" leftmargin="0" topmargin="0">
<form action="/servlet/com.sino.sso.servlet.OABoxServlet" method="post" name="mainForm">
    <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#ECF5FF">
        <tr>
            <td align="center" valign="top" bgcolor="#ECF5FF">
        <tr valign="top">
            <td colspan="3" align="center">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <%
                        RowSet rows = (RowSet) request.getAttribute(ReqAttributeList.INBOX_DATA);
                        if (rows != null && !rows.isEmpty()) {
                            Row row = null;
                            for (int i = 0; i < rows.getSize(); i++) {
                                row = rows.getRow(i);
                                String fromDate = row.getStrValue("SFACT_FROM_DATE");
                                String procName = row.getStrValue("CATEGORY_NAME");
                                String applyNumber = row.getStrValue("SFACT_APPL_COLUMN_1");
                                String fromUser = row.getStrValue("SFACT_FROM_TASK_USER");
                                String composeUser = row.getStrValue("SFACT_COMPOSE_USER");
                                fromUser = StrUtil.isEmpty(fromUser) ? composeUser : fromUser;

                                String title = fromDate + " " + procName + "[" + applyNumber + "]   " + fromUser;

                                String caseId = row.getStrValue("SFACT_CASE_ID");
                                if (row.getStrValue("SFACT_DOC_TYPE").equals("98")) {
                                    int index = caseId.indexOf(":");
                                    if (index >= 0)
                                        caseId = caseId.substring(index + 1);
                                }
                                String bitMask = row.getStrValue("ALLOW_OPERATION");
                                String url = "/servlet/com.sino.sinoflow.servlet.ProcessCopy?sf_copyID='" + caseId + "'&sf_appMask=" + bitMask + "&signAct=0";
                    %>
                    <tr align="left">
                        <td width="1%" align="left"><img src="/images/dot01.jpg"></td>
                        <td align="left">&nbsp;<a class="bluelink" href="#"
                                                  onclick="doStep('<%=url%>')"
                                                  title="<%=title%>"><%=title%>
                        </a></td>
                    </tr>

                    <%
                            }
                        } else {
                            hasSomethingToDO = false;
                        }
                    %>
                    <%
                        if (hasAssetsUpdate) {//�ʲ��䶯ֱ��ͬ��
                            String title = "�ʲ��䶯ֱ��ͬ��";
                    %>
                    <tr align="left">
                        <td width="1%" align="left"><img src="/images/dot01.jpg"></td>
                        <td align="left">&nbsp;<a class="bluelink" href="#"
                                                  onclick="doStep('<%=SSOURLDefineList.SYNC_CHANGES_URL%>')"
                                                  title="<%=title%>"><%=title%>
                        </a></td>
                    </tr>
                    <%
                        }
                        if (hasAssetsCommit) {//�ʲ��������ͬ��
                            String title = "�ʲ��������ͬ��";
                    %>
                    <tr align="left">
                        <td width="1%" align="left"><img src="/images/dot01.jpg"></td>
                        <td align="left">&nbsp;<a class="bluelink" href="#"
                                                  onclick="doStep('<%=SSOURLDefineList.SYNC_TRANS_RESULT_URL%>')"
                                                  title="<%=title%>"><%=title%>
                        </a></td>
                    </tr>
                    <%
                        }
                        if (hasAssetsCommitCOMP) {//�ʲ��������ͬ��(��˾��)
                            String title = "��˾�����ͬ��";
                    %>
                    <tr align="left">
                        <td width="1%" align="left"><img src="/images/dot01.jpg"></td>
                        <td align="left">&nbsp;<a class="bluelink" href="#"
                                                  onclick="doStep('<%=SSOURLDefineList.SYNC_TRANS_IN_COMP_URL%>')"
                                                  title="<%=title%>"><%=title%>
                        </a></td>
                    </tr>
                    <%
                        }
                        hasSomethingToDO = hasSomethingToDO || hasAssetsUpdate || hasAssetsCommit || hasAssetsCommitCOMP;
                    %>
                    <%if (!hasSomethingToDO) {%>
                    <tr>
                        <td colspan="2"><b><font color='blue'>&nbsp;&nbsp;&nbsp;��û���ʲ�ʵ�����</font></b></td>
                    </tr>
                    <%}%>
                </table>
            </td>
        </tr>

    </table>
    <input type="hidden" name="flag" value="">
    <input type="hidden" name="applyId" value="">
</form>
<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
<%=WebConstant.WAIT_TIP_MSG%>
</body>
</html>
<script>

    function doStep(url) {
        var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes";
        window.open(url, 'applyInFlowWin', style);
    }

</script>