package com.sino.pda.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.dzyh.dao.DHOrderUploadDAO;
import com.sino.ams.log.dao.UserLoginDAO;
import com.sino.ams.newasset.dao.ChkOrderUpLoadDAO;
import com.sino.ams.newasset.urgenttrans.pda.dao.UrgentOrderInUploadDAO;
import com.sino.ams.newasset.urgenttrans.pda.dao.UrgentOrderOutUploadDAO;
import com.sino.ams.sampling.dao.SamplingOrderUploadDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ������(�̵㹤������鹤��)����ͳһ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OrderUploadService {

	private static List uploadDAOs = new ArrayList();
	private Object[] daoArgs = new Object[2];
	private ServletConfigDTO servletConfig = null;

	static {
		uploadDAOs.add(ChkOrderUpLoadDAO.class.getName());
		uploadDAOs.add(SamplingOrderUploadDAO.class.getName());
		uploadDAOs.add(DHOrderUploadDAO.class.getName());
		uploadDAOs.add(UrgentOrderInUploadDAO.class.getName() );
		uploadDAOs.add(UrgentOrderOutUploadDAO.class.getName() );
	}

	public OrderUploadService(SfUserDTO userAccount, Connection conn) {
		daoArgs[0] = userAccount;
		daoArgs[1] = conn;
	}

	public void setServletConfig(ServletConfigDTO servletConfig){
		this.servletConfig = servletConfig;
	}

	/**
	 * ���ܣ����ع�����
	 * @param filePath String
	 * @return boolean
	 */
	public boolean uploadOrder(String filePath) {
		boolean operateResult = true;//Ϊ��֤�����ͳһ,��ʹû�й�������ҲӦ������Ϊtrue
		try {
			OrderUploadDAO uploadDAO = null;
			String DAOClass = "";
			for (int i = 0; i < uploadDAOs.size(); i++) {
				DAOClass = (String) uploadDAOs.get(i);
				uploadDAO = (OrderUploadDAO) ReflectionUtil.getInstance(DAOClass, daoArgs);
				uploadDAO.setServletConfig(servletConfig);
				operateResult = operateResult && uploadDAO.uploadOrders(filePath);
			}
		} catch (Exception ex) {
			Logger.logError(ex);
			operateResult = false;
		}
		return operateResult;
	}


	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			conn = DBManager.getDBConnection();
			String loginName = "YUZHIDAN";
			SfUserDTO userAccount = new SfUserDTO();
			userAccount.setLoginName(loginName);
			userAccount.setPassword("eam");
			UserLoginDAO loginDAO = new UserLoginDAO(userAccount, conn);

			if (loginDAO.isValidUser()) {
				userAccount = (SfUserDTO) loginDAO.getUserAccount();
				OrderUploadService uploadService = new OrderUploadService(userAccount, conn);
				ServletConfigDTO servletConfig = new ServletConfigDTO();
				servletConfig.setProvinceOrgId(714);
				uploadService.setServletConfig(servletConfig);
				String file = "C:\\workorder.xml";
				uploadService.uploadOrder(file);
				System.out.println("uploadOrder succeed...");
			}
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally {
			DBManager.closeDBConnection(conn);
			System.exit(0);
		}
	}
}
