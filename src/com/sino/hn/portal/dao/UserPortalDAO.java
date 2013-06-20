package com.sino.hn.portal.dao;

import java.sql.Connection;
import java.text.ParseException;

import com.mochasoft.portal.encrypt.EncryptorUtil;
import com.sino.ams.log.dao.UserLoginDAO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.base.util.SinoEncryptor;
import com.sino.hn.constant.PortalConstant;
import com.sino.hn.portal.model.UserInfoModel;

/**
 * 
 * @ϵͳ����: 
 * @��������: �����¼
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Oct 28, 2011
 */
public class UserPortalDAO extends UserLoginDAO {
	private String portalUserId = "";
	private Connection conn = null;
	private String loginName = "";
	private String decodeStr = PortalConstant.PARAM_EKEY;
	
	private String isTd = "";
	
	private boolean isTodo = false; //�жϵ�������

//	public UserPortalDAO(BaseUserDTO userAccount, Connection conn) {
//		super(userAccount, conn);
//		this.portalUserId = SinoEncryptor.decode(decodeStr, portalUserId);
//		this.conn = conn;
//	}
	

	/**
	 * ���ܷ���
	 * @param secretKey ������Կ
	 * @param ciphertext ����
	 * @param outTime ����ʧЧʱ�䣬����Ϊ��λ
	 * @return ���ܺ���ַ���
	 */
	public static String decode(String secretKey, String ciphertext, int outTime) {
		String uid = null;
		try {
			uid = EncryptorUtil.decode(secretKey, ciphertext, outTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return uid;
	}

	public UserPortalDAO(String portalUserId, Connection conn) {
		super(null, conn);
		this.portalUserId = portalUserId;
		this.conn = conn;
	}

	/**
	 * �ж�����portal���û��Ƿ�ϵͳ�Ϸ��û�
	 * 
	 * @return boolean
	 * @throws QueryException
	 */
	public boolean isValidUser() throws QueryException {
		if( isTodo ){
			this.portalUserId = UserPortalDAO.decode(decodeStr, portalUserId , 1800 );
		}else{
			this.portalUserId = SinoEncryptor.decode(decodeStr, portalUserId);
		}
		UserInfoModel loginModel = new UserInfoModel(null);
		SQLModel sqlModel = loginModel.getUserLoginModel( portalUserId , isTd );
		return isValidUser( sqlModel, true );

	}

	public String getLoginName() {
		return loginName;
	}

	public void setTodo(boolean isTodo) {
		this.isTodo = isTodo;
	}

	public String getIsTd() {
		return isTd;
	}

	public void setIsTd(String isTd) {
		this.isTd = isTd;
	}

}
