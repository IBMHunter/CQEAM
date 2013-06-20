package com.sino.ams.spare.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.DateConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;

import com.sino.framework.dto.BaseUserDTO;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.spare.assistant.SpareMessageKeys;
import com.sino.ams.spare.dto.AmsItemAllocateDDTO;
import com.sino.ams.spare.dto.AmsItemAllocateHDTO;
import com.sino.ams.spare.model.BjDboModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.util.BillUtil;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.sms.bean.MessageSaver;
import com.sino.sms.constant.SMSConstant;
import com.sino.sms.dto.SfMsgDefineDTO;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */
public class BjDbDAO extends AMSProcedureBaseDAO {

	public BjDbDAO(SfUserDTO userAccount, AmsItemAllocateHDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
		super.sqlProducer = new BjDboModel((SfUserDTO) userAccount, dto);
	}

	/**
	 * ���ܣ����뱸������ͷ��(AMS)��AMS_ITEM_TRANS_H�����ݡ�
	 */
	public void createData() throws DataHandleException {
		super.createData();
		getMessage().addParameterValue("��������ͷ��(AMS)");
	}

	/**
	 * ���ܣ����±�������ͷ��(AMS)��AMS_ITEM_TRANS_H�����ݡ�
	 */
	public void updateData() throws DataHandleException {
		super.updateData();
		getMessage().addParameterValue("��������ͷ��(AMS)");
	}

	/**
	 * ���ܣ�ɾ����������ͷ��(AMS)��AMS_ITEM_TRANS_H�����ݡ�
	 */
	public void deleteData() throws DataHandleException {
		super.deleteData();
		getMessage().addParameterValue("��������ͷ��(AMS)");
	}

