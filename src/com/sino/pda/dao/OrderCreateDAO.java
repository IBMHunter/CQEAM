package com.sino.pda.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.XMLParseException;
import com.sino.base.log.Logger;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.framework.security.dto.ServletConfigDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class OrderCreateDAO {
	protected Connection conn = null;
	protected FilterConfigDTO filterConfig = null;
	protected String filePath = "";
	protected SfUserDTO userAccount = null;


	protected String orderNo = "";
	protected ServletConfigDTO servletConfig = null;
	protected AMSSQLProducer sqlProducer = null;
	protected DTO orderParameter = null;

	public OrderCreateDAO(Connection conn, FilterConfigDTO filterConfig, String filePath) {
		this.conn = conn;
		this.filterConfig = filterConfig;
		this.filePath = filePath;
		try {
			initOrderData();
		} catch (XMLParseException ex) {
			ex.printLog();
		}
	}

	public void setOrderData(DTO orderParameter) {
		this.orderParameter = orderParameter;
		if(sqlProducer != null){
			sqlProducer.setDTOParameter(orderParameter);
		}
	}

	/**
	 * ���ܣ�����servletConfig
	 * @param servletConfig ServletConfigDTO
	 */
	public void setServletConfig(ServletConfigDTO servletConfig) {
		this.servletConfig = servletConfig;
	}

	/**
	 * ���ܣ��ɴ����XML�ļ��������ʼ����س���
	 * @throws XMLParseException
	 */
	protected abstract void initOrderData() throws XMLParseException;


	/**
	 * ���ܣ��ж�ָ���ص���Ƿ��Ѿ�����δ�鵵������
	 * <B>�����̵㹤������Ҫ�˹��鵵</B>
	 * <B>���ڳ�鹤������������ʱ���Զ��鵵</B>
	 * @return boolean
	 */
	public abstract boolean hasPreviousOrder();

	/**
	 * ���ܣ�����������
	 * @return String �ɹ��򷵻ش��������ı�ţ����򷵻ؿ��ַ���
	 */
	public String createOrder() {
		boolean operateResult = false;
		boolean autoCommit = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			createOrderBatch();
			createOrderHeader();
			createOrderLine();
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					orderNo = "";
					conn.rollback();
				} else {
					conn.commit();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
		return orderNo;
	}


	/**
	 * ���ܣ�����������
	 * @throws DataHandleException
	 */
	protected abstract void createOrderBatch() throws DataHandleException;


	/**
	 * ���ܣ���������ͷ
	 * @throws DataHandleException
	 */
	protected abstract void createOrderHeader() throws DataHandleException;

	/**
	 * ���ܣ����������豸��
	 * @throws DataHandleException
	 */
	protected abstract void createOrderLine() throws DataHandleException;
}
