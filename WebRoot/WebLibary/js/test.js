function do_Complete_Validate(){
	return (  validateData() && validateDate() && checkUpFeeItem() && doVerifyVendorItem() );
}

function do_Save_Validate(){
	return (  validateData() && checkUpFeeItem() );
}

function do_Save_flow() {
    autoValue(AV_SAVE_MASK);
    if(!do_Save_Validate())
        return;
    SFQuerySave();
    if(!Launch_Continue) {
        if(Error_Msg != "")
            alert(Error_Msg);
        return;
    }
    clearDivRight();
    saveInfo();
    isSave = true;
    do_Save_app();
}

/**
 * �����ύ
**/
function do_Complete_flow() {

        SFQueryComplete();
        if(!Launch_Continue) {
	        if(Error_Msg != "")
	            alert(Error_Msg);
	        return;
	    }
        var str = getNextTask();
        if(str != "") {
	        autoValue(AV_COMPLETE_MASK);
	        if(!validation())
	            return;
	        SFQuerySave();
            if(!Launch_Continue) {
	            if(Error_Msg != "")
	                alert(Error_Msg);
	            return;
	        }

            //var flowDesc = getJsonData( str , "flowHint:");

	        //if( getAttribute3() == "PLAN_APPROVE" || ( getAttribute3() == "NETWORK_APPROVE" && Launch_Code == "C1"  )  ){
	        //	try{
	        //		var tempStr = inputOpinion2( flowDesc );
	        //		document.getElementById("sf_opinion").value = tempStr ;
	        //	}catch(ex){
	        //		alert( ex.message );
	        //	}
	        //}else{
	        //	var tempStr = inputOpinion( flowDesc );
	        //	document.getElementById("sf_opinion").value = tempStr ;
	        //}

	        var flowDesc = getJsonData( str , "flowHint:");
	        var tempStr = inputOpinion( flowDesc );
            document.getElementById("sf_opinion").value = tempStr ;
	        if(document.getElementById("sf_opinion").value == "undefined" || document.getElementById("sf_opinion").value == "" ) {
	            document.getElementById("sf_opinion").value = "";
	            return;
	        }

	        document.getElementById("sf_nextTaskData").value = str;
	        if(finishMessage() == "") {
	            restoreNextTaskData();
	            return;
	        }

            setNextFlowDesc();
	        SFPostSave();
	        if(!Launch_Continue) {
	            if(Error_Msg != "")
	                alert(Error_Msg);
	            return;
	        }

            clearDivRight();
	        saveInfo();
	        isSave = true;
            do_Complete_app_yy();
	    }
}

function do_NewApproveOrder() {
    if(!isLoaded) {
        alert("ҳ������û����ȫ����, ��ʱ�������");
        return;
    }
    var copyUsers = "";
    if(document.getElementById("sf_caseID").value.indexOf(":") > 0) {
        isSave = true;
        document.forms[0].action = "/servlet/com.sino.sinoflow.servlet.CaseComplete?send='0'";
        document.forms[0].submit();
        return;
    }

    if(isAbandon()) {
        return;
    }

    //�˴�������������ɺ�ͬ��ĿΪ"�������������ϡ��ƽ�������"������ר����ӡ�
    //Ŀ������ʹ��ҳ����֤������ѡ��֮ǰ���У���ע��:ԭ����ѡ��֮�����֤�����Ѿ�ע����
    //�����ط��ɸ�����Ҫ��������,���߸�������
    //����AppStandard.js��147��
    var isAppValidatePass=false;		//ͨ��OR NO
    var sfProcedureValue=document.getElementById("sf_procedure").value;
    if(sfProcedureValue.indexOf("���") >= 0){
    	isAppValidatePass=do_App_Validate();
    	if(!isAppValidatePass){return;}			//��֤��ͨ����ֹͣ
    }else if (sfProcedureValue.indexOf("���") >= 0){
    	isAppValidatePass=do_App_Validate();
    	if(!isAppValidatePass){return;}			//��֤��ͨ����ֹͣ
    }else if (sfProcedureValue.indexOf("����") >= 0){
    	isAppValidatePass=do_App_Validate();
    	if(!isAppValidatePass){return;}			//��֤��ͨ����ֹͣ
    }else if (sfProcedureValue.indexOf("�ƽ�") >= 0){
    	isAppValidatePass=do_App_Validate();
    	if(!isAppValidatePass){return;}			//��֤��ͨ����ֹͣ
    }else if (sfProcedureValue.indexOf("����") >= 0){
    	isAppValidatePass=do_App_Validate();
    	if(!isAppValidatePass){return;}			//��֤��ͨ����ֹͣ
    }else if (sfProcedureValue.indexOf("���ж���") >= 0){
    	isAppValidatePass=do_App_Validate();
    	if(!isAppValidatePass){return;}			//��֤��ͨ����ֹͣ
    }

    SFQueryComplete();
    if(!Launch_Continue) {
        if(Error_Msg != "")
            alert(Error_Msg);
        return;
    }
    getNextTask2();
//        do_Complete_app()
//        if( do_InsertMark() ){
//        	do_Complete_app();
//        }
    
}

