package com.sino.ams.prematch.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.prematch.dto.AmsPaMatchDTO;
import com.sino.ams.prematch.dto.AmsPaMatchLogDTO;
import com.sino.ams.prematch.model.AmsPaMatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsPaMatchDAO</p>
 * <p>Description:�����Զ����ɷ������AmsPaMatchDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsPaMatchDAO extends AMSBaseDAO {

	/**
	 * ���ܣ�EAMϵͳ�ʲ�ʵ����MISת��׼���嵥Ԥƥ�� AMS_PA_MATCH ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsPaMatchDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsPaMatchDAO(SfUserDTO userAccount, AmsPaMatchDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsPaMatchDTO dto = (AmsPaMatchDTO)dtoParameter;
		sqlProducer = new AmsPaMatchModel(user, dto);
	}

	/**
	 * ���ܣ�����ƥ�����ݣ�ͬʱ��¼ƥ����־
	 * @throws DataHandleException
	 */
	public void createData() throws DataHandleException{
		try {
			AmsPaMatchDTO dto = (AmsPaMatchDTO) dtoParameter;
			AmsPaMatchLogDTO logDTO = new AmsPaMatchLogDTO();
			ReflectionUtil.copyData(dto, logDTO);
			logDTO.setMatchedBy(userAccount.getUserId());
			logDTO.setMatchedDate(CalendarUtil.getCurrCalendar());
			logDTO.setOrganizationId(userAccount.getOrganizationId());
			logDTO.setAct(AMSActionConstant.CREATE_ACTION);
			super.createData();
			AmsPaMatchLogDAO logDAO = new AmsPaMatchLogDAO(userAccount, logDTO, conn);
			logDAO.createData();
		} catch (CalendarException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (ReflectException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ�������ƥ���嵥��Excel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			AmsPaMatchModel modelProducer = (AmsPaMatchModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = userAccount.getCompany() + "��ƥ���嵥";
			String fileName = reportTitle + ".xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			rule.setFieldMap(getFieldMap());
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
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

	/**
	 * ���ܣ���ȡ��ƥ���嵥�����ֶ�ӳ��
	 * @return Map
	 */
	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "EAM��ǩ");
		fieldMap.put("ITEM_NAME", "EAM����");
		fieldMap.put("ITEM_SPEC", "EAM�ͺ�");
		fieldMap.put("PROJECT_NUMBER_AMS", "EAM��Ŀ���");
		fieldMap.put("PROJECT_NAME_AMS", "EAM��Ŀ����");
		fieldMap.put("WORKORDER_OBJECT_CODE", "EAM�ص����");
		fieldMap.put("WORKORDER_OBJECT_NAME", "EAM�ص�����");
		fieldMap.put("EMPLOYEE_NUMBER", "EAMԱ�����");
		fieldMap.put("USER_NAME", "EAM������");

		fieldMap.put("TAG_NUMBER", "PA��ǩ");
		fieldMap.put("ASSETS_DESCRIPTION", "PA����");
		fieldMap.put("MODEL_NUMBER", "PA�ͺ�");
		fieldMap.put("PROJECT_NUMBER", "PA��Ŀ���");
		fieldMap.put("PROJECT_NAME", "PA��Ŀ����");
		fieldMap.put("ASSETS_LOCATION_CODE", "PA�ص����");
		fieldMap.put("ASSETS_LOCATION", "PA�ص�����");
		fieldMap.put("ASSIGNED_TO_NUMBER", "PAԱ�����");
		fieldMap.put("ASSIGNED_TO_NAME", "PA������");

		fieldMap.put("USERNAME", "ƥ����");
		fieldMap.put("CREATION_DATE", "ƥ��ʱ��");
		fieldMap.put("REMARK", "ƥ�䱸ע");
		return fieldMap;
	}

	/**
	 * ���ܣ���ȡѡ�е�ƥ���ϵ�в���ɾ����ƥ���ϵ
	 * @param systemIds String[]
	 * @return boolean
	 * @throws QueryException
	 */
	private String getCanNotDelMathes(String[] systemIds) throws QueryException {
		String canNotDelMatches = "";
		try {
			AmsPaMatchModel modelProducer = (AmsPaMatchModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getCanDeleteModel(systemIds);
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				RowSet rows = simp.getSearchResult();
				Row row = null;
				for (int i = 0; i < rows.getSize(); i++) {
					row = rows.getRow(i);
					canNotDelMatches += row.getValue("BARCODE") + ":";
					canNotDelMatches += row.getValue("TAG_NUMBER") + "&nbsp;";
				}
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return canNotDelMatches;
	}

	/**
	 * ���ܣ��Զ�ƥ��(��ѡƥ��)
	 * @param systemIds String[]
	 * @throws DataHandleException
	 */
	public void deleteMatchs(String[] systemIds) throws DataHandleException {
		boolean operateResult = false;
		boolean autoCommit = true;
		String canNotDelMatches = "";
		try {
			canNotDelMatches = getCanNotDelMathes(systemIds);
			if(canNotDelMatches.equals("")){
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				AmsPaMatchModel modelProducer = (AmsPaMatchModel) sqlProducer;
				SQLModel sqlModel = modelProducer.getDeleteMatchLogModel(systemIds);
				DBOperator.updateRecord(sqlModel, conn);
				AmsPaMatchDTO dto = null;
				for (int i = 0; i < systemIds.length; i++) {
					dto = new AmsPaMatchDTO();
					dto.setSystemId(systemIds[i]);
					setDTOParameter(dto);
					deleteByPrimaryKey();
				}
				operateResult = true;
			}
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally{
			try {
				if(canNotDelMatches.equals("")){
					if (operateResult) {
						prodMessage(CustMessageKey.DELETE_MATCH_SUCCESS);
						conn.commit();
					} else {
						conn.rollback();
						prodMessage(CustMessageKey.DELETE_MATCH_FAILURE);
					}
					conn.setAutoCommit(autoCommit);
				} else {
					prodMessage(CustMessageKey.CAN_NOT_DELETE);
					message.addParameterValue(canNotDelMatches);
				}
				message.setIsError(!operateResult);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
	}
}
