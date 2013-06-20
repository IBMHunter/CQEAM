<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<%@ page import="com.sino.ams.apd.dto.AmsAssetsCheckByYrHeaderDTO"%>
<%@ page import="com.sino.ams.apd.dto.AmsAssetsCheckByYrLineDTO"%>
<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
	<head>
		<%--
          Function:		�·��̵㹤��
         --%>
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/eam.css">
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
	    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
	    <script type="text/javascript" src="/WebLibary/js/util.js"></script>

		<%
			RequestParser reqParser = new RequestParser();
			reqParser.transData(request);
			AmsAssetsCheckByYrLineDTO headerDTO = (AmsAssetsCheckByYrLineDTO) reqParser.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
		%>

	</head>

	<body leftmargin="0" topmargin="0" onload="initPage2();helpInit('4.4.4');">
		<input type="hidden" name="helpId" value="">
		<jsp:include page="/message/MessageProcess" />
		<jsp:include page="/public/exportMsg.jsp" />

		<form name="mainFrm" method="post"
			action="/servlet/com.sino.ams.apd.servlet.AmsAssetsCheckCrOrderServlet">
			<script type="text/javascript">
		      var ArrAction1 = new Array(true, "�����̵㹤��", "act_query.gif", "�����̵㹤��", "do_create()");
		      var ArrAction2 = new Array(true, "�����̵㹤��", "act_query.gif", "�����̵㹤��", "do_save()");
		      var ArrAction3 = new Array(true, "�·��̵㹤��", "action_draft.gif", "�·��̵㹤��", "do_send()");
		      var ArrActions = new Array(ArrAction1,ArrAction2,ArrAction3);
		      var ArrSinoViews = new Array();
		      printTitleBar("�·��̵㹤��");
		      printToolBar();
  			</script>
  			<input type="hidden" name="act" value="">
  			<input type="hidden" name="receivdBy" value="<%=headerDTO.getReceivdBy()%>">
  			<input type="hidden" name="companyCode" value="<%=headerDTO.getCompanyCode()%>">
  			<input type="hidden" name="bookTypeCode" value="<%=headerDTO.getBookTypeCode()%>">
  			<input type="hidden" name="orgId" value="<%=headerDTO.getRborganizationId()%>">
  			<input type="hidden" name="transId" value="<%=headerDTO.getTransId()%>">
  			<input type="hidden" name="lineId" value="<%=headerDTO.getLineId()%>">
  			<input type="hidden" name="nowDate" value="<%=headerDTO.getEndDisableDate()%>">
  			
			<table width="100%" border=0 class="queryTable" cellpadding="2"
				cellspacing="0"
				style="width: 100%; TABLE-LAYOUT: fixed; word-break: break-all">
				<tr>
					<td align=right width="13%">
						�����ˣ�
					</td>
					<td width="17%">
						<input name="receivdByName" class="input_style2" readonly
							style="width: 100%;" value="<%=headerDTO.getReceivdByName()%>">
					</td>

					<td align=right width="13%">
						��˾��
					</td>
					<td width="27%">
						<input name="company" class="input_style2" readonly
							style="width: 100%;" value="<%=headerDTO.getCompany()%>">
					</td>
					<td align=right width="13%">
						�ʲ��ʲ���
					</td>
					<td width="17%">
						<input name="bookTypeName" class="input_style2" readonly
							style="width: 100%;" value="<%=headerDTO.getBookTypeName()%>">
					</td>
				</tr>

				<tr>
					<td width="13%" align="right">
						��׼�տ�ʼ���ڣ�
					</td>
					<td width="17%">
						<input style="width: 100%" type="text" class="input_style2"
							name="startDate" value="<%=headerDTO.getStartDate()%>"
							readonly
							>
					</td>
					<td width="13%" align="right">
						����
					</td>
					<td width="27%">
						<input style="width: 100%" type="text" class="input_style2"
							name="endDate" value="<%=headerDTO.getEndDate()%>"
						    readonly  
							>
					</td>
					
				     <td align="right" width="13%">��׼�գ�</td>
                        <td width="17%"><input type="text" name="lastUpdateDate"
                                  value="<%=headerDTO.getLastUpdateDate()%>" class="finputNoEmpty"
                                  style="width:80%" title="���ѡ������"
                                  readonly  onclick="gfPop.fEndPop(startDate,lastUpdateDate)">
                     </td>
                    
					
				</tr>
				
				<tr>
				    <td width="13%" align="right">
						ִ�п�ʼ���ڣ�
					</td>
					<td width="17%">
						<input style="width: 100%" type="text" class="input_style2"
							name="startCreationDate" value="<%=headerDTO.getStartCreationDate()%>"
							readonly
							>
					</td>
					<td width="13%" align="right">
						����
					</td>
					<td width="27%">
						<input style="width: 100%" type="text" class="input_style2"
							name="endCreationDate" value="<%=headerDTO.getEndCreationDate()%>"
						    readonly  
							>
					</td>
					<td align=right width="13%">
						��ʵ���·���ʽ��
					</td>
			        <td width="17%">
						<select name="createType" class="select_style1" style="width:100%" onchange="document.getElementById('sf_priority').value = this.value;"><%=headerDTO.getTypeValueOption()%></select>
					</td>
				</tr>

			</table>



         <script type="text/javascript">
	        var columnArr = new Array("checkbox","��������","�·���ʽ","���������");
	        var widthArr = new Array("4","32%","32%","32%");
	        printTableHead(columnArr, widthArr);
         </script>
         
         
      <%
        RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
        if (rows != null && !rows.isEmpty()) {
      %>
    <div id="dataDiv" style="overflow-y:scroll;position:absolute;width:100%;left:1px;margin-left:0px" onscroll="document.getElementById('headDiv').scrollLeft=this.scrollLeft;">
        <table id="dataTable" width="100%" height="30%" border="1" style="table-layout:fixed;">
            <%
                Row row = null;
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
                    String iStr = String.valueOf(i);
                    
                    String creationTypeL = "creationType" + iStr;
                    String creationTypeV = row.getStrValue("CREATION_TYPE");//�ɹ�����
                    
                    String createValueL = "createValue" + iStr;
                    String createValueV = row.getStrValue("CREATE_VALUE");//�ɹ�����
                    
                    String sendTypeL = "sendType" + iStr;
                    String sendTypeV = row.getStrValue("SEND_TYPE");//�ʲ���ǩ��
                    
                    String sendValueL = "sendValue" + iStr;
                    String sendValueV = row.getStrValue("SEND_VALUE");//�ʲ���ǩ��
                    
                    String transUserL = "transUser" + iStr;
                    String transUserV = ""; //
                    
                    String transNameL="transName"+iStr;
                    String transNameV=row.getStrValue("TRANS_NAME");
                   
            %>
          
            <tr class="dataTR">
                 <td width="4%" align="left" id="disableTR<%=i%>">
			     <input type="checkbox" name="subCheck" disabled="disabled"> 
			     </td>
                <td width="32%" style="cursor:pointer" ><input type="text" name="createValue" id="<%=createValueL%>" value="<%=createValueV%>" class="finput" readonly></td>
                <td width="32%" style="cursor:pointer" ><input type="text" name="sendValue" id="<%=sendValueL%>" value="<%=sendValueV%>" class="finput" readonly></td>
                <td width="20%" style="cursor:pointer" ><input type="text" name="transName" id="<%=transNameL%>" value="<%=transNameV%>" readonly class="finput" onClick="do_SelectPerson(this)" title="���ѡ�����Ľ�����" style="cursor:pointer"></td>
            </tr>
            
            <td style="display:none">
                 <input type="hidden" name="transUser" id="<%=transUserL%>" value="<%=transUserV%>">
                <input type="hidden" name="creationType" id="<%=creationTypeL%>" value="<%=creationTypeV%>">
                <input type="hidden" name="sendType" id="<%=sendTypeL%>" value="<%=sendTypeV%>">
            </td>
            <%
                    }
                }
            %>
        </table>
    </div>
    
    
         
		</form>

	</body>
	<iframe width=174 height=189 name="gToday:normal:calendar.js"
		id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm"
		scrolling="no" frameborder="0"
		style="visibility: visible; z-index: 999; position: absolute; left: -500px; top: 0;">
	</iframe>
