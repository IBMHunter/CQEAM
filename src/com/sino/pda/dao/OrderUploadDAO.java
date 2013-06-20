package com.sino.pda.dao;

import java.sql.Connection;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTOSet;
import com.sino.framework.security.dto.ServletConfigDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class OrderUploadDAO {
	protected SfUserDTO userAccount = null;
	protected Connection conn = null;
	protected AMSSQLProducer lineProducer = null;//��Թ����н������ݴ����SQL
	protected DTOSet orderBarcodes = null; //������ԭ���ʲ�

	protected ServletConfigDTO servletConfig = null;

	public OrderUploadDAO(SfUserDTO userAccount, Connection conn) {
		this.userAccount = userAccount;
		this.conn = conn;
		orderBarcodes = new DTOSet();
		initUploadApp();
	}

	public void setServletConfig(ServletConfigDTO servletConfig){
		this.servletConfig = servletConfig;
	}

	/**
	 * ���ܣ�����������ʼ������
	 * <B>�������า��</B>
	 */
	protected void initUploadApp(){

	}

	/**
	 * ���ܣ��������ص�xml�ļ������������ݵ����ݿ�
	 * @param uploadFile String
	 * @return boolean
	 */
	public abstract boolean uploadOrders(String uploadFile);
}
