<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ page import="com.sino.base.constant.web.WebActionConstant"%>
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%@ page import="com.sino.ams.newasset.report.dto.ReportInDataDTO"%>
<%@ page import="com.sino.ams.newasset.constant.AssetsWebAttributes"%>
<%@ page import="com.sino.base.util.StrUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: su
  Date: 2009-5-14
  Time: 20:30:51
  To change this template use File | Settings | File Templates.
--%>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>��������ͳһ¼����ϸ��Ϣ</title>
    <link rel="stylesheet" type="text/css" href="/WebLibary/css/main.css">
    <script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
    <script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
    <script type="text/javascript" src="/WebLibary/js/FormProcess.js"></script>
    <script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
    <script language="javascript" src="/style/js/calendar.js"></script>
    
<style type="text/css">
        <!--
        .STYLE1 {
            color: #0033FF
        }
        -->
</style>
</head>
<body onload="showPeriod();">
 <jsp:include page="/message/MessageProcess"/>
<%
    ReportInDataDTO dto = (ReportInDataDTO)request.getAttribute(QueryConstant.QUERY_DTO);
    String managerGuideType = dto.getManagerGuideType();
    String action = dto.getAct();
%>
<form name="mainFrm" method="POST">
	<input type="hidden" name="isKpi" value="<%=dto.getKpi()%>">
<fieldset style="margin-left:0;height:450px">
<%
    if (managerGuideType.equals("TRUN_RATE")) {
%>
       <legend><span class="STYLE1">ת����ά��ҳ��</span></legend>
<%
    } else if (managerGuideType.equals("IN_TIME_RATE")) { 
%>
       <legend><span class="STYLE1">�ʲ�����ϵͳ���߷��������ϱ���ʱ��ά��ҳ��</span></legend>
<%
    } else if (managerGuideType.equals("NICETY_RATE")) {
%>
       <legend><span class="STYLE1">ת����Ϣ׼ȷ��ά��ҳ��</span></legend>
<%
    } else if (managerGuideType.equals("CHECK_RATE")) {
%>
       <legend><span class="STYLE1">�ʲ�ʵ����������������ά��ҳ��</span></legend>
<%
    } else if (managerGuideType.equals("MATCH_CASE_RATE")) {
%>
       <legend><span class="STYLE1">����̵��ʲ���ʵ�����ά��ҳ��</span></legend>
<%
    } else if (managerGuideType.equals("COP_RATE")) {
%>
       <legend><span class="STYLE1">�ճ�Ѳ���ʲ��̵������</span></legend>
<%
    } else if (managerGuideType.equals("COP_MATCH_RATE")) {
%>
       <legend><span class="STYLE1">�ճ�Ѳ���ʲ��̵���ʵ�����</span></legend>
<%
    } else if (managerGuideType.equals("ACCOUNTING_ACCURATE")) {
%>
       <legend><span class="STYLE1">�ʲ�����׼ȷ��</span></legend>
<%
    } else if (managerGuideType.equals("RETURN_RATE")) {
%>
        <legend><span class="STYLE1">�̶��ʲ��ر���ά��ҳ��</span></legend>
<%
    } else if (managerGuideType.equals("TURNOVER_RATE")) {
%>
         <legend><span class="STYLE1">��ת��ά��ҳ��</span></legend>
<%
    } else if (managerGuideType.equals("FEYOFAI_RATE")) {
%>
        <legend><span class="STYLE1">ÿ��Ԫ�̶��ʲ����루��ȣ�ά��ҳ��</span></legend>
<%
   	} else if (managerGuideType.equals("FEYOFACCT_RATE")) {
%>
        <legend><span class="STYLE1">ÿ��Ԫ�̶��ʲ����ؼƷ�ʱ������ȣ�ά��ҳ��</span></legend>
<%
   	} else if (managerGuideType.equals("FEOFATTP_RATE")) {
%>
        <legend><span class="STYLE1">ÿ��Ԫ�̶��ʲ���������ȣ�ά��ҳ��</span></legend>
<%
   	}  else if (managerGuideType.equals("FEYOFATPA_RATE")) {
%>
        <legend><span class="STYLE1">ÿ��Ԫ�̶��ʲ������ܶ��ȣ�ά��ҳ��</span></legend>
<%
   	} else if (managerGuideType.equals("ECCCT_RATE")) {
%>
        <legend><span class="STYLE1">ÿ�ŵ����ؼƷ�ʱ������ȣ�ά��ҳ��</span></legend>
<%
   	} else if (managerGuideType.equals("EAMSDARTR_RATE")) {
%>
        <legend><span class="STYLE1">EAMϵͳ���߷��������ϱ���ʱ��ά��ҳ��</span></legend>
<%
   	} else if (managerGuideType.equals("IATTCFALOAR_RATE")) {
%>
        <legend><span class="STYLE1">����Ƶ���ʲ��̿��ʣ���ֵͳ�ƣ�ά��ҳ��</span></legend>
<%
   	} else if (managerGuideType.equals("IATTCFALOFS_RATE")) {
%>
        <legend><span class="STYLE1">����Ƶ���ʲ��̿��ʣ�����ͳ�ƣ�ά��ҳ��</span></legend>
<%
   	} else if (managerGuideType.equals("AISRS_RATE")) {
%>
        <legend><span class="STYLE1">�ʲ��̿��ʣ�����ͳ�ƣ�ά��ҳ��</span></legend>
<%
   	} else if (managerGuideType.equals("AISRVS_RATE")) {
%>
        <legend><span class="STYLE1">�ʲ��̿��ʣ���ֵͳ�ƣ�ά��ҳ��</span></legend>
<%
   	}else if (dto.getKpi()){
%>
        <legend><span class="STYLE1"><%=dto.getKpiName()%></span></legend>
<%
    }
