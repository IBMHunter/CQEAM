<%@ page import="com.sino.base.constant.web.WebActionConstant" %>
<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ page import="com.sino.base.data.Row" %>
<%@ page import="com.sino.base.data.RowSet" %>
<%@ page import="com.sino.base.util.StrUtil" %>
<%@ page import="com.sino.base.web.request.upload.RequestParser" %>
<%@ page import="com.sino.ams.newasset.dto.AssetsTagNumberQueryDTO" %>
<%@ page import="com.sino.base.constant.db.QueryConstant" %>
<%@ page import="com.sino.ams.newasset.dto.AmsAssetsCJYCDTO" %>


<html>
<Title>�ʲ�Ԥ�����Ʒ���</Title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%
    /*************** all jsp files must include the following part.********************************************/
    RequestParser reqParser = new RequestParser();
    reqParser.transData(request);
    AmsAssetsCJYCDTO dto = (AmsAssetsCJYCDTO) request.getAttribute("AMSBJTRANSNOHDTO");
//    out.print(dto);
    RowSet rows = (RowSet) request.getAttribute(QueryConstant.SPLIT_DATA_VIEW);
    response.addHeader("pragma", "No-cache");
    response.addHeader("Cache-Control", "no-cache");
    response.addDateHeader("Expires", 1);

%>
<head>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script language="javascript" src="/WebLibary/js/Constant.js"></script>
    <script language="javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script language="javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBar.js"></script>
    <script language="javascript" src="/WebLibary/js/calendar.js"></script>
    <script language="javascript" src="/WebLibary/js/CheckboxProcess.js"></script>
    <script language="javascript" src="/WebLibary/js/LookUp.js"></script>
    <script language="javascript" src="/WebLibary/js/SinoToolBarConst.js"></script>
    <script language="javascript" src="/WebLibary/js/SelectProcess.js"></script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<script type="text/javascript">
    printTitleBar("�ʲ������۾�Ԥ��")
</script>


<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr bgcolor="#E8E8E8">
        <td valign="top" align=left>
            <form action="/servlet/com.sino.ams.newasset.servlet.AmsAssetsFLCJYCServlet" name="mainForm" method="post">
                <table border="1" width="100%" id="table1" cellspacing="0" cellpadding="0"
                       style="background-color:#efefef">
                    <tr width="22%" style="width:100%">
                        <td width=13% align="right"> �ʲ��˲���</td>
                        <td width="22%">
                            <select name="bookTypeCode" id="bookTypeCode"
                                    style="width:100%"><%=request.getAttribute("BOOK_TYPE_CODE")%>
                            </select></td>
                        <td width="20%" align="right">�ʲ�����Ŀ¼��</td>
                        <td><input type="text" name="catSegment2" value="<%=dto.getCatSegment2()%>"></td>
                        <td width=10% align="center"><img src="/images/eam_images/search.jpg" alt="��ѯ"
                                                          onClick="do_search(); return false;"></td>
                    </tr>
                </table>
                <input type="hidden" name="act">

            </form>
        </td>
    </tr>
