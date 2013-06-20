<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%
	AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)request.getAttribute(QueryConstant.QUERY_DTO);
	RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
	boolean hasData = (rows != null && !rows.isEmpty());
	String pageTitle = request.getParameter("pageTitle");
	if(pageTitle == "TD�ʲ���ѯ"){
		pageTitle = "TD�ʲ���ѯ";
	} else {
        pageTitle = "MIS�ʲ�̨��";
    }
	SfUserDTO userAccount = (SfUserDTO)SessionUtil.getUserAccount(request);
	
	String allResName = (String) request.getAttribute( WebAttrConstant.ALL_RES_NAME );
	
	int topHeight = 140;
	String tbHeight = "65%";
	if( dto.getSearchType().equals( WebAttrConstant.SEARCH_ADVANCE  ) ){
		topHeight = 150;
		tbHeight = "65%";
	}else{
		topHeight = 130;
		tbHeight = "68%";
	}
%>

<html> 
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>MIS�ʲ�̨��</title>
 </head>
<body leftmargin="0" topmargin="0" onkeydown="autoExeFunction('do_Search();')" onload="do_SetPageWidth();">
<jsp:include page="/public/exportMsg.jsp"/>
<form name="mainFrm" method="post" action="/servlet/com.sino.ams.newasset.servlet.MisAssetsQueryServlet">
    <%=WebConstant.WAIT_TIP_MSG%>
<script type="text/javascript">
    printTitleBar("<%=allResName%>");
    
    var searchType = "<%= dto.getSearchType() %>";
    
    var myArrAction0 = new Array(true, "��ѯ", "action_view.gif", "��ѯ", "do_Search");
    var myArrAction1 = new Array(true, "����", "toexcel.gif", "����", "do_ShowExcel");
   	var myArrAction2 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "doClose"); 
   	
   	var myArrAction3 = new Array(true, "��ͨ����", "actn023.gif", "��ͨ����", "do_PtSearch");
    var myArrAction4 = new Array(true, "�߼�����", "actn023.gif", "�߼�����", "do_AdSearch");
    
   	if( searchType == "<%= WebAttrConstant.SEARCH_ADVANCE %>" ){
   		ArrActions = new Array( myArrAction0,myArrAction3,myArrAction1,myArrAction2 ); 
   	}else{
   		ArrActions = new Array( myArrAction0,myArrAction4,myArrAction1,myArrAction2 ); 
   	}
   	
	printToolBar();
    function doClose(){
    	window.close();
    }    
    
    function do_PtSearch(){
    	document.getElementById("searchType").value = "";
    	document.forms[0].submit();
    }
    
    function do_AdSearch(){
    	document.getElementById("searchType").value = "<%= WebAttrConstant.SEARCH_ADVANCE %>";
    	document.forms[0].submit();
    }
