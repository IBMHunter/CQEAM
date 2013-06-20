<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@ include file="/rds/public/listPageInclude.jsp"%>

<html>
<head>
    <title>��������ֶ���ϸ��Ϣ</title>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/toolbar/dynlayer.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/form/FormProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/validate/FormValidate.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/form/CheckboxProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/form/SelectProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/table/TableProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/util/AjaxProcess.js"></script>
</head>
<body leftmargin="0" topmargin="1" onload="do_initPage();" onkeydown="autoExeFunction('do_Save()');">
<jsp:include page="/message/MessageProcess"/>
<html:form action="/rds/reportCategoryProcess" method="post">
    <script type="text/javascript">
        var title = "��������ֶ���ϸ��Ϣ";
        ArrActions[4][0] = true;
        ArrActions[15][0] = true;
    </script>
    <logic:empty property="reportId" name="reportCategoryProcessFrm">
        ��<div align="center">
            <table border="0" width="100%" cellspacing="0" cellpadding="0" style="text-align: center" height="80%">
                <tr>
                    <td><font size="7" face="΢���ź�">���ڡ��Ѷ��屨����ѡ�񱨱�</font></td>
                </tr>
            </table>
        </div>
    </logic:empty>
    <logic:notEmpty property="reportId" name="reportCategoryProcessFrm">
        <logic:equal value="1" property="report.reportType" name="reportCategoryProcessFrm">
            ��<div align="center">
                <table border="0" width="100%" cellspacing="0" cellpadding="0" style="text-align: center" height="80%">
                    <tr>
                        <td><font size="7" face="΢���ź�">�ǽ��汨���޷����ֶ���Ϣ</font></td>
                    </tr>
                </table>
            </div>
        </logic:equal>
        <logic:notEqual value="1" property="report.reportType" name="reportCategoryProcessFrm">
        ��<div align="center">
            <table border="0" width="100%" cellspacing="0" cellpadding="0" style="text-align: center">
                <tr>
                    <td><font size="3" face="΢���ź�">����<bean:write name="reportCategoryProcessFrm" property="report.reportName"/>���ķ����ֶ���Ϣ</font></td>
                </tr>
                <tr>
                    <td height="1px"><hr color="#800000" size="1" style="width:100%"></td>
                </tr>
            </table>
        </div>
    <table style="width: 100%">
        <tr>
            <td>
                <table border="0" width="100%" cellspacing="1">
                    <tr>
                        <td width="8%" height="23">��</td>
                        <td width="17%" height="23" align="center">��ѡ�ֶ�</td>
                        <td width="14%" height="23" align="center">����</td>
                        <td width="19%" height="23" align="center" colspan="2">����ѡ�ֶ�</td>
                        <td width="17%" height="23" align="center">����㼶</td>
                        <td width="8%" height="23">&nbsp;&nbsp;</td>
                    </tr>
                    <tr style="height:160px">
                        <td width="8%" rowspan="3"></td>
                        <td width="17%" rowspan="3">
                            <html:select property="availableField" styleId="availableField" multiple="true" style="width:100%;height:100%"><bean:write name="reportCategoryProcessFrm" property="availableFieldsOptions" filter="false"/></html:select>
                        </td>
                        <td width="14%" align="center" height="75">
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�����ѡ" id="aboveAdd" onclick="do_AddSelected()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="ɾ����ѡ" id="aboveRemove" onclick="do_ClearSelected()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="ɾ��ȫ��" id="aboveRemoveAll" onclick="do_ClearAll()"></P>
                        </td>
                        <td width="2%" align="center" height="75">
                        	<P style="margin-top: 2px; margin-bottom: 2px">��</P>
                        	<P style="margin-top: 2px; margin-bottom: 2px">��</P>
                        	<P style="margin-top: 2px; margin-bottom: 2px">��</P>
                        	<P style="margin-top: 2px; margin-bottom: 2px">��</P>
						</td>
                        <td width="17%" height="75">
                            <html:select property="aboveField" styleId="aboveField" multiple="true" style="width:100%;height:100%" ondblclick="do_ClearSelected()"><bean:write name="reportCategoryProcessFrm" property="aboveFieldsOptions" filter="false"/></html:select></td>
                        <td width="17%" align="center" height="75">
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�� ��" id="aboveTop" onclick="do_PositionTop()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�� ��" id="aboveUp" onclick="do_PositionUp()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�� ��" id="aboveDown" onclick="do_PositionDown()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�� ��" id="aboveBottom" onclick="do_PositionBottom()"></P>
                        </td>
                        <td width="8%" rowspan="3"> </td>
                    </tr>
                    <tr height="2">
                        <td width="50%" align="center" height="2" colspan="4">
						<hr style="width:100%" color="#666666" size="1"></td>
                    </tr>
                    <tr height="75">
                        <td width="14%" align="center" height="75">
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�����ѡ" id="leftAdd" onclick="do_AddSelected()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="ɾ����ѡ" id="leftRemove" onclick="do_ClearSelected()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="ɾ��ȫ��" id="leftRemoveAll" onclick="do_ClearAll()"></P>
                        </td>
                        <td width="2%" align="center" height="75">
                        	<P style="margin-top: 2px; margin-bottom: 2px">��</P>
                        	<P style="margin-top: 2px; margin-bottom: 2px">��</P>
                        	<P style="margin-top: 2px; margin-bottom: 2px">��</P>
                        	<P style="margin-top: 2px; margin-bottom: 2px">��</P>
						</td>
                        <td width="17%" height="75"><html:select property="leftField" styleId="leftField" multiple="true" style="width:100%;height:100%" ondblclick="do_ClearSelected()"><bean:write name="reportCategoryProcessFrm" property="leftFieldsOptions" filter="false"/></html:select></td>
                        <td width="17%" align="center" height="75">
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�� ��" id="leftTop" onclick="do_PositionTop()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�� ��" id="leftUp" onclick="do_PositionUp()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�� ��" id="leftDown" onclick="do_PositionDown()"></P>
                            <P style="margin-top: 2px; margin-bottom: 2px"><input type="button" value="�� ��" id="leftBottom" onclick="do_PositionBottom()"></P>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <div id="headDiv" style="overflow-x:hidden;overflow-y:scroll;position:absolute;top:285px;left:1px;width:100%">
        <table id="headTable" border="1" width="100%">
            <tr class="headerTR" style="text-align:center">
                <td width="8%">����λ��</td>
                <td width="15%">�ֶ�����</td>
                <td width="15%">�ֶ�����</td>
                <td width="8%">����㼶</td>
                <td width="10%">��ʾ��־</td>
                <td width="8%">�Ƿ���Ч</td>
                <td style="display:none">�������ֶ�</td>
            </tr>
        </table>
    </div>
    <div id="dataDiv" style="overflow:scroll;height:60%;width:100%;position:absolute;top:358px;left:1px" align="left"
		     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
        <table width="100%" border="1" bordercolor="#666666" id="dataTable" style="TABLE-LAYOUT:fixed;word-break:break-all">
            <logic:notEmpty name="reportCategoryProcessFrm" property="categoryFields">
                <logic:iterate id="categoryFields" name="reportCategoryProcessFrm" property="categoryFields" indexId="index">
                    <tr>
                        <td width="8%"><html:text property="viewLocationName" name="categoryFields" indexed="true" styleClass="tableReadonlyInput1" readonly="true"/></td>
                        <td width="15%"><html:text property="fieldName" name="categoryFields" indexed="true"  styleClass="tableReadonlyInput1" readonly="true"/></td>
                        <td width="15%"><html:text property="fieldDesc" name="categoryFields" indexed="true" styleClass="tableInput1"/></td>
                        <td width="8%"><html:text property="categoryLevel" name="categoryFields" indexed="true"  styleClass="tableReadonlyInput3" readonly="true"/></td>
                        <td width="10%"><html:select property="displayFlag" name="categoryFields" indexed="true" style="width:100%"><bean:write name="categoryFields" property="displayFlagOptions" filter="false"/></html:select></td>
                        <td width="8%"><html:select property="enabled" name="categoryFields" indexed="true" style="width:100%"><bean:write name="categoryFields" property="enabledOptions" filter="false"/></html:select></td>
                        <td style="display:none">
                            <html:hidden property="categoryId" name="categoryFields" indexed="true"/>
                            <html:hidden property="fieldId" name="categoryFields" indexed="true"/>
                            <html:hidden property="viewLocation" name="categoryFields" indexed="true"/>
                        </td>
                    </tr>
                </logic:iterate>
            </logic:notEmpty>
            <logic:empty name="reportCategoryProcessFrm" property="categoryFields">
                <tr style="display:none">
                    <td width="8%"><input type="text" name="categoryFields[0].viewLocationName" class="tableReadonlyInput1" readonly="true"></td>
                    <td width="15%"><input type="text" name="categoryFields[0].fieldName" class="tableReadonlyInput1" readonly="true"></td>
                    <td width="15%"><input type="text" name="categoryFields[0].fieldDesc" class="tableInput1"></td>
                    <td width="8%"><input type="text" name="categoryFields[0].categoryLevel" class="tableReadonlyInput3" readonly="true"></td>
                    <td width="10%"><select name="categoryFields[0].displayFlag" style="width:100%"></select></td>
                    <td width="8%"><select name="categoryFields[0].enabled" style="width:100%"></select></td>
                    <td style="display:none">
                        <input type="hidden" name="categoryFields[0].categoryId">
                        <input type="hidden" name="categoryFields[0].fieldId">
                        <input type="hidden" name="categoryFields[0].viewLocation">
                    </td>
                </tr>
            </logic:empty>
        </table>
       </div>
        </logic:notEqual>
    </logic:notEmpty>
    <html:select property="referenceEnabled" styleId="referenceEnabled" style="display:none"><bean:write name="reportCategoryProcessFrm" property="enabledOptions" filter="false"/></html:select>
    <html:select property="referenceDisplayFlag" styleId="referenceDisplayFlag" style="display:none"><bean:write name="reportCategoryProcessFrm" property="displayFlagOptions" filter="false"/></html:select>
    <html:hidden property="act" styleId="act"/>
    <html:hidden property="reportId" styleId="reportId"/>
    <html:hidden property="dataSaved" styleId="dataSaved"/>
    <div id="pageNaviDiv" align="right"><a href="" onclick="do_Prev();return false;">��һ��</a> </div>
