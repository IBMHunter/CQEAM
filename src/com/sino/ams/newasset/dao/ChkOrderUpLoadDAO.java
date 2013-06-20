package com.sino.ams.newasset.dao;

import java.sql.Connection;
import java.util.List;

import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.log.Logger;
import com.sino.ams.log.dao.UserLoginDAO;
import com.sino.ams.newasset.bean.ChkOrderXMLProcessor;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.pda.dao.OrderUploadDAO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ChkOrderUpLoadDAO extends OrderUploadDAO{

	public ChkOrderUpLoadDAO(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
	}

	/**
	 * ���ܣ�����ɨ���ʲ�����̵㹤��
	 * <B>�ɹ�������servlet WorkorderSubmit����������������ά����ͳһ</B>
	 * @param filePath String
	 * @return boolean
	 */
	public boolean uploadOrders(String filePath) {
		boolean operateResult = false;
		try {
			ChkOrderXMLProcessor xmlParser = new ChkOrderXMLProcessor(conn);
			xmlParser.parseXML(filePath);

			List headerIds = xmlParser.getHeaderIds();
			String headerId = "";
			boolean itemExist = false;
			String scanStatus = "";

			AmsAssetsCheckHeaderDTO orderHeader = new AmsAssetsCheckHeaderDTO();
			ChkOrderHeaderUploadDAO headerDAO = new ChkOrderHeaderUploadDAO(userAccount, orderHeader, conn);
			AmsAssetsCheckLineDTO orderLine = new AmsAssetsCheckLineDTO();
			ChkOrderLineUploadDAO lineDAO = new ChkOrderLineUploadDAO(userAccount, orderLine, conn);

			for (int i = 0; i < headerIds.size(); i++) {
				headerId = (String) headerIds.get(i);
				orderHeader = xmlParser.getChkOrder(headerId);
				headerDAO.setDTOParameter(orderHeader);
//				headerDAO.uploadOrderHeader();

				orderBarcodes = headerDAO.getOrderBarcodes(true);//��ȡ�õ������豸��Ϣ
				DTOSet chkLines = xmlParser.getChkLines(headerId);//��ȡ���ι����ύ���豸��Ϣ
				if (chkLines != null && !chkLines.isEmpty()) {
					for (int j = 0; j < chkLines.getSize(); j++) {
						orderLine = (AmsAssetsCheckLineDTO) chkLines.getDTO(j);
						scanStatus = orderLine.getScanStatus();
						if (scanStatus.equals(AssetsDictConstant.STATUS_NO)) { //δɨ�赽���ʲ�
							continue;
						}
						itemExist = orderBarcodes.contains("barcode", orderLine.getBarcode());
						lineDAO.setDTOParameter(orderLine);
						lineDAO.uploadOrderLine(itemExist);
					}
//					lineDAO.updateLeftBarcodes();
				}
			}
			operateResult = true;
		} catch (Exception ex) {
			Logger.logError(ex);
		}
		return operateResult;
	}

	public static void main(String[] args) throws Exception {
		Connection conn = DBManager.getDBConnection();
		String loginName = "4022MAWEI";
		SfUserDTO userAccount = new SfUserDTO();
		userAccount.setLoginName(loginName);
		userAccount.setPassword("eam");
		UserLoginDAO loginDAO = new UserLoginDAO(userAccount, conn);
		if (loginDAO.isValidUser()) {
			userAccount = (SfUserDTO) loginDAO.getUserAccount();
			ChkOrderUpLoadDAO uploadDAO = new ChkOrderUpLoadDAO(userAccount, conn);
			uploadDAO.uploadOrders("c:\\outgoing_tmp.xml");
		}
		DBManager.closeDBConnection(conn);
		System.exit(0);
	}
}
