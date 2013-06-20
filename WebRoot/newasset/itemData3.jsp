<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.jsp" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%--
  Created by IntelliJ IDEA.
  User: T_suhuipeng
  Date: 2011-3-14
  Time: 14:13:39
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
<%
	String ADVANCE = "ADVANCE";
	String specialityDeptTitle = "ʵ�ﲿ��";
    SfUserDTO userAccount=(SfUserDTO)SessionUtil.getUserAccount(request);
    AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) request.getAttribute(QueryConstant.QUERY_DTO);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    boolean hasData = (rows != null && !rows.isEmpty());
    
    String searchType = StrUtil.nullToString( dto.getSearchType() );
    
    int topHeight = 161;
    int tbHeight = 310;
    if( searchType.equals( ADVANCE ) ){
    	topHeight = 205;
    	tbHeight = 310;
    }else{
    	topHeight = 78;
    	tbHeight = 390;
    }
%>
    <title>ʵ��̨�ʲ�ѯ</title>
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>

</head>
<body leftmargin="0" topmargin="0" onload="initPage();" onkeydown="autoExeFunction('do_Search();')">
<%-- =WebConstant.WAIT_TIP_MSG --%>
<%=AssetsWebAttributes.EXPORT_TIP_MSG%>

<form action="/servlet/com.sino.ams.newasset.servlet.ItemMaintainServlet3" method="post" name="mainFrm">
<script type="text/javascript">
    var myArrAction0 = new Array(true, "��ѯ", "action_draft.gif", "��ѯ", "do_Search");
    var myArrAction4 = new Array(true, "�߼�����", "action_draft.gif", "�߼�����", "do_AdSearch");
    var myArrAction1 = new Array(true, "����", "toexcel.gif", "����", "do_ShowExcel");
    var myArrAction2 = new Array(true, "��ͨ����", "action_draft.gif", "��ͨ����", "do_PtSearch");
    if("<%= searchType %>"=="ADVANCE"){
        ArrActions = new Array(myArrAction0, myArrAction2, myArrAction1);
    } else {
        ArrActions = new Array(myArrAction0, myArrAction4, myArrAction1);
    }
    printToolBar();
    function doClose() {
        window.close();
    }
    
    function do_SelectItemCode(){
		with(mainFrm){
			var lookUpName = "LOOK_UP_SYS_ITEM";
			var dialogWidth = 48;
			var dialogHeight = 30;
			var userPara = ""; 
			//"itemCategory=" + document.mainFrm.itemCategory.value;
			var objs = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userPara);
			if (objs) {
			    var obj = objs[0];
				dto2Frm(obj, "mainFrm");
			} else {
				//itemCode.value = "";
				mainFrm.itemName.value = "";
				try{
				mainFrm.itemSpec.value = "";
				}catch(ex){}
			}
		}
	}
