<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<div id="buttonSet" >
	<script type="text/javascript">
	var ArrAction0 = new Array(true, "�ر�", "action_cancel.gif", "�ر�", "do_Close");
    var ArrAction1 = new Array(true, "��ӡ����", "download.gif", "��ӡ����", "do_SetupPrint(); return false;");
    var ArrAction2 = new Array(true, "��ӡԤ��", "action_viewstatus.gif", "��ӡԤ��", "do_PrevPrint(); return false;");
    var ArrAction3 = new Array(true, "��ӡȫ��", "print.gif", "��ӡȫ��", "do_PrintOrder_all(); return false;"); 
    var ArrAction4 = new Array(true, "��ӡ���뷽��ǩ", "print.gif", "��ӡ���뷽��ǩ", "do_PrintOrder_2(); return false;"); 
    var ArrAction5 = new Array(true, "�������뷽��ǩ", "export.gif", "�������뷽��ǩ", "do_Export(); return false;"); 
    var ArrActions = new Array(); 
   	ArrActions = new Array(ArrAction0, ArrAction1, ArrAction2 , ArrAction3 , ArrAction4 , ArrAction5);  
     	
   	var ArrSinoViews = new Array();
    printToolBar();
       
    var hasExport = false;
    function do_SetupPrint(){
    	if( checkHasExport() ){
    		return false;
    	}
		webbrowser.execwb(8,0);
	}
	
	function do_PrevPrint(){
		if( checkHasExport() ){
    		return false;
    	}
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
		return false;
	} 
	
	function checkHasExport(){
		if( hasExport ){
			alert("�������ӡ���뷽��ǩ����Ҫˢ��ҳ�棬����ؼ��޷�����ʹ��");
			location.reload();
		} 
		return hasExport;
	}
	
	function do_PrintOrder_all(){
		if( checkHasExport() ){
    		return false;
    	}
		var labelTable = document.getElementById("labelTable");
		var tHead = labelTable.tHead;
		var tbody = labelTable.tBodies[0];
		tHead.rows[0].cells[0].style.display = "block";
		
		var dataRows = tbody.rows;
		var len = dataRows.length;
		for( var i=0 ; i< len; i ++ ){
			dataRows[i].cells[0].style.display = "block";
		}
		
		do_PrintOrder();
	}
	function do_PrintOrder_2(){ 
		if( checkHasExport() ){
    		return false;
    	}
    	document.submitForm.target = "_Blank";
    	document.submitForm.act.value = "PRINT_BARCODE_ACTION";
        document.submitForm.submit();
        hasExport = true ;
        //location.reload();
        return false;
	}
	
	function do_Export(){
		document.submitForm.target = "submitFrm";
		document.submitForm.act.value = "EXPORT_ACTION";
        document.submitForm.submit();
        submitForm.reset();
        hasExport = true ;
        return false;
	}
   </script>
</div>
<object id="webbrowser" width="0" height="0"
	classid="clsid:8856f961-340a-11d0-a96b-00c04fd705a2"></object>