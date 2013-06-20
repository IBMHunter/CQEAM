package com.sino.ams.newSite.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newSite.dto.EamAddressAddLDTO;
import com.sino.ams.newSite.model.EamAddressAddLModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;

/** 
 * @author ���� :wangzhipeng
 * @version ����ʱ�䣺Apr 15, 2011 10:18:10 AM 
 * ��˵��:�ص���Ϣ ��������Ϣ 
 *
 */
public class EamAddressAddLDAO extends AMSBaseDAO {

	public EamAddressAddLDAO(SfUserDTO userAccount, DTO dtoParameter,Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamAddressAddLDTO dtoPara =(EamAddressAddLDTO)dtoParameter;
		sqlProducer = new EamAddressAddLModel((SfUserDTO)userAccount, dtoPara);	
	}

}
