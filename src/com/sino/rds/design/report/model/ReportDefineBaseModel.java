package com.sino.rds.design.report.model;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.rds.appbase.model.RDSBaseSQLModel;

public interface ReportDefineBaseModel extends RDSBaseSQLModel {

    /**
     * ���ܣ���ȡ������һ����������SQL
     * @return ������һ����������SQL
     */
    SQLModel getNextReportCodeModel();

}
