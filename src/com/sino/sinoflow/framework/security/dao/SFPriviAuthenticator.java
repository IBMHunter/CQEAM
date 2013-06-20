package com.sino.sinoflow.framework.security.dao;

import java.sql.Connection;

import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.security.dao.PrivilegeAuthenticator;
import com.sino.sinoflow.framework.security.model.PrivilegeAuthenticatorModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class SFPriviAuthenticator extends PrivilegeAuthenticator{

    private PrivilegeAuthenticatorModel authenModel = null;

    public SFPriviAuthenticator(SfUserBaseDTO userAccount, Connection conn) {
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
            SfUserBaseDTO sfUser = (SfUserBaseDTO)userAccount;
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
            Logger.logError(ex);
            throw new QueryException(ex);
        }
    }
}