</html:form>
</body>
</html>
<script type="text/javascript">
var srcObj = null;

function do_initPage(){
    do_SetPageWidth();
}

function do_AddSelected(){
    var fromObj = document.getElementById("availableField");
    var selectedCount = getSelectedCount(fromObj);
    if(selectedCount == 0){
        alert("δѡ�д���ӵ��ֶΣ�����ѡ���ֶΡ�");
        return;
    }
    var eventSrc = event.srcElement.id;
    srcObj = eventSrc;
    var targetObj = (eventSrc == "aboveAdd") ? "aboveField" : "leftField";
    moveSelectedOption("availableField", targetObj);
    do_RequestServerFields();
}

function do_RequestServerFields(){
    var eventSrc = event.srcElement.id;
    var targetObj = (eventSrc == "aboveAdd") ? "aboveField" : "leftField";
    selectAll(targetObj);
    var fieldIds = getSelectValue(targetObj, ",");
    var lineFieldIds = do_GetLineFieldIds();
    fieldIds = do_MinusFields(fieldIds, lineFieldIds);

    var userParameter = "reportId=" + document.getElementById("reportId").value;
    userParameter += "&fieldIds=" + fieldIds;

    var ajaxProcessor = new RDSAjaxProcessor(do_ProcessFieldsDisplay, true);
    ajaxProcessor.setServiceClass("com.sino.rds.design.report.service.ReportCategoryProcessService");
    ajaxProcessor.setMethodName("getSelectedFieldsXML");
    ajaxProcessor.setStrutsFrm("reportCategoryProcessFrm");
    ajaxProcessor.setSendData(userParameter);
    ajaxProcessor.performTask();
}

