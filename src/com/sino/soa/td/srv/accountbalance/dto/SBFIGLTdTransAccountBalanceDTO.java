package com.sino.soa.td.srv.accountbalance.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-10-13
 * Time: 11:33:36
 * To change this template use File | Settings | File Templates.
 */
public class SBFIGLTdTransAccountBalanceDTO extends CheckBoxDTO {

    private String periodName = "";
    private String currencyCode = "";
    private String actualFlag = "";
    private String codeCombinationId = null;
    private String segment1 = "";
    private String segment2 = "";
    private String segment3 = "";
    private String segment4 = "";
    private String segment5 = "";
    private String segment6 = "";
    private String segment7 = "";
    private String beginBalanceDr = null;
    private String beginBalanceCr = null;
    private String beginBalance = null;
    private String periodNetDr = null;
    private String periodNetCr = null;
    private String periodNet = null;
    private String endBalanceDr = null;
    private String endBalanceCr = null;
    private String endBalance = null;
    private String structuredHierarchyNameCom = "";
    private String structuredHierarchyNameCos = "";
    private String setOfBooks = "";

    private String accountType = "";
    private String companyCode = "";
    private String concatenatedSemgmentsFrom = "";

	private String bookTypeCode = "";
	private String startLastUpdateDate = "";
	private String endLastUpdateDate = "";
    private String assetsType = "";

