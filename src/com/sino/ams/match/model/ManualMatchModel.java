package com.sino.ams.match.model;

import com.sino.base.dto.DTO;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2007-11-28
 */
public class ManualMatchModel extends AMSSQLProducer {
    public ManualMatchModel(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }
}
