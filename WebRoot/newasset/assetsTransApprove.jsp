<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.sino.ams.system.user.dto.*"%>
<%@ include file="/newasset/headerInclude.jsp"%>
<%@ include file="/newasset/headerInclude.htm"%>
<html>
<%
	AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) request.getAttribute(AssetsWebAttributes.ORDER_HEAD_DATA);
	String transType = headerDTO.getTransType();
	String transferType = headerDTO.getTransferType();
    String isThred = headerDTO.getThred();
    String transId = headerDTO.getTransId();
	SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(request);
    String orgId = userAccount.getOrganizationId()+"";
	String userId = userAccount.getUserId()+"";
	String provinceCode = headerDTO.getServletConfig().getProvinceCode();
	String provOrgId = headerDTO.getServletConfig().getProvinceOrgId()+"";
    String sectionRight = StrUtil.nullToString(request.getParameter("sectionRight"));
	String producedNewBarcode = headerDTO.getProducedNewBarcode();
	String attribute4 = headerDTO.getAttribute4();

    String toOrgId = headerDTO.getToOrganizationId()+"";
    String currRoleName = headerDTO.getCurrRoleName();
    ServletConfigDTO servletConfig = headerDTO.getServletConfig();
    DTOSet userDTO = userAccount.getUserRight();
    String roleName = "";
    Map  userRightMap = new HashMap();
    for (int i = 0;i<userDTO.getSize();i++) {
        SfUserRightDTO userRight = (SfUserRightDTO)userDTO.getDTO(i);
        roleName = userRight.getRoleName();
        userRightMap.put(roleName,roleName);
    }
    boolean isDptMgr = userRightMap.containsValue("���ž���");
    String isGroupPID = request.getAttribute(AssetsWebAttributes.IS_GROUP_PID).toString();//�Ƿ��й�˾�ۺϲ��������
    String isFinanceGroup = request.getAttribute(AssetsWebAttributes.IS_FINANCE_GROUP).toString();
%>
<head>
	<%
    	String titleText="";
    	String titleBar="";
    	SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(request);
    	if ("Y".equalsIgnoreCase(user.getIsTd())) {
    		titleText=headerDTO.getTransTypeValue()+"(TD)";
    		titleBar=headerDTO.getTransTypeValue()+"(TD)";
    	} else {
    		titleText=headerDTO.getTransTypeValue();
    		titleBar=headerDTO.getTransTypeValue();
    	}
    %>
	<title><%=titleText%></title>
</head>
<body  leftmargin="0" topmargin="0" onload="initPage()">
<form action="<%=AssetsURLList.ORDER_APPROVE_SERVLET%>" method="post" name="mainFrm">
<jsp:include page="/message/MessageProcess"/>
<jsp:include page="/newasset/headerDetail.jsp" flush="true"/>
<jsp:include page="/flow/include.jsp" flush="true"/>
<input type="hidden" name="faContentCode" value="<%=headerDTO.getFaContentCode()%>">
<input type="hidden" name="fromGroup" value="<%=headerDTO.getFromGroup()%>">
<input type="hidden" name="fromOrganizationId" value="<%=headerDTO.getFromOrganizationId()%>">
<input type="hidden" name="toOrganizationId" value="<%=headerDTO.getToOrganizationId()%>">
<input type="hidden" name="transType" value="<%=transType%>">
<input type="hidden" name="transferType" value="<%=headerDTO.getTransferType()%>">
<input type="hidden" name="created" value="<%=headerDTO.getCreated()%>">
<input type="hidden" name="createdBy" value="<%=headerDTO.getCreatedBy()%>">
<input type="hidden" name="transId" value="<%=transId%>">
<%
	if(transType.equals(AssetsDictConstant.ASS_SELL)){
%>
	<input type="hidden" name="procdureName" value="�ʲ���������">
<%
	} else if(transType.equals(AssetsDictConstant.ASS_RENT)){
%>
	<input type="hidden" name="procdureName" value="�ʲ���������">
<%
	} else if(transType.equals(AssetsDictConstant.ASS_SELL)){
%>
	<input type="hidden" name="procdureName" value="�ʲ���������">
<%
	} else {
%>
	<input type="hidden" name="procdureName" value="<%=headerDTO.getProcdureNameIncludeTd(user.getIsTd())%>">
<%
	}