	/**
	 * ���浥��
	 * @param lineSet ������
	 * @param flowDTO FlowDTO
	 * @return operateResult
	 * @throws java.sql.SQLException
	 */
	public boolean saveOrder(DTOSet lineSet, FlowDTO flowDTO) throws SQLException {
		boolean operateResult = true;
		boolean autoCommit = false;
		try {
			AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String transId = dto.getTransId();
			if (transId.equals("")) {
				OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), dto.getTransType());
				dto.setTransNo(ong.getOrderNum());
				SeqProducer seq = new SeqProducer(conn);
				transId = seq.getGUID();
                dto.setTransStatus(DictConstant.SAVE_TEMP);
                dto.setTransId(transId);
				createData();
				flowDTO.setApplyId(transId);
				flowDTO.setApplyNo(dto.getTransNo());
				FlowAction fa = new FlowAction(conn);
				fa.setDto(flowDTO);
				fa.add2Flow("������������");
			} else {
				updateData();
				deleteLines(transId);
			}
			saveLines(lineSet);
			conn.commit();
			prodMessage("SAVE_SUCCESS");
			operateResult = true;
		} catch (SQLException e) {
			conn.rollback();
			operateResult = false;
			Logger.logError(e);
			prodMessage("SAVE_FAILURE");
		} catch (DataHandleException e) {
			conn.rollback();
			operateResult = false;
			Logger.logError(e);
			prodMessage("SAVE_FAILURE");
		} catch (QueryException e) {
			conn.rollback();
			operateResult = false;
			Logger.logError(e);
			prodMessage("SAVE_FAILURE");
		} finally {
			conn.setAutoCommit(autoCommit);
		}
		return operateResult;
	}

    /**
     * ��������
     *
     * @param lineSet ������
     * @param flowDTO FlowDTO
     * @return operateResult
     * @throws java.sql.SQLException
     */
    public boolean cancelOrder(DTOSet lineSet, FlowDTO flowDTO) throws SQLException {
        boolean operateResult = false;
        boolean autoCommit = false;
        try {
            AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String transId = dto.getTransId();
            FlowAction fa = new FlowAction(conn);
            flowDTO.setProcName("������������");
            BillUtil.updateStatus("AMS_ITEM_ALLOCATE_H", "TRANS_ID", transId, "TRANS_STATUS", DictConstant.CANCELED, conn); // ��������
            flowDTO.setApplyId(transId); //ɾ���ڰ�������
            fa.setDto(flowDTO);
            fa.cancel();
            operateResult = true;
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (DataHandleException e) {
            Logger.logError(e);
        } finally {
          try {
				if (operateResult) {
					conn.commit();
					prodMessage(AssetsMessageKeys.ORDER_CANCEL_SUCCESS);
				} else {
					conn.rollback();
					prodMessage(AssetsMessageKeys.ORDER_CANCEL_FAILURE);
				}
				conn.setAutoCommit(autoCommit);
              	message.addParameterValue("��������");
                message.setIsError(!operateResult);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
				prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
			}
        }
        return operateResult;
	}

    /**
	 * ���ܣ����ɵ��������ύ����
	 * @param lineSet ������
	 * @return operateResult
	 * @throws java.sql.SQLException
	 */
	public boolean submitOrder(DTOSet lineSet, AmsItemAllocateHDTO allocationDTO) throws SQLException, ContainerException {
		boolean operateResult = false;
		boolean autoCommit = false;
		AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String transId = dto.getTransId();
			dto.setTransDate(CalendarUtil.getCurrDate());
			if (transId.equals("")) {
				OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), dto.getTransType());
				dto.setTransNo(ong.getOrderNum());
				SeqProducer seq = new SeqProducer(conn);
				transId = seq.getGUID();
				dto.setTransId(transId);
				createData();
			} else {
				updateData();
				deleteLines(transId);
			}
            updateOut(lineSet, transId);//�ύʱ��д������(2010.1.8 SU)
            setDTOParameter(dto);
			saveLines(lineSet);
			allocationDTO.setPrimaryKey(transId);
            allocationDTO.setOrderNo(dto.getTransNo());
            allocationDTO.setOrderName("��������");
            processProcedure(true);
			conn.commit();
			operateResult = true;
		} catch (SQLException e) {
			Logger.logError(e);
		} catch (DataHandleException e) {
			Logger.logError(e);
		} catch (CalendarException e) {
			Logger.logError(e);
		} finally {
			if(operateResult){
				conn.commit();
				prodMessage(SpareMessageKeys.TRANS_SUBMIT_SUCCESS);
				message.addParameterValue(dto.getTransNo());
			} else {
				conn.rollback();
				prodMessage(SpareMessageKeys.TRANS_SUBMIT_FAILURE);
				message.setNeedClose(true);
			}
			message.setIsError(!operateResult);
			conn.setAutoCommit(autoCommit);
		}
		return operateResult;
	}

	public void updateOut(DTOSet lineSet, String transId) throws SQLException {
		CallableStatement cStmt = null;
		String sqlStr = "{call dbo.AITS_WRITE_SAVE_QTY(?,?,?)}";
		try {
			cStmt = conn.prepareCall(sqlStr);
			AmsItemAllocateHDTO headerDTO = (AmsItemAllocateHDTO)dtoParameter;
			for (int i = 0; i < lineSet.getSize(); i++) {
				AmsItemAllocateDDTO dto = (AmsItemAllocateDDTO) lineSet.getDTO(i);
				cStmt.setString(1, transId);
				cStmt.setString(2, dto.getBarcode());
				cStmt.setInt(3, dto.getQuantity());
				cStmt.execute();
			}
		} finally {
			DBManager.closeDBStatement(cStmt);
		}
	}

    public void cancelDB(DTOSet lineSet, AmsItemAllocateHDTO hdto) throws SQLException {
        CallableStatement cStmt = null;
        String sqlStr = "{call AMS_ITEM_TRANS_SX.CANCEL_RESERVED_QTY(?,?,?)}";
        try {
            cStmt = conn.prepareCall(sqlStr);
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsItemAllocateDDTO dto = (AmsItemAllocateDDTO) lineSet.getDTO(i);
                cStmt = conn.prepareCall(sqlStr);
                cStmt.setString(1, hdto.getTransId());
                cStmt.setString(2, dto.getBarcode());
                cStmt.setInt(3, dto.getQuantity());
                cStmt.execute();
            }
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }

    /**
	 * ���ܣ�������������������
	 * @param lineSet
	 * @return
	 * @throws SQLException
	 * @throws ContainerException
	 */

	public boolean approveOrder(DTOSet lineSet, AmsItemAllocateHDTO allocationDTO) throws SQLException, ContainerException {
		boolean operateResult = false;
		boolean autoCommit = false;
		AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			//���̴���
			String transId = dto.getTransId();
		   if(allocationDTO.getSf_task_attribute1().equals("OUT")){
				deleteLines(transId);
				saveLines(lineSet);
		   }
		   if (allocationDTO.getSf_end().equals("1")) {
//                updateOut(lineSet); //�ύʱд��������ͨ�������ڵ㲻��Ҫд��2010.1.8 SU��
				BjDboModel model = new BjDboModel(userAccount, null);
				DBOperator.updateRecord(model.getUpdateHStatusModel(dto.getTransId()), conn);
		   }
           allocationDTO.setPrimaryKey(dto.getTransId());
           allocationDTO.setOrderNo(dto.getTransNo());
           allocationDTO.setOrderName("��������");
           processProcedure(true);
		   conn.commit();
     	   operateResult = true;
		} catch (SQLException e) {
			Logger.logError(e);
		} catch (DataHandleException e) {
			Logger.logError(e);
		} finally {
			if(operateResult){
				conn.commit();
				prodMessage(SpareMessageKeys.TRANS_APPROVE_SUCCESS);
			} else {
				conn.rollback();
				prodMessage(SpareMessageKeys.TRANS_APPROVE_FAILURE);
				message.setNeedClose(true);
			}
			message.addParameterValue(dto.getTransNo());
			message.setIsError(!operateResult);
			conn.setAutoCommit(autoCommit);
		}
		return operateResult;
	}


	/**
	 * ���ܣ������ֻ����Ÿ�����������
	 * @throws DataHandleException
	 */
	private void saveMessage() throws DataHandleException {

		AmsItemAllocateHDTO headerDTO = (AmsItemAllocateHDTO) dtoParameter;
		String userId = "";
		String orderNum = headerDTO.getTransNo();
		String userName = "";
		String userTel = "";
		Row row = null;
		try {
			MessageSaver msgSaver = new MessageSaver(conn);
			String fromOrgId = getFromOrgId(conn, headerDTO.getTransId());
			String toCompanyName = getToCompanyName(conn, headerDTO.getTransId());

			if (!StrUtil.isEmpty(fromOrgId)) {
				RowSet rows = getUserNames(conn, fromOrgId);
				if (rows != null && rows.getSize() > 0) {
					for (int i = 0; i < rows.getSize(); i++) {
						row = rows.getRow(i);

						userId = row.getValue("USER_ID").toString();
						userName = row.getValue("USERNAME").toString();
						userTel = getMovetel(conn, userId);

						SfMsgDefineDTO msgDefineDTO = new SfMsgDefineDTO();
						msgDefineDTO.setMsgCategoryId(SMSConstant.SPARE_ALLOT_ID);
						msgDefineDTO.setCreatedBy(userAccount.getUserId());
						msgDefineDTO.setCellPhone(userTel);
						msgDefineDTO.setApplyNumber(orderNum);
						msgDefineDTO.setUserId(userId);
						msgDefineDTO.setMsgContent("����" + userName + "���밲�Ź�˾�����ֿ������" + toCompanyName + "��˾����������:" + orderNum);
						msgSaver.saveMsg(msgDefineDTO);

					}
				}
			}
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (ContainerException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (Exception ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}


	private String getFromOrgId(Connection conn, String transId) throws QueryException, ContainerException {
		String fromOrgId = "";
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT" +
				" AIAH.FROM_ORGANIZATION_ID\n" +
				" FROM " +
				" AMS_ITEM_ALLOCATE_H AIAH\n" +
				" WHERE " +
				" AIAH.TRANS_ID = ?";
		sqlArgs.add(transId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
		sq.executeQuery();
		Row row = sq.getFirstRow();
		fromOrgId = row.getValue("FROM_ORGANIZATION_ID").toString();
		return fromOrgId;
	}

	/**
	 * ���ܣ�������͵Ĺ�˾������
	 * @param conn
	 * @param transId
	 * @return
	 * @throws QueryException
	 * @throws ContainerException
	 */
	private String getToCompanyName(Connection conn, String transId) throws QueryException, ContainerException {
		String toCompanyName = "";
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT " +
				" AIAH.TO_ORGANIZATION_ID,\n" +
				" AMS_PUB_PKG.GET_ORGNIZATION_NAME(AIAH.TO_ORGANIZATION_ID) COMPANY_ANME\n" +
				" FROM " +
				" AMS_ITEM_ALLOCATE_H AIAH\n" +
				" WHERE " +
				" AIAH.TRANS_ID = ?";
		sqlArgs.add(transId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
		sq.executeQuery();
		Row row = sq.getFirstRow();
		toCompanyName = row.getValue("COMPANY_ANME").toString();
		return toCompanyName;
	}


	private RowSet getUserNames(Connection conn, String orgId) throws QueryException, ContainerException {
		String fromOrgId = "";
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT DISTINCT " +
				" SU.USER_ID,\n" +
				" SU.USERNAME,\n" +
				" SU.ORGANIZATION_ID,\n" +
				" AMS_PUB_PKG.GET_ORGNIZATION_NAME(SU.ORGANIZATION_ID)\n" +
				" FROM " +
				" SF_USER SU, SF_USER_RIGHT SUR, SF_ROLE SR\n" +
				" WHERE " +
				" SUR.ROLE_ID = SR.ROLE_ID\n" +
				" AND SUR.USER_ID = SU.USER_ID\n" +
				" AND (SR.ROLENAME LIKE '%���б�Ʒ��������Ա%' OR SR.ROLENAME LIKE '%ʡ��˾��Ʒ��������Ա%')\n" +
				" AND SU.ORGANIZATION_ID = ?";
		sqlArgs.add(orgId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
		sq.executeQuery();
		return sq.getSearchResult();
	}

	/**
	 * @param conn
	 * @param userId
	 * @return
	 * @throws QueryException
	 * @throws ContainerException
	 */
	private String getMovetel(Connection conn, String userId) throws QueryException, ContainerException {
		String movetel = "";
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = " SELECT DISTINCT SU.MOVETEL FROM SF_USER SU WHERE SU.USER_ID = ?";
		sqlArgs.add(userId);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		SimpleQuery sq = new SimpleQuery(sqlModel, conn);
		sq.executeQuery();
		Row row = sq.getFirstRow();
		movetel = row.getValue("MOVETEL").toString();
		return movetel;
	}

	public boolean reject(AmsItemAllocateHDTO dto, FlowDTO flowDTO, DTOSet lineSet) throws SQLException {
		boolean operateResult = false;
		try {
			conn.setAutoCommit(false);
			//ҵ����
			BjDboModel model = new BjDboModel(userAccount, dto);
			String status = DictConstant.REJECTED;
			DBOperator.updateRecord(model.updateStatusModel(status), conn);
			flowDTO.setProcName("������������");
            cancelDB(lineSet, dto);//�˻ص��ݱ���������
            FlowAction fb = new FlowAction(conn, flowDTO);
			fb.reject2Begin();
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally {
			if(operateResult){
				conn.commit();
				prodMessage(SpareMessageKeys.TRANS_REJECT_SUCCESS);
			} else {
				conn.rollback();
				prodMessage(SpareMessageKeys.TRANS_REJECT_FAILURE);
			}
			message.addParameterValue(dto.getTransNo());
			message.setIsError(!operateResult);
			conn.setAutoCommit(true);
		}
        return operateResult;
    }

	/**
	 * ��������Ϣ�����ݱ�
	 * @param lineSet ������
	 */
	public void saveLines(DTOSet lineSet) throws DataHandleException, SQLException {
		if (lineSet != null && !lineSet.isEmpty()) {
			CallableStatement cStmt = null;
			String sqlStr = "{call dbo.AITS_SAVE_DIAOBO_LINE(?,?,?,?)}";
			try {
				AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
				cStmt = conn.prepareCall(sqlStr);
				for (int i = 0; i < lineSet.getSize(); i++) {
					AmsItemAllocateDDTO lineData = (AmsItemAllocateDDTO) lineSet.getDTO(i);
					cStmt.setString(1, dto.getTransId());
					cStmt.setString(2, lineData.getBarcode());
					cStmt.setString(3, "-1");
					cStmt.setInt(4, lineData.getQuantity());
					cStmt.execute();
				}
			} finally {
				DBManager.closeDBStatement(cStmt);
			}
		}
	}

	public void deleteLines(String transId) throws DataHandleException {
		BjDboModel model = new BjDboModel(userAccount, null);
		DBOperator.updateRecord(model.getDeleteByTransIdModel(transId), conn);
	}

	/**
	 * дAMS_SPARE_INFO��
	 * @param lineSet ������
	 * @throws com.sino.base.exception.DataHandleException
	 *
	 */
	private void addSpareInfo(DTOSet lineSet) throws DataHandleException, SQLException {
		if (lineSet != null && !lineSet.isEmpty()) {
			CallableStatement cStmt = null;
			String sqlStr = "{call AMS_INV_TRANS.ADD_SPARE_INFO(?,?,?,?,?,?)}";
			try {
				AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
				cStmt = conn.prepareCall(sqlStr);
				for (int i = 0; i < lineSet.getSize(); i++) {
					AmsItemAllocateDDTO lineData = (AmsItemAllocateDDTO) lineSet.getDTO(i);
					cStmt.setString(1, lineData.getBarcode());
					cStmt.setString(2, lineData.getItemCode());
					cStmt.setInt(3, lineData.getQuantity());
					cStmt.setInt(4, userAccount.getOrganizationId());
					cStmt.setInt(5, userAccount.getUserId());
					cStmt.setString(6, dto.getToObjectNo());
					cStmt.execute();
				}
			} finally {
				DBManager.closeDBStatement(cStmt);
			}
		}
	}

	/**
	 * �����������ڲֿ�Ϊ��;��
	 * @param dtoSet DTOSet
	 * @throws java.sql.SQLException
	 */
	private void updateAddressIdOnWay(DTOSet dtoSet) throws SQLException {
		if (dtoSet != null && !dtoSet.isEmpty()) {
			CallableStatement cStmt = null;
			String sqlStr = "{call AMS_ITEM_TRANS.ITEM_ON_THE_WAY(?,?,?)}";
			try {
				AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
				cStmt = conn.prepareCall(sqlStr);
				for (int i = 0; i < dtoSet.getSize(); i++) {
					AmsItemAllocateDDTO lineData = (AmsItemAllocateDDTO) dtoSet.getDTO(i);
					cStmt.setString(1, lineData.getBarcode());
					cStmt.setInt(2, dto.getToOrganizationId());
					cStmt.setInt(3, userAccount.getUserId());
					cStmt.execute();
				}
			} finally {
				DBManager.closeDBStatement(cStmt);
			}
		}
	}

	public void InvStorageIn(DTOSet lineSet) throws SQLException {
		if (lineSet != null && !lineSet.isEmpty()) {
			CallableStatement cStmt = null;
			String sqlStr = "{call AMS_ITEM_TRANS.INV_STORAGE_IN(?,?,?,?,?,?)}";
			try {
				AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
				cStmt = conn.prepareCall(sqlStr);
				for (int i = 0; i < lineSet.getSize(); i++) {
					AmsItemAllocateDDTO lineData = (AmsItemAllocateDDTO) lineSet.getDTO(i);
					cStmt.setString(1, lineData.getBatchNo());
					cStmt.setString(2, dto.getToObjectNo());
					cStmt.setInt(3, dto.getToOrganizationId());
					cStmt.setString(4, lineData.getItemCode());
					cStmt.setInt(5, lineData.getQuantity());
					cStmt.setInt(6, userAccount.getUserId());
					cStmt.execute();
				}
			} finally {
				DBManager.closeDBStatement(cStmt);
			}
		}
	}

	public void InvStorageOut(DTOSet lineSet) throws SQLException {
		if (lineSet != null && !lineSet.isEmpty()) {
			CallableStatement cStmt = null;
			String sqlStr = "{call AMS_ITEM_TRANS.INV_STORAGE_OUT(?,?,?)}";
			try {
				cStmt = conn.prepareCall(sqlStr);
				for (int i = 0; i < lineSet.getSize(); i++) {
					AmsItemAllocateDDTO lineData = (AmsItemAllocateDDTO) lineSet.getDTO(i);
					cStmt.setInt(3, userAccount.getUserId());
					cStmt.execute();
				}
			} finally {
				DBManager.closeDBStatement(cStmt);
			}
		}
	}

	public boolean submitOrderFk(DTOSet lineSet, FlowDTO flowDTO) throws SQLException {
		boolean operateResult = true;
		boolean autoCommit = false;
		try {
			AmsItemAllocateHDTO dto = (AmsItemAllocateHDTO) dtoParameter;
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String transId = dto.getTransId();
			dto.setTransDate(CalendarUtil.getCurrDate());
			FlowAction fa = null;

			if (transId.equals("")) {
				OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), dto.getTransType());
				dto.setTransNo(ong.getOrderNum());
				SeqProducer seq = new SeqProducer(conn);
				transId = seq.getGUID();
				dto.setTransId(transId);
				createData();
			} else {
				updateData();
				//�����ݣ�ɾ����Ϣ
				deleteLines(transId);
			}
			saveLines(lineSet);
			if (dto.getTransType().equals(DictConstant.BJRK)) {
				addSpareInfo(lineSet);

			} else if (dto.getTransType().equals(DictConstant.TMRK)) { //�����豸���
			} else if (dto.getTransType().equals(DictConstant.TMCK)) { //�����豸����
			} else if (dto.getTransType().equals(DictConstant.FTMRK)) {//�������豸���
				InvStorageIn(lineSet);
			} else if (dto.getTransType().equals(DictConstant.FTMCK)) {//�������豸����
				InvStorageOut(lineSet);
			} else if (dto.getTransType().equals(DictConstant.BJFK)) {
				//�ж������Ƿ����,�����޸ĵص�,û����������
				addSpareInfo(lineSet);
			} else if (dto.getTransType().equals(DictConstant.BJSL)) {
				flowDTO.setApplyId(transId);
				flowDTO.setApplyNo(dto.getTransNo());
				flowDTO.setActivity(FlowConstant.FLOW_CODE_NEXT);
				fa = new FlowAction(conn, flowDTO);
				fa.flow();
			}
			conn.commit();
			prodMessage("SAVE_SUCCESS");
			operateResult = true;
		} catch (SQLException e) {
			conn.rollback();
			operateResult = false;
			Logger.logError(e);
			prodMessage("SAVE_FAILURE");
		} catch (DataHandleException e) {
			conn.rollback();
			operateResult = false;
			Logger.logError(e);
			prodMessage("SAVE_FAILURE");
		} catch (CalendarException e) {
			conn.rollback();
			operateResult = false;
			Logger.logError(e);
			prodMessage("SAVE_FAILURE");
		} catch (QueryException e) {
			conn.rollback();
			operateResult = false;
			Logger.logError(e);
			prodMessage("SAVE_FAILURE");
		} finally {
			conn.setAutoCommit(autoCommit);
		}
		return operateResult;
	}


	public String getObjectN0() throws QueryException, ContainerException {
		String objectN0 = "";
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =
				"SELECT EO.WORKORDER_OBJECT_NO, EO.WORKORDER_OBJECT_LOCATION||EO.WORKORDER_OBJECT_NAME\n" +
						"  FROM ETS_OBJECT EO\n" +
						" WHERE (EO.OBJECT_CATEGORY = '71' )\n" +
						"   AND EO.ORGANIZATION_ID = ?";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			objectN0 = simpleQuery.getFirstRow().getStrValue("WORKORDER_OBJECT_NO");
		} else {
			objectN0 = "";
		}
		return objectN0;
	}

	public RowSet getLines(String transId) throws QueryException {
		BjDboModel model = (BjDboModel) sqlProducer;
		SimpleQuery sq = new SimpleQuery(model.getDataByTransIdModel(transId), conn);
		sq.executeQuery();
		return sq.getSearchResult();
	}

	public RowSet getPrintLines(String transId) throws QueryException {
		BjDboModel model = (BjDboModel) sqlProducer;

		SimpleQuery sq = new SimpleQuery(model.getModel(transId), conn);
		sq.executeQuery();
		sq.setCalPattern(DateConstant.LINE_PATTERN);
		return sq.getSearchResult();
	}

	public File exportFile(String transId) throws DataTransException {
		File file = null;
		BjDboModel model = new BjDboModel(userAccount, null);
		SQLModel sqlModel = model.getModel(transId);
		TransRule rule = new TransRule();
		rule.setDataSource(sqlModel);
		rule.setSourceConn(conn);

		String fileName = "����������ϸͳ�Ʊ�.xls";
		String filePath = WorldConstant.USER_HOME;
		filePath += WorldConstant.FILE_SEPARATOR;
		filePath += fileName;
		rule.setTarFile(filePath);

		DataRange range = new DataRange();
		rule.setDataRange(range);

		Map fieldMap = new HashMap();
		fieldMap.put("TRANS_NO", "�������ݺ�");
		fieldMap.put("ITEM_NAME", "��������");
		fieldMap.put("BARCODE", "������");
		fieldMap.put("ITEM_SPEC", "����ͺ�");
		fieldMap.put("VENDOR_NAME", "�豸����");
		fieldMap.put("FROM_ORGANIZATION_NAME", "ԭ��ŵ�");
		fieldMap.put("TO_ORGANIZATION_NAME", "�ִ�ŵ�");
		fieldMap.put("CREATION_DATE", "����ʱ��");
		rule.setFieldMap(fieldMap);

		CustomTransData custData = new CustomTransData();
		custData.setReportTitle(fileName);
		custData.setReportPerson(userAccount.getUsername());
		custData.setNeedReportDate(true);
		rule.setCustData(custData);
		/*rule.setSheetSize(1000);*/
		//���÷�ҳ��ʾ
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		file = (File) transfer.getTransResult();
		return file;
	}

    public String checkKYL(String barcode, String fromObjectNo) throws QueryException, ContainerException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT (ASI.QUANTITY - ASI.RESERVE_QUANTITY) ONHAND_QTY\n" +
                "  FROM AMS_SPARE_CATEGORY AC,\n" +
                "       ETS_OBJECT         EO,\n" +
                "       AMS_SPARE_INFO     ASI,\n" +
                "       AMS_SPARE_VENDORS  ASV\n" +
                " WHERE ASI.BARCODE = AC.BARCODE\n" +
                "       AND EO.WORKORDER_OBJECT_NO = ASI.OBJECT_NO\n" +
                "       AND AC.VENDOR_ID = ASV.VENDOR_ID\n" +
                "       AND EO.OBJECT_CATEGORY = 71\n" +
                "       AND AC.ENABLED = 'Y'\n" +
                "       AND (ASI.QUANTITY - ASI.RESERVE_QUANTITY) > 0\n" +
                "       AND ASI.BARCODE = ?\n" +
                "       AND EO.WORKORDER_OBJECT_NO = ?";
        sqlArgs.add(barcode);
        sqlArgs.add(fromObjectNo);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        String onhandQty = "";
        if (sq.hasResult()) {
            onhandQty = sq.getFirstRow().getStrValue("ONHAND_QTY");
        } else {
            onhandQty = "0";
        }
        return onhandQty;
    }
}
