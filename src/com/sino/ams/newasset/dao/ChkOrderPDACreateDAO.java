package com.sino.ams.newasset.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.XMLParseException;
import com.sino.base.log.Logger;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.bean.OrderPDACreateParser;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckBatchDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.model.ChkOrderPDACreateModel;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.pda.dao.OrderCreateDAO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: �й��ƶ��ʲ�ʵ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ChkOrderPDACreateDAO extends OrderCreateDAO{

	/**
	 *
	 * @param conn ���ݿ�����
	 * @param filterConfig FilterConfigDTO �����ļ���Ϣ
	 * @param filePath String ���صĹ����ļ�
	 */
	public ChkOrderPDACreateDAO(Connection conn, FilterConfigDTO filterConfig, String filePath) {
		super(conn, filterConfig, filePath);
	}

	/**
	 * ���ܣ���ʼ����������
	 * @throws XMLParseException
	 */
	protected void initOrderData() throws XMLParseException {
		OrderPDACreateParser chkParser = new OrderPDACreateParser();
		chkParser.parseXML(filterConfig, conn, filePath);
		orderParameter = chkParser.getCheckOrder();
		userAccount = chkParser.getCreatedUser();
		AmsAssetsCheckHeaderDTO checkOrder = (AmsAssetsCheckHeaderDTO)orderParameter;
		sqlProducer = new ChkOrderPDACreateModel(userAccount, checkOrder);
	}

	/**
	 * ���ܣ��ж��ڸõص��Ƿ��й���δ�鵵�Ĺ���
	 * @return true��ʾ�õص���������δ������
	 */
	public boolean hasPreviousOrder() {
		boolean hasPreviousOrder = false;
		try {
			ChkOrderPDACreateModel modelProducer = (ChkOrderPDACreateModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getHasPreviousOrderModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			hasPreviousOrder = simp.hasResult();
		} catch (QueryException ex) {
			ex.printLog();
		}
		return hasPreviousOrder;
	}

	/**
	 * ���ܣ�����������
	 * @throws DataHandleException
	 */
	protected void createOrderBatch() throws DataHandleException {
		try {
			AmsAssetsCheckBatchDTO batchDTO = new AmsAssetsCheckBatchDTO();
            SeqProducer sq=new SeqProducer(conn);
			String batchId = sq.getGUID();
			batchDTO.setBatchId(batchId);
			AmsAssetsCheckHeaderDTO checkOrder = (AmsAssetsCheckHeaderDTO)orderParameter;

			checkOrder.setBatchId(batchId);
			batchDTO.setGroupId(checkOrder.getGroupId());
			batchDTO.setCostCenterCode(checkOrder.getCostCenterCode());
			batchDTO.setCreatedBy(userAccount.getUserId());
			batchDTO.setDistributeBy(userAccount.getUserId());
			batchDTO.setCurrCreationDate();
			batchDTO.setOrderType(checkOrder.getOrderType());
			batchDTO.setCurrCalendar("distributeDate");
			batchDTO.setTaskDesc("PDA�����ʲ��̵㹤��");
			batchDTO.setBatchStatus(AssetsDictConstant.CHK_STATUS_DISTRUIBUTED);
			String companyCode = userAccount.getCompanyCode();
			String transType = AssetsDictConstant.ASS_CHK_TASK;
			OrderNumGenerator numberProducer = new OrderNumGenerator(conn,companyCode, transType);
			numberProducer.setOrderLength(3);
			batchDTO.setBatchNo(numberProducer.getOrderNum());

			ChkOrderPDACreateModel modelProducer = (ChkOrderPDACreateModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getOrderBatchCreateModel(batchDTO);
			DBOperator.updateRecord(sqlModel, conn);

			setOrderData(checkOrder);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} catch (Exception ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}


	/**
	 * ���ܣ������̵㹤����Ϣ���������̵㹤���Ĺ�����
	 * @throws DataHandleException
	 */
	protected void createOrderHeader() throws DataHandleException {
		String headerId = "";
		try {
            SeqProducer seqProducer=new SeqProducer(conn);
			headerId =seqProducer.getGUID();
			AmsAssetsCheckHeaderDTO checkOrder = (AmsAssetsCheckHeaderDTO)orderParameter;

			checkOrder.setHeaderId(headerId);
			String companyCode = userAccount.getCompanyCode();
			checkOrder.setTransType(checkOrder.getOrderType());
			OrderNumGenerator numberProducer = new OrderNumGenerator(conn, companyCode, checkOrder.getOrderType());
			orderNo = numberProducer.getOrderNum();
			checkOrder.setTransNo(orderNo);
			checkOrder.setCreatedBy(userAccount.getUserId());
			checkOrder.setOrganizationId(userAccount.getOrganizationId());
			checkOrder.setImplementBy(userAccount.getUserId());
			checkOrder.setCurrCalendar("distributeDate");
			checkOrder.setCurrCalendar("startTime");
			checkOrder.setImplementDays(1);
			checkOrder.setOrderStatus(AssetsDictConstant.CHK_STATUS_DISTRUIBUTED);
			checkOrder.setFlow2End(true);

			ChkOrderPDACreateModel modelProducer = (ChkOrderPDACreateModel)sqlProducer;
			modelProducer.setDTOParameter(checkOrder);
			SQLModel sqlModel = modelProducer.getOrderHeaderCreateModel();
			DBOperator.updateRecord(sqlModel, conn);

			setOrderData(checkOrder);
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ������̵㹤��������
	 * @throws DataHandleException
	 */
	protected void createOrderLine() throws DataHandleException {
		ChkOrderPDACreateModel modelProducer = (ChkOrderPDACreateModel)sqlProducer;
		modelProducer.setServletConfig(servletConfig);
		SQLModel sqlModel = modelProducer.getOrderLineCreateModel();
		DBOperator.updateRecord(sqlModel, conn);
	}
}