function do_ProcessFieldsDisplay(returnXML){
    if(returnXML){
        var tab = document.getElementById("dataTable");
        var tabProcessor = new TableProcessor(tab);
        tabProcessor.setNamePrefix("categoryFields");
        tabProcessor.setUniqueField("fieldId");
        var lineNodes = returnXML.getElementsByTagName("line");
        if (lineNodes != null && lineNodes != undefined) {
            var viewLocation = (srcObj == "aboveAdd") ? "ABOVE":"LEFT";
            var viewLocationName = (srcObj == "aboveAdd") ? "�����Ϸ�":"������";
            var fieldCount = lineNodes.length;
            for (var i = 0; i < fieldCount; i++) {
                var dtoData = xmlNode2DTO(lineNodes[i]);
                dtoData["viewLocation"] = viewLocation;
                dtoData["viewLocationName"] = viewLocationName;
                tabProcessor.addRowData(dtoData);
                do_ProcessLineOptions();
            }
        }
    }
}

function xmlNode2DTO(lineNode) {
    var dto = new Object();
    var fields = lineNode.childNodes;
    for (var i = 0; i < fields.length; i++) {
        var field = fields[i];
        var fieldName = field.tagName;
        dto[fieldName] = field.text;
    }
    return dto;
}

function do_ProcessLineOptions(){
    var tab = document.getElementById("dataTable");
    var rows = tab.rows;
    var tr = rows[rows.length - 1];

    var toObj = getTrNode(tr, "enabled");
    var fromObj = document.getElementById("referenceEnabled");
    if(toObj.length == 0){
        copyObjOptions(fromObj, toObj);
    }

    toObj = getTrNode(tr, "displayFlag");
    if(toObj.length == 0){
        fromObj = document.getElementById("referenceDisplayFlag");
        copyObjOptions(fromObj, toObj);
    }
}

