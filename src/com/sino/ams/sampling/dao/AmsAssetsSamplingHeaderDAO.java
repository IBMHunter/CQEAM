package com.sino.ams.sampling.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.constant.SamplingMessages;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.ams.sampling.model.AmsAssetsSamplingHeaderModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsSamplingHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsSamplingHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsAssetsSamplingHeaderDAO extends AMSBaseDAO {


	/**
	 * ���ܣ���鹤���� AMS_ASSETS_SAMPLING_HEADER ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsSamplingHeaderDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsSamplingHeaderDAO(BaseUserDTO userAccount, AmsAssetsSamplingHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)dtoParameter;
		SfUserDTO user = (SfUserDTO)userAccount;
		sqlProducer = new AmsAssetsSamplingHeaderModel(user, dto);
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
		fieldMap.put("IMPLEMENT_USER", "����ִ����");
		fieldMap.put("IMPLEMENT_DAYS", "����ִ������");
		fieldMap.put("ORDER_STATUS_VALUE", "����״̬");
		fieldMap.put("SAMPLEDED_OU_NAME", "ִ�й�˾");
		fieldMap.put("TASK_NO", "������");
		fieldMap.put("TASK_NAME", "��������");
		fieldMap.put("TASK_CREATION_DATE", "���񴴽�����");
		fieldMap.put("START_DATE", "������ʼ����");
		fieldMap.put("END_DATE", "�����ֹ����");
		fieldMap.put("CREATED_OU_NAME", "���񴴽���˾");
		fieldMap.put("TASK_CREATED_USER", "���񴴽���");

		return fieldMap;
	}


	/**
	 * ���ܣ������������������������󣬲��ɽ��н�һ���Ĳ�����
	 * @param headerIds ����ID
	 * @return boolean
	 */
	public boolean cancelOrders(String[] headerIds){
		boolean operateResult = false;
		try {
			AmsAssetsSamplingHeaderModel modelProducer = (AmsAssetsSamplingHeaderModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getMulOrderCanceledModel(headerIds);
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if (operateResult) {
				prodMessage(SamplingMessages.MUL_CALORD_SUCCESS);
			} else {
				prodMessage(SamplingMessages.MUL_CALORD_FAILURE);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ������·������������·���PDA�������ء�
	 * @param headerIds ����ID
	 * @return boolean
	 */
	public boolean distributeOrders(String[] headerIds){
		boolean operateResult = false;
		try {
			AmsAssetsSamplingHeaderModel modelProducer = (AmsAssetsSamplingHeaderModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getMulOrderDistributedModel(headerIds);
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if (operateResult) {
				prodMessage(SamplingMessages.MUL_DISORD_SUCCESS);
			} else {
				prodMessage(SamplingMessages.MUL_DISORD_FAILURE);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ���ȡָ�����������ݴ�Ĺ���
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getTempSavedOrdersByBatchId() throws QueryException {
		AmsAssetsSamplingHeaderModel modelProducer = (AmsAssetsSamplingHeaderModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getTempSavedOrderModelByBatchId();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsSamplingHeaderDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}

	/**
	 * ���ܣ�ɾ��ѡ�еĹ���
	 * @param headerIds String[]
	 * @throws DataHandleException
	 */
//	public void deleteOrders(String[] headerIds) throws DataHandleException {
//		AmsAssetsSamplingLineDTO orderLine = new AmsAssetsSamplingLineDTO();
//		AmsAssetsSamplingLineDAO lineDAO = new AmsAssetsSamplingLineDAO(userAccount, null, conn);
//		AmsAssetsSamplingHeaderDTO orderHeader = new AmsAssetsSamplingHeaderDTO();
//		for(int i = 0; i < headerIds.length; i++){
//			orderLine.setHeaderId(headerIds[i]);
//			lineDAO.setDTOParameter(orderLine);
//			lineDAO.DeleteByForeignKey("headerId");
//
//			orderHeader.setHeaderId(headerIds[i]);
//			setDTOParameter(orderHeader);
//			deleteData();
//		}
//	}

	/**
	 * ���ܣ���ȡ��ǰ�����µ��豸
	 * @param includePDA boolean
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getOrderBarcodes(boolean includePDA, AmsAssetsSamplingHeaderDTO dto, String headerId) throws QueryException {
//		AmsAssetsSamplingHeaderModel modelProducer = (AmsAssetsSamplingHeaderModel) sqlProducer;
        AmsAssetsSamplingHeaderModel modelProducer = new AmsAssetsSamplingHeaderModel(userAccount, dto);
        SQLModel sqlModel = modelProducer.getOrderBarcodesModel(includePDA, headerId);
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsSamplingLineDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}
}
