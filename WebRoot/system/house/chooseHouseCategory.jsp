<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2008-1-24
  Time: 13:09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="/newasset/headerInclude.htm" %>
<html>
<head>
	<title>ѡ������������</title>
</head>
<%
    String userOfGroupOpt = (String) request.getAttribute("userOfGroup");
    String groupOpt = (String) request.getAttribute("GROUPATTR");
%>

<body topMargin=0 leftMargin=0>
<form action="chooseHouseCategory.jsp" name="mainFrm">
    <script type="text/javascript">
        printTitleBar("ѡ������������")
    </script>
    <table width="100%" align="center">
          <tr height=20px>
            <td width="70px" align="right">���룺</td>
            <td style="border:none"><%=request.getParameter("barcode")%>
                <%--<select name="bts" style="width:80%;" >--%>
                    <%--<option value="��վ">��վ</option>--%>
                    <%--<option value="�ǻ�վ">�ǻ�վ</option>--%>
                <%--</select>--%>
            </td>
        </tr>
        <tr height=20px>
            <td width="70px" align="right">��;��</td>
            <td style="border:none">
                <select name="bts" class="select_style1" style="width:80%;" >
                    <option value="��վ">��վ</option>
                    <option value="�ǻ�վ">�ǻ�վ</option>
                </select>
            </td>
        </tr>
        <tr height=20px>
            <td width="70px" align="right">���ͣ�</td>
            <td style="border:none">
                <select name="category" class="select_style1" style="width:80%;">
                    <option value="����">����</option>
                    <option value="����">����</option>
                    <option value="���غ�һ">���غ�һ</option>
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

       function do_submit(){
           var bts = mainFrm.bts.value;
           var category = mainFrm.category.value;
           var height = "250px";
          if (((bts == "�ǻ�վ") && (category == "����")) || ((bts == "�ǻ�վ") && (category == "���غ�һ"))) {
            height = "400px";
           }
           if ((category!=="")&& (bts!=="")) {
               var url = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet?act=NEW_ACTION&bts=" + bts + "&category="+category+"&barcode="+document.mainFrm.barcode.value;
               var winStyle = "height="+height+",width=620px,top=150, left=150, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=yes,help=no";
               window.open(url, "_bss", winStyle);
//               window.close();
           } else {
               alert("��ѡ���Ƿ��վ�ͷ����������ͣ�");
           }
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


  function do_search() {
//          window.top.main.document.do_search();
//         alert("222222");
//       window.opener.document.do_search();

        window.top.opener.do_search();

        window.close();

//       window.opener.location.reload();

//        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        <%--mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";--%>
//        mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet";
//        mainFrm.submit();

    }
</script>