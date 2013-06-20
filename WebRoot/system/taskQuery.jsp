<%@ page contentType="text/html; charset=GBK" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>     
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="com.sino.base.data.Row"%>
<%@ page import="com.sino.base.data.RowSet"%>
<%@ page import="com.sino.base.util.StrUtil"%>  
<%@ page import="com.sino.sinoflow.dto.TaskLookUpDTO"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>�����ڵ�ʱ��ͳ��</title>
	<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
	 <script language="javascript" src="/WebLibary/js/Constant.js"></script>
	 <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
	<script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
	<script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
	<script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
	<script language="javascript" src="/WebLibary/js/AppStandard.js"></script>  
	<script type="text/javascript" src="/WebLibary/js/LookUp.js"></script>
</head>
<% 
    String act = "";
    TaskLookUpDTO dto = (TaskLookUpDTO)request.getAttribute(QueryConstant.QUERY_DTO);
%>
<jsp:include page="/message/MessageProcess"/>
<body leftmargin="0" topmargin="0" onload="do_SetPageWidth();"  onkeydown="autoExeFunction('do_Search();')" >
	<iframe width="174" height="189" name="gToday:normal:calendar.js"
		id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm"
		scrolling="no" frameborder="0"
		style="visibility: visible; z-index: 999; position: absolute; left: -500px; top: 0;"></iframe>
 
	<script language="javascript">
        printTitleBar("�����ڵ�ʱ��ͳ��");
    </script>
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0">
    <form name="mainFrm" method="post" action="/servlet/com.sino.sinoflow.servlet.TaskQuery">
        <input type="hidden" name="act"/>
			<tr>
				<td width="10%" align="right">��˾</td>
				<td width="20%">
                    <select class="select_style1" style="width:80%" name="companyName">
                        <%=dto.getCompanyNameOpt()%>
                    </select>
				</td>
				<td width="10%" align="right">������������</td>
				<td width="25%" >
                    <select class="select_style1" style="width:80%" name="procedureName">
                        <%=dto.getProcedureNameOpt()%>
                    </select>
				</td>
				<td width="10%" align="right">�ڵ�</td>
				<td width="25%" >
                    <select class="select_style1" style="width:80%" name="taskName">
                        <%=dto.getTaskNameOpt()%>
                    </select>
				</td>
            </tr>
			<tr>
				<td width="10%" align="right">ͳ��������</td>
				<td width="20%" >
                    <input type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width: 80%" maxlength="10" onclick="gfPop.fStartPop(startDate, endDate)" />
				</td>
				<td width="10%" align="right">ͳ������ֹ</td>
				<td width="25%">
                    <input type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width: 80%" maxlength="10" onclick="gfPop.fEndPop(startDate, endDate)" />
				</td>
				
				<td width="10%" align="right"></td>
                <td width="25%" align="center">
                    <img src="/images/eam_images/search.jpg" alt="�����ѯ" onclick="do_Search();;">
                    <img src="/images/eam_images/export.jpg" title="�������" onclick="do_Export();">
                </td>
            </tr>
		</form>
    </table>
		<script type="text/javascript">
		var columnArr = new Array( "��˾","������������", "�ڵ�", "�ڵ�����", "�����ʱ��", "ƽ������ʱ��", "�������ʱ��");
		var widthArr = new Array( "10%", "20%","15%", "10%", "15%", "15%", "15%");
	    printTableHead(columnArr,widthArr);
     	</script> 
 
	  	<div id="dataDiv" style="overflow-y:scroll;left:1px;width:100%;height:75%">
		<table id="dataTable" width="100%" border="1" bordercolor="#666666" style="table-layout:fixed;">
		<%
				RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
				if (rows != null && !rows.isEmpty()) {
					Row row = null;
					for (int i = 0; i < rows.getSize(); i++) {
						row = rows.getRow(i);
				%>
				<tr class="dataTR" >
					<td width="10%">
						<%=row.getStrValue("COMPANY_NAME")%>
					</td>
					<td width="20%">
						<%=row.getStrValue("PROCEDURE_NAME")%>
					</td> 
					<td width="15%">
						<%=row.getStrValue("TASK_NAME")%>
					</td>
					<td width="10%">
                        <%=row.getStrValue("COUNT_NO")%>
					</td>
                    <td width="15%">
                        <%=row.getStrValue("LONGEST_TIME")%>
                    </td>
					<td width="15%">
                        <%=row.getStrValue("PROCESS_TIME")%>
					</td>
                    <td width="15%">
                        <%=row.getStrValue("SHORTEST_TIME")%>
                    </td>
				</tr>
			<%
				}
				}
			%>   
		</table>
              <div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
	</div>
	</div>
	<%=WebConstant.WAIT_TIP_MSG%>
</body>
<script type="text/javascript">
function do_ChangeProc(obj) {
    var url = "/servlet/com.sino.sinoflow.servlet.TaskQuery";
    url += "?act=CHANGE_PROC";
    url += "&procedureName=" + obj.value;
    do_ProcessSimpleAjax(url, null, "do_ProcessResponseProc");
}

function do_ProcessResponseProc() {
    document.getElementById("taskName").outerHTML = "<select class=\"input_3\" style=\"width:75%;\" name=\"taskName\" id=\"taskName\">"
            + resText + "</select>";
}

function do_Search() {
    if (do_CheckSearch()) {
        var tipObj = document.getElementById("$$$waitTipMsg$$$");
        if (!tipObj) {
            alert("���ڲ�ѯҳ�������ʾ��ϢDIV��");
            return;
        }
        var actObj = document.getElementById("act");
        if (!actObj) {
            alert("���ڲ�ѯҳ�����act������");
        }
        tipObj.style.visibility = "visible";
        actObj.value = "QUERY_ACTION";
        document.forms[0].target = "_self";
        document.forms[0].submit();
    }
}

function do_Export() {
    if (confirm("ȷ��Ҫ������EXCEL�𣿼���������ȷ������ť������������ȡ������ť")) {
        document.forms[0].act.value = "EXPORT_ACTION";
        document.forms[0].submit();
    }
}

function do_CheckSearch() {
    return true;
}
</script>
</html>