<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import = "com.sino.base.util.StrUtil"%>
<%@ page import = "com.sino.sinoflow.dto.SfWorkScheduleDTO" %>
<%@ page import ="com.sino.base.dto.DTOSet"%>

<%
    DTOSet ds = (DTOSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
%>

<html>
  <head>
    <title>����ʱ��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/clientRowSet.js"></script>
    <script type="text/javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/OperationProjectGroupRole.js"></script>
    <script language="javascript" src="/WebLibary/js/expendCollapse.js"></script>
    <script type="text/javascript">
       function printTool(){
	        var ArrAction = new Array("����", "action_draft.gif","create","xz_t");
	        var ArrAction2 = new Array("ˢ��", "act_refresh.gif","doReload","sh_t");
	        var ArrAction1 = new Array("ɾ��", "del.gif","del","de_t");
	        
	        var toolBar = new SinoPrintToolBar();            
	        toolBar.SinoActions = new Array(ArrAction,ArrAction1,ArrAction2);
	        toolBar.imagePath = "../images/buttonbar/";
	        toolBar.titleStr = "����ʱ�䶨��";
	        toolBar.align="center";
	        toolBar.treeViewTitle = new Array("&nbsp;","<img src='../images/buttonbar/t_check.gif' onclick=\"sdAll('mdc');\"/>","����","��һ�ι���ʱ��","�ڶ��ι���ʱ��","������","�ڼ���","");
			toolBar.treeViewWidth = new Array("2%","1%","12%","16%","16%","","20%","2%");
	      	toolBar.print();
	    }
	    printTool();
     </script>
  </head>
  	
  	<body>
  		<jsp:include page="/message/MessageProcess"/>
  		<div style="overflow-y:scroll;height:75%;width:100%;left:1px;margin-left:0px" align="left">
  		<form name="mainFrm" action="" method="post">
  			<table width="100%" border="0" bordercolor="#666666" id="tab">
  			<% 
  				if(ds != null && !ds.isEmpty()){
  					for(int i=0;i<ds.getSize();i++){
  						SfWorkScheduleDTO sd = (SfWorkScheduleDTO)ds.getDTO(i);
			%>  						
  					<tr class="dataTR">
  						<td width="1%"></td>
  						<td width="2%" align="center">
  							&nbsp;<input type="checkbox" name="mdc" value="<%=sd.getWorkScheduleId()%>"/>
  						</td>
  						<td width="12%"  align="center" onclick="do_ShowDetail('<%= sd.getWorkScheduleId()%>');">
  							<%= sd.getWorkScheduleName()%>
  						</td>
  						<td width="16%" align="center" onclick="do_ShowDetail('<%= sd.getWorkScheduleId()%>');">
  							<%= sd.getWorkBegin1()%>-<%= sd.getWorkEnd1()%>
  						</td>
  						<td width="16%" align="center" onclick="do_ShowDetail('<%= sd.getWorkScheduleId()%>');">
  							<%= sd.getWorkBegin2()%>-<%= sd.getWorkEnd2()%>
  						</td>
  						<td width="" align="center" onclick="do_ShowDetail('<%= sd.getWorkScheduleId()%>');">  
  							<%= sd.getWorkStr()%>
  						</td>
  						<td width="20%" align="center" onclick="do_ShowDetail('<%= sd.getWorkScheduleId()%>');">
  							<%= sd.getHolidays()%>
  						</td>
  					</tr>	
  			<%		
  					} 
  				}
  			%>
  			</table>
  			
  			
  		</form>
  		
  		</div>
  		<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
		<%= WebConstant.WAIT_TIP_MSG%>
  	</body>
  </html>
  
  <script type="text/javascript">
	 <!-- 
	function create(){//�½�
		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfWorkScheduleServlet?act=<%= WebActionConstant.NEW_ACTION %>");
	}
  	
	function do_ShowDetail(id){//��ϸ
		window.location.assign(
		"/servlet/com.sino.sinoflow.servlet.SfWorkScheduleServlet?act=<%= WebActionConstant.DETAIL_ACTION %>&workScheduleId="+id);
	}
	
	function del(){//ɾ��
		var tc = document.getElementsByName("mdc");
		for(var i=0;i<tc.length;i++){
			if(tc.item(i).checked){
				if(confirm("ȷ��ɾ����ѡ���� ����������ȷ���� ��������ȡ����")){
					mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfWorkScheduleServlet?act=<%= WebActionConstant.DELETE_ACTION %>";
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
       	window.location.assign("/servlet/com.sino.sinoflow.servlet.SfWorkScheduleServlet");
   	}
	  	
	  //-->
  </script>