</script>
    
    
    <input readonly name="act" type="hidden">
     
    <input name="projectNumber" type="hidden" value="<%=dto.getProjectNumber()%>">
    <input name="contentCode" type="hidden" value="<%=dto.getContentCode()%>"> 
    <input type="hidden" name="excelType"/>
    <input name="isTD" type="hidden" value="<%=dto.getIsTD()%>">
    <input name="pageTitle" type="hidden" value="<%=dto.getPageTitle()%>">
    
    <input name="searchType" id="searchType" type="hidden" value="<%=dto.getSearchType()%>"> 
	
	<table border="0" width="100%" class="queryTable">
		<%
		if( dto.getSearchType().equals( WebAttrConstant.SEARCH_ADVANCE  ) ){
		%>
		 <tr height="20">
            <td width="10%" class="queryTable_lable"> ��˾���ƣ�</td>
            <td width="15%">
            	<% if( userAccount.isProvinceUser() ){ %>
            		<input class="blueborderYellow" style="width:80%" readonly id="companyName" name="companyName" value="<%=dto.getCompanyName()%>" type="text"><a href="" onclick="do_SelectCompany(); return false;"  title="���ѡ��˾">[��]</a>
                	<input name="companyCode" type="hidden" value="<%=dto.getCompanyCode()%>" />
                <% }else{ %> 
                	<select  name="companyCode" id="companyCode" class="select_style1" style="width:80%" size="1"><%=dto.getOrgOption()%></select>
                <% } %> 
            </td>
            <td width="10%" class="queryTable_lable"> ��ǩ�ţ�</td>
            <td width="15%">
                <input class="input_style1" style="width:80%" name="misTagNumber" value="<%=dto.getMisTagNumber()%>" type="text" size="20"></td>
            <td width="10%" class="queryTable_lable"> �豸���ƣ�</td>
            <td width="15%">
                <input class="input_style1" style="width:80%" name="assetsDescription" value="<%=dto.getAssetsDescription()%>" type="text">
            </td>
             <td width="10%" class="queryTable_lable">����ͺţ�</td>
            <td width="15%">
                <input class="input_style1" type="text"  name="modelNumber" value="<%=dto.getModelNumber()%>" style="width:80%">
            </td>
        </tr>
        <tr height="20">
            <td  class="queryTable_lable">�������ڣ�</td>
            <td >
                <input class="input_style2" type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:80%;cursor:hand" title="���ѡ��ʼ����" readonly
                       onclick="gfPop.fStartPop(startDate, endDate)">
            </td>
            <td  class="queryTable_lable">����</td>
            <td >
                <input class="input_style2" type="text" name="endDate" value="<%=dto.getEndDate()%>" style="width:80%;cursor:hand" title="���ѡ���ֹ����" readonly
                       onclick="gfPop.fEndPop(startDate, endDate)">
            </td>
            <td  class="queryTable_lable">�������ڣ�</td>
            <td >
                <input class="input_style2" type="text" name="startCreationDate" value="<%=dto.getStartCreationDate()%>" style="width:80%;cursor:hand" title="���ѡ��ʼ����" readonly
                       onclick="gfPop.fStartPop(startCreationDate, endCreationDate)">
            </td>
            <td  class="queryTable_lable">����</td>
            <td >
                <input class="input_style2" type="text" name="endCreationDate" value="<%=dto.getEndCreationDate()%>" style="width:80%;cursor:hand" title="���ѡ���ֹ����" readonly
                       onclick="gfPop.fEndPop(startCreationDate, endCreationDate)">
            </td>
        </tr>
        <tr height="20">
            <td  class="queryTable_lable"> �ɱ����ģ�</td>
            <td >
                <input class="input_style1" style="width:80%" readonly name="costCenterCode" value="<%=dto.getCostCenterCode()%>" type="text"><a href="" onclick="do_SelectCostCenter(); return false;"
                                                                                                                   title="���ѡ��ɱ�����">[��]</a>
            </td>
            <td  class="queryTable_lable"> ��Ŀ���ƣ�</td>
            <td >
                <input class="input_style1" readonly title="<%=dto.getProjectName()%>"  style="width:80%" type="text" name="projectName" value="<%=dto.getProjectName()%>"><a href="" onclick="do_SelectProject(); return false;"
                                                                                                             title="���ѡ����Ŀ">[��]</a>
            </td>
            <td  class="queryTable_lable"> �ص��ţ�</td>
            <td >
                <input class="input_style1" type="text" name="assetsLocationCode" value="<%=dto.getAssetsLocationCode()%>" style="width:80%"><a href="" onclick="do_SelectAddress(); return false;"
                                                                                                                           title="���ѡ��ص�">[��]</a>
            </td>
            <td  class="queryTable_lable">�����ˣ�</td>
            <td >
                <input class="input_style1" style="width:80%" name="assignedToName" value="<%=dto.getAssignedToName()%>" type="text"><a href="" onclick="do_SelectPerson(); return false;"
                                                                                                                   title="���ѡ��������">[��]</a>
            </td>
        </tr>
        <tr height="20">
        	<%--
            <td width="10%" class="queryTable_lable">Ӧ������</td>
            <td width="15%">
                <input class="input_style1" type="text" name="faCategory1" value="<%=dto.getFaCategory1()%>" style="width:80%">
            </td>
             --%>
            <td class="queryTable_lable">�ʲ����</td>
            <td >
                <input class="input_style1" readonly type="text" name="faCategory2" value="<%=dto.getFaCategory2()%>" style="width:80%"><a href="" title="���ѡ��Ŀ¼" onclick="do_SelectContent(); return false;">[��]</a>
            </td>
			<td  class="queryTable_lable">�����ʲ���</td>
            <td >
                <select  name="isOverageAssets" id="isOverageAssets" class="select_style1" style="width:80%">
                	<option value="" <%if (dto.getIsImportantAssets().equals("")) {%> selected <%}%>>--��ѡ��--</option>
                	<option value="Y" <%if (dto.getIsOverageAssets().equals("Y")) {%> selected <%}%>>��</option>
                	<option value="N" <%if (dto.getIsOverageAssets().equals("N")) {%> selected <%}%>>��</option>
                </select>
            </td>
            <td  class="queryTable_lable">��Ҫ�����ʲ���</td>
            <td >
                <select  name="isImportantAssets" id="isImportantAssets" class="select_style1" style="width:80%">
                	<option value="" <%if (dto.getIsImportantAssets().equals("")) {%> selected <%}%>>--��ѡ��--</option>
                	<option value="Y" <%if (dto.getIsImportantAssets().equals("Y")) {%> selected <%}%>>��</option>
                	<option value="N" <%if (dto.getIsImportantAssets().equals("N")) {%> selected <%}%>>��</option>
                </select>
            </td>
            <td width="10%" class="queryTable_lable">�Ƿ񱨷ϣ�</td>
            <td width="15%">
                <select name="discarded" style="width:80%">
                    <option value="0" <%if(dto.getDiscarded().equals("0")){%>selected<%}%>>δ����</option>
                    <option value="1" <%if(dto.getDiscarded().equals("1")){%>selected<%}%>>����</option>
                </select>
            </td>
        </tr>
		<% }else{ %>  
		<tr height="20">
            <td width="10%" class="queryTable_lable"> ��˾���ƣ�</td>
            <td width="15%">
            	<% if( userAccount.isProvinceUser() ){ %>
            		<input class="blueborderYellow" style="width:80%" readonly name="companyName" value="<%=dto.getCompanyName()%>" type="text"><a href="" onclick="do_SelectCompany(); return false;"  title="���ѡ��˾">[��]</a>
                	<input name="companyCode" type="hidden" value="<%=dto.getCompanyCode()%>" />
                <% }else{ %> 
                	<select  name="companyCode" id="companyCode" class="select_style1" style="width:80%" size="1"><%=dto.getOrgOption()%></select>
                <% } %> 
            </td>
            <td width="10%" class="queryTable_lable"> ��ǩ�ţ�</td>
            <td width="15%">
                <input class="input_style1" style="width:80%" name="misTagNumber" value="<%=dto.getMisTagNumber()%>" type="text" size="20"></td>
            <td width="10%" class="queryTable_lable"> �豸���ƣ�</td>
            <td width="15%">
                <input class="input_style1" style="width:80%" name="assetsDescription" value="<%=dto.getAssetsDescription()%>" type="text">
            </td>
             <td width="10%" class="queryTable_lable">����ͺţ�</td>
            <td width="15%">
                <input class="input_style1" type="text"  name="modelNumber" value="<%=dto.getModelNumber()%>" style="width:80%">
            </td>
        </tr> 
        <tr height="20">
            <td  class="queryTable_lable"> �ɱ����ģ�</td>
            <td >
                <input class="input_style1" style="width:80%" readonly name="costCenterCode" value="<%=dto.getCostCenterCode()%>" type="text"><a href="" onclick="do_SelectCostCenter(); return false;"
                                                                                                                   title="���ѡ��ɱ�����">[��]</a>
            </td>
            <td  class="queryTable_lable"> ��Ŀ���ƣ�</td>
            <td >
                <input class="input_style1" readonly title="<%=dto.getProjectName()%>"  style="width:80%" type="text" name="projectName" value="<%=dto.getProjectName()%>"><a href="" onclick="do_SelectProject(); return false;"
                                                                                                             title="���ѡ����Ŀ">[��]</a>
            </td>
            <td  class="queryTable_lable"> �ص��ţ�</td>
            <td >
                <input class="input_style1"  type="text" name="assetsLocationCode" value="<%=dto.getAssetsLocationCode()%>" style="width:80%"><a href="" onclick="do_SelectAddress(); return false;"
                                                                                                                           title="���ѡ��ص�">[��]</a>
            </td>
            <td width="10%" class="queryTable_lable">�Ƿ񱨷ϣ�</td>
            <td width="15%">
                <select name="discarded" style="width:80%">
                    <option value="0" <%if(dto.getDiscarded().equals("0")){%>selected<%}%>>δ����</option>
                    <option value="1" <%if(dto.getDiscarded().equals("1")){%>selected<%}%>>����</option>
                </select>
            </td>
        </tr>
        <tr height="20">
        	<%--
            <td width="10%" class="queryTable_lable">Ӧ������</td>
            <td width="15%">
                <input class="input_style1" type="text" name="faCategory1" value="<%=dto.getFaCategory1()%>" style="width:80%">
            </td>
             --%>
            <td class="queryTable_lable">�ʲ����</td>
            <td >
                <input class="input_style1" readonly type="text" name="faCategory2" value="<%=dto.getFaCategory2()%>" style="width:80%"><a href="" title="���ѡ��Ŀ¼" onclick="do_SelectContent(); return false;">[��]</a>
            </td>
			<td  class="queryTable_lable">�����ʲ���</td>
            <td >
                <select  name="isOverageAssets" id="isOverageAssets" class="select_style1" style="width:80%">
                	<option value="" <%if (dto.getIsImportantAssets().equals("")) {%> selected <%}%>>--��ѡ��--</option>
                	<option value="Y" <%if (dto.getIsOverageAssets().equals("Y")) {%> selected <%}%>>��</option>
                	<option value="N" <%if (dto.getIsOverageAssets().equals("N")) {%> selected <%}%>>��</option>
                </select>
            </td>
            <td  class="queryTable_lable">��Ҫ�����ʲ���</td>
            <td >
                <select  name="isImportantAssets" id="isImportantAssets" class="select_style1" style="width:80%">
                	<option value="" <%if (dto.getIsImportantAssets().equals("")) {%> selected <%}%>>--��ѡ��--</option>
                	<option value="Y" <%if (dto.getIsImportantAssets().equals("Y")) {%> selected <%}%>>��</option>
                	<option value="N" <%if (dto.getIsImportantAssets().equals("N")) {%> selected <%}%>>��</option>
                </select>
            </td>
        </tr> 
       	<% } %>
        <%-- 
        <tr height="25px">
            <td colspan="8" align="right" class="queryTableButton">
                <img src="/images/eam_images/search.jpg" alt="��ѯ" onclick="do_Search();">
                <img src="/images/eam_images/export.jpg" alt="����EXCEL��CSV" onclick="do_ShowExcel()">
                
                <div id="ddDiv" align="center" style="position:absolute;z-index:2;top:260px;left:450px;background-color:azure;border:1px;width:300px;height:50px;text-align:center;visibility:hidden;">
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
				</div>
            </td>
        </tr>
		--%>
    </table>	
   <div id="ddDiv" align="center" style="position:absolute;z-index:2;top:260px;left:450px;background-color:azure;border:1px;width:300px;height:50px;text-align:center;visibility:hidden;">
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
	</div> 
