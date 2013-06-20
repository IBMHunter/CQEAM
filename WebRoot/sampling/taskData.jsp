<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/sampling/headerInclude.jsp"%>
<%@ include file="/sampling/headerInclude.htm"%>
<html>
<head>
<title>���������ϸ��Ϣ</title>
</head>
<body topmargin="0" leftmargin="0" onload="window.focus()" onKeyUp="autoExeFunction('do_PublishTask()')">
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>

<%
	AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String taskId = dto.getTaskId();
	String taskStatus = dto.getTaskStatus();
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	String titalTaskNo = dto.getTaskNo();
	String pageTitle = "�������" + titalTaskNo + "��ϸ��Ϣ";
	if(titalTaskNo.equals(SamplingWebAttributes.ORDER_AUTO_PROD)){
		pageTitle = "�½��������";
	}
	int createdOu = dto.getCreatedOu();
	int userOu = userAccount.getOrganizationId();
	boolean canEdit = (userOu ==createdOu);
%>
<form name="mainFrm" action="<%=SamplingURLs.TASK_SERVLET%>" method="post">
<script>
    printTitleBar("<%=pageTitle%>")
</script>
	<table border="1" bordercolor="#226E9B" class="detailHeader" width="100%" style="border-collapse: collapse" id="table1">
		<tr height="22">
			<td width="15%" align="right">�����ţ�</td>
			<td width="35%"><input class="input_style2" type="text" name="taskNo" style="width:95%" readonly value="<%=dto.getTaskNo()%>"></td>
			<td width="15%" align="right">�������ƣ�</td>
			<td width="35%"><input class="input_style1" type="text" name="taskName" style="width:95%" value="<%=dto.getTaskName()%>"><font color="red">*</font></td>
		</tr>
		<tr height="22">
			<td width="15%" align="right">��ʼ���ڣ�</td>
			<td width="35%"><input class="blueborderYellow" type="text" style="width:95%;cursor:hand" name="startDate" value="<%=dto.getStartDate()%>" title="���ѡ��ʼ����" readonly onclick="gfPop.fStartPop(startDate, endDate)"><font color="red">*</font></td>
			<td width="15%" align="right">��ֹ���ڣ�</td>
			<td width="35%"><input class="blueborderYellow" type="text" style="width:95%;cursor:hand" name="endDate" value="<%=dto.getEndDate()%>" title="���ѡ���ֹ����" readonly onclick="gfPop.fEndPop(startDate, endDate)"><font color="red">*</font></td>
		</tr>
        <%--<tr height="22">--%>
			<%--<td width="15%" align="right">�ʲ��������ڣ�</td>--%>
			<%--<td width="35%"><input class="input_style2" type="text" style="width:95%;cursor:hand" name="assetsCreationDate" readonly value="<%=dto.getAssetsCreationDate()%>" title="���ѡ���ʲ���������" onclick="gfPop.fStartPop(assetsCreationDate, assetsEndDate)"><font color="red">*</font></td>--%>
			<%--<td width="15%" align="right">�ʲ�������ֹ���ڣ�</td>--%>
			<%--<td width="35%"><input class="input_style2" type="text" style="width:95%;cursor:hand" name="assetsEndDate" readonly value="<%=dto.getAssetsEndDate()%>" title="���ѡ���ʲ�������ֹ����" onclick="gfPop.fEndPop(assetsCreationDate, assetsEndDate)"><font color="red">*</font></td>--%>
		<%--</tr>--%>
		<tr height="22">
			<td width="15%" align="right">������˾��</td>
			<td width="35%"><input class="input_style2" name="createdOuName" style="width:95%" readonly value="<%=dto.getCreatedOuName()%>"></td>
			<td width="15%" align="right">������</td>
			<td width="35%"><input class="input_style2" type="text" style="width:95%" name = "samplingType" value="���ʲ�����" class="readonlyInput" readonly></td>
		</tr>
		<tr height="22">
			<td width="15%" align="right">���ٷֱȣ�</td>
			<td width="35%">
			<input type="text" class="input_style1" name="samplingRatio" style="width:93%" value="<%=dto.getSamplingRatio()%>"><font color="red">*</font></td>
			<td width="15%" align="right">����״̬��</td>
			<td width="35%">
			<input type="text" class="input_style2" name="taskStatusValue" style="width:95%" readonly value="<%=dto.getTaskStatusValue()%>"></td>
		</tr>
		<tr height="22">
			<td width="15%" align="right">�ر����ڣ�</td>
			<td width="35%">
			<input type="text" name="closedDate" style="width:95%" class="input_style2" readonly value="<%=dto.getClosedDate()%>"></td>
			<td width="15%" align="right">�ر��ˣ�</td>
			<td width="35%">
			<input type="text" name="closedUser" style="width:95%" class="input_style2" readonly value="<%=dto.getClosedUser()%>"></td>
		</tr>
		<tr height="22">
			<td width="15%" align="right">�����ڣ�</td>
			<td width="35%">
			<input type="text" name="openedDate" style="width:95%" class="input_style2" readonly value="<%=dto.getOpenedDate()%>"></td>
			<td width="15%" align="right">���ˣ�</td>
			<td width="35%">
			<input type="text" name="openedUser" style="width:95%" class="input_style2" readonly value="<%=dto.getOpenedUser()%>"></td>
		</tr>
		<tr height="22">
			<td width="15%" align="right">�������ڣ�</td>
			<td width="35%"><input type="text" name="creationDate" style="width:95%" class="input_style2" readonly value="<%=dto.getCreationDate()%>"></td>
			<td width="15%" align="right">�����ˣ�</td>
			<td width="35%"><input type="text" name="createdUser" style="width:95%" class="input_style2" readonly value="<%=dto.getCreatedUser()%>"></td>
		</tr>
		<tr height="90">
			<td width="15%" align="right">����������</td>
			<td width="85%" colspan="3"><textarea class="input_style1" style="width:100%;height:100%" name="taskDesc"><%=dto.getTaskDesc()%></textarea></td>
		</tr>
