<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.soa.common.SrvURLDefineList" %>
<%@ page import="com.sino.soa.common.SrvWebActionConstant" %>
<%@ page import="com.sino.soa.mis.srv.transfaassetinfo.dto.TransFaAssetInfoDTO" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
</head>
<body leftmargin="0" topmargin="0"  onload="initPage();" onkeydown="autoExeFunction('query();');">
<%
    TransFaAssetInfoDTO dto = (TransFaAssetInfoDTO) request.getAttribute(QueryConstant.QUERY_DTO);
	String assetsType = StrUtil.nullToString(dto.getAssetsType());
	String pageTitle = "ͬ���ʲ�������Ϣ(ODI)";
	
	if((AssetsWebAttributes.TD_ASSETS_TYPE).equals(assetsType)){
		pageTitle = "ͬ��TD�ʲ�������Ϣ(ODI)";
	}
%>
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form action="<%=SrvURLDefineList.TD_TRANS_FAASSET_SERVLET%>" method="post" name="mainFrm">
<script type="text/javascript">
     printTitleBar("<%=pageTitle%>");
</script>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="assetsType" value="<%=assetsType%>">
    <table bgcolor="#E9EAE9" style="width:100%;TABLE-LAYOUT:fixed;word-break:break-all">
        <tr>
            <td align="right" width="10%">�ʲ��˲���</td>
            <td align="left" width="15%"><select name="bookTypeCode" class="noEmptyInput" id="bookTypeCode" style="width:100%"><%=dto.getBookTypeCode()%></select></td>
            <td align="right" width="20%" align="right">����ڼ䣺</td>
            <td align="left" width="20%">
             <input name="periodName" id="periodName" style="width:100%" class="readonlyInput"  value="<%=dto.getPeriodName()%>">
             <%-- <select class="noEmptyInput" name="projectNum" id="projectNum" style="width:100%" size="1"><%=dto.getProjectNum()%></select>--%>
            <td>
            <td width="15%" align="left">
                <img src="/images/eam_images/synchronize.jpg" alt="���ͬ��" onclick="do_SubmitSyn();">
            </td>
        </tr>
    </table>
</form>

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
	   var bookTypeCode = document.getElementById("bookTypeCode");
	   var periodName =document.getElementById("periodName");	
	   
       if(bookTypeCode.value==""){
          alert("�ʲ��˲�����Ϊ�գ������룡");
          document.getElementById("bookTypeCode").focus();
          return false;
       }  
	   if(periodName.value=="" ){
	      alert("����ڼ䲻��Ϊ�գ������룡");
	      document.getElementById("periodName").focus();
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