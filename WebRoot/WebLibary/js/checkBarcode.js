/**
 * ���ڼ��Ҫ����������Ƿ��ڸòֿ���
 * Ҫ��:
 *     1.ҳ����Ҫ�� fromObjectNo ���������
 *     2.ҳ�������������Ӧ��Ϊ barcode
 *     3.ҳ����Ҫ��ӡ WebConstant.WAIT_TIP_MSG (��ʾ��Ϣ)
 *     4.ҳ�����·�������һ��div,������ʾ������Ϣ,idӦΪ showMsg ,��:<div id=\"showMsg\" style=\"color:red\"></div>
 *     5.ҳ���ύ����ִ�е� function ӦΪ: do_submit(),�ύ��ť�� onclick �¼���Ϊ checkBarcode();
 *     6.��ͬʱ������������JS�ļ�: /WebLibary/js/ajax.js
 *                              /WebLibary/js/json.js
 **/
var xmlHttp;
function checkBarcode() {
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    var barcodes = document.getElementsByName("barcode");
    var barcodeArr = new Array();
    for (var i = 0; i < barcodes.length; i++) {
        barcodeArr[i] = barcodes[i].value;
    }
    var qtys = document.getElementsByName("quantity");
    var quantityArr = new Array();
    for (var i = 0; i < qtys.length; i++) {
        quantityArr[i] = qtys[i].value;
    }
    var objectNo = document.getElementById("fromObjectNo").value;

    var url = "/servlet/com.sino.ams.bean.CheckBarCodeServlet?objectNo=" + objectNo;
    xmlHttp = GetXmlHttpObject(do_check);
    xmlHttp.open('POST', url, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
    xmlHttp.send(barcodeArr.toJSONString()+"$$$"+quantityArr.toJSONString());
}
function do_check() {
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        var resText = xmlHttp.responseText;
        if (resText == "ERROR") {
            alert(resText);
        } else if (resText == "OK") {
            eval("do_submit()");
        } else {
            document.getElementById("showMsg").innerText = "�������ϱ�����������㣬�޷�����������ѡ��:" + resText;
        }
        document.getElementById("$$$waitTipMsg$$$").style.visibility = "hidden";
        xmlHttp = null;
    }
}