%>
    <table width="50%" align="center">
    	<tr>
    		<td width="10%" align="right" height="22"> ����ڼ䣺</td>
    		<input id="period1" name="period1" type="hidden" value="<%=dto.getPeriod()%>"/>
			<td width="60%" align="left" height="22">
				<select fieldLabel="����ڼ�" name="period" class="noEmptyInput" id="period" style="width:60%;text-align:">
					<option value="">----��ѡ��----</option>
					<option value="201201">2012-01</option>
					<option value="201202">2012-02</option>
					<option value="201203">2012-03</option>
					<option value="201204">2012-04</option>
					<option value="201205">2012-05</option>
					<option value="201206">2012-06</option>
					<option value="201207">2012-07</option>
					<option value="201208">2012-08</option>
					<option value="201209">2012-09</option>
					<option value="201210">2012-10</option>
					<option value="201211">2012-11</option>
					<option value="201212">2012-12</option>
					<option value="201301">2013-01</option>
					<option value="201302">2013-02</option>
					<option value="201303">2013-03</option>
					<option value="201304">2013-04</option>
					<option value="201305">2013-05</option>
					<option value="201306">2013-06</option>
					<option value="201307">2013-07</option>
					<option value="201308">2013-08</option>
					<option value="201309">2013-09</option>
					<option value="201310">2013-10</option>
					<option value="201311">2013-11</option>
					<option value="201312">2013-12</option>
					<option value="201401">2014-01</option>
					<option value="201402">2014-02</option>
					<option value="201403">2014-03</option>
					<option value="201404">2014-04</option>
					<option value="201405">2014-05</option>
					<option value="201406">2014-06</option>
					<option value="201407">2014-07</option>
					<option value="201408">2014-08</option>
					<option value="201409">2014-09</option>
					<option value="201410">2014-10</option>
					<option value="201411">2014-11</option>
					<option value="201412">2014-12</option>
					<option value="201501">2015-01</option>
					<option value="201502">2015-02</option>
					<option value="201503">2015-03</option>
					<option value="201504">2015-04</option>
					<option value="201505">2015-05</option>
					<option value="201506">2015-06</option>
					<option value="201507">2015-07</option>
					<option value="201508">2015-08</option>
					<option value="201509">2015-09</option>
					<option value="201510">2015-10</option>
					<option value="201511">2015-11</option>
					<option value="201512">2015-12</option>
					<option value="201601">2016-01</option>
					<option value="201602">2016-02</option>
					<option value="201603">2016-03</option>
					<option value="201604">2016-04</option>
					<option value="201605">2016-05</option>
					<option value="201606">2016-06</option>
					<option value="201607">2016-07</option>
					<option value="201608">2016-08</option>
					<option value="201609">2016-09</option>
					<option value="201610">2016-10</option>
					<option value="201611">2016-11</option>
					<option value="201612">2016-12</option>
				</select>
			</td>
    	</tr>
