package com.sino.ams.match.model;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ��ʿ��
 * @version 0.1
 *          Date: 2008-12-08
 */
public class ManualCtMatchModel extends AMSSQLProducer {

	public ManualCtMatchModel(BaseUserDTO userAccount, DTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

}
