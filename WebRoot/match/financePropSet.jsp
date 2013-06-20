<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.URLDefineList" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.CheckBoxProp" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ include file="/newasset/headerInclude.htm"%>
<%--
  created by YS
  Date: 2007-09-28
  Time: 8:23:36
--%>
<html>
<head>
    <title></title>
</head>
<body onkeydown="autoExeFunction('do_search()');">
<%
    RequestParser reqParser = new RequestParser();
    CheckBoxProp checkProp = new CheckBoxProp("systemId");
    reqParser.setCheckBoxProp(checkProp);
    reqParser.transData(request);
    String matchType = reqParser.getParameter("matchType");
    String itemName = reqParser.getParameter("itemName");
    String barcode = reqParser.getParameter("barcode");
    String workorderObjectNo = reqParser.getParameter("workorderObjectNo");
    String workorderObjectName = reqParser.getParameter("workorderObjectName");
    String item_code = reqParser.getParameter("item_code");
    String itemSpec = reqParser.getParameter("itemSpec");
    String prjId = reqParser.getParameter("prjId");
    String prjName = reqParser.getParameter("prjName");
%>

<form method="post" name="mainFrm">
    <script type="text/javascript">
        if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_SPARE)%>) {
            printTitleBar("����ȷ��")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_SPARE_RET)%>) {
            printTitleBar("��������")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL)%>) {
            printTitleBar("�ڽ�����ȷ��")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)%>) {
            printTitleBar("�ڽ����̳���")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_OTHER )%>) {
            printTitleBar("�豸����")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_0THER_RET)%>) {
            printTitleBar("�����豸����")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_RENT)%>) {
            printTitleBar("�����ʲ�ȷ��")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_RENT_RET)%>) {
            printTitleBar("�����ʲ�����")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_DG)%>) {
            printTitleBar("��ͨ�ʲ�ȷ��")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_DG_RET)%>) {
            printTitleBar("��ͨ�ʲ�����")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_LC)%>) {
            printTitleBar("��ֵ�׺��ʲ�ȷ��")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_LC_RET)%>) {
            printTitleBar("��ֵ�׺��ʲ�����")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_TD)%>) {
            printTitleBar("TD�ʲ�ȷ��")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_TD_RET)%>) {
            printTitleBar("TD�ʲ�����")
        } else if (<%=matchType.equals(WebAttrConstant.MATCH_MODE_CT)%>) {
            printTitleBar("��ͨ�ʲ�ȷ��")
        }
    </script>
    <table width="100%" border="0" class="queryTable">
    
    <%
    	if(matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL) || matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)){
    %>
    	<tr>
            <td width="8%" align="right">��ǩ�ţ�</td>
            <td width="25%" align="left"><input name="barcode" class="input_style1" style="width:80%" type="text" value="<%=barcode%>"></td>
            <td width="8%" align="right">�豸���ƣ�</td>
            <td width="25%"><input name="itemName" class="input_style1" style="width:80%" type="text" value="<%=itemName%>"></td>
            <td width="8%" align="right">�豸�ͺţ�</td>
            <td width="20%">
                <input type=text name=itemSpec class="input_style1" style="width:80%" value="<%=itemSpec%>">
                <input type="hidden" name=item_code value="<%=item_code%>"><a href=# title="���ѡ���豸�ͺ�" class="linka" onclick="do_SelectSpec();">[��]</a>
            </td>
            
        </tr>
        <tr>
            <td width="8%" align="right">�ص��ƣ�</td>
            <td width="20%" align="left">
                <input type=hidden name=workorderObjectNo value="<%=workorderObjectNo%>">
                <input name=workorderObjectName type=text class="input_style1" style="width:80%" value="<%=workorderObjectName%>"><a href=# title="���ѡ��ص�" class="linka" onclick="do_SelectProj();">[��]</a>
            </td>
            <td width="8%" align="right">��Ŀ���ƣ�</td>
            <td width="20%" align="left">
                <input type="text" name="prjName" value="<%=prjName %>" style="width:80%" class="input_style2" readonly onclick="do_SelectProject(); "><a href="" title="���ѡ����Ŀ" onclick="do_SelectProject(); return false;">[��]</a>
            	<input type= "hidden" name = "prjId" value = "<%=prjId %>">
            </td>
            <td width="8%"></td>
            <td align="center"  align = "right" width="20%"><img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ">&nbsp;&nbsp;&nbsp;&nbsp;
            <%
                if (matchType.equals(WebAttrConstant.MATCH_MODE_OTHER)) {
            %>
            	<img src="/images/eam_images/hide.jpg" style="cursor:'hand'" onclick="do_match();" alt="����">
            <%} else if(matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)
                        || matchType.equals(WebAttrConstant.MATCH_MODE_RENT_RET)
                        || matchType.equals(WebAttrConstant.MATCH_MODE_DG_RET)
                        || matchType.equals(WebAttrConstant.MATCH_MODE_TD_RET)
                        || matchType.equals(WebAttrConstant.MATCH_MODE_LC_RET)) {%>
            	<img src="/images/eam_images/revoke.jpg" style="cursor:'hand'" onclick="cancleDistributePrj();" alt="����">
            <% } else {
            %>
            	<img src="/images/eam_images/ok.jpg" style="cursor:'hand'" onclick="distributePrj();" alt="ȷ��">
            <%}%>
            </td>
        </tr>
    <%		
    	} else {
    %>
        <tr>
            <td width="10%" align="right">��ǩ�ţ�</td>
            <td width="25%" align="left"><input name="barcode" class="input_style1" style="width:80%" type="text" value="<%=barcode%>"></td>
            <td width="10%" align="right">�豸���ƣ�</td>
            <td width="25%"><input name="itemName" class="input_style1" style="width:80%" type="text" value="<%=itemName%>"></td>
            <td align="center" valign="top" width="10%"><img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ"></td>
        </tr>
        <tr>
            <td width="10%" align="right">�ص��ƣ�</td>
            <td width="25%" align="left">
                <input type=hidden name=workorderObjectNo value="<%=workorderObjectNo%>">
                <input name=workorderObjectName type=text class="input_style1" style="width:80%" value="<%=workorderObjectName%>"><a href=# title="���ѡ��ص�" class="linka" onclick="do_SelectProj();">[��]</a>
            </td>
            <td width="10%" align="right">�豸�ͺţ�</td>
            <td width="25%">
                <input type=text name=itemSpec class="input_style1" style="width:80%" value="<%=itemSpec%>">
                <input type="hidden" name=item_code value="<%=item_code%>"><a href=# title="���ѡ���豸�ͺ�" class="linka" onclick="do_SelectSpec();">[��]</a>
            </td>
            <%
                if (matchType.equals(WebAttrConstant.MATCH_MODE_OTHER)) {
            %>
            <td align="center"><img src="/images/eam_images/hide.jpg" style="cursor:'hand'" onclick="do_match();" alt="����"></td>
            <%} else if(matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)
                        || matchType.equals(WebAttrConstant.MATCH_MODE_RENT_RET)
                        || matchType.equals(WebAttrConstant.MATCH_MODE_DG_RET)
                        || matchType.equals(WebAttrConstant.MATCH_MODE_TD_RET)
                        || matchType.equals(WebAttrConstant.MATCH_MODE_LC_RET)) {%>
            <td align="center"><img src="/images/eam_images/revoke.jpg" style="cursor:'hand'" onclick="do_match();" alt="����"></td>
            <% } else {
            %>
            <td align="center"><img src="/images/eam_images/ok.jpg" style="cursor:'hand'" onclick="do_match();" alt="ȷ��">
            </td>
            <%}%>
        </tr>
        <%} %>
    </table>
    <input type="hidden" name="act" value="<%=reqParser.getParameter("act")%>">


	  <%
      	if(matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL) || matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)){
      %>
      	<div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
        <table width="100%" align="left" border="1" cellpadding="2" cellspacing="0" class="headerTable">
            <jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>"/>
            <tr>
                <td width="1%" align="center">
                    <input type="checkBox" name="controlBox" class="headCheckbox" onClick="checkAll(this.name, 'systemId')">
                </td>
                <td height="20" width="4%" align="center">��ǩ��</td>
                <td height="20" width="6%" align="center">�豸����</td>
                <td height="20" width="6%" align="center">�豸�ͺ�</td>
                <td height="20" width="6%" align="center">�豸רҵ</td>
                <td height="20" width="10%" align="center">�ص���</td>
                <td height="20" width="8%" align="center">������Ŀ����</td>
            </tr>

        </table>
    </div>
    <% } else { %>
    	<div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
        <table width="100%" align="left" border="1" cellpadding="2" cellspacing="0" class="headerTable">
            <jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>"/>
            <tr>
                <td width="5%" align="center">
                    <input type="checkBox" name="controlBox" class="headCheckbox" onClick="checkAll(this.name, 'systemId')">
                </td>
                <td height="20" width="10%" align="center">��ǩ��</td>
                <td height="20" width="17%" align="center">�豸����</td>
                <td height="20" width="20%" align="center">�豸�ͺ�</td>
                <td height="20" width="8%" align="center">�豸רҵ</td>
                <td height="20" width="25%" align="center">�ص���</td>
            </tr>

        </table>
    </div>
    <% } %>
    
    <%
        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        if (rows != null && !rows.isEmpty()) {
        	if(matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL) || matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)){
    %>
    
    <div style="overflow-y:scroll;height:302px;width:100%;left:1px;margin-left:0px"
         align="left">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
            %>
            <tr class="dataTR" onClick="executeClick(this)">
                <td width="1%" align="center"><input type="checkbox" name="systemId" id="systemId<%=i %>" value="<%=row.getStrValue("SYSTEM_ID")%>">
                                                     <!--onPropertyChange="setCheckBoxPropValue(this)">-->
                </td>
                <td style="word-wrap:break-word" height="20" width="4%"
                    align="center"><%=row.getValue("BARCODE")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="6%"
                    align="center"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="6%"
                    align="center"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="6%"
                    align="center"><%=row.getValue("ITEM_CATEGORY")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="10%"
                    align="center"><%=row.getValue("WORKORDER_OBJECT_NAME")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="8%" align="center">
                	<input type="text" id = "name<%=i %>" style="width:100%; border: 1px solid #FFFFFF;text-align: center" readonly value="<%=row.getValue("NAME")%>"></td>
                <%
                	}
                %>
            </tr>
	   	</table>
	   </div>
      <%
      	} else {
      %>	
       <div style="overflow-y:scroll;height:302px;width:100%;left:1px;margin-left:0px"
         align="left">
        <table width="100%" border="1" bordercolor="#666666">
            <%
                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
            %>
            <tr class="dataTR" onClick="executeClick(this)">
                <td width="5%" align="center"><input type="checkbox" name="systemId" id="systemId0" value="<%=row.getStrValue("SYSTEM_ID")%>">
                                                     <!--onPropertyChange="setCheckBoxPropValue(this)">-->
                </td>
                <td style="word-wrap:break-word" height="20" width="10%"
                    align="center"><%=row.getValue("BARCODE")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="17%"
                    align="center"><%=row.getValue("ITEM_NAME")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="20%"
                    align="center"><%=row.getValue("ITEM_SPEC")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="8%"
                    align="center"><%=row.getValue("ITEM_CATEGORY")%>
                </td>
                <td style="word-wrap:break-word" height="20" width="25%"
                    align="center"><%=row.getValue("WORKORDER_OBJECT_NAME")%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
      <%	
      		}
      	}
      %>
