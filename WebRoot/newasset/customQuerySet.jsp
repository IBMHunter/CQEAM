<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
	<title>�Զ����ѯ����</title>
<head>
<body leftmargin="0" topmargin="0">
<jsp:include page="/message/MessageProcess"/>
<form name="mainFrm" action="<%=AssetsURLList.CUST_QRY_SET_SERVLET%>" method="post">
<table border="0" width="100%" cellspacing="1">
	<tr>
		<td>
			<table border="0" width="100%" id="table1">
				<tr>
					<td width="100%" height="22" colspan="5" align="center">�ۺϲ�ѯ�Զ���</td>
				</tr>
				<tr>
					<td width="20%" height="22">��</td>
					<td width="25%" height="22" align="center">��ѡ�ֶ�</td>
					<td width="20%" height="22" align="center">����</td>
					<td width="25%" height="22" align="center">��ѡ�ֶ�</td>
					<td width="10%" height="22">��</td>
				</tr>
				<tr>
					<td width="20%" height="250" align="right"><b>��ѯ�ֶζ��壺</b></td>
					<td width="25%" height="250">
						<select id="allQueryFields" size="10" style="width:100%;height:100%" ondblclick="do_AddField(1)" name="allQueryFields" title="˫��ѡ����ֶ�" multiple>
						<%=request.getAttribute(AssetsWebAttributes.ALL_QRY_FIELDS)%>
						</select>
					</td>
					<td width="20%" height="250" align="center">
						<img src="/images/eam_images/new.jpg" onClick="do_AddField(1);"><p>
						<img src="/images/eam_images/add_all.jpg" onClick="do_AddAllField(1);"><p>
						<img src="/images/eam_images/delete.jpg" onClick="do_RemoveField(1)"><p>
						<img src="/images/eam_images/delete_all.jpg" onClick="do_RemoveAllField(1)">
					</td>
					<td width="25%" height="250">
						<select id="chkQueryField" size="10" style="width:100%;height:100%" ondblclick="do_RemoveField(1)" name="chkQueryField" title="˫���Ƴ����ֶ�" multiple>
						<%=request.getAttribute(AssetsWebAttributes.CHK_QRY_FIELDS)%>
						</select>
					</td>
					<td width="10%" height="250">
						<img src="/images/eam_images/top.jpg" alt="����" onClick="do_Move2Top(1)"><p>
						<img src="/images/eam_images/up.jpg" alt="����" onClick="do_MoveUp(1)"><p>
						<img src="/images/eam_images/top.jpg" alt="����" onClick="do_MoveDown(1)"><p>
						<img src="/images/eam_images/bottom.jpg" alt="����" onClick="do_Move2Bottom(1)">
					</td>
				</tr>
			</table>
			<table border="0" width="100%" id="table1">
				<tr>
					<td width="20%" height="250" align="right"><b>��ʾ�ֶζ��壺</b></td>
					<td width="25%" height="250">
						<select id="allDisplayFields" size="10" style="width:100%;height:100%" ondblclick="do_AddField(2)" name="allDisplayFields" title="˫��ѡ����ֶ�" multiple>
						<%=request.getAttribute(AssetsWebAttributes.ALL_DIS_FIELDS)%>
						</select>
					</td>
					<td width="20%" height="250" align="center">
						<img src="/images/eam_images/new.jpg" onClick="do_AddField(2);"><p>
						<img src="/images/eam_images/add_all.jpg" onClick="do_AddAllField(2);"><p>
						<img src="/images/eam_images/delete.jpg" onClick="do_RemoveField(2)"><p>
						<img src="/images/eam_images/delete_all.jpg" onClick="do_RemoveAllField(2)">
					</td>
					<td width="25%" height="250">
						<select id="chkDisplayField" size="10" style="width:100%;height:100%" ondblclick="do_RemoveField(2)" name="chkDisplayField" title="˫���Ƴ����ֶ�" multiple>
						<%=request.getAttribute(AssetsWebAttributes.CHK_DIS_FIELDS)%>
						</select>
					</td>
					<td width="10%" height="250">
						<img src="/images/eam_images/top.jpg" alt="����" onClick="do_Move2Top(2)"><p>
						<img src="/images/eam_images/up.jpg" alt="����" onClick="do_MoveUp(2)"><p>
						<img src="/images/eam_images/down.jpg" alt="����" onClick="do_MoveDown(2)"><p>
						<img src="/images/eam_images/bottom.jpg" alt="����" onClick="do_Move2Bottom(2)">
					</td>
				</tr>
				<tr>
					<td width="20%" height="22" align="center">
						��</td>
					<td width="25%" height="22" align="center">
						��</td>
					<td width="20%" height="22" align="center">
						<img border="0" src="/images/eam_images/save.jpg" onclick="do_SaveCustomizeFields();">&nbsp;
					</td>
					<td width="25%" height="22" align="center">
						��</td>
					<td width="10%" height="22" align="center">
						��</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<input type="hidden" name="act" value="">
</form>
</body>
</html>
<script type="text/javascript">

function do_AddField(arg){
	if(arg == 1){
		moveSelectedOption("allQueryFields", "chkQueryField");
	} else {
		moveSelectedOption("allDisplayFields", "chkDisplayField");
	}
}

function do_AddAllField(arg){
	if(arg == 1){
		moveAllOption("allQueryFields", "chkQueryField");
	} else {
		moveAllOption("allDisplayFields", "chkDisplayField");
	}
}

function do_RemoveField(arg){
	if(arg == 1){
		moveSelectedOption("chkQueryField", "allQueryFields");
	} else {
		moveSelectedOption("chkDisplayField", "allDisplayFields");
	}
}

function do_RemoveAllField(arg){
	if(arg == 1){
		moveAllOption("chkQueryField", "allQueryFields");
	} else {
		moveAllOption("chkDisplayField", "allDisplayFields");
	}
}

function do_MoveUp(arg){
	if(arg == 1){
		moveUp(mainFrm.chkQueryField);
	} else {
		moveUp(mainFrm.chkDisplayField);
	}
}


function do_Move2Top(arg){
	if(arg == 1){
		moveUp(mainFrm.chkQueryField, true);
	} else {
		moveUp(mainFrm.chkDisplayField, true);
	}
}

function do_MoveDown(arg){
	if(arg == 1){
		moveDown(mainFrm.chkQueryField);
	} else {
		moveDown(mainFrm.chkDisplayField);
	}
}


function do_Move2Bottom(arg){
	if(arg == 1){
		moveDown(mainFrm.chkQueryField, true);
	} else {
		moveDown(mainFrm.chkDisplayField, true);
	}
}

function do_SaveCustomizeFields(){
	selectAll("chkQueryField");
	if(mainFrm.chkQueryField.length <= 0){
		alert("û�ж����ѯ�ֶΣ����ܱ�������");
		return;
	}
	selectAll("chkDisplayField");
	if(mainFrm.chkDisplayField.length <= 0){
		alert("û�ж�����ʾ�ֶΣ����ܱ�������");
		return;
	}
	addText2Value(mainFrm.chkQueryField);
	addText2Value(mainFrm.chkDisplayField);
	mainFrm.act.value = "<%=AssetsActionConstant.SAVE_ACTION%>";
	mainFrm.submit();
}
</script>