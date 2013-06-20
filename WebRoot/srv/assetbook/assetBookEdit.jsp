<%@ page import="com.sino.soa.common.SrvWebActionConstant" %>
<%@ page import="com.sino.soa.common.SrvURLDefineList" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.soa.mis.srv.assetbook.dto.SBFIFAAssetBookDTO" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>

</head>
<%	SBFIFAAssetBookDTO dtoOpt = (SBFIFAAssetBookDTO) request.getAttribute(QueryConstant.QUERY_DTO);
	String pageTitle = "MISϵͳ�ʲ��˲�";
%>
 <%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<body leftmargin="0" topmargin="0"  onload="initPage();" onkeydown="autoExeFunction('query();')" >

<form action="<%=SrvURLDefineList.SRV_ESSET_BOOK_SERVLET %>" method="post" name="mainFrm">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>
    <input type="hidden" name="act" value="">
    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td width="8%" align="right">�ʲ��˲���</td>
            <td width="15%"><select name="bookTypeCode" id="bookTypeCode" style="width:80%" size="1"><%=dtoOpt.getOrgOption()%></select></td>
            <td width="8%" align="right">�������ڣ�</td>
            <td width="15%">
				<input  name="lastUpdateDate"   style="width:80%" title="���ѡ������" value="<%=dtoOpt.getLastUpdateDate()%>" readonly class="readonlyInput" onclick="gfPop.fPopCalendar(lastUpdateDate)"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fPopCalendar(lastUpdateDate)">
           	</td>
            <td width="15%">
			<img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_SearchOrder();">
            <img src="/images/eam_images/synchronize.jpg" alt="���ͬ��" onclick="do_SubmitSyn();">
			</td>


        </tr>
    </table>

</form>
<%
	DTOSet ds=(DTOSet)request.getAttribute(SrvWebActionConstant.ASSETBOOKTRANSOU);
 %>
<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:100%">
	<table border=1 width="100%" class="headerTable">
		<tr class=headerTable height="20px">
			<td align=center width="10%">��֯ID</td>
			<td align=center width="10%">��֯����</td>       
			<td align=center width="10%">�˲�����</td>
			<td align=center width="10%">�˲�����</td>
  			<td align=center width="10%">���¸���ʱ��</td>
			<td align=center width="10%">�˲���Ч����</td>
		</tr>
	</table>
</div>
<%
	if(ds!=null&&ds.getSize()>0){		
 %>
<div id="dataDiv" style="overflow:scroll;height:68%;width:855px;position:absolute;top:66px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	<table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%      SBFIFAAssetBookDTO dto=new SBFIFAAssetBookDTO();

		for(int i=0;i<ds.getSize();i++){
		 dto=(SBFIFAAssetBookDTO)ds.getDTO(i);
		
 %>
        <tr class="dataTR" >

			<td align="right" width="10%"><%=dto.getOrgId() %></td>
			<td align="right" width="10%"><%=dto.getOrgName() %></td>
			<td align="right" width="10%"><%=dto.getBookTypeCode()%> </td>
			<td align="right" width="10%"><%=dto.getBookTypeName() %></td>
			<td align="right" width="10%"><%=dto.getLastUpdateDate() %></td>
			<td align="right" width="10%"><%=dto.getDateIneffective() %></td>
<%	
		}
    }
 %>
    </table>
</div>

</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">

	function do_SearchOrder() {
	    mainFrm.act.value = "<%=SrvWebActionConstant.QUERY_ACTION%>";
	    mainFrm.submit();
	}
	
	function do_SubmitSyn() {
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	    mainFrm.act.value = "<%=SrvWebActionConstant.INFORSYN%>";
	    mainFrm.submit();
	}
	
    function initPage() {
        do_SetPageWidth();
    }
</script>
</html>