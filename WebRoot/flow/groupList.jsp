<%@ page import="com.sino.base.dto.DTOSet"%>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant"%>
<%@ page import="com.sino.sinoflow.dto.SfGroupDTO" %>
<%--
  User: Yung,Kam Hing
  Date: 2008-9-08
  Time: 10:05:36
  Function:ѡ���û���ǰ��רҵ�����
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<link href="/WebLibary/cms_css/cms_css.css" rel="stylesheet" type="text/css"/>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    DTOSet groups = (DTOSet) request.getAttribute(WebAttrConstant.GROUP_DTOSET);
%>
<html>
  <head>
      <title>ѡ��ǰ���</title>
   <script type="text/javascript">
       window.returnValue = "";
       function do_select() {
           var idx = document.getElementById("sf_curGroupId").selectedIndex;
           if (idx > -1) {
               window.returnValue = document.getElementById("sf_curGroupId").options[idx].value;
               window.close();
           } else {
               alert("��ѡ�����");
           }
       }

       function do_close() {
            var idx = document.getElementById("sf_curGroupId").selectedIndex;
            if (idx > -1) {
                window.returnValue = document.getElementById("sf_curGroupId").options[idx].value;
//                window.close();
            } else {
                window.returnValue = "";
                event.returnValue = "��ѡ����𣡲�ѡ����������Զ�ȡ������, ���и��Ķ������ϣ�";
            }
        }

   </script>
  </head>
<body onbeforeunload=do_close()>
  <!--form name="mainFrm" action=do_select() -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
       <td colspan="2">&nbsp;</td>
    </tr>
    <tr valign="top">
      <td width="5%" />
      <td width="90%" colspan="2" align="center">
        <select name="sf_curGroupId" size="10" style="width:100%" ondblclick="do_select()">
            <%
                if (groups != null && groups.getSize() > 0) {
                    SfGroupDTO sfGroup;
                    for (int i = 0; i < groups.getSize(); i++) {
                         sfGroup =(SfGroupDTO) groups.getDTO(i);
            %>
            <option value="<%=sfGroup.getGroupName()%>"><%=sfGroup.getGroupName()%></option>
            <%
                    }
                }
            %>
        </select>
      </td>
      </tr>
        <tr>
            <table width="40%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td colspan="2">&nbsp;</td>
                </tr>
              <tr>
                <td align="center"><input name="Submit" type="button" class="but2" id="Submit" value="ȷ��" onClick="do_select()"/></td>
              </tr>
            </table>

      </tr>        
  </table>
  <!--/form-->
</body>
</html>