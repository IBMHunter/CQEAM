<%--
  Created by IntelliJ IDEA.
  User: V-yuanshuai
  Date: 2008-2-28
  Time: 1:10:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType = "text/html;charset=GBK" language = "java" %>
<html>
<head>
    <title>�ʲ���Ϣ�䶯����</title>

    <script type = "text/javascript" src = "/WebLibary/js/SinoToolBar.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/SinoToolBarConst.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/SinoToolBarScroll.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/FormValidate.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/CheckboxProcess.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/RadioProcess.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/SelectProcess.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/FormProcess.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/TableProcess.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/datepicker.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/Constant.js"></script>
    <script type = "text/javascript" src = "/WebLibary/js/CommonUtil.js"></script>
    <link rel = "stylesheet" type = "text/css" href = "/WebLibary/css/main.css">
    <script language = "javascript" src = "/WebLibary/js/LookUp.js"></script>

    <script type = "text/javascript">
        function match() {
        }                 </script>
</head>

<body topmargin = "1" leftmargin = "0">

<form name = "form1" method = "post" action = "">
<script>
    printTitleBar(">>�ʲ���Ϣ�䶯����");
</script>
    <table width="100%" border="0" class="queryHeadBg">
        <tr>
            <td width="9%" align="right">��ǩ�ţ�</td>
            <td width="22%" align="left"><input name=workorderObjectName type=text style="width:80%"><input type=hidden name=workorderObjectNo></td>
            <td width="10%" align="right">�豸���ƣ�</td>
            <td width="20%"><input name="itemName" style="width:100%" type="text"></td>
            <td width="10%" align="right">����ͺţ�</td>
            <td width="10%"><input type=text name=itemSpec></td>
            <td align="center" valign="top"><img src="/images/eam_images/search.jpg" style="cursor:'hand'" onclick="do_search();" alt="��ѯ"></td>
            <td align="center"><img src="/images/button/synchronize.gif" style="cursor:'hand'" onclick="do_match();" alt="ȷ��">
            </td>
        </tr>
    </table>
<div id="headDiv" style="overflow:hidden;position:absolute;top:50px;left:1px;width:850px">
    <table class="headerTable" border="1" width="150%">
        <tr bgcolor="#0073BF" height="22">
            <td rowspan="2" width="4%"><input type="checkbox" name="chk"></td>
            <td colspan = "6" width="48%" align="center"><font color='white'>MISϵͳ</font></td>
            <td colspan = "6" width="4%" align="center"><font color='white'>EAMϵͳ</font></td>
        </tr>

        <tr bgcolor="#0073BF" height="22">
            <td width="8%"><font color='white'>�ʲ���ǩ</font></td>
            <td width="8%"><font color='white'>�ʲ�����</font></td>
            <td width="8%"><font color='white'>�ʲ��ͺ�</font></td>
            <td width="8%"><font color='white'>������ </font>   </td>
            <td width="8%"><font color='white'>���β���</font></td>
            <td width="8%"><font color='white'>���ڵص�</font></td>
            <td width="8%"><font color='white'>��ǩ��</font></td>
            <td width="8%"><font color='white'>�豸����</font></td>
            <td width="8%"><font color='white'>�豸�ͺ�</font></td>
            <td width="8%"><font color='white'>������ </font>  </td>
            <td width="8%"><font color='white'>���β���</font></td>
            <td width="8%"><font color='white'>���ڵص�</font></td>
        </tr>
       </table>
