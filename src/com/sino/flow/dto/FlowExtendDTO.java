package com.sino.flow.dto;

import com.sino.base.dto.DTO;

/**
 * Created by wwb.
 * User: V-wangwenbin
 * Date: 2007-11-6
 * Time: 15:12:08
 * ���DTO��FlowExtend�����Ĳ���DTO��
 * Ϊ�˱�����չ��flowExtend��������ֱ�Ӵ����������ǰ�װ��DTO����
 */
public class FlowExtendDTO implements DTO {
    private String key = "";
    private String orgId = "";
    private String appointType = "";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAppointType() {
        return appointType;
    }

    public void setAppointType(String appointType) {
        this.appointType = appointType;
    }
}