</html>

<script language="javascript">
	function initPage2(){
		do_SetPageWidth();
	}

	function do_create(){
		document.mainFrm.act.value = "DO_CREATE";
	    document.mainFrm.submit();
	}

	//ѡ��������������
	function do_SelectPerson(lineBox){
		var objName = lineBox.name;
		var objId = lineBox.id;
		var idNumber = objId.substring(objName.length);
		//var companyCode=document.getElementById("companyCode" + idNumber).value;
		var orgId=document.getElementById("orgId").value;
		var lookUpName = "LOOK_UP_ORUSER";
		var userPara="orgId="+orgId;
		var dialogWidth = 50;
		var dialogHeight = 28;
		var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight,userPara);
		if (objs) {
			var obj = objs[0];
			document.getElementById("transUser" + idNumber).value=obj["userId"];
			document.getElementById("transName" + idNumber).value=obj["userName"];
		}
	}

	//�·��̵�����
	function do_send(){
		alert(2);
        var lastUpdateDate=document.mainFrm.lastUpdateDate.value;
        var nowDate=document.mainFrm.nowDate.value;
        var startCreationDate=document.mainFrm.startCreationDate.value;
		if(nowDate<startCreationDate){
			alert("δ������ִ�п�ʼ���ڣ��������·�����");
		}
        
        //��׼��У�� �ǿ��ҷ�Χ���̵��׼��֮��
        if(lastUpdateDate==null||lastUpdateDate==""){
			alert("��ѡ���׼��");
			return;
        }
        var checkArr = document.getElementsByName("subCheck");
        var count=checkArr.length;
        var flag=0;
        for(var i=0;i<count;i++){
			var recevdByName=document.getElementById("transName" + i).value;
			if(recevdByName==null||recevdByName==""){
				var ii=i+1;
				alert("��"+ii+"����ѡ�����������");
				flag=1;
				break;
			}
        }
        if(flag==0){
	        document.mainFrm.act.value = "DO_SEND";
		    document.mainFrm.submit();
        }
        
	}

	function do_save(){
		var lastUpdateDate=document.mainFrm.lastUpdateDate.value;
		var endDate=document.mainFrm.endDate.value;
		
        //��׼��У�� �ǿ��ҷ�Χ���̵��׼��֮��
        if(lastUpdateDate==null||lastUpdateDate==""){
			alert("��ѡ���׼��");
			return;
        }

        if(lastUpdateDate>endDate){
			alert("�̵��׼�ձ����ڻ�׼�շ�Χ��ѡ��");
			return ;
        }
        
        var checkArr = document.getElementsByName("subCheck");
        var count=checkArr.length;
        var flag=0;
        for(var i=0;i<count;i++){
			var recevdByName=document.getElementById("transName" + i).value;
			if(recevdByName==null||recevdByName==""){
				var ii=i+1;
				alert("��"+ii+"����ѡ�����������");
				flag=1;
				break;
			}
        }
        if(flag==0){
	        document.mainFrm.act.value = "DO_SAVE";
		    document.mainFrm.submit();
        }
	}
</script>