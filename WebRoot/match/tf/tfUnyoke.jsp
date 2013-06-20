<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.CheckBoxProp" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.ams.newasset.constant.AssetsLookUpConstant" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/RadioProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
    <% 
    	String titleText="";
    	String titleBar="";
    	SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    	if ("Y".equalsIgnoreCase(user.getIsTd())) { 
    		titleText="�����ʲ�ƥ���ϵ(TD)";
    		titleBar="�����ʲ�ƥ���ϵ(TD)";
    	} else {
    		titleText="����ͨ���ʲ�ƥ���ϵ";
    		titleBar="����ͨ���ʲ�ƥ���ϵ";
    	}
    %>	
	<title><%=titleText %></title>
</head>

<body onkeydown="autoExeFunction('do_search()');" onload="do_SetPageWidth();mainFrm.workorderObjectName.focus();">

<%
    RequestParser reqParser = new RequestParser();
    CheckBoxProp checkProp = new CheckBoxProp("systemId");
    reqParser.setCheckBoxProp(checkProp);
    reqParser.transData(request);
    String matchType = StrUtil.nullToString(reqParser.getParameter("matchType"));
    String itemName = StrUtil.nullToString(reqParser.getParameter("itemName"));
    String itemSpec = StrUtil.nullToString(reqParser.getParameter("itemSpec"));
    String workorderObjectNo = StrUtil.nullToString(reqParser.getParameter("workorderObjectNo"));
    String workorderObjectName = StrUtil.nullToString(reqParser.getParameter("workorderObjectName"));
    String tagNumber = StrUtil.nullToString(reqParser.getParameter("tagNumber"));
    String workorderobjectCode = StrUtil.nullToString(reqParser.getParameter("workorderobjectCode"));
    String barcode = StrUtil.nullToString(reqParser.getParameter("barcode"));
    String matchUserName = StrUtil.nullToString(reqParser.getParameter("matchUserName"));
    String matchUserId = StrUtil.nullToString(reqParser.getParameter("matchUserId"));
    SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
%>
<form method="post" name="mainFrm">
<script type="text/javascript">
    printTitleBar("<%=titleBar%>")
</script>
<table border="0" width="100%" class="queryTable">
    <tr>
        <td width="8%" align="right">�ص㣺</td>
        <td width="22%" align="left">
            <input class="input_style1" name="workorderObjectName" type="text" style="width:80%" value="<%=workorderObjectName%>"><a href=# title="���ѡ��ص�" class="linka" onclick="do_SelectProj();">[��]</a>
            <input type=hidden name="workorderObjectNo" value="<%=workorderObjectNo%>">
        </td>
        <td align="right" width="8%">�����ǩ�ţ�</td>
        <td align="left" width="15%"><input class="input_style1" name="tagNumber" style="width:83%" type="text" value="<%=tagNumber%>"></td>
        <td align="right" width="8%">EAM��ǩ�ţ�</td>
        <td align="left" width="10%"><input class="input_style1" name="barcode" style="width:100%" type="text" value="<%=barcode%>"></td>


    </tr>
    <tr>
        <td width="8%" align="right">�豸���ƣ�</td>
        <td width="22%" align="left"><input class="input_style1" name="itemName" style="width:80%" type="text" value="<%=itemName%>"></td>
        <td width="8%" align="right">����ͺţ�</td>
        <td width="15%" align="left"><input class="input_style1" type=text name=itemSpec style="width:83%" value="<%=itemSpec%>"><input name=item_code type=hidden><a href=# title="���ѡ�������ͺ�" class="linka" onclick="do_SelectSpec();">[��]</a></td>
        <td width="8%" align="right">ƥ���ˣ�</td>
        <td width="10%" align="left">
            <input class="input_style1" type="text" name="matchUserName" value="<%=matchUserName%>" style="width:100%;cursor:hand" onclick="do_selectUser();" title="���ѡ���û�" readonly>
            <input type="hidden" name="matchUserId" value="<%=matchUserId%>">
        </td>
        <td width="18%" align="right" colspan="2">
            <img src="/images/eam_images/unmatch.jpg" style="cursor:'hand'" onclick="do_unmatch();" alt="���ƥ��">
            <img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ">
        </td>
    </tr>
</table>
<input type="hidden" name="act" value="<%=reqParser.getParameter("act")%>">
<input type="hidden" name="flag" value="0">

<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:73px;left:0px;width:100%" class="crystalScroll">
    <table class="eamHeaderTable" border="1" width="240%">
        <jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>"/>
        <tr onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
            <!--<td width="3%" align="center"><input type="checkBox" name="controlBox" class="headCheckbox"-->
                                                 <%--onClick="checkAll(this.name, 'systemId')"></td>--%>
            <td width="3%" align="center" style="padding:0"><input type="checkbox" name="titleCheck" class="headCheckbox" id="controlBox" onclick="checkAll('titleCheck','subCheck')"></td>
            <td height="22" width="7%" align="center">EAM����</td>
            <td height="22" width="7%" align="center">�����ǩ��</td>
            <td height="22" width="10%" align="center">�豸����</td>
            <td height="22" width="10%" align="center">����ͺ�</td>
            <td height="22" width="15%" align="center">�ص����</td>
            <td height="22" width="10%" align="center">�ص���</td>
            <td height="22" width="4%" align="center">ƥ����</td>
            <td height="22" width="10%" align="center">ƥ��ʱ��</td>
            <td height="22" width="4%" align="center">ԭ�ʲ�����</td>
            <td height="22" width="5%" align="center">EAMĿ¼����</td>
            <td height="22" width="5%" align="center">EAMĿ¼����</td>
            <td height="22" width="5%" align="center">MISĿ¼����</td>
            <td height="22" width="5%" align="center">MISĿ¼����</td>
        </tr>
    </table>