    public SBFIGLTdTransAccountBalanceDTO() {
        super();
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��Ӧ�Ļ���ڼ�
     * @param periodName String
     */
    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ���ִ���
     * @param currencyCode String
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� �������
     * @param actualFlag String
     */
    public void setActualFlag(String actualFlag) {
        this.actualFlag = actualFlag;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��Ŀ���ID
     * @param codeCombinationId String
     */
    public void setCodeCombinationId(String codeCombinationId) {
        this.codeCombinationId = codeCombinationId;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��˾����
     * @param segment1 String
     */
    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� �ɱ����Ĵ���
     * @param segment2 String
     */
    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��Ŀ����
     * @param segment3 String
     */
    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� Ʒ�ƴ���
     * @param segment4 String
     */
    public void setSegment4(String segment4) {
        this.segment4 = segment4;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��Ŀ����
     * @param segment5 String
     */
    public void setSegment5(String segment5) {
        this.segment5 = segment5;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��������
     * @param segment6 String
     */
    public void setSegment6(String segment6) {
        this.segment6 = segment6;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ���ô���
     * @param segment7 String
     */
    public void setSegment7(String segment7) {
        this.segment7 = segment7;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� �ڳ��跽���
     * @param beginBalanceDr String
     */
    public void setBeginBalanceDr(String beginBalanceDr) {
        this.beginBalanceDr = beginBalanceDr;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� �ڳ��������
     * @param beginBalanceCr String
     */
    public void setBeginBalanceCr(String beginBalanceCr) {
        this.beginBalanceCr = beginBalanceCr;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� �ڳ����
     * @param beginBalance String
     */
    public void setBeginBalance(String beginBalance) {
        this.beginBalance = beginBalance;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ���ڽ跽������
     * @param periodNetDr String
     */
    public void setPeriodNetDr(String periodNetDr) {
        this.periodNetDr = periodNetDr;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ���ڴ���������
     * @param periodNetCr String
     */
    public void setPeriodNetCr(String periodNetCr) {
        this.periodNetCr = periodNetCr;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ���ڷ�����
     * @param periodNet String
     */
    public void setPeriodNet(String periodNet) {
        this.periodNet = periodNet;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��ĩ�跽���
     * @param endBalanceDr String
     */
    public void setEndBalanceDr(String endBalanceDr) {
        this.endBalanceDr = endBalanceDr;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��ĩ�������
     * @param endBalanceCr String
     */
    public void setEndBalanceCr(String endBalanceCr) {
        this.endBalanceCr = endBalanceCr;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��ĩ��
     * @param endBalance String
     */
    public void setEndBalance(String endBalance) {
        this.endBalance = endBalance;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� ��˾�㼶
     * @param structuredHierarchyNameCom String
     */
    public void setStructuredHierarchyNameCom(String structuredHierarchyNameCom) {
        this.structuredHierarchyNameCom = structuredHierarchyNameCom;
    }

    /**
     * ���ܣ����ÿ�Ŀ���������� �ɱ����Ĳ㼶
     * @param structuredHierarchyNameCos String
     */
    public void setStructuredHierarchyNameCos(String structuredHierarchyNameCos) {
        this.structuredHierarchyNameCos = structuredHierarchyNameCos;
    }


    /**
     * ���ܣ���ȡ��Ŀ���������� ��Ӧ�Ļ���ڼ�
     * @return String
     */
    public String getPeriodName() {
        return this.periodName;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ���ִ���
     * @return String
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� �������
     * @return String
     */
    public String getActualFlag() {
        return this.actualFlag;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��Ŀ���ID
     * @return String
     */
    public String getCodeCombinationId() {
        return this.codeCombinationId;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��˾����
     * @return String
     */
    public String getSegment1() {
        return this.segment1;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� �ɱ����Ĵ���
     * @return String
     */
    public String getSegment2() {
        return this.segment2;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��Ŀ����
     * @return String
     */
    public String getSegment3() {
        return this.segment3;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� Ʒ�ƴ���
     * @return String
     */
    public String getSegment4() {
        return this.segment4;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��Ŀ����
     * @return String
     */
    public String getSegment5() {
        return this.segment5;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��������
     * @return String
     */
    public String getSegment6() {
        return this.segment6;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ���ô���
     * @return String
     */
    public String getSegment7() {
        return this.segment7;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� �ڳ��跽���
     * @return String
     */
    public String getBeginBalanceDr() {
        return this.beginBalanceDr;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� �ڳ��������
     * @return String
     */
    public String getBeginBalanceCr() {
        return this.beginBalanceCr;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� �ڳ����
     * @return String
     */
    public String getBeginBalance() {
        return this.beginBalance;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ���ڽ跽������
     * @return String
     */
    public String getPeriodNetDr() {
        return this.periodNetDr;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ���ڴ���������
     * @return String
     */
    public String getPeriodNetCr() {
        return this.periodNetCr;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ���ڷ�����
     * @return String
     */
    public String getPeriodNet() {
        return this.periodNet;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��ĩ�跽���
     * @return String
     */
    public String getEndBalanceDr() {
        return this.endBalanceDr;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��ĩ�������
     * @return String
     */
    public String getEndBalanceCr() {
        return this.endBalanceCr;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��ĩ��
     * @return String
     */
    public String getEndBalance() {
        return this.endBalance;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� ��˾�㼶
     * @return String
     */
    public String getStructuredHierarchyNameCom() {
        return this.structuredHierarchyNameCom;
    }

    /**
     * ���ܣ���ȡ��Ŀ���������� �ɱ����Ĳ㼶
     * @return String
     */
    public String getStructuredHierarchyNameCos() {
        return this.structuredHierarchyNameCos;
    }

	/**
	 * @return the setOfBooks
	 */
	public String getSetOfBooks() {
		return setOfBooks;
	}

	/**
	 * @param setOfBooks the setOfBooks to set
	 */
	public void setSetOfBooks(String setOfBooks) {
		this.setOfBooks = setOfBooks;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the concatenatedSemgmentsFrom
	 */
	public String getConcatenatedSemgmentsFrom() {
		return concatenatedSemgmentsFrom;
	}

	/**
	 * @param concatenatedSemgmentsFrom the concatenatedSemgmentsFrom to set
	 */
	public void setConcatenatedSemgmentsFrom(String concatenatedSemgmentsFrom) {
		this.concatenatedSemgmentsFrom = concatenatedSemgmentsFrom;
	}

	/**
	 * @return the bookTypeCode
	 */
	public String getBookTypeCode() {
		return bookTypeCode;
	}

	/**
	 * @param bookTypeCode the bookTypeCode to set
	 */
	public void setBookTypeCode(String bookTypeCode) {
		this.bookTypeCode = bookTypeCode;
	}

	/**
	 * @return the endLastUpdateDate
	 */
	public String getEndLastUpdateDate() {
		return endLastUpdateDate;
	}

	/**
	 * @param endLastUpdateDate the endLastUpdateDate to set
	 */
	public void setEndLastUpdateDate(String endLastUpdateDate) {
		this.endLastUpdateDate = endLastUpdateDate;
	}

	/**
	 * @return the startLastUpdateDate
	 */
	public String getStartLastUpdateDate() {
		return startLastUpdateDate;
	}

	/**
	 * @param startLastUpdateDate the startLastUpdateDate to set
	 */
	public void setStartLastUpdateDate(String startLastUpdateDate) {
		this.startLastUpdateDate = startLastUpdateDate;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

}