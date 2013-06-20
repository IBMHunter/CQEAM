<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.constant.*" %>
<%@ page import="com.sino.ams.system.house.dto.AmsHouseInfoDTO" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2007-10-18
  Time: 15:57:18
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
	<title>��������ά��</title>
</head>
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    String action = reqParser.getParameter("act");
    String fromDate = StrUtil.nullToString(request.getParameter("fromDate"));
    String toDate = StrUtil.nullToString(request.getParameter("toDate"));
    AmsHouseInfoDTO housedto = (AmsHouseInfoDTO) request.getAttribute(WebAttrConstant.AMS_HOUSE_INFO_DTO);
%>
<body onkeydown="autoExeFunction('do_search()');"style="overflow:scroll;">
<%=WebConstant.WAIT_TIP_MSG%>
<form action="/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet?act=QUERY_ACTION" name="mainFrm" method="post">
<script type="text/javascript">
    printTitleBar("��������ά��")
</script>
<div id="marqueetipMsg" style="position:absolute;top:44px;left:-259px;width:743px; z-index:10; visibility:hidden">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="">
        <td align="right">�������ڣ�</td>
        <td align="left"><input type="text" name="fromDate" value="<%=fromDate%>" readOnly style="width:85%" class="input_style2" onclick="gfPop.fStartPop(fromDate,toDate);"><img src="/images/calendar.gif"
                                                                                                alt="���ѡ������" onclick="gfPop.fStartPop(fromDate,toDate);">
        </td>
        <td align="right">����</td>
        <td align="left"><input type="text" name="toDate" value="<%=toDate%>" readOnly style="width:85%"
                                class="input_style2" onclick="gfPop.fEndPop(fromDate,toDate);"><img src="/images/calendar.gif" alt="���ѡ������" onclick="gfPop.fEndPop(fromDate,toDate);">
        </td>
    </table>
</div>
<table border="0" width="100%" class="queryTable">
    <tr>
        <td width="10%" align="right">���룺</td>
        <td width="14%" align="left"><input type="text" name="barcode" class="input_style1" style="width:100%" value="<%=housedto.getBarcode()%>">
        </td>
        <td width="12%" align="right">���ƣ�</td>
        <td width="14%" align="left"><input name="itemName" class="input_style1" style="width:100%" value = "<%=housedto.getItemName()%>"></td>
        <td width="12%" align="right">�Ƿ��Ѵ���</td>
        <td width="14%" align="left"><select name="temp" class="select_style1" style="width:100%;">
                    <option value= "">--��ѡ��--</option>
                    <option value="δ����" <%=housedto.getTemp().equals("δ����")? "selected":""%>>δ����</option>
                    <option value="�Ѵ���" <%=housedto.getTemp().equals("�Ѵ���")? "selected":""%>>�Ѵ���</option>
                </select></td>
        <td width = "10%" align="right">�������ʣ�</td>
        <td width = "14%" ><select name="landType" class="select_style1" style="width:100%">
                    <option value= "">--��ѡ��--</option>
                    <option value="����" <%=housedto.getLandType().equals("����")? "selected":""%>>����</option>
                    <option value="����" <%=housedto.getLandType().equals("����")? "selected":""%>>����</option>
                    <option value="����" <%=housedto.getLandType().equals("����")? "selected":""%>>����</option>
                </select>
        </td>
    </tr>
     <tr>
       <td align="right" >�Ƿ��վ��</td>
       <td align="left">
                <select name="officeUsage" class="select_style1" style="width:100%" >
                    <option value="">--��ѡ��--</option>
                    <option value="��վ" <%=housedto.getOfficeUsage().equals("��վ")? "selected":""%> >��վ</option>
                    <option value="�ǻ�վ" <%=housedto.getOfficeUsage().equals("�ǻ�վ")? "selected" : ""%>>�ǻ�վ</option>
                </select></td>
       <td align="right" >�����������ͣ�</td>
       <td  align="left"><select name="officeType" class="select_style1" style="width:100%;">
                    <option value="">--��ѡ��--</option>
                    <option value="����" <%=housedto.getOfficeType().equals("����")? "selected":""%>>����</option>
                    <option value="����" <%=housedto.getOfficeType().equals("����")? "selected":""%>>����</option>
                    <option value="���غ�һ" <%=housedto.getOfficeType().equals("���غ�һ")? "selected":""%>>���غ�һ</option>
                </select></td>
      <td  align="right">����֤�ţ�</td>
      <td align="right"><input type="text" name="houseCertificateNo" class="input_style1" style="width:100%;" value="<%=housedto.getHouseCertificateNo()%>"></td>
      <td align= "right">����֤�ţ�</td>
      <td align = "right"><input type="text" name="landCertficateNo" class="input_style1" style="width:100%;" value ="<%=housedto.getLandCertficateNo()%>"></td>
    </tr>
    <tr>
        <td align="right"></td>
        <td align="left"></td>
        <td align="right" > </td>
        <td align="left"></td>
        <td align="right"></td>
        <td align="left"></td>
        <td align="right"><img src="/images/eam_images/search.jpg" alt="��ѯδ������Ϣ" onClick="do_search(); return false;"></td>
        <td align = "left"><img src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel"><img src="/images/eam_images/reset.jpg" style="cursor:'hand'" onclick="do_disabled();" alt="���ø�Ϊδ����">
        </td>
    </tr>
