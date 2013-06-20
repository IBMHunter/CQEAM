package com.sino.ams.dzyh.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
* <p>Title: ��ֵ�׺�Ʒ����(EAM) EamDhCatalogSet</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����
* @version 1.0
*/

public class EamDhCatalogSetDTO extends CommonRecordDTO{

	private String catlogSetId = "";
	private String setCode = "";
	private String setName = "";
	private int enabled = 1;

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ����(EAM)���� EAM_DH_CATALOG_SET_S.NEXTVAL
	 * @param catlogSetId String
	 */
	public void setCatlogSetId(String catlogSetId){
		this.catlogSetId = catlogSetId;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ����(EAM)���� �����
	 * @param setCode String
	 */
	public void setSetCode(String setCode){
		this.setCode = setCode;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ����(EAM)���� �������
	 * @param setName String
	 */
	public void setSetName(String setName){
		this.setName = setName;
	}

	/**
	 * ���ܣ����õ�ֵ�׺�Ʒ����(EAM)���� �Ƿ���Ч
	 * @param enabled String
	 */
	public void setEnabled(int enabled){
		this.enabled = enabled;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ����(EAM)���� EAM_DH_CATALOG_SET_S.NEXTVAL
	 * @return String
	 */
	public String getCatlogSetId() {
		return this.catlogSetId;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ����(EAM)���� �����
	 * @return String
	 */
	public String getSetCode() {
		return this.setCode;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ����(EAM)���� �������
	 * @return String
	 */
	public String getSetName() {
		return this.setName;
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺�Ʒ����(EAM)���� �Ƿ���Ч
	 * @return String
	 */
	public int getEnabled() {
		return this.enabled;
	}
}
