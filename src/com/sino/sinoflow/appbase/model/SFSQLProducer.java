package com.sino.sinoflow.appbase.model;

import com.sino.base.dto.DTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SinoFlo</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class SFSQLProducer extends BaseSQLProducer {//����Ĵ�����Ϊ����ȥ����SQLProducer�ദ�������û�����
    protected SfUserBaseDTO userAccount = null;

    public SFSQLProducer(SfUserBaseDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
        this.userAccount = (SfUserBaseDTO)userAccount;
    }
}