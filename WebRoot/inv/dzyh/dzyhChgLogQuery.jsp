<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/inv/headerInclude.jsp"%>
<%@ include file="/inv/headerInclude.htm"%>
<%@ page import = "com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.inv.dzyh.dto.EamDhBillLDTO" %>

<html>
<head>
    <title>��ֵ�׺�Ʒ���⣭���������嵥��ѯ</title>
    <style>
.finput {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;}
.finput2 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:center;}
.finput3 {WIDTH:100%;BORDER-RIGHT: 0px ridge;BORDER-TOP: 0px ridge; BORDER-LEFT: 0px ridge ; BORDER-BOTTOM: 0px ridge;font-size: 12px;text-align:right;}
</style>
</head>

<body leftmargin = "0" rightmargin = "0" topmargin = "0">
 
<% 
 
    RequestParser parser = new RequestParser(); 
    parser.transData(request); 
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW); 
    boolean hasData = (rows != null && !rows.isEmpty()); 
    Row row = null; 
	EamDhBillLDTO dto = (EamDhBillLDTO)request.getAttribute(QueryConstant.QUERY_DTO); //���Servlet���dto.setXXX()����д��   
%>
<form method="post" name="mainFrm">
    <script type = "text/javascript">
        printTitleBar("��ֵ�׺�Ʒ���⣭���������嵥��ѯ")
    </script>

	<iframe width = 174 height = 189 name = "gToday:normal:calendar.js" id = "gToday:normal:calendar.js"
        src = "/WebLibary/js/DateHTML.htm" scrolling = "no" frameborder = "0"
        style = "visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
	</iframe>

    <table width = "100%" border = "0" class = "queryHeadBg">
        <tr>
        	<td width = "10%" align = "right">Ŀ¼��ţ�</td>
            <td width = "20%">
            	<input type="text" name="catalogValueId" value="<%=dto.getCatalogValueId()%>"  size="16">
            	<a href="#" title="���ѡ��Ŀ¼���" class="linka" onclick="do_SelectCatalogValueId();">[��]</a>
            </td>
            <td width = "8%" align = "right">���룺</td>
            <td width="16%" align="right">
            <input type="text" name="barcode" value="<%=dto.getWorkorderObjectName() %>" size="13"><a href="#" title="���ѡ������" class="linka" onclick="do_SelectBarcode();">[��]</a>
			</td>
            
            <td width = "10%" align = "right">�������ڣ�</td>
            <td width = "18%" align = "left">
            <input type = "text" readonly = "true" class = "readonlyInput" name = "minTime" size = "25" style = "width:80%" onclick = "gfPop.fPopCalendar(minTime)">
            <img src = "/images/calendar.gif" alt = "���ѡ������" onclick = "gfPop.fPopCalendar(minTime)">
            </td>
            
            <td width = "6%" align = "right">Ʒ����</td>
            <td width = "10%">
            	<input type="text" name="itemName" value="<%=dto.getItemName()%>" size="14">
            </td>
            
            <td width = "2%" align = "center">
                <img src = "/images/eam_images/search.jpg" style = "cursor:'hand'" onclick = "do_search();" alt = "��ѯ">
            </td>
         </tr>
         <tr>
            <td width = "10%" align = "right">���ò��ţ�</td>
            <td width = "20%">
            	<input type="text" name="deptName" value="<%=dto.getDeptName()%>" size="16">
            	<a href="#" title="���ѡ�����ò���" class="linka" onclick="do_SelectDept();">[��]</a>
            	<input type="hidden" name="responsibilityDept" value="">
            </td>
            <td width = "8%" align = "right">�����ˣ�</td>
            <td width = "16%" align="right">
            	<input type="text" name="userName" value="<%=dto.getUserName()%>" size="13"><a href="#" title="���ѡ��������" class="linka" onclick="do_SelectUser();">[��]</a>
            	<input type="hidden" name="responsibilityUser" value="">
            </td>
            <td align = "right">����</td>
            <td width="18%" align = "left">
            <input type = "text" readonly = "true" class = "readonlyInput" name = "maxTime" size = "25" value = "" style = "width:80%" onclick = "gfPop.fPopCalendar(maxTime)">
            <img src = "/images/calendar.gif" alt = "���ѡ������" onclick = "gfPop.fPopCalendar(maxTime)">
            </td>
            
            <td width = "10%" align = "right">����ͺţ�</td>
            <td width = "22%">
            <input type="text" name="itemSpec" value="<%=dto.getItemSpec()%>" size="14"><a href="#" title="���ѡ�����ͺ�" class="linka" onclick="do_SelectItemSpec();">[��]</a>
            </td>
            
        </tr>
    </table>
    
    <div style = "/*overflow-x:scroll;width:100%*/">
		
        <div style="overflow-y:scroll;overflow-x:scroll;left:0px;width:100%;height:390px">
        <!--  
        <script type="text/javascript">
        	var columnArr = new Array("Ŀ¼���","�����", "Ʒ��", "����ͺ�", "����", "����","ʹ�ò���","������","�ص�","������","��������","����","��ע");
        	var widthArr = new Array("6%", "10%", "8%", "8%", "5%","5%","8%","8%","8%","8%","8%","10%","12%");
        	printTableHead(columnArr, widthArr);
    	</script>
    	-->
			<table width="160%" border="1" bordercolor="#666666">
			    
			   <tr height = "20" class = "headerTable">
                    <td width = "6%" align = "center">Ŀ¼���</td>
                    <td width = "10%" align = "center">�����</td>
                    <td width = "8%" align = "center">Ʒ��</td>
                    <td width = "8%" align = "center">����ͺ�</td>
                    <td width = "5%" align = "center">����</td>
                    <td width = "5%" align = "center">����</td>
                    <td width = "8%" align = "center">ʹ�ò���</td>
                    <td width = "8%" align = "center">������</td>
                    <td width = "8%" align = "center">�ص�</td>
                    <td width = "8%" align = "center">������</td>
                    <td width = "8%" align = "center">��������</td>
                    <td width = "10%" align = "center">����</td>
                    <td width = "12%" align = "center">��ע</td>
               </tr>
               
