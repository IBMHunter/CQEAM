package com.sino.rds.design.report.model;

import com.sino.base.db.sql.model.SQLModel;

public interface ReportDefineCopyModel extends ReportDefineBaseModel {


    /**
     * ���ܣ���ȡ������ĸ���SQL
     * @return  ������ĸ���SQL
     */
    SQLModel getReportDefineCopyModel();

}
