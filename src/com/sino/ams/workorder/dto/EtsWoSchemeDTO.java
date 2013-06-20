package com.sino.ams.workorder.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
* <p>Title: �½�������վ������Ϣ(EAM) EtsWoSchemeTmp</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class EtsWoSchemeDTO extends CheckBoxDTO{

	private int systemid;
	private String workorderNo = "";
	private String itemCode = "";
	private int itemQty;
	private String attribute1 = "";
	private String attribute2 = "";
	private String attribute3 = "";
	private String attribute4 = "";
	private String attribute5 = "";
	private int attribute6;


	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param systemid String
	 */
	public void setSystemid(int systemid){
		this.systemid = systemid;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param workorderNo String
	 */
	public void setWorkorderNo(String workorderNo){
		this.workorderNo = workorderNo;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param itemCode String
	 */
	public void setItemCode(String itemCode){
		this.itemCode = itemCode;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param itemQty String
	 */
	public void setItemQty(int itemQty){
		this.itemQty = itemQty;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param attribute1 String
	 */
	public void setAttribute1(String attribute1){
		this.attribute1 = attribute1;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param attribute2 String
	 */
	public void setAttribute2(String attribute2){
		this.attribute2 = attribute2;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param attribute3 String
	 */
	public void setAttribute3(String attribute3){
		this.attribute3 = attribute3;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param attribute4 String
	 */
	public void setAttribute4(String attribute4){
		this.attribute4 = attribute4;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param attribute5 String
	 */
	public void setAttribute5(String attribute5){
		this.attribute5 = attribute5;
	}

	/**
	 * ���ܣ������½�������վ������Ϣ(EAM)���� null
	 * @param attribute6 String
	 */
	public void setAttribute6(int attribute6){
		this.attribute6 = attribute6;
	}


	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public int getSystemid(){
		return this.systemid;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public String getWorkorderNo(){
		return this.workorderNo;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public String getItemCode(){
		return this.itemCode;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public int getItemQty(){
		return this.itemQty;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public String getAttribute1(){
		return this.attribute1;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public String getAttribute2(){
		return this.attribute2;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public String getAttribute3(){
		return this.attribute3;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public String getAttribute4(){
		return this.attribute4;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public String getAttribute5(){
		return this.attribute5;
	}

	/**
	 * ���ܣ���ȡ�½�������վ������Ϣ(EAM)���� null
	 * @return String
	 */
	public int getAttribute6(){
		return this.attribute6;
	}

}