</script>
<input type="hidden" name="searchType" id="searchType" value="<%= searchType %>"/>
<% 
if( searchType.equals( ADVANCE ) ){
%>
<table id="searchTable" border="0" width="100%" class="queryTable" style="TABLE-LAYOUT:fixed;word-break:break-all">
    <tr height="22px">
        <td width="7%" align="right">��˾���ƣ�</td>
        <td width="18%"><input class="input_style2" type="text" name="company" style="width:100%" value="<%=dto.getCompany()%>" readonly></td>
        <td width="7%" align="right">�豸���ƣ�</td>
        <td width="18%"><input class="input_style1" type="text" name="itemName" style="width:80%" value="<%=dto.getItemName()%>"><a href="" title="���ѡ���豸����" onClick="do_SelectItemCode();return false">[��]</a></td>
        <td width="7%" align="right">��ǩ�ţ�</td>
        <td width="18%"><input class="input_style1" type="text" name="fromBarcode" style="width:80%" value="<%=dto.getFromBarcode()%>"></td>
        <td width="7%" align="right">����</td>
        <td width="18%"><input class="input_style1" type="text" name="toBarcode" style="width:98%" value="<%=dto.getToBarcode()%>"></td>
    </tr>
    <tr height="22px">
        <td  width="7%" align="right">���β��ţ�</td>
        <td  width="18%"><input class="input_style2" title="<%=dto.getDeptName()%>" type="text" name="deptName" value="<%=dto.getDeptName()%>" style="width:100%" readonly></td>
        <td  width="7%" align="right">����ͺţ�</td>
        <td  width="18%"><input class="input_style1" type="text" name="itemSpec" style="width:80%" value="<%=dto.getItemSpec()%>"><a href="" title="���ѡ���豸����" onClick="do_SelectItemCode();return false">[��]</a></td>
        <td  width="7%" align="right">�������ڣ�</td>
        <td  width="18%"><input class="input_style2" type="text" name="creationDate" value="<%=dto.getCreationDate()%>" style="width:80%;cursor:pointer" title="���ѡ��ʼ����" readonly onclick="gfPop.fStartPop(creationDate, lastUpdateDate)"></td>
        <td  width="7%" align="right">����</td>
        <td  width="18%"><input class="input_style2" type="text" name="lastUpdateDate" value="<%=dto.getLastUpdateDate()%>" style="cursor:pointer;width:98%" title="���ѡ���������" readonly onclick="gfPop.fEndPop(creationDate, lastUpdateDate)"></td>
    </tr>
    <tr height="22px">
        <td width="7%" align="right"><%= specialityDeptTitle %>��</td>
        <td width="18%"><select class="select_style1" onmouseover="do_ProcessOptionWidth(this)" name="specialityDept" style="width:100%"><%=request.getAttribute("DEPT_OPTIONS2")%></select></td>
        <td width="7%" align="right">�ص㣺</td>
        <td width="18%"><input class="input_style1" type="text" name="workorderObjectName" value="<%=dto.getWorkorderObjectName()%>" style="width:80%"><a href="" title="���ѡ��ص�" onclick="do_SelectAddress(); return false;">[��]</a></td>
        <td width="7%" align="right">�������ڣ�</td>
        <td width="18%"><input class="input_style2" type="text" name="startDate" value="<%=dto.getStartDate()%>" style="width:80%;cursor:pointer" title="���ѡ��ʼ����" readonly onclick="gfPop.fStartPop(startDate, endDate)"></td>
        <td width="7%" align="right">����</td>
        <td width="18%"><input class="input_style2" type="text" name="endDate" value="<%=dto.getEndDate()%>" style="cursor:pointer;width:98%" title="���ѡ���������" readonly  onclick="gfPop.fEndPop(startDate, endDate)"></td>
    </tr>
    <tr height="22px">
        <td width="7%" align="right">�豸״̬��</td>
        <td width="18%"><select class="select_style1" name="itemStatus" style="width:100%"><%=request.getAttribute(AssetsWebAttributes.ITEM_STATUS_OPTIONS)%></select></td>
        <td width="7%" align="right">��Ŀ���ƣ�</td>
        <td width="18%">
            <input class="input_style1" type="text" name="projectName" readonly="true" value="<%=dto.getProjectName()%>" style="width:80%;"><a href="" title="���ѡ����Ŀ" onclick="do_SelectProject(); return false;">[��]</a>
            <input type="hidden" name="projectNumber" value="<%=dto.getProjectNumber()%>">
        </td>
        <td width="7%" align="right">�ʲ�Ŀ¼��</td>
        <td width="18%"><input type="text" class="input_style1" name="contentName" readonly="true" value="<%=dto.getContentName()%>" style="width:80%"><a href="" title="���ѡ��Ŀ¼" onclick="do_SelectContent(); return false;">[��]</a></td>
        <td width="7%" align="right">Ŀ¼���룺</td>
        <td width="18%"><input type="text" class="input_style1" name="contentCode" readonly="true" value="<%=dto.getContentCode()%>" style="width:98%"></td>
    </tr>
    <tr height="22px">
        <td width="7%" align="right">ʹ�ò��ţ�</td> 
        <td width="18%"><select onmouseover="do_ProcessOptionWidth(this)" class="select_style1" name="maintainDept" style="width:100%"><%=request.getAttribute("DEPT_OPTIONS3")%></select></td>
        <td width="7%" align="right">�����ˣ�</td>
        <td width="18%"><input class="input_style1" type="text" name="responsibilityUserName" value="<%=dto.getResponsibilityUserName()%>" style="width:80%;"><a href="" title="���ѡ��������"
                                                                                                      onclick="do_SelectPerson(); return false;">[��]</a>
        </td>
        <td width="7%" align="right">ʹ���ˣ�</td>
        <td width="18%"><input class="input_style1" type="text" name="maintainUser" style="width:80%" value="<%=dto.getMaintainUser()%>"></td>
        <td width="7%" align="right">�ʲ����ࣺ</td>
        <td width="18%">
            <select class="select_style1" name="financeProp" style="width:98%"><%=dto.getFinancePropOpt()%>
            </select>
        </td>
    </tr>
    <tr height="22px">
        <td width="7%" align="right">�����У�</td>
        <td width="18%"><select class="select_style1" name="city" onchange="do_ChangeCounty(this);" style="width:100%"><%=dto.getCityOpt()%>
        </select></td>
        <td width="7%" align="right">�����أ�</td>
        <td width="18%"><select class="select_style1" name="county" style="width:80%"><%=dto.getCountyOpt()%>
        </select></td>
        <td width="7%" align="right">����������</td>
        <td width="18%"><select class="select_style1" name="areaType" style="width:80%"><%=dto.getAreaTypeOpt()%>
        </select></td>
        <td width="7%" align="right">��Ӧ�̣�</td>
        <td width="18%">
        	 <input class="input_style1" type="text" name="vendorName" readonly="true" value="<%=dto.getVendorName()%>" style="width:80%;"><a href="" title="���ѡ����Ŀ" onclick="do_SelectVendor(); return false;">[��]</a>
    		 <input type="hidden" name="vendorId" value=""> 
        </td>
    </tr>	
    <tr height="22px">
    	<td  align="right">�������ͣ�</td>
        <td ><select class="select_style1" name="isShare" style="width:100%"><%=request.getAttribute("SHARE_OPTION")%></select></td>
    	<td  align="right">����Ԫ�أ�</td>
        <td ><input type="text" class="input_style1" name="logNetEle" value="<%=dto.getLogNetEle()%>" style="width:80%"><a href="" title="���ѡ���߼�����Ԫ��" onclick="do_SelectLne(); return false;">[��]</a></td>
        <td  align="right">Ͷ�ʷ��ࣺ</td>
        <td ><input type="text" class="input_style1" name="investCatName" value="<%=dto.getInvestCatName()%>" style="width:80%"><a href="" title="���ѡ��Ͷ�ʷ���" onclick="do_SelectCex(); return false;">[��]</a></td>
        <td  align="right">ҵ��ƽ̨��</td>
        <td ><input type="text" class="input_style1" name="opeName" value="<%=dto.getOpeName()%>" style="width:80%"><a href="" title="���ѡ��ҵ��ƽ̨" onclick="do_SelectOpe(); return false;">[��]</a></td>
        
    </tr>        
   	<tr height="22px">
       	<td  align="right">�������ͣ�</td>
        <td ><select class="select_style1" name="constructStatus" style="width:100%"><%=request.getAttribute("CONSTRUCT_OPTION")%></select></td>
    	<td  align="right">�����Σ�</td>
        <td ><input type="text" class="input_style1" name="lneName" value="<%=dto.getLneName()%>" style="width:80%"><a href="" title="���ѡ��������" onclick="do_SelectNle(); return false;">[��]</a></td>
        <td  align="right"></td>
    </tr>
</table>
<div id="ddDiv" style="position:absolute;z-index:2;top:130px;left:350px;background-color:azure;border:1px;width:300px;height:50px;text-align:center;visibility:hidden;">
	<table border = "0" width="100%">
	   <tr style="cursor:move;background:#0997F7;color:white;font:bold;height:20">
            <td>&nbsp;&nbsp;<span key="PleaseSelectFunction"/></td>
    	    <td align="right"><div style="padding-right:10px"><font face="webdings" style="cursor:pointer" onclick="do_ShowExcel()">r</font></div></td>
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
<% }else{ %>
      <table id="searchTable" border="0" width="100%" style="TABLE-LAYOUT: fixed; word-break: break-all">
      		<tr height="0px">
      			<td width="7%"></td>
      			<td width="18%"></td>
      			<td width="7%"></td>
      			<td width="18%"></td>
      			<td width="7%"></td>
      			<td width="18%"></td>
      			<td width="7%"></td>
      			<td width="18%"></td>
      		</tr>
      		<tr height="22px">
		      	<td width="7%" align="right">��˾���ƣ�</td>
		        <td width="18%"><input class="input_style2" type="text" name="company" style="width:100%" value="<%=dto.getCompany()%>" readonly></td>
				<td  width="7%" align="right">���β��ţ�</td>
        		<td  colspan="3"><input class="input_style2" type="text" name="deptName" value="<%=dto.getDeptName()%>" style="width:100%" readonly></td>
				  
				<td width="7%" align="right">�ʲ����ࣺ</td>
		        <td width="18%">
		            <select class="select_style1" name="financeProp" style="width:98%"><%=dto.getFinancePropOpt()%>
		            </select>
		        </td>
			</tr>	
			<tr height="22px">
				<td width="7%" align="right">�ص㣺</td>
				<td width="18%">
					<input class="input_style1" type="text" name="workorderObjectName"
						value="<%=dto.getWorkorderObjectName()%>" style="width: 80%">
					<a href="" title="���ѡ��ص�"
						onclick="do_SelectAddress(); return false;">[��]</a>
				</td>
				<td width="7%" align="right">
					��Ŀ���ƣ�
				</td>
				<td width="18%">
					<input class="input_style1" type="text" name="projectName"
						readonly="true" value="<%=dto.getProjectName()%>"
						style="width: 80%;">
					<a href="" title="���ѡ����Ŀ"
						onclick="do_SelectProject(); return false;">[��]</a>
					<input type="hidden" name="projectNumber" value="<%=dto.getProjectNumber()%>">
				</td>
				<td width="7%" align="right">
					�ʲ�Ŀ¼��
				</td>
				<td width="18%">
					<input type="text" class="input_style1" name="contentName"
						readonly="true" value="<%=dto.getContentName()%>"
						style="width: 80%">
					<a href="" title="���ѡ��Ŀ¼"
						onclick="do_SelectContent(); return false;">[��]</a>
				</td>
				<td width="7%" align="right">
					�豸���ƣ�
				</td>
				<td width="18%">
					<input class="input_style1" type="text" name="itemName"
						style="width: 80%" value="<%=dto.getItemName()%>"><a href="" title="���ѡ���豸����" onClick="do_SelectItemCode();return false">[��]</a>
				</td>
			</tr>
		</table>
<div id="ddDiv" align="center" style="position:absolute;z-index:2;top:260px;left:450px;background-color:azure;border:1px;width:300px;height:50px;text-align:center;visibility:hidden;">
	<table border = "0" width="100%">
	   <tr style="cursor:move;background:#0997F7;color:white;font:bold;height:20">
            <td>&nbsp;&nbsp;<span key="PleaseSelectFunction"/></td>
    	    <td align="right"><div style="padding-right:10px"><font face="webdings" style="cursor:pointer" onclick="do_ShowExcel()">r</font></div></td>
        </tr>
       <tr>  
           <td width="80%" nowrap="nowrap" align="center">
				<input type="button" value="����EXCEL" onclick="do_Export('xls')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		           	
   						<input type="button" value="����CSV" onclick="do_Export('csv')"/>
           </td>
       </tr>
	 </table>
</div> 	
    <input type="hidden" name="contentCode" id="contentCode" value="<%=dto.getContentCode()%>">
    <input type="hidden" name="deptName" id="deptName" value="<%=dto.getDeptName()%>">
<% } %>
    <input type="hidden" name="act" id="act">
    <input type="hidden" name="excelType"/>