<% 
	if(hasData) {
		for(int i = 0; i < rows.getSize(); i++) {
			row = rows.getRow(i);
%> 
				<tr height="20" style="cursor: 'hand'" onmousemove="style.backgroundColor='#EFEFEF'" onmouseout="style.backgroundColor='#ffffff'">			
					<td width="6%" align="left"><%=row.getValue("ITEM_CATEGORY2")%></td>
					<td width="10%" align="left"><%=row.getValue("BARCODE")%></td>
					<td width="8%" align="right"><%=row.getValue("ITEM_NAME")%></td>
					<td width="8%" align="right"><%=row.getValue("ITEM_SPEC") %></td>
					<td width="5%" align="left"><%=row.getValue("ITEM_QTY")%></td>
					<td width="5%" align="left"><%=row.getValue("PRICE")%></td>
					<td width="8%" align="left"><input type="text" value="<%=row.getValue("DEPT_NAME")%>" readonly="readonly" class="finput"></td>
					<td width="8%" align="center"><%=row.getValue("USER_NAME")%></td>
					<td width="8%" align="center"><input type="text" value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>" readonly="readonly" class="finput"></td>
					<td width="8%" align="center"><input type="text" value="<%=row.getValue("MAINTAIN_USER") %>" readonly="readonly" class="finput"></td>
					<td width="8%" align="center"><%=row.getValue("LAST_LOC_CHG_DATE") %></td>
					<td width="10%" align="right"><input type="text" value="<%=row.getValue("ATTRIBUTE3")%>" readonly="readonly" class="finput"></td>
					<td width="12%" align="right"><input type="text" value="<%=row.getValue("REMARK")%>" readonly="readonly" class="finput"></td>
				</tr>
<% 
		}
	}
%>
			</table>
		</div>
		
		</div>

		<input name="act" type="hidden">
		<!--  
		<input type="hidden" name="responsibilityDept" value="">
		<input type="hidden" name="responsibilityUser" value="">
		<input type="hidden" name="workorderObjectNo">
		-->
	</form>
<%
	if(hasData){
%>
	<div style="position:absolute;top:470px;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}	
%>
	<%=WebConstant.WAIT_TIP_MSG%>
	<jsp:include page="/message/MessageProcess" flush="true"></jsp:include>
</body>
</html>
<script type = "text/javascript">
function do_search() {
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.inv.dzyh.servlet.EamDhChgLogServlet";
	mainFrm.submit();
}

//ѡ��Ŀ¼���
function do_SelectCatalogValueId() {
	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_CATALOG_VALUE_ID%>";
   	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
   	var vendorNames = window.showModalDialog(url, null, popscript);
   	if(vendorNames){
       	var vendorName = null;
       	document.forms[0].catalogValueId.value = vendorNames[0].catalogValueId;
   	}
}

//ѡ������
function do_SelectBarcode() {
	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpConstant.BJSL_ITEM_INFO%>";
   	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
   	var vendorNames = window.showModalDialog(url, null, popscript);
   	if(vendorNames){
       	var vendorName = null;
       	document.forms[0].barcode.value = vendorNames[0].barcode;
   	}
}

//ѡ�����ò���
function do_SelectDept() {
	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_RESPONSIBILITY_DEPT%>";
   	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
   	var vendorNames = window.showModalDialog(url, null, popscript);
   	if(vendorNames){
       	var vendorName = null;
       	document.forms[0].responsibilityDept.value = vendorNames[0].responsibilityDept;
       	document.forms[0].deptName.value = vendorNames[0].deptName;
   	}
}

//ѡ��������
function do_SelectUser() {
	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_RESPONSIBILITY_USER%>";
   	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
   	var vendorNames = window.showModalDialog(url, null, popscript);
   	if(vendorNames){
       	var vendorName = null;
       	document.forms[0].responsibilityUser.value = vendorNames[0].responsibilityUser;
       	document.forms[0].userName.value = vendorNames[0].userName;	
   	}
}

//ѡ�����ͺ�
function do_SelectItemSpec() { 
	var url="/servlet/com.sino.ams.inv.storeman.bean.AMSInvLookUpServlet?lookUpName=<%=LookUpInvConstant.LOOK_UP_SYS_ITEM%>";
   	var popscript = "dialogWidth:47.5;dialogHeight:30;center:yes;status:no;scrollbars:no";
   	var vendorNames = window.showModalDialog(url, null, popscript);
   	if(vendorNames){
       	var vendorName = null;
       	document.forms[0].itemSpec.value = vendorNames[0].itemSpec;
   	}
}
</script>