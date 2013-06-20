//var submitMethod;
//��������ҳ����õ�������
var mouse_x;
var mouse_y;
var submitHander;
var nextHiddenRight;
var flow_appId;
var flow_extendType;
var flow_attr1;
var flow_attr2;
var flow_attr3;
var flow_ou;
/**
 * paramObj ������˵����
 * orgId: ou��ʱδ����
 * userId:������ID����ʱδ����
 * groupId:���������̶������Ѿ�ָ����𣬿��Բ��ṩ��������Ҫ�ṩ
 * procdureName���������ƣ���һ���ύ����������������������ID
 * appId��Ӧ��ID ��չ�ã��Ǳ���
 * extendType����չ���ͣ���չ�ã��Ǳ���
 * attr1:��չ�ֶ�1 �Ǳ���
 * attr2:��չ�ֶ�2 �Ǳ���
 * attr3:��չ�ֶ�3 �Ǳ���\
 * flowCode:�������������
 * submitH:�ύ����
 */
function assign(paramObj) {
    //orgId, userId, deptId, procdureName, flowCode, submitH
    var orgId = paramObj.orgId?paramObj.orgId:'';
    var userId = paramObj.userId?paramObj.userId:'';
    var deptId = paramObj.groupId?paramObj.groupId:'';
    var procdureName = paramObj.procdureName?paramObj.procdureName:'';
    var flowCode = paramObj.flowCode?paramObj.flowCode:'';
    var submitH = paramObj.submitH?paramObj.submitH:'';
    flow_appId = paramObj.appId?paramObj.appId:'';
    flow_extendType = paramObj.extendType?paramObj.extendType:'';
    flow_attr1 = paramObj.attr1?paramObj.attr1:'';
    flow_attr2 = paramObj.attr2?paramObj.attr2:'';
    flow_attr3 = paramObj.attr3?paramObj.attr3:'';
    flow_ou = orgId;
    disableDocument();
    showWaitDiv();
    if (event) {//�п�����û�����ֵ
        mouse_x = document.body.scrollLeft + event.clientX;
        mouse_y = document.body.scrollTop + event.clientY;
    } else {
        mouse_x = document.body.scrollLeft + 50;
        mouse_y = document.body.scrollTop + 50;
    }
    submitHander = submitH;
    document.getElementById('flowcurrDeptId').value = deptId;
    document.getElementById("flowProcName").value=procdureName;
    try {
        var currTaskId = document.getElementById("currTaskId").value;
        var procId = document.getElementById("flowprocId").value;
        var url = "/servlet/com.sino.flow.servlet.TaskFindServlet?currTaskId=" + currTaskId + "&procId=" + procId;
        var actId = document.getElementById("actId").value;
        var param = new ParamObject(orgId, deptId, procdureName, actId, flowCode);
        xmlHttp = getXmlHttpFlow(getNextTaskInfo);
        xmlHttp.open('POST', url, true);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlHttp.send(escape(param.toJSONString()));
    } catch(e) {
        alert("�ύ���ݳ�����֪ͨϵͳ����Ա��" + e.message);
        hiddenWaitDiv();
        cancelDisable();
    }
}
/**
 * orgId OU
 * procdureName ,������
 */
function ParamObject(orgId, deptId, procdureName, actId, flowCode) {
    this.orgId = orgId;
    this.procdureName = procdureName;
    this.actId = actId;
    this.flowCode = flowCode;
    this.deptId = deptId;
}
//�ӷ�����ȡ��һ�ڵ���Ϣ���������һ���ڵ㣬����
function getNextTaskInfo() {
    try {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
            var resText = xmlHttp.responseText;
            if (resText == 'ERROR') {
                alert("�ύ����ʧ�ܣ������³����ύ��֪ͨϵͳ����Ա!");
                hiddenWaitDiv();
                cancelDisable();
                return;
            }
            var resArray = resText.parseJSON();
            if (resArray.length < 1) { //���û���ҵ���һ���ڵ�
                alert("û���ҵ���һ����������֪ͨϵͳ����Ա��");
                hiddenWaitDiv();
                cancelDisable();
                return;
            }
            if (resArray.length == 1) { //�����һ������ֻ��һ���ڵ� ,���������ڣ�����ȡ��һ�û�
                var taskObj = resArray[0];
                document.getElementById("flownextTaskId").value = taskObj.nextTaskId;
                document.getElementById("flownextDeptId").value = taskObj.nextDeptId;
                document.getElementById("flowprocId").value = taskObj.procId;
                nextHiddenRight = taskObj.nextHiddenRight;
                var nextTaskName = taskObj.nextTaskName;
                if (nextTaskName == '����') {
                    eval(submitHander);
                } else {
                    fetchNextUsers(taskObj.nextTaskId, taskObj.nextDeptId, taskObj.procId);
                }
            } else {
                //step2:�����һ���ڵ������������ʾһ������������ѡ��
                //���صȴ�ҳ�棬��ʾ����һ�ڵ�ҳ�� ,���ֻ��һ���Ͳ�����
                hiddenWaitDiv();
                showNextTasksDiv();
                var taskSelect = document.getElementById("flowNextTasksSelect");
                removeOption(taskSelect);
                if (resArray.length >= 1) {
                    for (var i = 0; i < resArray.length; i++) {
                        var taskObj = resArray[i];
                        var vValue = taskObj.nextTaskId + "&&" + taskObj.nextDeptId + "&&" + taskObj.procId + "&&" + taskObj.nextTaskName + "&&" + taskObj.nextHiddenRight;
                        var vText = taskObj.flowDesc;
                        var vOption = new Option(vText, vValue);
                        taskSelect.add(vOption);
                    }
                }
            }
        }
    } catch(e) {
        alert("�ύ���ݳ�����֪ͨϵͳ����Ա��" + e.message);
        hiddenWaitDiv();
        cancelDisable();
    }
}
/**
 ˢ���ռ���
 */
