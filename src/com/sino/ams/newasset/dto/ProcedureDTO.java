package com.sino.ams.newasset.dto;

import com.sino.ams.bean.CommonRecordDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ProcedureDTO extends CommonRecordDTO {
    private String procdureName = "";

    public ProcedureDTO() {
        super();
    }

    public void setProcdureName(String procdureName) {
        this.procdureName = procdureName;
    }

    public String getProcdureName() {
        return procdureName;
    }
}
