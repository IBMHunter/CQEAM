package com.sino.ams.newasset.report.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-13
 * Time: 15:28:27
 * To change this template use File | Settings | File Templates.
 */
public class AssetsGeneralDTO extends CommonRecordDTO {
    private String period = "";//����ڼ�
    private String managerGuideType = "";//����ָ���౨������

    public String getManagerGuideType() {
        return managerGuideType;
    }

    public void setManagerGuideType(String managerGuideType) {
        this.managerGuideType = managerGuideType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
