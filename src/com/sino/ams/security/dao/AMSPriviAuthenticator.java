package com.sino.ams.security.dao;

import java.sql.Connection;

import com.sino.ams.security.model.PrivilegeAuthenticatorModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.framework.security.dao.PrivilegeAuthenticator;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AMSPriviAuthenticator extends PrivilegeAuthenticator{

	private PrivilegeAuthenticatorModel authenModel = null;

    public AMSPriviAuthenticator(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
		authenModel = new PrivilegeAuthenticatorModel(userAccount);
    }

	/**
	 * ���ܣ�ִ��Ȩ����֤
	 * @param requestURL ��ǰ��֤����Դ
	 * @throws QueryException
	 */
	protected void authenticate(String requestURL)throws QueryException{
		try {
			SfUserDTO sfUser = (SfUserDTO)userAccount;
			if(sfUser.isSysAdmin()){
				hasPrivilege = true;
			} else {
				SQLModel sqlModel = authenModel.getHasInControlModel(requestURL);
				SimpleQuery simpl = new SimpleQuery(sqlModel, conn);
				simpl.executeQuery();
				if (simpl.hasResult()) {
					Row resource = simpl.getFirstRow();
					sqlModel = authenModel.getAuthenticateModel(requestURL);
					simpl.setSql(sqlModel);
					simpl.executeQuery();
					hasPrivilege = simpl.hasResult();
					if (!hasPrivilege) {
						urlName = resource.getStrValue("RES_NAME");
					}
				} else {//����ԴΪδ�����ڲ˵���Ŀ�е���Դ��Ĭ��ΪΪ��Ȩ��
					hasPrivilege = true;
				}
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
	}
}