<%
	if(userAccount.isProvinceUser()){
%>
		<tr height="160">
			<td align="right">��鹫˾��</td>
			<td align="center" colspan="3">
				<table style="width:100%;height:100%">
					<tr height="18">
						<td align="center" width="40%">��ѡ��˾</td>
						<td align="center" width="20%">ѡ�����</td>
						<td align="center" width="40%">��ѡ��˾</td>
					</tr>
					<tr height="1">
						<td colspan="4"><hr color="green" width="100%" size="1"></td>
					</tr>
					<tr>
						<td align="center" width="40%"><select class="select_style1" name="leftOu" size="6" style="width:100%;height:100%" multiple="multiple" ondblclick="do_AddCompany()" title="˫��ѡ��ù�˾"><%=dto.getLeftOuOpt()%></select></td>
						<td align="center" width="20%">
							<p style="margin-top: 6px; margin-bottom: 6px"><img src="/images/eam_images/new.jpg" onClick="do_AddCompany();"></p>
							<p style="margin-top: 6px; margin-bottom: 6px"><img src="/images/eam_images/add_all.jpg" onClick="do_AddAllCompany();"></p>
							<p style="margin-top: 6px; margin-bottom: 6px"><img src="/images/eam_images/delete.jpg" onClick="do_RemoveCompany()"></p>
							<p style="margin-top: 6px; margin-bottom: 6px"><img src="/images/eam_images/delete_all.jpg" onClick="do_RemoveAllCompany()"></p>
						</td>
						<td align="center" width="40%"><select class="select_style1" name="sampledOu" size="6" style="width:100%;height:100%" multiple="multiple" ondblclick="do_RemoveCompany()" title="˫��ȥ���ù�˾"><%=dto.getSampledOuOpt()%></select></td>
					</tr>
				</table>
			</td>
		</tr>
<%
	}	
%>
	</table>
	<p align="center">
<%
	if(canEdit){
        if(taskStatus.equals(SamplingDicts.TSK_STATUS1_NEW) || taskStatus.equals(SamplingDicts.TSK_STATUS1_SAVE_TEMP)){
%>
		<img src="/images/eam_images/tmp_save.jpg" onClick="do_SaveTask()">&nbsp;
		<img src="/images/eam_images/publishTask.jpg" onClick="do_PublishTask()">&nbsp;
<%
		}	
		if(taskStatus.equals(SamplingDicts.TSK_STATUS1_SAVE_TEMP)){
%>
		<img src="/images/eam_images/cancel.jpg" onClick="do_CancelTask()">&nbsp;
<%
		}	
		if(taskStatus.equals(SamplingDicts.TSK_STATUS1_OPENING) && !dto.getSeachType().equals("Y")){
%>

		<img src="/images/eam_images/closeTask.jpg" onClick="do_CloseTask()">&nbsp;
<%
		}	
		if(taskStatus.equals(SamplingDicts.TSK_STATUS1_CLOSED)){
%>

		<img src="/images/eam_images/openTask.jpg" onClick="do_OpenTask()">&nbsp;
<%
		}	
	}
	if(!taskId.equals("") && !dto.getSeachType().equals("Y")){
%>

		<img src="/images/eam_images/new_add.jpg" onClick="do_CreateTask()">&nbsp;
<%
	}
