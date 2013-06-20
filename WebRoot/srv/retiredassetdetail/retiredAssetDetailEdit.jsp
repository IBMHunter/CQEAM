<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.soa.common.SrvURLDefineList" %>
<%@ page import="com.sino.soa.common.SrvWebActionConstant" %>
<%@ page import="com.sino.soa.mis.srv.InquiryRetiredAssetDetail.dto.PageRetiredAssetDTO" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <title>��ѯ�����ʲ�������Ϣ����ҳ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>


</head>
<% PageRetiredAssetDTO dtoOpt= (PageRetiredAssetDTO) request.getAttribute(QueryConstant.QUERY_DTO); 
	String assetsType = dtoOpt.getAssetsType();
	String pageTitle = "ͬ�������ʲ�(��ҳ)";
	
	if((AssetsWebAttributes.TD_ASSETS_TYPE).equals(assetsType)){
		pageTitle = "ͬ��TD�����ʲ�(��ҳ)";
	}
 %>
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();" onkeydown="autoExeFunction('query();')">

<form action="<%=SrvURLDefineList.SRV_RETIRED_ASSET_DETAIL1_SERVLET %>" method="post" name="mainFrm">
<script type="text/javascript">
    printTitleBar("<%=pageTitle%>");
</script>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="assetsType" value="<%=assetsType%>">
    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
       <tr>
            <td width="10%" align="right">�ʲ��˲���</td>
            <td width="15%"><select class="noEmptyInput" name="bookTypeCode" id="bookTypeCode" style="width:100%" size="1"><%=dtoOpt.getOrgOption()%></select></td>
            <td width="10%" align="right">�ʲ���ţ�</td>
            <td width="20%"><input type="text"  name="assetNumber" value="<%=dtoOpt.getAssetNumber()%>" style="width:80%" ></td>
            <td width="10%" align="right">�ʲ���ǩ�ţ�</td>
            <td width="20%"><input type="text"  name="tagNumber" value="<%=dtoOpt.getTagNumber()%>" style="width:80%"></td>
            <%if(!(AssetsWebAttributes.TD_ASSETS_TYPE).equals(assetsType)){ %>
            <td width="15%" align="center"><img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_SearchOrder();"></td>
            <%} %>
        </tr>
        <tr>
             <td width="10%" align="right">���ڲ��ţ�</td>
             <td width="15%"><input type="text"  class="noEmptyInput" value="<%=dtoOpt.getLocalionDep()%>" name=localionDep style="width:100%"></td>
             <td width="10%" align="right">����ʱ��ӣ�</td>
             <td width="20%"><input  name="dateRettredFrom"   style="width:70%" title="���ѡ������" value="<%=dtoOpt.getDateRettredFrom()%>" readonly class="readonlyInput" onclick="gfPop.fStartPop(dateRettredFrom, dateRettredTo);"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(dateRettredFrom, dateRettredTo);"></td>
             <td width="10%" align="right">����ʱ�䵽��</td>
             <td width="20%" ><input  name="dateRettredTo"   style="width:70%" title="���ѡ������" value="<%=dtoOpt.getDateRettredTo()%>" readonly class="readonlyInput" onclick="gfPop.fEndPop(dateRettredFrom, dateRettredTo);"><img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(dateRettredFrom, dateRettredTo);"></td>
            <td width="15%" align="center">
              <img src="/images/button/synchronize.gif" alt="���ͬ��" onclick="do_SubmitSyn();">
            </td>
        </tr>
    </table>

</form>
<%
	DTOSet ds=(DTOSet)request.getAttribute(SrvWebActionConstant.ASSETCATEGORYSEAR);
	if(!(AssetsWebAttributes.TD_ASSETS_TYPE).equals(assetsType)){
%>
<div id="headDiv" style="overflow:hidden;position:absolute;top:70px;left:1px;width:100%">
	<table border=1 width="100%" class="headerTable">
		<tr class=headerTable height="20px">
			<td align=center width="10%">�ʲ��˲�</td>
			<td align=center width="8%">�ʲ�ID</td>      
			<td align=center width="8%">�ʲ����</td>
			<td align=center width="8%">�ʲ���ǩ��</td>
			<td align=center width="8%">ʹ������</td>
			<td align=center width="8%">��������</td>
			<td align=center width="8%">��Ч����</td>      
			<td align=center width="8%">����ֵ</td>
			<td align=center width="8%">��ǰ״̬</td>
			<td align=center width="8%">����</td>
			<td align=center width="8%">��������</td>
		</tr>
	</table>
</div>
<%
	if(ds!=null&&ds.getSize()>0){		
 %>
<div id="dataDiv" style="overflow:scroll;height:68%;width:855px;position:absolute;top:92px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
	<table id="dataTable" width="100%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%      PageRetiredAssetDTO dto=new PageRetiredAssetDTO();

		for(int i=0;i<ds.getSize();i++){
		 dto=(PageRetiredAssetDTO)ds.getDTO(i);
		
 %>
        <tr class="dataTR" >

			<td align="right" width="10%"><%=dto.getBookTypeCode() %></td>
			<td align="right" width="8%"><%=dto.getAssetId() %></td>
			<td align="right" width="8%"><%=dto.getAssetNumber() %></td>
			<td align="right" width="8%"><%=dto.getTagNumber() %></td>
			<td align="right" width="8%"><%=dto.getDatePlacedInService() %></td>
			<td align="right" width="8%"><%=dto.getDateRettred() %></td>
			<td align="right" width="8%"><%=dto.getDateEffective() %></td>
			<td align="right" width="8%"><%=dto.getCostRetired() %></td>
			<td align="right" width="8%"><%=dto.getStatus() %></td>
			<td align="right" width="8%"><%=dto.getUnits() %></td>
			<td align="right" width="8%"><%=dto.getRetirementTypeCode() %></td>
			
<%	
		}
 } else{
 %>
 <div id="dataDiv" style="overflow:scroll;height:68%;width:855px;position:absolute;top:66px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
 
 <%
 	}}
  %>
    </table>
</div>

</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<script type="text/javascript">

	function do_SearchOrder() {
		if(mainFrm.transferType){
			mainFrm.transferType.disabled = false;
		}
	    mainFrm.act.value = "<%=SrvWebActionConstant.QUERY_ACTION%>";
	    mainFrm.submit();
	}
	
	function do_SubmitSyn() {
		var bookTypeCode = document.getElementById("bookTypeCode").value;
		if(bookTypeCode ==""){
			alert("�ʲ��˲�����Ϊ�գ�");
			return false;
		}
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
	    mainFrm.act.value = "<%=SrvWebActionConstant.INFORSYN%>";
	    mainFrm.submit();
	}
	
   function initPage() {
        do_SetPageWidth();
    }
</script>
</html>