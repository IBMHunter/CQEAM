package com.sino.nm.ams.inv.common.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.DatabaseForWeb;

import com.sino.ams.bean.OptionProducer;

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