function do_Complete_app() {
	if(true){
        try{
//			disabledBtn();
            var actObj = document.getElementById("act");
			actObj.value = "APPROVE_ACTION";
            //setFrmEnable("mainFrm");
//			document.forms[0].action="/servlet/com.sino.ies.inv.ywypmgr.servlet.CtmsForwardServlet";
			document.forms[0].submit();
		}catch(ex){
			alert( ex.message );
		}finally{
			enableBtn();
		}
	}
}
//�̻���������
 function do_phComplete_flow() {

        SFQueryComplete();
	    if(!Launch_Continue) {
	        if(Error_Msg != "")
	            alert(Error_Msg);
	        return;
	    }
        var str = getNextTask();
        if(str != "") {
	        autoValue(AV_COMPLETE_MASK);
	        if(!validation())
	            return;
	        SFQuerySave();
            if(!Launch_Continue) {
	            if(Error_Msg != "")
	                alert(Error_Msg);
	            return;
	        }

            //var flowDesc = getJsonData( str , "flowHint:");

	        //if( getAttribute3() == "PLAN_APPROVE" || ( getAttribute3() == "NETWORK_APPROVE" && Launch_Code == "C1"  )  ){
	        //	try{
	        //		var tempStr = inputOpinion2( flowDesc );
	        //		document.getElementById("sf_opinion").value = tempStr ;
	        //	}catch(ex){
	        //		alert( ex.message );
	        //	}
	        //}else{
	        //	var tempStr = inputOpinion( flowDesc );
	        //	document.getElementById("sf_opinion").value = tempStr ;
	        //}

	        var flowDesc = getJsonData( str , "flowHint:");
	        var tempStr = inputOpinion( flowDesc );
            document.getElementById("sf_opinion").value = tempStr ;
	        if(document.getElementById("sf_opinion").value == "undefined" || document.getElementById("sf_opinion").value == "" ) {
	            document.getElementById("sf_opinion").value = "";
	            return;
	        }

	        document.getElementById("sf_nextTaskData").value = str;
	        if(finishMessage() == "") {
	            restoreNextTaskData();
	            return;
	        }

            setNextFlowDesc();
	        SFPostSave();
	        if(!Launch_Continue) {
	            if(Error_Msg != "")
	                alert(Error_Msg);
	            return;
	        }

            clearDivRight();
	        saveInfo();
	        isSave = true;
            do_phComplete_app_yy();
	    }
}
function do_phComplete_app_yy() {
	if(true){
        try{
//			disabledBtn();
            var actObj = document.getElementById("act");
			actObj.value = "SAVE_PHONE";
            itemListFrm.mainFrm.sf_appFieldValue.value=  document.getElementById("sf_appFieldValue").value;
//            alert(sf_flow)
             itemListFrm.mainFrm.action = "/servlet/com.sino.ies.po.orderform.distribution.CtmsPhForwardServlet?act="+actObj.value;
            itemListFrm.mainFrm.target = "_self";
            itemListFrm.mainFrm.submit();
            //setFrmEnable("mainFrm");
//			document.forms[0].action="/servlet/com.sino.ies.inv.ywypmgr.servlet.CtmsForwardServlet";
//			document.forms[0].submit();
		}catch(ex){
			alert( ex.message );
		}finally{
			enableBtn();
		}
	}
}
function do_Complete_app_validate(){
	return true;
}
function do_Save_app_validate(){
	return true;
}

