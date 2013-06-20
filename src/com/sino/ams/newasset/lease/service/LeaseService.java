package com.sino.ams.newasset.lease.service;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.newasset.lease.constant.LeaseAppConstant;
import com.sino.ams.newasset.lease.dao.LeaseDAO;
import com.sino.ams.newasset.lease.dto.LeaseDTO;
import com.sino.ams.newasset.lease.dto.LeaseHeaderDTO;
import com.sino.ams.newasset.lease.dto.LeaseLineDTO;
import com.sino.ams.newasset.service.AssetsBaseService;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * @ϵͳ����: ����
 * @��������:
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Jul 14, 2011
 */
public class LeaseService extends AssetsBaseService {
	static String ORDER_TITLE = "���ⵥ";
	private LeaseDTO leaseDTO = null;
	private LeaseHeaderDTO headerDTO = null;

	private DTOSet lines = null;

	private  LeaseDAO headerDAO = null;

	private  String msg = null;

	public LeaseService(SfUserDTO user, LeaseDTO dto, Connection conn) {
		super(user, dto, conn);
		this.init(user, dto, conn);
	}

	
	/**
	 * ����
	 * 
	 * @param
	 * @return
	 */
	public boolean doCancel() {
		boolean operateResult = false;
		boolean autoCommit = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			headerDTO.setTransStatus(AssetsDictConstant.CANCELED);
			headerDAO.updateHeaderStatus(headerDTO);
			super.deleteReserved(headerDTO.getTransId());
			headerDTO.setApp_dataID( headerDTO.getTransId() ); 
			headerDAO = new LeaseDAO( userAccount , headerDTO, conn);
			operateResult = super.cancelProcedure();
			
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					this.msg = ORDER_TITLE + "(" + headerDTO.getTransNo() +  ")" +  "����ʧ��";
					conn.rollback();
				} else {
					this.msg = ORDER_TITLE + "(" + headerDTO.getTransNo() +  ")" +   "�����ɹ�";
					conn.commit();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * �˻�
	 * 
	 * @param
	 * @return
	 */
	public boolean doReturn() {
		boolean operateResult = false;
		boolean autoCommit = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			headerDTO.setTransStatus(AssetsDictConstant.REJECTED);
			headerDAO.updateHeaderStatus(headerDTO);
			headerDTO.setApp_dataID( headerDTO.getTransId() ); 
			headerDAO = new LeaseDAO( userAccount , headerDTO, conn);
			operateResult = super.rejectProcedure();

		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					this.msg = ORDER_TITLE + "(" + headerDTO.getTransNo() +  ")" +  "�˻�ʧ��";
					conn.rollback();
				} else {
					this.msg = ORDER_TITLE + "(" + headerDTO.getTransNo() +  ")" +  "�˻سɹ�";
					conn.commit();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}
	
	/**
	 * ����
	 * 
	 * @param
	 * @return
	 */
	public boolean doSave() {
		boolean operateResult = false;
		boolean autoCommit = true;
		String transType = headerDTO.getTransType();
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			headerDTO.setTransStatus(AssetsDictConstant.SAVE_TEMP);
			this.saveHeader();
			this.saveLines();
			operateResult = super.processProcedure(false);
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			Logger.logError(ex);
		} catch (CalendarException ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					this.msg = ORDER_TITLE + "(" + headerDTO.getTransNo() +  ")" +  "����ʧ��";
					conn.rollback();
				} else {
					this.msg = ORDER_TITLE + "(" + headerDTO.getTransNo() +  ")" +  "����ɹ�";
					conn.commit();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ����
	 * 
	 * @param
	 * @return
	 */
	public boolean doSubmit() {
		boolean operateResult = false;
		boolean autoCommit = true;
		// String transType = headerDTO.getTransType();
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String sfAtt3 = headerDTO.getSf_task_attribute3();
			String flowCode = headerDTO.getFlowCode();
			this.msg = ORDER_TITLE + "�ύ";

			if (sfAtt3.equals(LeaseAppConstant.ATT3_FILL_DATA)) {
				headerDTO.setTransStatus(AssetsDictConstant.IN_PROCESS);
				this.saveHeader();
				this.saveLines();
			} else if (sfAtt3.equals(LeaseAppConstant.ATT3_APPROVING)) {
				if (flowCode.equals("A1")) {
					headerDTO.setTransStatus(AssetsDictConstant.APPROVED);
				} else { // ����
					super.deleteReserved(headerDTO.getTransId());
					headerDTO.setTransStatus(AssetsDictConstant.CANCELED);
				}
				headerDAO.updateHeaderStatus(headerDTO);
			} else if (sfAtt3.equals(LeaseAppConstant.ATT3_ASS_APPROVE)) {
				headerDTO.setTransStatus(AssetsDictConstant.COMPLETED);
				headerDAO.completeOrder(headerDTO);
				
				/****�ڴ˴�����EII�е��ʲ�״̬��'����'��Ϊ'����'��ʧЧ�����ÿ�****/
				prodLines();
				if (lines != null && !lines.isEmpty()) {
					for (int i = 0; i < lines.getSize(); i++) {
						LeaseLineDTO line = (LeaseLineDTO) lines.getDTO(i);
						headerDAO.updateEii(line);
					}
				}
				super.deleteReserved(headerDTO.getTransId());
				completeRent();
				// ��¼�ʲ�������
				saveItemInfoHistory();
			}
			operateResult = super.processProcedure(true);
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			Logger.logError(ex);
		} catch (CalendarException ex) {
			Logger.logError(ex);
		} catch (QueryException ex) {
			ex.printLog();
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					this.msg += ORDER_TITLE + "(" + headerDTO.getTransNo() +  ")" +  "�ύʧ��";
					conn.rollback();
				} else {
					this.msg = ORDER_TITLE + "(" + headerDTO.getTransNo() +  ")" +   "�ύ�ɹ�";
					conn.commit();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
		return operateResult;
	}

	/**
	 * ��¼�����ʷ
	 * 
	 * @throws DataHandleException
	 */
	private void saveItemInfoHistory() throws DataHandleException {
		try {
			prodLines();
			if (lines != null && !lines.isEmpty()) {
				String orderURL = "/servlet/com.sino.ams.newasset.lease.servlet.LeaseServlet";
				orderURL += "?act=DETAIL_ACTION";
				orderURL += "&transId=" + headerDTO.getTransId();
				for (int i = 0; i < lines.getSize(); i++) {
					LeaseLineDTO line = (LeaseLineDTO) lines.getDTO(i);

					AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();
					historyDTO.setBarcode(line.getBarcode());
					historyDTO
							.setOrderCategory(LeaseAppConstant.ORDER_CATEGORY);
					historyDTO.setOrderNo(headerDTO.getOrderNo());
					historyDTO.setOrderDtlUrl(orderURL);
					historyDTO.setRemark(LeaseAppConstant.TRANS_TYPE_NAME);

					super.saveItemInfoHistory(historyDTO);
				}
			}
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (Throwable ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex.getMessage());
		}
	}

	/**
	 * ���ܣ��������
	 * 
	 * @return String
	 * @throws SQLException
	 */
	public void completeRent() throws SQLException {
		CallableStatement cst = null;
		String sqlStr = "{CALL dbo.ASSET_LEASE_COMPLETE(?)}";
		try {
			cst = conn.prepareCall(sqlStr);
			cst.setString(1, headerDTO.getTransId());
			cst.execute();
		} finally {
			DBManager.closeDBStatement(cst);
		}
	}

	/**
	 * ����ͷ
	 * 
	 * @throws DataHandleException
	 * @throws SQLException
	 */
	public void saveHeader() throws DataHandleException, SQLException {
		if (headerDTO.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
			if (StrUtil.isEmpty(headerDTO.getTransId())) {
				SeqProducer seqProducer = new SeqProducer(conn);
				String transId = seqProducer.getGUID();
				headerDTO.setTransId(transId);
			}
			String companyCode = userAccount.getCompanyCode(); // ���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ
			String transType = headerDTO.getTransType();
			OrderNumGenerator numberProducer = new OrderNumGenerator(conn,
					companyCode, transType);
			headerDTO.setTransNo(numberProducer.getOrderNum());
			headerDAO.createHeader(headerDTO);
		} else {
			headerDAO.updateHeader(headerDTO);
		}
	}

	/**
	 * ������
	 * 
	 * @throws DataHandleException
	 * @throws CalendarException
	 */
	public void saveLines() throws DataHandleException, CalendarException {
		String transId = headerDTO.getTransId();
		if (!StrUtil.isEmpty(transId)) {
			super.deleteReserved(transId);
			headerDAO.deleteLine(transId);
		}
		LeaseLineDTO line = null;
		String lineId = null;
		SeqProducer seqProducer = new SeqProducer(conn);
		for (int i = 0; i < lines.getSize(); i++) {
			line = (LeaseLineDTO) lines.getDTO(i);
			lineId = seqProducer.getGUID();
			line.setLineId(lineId);
			line.setTransId(headerDTO.getTransId());
			headerDAO.createLine(line);
			super.createReserved(transId, line.getBarcode());
		}
	}

	/**
	 * ��ʼ��ͷ��Ϣ
	 * 
	 * @param dto
	 * @return
	 */
	private LeaseHeaderDTO initHeaderData(LeaseHeaderDTO dto) {
		dto.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD); // ���õ��ݺ�
		dto.setCreatedBy(userAccount.getUserId()); // ���ô�����
		dto.setCreated(userAccount.getUsername()); // ���ô�����
		dto.setFromOrganizationId(userAccount.getOrganizationId());
		dto.setFromCompanyName(userAccount.getCompany());
		dto.setTransTypeValue(LeaseAppConstant.TRANS_TYPE_NAME);
		dto.setTransType(LeaseAppConstant.TRANS_TYPE);

		dto.setCurrCreationDate();
		dto.setEmail(userAccount.getEmail());
		dto.setPhoneNumber(userAccount.getMobilePhone());
		return dto;
	}

	/**
	 * ȡ��ϸ����
	 * 
	 * @throws QueryException
	 */
	public void prodData() throws QueryException {
		headerDTO = this.setFlowIdToDTO(headerDTO);
		prodHeader();
		prodLines();
	}

	/**
	 * ȡͷ��Ϣ
	 * 
	 * @throws QueryException
	 */
	private void prodHeader() throws QueryException {
		headerDAO.setDTOClassName(LeaseHeaderDTO.class.getName());
		headerDAO.setCalPattern(CalendarConstant.LINE_PATTERN);

		LeaseHeaderDTO tmpDTO = (LeaseHeaderDTO) headerDTO.clone();

		headerDTO = (LeaseHeaderDTO) headerDAO.getDataByPrimaryKey();
		// ���½�ʱ��
		if (null == headerDTO || StrUtil.isEmpty(headerDTO.getTransId())) {
			headerDTO = new LeaseHeaderDTO();
			headerDTO = initHeaderData(headerDTO);
		}
		headerDTO.setSf_task_attribute3(tmpDTO.getSf_task_attribute3());

		leaseDTO.setHeaderDTO(headerDTO);
	}

	/**
	 * ȡ����Ϣ
	 * 
	 * @throws QueryException
	 */
	private void prodLines() throws QueryException {
		lines = headerDAO.getLinesData(headerDTO.getTransId());
		leaseDTO.setLines(lines);
	}

	public LeaseDTO getForm() throws QueryException {
		AssetsOptProducer optProducer = new AssetsOptProducer(userAccount, conn);
		if ("".equals(leaseDTO.getHeaderDTO().getEmergentLevel())) {
			leaseDTO.getHeaderDTO().setEmergentLevel("0");
		}
		String emergentLevelOption = optProducer.getAmsEmergentLevel(headerDTO
				.getEmergentLevel());
		headerDTO.setEmergentLevelOption(emergentLevelOption);
		leaseDTO.setHeaderDTO(headerDTO);
		leaseDTO.setLines(lines);
		return leaseDTO;
	}

	public void setForm(LeaseDTO leaseDTO) {
		this.leaseDTO = leaseDTO;
		this.lines = leaseDTO.getLines();
		this.headerDTO = leaseDTO.getHeaderDTO();
		this.headerDAO = new LeaseDAO(userAccount, headerDTO, conn);
	}

    /**
     * ���ܣ�׼����������,��Ӧ��ʵ��
     */
    protected void prepareProcedureData(){
        flowDTO.setApp_dataID(headerDTO.getTransId()); // Ӧ��ID
        flowDTO.setPrimaryKey(headerDTO.getTransId()); // Ӧ��ID
        flowDTO.setOrderNo(headerDTO.getTransNo()); // Ӧ�õĵ��ݱ��
        flowDTO.setOrderName(LeaseAppConstant.PROC_APP_NAME); // Ӧ�õĵ��ݱ��
    }


	/**
	 * ��ʼ��
	 * 
	 * @param user
	 * @param dto
	 * @param conn
	 */
	private void init(SfUserDTO user, LeaseDTO dto, Connection conn) {
		this.leaseDTO = dto;
		this.lines = leaseDTO.getLines();
		this.headerDTO = leaseDTO.getHeaderDTO();
		this.headerDAO = new LeaseDAO(user, headerDTO, conn);
	}

	/**
	 * �������б���ĵ���ID���ý�DTO
	 * 
	 * @param dtoParameter
	 * @return
	 */
	private LeaseHeaderDTO setFlowIdToDTO(LeaseHeaderDTO dtoParameter) {
		if (StrUtil.isEmpty(dtoParameter.getTransId())) {
			dtoParameter.setTransId(StrUtil.nullToString(dtoParameter
					.getApp_dataID()));
		}
		return dtoParameter;
	}

	public File exportFile() throws DataTransException {
		return headerDAO.exportFile();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
