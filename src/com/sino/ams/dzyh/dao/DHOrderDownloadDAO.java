package com.sino.ams.dzyh.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.bean.OrderXMLAssistant;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.dzyh.model.DHOrderDownloadModel;
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
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class DHOrderDownloadDAO extends AMSBaseDAO implements OrderDownLoadInterface{

	private StringBuffer orderXml = null;
	private EamDhCheckLineDTO orderLine = null;
	private boolean xmlProduced = false;

	private static Map orderMap = OrderXMLAssistant.getOrderMap();
	private static Map itemMap = OrderXMLAssistant.getDownloadItemMap();
	private static Map orderMaps = OrderXMLAssistant.getOrderMaps();

	private DHLineDownloadDAO lineDAO = null;


	public DHOrderDownloadDAO(BaseUserDTO userAccount, EamDhCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		orderLine = new EamDhCheckLineDTO();
		lineDAO = new DHLineDownloadDAO(userAccount, orderLine, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO)dtoParameter;
		sqlProducer = new DHOrderDownloadModel(userAccount, dto);
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
				EamDhCheckHeaderDTO headerDTO = null;
				String orderType = "";
				for (int i = 0; i < orderCount; i++) {
					headerDTO = (EamDhCheckHeaderDTO) orders.getDTO(i);
					setOrderParameter(headerDTO);
					downloadOrder();//�ı乤��״̬Ϊ������
					startOrderXml();
					orderType = headerDTO.getOrderType();
					if(!orderType.equals(LvecDicts.ORD_TYPE1_DHBS)){
						downloadAssets(getDownloadAssets());
					}
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
		EamDhCheckHeaderDTO order = (EamDhCheckHeaderDTO)dtoParameter;
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
	}


	/**
	 * ���ܣ���ȡ�����صĳ�鹤��
	 * @return DTOSet
	 * @throws QueryException
	 */
	private DTOSet getDownloadOrders() throws QueryException {
		DHOrderDownloadModel modelProducer = (DHOrderDownloadModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getDownloadOrdersModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(EamDhCheckHeaderDTO.class.getName());
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
		EamDhCheckHeaderDTO headerDTO = (EamDhCheckHeaderDTO)dtoParameter;
		orderLine.setHeaderId(headerDTO.getHeaderId());
		lineDAO.setDTOParameter(orderLine);
		return lineDAO.getDownloadAssets();
	}

	/**
	 * ���ܣ����ô����ع��������ͺ�״̬
	 * @param headerDTO EamDhCheckHeaderDTO
	 */
	private void setOrderParameter(EamDhCheckHeaderDTO headerDTO) {
		headerDTO.setOrderStatus(LvecDicts.ORDER_STS1_DOWNLOADED);
		headerDTO.setOrderType(headerDTO.getOrderTypeValue());
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
				orderLine = (EamDhCheckLineDTO) orderLines.getDTO(j);
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
		orderXml.append(WorldConstant.ENTER_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
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
				nodeValue = LvecDicts.SACN_NO;
			}
			orderXml.append(" ");
			orderXml.append(pdaField);
			orderXml.append("=\"");
			orderXml.append(nodeValue);
			orderXml.append("\"");
		}
		orderXml.append("/>");
		if (!isLastAssets) {
			orderXml.append(WorldConstant.TAB_CHAR);
		}
	}

	/**
	 * ���ܣ������鹤���ڵ����xml
	 */
	private void endOrderXml() {
		orderXml.append(WorldConstant.ENTER_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
		orderXml.append(WorldConstant.TAB_CHAR);
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
		DHOrderDownloadModel modelProducer = (DHOrderDownloadModel)sqlProducer;
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
		user.setUserId( 1955 );
		EamDhCheckHeaderDTO dto = new EamDhCheckHeaderDTO();
		Connection conn = null;
		try {
			conn = DBManager.getDBConnection();
			DHOrderDownloadDAO downDAO = new DHOrderDownloadDAO(user, dto, conn);
			FileUtil.saveStrContent(downDAO.getOrderXml(), "C:\\workorder.xml");
		} finally{
			DBManager.closeDBConnection(conn);
			System.exit(0);
		}
	}
}
