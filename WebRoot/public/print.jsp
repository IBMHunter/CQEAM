<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<div id="buttonSet">
<script type="text/javascript">
	var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "window.close(); return false;");
       var ArrAction1 = new Array(true, "��ӡ����", "download.gif", "��ӡ����", "do_SetupPrint(); return false;");
       var ArrAction2 = new Array(true, "��ӡԤ��", "action_viewstatus.gif", "��ӡԤ��", "do_PrevPrint(); return false;");
       var ArrAction3 = new Array(true, "��ӡ", "print.gif", "��ӡ", "do_PrintOrder(); return false;"); 
       var ArrActions = new Array(); 
     	ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2 , ArrAction3 );  
       var ArrSinoViews = new Array();
       printToolBar();
       
       
       function do_SetupPrint(){
		webbrowser.execwb(8,0);
	}
	
	function do_PrevPrint(){
		document.getElementById("buttonSet").style.display = "none";
		webbrowser.execwb(7,0);
		document.getElementById("buttonSet").style.display = "block";
	}
	
	function do_PrintOrder(){
		document.getElementById("buttonSet").style.display = "none";
		webbrowser.execwb(6,6);
		document.getElementById("buttonSet").style.display = "block";
	}
	
	function do_Close(){ 
		top.close();
	} 
   </script>
</div>
<object id="webbrowser" width="0" height="0" classid="clsid:8856f961-340a-11d0-a96b-00c04fd705a2"></object>