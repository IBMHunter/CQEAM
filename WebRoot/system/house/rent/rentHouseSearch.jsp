<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.base.constant.web.WebConstant" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.ams.constant.*" %>
<%@ page import="com.sino.ams.system.fixing.dto.EtsItemInfoDTO" %>
<%@ page import="com.sino.ams.system.house.dto.AmsHouseInfoDTO" %>
<%@ include file="/newasset/headerInclude.htm" %>
<%--
  Created by IntelliJ IDEA.
  User: Zyun
  Date: 2007-10-18
  Time: 15:57:18
  Functin:���޷�������ά����
--%>
<html>
<head>
	<title>���޷�������ά��</title>
</head>
<%
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    String action = reqParser.getParameter("act");
    String fromDate = StrUtil.nullToString(request.getParameter("fromDate"));
    String toDate = StrUtil.nullToString(request.getParameter("toDate"));
    String rentUnit = StrUtil.nullToString(request.getParameter("rentUnit"));
    String isRent = (String) reqParser.getAttribute(WebAttrConstant.IS_RENT_OPTION);
    String isCertificate = (String) request.getAttribute(WebAttrConstant.ISLAND_CERTIFICATE_OPTION);
    String hasLandCNo = (String) request.getAttribute(WebAttrConstant.IS_CERTIFICATE_OPTION);
    AmsHouseInfoDTO housedto = (AmsHouseInfoDTO) request.getAttribute(WebAttrConstant.AMS_HOUSE_INFO_DTO);
%>
<body onkeydown="autoExeFunction('do_search()');">
<%=WebConstant.WAIT_TIP_MSG%>
<form action="/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet?act=QUERY_ACTION" name="mainFrm" method="post">
<script type="text/javascript">
    printTitleBar("���޷�������ά��")
</script>
<table border="0" width="100%" class="queryTable">
    <tr>
        <td width="10%" align="right">���룺</td>
        <td width="14%" align="left"><input type="text" name="barcode" style="width:100%" class="input_style1"
                                            value="<%=housedto.getBarcode()%>">
        </td>
        <td width="12%" align="right">���ƣ�</td>
        <td width="14%" align="left"><input name="itemName" style="width:100%" class="input_style1" value = "<%=housedto.getItemName()%>"></td>
        <td width="12%" align="right">�ͺţ�</td>
        <td width="14%" align="left"><input name="itemSpec" style="width:100%" class="input_style1" value = "<%=housedto.getItemSpec()%>"></td>
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
        <td align="right">���޽������ڣ�</td>
        <td align="left"><input type="text" name="fromDate" value="<%=fromDate%>" readOnly  class= "input_style2"
                                    style="width:100%" alt="���ѡ������" onclick="gfPop.fStartPop(fromDate,toDate);"></td>
        <td align="right">����</td>
        <td align="right"><input type="text" name="toDate" value="<%=toDate%>" readOnly style="width:100%"  class= "input_style2"
                                               alt="���ѡ������"  onclick="gfPop.fEndPop(fromDate,toDate);"></td>
        <td align="right">���ⵥλ��</td>
        <td align="right"><input type="text" name = "rentUnit" class="input_style1" style="width:100%" value="<%=rentUnit%>"></td>
        <td align="right" colspan="2">
        	<img src="/images/eam_images/search.jpg"  alt="��ѯδ������Ϣ" onClick="do_search(); return false;">
       	 	<img  src="/images/eam_images/new_add.jpg" alt="������Ϣ" onClick=" do_creat(); return false;">
        	<img src="/images/eam_images/export.jpg" id="queryImg" style="cursor:'hand'" onclick="do_Export();" alt="������Excel">
        </td>
    </tr>
</table>
<input type="hidden" name="act" value="<%=action%>">
<div id="headDiv" style="position:absolute;top:97px;left:1px;width:840px">
    <table class="headerTable" border="1" width="200%" >
        <tr>
            <td height="22" width="7%" align="center">����</td>
            <td height="22" width="14%" align="center">����</td>
            <td height="22" width="14%" align="center">�ͺ�</td>
            <td height="22" width="14%" align="center">�ص�</td>
            <td height="22" width="5%" align="center">�Ƿ��վ</td>
            <td height="22" width="5%" align="center">����</td>
            <td height="22" width="6%" align="center">����֤��</td>
            <td height="22" width="6%" align="center">����֤��</td>
            <td height="22" width="6%" align="center">��������</td>
            
            <td height="22" width="5%" align="center">���</td>
            <td height="22" width="5%" align="center">���ⵥλ</td>

            <td height="22" width="5%" align="center">��ʼ����</td>
            <td height="22" width="5%" align="center">��������</td>
        </tr>

    </table>
