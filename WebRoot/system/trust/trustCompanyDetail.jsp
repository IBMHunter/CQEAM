<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.ams.system.trust.dto.AmsMaintainCompanyDTO"%>
<%@ page import="com.sino.ams.system.trust.dto.AmsMaintainFilesDTO"%>


<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>��ά��˾��ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script language = "javascript" src = "/WebLibary/js/SinoToolBar.js"></script>
    <script language = "javascript" src = "/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/adjunct.js"></script>
</head>

<%
    AmsMaintainCompanyDTO mainCop = (AmsMaintainCompanyDTO) request.getAttribute(WebAttrConstant.MAINTAIN_CORP_ATTR);
    RequestParser parser = new RequestParser();
    parser.transData(request);
%>
<body onload="initPage();">
<jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>" flush="true"/>

<form name="mainFrm" method="POST">
    <script type = "text/javascript">
        printTitleBar("��ά��˾ά��")
    </script>

    <table border="0" width="100%" id="table1">
        <input type="hidden" name="act" value="<%= mainCop.getAct() %>">
        <tr>
            <td width="25%" align="right" height="22">��˾���ƣ�</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="name" size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%=mainCop.getName() %>"></td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font> </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">��˾��ַ��</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="address" size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%=mainCop.getAddress() %>"></td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">���˴���</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="legalRepresentative"
                                                                        size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%=mainCop.getLegalRepresentative() %>"
                    ></td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">��ϵ�ˣ�</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="contactPeople" size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%=mainCop.getContactPeople() %>"></td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�칫�绰��</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="officeTelephone" size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%=mainCop.getOfficeTelephone() %>"
                                                                        onkeydown="intOnlyOnkeyDown(this.value);">
            </td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">��ϵ�˵绰��</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="contactTelephone" size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%=mainCop.getContactTelephone() %>">
            </td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">������룺</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="faxNumber" size="40"
                                                                        class="input_style1" style="width:100%"
                                                                        value="<%=mainCop.getFaxNumber() %>"
                                                                        onkeydown="intOnlyOnkeyDown(this.value);"></td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">���ش��룺</td>
            <td width="50%" align="left" height="22" colspan="3">
                <select name="countyCode"  class="select_style1"   style="width:100%"><%=request.getAttribute(WebAttrConstant.COUNTY_OPTION)%></select>
            </td>
            <td width="25%" align="left" height="22">&nbsp;<font style="color: red">*</font></td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�� ע��</td>
            <td width="50%" align="left" height="22" colspan="3"><textarea cols="97" name="remark"  rows="4"
                                                                        style="width:100%" ><%=mainCop.getRemark()%></textarea></td>
            <td width="25%" align="left" height="22"></td>
        </tr>
<%-- 
    DTOSet files = (DTOSet)request.getAttribute(WebAttrConstant.ATTACH_FILES);
    if(files != null && !files.isEmpty()){
%>
        <tr><td width="25%" align="right" height="22">����ļ���</td>
            <td width="50%" align="left" height="22" colspan="3">
<%
        int fileCount = files.getSize();
        for(int i = 0; i < fileCount; i++){
            AmsMaintainFilesDTO file = (AmsMaintainFilesDTO)files.getDTO(i);
%>
                <a href="" onClick="do_DownLoad(<%=file.getSystemId()%>); return false;"><%=file.getFileDescription()%></a>
<%
        }
%>
            </td>
        </tr>
<%
    }
--%>

        <tr>
            <td width="100%" align="center" height="22" colspan="5">
                <img src="/images/eam_images/save.jpg" alt="����" onClick="do_SaveMaintainCompany(); return false;">&nbsp;
                 <img src="/images/eam_images/attach.jpg" alt="��������" onclick="editAttach(mainFrm.companyId,'AMS_INFO_PUBLISH','AMS_INFO_PUBLISH_S');"/>&nbsp;
                <%--<img src="/images/eam_images/attach.jpg" alt="�����ļ�" onClick="do_AttachFiles()">&nbsp;--%>
                <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="do_Back(); return false;"></td>
        </tr>

    </table>

    <input type="hidden" name="companyId" value="<%=mainCop.getCompanyId()%>">
    <input type="hidden" name="isAttachFile" value="<%=parser.getAttribute(WebAttrConstant.ATTACH_FILE_ATTR)%>">
