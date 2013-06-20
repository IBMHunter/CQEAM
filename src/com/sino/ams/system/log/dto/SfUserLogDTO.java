package com.sino.ams.system.log.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CalendarUtililyDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
* <p>Title: �û�URL������־��(EAM) SfUserLog</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class SfUserLogDTO extends CalendarUtililyDTO{

	private int logId;
	private int userId;
	private String resName="";
	private String clientIp = "";
	private String reqUrl = "";
	private String actionType = "";
	private String source = "";
	private String server = "";
	private SimpleCalendar logTime = null;
	private String userAccount = "";
	private String columeType="";//���˹���̨����ϵͳ���ý��� SYSTEM/PERSONAL //personal
	
	public String getResName() {
		return resName;
	}
	/**
	 * ���ò�������
	 * @param resName
	 */
	public void setResName(String resName) {
		this.resName = resName;
	}

	/**
	 * ���ܣ������û�URL������־��(EAM)���� ��־���к�
	 * @param logId String
	 */
	public void setLogId(int logId){
		this.logId = logId;
	}

	/**
	 * ���ܣ������û�URL������־��(EAM)���� ��¼�û�
	 * @param userId String
	 */
	public void setUserId(int userId){
		this.userId = userId;
	}

	/**
	 * ���ܣ������û�URL������־��(EAM)���� �û�IP
	 * @param clientIp String
	 */
	public void setClientIp(String clientIp){
		this.clientIp = clientIp;
	}

	/**
	 * ���ܣ������û�URL������־��(EAM)���� ������Դ
	 * @param reqUrl String
	 */
	public void setReqUrl(String reqUrl){
		this.reqUrl = reqUrl;
	}

	/**
	 * ���ܣ������û�URL������־��(EAM)���� ��������
	 * @param actionType String
	 */
	public void setActionType(String actionType){
		this.actionType = actionType;
	}

	/**
	 * ���ܣ������û�URL������־��(EAM)���� ������Դ(WEB,PDA)
	 * @param source String
	 */
	public void setSource(String source){
		this.source = source;
	}

	/**
	 * ���ܣ������û�URL������־��(EAM)���� ������
	 * @param server String
	 */
	public void setServer(String server){
		this.server = server;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * ���ܣ������û�URL������־��(EAM)���� ����ʱ��
	 * @param logTime SimpleCalendar
	 * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
	 */
	public void setLogTime(String logTime) throws CalendarException{
		if(!StrUtil.isEmpty(logTime)){
			this.logTime = new SimpleCalendar(logTime);
		}
	}


	/**
	 * ���ܣ���ȡ�û�URL������־��(EAM)���� ��־���к�
	 * @return String
	 */
	public int getLogId(){
		return this.logId;
	}

	/**
	 * ���ܣ���ȡ�û�URL������־��(EAM)���� ��¼�û�
	 * @return String
	 */
	public int getUserId(){
		return this.userId;
	}

	/**
	 * ���ܣ���ȡ�û�URL������־��(EAM)���� �û�IP
	 * @return String
	 */
	public String getClientIp(){
		return this.clientIp;
	}

	/**
	 * ���ܣ���ȡ�û�URL������־��(EAM)���� ������Դ
	 * @return String
	 */
	public String getReqUrl(){
		return this.reqUrl;
	}

	/**
	 * ���ܣ���ȡ�û�URL������־��(EAM)���� ��������
	 * @return String
	 */
	public String getActionType(){
		return this.actionType;
	}

	/**
	 * ���ܣ���ȡ�û�URL������־��(EAM)���� ������Դ(WEB,PDA)
	 * @return String
	 */
	public String getSource(){
		return this.source;
	}

	/**
	 * ���ܣ���ȡ�û�URL������־��(EAM)���� ������
	 * @return String
	 */
	public String getServer(){
		return this.server;
	}

	/**
	 * ���ܣ���ȡ�û�URL������־��(EAM)���� ����ʱ��
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getLogTime(){
		return this.logTime;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public String getColumeType() {
		return columeType;
	}

	public void setColumeType(String columeType) {
		this.columeType = columeType;
	}
}