</form>

<div id="headDiv" style="overflow-y:scroll;overflow-x:hidden;position:absolute;top:<%= topHeight %>px;left:0px;width:100%">
    <table id="$$$headerTable$$$" border=1 width="700%" class="eamHeaderTable">
        <tr height="22px">
            <td align=center width=2%>��ǩ��</td>
            <td align=center width=2%>�豸רҵ</td>
            <td align=center width=3%>�豸����</td>
            <td align=center width=3%>����ͺ�</td>
            <td align=center width=3%>Ŀ¼����</td>
            <td align=center width=4%>Ŀ¼����</td>
            <td align=center width=1%>������λ</td>
            <td align=center width=1%>����</td>
            <td align=center width=2%>�ʲ�����</td>
            <td align=center width=2%>�豸״̬</td>
            <td align=center width=3%>�ص���</td>
            <td align=center width=5%>�ص�����</td>
            <td align=center width=1%>������</td>
            <td align=center width=1%>������</td>
            <td align=center width=1%>��������</td>
            <td align=center width=1%>������</td>
            <td align=center width=1%>Ա�����</td>
            <td align=center width=4%>���β���</td>
            <td align=center width=2%>ʹ����</td>
            <td align=center width=4%>ʹ�ò���</td>
            <td align=center width=4%><%= specialityDeptTitle %></td>
            <td align=center width=3%>��Ŀ����</td>
            <td align=center width=2%>��Ŀ���</td>
            <td align=center width=2%>����״̬</td>
            <td align=center width=2%>����״̬</td>
            <td align=center width=2%>�߼�����Ԫ��</td>
            <td align=center width=2%>Ͷ�ʷ���</td>
            <td align=center width=2%>ҵ��ƽ̨</td>
            <td align=center width=2%>������</td>
            <td align=center width=2%>�ʲ�ʹ������</td>
            <td align=center width=2%>�ʲ���������</td>
            <td align=center width=2%>�ʲ���������</td>
            <td align=center width=2%>�ʲ�ԭֵ</td>
            <td align=center width=2%>�ʲ���ֵ</td>
            <td align=center width=2%>�ʲ���ֵ׼��</td>
            <td align=center width=2%>�ʲ��ۼ��۾�</td>
            <td align=center width=2%>�ʲ�����</td>
            <td align=center width=1%>ʵ������</td>
            <td align=center width=4%>��ע</td>
            <td align=center width=4%>��עһ</td>
            <td align=center width=4%>��ע��</td>
        </tr>
    </table>
