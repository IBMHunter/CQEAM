<%@ page contentType = "text/html; charset=GBK" language = "java" errorPage = "" %>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import = "com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%
    RowSet rs = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
%>
<html>
	<head>
		<title></title>
		<link rel = "stylesheet" type = "text/css" href = "/WebLibary/css/main.css">
		<script type = "text/javascript" src = "/WebLibary/js/Constant.js"></script>
		<script type = "text/javascript" src = "/WebLibary/js/CommonUtil.js"></script>
		<script type = "text/javascript" src = "/WebLibary/js/FormValidate.js"></script>
		<script type = "text/javascript" src = "/WebLibary/js/FormProcess.js"></script>
		<script type = "text/javascript" src = "/WebLibary/js/SelectProcess.js"></script>
		<script language="javascript" src="/WebLibary/js/clientRowSet.js"></script>
		<script language="javascript" src="/WebLibary/js/OperationProjectGroupRole.js"></script>
		<script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
		<script language="javascript" src="/WebLibary/js/expendCollapse.js"></script>
		 <script type="text/javascript">
	       function printTool(){
		        var ArrAction = new Array("����", "action_draft.gif","create","xz_t");
		        var ArrAction3 = new Array("����", "cancel.gif","fh","de_t");
		        var ArrAction2 = new Array("ˢ��", "act_refresh.gif","doReload","sh_t");
		        var ArrAction1 = new Array("ɾ��", "del.gif","del","de_t");
		        
		        var toolBar = new SinoPrintToolBar();            
		        toolBar.SinoActions = new Array(ArrAction,ArrAction3,ArrAction1,ArrAction2);
		        toolBar.imagePath = "../images/buttonbar/";
		        toolBar.titleStr = "����ʱ�䶨��";
		        toolBar.align="center";
		        toolBar.treeViewTitle = new Array("&nbsp;","<img src='../images/buttonbar/t_check.gif' onclick=\"sdAll('mdc');\"/>","����","��һ�ι���ʱ��","�ڶ��ι���ʱ��","������","");
				toolBar.treeViewWidth = new Array("2%","2%;","15%","15%","15%","","2%");
		      	toolBar.print();
		    }
		    printTool();
	     </script> 
	</head>

	<body>
	<jsp:include page = "/message/MessageProcess" flush = "true" />

		<div style="overflow-y:scroll;height:75%;width:100%;left:1px;margin-left:0px" align="left">
	  		<form name="mainFrm" action="" method="post">
	  			<table width="100%" border="0" bordercolor="#666666" id="tab">
	  				<% 
	  					if(rs != null && !rs.isEmpty()){
  							for(int i=0;i<rs.getSize();i++){
  								Row r = (Row)rs.getRow(i);%>
	  				<tr class="dataTR">
	  					<td width="2%"></td>
	  					<td width="2%" align="center">
  							<input type="checkbox" name="mdc" value="<%= r.getStrValue("WORK_HOUR_ID") %>"/>
  						</td>
  						<td align="center" width="15%" onclick="do_ShowDetail('<%= r.getStrValue("WORK_HOUR_ID") %>');">
	  						<%= r.getStrValue("WORK_HOUR_NAME") %>
	  					</td>
	  					<td align="center" width="15%" onclick="do_ShowDetail('<%= r.getStrValue("WORK_HOUR_ID") %>');">
	  						<%= r.getStrValue("WORK_BEGIN_1") %>
	  						-<%= r.getStrValue("WORK_END_1") %>
	  					</td>
	  					<td align="center" width="15%" onclick="do_ShowDetail('<%= r.getStrValue("WORK_HOUR_ID") %>');">
	  						<%= r.getStrValue("WORK_BEGIN_2") %>
	  						-<%= r.getStrValue("WORK_END_2") %>
	  					</td>
	  					<td align="center" width="" onclick="do_ShowDetail('<%= r.getStrValue("WORKING_DATE") %>');">
	  						<%= r.getStrValue("wrok_str") %>
	  					</td>
	  					<td width="2%"></td>
	  				</tr>
	  				<%}}%>
	  			</table>
	  		</form>
  		</div>

		<div>
			<%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
			<%=WebConstant.WAIT_TIP_MSG%>
		</div>
	</body>
</html>

<script type="text/javascript">
	 <!-- 
	function create(){//�½�
		window.location.assign("/flowTask/workHourDetail.jsp");
	}
  	
	function do_ShowDetail(id){//��ϸ
		window.location.assign(
		"/servlet/com.sino.sinoflow.servlet.SfWorkHourServlet?act=<%= WebActionConstant.DETAIL_ACTION %>&workHourId="+id);
	}
	
	function del(){//ɾ��
		var tc = document.getElementsByName("mdc");
		for(var i=0;i<tc.length;i++){
			if(tc.item(i).checked){
				if(confirm("ȷ��ɾ����ѡ���� ����������ȷ���� ��������ȡ����")){
					mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfWorkHourServlet?act=<%= WebActionConstant.DELETE_ACTION %>";
					mainFrm.submit();
					return;
				}else{
					return;
				}	
			}
		}
	}
	
	function doReload(){//ˢ��
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
       	window.location.assign("/servlet/com.sino.sinoflow.servlet.SfWorkHourServlet");
   	}
   	
   	function fh(){//����
   		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
   		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfWorkScheduleServlet");
   	}
	  	
	  //-->
  </script>