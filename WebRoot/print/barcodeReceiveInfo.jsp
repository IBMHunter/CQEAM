<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.framework.security.bean.SessionUtil" %>
<%@ page import="com.sino.ams.system.user.dto.SfUserDTO" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.print.dto.BarcodeReceiveDTO" %>
<html>
<head>
    <title>��ǩ������ά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarScroll.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
    <script language="javascript" src="/WebLibary/js/AssetsLookUp.js"></script>
</head>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
<body leftmargin="1" topmargin="0"  onload="initPage();">
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    String orgaOption = (String) request.getAttribute(WebAttrConstant.CITY_OPTION);
    String deptOption = (String) request.getAttribute(WebAttrConstant.COUNTY_OPTION);
    SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    BarcodeReceiveDTO brDTO = (BarcodeReceiveDTO) request.getAttribute(WebAttrConstant.BARCODE_RECEIVE_DTO);
    String action = StrUtil.nullToString(request.getParameter("act"));
    
    String allResName = (String) request.getAttribute( WebAttrConstant.ALL_RES_NAME );
    
%>

<form name="mainFrm" method="post" action="/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet">
    <script language="javascript">
        printTitleBar( "<%= allResName %>" );
    </script>
    <input type="hidden" name="act" value="<%=action%>">
    <input type="hidden" name="excelType"/>
    <input type="hidden" name="userId" value="">
    <jsp:include page="/message/MessageProcess"/>
    <table border="0" width="100%" class="queryTable" id="table1">
    <tr>
	    <td width="4%" align="right">��ʼ��ǩ��</td>
		<td width="6%"><input name="fromBarcode" class="input_style1" value = "<%=brDTO.getFromBarcode() %>" style="width:75%"></td>
		<td width="4%" align="right">������ǩ��</td>
		<td width="6%"><input name="toBarcode"  class="input_style1" value = "<%=brDTO.getToBarcode() %>" style="width:75%"></td>
        <td width="4%" align="right">�������У�</td>
        <td width="6%">
        	<select style="width:75%" class="select_style1" name="organizationId" onchange="getDeptOpt();"><%=orgaOption%></select>
        </td>   
        <td width="4%" align="right">��ӡ�ˣ�</td>
        <td width="12%"><input type="hidden" name="printUser" value="<%=brDTO.getPrintUser()%>"><input type="text" name="printUserName" class="input_style1" value = "<%=brDTO.getPrintUserName() %>" style="width:75%"></td>
    </tr>
    <tr>
    	<td width="4%" align="right">��ӡ���ڣ�</td>
        <td width="6%">
        	<input style="width:75%" type="text" name="printDateBegin" value="<%=StrUtil.nullToString(brDTO.getPrintDateBegin()) %>" title="���ѡ��ӡ��ʼ����" readonly class="input_style2" onclick="gfPop.fStartPop(printDateBegin, printDateEnd)">
        	<img src="/images/calendar.gif" alt="���ѡ���ӡ��ʼ����" onclick="gfPop.fStartPop(printDateBegin, printDateEnd)">
        </td>
        <td align = "right">����</td>
        <td width="6%">
        	<input style="width:75%" type="text" name="printDateEnd" value="<%=StrUtil.nullToString(brDTO.getPrintDateEnd())%>" title="���ѡ��ӡ��������" readonly class="input_style2" onclick="gfPop.fEndPop(printDateBegin, printDateEnd)">
        	<img src="/images/calendar.gif" alt="���ѡ���ӡ����" onclick="gfPop.fEndPop(printDateBegin, printDateEnd)">
        </td>
        <td width="4%" align="right">�������ڣ�</td>
        <td width="6%">
        	<input style="width:75%" type="text" name="receiveDateBegin" value="<%=StrUtil.nullToString(brDTO.getReceiveDate())%>" title="���ѡ����������" readonly class="input_style2" onclick="gfPop.fStartPop(receiveDateBegin, receiveDateEnd)">
        	<img src="/images/calendar.gif" alt="���ѡ��������ʼ����" onclick="gfPop.fStartPop(receiveDateBegin, receiveDateEnd)">
        </td>
        <td align = "right">����</td>
        <td>
        	<input style="width:75%" type="text" name="receiveDateEnd" value="<%=StrUtil.nullToString(brDTO.getReceiveDate())%>" title="���ѡ����������" readonly class="input_style2" onclick="gfPop.fEndPop(receiveDateBegin, receiveDateEnd)">
        	<img src="/images/calendar.gif" alt="���ѡ�����ý�������" onclick="gfPop.fStartPop(receiveDateBegin, receiveDateEnd)">
        </td>
    </tr>    
    <tr>
    	<td align="right">���ò��ţ�</td>
        <td colspan = "3"><select style="width:100%" class="select_style1" name="receiveDept"><%=deptOption%></select></td>
        <td align="right">�����ˣ�</td>
        <td><input style="width:75%" type="text" class="input_style1" name="receiveUserName" value="<%=brDTO.getReceiveUserName() %>"></td>
        <td align="right" colspan = "3">
        	<img src="/images/eam_images/search.jpg" style="cursor:hand" onclick="do_Search();" title="��ѯ">&nbsp;
        	<!-- <img src="/images/eam_images/new_add.jpg" alt="�� ��" onClick="do_CreateData(); return false;">&nbsp; -->
        	
        	<img src="/images/eam_images/imp_from_excel.jpg" alt="��������ǩ" onClick="do_ImportBarcode(); return false;">
        	       	
			<img src="/images/eam_images/export.jpg" style="cursor:hand" onclick="do_ShowExcel()" title="����">&nbsp;
            <img src="/images/eam_images/confirm.jpg"  style="cursor:'hand'" onclick="do_Confirm();" alt="��ǩ�Ŵ�ӡȷ��">
            
            <div id="ddDiv" style="position:absolute;z-index:2;top:130px;left:350px;background-color:azure;border:1px;width:300px;height:50px;text-align:center;visibility:hidden;">
				<table border = "0" width="100%">
				   <tr style="cursor:move;background:#0997F7;color:white;font:bold;height:20">
			            <td>&nbsp;&nbsp;<span key="PleaseSelectFunction"/></td>
			    	    <td align="right"><div style="padding-right:10px"><font face="webdings" style="cursor:hand" onclick="do_ShowExcel()">r</font></div></td>
			       </tr>
			       <tr>  
			           <td width="80%" nowrap="nowrap" align="center">
							<input type="button" value="����EXCEL" onclick="do_Export('xls')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		           	
 		   						<input type="button" value="����CSV" onclick="do_Export('csv')"/>
			           </td>
			       </tr>
				 </table>
				 <iframe   src="" frameborder="0"  style="position:absolute;   visibility:inherit;   top:0px;   left:0px;  width:expression(this.parentNode.offsetWidth);   height:expression(this.parentNode.offsetHeight);   z-index:-1;"></iframe> 				 
			</div>
		</td>
    </tr> 

