<%@ page import="com.sino.ams.freeflow.FreeFlowDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�������̹���</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<%
	 RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    String srcPage = reqParser.getParameter("srcPage");
    FreeFlowDTO dto = (FreeFlowDTO)reqParser.getAttribute(QueryConstant.QUERY_DTO);
    String transType = dto.getTransType();
    String pageTitle = "";
     if (transType.equals(AssetsDictConstant.ASS_FREE)) {//���õ��ݲ�ѯ
    	 System.out.println("-------------------" + transType);
		if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
			pageTitle = "�ʲ����ù���>>������������";
		} else {
			pageTitle = "�ʲ����ù���>>������������";
		}
      } else if(transType.equals(AssetsDictConstant.ASS_SHARE)){
    	  if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
  			pageTitle = "�ʲ��������>>������������";
  		  } else {
  			pageTitle = "�ʲ��������>>������������";
  		  } 
      }
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
%>
<form name="mainFrm" method="post" action="<%=transType.equals(AssetsDictConstant.ASS_SHARE) ? "/servlet/com.sino.ams.newasset.servlet.AssetsShareFlowServlet" : "/servlet/com.sino.ams.freeflow.FreeFlowServlet" %>">
<%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>")
</script>
	<table width="100%" border="0" class="queryHeadBg">
		<tr>
            <td width="10%" align="right"><%=transType.equals(AssetsDictConstant.ASS_SHARE) ? "������" : "���õ���" %>��</td>
            <td width="20%"><input name="transNo" style="width:80%" type="text" value="<%=dto.getTransNo()%>" >
            <td width="10%" align="right">�������ڣ�</td>
                        <td width="30%">
                            <input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fStartPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(startDate, endDate);">
                            <input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:40%" title="���ѡ������" readonly class="readonlyInput" onclick="gfPop.fEndPop(startDate, endDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(startDate, endDate);">
                        </td>
                        <td width="25%" align="right">
                        <img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_SearchOrder();">
<%
                    if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
%>
                        <img src="/images/eam_images/revoke.jpg" alt="�������" onclick="do_CancelOrder();">
<%
                    } else {
%>
                        <img src="/images/eam_images/new_add.jpg" alt="�������" onclick="do_CreateOrder();">
<%
                    }
%>
                        </td>

        </tr>
	</table>
	  <input type="hidden" name="act" value="">
    <input type="hidden" name="transType" value="<%=transType%>">
	<input type="hidden" name="srcPage" value="<%=srcPage%>">
</form>
<%
	if(!transType.equals(AssetsDictConstant.ASS_RED)){//�ǵ�����������
%>
<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:100%">
	<table border=1 width="100%" class="headerTable">
		<tr class=headerTable height="20px">
<%
	if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
%>
			<td align=center width=4%><input type="checkbox" name="mainCheck" onClick="checkAll(this.name, 'subCheck')"></td>
<%
	}
%>
			<td align=center width="18%"><%=transType.equals(AssetsDictConstant.ASS_SHARE) ? "������" : "���õ���" %></td>
			<td align=center width="10%">����״̬</td>
			<td align=center width="26%"><%=transType.equals(AssetsDictConstant.ASS_SHARE) ? "������" : "���ò���" %></td>
			<td align=center width="12%">������</td>
			<td align=center width="10%">��������</td>
		</tr>
	</table>
</div>
<div id="dataDiv" style="overflow:scroll;height:68%;width:855px;position:absolute;top:66px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	<table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
		if (rows != null && !rows.isEmpty()) {
			for (int i = 0; i < rows.getSize(); i++) {
				Row row=rows.getRow(i);
%>
        <tr class="dataTR" onclick="showDetail('<%=row.getValue("TRANS_ID")%>')">
<%
	if(srcPage.equals(AssetsActionConstant.CANCEL_ACTION)){
%>
          <td width=4% align="center"><%=row.getValue("$$$CHECK_BOX_PROP$$$")%></td>
<%
	}
%>
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
	}
%>
<%
	if(hasData){
%>
<div style="position:absolute;top:468px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function do_SearchOrder(){
	mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function showDetail(transId){
    var transType = mainFrm.transType.value;
    var url = mainFrm.action + "?act=<%=AssetsActionConstant.EDIT_ACTION%>&transType=" + transType+"&transId="+transId;
    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
    window.open(url, 'transferWin', style);
}


    function do_CreateOrder() {
	    var transType = mainFrm.transType.value;
	    var url = mainFrm.action + "?act=<%=AssetsActionConstant.NEW_ACTION%>&transType=" + transType;
	    var style = "width=1024,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no";
	    window.open(url, "transferWin", style);
    }

    function do_CancelOrder(){
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$){
		alert("û�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	if(!mainFrm.$$$CHECK_BOX_HIDDEN$$$.value){
		alert("û��ѡ�����ݣ�����ִ��ָ���Ĳ�����");
		return;
	}
	if(confirm("ȷ��Ҫ����ѡ��ĵ����𣿼���������ȷ������ť������������ȡ������ť��")){
	    mainFrm.act.value = "<%=AssetsActionConstant.CANCEL_ACTION%>";
	    mainFrm.submit();
    }
}

</script>