</div>
<div id="dataDiv" style="overflow:scroll;height:<%= tbHeight %>px;width:100%;position:absolute;top:<%= topHeight + 22 %>px;left:0px" align="left"  onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" class="eamLineTable" width="700%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
<%
    if (hasData) {
        for (int i = 0; i < rows.getSize(); i++) {
            Row row = rows.getRow(i);
            String barcode = row.getStrValue("BARCODE");
%>
        <tr class="dataTR" onClick="do_ShowDetail('<%=barcode%>')" style="cursor:pointer" title="����鿴�ʲ���<%=barcode%>����ϸ��Ϣ">
            <td width=2%><input type="text" class="finput2" readonly value="<%=row.getStrValue("BARCODE")%>"></td>
            <td width=2%><input type="text" class="finput2" readonly value="<%=row.getValue("ITEM_CATEGORY_NAME")%>"></td>
            <td width=3%><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_NAME")%>"></td>
            <td width=3%><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_SPEC")%>"></td>
            <td width=3%><input type="text" class="finput2" readonly value="<%=row.getValue("CONTENT_CODE")%>"></td>
            <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("CONTENT_NAME")%>"></td>
            <td width=1%><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_UNIT")%>"></td>
            <td width=1%><input type="text" name="currentUnits" class="finput3" readonly value="<%=row.getValue("ITEM_QTY")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("FINANCE_PROP_VALUE")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("ITEM_STATUS_NAME")%>"></td>
            <td width=3%><input type="text" class="finput2" readonly value="<%=row.getValue("WORKORDER_OBJECT_CODE")%>"></td>
            <td width=5%><input type="text" class="finput" readonly value="<%=row.getValue("WORKORDER_OBJECT_NAME")%>"></td>
            <td width=1%><input type="text" class="finput" readonly value="<%=row.getValue("CITY")%>"></td>
            <td width=1%><input type="text" class="finput" readonly value="<%=row.getValue("COUNTY")%>"></td>
            <td width=1%><input type="text" class="finput" readonly value="<%=row.getValue("AREA_TYPE_NAME")%>"></td>
            <td width=1%><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_USER_NAME")%>"></td>
            <td width=1%><input type="text" class="finput2" readonly value="<%=row.getValue("EMPLOYEE_NUMBER")%>"></td>
            <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("RESPONSIBILITY_DEPT_NAME")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("MAINTAIN_USER")%>"></td>
            <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("MAINTAIN_DEPT_NAME")%>"></td>
            <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("SPECIALITY_DEPT_NAME")%>"></td>
            <td width=3%><input type="text" class="finput" readonly value="<%=row.getValue("PROJECT_NAME")%>"></td>
            <td width=2%><input type="text" class="finput2" readonly value="<%=row.getValue("PROJECT_NUMBER")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("IS_SHARE")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("CONSTRUCT_STATUS")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("LOG_NET_ELE")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("INVEST_CAT_NAME")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("OPE_NAME")%>"></td>
            <td width=2%><input type="text" class="finput" readonly value="<%=row.getValue("LNE_NAME")%>"></td>
            <td width=2%><input type="text" class="finput3" readonly value="<%=row.getValue("LIFE_IN_YEARS")%>"></td>
            <td width=2%><input type="text" class="finput2" readonly value="<%=row.getValue("DATE_PLACED_IN_SERVICE")%>"></td>
            <td width=2%><input type="text" class="finput2" readonly value="<%=row.getValue("ASSETS_CREATE_DATE")%>"></td>
            <td width=2%><input type="text" class="finput3" readonly value="<%=row.getValue("ORIGINAL_COST")%>"></td>
            <td width=2%><input type="text" class="finput3"readonly value="<%=row.getValue("SCRAP_VALUE")%>"></td>
            <td width=2%><input type="text" class="finput3" readonly value="<%=row.getValue("IMPAIR_RESERVE")%>"></td>
            <td width=2%><input type="text" class="finput3" readonly value="<%=row.getValue("DEPRN_RESERVE")%>"></td>
            <td width=2%><input type="text" class="finput3" readonly value="<%=row.getValue("DEPRN_COST")%>"></td>
            <td width=1%><input type="text" class="finput3" readonly value="<%=row.getValue("ACTUAL_QTY")%>"></td>
            <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("REMARK")%>"></td>
            <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("REMARK1")%>"></td>
            <td width=4%><input type="text" class="finput" readonly value="<%=row.getValue("REMARK2")%>"></td>
        </tr>
<%
        }
    }