function refreshInbox(userId) {
    if (document.getElementById('actId').value != '') {
        window.opener.document.forms[0].userId.value = userId;
        window.opener.document.forms[0].submit();
    }
}
/**
 �鿴�������
 appId,Ӧ��ID
 appTableName,Ӧ�ñ���
 ����������ȷ��Ψһ�ԣ�����������̵���ת�У����ܱ���ҳ����actId,���Բ�������������
 */

function viewOpinion(appId, appTableName) {
    var url = "/servlet/com.sino.flow.servlet.ApproveContentServlet";
    if (appId) {
        url += "?appId=" + appId + "&appTableName=" + appTableName;
    } else {
        if (document.getElementById('actId')) {
            var actId = document.getElementById('actId').value;
            url += "?actId=" + actId;
        } else {
            alert("��ҳ�����Ҳ���actId�������봫�����������������");
            return;
        }
    }
    var factor = 0.8;
    var dialogWidth = window.screen.availWidth * factor;
    var dialogHeight = window.screen.availHeight * factor;
    var dialogLeft = window.screen.availWidth * (1 - factor) / 2;
    var dialogTop = window.screen.availHeight * (1 - factor) / 2;

    var style = "dialogLeft:"+dialogLeft+"px;" +
            "dialogTop:"+dialogTop+"px;" +
            "dialogWidth:"+dialogWidth+"px;" +
            "dialogHeight:"+dialogHeight+"px;" +
            "toolbar:no;" +
            "directories:no;" +
            "status:no;" +
            "menubar:no;" +
            "scrollbars:no;" +
            "revisable:no;" +
            "resizable:yes";
    window.showModalDialog(url, null, style);
}


//function viewOpinion(appId, appName) {
//    var url = "/servlet/com.sino.sinoflow.servlet.GetSaveStatus";
//    if(document.getElementById("sf_caseID")) {
//        var caseId = document.getElementById('sf_caseID').value;
//        url += "?sf_caseID=" + caseId;
//    } else if (appId) {
////        url += "?sf_appDataID=" + appId + "&sf_appName=" + appName;
//        url += "?sf_appDataID=" + appId + "&sf_appName=";
//    } else {
//        alert("��ҳ�����Ҳ���sf_caseID�������봫�����������������");
//    }
//    var factor = 0.8;
//    var dialogWidth = window.screen.availWidth * factor;
//    var dialogHeight = window.screen.availHeight * factor;
//    var dialogLeft = window.screen.availWidth * (1 - factor) / 2;
//    var dialogTop = window.screen.availHeight * (1 - factor) / 2;
//
//    var style = "dialogLeft:"+dialogLeft+"px;" +
//            "dialogTop:"+dialogTop+"px;" +
//            "dialogWidth:"+dialogWidth+"px;" +
//            "dialogHeight:"+dialogHeight+"px;" +
//            "toolbar:no;" +
//            "directories:no;" +
//            "status:no;" +
//            "menubar:no;" +
//            "scrollbars:no;" +
//            "revisable:no;" +
//            "resizable:yes";
//    window.showModalDialog(url, null, style);
//}

/**
 ��ͨ��ʱ������������
 */
