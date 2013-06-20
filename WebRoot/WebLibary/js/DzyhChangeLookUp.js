var factor = 0.8;
var g_dialogWidth = window.screen.availWidth * factor;
var g_dialogHeight = window.screen.availHeight * factor;

function lookUpChangeValues(lookUpName, dialogWidth, dialogHeight, userPara) {
    var url = "/servlet/com.sino.ams.dzyh.servlet.DzyhChangeLookUpServlet?lookUpName=" + lookUpName;
    if(userPara != "undefined" && userPara != null){
        url += "&" + userPara;
    }
    dialogWidth = g_dialogWidth;
    dialogHeight = g_dialogHeight;
    var popscript = "dialogWidth:"
            + dialogWidth
            + ";dialogHeight:"
            + dialogHeight
            + ";center:yes;status:no;scrollbars:no;help:no;resizable:yes";
//			window.open(url);
    return window.showModalDialog(url, null, popscript);
}


////���ܣ����ô��ڱ�������ݵ���Ҫ�����Ĳ�Ŀ��
//function do_SetPageWidth(){
//	var headerWidth = document.body.clientWidth - 21;
//	var dataWidth = document.body.clientWidth - 4;
//	if(document.getElementById("headDiv")){
//		document.getElementById("headDiv").style.width = headerWidth;
//	}
//	if(document.getElementById("dataDiv")){
//		document.getElementById("dataDiv").style.width = dataWidth;
//	}
//}
////���ܣ���wincodw���ڷ�����С�䶯��ʱ������ִ����������
//window["onresize"] = function() {
//	do_SetPageWidth();
//}


/**
 * ���ܣ���wincodw���ڷ�����С�䶯��ʱ������ִ����������
 */
window["onresize"] = function() {
    do_SetPageWidth();
};

/**
 * ���ܣ�����ҳ����Ŀ�ȡ������ѯҳ���ʹ��
 * �÷�����ʹҳ���ȸ�����Ļ�ֱ������е���
 * Ҫ��ҳ����Ҫ��headDiv��dataDiv������
 */
function do_SetPageWidth() {
    var bodyWidth = document.body.clientWidth - 1;
    var bodyHeight = document.body.clientHeight;
    var pageNaviDiv = document.getElementById("pageNaviDiv");
    if(pageNaviDiv == null){
        var naviTable = document.getElementById("$$$changePageTable$$$");
        if(naviTable != undefined && naviTable != null){
            var obj = naviTable;
            while(obj.tagName != "DIV"){
                obj = obj.parentNode;
            }
            if(obj != null && obj.tagName == "DIV"){
                pageNaviDiv = obj;
            }
        }
    }

    var headDiv = document.getElementById("headDiv");
    var dataDiv = document.getElementById("dataDiv");
    if (headDiv) {
        headDiv.style.overflowX = "hidden";
        headDiv.style.overflowY = "scroll";
        headDiv.style.width = bodyWidth;
    }
    if (dataDiv) {
        dataDiv.style.overflow = "scroll";
        dataDiv.style.width = bodyWidth;
        var dataTop = dataDiv.style.top.toLowerCase();
        var dataHeight = dataDiv.style.height.toLowerCase();
        if(dataTop.indexOf("px") > -1){
            dataTop = Number(dataTop.substring(0, dataTop.length - 2));
        } else if(dataTop == ""){
            dataTop = Number(dataDiv.offsetTop);
        }
        if(dataHeight.indexOf("px") > -1){
            dataHeight = Number(dataHeight.substring(0, dataHeight.length - 2));
        } else if(dataHeight.indexOf("%") > -1){
            dataHeight = "0." + dataHeight.replace("%", "");
            dataHeight = Number(dataHeight) * bodyHeight;
        }
        dataHeight = bodyHeight - dataTop;
        dataDiv.style.height = dataHeight;
        if(pageNaviDiv != null && !pageNaviDiv != undefined){
            dataDiv.style.height = dataHeight - 30;
            pageNaviDiv.style.width = bodyWidth;
            pageNaviDiv.style.position = "absolute";
            pageNaviDiv.style.top = bodyHeight - 25;
        }
    }
}

function do_Close(){
	if(confirm("��ȷ���Ѿ���������Ĺ���������������ȷ������ť�������ȡ������ť���ڵ�ǰҳ��")){
		self.close();
		if(window.opener){
			var frm = opener.document.forms[0];
			if(frm){
				if(frm.act){
					frm.act.value = "QUERY_ACTION";
				}
				frm.submit();
			}
		}
	}
}


function do_ProcessSimpleAjax(url, sendData){
	do_ProcessAjax(url, "POST", true, sendData);
}

function do_ProcessAjax(url, reqMethod, isSynchronize, sendData) {//����XMLHttpRequest����
	if(!url || url == null || url == ""){
		alert("û��ָ�������URL��");
		return;
	}
	if(!sendData){
		sendData = null;
	}

	if(!isSynchronize){
		isSynchronize = true;
	}
	if(!reqMethod){
		reqMethod = "POST";
	} else {
		reqMethod = reqMethod.toUpperCase();
	}
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
	if(!xmlHttp){
		alert("����XMLHttpRequest����ʧ�ܣ������ԣ�");
		return;
	}
	xmlHttp.onreadystatechange = handleReadyStateChange;
	xmlHttp.open(reqMethod, url, isSynchronize);
	xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xmlHttp.send(sendData);
}

function handleReadyStateChange() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
			do_ProcessResponse(xmlHttp.responseText);
        } else {
            alert(xmlHttp.status);
        }
    }
}
