package com.sino.rds.execute.service.impl;

import com.sino.base.exception.ReportException;
import com.sino.rds.execute.dao.DimensionProduceDAO;
import com.sino.rds.share.form.IntersectReportExecuteFrm;

public class FixedBothProducerImpl extends DefaultProducerImpl {

    public FixedBothProducerImpl(IntersectReportExecuteFrm executeFrm) {
        super(executeFrm);
    }

    public void processAboveDimensions() throws ReportException {
        DimensionProduceDAO dimensionProducer = new DimensionProduceDAO();
    }

    /**
     * ����:���ɱ������γ��ֵ
     *
     * @throws com.sino.base.exception.ReportException
     *          ����ʱ�׳������쳣
     */
    public void processLeftDimensions() throws ReportException {
        DimensionProduceDAO dimensionProducer = new DimensionProduceDAO();
    }
}