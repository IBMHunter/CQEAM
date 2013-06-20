package com.sino.pda.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sino.ams.dzyh.dao.DHOrderDownloadDAO;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.log.dao.UserLoginDAO;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dao.ChkOrderDownLoadDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.urgenttrans.dto.UrgentHeaderDTO;
import com.sino.ams.newasset.urgenttrans.pda.dao.UrgentOrderInDownloadDAO;
import com.sino.ams.newasset.urgenttrans.pda.dao.UrgentOrderOutDownloadDAO;
import com.sino.ams.sampling.dao.SamplingOrderDownloadDAO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.file.FileUtil;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OrderDownloadService{
	private SfUserDTO userAccount = null;
	private Connection conn = null;

	private static Map DAODTOMap = new HashMap();

	static {
		DAODTOMap.put(ChkOrderDownLoadDAO.class.getName(), AmsAssetsCheckHeaderDTO.class);//�̵�
		DAODTOMap.put(SamplingOrderDownloadDAO.class.getName(), AmsAssetsSamplingHeaderDTO.class);//���(ɽ����)
		DAODTOMap.put(DHOrderDownloadDAO.class.getName(), EamDhCheckHeaderDTO.class);//��ֵ�׺ġ������Ǳ��̵�(������)
		DAODTOMap.put(UrgentOrderOutDownloadDAO.class.getName(), UrgentHeaderDTO.class);//��������������
		DAODTOMap.put(UrgentOrderInDownloadDAO.class.getName(), UrgentHeaderDTO.class);//��������������
	}

	public OrderDownloadService(SfUserDTO userAccount, Connection conn){
		this.userAccount = userAccount;
		this.conn = conn;
	}

	/**
	 * ���ܣ���ȡ���ع�����xml����
	 * @return StringBuffer
	 * @throws QueryException
	 */
	public StringBuffer getOrderXml() throws QueryException {
		StringBuffer orderXML = new StringBuffer();
		try {
			OrderDownLoadInterface downloadDAO = null;
			Iterator daos = DAODTOMap.keySet().iterator();
			DTO dtoParameter = null;
			String DAOClass = "";
			Class DTOClass = null;
			Object[] daoArgs = new Object[3];
			daoArgs[0] = userAccount;
			daoArgs[2] = conn;
			while (daos.hasNext()) {
				DAOClass = (String) daos.next();
				DTOClass = (Class) DAODTOMap.get(DAOClass);
				dtoParameter = (DTO) DTOClass.newInstance();
				ReflectionUtil.setProperty(dtoParameter, "orderStatus", AssetsDictConstant.CHK_STATUS_DISTRUIBUTED);
				daoArgs[1] = dtoParameter;
				downloadDAO = (OrderDownLoadInterface) ReflectionUtil.getInstance(DAOClass, daoArgs);
				orderXML.append(downloadDAO.getOrderXml());
			}
		} catch (ReflectException ex) {
			ex.printLog();
			throw new QueryException(ex);
		} catch (IllegalAccessException ex) {
			Logger.logError(ex);
			throw new QueryException(ex);
		} catch (InstantiationException ex) {
			Logger.logError(ex);
			throw new QueryException(ex);
		}
		return orderXML;
	}

	public static void main(String[] args) throws Exception{
		Connection conn = null;
		try {
			conn = DBManager.getDBConnection();
			String loginName = "4110LVTIANFEI";
			SfUserDTO userAccount = new SfUserDTO();
			userAccount.setLoginName(loginName);
			userAccount.setPassword("a");
			UserLoginDAO loginDAO = new UserLoginDAO(userAccount, conn);
			if (loginDAO.isValidUser()) {
				userAccount = (SfUserDTO) loginDAO.getUserAccount();
				StringBuffer workorder = new StringBuffer();
				OrderDownloadService downService = new OrderDownloadService(userAccount, conn);
				workorder.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
				workorder.append(WorldConstant.ENTER_CHAR);
				workorder.append(WorldConstant.TAB_CHAR);
				workorder.append("<workorders>");
				workorder.append(WorldConstant.ENTER_CHAR);
				workorder.append(WorldConstant.TAB_CHAR);
				workorder.append(WorldConstant.TAB_CHAR);
				workorder.append(downService.getOrderXml());
				workorder.append(WorldConstant.ENTER_CHAR);
				workorder.append(WorldConstant.TAB_CHAR);
				workorder.append("</workorders>");
				String file = "C:\\workorder.xml";
				FileUtil.saveStrContent(workorder, file);
				System.out.println("download workorders successfully...");
			} else {
				System.out.println("user is not a valid user");
			}
		} finally{
			DBManager.closeDBConnection(conn);
//			System.exit(0);
//			<?xml version="1.0" encoding="GB2312" ?>
		}
	}
}