%>
<input type="hidden" name="toGroup" id="toGroup" value="<%=headerDTO.getToGroup()%>">
<input type="hidden" name="groupPid" id="groupPid" value="<%=headerDTO.getGroupPid()%>">
<input type="hidden" name="groupProp" id="groupProp" value="<%=headerDTO.getGroupProp()%>">
<input type="hidden" name="provinceCode" value="<%=provinceCode%>">
<input type="hidden" name="act" value="">
<input type="hidden" name="flowCode" value="">
<fieldset style="border:1px solid #397DF3; position:absolute;top:115px;width:100%;height:80%">
    <legend>
<%
	if(!headerDTO.getTransStatus().equals(AssetsDictConstant.APPROVED)){
%>
        <img src="/images/button/pass.gif" id="img6" alt="ͨ��" onClick="do_ApproveOrder('<%=FlowConstant.FLOW_CODE_NEXT%>'); return false;">
<%
		if((transferType.equals(AssetsDictConstant.TRANS_BTW_COMP) && producedNewBarcode.equals("N")) || !transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){
%>
        <img src="/images/button/noPass.gif" id="img6" alt="��ͨ��" onClick="do_ApproveOrder('<%=FlowConstant.FLOW_CODE_PREV%>'); return false;">
<%
		}
		if(sectionRight.equals(AssetsDictConstant.NEW_TAG_NODE) && producedNewBarcode.equals("N")){
%>
		<img src="/images/eam_images/gen_tag.jpg" id="newTagImg" alt="�����±�ǩ" onClick="do_ProduceNewTag(); return false;">
<%
		}
	}
%>
        <img src="/images/eam_images/view_opinion.jpg" alt="�����������" onClick="viewOpinion(); return false;">
        <img src="/images/eam_images/view_flow.jpg" alt="��������" onClick="viewFlow(); return false;">
        <img src="/images/eam_images/close.jpg" id="img6" alt="�ر�" onClick="window.close(); return false;">
<%
		if(attribute4.equals(AssetsDictConstant.EDIT_ACCOUNT)){
//        if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP) && toOrgId.equals(orgId) && (currRoleName.equals("�ʲ����") || currRoleName.equals(servletConfig.getDeptAssetsMgr()))){
%>
        ͳһ���ã�
			<input type="checkbox" name="allLocation" id="allLocation"><label for="allLocation">����ص�</label>
			<input type="checkbox" name="allUser" id="allUser"><label for="allUser">��������</label>
			<input type="checkbox" name="allAccount" id="allAccount"><label for="allAccount">���۾��˻�</label>
			<input type="checkbox" name="allFaCategory" id="allFaCategory"><label for="allFaCategory">�����</label>
<%
		}
%>
    </legend>
 <div style="position:absolute;bottom:0px;top:0px;left:0px;right:0px;width:100%;height:96%">
<jsp:include page="/newasset/transLineDetail.jsp" flush="true"/>
 </div>
</fieldset>
     <%
        if (transType.equals(AssetsDictConstant.ASS_RED)||transType.equals(AssetsDictConstant.ASS_DIS)) {
    %>

    <div style="position:absolute;bottom:0px;top:700px;left:0px;right:0px;width:100%;height:100%">
         <jsp:include page="/newasset/uploadInclude.jsp" flush="true"/>
        <%--<% if (transType.equals(AssetsDictConstant.ASS_RED)){ %>--%>
          <%--&nbsp;&nbsp;&nbsp<input type="button" name="sub" value="EXCELģ������" onclick="do_exportToExcel();">--%>
        <%--<%} %>--%>
    </div>
      <%
          }
    %>
</form>
</body>
</html>

<script type="text/javascript">

