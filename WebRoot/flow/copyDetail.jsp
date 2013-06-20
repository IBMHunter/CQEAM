<%@ page contentType="text/html; charset=GBK" language="java" errorPage=""%>
<%@ page import="com.sino.base.constant.db.QueryConstant"%>
<%@ page import="com.sino.base.util.StrUtil"%>
<%@ page import="com.sino.sinoflow.constant.WebAttrConstant"%>
<%@ page import="com.sino.sinoflow.dto.SfActCopyDTO" %>
<%
	SfActCopyDTO copy = (SfActCopyDTO)request.getAttribute(WebAttrConstant.SF_ACT_COPY_INFO);
%>
<html>
	<head>
		<title>���ͼ���ϸ��ʾҳ��</title>
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/style.css">
		<link rel="stylesheet" type="text/css" href="/WebLibary/css/selectMenu.css">
		<script type="text/javascript" src="/WebLibary/js/selectMenu.js"></script>
		<script type="text/javascript" src="/WebLibary/js/ajax.js"></script>
		<script type="text/javascript" src="/WebLibary/js/lxOperation.js"></script>
		<script type="text/javascript" src="/WebLibary/js/clientRowSet.js"></script>
		<script type="text/javascript" src="/WebLibary/js/RadioProcess.js"></script>
		<script type="text/javascript" src="/WebLibary/js/SelectProcess.js"></script>
		<script type="text/javascript" src="/WebLibary/js/CommonUtil.js"></script>
		<script type="text/javascript" src="/WebLibary/js/Constant.js"></script>
		<script type="text/javascript" src="/WebLibary/js/FormValidate.js"></script>
		<script type="text/javascript" src="/WebLibary/js/DateProcess.js"></script>
		<script type="text/javascript" src="/WebLibary/js/calendar.js"></script>
        <script type="text/javascript" src="/WebLibary/js/printToolBar.js"></script>
    </head>
	<body>
		<jsp:include page="/message/MessageProcess" flush="true" />
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td background="/images/bd4.gif">
					&nbsp;
				</td>
				<td align="center" valign="top" bgcolor="#cedeee" style="border: 1px solid #434948; background-color: #FFFFFF">
					<!-- *********************************************�����ʽ************************************************** -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center">
								<table width="99%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="8" height="8" style="background: url(/images/fr1.gif) right bottom no-repeat;">
											<img src="/images/blank.gif" width="8" height="8">
										</td>
										<td style="background: url(/images/fr2.gif) bottom repeat-x;">
											<img src="/images/blank.gif" width="1" height="1">
										</td>
										<td width="8" height="8" style="background: url(/images/fr3.gif) bottom left no-repeat;">
											<img src="/images/blank.gif" width="8" height="8">
										</td>
									</tr>
									<tr>
										<td style="background: url(/images/fr4.gif) right repeat-y">
											&nbsp;
										</td>
										<td >
                                        <script type="text/javascript">
                                        	function printTool(){
                                                var ArrAction = new Array("<font style='font-size: 15;'>�ر�</font>", "action_complete.gif", "readCopy","c_t");
                                                var ArrAction2 = new Array("<font style='font-size: 15;'>�رղ�ɾ����������</font>", "action_cancel.gif", "disableCopy", "");
                                                var toolBar = new SinoPrintToolBar();
                                                toolBar.SinoActions = new Array(ArrAction, ArrAction2);
                                                toolBar.imagePath = "../images/buttonbar/";
                                                toolBar.titleStr = "����";
                                                toolBar.printNoTitle();
                                            }
                                            printTool();
                                         </script>
											<!-- ***************************************�ڲ���ʽ******************************************** -->
											<table width="100%" border="0" >
												<tr style="height: 14px;">
													<td colspan="5"></td>
												</tr>
												<tr style="height: 23px;">
													<td align="right"><font style="font-size: 15;"><b>���͸�<%=copy.getToUserName()%></b>:</font></td>
													<td colspan="3" align="right">&nbsp;&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr style="height: 7px;">
													<td colspan="5"></td>
												</tr>
												<tr style="height: 23px;">
													<td width="20%" align="right"><font style="font-size: 15;">��&nbsp;&nbsp;��:</font></td>
													<td width="68%" colspan="3" class="content" nowrap="nowrap">
														<input type="text" readonly name="sfactProcName" class="xfNoBorderAllCL" value="<%=copy.getSfactProcName()%> : <%=copy.getSfactTaskName()%>" style="width:50%" />
													</td>
													<td width="12%">&nbsp;</td>
												</tr>
                                                <tr style="height: 23px;">
                                                    <td width="20%" align="right"><font style="font-size: 15;">ԭ&nbsp;&nbsp;��:</font></td>
                                                    <td width="68%" colspan="3" class="content" nowrap="nowrap">
                                                        <input type="text" readonly name="reason" class="xfNoBorderAllCL" value="<%=copy.getCopyReason()%>" style="width:50%" />
                                                    </td>
                                                    <td width="12%">&nbsp;</td>
                                                </tr>
												<tr style="height: 23px;">
													<td align="right"><font style="font-size: 15;">�ؼ���:</font></td>
													<td colspan="3" class="content">
														<input type="text" readonly name="sfactProcName" class="xfNoBorderAllCL" value="<%=copy.getSfactApplColumn1()%>" style="width: 50%" />
													</td>
													<td>&nbsp;</td>
												</tr>
												<tr style="height: 23px;">
													<td align="right"><font style="font-size: 15;">��&nbsp;&nbsp;��:</font></td>
													<td colspan="3" class="content">
														<input type="text" readonly name="sfactProcName" class="xfNoBorderAllCL" value="<%=copy.getSfactApplColumn2()%>" style="width: 50%" />
														<font style="font-size: 15;">֮�ļ�</font>
													</td>
													<td>&nbsp;</td>
												</tr>
												<%
													if(!copy.getUserMsg().equals("")){
												%>
												<tr style="height: 23px;">
													<td align="right" valign="top" ><font style="font-size: 15;">������Ϣ:</font></td>
													<td colspan="3">
														<textarea name="caseContent" rows="10" style="width: 100%" readonly><%=copy.getUserMsg()%></textarea>
													</td>
													<td>&nbsp;</td>
												</tr>
												<%
													}else{
												%>
												<tr style="height: 170px;">
													<td align="right">
														&nbsp;
													</td>
													<td colspan="3">
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<%
													}
												%>
												<tr style="height: 4px;">
													<td colspan="5"></td>
												</tr>
												<tr style="height: 23px;">
													<td align="right">
														<font style="font-size: 15;">������:</font>
													</td>
													<td colspan="3" class="caption">
														<font class="content"><%=copy.getFromUserName()%></font>&nbsp;&nbsp;&nbsp;
													</td>
													<td>
														<input type="button" name="btn" class="xfBtn" style="size:15;" onclick="OpenApplication('<%=copy.getCopyId()%>');" style="cursor: pointer;" value="�򿪰���" />
													</td>
												</tr>
											</table>
											<!--�ڲ���ʽ***************************************************************************************************** -->
										</td>
										<td style="background: url(/images/fr5.gif) left repeat-y;">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td style="background: url(/images/fr6.gif) top right no-repeat">
											<img src="/images/blank.gif" width="8" height="8">
										</td>
										<td style="background: url(/images/fr7.gif) top repeat-x">
											<img src="/images/blank.gif" width="1" height="1">
										</td>
										<td style="background: url(/images/fr8.gif) top left no-repeat;">
											<img src="/images/blank.gif" width="8" height="8">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<div><%=StrUtil.nullToString(request
							.getAttribute(QueryConstant.SPLIT_PAGE_HTML))%></div>
					<!-- ***************************************************************************************************** -->
				</td>
				<td background="/images/bd4.gif">
					&nbsp;
				</td>
			</tr>
			
		</table>
	</body>
	<script language=javascript>
      	function OpenApplication(copyId) {
/*
            window.close();
        	var caselink = "/servlet/com.sino.sinoflow.servlet.ProcessCopy?sf_copyID='"+ copyId + "'";
            h = window.screen.height;
            w = window.screen.width;
            f1 = "top=0,left=0,width=" + w + ",height=" + h + ",scroll=yes,resizable=yes";
            window.open(caselink,"", f1);
*/
              document.location.href = "/servlet/com.sino.sinoflow.servlet.ProcessCopy?sf_copyID='"+ copyId + "'";
        }

        function closeWindow(){
			window.close();
			window.opener.document.location.href=window.opener.document.location; 
		}

          var ajaxReturn = "";
        function readCopy() {
            var url;
            url = "/servlet/com.sino.sinoflow.servlet.CopyRead?id="
                    + "" + <%=copy.getCopyId()%>;
            var popscript;
            popscript = "dialogWidth:200px"
                    + ";dialogHeight:120px"
                    + ";center:yes;status:no;scroll:no;help:no";
            makeRequest(url, ajaxFunction);
            closeWindow();
        }

          function disableCopy() {
              var url;
              url = "/servlet/com.sino.sinoflow.servlet.CopyDisabled?id="
                      + "" + <%=copy.getCopyId()%>;
              var popscript;
              popscript = "dialogWidth:200px"
                      + ";dialogHeight:120px"
                      + ";center:yes;status:no;scroll:no;help:no";
              makeRequest(url, ajaxFunction);
              if(ajaxReturn.indexOf("Success") >= 0) {
                  closeWindow();
              } else {
                  alert("ɾ������,���������Ƿ�������!");
              }
          }

          function makeRequest(url, alertContents) {

              http_request = false;

              if (window.XMLHttpRequest) { // Mozilla, Safari,...
                  http_request = new XMLHttpRequest();
                  if (http_request.overrideMimeType) {
                      http_request.overrideMimeType('text/xml');
                  }
              } else if (window.ActiveXObject) { // IE
                  try {
                      http_request = new ActiveXObject("Msxml2.XMLHTTP");
                  } catch (e) {
                      try {
                          http_request = new ActiveXObject("Microsoft.XMLHTTP");
                      } catch (e) {}
                  }
              }

              if (!http_request) {
                  alert('�������� xml http ����');
                  return false;
              }
              http_request.onreadystatechange = alertContents;
              http_request.open('POST', url, false);
              http_request.send(null);
              return true;
          }

          function ajaxFunction() {
              try {
                  if (http_request.readyState == 4 || http_request.readyState == "complete") {
                      var resText = http_request.responseText;
                      if(resText.indexOf("404") >= 0) {
                          alert("�Ҳ���ǩ�� servlet����֪ͨϵͳ����Ա��");
                          ajaxReturn = "";
                          return;
                      }
                      if (resText.indexOf("ERROR") >= 0) {
                          var res;
                          eval("res = " + resText);
                          alert(res[0].message);
                          ajaxReturn = "";
                          return;
                      }
                      ajaxReturn = resText;
                 }
              } catch(e) {
                  alert("���������ݴ�����֪ͨϵͳ����Ա��" + e.message);
                  ajaxReturn = "";
              }
          }

    </script>
</html>
