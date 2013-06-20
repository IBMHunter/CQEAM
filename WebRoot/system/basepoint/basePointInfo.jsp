<%@ page import="com.sino.ams.constant.LookUpConstant" %>
<%@ page import="com.sino.ams.constant.WebAttrConstant" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectAttributeDTO" %>
<%@ page import="com.sino.ams.system.basepoint.dto.EtsObjectDTO" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.constant.AMSActionConstant" %>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%--
  created by Zyun
  Date: 2007-09-27
  Time: 18:23:30
--%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<HTML>
<head>
    <title>��վ�ص���ϸ��Ϣ</title>
     <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
     <script language="javascript" src="/WebLibary/js/Constant.js"></script>
     <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
     <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
     <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
     <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
     <script language="javascript" src="/WebLibary/js/calendar.js"></script>
     <script language="javascript" src="/WebLibary/js/FormValidate.js"></script>
     <script language="javascript" src="/WebLibary/js/jslib.js"></script>
     <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
</head>
<%--<script type="text/javascript">--%>
    <%--printTitleBar("��վ�ص���ϸ��Ϣ")--%>
<%--</script>--%>
<%
    String category="10";
    EtsObjectDTO spotDTO = (EtsObjectDTO) request.getAttribute(WebAttrConstant.ETS_OBJECT_DTO);
    EtsObjectAttributeDTO attriDTO = (EtsObjectAttributeDTO) request.getAttribute(WebAttrConstant.ETS_OBJECT_ATTRIBUTE_DTO);
    String countyCode = (String) request.getAttribute(WebAttrConstant.COUNTY_OPTION);
    String isall = (String) request.getAttribute(WebAttrConstant.CHECK_OPTION);

%>
<BODY TEXT="000000" BGCOLOR="FFFFFF" leftmargin=0 topmargin=0 class="STYLE1" onload="window.focus();">
<form name="form1" method="post" action="/servlet/com.sino.ams.system.basepoint.servlet.EtsObjectServlet">
 <fieldset style="margin-left:0;height:1350">
 <legend><img src="/images/eam_images/save.jpg" alt="����" style="cursor:'hand'" onClick="do_submit();">
<%
    if (!spotDTO.getWorkorderObjectNo().equals("")) {
        if (StrUtil.isEmpty(spotDTO.getDisableDate())) {
            System.out.println("spotDTO.getDisableDate() = " + spotDTO.getDisableDate());
%>
    <%--<img src="/images/eam_images/disable.jpg" onClick="do_delete(); return false;" alt="ʧЧ" id="delete">--%>
<%
       } else{
%>
  <img src="/images/button/efficient.gif" onClick="do_efficient(); return false;" alt="��Ч" id="delete">
<%
      }
    }
%>
     <img src="/images/eam_images/close.jpg" onClick="do_close();" alt="�ر�"></legend>
