package com.sino.ams.sampling.constant;

import com.sino.ams.newasset.constant.AssetsWebAttributes;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface SamplingWebAttributes extends AssetsWebAttributes {
	String TASK_DTO = "TASK_DTO";
	String BATCH_DTO = "BATCH_DTO";
	String ORDER_DTO = "ORDER_DTO";
	String ORDER_HEADERS = "ORDER_HEADERS";//��������ҳ�棬�����Զ��е���ʽ��ʾ
	String ORDER_LINES = "ORDER_LINES";//�����޸�ҳ�棬�����豸�Ķ�����ʾ
	String TASK_TREE = "TASK_TREE";//���������չʾ
	String NO_TASK_REMARK = "��δ��������������ȴ�������";
	String WEB_WAIT_TIP = "<div id=\"$$$disableMsg$$$\" style=\"position:absolute;bottom:0px;top:0px;left:0px;right:0px;z-index:10;visibility:hidden;width:100%;height:100%\">"
						  + "<table width=\"100%\" height=\"100%\" style=\"background-color:#FFFFFF;filter:progid:DXImageTransform.Microsoft.Alpha(opacity=50,finishOpacity=50,style=2)\">"
						  + "<tr>"
						  + "<td colspan=\"3\"></td>"
						  + "</tr>"
						  + "<tr>"
						  + "<td width=\"30%\"></td>"
						  + "<td bgcolor=\"#ff9900\"  height=\"60\">"
						  + "<table width=\"100%\" height=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\">"
						  + "<tr>"
						  + "<td bgcolor=\"#FFFFCC\" align=\"center\"><font color=\"#008000\" size=\"2\">�����ύ���ݣ����Ժ�......</font><img src=\"/images/wait.gif\" alt=\"\"></td>"
						  + "</tr>"
						  + "</table>"
						  + "</td>"
						  + "<td width=\"30%\"></td>"
						  + "</tr>"
						  + "<tr>"
						  + "<td colspan=\"3\"></td>"
						  + "</tr>"
						  + "</table>"
						  + "</div>";
}
