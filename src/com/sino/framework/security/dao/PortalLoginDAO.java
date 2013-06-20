package com.sino.framework.security.dao;

import java.sql.Connection;

import com.sino.base.constant.WebConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.SinoEncryptor;
import com.sino.framework.security.model.PortalLoginModel;

/**
 * 
 * @ϵͳ����: �û���֤
 * @��������:
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Oct 12, 2011
 */
public class PortalLoginDAO {
	private String portalUserId = "";
	private Connection conn = null;
	private String loginName = "";
	private String decodeStr = "SinoETS_SSO";

	public PortalLoginDAO(String portalUserId, Connection conn) {
		super();
		this.portalUserId = SinoEncryptor.decode(decodeStr, portalUserId);
		this.conn = conn;
	}

	/**
	 * �жϵ�ǰOA�û��Ƿ��ڱ�ϵͳ�д��ڶ�Ӧ�û���Ϣ
	 * 
	 * @return
	 * @throws QueryException 
	 * @throws ContainerException 
	 */
	public boolean isValidUserInCurSystem() throws QueryException, ContainerException {
		boolean isValidUser = false;
        PortalLoginModel loginModel = new PortalLoginModel();
        SQLModel sqlModel = loginModel.getUserPortalModel(portalUserId);
        SimpleQuery gridBean = new SimpleQuery(sqlModel, conn);
        gridBean.executeQuery();
        RowSet rows = gridBean.getSearchResult();
        if (rows != null && rows.getSize() > 0) {
            Row row = rows.getRow(0);
            loginName = row.getStrValue("LOGIN_NAME");
            isValidUser = true;
        }
        return isValidUser;
	}

	/**
	 * �ж�����portal���û��Ƿ�ϵͳ�Ϸ��û�
	 * @throws QueryException 
	 * @throws ContainerException 
	 */
	public boolean isValidUser() throws QueryException, ContainerException {
		boolean isValidUser = false;
        if (!isValidUserInCurSystem()) {     //�ж��û��Ƿ����ж�Ӧ��OA��¼��
            Connection co = null;
            PortalLoginModel loginModel = new PortalLoginModel();
            SQLModel sqlModel = loginModel.getLoginNameByOaName(portalUserId);
            try {
//                co = DBManager.getDBConnection(WebConstant.DB_POOL_MIS);        //����ΪMIS�����ӳأ���ʹ��DBLINK
                SimpleQuery gridBean = new SimpleQuery(sqlModel, co);
                gridBean.executeQuery();
                RowSet rows = gridBean.getSearchResult();
                if (rows != null && rows.getSize() > 0) {
                    Row row = rows.getRow(0);
                    loginName = row.getStrValue("LOGIN_NAME");
                    isValidUser = true;
                }
            } catch (ContainerException ex) {
                throw new QueryException(ex.getMessage());
            } finally {
                DBManager.closeDBConnection(co);

            }
        } else {
            isValidUser = true;
        }
        return isValidUser;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
