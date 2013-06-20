package com.sino.ams.sampling.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.bean.OrderXMLAssistant;
import com.sino.ams.sampling.constant.SamplingDicts;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.ams.sampling.model.SamplingOrderDownloadModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.file.FileUtil;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.pda.dao.OrderDownLoadInterface;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class SamplingOrderDownloadDAO extends AMSBaseDAO  implements OrderDownLoadInterface{
	private StringBuffer orderXml = null;
	private AmsAssetsSamplingLineDTO orderLine = null;
	private boolean xmlProduced = false;

	private static Map orderMap = OrderXMLAssistant.getSamOrderMap();
	private static Map itemMap = OrderXMLAssistant.getDownloadItemMap();

	private SamplingLineDownloadDAO lineDAO = null;

	public SamplingOrderDownloadDAO(SfUserDTO userAccount, AmsAssetsSamplingHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		orderLine = new AmsAssetsSamplingLineDTO();
		lineDAO = new SamplingLineDownloadDAO(userAccount, orderLine, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)dtoParameter;
		sqlProducer = new SamplingOrderDownloadModel((SfUserDTO) userAccount, dto);
	}

	/**
	 * ���ܣ������鹤���������ݣ����������ݿ�״̬
	 * <B>�������ʲ���顢���޳�顢�����Ǳ���</B>
	 */
	private void consructXml() {
		boolean autoCommit = true;
		boolean operateResult = false;
		try {
			orderXml = new StringBuffer();
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
//			startRootXml();//�����繤���ϲ���������Ҫ
			DTOSet orders = getDownloadOrders();
			if (orders != null && !orders.isEmpty()) {
				int orderCount = orders.getSize();
				AmsAssetsSamplingHeaderDTO headerDTO = null;
				for (int i = 0; i < orderCount; i++) {
					headerDTO = (AmsAssetsSamplingHeaderDTO) orders.getDTO(i);
					setOrderParameter(headerDTO);
					downloadOrder();//�ı乤��״̬Ϊ������
					startOrderXml();
					downloadAssets(getDownloadAssets());
					endOrderXml();
				}
			}
//			endRootXml();//�����繤���ϲ���������Ҫ
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (QueryException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (ReflectException ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (operateResult) {
					conn.commit();
				} else {
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
	}

	/**
	 * ���ܣ������鹤���ڵ���ʼxml
	 * @throws ReflectException
	 */
	private void startOrderXml() throws ReflectException {
		AmsAssetsSamplingHeaderDTO order = (AmsAssetsSamplingHeaderDTO)dtoParameter;
		orderXml.append(WorldConstant.ENTER_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append("<");
		orderXml.append(OrderXMLAssistant.getOrderName());
		Iterator pdaKeys = orderMap.keySet().iterator();
		String pdaField = "";
		String serverField = "";
		String nodeValue = "";
		while (pdaKeys.hasNext()) {
			pdaField = (String) pdaKeys.next();
			serverField = (String) orderMap.get(pdaField);
			nodeValue = String.valueOf(ReflectionUtil.getProperty(order, serverField));
			orderXml.append(" ");
			orderXml.append(pdaField);
			orderXml.append("=\"");
			orderXml.append(StrUtil.xmlFormat(nodeValue));
			orderXml.append("\"");
		}
		orderXml.append(">");
		orderXml.append(WorldConstant.ENTER_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
	}


	/**
	 * ���ܣ���ȡ�����صĳ�鹤��
	 * @return DTOSet
	 * @throws QueryException
	 */
	private DTOSet getDownloadOrders() throws QueryException {
		SamplingOrderDownloadModel modelProducer = (SamplingOrderDownloadModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getDownloadOrdersModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsSamplingHeaderDTO.class.getName());
		simp.setCalPattern(LINE_PATTERN);
		simp.executeQuery();
		return simp.getDTOSet();
	}

	/**
	 * ���ܣ���ȡĳ�����¿����ص��豸
	 * @return DTOSet
	 * @throws QueryException
	 */
	private DTOSet getDownloadAssets() throws QueryException {
		AmsAssetsSamplingHeaderDTO headerDTO = (AmsAssetsSamplingHeaderDTO)dtoParameter;
		orderLine.setHeaderId(headerDTO.getHeaderId());
		lineDAO.setDTOParameter(orderLine);
		return lineDAO.getDownloadAssets();
	}

	/**
	 * ���ܣ����ô����ع��������ͺ�״̬
	 * @param headerDTO AmsAssetsSamplingHeaderDTO
	 */
	private void setOrderParameter(AmsAssetsSamplingHeaderDTO headerDTO) {
		headerDTO.setOrderStatus(SamplingDicts.ORDER_STS1_DOWNLOADED);
		String orderTypeName = headerDTO.getOrderTypeName();
		if (headerDTO.getOrderType().equals(SamplingDicts.ORDR_NO_MARK)) {
			orderTypeName = SamplingDicts.ASS_SAM_PDA;
		}
		headerDTO.setOrderType(orderTypeName);
		setDTOParameter(headerDTO);
	}

	/**
	 * ���ܣ������豸���ݵ�XML�ĵ�
	 * @param orderLines DTOSet
	 * @throws ReflectException
	 */
	private void downloadAssets(DTOSet orderLines) throws ReflectException {
		if (orderLines != null && !orderLines.isEmpty()) {
			int itemCount = orderLines.getSize();
			for (int j = 0; j < itemCount; j++) {
				orderLine = (AmsAssetsSamplingLineDTO) orderLines.getDTO(j);
				constructLineXML((j == (itemCount - 1)));
			}
		}
	}


	/**
	 * ���ܣ��������ʲ�xml��Ϣ
	 * @param isLastAssets boolean
	 * @throws ReflectException
	 */
	private void constructLineXML(boolean isLastAssets) throws ReflectException {
		orderXml.append("<");
		orderXml.append(OrderXMLAssistant.getItemName());
		Iterator pdaKeys = itemMap.keySet().iterator();
		String pdaField = "";
		String serverField = "";
		String nodeValue = "";
		while (pdaKeys.hasNext()) {
			pdaField = (String) pdaKeys.next();
			serverField = (String) itemMap.get(pdaField);
			nodeValue = String.valueOf(ReflectionUtil.getProperty(orderLine, serverField));
			nodeValue = StrUtil.xmlFormat(nodeValue);
			if (pdaField.equals("status")) {
				nodeValue = SamplingDicts.SACN_NO;
			}
			orderXml.append(" ");
			orderXml.append(pdaField);
			orderXml.append("=\"");
			orderXml.append(nodeValue);
			orderXml.append("\"");
		}
		orderXml.append("/>");
		orderXml.append(WorldConstant.ENTER_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		if (!isLastAssets) {
			orderXml.append(WorldConstant.TAB_CHAR);
		}
	}

	/**
	 * ���ܣ������鹤���ڵ����xml
	 */
	private void endOrderXml() {
		orderXml.append("</");
		orderXml.append(OrderXMLAssistant.getOrderName());
		orderXml.append(">");
//		orderXml.append(WorldConstant.ENTER_CHAR);
//		orderXml.append(WorldConstant.TAB_CHAR);
//		orderXml.append(WorldConstant.TAB_CHAR);
	}

	/**
	 * ���ܣ�����ѭ�������еĳ�鹤��״̬
	 * @throws DataHandleException
	 */
	private void downloadOrder() throws DataHandleException {
		SamplingOrderDownloadModel modelProducer = (SamplingOrderDownloadModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getOrderDownloadModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ���ȡ���ص�xml�ļ�
	 * @return StringBuffer
	 * @throws QueryException
	 */
	public StringBuffer getOrderXml() throws QueryException {
		if (!xmlProduced) {
			consructXml();
			xmlProduced = true;
		}
		return orderXml;
	}

	/**
	 * ���ܣ�����xml��ʼ���ڵ�
	 */
	private void startRootXml() {
		orderXml.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?> ");
		orderXml.append(WorldConstant.ENTER_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append("<");
		orderXml.append(OrderXMLAssistant.getRootName());
		orderXml.append(">");
	}


	/**
	 * ���ܣ��������ڵ�
	 */
	private void endRootXml() {
		orderXml.append(WorldConstant.ENTER_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append("</");
		orderXml.append(OrderXMLAssistant.getRootName());
		orderXml.append(">");
	}

	public static void main(String[] args) throws Exception{
		SfUserDTO user = new SfUserDTO();
		user.setUserId(1);
		AmsAssetsSamplingHeaderDTO dto = new AmsAssetsSamplingHeaderDTO();
		Connection conn = null;
		try {
			conn = DBManager.getDBConnection();
			SamplingOrderDownloadDAO downDAO = new SamplingOrderDownloadDAO(user, dto, conn);
			FileUtil.saveStrContent(downDAO.getOrderXml(), "C:\\workorder.xml");
		} finally{
			DBManager.closeDBConnection(conn);
			System.exit(0);
		}
	}
}
