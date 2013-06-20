<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.soa.mis.srv.assetsinfoupdate.dto.SrvAccountBalanceDTO" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>
 
<html>
<head>
    <title>�ʲ�������Ϣͬ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
</head>
<body  onkeydown="autoExeFunction('do_Search()');" onload="initPage();">
<%
	SrvAccountBalanceDTO dto = (SrvAccountBalanceDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	String assetsType = dto.getAssetsType();
	String pageTitle = "ͬ���ʲ�ͷ������Ϣ(ODI)";
	
	if((AssetsWebAttributes.TD_ASSETS_TYPE).equals(assetsType)){
		pageTitle = "ͬ��TD�ʲ�ͷ������Ϣ(ODI)";
	}
%>
<%=WebConstant.WAIT_TIP_MSG%>
<jsp:include page="/message/MessageProcess"/>
<form name="mainFrm" method="post" action="/servlet/com.sino.soa.td.srv.assetsinfoupdate.servlet.TDTransAssetHeaderInfoServlet">
    <script type="text/javascript">
        printTitleBar("<%=pageTitle%>");
    </script>
    <table width="100%" topmargin="0" border="0" bgcolor="#EFEFEF" style="width:100%">
        <tr>
            <td align="right" width="10%">�ʲ��˲����ƣ�</td>
            <td align="left" width="15%"><select name="bookTypeCode" class="noEmptyInput" id="bookTypeCode" ><%=dto.getBookTypeCode()%></select></td>
            <td align="right" width="6%">��ʼʱ�䣺</td>
            <td align="left" style="width:20%">
                <input type="text" name="startLastUpdateDate" value="<%=dto.getStartLastUpdateDate()%>" style="width:60%" title="���ѡ������" readonly
                       class="readonlyInput" onclick="gfPop.fStartPop(startLastUpdateDate, endLastUpdateDate);"><img
                    src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(startLastUpdateDate, endLastUpdateDate);">
            </td>
            <td align="right" width="6%">����ʱ�䣺</td>
            <td align="left" style="width:20%">
                <input type="text" name="endLastUpdateDate" value="<%=dto.getEndLastUpdateDate()%>" style="width:60%" title="���ѡ������" readonly
                       class="readonlyInput" onclick="gfPop.fEndPop(startLastUpdateDate, endLastUpdateDate);"><img
                    src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(startLastUpdateDate, endLastUpdateDate);">
            </td>
            <td  width="15%" align="left"><img src="/images/eam_images/synchronize.jpg" style="cursor:'hand'" onclick="do_syschronize();" alt="ͬ��"></td>
        </tr>
    </table>
    <input name="act" type="hidden">
    <input name="company" type="hidden">
	<input type="hidden" name="assetsType" value="<%=assetsType%>">


</form>
<div style="position:absolute;top:92%;left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm"
        scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

<script type="text/javascript">

    function do_Search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        document.mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        document.mainFrm.submit();
    }
    
   /**
	* ���ܣ�ͬ������
	*/
	function do_syschronize() {
		
		var dc=document.getElementById("bookTypeCode");
		var sdate=document.getElementById("startLastUpdateDate");
		var edate=document.getElementById("endLastUpdateDate");
//		if(dc.value ==""){
//			alert("��ѡ���ʲ��˲����ƣ�");
//			return ;
//		}
//		if(sdate.value ==""){
//			alert("��ѡ��ʼʱ�䣡");
//			return ;
//		}
//		if(edate.value ==""){
//			alert("��ѡ�����ʱ�䣡");
//			return ;
//		}
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
		document.mainFrm.action = "/servlet/com.sino.soa.td.srv.assetsinfoupdate.servlet.TDTransAssetHeaderInfoServlet";     
		document.mainFrm.act.value = "<%=WebActionConstant.SYSCHRONIZE_ACTION%>";
		document.mainFrm.submit();
	}
	
    function initPage() {
        do_SetPageWidth();
    }
</script>
</html>