</div>
<div style="overflow:scroll;height:68%;width:100%;position:absolute;top:96px;left:0px" align="left"
     onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
    <%
        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        if (rows != null && !rows.isEmpty()) {
    %>
    <%--    <div style="overflow-y:scroll;height:362;width:852;position:absolute;top:95px;left:1px;margin-left:0px"
    align="left">--%>
    <table width="240%" border="1" bordercolor="#666666">
        <%
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
        %>
        <tr class="dataTR" onClick="executeClick(this)">
            <td width="3%" align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%>
            <!--<td width="3%" align="center">-->
                <%--<input type="checkbox" name="systemId" value="<%=row.getStrValue("SYSTEM_ID")%>">--%>
                <%--<input type="hidden" name="assetId" value="<%=row.getStrValue("ASSET_ID")%>">--%>
            <!--</td>-->
            <td style="word-wrap:break-word" height="22" width="7%"
                align="center"><%=row.getValue("BARCODE")%>
                <%--<input type="hidden" name="assetId" value="<%=row.getValue("BARCODE")%>">--%>
            </td>
            <td style="word-wrap:break-word" height="22" width="7%"
                align="center"><%=row.getValue("TAG_NUMBER")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="10%"
                align="left"><%=row.getValue("ITEM_NAME")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="10%"
                align="left"><%=row.getValue("ITEM_SPEC")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="15%"
                align="center"><%=row.getValue("WORKORDER_OBJECT_CODE")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="10%"
                align="left"><%=row.getValue("WORKORDER_OBJECT_NAME")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="4%"
                align="center"><%=row.getValue("MATCH_USER_NAME")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="10%"
                align="center"><%=row.getValue("MATCH_DATE")%>
            </td>
            <td style="word-wrap:break-word" height="22" width="4%"
                align="center"><%=row.getValue("OLD_FINANCE_PROP")%>
            </td><td style="word-wrap:break-word" height="22" width="5%"
                align="center"><%=row.getValue("CONTENT_CODE")%>
            </td><td style="word-wrap:break-word" height="22" width="5%"
                align="center"><%=row.getValue("CONTENT_NAME")%>
            </td><td style="word-wrap:break-word" height="22" width="5%"
                align="center"><%=row.getValue("FA_CODE")%>
            </td><td style="word-wrap:break-word" height="22" width="5%"
                align="center"><%=row.getValue("FA_CATEGORY2")%>
            </td>
           
        </tr>
        <%
                }
            }
        %>
    </table>
</div>

</form>

<div style="position:absolute;top:415px;left:0; right:20"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>

<jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>" flush="true"/>
<%=WebConstant.WAIT_TIP_MSG%>
</body>
</html>
<script type="text/javascript">
    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "<%=URLDefineList.TF_FINANCE_PROP_SET_SERVLET%>?matchType=<%=matchType%>";
        mainFrm.submit();
    }
    function do_unmatch() {
//        var checkCount = getCheckedBoxCount("systemId")
        var checkCount = getCheckedBoxCount("subCheck")
        if (checkCount < 1) {
            alert("������ѡ��һ�����ݣ�");
            return;
        } else {
            if (document.mainFrm.flag.value == "1") {
                alert("���ڳ���ƥ�䣬��ȴ���");
                return;
            }
            if (confirm("ȷ������ƥ����?")) {
                document.mainFrm.flag.value="1";
                document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
                mainFrm.act.value = "<%=AMSActionConstant.MATCH_ACTION%>";
                mainFrm.action = "<%=URLDefineList.TF_FINANCE_PROP_SET_SERVLET%>?matchType=<%=matchType%>";
                mainFrm.submit();
            }

        }
    }
    function do_SelectProj() {
        var lookUpAddr = "<%=LookUpConstant.LOOK_UP_TF_ASSETS_OBJECT%>";
        var dialogWidth = 47.5;
        var dialogHeight = 30;
        var Addrs = getLookUpValues(lookUpAddr, dialogWidth, dialogHeight);
        if (Addrs) {
            var Addr = null;
            for (var i = 0; i < Addrs.length; i++) {
                Addr = Addrs[i];
                dto2Frm(Addr, "mainFrm");
            }
        }
    }
    function do_SelectSpec() {

        var lookUpSpec = "<%=LookUpConstant.LOOK_UP_ITEM_SIMPLE%>";
        var dialogWidth = 51;
        var dialogHeight = 30;
        var specs = getLookUpValues(lookUpSpec, dialogWidth, dialogHeight);
        if (specs) {
            var spec = null;
            for (var i = 0; i < specs.length; i++) {
                spec = specs[i];
                dto2Frm(spec, "mainFrm");
            }
        }
    }
    function do_selectUser() {
        var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_USER%>";
        var dialogWidth = 44;
        var dialogHeight = 29;
        var userPara = "organizationId=<%=userAccount.getOrganizationId()%>";
        var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);

        if (objs) {
            var obj = objs[0];
            document.mainFrm.matchUserId.value = obj["userId"];
            document.mainFrm.matchUserName.value = obj["userName"];
        } else {
            document.mainFrm.matchUserId.value = "";
            document.mainFrm.matchUserName.value = "";
        }
    }
</script>