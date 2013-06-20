package com.sino.pda.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.newasset.dao.ChkOrderPDACreateDAO;
import com.sino.ams.newasset.urgenttrans.constant.UrgentAppConstant;
import com.sino.ams.newasset.urgenttrans.pda.dao.UrgentOrderCreateDAO;
import com.sino.ams.sampling.constant.SamplingDicts;
import com.sino.ams.sampling.dao.SampOrderPDACreateDAO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.exception.ReflectException;
import com.sino.base.util.ReflectionUtil;
import com.sino.framework.security.dto.FilterConfigDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OrderCreateFactory {
	private static OrderCreateFactory instance = null;
	private static Object[] daoArgs = new Object[3];

	private static Map typeDAOMap = new HashMap();

	static{
		typeDAOMap.put(SamplingDicts.ASS_SAM_PDA, SampOrderPDACreateDAO.class.getName());
		typeDAOMap.put(SamplingDicts.ASS_CHK_PAD, ChkOrderPDACreateDAO.class.getName());
		typeDAOMap.put(SamplingDicts.INS_CHK_PAD, ChkOrderPDACreateDAO.class.getName());
		typeDAOMap.put(SamplingDicts.RNT_CHK_PAD, ChkOrderPDACreateDAO.class.getName());
		
		typeDAOMap.put(UrgentAppConstant.PDA_CREATE_TYPE_NAME , UrgentOrderCreateDAO.class.getName());
	}

	private OrderCreateFactory (FilterConfigDTO filterConfig) {
		daoArgs[1] = filterConfig;
	}

	/**
	 * ���ܣ���ȡ�������ع�����
	 * @param filterConfig FilterConfigDTO
	 * @return OrderCreateFactory
	 */
	public static OrderCreateFactory getInstance(FilterConfigDTO filterConfig) {
		if(instance == null){
			instance = new OrderCreateFactory(filterConfig);
		}
		return instance;
	}

	/**
	 * ���ܣ���ȡ��������DAO�ࡣ
	 * @param conn ���ݿ�����
	 * @param filePath ���ع����ڷ������ı���·��
	 * @param orderType String �������ͣ��̵㡢���
	 * @return OrderCreateDAO �ɹ��򷵻ش�����Ӧ������DAO���򣬷��򷵻�null
	 */
	public OrderCreateDAO getOrderCreator(Connection conn, String filePath, String orderType){
		OrderCreateDAO createDAO = null;
		try {
			daoArgs[0] = conn;
			daoArgs[2] = filePath;
			String daoClass = (String) typeDAOMap.get(orderType);
			createDAO = (OrderCreateDAO) ReflectionUtil.getInstance(daoClass, daoArgs);
		} catch (ReflectException ex) {
			ex.printLog();
		}
		return createDAO;
	}

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			FilterConfigDTO filterConfig = new FilterConfigDTO();
			filterConfig.setLoginName("loginName");
			OrderCreateFactory fac = OrderCreateFactory.getInstance(filterConfig);
			String sFile = "C:\\workorder.xml";
			String orderType = "���";
			conn = DBManager.getDBConnection();
			OrderCreateDAO createDAO = fac.getOrderCreator(conn, sFile, orderType);
			if (createDAO.hasPreviousOrder()) {
				System.out.println("�õص���δ�鵵��������");
			} else {
				String orderNo = createDAO.createOrder();
				System.out.println("�½����������" + orderNo);
			}
		} finally{
			DBManager.closeDBConnection(conn);
//			System.exit(0);
		}
	}
}
