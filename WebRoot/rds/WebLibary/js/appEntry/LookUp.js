/**
 * ���ܣ�����LookUp������
 * @para lookupId LookUp��ʶ����lookUpAction���ݲ�ͬ��ִֵ�в�ͬ�Ĳ�ѯ����
 * @para dialogWidth ��ҳ�Ի����ȣ�����ָ��ʱȡֵΪ50
 * @para dialogHeight ��ҳ�Ի���߶ȣ�����ָ��ʱȡֵΪ38
 * @para userPara ��Ҫ���ݵ��û�����
 */
var contextPath = "";
var lookUpAction = contextPath + "/rds/reportRun.do";
function do_Lookup(lookUpId, lookUpCode) {
    if ((lookUpId == undefined || lookUpId == null || lookUpId == "") && (lookUpCode == undefined || lookUpCode == null || lookUpCode == "")) {
        alert("lookup���ó����������á�lookupId����lookUpCode��֮һ��");
        return;
    }
    var lookUpConfig = configLookUp(lookUpId, lookUpCode);
    if (!lookUpConfig) {
        return;
    }
    if (!lookUpConfig.processor) {
        alert("lookup���ó���δ����lookup����ֵ��������");
        return;
    }
    var lookUpURL = lookUpConfig.lookUpURL;
    if (!lookUpURL) {
        lookUpURL = lookUpAction;
    }
    if (lookUpURL.indexOf("?") > -1) {
        lookUpURL += "&lookUpId=" + lookUpConfig.lookUpId;
    } else {
        lookUpURL += "?lookUpId=" + lookUpConfig.lookUpId;
    }
    lookUpURL += "&lookUpCode=" + lookUpConfig.lookUpCode;
    if (lookUpConfig.userPara) {
        var userPara = lookUpConfig.userPara;
        if (userPara != undefined && userPara != null && userPara != "") {
            if (userPara.indexOf("&") != 0) {
                lookUpURL += "&";
            }
            lookUpURL += userPara;
        }
    }
    if(!lookUpConfig.widthPercent){
        lookUpConfig.widthPercent = 1;
    }
    if(!lookUpConfig.heightPercent){
        lookUpConfig.heightPercent = 1;
    }
    var dialogWidth = window.screen.availWidth * lookUpConfig.widthPercent;
    var dialogHeight = window.screen.availHeight * lookUpConfig.heightPercent;
    var dialogLeft = (window.screen.availWidth - dialogWidth) / 2;
    var dialogTop = (window.screen.availHeight - dialogHeight) / 2;

    var style = "dialogWidth:"
            + dialogWidth
            + "px;dialogHeight:"
            + dialogHeight
            + "px;dialogLeft:"
            + dialogLeft
            + "px;dialogTop:"
            + dialogTop
            + "px;center:yes;status:no;scrollbars:no;help:no;resizable:yes";
    var returnValue = window.showModalDialog(lookUpURL, null, style);
    lookUpConfig.processor.apply(this, [returnValue]);
}

function LookUpConfig() {
    this.setLookUpId = function(lookUpId) {
        this.lookUpId = lookUpId;
    };
    this.setLookUpCode = function(lookUpCode) {
        this.lookUpCode = lookUpCode;
    };
    this.setLookUpURL = function(lookUpURL) {
        this.lookUpURL = lookUpURL;
    };
    this.setWidthPercent = function(widthPercent) {
        if (isNaN(widthPercent) || Number(widthPercent) > 1 || Number(widthPercent) < 0) {
            this.widthPercent = 1;
        } else {
            this.widthPercent = widthPercent;
        }
    };
    this.setHeightPercent = function(heightPercent) {
        if (isNaN(heightPercent) || Number(heightPercent) > 1 || Number(heightPercent) < 0) {
            this.heightPercent = 1;
        } else {
            this.heightPercent = heightPercent;
        }
    };
    this.setUserPara = function(userPara) {
        this.userPara = userPara;
    };
    this.setProcessor = function(processor) {
        this.processor = processor;
    };
}

function do_CompleteLookUp() {
    window.returnValue = document.getElementById("returnField").value;
    self.close();
}

//
//function configLookUp(lookUpId, lookUpCode) {
//    alert("����㿴���öԻ��򣬱�ʾ�Ǳ�ҳ��Ŀ�����Ա��δʵ�ַ���configLookUp,������֪ͨ...");
//    return null;
//}

var paraObj = null;

function configLookUp(lookUpId, lookUpCode){
    paraObj = event.srcElement;
    var lookUpConfig = new LookUpConfig();
    lookUpConfig.setLookUpId(lookUpId);
    lookUpConfig.setLookUpCode(lookUpCode);
    lookUpConfig.setProcessor(do_ProcessReturnValue);
    return lookUpConfig;
}

function do_ProcessReturnValue(returnValue){
    if(returnValue != undefined && returnValue != null){
        paraObj.value = returnValue;
    } else {
        paraObj.value = "";
    }
}

function setCheckRadioPropValue(obj){
    document.getElementById("returnField").value = obj.value;
}