function do_ApproveOrder(flowCode) {
    if(!do_ValidateSXRequirement(flowCode)){
		return;
	}
    if(!do_ValidateNewBarcode(flowCode)){
		return false;
	}
    var attribute44 = mainFrm.attribute4.value;
    if (attribute44 == "MTL_ASSETS" || attribute44 == "PROV_ASSETS") {
        var checkBoxCount= getCheckedBoxCount("subCheck");
        if (checkBoxCount <= 0) {
            alert("û��ѡ���κ��ʲ�������ִ�б��ϲ�����");
            return false;
        }
        if (confirm("ѡ���ʲ�Ϊ�ɱ����ʲ�����ȷ��ѡ���ʲ��Ƿ���ϱ���������")) {
        } else {
            return false;
        }
    }

    addApproveContent(flowCode);
	var transType = mainFrm.transType.value;
    mainFrm.flowCode.value = flowCode;
	var transferType = mainFrm.transferType.value;
	var sectionRight = mainFrm.sectionRight.value;
	var attribute1 = mainFrm.attribute1.value;
	if(attribute1 != ""){
		attribute1 = document.getElementById(mainFrm.attribute1.value).value;
	}
	var attribute2 = mainFrm.attribute2.value;//���ݴ���OU��֯ID
    var attribute3 = mainFrm.attribute3.value;//���
	var attribute4 = mainFrm.attribute4.value;//�������ݵĽ���OU��֯ID
	var attribute5 = mainFrm.attribute5.value;//����ģ���Ҫ�޸�
	var hiddenValue = mainFrm.hiddenRight.value;
    var fromOrganizationId = mainFrm.fromOrganizationId.value;

	var userId = "<%=userId%>";
	var groupId = mainFrm.fromGroup.value;
    var orgId = "<%=orgId%>";
	var procdureName = mainFrm.procdureName.value;
	var paramObj = new Object();
	paramObj.orgId = orgId;
	paramObj.useId = userId;
	paramObj.groupId = groupId;
	paramObj.procdureName = procdureName;
	paramObj.flowCode = "";
    var isDptMgr ="<%=isDptMgr%>";
    var isGroupPID = "<%=isGroupPID%>";
    var isFinanceGroup = "<%=isFinanceGroup%>";
    var isThred = "<%=isThred%>";
    if(flowCode == "<%=FlowConstant.FLOW_CODE_NEXT%>"){
        if (transType != "ASS-SUB") {
            if (attribute2) {
                orgId = document.getElementById(mainFrm.attribute2.value).value;
            }
        }
		if(attribute3 != "" && document.getElementById(attribute3).value != ""){
			groupId = document.getElementById(attribute3).value;
		}
		if(transType == "<%=AssetsDictConstant.ASS_RED%>"){//����
            paramObj.flowCode = "<%=AssetsDictConstant.OTHER_FLOW%>";
			if(transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>"){//�����ڵ���
                if(sectionRight == "<%=AssetsDictConstant.END_INN_DEPT%>"){
                    paramObj.flowCode = "<%=AssetsDictConstant.TRANS_INN_DEPT%>";
				} else if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
                    paramObj.flowCode = mainFrm.faContentCode.value;
				}
				if(mainFrm.provinceCode.value == "<%=AssetsDictConstant.PROVINCE_CODE_SX%>"){
					paramObj.flowCode = "";
				}
			} else if(transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>" && isThred != "Y"){//���ż����
                if(sectionRight == "<%=AssetsDictConstant.END_BTW_DEPT%>"){
                    paramObj.flowCode = "<%=AssetsDictConstant.TRANS_BTW_DEPT%>";
                } else if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
                    if(isDptMgr == "true" && isGroupPID == "false"){
                        paramObj.flowCode = mainFrm.faContentCode.value;
                    } else if(isDptMgr == "true" && isGroupPID == "true"){
                        paramObj.flowCode = "CW-ASSETS";
                    }
				} else if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW3%>"){
                    var fromDeptName = "<%=headerDTO.getFromDeptName()%>";
                    if(fromDeptName.indexOf("<%=AssetsDictConstant.FINANCE_DEPT%>") > 0){
                        paramObj.flowCode = "INNER"
                        groupId = mainFrm.toGroup.value;
                    } else {
                        paramObj.flowCode = "OTHER"
                    }
                }
                if(mainFrm.provinceCode.value == "<%=AssetsDictConstant.PROVINCE_CODE_SHAN%>"){
					if(attribute3 == "toGroup"){
                        groupId = mainFrm.toGroup.value;
                    }
                    if (hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW2%>") {
                        if (isFinanceGroup == "true") {
                            groupId = mainFrm.toGroup.value;
                            paramObj.flowCode = "FINANCE_GROUP";
                        } else {
                            paramObj.flowCode = "OTHER";
                        }
                    }
                }

                if(mainFrm.provinceCode.value == "<%=AssetsDictConstant.PROVINCE_CODE_SX%>"){
					if(attribute3 == "groupPid"){
//						paramObj.flowCode = "<%=AssetsDictConstant.MGR_DPT%>";
						paramObj.flowCode = "";
					}
				}
			} else if(transferType == "<%=AssetsDictConstant.TRANS_BTW_DEPT%>" && isThred == "Y"){//���ż����(���������������)
//                alert(sectionRight);
//                alert(hiddenValue);
                if(sectionRight == "<%=AssetsDictConstant.END_INN_DEPT%>"){
                    paramObj.flowCode = "<%=AssetsDictConstant.TRANS_INN_DEPT%>";
				}
            } else if(transferType == "<%=AssetsDictConstant.TRANS_BTW_COMP%>"){//��˾�����
//                alert(mainFrm.faContentCode.value +  '     sectionRight-' + sectionRight + "      hiddenValue-" + hiddenValue );

                if(sectionRight == "<%=AssetsDictConstant.END_BTW_COMP%>"){
                    paramObj.flowCode = "<%=AssetsDictConstant.TRANS_BTW_COMP%>";
				} else if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
                    if(isGroupPID == "true"){
                        paramObj.flowCode = "FIN-ASSETS";
                    } else {
                        paramObj.flowCode = mainFrm.faContentCode.value;
                    }
                } else if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW3%>"){
                    var fromDeptName = "<%=headerDTO.getFromDeptName()%>";
                    if(fromDeptName.indexOf("<%=AssetsDictConstant.FINANCE_DEPT%>") > 0){
                        paramObj.flowCode = "INNER"
                        groupId = mainFrm.toGroup.value;
                    } else {
                        paramObj.flowCode = "OTHER"
                    }
                }
				<%--if(mainFrm.provinceCode.value == "<%=AssetsDictConstant.PROVINCE_CODE_SX%>"){--%>
					<%--if(attribute3 == "groupPid"){--%>
						<%--if(mainFrm.groupPid.value != ""){--%>
							<%--paramObj.flowCode = "<%=AssetsDictConstant.MGR_DPT%>";--%>
							<%--groupId = mainFrm.groupPid.value;--%>
							<%--orgId = mainFrm.fromOrganizationId.value;--%>
						<%--} else {--%>
							<%--paramObj.flowCode = "<%=AssetsDictConstant.RCV_DPT%>";--%>
							<%--groupId = mainFrm.toGroup.value;--%>
							<%--orgId = mainFrm.toOrganizationId.value;--%>
						<%--}--%>
					<%--}--%>
				<%--}--%>
				if(fromOrganizationId == "<%=provOrgId%>" && attribute5 == "Y"){//�����ʡ��˾����
					attribute4 = document.getElementById(attribute4).value;
					paramObj.flowCode = fromOrganizationId;
					orgId = attribute4;
				}
                if(mainFrm.provinceCode.value == "<%=AssetsDictConstant.PROVINCE_CODE_SHAN%>"){
                    if(attribute3 == "toGroup"){
                        groupId = mainFrm.toGroup.value;
                        orgId = mainFrm.toOrganizationId.value;
                    }
				}
            }
		} else if(transType == "<%=AssetsDictConstant.ASS_DIS%>"){//����
			if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
                paramObj.flowCode = mainFrm.faContentCode.value;
                if(attribute2 != ""){
                    var comFaContentCode =  mainFrm.faContentCode.value;
                    attribute2 = document.getElementById(attribute2).value;
                    <% if ("Y".equalsIgnoreCase(user.getIsTd())) { %>
                    if(attribute2 == "101" && comFaContentCode == "MGR-ASSETS"){
						paramObj.flowCode = "MGR-COMP";
					} else if (attribute2 == "101" && comFaContentCode == "NET-ASSETS") {
                        paramObj.flowCode = "NET-COMP";
                    } else if (attribute2 == "101" && comFaContentCode == "MAR-ASSETS") {    //����δ�� MAR-ASSETS���������
                        paramObj.flowCode = "MAR-COMP";
                    }
                    <% } else {%>
                    if(attribute2 == "<%=provOrgId%>" && comFaContentCode == "MGR-ASSETS"){
						paramObj.flowCode = "MGR-COMP";
					} else if (attribute2 == "<%=provOrgId%>" && comFaContentCode == "NET-ASSETS") {
                        paramObj.flowCode = "NET-COMP";
                    } else if (attribute2 == "<%=provOrgId%>" && comFaContentCode == "MAR-ASSETS") {    //����δ�� MAR-ASSETS���������
                        paramObj.flowCode = "MAR-COMP";
                    }
                    <%}%>
				}
            }
        } else if(transType == "<%=AssetsDictConstant.ASS_CLR%>"){//����
			if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
				paramObj.flowCode = mainFrm.faContentCode.value;
				if(attribute2 != ""){
					attribute2 = document.getElementById(attribute2).value;
					if(attribute2 == "<%=provOrgId%>"){
						paramObj.flowCode = attribute2;
					}
				}
			}
		} else if(transType == "<%=AssetsDictConstant.ASS_FREE%>"){//����
			if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
				paramObj.flowCode = mainFrm.faContentCode.value;
				if(attribute2 != ""){
					attribute2 = document.getElementById(attribute2).value;
					if(attribute2 == "<%=provOrgId%>"){
						paramObj.flowCode = attribute2;
					}
				}
			}
		}else if(transType == "ASS-SUB"){//��ֵ
            var provOrgIdAssSub="<%=provOrgId%>";
            if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
				paramObj.flowCode = mainFrm.faContentCode.value;
				if(attribute2 != ""){
					attribute2 =  mainFrm.attribute2.value;;
					if(attribute2 == "provice82"&&orgId=="<%=provOrgId%>"){
						paramObj.flowCode = "PRO_OVER";
					}
				}
			}
		} else if(transType == "<%=AssetsDictConstant.ASS_SHARE %>" || transType == "<%=AssetsDictConstant.ASS_SELL %>" || transType == "<%=AssetsDictConstant.ASS_RENT %>" || transType == "<%=AssetsDictConstant.ASS_DONA %>"){
			if(hiddenValue == "<%=AssetsDictConstant.SPLIT_FLOW%>"){
				var faContentCode = mainFrm.faContentCode.value;
				if(isDptMgr=="true" ){
		     		 if(orgId != "<%=provOrgId%>"  && faContentCode=="NET-ASSETS" && isGroupPID == "false"){
			             paramObj.flowCode="C-NET";
			         }else if(orgId == "<%=provOrgId%>" && isGroupPID == "true"){
			         	 paramObj.flowCode="END";
			         } else if(orgId != "<%=provOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "false"){
			             paramObj.flowCode="C-MAR" ;
			         } else if (orgId != "<%=provOrgId%>"  && faContentCode=="MGR-ASSETS" && isGroupPID == "false"){
			             paramObj.flowCode="C-MGR";
			         }else if((orgId != "<%=provOrgId%>"  && faContentCode=="NET-ASSETS" && isGroupPID == "true") || (orgId == "<%=provOrgId%>" && faContentCode=="NET-ASSETS" && isGroupPID == "false")) {
			             paramObj.flowCode="P-NET" ;
			         } else if((orgId != "<%=provOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "true") || (orgId == "<%=provOrgId%>" && faContentCode=="MAR-ASSETS" && isGroupPID == "false")) {
			             paramObj.flowCode="P-MAR";
			         }else if((orgId != "<%=provOrgId%>" && faContentCode=="MGR-ASSETS" && isGroupPID == "true") || (orgId == "<%=provOrgId%>" && faContentCode=="MGR-ASSETS" && isGroupPID == "false")){
			             paramObj.flowCode="P-MGR";
			         }
	        	}
		    }
		    else {
			  	paramObj.flowCode="";
		    }
	    }
		paramObj.groupId = groupId;
		paramObj.orgId = orgId;
		paramObj.submitH = 'submitH()';
        assign(paramObj);
	} else {
		if(confirm("ȷ��Ҫ�˻��𣿼���������ȷ������ť������������ȡ������ť��")){
			if(mainFrm.approveContent.value == ""){
				alert("�˻�����ʱ������д�������");
				return;
			}
			submitH();
		}
	}
}

