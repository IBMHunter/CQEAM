package com.sino.ams.newasset.dto;

import com.sino.base.dto.CheckBoxDTO;

/**
 * <p>Title: �̶��ʲ�Ŀ¼�ֵ� AmsFaDict</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AmsFaDictDTO extends CheckBoxDTO {

    private String faCode = "";
    private String faDescription = "";
    private String faContentCode = "";

    public AmsFaDictDTO() {
        super();
    }

    /**
     * ���ܣ����ù̶��ʲ�Ŀ¼�ֵ����� �̶��ʲ��ڶ��εĵ�һ�δ���
     * @param faCode String
     */
    public void setFaCode(String faCode) {
        this.faCode = faCode;
    }

    /**
     * ���ܣ����ù̶��ʲ�Ŀ¼�ֵ����� �̶��ʲ��ڶ��εĵ�һ������
     * @param faDescription String
     */
    public void setFaDescription(String faDescription) {
        this.faDescription = faDescription;
    }

    /**
     * ���ܣ����ù̶��ʲ�Ŀ¼�ֵ����� EAMϵͳ����Ĺ̶��ʲ�Ŀ¼�ֵ����
     * @param faContentCode String
     */
    public void setFaContentCode(String faContentCode) {
        this.faContentCode = faContentCode;
    }


    /**
     * ���ܣ���ȡ�̶��ʲ�Ŀ¼�ֵ����� �̶��ʲ��ڶ��εĵ�һ�δ���
     * @return String
     */
    public String getFaCode() {
        return this.faCode;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ�Ŀ¼�ֵ����� �̶��ʲ��ڶ��εĵ�һ������
     * @return String
     */
    public String getFaDescription() {
        return this.faDescription;
    }

    /**
     * ���ܣ���ȡ�̶��ʲ�Ŀ¼�ֵ����� EAMϵͳ����Ĺ̶��ʲ�Ŀ¼�ֵ����
     * @return String
     */
    public String getFaContentCode() {
        return this.faContentCode;
    }
}
