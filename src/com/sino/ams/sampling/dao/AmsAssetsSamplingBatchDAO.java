package com.sino.ams.sampling.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.sampling.constant.SamplingActions;
import com.sino.ams.sampling.constant.SamplingDicts;
import com.sino.ams.sampling.constant.SamplingMessages;
import com.sino.ams.sampling.dto.AmsAssetsSamplingBatchDTO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.ams.sampling.model.AmsAssetsSamplingBatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsSamplingBatchDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsSamplingBatchDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsAssetsSamplingBatchDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��������� AMS_ASSETS_SAMPLING_BATCH ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsSamplingBatchDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsSamplingBatchDAO(SfUserDTO userAccount, AmsAssetsSamplingBatchDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO)dtoParameter;
		sqlProducer = new AmsAssetsSamplingBatchModel((SfUserDTO)userAccount, dto);
	}

	/**
	 * ���ܣ����湤��
	 * @param orders DTOSet
	 * @return boolean
	 */
	public boolean saveOrders(DTOSet orders) {
		boolean operateResult = false;
		boolean autoCommit = true;
		AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO)getDTOParameter();//��������
		String act = dto.getAct();
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			saveBatchData();

			AmsAssetsSamplingLineDTO orderLine = new AmsAssetsSamplingLineDTO();
			orderLine.setBatchId(dto.getBatchId());
			AmsAssetsSamplingLineDAO lineDAO = new AmsAssetsSamplingLineDAO(userAccount, orderLine, conn);
			lineDAO.DeleteByForeignKey("batchId");

			AmsAssetsSamplingHeaderDTO orderHeader = new AmsAssetsSamplingHeaderDTO();
			orderHeader.setBatchId(dto.getBatchId());
			AmsAssetsSamplingHeaderDAO headerDAO = new AmsAssetsSamplingHeaderDAO(userAccount, orderHeader, conn);
			headerDAO.DeleteByForeignKey("batchId");

			SeqProducer seqPrd = new SeqProducer(conn);
			String companyCode = userAccount.getCompanyCode();
			String orderType = SamplingDicts.ORDR_NO_MARK;
			OrderNumGenerator orderPrd = new OrderNumGenerator(conn, companyCode, orderType);

			int orderCount = orders.getSize();
			String headerId = "";
			for(int i = 0; i < orderCount; i++){
				orderHeader = (AmsAssetsSamplingHeaderDTO)orders.getDTO(i);
				if(orderHeader.getSamplingLocation().equals("")){
					continue;
				}
				orderHeader.setTaskId(dto.getTaskId());
				orderHeader.setTaskNo(dto.getTaskNo());
				orderHeader.setBatchId(dto.getBatchId());
				orderHeader.setSampledOu(dto.getSampledOu());
				if(act.equals(SamplingActions.SAVE_ACTION)){
					orderHeader.setOrderStatus(SamplingDicts.ORDER_STS1_SAVE_TEMP);
				} else if(act.equals(SamplingActions.DISTRIBUTE_ORDER)){
					orderHeader.setOrderStatus(SamplingDicts.ORDER_STS1_DISTRIBUTED);
					orderHeader.setDistributeBy(userAccount.getUserId());
					orderHeader.setCurrCalendar("distributeDate");
				}
				headerId = orderHeader.getHeaderId();
				if(headerId.equals("")){
					orderHeader.setHeaderId(seqPrd.getGUID());
					orderHeader.setOrderNo(orderPrd.getOrderNum());
				}

				headerDAO.setDTOParameter(orderHeader);
				headerDAO.createData();

				orderLine.setHeaderId(orderHeader.getHeaderId());
				lineDAO.setDTOParameter(orderLine);
				lineDAO.createData();
			}
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					if(act.equals(SamplingActions.SAVE_ACTION)){
						prodMessage(SamplingMessages.CREATE_ORDER_SUCCESS);
					} else {
						prodMessage(SamplingMessages.DISTRI_ORDER_SUCCESS);
					}
					message.addParameterValue(dto.getTaskNo());
				} else {
					conn.rollback();
					if(act.equals(SamplingActions.SAVE_ACTION)){
						prodMessage(SamplingMessages.CREATE_ORDER_FAILURE);
					} else {
						prodMessage(SamplingMessages.DISTRI_ORDER_FAILURE);
					}
					message.addParameterValue(dto.getTaskNo());
					message.setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ�����������
	 * @throws DataHandleException
	 */
	private void saveBatchData() throws DataHandleException{
		try {
			AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO) dtoParameter;
			if (dto.getBatchId().equals("")) {
				SeqProducer seqPrd = new SeqProducer(conn);
				dto.setBatchId(seqPrd.getGUID());
				String companyCode = userAccount.getCompanyCode();
				String orderType = SamplingDicts.BATH_NO_MARK;
				OrderNumGenerator orderPrd = new OrderNumGenerator(conn, companyCode, orderType);
				orderPrd.setOrderLength(3);
				dto.setBatchNo(orderPrd.getOrderNum());
				setDTOParameter(dto);
				createData();
			} else {
				updateData();
			}
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}


	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "��鹤��";
			String fileName = reportTitle + ".xls";
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = getFieldMap();
			rule.setFieldMap(fieldMap);
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			rule.setCalPattern(LINE_PATTERN);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}

	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("ORDER_NO", "�������");
		fieldMap.put("SAMPLING_LOCATION_NAME", "���ص�");
		fieldMap.put("CREATION_DATE", "������������");
		fieldMap.put("IMPLEMENT_USER", "ִ����");
		fieldMap.put("IMPLEMENT_DAYS", "ִ������");
		fieldMap.put("TASK_NO", "������");
		fieldMap.put("TASK_NAME", "��������");
		fieldMap.put("TASK_CREATION_DATE", "���񴴽�����");
		fieldMap.put("START_DATE", "������ʼ����");
		fieldMap.put("END_DATE", "�����ֹ����");
		fieldMap.put("DISTRIBUTE_COMPANY", "���񴴽���˾");
		fieldMap.put("CREATED_USER", "���񴴽���");
		return fieldMap;
	}

	/**
	 * ���ܣ��������Ż�ȡ����Ϣ(��������Ϣ)
	 * @return AmsAssetsSamplingBatchDTO
	 * @throws QueryException
	 */
	public AmsAssetsSamplingBatchDTO getDataByBatchNo() throws QueryException {
		AmsAssetsSamplingBatchDTO batch = new AmsAssetsSamplingBatchDTO();
		AmsAssetsSamplingBatchModel modelProducer = (AmsAssetsSamplingBatchModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getDataByBatchNoModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsSamplingBatchDTO.class.getName());
		simp.executeQuery();
		if(simp.hasResult()){
			batch = (AmsAssetsSamplingBatchDTO)simp.getFirstDTO();
		}
		return batch;
	}

	/**
	 * ���ܣ���ñ����������ݴ�Ĺ�����Ϣ
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getTempSavedOrders() throws QueryException {
		AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO)dtoParameter;
		AmsAssetsSamplingHeaderDTO orderHeader = new AmsAssetsSamplingHeaderDTO();
		orderHeader.setBatchId(dto.getBatchId());
		AmsAssetsSamplingHeaderDAO headerDAO = new AmsAssetsSamplingHeaderDAO(userAccount, orderHeader, conn);
		return headerDAO.getTempSavedOrdersByBatchId();
	}

	/**
	 * ���ܣ�ɾ��ѡ�еĹ�������
	 * @param headerIds String[]
	 * @return boolean
	 */