</table>
<input type="hidden" name="act" value="<%=action%>">
  <div style="left:1px;width:100%;overflow-y:scroll" class="crystalScroll">
    <table  border="1" width="100%" class="headerTable" cellpadding="0" cellspacing="0">
        <tr height="22">
            <td width="2%" align="center" style="padding:0"><input type="checkbox" name="titleCheck"
                                                                   class="headCheckbox"
                                                                   id="controlBox"
                                                                   onclick="checkAll('titleCheck','barcodes')"></td>
            <td width="10%" align="center">����</td>
            <td width="12%" align="center">����</td>
            <td width="12%" align="center">�ͺ�</td>
            <td width="19%" align="center">�ص�</td>
            <td width="7%" align="center">�Ƿ��վ</td>
            <td width="7%" align="center">����</td>
            <td width="8%" align="center">����֤��</td>
            <td width="9%" align="center">����֤��</td>
            <td width="7%" align="center">��������</td>
            <td width="7%" align="center">�Ƿ���</td>
        </tr>
    </table>
</div>
<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && !rows.isEmpty()) {
%>
<div style="overflow-y:scroll;height:355px;width:100%;left:1px;margin-left:0px" align="left">
    <table width="100%" border="1" bordercolor="#666666" id="dataTab">
        <%
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
        %>
        <tr class="dataTR" >
            <td width="2%" align="center"><input type="checkbox" name="barcodes" value="<%=row.getValue("SYSTEMID")%>"></td>
            <td height="22" width="10%" align="center" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("BARCODE")%>
            </td>
            <td height="22" width="12%" align="left" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("ITEM_NAME")%>
            </td>
            <td height="22" width="12%" align="left" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("ITEM_SPEC")%>
            </td>
            <td height="22" width="19%" align="left" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("HOUSE_ADDRESS")%>
            </td>
            <td height="22" width="7%" align="left" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("OFFICE_USAGE")%>
            </td>
            <td height="22" width="7%" align="left" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("OFFICE_TYPE")%>
            </td>
            <td height="22" width="8%" align="left" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("HOUSE_CERTIFICATE_NO")%>
            </td>
            <td height="22" width="9%" align="left" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("LAND_CERTFICATE_NO")%>
            </td>
            <td height="22" width="7%" align="center" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("LAND_TYPE")%>
            </td>
            <td height="22" width="7%" align="center" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>')"><%=row.getValue("ATTRIBUTE2")%>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
</form>
<div style="left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
</div>
<jsp:include page="/message/MessageProcess"/>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js"
        src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0"
        style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<iframe name="downFrm" src="" style="display:none"></iframe>
<script type="text/javascript">
function do_search() {
    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet";
    mainFrm.submit();
}

function show_detail(barcode, systemId, officeUsage, officeType, temp) {
    var height = "250px";
    if (((officeUsage == "�ǻ�վ") && (officeType == "����")) || ((officeUsage == "�ǻ�վ") && (officeType == "���غ�һ"))) {
        height = "400px";
    }
    if (temp == "δ����") {
        var url = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet?act=SUBMIT_ACTION&barcode=" + barcode + "&bts=" + officeUsage + "&category=" + officeType;
        var winStyle = "height=125,width=320, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,help=no,top=200,left=200";
        window.open(url, "_house", winStyle);
    } else if (temp == "�Ѵ���") {
        var url = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet?act=DETAIL_ACTION&barcode=" + barcode + "&temp=hased";
        var winStyle = "height=" + height + ",width=620px,top=150, left=150, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=yes,help=no";
        window.open(url, "_house", winStyle);
    }
}

function do_misIn() {   //��mis���뷿�����ص���Ϣ                WorkPersonServlet
    mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.GetMisHousInfoServlet";
    mainFrm.submit();
}

function do_Export() {                  //����execl
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet";
    mainFrm.submit();
}

function do_showCalendar1() {
    var isRent = document.all["isRent"].options;
    for (var i = 0; i < isRent.length; i++) {
        if (isRent[i].selected && isRent[i].value == "Y") {
            //        mainFrm.rentInfo.style.display = "none";
            //        document.all["rentInfo"].style.display = "inline";
            document.getElementById("marqueetipMsg").style.visibility = "visible";
        } else if (isRent[i].selected && isRent[i].value == "N") {
            document.getElementById("marqueetipMsg").style.visibility = "hidden";
            document.mainFrm.fromDate.value = "";
            document.mainFrm.toDate.value = "";

        } else if (isRent[i].selected && isRent[i].value == "") {
            document.getElementById("marqueetipMsg").style.visibility = "hidden";
            document.mainFrm.fromDate.value = "";
            document.mainFrm.toDate.value = "";
        }
    }
    //display:inline
}

function do_showCertificateNo() {
    if (mainFrm.isCertificate.value == "Y") {
        document.getElementById("certificate").style.display = "block";
    } else {
        document.getElementById("certificate").style.display = "none";
        document.mainFrm.fromDate.value = "";
        document.mainFrm.toDate.value = "";
    }
}

function do_disabled() {             //�����޸�Ϊ"δ����"
    var checkedCount = getCheckedBoxCount("barcodes");
    if (checkedCount < 1) {
        alert("������ѡ��һ�����ݣ�");
        return;
    } else {
        mainFrm.act.value = "<%=AMSActionConstant.INSTEAD_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.DispositonHouserServlet";
        mainFrm.submit();
    }
}

</script>