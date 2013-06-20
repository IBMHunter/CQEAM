<%@ page import="com.sino.ams.yj.constant.YjLookUpConstant" %>
<%@ page import="com.sino.ams.yj.dto.AmsYjDrillDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.htm" %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<HTML>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>Ӧ�����������ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>

<body>
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
<%
    AmsYjDrillDTO amsYjDrillDTO = (AmsYjDrillDTO) request.getAttribute("AMS_YJ_DRILL");
    boolean isNew = StrUtil.isEmpty(amsYjDrillDTO.getDrillName());
%>
<script type="text/javascript">
    printTitleBar("Ӧ�����������ϸ��Ϣ");
</script>

<form name="mainFrm" action="/servlet/com.sino.ams.yj.servlet.AmsYjDrillServlet" method="post">
<input type="hidden" name="show" value="show">
<input type="hidden" name="act" value="">
<input type="hidden" name="drillId" value="<%=amsYjDrillDTO.getDrillId()%>">
<input type="hidden" name="isExist">
<table border="0" width="100%" id="table1">
    <tr>
        <td width="6%" colspan="3" align="right">��˾���ƣ�</td>
        <td width="11%"><select class="select_style1" style="width:50%" name="organizationId"><%=amsYjDrillDTO.getOrganizationOption()%>
        </select></td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">�������ƣ�</td>
        <td width="35%" align="left" height="22" ><input class="input_style1" type="text" name="drillName" size="40"
                                                        style="width:50%" value="<%=amsYjDrillDTO.getDrillName()%>">&nbsp;<font color="red">*</font> </td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">�������ͣ�</td>
        <td width="35%" align="left" height="22">
            <select name="drillType" class="select_style1" style="width:50%">
                <option value="����ҵ��-��Ȼ���" <%=amsYjDrillDTO.getDrillType().equals("����ҵ��-��Ȼ���") ? "selected" : ""%>>����ҵ��-��Ȼ���</option>
                <option value="�����¼�-ר��Ԥ��" <%=amsYjDrillDTO.getDrillType().equals("�����¼�-ר��Ԥ��") ? "selected" : ""%>>�����¼�-ר��Ԥ��</option>
                <option value="�����豸-Ӧ��������" <%=amsYjDrillDTO.getDrillType().equals("�����豸-Ӧ��������") ? "selected" : ""%>>�����豸-Ӧ��������</option>
            </select>
        </td>

    </tr>

    <tr>
        <td width="6%" colspan="3" align="right">�������ʣ�</td>
        <td width="35%" align="left" height="22">
            <select name="drillNature" class="select_style1" style="width:50%">
                <option value="��׼��" <%=amsYjDrillDTO.getDrillNature().equals("��׼��") ? "selected" : ""%>>��׼��</option>
                <option value="��׼��" <%=amsYjDrillDTO.getDrillNature().equals("��׼��") ? "selected" : ""%>>��׼��</option>
            </select>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">����ʱ�䣺</td>
        <td width="35%" align="left" height="22">
            <input class="input_style1" readonly name="drillDate" style="width:50%"
                   value="<%=StrUtil.nullToString(amsYjDrillDTO.getDrillDate())%>"><img
                src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fPopCalendar(drillDate)">
        </td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">�����ص㣺</td>
        <td width="35%" align="left" height="22"><input type="text" name="drillAddress" size="40"
                                                       class="input_style1" style="width:50%" value="<%=amsYjDrillDTO.getDrillAddress()%>"></td>
    </tr>

    <tr>
        <td width="6%" colspan="3" align="right">����������</td>
        <td width="35%" align="left" height="22"><input type="text" name="peopleQuality" size="40"
                                                       class="input_style1" style="width:50%" value="<%=amsYjDrillDTO.getPeopleQuality()%>"></td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">����װ��������</td>
        <td width="35%" align="left" height="22"><input type="text" name="equipmentQuantity" size="40"
                                                       class="input_style1" style="width:50%" value="<%=amsYjDrillDTO.getEquipmentQuantity()%>"></td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">����Ԥ����</td>
        <td width="35%" align="left" height="22"><input type="text" name="plan1" size="40"
                                                       class="input_style1" style="width:50%" value="<%=amsYjDrillDTO.getPlan1()%>"></td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">�����������⣺</td>
        <td width="35%" align="left" height="22"><input type="text" name="question" size="40"
                                                       class="input_style1" style="width:50%" value="<%=amsYjDrillDTO.getQuestion()%>"></td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">�Ƿ���Ҫ����Ԥ����</td>
        <td width="35%" align="left" height="22">
        <select name="isPerfect" class="select_style1" style="width:50%">
            <option value="��" <%=amsYjDrillDTO.getIsPerfect().equals("��") ? "selected" : ""%>>��</option>
            <option value="��" <%=amsYjDrillDTO.getIsPerfect().equals("��") ? "selected" : ""%>>��</option>
        </select>
        </td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">����Ԥ���ƻ�ʱ�䣺</td>
        <td width="35%" align="left" height="22">
            <input class="input_style1" readonly name="planDate" style="width:50%" value="<%=StrUtil.nullToString(amsYjDrillDTO.getPlanDate())%>"><img
                src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fPopCalendar(planDate)">

        </td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right">��ע��</td>
        <td width="35%" align="left" height="22"><input type="text" name="remark" size="40" class="input_style1"
                                                        style="width:50%" value="<%=amsYjDrillDTO.getRemark()%>"></td>
    </tr>
    <tr>
        <td width="6%" colspan="3" align="right"></td>
        <td width="35%" align="left" height="22">
            <img src="/images/eam_images/save.jpg" alt="�������" onClick="do_Save(); return false;">&nbsp;
            <%
                if (!amsYjDrillDTO.getDrillName().equals("")) {
            %>
            <img src="/images/eam_images/delete.jpg" alt="���ɾ��" onClick="do_Delete(); return false;">&nbsp;
            <%
                }
            %>
            <img src="/images/eam_images/back.jpg" alt="���ȡ��" onClick="do_Back(); return false;"></td>
    </tr>

