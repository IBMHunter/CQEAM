package com.sino.ams.newasset.model;


import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsFaDictDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsFaDictModel</p>
 * <p>Description:�����Զ�����SQL��������AmsFaDictModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsFaDictModel extends AMSSQLProducer {

    public AmsFaDictModel(SfUserDTO userAccount, AmsFaDictDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

}
