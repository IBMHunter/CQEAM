package com.sino.ams.dzyh.dao;

import java.sql.Connection;

import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.constant.DzyhActionConstant;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.constant.LvecURLs;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.dzyh.dto.EamDhChgLogDTO;
import com.sino.ams.dzyh.model.OrderLineConfirmModel;
import com.sino.ams.newasset.dao.AmsItemInfoHistoryDAO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OrderLineConfirmDAO extends AMSBaseDAO {
	private SimpleQuery simp = null;

	public OrderLineConfirmDAO(BaseUserDTO userAccount, EamDhCheckLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		simp = new SimpleQuery(null, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamDhCheckLineDTO dto = (EamDhCheckLineDTO)dtoParameter;
		sqlProducer = new OrderLineConfirmModel(userAccount, dto);
	}

	/**
	 * ���ܣ�ȷ���豸��Ϣ
	 * <B>����������������Ƶķ�������</B>
	 * @throws DataHandleException
	 */
	public void confirmItem()throws DataHandleException{
		confirmOrderLine();
		archiveOrderLine();
		createLog();
		createHistory();
		updateItemInfo();
	}

	/**
	 * ���ܣ�ȷ�Ϲ�������Ϣ
	 * @throws DataHandleException
	 */
	private void confirmOrderLine() throws DataHandleException{
		OrderLineConfirmModel modelProducer = (OrderLineConfirmModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getLineConfirmModel();
		DBOperator.updateRecord(sqlModel, conn);
	}


	/**
	 * ���ܣ��鵵�����С�
	 * @throws DataHandleException
	 */
	private void archiveOrderLine() throws DataHandleException {
		OrderLineConfirmModel modelProducer = (OrderLineConfirmModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getLineArchiveModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ������ͺı��豸�䶯��־
	 * @throws DataHandleException
	 */
	private void createLog() throws DataHandleException {
		EamDhCheckLineDTO dto = (EamDhCheckLineDTO)dtoParameter;

		EamDhChgLogDTO logDTO = new EamDhChgLogDTO();
		logDTO.setBarcode(dto.getBarcode());
		logDTO.setRefNo(dto.getOrderNo());

		logDTO.setFromDept(dto.getResponsibilityDept());
		logDTO.setToDept(userAccount.getDeptCode());

		logDTO.setFromResponsibilityUser(dto.getResponsibilityUser());
		logDTO.setToResponsibilityUser(userAccount.getEmployeeId());

		logDTO.setChgType(LvecDicts.LOG_CHG_TYPE);
		logDTO.setCatalogValueId(dto.getCatalogValueId());

		EamDhChgLogDAO logDAO = new EamDhChgLogDAO(userAccount, logDTO, conn);
		logDAO.createData();
	}


	/**
	 * ���ܣ������豸�䶯��ʷ
	 * @throws DataHandleException
	 */
	private void createHistory() throws DataHandleException {
		EamDhCheckLineDTO dto = (EamDhCheckLineDTO)dtoParameter;
		AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();

		historyDTO.setBarcode(dto.getBarcode());
		historyDTO.setOrderNo(dto.getOrderNo());
		historyDTO.setResponsibilityDept(userAccount.getDeptCode());
		historyDTO.setResponsibilityUser(userAccount.getEmployeeId());
		historyDTO.setOrderCategory(LvecDicts.ORDER_CATEGORY_4);
        historyDTO.setCreatedBy(userAccount.getUserId());

		String orderDtlURL = LvecURLs.ORDER_SERVLET;
		orderDtlURL += "?act=" + DzyhActionConstant.DETAIL_ACTION;
		orderDtlURL += "&headerId=" + dto.getHeaderId();
		historyDTO.setOrderDtlUrl(orderDtlURL);
		historyDTO.setRemark(LvecDicts.INSTRU_ARCH_REMARK);

		AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(userAccount, historyDTO, conn);
		historyDAO.recordHistory();
	}

	/**
	 * ���ܣ������豸��Ϣ
	 * @throws DataHandleException
	 */
	private void updateItemInfo() throws DataHandleException {
		OrderLineConfirmModel modelProducer = (OrderLineConfirmModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getItemInfoUpdateModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ���ȡ��ǰ����ʣ��δȷ���豸����
	 * @return int
	 * @throws QueryException
	 */
	public boolean isLastBarcode() throws QueryException {
		boolean lastBarcode = false;
		try {
			OrderLineConfirmModel modelProducer = (OrderLineConfirmModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getLeftCountModel();
			simp.setSql(sqlModel);
			simp.executeQuery();
			Row row = simp.getFirstRow();
			lastBarcode = (Integer.parseInt(row.getStrValue("LEFT_COUNT")) == 0);
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		} catch (NumberFormatException ex) {
			Logger.logError(ex);
			throw new QueryException(ex);
		}
		return lastBarcode;
	}
}
