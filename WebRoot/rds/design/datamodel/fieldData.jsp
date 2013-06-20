<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@ include file="/rds/public/listPageInclude.jsp"%>

<html>
<head>
    <title>ģ���ֶ���ϸ��Ϣ</title>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/util/AjaxProcess.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/rds/WebLibary/js/validate/FormValidate.js"></script>
</head>
<body leftmargin="0" topmargin="0" onload="do_initPage();" onkeydown="autoExeFunction('do_Save()');">
<jsp:include page="/message/MessageProcess"/>
<html:form action="/rds/modelFieldAction" method="post">
    <logic:empty property="modelId" name="dataModelFrm">
        ��<div align="center">
            <table border="0" width="100%" cellspacing="0" cellpadding="0" style="text-align: center" height="80%">
                <tr>
                    <td><font size="7" face="΢���ź�">���ڡ��Ѷ���ģ�͡���ѡ��ģ��</font></td>
                </tr>
            </table>
        </div>
    </logic:empty>
    <logic:notEmpty property="modelId" name="dataModelFrm">
        ��<div align="center">
            <table border="0" width="100%" cellspacing="0" cellpadding="0" style="text-align: center">
                <tr>
                    <td><font size="3" face="΢���ź�">ģ�͡�<bean:write name="dataModelFrm" property="modelName"/>���ֶ���Ϣ </font></td>
                </tr>
            </table>
        </div>
        <div id="headDiv" style="overflow-x:hidden;overflow-y:scroll;position:absolute;top:43px;left:1px;width:100%">
            <table id="headTable" border="1" width="100%" class="headerTR" >
                <tr class="headerTR" style="text-align:center;cursor:pointer">
                    <td width="20%"><span class="resizeDivClass"></span>�ֶ�����</td>
                    <td width="20%"><span class="resizeDivClass"></span>�ֶ�����</td>
                    <td width="20%"><span class="resizeDivClass"></span>�ֶ�����</td>
                    <td width="20%"><span class="resizeDivClass"></span>�Ƿ���Ч</td>
                    <td width="20%"><span class="resizeDivClass"></span>��������</td>
                    <td style="display:none">�������ֶ�</td>
                </tr>
            </table>
        </div>
        <logic:notEmpty name="dataModelFrm" property="fields">
            <div id="dataDiv" style="overflow:scroll;height:60%;width:100%;position:absolute;top:66px;left:1px" align="left"
                     onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
                <table width="100%" border="1" bordercolor="#666666" id="dataTable" style="TABLE-LAYOUT:fixed;word-break:break-all">
                <logic:iterate id="fields" name="dataModelFrm" property="fields" indexId="index">
                    <tr class="dataTR">
                        <td width="20%"><html:text property="fieldName" name="fields" indexed="true" styleClass="tableInput1" readonly="true"/></td>
                        <td width="20%"><html:text property="fieldDesc" name="fields" indexed="true" styleClass="tableInput1" onkeyup="do_ProcessKey()"/></td>
                        <td width="20%"><html:text property="fieldType" name="fields" indexed="true" styleClass="tableInput1" readonly="true"/></td>
                        <td width="20%"><html:select property="enabled" name="fields" indexed="true" style="width:100%"><bean:write name="fields" property="enabledOptions" filter="false"/></html:select></td>
                        <td width="20%"><html:text property="creationDate" name="fields" indexed="true" styleClass="tableInput2" readonly="true"/></td>
                        <td style="display:none">
                            <html:hidden property="fieldId" name="fields" indexed="true"/>
                        </td>
                    </tr>
            </logic:iterate>
            </table>
           </div>
        </logic:notEmpty>
        <div id="pageNaviDiv" align="right"><a href="" onclick="do_Prev();return false;">��һ��</a> <a href="" onclick="do_Next();return false;">��һ��</a> </div>
    </logic:notEmpty>
    <html:hidden property="act" styleId="act"/>
    <html:hidden property="modelId" styleId="modelId"/>
    <html:hidden property="modelName" styleId="modelName"/>
    <html:hidden property="dataSaved" styleId="dataSaved"/>
</html:form>
</body>
</html>
<script type="text/javascript">
function do_initPage(){
    do_SetPageWidth();
}

function do_Check_Save(){
    var isValid = true;
    var modelId = document.getElementById("modelId").value;
    if(modelId == ""){
        isValid = false;
        alert("�ѡ�ҳ�治���κ�ģ����Ϣ,���ܱ���,�����ڡ��Ѷ���ģ�͡���ѡ��ģ�ͻ�������ģ��!");
    } else {
        var fieldNames = "fieldDesc;enabled";
        var fieldDescs = "�ֶ�����;�Ƿ���Ч";
        isValid = formValidate(fieldNames, fieldDescs, EMPTY_VALIDATE, "fields");
    }
    return isValid;
}

function do_Next(){
    var ajaxProcessor = new RDSAjaxProcessor(do_ResponseCheckResult, false);
    ajaxProcessor.setServiceClass("com.sino.rds.design.datamodel.service.ModelFieldProcessService");
    ajaxProcessor.setMethodName("hasModelField");
    ajaxProcessor.setStrutsFrm("dataModelFrm");
    ajaxProcessor.setSendData("modelId=" + document.getElementById("modelId").value);
    ajaxProcessor.performTask();
}

function do_ResponseCheckResult(checkResult){
    if(checkResult == "N"){
        alert("��δ�����κ�ģ���ֶΣ����ܼ���...");
    } else {
        var actionURL = "/rds/reportDefineAction.do";
        actionURL += "?act=DETAIL_ACTION";
        actionURL += "&modelId=" + document.getElementById("modelId").value;
        actionURL += "&modelName=" + document.getElementById("modelName").value;
        var frm = window.parent.frames["reportDataFrm"];
        frm.location = actionURL;
        setTimeout(do_NextTab, 2000);
    }
}

function do_NextTab(){
    window.parent.tabBox.doclick(5, "");
}

function do_Prev(){
    window.parent.tabBox.doclick(2, "");
}

function do_ProcessKey(){
    var srcObj = event.srcElement;
    while (srcObj.tagName != "TR"){
        srcObj = srcObj.parentNode;
    }
    var rowIndex = srcObj.rowIndex;
    var keyCode = event.keyCode;
    if(keyCode == 38){//���ϼ�
        rowIndex--;
    } else if(keyCode == 40){//���¼�
        rowIndex++;
    } else {
        return;
    }
    var tab = document.getElementById("dataTable");
    var rowCount = tab.rows.length;
    if(rowIndex >= 0 && rowIndex < rowCount){
        var tr = tab.rows[rowIndex];
        var obj = getTrNode(tr, "fieldDesc");
        if(obj != null && obj != undefined){
            obj.focus();
            obj.select();
        }
    }
}
</script>