%>
		<img src="/images/eam_images/close.jpg" onClick="do_Close();">
	</p>
	<input type="hidden" name="act">
	<input type="hidden" name="taskId" value="<%=taskId%>">
	<input type="hidden" name="createdOu" value="<%=dto.getCreatedOu()%>">
<%
	if(!userAccount.isProvinceUser()){
%>
	<input type="hidden" name="sampledOu" value="<%=userOu%>">
<%
	}
%>
</form>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;"></iframe>

</body>
</html>
<script>

function do_AddCompany(){
	moveSelectedOption("leftOu", "sampledOu");
	do_SetSavedProp(false);
}

function do_AddAllCompany(){
	moveAllOption("leftOu", "sampledOu");
	do_SetSavedProp(false);
}

function do_RemoveCompany(){
	moveSelectedOption("sampledOu", "leftOu");
	do_SetSavedProp(false);
}

function do_RemoveAllCompany(){
	moveAllOption("sampledOu", "leftOu");
	do_SetSavedProp(false);
}


function do_SaveTask(){
<%
	if(userAccount.isProvinceUser()){
%>
	selectAll("sampledOu");
<%
	}
%>
    if (doVilidate()) {
        mainFrm.act.value = "<%=SamplingActions.SAVE_ACTION%>";
	    mainFrm.submit();
    } else {
        return;
    }
}

function doVilidate() {
    var isVilidate = true;
    var slRatio = mainFrm.samplingRatio.value;
    if(Number(slRatio) > 100){
        alert("����Ƿ���ԭ���ǣ������ٷֱȡ����ܴ��ڡ�100��");
        return false;
    }
    if (!isPositiveInteger(slRatio)) {
        alert("����Ƿ���ԭ���ǣ������ٷֱȡ�Ҫ�������������������");
        return false;
    }
    return isVilidate;
}

function do_PublishTask(){
	var fieldNames = "taskName;startDate;endDate;samplingType;samplingRatio";
	var fieldLabels = "��������;��ʼ����;��ֹ����;������;���ٷֱ�";
	var validateType = EMPTY_VALIDATE;
	if(formValidate(fieldNames, fieldLabels, validateType)){
		fieldNames = "samplingRatio";
		fieldLabels = "���ٷֱ�";
		validateType = POSITIVE_VALIDATE;
		if(formValidate(fieldNames, fieldLabels, validateType)){
			if(Number(mainFrm.samplingRatio.value) > 100){
				mainFrm.samplingRatio.value = "";
				alert("����Ƿ���ԭ���ǣ������ٷֱȡ����ܴ��ڡ�100��");
				return;
			}
<%
	if(userAccount.isProvinceUser()){
%>
			var selectedCount = mainFrm.sampledOu.options.length;
			if(selectedCount == 0){
				alert("û��ѡ���鹫˾�����ܷ���������");
				return;
			}
			selectAll("sampledOu");
<%
	}
%>	
			if(confirm("���񷢲��󲻿��޸ģ�ȷ���ύ���������ȷ��������ȷ������ť������������ȡ������ť")){
				mainFrm.act.value = "<%=SamplingActions.PUBLISH_TASK%>";
				mainFrm.submit();
			}
		}
	}
}

function do_CloseTask(){
	if(confirm("����رպ󲻿��ٴ�����鹤����ȷ�Ϲرձ�������ȷ��������ȷ������ť������������ȡ������ť")){
		mainFrm.act.value = "<%=SamplingActions.CLOSE_TASK%>";
		mainFrm.submit();
	}
}

function do_OpenTask(){
	if(confirm("����򿪺�������¼���������鹤����ȷ�ϴ򿪱�������ȷ��������ȷ������ť������������ȡ������ť")){
		mainFrm.act.value = "<%=SamplingActions.OPEN_TASK%>";
		mainFrm.submit();
	}
}

function do_CreateTask(){
	mainFrm.taskId.value = "";
	mainFrm.act.value = "<%=SamplingActions.NEW_ACTION%>";
    mainFrm.submit();
}

function do_CancelTask(){
	if(confirm("ϵͳ��ȡ���ݴ����������ȡ���󽫲��ɽ����κβ�����ȷ��ȡ����ȷ��������ȷ������ť������������ȡ������ť")){
		mainFrm.act.value = "<%=SamplingActions.CANCEL_ACTION%>";
		mainFrm.submit();
	}
}
</script>