function submitH() {
    mainFrm.act.value = "<%=AssetsActionConstant.APPROVE_ACTION%>";
    mainFrm.submit();
}

function do_ShowDetail(barcode){
	var url = "/servlet/com.sino.ams.newasset.servlet.EtsFaAssetsServlet?act=<%=AssetsActionConstant.DETAIL_ACTION%>&barcode=" + barcode;
	var winName = "assetsWin";
	var style = "width=860,height=495,left=100,top=130";
	window.open(url, winName, style);
}

function do_ValidateNewBarcode(flowCode){
	var isValid = true;
	var sectionRight = "<%=sectionRight%>";
	var producedNewBarcode = "<%=producedNewBarcode%>";
	if(flowCode == "<%=FlowConstant.FLOW_CODE_NEXT%>" && sectionRight == "<%=AssetsDictConstant.NEW_TAG_NODE%>" && producedNewBarcode == "N"){
		var newTagObjs = document.getElementsByName("newBarcode");
		for(var i = 0; i < newTagObjs.length; i++){
			if(newTagObjs[i].value == ""){
				isValid = false;
				alert("���������±�ǩ�ţ�");
				return;
			}
		}
	}
	return isValid;
}

var xmlHttp = null;

function do_ProduceNewTag() {
	var url = "/servlet/com.sino.ams.newasset.servlet.NewTagProduceServlet";
	//url += "?transId=" + mainFrm.transId.value;
	url += "?transId=" + document.getElementsByName("transId")[0].value;
	url += "&refNumber=" + mainFrm.transNo.value;
	url += "&fromOrganizationId=" + mainFrm.fromOrganizationId.value;
	url += "&toOrganizationId=" + mainFrm.toOrganizationId.value;
	var sendData = new Array();
	var tagObjs = document.getElementsByName("barcode");
	var baocodeCount = tagObjs.length;
	for(var i = 0; i < baocodeCount; i++){
		sendData[i] = tagObjs[i].value;
	}
	do_ProcessSimpleAjax(url, sendData.toJSONString());
}

