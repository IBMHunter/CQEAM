<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@ include file="/rds/public/listPageInclude.jsp"%>

<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>

<html>
<head>
    <title>��������</title>
</head>
<body leftmargin="0" topmargin="0" onload="do_initPage()" onkeydown="autoExeFunction('do_Search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<html:form action="/rds/reportQuery" method="post">
    <script type="text/javascript">
        var title = "��������";
        printTitleBar(title);
        ArrActions[0][0] = true;
        ArrActions[1] = new Array(true, "����", "application_xp.png", "����", "do_RunReport");
        ArrActions[2] = new Array(true, "��ӵ��ղؼ�", "favorite.jpg", "��ӵ��ղؼ�", "do_AddFavorite");
        ArrActions[3] = new Array(true, "ǰ���ҵ��ղؼ�", "turnPic.jpg", "ǰ���ҵ��ղؼ�", "do_GoMyFavorite");

        ArrActions[11] = new Array(false, "ȷ��", "completeLookup.gif", "ȷ��", "do_Complete");
        ArrActions[12] = new Array(false, "ȡ��", "action_cancel.gif", "ȡ��", "do_CloseDIV");
        ArrActions[15][0] = true;
        printToolBar();
    </script>
    <table border="0" width="100%" id="table1" class="queryHeadBg">
        <tr style="height:22px">
            <td width="10%" align="right">��ѯ�ؼ��֣�</td>
            <td width="30%"><html:text property="reportName" styleId="reportName" styleClass="fInput1"/></td>
            <td width="10%" align="right">��ѡ��������</td>
            <td width="10%" align="right"><input type="text" name="checkedCount" id="checkedCount" style="width:100%"
                                                 class="readonlyInput1" readonly="true"></td>
            <td width="10%" align="right">��ѡ�б���</td>
            <td width="30%" align="right"><input type="text" name="reportShow" id="reportShow" style="width:100%"
                                                 class="readonlyInput1" readonly="true"></td>
        </tr>
    </table>
    <div id="favoriteDIV" style="display:none;position:absolute;top:70px;left:1px;width:100%;height:200px;z-index: 10">
        <table border="0" width="100%" id="favoriteTable" class="queryHeadBg">
            <tr>
                <td width="15%" align="right">�����ղؼУ�</td>
                <td width="35%">
                    <html:select property="headerId" styleId="headerId" style="width:100%"
                                 onchange="do_ProcessChange(this)">
                        <bean:write name="favoriteFrm" property="myFavoriteOptions" filter="false"/>
                    </html:select>
                </td>
                <td width="15%" align="right">�����ղؼУ�</td>
                <td width="35%"><html:text property="favoriteName" styleId="favoriteName" styleClass="fInput1"/></td>
            </tr>
        </table>
    </div>
    <html:hidden property="act" styleId="act"/>
</html:form>
<div id="headDiv" style="overflow-x:hidden;overflow-y:scroll;position:absolute;top:71px;left:1px;width:100%">
    <table id="headTable" border="1" width="100%">
        <tr class="headerTR" style="text-align:center;cursor:pointer" title="���ȫѡ��ȡ��ȫѡ" onclick="do_ProcessCheck(this)">
            <td width="3%"><input type="checkbox" name="controlChk" onPropertyChange="checkAll(this.name, 'subCheck')">
            </td>
            <td width="15%">�������</td>
            <td width="22%">��������</td>

            <td width="15%">ģ��</td>
            <td width="15%">��������</td>
            <td width="15%">�Ƿ���Ч</td>
            <td width="15%">��������</td>
        </tr>
    </table>
