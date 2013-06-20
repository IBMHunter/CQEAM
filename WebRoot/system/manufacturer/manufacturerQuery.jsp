<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.CheckBoxProp" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.system.manufacturer.EtsManufacturerDTO" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<html>
<head>
    <title>������Ϣά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
	<script language="javascript" src="/WebLibary/js/TableProcess.js"></script>
</head>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<body leftmargin="1" topmargin="0"  onload="initPage();">
<%
    RequestParser reqParser = new RequestParser();
    CheckBoxProp checkProp = new CheckBoxProp("subCheck");
    reqParser.setCheckBoxProp(checkProp);
    reqParser.transData(request);
    String[] subChecks = reqParser.getParameterValues("subCheck");
    if (subChecks != null) {
        for (int i = 0; i < subChecks.length; i++) {
            System.out.print(subChecks[i] + ";");
        }
    }
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    EtsManufacturerDTO dto = (EtsManufacturerDTO)request.getAttribute(WebAttrConstant.MANUFACTURER_DTO);
    String action = StrUtil.nullToString(request.getParameter("act"));
%>

<form name="mainFrm" method="post" action="<%=URLDefineList.MANUFACTURER_QUERY_SERVLET%>">
    <script language="javascript">
        printTitleBar("������Ϣά��");
    </script>
    <input type="hidden" name="act" >
    <input type="hidden" name="manufacturerId">
    <jsp:include page="/message/MessageProcess"/>
    <table border="0" width="100%"  id="table1">
	    <tr>
	    	<td width="11%" align="right">����������</td>
	        <td width="15%"><input class="input_style1" style="width:100%" type="text" name="manufacturerCode" value="<%=dto.getManufacturerCode() %>"></td>
	        <td width="11%" align="right">��Ч״̬��</td>
	        <td width="10%">
                <select class="select_style1" style="width:80%" name="enable" id="enable" onchange="do_SetImageProp();">
                    <option value="Y" <%=dto.getEnable().equals("Y") ? "selected": ""%>>��Ч</option>
                    <option value="N" <%=dto.getEnable().equals("N") ? "selected" : ""%>>��Ч</option>
	            </select>
            </td>
	    	<td width = "45%" colspan = "4" align = "right">
	        	<img src="/images/eam_images/search.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Search();" alt="��ѯ">&nbsp;
	        	<img src="/images/eam_images/new.jpg" id="newImg" style="cursor:'hand'" onclick="do_Create();" alt="����">&nbsp;
	 			<img src="/images/eam_images/delete.jpg" id="deleteImg" style="cursor:'hand'" onclick="do_Delete();" alt="ɾ��">&nbsp;
	 			<img src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">
                <img src="/images/eam_images/enable.jpg" style="cursor:hand;display:none" onclick="do_EnableObject();" title="������Ч" id="enablePic">
			    <img src="/images/eam_images/disable.jpg" style="cursor:hand;display:none" onclick="do_DisableObject();" title="����ʧЧ" id="disablePic">
	        </td>
	    </tr>    
	</table>   
</form>
		
<input type="hidden" name="act">    
<div id="headDiv" style="overflow:hidden;position:absolute;top:45px;left:1px;width:838px">
	<table class="headerTable" border="1" width="100%">
	    <tr height=20px onClick="executeClick(this)" style="cursor:hand" title="���ȫѡ��ȡ��ȫѡ">
            <td width="2%" align="center"><input type="checkbox" name="subCheck" class="headCheckbox" id="controlBox" onclick="checkAll('subCheck','manufacturerId')"></td>
	        <td align=center width="10%">��������</td>
	        <td align=center width="10%">�Ƿ���Ч</td>
	        <td align=center width="10%">������</td>
	        <td align=center width="10%">��������</td>
	        <td align=center width="10%">��������</td>
	        <td align=center width="10%">������ʱ��</td>
		</tr>
    </table>
</div>
	