%>
    </table>
</div>
<%
    if (hasData) {
%>
<div id="pageNaviDiv" style="position:absolute;top:500px;left:0;"><%=request.getAttribute(QueryConstant.SPLIT_PAGE_HTML)%></div>
<%
    }
%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no"
        frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script type="text/javascript">
function initPage() {
    do_SetPageWidth();
    do_FormatQuantity();
    
    //�����������ֵtitle start
    var specialityDeptObj = document.getElementById("specialityDept");
    var maintainDeptObj = document.getElementById("maintainDept");
    
    if( specialityDeptObj ){
    	setTitle(specialityDeptObj);
    } 
        
    if( maintainDeptObj ){
    	setTitle(maintainDeptObj);
    }
    //�����������ֵtitle end
    setAllTdNoBr();
}

function setAllTdNoBr(){
	var searchTable = document.getElementById( "searchTable" );
	try{
		var tds = searchTable.getElementsByTagName( "TD" );
		for( var i=0 ; i < tds.length ; i ++ ){
			if( tds[i].firstChild && tds[i].firstChild.tagName == "INPUT" ){
			}else{
				tds[i].innerHTML = "<nobr>" + tds[i].innerHTML + "</nobr>";
			}
		}
	}catch(ex){alert( ex.message ); }
}