<div style="overflow-y:scroll;position:absolute;top:30px;left:0px;width:100%;height:610px">
 <table >
 <tr align="center" id="barcodeNo11" style="color:red;visibility:hidden">�Բ��𣬸û�վ���Ѵ���!</tr>
     <tr>
         <td width="20px" ></td>
         <td>
             <table class="MsoTableGrid" border="1" cellspacing="0" cellpadding="0" width="800" style=" border-collapse: collapse; border: medium none" id="table1" bordercolor="#666666">

             <tr>
                  <td width="4%" rowspan="8"  valign="middle">
                  <p class="MsoNormal" align="center" style="text-align: center">
                   <span style="font-family: '����'">��</span></p>
                  <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">վ</span></p></td>
                  <td width="36%">��վ��</td>
                  <td valign="middle" width="20%"><input  name="workorderObjectCode" type="text"   id="workorderObjectCode" value="<%=spotDTO.getWorkorderObjectCode()%>" class='noEmptyInput' style="width:100%" maxlength='20' onblur="do_verifyworkNo();"></td>
                  <td valign="middle" width="20%">��վ��</td>
                  <td valign="middle" width="20%" ><input name="workorderObjectName" type="text" id="workorderObjectName" value="<%=spotDTO.getWorkorderObjectName()%>" class='noEmptyInput' style="width:100%"></td>
                </tr>
                <tr>
                    <td width="36%">��վ�ص�</td>
                    <td valign="middle" width="20%"><input size="55" name="workorderObjectLocation" type="text" id="workorderObjectLocation" value="<%=spotDTO.getWorkorderObjectLocation()%>"  style="width:100%" class="noEmptyInput"></td>
                    <td valign="middle" width="20%">��</td>
                    <td valign="middle" width="20%" >��</td>
                </tr>
                <tr>
                  <td width="36%" >����BSC</td>
                  <td valign="middle" width="20%" ><input name="attribute59" type="text" id="attribute59" value="<%=attriDTO.getAttribute59()%>" style="width:100%"></td>
                  <td valign="middle" width="20%">��������</td>
                  <td valign="middle" width="20%"  ><input name="projectName"  readonly class = "readonlyInput"type= "text" id="projectName" value="<%=spotDTO.getProjectName()%>" style="width:85%"><a href= "#" onClick="choosePrj()" title = "���ѡ�񹤳�" class="linka" >[��]</a>
                  </td>
                </tr>
                <tr>
                  <td width="36%" >��������</td>
                  <td valign="middle" width="20%" >
                        <select class='noEmptyInput' name="countyCode" style="width:100%">
                          <%=countyCode%>
                        </select></td>
                  <td valign="middle" width="20%"  >��������</td>
                  <td valign="middle" width="20%"  ><input name="attribute1" type="text" id="attribute1" value="<%=attriDTO.getAttribute1()%>" style="width:100%"></td>
                </tr>
                <tr>
                  <td width="36%" >��վ����</td>
                  <td valign="middle" width="20%" ><input name="attribute60" type="text" id="attribute60" value="<%=attriDTO.getAttribute60()%>" style="width:100%"></td>
                  <td valign="middle" width="20%" >������Ϣ</td>
                  <td valign="middle" width="20%"  ><input name="attribute65" type="text" id="attribute65" value="<%=attriDTO.getAttribute65()%>" style="width:100%"></td>
                </tr>
                <tr>
                  <td width="36%" >����</td>
                  <td valign="middle" width="20%" ><input name="attribute2" type="text" id="attribute2" value="<%=attriDTO.getAttribute2()%>" style="width:100%"></td>
                  <td valign="middle" width="20%">γ��</td>
                  <td valign="middle" width="20%"  ><input name="attribute3" type="text" id="attribute3" value="<%=attriDTO.getAttribute3()%>" style="width:100%"></td>
                </tr>
                <tr>
                    <td width="36%" >MMS��Ϣ��һ��<br></td>
                    <td valign="middle" width="20%" ><input name="attribute63" type="text" value="<%=attriDTO.getAttribute63()%>" style="width:100%"></td>
                  <td valign="middle" width="20%"  >MMS��Ϣ�ڶ���<br></td>
                    <td  valign="middle" width="20%"  ><input name="attribute64" type="text" value="<%=attriDTO.getAttribute64()%>" style="width:100%"></td>
                </tr>
				<tr>
					<td width="36%">Ѳ��ģʽ</td>
					<td  valign="middle" width="20%"><select name="isall" style="width:100%"><%=isall%>
                                                    </select></td>
					<td valign="middle" width="20%">��</td>
					<td  valign="middle" width="20%">��</td>
				</tr>
                <tr>
                    <td rowspan="9" width="4%" >
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></td>
                    <td width="36%" >��װ����</td>
                    <td valign="middle" width="20%" ><input name="attribute4" type="text" id="attribute4" value="<%=attriDTO.getAttribute4()%>"  style="width:100%"></td>
                    <td valign="middle" width="20%"  >��������</td>
                    <td  valign="middle" width="20%" ><input name="attribute5" type="text" id="attribute5" value="<%=attriDTO.getAttribute5()%>"  style="width:100%"></td>
                </tr>
                <tr>
                    <td width="36%" >�Ҹߣ��ף�</td>
                    <td  valign="middle" width="20%"> <input size="55" name="attribute6" type="text" id="attribute6" value="<%=attriDTO.getAttribute6()%>"  style="width:100%"></td>
                    <td  valign="middle" width="20%"> ��</td>
                    <td  valign="middle" width="20%"> ��</td>
                </tr>
                <tr>
                    <th width="36%">��</th>
                    <th valign="middle" width="20%">1s</th>
                    <th  valign="middle" width="20%">2s</th>
                    <th valign="middle" width="20%"   >3s</th>
                </tr>
                <tr>
                  <td width="36%">ˮƽ�����</td>
                  <td valign="middle" width="20%" ><input name="attribute7" type="text" id="attribute7" value="<%=attriDTO.getAttribute7()%>"  style="width:100%"></td>
                  <td  valign="middle" width="20%"><input name="attribute8" type="text" id="attribute8" value="<%=attriDTO.getAttribute8()%>"  style="width:100%"></td>
                  <td valign="middle" width="20%"  ><input name="attribute9" type="text" id="attribute9" value="<%=attriDTO.getAttribute9()%>"  style="width:100%"></td>
                </tr>
                <tr>
                  <td width="36%">�������</td>
                  <td valign="middle" width="20%"><input name="attribute10" type="text" id="attribute10" value="<%=attriDTO.getAttribute10()%>" style="width:100%"></td>
                  <td  valign="middle" width="20%" ><input name="attribute11" type="text" id="attribute11" value="<%=attriDTO.getAttribute11()%>" style="width:100%"></td>
                  <td  valign="middle" width="20%"   ><input name="attribute12" type="text" id="attribute12" value="<%=attriDTO.getAttribute12()%>" style="width:100%"></td>
                </tr>
                <tr>
                    <td width="36%">���������ƽ̨�����ٰ�װ����������</td>
                    <td valign="middle" width="60%" colspan="3"> <input size="55" name="attribute13" type="text" id="attribute13" value="<%=attriDTO.getAttribute13()%>" style="width:100%"></td>
                    <td valign="middle" > ��</td>
                </tr>
                <tr>
                    <td width="36%" >�Ƿ�����ͨGSM/CDMA������      </td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute14" type="text" id="attribute14" value="<%=attriDTO.getAttribute14()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >�Ƿ���PHS��С��ͨ��������</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute15" type="text" id="attribute15" value="<%=attriDTO.getAttribute15()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                  <td width="36%" >��ע</td>
                  <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute16" type="text" id="attribute16" value="<%=attriDTO.getAttribute16()%>" style="width:100%"></p></td>
                  <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td rowspan="6" width="4%" >
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></td>
                    <td width="36%" >���跽ʽ</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute17" type="text" id="attribute17" value="<%=attriDTO.getAttribute17()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >����¥�㣨F��</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute18" type="text" id="attribute18" value="<%=attriDTO.getAttribute18()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >���������ƽ���ף�</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute19" type="text" id="attribute19" value="<%=attriDTO.getAttribute19()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >�Ƿ�������������Ӫ�̹���</td>
                    <td valign="middle" width="60%" colspan="3" ><p> <input size="55" name="attribute20" type="text" id="attribute20" value="<%=attriDTO.getAttribute20()%>" style="width:100%"></p></td>
                    <td valign="middle" > ��</td>
                </tr>
                <tr>
                  <td width="36%" >�Ƿ�׼����2008��װ��</td>
                  <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute21" type="text" id="attribute21" value="<%=attriDTO.getAttribute21()%>" style="width:100%"></p></td>
                  <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >��ע</td>
                    <td valign="middle" width="60%" colspan="3" ><p><textarea  name="attribute22" cols="55" rows="4" id="attribute22" style="width:100%"><%=attriDTO.getAttribute22()%></textarea></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td rowspan="7" width="4%" >
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></td>
                    <th width="36%" > <span class="STYLE5">����</span></th>
                    <th valign="middle" width="20%" ><span class="STYLE4">1</span></th>
                    <th  valign="middle" width="20%" ><span class="STYLE4">2</span></th>
                    <th valign="3" width="20%"   ><span class="STYLE4">3</span></th>
                </tr>
                <tr>
                    <td width="36%" >���͹��</td>
                    <td valign="middle" width="20%" ><input name="attribute23" type="text" id="attribute23" value="<%=attriDTO.getAttribute23()%>" style="width:100%"></td>
                    <td  valign="middle" width="20%" > <input name="attribute29" type="text" id="attribute29" value="<%=attriDTO.getAttribute29()%>" style="width:100%"></td>
                    <td valign="middle" width="20%"   ><input name="attribute25" type="text" id="attribute25" value="<%=attriDTO.getAttribute25()%>" style="width:100%"></td>
                </tr>
                <tr>
                    <td width="36%" >TRX��</td>
                    <td valign="middle" width="20%" > <input name="attribute32" type="text" id="attribute32" value="<%=attriDTO.getAttribute32()%>" style="width:100%"></td>
                    <td  valign="middle" width="20%" > <input name="attribute33" type="text" id="attribute33" value="<%=attriDTO.getAttribute33()%>" style="width:100%"></td>
                    <td valign="middle" width="20%"   > <input name="attribute34" type="text" id="attribute34" value="<%=attriDTO.getAttribute34()%>" style="width:100%"></td>
                </tr>
                <tr>
                  <td width="36%" >��Դ����US��</td>
                  <td valign="middle" width="20%"><input name="attribute35" type="text" id="attribute35" value="<%=attriDTO.getAttribute35()%>" style="width:100%"></td>
                  <td  valign="middle" width="20%" ><input name="attribute31" type="text" id="attribute31" value="<%=attriDTO.getAttribute31()%>" style="width:100%"></td>
                  <td valign="middle" width="20%"   ><input name="attribute37" type="text" id="attribute37" value="<%=attriDTO.getAttribute37()%>" style="width:100%"></td>
                </tr>
                <tr>
                  <td nowrap width="36%" >�������ͣ�DE34��</td>
                  <td valign="middle" width="20%"><input name="attribute26" type="text" id="attribute26" value="<%=attriDTO.getAttribute26()%>" style="width:100%"></td>
                  <td  valign="middle" width="20%" ><input name="attribute24" type="text" id="attribute24" value="<%=attriDTO.getAttribute24()%>" style="width:100%"></td>
                  <td  valign="middle" width="20%"  ><input name="attribute38" type="text" id="attribute38" value="<%=attriDTO.getAttribute38()%>" style="width:100%"></td>
                </tr>
                <tr>
                  <td width="36%" >��������</td>
                  <td valign="middle" width="20%"><input name="attribute27" type="text" id="attribute27"  value="<%=attriDTO.getAttribute27()%>" style="width:100%"></td>
                  <td  valign="middle" width="20%" ><input name="attribute30" type="text" id="attribute30" value="<%=attriDTO.getAttribute30()%>" style="width:100%"></td>
                  <td valign="middle" width="20%"   ><input name="attribute39" type="text" id="attribute39" value="<%=attriDTO.getAttribute39()%>" style="width:100%"></td>
                </tr>
                <tr>
                    <td width="36%" >���߳���</td>
                    <td width="20%" valign="middle"><input name="attribute28" type="text" id="attribute28" value="<%=attriDTO.getAttribute28()%>" style="width:100%"></td>
                    <td  valign="middle" width="20%" ><input name="attribute36" type="text" id="attribute36" value="<%=attriDTO.getAttribute36()%>" style="width:100%"></td>
                    <td valign="middle" width="20%"   > <input name="attribute40" type="text" id="attribute40" value="<%=attriDTO.getAttribute40()%>" style="width:100%"></td>
                </tr>
                <tr>
                    <td rowspan="14" width="4%" >
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></td>
                    <td width="36%" >��վ���䷽ʽ</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="58" name="attribute41" type="text" id="attribute41" value="<%=attriDTO.getAttribute41()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >���д����豸���ṩ���ӵ�2Mbps��·��</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute42" type="text" id="attribute42" value="<%=attriDTO.getAttribute42()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >���д����豸�ܷ��������</td>
                    <td valign="middle" width="60%" colspan="3" ><p> <input size="55" name="attribute43" type="text" id="attribute43" value="<%=attriDTO.getAttribute43()%>" style="width:100%"></p></td>
                    <td valign="middle" > ��</td>
                </tr>
                <tr>
                    <td width="36%" >��վ��Դ����</td>
                    <td valign="middle" width="60%" colspan="3" ><p> <input size="55" name="attribute44" type="text" id="attribute44" value="<%=attriDTO.getAttribute44()%>" style="width:100%"></p></td>
                    <td valign="middle" > ��</td>
                </tr>
                <tr>
                    <td width="36%" >������������е�������Kwh�� </td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute45" type="text" id="attribute45" value="<%=attriDTO.getAttribute45()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >������� ��A��</td>
                    <td valign="middle" width="60%" colspan="3" ><p> <input size="55" name="attribute46" type="text" id="attribute46" value="<%=attriDTO.getAttribute46()%>" style="width:100%"></p></td>
                    <td valign="middle" > ��</td>
                </tr>
                <tr>
                    <td width="36%" >�����ѹ ��V��</td>
                    <td valign="middle" width="60%" colspan="3" ><p> <input size="55" name="attribute47" type="text" id="attribute47" value="<%=attriDTO.getAttribute47()%>" style="width:100%"></p></td>
                    <td valign="middle" > ��</td>
                </tr>
                <tr>
                    <td width="36%" >���ص�Դģ������������</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute48" type="text" id="attribute48" value="<%=attriDTO.getAttribute48()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >����������������</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute49" type="text" id="attribute49" value="<%=attriDTO.getAttribute49()%>" style="width:100%"></p></td>
                    <td valign="middle" ></td>
                </tr>
                <tr>
                    <td width="36%" >�Ƿ����������������</td>
                    <td valign="middle" width="60%" colspan="3" ><p>
                      <input size="55" name="attribute50" type="text" id="attribute50" value="<%=attriDTO.getAttribute50()%>" style="width:100%"></p></td>
                    <td valign="middle" >
                      ��</td>
                </tr>
                <tr>
                  <td width="36%" >�Ƿ��Ѳ��ó��ز۸ּӹ�</td>
                  <td valign="middle" width="60%" colspan="3" ><p><input name="attribute61" type="text" id="attribute61" value="<%=attriDTO.getAttribute61()%>" size="55" style="width:100%"></p></td>
                  <td valign="middle" >��</td>
                </tr>
                <tr>
                  <td width="36%" >�����ʶ</td>
                  <td valign="middle" width="60%" colspan="3" ><p><input name="attribute62" type="text" value="<%=attriDTO.getAttribute62()%>" size="55" style="width:100%"></p></td>
                  <td valign="middle" >��</td>
                </tr>
                <tr>
                  <td width="36%" >�յ��ͺż���ȫ���</td>
                  <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute51" type="text" id="attribute51" value="<%=attriDTO.getAttribute51()%>" style="width:100%"></p></td>
                  <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >��ע</td>
                    <td valign="middle" width="60%" colspan="3" ><p><textarea name="attribute52" cols="55" rows="3" id="attribute52" style="width:100%" ><%=attriDTO.getAttribute52()%></textarea></p></td>
                  <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td rowspan="5" width="4%">
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></p>
                    <p class="MsoNormal" align="center" style="text-align: center">
                    <span style="font-family: '����'">��</span></td>
                    <td width="36%" >��������</td>
                    <td valign="middle" width="60%" colspan="3" ><p><input size="55" name="attribute53" type="text" id="attribute53" value="<%=attriDTO.getAttribute53()%>" style="width:100%"></p></td>
                    <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >�Ƿ񸲸�վ</td>
                    <td valign="middle" width="60%" colspan="3" ><p>
                  <input size="55" name="attribute54" type="text" id="attribute54" value="<%=attriDTO.getAttribute54()%>" style="width:100%"></p></td>
                    <td valign="middle" >
                  ��</td>
                </tr>
                <tr>
                    <td width="36%" >�ܷ���3G��վ</td>
                    <td valign="middle" width="60%" colspan="3" ><p>
                      <input size="55" name="attribute55" type="text" id="attribute55" value="<%=attriDTO.getAttribute55()%>" style="width:100%"></p></td>
                    <td valign="middle" >
                      ��</td>
                </tr>
                <tr>
                  <td width="36%" >���ܹ�վ�ľ�������</td>
                  <td valign="middle" width="60%" colspan="3" ><p><input name="attribute56" type="text" id="attribute56" value="<%=attriDTO.getAttribute56()%>" size="55" style="width:100%"></p></td>
                  <td valign="middle" >��</td>
                </tr>
                <tr>
                    <td width="36%" >��ע</td>
                    <td valign="middle" width="60%" colspan="3" ><p><textarea name="attribute57" cols="55" rows="3" id="attribute57" style="width:100%"><%=attriDTO.getAttribute57()%></textarea></p></td>
                    <td valign="middle" >��</td>
                </tr>
            </table>
       </td>
        </tr>
