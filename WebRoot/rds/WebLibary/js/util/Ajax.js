//var resHandler = null;
//var responseType = null;
//var xmlHttp = null;
//var resXML = null;
//var resText = null;
//
//function do_ProcessSimpleAjax(url, sendData, responseHandler) {
//    resHandler = responseHandler;
//    do_ProcessAjax(url, "POST", true, sendData, 1);
//}
//
//function do_ProcessXMLAjax(url, sendData, responseHandler) {
//    resHandler = responseHandler;
//    do_ProcessAjax(url, "POST", true, sendData, 2);
//}
//
//function do_SynchronizeSimpleAjax(url, sendData, responseHandler) {
//    resHandler = responseHandler;
//    do_ProcessAjax(url, "POST", false, sendData, 1);
//}
//
//function do_SynchronizeXMLAjax(url, sendData, responseHandler) {
//    resHandler = responseHandler;
//    do_ProcessAjax(url, "POST", false, sendData, 2);
//}
//
//function GetXmlRequest() {
//    var objXmlHttp = null;
//    if (navigator.userAgent.indexOf("Opera") >= 0) {
//        alert("�Բ�������������֧�ָò�����");
//        return null;
//    }
//    if (navigator.userAgent.indexOf("MSIE") >= 0) {
//        var strName = "Msxml2.XMLHTTP";
//        if (navigator.appVersion.indexOf("MSIE 5.5") >= 0) {
//            strName = "Microsoft.XMLHTTP";
//        }
//        if (window.ActiveXObject) {
//            objXmlHttp = new ActiveXObject(strName);
//        } else if (window.XMLHttpRequest) {
//            objXmlHttp = new XMLHttpRequest();
//        }
//    } else if (navigator.userAgent.indexOf("Mozilla") >= 0) {
//        objXmlHttp = new XMLHttpRequest();
//        return objXmlHttp;
//    }
//    return objXmlHttp;
//}
//
//function do_ProcessAjax(url, reqMethod, isSynchronize, sendData, resType) {//����XMLHttpRequest����
//    if (!url || url == null || url == "") {
//        alert("û��ָ�������URL��");
//        return;
//    }
//    if (!sendData) {
//        sendData = null;
//    }
//    if (isSynchronize == undefined || isSynchronize == null) {
//        isSynchronize = true;
//    }
//    if (!reqMethod) {
//        reqMethod = "POST";
//    } else {
//        reqMethod = reqMethod.toUpperCase();
//    }
//    if (!resType || resType != 2) {
//        resType = 1;
//    }
//    responseType = resType;
//    xmlHttp = GetXmlRequest();
//    if (!xmlHttp) {
//        alert("����XMLHttpRequest����ʧ�ܣ������ԣ�");
//        return;
//    }
//    xmlHttp.onreadystatechange = handleReadyStateChange;
//    xmlHttp.open(reqMethod, url, isSynchronize);
//    xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
//    xmlHttp.send(sendData);
//}
//
///**
//* responseType��xmlHttp����Ľ�����͡�2��ʾxml�������κβ����򲻴�������ʾtext��
//*/
//function handleReadyStateChange() {
//    if (xmlHttp.readyState == 4) {
//        if (xmlHttp.status == 200) {
//            if (responseType == 1) {
//                resText = xmlHttp.responseText;
//                if (resHandler) {
//                    eval(resHandler + "()");
//                } else {
//                    do_ProcessTXTResponse(resText);
//                }
//            } else {
//                resXML = xmlHttp.responseXML;
//                if (resHandler) {
//                    eval(resHandler + "()");
//                } else {
//                    do_ProcessXMLResponse(resXML);
//                }
//            }
//        } else {
//            alert(xmlHttp.responseText);
//        }
//        resHandler = null;
//        //        responseType = null;
//    }
//}
//
//function do_ProcessXMLResponse(responseXML) {
//    alert("��ʵ�ַ���do_ProcessXMLResponse");
//}
//
//function do_ProcessTXTResponse(responseXML) {
//    alert("��ʵ�ַ���do_ProcessXMLResponse");
//}