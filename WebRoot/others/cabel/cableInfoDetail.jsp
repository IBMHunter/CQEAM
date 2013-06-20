<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.others.cabel.dto.AmsCabelInfoDTO" %>
<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>��ά��˾��Ա��ϸ��Ϣ</title>
</head>
<body>
<%
    AmsCabelInfoDTO amsCabelInfoDTO = (AmsCabelInfoDTO) request.getAttribute(WebAttrConstant.CABEL_INFO_DTO);
    RequestParser parser = new RequestParser();
    parser.transData(request);
%>
<form name="mainFrm" method="POST">
<script type="text/javascript">
    printTitleBar("�����ʲ�ά����ϸ");
</script>
<jsp:include page="/message/MessageProcess"/>
<table border="0" width="100%" id="table1">
<input type="hidden" name="act" value="<%=parser.getParameter("act")%>">
<tr>
    <td width="25%" align="right" height="22">���룺</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" name="barcode" size="40"   readonly
                                                                class="input_style2" style="width:100%"
                                                                value="<%=amsCabelInfoDTO.getBarcode() %>">
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>

<tr>
    <td width="25%" align="right" height="22">���ࣺ</td>
    <td width="50%" align="left" height="22" colspan="3"><input  type="text" name="itemCategory" size="40"
                                                                 readonly
                                                                style="width:100%" class="input_style2"
                                                                value="<%=amsCabelInfoDTO.getItemCategory()%>">
    </td>
    <td width="25%" align="left" height="22">
        <input type="hidden" name="itemCode" value="<%=amsCabelInfoDTO.getItemCode() %>">
        <a href="#" <%--onClick="do_SelectCabelType();"--%> title="���ѡ�������ͺ�" class="linka"></a></td>
</tr>
<tr>
    <td width="25%" align="right" height="22">���ƣ�</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" name="itemName"
                                                                size="40"   readonly
                                                                class="input_style2" style="width:100%"
                                                                value="<%= amsCabelInfoDTO.getItemName()%>"></td>
    <td width="25%" align="left" height="22"></td>
</tr>
<tr>
    <td width="25%" align="right" height="22">�ͺţ�</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" readonly name="itemSpec" size="40"
                                                                style="width:100%"   class="input_style2"
                                                                value="<%= amsCabelInfoDTO.getItemSpec()%>">
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>
<%--<tr>
    <td width="25%" align="right" height="22">�����أ�</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" name="fromAddress" size="40"
                                                                style="width:100%" readonly class="readonlyInput"
                                                                value="<%= amsCabelInfoDTO.getFromAddress()%>">
    </td>
    <td width="25%" align="left" height="22">
        <input type="hidden" name="itemCode" value="<%=amsCabelInfoDTO.getItemCode() %>">
        <a href="#" onClick="do_SelectCabelType();" title="���ѡ�������ͺ�" class="linka">[��]</a></td>
</tr>--%>
<tr>
    <td width="25%" align="right" height="22">ʼ�أ�</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" name="fromAddress" size="40"
                                                                style="width:100%"
                                                                value="<%= amsCabelInfoDTO.getFromAddress()%>">
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>
<tr>
    <td width="25%" align="right" height="22">ֹ�أ�</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" name="toAddress" size="40"
                                                                style="width:100%" class="input_style1"
                                                                value="<%= amsCabelInfoDTO.getToAddress()%>">
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>
<tr>
    <td width="25%" align="right" height="22">ʼ��γ��</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" name="fromTude" size="40"
                                                                style="width:100%" class="input_style1"
                                                                value="<%= amsCabelInfoDTO.getFromTude()%>">
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>
<tr>
    <td width="25%" align="right" height="22">ֹ��γ��</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" name="toTude" size="40"
                                                                style="width:100%" class="input_style1"
                                                                value="<%= amsCabelInfoDTO.getToTude()%>">
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>
<tr>
    <td width="25%" align="right" height="22">��ʽ��</td>
    <td width="50%" align="left" height="22" colspan="3">
        <select name="spreadType" class="select_style1"
                style="width:100%"><%=request.getAttribute(WebAttrConstant.SPREAD_TYPE_OPTION)%></select>
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>
<tr>
    <td width="25%" align="right" height="22">��;��</td>
    <td width="50%" align="left" height="22" colspan="3">
        <select name="cabelUsage" class="select_style1"
                style="width:100%"><%=request.getAttribute(WebAttrConstant.CABEL_USAGE_OPTION)%></select>
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>

<tr>
    <td width="25%" align="right" height="22">���</td>
    <td width="50%" align="left" height="22" colspan="3"><input type="text" name="cabelDepth" size="40"
                                                                style="width:100%" class="input_style1"
                                                                value="<%= amsCabelInfoDTO.getCabelDepth()%>">
    </td>
    <td width="25%" align="left" height="22"></td>
</tr>

<tr>
    <td width="100%" align="center" height="22" colspan="5">
        <img src="/images/eam_images/save.jpg" alt="����" onClick="do_SaveMaintainCompany(); return false;">&nbsp;&nbsp;
        <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="do_Back(); return false;"></td>
</tr>

</table>


</form>
</body>
</html>
<script>
    function do_SaveMaintainCompany() {
        //����У�� -- �ǿ�
        var fieldNames = "barcode;itemCategory;itemName";
        var fieldLabels = "����;�豸����;�豸����";
        var validateType = EMPTY_VALIDATE;
        var isValid = formValidate(fieldNames, fieldLabels, validateType);
        if (isValid) {
            var action = "<%=WebActionConstant.CREATE_ACTION%>";
            mainFrm.act.value = action;
            mainFrm.action = "<%= URLDefineList.CABEL_INFO_SERVLET%>";
            mainFrm.submit();
        }
    }

    function do_Back() {
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "<%= URLDefineList.CABEL_INFO_SERVLET%>";
        mainFrm.submit();
    }

    function do_SelectCabelType() {

        var lookUpName = "<%=LookUpConstant.LOOK_UP_CABEL%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        var lookupValues = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (lookupValues) {
            var lookupValue = null;
            for (var i = 0; i < lookupValues.length; i++) {
                lookupValue = lookupValues[i];
                dto2Frm(lookupValue, "mainFrm");
            }
        }

    }
</script>