function do_FormatQuantity(){
    var tab = document.getElementById("dataTable");
    if(tab){
        var rows = tab.rows;
        if(rows){
            for(var i = 0; i < rows.length; i++){
                var tr = rows[i];
                var node = getTrNode(tr, "currentUnits");
                if(node){
                    var currentUnits = node.value;
                    currentUnits = formatNum(currentUnits, 0);
                    node.value = currentUnits;
                }
            }
        }
    }
}

function do_Search() {
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
    mainFrm.act.value = "<%=AssetsActionConstant.EXPORT_ACTION%>";
    mainFrm.excelType.value = type;
    mainFrm.submit();
    
    //document.getElementById("$$$disableExportMsg$$$").style.visibility = "visible";
    var _d = document.getElementById("ddDiv");
    _d.style.visibility = "hidden";
}

function do_SelectAddress() {
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ADDRESS%>";
    var dialogWidth = 55;
    var dialogHeight = 30;
    var  userPara;
    if (document.mainFrm.organizationId) {
        userPara = "organizationId=" + document.mainFrm.organizationId.value;
    } else {
         userPara = "organizationId=<%=userAccount.getOrganizationId()%>";
    }

    var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
    if (objs) {
        var obj = objs[0];
        dto2Frm(obj, "mainFrm");
        mainFrm.workorderObjectName.value = obj["workorderObjectLocation"];
    } else {
        mainFrm.workorderObjectName.value = "";
    }
}