function do_Complete_app_yy() {
	if(true){
        try{
//			disabledBtn();
            var actObj = document.getElementById("act");
			actObj.value = "SUBMIT_ACTION";
            //setFrmEnable("mainFrm");
//			document.forms[0].action="/servlet/com.sino.ies.inv.ywypmgr.servlet.CtmsForwardServlet";
			document.forms[0].submit();
		}catch(ex){
			alert( ex.message );
		}finally{
			enableBtn();
		}
	}
}
function do_app_flow() {

        SFQueryComplete();
	    if(!Launch_Continue) {
	        if(Error_Msg != "")
	            alert(Error_Msg);
	        return;
	    }
        var str = getNextTask();
        if(str != "") {
	        autoValue(AV_COMPLETE_MASK);
	        if(!validation())
	            return;
	        SFQuerySave();
            if(!Launch_Continue) {
	            if(Error_Msg != "")
	                alert(Error_Msg);
	            return;
	        }
            //var flowDesc = getJsonData( str , "flowHint:");

	        //if( getAttribute3() == "PLAN_APPROVE" || ( getAttribute3() == "NETWORK_APPROVE" && Launch_Code == "C1"  )  ){
	        //	try{
	        //		var tempStr = inputOpinion2( flowDesc );
	        //		document.getElementById("sf_opinion").value = tempStr ;
	        //	}catch(ex){
	        //		alert( ex.message );
	        //	}
	        //}else{
	        //	var tempStr = inputOpinion( flowDesc );
	        //	document.getElementById("sf_opinion").value = tempStr ;
	        //}

	        var flowDesc = getJsonData( str , "flowHint:");
	        var tempStr = inputOpinion( flowDesc );
            document.getElementById("sf_opinion").value = tempStr ;
	        if(document.getElementById("sf_opinion").value == "undefined" || document.getElementById("sf_opinion").value == "" ) {
	            document.getElementById("sf_opinion").value = "";
	            return;
	        }

	        document.getElementById("sf_nextTaskData").value = str;
	        if(finishMessage() == "") {
	            restoreNextTaskData();
	            return;
	        }

            setNextFlowDesc();
	        SFPostSave();
	        if(!Launch_Continue) {
	            if(Error_Msg != "")
	                alert(Error_Msg);
	            return;
	        }

            clearDivRight();
	        saveInfo();
	        isSave = true;
            do_app_yy();
	    }
}
  function do_app_yy() {
	if(true){
        try{
//			disabledBtn();
            var actObj = document.getElementById("act");
			actObj.value = "approve";
            //setFrmEnable("mainFrm");
//			document.forms[0].action="/servlet/com.sino.ies.inv.ywypmgr.servlet.CtmsForwardServlet";
			document.forms[0].submit();
		}catch(ex){
			alert( ex.message );
		}finally{
			enableBtn();
		}
	}
}
/** ģ��ͨ�ã����봴�����̲����������  **/
function do_Save_app() {
	//alert( "save app" );
	if( true ){
//		var saveBtnObj = document.getElementById("saveBtn");
		try{
//			disabledBtn();
//			saveBtnObj.disabled = true;
			var actObj = document.getElementById("act");
			actObj.value = "SAVE_ACTION";
			//setFrmEnable("mainFrm");
			document.forms[0].submit();
		}catch(ex){
			//alert( ex.message );
		}finally{
//			saveBtnObj.disabled = false;
//			enableBtn();
		}
	}
}

function disabledBtn(){
	var nextBtnObj = document.getElementById("nextBtn");
	nextBtnObj.disabled = true;
}

function enableBtn(){
	var nextBtnObj = document.getElementById("nextBtn");
    if(nextBtnObj){
        nextBtnObj.disabled = false;
    }
}

/**
function doInit(){
 	calculatePrice();
 	autoSelected();
 	autoFromToSpanDis('mtlTable', 9, 10, 21);

 	document.getElementById("procId").value = document.getElementById("sf_procedureid").value ;
 	document.getElementById("progressbar").style.display="none";
}
**/
function doInit(){
	window.focus();
	calculatePrice();
	autoSelected();
	autoFromToSpanDis('mtlTable', 10, 11, 21);
	document.getElementById("progressbar").style.display="none";
}

function showOpinionDlg() {
    var opinionURL = "/servlet/com.sino.sinoflow.servlet.GetSaveStatus?sf_caseID='"
        document.getElementById("sf_caseID").value;

    h = window.screen.height;
    w = window.screen.width;
    alert(h)
    alert(w)
    var f1 = "dialogWidth:" + w
            + ";dialogHeight:" + h
            + ";center:yes;status:no;scrollbars:no;help:no";
    return window.showModalDialog(opinionURL, null, f1);
}
