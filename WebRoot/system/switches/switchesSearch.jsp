<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2007-10-11
  Time: 17:03:50
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>�ص���Ϣά��</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/jslib.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>

</head>
<body onkeydown="autoExeFunction('do_search()')">
<%=WebConstant.WAIT_TIP_MSG%>
<%
	EtsObjectDTO etsObject = (EtsObjectDTO)request.getAttribute(WebAttrConstant.ETS_OBJECT_DTO);
%>
<form method="post" name="mainFrm"  action="/servlet/com.sino.ams.system.switches.servlet.EtsObjectServlet">
<input type="hidden" name="objectCategory" value="<%=etsObject.getObjectCategory()%>">
<input type="hidden" name="objectCategory" value="<%=etsObject.getObjectCategory()%>">
<input type="hidden" name="categoryName"  id="categoryName" value="<%=etsObject.getCategoryName()%>">
<script type="text/javascript">
    printTitleBar("<%=etsObject.getCategoryName()%>ά��")
</script>
<table width="100%" border="0" class="queryHeadBg">
        <tr>
            <td width="10%" align="right">�ص��ţ�</td>
            <td width="20%"><input style="width:100%" type="text" name="workorderObjectCode" value="<%=etsObject.getWorkorderObjectCode()%>"></td>
            <td width="10%" align="right">�ص��ƣ�</td>
            <td width="20%"><input name="workorderObjectName" style="width:100%" value="<%=etsObject.getWorkorderObjectName()%>"></td>
            <td width="10%" align="right">�������أ�</td>
            <td width="30%"><select style="width:80%" type="text" name="countyCode"><%=request.getAttribute(WebAttrConstant.COUNTY_OPTION)%></select></td>
        </tr>
</table>
<fieldset style="margin-left:0;height:auto/*460px*/">
<legend align="left">
            <img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ">
            <img src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">
            <img src="/images/eam_images/new_add.jpg" alt="����" onClick="do_add();">
            <img src="/images/button/batchenable.gif" style="cursor:'hand'" onclick="do_efficient();" alt="������Ч">
            <img src="/images/button/batchdisable.gif" style="cursor:'hand'" onclick="do_disabled();" alt="����ʧЧ">
</legend>
<%--<div style="position:absolute;top:73px;left:0px;width:833px">--%>
    <div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
    <table width="100%" class="headerTable" border="1">
        <tr height="20">
            <td width="3%" align="center" style="padding:0"><input type="checkbox" name="titleCheck"
                                                                   class="headCheckbox"
                                                                   id="controlBox"
                                                                   onclick="checkAll('titleCheck','workorderObjectNos')"></td>
            <td width="12%" align="center">�ص���</td>
            <td width="23%" align="center">�ص���</td>
            <td width="30%" align="center">���ڵص�</td>
            <td width="10%" align="center">��������</td>
            <td width="12%" align="center">Ѳ��ģʽ</td>
            <td width="10%" align="center">ʧЧ����</td>
        </tr>
    </table>
</div>
<input type="hidden" name="act">

<div style="overflow-y:scroll;left:0px;width:100%;height:370px">
    <table width="100%" border="1" bordercolor="#666666">
