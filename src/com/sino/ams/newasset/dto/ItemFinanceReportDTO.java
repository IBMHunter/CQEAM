package com.sino.ams.newasset.dto;

import com.sino.base.SinoBaseObject;
import com.sino.base.dto.DTO;

/**
 * <p>Title: AMS_ACCOUNT_V AmsAccountV</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class ItemFinanceReportDTO extends SinoBaseObject implements DTO {
    private String financeProp = "";
    private String itemCount = "";

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getFinanceProp() {
        return financeProp;
    }

    public void setFinanceProp(String financeProp) {
        this.financeProp = financeProp;
    }
}