</form>
<%

String[] widthArr = { "3%","3%","3%","3%","5%",
					  "5%","5%","1%","1%","3%",	
					  "6%","2%","2%","2%","4%",
					  "2%","1%","2%","2%","2%",
					  "2%","2%","2%","2%","2%",
					  
					  "2%","2%","2%","2%","2%",
					  "3%","3%","3%","6%","7%",
					  "3%" };
int arrIndex = 0; 
%>
<div id="aa" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:<%= topHeight %>px;left:0px;width:100%" class="crystalScroll">
	<table class="eamHeaderTable" border="1" width="500%">
		<tr height="22">
			<td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��ǩ��</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ʲ����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">Ӧ������</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ʲ�������</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ʲ����</td>

            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�豸����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">����ͺ�</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��λ</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ص���</td>

            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ص�����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">������</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">Ա�����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��Ŀ���</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��Ŀ����</td>

            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��Ŀ����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��������</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��������</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">ԭʼ�ɱ�</td>

			<td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��ֵ</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��ǰ�ɱ�</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ʲ�����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ʲ���ֵ</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�����۾ɶ�</td>
            
			<td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�����۾ɶ�</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ۼ��۾�</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ʲ���ֵ׼��</td>
			<td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�����ֵ׼��</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��ֵ׼���ۼ�</td>

            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">��˾����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ʲ��ʲ�����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�ʲ��ʲ�����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�۾��ʻ�����</td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">�۾��ʻ�����</td>
            
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center">����״̬</td>
		</tr>
	</table>
