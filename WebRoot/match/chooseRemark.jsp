<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2007-12-07
  Time: 14:12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head><title>����ԭ��</title>
    <script type="text/javascript" src="/WebLibary/js/SinoToolBar.js"></script>
   <script type="text/javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
</head>
<body topMargin=0 leftMargin=0 rightMargin=0>
<form>
    <script language="javascript">
    printTitleBar("����ԭ��");
</script>
    <table style="margin-left:0;margin-right:0;width:100%" bgcolor="#eeeeee">
        <tr>
            <td>&nbsp;&nbsp;&nbsp;<font size="2">��ѡ������ԭ��</font>
            </td>
            <td>
                <select name="remark" >
                    <option value="">--��ѡ��--</option>
                    <option value="�������ʲ�">�������ʲ�</option>
                    <option value="�ݹ��ʲ�">�ݹ��ʲ�</option>
                    <option value="�����ͺŲ����ʲ�">�����ͺŲ����ʲ�</option>
                    <option value="MIS�����ϸ�ʲ�">MIS�����ϸ�ʲ�</option>
                    <option value="�������ʲ�">�������ʲ�</option>
                    <option value="ETS�������ʲ�">AMS�������ʲ�</option>
                    <option value="�޷��ҵ��ʲ�">�޷��ҵ��ʲ�</option>
                </select>
            </td>
        </tr>
        <tr>
            <td align="right">&nbsp;&nbsp;&nbsp;<a name="return" style="cursor:'hand'" class="hei" onClick="ret();"><font color="Blue"
                                                                                                        size="2">
                [ȷ��]</font></a>&nbsp;</td>
            <td align="left" height="50">&nbsp;&nbsp;&nbsp;<a name="closeit" style="cursor:'hand'" class="hei" onClick="window.close();"><font
                    color="Blue" size="2">[�ر�]</font></a></td>

        </tr>
    </table>
    <script language="javascript">

        function ret()
        {
            var retVal = document.forms[0].remark.value;
            if(retVal==null||retVal=="")
            {
                alert("��ѡ������ԭ��");
                return false;
            }
//            if (retVal.length > 200)
//                alert("���ܳ���200�ַ���");
//            else
//            {
                window.returnValue = retVal;
                close();
//            }
        }
    </script>
</form>
</body>
</html>