<div id="dataDiv" style="overflow:scroll;height:75%;width:847px;position:absolute;top:68px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
  <form  name="mainFrm1" method="post" action="">
    <table id="dataTable" width="100%" border="1" style="TABLE-LAYOUT:fixed;word-break:break-all">
	    <input type="hidden" name="act">
	<%
		RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
		boolean hasData = (rows != null && !rows.isEmpty());
		if (hasData) {
				Row row = null;
				for (int i = 0; i < rows.getSize(); i++) {
					row = rows.getRow(i);
	%>
		<tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
            <td width="2%" align="center"><input type="checkbox" name="manufacturerId" value="<%=row.getValue("MANUFACTURER_ID")%>"></td>
			<td align=center width="10%" onclick="show_Detail('<%=row.getValue("MANUFACTURER_ID")%>')"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("MANUFACTURER_NAME")%>"></td>
			<td align=center width="10%" onclick="show_Detail('<%=row.getValue("MANUFACTURER_ID")%>')"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("ENABLE")%>"></td>
			<td align=center width="10%" onclick="show_Detail('<%=row.getValue("MANUFACTURER_ID")%>')"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("CREATE_BY")%>"></td>
			<td align=center width="10%" onclick="show_Detail('<%=row.getValue("MANUFACTURER_ID")%>')"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("CREATE_DATE")%>"></td>
			<td align=center width="10%" onclick="show_Detail('<%=row.getValue("MANUFACTURER_ID")%>')"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("LAST_UPDATE_BY")%>"></td>
			<td align=center width="10%" onclick="show_Detail('<%=row.getValue("MANUFACTURER_ID")%>')"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("LAST_UPDATE_DATE")%>"></td>
		</tr>
	<%
			    }
		}
	%>
    </table>
  </form>
</div>
<%
	if(hasData){
%>
<div style="position:absolute;top:91%;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
<%=WebConstant.WAIT_TIP_MSG%>

</body>
</html>

<script type="text/javascript">
//����ҳ�����ʱ�����ݵ�table����е���
function initPage(){
	do_SetPageWidth();
    do_SetImageProp();
}

function do_SetImageProp(){
	var enabled = mainFrm.enable.value;
	var enPic = document.getElementById("enablePic");
	var disPic = document.getElementById("disablePic");
	if(enabled == "Y"){
		enPic.style.display = "none";
		disPic.style.display = "";
	} else if(enabled == "N"){
		enPic.style.display = "";
		disPic.style.display = "none";
	} else {
		enPic.style.display = "none";
		disPic.style.display = "none";
	}
}

function do_DisableObject(){
	var checkedCount = getCheckedBoxCount("manufacturerId");
    if (checkedCount < 1) {
        alert("������ѡ��һ�����ݣ�");
        return;
    }
    if(confirm("�Ƿ�ȷ��ʧЧ������Ϣ������������ȷ����������������ȡ������ť")){
        mainFrm1.act.value = "<%=AMSActionConstant.DISABLED_ACTION%>";
        mainFrm1.submit();
    }
}

function do_EnableObject(){
	var checkedCount = getCheckedBoxCount("manufacturerId");
    if (checkedCount < 1) {
        alert("������ѡ��һ�����ݣ�");
        return;
    }
    if(confirm("�Ƿ�ȷ����Ч������Ϣ������������ȷ����������������ȡ������ť")){
        mainFrm1.act.value = "<%=AMSActionConstant.ENABLE_ACTION%>";
        mainFrm1.submit();
    }
}

function do_Search() {
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.submit();
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_Export(){                  //����execl
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.submit();
}

function do_Create() {
    mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
    mainFrm.submit();
}

function do_Delete() {
    var checkedCount = getCheckedBoxCount("manufacturerId");
    if (checkedCount < 1) {
        alert("������ѡ��һ�����ݣ�");
        return;
    } else {
    	if(confirm("�Ƿ�ȷ��ɾ��������Ϣ������������ȷ����������������ȡ������ť")){
    		 mainFrm1.act.value = "<%=WebActionConstant.DELETE_ACTION %>";
    		 mainFrm1.submit();
    	}       
    }
}

function show_Detail(manufacturerId) {
    mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";
    mainFrm.manufacturerId.value = manufacturerId;
    mainFrm.submit();
}    
</script>