</table>
</div>
 </fieldset>
 <input type=hidden name="objectCategory" value="<%=category%>">
 <input type=hidden name="organizationId" value="<%=spotDTO.getOrganizationId()%>">
 <input type=hidden name="disableDate" value="<%=spotDTO.getDisableDate()%>">
 <input name="projectId" type="hidden" id="projectId" value="<%=spotDTO.getProjectId()%>">
 <input type="hidden" name="act">
 <input type="hidden" name="isExist">
 <input name="workorderObjectNo" type="hidden" id="workorderObjectNo" value="<%=spotDTO.getWorkorderObjectNo()%>">
</form>
</BODY>
</HTML>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
<script type="text/javascript">
function do_submit() {
    var fieldNames = "workorderObjectCode;workorderObjectName;workorderObjectLocation;countyCode";
    var fieldLabels = "��վ��;��վ��;��վ�ص�;��������";
    if (formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE)) {
        with (form1) {
            if (workorderObjectNo.value == "") {
//                   alert(workorderObjectNo.value);
//                alert("ccc");
                act.value = "<%=WebActionConstant.CREATE_ACTION%>";
                action = "/servlet/com.sino.ams.system.basepoint.servlet.EtsObjectServlet";
                submit();
//                var d = window.opener.document.mainFrm;
                <%--d.act.value = "<%=WebActionConstant.QUERY_ACTION%>";--%>
//                d.submit();
//                window.close();
            } else {
//                alert(workorderObjectNo.value);
//                alert("uuu") ;
                act.value = "<%=WebActionConstant.UPDATE_ACTION%>";
                action = "/servlet/com.sino.ams.system.basepoint.servlet.EtsObjectServlet";
                submit();
//                var d = window.opener.document.mainFrm;
                <%--d.act.value = "<%=WebActionConstant.QUERY_ACTION%>";--%>
//                d.submit();
//                window.close();
            }
            //       alert("act="+act.value);
            //       window.parent.submit();
            //        window.close();
            //         alert("�����ɹ���");
        }
    }
}