</div>		
<div style="overflow:scroll;height:<%= tbHeight %>;width:100%;position:absolute;top:<%= topHeight + 22 %>px;left:0px" align="left" onscroll="document.getElementById('aa').scrollLeft = this.scrollLeft;">
    <table class="eamLineTable"  width="500%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all;">
<%
	if(hasData){
		Row row = null;
		for(int i = 0; i < rows.getSize(); i++){
			row = rows.getRow(i);
			arrIndex = 0;
%>	
		<tr height="22" class="dataTR">
			<td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("MIS_TAG_NUMBER")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSET_NUMBER")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("FA_CATEGORY1")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("SEGMENT2")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("FA_CATEGORY2")%>"></td>

            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("ASSETS_DESCRIPTION")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("MODEL_NUMBER")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("CURRENT_UNITS")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("UNIT_OF_MEASURE")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION_CODE")%>"></td>

            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("ASSETS_LOCATION")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("ASSIGNED_TO_NAME")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSIGNED_TO_NUMBER")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("PROJECT_NUMBER")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("PROJECT_NAME")%>"></td>

            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("PROJECT_TYPE")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("LIFE_IN_YEARS")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("ASSETS_CREATE_DATE")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("ORIGINAL_COST")%>"></td>
 
			<td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("NET_ASSET_VALUE")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("COST")%>"></td>
             <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("DEPRN_COST")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("SCRAP_VALUE")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("DEPRN_AMOUNT")%>"></td>
            
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("YTD_DEPRN")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("DEPRN_RESERVE")%>"></td>
             <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("IMPAIR_AMOUNT")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("YTD_IMPAIRMENT")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput3" readonly value="<%=row.getValue("IMPAIR_RESERVE")%>"></td>

            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("COMPANY")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("BOOK_TYPE_CODE")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("BOOK_TYPE_NAME")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("DEPRECIATION_ACCOUNT")%>"></td>
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput" readonly value="<%=row.getValue("DEPRECIATION_ACCOUNT_NAME")%>"></td>
            
            <td width="<%= widthArr[ arrIndex ++  ] %>" align="center"><input class="finput2" readonly value="<%=row.getValue("IS_RETIREMENTS")%>"></td>
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
<div style="position:absolute;top:90%;left:0; right:20"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
	}
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>

</html>
<script>
function do_Search(){
	var companyCode = document.getElementById("companyCode").value;
	if( companyCode == "" ){
		alert( "��ѡ��˾" );
		return false;
	}
	mainFrm.act.value = "<%=AssetsActionConstant.QUERY_ACTION%>";
	mainFrm.submit();
	document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
}

function do_ShowExcel() {
	var _d = document.getElementById("ddDiv");
	var left = event.clientX;
	var top = event.clientY;
	_d.style.position = "absolute";
	_d.style.top = top + event.srcElement.offsetHeight;
	_d.style.left = left - 80;
	if (_d.style.visibility == "hidden") {
		_d.style.visibility = "visible";
	}else {
		_d.style.visibility = "hidden";
	}
}

function do_Export(type) {
/*
	if(!document.$$$WebGridSystemFrm$$$){
		alert("����ִ�в�ѯ���ٵ���");
		return;
	}
	var totalRecord = Number($$$WebGridSystemFrm$$$.$$$WebGridTotalRecord$$$.value);
	if(totalRecord > 5000){
		alert("��ǰ�����¼�¼�����࣬��������Ӧ���������¼�����ٵ���");
		return;
	}
*/
	var companyName = "" ;
	var companyCode = "" ;
	if( document.getElementById("companyName") ){
		companyName = document.getElementById("companyName").value;
	}
	
	if( document.getElementById("companyCode") ){
		companyCode = document.getElementById("companyCode").value;
	}
	
	if( companyName == "" && companyCode == "" ){
		alert( "��ѡ��˾" );
		return false;
	}
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.excelType.value = type;
    mainFrm.submit();
    
    mainFrm.act.value = "";
	closeExportDiv();
}

function do_SelectAddress() {
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_MISLOC%>";
    var dialogWidth = 55;
    var dialogHeight = 30;
    var userParameters = "multipleChose=true";
    var returnValues = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userParameters);
    var project = new Object();
    project["assetsLocationCode"] = "";
    //project["projectNumber"] = "";
    if (returnValues) {
        var valueCount = returnValues.length;
        for (var i = 0; i < valueCount; i++) {
            var returnValue = returnValues[i];
            project["assetsLocationCode"] += "'" + returnValue["assetsLocationCode"] + "'";
            //project["projectNumber"] += "'" + returnValue["projectNumber"] + "'";
            if(i < valueCount - 1){
                project["assetsLocationCode"] += ", ";
                //project["projectNumber"] += ", ";
            }
        }
    }
    dto2Frm(project, "mainFrm");
}
/**
function do_SelectAddress(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_MISLOC%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		mainFrm.assetsLocationCode.value = obj["assetsLocationCode"];
	}
}
**/