function do_ProcessResponse(responseContent){
	var oldTagObjs = document.getElementsByName("barcode");
	var newTagObjs = document.getElementsByName("newBarcode");
	var responArr = responseContent.split("&&&");
	var barcodeStr = "";
	var baocodeCount = oldTagObjs.length;
	var oldBarcode = "";
	var index = -1;
	for(var i = 0; i < baocodeCount; i++){
		oldBarcode = oldTagObjs[i].value;
		for(var j = 0; j < responArr.length; j++){
			barcodeStr = responArr[j];
			index = barcodeStr.indexOf(";");
			if(barcodeStr.substring(0, index) == oldBarcode){
				newTagObjs[i].value = barcodeStr.substring(index + 1);
				responArr = responArr.slice(0, j).concat(responArr.slice(j + 1));
				break;
			}
		}
	}
	document.getElementById("newTagImg").style.display = "none";
}

function do_SelectDepreciationAccount(lineBox){
    var toOrganizationId = mainFrm.toOrganizationId.value;
	var objName = lineBox.name;
	var objId = lineBox.id;
	var idNumber = objId.substring(objName.length);
	var userPara = "organizationId=" + toOrganizationId;
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ACCOUNT%>";
	var dialogWidth = 51;
	var dialogHeight = 29;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	var accountChk = mainFrm.allAccount;
    if(!accountChk.checked){
        if (objs) {
            obj = objs[0];
			lineBox.value = obj["accountCode"];
			lineBox.style.backgroundColor = "#336699";
			lineBox.style.color = "#FFFFFF";
		}
	} else {
        //alert(objs);
        if (objs) {
            var selectedData = objs[0]["accountCode"];
			var accounts = document.getElementsByName("depreciationAccount");
			var count = accounts.length;
			var dataTable = document.getElementById("dataTable");
			for(var i = 0; i < count; i++){
				accounts[i].value = selectedData;
				if(selectedData != ""){
					accounts[i].style.backgroundColor = "#336699";
					accounts[i].style.color = "#FFFFFF";
				}
			}
		}
	}
}