//	public boolean deleteOrders(String[] headerIds){
//		boolean operateResult = false;
//		boolean autoCommit = true;
//		try {
//			autoCommit = conn.getAutoCommit();
//			conn.setAutoCommit(false);
//			AmsAssetsSamplingBatchDTO dto = (AmsAssetsSamplingBatchDTO) dtoParameter;
//			AmsAssetsSamplingHeaderDTO orderHeader = new AmsAssetsSamplingHeaderDTO();
//			orderHeader.setBatchId(dto.getBatchId());
//			AmsAssetsSamplingHeaderDAO headerDAO = new AmsAssetsSamplingHeaderDAO(userAccount, orderHeader, conn);
//			headerDAO.deleteOrders(headerIds);
//			operateResult = true;
//		} catch (DataHandleException ex) {
//			ex.printLog();
//		} catch (SQLException ex) {
//			Logger.logError(ex);
//		} finally{
//			try {
//				if (operateResult) {
//					prodMessage(SamplingMessages.DELETE_DATA_SUCCESS);
//					conn.commit();
//				} else {
//					prodMessage(SamplingMessages.DELETE_DATA_FAILURE);
//					message.setIsError(true);
//					conn.rollback();
//				}
//				message.addParameterValue("��鹤��");
//				conn.setAutoCommit(autoCommit);
//			} catch (SQLException ex) {
//				Logger.logError(ex);
//			}
//		}
//		return operateResult;
//	}
}
