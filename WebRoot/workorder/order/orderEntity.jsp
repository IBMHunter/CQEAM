<%--
  User: zhoujs
  Date: 2008-3-28 15:22:38
  Function:
--%>
<%@ page language="java" buffer="none" contentType="text/html; charset=GBK" %>


<html>
<head>
    <title>���촦��</title>
    <script type="text/javascript">
        function save() {
            window.returnValue = getValue();
            window.close();
        }

        function getValue() {
            var radio = document.dealDiff.result;
            for (i = 0; i < radio.length; i++) {
                if (radio[i].checked) {
                    return radio[i].value;
                }
            }
            return "";
        }
    </script>
</head>
<%


%>
<base target="_self">

<body bgcolor="#FFFFFF" text="#000000">
<form name="dealDiff">
    <p>

        <br/>
    <fieldset style="width: auto">
        <legend style="color:#CECECE">���촦��</legend>
        <table width="100%" border="0"  cellspacing="1" bgcolor="#829AD5">
            <tr bgcolor="#FEFFE8">
                <td><input name="result" type="radio" checked  value="ɨ����Ϊ׼">��ɨ����Ϊ׼</td>
                <td><input name="result" type="radio" value="ϵͳ����Ϊ׼"> ��ϵͳ����Ϊ׼</td>
            </tr>
            <tr  bgcolor="#FEFFE8">
                <td align="center" width="40%"><input name="ok" type="button" onclick="save()" value="ȷ��"></td>
                <td align="center" width="40%"><input name="cancle" type="button" onclick="window.close();" value="ȡ��"></td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>