function do_verify1() {
      var fieldNames = "attribute6";
      var fieldLabels = "�߹�";
      if (!formValidate(fieldNames, fieldLabels, POSITIVE_VALIDATE)) {
//          alert("�߹ұ���Ϊ���֣�");
          }
}

function validate() {
    var attribute31 = document.forms[0].attribute31.value;
    var attribute32 = document.forms[0].attribute32.value;
    var attribute33 = document.forms[0].attribute33.value;
    var attribute34 = document.forms[0].attribute34.value;
    var attribute35 = document.forms[0].attribute35.value;

    if (isNaN(attribute31) || isNaN(attribute35)) {
        alert("��Դ��Ŀ����Ϊ���֣�");
        document.forms[0].attribute35.focus();
        return false;
    }
    if (isNaN(attribute32) || isNaN(attribute33) || isNaN(attribute34)) {
        alert("TRX��Ŀ����Ϊ���֣�");
        document.forms[0].attribute32.focus();
        return false;
    }
    return true;
}


function choosePrj() {
    var lookUpName = "<%=LookUpConstant.LOOK_UP_PROJECT2%>";
    var dialogWidth = 50.6;
    var dialogHeight = 30;
    var projects = getLookUpValues(lookUpName, dialogWidth, dialogHeight);
    if(projects){
        dto2Frm(projects[0], "form1");
    }
}