</div>
<div style="overflow:scroll;height:75%;width:867px;position:absolute;top:95px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
    <table id="dataTable" width="150%" border="1" bordercolor="#666666" style="TABLE-LAYOUT:fixed;word-break:break-all">
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck1" value="ON"></TD>
			<TD width="8%" height="22">4089-00000002</TD>
			<TD width="8%" height="22">�㳮��</TD><TD width="8%" height="22">WJDFJ03C</TD>
			<TD width="8%" height="22">������,</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">892500.XANX092.000</TD>
			<TD width="8%" height="22">4089-00000002</TD>
			<TD width="8%" height="22">�㳮��</TD><TD width="8%" height="22">���WJDFJ03C</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">����Ӫҵ��������Ӫҵ��</TD>
			<TD width="8%" height="22">892500.���½�Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck2" value="ON"></TD>
			<TD width="8%" height="22">4089-00000012</TD>
			<TD width="8%" height="22">�㳮��</TD><TD width="8%" height="22">WJDFG03C</TD>
			<TD width="8%" height="22">��־��,</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">892700.XANX271.000</TD>
			<TD width="8%" height="22">4089-00000012</TD>
			<TD width="8%" height="22">�㳮��</TD><TD width="8%" height="22">���WJDFG03C</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">����Ӫҵ��������Ӫҵ��</TD>
			<TD width="8%" height="22">892700.������·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck3" value="ON"></TD>
			<TD width="8%" height="22">4089-00000016</TD>
			<TD width="8%" height="22">�㳮��</TD><TD width="8%" height="22">WJDFJ03C</TD>
			<TD width="8%" height="22">������</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">897400.XANX616.000</TD>
			<TD width="8%" height="22">4089-00000016</TD>
			<TD width="8%" height="22">�㳮��</TD><TD width="8%" height="22">���WJDFJ03C</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ�����·ֲ�</TD>
			<TD width="8%" height="22">897400.���԰Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck4" value="ON"></TD>
			<TD width="8%" height="22">4089-00000023</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000023</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck5" value="ON"></TD>
			<TD width="8%" height="22">4089-00000024</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000024</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck6" value="ON"></TD>
			<TD width="8%" height="22">4089-00000025</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000025</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck7" value="ON"></TD>
			<TD width="8%" height="22">4089-00000026</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000026</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck8" value="ON"></TD>
			<TD width="8%" height="22">4089-00000027</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000027</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck9" value="ON"></TD>
			<TD width="8%" height="22">4089-00000028</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000028</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck10" value="ON"></TD>
			<TD width="8%" height="22">4089-00000029</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000029</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck11" value="ON"></TD>
			<TD width="8%" height="22">4089-00000030</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000030</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck12" value="ON"></TD>
			<TD width="8%" height="22">4089-00000031</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">����</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000031</TD>
			<TD width="8%" height="22">��ʾ���Ŷӻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22">
			<input type="checkbox" name="subCheck13" value="ON"></TD>
			<TD width="8%" height="22">4089-00000046</TD>
			<TD width="8%" height="22">�ŶӽкŻ�</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">����ƽ,</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">896900.XANX410.000</TD>
			<TD width="8%" height="22">4089-00000046</TD>
			<TD width="8%" height="22">�ŶӽкŻ�</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">��������Ӫҵ����¥�ֲ�</TD>
			<TD width="8%" height="22">896900.����·Ӫҵ��.000</TD></TR>
        <TR><TD width="4%" height="22"><input type="checkbox" name="subCheck"></TD>
			<TD width="8%" height="22">4089-00000067</TD>
			<TD width="8%" height="22">��ӡ������</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">���,</TD><TD width="8%" height="22">��</TD>
			<TD width="8%" height="22">895700.XANX719.000</TD>
			<TD width="8%" height="22">4089-00000067</TD>
			<TD width="8%" height="22">����</TD><TD width="8%" height="22">δ֪Ʒ��δ֪�ͺ�</TD>
			<TD width="8%" height="22">��</TD><TD width="8%" height="22">����Ӫҵ���ۺϰ칫��</TD>
			<TD width="8%" height="22">895700.�ۺϲ���¥�ⷿ.000</TD></TR>
    </table>
</div>
</form>
</body>
</html>
<script type="text/javascript">
    function do_search(){
        window.location.href = "/match/syc.jsp";
    }
</script>