<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <title>�����ʲ�Ŀ¼���߼�����Ԫ�ض�Ӧ��ϵ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">  
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>
<body >
 <jsp:include page="/message/MessageProcess"/>
<%
	AmsElementMatchDTO aemDTO = (AmsElementMatchDTO) request.getAttribute(WebAttrConstant.AMS_ELEMENT_MATCH_DTO);
%>

<form name="mainFrm" method="POST">
    <script language="javascript">
        printTitleBar("�߼�����Ԫ�����ʲ�Ŀ¼��Ӧ��ϵά��");
    </script>
    <table border="0" width="70%" id="table1" align = "center">
    	<caption>&nbsp;</caption>
    	<caption align = "left" style = "font-size:12px">��ѡ���Ӧ--�ʲ�Ŀ¼��</caption>
        <tr>
            <td width="25%" align="right" height="22">�ʲ�Ŀ¼���ƣ�&nbsp;</td>
             <td width="50%" align="left" height="22">
            	<input type="text" name = "contentName" size = "40" onclick="do_SelectContent(); return false;" 
            	class="input_style1" title="���ѡ���ʲ�Ŀ¼" style="width:80%;cursor:hand" readonly>&nbsp;<font style="color: red">*</font>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�ʲ�Ŀ¼���룺&nbsp;</td>
            <td width="50%" align="left" height="22" colspan="3"><input type="text" name="contentCode" class="input_style1" readonly size="40" style="width:80%"></td>
        </tr>
     </table>
     <p></p>
     <table border="0" width="70%" id="table1" align = "center">
     	<caption align = "left" style = "font-size:12px">��ѡ���Ӧ--�߼�����Ԫ�أ� </caption>
     	<input type = "hidden" name = "amsLneId">
        <tr>
            <td width="25%" align="right" height="22">�߼�����Ԫ�أ�&nbsp;</td>
            <td width="50%" align="left" height="22">
                <input type="text" name="logNetEle" class="input_style1" style="width:80%;cursor:hand" readonly 
                 title="���ѡ���߼�����Ԫ��" onClick="do_SelectLne();">&nbsp;<font style="color: red">*</font>	
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">��Ԫ���룺&nbsp;</td>
            <td width="50%" align="left" height="22" >
            <input type="text" name="netUnitCode"  readonly size="40" class="input_style1" style="width:80%"></td>
        </tr>
        <tr>
        	<tr>
            <td width="25%" align="right" height="22">����רҵ1��&nbsp;</td>
            <td width="50%" align="left" height="22" >
            <input type="text" name="netCategory1" class="input_style1" readonly size="40"  style="width:80%">
            </td>
        </tr>
        <tr>
        	<tr>
            <td width="25%" align="right" height="22">����רҵ2��&nbsp;</td>
            <td width="50%" align="left" height="22">
              <input type="text" name="netCategory2" class="input_style1" readonly size="40"  style="width:80%">
            </td>
        </tr>
    </table>
    <p align = "center">
    	<img src="/images/eam_images/save.jpg" alt="����" onClick="do_Save(); return false;">&nbsp;&nbsp;
        <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="history.go(-1);">
    </p>
	<input type="hidden" name="act" value="">
</form>
</body>
</html>
<script>
function do_Save() {
		if(mainFrm.contentCode.value != ""){
			if(mainFrm.netUnitCode.value != ""){
				var action = "<%=WebActionConstant.CREATE_ACTION%>";
				mainFrm.act.value = action;
				mainFrm.action = "com.sino.ams.match.amselementmatch.servlet.AmsElementMatchServlet?accessType=lne";
				mainFrm.submit();
			}else{
				alert("��ѡ���Ӧ�߼�����Ԫ��!");
			}			
		}else{
			alert("��ѡ���Ӧ�ʲ�Ŀ¼!");
		}
}

function do_SelectLne(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_LNE %>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
	} else {
        mainFrm.logNetEle.value = "";
        mainFrm.lneId.value = "";
    }
}

function do_SelectContent() {
        var lookUpName = "<%=LookUpConstant.LOOK_UP_CONTENT_NOMATCH_LNE %>";
        var dialogWidth = 48;
        var dialogHeight = 30;
        var users = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (users) {
            var user = null;
            for (var i = 0; i < users.length; i++) {
                user = users[i];
                dto2Frm(user, "mainFrm");
            }
        } else {
            mainFrm.contentName.value = "";
            mainFrm.contentCode.value = "";
        }
}

</script>