function do_SelectPerson(){
	var lookUpName = "LOOK_UP_PERSON";
	var dialogWidth = 47;
	var dialogHeight = 28;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if(objs){
		var obj = objs[0];
		mainFrm.assignedToName.value = obj["userName"];
	}
}

function do_SelectProject() {
    var lookUpName = "LOOKUP_PROJECT";
    var dialogWidth = 55;
    var dialogHeight = 30;
    var userParameters = "multipleChose=true";
    var returnValues = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userParameters);
    var project = new Object();
    project["projectName"] = "";
    project["projectNumber"] = "";
    if (returnValues) {
        var valueCount = returnValues.length;
        for (var i = 0; i < valueCount; i++) {
            var returnValue = returnValues[i];
            project["projectName"] += "'" + returnValue["projectName"] + "'";
            project["projectNumber"] += "'" + returnValue["projectNumber"] + "'";
            if(i < valueCount - 1){
                project["projectName"] += ", ";
                project["projectNumber"] += ", ";
            }
        }
    }
    dto2Frm(project, "mainFrm");
}


/**
function do_SelectProject() {
	var lookUpName = "LOOKUP_PROJECT2";
	var dialogWidth = 50;
	var dialogHeight = 30;
	var objs = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		mainFrm.projectName.value = obj["projectName"];
	}
}
**/


