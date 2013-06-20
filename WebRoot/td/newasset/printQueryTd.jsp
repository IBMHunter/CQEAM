<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/td/newasset/headerIncludeTd.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
<%
    RequestParser parser = new RequestParser();
    parser.transData(request);
	TdAssetsTransHeaderDTO dto = (TdAssetsTransHeaderDTO)parser.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    String transType = dto.getTransType();
	String provinceCode = dto.getServletConfig().getProvinceCode();
	String disapleProp = "";
	if(provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)){
		disapleProp = "disabled";
	}
	String transferType = dto.getTransferType();

	String pageTitle = "";
	String orderNoName = "";
	String deptNameDesc = "";
    if (transType.equals(AssetsDictConstant.ASS_RED)) {
		if(provinceCode.equals(AssetsDictConstant.PROVINCE_CODE_NM)){
			if(transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)){
				pageTitle = "TD�����ڵ���>>��������ӡ";
			} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)){
				pageTitle = "TD���ż����>>��������ӡ";
			} else if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){
				pageTitle = "TD���м����>>��������ӡ";
			}
		} else {
			pageTitle = "TD�ʲ���������>>��������ӡ";
		}
		orderNoName = "��������";
		deptNameDesc = "��������";
    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
		pageTitle = "TD�ʲ����ù���>>���õ���ӡ";
		orderNoName = "���õ���";
		deptNameDesc = "���ò���";
    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
		pageTitle = "���ϵ���ӡ";
		orderNoName = "���ϵ���";
		deptNameDesc = "���ϲ���";
    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
		pageTitle = "���õ���ӡ";
		orderNoName = "���õ���";
		deptNameDesc = "���ò���";
    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
		pageTitle = "��ֵ���ݴ�ӡ";
		orderNoName = "��ֵ����";
		deptNameDesc = "��ֵ����";
    } else if (transType.equals(AssetsDictConstant.ASS_SHARE)){
    	pageTitle = "�����ݴ�ӡ";
    	orderNoName = "������";
    	deptNameDesc = "������";
    } else if (transType.equals(AssetsDictConstant.ASS_SELL)){
    	pageTitle = "���۵��ݴ�ӡ";
    	orderNoName = "���۵���";
    	deptNameDesc = "���۲���";
    } else if (transType.equals(AssetsDictConstant.ASS_RENT)){
    	pageTitle = "���ⵥ�ݴ�ӡ";
    	orderNoName = "���ⵥ��";
    	deptNameDesc = "���ⲿ��";
    } else if (transType.equals(AssetsDictConstant.ASS_DONA)){
    	pageTitle = "�������ݴ�ӡ";
    	orderNoName = "��������";
    	deptNameDesc = "��������";
    }
	String srcPage = parser.getParameter("srcPage");

%>
<title>���ݴ�ӡ��ѯ</title>
</head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_SearchOrder();')" onload="initPage();mainFrm.transferType.focus();">
<%=WebConstant.WAIT_TIP_MSG%>
<form action="<%=TdURLDefineList.ORDER_PRINT_SERVLET_TD%>" method="post" name="mainFrm">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="transType" value="<%=transType%>">

    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
<%
	if(transType.equals(AssetsDictConstant.ASS_RED)){
%>
            <td width="10%" align="right">�������ͣ�</td>
            <td width="10%"><select name="transferType" style="width:100%" <%=disapleProp%>><%=dto.getTransferTypeOption()%></select></td>
            <td width="10%" align="right"><%=orderNoName%>��</td>
            <td width="10%"><input type="text" name="transNo" style="width:100%" value="<%=dto.getTransNo()%>"></td>
            <td width="10%" align="right">�������ڣ�</td>
            <td width="20%">
				<input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:48%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)">
				<input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:48%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)">
            </td>
            <td width="20%"><%=request.getAttribute(AssetsWebAttributes.PRINT_RADIO)%></td>
            <td width="10%" align="right">
			<img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_SearchOrder();">
			</td>
<%
	} else {
%>
            <td width="10%" align="right"><%=orderNoName%>��</td>
            <td width="20%"><input type="text" name="transNo" style="width:100%" value="<%=dto.getTransNo()%>"></td>
            <td width="10%" align="right">�������ڣ�</td>
            <td width="30%">
				<input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(startDate, endDate);">
				<input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(startDate, endDate);">
            </td>
            <td width="25%" align="right">
			<img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_SearchOrder();">
			</td>
<%
	}
