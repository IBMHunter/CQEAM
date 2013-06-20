package com.sino.ams.sampling.dao;

import java.sql.Connection;
import java.util.List;

import com.sino.ams.sampling.constant.SamplingDicts;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTOSet;
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
public class SamplingOrderUploadDAO extends OrderUploadDAO{

	public SamplingOrderUploadDAO(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
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
			SamplingOrderXMLProcessor xmlParser = new SamplingOrderXMLProcessor(conn);
			xmlParser.parseXML(filePath);
			List headerIds = xmlParser.getHeaderIds();
			String headerId = "";
			boolean itemExist = false;
			String scanStatus = "";

			AmsAssetsSamplingHeaderDTO orderHeader = new AmsAssetsSamplingHeaderDTO();
			AmsAssetsSamplingLineDTO orderLine = new AmsAssetsSamplingLineDTO();
			OrderHeaderUploadDAO headerDAO = new OrderHeaderUploadDAO(userAccount, orderHeader, conn);
			OrderLineUploadDAO lienDAO = new OrderLineUploadDAO(userAccount, orderLine, conn);

			for (int i = 0; i < headerIds.size(); i++) {
				headerId = (String) headerIds.get(i);
				orderBarcodes = headerDAO.getOrderBarcodes(false,orderHeader, headerId);
				orderHeader = xmlParser.getSamplingOrder(headerId);
				headerDAO.setDTOParameter(orderHeader);
				headerDAO.uploadOrderHeader();

				DTOSet orderLines = xmlParser.getOrderLines(headerId);
				if (orderLines != null && !orderLines.isEmpty()) {
					for (int j = 0; j < orderLines.getSize(); j++) {
						orderLine = (AmsAssetsSamplingLineDTO) orderLines.getDTO(j);
						scanStatus = orderLine.getScanStatus();
						if (scanStatus.equals(SamplingDicts.STATUS_NO)) { //δɨ�赽���ʲ�
							continue;
						}
						itemExist = orderBarcodes.contains("barcode", orderLine.getBarcode());
						lienDAO.setDTOParameter(orderLine);
						lienDAO.uploadOrderLine(itemExist);
					}
					orderLine.setHeaderId(headerId);
					lienDAO.setDTOParameter(orderLine);
					lienDAO.updateLeftBarcodes();
				}
			}
			operateResult = true;
		} catch (Exception ex) {
			Logger.logError(ex);
		}
		return operateResult;
	}
}
