package com.sino.ams.inv.common.bean;

import java.sql.Connection;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: SinoIES</p>
 * <p>Description: �����ƶ�IESϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 * @author ��ʿ��
 * @version 1.0
 */
public class InvOptionProducer extends OptionProducer{


	public InvOptionProducer(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
	}
}