function do_SelectCostCenter() {
    var lookUpName = "LOOK_UP_COST";
    var dialogWidth = 55;
    var dialogHeight = 30;
    
    var userParameters = "companyCode="+document.mainFrm.companyCode.value + "&multipleChose=true";
    //var userParameters = "organizationId="+document.mainFrm.organizationId.value + "&multipleChose=true";
    var returnValues = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userParameters);
    var project = new Object();
    project["costCenterCode"] = "";
    //project["projectNumber"] = "";
    if (returnValues) {
        var valueCount = returnValues.length;
        for (var i = 0; i < valueCount; i++) {
            var returnValue = returnValues[i];
            project["costCenterCode"] += "'" + returnValue["costCode"] + "'";
            if(i < valueCount - 1){
                project["costCenterCode"] += ", ";
            }
        }
    }
    dto2Frm(project, "mainFrm");
}

/**
function do_SelectCostCenter(){
	var lookUpName = "LOOK_UP_COST";
    var userPara="organizationId="+document.mainFrm.organizationId.value;
    var dialogWidth = 50;
	var dialogHeight = 28;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight,userPara);
	if (objs) {
		var obj = objs[0];
		document.mainFrm.costCenterCode.value = obj["costCode"];
	}
}
**/
function do_SelectContent() {
    var lookUpName = "LOOK_UP_CONTENT";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var userParameters = "multipleChose=true";
    var returnValues = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userParameters);
    var content = new Object();
    content["faCategory2"] = "";
    content["contentCode"] = "";
    if (returnValues) {
        var valueCount = returnValues.length;
        var contentCode ;
        for (var i = 0; i < valueCount; i++) {
            var returnValue = returnValues[i];
            content["faCategory2"] += "'" + returnValue["contentName"] + "'";
            contentCode = returnValue["contentCode"].substring(3,14);
            content["contentCode"] += "'" + contentCode + "'";
            if(i < valueCount - 1){
                content["faCategory2"] += ", ";
                content["contentCode"] += ", ";
            }
        }
    }
    dto2Frm(content, "mainFrm");
}

function do_SelectCompany() {
    var lookUpName = "LOOK_UP_MIS_COMPANY";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var userParameters = "multipleChose=true";
    var returnValues = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userParameters);
    var content = new Object();
    content["companyCode"] = "";
    content["companyName"] = "";
    if (returnValues) {
        var valueCount = returnValues.length;
        var contentCode ;
        for (var i = 0; i < valueCount; i++) {
            var returnValue = returnValues[i];
            content["companyCode"] += "" + returnValue["organizationId"] + "";
            content["companyName"] += "'" + returnValue["companyName"] + "'";
            
            if(i < valueCount - 1){
                content["companyCode"] += ", ";
                content["companyName"] += ", ";
            }
        }
    }
    dto2Frm(content, "mainFrm");
}


</script>