<%
    if (managerGuideType.equals("TRUN_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">�������ڹ���ת�ʶ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="projectTrunAssets" class="noEmptyInput" style="width:60%" value="<%=dto.getProjectTrunAssets()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">�����ۼ�Ͷ���ܶ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="projectSumAssets" class="noEmptyInput" style="width:60%"  value="<%=dto.getProjectSumAssets()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("IN_TIME_RATE")) {
%>
        <tr>
            <td width="17%" align="right" height="22">δ��ʱ�ϱ�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="noTimelyReportNum" class="noEmptyInput" style="width:60%" value="<%=dto.getNoTimelyReportNum()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">������Ӧ�ϱ�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="assetsmentReportNum" class="noEmptyInput" style="width:60%"  value="<%=dto.getAssetsmentReportNum()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("NICETY_RATE")) {
%>
        <tr>
            <td width="30%" align="right" height="22">�������ڷ�����ת���ʲ���׼ȷ��������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="assetsmentFalseNum" class="noEmptyInput" style="width:60%" value="<%=dto.getAssetsmentFalseNum()%>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="right" height="22">��������ת���ʲ�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="assetsmentAssetsSum" class="noEmptyInput" style="width:60%"  value="<%=dto.getAssetsmentAssetsSum()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("CHECK_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ʵ����ɵ��ʲ�ʵ��������̵����񹤵�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="completeCheckNum" class="noEmptyInput" style="width:60%" value="<%=dto.getCompleteCheckNum()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">�ƻ��涨���ʲ�����̵����񹤵�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="planCheckNum" class="noEmptyInput" style="width:60%"  value="<%=dto.getPlanCheckNum()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("MATCH_CASE_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�������ʵ������ʲ�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="accountMatchCase" class="noEmptyInput" style="width:60%" value="<%=dto.getAccountMatchCase()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">����ʲ���������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="checkAssetsSum" class="noEmptyInput" style="width:60%"  value="<%=dto.getCheckAssetsSum()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("COP_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">����ɵ��ճ�Ѳ���̵�Ĺ�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="assetsCopNum" class="noEmptyInput" style="width:60%" value="<%=dto.getAssetsCopNum()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">�ƻ����ճ�Ѳ���̵㹤��������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="assetsCopSum" class="noEmptyInput" style="width:60%"  value="<%=dto.getAssetsCopSum()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("COP_MATCH_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�̵�����ʵ������ʲ�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="assetsMatchCase" class="noEmptyInput" style="width:60%" value="<%=dto.getAssetsMatchCase()%>">
            </td>
        </tr>
        <tr>
            <td width="17%" align="right" height="22">�̵��ʲ���������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="assetsCheckSum" class="noEmptyInput" style="width:60%"  value="<%=dto.getAssetsCheckSum()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("ACCOUNTING_ACCURATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">�ʲ�������صĲ�������</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="accurateErrorNumber" class="noEmptyInput" style="width:60%" value="<%=dto.getAccurateErrorNumber()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("RETURN_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ������</td>
            <td width="60%" align="left" height="22">
                <textarea   class="noEmptyInput" style="width:60%"   disabled="disabled">���ڳ��̶ܹ��ʲ�����+��ĩ�̶ܹ��ʲ����/2</textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("TURNOVER_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ������</td>
            <td width="60%" align="left" height="22">
                <textarea class="noEmptyInput" style="width:60%"  disabled="disabled">���ڳ��̶ܹ��ʲ�����+��ĩ�̶ܹ��ʲ����/2</textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
        
<%
    } else if (managerGuideType.equals("FEYOFAI_RATE")) {
%>
        <tr>
            <td width="25%" align="right" height="22">ÿ��Ԫ�̶��ʲ����루��ȣ���</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("FEYOFACCT_RATE")) {
%>
        <tr>
            <td width="25%" align="right" height="22">ÿ��Ԫ�̶��ʲ����ؼƷ�ʱ������ȣ���</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("FEOFATTP_RATE")) {
%>
        <tr>
            <td width="25%" align="right" height="22">ÿ��Ԫ�̶��ʲ���������ȣ���</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("FEYOFATPA_RATE")) {
%>
        <tr>
            <td width="25%" align="right" height="22">ÿ��Ԫ�̶��ʲ������ܶ��ȣ���</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("ECCCT_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="25%" align="right" height="22">ÿ�ŵ����ؼƷ�ʱ������ȣ���</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("EAMSDARTR_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ������</td>
            <td width="60%" align="left" height="22">
                <textarea  class="noEmptyInput" style="width:60%" disabled="disabled">��ʱ�ϱ�����/������Ӧ�ϱ�������100��</textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("AISRS_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
          <tr>
            <td width="30%" align="right" height="22">ֵ������</td>
            <td width="60%" align="left" height="22">
                <textarea  class="noEmptyInput" style="width:60%" disabled="disabled">ϵͳ�Զ�������̿��ʲ��Ŀ�Ƭ����/�ʲ���Ƭ��������100��</textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
        
<%
    } else if (managerGuideType.equals("AISRVS_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
         <tr>
            <td width="30%" align="right" height="22">ֵ������</td>
            <td width="60%" align="left" height="22">
                <textarea  class="noEmptyInput" style="width:60%"  disabled="disabled">�̵�����ʵ������ʲ�ԭֵ/�̵��ʲ�ԭֵ��100��</textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("IATTCFALOAR_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ������</td>
            <td width="60%" align="left" height="22">
                <textarea  class="noEmptyInput" style="width:60%" disabled="disabled">�̵�����ʵ������ʲ�����������Ƶ��ԭֵ/�̵��ʲ�����������Ƶ��ԭֵ��100��</textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (managerGuideType.equals("IATTCFALOFS_RATE")) {
%>
        <tr>
            <td width="10%" align="right" height="22">OU��</td>
            <td width="60%" align="left" height="22">
                <select name="organizationId" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ������</td>
            <td width="60%" align="left" height="22">
                <textarea  class="noEmptyInput" style="width:60%"  disabled="disabled">�̵�����ʵ������ʲ�����������Ƶ����Ƭ����/�̵��ʲ�����������Ƶ����Ƭ��������100��</textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right" height="22">ֵ</td>
            <td width="60%" align="left" height="22">
                <input type="text" name="value" class="noEmptyInput" style="width:60%" value="<%=dto.getValue()%>">
            </td>
        </tr>
<%
    } else if (dto.getKpi()){
%>        <input type="hidden" name="isKpi" value="<%=dto.getKpi()%>">
          <tr>
                <td width="10%" align="right" height="22">OU��</td>
                <td width="60%" align="left" height="22">
                    <select name="companyCode" style="width:60%" class="noEmptyInput"><%=request.getAttribute(AssetsWebAttributes.OU_OPTION)%></select>
                </td>
            </tr>
            <tr>
                <td width="40%" align="right" height="22">��ǰֵ��</td>
                <td width="60%" align="left" height="22">
                    <input type="text" name="curValue" class="noEmptyInput" style="width:60%" value="<%=dto.getCurValue()%>">������ʱ�ķ��ӣ�
                </td>
            </tr>
            <tr>
                <td width="40%" align="right" height="22">��ֵ��</td>
                <td width="60%" align="left" height="22">
                    <input type="text" name="totalValue" class="noEmptyInput" style="width:60%"  value="<%=dto.getTotalValue()%>">������ʱ�ķ�ĸ��
                </td>
            </tr>
<%
    }
%>
        <tr>
            <td width="50%" align="center" height="22" colspan="5">
                <img src="/images/eam_images/save.jpg" alt="����" onClick="do_SaveData();">&nbsp;
<%
    if (!action.equals(WebActionConstant.NEW_ACTION)) {
%>
                <img src="/images/eam_images/delete.jpg" alt="ɾ��" onClick="do_DeleteData(); return false;">&nbsp;
<%
    }
%>
                <img src="/images/eam_images/back.jpg" alt="ȡ��" onClick="do_Back(); return false;"></td>
        </tr>
    </table>
</fieldset>
    <input type="hidden" name="act" value="">
    <input type="hidden" name="reportId" value="<%=dto.getReportId()%>">
    <input type="hidden" name="managerGuideType" value="<%=managerGuideType%>">
</form>
</body>
<iframe width=174 height=189 name="gToday:normal:calendar.js" id="gToday:normal:calendar.js" src="/WebLibary/js/DateHTML.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0;">
</iframe>
</html>
<script>
function do_SaveData() {
	var period = mainFrm.period.value;
	if(period == null || period == ""){
		alert("����ڼ䲻��Ϊ�գ�");
		mainFrm.period.focus();
		return;
	}
	var dates=mainFrm.period.value.split("-",3);
	if (dates.length==3) {
		mainFrm.period.value=dates[0]+dates[1];
	}
    var fieldNames;
    var fieldLabels;
    var managerGuideType = "<%=managerGuideType%>";
    if (managerGuideType == "TRUN_RATE") {
        fieldNames = "organizationId;period;projectTrunAssets;projectSumAssets";
        fieldLabels = "OU;����ڼ�;�������ڹ���ת�ʶ�;�����ۼ�Ͷ���ܶ�";
    } else if (managerGuideType == "IN_TIME_RATE") {
        fieldNames = "period;noTimelyReportNum;assetsmentReportNum";
        fieldLabels = "����ڼ�;δ��ʱ�ϱ�����;������Ӧ�ϱ�����";
    } else if (managerGuideType == "NICETY_RATE") {
        fieldNames = "period;assetsmentFalseNum;assetsmentAssetsSum";
        fieldLabels = "����ڼ�;�������ڷ�����ת���ʲ���׼ȷ������;��������ת���ʲ�����";
    } else if (managerGuideType == "CHECK_RATE") {
        fieldNames = "organizationId;period;completeCheckNum;planCheckNum";
        fieldLabels = "OU;����ڼ�;ʵ����ɵ��ʲ�ʵ��������̵����񹤵�����;�ƻ��涨���ʲ�����̵����񹤵�����";
    } else if (managerGuideType == "MATCH_CASE_RATE") {
        fieldNames = "organizationId;period;accountMatchCase;checkAssetsSum";
        fieldLabels = "OU;����ڼ�;�������ʵ������ʲ�����;����ʲ�������";
    } else if (managerGuideType == "COP_RATE") {
        fieldNames = "organizationId;period;assetsCopNum;assetsCopSum";
        fieldLabels = "OU;����ڼ�;����ɵ��ճ�Ѳ���̵�Ĺ�����;�ƻ����ճ�Ѳ���̵㹤������";
    } else if (managerGuideType == "COP_MATCH_RATE") {
        fieldNames = "organizationId;period;assetsMatchCase;assetsCheckSum";
        fieldLabels = "OU;����ڼ�;�̵�����ʵ������ʲ�����;�̵��ʲ�������";
    } else if (managerGuideType == "ACCOUNTING_ACCURATE") {
        fieldNames = "organizationId;period;accurateErrorNumber";
        fieldLabels = "OU;����ڼ�;�ʲ�������صĲ�����";
    } else if (<%=dto.getKpi()%>) {
        fieldNames = "companyCode;period;curValue;totalValue";
        fieldLabels = "OU;����ڼ�;��ǰֵ������ʱ�ķ��ӣ�;��ֵ������ʱ�ķ�ĸ��";
    }  else if (managerGuideType == "FEYOFATPA_RATE") {
        fieldNames = "period;value";
        fieldLabels = "����ڼ�;ÿ��Ԫ�̶��ʲ������ܶ��ȣ�";
    } else if (managerGuideType == "FEOFATTP_RATE") {
        fieldNames = "period;value";
        fieldLabels = "����ڼ�;ÿ��Ԫ�̶��ʲ���������ȣ�";
    } else if (managerGuideType == "FEYOFACCT_RATE") {
        fieldNames = "period;value";
        fieldLabels = "����ڼ�;ÿ��Ԫ�̶��ʲ����ؼƷ�ʱ������ȣ�";
    } else if (managerGuideType == "FEYOFAI_RATE") {
        fieldNames = "period;value";
        fieldLabels = "����ڼ�;ÿ��Ԫ�̶��ʲ����루���";
    } else {
        fieldNames = "organizationId;period;value";
        fieldLabels = "OU;����ڼ�;ֵ";
    }
    var isValid = formValidate(fieldNames, fieldLabels, EMPTY_VALIDATE);
    if (isValid) {
        if (managerGuideType == "TRUN_RATE") {
            if (!isNumber(mainFrm.projectTrunAssets.value)) {
                alert("���������ڹ���ת�ʶ�������������ͣ�");
                mainFrm.projectTrunAssets.focus();
                return;
            }
            if (!isNumber(mainFrm.projectSumAssets.value)) {
                alert("�������ۼ�Ͷ���ܶ�������������ͣ�");
                mainFrm.projectSumAssets.focus();
                return;
            }
        } else if (managerGuideType == "IN_TIME_RATE") {
            if (!isInt(mainFrm.noTimelyReportNum.value)) {
                alert("��δ��ʱ�ϱ�������������������");
                mainFrm.noTimelyReportNum.focus();
                return;
            }
            if (!isInt(mainFrm.assetsmentReportNum.value)) {
                alert("��������Ӧ�ϱ�������������������");
                mainFrm.assetsmentReportNum.focus();
                return;
            }
        } else if (managerGuideType == "NICETY_RATE") {
            if (!isInt(mainFrm.assetsmentFalseNum.value)) {
                alert("���������ڷ�����ת���ʲ���׼ȷ��������������������");
                mainFrm.assetsmentFalseNum.focus();
                return;
            }
            if (!isInt(mainFrm.assetsmentAssetsSum.value)) {
                alert("����������ת���ʲ�������������������");
                mainFrm.assetsmentAssetsSum.focus();
                return;
            }
        } else if (managerGuideType == "CHECK_RATE") {
            if (!isInt(mainFrm.completeCheckNum.value)) {
                alert("��ʵ����ɵ��ʲ�ʵ��������̵����񹤵�������������������");
                mainFrm.assetsmentFalseNum.focus();
                return;
            }
            if (!isInt(mainFrm.planCheckNum.value)) {
                alert("���ƻ��涨���ʲ�����̵����񹤵�������������������");
                mainFrm.assetsmentAssetsSum.focus();
                return;
            }
        } else if (managerGuideType == "MATCH_CASE_RATE") {
            if (!isInt(mainFrm.accountMatchCase.value)) {
                alert("���������ʵ������ʲ�������������������");
                mainFrm.accountMatchCase.focus();
                return;
            }
            if (!isInt(mainFrm.checkAssetsSum.value)) {
                alert("������ʲ���������������������");
                mainFrm.checkAssetsSum.focus();
                return;
            }
        } else if (managerGuideType == "COP_RATE") {
            if (!isInt(mainFrm.assetsCopNum.value)) {
                alert("������ɵ��ճ�Ѳ���̵�Ĺ�������������������");
                mainFrm.assetsCopNum.focus();
                return;
            }
            if (!isInt(mainFrm.assetsCopSum.value)) {
                alert("���ƻ����ճ�Ѳ���̵㹤��������������������");
                mainFrm.assetsCopSum.focus();
                return;
            }
        } else if (managerGuideType == "COP_MATCH_RATE") {
            if (!isInt(mainFrm.assetsMatchCase.value)) {
                alert("���̵�����ʵ������ʲ�������������������");
                mainFrm.assetsMatchCase.focus();
                return;
            }
            if (!isInt(mainFrm.assetsCheckSum.value)) {
                alert("���̵��ʲ���������������������");
                mainFrm.assetsCheckSum.focus();
                return;
            }
        } else if (managerGuideType == "ACCOUNTING_ACCURATE") {
            if (!isInt(mainFrm.accurateErrorNumber.value)) {
                alert("���ʲ�������صĲ�������������������");
                mainFrm.assetsMatchCase.focus();
                return;
            }
        } else if (<%=dto.getKpi()%>) {
            var curValue = mainFrm.curValue.value;
            var totalValue = mainFrm.totalValue.value;
            var Expression = /^[0-9]*$/;
            var objExp=new RegExp(Expression);
            if(!objExp.test(curValue)){
                alert("����ǰֵ������ʱ�ķ��ӣ������Ϸ�����������������");
                mainFrm.curValue.focus();
                return;
            }
            if (!objExp.test(totalValue)) {
                alert("����ֵ������ʱ�ķ�ĸ�������Ϸ�����������������");
                mainFrm.totalValue.focus();
                return;
            }
            if(parseInt(curValue) > parseInt(totalValue)){
                alert("����ǰֵ������ʱ�ķ��ӣ��� ���ܳ��� ����ֵ������ʱ�ķ�ĸ������ֵ��");
                mainFrm.curValue.focus();
                return;
            }
            if(totalValue == "0"){
               alert("����ֵ������ʱ�ķ�ĸ��������Ϊ�㣡");
                mainFrm.totalValue.value = "";
                mainFrm.totalValue.focus();
                return;
            }
        }else{
        	 if (!isNumber(mainFrm.value.value)) {
                 alert("��ֵ���������������ͣ�");
                 mainFrm.value.focus();
                 return;
             }
        }
    }
    if (isValid) {
		var action = "";
        if (mainFrm.reportId.value == "") {
                action = "<%=WebActionConstant.CREATE_ACTION%>";
        }else{
        	action =  "<%=WebActionConstant.UPDATE_ACTION%>";   
        	//if("<%=dto.getKpi()%>"== "true" && "<%=dto.getCurValue()%>"!= "" && "<%=dto.getTotalValue()%>" != ""){
            //	action = "<%=WebActionConstant.UPDATE_ACTION%>";    
       	 	//}
        }
		mainFrm.act.value = action;
		mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.ReportInDataServlet";
		mainFrm.submit();
	}
}

function do_DeleteData() {
    var reportId = mainFrm.reportId.value;
    if (confirm("ȷ��ɾ���𣿼�����㡰ȷ������ť��������㡰ȡ������ť��")) {
        mainFrm.act.value = "<%=WebActionConstant.DELETE_ACTION%>";
        mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.ReportInDataServlet?reportId=" + reportId;
        mainFrm.submit();
    }
}


function do_Back() {
    var managerGuideType = "<%=managerGuideType%>";
    if (managerGuideType == "TRUN_RATE") {
        mainFrm.organizationId.value = "";
        mainFrm.period.value = "";
        mainFrm.projectTrunAssets.value = "";
        mainFrm.projectSumAssets.value = "";
    } else if (managerGuideType == "IN_TIME_RATE") {
        mainFrm.period.value = "";
        mainFrm.noTimelyReportNum.value = "";
        mainFrm.assetsmentReportNum.value = "";
    } else if (managerGuideType == "NICETY_RATE") {
        mainFrm.period.value = "";
        mainFrm.assetsmentFalseNum.value = "";
        mainFrm.assetsmentAssetsSum.value = "";
    } else if (managerGuideType == "CHECK_RATE") {
        mainFrm.organizationId.value = "";
        mainFrm.period.value = "";
        mainFrm.completeCheckNum.value = "";
        mainFrm.planCheckNum.value = "";
    } else if (managerGuideType == "MATCH_CASE_RATE") {
        mainFrm.organizationId.value = "";
        mainFrm.period.value = "";
        mainFrm.accountMatchCase.value = "";
        mainFrm.checkAssetsSum.value = "";
    } else if (managerGuideType == "COP_RATE") {
        mainFrm.organizationId.value = "";
        mainFrm.period.value = "";
        mainFrm.assetsCopNum.value = "";
        mainFrm.assetsCopSum.value = "";
    } else if (managerGuideType == "COP_MATCH_RATE") {
        mainFrm.organizationId.value = "";
        mainFrm.period.value = "";
        mainFrm.assetsMatchCase.value = "";
        mainFrm.assetsCheckSum.value = "";
    } else if (<%=dto.getKpi()%>) {
//        mainFrm.companyCode.value = "";
//        mainFrm.period.value = "";
        mainFrm.curValue.value = "";
        mainFrm.totalValue.value = "";
    }

    mainFrm.act.value = "<%=WebActionConstant.QUERY_ACTION%>";
	mainFrm.action = "/servlet/com.sino.ams.newasset.report.servlet.ReportInDataServlet";
	mainFrm.submit();
}
function showPeriod(){
	if(document.getElementById("period1").value!=""){
		document.getElementById("period").value=document.getElementById("period1").value
	}
}
</script>