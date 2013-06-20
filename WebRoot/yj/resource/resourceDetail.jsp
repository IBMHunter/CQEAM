<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.sino.ams.yj.constant.YJWebAttribute" %>
<%@ page import="com.sino.ams.yj.constant.YjLookUpConstant" %>
<%@ page import="com.sino.ams.yj.resource.dto.AmsYjCommunicateResourceDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ include file="/newasset/headerInclude.htm" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>ս��Ӧ��ͨ����Դ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/yj/yjLookUp.js"></script>

</head>
<body>
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsYjCommunicateResourceDTO resourceDTO = (AmsYjCommunicateResourceDTO) request.getAttribute(YJWebAttribute.RESOURCE);
    if (resourceDTO == null) {
        resourceDTO = new AmsYjCommunicateResourceDTO();
    }
    String action = (String) request.getAttribute("act");
    String formAction = "/servlet/com.sino.ams.yj.resource.servlet.AmsYjCommunicateResourceServlet";
%>
<form name="mainFrm" method="post" action="<%=formAction%>">
<script type="text/javascript">
    printTitleBar("ս��Ӧ��ͨ����Դ��Ϣ");
</script>
<input type="hidden" name="act" value="">
<input type="hidden" name="resourceId" value="<%=resourceDTO.getResourceId()%>">
<br>
<table width="100%" height="100%">
<tr>
<td width="5%">&nbsp;</td>
<td width="90%" valign="top">
    <table border="1" width="100%" id="table1" align="center">
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">��˾���ƣ�</td>
            <td width="82%" align="left" height="22" nowrap><select name="organizationId" class="select_style1" style="width:100%">
                <%=resourceDTO.getOrgOpt()%>
            </select></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">��λ���ƣ�</td>
            <td width="82%" align="left" height="22" nowrap><input type="text" name="deptName" size="40" class="input_style1" style="width:100%"
                                                                   value="<%=resourceDTO.getDeptName()%>"></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">װ�����ƣ�</td>
            <td width="82%" align="left" height="22" nowrap>
                <select name="equipmentName" class="select_style1" style="width:100%"><%=resourceDTO.getEquipmentOpt()%>
                </select>
            </td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">������</td>
            <td width="82%" align="left" height="22" nowrap>
                <input type="text" name="resourceQty" class="input_style1" style="width:100%" value="<%=resourceDTO.getResourceQty()%>"></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">�÷�λ�ã�</td>
            <td width="82%" align="left" height="22" nowrap>
                <input type="text" name="location" value="<%=resourceDTO.getLocation()%>" class="input_style1" style="width:100%"></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">���</td>
            <td width="82%" align="left" height="22" nowrap>
                <input type="text" name="model" class="input_style1" style="width:100%" value="<%=resourceDTO.getModel()%>"></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">�������ܣ�</td>
            <td width="82%" align="left" height="22" nowrap>
                <select name="normalStatus" class="select_style1" style="width:100%">
                    <option value="��">��</option>
                    <option value="��" <%=resourceDTO.getNormalStatus().equals("��") ? "selected" : ""%>>��</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">���ܲ��ţ�</td>
            <td width="82%" align="left" height="22" nowrap>
                <input type="text" name="chargeDept" size="40" class="input_style1" style="width:100%" value="<%=resourceDTO.getChargeDept()%>"></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">�����ˣ�</td>
            <td width="82%" align="left" height="22" nowrap>
                <input type="text" name="charger" size="40" class="input_style1" style="width:100%" value="<%=resourceDTO.getCharger()%>"></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">�ֻ���</td>
            <td width="82%" align="left" height="22" nowrap>
                <input type="text" name="chargerMobile" value="<%=resourceDTO.getChargerMobile()%>" class="input_style1" style="width:100%"></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">�����ˣ�</td>
            <td width="82%" align="left" height="22" nowrap>
                <input type="text" name="keeper" value="<%=resourceDTO.getKeeper()%>" class="input_style1" style="width:100%"></td>
        </tr>
        <tr>
            <td width="18%" height="22" align="right" nowrap bgcolor="#00CCFF">�ֻ���</td>
            <td width="82%" align="left" height="22" nowrap>
                <input type="text" name="keeperMobile" class="input_style1" style="width:100%" value="<%=resourceDTO.getKeeperMobile()%>"></td>
        </tr>
        <tr>
            <td height="22" align="right" nowrap bgcolor="#00CCFF">��ע��</td>
            <td align="left" height="22" nowrap><input type="text" name="remark" value="<%=resourceDTO.getRemark()%>" class="input_style1" style="width:100%">
            </td>
        </tr>
        <tr>
            <td height="22" align="right" nowrap bgcolor="#00CCFF">����Ӧ��ͨ�ų���</td>
            <td align="left" height="22" nowrap><a
                    href="/servlet/com.sino.ams.yj.comvan.servlet.AmsYjComvanServlet?act=<%=WebActionConstant.QUERY_ACTION%>&resourceId=<%=resourceDTO.getResourceId()%>"
                    target="_blank"><%=resourceDTO.getComvan()%>
            </a></td>
        </tr>
        <tr>
            <td height="22" align="right" nowrap bgcolor="#00CCFF">�������ǵ绰��</td>
            <td align="left" height="22" nowrap><a
                    href="/servlet/com.sino.ams.yj.servlet.AmsYjIsatphoneServlet?act=<%=WebActionConstant.QUERY_ACTION%>&resourceId=<%=resourceDTO.getResourceId()%>"
                    target="_blank"><%=resourceDTO.getIsatphone()%>
            </a></td>
        </tr>
    </table>
    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td align="center" nowrap>
                <img src="/images/eam_images/save.jpg" alt="�������" onClick="do_save();">
                <img src="/images/eam_images/back.jpg" alt="����ر�" onClick="do_close();">
            </td>
        </tr>
    </table>
</td>
<td width="5%">&nbsp;</td>
</tr>
</table>
</form>
<%=WebConstant.WAIT_TIP_MSG%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>

<script>

    //ȡ��
    function do_close() {
        window.close();
    }

    //����
    function do_save() {
        var deptName = document.getElementsByName("deptName")[0];
        if(deptName.value==null || deptName.value=="" ){
          alert("�����뵥λ����!");
          deptName.focus();
          return false;
        }
          var resourceQty =  document.getElementsByName("resourceQty")[0];
          var reg = /^[0-9]+$/;   
	      if(resourceQty.value!=""&&!reg.test(resourceQty.value)){   
	        alert('"����" ���������֣�');   
	        resourceQty.value = "";   
	        resourceQty.focus();   
	        return false;   
	      }     
	      
        with (mainFrm) {
            if (confirm("ȷ�ϱ�����Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
                mainFrm.act.value = "<%=WebActionConstant.SAVE_ACTION%>";
                mainFrm.action = "<%=formAction%>";
                mainFrm.submit();
            }
        }
    }

    function choose_equipment() {
        var lookUpName = "<%=YjLookUpConstant.LOOK_UP_EQUIPMENT%>";
        var dialogWidth = 50.6;
        var dialogHeight = 30;
        var retValue = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
        if (retValue) {
            document.mainFrm.equipmentName.value = retValue[0].itemName;
        }
    }

    function view_comvan() {
        var url = "/servlet/com.sino.ams.yj.comvan.servlet.AmsYjComvanServlet?act=" + WebActionConstant.QUERY_ACTION + "&resourceId=" + document.mainFrm.recourceId.value;
        alert(url);
        windows.open(url, '', '');
    }

</script>