package com.sino.pda.dao;

import com.sino.base.exception.QueryException;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface OrderDownLoadInterface {

	/**
	 * ���ܣ���ȡ��������XML�ļ��ַ���
	 * @return StringBuffer
	 * @throws QueryException
	 */
	public StringBuffer getOrderXml() throws QueryException;
}
