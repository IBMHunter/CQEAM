package com.sino.rds.execute.service;

import com.sino.base.exception.ReportException;

public interface IntersectProducer {

    /**
     * ���ܣ����ɱ�����Ҫ�Ķ�ά��������
     *
     * @throws ReportException ����ʱ�׳������쳣
     */
    public void processIntersectData() throws ReportException;
}