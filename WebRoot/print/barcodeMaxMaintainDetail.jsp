<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant"%>
<%@ page import="com.sino.ams.print.dto.BarcodeMaxMaintainDTO"%>
<%--
  Author: ����
  Date: 2009-4-29
  Time: 14:50:00
--%>
<html>
<head>
    <title>��ǩ�Ź�����</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
</head>
<script type="text/javascript">
    printTitleBar("��ǩ�Ź�����")
</script>

<body leftmargin="0" topmargin="0">
<%
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
	BarcodeMaxMaintainDTO bmmDTO = (BarcodeMaxMaintainDTO) request.getAttribute(WebAttrConstant.BARCODE_MAX_MAINTAIN_DTO);
%>
<form action="/servlet/com.sino.ams.print.servlet.BarcodeMaxMaintainServlet" method="post" name="mainFrm">
    <jsp:include page="/message/MessageProcess"/>
    <input type="hidden" name="act">
    <table name="mainFrm" width="81%" border="0" class="queryTable" align="center" >
        <tr id="loginName">        
            <td width="20%" align="right">��˾��ţ�</td>
            <td width="80%"><input type="text" name="companyId" class="input_style2" readonly style="width:80%"
                                   value="<%=bmmDTO.getCompanyId() %>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">��˾���ƣ�</td>
            <td width="80%"><input type="text" name="companyName" class="input_style2" readonly style="width:80%"
                                   value="<%=bmmDTO.getCompanyName() %>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">�����ñ�ǩ�ţ�</td>
            <td width="80%"><input type="text" name = "completeBarcode" size = "40"  class="input_style2" readonly  style = "width:80%"
                                   value="<%=bmmDTO.getCompleteBarcode() %>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">�޸ı�ǩ�ţ�</td>
            <td width="80%"><input type="text" name = "tagSeq" size = "40" class="input_style1" style = "width:80%"
                                   value="<%=bmmDTO.getTagSeq() %>"><font color="red">*</font>
            </td>
        </tr>
        <tr>
            <td width="20%" align="right">��ǩ���ͣ�</td>
            <td width="80%"><input type="text" name = "assetsType" size = "40"  class="input_style2" readonly  style = "width:80%"
                                   value="<%=bmmDTO.getAssetsType() %>">
            </td>
        </tr>
     </table>
    <p align="center">
    	<img src="/images/button/modify.gif" alt="�޸�" onClick="do_Update(); return false;">&nbsp;&nbsp;
        <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="do_Back(); return false;">
   	</p>
</form>
</body>
</html>
<script type="text/javascript">
    function do_Update() {
    	var updateTagSeq = mainFrm.tagSeq.value;
    	if(updateTagSeq != ""){
    		var tagSeq = <%=bmmDTO.getTagSeq()  %>		
			if(parseFloat(updateTagSeq) > parseFloat(tagSeq)){
				var tagType = mainFrm.tagSeq.value
				if("<%=bmmDTO.getAssetsType() %>" == "ZY" || "<%=bmmDTO.getAssetsType() %>" == "TD"){
					if(tagType.length != 8){
						alert('��ǩ�ű�����8λ!');
						return false;
					}
				}else{
					if(tagType.length > 6){
						alert('��ǩ�ű���С�ڵ���6λ!');
						return false;
					}
				}
				var companyId = mainFrm.companyId.value;		
				var assetsType = mainFrm.assetsType.value;	
				mainFrm.action = "/servlet/com.sino.ams.print.servlet.BarcodeMaxMaintainServlet";
				mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION %>";
				mainFrm.action.value += "&companyId=" + companyId + "&tagSeq=" + tagSeq + "&updateTagSeq=" +updateTagSeq + "&assetsType=" + assetsType
				mainFrm.submit();
			}else{
				alert("�޸ĺ�ı�ǩ�ű������ԭ�ȱ�ǩ��!");
				return;
			}
    	}else{
    		alert("��ǩ�Ų���Ϊ��!");    		    		
    	}
	}
    
    
    function do_Back() {
	    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
		mainFrm.action = "/servlet/com.sino.ams.print.servlet.BarcodeMaxMaintainServlet";
		mainFrm.submit();
	}
</script>