</table>   
</form>
		
<input type="hidden" name="act">    
<div id="headDiv" style="overflow:hidden;position:absolute;top:98px;left:0px;width:830px">
    <table class="headerTable" border="1" width = "150%">
        <tr height="22" onClick="executeClick(this)" style="cursor:hand">
			<!-- 
			<td width="8%" align="center">��ʼ��ǩ</td>
            <td width="8%" align="center">������ǩ</td>
            <td width="8%" align="center">��ǩ����</td> 
             -->
            <td width="8%" align="center">��ǩ��</td>
            <td width="15%" align="center">���ò���</td>
            <td width="8%" align="center">������</td>
            <td width="8%" align="center">��������</td>
			<td width="10%" align="center">��ӡ��</td>
			<td width="8%" align="center">��ӡ����</td>
			<td width="15%" align="center">��������</td>
			<td width="15%" align="center">����ԭ��</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:68%;width:847px;position:absolute;top:119px;left:0px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="150%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
	<%
		RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
		boolean hasData = (rows != null && !rows.isEmpty());
		if (hasData) {
				Row row = null;
				for (int i = 0; i < rows.getSize(); i++) {
					row = rows.getRow(i);
	%>
	            <%-- <tr class="dataTR" onclick="do_ShowDetail('<%=row.getValue("BARCODE_RECEIVE_ID")%>','<%=row.getValue("PRINT_USER")%>' ); return false;"> --%>
	            <tr class="dataTR" >
	            	<input type = "hidden" name = "barcodeReceiveId" value = "<%=row.getValue("BARCODE_RECEIVE_ID") %>">
		            <%-- 
		            <td height="22" width="8%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("FROM_BARCODE")%>"></td>
		            <td height="22" width="8%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("TO_BARCODE")%>"></td>
		            <td height="22" width="8%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("BARCODE_QTY")%>"></td>
		            --%>
		            <td height="22" width="8%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("BARCODE")%>"></td>
		            <td height="22" width="15%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: left" readonly value="<%=row.getValue("RECEIVE_DEPT_NNAME")%>"></td>
		            <td height="22" width="8%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("RECEIVE_USER_NAME")%>"></td>
		            <td height="22" width="8%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("RECEIVE_DATE")%>"></td>
		            <td height="22" width="10%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("PRINT_USER_NAME")%>"></td>
		            <td height="22" width="8%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("PRINT_DATE")%>"></td>
		            <td height="22" width="15%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("COMPANY")%>"></td>
		            <td height="22" width="15%"><input type="text" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("RECEIVE_REMARK")%>"></td>
	            </tr>
	<%
			}
		}
	%>
    </table>