</div>
<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && !rows.isEmpty()) {
%>

<div id="dataDiv" style="overflow:scroll;height:60%;width:100%;position:absolute;top:98px;left:1px"
     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table width="100%" border="1" class="gridTable" bordercolor="#666666" id="dataTable"
           style="TABLE-LAYOUT:fixed;word-break:break-all">
        <%
            for (int i = 0; i < rows.getSize(); i++) {
                Row frm = rows.getRow(i);
        %>
        <tr onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#FFFFFF'" class="dataTR">
            <td width="3%" align="center"><%=frm.getStrValue(QueryConstant.CHECK_BOX_PROP)%>
            </td>
            <td width="15%"><input type="text" readonly class="tableInput1" value="<%=frm.getValue("REPORT_CODE")%>">
            </td>
            <td width="22%"><input type="text" readonly class="tableInput1" value="<%=frm.getValue("REPORT_NAME")%>">
            </td>

            <td width="15%"><input type="text" readonly class="tableInput1" value="<%=frm.getValue("MODEL_NAME")%>">
            </td>
            <td width="15%"><input type="text" readonly class="tableInput2"
                                   value="<%=frm.getValue("REPORT_TYPE_NAME")%>"></td>
            <td width="15%"><input type="text" readonly class="tableInput2" value="<%=frm.getValue("ENABLED_NAME")%>">
            </td>
            <td width="15%"><input type="text" readonly class="tableInput2" value="<%=frm.getValue("CREATION_DATE")%>">
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<%
    }
%>
<div id="pageNaviDiv"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
</html>
<script type="text/javascript">
    function do_ProcessCheck(tr) {
        executeClick(tr);
        do_SetCheckedReport();
        document.getElementById("favoriteName").value = "";
    }

    function do_initPage() {
        do_SetPageWidth();
        do_SetCheckedReport();
    }

    var reportCode = "";

    function do_SetCheckedReport() {
        var checkedObj = document.getElementById("$$$CHECK_TRANS_FIELD$$$");
        if (checkedObj) {
            var checkedValue = checkedObj.value;
            var reportName = "";
            var checkedCount = 0;
            if (checkedValue.length > 0) {
                var reportArr = checkedValue.split("$$$");
                checkedCount = reportArr.length;
                for (var i = 0; i < checkedCount; i++) {
                    var report = reportArr[i];
                    var arr = report.split("@@@");
                    reportName += arr[1];
                    reportCode += arr[2];
                    if (i < checkedCount - 1) {
                        reportName += ",";
                        reportCode += ",";
                    }
                }
            }
            document.forms["favoriteFrm"].reportShow.value = reportName;
            document.forms["favoriteFrm"].checkedCount.value = checkedCount;
        }
    }

    function do_AddFavorite() {
        var checkedValue = document.forms["favoriteFrm"].reportShow.value;
        if (checkedValue == "") {
            alert("δѡ���κα������ܴ����ղؼС�");
            return;
        }
        ShowSinoButton(11);
        ShowSinoButton(12);

        HideSinoButton(0);
        HideSinoButton(1);
        HideSinoButton(2);
        HideSinoButton(3);
        HideSinoButton(15);
        document.getElementById("favoriteDIV").style.display = "block";
        document.getElementById("headDiv").style.top = "93px";
        do_SetPageWidth();
    }

    function do_Complete() {
        var headerId = document.getElementById("headerId").value;
        var favoriteName = document.getElementById("favoriteName").value;
        if (headerId == "" && isEmpty(favoriteName)) {
            alert("����ѡ��һ���Ѿ����ڵ��ղؼл����µ��ղؼ����ơ�");
            return;
        }
        var frm = document.forms["favoriteFrm"];
        frm.action = "/rds/favoriteReport.do";
        frm.act.value = "SAVE_ACTION";
        frm.submit();
    }

    function do_CloseDIV() {
        HideSinoButton(11);
        HideSinoButton(12);

        ShowSinoButton(0);
        ShowSinoButton(1);
        ShowSinoButton(2);
        ShowSinoButton(3);
        ShowSinoButton(15);
        
        document.getElementById("favoriteDIV").style.display = "none";
        document.getElementById("headDiv").style.top = "71px";
        do_SetPageWidth();
    }

    function do_ProcessChange(obj) {
        var favoriteName = "";
        if (obj.value != "") {
            var options = obj.options;
            favoriteName = options[options.selectedIndex].text;
        }
        document.getElementById("favoriteName").value = favoriteName;
    }
    function do_GoMyFavorite() {
        window.location.href = "<%=contextPath%>/rds/favoriteFrm.do";
    }


    function do_CloseConfig() {
        var cfg = new CloseConfig();
        cfg.setEditPage(true);
        cfg.setRefreshOpener(false);
        return cfg;
    }

    function do_RunReport() {
        var checkedCount = document.getElementById("checkedCount").value;
        if (checkedCount != "1") {
            alert("ֻ��ѡ��һ���������У����ܲ�ѡ��Ҳ���ܶ�ѡ��");
            return;
        }
        var pageConfig = new DataPageConfig();
        pageConfig.setActionURL("<%=contextPath%>/rds/reportRun.do");
        pageConfig.setAct("");
        pageConfig.setOpenWindow(true);
        pageConfig.setWindowName(reportCode);
        var userParameter = "reportCode=" + reportCode;
        pageConfig.setParameters(userParameter);
        do_ProcessDataPage(pageConfig);
    }
</script>