%>

        </tr>
    </table>

</form>
<%
	if(!transType.equals(AssetsDictConstant.ASS_RED)){
%>
<table border=1 style="position:absolute;top:45px;width:1138px;left:1px;">
    <tr class=headerTable height="20px">
        <td align=center width=18%><%=orderNoName%></td>
        <td align=center width=10%>����״̬</td>
        <td align=center width=26%><%=deptNameDesc%></td>
        <td align=center width=12%>������</td>
        <td align=center width=10%>��������</td>
    </tr>
</table>
<div style="width:1155px;top:66px;height:362px;left:1px;position:absolute;overflow-y:scroll;">
    <table width=100% border=1 bordercolor="#666666">
<%
		if (rows != null && !rows.isEmpty()) {
			for (int i = 0; i < rows.getSize(); i++) {
				Row row=rows.getRow(i);
%>
        <tr class="dataTR" onclick="showDetail('<%=row.getValue("TRANS_ID")%>')">
		  <td width="18%"><input type="text" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
          <td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("TRANS_STATUS_DESC")%>"></td>
          <td width="26%"><input type="text" class="finput" readonly value="<%=row.getValue("FROM_DEPT_NAME")%>"></td>
          <td width="12%"><input type="text" class="finput" readonly value="<%=row.getValue("CREATED")%>"></td>
          <td width="10%"><input type="text" class="finput2" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
        </tr>
<%
			}
		}
%>
    </table>
</div>
<%
	} else {
%>
<table border=1 style="position:absolute;top:45px;width:1106px;left:1px;">
    <tr class=headerTable height="20px" width=100%>
        <td align=center width="18%">��������</td>
        <td align=center width="10%">��������</td>
        <td align=center width="16%">������λ</td>
        <td align=center width="12%">����������</td>
        <td align=center width="10%">������������</td>
        <td align=center width="10%">������״̬</td>
    </tr>
</table>
<div style="width:1123px;top:66px;height:362px;left:1px;position:absolute;overflow-y:scroll;">
    <table width=100% border=1 bordercolor="#666666">
<%
		String transId = "";
		if (rows != null && !rows.isEmpty()) {
			for (int i = 0; i < rows.getSize(); i++) {
				Row row=rows.getRow(i);
				transferType = row.getStrValue("TRANSFER_TYPE");
				int index = ArrUtil.getArrIndex(AssetsDictConstant.TRANS_OPT_VALUES, transferType);
				transferType = AssetsDictConstant.TRANS_OPT_LABELS_TD[index];
				transId = row.getStrValue("TRANS_ID");
%>
        <tr class="dataTR" title="����򿪸õ���" onclick="showDetail('<%=transId%>')">
		  <td width="18%"><input type="text" class="finput2" readonly value="<%=row.getValue("TRANS_NO")%>"></td>
		  <td width="10%"><input type="text" class="finput2" readonly value="<%=transferType%>"></td>
          <td width="16%"><input type="text" class="finput" readonly value="<%=row.getValue("FROM_DEPT_NAME")%>"></td>
          <td width="12%"><input type="text" class="finput" readonly value="<%=row.getValue("CREATED")%>"></td>
          <td width="10%"><input type="text" class="finput2" readonly value="<%=row.getValue("CREATION_DATE")%>"></td>
          <td width="10%"><input type="text" class="finput" readonly value="<%=row.getValue("TRANS_STATUS_DESC")%>"></td>
        </tr>
<%
			}
		}
%>
    </table>
</div>
<%
	}
    if (rows != null && !rows.isEmpty()) {
%>
<div style="position:absolute;top:428px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">
function initPage(){
	do_SetPageWidth();
}

function do_SearchOrder() {
	if(mainFrm.transferType){
		mainFrm.transferType.disabled = false;
	}
    mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function showDetail(transId){
    var transType = mainFrm.transType.value;
    var url = "<%=TdURLDefineList.ORDER_PRINT_SERVLET_TD%>?act=<%=AssetsActionConstant.DETAIL_ACTION%>&transType=" + transType+"&transId="+transId;
    if(mainFrm.printType){
    	url += "&printType=" + getRadioValue("printType");
    }
    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    var newWin = window.open(url, 'printWin', style);
	newWin.focus();
}
</script>
</html>