function do_SelectVendor() {
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_VENDOR%>";
    var dialogWidth = 55;
    var dialogHeight = 30;
    var userParameters = "multipleChose=true";
    var returnValues = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userParameters);
    var project = new Object();
    project["vendorName"] = "";
    project["vendorNumber"] = "";
    if (returnValues) {
        var valueCount = returnValues.length;
        for (var i = 0; i < valueCount; i++) {
            var returnValue = returnValues[i];
            project["vendorName"] += "'" + returnValue["vendorName"] + "'";
            project["vendorNumber"] += "'" + returnValue["vendorNumber"] + "'";
            if(i < valueCount - 1){
                project["vendorName"] += ", ";
                project["vendorNumber"] += ", ";
            }
        }
    }
    dto2Frm(project, "mainFrm");
}

function do_SelectProject() {
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_PROJECT%>";
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

function do_SelectPerson() {
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_PERSON%>";
    var dialogWidth = 47;
    var dialogHeight = 30;
    var users = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    if (users) {
        var user = users[0];
        mainFrm.responsibilityUserName.value = user["userName"];
    }
}

function do_ShowDetail(barcode) {
    var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
    var winName = "assetsWin";
    var style = "width=860,height=495,left=100,top=130";
    window.open(url, winName, style);
}

function do_SelectItemName() {
    var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ITEMNAME%>";
    var dialogWidth = 35;
    var dialogHeight = 30;
    var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
    if (objs) {
        var obj = objs[0];
        dto2Frm(obj, "mainFrm");
    }
}


function do_ChangeCounty(obj) {
    var url = "/servlet/com.sino.ams.system.object.servlet.CommonObjectServlet";
    url += "?act=CHANGE_COUNTYS";
    url += "&city=" + obj.value;
    do_AddressProcessSimpleAjax(url, null);
}
function do_AddressProcessSimpleAjax(url, sendData) {
    do_AddressProcessAjax(url, "POST", true, sendData);
}

function do_AddressProcessAjax(url, reqMethod, isSynchronize, sendData) {//����XMLHttpRequest����
    if (!url || url == null || url == "") {
        alert("û��ָ�������URL��");
        return;
    }
    if (!sendData) {
        sendData = null;
    }

    if (!isSynchronize) {
        isSynchronize = true;
    }
    if (!reqMethod) {
        reqMethod = "POST";
    } else {
        reqMethod = reqMethod.toUpperCase();
    }
    try {
        xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
    } catch(e) {
        try {
            xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
        } catch(e) {
            try {
                xmlHttp = new XMLHttpRequest();
            } catch(e) {
                alert("����XMLHttpRequest����ʧ�ܣ�");
            }
        }
    }
    if (!xmlHttp) {
        alert("����XMLHttpRequest����ʧ�ܣ������ԣ�");
        return;
    }
    xmlHttp.onreadystatechange = handleReadyStateChanges;
    xmlHttp.open(reqMethod, url, isSynchronize);
    xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlHttp.send(sendData);
}
function handleReadyStateChanges() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            setCounty(xmlHttp.responseText);
        } else {
            alert(xmlHttp.status);
        }
    }
}
function setCounty(responseContent) {
    document.mainFrm.county.outerHTML = "<select name=\"county\" style=\"width:80%\">" + responseContent + "</select>";
}