</table>
<table id="dataTable" width="100%" border="1" bordercolor="#666666"
          >

        <tr>
        <td valign="top" align=left>
            <applet code=com.objectplanet.chart.LineChartApplet
                    archive="/newasset/chart.jar" width=800 height=400>
                <!--��ʽ-->
                <param name=background value=white>
                <param name=defaultGridLinesOn value="true">

                <!-- Title -->
                <param name=chartTitle value="�۾�Ԥ��-�ʲ��䶯���">
                <param name=titleFont value="����,bold,12">

                <!-- ������� -->
                <param name="sampleAxisLabel" value="δ���·�">
                <param name="sampleAxisLabelFont" value="����,bold,12">
                <param name="rangeAxisLabel"  value="���۾ɶԪ��">
                <param name=rangeAxisLabelAngle value="270">
                <param name="rangeAxisLabelFont" value="����,bold,12">

                <!--��������ֵ-->
                 <%
            if (rows != null && rows.getSize() > 0) {
                Row row = null;
                int j=rows.getSize();
                for (int i = 0; i < rows.getSize(); i++) {
                    row = rows.getRow(i);
        %>
                <param name=seriesCount value=<%=j%>>
                   <%-- <param name=sampleValues_<%=i%>
                       value="<%=row.getValue("ALL_QTY1")%>,<%=row.getValue("ALL_QTY10")%>,
                       <%=row.getValue("ALL_QTY20")%>,
                      <%=row.getValue("ALL_QTY30")%>,
                       <%=row.getValue("ALL_QTY40")%>,
                       <%=row.getValue("ALL_QTY50")%> ,
                       <%=row.getValue("ALL_QTY60")%>
                       ">--%>
                <param name=sampleValues_<%=i%>
                       value="<%=row.getValue("ALL_QTY1")%>,<%=row.getValue("ALL_QTY2")%>,<%=row.getValue("ALL_QTY3")%>,<%=row.getValue("ALL_QTY4")%>,<%=row.getValue("ALL_QTY5")%>,
                       <%=row.getValue("ALL_QTY6")%>,<%=row.getValue("ALL_QTY7")%> ,<%=row.getValue("ALL_QTY8")%>,<%=row.getValue("ALL_QTY9")%>,<%=row.getValue("ALL_QTY10")%>,
                       <%=row.getValue("ALL_QTY11")%>,<%=row.getValue("ALL_QTY12")%>,<%=row.getValue("ALL_QTY13")%>,<%=row.getValue("ALL_QTY14")%>,<%=row.getValue("ALL_QTY15")%>,
                       <%=row.getValue("ALL_QTY16")%>,<%=row.getValue("ALL_QTY17")%> ,<%=row.getValue("ALL_QTY18")%>,<%=row.getValue("ALL_QTY19")%>,<%=row.getValue("ALL_QTY20")%>,
                       <%=row.getValue("ALL_QTY21")%>,<%=row.getValue("ALL_QTY22")%>,<%=row.getValue("ALL_QTY23")%>,<%=row.getValue("ALL_QTY24")%>,<%=row.getValue("ALL_QTY25")%>,
                       <%=row.getValue("ALL_QTY26")%>,<%=row.getValue("ALL_QTY27")%> ,<%=row.getValue("ALL_QTY28")%>,<%=row.getValue("ALL_QTY29")%>,<%=row.getValue("ALL_QTY30")%>,
                       <%=row.getValue("ALL_QTY31")%>,<%=row.getValue("ALL_QTY32")%>,<%=row.getValue("ALL_QTY33")%>,<%=row.getValue("ALL_QTY34")%>,<%=row.getValue("ALL_QTY35")%>,
                       <%=row.getValue("ALL_QTY36")%>,<%=row.getValue("ALL_QTY37")%> ,<%=row.getValue("ALL_QTY38")%>,<%=row.getValue("ALL_QTY39")%>,<%=row.getValue("ALL_QTY40")%>,
                       <%=row.getValue("ALL_QTY41")%>,<%=row.getValue("ALL_QTY42")%>,<%=row.getValue("ALL_QTY43")%>,<%=row.getValue("ALL_QTY44")%>,<%=row.getValue("ALL_QTY45")%>,
                       <%=row.getValue("ALL_QTY46")%>,<%=row.getValue("ALL_QTY47")%> ,<%=row.getValue("ALL_QTY48")%>,<%=row.getValue("ALL_QTY49")%>,<%=row.getValue("ALL_QTY50")%>,
                       <%=row.getValue("ALL_QTY51")%>,<%=row.getValue("ALL_QTY52")%>,<%=row.getValue("ALL_QTY53")%>,<%=row.getValue("ALL_QTY54")%>,<%=row.getValue("ALL_QTY55")%>,
                       <%=row.getValue("ALL_QTY56")%>,<%=row.getValue("ALL_QTY57")%> ,<%=row.getValue("ALL_QTY58")%>,<%=row.getValue("ALL_QTY59")%>,<%=row.getValue("ALL_QTY60")%>
                       ">
                <param name=sampleColors value="#FFCC00,#FF6600,#99CC00,#FF0000,#0000FF,#00FF00">
                 <%
                }
            }
        %>
                X������
                <param name=sampleLabels value="1��, , , , 5��, ,, , , 10�� ,
                , , , , 15��, ,, , , 20��,
                , , , , 25��, ,, , , 30��,
                , , , ,35��,, , , , 40��,
                , , , , 45��,,, , , 50��,
                , , , , 55��,,, , , 60��,
                ">
                <%--<param name=sampleLabels value="1��, 2��, 3��, 4��, 5��, 6��,7��, 8��, 9��, 10�� ,
                11��, 12��, 13��, 14��, 15��, 16��,17��, 18��, 19��, 20��,
                21��, 22��, 23��, 24��, 25��, 26��,27��, 28��, 29��, 30��,
                31��, 32��, 33��, 34��,35��, 36��,37��, 38��, 39��, 40��,
                41��, 42��, 43��, 44��, 45��, 46��,47��, 48��, 49��, 50��,
                51��, 52��, 53��, 54��, 55��, 56��,57��, 58��, 59��, 60��,
                ">--%>
                <param name=sampleLabelsOn value=true>
                <param name=sampleLabelStyle value=below>
                <param name=sampleLabelFont value="����,bold,12">


                <!--y������-->
                <!--param name=range value=30000-->
                <param name=rangeLabelFont value="����,bold,12">


                <!--ͼ������-->
                <param name=legendOn value=true>
                <param name=legendLabels value="�ƶ�ͨ����,�����ù̶��ʲ�,���ù̶��ʲ�,����̶��ʲ�,δʹ�ù̶��ʲ�,�����ù̶��ʲ�,����,�����ʲ�,��ֵ�׺�Ʒ,���ڴ�̯����,��̯����,�ǻ����Ը����ʲ�">
                <param name=legendFont value="����,bold,11">
                <param name=valueLabelsOn value=false>
                <param name=valueLabelFont value="����,bold,11">
                <param name=legendPosition value=right>
                <!--������������-->
                <param name=sampleScrollerOn value="true">
                <param name=rangeAdjusterOn value="true">
                <param name=rangeAdjusterPosition value="left">
                <param name=visibleSamples value="0,60">
            </applet>
        </td>
    </tr>

    </table>
</body>
</html>
<script type="text/javascript">
    function do_search() {
        //        document.getElementById("$$$waitTipMsg$$$").style.visibility = "visible";
        mainForm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
        mainForm.submit();
    }
    function do_export() {
        mainForm.act.value = "<%=WebActionConstant.EXPORT_ACTION%>";
        mainForm.submit();
    }
    function do_drop() {
        var transType = document.getElementById("transType")   ;
        var transStatus = document.getElementById("transStatus");
        dropSpecialOption(transType, 'BJBFS');
        //        dropSpecialOption(transStatus, 'APPROVED');
    }
    function do_ShowDetail(transId, transType) {
        mainForm.transId.value = transId;
        mainForm.transType.value = transType;
        var url = "/servlet/com.sino.ams.spare.query.servlet.AmsBjTransQueryServlet?act=<%=WebActionConstant.DETAIL_ACTION%>&transId=" + transId + "&transType=" + transType;
        var popscript = "width=1020,height=700,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes";
        window.open(url, "instrum", popscript);
    }
</script>
