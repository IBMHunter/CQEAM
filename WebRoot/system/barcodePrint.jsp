<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@page import="com.sino.ams.constant.WebAttrConstant"%>

<%
String allResName = (String) request.getAttribute( WebAttrConstant.ALL_RES_NAME );
%>
<%--
  Created by HERRY.
  Date: 2008-3-30
  Time: 16:54:00
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>�����±�ǩ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
     <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
</head>
<body>
<script type="text/javascript">
    printTitleBar( "<%= allResName %>" );
</script>
<%=WebConstant.WAIT_TIP_MSG%>
<%
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    String showMsg = StrUtil.nullToString(request.getAttribute("showMsg"));
%>
<form action="/servlet/com.sino.ams.util.BarcodePrintServlet" name="mainForm" method="post">
    <input type="hidden" name="act">
    <input type="hidden" name="companyCode" value="<%=user.getCompanyCode()%>">
    <table width="60%" align="center" bordercolor="#9FD6FF" border="1">
        <tr height="22">
            <td width="40%" align="right">��˾���ƣ�</td>
            <td><%=user.getCompany()%>
            </td>
        </tr>
        <tr height="22">
            <td align="right">��˾�˲����룺</td>
            <td><%=user.getBookTypeCode()%></td>
        </tr>
        <tr height="22">
            <td align="right">ʵ�����ͣ�</td>
            <td>
                <select name="assetType" style="width:40%;border-style:none">
                    <option value="">�����ʲ�</option>
<%
				if ("Y".equals(user.getIsTd()) && !"Y".equals(user.getIsTt())) { 
%>
                    <option value="TD">TD�̶��ʲ�-�հ�</option>
                    <option value="LS">TD�̶��ʲ�-��ʱ</option>
<%
				} else if ("Y".equals(user.getIsTt())) {
%>
                    <option value="TT">��ͨ�������ʲ�-�հ�</option>
                    <option value="LS">��ͨ�������ʲ�-��ʱ</option>
<%					
				} else {
%>
                    <option value="MIS">�̶��ʲ�-�հ�</option>
                    <option value="LS">�̶��ʲ�-��ʱ</option>
                    <option value="ZL">�����ʲ�</option>
                    <option value="DH">��Ҫ��ֵ�׺�Ʒ</option>
                    <option value="TF">ͨ���ʲ�</option>
                    <option value="BJ">��Ʒ����</option>
<%
				}
%>
                </select>
            </td>
        </tr>
        <tr height="22">
            <td align="right">����������</td>
            <td><input type="text"  style="width:40%" name="quantity" value="" class="blueborderYellow" onblur="do_Check(this);"><font color="red">*</font></td>
        </tr>
        <tr height="22">
            <td colspan="2" align="center"><a href="#" class="linka" onclick="doBuild();">����</a></td>
        </tr>
    </table>
    <%=showMsg%>
</form>
</body>
<script type="text/javascript">
    function doBuild() {
        var quantity = document.mainForm.quantity.value ;
        if (quantity == "") {
            alert("����д��Ҫ��ӡ�ı�ǩ������");
            return;
        }
        if(quantity>2000){
            alert("Ϊ�����ӡ��ȷ��������2000֮�ڣ���");
            return;
        }
        document.mainForm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        document.mainForm.submit();
    }

    function do_Check(object) {
    if (object.value != "") {
        if (isNaN(object.value)) {
            alert("������Ϸ����֣�");
            object.value = "";
			object.focus();
        } else {
            if(object.value<=0){
                alert("������������");
                object.value = "";
                object.focus();
                return;
            }     			
		}
	}
}
</script>
</html>