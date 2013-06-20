package com.sino.td.newasset.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.td.newasset.dto.TdAssetsRcvHeaderDTO;
import com.sino.td.newasset.dto.TdAssetsTransHeaderDTO;
import com.sino.td.newasset.model.TdAssetsRcvHeaderModel;

/**
 * <p>Title: AmsAssetsRcvHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsRcvHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class TdAssetsRcvHeaderDAO extends AMSBaseDAO {

	/**
	 * ���ܣ������ʲ�����ͷ��(���ż�͹�˾����Ҫ) AMS_ASSETS_RCV_HEADER ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter TdAssetsRcvHeaderDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public TdAssetsRcvHeaderDAO(SfUserDTO userAccount, TdAssetsRcvHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		TdAssetsRcvHeaderDTO dto = (TdAssetsRcvHeaderDTO) dtoParameter;
		sqlProducer = new TdAssetsRcvHeaderModel((SfUserDTO) userAccount,dto);
	}

	/**
	 * ���ܣ����ݵ�����ͷ��Ϣ�ͽ��շ�������Ϣ�Զ����ɽ���������
	 * @param order TdAssetsTransHeaderDTO
	 * @param assignLines DTOSet
	 * @throws DataHandleException
	 */
	public void autoCreateRcvDatas(TdAssetsTransHeaderDTO order, DTOSet assignLines) throws DataHandleException {
		createRcvOrder(order);
		TdAssetsRcvLineDAO rcvLineDAO = new TdAssetsRcvLineDAO(userAccount, null, conn);
		TdAssetsRcvHeaderDTO dto = (TdAssetsRcvHeaderDTO) dtoParameter;
		rcvLineDAO.setRcvHeader(dto);
		rcvLineDAO.createRcvLines(assignLines);
	}

	/**
	 * ���ܣ����ݵ�����ͷ��Ϣ���ɵ������յ�ͷ��Ϣ
	 * @param order TdAssetsTransHeaderDTO
	 * @throws DataHandleException
	 */
	private void createRcvOrder(TdAssetsTransHeaderDTO order) throws
			DataHandleException {
		try {
			TdAssetsRcvHeaderDTO dto = new TdAssetsRcvHeaderDTO();
			SeqProducer seqProducer = new SeqProducer(conn);
			String rcvId = StrUtil.nullToString(seqProducer.getStrNextSeq("AMS_ASSETS_RCV_HEADER_S"));
			dto.setReceiveHeaderId(rcvId);
			String companyCode = userAccount.getCompanyCode();
			OrderNumGenerator noProducer = new OrderNumGenerator(conn, companyCode, AssetsDictConstant.ASS_RCV);
			dto.setReceiveNo(noProducer.getOrderNum());
			dto.setTransId(order.getTransId());
			dto.setReceiveUser(StrUtil.nullToString(userAccount.getUserId()));
			dto.setOrderStatus(AssetsDictConstant.SAVE_TEMP);
			dto.setReceiveOrganizationId(StrUtil.nullToString(userAccount.getOrganizationId()));
			dto.setCreatedBy(userAccount.getUserId());
			dto.setCurrCalendar("receiveDate");
			dto.setCurrCreationDate();
			dto.setFaContentCode(order.getFaContentCode());
			setDTOParameter(dto);
			TdAssetsRcvHeaderModel modelProducer = (TdAssetsRcvHeaderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getDataCreateModel();
			DBOperator.updateRecord(sqlModel, conn);
			add2Flow();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ������ڰ���
	 * @throws DataHandleException
	 */
	private void add2Flow() throws DataHandleException {
		try {
			TdAssetsRcvHeaderModel modelProducer = (TdAssetsRcvHeaderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getFirstTaskModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				Row row = simp.getFirstRow();
				TdAssetsRcvHeaderDTO dto = (TdAssetsRcvHeaderDTO) dtoParameter;
				FlowDTO flowDTO = new FlowDTO();
				flowDTO.setProcName(dto.getProcdureName());
				flowDTO.setProcId(row.getStrValue("PROC_ID"));
				flowDTO.setToTaskId(row.getStrValue("TASK_ID"));
				flowDTO.setSessionUserId(userAccount.getUserId());
				flowDTO.setApplyId(dto.getReceiveHeaderId());
				flowDTO.setApplyNo(dto.getReceiveNo());
				flowDTO.setApproveContent("���շ����ʲ����������ʲ����յ�");
				FlowAction flowProcessor = new FlowAction(conn);
				flowProcessor.setDto(flowDTO);
				flowProcessor.add2Flow(dto.getProcdureName());
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ��ύ�������յ�����һ����
	 * @param flowDTO FlowDTO
	 * @return boolean
	 */
	public boolean submitRcvOrder(FlowDTO flowDTO) {
		boolean operateResult = false;
		boolean autoCommit = true;
		TdAssetsRcvHeaderDTO dto = (TdAssetsRcvHeaderDTO) dtoParameter;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_NEW);
			flowDTO.setProcName(dto.getProcdureName());
			flowDTO.setActivity(FlowConstant.FLOW_CODE_NEXT);
			flowDTO.setApplyId(dto.getReceiveHeaderId());
			flowDTO.setSessionUserId(userAccount.getUserId());
			flowDTO.setSessionUserName(userAccount.getUsername());
			flowDTO.setApplyNo(dto.getReceiveNo());
			dto.setOrderStatus(AssetsDictConstant.IN_PROCESS);
			setDTOParameter(dto);
			updateData();
			FlowAction flowProcessor = new FlowAction(conn);
			flowProcessor.setDto(flowDTO);
			flowProcessor.flow();
			operateResult = true;
		} catch (QueryException ex) {
			ex.printLog();
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					conn.rollback();
					prodMessage(AssetsMessageKeys.RCVORDER_SUBMIT_FAILURE);
				} else {
					conn.commit();
					prodMessage(AssetsMessageKeys.RCVORDER_SUBMIT_SUCCESS);
				}
				conn.setAutoCommit(autoCommit);
				message.setIsError(!operateResult);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ��жϵ�ǰ�û���ָ�����̵�ָ��Ӧ�õĵ�ǰ�ڵ��Ƿ�ɽ�������
	 * @return boolean
	 */
	public boolean canApproveCurrTask() {
		boolean canApprove = false;
		try {
			TdAssetsRcvHeaderModel modelProducer = (TdAssetsRcvHeaderModel)
					sqlProducer;
			SQLModel sqlModel = modelProducer.getCanApproveModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				canApprove = true;
			}
		} catch (QueryException ex) {
			ex.printLog();
		}
		return canApprove;
	}

	/**
	 * ���ܣ����������ʲ����յ�
	 * @param flowDTO FlowDTO
	 * @return boolean
	 */
	public boolean approveRcvOrder(FlowDTO flowDTO) {
		boolean operateResult = false;
		boolean autoCommit = true;
		TdAssetsRcvHeaderDTO dto = (TdAssetsRcvHeaderDTO) dtoParameter;
		String flowCode = dto.getFlowCode();
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_NEW);
			flowDTO.setProcName(dto.getProcdureName());
			flowDTO.setActivity(FlowConstant.FLOW_CODE_NEXT);
			flowDTO.setApplyId(dto.getReceiveHeaderId());
			flowDTO.setSessionUserId(userAccount.getUserId());
			flowDTO.setSessionUserName(userAccount.getUsername());
			flowDTO.setApplyNo(dto.getReceiveNo());
			FlowAction flowProcessor = new FlowAction(conn, flowDTO);
			boolean flow2End = flowProcessor.isFlowToEnd();
			dto.setFlow2End(flow2End);
			if (flowCode.equals(FlowConstant.FLOW_CODE_NEXT)) {
				if (flow2End) {
					dto.setOrderStatus(AssetsDictConstant.APPROVED);
					flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_END);
				} else {
					dto.setOrderStatus(AssetsDictConstant.IN_PROCESS);
					flowDTO.setApproveContent(FlowConstant.
											  APPROVE_CONTENT_AGREE);
				}
				flowProcessor.setDto(flowDTO);
				flowProcessor.flow();
			} else {
				dto.setOrderStatus(AssetsDictConstant.REJECTED);
				flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_REJECT);
				flowProcessor.reject2Begin();
			}
			setDTOParameter(dto);
			updateData();
			operateResult = true;
		} catch (QueryException ex) {
			ex.printLog();
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					conn.rollback();
				} else {
					conn.commit();
				}
				processMessage(operateResult, flowCode);
				conn.setAutoCommit(autoCommit);
				message.setIsError(!operateResult);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ����쵥��������Ϣ��ʾ
	 * @param operateResult boolean
	 * @param flowCode String
	 */
	private void processMessage(boolean operateResult, String flowCode) {
		TdAssetsRcvHeaderDTO dto = (TdAssetsRcvHeaderDTO) dtoParameter;
		if (flowCode.equals(FlowConstant.FLOW_CODE_NEXT)) {
			if (operateResult) {
				prodMessage(AssetsMessageKeys.PASS_ORDER_SUCCESS);
			} else {
				prodMessage(AssetsMessageKeys.PASS_ORDER_FAILURE);
			}
		} else {
			if (operateResult) {
				prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
			} else {
				prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
			}
		}
		message.addParameterValue("�ʲ���������");
		message.addParameterValue(dto.getReceiveNo());
		message.setIsError(!operateResult);
	}
}
