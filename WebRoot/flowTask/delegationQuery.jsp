<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import = "com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.sinoflow.dto.SfValidationDTO"%>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import = "com.sino.base.util.StrUtil"%>
<%@page import="com.sino.sinoflow.dto.SfDelegationDTO"%>

<%
    DTOSet ds = (DTOSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
//    boolean hasData = (ds.getSize() > 0)? true : false;
%>

<html>
  <head>
    <title>delegation</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/cms_css/cms_css.css">
    <script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/clientRowSet.js"></script>
    <script type="text/javascript" src="/WebLibary/js/OperationProjectGroupRole.js"></script>
    <script language="javascript" src="/WebLibary/js/expendCollapse.js"></script>
    
     <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script> 
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarVar.js"></script>
    <script type="text/javascript"> 
    	<!--
        printTitleBar("ί�ɶ���");
//        var myArrAction0 = new Array(true, "����", "action_draft.gif", "����", "create");
        var myArrAction1 = new Array(true, "ˢ��", "act_refresh.gif", "ˢ��", "doReload");
        var myArrAction2 = new Array(true, "ɾ��", "del.gif", "ɾ��", "del");
        ArrActions = new Array(myArrAction1,myArrAction2 );
        printToolBar();

        var columnArr = new Array("","<img src='../images/buttonbar/t_check.gif'  onclick=\"sdAll('mdc');\"/>","�û�","ί���û�","�Ƿ�ί��","��ʼʱ��","����ʱ��","&nbsp;");
        var widthArr = new Array("4%","2%","25%","","8%","20%","20%","2%");
        printTableHead(columnArr,widthArr);
        //--> 
	 </script>
 
  </head>
  	<body>
  		<jsp:include page="/message/MessageProcess"/>
  		<div style="overflow-y:scroll;height:350px;width:100%;left:1px;margin-left:0px" align="left">
  		<form action="" method="post" name="mainFrm">
  			<table width="100%" border="0" class="queryHeadBg">
  			<%
  				if(ds != null && !ds.isEmpty()){
  					for(int i=0;i<ds.getSize();i++){
  						SfDelegationDTO sf = (SfDelegationDTO)ds.getDTO(i);
  			%>
  				<tr class="dataTR">
  					<td width="3%"></td>
  					<td width="2%" align="right"><input type="checkbox" name="mdc" value="<%= sf.getDelegationId()%>"/></td>
  					<td width="25%" align="center" onclick="do_ShowDetail('<%=sf.getDelegationId()%>');"><%=sf.getSName() %></td>
  					<td width="" align="center" onclick="do_ShowDetail('<%=sf.getDelegationId()%>');"><%=sf.getEName() %></td>
  					<td width="8%" align="center" onclick="do_ShowDetail('<%=sf.getDelegationId()%>');"><%= sf.getStatusCtl() == 1 ? "<img src='../images/buttonbar/viewyes.gif'/>":""%></td>
  					<td width="20%" align="center" onclick="do_ShowDetail('<%=sf.getDelegationId()%>');"><%=sf.getStartDate() %></td>
  					<td width="20%" align="center" onclick="do_ShowDetail('<%=sf.getDelegationId()%>');"><%=sf.getEndDate() %></td>
  					<td width="2%"></td>
  				</tr>
  			<%			
  					}
  				}
  			 %>	
  			</table>
  			 </form>
  		</div>
  		<%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
		<%=WebConstant.WAIT_TIP_MSG%>
  	</body>
  </html>
  
   <script type="text/javascript">
  	<!--
	function create(){//�½�
		window.location.assign("/servlet/com.sino.sinoflow.servlet.SfDelegationServlet?act=<%= WebActionConstant.NEW_ACTION %>");
	}
	  	
	function do_ShowDetail(id){//��ѯ��ϸ
		window.location.assign(
		"/servlet/com.sino.sinoflow.servlet.SfDelegationServlet?act=<%= WebActionConstant.DETAIL_ACTION %>&delegationId="+id);
	}
	
	function del(){//ɾ��
		var tc = document.getElementsByName("mdc");
		for(var i=0;i<tc.length;i++){
			if(tc.item(i).checked){
				if(confirm("ȷ��ɾ����ѡ���� ����������ȷ���� ��������ȡ����")){
					mainFrm.action = "/servlet/com.sino.sinoflow.servlet.SfDelegationServlet?act=<%= WebActionConstant.DELETE_ACTION %>";
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
       	window.location.assign("/servlet/com.sino.sinoflow.servlet.SfDelegationServlet");
   	}
  	//-->	
</script>
  