<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && rows.getSize() > 0) {
	    Row row = null;
        for (int i = 0; i < rows.getSize(); i++) {
            row = rows.getRow(i);
%>
        <tr height="22" style="cursor:'hand'" onMouseMove="style.backgroundColor='#EFEFEF'" onMouseOut="style.backgroundColor='#ffffff'">
            <td width="3%" align="center"><input type="checkbox" name="workorderObjectNos" value="<%=row.getValue("WORKORDER_OBJECT_NO")%>"></td>
            <td width="12%" align="center" onclick="show_detail('<%=row.getValue("WORKORDER_OBJECT_NO")%>')"><%=row.getValue("WORKORDER_OBJECT_CODE")%></td>
            <td width="23%" onclick="show_detail('<%=row.getValue("WORKORDER_OBJECT_NO")%>')"><%=row.getValue("WORKORDER_OBJECT_NAME")%></td>
            <td width="30%" align="left" onclick="show_detail('<%=row.getValue("WORKORDER_OBJECT_NO")%>')"><%=row.getValue("WORKORDER_OBJECT_LOCATION")%></td>
            <td width="10%" align="center" onclick="show_detail('<%=row.getValue("WORKORDER_OBJECT_NO")%>')"><%=row.getValue("COUNTY_NAME")%></td>
            <td width="12%" onclick="show_detail('<%=row.getValue("WORKORDER_OBJECT_NO")%>')"><%=row.getValue("ISALL")%></td>
            <td width="10%" align="center" onclick="show_detail('<%=row.getValue("WORKORDER_OBJECT_NO")%>')"><%=row.getValue("DISABLE_DATE")%></td>
        </tr>
<%
	    } }
%>
    </table>
</div>
     </fieldset>
</form>

<div><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>

<jsp:include page="/message/MessageProcess"/>
</body>
</html>
<script type="text/javascript">
function do_search() {
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.switches.servlet.EtsObjectServlet";
    mainFrm.submit();
}

function show_detail(workorderObjectNo) {
    mainFrm.act.value = "<%=WebActionConstant.DETAIL_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.switches.servlet.EtsObjectServlet?&workorderObjectNo="+workorderObjectNo +"&categoryName="+mainFrm.categoryName.value;
    mainFrm.submit();
}

function do_add() {
    mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.switches.servlet.EtsObjectServlet";
    mainFrm.submit();
}

function do_disabled(){             //����ʧЧ
    var checkedCount = getCheckedBoxCount("workorderObjectNos");
    if (checkedCount < 1) {
         alert("������ѡ��һ�����ݣ�");
         return;
     } else {
        do_verifyObjectNos();

      }  
}

function do_efficient(){             //������Ч
    var checkedCount = getCheckedBoxCount("workorderObjectNos");
    if (checkedCount < 1) {
         alert("������ѡ��һ�����ݣ�");
         return;
        } else {
        if(confirm(ENABLE_MSG))  {
            mainFrm.act.value = "<%=AMSActionConstant.EFFICIENT_ACTION%>";
            mainFrm.action = "/servlet/com.sino.ams.system.switches.servlet.EtsObjectServlet";
            mainFrm.submit();
            }
       }
}

function do_Export(){                  //����execl
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.switches.servlet.EtsObjectServlet";
    mainFrm.submit();
}


var xmlHttp;
function do_verifyObjectNos() {
    var url = "";
    var   a   =   document.getElementsByName("workorderObjectNos");
    var   str="";
    var workorderObjectNos=new Array();
//    alert(workorderObjectNos);
    for(var i=0;i<a.length;i++) {
        if(a[i].checked){
//            alert(a[i].value)
            workorderObjectNos[workorderObjectNos.length]=a[i].value

//            workorderObjectNos[i]= a[i].value;
//            alert(workorderObjectNos[i]);
//            str+=","+ a[i].value;
        }
    }
//    alert(workorderObjectNos);
    createXMLHttpRequest();
        url = "/servlet/com.sino.ams.system.switches.servlet.EtsObjectServlet?act=verifyObjectNos&workorderObjectNos="+workorderObjectNos;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
}

function createXMLHttpRequest() {     //����XMLHttpRequest����
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
}

function handleReadyStateChange1() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            if (xmlHttp.responseText == 'Y') {
                alert("��ѡ�ص��д����豸��������ʧЧ��");
            } else {
//                document.mainFrm.isExist.value = 'N';
                if (confirm(DISABLE_MSG)) {
//                    alert("sucess");
            mainFrm.act.value = "<%=AMSActionConstant.DISABLED_ACTION%>";
            mainFrm.action = "/servlet/com.sino.ams.system.switches.servlet.EtsObjectServlet";
            mainFrm.submit();
         }
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}
</script>