function do_AddAll(){
    var eventSrc = event.srcElement.id;
    srcObj = eventSrc;
    var targetObj = (eventSrc == "aboveAdd") ? "aboveField" : "leftField";
    moveAllOption("availableField", targetObj);
}


function do_MinusFields(val1, val2){
    var returnValue = val1;
    if(val2 != ""){
        var arr1 = val1.split(",");
        var arr2 = val2.split(",");
        for(var i = 0; i < arr1.length; i++){
            for(var j = 0; j < arr2.length; j++){
                if(arr1[i] == arr2[j]){
                    arr1.splice(i, 1);
                    i--;
                    break;
                }
            }
        }
        returnValue = "";
        for(i = 0; i < arr1.length; i++){
            returnValue += arr1[i];
            if(i < arr1.length - 1){
                returnValue += ",";
            }
        }
    }
    return returnValue;
}

function do_GetLineFieldIds(){
    var fieldIds = "";
    var tab = document.getElementById("dataTable");
    var rows = tab.rows;
    for(var i = 0; i < rows.length; i++){
        var tr = rows[i];
        var node = getTrNode(tr, "fieldId");
        fieldIds += node.value;
        if(i < rows.length - 1){
            fieldIds += ",";
        }
    }
    return fieldIds;
}

function do_ClearSelected(){
    var eventSrc = event.srcElement.id;
    var fromObj = (eventSrc.indexOf("above") == 0) ? "aboveField" : "leftField";
    var alreadyField = document.getElementById(fromObj);
    var selectedCount = getSelectedCount(alreadyField);
    if(selectedCount == 0){
        alert("δѡ�д�ɾ�����ֶΣ�����ѡ���ֶΡ�");
        return;
    }
    if(confirm("ȷ��ɾ����ѡ�ֶ��𣿼���������ȷ������ť������������ȡ������ť��")){
        var tab = document.getElementById("dataTable");
        var rows = tab.rows;
        var categoryIds = "";
        for(var j = 0; j < alreadyField.length; j++){
            var option = alreadyField.options[j];
            if(option.selected){
                var clearField = option.value;
                for(var i = 0; i < rows.length; i++){
                    var tr = rows[i];
                    var node = getTrNode(tr, "fieldId");
                    if(node.value == clearField){
                        var categoryId = getTrNode(tr, "categoryId");
                        if(categoryId.value != ""){
                            categoryIds += categoryId.value + ",";
                        }
                        if(rows.length == 1){
                            node.value = "";
                            tr.style.display = "none";
                        } else {
                            tab.deleteRow(i);
                        }
                        break;
                    }
                }
            }
        }
        moveSelectedOption(fromObj, "availableField");
        if(categoryIds != ""){
            categoryIds = categoryIds.substring(0, categoryIds.length - 1);
            do_DeleteReportCategory(categoryIds);
        }
    }
}

function do_ClearAll(){
    if(confirm("ȷ��ɾ����ѡ�ֶ��𣿼���������ȷ������ť������������ȡ������ť��")){
        var eventSrc = event.srcElement.id;
        var fromObjName = (eventSrc == "aboveRemoveAll") ? "aboveField" : "leftField";
        var fromObject = document.getElementById(fromObjName);
        var optionCount = fromObject.options.length;
        moveAllOption(fromObjName, "availableField");
        if(optionCount > 0){
            var tab = document.getElementById("dataTable");
            var rows = tab.rows;
            var categoryIds = "";
            for(var i = 0; i < rows.length; i++){
                var tr = rows[i];
                var node = getTrNode(tr, "categoryId");
                var viewLocation = getTrNode(tr, "viewLocation").value;
                if((viewLocation == "ABOVE" && fromObjName == "aboveField")
                        || (viewLocation == "LEFT" && fromObjName == "leftField")){
                    if(rows.length > 1){
                        tab.deleteRow(i);
                        i--;
                    } else {
                        var field = getTrNode(tr, "fieldId");
                        field.value = "";
                        tr.style.display = "none";
                    }
                    if(node.value != ""){
                        categoryIds += node.value + ",";
                    }
                }
            }
            if(categoryIds != ""){
                categoryIds = categoryIds.substring(0, categoryIds.length - 1);
                do_DeleteReportCategory(categoryIds);
            }
        }
    }
}