function addApproveContent(flowCode) {
    var url = "/flow/addOpinion.jsp";
    var style = "dialogleft:200px;dialogtop:200px;dialogwidth:300px;dialogheight:180px;toolbar:no;directories:no;status:no;menubar:no;scrollbars:no;revisable:no";
	var alreadyContent = document.getElementById('approveContent').value;
	if(flowCode){
		var lastFlowCode = document.getElementById('lastFlowCode').value;
		if(flowCode == "9"){//ͨ��
			if(lastFlowCode != "9" || alreadyContent == ""){
				alreadyContent = "ͬ��";
			}
		} else {
			if(lastFlowCode == "9" || alreadyContent == ""){
				alreadyContent = "��ͬ��";
			}
		}
		document.getElementById('lastFlowCode').value = flowCode;
	}
    var approveContent = window.showModalDialog(url, alreadyContent, style);
    if (approveContent) {
        document.getElementById('approveContent').value = approveContent;
    }
}
//������ҳ��Disabled
//�������ԭʼ����
function disableDocument() {
    //disable����ҳ��
    var hideDiv = document.getElementById("flowhideDiv");
    //hideDiv.style.filter = "progid:DXImageTransform.Microsoft.Alpha (opacity=10,finishOpacity=100,style=2)";
    hideDiv.style.visibility = 'visible';
}
//ȡ��Disable
function cancelDisable() {
    var hideDiv = document.getElementById("flowhideDiv");
    hideDiv.style.visibility = 'hidden';
}
function showWaitDiv() {
    //�����ȴ�DIV
    var winHeight = document.body.clientHeight;
    // alert(winHeight);
    var waitDiv = document.getElementById("flowWaitDiv");
    waitDiv.style.top = winHeight / 2
    waitDiv.style.visibility = 'visible';
}
//���صȴ�ҳ��
function hiddenWaitDiv() {
    var waitDiv = document.getElementById("flowWaitDiv");
    waitDiv.style.visibility = 'hidden';
}
function getXmlHttpFlow(handler) {
    var objXmlHttp = null
    if (navigator.userAgent.indexOf("Opera") >= 0) {
        alert("�Բ�������������֧�ָò�����")
        return;
    }
    if (navigator.userAgent.indexOf("MSIE") >= 0) {
        var strName = "Msxml2.XMLHTTP"
        if (navigator.appVersion.indexOf("MSIE 5.5") >= 0) {
            strName = "Microsoft.XMLHTTP"
        }
        try {
            objXmlHttp = new ActiveXObject(strName)
            objXmlHttp.onreadystatechange = handler
            return objXmlHttp
        } catch(e) {
            alert("Error. Scripting for ActiveX might be disabled")
            return
        }
    }
    if (navigator.userAgent.indexOf("Mozilla") >= 0) {
        objXmlHttp = new XMLHttpRequest();
        objXmlHttp.onload = handler
        objXmlHttp.onerror = handler
        return objXmlHttp
    }
}
//��ʾ��һ�����DIV
function showNextTasksDiv() {
    var nextTaskDiv = document.getElementById("flowNextTaskDiv");
    nextTaskDiv.style.position = 'absolute';
    nextTaskDiv.style.left = mouse_x;
    nextTaskDiv.style.top = mouse_y;
    nextTaskDiv.style.visibility = 'visible';
}
function hideNextTasksDiv() {
    var nextTaskDiv = document.getElementById("flowNextTaskDiv");
    nextTaskDiv.style.visibility = 'hidden';
}
function removeOption(vselect) {
    if (vselect.length > 1) {
        //��Ϊremoveһ�����ˣ�length�ͻ���̣�����Ӧ�÷�����remove
        for (var i = vselect.length - 1; i > 0; i--) {
            vselect.remove(i);
        }
    }
}
//��nextTasks���ص�ҳ�档
function getFlowTasks() {
    //step1:����Ҫ����Ϣ����ҳ��ı���
    var nextTaskSelect = document.getElementById("flowNextTasksSelect");
    var nextTaskValue = nextTaskSelect.options[nextTaskSelect.selectedIndex].value;
    if (nextTaskValue == "") {
        alert("����ѡ����һ��������Ȼ����ȷ����");
        return;
    }
    var nextTaskArr = nextTaskValue.split("&&");
    var nextTaskId = nextTaskArr[0];
    var nextDeptId = nextTaskArr[1];
    var procId = nextTaskArr[2];
    var nextTaskName = nextTaskArr[3];
    nextHiddenRight = nextTaskArr[4];
    document.getElementById("flownextTaskId").value = nextTaskId;
    document.getElementById("flownextDeptId").value = nextDeptId;
    document.getElementById("flowprocId").value = procId;
    //��ʾ�ȴ�ҳ�棬
    hideNextTasksDiv();
    disableDocument();
    showWaitDiv();
    if (nextTaskName == '����') {
        eval(submitHander);
    } else {
        //step2:������һ�ڵ㣬ȡ������
        fetchNextUsers(nextTaskId, nextDeptId, procId);
    }
}
//ȥ������ȡ��һ������
function fetchNextUsers(nextTaskId, nextDeptId, procId) {
    try {
        var url = "/servlet/com.sino.flow.servlet.ApproveUserFindServlet" ;
        var nextTask = new Object();
        nextTask.nextTaskId = nextTaskId;
        nextTask.nextDeptId = nextDeptId;
        nextTask.procId = procId;
        nextTask.appId = flow_appId;
        nextTask.extendType = flow_extendType;
        nextTask.attr1 = flow_attr1;
        nextTask.attr2 = flow_attr2;
        nextTask.attr3 = flow_attr3;
        nextTask.ou = flow_ou;
        xmlHttp = getXmlHttpFlow(getNextUsers);
        xmlHttp.open('POST', url, true);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlHttp.send(nextTask.toJSONString());
    } catch(e) {
        alert("�ύ����ʧ�ܣ��볢�������ύ��֪ͨϵͳ����Ա��" + e.message);
        hiddenWaitDiv();
        cancelDisable();
    }
}
//������������һ������
function getNextUsers() {
    try {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
            var resText = xmlHttp.responseText;
            if (resText == 'ERROR') {
                alert("�ύ����ʧ�ܣ������³����ύ��֪ͨϵͳ����Ա!");
                hiddenWaitDiv();
                cancelDisable();
                return;
            }
            var resArray = resText.parseJSON();
            if (resArray.length < 1) {
                alert("û���ҵ���һ��������ˣ���֪ͨϵͳ����Ա��");
                hiddenWaitDiv();
                cancelDisable();
                return;
            }
            if (resArray.length == 1) {//�������Ϊ1��ֱ���ύ����
                var userObj = resArray[0];
                document.getElementById("flownextUserId").value = userObj.nextUserId;
                document.getElementById("flownextPositionId").value = userObj.nextPositionId;
                eval(submitHander);
            } else {
                //�鿴��һ�ڵ��Ƿ�Ϊ���û���������Ƕ��û�����Ҳ���õ���ҳ�档
                var hiddenArr = nextHiddenRight.split(";");
                //
                if (hiddenArr[0] == 'both') {//��ʱ
                    //��һЩ�������÷ֺŷָ���
                    var nextUserIds;
                    //��һЩ������ְλ�÷ֺŷָ�
                    var nextPositionIds;

                    for (var i = 0; i < resArray.length; i++) {
                        var userObj = resArray[i];
                        if (i == 0) {
                            nextUserIds = userObj.nextUserId;
                            nextPositionIds = userObj.nextPositionId;
                        } else {
                            nextUserIds = nextUserIds + ";" + userObj.nextUserId;
                            nextPositionIds = nextPositionIds + ";" + userObj.nextPositionId;
                        }
                    }
                    document.getElementById("flownextUserId").value = nextUserIds;
                    document.getElementById("flownextPositionId").value = nextPositionIds;
                    //�ύҳ�档
                    eval(submitHander);
                } else {
                    /*�¸��ڵ��ж���ˣ���Ӧ��Ҳ�п�����ָ��ĳ������һ��ָ�����ˡ����ָ�������˾Ͳ��õ�
                        ��������ֱ���ύ.
                        ����ָ����˭����Ӧ��ȥʵ������ӿ�appointPerson();
                        �÷�������ָ����userId
                        eg:
                        function appointPerson(){
                            return userId;
                        }
                    */
                    var isAppointPerson = false;
                    if (typeof(appointPerson) == "function") {
                        //ָ�����˵�userId;
                        var appointPersonUserId = appointPerson();
                        if (appointPersonUserId != "") {
                            for (var i = 0; i < resArray.length; i++) {
                                var userObj = resArray[i];
                                if (userObj.nextUserId == appointPersonUserId) {
                                    document.getElementById("flownextUserId").value = userObj.nextUserId;
                                    document.getElementById("flownextPositionId").value = userObj.nextPositionId;
                                    isAppointPerson = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (isAppointPerson) {//�����ָ�������ύҳ�档
                        eval(submitHander);
                    } else {
                        //step2:�����һ���ڵ������������ʾһ������������ѡ��
                        //���صȴ�ҳ�棬��ʾ����һ�ڵ�ҳ�� ,���ֻ��һ���Ͳ�����
                        hiddenWaitDiv();
                        showNextUserDiv();
                        var userSelect = document.getElementById("flowNextUsersSelect");
                        removeOption(userSelect);
                        if (resArray.length >= 1) {
                            for (var i = 0; i < resArray.length; i++) {
                                var userObj = resArray[i];
                                var vValue = userObj.nextUserId + "&&" + userObj.nextPositionId;
                                var vText = userObj.nextUserName;
                                var vOption = new Option(vText, vValue);
                                userSelect.add(vOption);
                            }
                        }
                    }
                }
            }
        }
    } catch(e) {
        alert("�ύ���ݳ�����֪ͨϵͳ����Ա��" + e.message);
        hiddenWaitDiv();
        cancelDisable();
    }
}
function showNextUserDiv() {
    var nextTaskDiv = document.getElementById("flowNextUserDiv");
    nextTaskDiv.style.position = 'absolute';
    nextTaskDiv.style.left = mouse_x;
    nextTaskDiv.style.top = mouse_y;
    nextTaskDiv.style.visibility = 'visible';
}
function hideNextUserDiv() {
    var nextTaskDiv = document.getElementById("flowNextUserDiv");
    nextTaskDiv.style.visibility = 'hidden';
}
function getFlowUsers() {
    //step1:����Ҫ����Ϣ����ҳ��ı���
    var nextUserSelect = document.getElementById("flowNextUsersSelect");
    var nextUserValue = nextUserSelect.options[nextUserSelect.selectedIndex].value;
    if (nextUserValue == "") {
        alert("����ѡ����һ�����ˣ�Ȼ����ȷ����");
        return;
    }
    var arr = nextUserValue.split("&&");
    document.getElementById("flownextUserId").value = arr[0];
    document.getElementById("flownextPositionId").value = arr[1];
    //��ʾ�ȴ�ҳ�棬
    hideNextUserDiv();
    disableDocument();
    showWaitDiv();
    //window.event = submitHander;
    eval(submitHander);
}
//ǩ�����룬����ǰ�û�����һ��ʱ��
var flowopenMethod = ""//ǩ�������ռ���ķ���
function signApply() {
    try {
        var actId = document.getElementById("actId").value;
        var signFlag = document.getElementById("flowSignFlag").value;
        if (signFlag == 'Y') {
            return;
            //����Ѿ�ǩ�գ��Ͳ���ǩ����
        }
        disableDocument();
        showWaitDiv();
        var url = '/servlet/com.sino.flow.servlet.SignApplyServlet?actId=' + actId ;
        xmlHttp = getXmlHttpFlow(receiveFromSignApply);
        xmlHttp.open('GET', url, true);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlHttp.send(null);
    } catch(e) {
        alert("ǩ��ʧ�ܣ�");
        hiddenWaitDiv();
        cancelDisable();
    }
}
//signApply���������������󣬸÷������շ������˷��ص���Ӧ
function receiveFromSignApply() {
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        var resText = xmlHttp.responseText;
        if (resText == '1') {//ǩ�ճɹ�
            document.getElementById("flowSignFlag").value = 'Y';
            alert("ǩ�ճɹ���");
        } else if (resText == '0') {//�����Ѿ�ǩ��
            alert("�Ѿ�����ǩ���˸����룡������ˢ���ռ��䣡");
        } else if (resText == 'ERROR') {//�������׳��쳣
            alert("ϵͳ������֪ͨϵͳ����Ա��");
        }
        //ˢ���ռ���
        if (window.opener && window.opener.document && window.opener.document.forms[0]) {
            window.opener.document.forms[0].submit();
        }
        hiddenWaitDiv();
        cancelDisable();
    }
}
//��������
function viewFlow() {
    var procName=document.getElementById("procdureName").value;
    var actId = document.getElementById("actId").value;
    if ((actId =="")&&(procName=="")) {
        alert("û���ҵ����̵���Ϣ");
        return;
    }
//    var url = "/servlet/ViewFlowServlet?actId=" + actId;
    var url = "/servlet/com.sino.flow.servlet.ViewFlow?actId=" + actId+"&procName="+procName;
    var style = "width=1024,height=768,top=0,left=0";
    var winName = "flowDetail";
    window.open(url, winName, style);
}
//���̳����������˻�ǰ���ã���ʱ������Ҫȥ��̨����һ�����ˣ�ֻ��Ҫ�ѵ�ǰ����ID��ֵ��Didsabled����ҳ�棬�����ȵ�ҳ��
function assignDeptId(deptId) {
    try {
        disableDocument();
        showWaitDiv();
        document.getElementById('flowcurrDeptId').value = deptId;
    } catch(e) {
        alert("�ύ����");
        hiddenWaitDiv();
        cancelDisable();
    }
}