</table>
</form>
</body>
</html>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script>
    function do_Save() {
       var drillName = document.getElementsByName("drillName")[0]; 
       var peopleQuality =document.getElementsByName("peopleQuality")[0];
       var equipmentQuantity =document.getElementsByName("equipmentQuantity")[0];
        if(drillName.value==null || drillName.value==""){
          alert("�������Ʋ���Ϊ�գ�");
          drillName.focus();
          return false;
        
        }
           var reg = /^[0-9]+$/;   
	      if(peopleQuality.value!=""&&!reg.test(peopleQuality.value)){   
	        alert('"��������" ���������֣�');   
	        peopleQuality.value = "";   
	        peopleQuality.focus();   
	        return false;   
	      }  
    	  if(equipmentQuantity.value!=""&&!reg.test(equipmentQuantity.value)){   
	        alert('"����װ������" ���������֣�');   
	        equipmentQuantity.value = "";   
	        equipmentQuantity.focus();   
	        return false;   
	      }  
       
    
        var fieldNames = "drillName";
        var fieldLabels = "��������";
        var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);

//        do_verifyItemName();       �ж����������Ƿ��ظ���������Ҫ��֤��ֻ��Ҫ���ø÷�����������ʵ������޸�

        if (isValid) {
            if (mainFrm.isExist.value == "Y") {
                alert("�����������Ѵ��ڣ�");
                return;
            }
            var action = "<%=WebActionConstant.CREATE_ACTION%>";
        <%if(isNew){%>
            document.mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";
        <%}else{%>
            document.mainFrm.act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
        <%}%>
            document.mainFrm.submit();
        }
    }

    function do_Delete() {
        var drillId = document.mainFrm.drillId.value;
        if (confirm("ȷ��ɾ����������Ϣ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
            document.mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
            document.mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjDrillServlet?drillId=" + drillId;
            document.mainFrm.submit();
        }
    }


    function do_Back() {
        document.mainFrm.drillId.value = "";
        document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.mainFrm.action = "/servlet/com.sino.ams.yj.servlet.AmsYjDrillServlet";
        document.mainFrm.submit();
    }

    var xmlHttp;
    function do_verifyItemName() {
        var url = "";
        createXMLHttpRequest();
        url = "/servlet/com.sino.ams.yj.servlet.AmsYjDrillServlet?act=verifyTeamName&drillId=" + document.mainFrm.drillId.value;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, false);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }

    function createXMLHttpRequest() {//����XMLHttpRequest����
        try {
            xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
        } catch(e) {
            try {
                xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
            } catch(e) {
                try {
                    xmlHttp = new XMLHttpRequest();
                } catch(e) {
                    alert("����XMLHttpRequest����ʧ�ܣ�");
                }
            }
        }
    }

    function handleReadyStateChange1() {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                if (xmlHttp.responseText == 'Y') {
                    document.mainFrm.isExist.value = 'Y';
                } else {
                    document.mainFrm.isExist.value = 'N';
                }
            } else {
                alert(xmlHttp.status);
            }
        }
    }


</SCRIPT>