</form>

<div style="position:absolute;top:400px;left:0; right:20"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>

<jsp:include page="<%=URLDefineList.MESSAGE_PROCESS%>" flush="true"/>
<%=WebConstant.WAIT_TIP_MSG%>
</body>
</html>
<script type="text/javascript">
    function do_search() {
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainFrm.action = "<%=URLDefineList.FINANCE_PROP_SET_SERVLET%>?matchType=<%=matchType%>";
        mainFrm.submit();
    }
    
    //author : ����
    function distributePrj(){
    	var prjId = mainFrm.prjId.value;
		var prjName = mainFrm.prjName.value;
		if(prjId == null || prjId == "" || prjName == null || prjName == ""){
			alert("��δѡ��Ҫ�������Ŀ!");
			return;		
		}
		var checkedCount = getCheckedBoxCount("systemId");
        if (checkedCount < 1) {
            alert("������ѡ��һ�����ݣ�");
            return;
        } 
		var systemIds = document.getElementsByName("systemId");
		for(var x = 0; x < systemIds.length; x++){
			if(systemIds[x].value != null && systemIds[x].value != "" && systemIds[x].checked == true){
				var name = document.getElementById("name" + x).value;
				if(name != null && name != ""){
					if(confirm("�㵱ǰִ�з���������������ѷ������Ӧ��Ŀ���Ƿ�ȷ�ϼ���ִ�иò�����")){
					 	mainFrm.action = "<%=URLDefineList.FINANCE_PROP_SET_SERVLET%>?act=<%=AMSActionConstant.MATCH_ACTION%>&matchType=<%=matchType%>";
            			mainFrm.submit();
            			return;
					}
				}
			}
		}
		mainFrm.action = "<%=URLDefineList.FINANCE_PROP_SET_SERVLET%>?act=<%=AMSActionConstant.MATCH_ACTION%>&matchType=<%=matchType%>";
        mainFrm.submit();
    }
    
    function cancleDistributePrj(){
    	var checkedCount = getCheckedBoxCount("systemId");
        if (checkedCount < 1) {
            alert("������ѡ��һ�����ݣ�");
            return;
        } 
		var systemIds = document.getElementsByName("systemId");
		for(var x = 0; x < systemIds.length; x++){
			if(systemIds[x].value != null && systemIds[x].value != "" && systemIds[x].checked == true){
				var name = document.getElementById("name" + x).value;
				if(name != null && name != ""){
					if(confirm("�㵱ǰִ�з���������������ѷ������Ӧ��Ŀ���Ƿ�ȷ�ϼ���ִ�иò�����")){
					 	mainFrm.action = "<%=URLDefineList.FINANCE_PROP_SET_SERVLET%>?act=<%=AMSActionConstant.MATCH_ACTION%>&matchType=<%=matchType%>";
            			mainFrm.submit();
            			return;
					}
				}
			}
		}
    }
        
    function do_match() {
    	
        var checkedCount = getCheckedBoxCount("systemId");
        if (checkedCount < 1) {
            alert("������ѡ��һ�����ݣ�");
            return;
        } else {
//            mainFrm.act.value = "<%=AMSActionConstant.MATCH_ACTION%>";
            mainFrm.action = "<%=URLDefineList.FINANCE_PROP_SET_SERVLET%>?act=<%=AMSActionConstant.MATCH_ACTION%>&matchType=<%=matchType%>";
            mainFrm.submit();
        }
    }
    
    function do_SelectProj() {
        var lookUpAddr = "<%=LookUpConstant.LOOK_UP_ASSETS_OBJECT%>";
        var dialogWidth = 47.5;
        var dialogHeight = 30;
        var Addrs = getLookUpValues(lookUpAddr, dialogWidth, dialogHeight);
        if (Addrs) {
            var Addr = null;
            for (var i = 0; i < Addrs.length; i++) {
                Addr = Addrs[i];
                dto2Frm(Addr, "mainFrm");
            }
        }
    }    
    
    function do_SelectProject() {
        var lookUpProj = "<%=LookUpConstant.LOOK_UP_PROJECT%>";
        var dialogWidth = 50;
        var dialogHeight = 30;
        var projs = getLookUpValues(lookUpProj, dialogWidth, dialogHeight);
        if (projs) {
            var proj = null;
            for (var i = 0; i < projs.length; i++) {
                proj = projs[i];
                dto2Frm(proj, "mainFrm");
            }
        }else{
        	mainFrm.prjId.value = "";
        	mainFrm.prjName.value = "";
        }
    }
    
    function do_SelectSpec() {

        var lookUpSpec = "<%=LookUpConstant.LOOK_UP_ITEM_SIMPLE%>";
        var dialogWidth = 50.5;
        var dialogHeight = 30;
        var specs = getLookUpValues(lookUpSpec, dialogWidth, dialogHeight);
        if (specs) {
            var spec = null;
            for (var i = 0; i < specs.length; i++) {
                spec = specs[i];
                dto2Frm(spec, "mainFrm");
            }
        }
    }
    
   
</script>