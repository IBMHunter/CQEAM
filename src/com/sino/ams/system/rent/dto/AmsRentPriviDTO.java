package com.sino.ams.system.rent.dto;

import com.sino.ams.newasset.dto.AmsAssetsPriviDTO;

/**
* <p>Title: ����Ȩ�ޱ� AmsRentPrivi</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class AmsRentPriviDTO extends AmsAssetsPriviDTO {
    public AmsRentPriviDTO(){
        super();
    }
   private String rentPrivi = "";

    public String getRentPrivi() {
        return rentPrivi;
    }

    public void setRentPrivi(String rentPrivi) {
        this.rentPrivi = rentPrivi;
    }
}