</div>

<%
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    if (rows != null && !rows.isEmpty()) {
%>
<div id="dataDiv" style="overflow:scroll;height:66%;width:857px;position:absolute;top:120px;left:1px" align="left" onscroll="document.getElementById('headDiv').scrollLeft = this.scrollLeft;">
     <table width="200%" border="1" bordercolor="#666666" >
        <%
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
        %>
        <tr class="dataTR" onclick="show_detail('<%=row.getValue("BARCODE")%>','<%=row.getValue("SYSTEMID")%>','<%=row.getValue("OFFICE_USAGE")%>','<%=row.getValue("OFFICE_TYPE")%>','<%=row.getValue("ATTRIBUTE2")%>','<%=row.getValue("RENT_ID")%>')">
             <td height="22" width="7%" align="center"><%=row.getValue("BARCODE")%>
            </td>
            <td height="22" width="14%" align="left"><%=row.getValue("ITEM_NAME")%>
            </td>
            <td height="22" width="14%" align="left"><%=row.getValue("ITEM_SPEC")%>
            </td>
            <td height="22" width="14%" align="left"><%=row.getValue("HOUSE_ADDRESS")%>
            </td>
            <td height="22" width="5%" align="left"><%=row.getValue("OFFICE_USAGE")%>
            </td>
            <td height="22" width="5%" align="left"><%=row.getValue("OFFICE_TYPE")%>
            </td>
            <td height="22" width="6%" align="left"><%=row.getValue("HOUSE_CERTIFICATE_NO")%>
            </td>
            <td height="22" width="6%" align="left"><%=row.getValue("LAND_CERTFICATE_NO")%>
            </td>
            <td height="22" width="6%" align="center"><%=row.getValue("LAND_TYPE")%>
            </td>
            <td height="22" width="5%" align="left"><%=row.getValue("RENT_FEE")%>
            </td>
            <td height="22" width="5%" align="center"><%=row.getValue("RENT_UNIT")%>
            </td>
             <td height="22" width="5%" align="center"><%=row.getValue("RENT_START_DATE").toString().substring(0,10)%>
            </td>
             <td height="22" width="5%" align="center"><%=row.getValue("RENT_END_DATE").toString().substring(0,10)%>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
</form>
<div style="position:absolute;top:458px;left:0; right:20px"><%=StrUtil.nullToString(request.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%>
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
    mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet";
    mainFrm.submit();
}

function do_creat() {
//    document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
    <%--mainFrm.act.value = "<%=WebActionConstant.CREATE_ACTION%>";--%>
//    mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet";
//    mainFrm.submit();
       var url = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet?act=CREATE_ACTION";
       var winStyle = "height=125,width=320, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,help=no,top=200,left=200";
       window.open(url, "_house", winStyle);
}

function show_detail(barcode, systemId, officeUsage, officeType, temp,rentId) {
    var height = "250px";
    if (((officeUsage == "�ǻ�վ") && (officeType == "����")) || ((officeUsage == "�ǻ�վ") && (officeType == "���غ�һ"))) {
        height = "420px";
    }
//    if (temp == "δ����") {
        var url = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet?act=SUBMIT_ACTION&barcode=" + barcode + "&bts=" + officeUsage + "&category=" + officeType + "&rentId=" + rentId;
        var winStyle = "height=125,width=320, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,help=no,top=200,left=200";
        window.open(url, "_house", winStyle);
}

function do_misIn() {   //��mis���뷿�����ص���Ϣ                WorkPersonServlet
    mainFrm.act.value = "<%=WebActionConstant.NEW_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.GetMisHousInfoServlet";
    mainFrm.submit();
}

function do_Export() {                  //����execl
    mainFrm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
    mainFrm.action = "/servlet/com.sino.ams.system.house.servlet.RentHouseInfoServlet";
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
</script>