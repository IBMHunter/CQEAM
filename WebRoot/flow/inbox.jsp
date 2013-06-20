<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.flow.constant.ReqAttributeList" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%--
  Created by wwb.
  User: demo
  Date: 2006-12-6
  Time: 10:34:50
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>�ռ���</title>
    <link href="/WebLibary/css/main.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="/flow/flow.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <%
        RequestParser rp = new RequestParser();
        rp.transData(request);
        SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
        String userId = userAccount.getUserId();
    %>
</head>

<body topmargin="0" leftmargin="0" onkeydown="enterQuery();" onload="initPage();">
<form action="/servlet/com.sino.flow.servlet.InboxServlet" method="post" name="mainForm">
    <script type="text/javascript">
        printTitleBar("�ռ���");
    </script>
    <input type="hidden" name="flag" value="">
    <table style="width:100%;" class="queryHeadBg">
        <tr height="24">
            <td width=10%></td>
            <td align=right width=10%  class=noborder>���ݺţ�</td>
            <td class=noborder  width="20%">
                <input style="width:100%" type="text" name="applyNumber" value="<%=rp.getParameter("applyNumber")%>">
            </td>
            <td width=10% align=right>�������ͣ�</td>
            <td class=noborder width="20%">
                <input style="width:100%" type="text" name="procName" value="<%=rp.getParameter("procName")%>">
            </td>
            <td width=20%></td>
            <!--<td>-->
            <%--<input type="button" name="" style="cursor:pointer" onclick="query();"--%>
            <!--value="��ѯ"></td>-->
            <td><a style="cursor:'hand'"><img src="/images/eam_images/search.jpg"
                                              alt="�����ѯ" onClick="query(); return false;"></a>
            </td>
            <!--<td>                         -->
            <%--<input type="button" name="" style="cursor:pointer" onclick="sign();"--%>
            <!--value="ǩ��"></td>-->
            <td><a style="cursor:'hand'"><img src="/images/eam_images/sign.jpg"
                                              alt="���ǩ��" onClick="sign(); return false;"></a>
        </tr>
    </table>
    <input type="hidden" name="applyId" value="">
    <%--<table style="position:relative;top:3px;width:840px" border=1 cellspacing="0" class="headerTable">
        <tr height="20px" width=100%>
            <td align="center" width=4%><input type="checkbox" name="mainCheck"
                                               onclick="checkAll('mainCheck','subCheck')"></td>
            <td align="center" width=16%>��������</td>
            <td align="center" width=16%>���ݺ�</td>
            <td align="center" width=16%>ת����</td>
            <td align="center" width=16%>ת������</td>
            <td align="center" width=16%>������</td>
            <td align="center" width=16%>��������</td>
        </tr>
    </table>--%>
    <script type="text/javascript">
        var columnArr = new Array("checkbox", "��������", "���ݺ�", "ת����", "ת������", "������", "��������");
        var widthArr = new Array("4%", "20%", "18%", "10%", "10%", "10%", "10%");
        printTableHead(columnArr, widthArr);
    </script>

    <div style="overflow-y:scroll;width:100%;height:380px">
        <table style="width:100%" border="1" borderColor="#9CC4FF" cellspacing="0"
               cellpadding="0" id="dataTable">
            <%
                RowSet rows = (RowSet) request.getAttribute(ReqAttributeList.INBOX_DATA);
                if (rows != null && !rows.isEmpty()) {
                    Row row = null;
                    for (int i = 0; i < rows.getSize(); i++) {
                        row = rows.getRow(i);
            %>

            <tr height=22px style="cursor:pointer" class="trBright" onMouseMove="style.backgroundColor='#EFEFEF'"
                onMouseOut="style.backgroundColor='#ffffff'"
                onclick="showDetail('<%=row.getValue("APP_ID")%>','<%=row.getValue("FROM_TASK_USER")%>','<%=row.getValue("TOTAL_USERS")%>','<%=row.getValue("ACTID")%>','<%=row.getValue("PROC_ID")%>','<%=row.getValue("CUR_TASK_ID")%>','<%=row.getValue("TASK_PROP")%>','<%=row.getValue("SECTION_RIGHT")%>','<%=row.getValue("HIDDEN_RIGHT")%>','<%=row.getValue("TASK_NAME")%>','<%=row.getValue("FORWARD_PATH")%>','<%=row.getValue("SIGN_FLAG")%>','<%=row.getValue("ATTRIBUTE1")%>','<%=row.getValue("ATTRIBUTE2")%>','<%=row.getValue("ATTRIBUTE3")%>','<%=row.getValue("ATTRIBUTE4")%>','<%=row.getValue("ATTRIBUTE5")%>')">
                <td width=4% align=center>
                    <input type="checkbox" name="subCheck" id="subCheck<%=i%>" value="<%=row.getValue("ACTID")%>">
                    <input type="hidden" name="applyNum" value="<%=row.getValue("APPLY_NUMBER")%>">
                    <input type="hidden" name="isSign" id="isSign<%=i%>" value="">
                    <input type="hidden" name="actId" value="<%=row.getValue("ACTID")%>">
                </td>
                <td width=20%><%=row.getValue("PROC_NAME")%></td>
                <td width="18%"><%=row.getValue("APPLY_NUMBER")%>
                </td>
                <td width="10%"><%=row.getValue("FROM_USER")%>
                </td>
                <td width="10%" align="center"><%=row.getValue("FROM_TIME")%>
                </td>
                <td width="10%"><%=row.getValue("USERNAME")%>
                </td>
                <td align="center" width="10%"><%=row.getValue("CREATION_DATE")%>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
<%=WebConstant.WAIT_TIP_MSG%>
</body>
</html>
<script>
    function query() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainForm.submit();
    }
    //applyId,Ӧ��ID   preTaskUser��Ҫ�˵���һ���ڵ㣬��һ���û���totalUsers����ǰ�ڵ�������ж��٣��������һ������Ҫǩ�ա�
    //actId:��¼ID��userId:��ǰ�û�ID
    var vapplyId = "";
    var vprevTaskId = "";
    var vprevUserId = "";
    var vactId = "";
    var vprocId = "";
    var vcurrTaskId = "";
    var vtaskProp = "";
    var vsectionRight = "";
    var vhiddenRight = "";
    var vcurrTaskName = "";
    var vforwardPath = "";
    var vsignFlag = "";
    var userId = "<%=userId%>";
	var vAttribute1 = "";//added by mshtang
	var vAttribute2 = "";//added by mshtang
	var vAttribute3 = "";//added by mshtang
	var vAttribute4 = "";//added by mshtang
	var vAttribute5 = "";//added by mshtang
    /*
    *applyId��Ӧ��ID
    *preTaskUser:Ӧ�˻ص�ǰһ���ڵ���û�
    *totalUsers:��ǰ�ڵ���������ܺ�
    * actId:��¼ID
    *procId:����ID
    *currTaskId:��ǰ�ڵ�ID
    *taskProp:�ڵ�����
    *sectionRight:Ȩ�޿����ֶ�
    *hiddenRight:������
    *currTaskName����ǰ�ڵ�����
    * forwardPath:ת���·��
    * signFlag:�Ƿ�ǩ��
     */
    function showDetail(applyId, preTaskUser, totalUsers, actId, procId, currTaskId, taskProp, sectionRight, hiddenRight, currTaskName, forwardPath, signFlag, attribute1, attribute2, attribute3, attribute4, attribute5) {
        if (event.srcElement && event.srcElement.name == "subCheck") {
            return;
        }
        vapplyId = applyId;
        var arr = preTaskUser.split("$$");
        vprevTaskId = arr[0];
        vprevUserId = arr[1];
        vactId = actId;
        vprocId = procId;
        vcurrTaskId = currTaskId;
        vtaskProp = taskProp;
        vhiddenRight = hiddenRight;
        vsectionRight = sectionRight;
        vcurrTaskName = currTaskName;
        vforwardPath = forwardPath;
        vsignFlag = signFlag;
		vAttribute1 = attribute1;
		vAttribute2 = attribute2;
		vAttribute3 = attribute3;
		vAttribute4 = attribute4;
		vAttribute5 = attribute5;
        mainForm.applyId.value = applyId;
        /*if (Number(totalUsers) > 1) { //ǩ��
            signApply(actId, userId, "showApp()");
        } else {
            showApp();
        }*/
        showApp();
    }
    function showApp() {
        var url = vforwardPath + vapplyId
			+ "&actId=" + vactId
			+ "&prevUserId=" + vprevUserId
			+ "&prevTaskId=" + vprevTaskId
			+ "&procId=" + vprocId
			+ "&currTaskId=" + vcurrTaskId
			+ "&taskProp=" + vtaskProp 
			+ "&sectionRight=" + vsectionRight
			+ "&hiddenRight=" + vhiddenRight
			+ "&currTaskName=" + vcurrTaskName
			+ "&signFlag=" + vsignFlag
			+ "&attribute1=" + vAttribute1
			+ "&attribute2=" + vAttribute2
			+ "&attribute3=" + vAttribute3
			+ "&attribute4=" + vAttribute4
			+ "&attribute5=" + vAttribute5;
        var style = "width="+screen.availWidth+",height="+screen.availHeight+",top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes";
        window.open(url, 'applyInFlowWin', style);
    }
    function enterQuery() {
        if (event.keyCode == 13) {
            query();
        }
    }
    function sign() {
        if (getCheckedBoxCount("subCheck") == 0) {
            alert("��ѡ����Ҫǩ�յ����룬Ȼ����ǩ�հ�ťǩ�գ�");
            return;
        }
        var dataTable = document.getElementById("dataTable");
        var length = dataTable.rows.length;
        for (var i = 0; i < length; i++) {
            if (document.getElementById("subCheck" + i).checked) {
                document.getElementById("isSign" + i).value = "Y";
            }
        }
        document.mainForm.flag.value = 'sign';
        document.mainForm.submit();
    }
    function initPage() {
        var msg = '<%=StrUtil.nullToString(request.getAttribute("SIGN_MSG"))%>';
        if (msg != '') {
            alert(msg);
        }
    }
</script>