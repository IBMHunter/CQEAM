<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.constant.web.WebConstant"%>
<%@ page import="com.sino.base.dto.DTOSet"%>
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%@ page import="com.sino.base.util.StrUtil"%>
<%@ page import="com.sino.traskmoting.dto.SfActInfoDTO"%>


<html>
	<head>
		<title>���̼��</title>
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
		<script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
		<script language="javascript" src="/WebLibary/js/clientRowSet.js"></script>
		<script language="javascript" src="/WebLibary/js/expendCollapse.js"></script>
		<script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
	</head>
	<body>
    <form name="listform" action="/servlet/com.sino.sinoflow.servlet.SfActInfoTaskServlet" method="POST">
        <jsp:include
			page="/message/MessageProcess" />
        <script type="text/javascript">
       function printTool(){
            var ArrAction = new Array("չ��", "act_expand.gif","doExpandAll","zk_t");
            var ArrAction1 = new Array("����", "act_collapse.gif","doCollapseAll","zd_t");
            var ArrAction3 = new Array("ˢ��", "act_refresh.gif","doReload","sh_t");
            var ArrAction4 = new Array("�����<select style='width:100' id='check'  onchange='getArrActionValue(this)'><option value='0'>--��ѡ��--</option><option value='users'>���û�</option><option value='role'>����ɫ</option><option value='traskName'>������</option><option value='Otrole'>��ʱ/����ɫ</option><option value='traskOtName'>��ʱ/������</option><option value='Otusers'>��ʱ/���û�</option><option value='Enote'>���߰�</option></select>","","doNull","sh_t");
//            var ArrAction5 = new Array("�߰�", "../buttonbar/mn_alarm.gif", "doENoteActs", "cb_t");
            var toolBar = new SinoPrintToolBar();
//	        toolBar.SinoActions = new Array(ArrAction,ArrAction1,ArrAction4,ArrAction5);
            toolBar.SinoActions = new Array(ArrAction, ArrAction1, ArrAction4);
            toolBar.imagePath = "../images/buttonbar/";
            toolBar.titleStr = "���̼��";
            toolBar.treeViewTitle = new Array("","��������","�ؼ���","����","����","ǩ��ʱ��","Ԥ��ʱ��","");
            toolBar.treeViewWidth = new Array("2%","19%","19%","19%","10%","15%","10%","2%");
               toolBar.print();
        }
        var getValue='<%=request.getParameter("checkValue")%>';
        window.onload=Intload;
         function Intload()
        {
          var intValue=document.getElementById("check");
          for(var i=0;i<intValue.length;i++)
          {
             if(getValue==intValue[i].value)
             {
                 intValue[i].selected=true;
                 break;
             }
           }
        }
        function getArrActionValue(obj)
        {
            var checkValue =obj.options[obj.selectedIndex].value;
            reqServlet(checkValue);
        }
         printTool();
     </script>
     </form>
        <%
			DTOSet ds = (DTOSet) request
					.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
		%>
		<div
			style="position:absolute; overflow-y: auto; overflow-x: auto; height:80%; width: 100%; left: 1px; margin-left: 0px"
			align="left">
		 
			<input type="hidden" name="act" value=""/>
				<table border= 1 width="100%">
					<%
						if (ds != null && !ds.isEmpty()) {
							SfActInfoDTO std = (SfActInfoDTO) ds.getDTO(0);
					%>
					<tr class="dataTR">
						<td width="2%">
							<input type="checkbox" name="mdc"
								value="<%=std.getSfactActId()%>" />
						</td>
						<td align="left" width="19%"
							onclick="do_ShowDetail('<%=std.getSfactActId()%>'); return false;"><%=std.getSfactTaskName()%></td>
						<td align="left" width="19%"
							onclick="do_ShowDetail('<%=std.getSfactActId()%>'); return false;"><%=std.getSfactApplColumn1()%></td>
						<td align="left" width="19%"
							onclick="do_ShowDetail('<%=std.getSfactActId()%>'); return false;"><%=std.getSfactApplColumn2()%></td>
						<td align="left" width="19%"
							onclick="do_ShowDetail('<%=std.getSfactActId()%>'); return false;"><%=std.getSfactApplColumn2()%></td>
						<td align="left" width="10%"
							onclick="do_ShowDetail('<%=std.getSfactActId()%>'); return false;"><%=std.getSfactSignDate()%></td>
						<td align="left" width="15%"
							onclick="do_ShowDetail('<%=std.getSfactActId()%>'); return false;"><%=std.getSfactSignDueDate()%></td>
						<td width="2%">
					</tr>
					<%
						}
					%>
				</table>
			 
		<div><%=StrUtil.nullToString(request
							.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
		</div>
		<%=WebConstant.WAIT_TIP_MSG%>
        </div>
    <!--/form-->
    </body>
</html>

<script type="text/javascript" language="JavaScript">
	function do_ShowDetail(id){//��ѯ��ϸ
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
       	window.location.assign("/servlet/com.sino.traskmoting.servlet.SfActInfoTaskServlet?act=<%=WebActionConstant.DETAIL_ACTION%>&apiId="+id);
	}
	function reqServlet(name)
	{
	    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
		window.location.assign("/servlet/com.sino.traskmoting.servlet.SfActInfoTaskServlet?act=<%=WebActionConstant.QUERY_ACTION%>&checkValue="+name);
	}
	
	function doReload(){//ˢ��
		document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
       	window.location.assign("/servlet/com.sino.traskmoting.servlet.SfActInfoTaskServlet?act=");
   	}

    function doNull() {}
</script>