function do_DeleteReportCategory(categoryIds){
    var userParameter = "reportId=" + document.getElementById("reportId").value;
    userParameter += "&categoryIds=" + categoryIds;
    var ajaxProcessor = new RDSAjaxProcessor(do_ProcessDeleteResponse, true);
    ajaxProcessor.setServiceClass("com.sino.rds.design.report.service.ReportCategoryProcessService");
    ajaxProcessor.setMethodName("deleteReportCategoryByIds");
    ajaxProcessor.setStrutsFrm("reportCategoryProcessFrm");
    ajaxProcessor.setSendData(userParameter);
    ajaxProcessor.performTask();
}

function do_ProcessDeleteResponse(XMLResponse){
    var content = XMLResponse.getElementsByTagName("content")[0];
    var contentValue = content.text;
    alert(contentValue);
}

function do_PositionTop(){
    var eventSrc = event.srcElement.id;
    var fromObj = (eventSrc == "aboveTop") ? "aboveField" : "leftField";
    var alreadyField = document.getElementById(fromObj);
    moveUp(alreadyField, true);
}

function do_PositionUp(){
    var eventSrc = event.srcElement.id;
    var fromObj = (eventSrc == "aboveUp") ? "aboveField" : "leftField";
    var alreadyField = document.getElementById(fromObj);
    moveUp(alreadyField, false);
}

function do_PositionDown(){
    var eventSrc = event.srcElement.id;
    var fromObj = (eventSrc == "aboveDown") ? "aboveField" : "leftField";
    var alreadyField = document.getElementById(fromObj);
    moveDown(alreadyField, false);
}

function do_PositionBottom(){
    var eventSrc = event.srcElement.id;
    var fromObj = (eventSrc == "aboveBottom") ? "aboveField" : "leftField";
    var alreadyField = document.getElementById(fromObj);
    moveDown(alreadyField, true);
}

function do_Check_Save(){
    var isValid = false;
    var reportId = document.getElementById("reportId").value;
    if(reportId == ""){
        isValid = false;
        alert("�ѡ�ҳ�治���κα�����Ϣ,���ܱ���,�����ڡ��Ѷ��屨����ѡ�񱨱����������!");
    } else {
        var tab = document.getElementById("dataTable");
        if(needValidate(tab)){
            selectAll("aboveField");
            selectAll("leftField");
            var fieldNames = "fieldDesc;displayFlag;enabled";
            var fieldLabels = "�ֶ�����;��ʾ��־;�Ƿ���Ч";
            var namePrefix = "categoryFields";
            isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE, namePrefix);
            if(isValid){
//                var leftCount = document.getElementById("availableField").length;
//                if(leftCount > 0){
//                    alert("�����ֶα��������ϡ�");
//                    isValid = false;
//                } else {
//                    do_ProcessSortNumber("aboveField");
//                    do_ProcessSortNumber("leftField");
//                }
                do_ProcessSortNumber("aboveField");
                do_ProcessSortNumber("leftField");
            }
        } else {
            alert("δѡ���ֶΣ����ܱ��档");
        }
    }
    return isValid;
}

function do_ProcessSortNumber(fieldPosition){
    var tab = document.getElementById("dataTable");
    var rows = tab.rows;
    var sortNumberObj = document.getElementById(fieldPosition);
    var options = sortNumberObj.options;
    var categoryLevel = null;
    for(var i = 0; i < rows.length; i++){
        var tr = rows[i];
        var node = getTrNode(tr, "fieldId");
        for(var j = 0; j < options.length; j++){
            var option = options[j];
            var fieldId = option.value;
            if(node.value == fieldId){
                categoryLevel = getTrNode(tr, "categoryLevel");
                categoryLevel.value = (j + 1);
                break;
            }
        }
    }
}


/**
 * ���ѡ�е���Ŀ��
 */
function getSelectedCount(select) {
    var retVal = 0;
    var optionObj = select.options;
    var optionCount = optionObj.length;
    for (var i = 0; i < optionCount; i++) {
        if (optionObj[i].selected) {
            retVal++;
        }
    }
    return retVal;
}

function do_Prev(){
    window.parent.tabBox.doclick(7, "");
}
</script>