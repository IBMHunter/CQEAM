<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-1-24
  Time: 13:09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>ѡ�����޷�����������</title></head>
 <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
  <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/ajax.js"></script>
    <script language="javascript" src="/WebLibary/js/json.js"></script>
<%
    String bts = (String) request.getParameter("bts");
    String category = (String) request.getParameter("category");
    String rentId = (String) request.getParameter("rentId");
%>

<body topMargin=0 leftMargin=0>
<form action="chooseHouseCategory.jsp" name="mainFrm">
    <script type="text/javascript">
        printTitleBar("ѡ�����޷�����������")
    </script>
    <table width="100%" align="center" bgcolor="#eeeeee">
          <tr height=20px>
            <td width="70px" align="right">���룺</td>
            <td style="border:none"><%=request.getParameter("barcode")%>
            </td>
        </tr>
        <tr height=20px>
            <td width="70px" align="right">��;��</td>
            <td style="border:none">
                <select name="bts" style="width:80%;" onchange="warning();">
                    <option value="��վ" <%=bts.equals("��վ")? "selected":""%> >��վ</option>
                    <option value="�ǻ�վ" <%=bts.equals("�ǻ�վ")? "selected":""%>>�ǻ�վ</option>
                </select>
            </td>
        </tr>
        <tr height=20px>
            <td width="70px" align="right">���ͣ�</td>
            <td style="border:none">
                <select name="category" style="width:80%;" onchange="warning();">
                    <option value="����" <%=category.equals("����")? "selected":""%>>����</option>
                    <option value="����" <%=category.equals("����")? "selected":""%>>����</option>
                    <option value="���غ�һ" <%=category.equals("���غ�һ")? "selected":""%>>���غ�һ</option>
                </select>
            </td>
        </tr>
        <tr height=20px align=right>
            <td width="50px" style="border:none">&nbsp;</td>
            <td style="border:none">
                <a href="#" onclick="do_submit()"
                   style="cursor:pointer;text-decoration:underline;color:blue">[ȷ��]</a>
                <a href="#" onclick="window.close();"
                   style="cursor:pointer;text-decoration:underline;color:blue">[�ر�]</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
        </tr>
        <tr height=20px>
            <td width="70px"></td>
            <td style="border:none">
            </td>
        </tr>
    </table>
    <input type = "hidden" name = "barcode" value="<%=request.getParameter("barcode")%>">
    <input type = "hidden" name = "usage1" value="<%=bts%>">
    <input type = "hidden" name = "type1" value="<%=category%>">
    <input type = "hidden" name = "rentId" value="<%=rentId%>">
</form>
</body>
</html>
<script type="text/javascript">
    function choose() {
        var bts = mainFrm.bts.value;
        var groupId = mainFrm.category.value;
//        window.returnValue = userId;
//        window.close();
    }

    function do_submit() {
        var bts = mainFrm.bts.value;
        var category = mainFrm.category.value;

        var usage = mainFrm.usage1.value;
        var type = mainFrm.type1.value;
        var height = "350px";
        if (((bts == "�ǻ�վ") && (category == "����")) || ((bts == "�ǻ�վ") && (category == "���غ�һ"))) {
            height = "450px";
        }
        if ((category !== "") && (bts !== "")) {
            if ((bts !== usage) || (category !== type)) {
//                   alert(bts+category+":"+usage+type);
                if (confirm("�ı䡰��;�������͡����������ݵĶ�ʧ��ȷ����")) {
                    var url = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet?act=NEW_ACTION&bts=" + bts + "&category=" + category + "&barcode=" + document.mainFrm.barcode.value +"&rentId="+document.mainFrm.rentId.value;
                    var winStyle = "height=" + height + ",width=620px,top=150, left=150, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=yes,help=no";
                    window.open(url, "_blank", winStyle);
//                    window.close();
                } else{
                    selected(document.all["bts"],usage);
                    selected(document.all["category"],type);
                }
            } else {
                var url = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet?act=DETAIL_ACTION&barcode=" + document.mainFrm.barcode.value +"&bts=" + bts + "&category=" + category +"&rentId="+document.mainFrm.rentId.value;
//                var url = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet?act=DETAIL_ACTION&barcode=" + document.mainFrm.barcode.value;
                var winStyle = "height=" + height + ",width=620px,top=150, left=150, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=yes,help=no";
                window.open(url, "_blank", winStyle);
//                window.close();
            }
        } else {
            alert("��ѡ���Ƿ��վ�ͷ����������ͣ�");
        }
    }


function selected(select, optionValue) {
    var optionObj = select.options;
    var optionCount = optionObj.length;
    var tempValue = "";
    for (var i = 0; i < optionCount; i++) {
        tempValue = optionObj[i].value;
            if (tempValue == optionValue) {
                optionObj[i].selected = true;
                break;
        }
    }
}

  function do_search() {
//          window.top.main.document.do_search();
//         alert("222222");
//       window.opener.document.do_search();

        window.top.opener.do_search();

        window.close();
//       window.opener.location.reload();

//        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        <%--mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";--%>
//        mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet";
//        mainFrm.submit();

    }

    var xmlHttp;
    function getImpMenu() {
        var implement = document.getElementById("implement")  ;

        if (implement.options.length > 0) {
            dropOption('implement', false);
        }

        var dept = document.getElementById("dept").value ;
        var url = "/servlet/com.sino.ams.workorder.servlet.WorkPersonServlet?act=implement&dept=" + dept;
        xmlHttp = GetXmlHttpObject(setImpMenu);
        xmlHttp.open('POST', url, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;');
        xmlHttp.send("a=1");


    }
    function setImpMenu() {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
            var flexValues = new Array();
            var descriptions = new Array();
            var resText = xmlHttp.responseText;
            var resArray = resText.parseJSON();
            if (resArray == "ERROR") {
                alert(resText);
            } else {
                if (resArray.length > 0) {

                    var implement = document.getElementById("implement");
                    //                var emptyOption = new Option("--��ѡ��--", "");
                    //                littleCategoryObj.add(emptyOption)
                    var retStr;
                    for (var i = 0; i < resArray.length; i++) {
                        retStr = resArray[i];
                        var arr = retStr.split("$");
                        var option = new Option(arr[1], arr[0]);
                        implement.add(option)
                    }
                }
            }
            xmlHttp = null;
        }
    }

    function warning() {
        var bts = mainFrm.bts.value; //������
        var category = mainFrm.category.value;  //������
        var usage = mainFrm.usage1.value;
        var type = mainFrm.type1.value;
        if ((bts !== usage) || (category !== type)) {
            alert("�ı䡰��;�������͡����������ݵĶ�ʧ");
        }
    }
</script>