package com.sino.hn.sms.service;

import java.sql.Connection;

import com.sino.base.config.SMSConfig;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.PoolException;
import com.sino.base.log.Logger;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.sms.service.SMSMsgCreate;
 
/**
 * 
 * @ϵͳ����: 
 * @��������: ���϶���
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Oct 28, 2011
 */
public class HnEamMsgService extends Thread {

	private SMSConfig smsConfig = null;
	private String systemName = "";
	String proCode = "";
	String code = "";
	private ServletConfigDTO servletCon = null;

	public HnEamMsgService() {
		super();
	}

	public void setSMSConfig(SMSConfig smsConfig) {
		setSmsConfig(smsConfig);
		this.systemName = smsConfig.getSystemName();
	}

	public void setServletConfig(ServletConfigDTO servletCon) {
		this.proCode = servletCon.getProvinceCode();
		code = servletCon.getProvinceCode();
	}

	public void run() {
//		Connection conn = null;
		System.out.println("start sms service...");
		// if(proCode.equals("42")){
		while (true) {
			try {
//				conn = DBManager.getDBConnection();
				// �����ռ�����
				SMSMsgCreate sc = new SMSMsgCreate();
				sc.AutoCreateMsg(); // �ռ�
				 
				HnSendMsg es = new HnSendMsg();
//				EamMsgSend es = new EamMsgSend();
				es.sendMsg(); // ����
				sleep(86400000); 
			} 
//			catch (PoolException ex) {
//				ex.printLog();
//			} 
			catch (InterruptedException ex) {
				Logger.logError(ex);
			}/*
				 * catch (NoSuchProviderException e) { Logger.logError(e); }
				 */finally {
//				DBManager.closeDBConnection(conn);
			}
		}
	}

//	/**
//	 * ���ݶ���Ϣ���� �ж��Ƿ���Ҫ���Ͷ���Ϣ
//	 * 
//	 * @param conn
//	 * @param msgCode
//	 * @return
//	 * @throws QueryException
//	 * @throws ContainerException
//	 */
//	public boolean sendMsg(Connection conn, String msgCode)
//			throws QueryException, ContainerException {
//		SQLModel sqlModel = new SQLModel();
//		String strSql = "SELECT sf_msg.need_send_message('" + msgCode
//				+ "') SEND ";
//		sqlModel.setSqlStr(strSql);
//		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
//		sq.executeQuery();
//		Row row = sq.getFirstRow();
//		return String.valueOf(row.getValue("SEND")).equals("1");
//	}

	public SMSConfig getSmsConfig() {
		return smsConfig;
	}

	public void setSmsConfig(SMSConfig smsConfig) {
		this.smsConfig = smsConfig;
	}
	
	public static void main(String[] args) {
		HnEamMsgService eamService = new HnEamMsgService();
		eamService.start();
	}
}