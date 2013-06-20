package com.sino.foundation.exception;

import com.sino.base.log.Logger;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>@todo���ڴ˼��뱾������������</p>
 * <p>Copyright: ����˼ŵ����Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class SinoRuntimeException extends RuntimeException {

	public SinoRuntimeException(Throwable cause) {
		super(cause.getMessage());
	}

	public SinoRuntimeException() {
		super();
	}

	public SinoRuntimeException(String msg) {
		super(msg);
	}

	/**
	 * ���ܣ�����־�����ļ������÷�ʽ��¼�쳣��Ϣ
	 */
	public void printLog() {
		Logger.logError(this);
	}
}