<%@ page import="com.sino.base.dto.DTOSet" %>
<%@ page import="com.sino.soa.common.SrvURLDefineList" %>
<%@ page import="com.sino.soa.common.SrvWebActionConstant" %>
<%@ page import="com.sino.soa.mis.srv.assetretire.dto.SrvRetiredAssetDTO" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.sinoflow.constant.LookUpConstant" %>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.workorder.dto.ZeroTurnBursurHDTO"%>
<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes"%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@page import="com.sino.ams.workorder.dto.ZeroTurnBursurHDTO"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<head>
    <title>���˱��</title>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
     <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script type="text/javascript" src="/WebLibary/js/TableProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
    <script type="text/javascript" src="/WebLibary/js/help.js"></script>
    <script type="text/javascript" src="/WebLibary/js/AppStandard.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/util.js"></script>
</head>
<style type="text/css">
    .finput {
        WIDTH: 100%;
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
    }

    .finput2 {
        WIDTH: 100%;
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
        text-align: center;
    }

    .finput3 {
        WIDTH: 100%;
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
        text-align: right;
        padding-right: 4px
    }

    .fDtlInput {
        WIDTH: 100%;
        border-style: solid;
        background-color: #F2F9FF;
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
        text-align: left
    }

    ;
    .finputNoEmpty {
        WIDTH: 100%;
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
        text-align: left;
        BACKGROUND-COLOR: #FFFF99
    }

    ;
    .finputNoEmpty2 {
        WIDTH: 100%;
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
        text-align: center;
        BACKGROUND-COLOR: #FFFF99
    }

    ;
    .finputNoEmpty3 {
        WIDTH: 100%;
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
        text-align: right;
        BACKGROUND-COLOR: #FFFF99;
        padding-right: 4px
    }

    .inputNoEmptySelect {
        WIDTH: 100%;
        height: 100%;
        BORDER-RIGHT: 0px ridge;
        BORDER-TOP: 0px ridge;
        BORDER-LEFT: 0px ridge;
        BORDER-BOTTOM: 0px ridge;
        font-size: 12px;
        BACKGROUND-COLOR: #FFFF99;
        cursor: pointer;
    }
</style>

<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    ZeroTurnBursurHDTO dto = (ZeroTurnBursurHDTO)request.getAttribute(AssetsWebAttributes.ORDER_LINE_DATA);
    String groupName = reqParser.getParameter("group");
    String deptName = reqParser.getParameter("dept");
    String projectName = reqParser.getParameter("project");
    String action = reqParser.getParameter("act");
%>
<jsp:include page="/message/MessageProcess"/>
<body leftmargin="1" topmargin="0" onload="do_SetPageWidth()">

