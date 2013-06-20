package com.sino.soa.mis.srv.assetcategory.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-6
 * Time: 17:15:59
 * To change this template use File | Settings | File Templates.
 */
public class SBFIFASrvAssetCategoryDTO extends CheckBoxDTO {

    private String categoryId = null;
    private String description = "";
    private String categoryType = "";
    private String segment1 = "";
    private String segment2 = "";
    private String segment3 = "";
    private String assetCostAccountCcid = null;
    private String reserveAccountCcid = null;
    private String assetClearingAccountCcid = null;
    private String lifeInMonths = null;
    private String percentSalvageValue = null;
    private String enabledFlag = "";
    private String attribute1 = "";
    private String inventorial = "";
    private String capitalizeFlag = "";
    private String bookTypeCode = "";
    private SimpleCalendar lastUpdateDate = null;
    private String orgOption="";
    private String concatenatedSegments ="";
    private String assetsType ="";

    public SBFIFASrvAssetCategoryDTO() {
        super();
        this.lastUpdateDate = new SimpleCalendar();
    }

    /**
     * ���ܣ������ʲ����������� �ʲ����ID
     * @param categoryId String
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * ���ܣ������ʲ����������� �������
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ���ܣ������ʲ����������� ��������"NON-LEASE"�����޵�
     * @param categoryType String
     */
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * ���ܣ������ʲ����������� Ӧ������
     * @param segment1 String
     */
    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    /**
     * ���ܣ������ʲ����������� Ŀ¼����
     * @param segment2 String
     */
    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    /**
     * ���ܣ������ʲ����������� ����
     * @param segment3 String
     */
    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    /**
     * ���ܣ������ʲ����������� �ʲ�ԭֵ��Ŀ
     * @param assetCostAccountCcid String
     */
    public void setAssetCostAccountCcid(String assetCostAccountCcid) {
        this.assetCostAccountCcid = assetCostAccountCcid;
    }

    /**
     * ���ܣ������ʲ����������� �ʲ��۾ɿ�Ŀ
     * @param reserveAccountCcid String
     */
    public void setReserveAccountCcid(String reserveAccountCcid) {
        this.reserveAccountCcid = reserveAccountCcid;
    }

    /**
     * ���ܣ������ʲ����������� �ʲ������Ŀ
     * @param assetClearingAccountCcid String
     */
    public void setAssetClearingAccountCcid(String assetClearingAccountCcid) {
        this.assetClearingAccountCcid = assetClearingAccountCcid;
    }

    /**
     * ���ܣ������ʲ����������� �۾�����
     * @param lifeInMonths String
     */
    public void setLifeInMonths(String lifeInMonths) {
        this.lifeInMonths = lifeInMonths;
    }

    /**
     * ���ܣ������ʲ����������� ��ֵ��
     * @param percentSalvageValue String
     */
    public void setPercentSalvageValue(String percentSalvageValue) {
        this.percentSalvageValue = percentSalvageValue;
    }

    /**
     * ���ܣ������ʲ����������� �Ƿ�����
     * @param enabledFlag String
     */
    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    /**
     * ���ܣ������ʲ����������� ������λ
     * @param attribute1 String
     */
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    /**
     * ���ܣ������ʲ����������� �Ƿ�ʵ���̴�
     * @param inventorial String
     */
    public void setInventorial(String inventorial) {
        this.inventorial = inventorial;
    }

    /**
     * ���ܣ������ʲ����������� �Ƿ��ʱ���
     * @param capitalizeFlag String
     */
    public void setCapitalizeFlag(String capitalizeFlag) {
        this.capitalizeFlag = capitalizeFlag;
    }

    /**
     * ���ܣ������ʲ����������� �ʲ�����
     * @param bookTypeCode String
     */
    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    /**
     * ���ܣ������ʲ����������� ���¸���ʱ��
     * @param lastUpdateDate SimpleCalendar
     * @throws com.sino.base.exception.CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }


    /**
     * ���ܣ���ȡ�ʲ����������� �ʲ����ID
     * @return String
     */
    public String getCategoryId() {
        return this.categoryId;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �������
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� ��������"NON-LEASE"�����޵�
     * @return String
     */
    public String getCategoryType() {
        return this.categoryType;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� Ӧ������
     * @return String
     */
    public String getSegment1() {
        return this.segment1;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� Ŀ¼����
     * @return String
     */
    public String getSegment2() {
        return this.segment2;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� ����
     * @return String
     */
    public String getSegment3() {
        return this.segment3;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �ʲ�ԭֵ��Ŀ
     * @return String
     */
    public String getAssetCostAccountCcid() {
        return this.assetCostAccountCcid;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �ʲ��۾ɿ�Ŀ
     * @return String
     */
    public String getReserveAccountCcid() {
        return this.reserveAccountCcid;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �ʲ������Ŀ
     * @return String
     */
    public String getAssetClearingAccountCcid() {
        return this.assetClearingAccountCcid;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �۾�����
     * @return String
     */
    public String getLifeInMonths() {
        return this.lifeInMonths;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� ��ֵ��
     * @return String
     */
    public String getPercentSalvageValue() {
        return this.percentSalvageValue;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �Ƿ�����
     * @return String
     */
    public String getEnabledFlag() {
        return this.enabledFlag;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� ������λ
     * @return String
     */
    public String getAttribute1() {
        return this.attribute1;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �Ƿ�ʵ���̴�
     * @return String
     */
    public String getInventorial() {
        return this.inventorial;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �Ƿ��ʱ���
     * @return String
     */
    public String getCapitalizeFlag() {
        return this.capitalizeFlag;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� �ʲ�����
     * @return String
     */
    public String getBookTypeCode() {
        return this.bookTypeCode;
    }

    /**
     * ���ܣ���ȡ�ʲ����������� ���¸���ʱ��
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
	}

	/**
	 * @return the orgOption
	 */
	public String getOrgOption() {
		return orgOption;
	}

	/**
	 * @param orgOption the orgOption to set
	 */
	public void setOrgOption(String orgOption) {
		this.orgOption = orgOption;
	}

	public String getConcatenatedSegments() {
		return concatenatedSegments;
	}

	public void setConcatenatedSegments(String concatenatedSegments) {
		this.concatenatedSegments = concatenatedSegments;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

}