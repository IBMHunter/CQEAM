package com.sino.ams.house.dto;

import com.sino.base.SinoBaseObject;
import com.sino.base.dto.DTO;

/**
 * �������ر�����Ϣͳ������ģ��
 * @author kouzh
 * 
 */
public class AmshouseinfoDTO extends SinoBaseObject implements DTO {

	private String company;// ��˾
	private long assetnum;// �ʲ�����
	private long officeHousenum;// �칫Ӫҵ��������
	private long officeLandnum;// �칫Ӫҵ��������
	private long landnum;// ��վ��������
	private long housenum;// ��վ��������
    private long officeHouseLandNum;//�칫���غ�һ����
    private long houseLandNum;//��վ���غ�һ����
    private long houseCertificateNum;// ����Ȩ֤����
    private long houseLandCertificateNum;//���غ�һȨ֤����
    private double houseArea;// �������
	private double occupyArea;// �������
	private double cost;// �ʲ�ԭֵ
	private double deprnReserve;// �ۼ��۾�
	private double netAssetValue;// �ʲ���ֵ
    private double landCertificateNum;

    public double getLandCertificateNum() {
        return landCertificateNum;
    }

    public void setLandCertificateNum(double landCertificateNum) {
        this.landCertificateNum = landCertificateNum;
    }

    public long getHouseLandCertificateNum() {
        return houseLandCertificateNum;
    }

    public void setHouseLandCertificateNum(long houseLandCertificateNum) {
        this.houseLandCertificateNum = houseLandCertificateNum;
    }

    public long getOfficeHouseLandNum() {
        return officeHouseLandNum;
    }

    public void setOfficeHouseLandNum(long officeHouseLandNum) {
        this.officeHouseLandNum = officeHouseLandNum;
    }

    public long getHouseLandNum() {
        return houseLandNum;
    }

    public void setHouseLandNum(long houseLandNum) {
        this.houseLandNum = houseLandNum;
    }

    public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public long getAssetnum() {
		return assetnum;
	}

	public void setAssetnum(long assetnum) {
		this.assetnum = assetnum;
	}

	public long getHouseCertificateNum() {
		return houseCertificateNum;
	}

	public void setHouseCertificateNum(long houseCertificateNum) {
		this.houseCertificateNum = houseCertificateNum;
	}

	public double getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(double houseArea) {
		this.houseArea = houseArea;
	}

	public double getOccupyArea() {
		return occupyArea;
	}

	public void setOccupyArea(double occupyArea) {
		this.occupyArea = occupyArea;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getDeprnReserve() {
		return deprnReserve;
	}

	public void setDeprnReserve(double deprnReserve) {
		this.deprnReserve = deprnReserve;
	}

	public double getNetAssetValue() {
		return netAssetValue;
	}

	public void setNetAssetValue(double netAssetValue) {
		this.netAssetValue = netAssetValue;
	}

	public long getOfficeHousenum() {
		return officeHousenum;
	}

	public void setOfficeHousenum(long officeHousenum) {
		this.officeHousenum = officeHousenum;
	}

	public long getOfficeLandnum() {
		return officeLandnum;
	}

	public void setOfficeLandnum(long officeLandnum) {
		this.officeLandnum = officeLandnum;
	}

	public long getLandnum() {
		return landnum;
	}

	public void setLandnum(long landnum) {
		this.landnum = landnum;
	}

	public long getHousenum() {
		return housenum;
	}

	public void setHousenum(long housenum) {
		this.housenum = housenum;
	}
}