</form>
</body>
</html>
<iframe name="downTarget" src="" style="display:none"></iframe>
<script>

    function validateFrm(){
        var isValid = false;
        var fieldNames = "name;address;legalRepresentative;contactPeople;countyCode;officeTelephone;contactTelephone;faxNumber";
        var fieldLabels = "��˾����;��˾��ַ;���˴���;��ϵ��;���ش���;�칫�绰;��ϵ�˵绰;�������";
        var validateType = EMPTY_VALIDATE;
        isValid = formValidate(fieldNames, fieldLabels, validateType);
        if (isValid) {
            fieldNames = "name;address;legalRepresentative;contactPeople;officeTelephone;contactTelephone;faxNumber";
            fieldLabels = "0$��˾����$60;0$��˾��ַ$60;0$���˴���$40;0$��ϵ��$40;0$�칫�绰$20;0$��ϵ�˵绰$20;0$�������$20";
            validateType = LENGTH_VALIDATE;
            isValid = formValidate(fieldNames, fieldLabels, validateType);
            if (isValid) {
                //����У�� -- ����
                fieldNames = "officeTelephone;contactTelephone;faxNumber";
                fieldLabels = "�칫�绰;��ϵ�˵绰;�������";
                validateType = NUMBER_VALIDATE;
                isValid = formValidate(fieldNames, fieldLabels, validateType);
            }
        }
        return isValid;
    }

    function do_SaveMaintainCompany() {
        var isValid = validateFrm();
        if (isValid) { 
            var action = mainFrm.act.value;
            if( action != "<%=WebActionConstant.CREATE_ACTION%>" && action != "<%=WebActionConstant.NEW_ACTION%>"  ){
            	action = "<%=WebActionConstant.UPDATE_ACTION%>";
            }
            mainFrm.act.value = action;
            mainFrm.target = "_self";
            mainFrm.action = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainCompanyServlet";
            mainFrm.submit();
        }
    }

    function do_DeleteMaintainCompany() {

        if (confirm("ȷ��ɾ���ý�ɫ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
            mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
            mainFrm.action = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainCompanyServlet";
            mainFrm.target = "downTarget";
            mainFrm.submit();
        }
    }


    function do_Back() {

        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainCompanyServlet";
        mainFrm.target = "_self";
        mainFrm.submit();
    }

    function do_AttachFiles() {
        if(mainFrm.companyId.value == ""){
            if(validateFrm()){
                mainFrm.isAttachFile.value = "true";
                do_SaveMaintainCompany();
            }
        } else {
            var url = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainFilesServlet?act=<%=WebActionConstant.NEW_ACTION%>&companyId=<%=mainCop.getCompanyId()%>";
            var style = "height=300,width=500,top=300,left=400";
            var winName = "uploadWin";
            window.open(url, winName, style);
        }
    }

    function initPage(){
        var attachFile = mainFrm.isAttachFile.value;
        if(attachFile == "true"){
            var url = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainFilesServlet?act=<%=WebActionConstant.NEW_ACTION%>&companyId=<%=mainCop.getCompanyId()%>";
            var style = "height=300,width=500,top=300,left=400";
            var winName = "uploadWin";
            window.open(url, winName, style);
        }
    }

    function do_DownLoad(fileId){
        var url = "/servlet/com.sino.ams.system.trust.servlet.AmsMaintainFilesServlet?act=<%=WebActionConstant.DOWNLOAD_ACTION%>&systemId=" + fileId;
        var style = "height=300,width=500,top=300,left=400";
        mainFrm.action = url;
        mainFrm.target = "downTarget";
        mainFrm.submit();
    }
    
    function intOnlyOnkeyDown(obj) {
	    var k = window.event.keyCode;
	    if ((k >= 48 && k <= 57) || (k >= 96 && k <= 105) || k == 8) {
	    	
	    } else {
	        window.event.returnValue = false;
    }
}
    
</script>