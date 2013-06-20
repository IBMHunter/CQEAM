package com.sino.ams.dzyh.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.log.dao.UserLoginDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.file.FileProcessor;
import com.sino.base.log.Logger;
import com.sino.pda.dao.OrderUploadDAO;


/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class DHOrderUploadDAO extends OrderUploadDAO {

	private Map orderArchMaps = null;

	public DHOrderUploadDAO(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
		orderArchMaps = new HashMap();
	}

	/**
	 * ���ܣ�����ɨ���ʲ�����̵㹤��
	 * <B>�ɹ����ύservlet WorkorderSubmit����������������ά����ͳһ����</B>
	 * @param filePath String
	 * @return boolean
	 */
	public boolean uploadOrders(String filePath) {
		boolean operateResult = false;
		try {
			FileProcessor fp = new FileProcessor();
//			fp.copyFile(filePath, "C:\\" + System.currentTimeMillis());

			DHOrderXMLProcessor xmlParser = new DHOrderXMLProcessor(conn);
			xmlParser.parseXML(filePath);
			List headerIds = xmlParser.getHeaderIds();
			String headerId = "";
			boolean itemExist = false;
			String scanStatus = "";
			EamDhCheckHeaderDTO orderHeader = new EamDhCheckHeaderDTO();
			EamDhCheckLineDTO orderLine = new EamDhCheckLineDTO();
			DHOrderHeaderUploadDAO headerUploadDAO = new DHOrderHeaderUploadDAO(userAccount, orderHeader, conn);
			DHOrderLineUploadDAO lineUploadDAO = new DHOrderLineUploadDAO(userAccount, orderLine, conn);

			for (int i = 0; i < headerIds.size(); i++) {
				headerId = (String) headerIds.get(i);
				orderHeader = xmlParser.getDHOrder(headerId);
//				if (orderHeader.getOrderType().equals(LvecDicts.ORD_TYPE2_DHBS)) {
				orderArchMaps.put(headerId, LvecDicts.ARCHIVE_AS_SCAN);//�ͺ��̵㡢�ͺĲ�ɨ���Ǳ��̵����๤���Զ��鵵
//				}
				headerUploadDAO.setDTOParameter(orderHeader);
				orderHeader.println("scanoverDate");
				headerUploadDAO.uploadOrderHeader();
				orderBarcodes = headerUploadDAO.getOrderBarcodes(false);//��ȡ�õ����·�ʱ�豸��Ϣ
				DTOSet orderLines = xmlParser.getOrderLines(headerId); //��ȡ�õ�������ʱ�豸��Ϣ
				if (orderLines != null && !orderLines.isEmpty()) {
					for (int j = 0; j < orderLines.getSize(); j++) {
						orderLine = (EamDhCheckLineDTO) orderLines.getDTO(j);
						scanStatus = orderLine.getScanStatus();
						if (scanStatus.equals(LvecDicts.STATUS_NO)) { //δɨ�赽���ʲ����������һ������
							continue;
						}
						itemExist = orderBarcodes.contains("barcode", orderLine.getBarcode());
						lineUploadDAO.setDTOParameter(orderLine);
						lineUploadDAO.uploadOrderLine(itemExist);
					}
//					orderLine.println();
//					orderLine.setHeaderId(headerId);
					lineUploadDAO.setDTOParameter(orderLine);
					lineUploadDAO.updateLeftBarcodes();//����ʣ���δɨ�赽���豸��״̬
				}
			}
			DHOrderArchiveDAO archiveDAO = new DHOrderArchiveDAO(userAccount, orderHeader, conn); //������ڲ�ɨ���������Զ��鵵
			archiveDAO.setServletConfig(servletConfig);
			archiveDAO.archiveOrders(orderArchMaps);
			operateResult = true;
		} catch (Exception ex) {
			Logger.logError(ex);
		}
		return operateResult;
	}

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		try {
			conn = DBManager.getDBConnection();
			String loginName = "EAMADMIN";
			SfUserDTO userAccount = new SfUserDTO();
			userAccount.setLoginName(loginName);
			userAccount.setPassword("eam");
			UserLoginDAO loginDAO = new UserLoginDAO(userAccount, conn);
			if (loginDAO.isValidUser()) {
				userAccount = (SfUserDTO) loginDAO.getUserAccount();
				DHOrderUploadDAO uploadService = new DHOrderUploadDAO(userAccount, conn);
				String file = "C:\\1232604925968\\outgoing_tmp.xml";
				uploadService.uploadOrders(file);
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
