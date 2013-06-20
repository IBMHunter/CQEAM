package com.sino.ams.sampling.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.*;
import com.sino.base.dto.DTO;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;

import com.sino.framework.dto.BaseUserDTO;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.sampling.constant.*;
import com.sino.ams.sampling.dto.AmsAssetsSamplingBatchDTO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingTaskDTO;
import com.sino.ams.sampling.dto.AmsSamplingTaskAssignDTO;
import com.sino.ams.sampling.model.AmsAssetsSamplingTaskModel;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsAssetsSamplingTaskDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsSamplingTaskDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsAssetsSamplingTaskDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��ʲ��������� AMS_ASSETS_SAMPLING_TASK ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsSamplingTaskDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsSamplingTaskDAO(SfUserDTO userAccount, AmsAssetsSamplingTaskDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO)dtoParameter;
		super.sqlProducer = new AmsAssetsSamplingTaskModel((SfUserDTO)userAccount, dto);
	}


	/**
	 * ���ܣ��ݴ�������ִ�и÷����󣬳��������޸ģ����������д�����鹤����
	 * @param sampledOu ����������OU
	 * @return boolean
	 */
	public boolean saveTask(String[] sampledOu){
		boolean operateResult = false;
		boolean autoCommit = true;
		try {
			AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO) dtoParameter;
			String taskId = dto.getTaskId();
			boolean isNewTask = taskId.equals("");
//			if(isDataValid(isNewTask)){
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				dto.setTaskStatus(SamplingDicts.TSK_STATUS1_SAVE_TEMP);
				dto.setOpenedBy(0);
				dto.setOpenedDate("");
				saveTaskData();
				saveTaskAssign(sampledOu);
				operateResult = true;
//			}
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (CalendarException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
				} else {
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ������������ִ�и÷����󣬳�����񲻿��޸ģ��ɴ�����ع�����
	 * @param sampledOu ����������OU
	 * @return boolean
	 */
	public boolean publishTask(String[] sampledOu){
		boolean operateResult = false;
		boolean autoCommit = true;
		try {
			AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO) dtoParameter;
			String taskId = dto.getTaskId();
			boolean isNewTask = taskId.equals("");
//			if(isDataValid(isNewTask)){
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				dto.setTaskStatus(SamplingDicts.TSK_STATUS1_OPENING);
				dto.setOpenedBy(userAccount.getUserId());
				dto.setCurrCalendar("openedDate");
				saveTaskData();
				saveTaskAssign(sampledOu);
				operateResult = true;
//			}
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
				} else {
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ���������
	 * @param sampledOu String[]
	 * @throws DataHandleException
	 */
	private void saveTaskAssign(String[] sampledOu) throws DataHandleException {
		AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO) dtoParameter;
		if (sampledOu != null && sampledOu.length > 0) {
			int sampledOuCount = sampledOu.length;
			AmsSamplingTaskAssignDTO assignDTO = new AmsSamplingTaskAssignDTO();
			assignDTO.setTaskId(dto.getTaskId());
			AmsSamplingTaskAssignDAO assignDAO = new AmsSamplingTaskAssignDAO(userAccount, assignDTO, conn);
			assignDAO.DeleteByForeignKey("taskId");
			for (int i = 0; i < sampledOuCount; i++) {
				assignDTO = new AmsSamplingTaskAssignDTO();
				assignDTO.setTaskId(dto.getTaskId());
				assignDTO.setOrganizationId(StrUtil.strToInt(sampledOu[i]));
				assignDAO.setDTOParameter(assignDTO);
				assignDAO.createData();
			}
		}
	}

	/**
	 * ���ܣ���������������
	 * @throws DataHandleException
	 */
	private void saveTaskData() throws DataHandleException {
		boolean operateResult = false;
		AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO)dtoParameter;
		String taskId = dto.getTaskId();
		String taskNo = dto.getTaskNo();
		String act = dto.getAct();
		boolean isNewTask = taskId.equals("");
		try {
			AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = null;
			if (taskNo.equals(SamplingWebAttributes.ORDER_AUTO_PROD)) {
				String companyCode = userAccount.getCompanyCode();
				String orderType = SamplingDicts.TASK_NO_MARK;
				OrderNumGenerator orderPrd = new OrderNumGenerator(conn, companyCode, orderType);
				dto.setTaskNo(orderPrd.getOrderNum());
			}
			if (isNewTask) {
				SeqProducer seqPrd = new SeqProducer(conn);
				taskId = seqPrd.getGUID();
				dto.setTaskId(taskId);
				sqlModel = modelProducer.getDataCreateModel();
			} else {
				sqlModel = modelProducer.getDataUpdateModel();
			}
			DBOperator.updateRecord(sqlModel, conn);
			setDTOParameter(dto);
			operateResult = true;
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} finally{
			if (operateResult) {
				if (isNewTask) {
					if (act.equals(SamplingActions.PUBLISH_TASK)) {
						prodMessage(SamplingMessages.PUBLISH_TASK_SUCCESS);
					} else {
						prodMessage(SamplingMessages.CREATE_TASK_SUCCESS);
					}
				} else {
					if (act.equals(SamplingActions.PUBLISH_TASK)) {
						prodMessage(SamplingMessages.PUBLISH_TASK_SUCCESS);
					} else {
						prodMessage(SamplingMessages.UPDATE_TASK_SUCCESS);
					}
				}
				message.addParameterValue(dto.getTaskNo());
			} else {
				if (isNewTask) {
					prodMessage(SamplingMessages.CREATE_TASK_FAILURE);
				} else {
					prodMessage(SamplingMessages.UPDATE_TASK_FAILURE);
					message.addParameterValue(dto.getTaskNo());
				}
				message.setIsError(true);
			}
		}
	}


	/**
	 * ���ܣ��رճ������ִ�и÷����󣬳�����񲻿��޸ģ����ɴ�����ع�����
	 * @return boolean
	 */
	public boolean closeTask() {
		boolean operateResult = false;
		AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO)dtoParameter;
		try {
			AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getTaskCloseModel();
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if(operateResult){
				prodMessage(SamplingMessages.CLOSE_TASK_SUCCESS);
			} else {
				prodMessage(SamplingMessages.CLOSE_TASK_FAILURE);
				message.setIsError(true);
			}
			message.addParameterValue(dto.getTaskNo());
		}
		return operateResult;
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		AmsAssetsSamplingTaskModel modelProducer = null;
		try {
			modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�������";
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
			throw new WebFileDownException(ex);
		} catch (DataTransException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		}
		return file;
	}

	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("TASK_NO", "������");
		fieldMap.put("TASK_NAME", "��������");
		fieldMap.put("START_DATE", "��ʼ����");
		fieldMap.put("END_DATE", "��������");
		fieldMap.put("SAMPLING_RATIO", "���ٷֱ�");