<form name="mainFrm" method="POST" action="/servlet/com.sino.ams.workorder.servlet.ZeroTurnReimburServlet">
    <script language="javascript">
        var ArrAction1 = new Array(true, "��ѯ", "act_query.gif", "��ѯ", "do_search()");
        var ArrAction2 = new Array(true, "��Ƿ���", "action_draft.gif", "��Ƿ���", 'do_save()');
        var ArrAction3 = new Array(true, "ȡ���������", "action_draft.gif", "ȡ���������", 'do_cancel()');
        var ArrAction4 = new Array(true, "����Excel", "toexcel.gif", "����Excel", 'do_Export()');
        var ArrActions = new Array(ArrAction1, ArrAction2, ArrAction3,ArrAction4);
        var ArrSinoViews = new Array();
        printTitleBar("����");
        printToolBar();
    </script>
    <input type="hidden" name="act" value="<%=action%>">

    <table border="0" width="100%" id="table1">
        <tr>
            <td width="12%" align="right">����״̬��</td>
            <td width="22%">
               <select class="select_style1" style="width:100%" name="arrivalStatus" onchange="document.getElementById('sf_priority').value = this.value;">
            		<%= dto.getArrivalStatusName()%>
            	</select>
            </td>
            <td width="12%" align="right">��ʼʱ�䣺</td>
            <td width="21%">
            <input  name="creationBeginDate"   style="width:70%" title="���ѡ������" value="<%=dto.getCreationBeginDate()%>" 
            readonly class="readonlyInput" onclick="gfPop.fStartPop(creationBeginDate, creationEndDate);">
            <img src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fStartPop(creationBeginDate, creationEndDate);">
             </td>
            <td width="12%" align="right">����ʱ�䣺</td>
            <td width="21%">
              <input type="text" name="creationEndDate" value="<%=dto.getCreationEndDate()%>" style="width:60%" title="���ѡ������" readonly
                class="readonlyInput" onclick="gfPop.fEndPop(creationBeginDate, creationEndDate);"><img
                src="/images/calendar.gif" alt="�����ѯ" onclick="gfPop.fEndPop(creationBeginDate, creationEndDate);">
            </td>
        </tr>
         <tr>
            <td width="12%" align="right">�ɱ����ģ�</td>
            <td width="22%">
               <input  name="costCenterName"  class="input_style1" style="width:100%"   value="<%=dto.getCostCenterName()%>"  >
            </td>
            <td width="12%" align="right">��������ţ�</td>
            <td width="21%">
            <input  name="misProcureCode"  class="input_style1" style="width:70%"   value="<%=dto.getMisProcureCode()%>"  >
             </td>
            <td width="12%" align="right">�����̣�</td>
            <td width="21%">
              <input type="text" name="manufacturerName" value="<%=dto.getManufacturerName()%>" class="input_style1" style="width:70%">
            </td>
        </tr>
    </table>

    <script type="text/javascript">
        var columnArr = new Array("checkbox","���������","�ʲ���ǩ��","����״̬", "����״̬", "��������", "�ʲ�����", "����ͺ�","�ص����","������","���β���","������","���","�Ƿ�����");
        var widthArr = new Array("3%","10%", "10%", "5%", "7%", "7%", "7%", "10%", "10%", "5%", "10%","5%","5%","5%");
        printTableHead(columnArr, widthArr);
    </script>
    <%
        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        if (rows != null && !rows.isEmpty()) {
    %>
    <div id="dataDiv" style="overflow-y:scroll;width:100%;left:1px;margin-left:0px" onscroll="document.getElementById('headDiv').scrollLeft=this.scrollLeft;">
        <table id="dataTable" width="100%" border="1" style="table-layout:fixed;">
            <%
                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
                    String iStr = String.valueOf(i);
                    String subCheckL = "subCheck" + iStr;
                    String isCheckL = "isCheck" + iStr;
                    boolean canReim=true;
                    
                    String misProcureCodeL = "misProcureCode" + iStr;
                    String misProcureCodeV = row.getStrValue("MIS_PROCURE_CODE");//�ɹ�����
                    
                    String barcodeL = "barcode" + iStr;
                    String barcodeV = row.getStrValue("BARCODE");//�ʲ���ǩ��
                    
                    String isReceivedL = "isReceived" + iStr;
                    String isReceivedV = row.getStrValue("IS_RECEIVED"); //
                   
                    String manufacturerNameL = "manufacturerName" + iStr;
                    String manufacturerNameV = row.getStrValue("MANUFACTURER_NAME"); // 
                    
                    String arrivalStatusL = "arrivalStatus" + iStr;
                    String arrivalStatusV = row.getStrValue("ARRIVAL_STATUS"); //����״̬
                   
                    String arrivalStatusNameL = "arrivalStatusName" + iStr;
                    String arrivalStatusNameV = row.getStrValue("ARRIVAL_STATUS_NAME"); //����״̬
                    
                    String arrivalDateL = "arrivalDate" + iStr;
                    String arrivalDateV = row.getStrValue("ARRIVAL_DATE"); //��������
                    
                    String assetsDescriptionL = "assetsDescription" + iStr;
                    String assetsDescriptionV = row.getStrValue("ASSETS_DESCRIPTION"); //�ʲ�����
                    
                    String itemSpecL = "itemSpec" + iStr;
                    String itemSpecV = row.getStrValue("ITEM_SPEC"); //����ͺ�
                    
                    String objectNoL = "objectNo" + iStr;
                    String objectNoV = row.getStrValue("OBJECT_NO"); //�ص���
                    
                    String responsibilityUserL = "responsibilityUser" + iStr;
                    String responsibilityUserV = row.getStrValue("RESPONSIBILITY_USER"); //������
                    
                    String responsibilityDeptL = "responsibilityDept" + iStr;
                    String responsibilityDeptV = row.getStrValue("RESPONSIBILITY_DEPT"); //���β���
                    
                    String priceL = "price" + iStr;
                    String priceV = row.getStrValue("PRICE"); //���
                    
                    String isPeriodL = "isPeriod" + iStr;
                    String isPeriodV = row.getStrValue("IS_PERIOD"); //���
                    
                    //�ѱ��˲����������ڿ���ȡ������
                    if(arrivalStatusV.equals("Y")&&isPeriodV.equals("N")){
                    	canReim=false;
                    //δ���ʶ����Ա���
                    }else if(arrivalStatusV.equals("N")){
                    	//
                    }
                    
                    if(canReim){
            %>
          
            <tr class="dataTR">
                <td width="3%" style="cursor:pointer" align="center">
                <input type="checkbox" name="subCheck" >
                <input type="hidden"   name="isall" id="isall<%=i%>" value="0">
                </td> 
            <%
               }else {
            %>
            
            <tr class="dataTR" title="�ѱ����Ҳ��������ڵ��ʲ�������ȡ�����ˣ�">
			     <td width="3%" align="center" id="disableTR<%=i%>">
			     <input type="checkbox" name="subCheck" disabled="disabled"> 
			     <input type="hidden"   name="isall" id="isall<%=i%>" value="1">
			     </td>
            <%
               }
            %>
                <input type="hidden" name="isCheck" id="<%=isCheckL%>" value="">
                <td width="10%" style="cursor:pointer" ><input type="text" name="misProcureCode" id="<%=misProcureCodeL%>" value="<%=misProcureCodeV%>" class="finput"></td>
                <td width="10%" style="cursor:pointer" ><input type="text" name="barcode" id="<%=barcodeL%>" value="<%=barcodeV%>" class="finput"></td>
                <td width="5%" style="cursor:pointer" ><input type="text" name="isReceived" id="<%=isReceivedL%>" value="<%=isReceivedV%>" class="finput"></td>
                <td width="7%" style="cursor:pointer" ><input type="text" name="arrivalStatusName" id="<%=arrivalStatusNameL%>" value="<%=arrivalStatusNameV%>" class="finput"></td>
                <td width="7%" style="cursor:pointer" ><input type="text" name="arrivalDate" id="<%=arrivalDateL%>" value="<%=arrivalDateV%>" class="finput"></td>
                <td width="7%" style="cursor:pointer" ><input type="text" name="assetsDescription" id="<%=assetsDescriptionL%>" value="<%=assetsDescriptionV%>" class="finput"></td>
                <td width="10%" style="cursor:pointer" ><input type="text" name="itemSpec" id="<%=itemSpecL%>" value="<%=itemSpecV%>" class="finput"></td>
                <td width="10%" style="cursor:pointer" ><input type="text" name="objectNo" id="<%=objectNoL%>" value="<%=objectNoV%>" class="finput"></td>
                <td width="5%" style="cursor:pointer" ><input type="text" name="responsibilityUser" id="<%=responsibilityUserL%>" value="<%=responsibilityUserV%>" class="finput"></td>
                <td width="10%" style="cursor:pointer" ><input type="text" name="responsibilityDept" id="<%=responsibilityDeptL%>" value="<%=responsibilityDeptV%>" class="finput"></td>
                <td width="5%" style="cursor:pointer" ><input type="text" name="manufacturerName" id="<%=manufacturerNameL%>" value="<%=manufacturerNameV%>" class="finput"></td>
                <td width="5%" style="cursor:pointer" ><input type="text" name="price" id="<%=priceL%>" value="<%=priceV%>" class="finput"></td>
                <td width="5%" style="cursor:pointer" ><input type="text" name="isPeriod" id="<%=isPeriodL%>" value="<%=isPeriodV%>" class="finput"></td>
                 
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</form>
<div id="pageNaviDiv"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
    <%=WebConstant.WAIT_TIP_MSG%>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script type="text/javascript">

function do_search() {
    document.mainFrm.act.value = "QUERY_ACTION";
    document.mainFrm.submit();
}

//����
function do_save() {
	var type=document.getElementById("arrivalStatus").value;
	if(type=='Y'){
		alert("��ѡ��δ���ʵ��ʲ����б���");
		return;
	}else{
	    var checkArr = document.getElementsByName("subCheck");
	    var isCheckArr = document.getElementsByName("isCheck");
	    var isAll=document.getElementsByName("isall");
	    var indexsStr = "";
	    for(var i = 0; i < checkArr.length; i++) {
	        if(checkArr[i].checked) {
	            isCheckArr[i].value = "1";
	            if(indexsStr == "") {
	                indexsStr = "" + i;
	            } else {
	                indexsStr += "," + i;
	            }
	        } else {
	            isCheckArr[i].value = "0";
	        }
	        if(isAll[i].value=="1"){
	        	 alert("��ѡ��δ�����ʲ�!");
	        	 return;
	         }
	    }
	    if(indexsStr == "") {
	        alert("�]��ѡ���κ�����!");
	        return;
	    }
	    document.mainFrm.act.value = "SAVE_ACTION";
	    document.mainFrm.submit();
	}
}

function do_cancel() {
	var type=document.getElementById("arrivalStatus").value;
	if(type=='N'){
		alert("��ѡ���ѱ��ʵ��ʲ�����ȡ������");
		return;
	}else{
	    var checkArr = document.getElementsByName("subCheck");
	    var isCheckArr = document.getElementsByName("isCheck");
	    var isAll=document.getElementsByName("isall");
	    var indexsStr = "";
	    for(var i = 0; i < checkArr.length; i++) {
	        if(checkArr[i].checked) {
	            isCheckArr[i].value = "1";
	            if(indexsStr == "") {
	                indexsStr = "" + i;
	            } else {
	                indexsStr += "," + i;
	            }
	        } else {
	            isCheckArr[i].value = "0";
	        }
	        if(checkArr[i].checked&&isAll[i].value=="1"){
	        	 alert("�ʲ����������ڣ�������ȡ������!");
	        	 return;
	         }
	    }
	    if(indexsStr == "") {
	        alert("�]��ѡ���κ�����!");
	        return;
	    }
	    document.mainFrm.act.value = "cancel";
	    document.mainFrm.submit();
	}
}


function do_Export(){
    document.mainFrm.act.value = "EXPORT_ACTION";
	mainFrm.submit();
}
</script>
