<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>
<link href="/WebLibary/css/main.css" rel="stylesheet" type="text/css"/>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
String sfpUrl = request.getParameter("sfpUrl");
if(sfpUrl.startsWith("\"") || sfpUrl.startsWith("'")) {
    sfpUrl = sfpUrl.substring(1,sfpUrl.length()-1);
}
sfpUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + sfpUrl;
String procName = request.getParameter("procName");
if(procName.startsWith("\"") || procName.startsWith("'")) {
    procName = procName.substring(1,procName.length()-1);
}
String fromTask = request.getParameter("fromTask");
if(fromTask.startsWith("\"") || fromTask.startsWith("'")) {
    fromTask = fromTask.substring(1,fromTask.length()-1);
}
String nowTask = request.getParameter("nowTask");
if(nowTask.startsWith("\"") || nowTask.startsWith("'")) {
    nowTask = nowTask.substring(1,nowTask.length()-1);
}
%>
<HTML> 
<HEAD> 
<TITLE>��������ѡ��</TITLE>
</HEAD>  

<SCRIPT LANGUAGE=javascript >                                                                
<!--
 var isSave = false;
 var taskName = "";
 function LoadFile(){ 
//       alert("server = " + "e:/flowEngine/out/exploded/flowEngineWeb/flow/sinoflo.sfp");
//       SinoFlowViewer.ShowFlowEx("e:/flowEngine/out/exploded/flowEngineWeb/flow/sinoflo.sfp","���ŷð�����","��¼����","У�Դ�ӡ");
//     alert("FromTask = " + "<%=fromTask%>" + ", nowTask = " + "<%=nowTask%>");
        SinoFlowViewer.ShowFlowEx("<%=sfpUrl%>","<%=procName%>","<%=fromTask%>","<%=nowTask%>");
     // window.resizeTo(1024,768);
 }

function do_close() {
    taskName = SinoFlowViewer.GetSelectedTaskName();
    if(taskName == "START") {
        alert("�������͵���ʼ!")
    } else if(taskName == "SPLIT") {
        alert("�������͵�����");
    } else if(taskName == "JOIN") {
        alert("�������͵����");
    } else if(taskName == "" || taskName == "<%=nowTask%>") {
        alert("��ѡ����������!");
    } else {
        var j = SinoFlowViewer.IsSameLevel("<%=nowTask%>", taskName);
        if(j == 0) {
            alert("ֻ�����͵�ͬһ���֮����, �����ͽ����ͳ�����!");
            return;
        }
        isSave = true;
        window.returnValue = taskName;
        window.close();
    }
}

function doUnload() {
    if(isSave)
        window.returnValue = taskName;
}

function doBeforeUnload() {
    if(!isSave) {
//        window.event.returnValue = "��ȷ����ȡ������";
        window.returnValue = "";
    }
}
//-->  
</SCRIPT>
<BODY  bgcolor="#FAFAFA" onLoad="LoadFile()" onbeforeunload="doBeforeUnload()" onunload="doUnload()" >
<div align="center" style="CURSOR: pointer">
<font color="#006699" size="2">
<!--<a href="javascript:window.close();">[�رմ���]</A>-->
<input type="button" name="Submit" value="��������ѡ�����" onClick="do_close()">
</font></div>     
<OBJECT id=SinoFlowViewer classid="clsid:730F3D4C-DB59-45C1-AE38-BF0AFA5C8BDC"  
  codeBase="/flow/SinoView.CAB" height=97%
  style="HEIGHT:97%; LEFT: 0px; TOP: 0px; WIDTH: 100%" width=100% VIEWASTEXT>
  <PARAM NAME="FileName" VALUE="">
  <embed height="97%" width="100%" _version="65536" _extentx="21167" _extenty="15875" _stockprops="0" scrollbars="0" curtask="" src="65536">
  </embed>
</OBJECT>
</body>
</HTML> 