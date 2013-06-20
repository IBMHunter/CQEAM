package com.sino.ams.system.object;

import com.sino.ams.bean.CommonRecordDTO;

/**
 *
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsObjectAddressDTO extends CommonRecordDTO{
	private String addressId = "";
	private String objectNo = "";
	private String boxNo = "";
    private String netUnit = "";
    private int organizationId;
    private String remark = "";
    private String addressNo = "";
    private String objectNos = "";

	/**
	* ���ܣ��Զ��������ݴ�������캯������ϵص��(EAM)��
	* <B>�������ֶ�ʱͬʱ��Ҫ������ʼ��������������Ҫ��ʼ�����ֶ����ڴ���ɡ�
	* ���������ɴ�������ݿ���������������ֶΣ������ڴ��������ʼ��</B>
	*/
	public AmsObjectAddressDTO() {
		super();
	}

	/**
	 * ���ܣ��������ԣ� ��ϵص�
	 * @param addressId String
	 */
	public void setAddressId(String addressId){
		this.addressId = addressId;
	}

	/**
	 * ���ܣ��������ԣ� �ص�ID
	 * @param objectNo String
	 */
	public void setObjectNo(String objectNo){
		this.objectNo = objectNo;
	}

	/**
	 * ���ܣ��������ԣ� ������
	 * @param boxNo String
	 */
	public void setBoxNo(String boxNo){
		this.boxNo = boxNo;
	}

	/**
	 * ���ܣ��������ԣ� ��Ԫ���
	 * @param netUnit String
	 */
	public void setNetUnit(String netUnit){
		this.netUnit = netUnit;
	}

	/**
	 * ���ܣ��������ԣ� ����OU
	 * @param organizationId String
	 */
	public void setOrganizationId(int organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * ���ܣ��������ԣ� ��ע
	 * @param remark String
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * ���ܣ��������ԣ� ��ϵص�
	 * @param addressNo String
	 */
	public void setAddressNo(String addressNo){
		this.addressNo = addressNo;
	}


	/**
	 * ���ܣ���ȡ���ԣ� ��ϵص�
	 * @return String
	 */
	public String getAddressId() {
		return this.addressId;
	}

	/**
	 * ���ܣ���ȡ���ԣ� �ص�ID
	 * @return String
	 */
	public String getObjectNo() {
		return this.objectNo;
	}

	/**
	 * ���ܣ���ȡ���ԣ� ������
	 * @return String
	 */
	public String getBoxNo() {
		return this.boxNo;
	}

	/**
	 * ���ܣ���ȡ���ԣ� ��Ԫ���
	 * @return String
	 */
	public String getNetUnit() {
		return this.netUnit;
	}

	/**
	 * ���ܣ���ȡ���ԣ� ����OU
	 * @return String
	 */
	public int getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * ���ܣ���ȡ���ԣ� ��ע
	 * @return String
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���ܣ���ȡ���ԣ� ��ϵص�
	 * @return String
	 */
	public String getAddressNo() {
		return this.addressNo;
	}

    public String getObjectNos() {
        return objectNos;
    }

    public void setObjectNos(String objectNos) {
        this.objectNos = objectNos;
    }
}
