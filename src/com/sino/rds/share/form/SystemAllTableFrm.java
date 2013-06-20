package com.sino.rds.share.form;

import com.sino.rds.appbase.form.RDSBaseFrm;
import com.sino.rds.foundation.web.component.WebOptions;

/**
 * <p>Title: ��������ģ�� RDS_DATA_MODEL</p>
 * <p>Description: ���빤���Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class SystemAllTableFrm extends RDSBaseFrm {
    private String tableName = "";
    private String tableComments = "";
    private String optionComments = "";
    private String tableType = "";
    private String tableTypeName = "";
    private String connectionId = "";
    private WebOptions tableTypeOptions = null;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComments() {
        return tableComments;
    }

    public void setTableComments(String tableComments) {
        this.tableComments = tableComments;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableTypeName() {
        return tableTypeName;
    }

    public void setTableTypeName(String tableTypeName) {
        this.tableTypeName = tableTypeName;
    }

    public WebOptions getTableTypeOptions() {
        return tableTypeOptions;
    }

    public void setTableTypeOptions(WebOptions tableTypeOptions) {
        this.tableTypeOptions = tableTypeOptions;
    }

    public String getOptionComments() {
        return optionComments;
    }

    public void setOptionComments(String optionComments) {
        this.optionComments = optionComments;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }
}