//ѡ�����ص�
function do_SelectLocation(lineBox){
	var toOrganizationId = mainFrm.toOrganizationId.value;
	var transferType = mainFrm.transferType.value;
	var deptCode = "";
	var objName = lineBox.name;
	var objId = lineBox.id;
	var idNumber = objId.substring(objName.length);
	var userPara = "organizationId=" + toOrganizationId + "&transferType=" + transferType + "&deptCode=" + deptCode;
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_ADDRESS%>";
	var dialogWidth = 55;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	var addressChk = mainFrm.allLocation;
    if(!addressChk){
        return;
	}
	if(!addressChk.checked){
        if (objs) {
			var obj = objs[0];
			document.getElementById("assignedToLocation" + idNumber).value = obj["toObjectNo"];
			document.getElementById("addressId" + idNumber).value = obj["addressId"];
			lineBox.value = obj["toObjectName"];
        } else {
			document.getElementById("assignedToLocation" + idNumber).value = "";
			document.getElementById("addressId" + idNumber).value = "";
			lineBox.value = "";
        }
	} else {
		var obj = null;
		var emptyData = false;
		if (objs) {
			obj = objs[0];
		} else {
			emptyData = true;
		}
		var addressNames = document.getElementsByName("assignedToLocationName");
        var addressNos = document.getElementsByName("assignedToLocation");
		var addressIds = document.getElementsByName("addressId");
		var count = addressNames.length;
		var dataTable = document.getElementById("dataTable");
		var rows = dataTable.rows;
		var row = null;
		var checkObj = null;
		var checkedCount = getCheckedBoxCount("subCheck");
		for(var i = 0; i < count; i++){
			if(checkedCount > 0){
				row = rows[i];
				checkObj = row.childNodes[0].childNodes[0];
				if(!checkObj.checked){
					continue;
				}
			}
			if(emptyData){
				addressNames[i].value = "";
				addressNos[i].value = "";
				addressIds[i].value = "";
			} else {
				addressNames[i].value = obj["toObjectName"];
				addressNos[i].value = obj["toObjectNo"];
				addressIds[i].value = obj["addressId"];
			}
		}
	}
}


