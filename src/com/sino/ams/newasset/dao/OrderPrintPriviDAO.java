package com.sino.ams.newasset.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 *
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 *
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 *
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class OrderPrintPriviDAO extends AMSBaseDAO {

    public OrderPrintPriviDAO(SfUserDTO userAccount,
                              AmsAssetsTransHeaderDTO dtoParameter,
                              Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     *
     * @param userAccount BaseUserDTO
     * @param dtoParameter DTO
     * @todo Implement this com.sino.framework.dao.BaseDAO method
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
    }
}
