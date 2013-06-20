<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.LookUpConstant"%>
<%@ page import="com.sino.ams.yj.ensure.dto.AmsYjCommunicateEnsureDTO"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ include file="/newasset/headerInclude.htm" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>Ӧ��ͨ�ű������</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/LookUp.js"></script>

</head>
<body >
<jsp:include page="/servlet/com.sino.framework.servlet.MessageProcessServlet" flush="true"/>
		<%
            AmsYjCommunicateEnsureDTO ensureDTO=(AmsYjCommunicateEnsureDTO)request.getAttribute("ENSURE");
            if(ensureDTO==null){
                ensureDTO=new AmsYjCommunicateEnsureDTO();
            }
            String action=(String)request.getAttribute("act");
            String formAction="/servlet/com.sino.ams.yj.ensure.servlet.AmsYjCommunicateEnsureServlet";
		%>
    <form name="mainFrm" method="post" action="<%=formAction%>">
			<script type="text/javascript">
    			printTitleBar("Ӧ��ͨ�ű������");
			</script>
			<input type="hidden" name="act" value="">
			<input type="hidden" name="communicateId" value="<%=ensureDTO.getCommunicateId()%>">
            <br>
        <table width="100%" height="100%">
            <tr>
                <td width="5%">&nbsp;</td>
                <td width="90%" valign="top">
			<table border="1" width="100%" id="table1" align="center">
		        <tr>
		          <td width="12%" align="right" nowrap bgcolor="#003366">&nbsp;</td>
		        	<td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">��˾��</td>
		            <td width="35%" align="left" height="22" nowrap>
                        <select name="organizationId" class="select_style1" style="width:100%"> <%=ensureDTO.getOrgOpt()%></select>	</td>
		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">&nbsp;</td>
		            <td width="35%" align="left" height="22" nowrap>&nbsp;</td>
	            </tr>
		        <tr>
		          <td width="12%" align="center" nowrap bgcolor="#003366"><p>&nbsp;</p>	              </td>

		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">��λ��</td>
		           	<td width="35%" align="left" height="22" nowrap>
		           		<input type="text" name="deptName" value="<%=ensureDTO.getDeptName()%>" size="40" class="input_style1"  style="width:100%">       	</td>

		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">ͨ�ű������ƣ�</td>
		           	<td width="35%" align="left" height="22" nowrap>
		           		<input class="input_style1"  type="text" name="ensureName" size="40" style="width:100%" value="<%=ensureDTO.getEnsureName()%>" >  	</td>
           	   </tr>
		        <tr>
		          <td width="12%" align="center" nowrap bgcolor="#003366">&nbsp;</td>
		          <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">�¼����ͣ�</td>
		            <td width="35%" align="left" height="22" nowrap>
                        <select name="eventType" class="select_style1" style="width:100%">
                            <option value="���ξ����¼���" <%=ensureDTO.getEventType().equals("���ξ����¼���")?"selected":""%>>���ξ����¼���</option>
                            <option value="�ڼ��ձ�����" <%=ensureDTO.getEventType().equals("�ڼ��ձ�����")?"selected":""%>>�ڼ��ձ�����</option>
                            <option value="��Ȼ�ֺ���" <%=ensureDTO.getEventType().equals("��Ȼ�ֺ���")?"selected":""%>>��Ȼ�ֺ���</option>
                            <option value="�¹�������" <%=ensureDTO.getEventType().equals("�¹�������")?"selected":""%>>�¹�������</option>
                            <option value="���������¼���" <%=ensureDTO.getEventType().equals("���������¼���")?"selected":""%>>���������¼���</option>
                            <option value="��ᰲȫ�¹���" <%=ensureDTO.getEventType().equals("��ᰲȫ�¹���")?"selected":""%>>��ᰲȫ�¹���</option>
                        </select>
                    </td>

		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">���ϵص㣺</td>
		           	<td width="35%" align="left" height="22" nowrap>
		           		<input class="input_style1"  type="text" name="ensureLocation" size="40" style="width:100%" value="<%=ensureDTO.getEnsureLocation()%>">		           	</td>
	           	</tr>
		        <tr>
		          <td width="12%" align="center" nowrap bgcolor="#003366">&nbsp;</td>
		          <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">����ʱ��ӣ�</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input  type="text" name="ensureDateFrom" style="width:100%" value="<%=ensureDTO.getEnsureDateFrom()%>" style="width:100%;cursor:hand" title="���ѡ��ʼ����" readonly
                     class="input_style1"   onclick="gfPop.fStartPop(ensureDateFrom, ensureDateTo)"></td>

					<td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">����ʱ�䵽��</td>
		           	<td width="35%" align="left" height="22" nowrap>
		           		<input type="text" name="ensureDateTo" size="40" style="width:100%" value="<%=ensureDTO.getEnsureDateTo()%>" style="cursor:hand;width:100%" title="���ѡ���������" readonly
                      class="input_style1"  onclick="gfPop.fEndPop(ensureDateFrom, ensureDateTo)">		            	</td>
	           	</tr>
		        <tr>
		          <td width="12%" align="center" nowrap bgcolor="#003366">����Ͷ��</td>
		          <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">Ͷ��������</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input class="input_style1"  type="text" name="manpowerQty"  value="<%=ensureDTO.getManpowerQty()%>"  style="width:100%"></td>

		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">Ͷ���˴Σ�</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input class="input_style1"  type="text" name="manpowerTimes" value="<%=ensureDTO.getManpowerTimes()%>" style="width:100%"></td>
	            </tr>
		        <tr>
		          <td width="12%" align="center" nowrap bgcolor="#003366">Ӧ����Ͷ��</td>
		          <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">Ӧ����������</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input class="input_style1"  type="text" name="comvanQty" style="width:100%" value="<%=ensureDTO.getComvanQty()%>">							            </td>

		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">Ӧ�������Σ�</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input class="input_style1"  name="comvanTimes" style="width:100%" value="<%=ensureDTO.getComvanTimes()%>"></td>
	            </tr>
		        <tr>
		          <td width="12%" align="center" nowrap bgcolor="#003366">����Ӧ��ͨ���豸Ͷ��</td>
		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">Ӧ��ͨ���豸����</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input type="text" name="equipmentQty" size="40" class="input_style1"  style="width:100%" value="<%=ensureDTO.getEquipmentQty()%>">		            </td>
		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">Ӧ��ͨ���豸�״Σ�</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input type="text" name="equipmentUnit" size="40" class="input_style1"  style="width:100%" value="<%=ensureDTO.getEquipmentUnit()%>">		                   </td>
	            </tr>
		        <tr>
		          <td width="12%" rowspan="4" align="center" nowrap bgcolor="#003366">ͨ����ʧ���������</td>
		          <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">ͨ����ϳ̶ȣ�</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input type="text" name="blockDegree" size="40" class="input_style1"  style="width:100%" value="<%=ensureDTO.getBlockDegree()%>">		            </td>

		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">��ʧ�����</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input type="text" name="lossCondition" style="width:100%" class="input_style1"  value="<%=ensureDTO.getLossCondition()%>">						</td>
	            </tr>
		        <tr>
		          <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">Ӧ�����ϴ�ʩ��</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input type="text" name="ensureMeasure"  class="input_style1"  value="<%=ensureDTO.getEnsureMeasure()%>"  style="width:100%">                		 </td>

		            <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">ͨ�Żָ������ʱ�䣺</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input type="text" name="recoverSituation" class="input_style1"  value="<%=ensureDTO.getRecoverSituation()%>" style="width:100%"> </td>
	            </tr>
		        <tr>
		          <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">�ط������������ۣ�</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input type="text" name="governmentEvaluate" class="input_style1"  value="<%=ensureDTO.getGovernmentEvaluate()%>"  style="width:100%">                    </td>
					<td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">�¼�ԭ��Ӱ�췶Χ��</td>
		           	<td width="35%" align="left" height="22" nowrap>
		           		<input type="text" name="reasonAffect" value="<%=ensureDTO.getReasonAffect()%>" class="input_style1"  style="width:100%" >		           	</td>
	           	</tr>
		        <tr>
		          <td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">���ڵ����⣺</td>
		            <td width="35%" align="left" height="22" nowrap>
		            	<input type="text" name="question" style="width:100%" class="input_style1"  value="<%=ensureDTO.getQuestion()%>">								            </td>

					<td width="12%" height="22" align="right" nowrap bgcolor="#00CCFF">δ��������ʩ��</td>
		           	<td width="35%" align="left" height="22" nowrap>
		           		<input type="text" name="guardMeasure" value="<%=ensureDTO.getGuardMeasure()%>" class="input_style1"  style="width:100%" >		           	</td>
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
            </td><td width="5%">&nbsp;</td></tr></table>
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
         var ensureName = document.getElementsByName("ensureName")[0];
        if(deptName.value==null || deptName.value=="" ){
           alert("��λ���Ʋ���Ϊ�գ�");
           deptName.focus();
           return false;
        }
        if(ensureName.value==null || ensureName.value=="" ){
           alert("ͨ�ű������Ʋ���Ϊ��!");
           ensureName.focus();
           return false;
        }
        var manpowerQty = document.getElementsByName("manpowerQty")[0];
        var manpowerTimes = document.getElementsByName("manpowerTimes")[0];
        var comvanQty = document.getElementsByName("comvanQty")[0];
        var comvanTimes = document.getElementsByName("comvanTimes")[0];
        var equipmentQty = document.getElementsByName("equipmentQty")[0];
        var reg = /^[0-9]+$/;   
	      if(manpowerQty.value!=""&&!reg.test(manpowerQty.value)){   
	        alert('"Ͷ������" ���������֣�');   
	        manpowerQty.value = "";   
	        manpowerQty.focus();   
	        return false;   
	      }     
	      if(manpowerTimes.value!=""&&!reg.test(manpowerTimes.value)){   
	        alert('"Ͷ���˴�" ���������֣�');   
	        manpowerTimes.value = "";   
	        manpowerTimes.focus();   
	        return false;   
	      }   
	      if(comvanQty.value!=""&&!reg.test(comvanQty.value)){   
	        alert('"Ӧ��������" ���������֣�');   
	        comvanQty.value = "";   
	        comvanQty.focus();   
	        return false;   
	      }   
	      if(comvanTimes.value!=""&&!reg.test(comvanTimes.value)){   
	        alert('"Ӧ��������" ���������֣�');   
	        comvanTimes.value = "";   
	        comvanTimes.focus();   
	        return false;   
	      }   
	      if(equipmentQty.value!=""&&!reg.test(equipmentQty.value)){   
	        alert('"Ӧ��ͨ���豸��" ���������֣�');   
	        equipmentQty.value = "";   
	        equipmentQty.focus();   
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



</script>