//ѡ����������
function do_SelectPerson(lineBox){
	var toOrganizationId = mainFrm.toOrganizationId.value;
	var transferType = mainFrm.transferType.value;

	if(toOrganizationId == ""){
		alert("����ѡ�������˾����ѡ���������ˡ�");
		return;
	}
	var objName = lineBox.name;
	var objId = lineBox.id;
	var idNumber = objId.substring(objName.length);
	var deptCode = "";
	if(transferType == "<%=AssetsDictConstant.TRANS_INN_DEPT%>"){
		deptCode = mainFrm.fromDept.value;
	} else {
		deptCode = document.getElementById("responsibilityDept" + idNumber).value;
		if(deptCode == ""){
			alert("����ѡ��������ţ���ѡ����������");
			var deptObj = document.getElementById("responsibilityDeptName" + idNumber);
			do_SelectDept(deptObj);
			return;
		}
	}
	var userPara = "organizationId=" + toOrganizationId + "&transferType=" + transferType + "&deptCode=" + deptCode;
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_RECIIVER%>";
	var dialogWidth = 47;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight, userPara);
	var userChk = mainFrm.allUser;
	if(!userChk){
		return;
	}

	if(!userChk.checked){
		if (objs) {
			var obj = objs[0];
			document.getElementById("responsibilityUser" + idNumber).value = obj["employeeId"];
			lineBox.value = obj["userName"];
		} else {
			document.getElementById("responsibilityUser" + idNumber).value = "";
			lineBox.value = "";
		}
	} else {
		var obj = null;
		var emptyData = false;
		if (objs) {
			obj = objs[0];
		} else {
			emptyData = true;
		}
		var userNames = document.getElementsByName("responsibilityUserName");
		var users = document.getElementsByName("responsibilityUser");
		var count = userNames.length;
		var dataTable = document.getElementById("dataTable");
		var rows = dataTable.rows;
		var row = null;
		var checkObj = null;
		var checkedCount = getCheckedBoxCount("subCheck");
		for(var i = 0; i < count; i++){
			if(checkedCount > 0){
				row = rows[i];
				checkObj = row.childNodes[0].childNodes[0];
				if(!checkObj.checked){
					continue;
				}
			}
			if(emptyData){
				userNames[i].value = "";
				users[i].value = "";
			} else {
				userNames[i].value = obj["userName"];
				users[i].value = obj["employeeId"];
			}
		}
	}
}