function do_close() {
    window.close();
 }

function do_delete() {
    document.form1.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
    document.form1.action = "/servlet/com.sino.ams.system.basepoint.servlet.EtsObjectServlet";
    document.form1.submit();
}

function do_efficient(){
    document.form1.act.value = "<%=AMSActionConstant.INURE_ACTION%>";
    document.form1.action = "/servlet/com.sino.ams.system.basepoint.servlet.EtsObjectServlet";
    document.form1.submit();
}


var xmlHttp;
function do_verifyworkNo() {
    var url = "";
//    var workorderObjectCode = document.form1.workorderObjectCode.value;
    createXMLHttpRequest();
    if (document.form1.workorderObjectCode.value) {
        url = "/servlet/com.sino.ams.system.basepoint.servlet.EtsObjectServlet?act=verifyworkNo&workorderObjectCode=" + document.form1.workorderObjectCode.value;
        xmlHttp.onreadystatechange = handleReadyStateChange1;
        xmlHttp.open("post", url, true);
        xmlHttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xmlHttp.send(null);
    }
}

function createXMLHttpRequest() {     //����XMLHttpRequest����
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
}

function handleReadyStateChange1() {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            if (xmlHttp.responseText == 'Y') {
                
                document.form1.isExist.value = 'Y';
                document.getElementById("barcodeNo11").style.visibility = "visible"
                document.form1.workorderObjectCode.style.color = "red";
                document.form1.workorderObjectCode.focus();
            } else {
                document.form1.isExist.value = 'N';
                document.form1.workorderObjectCode.style.color = "black";
                document.getElementById("barcodeNo11").style.visibility = "hidden";
            }
        } else {
            alert(xmlHttp.status);
        }
    }
}

</SCRIPT>