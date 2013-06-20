package com.sino.flow.dto;

import com.sino.base.SinoBaseObject;
import com.sino.base.dto.DTO;

/**
 * <p>Title: SfProcedureDef</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class SfProcedureDefDTO extends SinoBaseObject implements DTO {
    private String procId = "";
    private String procName = "";
    private String description = "";
    private String appTableName = "";
    private String appPkName = "";
    private int organizationId = -1;
    public void setProcId(String procId) {
        this.procId = procId;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppTableName(String appTableName) {
        this.appTableName = appTableName;
    }

    public void setAppPkName(String appPkName) {
        this.appPkName = appPkName;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getProcId() {
        return this.procId;
    }

    public String getProcName() {
        return this.procName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAppTableName() {
        return this.appTableName;
    }

    public String getAppPkName() {
        return this.appPkName;
    }

    public int getOrganizationId() {
        return this.organizationId;
    }
}