function do_SelectFaCategoryCode(lineBox) {
	var objName = lineBox.name;
	var objId = lineBox.id;
	var idNumber = objId.substring(objName.length);
	var lookUpName = "<%=AssetsLookUpConstant.LOOK_UP_FACAT_CODE%>";
	var dialogWidth = 54;
	var dialogHeight = 30;
	var objs = lookUpAssetsValues(lookUpName, dialogWidth, dialogHeight);
	var faCatChk = mainFrm.allFaCategory;
	if(!faCatChk){
		return;
	}
	if(!faCatChk.checked){
		if (objs) {
			obj = objs[0];
			document.getElementById("faCategoryCode" + idNumber).value = obj["faCategoryCode"];
		} else {
			lineBox.value = "";
		}
	} else {
		var obj = null;
		if (objs) {
			obj = objs[0];
		} else {
			obj = new Object();
			obj.faCategoryCode = "";
			obj.faCategoryName = "";
		}
		var catCodes = document.getElementsByName("faCategoryCode");
		var count = catCodes.length;
		var dataTable = document.getElementById("dataTable");
		var rows = dataTable.rows;
		var row = null;
		var checkObj = null;
		var checkedCount = getCheckedBoxCount("subCheck");
		for(var i = 0; i < count; i++){
			if(checkedCount > 0){
				row = rows[i];
				checkObj = row.childNodes[0].childNodes[0];
				if(!checkObj.checked){
					continue;
				}
			}
			catCodes[i].value = obj["faCategoryCode"];
		}
	}
}
/**
 * ���ܣ����������������
 * 1�����շ��ʲ�����Ա������д������ص�;�������ˡ�
 * 2�����շ��ʲ���Ʊ�����д�����۾��˻�;�����
 */
function do_ValidateSXRequirement(flowCode){
	var isValid = true;
	var transferType = mainFrm.transferType.value;
    if("<%=provinceCode%>" == "<%=AssetsDictConstant.PROVINCE_CODE_SN%>"){
        if(flowCode == "<%=FlowConstant.FLOW_CODE_NEXT%>" && transferType == "<%=AssetsDictConstant.TRANS_BTW_COMP%>"){
			if("<%=toOrgId%>" == "<%=orgId%>"){
				var fieldNames = "";
				var fieldLabels = "";
				var validateType = EMPTY_VALIDATE;
				if("<%=currRoleName%>" == "<%=servletConfig.getDeptAssetsMgr()%>"){
					fieldNames = "assignedToLocationName;responsibilityUserName";
					fieldLabels = "����ص�;��������";
					isValid = formValidate(fieldNames, fieldLabels, validateType);
				} else if("<%=currRoleName%>" == "�ʲ����"){
                    fieldNames = "depreciationAccount";
					fieldLabels = "���۾��˻�";
					isValid = formValidate(fieldNames, fieldLabels, validateType);
				}
			}
		}
	}
	return isValid;
}

function initPage(){
    window.focus();
    do_SetPageWidth();
}
</script>