</div>
<%
	if(hasData){
%>
    <div style="position:absolute;top:416px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
     
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
    }

    function do_SelectUser(){
        var lookUpName = "LOOK_UP_USER";
        var dialogWidth = 44;
        var dialogHeight = 29;
        var userPara = "organizationId=" + mainFrm.organizationId.value;
        var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
        if (objs) {
            var obj = objs[0];
            mainFrm.createdBy.value = obj["userName"];
        }
    }

    function do_Search() {
        var fromBarcode = mainFrm.fromBarcode.value;
        var toBarcode = mainFrm.toBarcode.value;
        if(fromBarcode > toBarcode){
            alert("������ǩ���������ʼ��ǩ��");
            mainFrm.toBarcode.focus();
            return;
        }
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.submit();
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    }

  	function do_ShowExcel() {
		var _d = document.getElementById("ddDiv");
		var left = event.clientX;
		var top = event.clientY;
		_d.style.position = "absolute";
		_d.style.top = top + event.srcElement.offsetHeight;
		_d.style.left = left - 180;
		if (_d.style.visibility == "hidden") {
			_d.style.visibility = "visible";
		}else {
			_d.style.visibility = "hidden";
		}
	}
	
   function do_Export(type) {                  //����execl
	    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
	    mainFrm.action = "/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet";
	    mainFrm.excelType.value = type;
	    mainFrm.submit();
   }


    function do_ShowDetail(barcodeReceiveId, printUser) {
        if("<%=user.getUserId()%>" == printUser) { //�ж�ֻ�д�ӡ���ǵ�ǰ�û��Ļ�,�����޸ĸü�¼
            var url = "/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet";
            url += "?act=<%=WebActionConstant.DETAIL_ACTION%>";
            url += "&barcodeReceiveId=" + barcodeReceiveId;
            mainFrm.action = url;
            mainFrm.submit();
        } else {
            alert("ֻ�д�ӡ�˿��޸ı�����¼!");
        }
    }

    function do_CreateData() {
        mainFrm.act.value = "NEW_ACTION";
        mainFrm.action = "/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet";
        mainFrm.submit();
    }

 var xmlHttp;
    function getDeptOpt() {
        var organizationId = document.getElementById("organizationId").value ;
        var url = "/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet?act=CHOOSE_GROUP&organizationId=" + organizationId;
        xmlHttp = GetXmlHttpObject(setDeptOpt);
        xmlHttp.open('POST', url, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
        xmlHttp.send("a=1");
    }

    function setDeptOpt() {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
            if (xmlHttp.status == 200) {//�ɹ�
                var resText = xmlHttp.responseText;
                var resArray = resText.parseJSON();
                if (resArray == "ERROR") {
                    alert(resText);
                } else {
                    var littleCategoryObj = document.getElementById("receiveDept");
                    littleCategoryObj.length = 0;
                    var emptyOption = new Option("--��ѡ��--", "");
                    littleCategoryObj.add(emptyOption)
                    if (resArray.length > 0) {
                        var retStr;
                        for (var i = 0; i < resArray.length; i++) {
                            retStr = resArray[i];
                            var arr = retStr.split("$");
                            var option = new Option(arr[1], arr[0]);
                            littleCategoryObj.add(option)
                        }
                    }
                }
                xmlHttp = null;
            }
        }
    }

/**
 * �����ص��б��������������
 * �޸���ɡ�
 */
function do_ProcessResponse(responseContent){
	mainFrm.countyCode.outerHTML = "<select style=\"width:100%\" name=\"countyCode\">" + responseContent + "</select>";
}

function do_Confirm(barcode){
	var url = "/servlet/com.sino.ams.print.servlet.BarcodePrintServlet";
	var winName = "assetsWin";
	var style = "width=860,height=495,left=100,top=130";
	window.open(url, winName, style);
}

function do_ImportBarcode() {

	var popscript = "dialogWidth:60;dialogHeight:30;center:yes;status:no;scrollbars:no;help:no";
	var url = "/servlet/com.sino.ams.print.servlet.BarcodeReceiveServlet?act=ImportClaimBarcode";
	var barcodes = window.showModalDialog(url, null, popscript);
	if (barcodes) {
		var data = null;
		var tab = document.getElementById("dataTable");
		for (var i = 0; i < barcodes.length; i++) {
			data = barcodes[i];
			data.transNo = "���ʱ�Զ�����";
			data.statusName = "����";
			appendDTO2Table(tab, data, false, "checkLocation");
		}
	}

}


</script>