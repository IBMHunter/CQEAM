<%@ page contentType="text/html;charset=GBK" language="java" isErrorPage="false"%>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.framework.dto.BaseUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.framework.security.dto.FilterConfigDTO" %>
<%@ page import="com.sino.ams.system.user.dto.EtsOuCityMapDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ include file="/newasset/headerInclude.htm"%>

<html>
	<head>
		<title>�л�OU��֯</title>
        <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
        <script type="text/javascript" src="/WebLibary/js/util.js"></script>
        <script type="text/javascript" src="/WebLibary/js/AjaxProcess.js"></script>
        <script type="text/javascript">
            printTitleBar("�л�OU��֯");
            ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "do_Close");
            ArrAction1 = new Array(true, "ȷ��", "action_save.gif", "����", "do_Confirm");
            ArrActions = new Array(ArrAction0, ArrAction1);
            printToolBar();
        </script>
	</head>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    String dataProcessed = StrUtil.nullToString(request.getAttribute("DATA_PROCESSED"));
%>
<body topmargin="0" leftmargin="0" onload="do_SetPageWidth();">
    <jsp:include page="/message/MessageProcess"/>
    <form method="post" id="mainFram" action="/servlet/com.sino.ams.log.servlet.ChangeOrganizationServlet">
<%
    if(dataProcessed.length() == 0){//��δ���������
        SfUserDTO userDTO = (SfUserDTO)SessionUtil.getUserAccount(request);
%>
        <strong><font color="#8a2be2">��ǰOU��<%=userDTO.getCompany()%></font></strong>
        <hr size="1" width="100%" color="#660000">
        <div id="headDiv" style="overflow-x:hidden;overflow-y:scroll;position:absolute;top:100px;left:1px;width:100%"  >
            <table class="headerTable" border="1" width="100%" style="text-align:center">
                <tr height="23px">
                    <td width="10%">ѡ��</td>
                    <td width="90%">���л�OU</td>
                </tr>
            </table>
        </div>
        <div id="dataDiv" style="overflow:scroll;height:75%;width:100%;position:absolute;top:74px;left:1px" align="left" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
            <table id="dataTable" width="100%" border="1"  style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
    List<EtsOuCityMapDTO> otherOrgList = (List<EtsOuCityMapDTO>)request.getAttribute("ORG_LIST");
    if(otherOrgList != null && !otherOrgList.isEmpty()){
        for(int i = 0; i < otherOrgList.size(); i++){
            EtsOuCityMapDTO org = otherOrgList.get(i);
%>
            <tr>
                <td width="10%" align="center"><input type="radio" name="organizationId" value="<%=org.getOrganizationId()%>" id="organizationId_<%=org.getOrganizationId()%>"></td>
                <td width="90%"><label for="organizationId_<%=org.getOrganizationId()%>"><%=org.getCompany()%></label></td>
            </tr>
<%
        }
    }
%>
            </table>
        </div>
<%
    } else {//�Ѿ������
        BaseUserDTO anotherAccount = (BaseUserDTO)request.getAttribute("ANOTHER_ACCOUNT");
        FilterConfigDTO filterDTO = SessionUtil.getFilterConfigDTO(request);
        String message = "";
        String forwardURL = "";
        if(anotherAccount != null){//OU��֯�л��ɹ���
            anotherAccount.setRemoteIp(request.getRemoteAddr());
            SessionUtil.saveUserSession(request, anotherAccount);
            forwardURL = filterDTO.getLoginSuccessURL();
            forwardURL = request.getContextPath() + forwardURL;
            message = "OU��֯�л��ɹ�";
        } else {
            forwardURL = filterDTO.getLoginUrl();
            SessionUtil.invalidateSession(request);
            message = "OU��֯�л�ʧ�ܣ�ϵͳ�Ѿ�����ע��...";
        }
        forwardURL += "?source=oa";
%>
        <script type="text/javascript">
            alert("<%=message%>");
            self.close();
            opener.parent.parent.location.href = "<%=forwardURL%>";
        </script>
<%
    }
%>
        <input type="hidden" name="act" id="act">
    </form>
</body>
</html>
<script type="text/javascript">

function do_Confirm(){
    var organizationId = getRadioValue("organizationId");
    if(organizationId == ""){
        alert("δѡ��Ŀ��OU��֯����������л���");
        return;
    }
    mainFram.act.value = "CHANGE_OU";
    mainFram.submit();
}

function do_Close(){
    self.close();
}
</script>