function do_SelectContent() {
    var lookUpName = "LOOK_UP_CONTENT";
    var dialogWidth = 48;
    var dialogHeight = 30;
    var userParameters = "multipleChose=true";
    var returnValues = getLookUpValues(lookUpName, dialogWidth, dialogHeight, userParameters);
    var content = new Object();
    content["contentName"] = "";
    content["contentCode"] = "";
    if (returnValues) {
        var valueCount = returnValues.length;
        for (var i = 0; i < valueCount; i++) {
            var returnValue = returnValues[i];
            content["contentName"] += "'" + returnValue["contentName"] + "'";
            content["contentCode"] += "'" + returnValue["contentCode"] + "'";
            if(i < valueCount - 1){
                content["contentName"] += ", ";
                content["contentCode"] += ", ";
            }
        }
    }
    dto2Frm(content, "mainFrm");
}

function do_AdSearch(){
	document.getElementById( "searchType" ).value = "ADVANCE";	
	mainFrm.act.value = "";
	mainFrm.submit();
}
function do_PtSearch(){
	document.getElementById( "searchType" ).value = "";	
	mainFrm.act.value = "";
	mainFrm.submit();
}

function setTitle( selectObj ){
	var str = ""; 
	var optionObj = selectObj.options;
    var optionCount = optionObj.length; 
    for (var i = 0; i < optionCount; i++) {
        if (optionObj[i].selected && !isEmpty(optionObj[i].value)) {
            str = optionObj[i].text ;
            break;
        }
    } 
	selectObj.title = str;
}



function do_SelectLne(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_LNE%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
	} else {
        mainFrm.logNetEle.value = "";
    }
}

function do_SelectCex(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_CEX%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
	} else {
        mainFrm.investCatName.value = "";
    }
}

function do_SelectOpe(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_OPE%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
	} else {
        mainFrm.opeName.value = "";
    }
}

function do_SelectNle(){
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_NLE%>";
	var dialogWidth = 48;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	if (objs) {
		var obj = objs[0];
		dto2Frm(obj, "mainFrm");
	} else {
        mainFrm.lneName.value = "";
    }
}

function closeDisabledDIV(){
	var disabledDIV = document.getElementById("$$$disableExportMsg$$$");
	disabledDIV.style.visibility = "hidden";
}
</script>

<div id="$$$disableExportMsg$$$" style="position:absolute;bottom:0px;top:0px;left:0px;right:0px;z-index:10;visibility:hidden;width:100%;height:100%">
	<table width="100%" height="100%" style="background-color:#FFFFFF;filter:progid:DXImageTransform.Microsoft.Alpha(opacity=80,finishOpacity=20,style=2)">
		<tr>
			<td colspan="3"></td>
		</tr>
		<tr>
			<td width="30%"></td>
			<td bgcolor="#ff9900"  height="60">
				<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td bgcolor="#FFFFFF" align="center" id="hintTD"><font color="#008000" size="2" onclick="closeDisabledDIV();">���ڵ������ݣ����Ժ�......</font><img src="/images/wait.gif" alt=""></td>
					</tr>
				</table>
			</td>
			<td width="30%"></td>
		</tr>
		<tr>
			<td colspan="3"></td>
		</tr>
	</table>
</div>

<div id="$$$waitTipMsg$$$" style="position:absolute;bottom:0px;top:0px;left:0px;right:0px;z-index:10;visibility:hidden;width:100%;height:100%">
	<table width="100%" height="100%" style="background-color:#FFFFFF;filter:progid:DXImageTransform.Microsoft.Alpha(opacity=80,finishOpacity=20,style=2)">
		<tr>
			<td colspan="3"></td>
		</tr>
		<tr>
			<td width="30%"></td>
			<td bgcolor="#ff9900"  height="60">
				<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td bgcolor="#FFFFFF" align="center" id="hintTD"><font color="#008000" size="2" >���ڴ�������������Ժ�......</font><img src="/images/wait.gif" alt=""></td>
					</tr>
				</table>
			</td>
			<td width="30%"></td>
		</tr>
		<tr>
			<td colspan="3"></td>
		</tr>
	</table>
</div>
