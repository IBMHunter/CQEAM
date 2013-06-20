package com.sino.ams.system.county.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: ���ر�(EAM) EtsCounty</p>
 * <p>Description:�����Զ�����DTO���ݴ������</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author zz_jlc
 * @version 1.0
 */

public class EtsCountyDTO extends CheckBoxDTO{

    private String countyCode  ;
    private String countyName = "";
    private String companyCode = "";
    private String enabled = "";
    private String countyCodeMis ; 
    private String countyCodeCoaCc = "";
	private SimpleCalendar creationDate = null;
    private int createdBy ;
	private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateBy  ;
    private int organizationId  ;

    public EtsCountyDTO() {
        super();
        this.creationDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * ���ܣ��������ر�(EAM)���� ���ش���
     * @param countyCode String
     */
    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }

    /**
     * ���ܣ��������ر�(EAM)���� ��������
     * @param countyName String
     */
    public void setCountyName(String countyName){
        this.countyName = countyName;
    }

    /**
     * ���ܣ��������ر�(EAM)���� ���й�˾����
     * @param companyCode String
     */
    public void setCompanyCode(String companyCode){
        this.companyCode = companyCode;
    }

    /**
     * ���ܣ��������ر�(EAM)���� ��Ч��־
     * @param enabled String
     */
    public void setEnabled(String enabled){
        this.enabled = enabled;
    }

    /**
     * ���ܣ��������ر�(EAM)���� ��ӦMIS����
     * @param countyCodeMis String
     */
    public void setCountyCodeMis(String countyCodeMis){
        this.countyCodeMis = countyCodeMis;
    }

    /**
     * ���ܣ��������ر�(EAM)���� ��ӦMIS COA_CC����
     * @param countyCodeCoaCc String
     */
    public void setCountyCodeCoaCc(String countyCodeCoaCc){
        this.countyCodeCoaCc = countyCodeCoaCc;
    }

    /**
     * ���ܣ��������ر�(EAM)���� ��������
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException{
        if(!StrUtil.isEmpty(creationDate)){
        this.creationDate.setCalendarValue(creationDate);
        }
    }

    /**
     * ���ܣ��������ر�(EAM)���� ������
     * @param createdBy String
     */
    public void setCreatedBy(int createdBy){
        this.createdBy = createdBy;
    }

    /**
     * ���ܣ��������ر�(EAM)���� �ϴ��޸�����
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException{
       if(!StrUtil.isEmpty(lastUpdateDate)){
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);  }
    }

    /**
     * ���ܣ��������ر�(EAM)���� �ϴ��޸���
     * @param lastUpdateBy String
     */
    public void setLastUpdateBy(int lastUpdateBy){
        this.lastUpdateBy = lastUpdateBy;
    }


    /**
     * ���ܣ���ȡ���ر�(EAM)���� ���ش���
     * @return String
     */
    public String getCountyCode() {
        return this.countyCode;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� ��������
     * @return String
     */
    public String getCountyName() {
        return this.countyName;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� ���й�˾����
     * @return String
     */
    public String getCompanyCode() {
        return this.companyCode;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� ��Ч��־
     * @return String
     */
    public String getEnabled() {
        return this.enabled;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� ��ӦMIS����
     * @return String
     */
    public String getCountyCodeMis() {
        return this.countyCodeMis;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� ��ӦMIS COA_CC����
     * @return String
     */
    public String getCountyCodeCoaCc() {
        return this.countyCodeCoaCc;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� ��������
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        creationDate.setCalPattern(getCalPattern());
        return this.creationDate;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� ������
     * @return String
     */
    public int getCreatedBy() {
        return this.createdBy;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� �ϴ��޸�����
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ���ȡ���ر�(EAM)���� �ϴ��޸���
     * @return String
     */
    public int getLastUpdateBy() {
        return this.lastUpdateBy;
    }

}