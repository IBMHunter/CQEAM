package com.sino.foundation.config;

/**
* <p>Title: SinoApplication</p>
* <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
* <p>@todo����ʱλ�ڸð��£�����ʵ����Ŀ����֤֮�󣬽����������⣬��ȡ��Ŀǰ���������ù���</p>
* <p>Copyright: ����˼ŵ����Ȩ����Copyright (c) 2003~2008��
* <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
* <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
* @author ����ʤ
* @version 0.1
 */
public interface ConfigLoadedResult {

	/**
	 * �ͷű������ݡ�������ͨ���ɶ�Ӧ��ConfigLoader���ء�
	 * <B>ʵ��Ϊ��ʼ��ʱ��״̬</B>
	 */
	public void releaseData();
}
