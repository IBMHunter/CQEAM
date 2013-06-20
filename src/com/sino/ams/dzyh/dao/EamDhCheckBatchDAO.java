package com.sino.ams.dzyh.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.dzyh.constant.DzyhActionConstant;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.constant.LvecMessageKeys;
import com.sino.ams.dzyh.constant.LvecWebAttributes;
import com.sino.ams.dzyh.dto.EamDhCheckBatchDTO;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.dzyh.model.EamDhCheckBatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhCheckBatchDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhCheckBatchDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class EamDhCheckBatchDAO extends AMSBaseDAO {

	/**
	 * ���ܣ���ֵ�׺��̵㹤������ EAM_DH_CHECK_BATCH ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCheckBatchDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhCheckBatchDAO(SfUserDTO userAccount, EamDhCheckBatchDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamDhCheckBatchDTO dto = (EamDhCheckBatchDTO) dtoParameter;
		sqlProducer = new EamDhCheckBatchModel((SfUserDTO) userAccount, dto);
	}

	/**
	 * ���ܣ����湤��
	 * @param orders DTOSet
	 * @return boolean
	 */
	public boolean saveOrders(DTOSet orders) {
		boolean operateResult = false;
		boolean autoCommit = true;
		EamDhCheckBatchDTO dto = (EamDhCheckBatchDTO) dtoParameter;
		boolean isNewBatch = dto.getBatchId().equals("");
		String act = dto.getAct();
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			if (isNewBatch) {
				SeqProducer seqPrd = new SeqProducer(conn);
				//TODO
				dto.setBatchId(seqPrd.getStrNextSeq("EAM_DH_CHECK_BATCH") + "" );
				String companyCode = userAccount.getCompanyCode();
				String orderType = LvecDicts.CHECK_BATCH;
				OrderNumGenerator numPrd = new OrderNumGenerator(conn, companyCode, orderType);
				numPrd.setOrderLength(3);
				dto.setBatchNo(numPrd.getOrderNum());
				setDTOParameter(dto);
				createData();
			} else {
				updateData();
			}
			EamDhCheckLineDAO lineDAO = new EamDhCheckLineDAO(userAccount, null, conn);
			EamDhCheckLineDTO orderLine = new EamDhCheckLineDTO();
			if(!dto.getOrderType().equals(LvecDicts.ORD_TYPE1_DHBS)){
				orderLine.setBatchId(dto.getBatchId());
				lineDAO.setDTOParameter(orderLine);
				lineDAO.DeleteByForeignKey("batchId");
			}
			EamDhCheckHeaderDTO orderHeader = new EamDhCheckHeaderDTO();
			orderHeader.setBatchId(dto.getBatchId());
			EamDhCheckHeaderDAO headerDAO = new EamDhCheckHeaderDAO(userAccount, orderHeader, conn);
			headerDAO.DeleteByForeignKey("batchId");

			int orderCount = orders.getSize();
			for(int i = 0; i < orderCount; i++){
				orderHeader = (EamDhCheckHeaderDTO)orders.getDTO(i);

				if(orderHeader.getCheckLocation()==0){

					continue;
				}
				orderHeader.setBatchId(dto.getBatchId());
				orderHeader.setCheckTaskId(dto.getCheckTaskId());
				orderHeader.setAct(act);
				headerDAO.setDTOParameter(orderHeader);
				headerDAO.saveOrder();

				if(!orderHeader.getOrderType().equals(LvecDicts.ORD_TYPE1_DHBS)){//�ǲ�ɨ��������Ҫ��������Ϣ
					orderHeader = (EamDhCheckHeaderDTO) headerDAO.getDTOParameter();
					orderLine.setHeaderId(orderHeader.getHeaderId());
					orderLine.setOrderType(orderHeader.getOrderType());
					orderLine.setBatchId(dto.getBatchId());
					lineDAO.setDTOParameter(orderLine);
					lineDAO.createData();
				}
			}
			operateResult = true;
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally{
			try {
				if(operateResult){
					conn.commit();
					if(act.equals(DzyhActionConstant.SAVE_ACTION)){//�ݴ湤��
						prodMessage(LvecMessageKeys.ORDER_SAVE_SUCCESS);
					} else if(act.equals(DzyhActionConstant.DISTRI_ORDER)){//�·�����
						prodMessage(LvecMessageKeys.ORDER_DISTRI_SUCCESS);
					}
				} else {
					conn.rollback();
					if (isNewBatch) {
						dto.setBatchId("");
						dto.setBatchNo(LvecWebAttributes.ORDER_NO_AUTO_PRODUCE);
					}
					if(act.equals(DzyhActionConstant.SAVE_ACTION)){//�ݴ湤��
						prodMessage(LvecMessageKeys.ORDER_SAVE_FAILURE);
					} else if(act.equals(DzyhActionConstant.DISTRI_ORDER)){//�·�����
						prodMessage(LvecMessageKeys.ORDER_DISTRI_FAILURE);
					}
					message.setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
				setDTOParameter(dto);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ���������������
	 * @param batchIds String[]
	 * @param singleCancel boolean
	 */
	public void cancelOrderBatchs(String[] batchIds, boolean singleCancel){
		boolean operateResult = false;
		boolean autoCommit = true;
		EamDhCheckBatchDTO dto = (EamDhCheckBatchDTO) dtoParameter;
		try {
			EamDhCheckBatchModel modelProducer = (EamDhCheckBatchModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getMulBatchCancelModel(batchIds);
			DBOperator.updateRecord(sqlModel, conn);
			EamDhCheckHeaderDAO headerDAO = new EamDhCheckHeaderDAO(userAccount, null, conn);
			headerDAO.cancelOrders(batchIds);
			setDTOParameter(dto);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			try {
				if(operateResult){
					conn.commit();
					if(singleCancel){//��ϸ��Ϣҳ�����ĳ���
						prodMessage(LvecMessageKeys.BATCH_CANCEL_SUCCESS);
					} else {//�б�ҳ�����ĳ���
						prodMessage(LvecMessageKeys.BATCH_MULCEL_SUCCESS);
					}
				} else {
					conn.rollback();
					if(singleCancel){//��ϸ��Ϣҳ�����ĳ���
						prodMessage(LvecMessageKeys.BATCH_CANCEL_FAILURE);
					} else {//�б�ҳ�����ĳ���
						prodMessage(LvecMessageKeys.BATCH_MULCEL_FAILURE);
					}
					message.setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
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
			String reportTitle = "�̵㹤����";
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
		fieldMap.put("BATCH_NO", "���������");
		fieldMap.put("CREATION_DATE", "��������������");
		fieldMap.put("CHEC_DEPT_NAME", "��������������");
		fieldMap.put("BATCH_STATUS_VALUE", "������״̬");
		fieldMap.put("TASK_NAME", "��������");
		fieldMap.put("CHECK_TYPE_VALUE", "�������");
		fieldMap.put("TASK_STATUS_VALUE", "����״̬");
		fieldMap.put("START_DATE", "������ʼ����");
		fieldMap.put("END_DATE", "�����ֹ����");
		return fieldMap;
	}
}