//		fieldMap.put("SAMPLING_TYPE_VALUE", "������");
		fieldMap.put("TASK_STATUS_VALUE", "����״̬");
		fieldMap.put("CREATION_DATE", "��������");
		return fieldMap;
	}

	/**
	 * ���ܣ��ر�ѡ��Ķ�������д��ڿ���״̬������
	 * @param taskIds String[]
	 * @return boolean
	 */
	public boolean closeMultipleTask(String[] taskIds) {
		boolean operateResult = false;
		try {
			AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getMultipleCloseModel(taskIds);
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage(SamplingMessages.CLOSE_MUL_TSK_SUCCESS);
				} else {
					conn.rollback();
					prodMessage(SamplingMessages.CLOSE_MUL_TSK_FAILURE);
					message.setIsError(true);
				}
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ�ȡ��ѡ��Ķ���������ݴ������
	 * @param taskIds String[]
	 * @return boolean
	 */
	public boolean cancelMultipleTask(String[] taskIds) {
		boolean operateResult = false;
		try {
			AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getMultipleCancelModel(taskIds);
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage(SamplingMessages.CANCEL_MUL_TSK_SUCCESS);
				} else {
					conn.rollback();
					prodMessage(SamplingMessages.CANCEL_MUL_TSK_FAILURE);
					message.setIsError(true);
				}
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ�����ѡ��Ķ���������ݴ������
	 * @param taskIds String[]
	 * @return boolean
	 */
	public boolean publishMultipleTask(String[] taskIds) {
		boolean operateResult = false;
		try {
			AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getMultiplePublishModel(taskIds);
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage(SamplingMessages.PUBLISH_MUL_TSK_SUCCESS);
				} else {
					conn.rollback();
					prodMessage(SamplingMessages.CANCEL_MUL_TSK_FAILURE);
					message.setIsError(true);
				}
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ���ѡ��Ķ���������ѹرյ�����
	 * @param taskIds String[]
	 * @return boolean
	 */
	public boolean openMultipleTask(String[] taskIds) {
		boolean operateResult = false;
		try {
			AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getMultipleOpenModel(taskIds);
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage(SamplingMessages.OPEN_MUL_TSK_SUCCESS);
				} else {
					conn.rollback();
					prodMessage(SamplingMessages.OPEN_MUL_TSK_FAILURE);
					message.setIsError(true);
				}
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}


	/**
	 * ���ܣ����ѹرյĳ������ִ�и÷����󣬳�����񲻿��޸ģ��ɴ�����ع�����
	 * @return boolean
	 */
	public boolean openTask() {
		boolean operateResult = false;
		AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO)dtoParameter;
		try {
			AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getTaskOpenModel();
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if(operateResult){
				prodMessage(SamplingMessages.OPEN_TASK_SUCCESS);
			} else {
				prodMessage(SamplingMessages.OPEN_TASK_FAILURE);
				message.setIsError(true);
			}
			message.addParameterValue(dto.getTaskNo());
		}
		return operateResult;
	}


	/**
	 * ���ܣ�ȡ����������ݴ�ĳ�������ȡ�����������񲻿�ȡ��
	 * @return boolean
	 */
	public boolean cancelTask(){
		boolean operateResult = false;
		AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO)dtoParameter;
		try {
			AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getTaskCancelModel();
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if(operateResult){
				prodMessage(SamplingMessages.CANCEL_TASK_SUCCESS);
			} else {
				prodMessage(SamplingMessages.CANCEL_TASK_FAILURE);
				message.setIsError(true);
			}
			message.addParameterValue(dto.getTaskNo());
		}
		return operateResult;
	}

	/**
	 * ���ܣ�������ݵĺϷ���
	 * @param isNewTask boolean
	 * @return boolean
	 */
	private boolean isDataValid(boolean isNewTask){
		boolean isValid = true;
		try {
			AmsAssetsSamplingTaskDTO dto = (AmsAssetsSamplingTaskDTO) dtoParameter;
			String tableName = "AMS_ASSETS_SAMPLING_TASK";
			DataValidChecker checker = new DataEmptyChecker(tableName, dto, conn);
			checker.setServletConfig(servletConfig);
			checker.setAnswerAsComment(true);
			isValid = checker.isDataValid();
			if(!isValid){
				prodMessage(SamplingMessages.EMPTY_ERROR);
				message.addParameterValue(tableName);
				message.addParameterValue(checker.getInValidData());
			} else {
				checker = new DataLengthChecker(tableName, dto, conn);
				checker.setServletConfig(servletConfig);
				checker.setAnswerAsComment(true);
				isValid = checker.isDataValid();
				if (!isValid) {
					message = new Message();
					message.setMessageValue(checker.getInValidData());
				} else {
					checker = new NumberChecker(tableName, dto, conn);
					checker.setServletConfig(servletConfig);
					checker.setAnswerAsComment(true);
					isValid = checker.isDataValid();
					if (!isValid) {
						message = new Message();
						message.setMessageValue(checker.getInValidData());
					} else {
						DataUniqueChecker uniquer = new DataUniqueChecker(tableName, dto, conn);
						uniquer.setAnswerAsComment(true);
						String dbAction = isNewTask ? "INSERT" : "UPDATE";
						uniquer.setDBAction(dbAction);
						uniquer.setServletConfig(servletConfig);
						isValid = uniquer.isDataValid();
						if (!isValid) {
							prodMessage(SamplingMessages.UNIQUE_ERROR);
							message.addParameterValue(uniquer.getInValidData());
						}
					}
				}
			}
		} catch (ValidateException ex) {
			ex.printLog();
		}
		return isValid;
	}

	/**
	 * ���ܣ���������Ż�ȡ��������Ϣ
	 * @return AmsAssetsSamplingTaskDTO
	 * @throws QueryException
	 */
	public AmsAssetsSamplingBatchDTO getDataByTaskNo() throws QueryException {
		AmsAssetsSamplingBatchDTO task = null;
		AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getDataByTaskNoModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsSamplingBatchDTO.class.getName());
		simp.executeQuery();
		if(simp.hasResult()){
			task = (AmsAssetsSamplingBatchDTO)simp.getFirstDTO();
		}
		return task;
	}

    public Map verify(String[] selectTaskIds) throws ContainerException, QueryException {
        Map map = new HashMap();
        String selectTaskId = "";
//        if (selectTaskIds != null & selectTaskIds.length > 0) {
//            for (int i = 0; i < selectTaskIds.length; i++) {
//                selectTaskId = selectTaskIds[i];
//                selectTaskId = selectTaskId.substring(8);
//                String orgId = getResult(selectTaskId);
//                if (StrUtil.isNotEmpty(orgId)) {
//                    map.put("orgId", orgId);
//                }
//            }
//        }
        selectTaskId = selectTaskIds[0];
        String[] TaskIds = StrUtil.splitStr(selectTaskId,",");
        if (TaskIds != null & TaskIds.length > 0) {
            for (int i = 0; i < TaskIds.length; i++) {
                selectTaskId = TaskIds[i];
                selectTaskId = selectTaskId.substring(8);
                String orgId = getResult(selectTaskId);
                if (orgId.equals("")) {
                    map.put("orgId", "EMPTY");
                    break;
                }
            }
        }
        return map;
    }

    private String getResult(String selectTaskId) throws QueryException, ContainerException {
        String orgId = "";
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT ASTA.ORGANIZATION_ID\n" +
                "  FROM AMS_SAMPLING_TASK_ASSIGN ASTA\n" +
                " WHERE ASTA.TASK_ID = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(selectTaskId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            orgId = simpleQuery.getFirstRow().getStrValue("ORGANIZATION_ID");
        }
        return orgId;
    }
    
	public AmsAssetsSamplingBatchDTO getDataByTaskName() throws QueryException {
		AmsAssetsSamplingBatchDTO task = null;
		AmsAssetsSamplingTaskModel modelProducer = (AmsAssetsSamplingTaskModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getDataByTaskNmaeModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsSamplingBatchDTO.class.getName());
		simp.executeQuery();
		if(simp.hasResult()){
			task = (AmsAssetsSamplingBatchDTO)simp.getFirstDTO();
		}
		return task;
	}
    
}
