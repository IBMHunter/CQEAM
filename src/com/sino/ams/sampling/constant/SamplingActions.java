package com.sino.ams.sampling.constant;

import com.sino.base.constant.web.WebActionConstant;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface SamplingActions extends WebActionConstant {
	String OPEN_TASK = "OPEN_TASK";//������
	String CLOSE_TASK = "CLOSE_TASK";//�ر�����
	String PUBLISH_TASK = "PUBLISH_TASK";//��������
	String CLOSE_SELECTED_TASK = "CLOSE_SELECTED_TASK";//�ر�ѡ��Ķ�������еĿ����е�����
	String OPEN_SELECTED_TASK = "OPEN_SELECTED_TASK";//��ѡ��Ķ�������е��ѹر��е�����
	String CANCEL_SELECTED_TASK = "CANCEL_SELECTED_TASK";//ȡ��ѡ��Ķ�������е��ݴ������
	String PUBLISH_SELECTED_TASK = "PUBLISH_SELECTED_TASK";//����ѡ��Ķ���������ݴ������

	String DISTRIBUTE_ORDER = "DISTRIBUTE_ORDER";//�·�����
}
