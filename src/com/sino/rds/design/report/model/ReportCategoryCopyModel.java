package com.sino.rds.design.report.model;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.rds.appbase.model.RDSBaseSQLModel;

public interface ReportCategoryCopyModel extends RDSBaseSQLModel {

    /**
     * ���ܣ���ȡ��������ֶθ��Ƶ�SQL
     * @return  ��������ֶθ��Ƶ�SQL
     */
    SQLModel getReportCategoryCopyModel();

}
