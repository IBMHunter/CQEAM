package com.sino.ams.newasset.dao;

import java.sql.Connection;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.newasset.dto.AmsAssetsChkLogDTO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.newasset.model.ArchiveLineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ArchiveLineDAO extends AMSBaseDAO {
	private DTOSet orderItems = null;
	private DTOSet locItems = null; //�����̵�ص��µ��豸(���ڹ鵵ʱʹ��)
	private AmsAssetsCheckLineDTO dto = null;
	private AmsAssetsCheckHeaderDTO headerDTO = null;

	private AmsItemInfoHistoryDAO historyDAO = null;
	private AmsAssetsChkLogDAO chkLogDAO = null; //���ڱ����ʲ��������̵�����

	public ArchiveLineDAO(SfUserDTO userAccount, AmsAssetsCheckLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		historyDAO = new AmsItemInfoHistoryDAO(userAccount, null, conn);
		chkLogDAO = new AmsAssetsChkLogDAO(userAccount, null, conn);
	}

	public void setOrderHeader(AmsAssetsCheckHeaderDTO headerDTO) {
		this.headerDTO = headerDTO;
		ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
		modelProducer.setOrderHeader(headerDTO);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		dto = (AmsAssetsCheckLineDTO) dtoParameter;
		sqlProducer = new ArchiveLineModel((SfUserDTO) userAccount, dto);
	}

	/**
	 * ���ܣ����Ǹ���Ĳ������÷���
	 * @param dtoParameter DTO
	 */
	public void setDTOParameter(DTO dtoParameter) {
		super.setDTOParameter(dtoParameter);
		this.dto = (AmsAssetsCheckLineDTO) dtoParameter;
	}

	/**
	 * ���ܣ����ô��̵��豸
	 * @param orderItems DTOSet
	 */
	public void setOrderItems(DTOSet orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * ���ܣ����ñ����̵�ص��µ��豸
	 * @param locItems DTOSet
	 */
	public void setLocItems(DTOSet locItems) {
		this.locItems = locItems;
	}

	/**
	 * ���ܣ��鵵�ʲ���
	 * @throws DataHandleException
	 */
	public void archiveChkLine() throws DataHandleException {
		try {
			String barcode = dto.getBarcode();
			String archiveStatus = dto.getArchiveStatus();
			String scanStatus = dto.getScanStatus();
			if(!scanStatus.equals(AssetsDictConstant.STATUS_YES)){
				scanStatus = AssetsDictConstant.STATUS_NO;
			}
			if (archiveStatus.equals(AssetsDictConstant.ARCHIVE_AS_SCAN)) { //��ɨ����Ϊ׼
				dto.setArchMaintainUser(dto.getScanMaintainUser());
				dto.setArchiveRemark(AssetsDictConstant.ARCHIVE_SCAN_REMARK);
				dto.setArchItemCode(dto.getScanItemCode());
				dto.setArchResponsibilityUser(dto.getScanResponsibilityUser());
				dto.setArchResponsibilityDept(dto.getScanResponsibilityDept());
				dto.setArchStartDate(dto.getScanStartDate().getCalendarValue());
				dto.setScanStatus(scanStatus);
				String scanItemCode = dto.getScanItemCode();
				if (scanStatus.equals(AssetsDictConstant.STATUS_NO)) { //PDAδɨ�赽���豸
					if (locItems.contains("barcode", barcode)) { //��ϵͳ��¼�豸�ڱ����̵�ص�
						locateItem2TmpInv(); //�ص�������;�⣬״̬��Ϊ��;
						dto.setArchToTempInv(AssetsDictConstant.STATUS_YES);
					} //��֮��������
				} else {
					dto.setArchToTempInv(AssetsDictConstant.STATUS_NO);
					if (scanItemCode.equals("")) { //����ӵ��豸����
						utilizeExistItemCode();
						if (dto.getScanItemCode().equals("")) {
							createSystemItem();
						} else {
							processItemDistribute();
						}
					}
//					if (orderItems.contains("barcode", barcode)) { //�豸��ԭ�ص㣬״̬��Ϊ����
//						updateItemProp();
//					} else {
						if (hasItemInDB()) { //���豸�����ݿ��д��ڣ��ڸõص㴴���µ��豸
                            updateItemProp();
						} else { //�����µ��豸
							dto.setRemark("�ʲ��̵���PDA�����µı�ǩ�š�");
                            dto.setFinanceProp(DictConstant.FIN_PROP_UNKNOW);
							createItem();
							String orderType = headerDTO.getOrderType();
							if (orderType.equals(AssetsDictConstant.INS_CHK)) { //�����Ǳ��̵�
								createInsItem();
							} else if (orderType.equals(AssetsDictConstant.RNT_CHK)) { //�����̵�
								createRentItem();
							}
						}
//					}
				}
				if (scanStatus.equals(AssetsDictConstant.STATUS_NO)) { //PDAδɨ�赽���豸
					if (locItems.contains("barcode", barcode)) { //��ϵͳ��¼�豸�ڱ����̵�ص�
						logItemHistory(); //�����豸�����ʷ
					}
				} else { //��������Ҫ��AMS_ITEM_INFO_HISTORY�������ʷ��¼
					logItemHistory();
				}
			} else {
				dto.setArchiveRemark(AssetsDictConstant.ARCHIVE_CURR_REMARK);
				dto.setArchMaintainUser(dto.getMaintainUser());
				dto.setArchItemCode(dto.getItemCode());
				dto.setArchResponsibilityUser(dto.getResponsibilityUser());
				dto.setArchResponsibilityDept(dto.getResponsibilityDept());
				dto.setArchToTempInv(AssetsDictConstant.STATUS_NO);
			}
			archiveOrderLine(); //�����й鵵
			if (scanStatus.equals(AssetsDictConstant.STATUS_NO)) { //PDAδɨ�赽���豸
				if (locItems.contains("barcode", barcode)) { //��ϵͳ��¼�豸�ڱ����̵�ص�
					recordChkLog(); //��¼�豸�����̵������Ϊ����ͬ����׼��
				}
			} else { //��������Ҫ��AMS_ASSETS_CHK_LOG������¼
				recordChkLog();
			}
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ����ñ��й鵵��Ϣ
	 * @throws DataHandleException
	 */
	private void archiveOrderLine() throws DataHandleException {
		try {
			ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getOrderLineArchiveModel();
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}


	/**
	 * ���ܣ����豸�������;��
	 * @throws DataHandleException
	 */
	private void locateItem2TmpInv() throws DataHandleException {
		String addressId = userAccount.getTmpAddressId();
		if (addressId.equals("")) { //δ������;��
			String errorMsg = userAccount.getCompany() + "δ������;�⣬���й鵵������ȫ��������";
			throw new DataHandleException(errorMsg);
		}
		ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getItem2TmpInvModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ��жϷ�������ڱ�ets_system_item�Ƿ����
	 * @throws QueryException
	 */
	private void utilizeExistItemCode() throws QueryException {
		ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getExistItemCatgoryModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsCheckLineDTO.class.getName());
		simp.executeQuery();
		if (simp.hasResult()) {
			AmsAssetsCheckLineDTO tmpDTO = (AmsAssetsCheckLineDTO) simp.getFirstDTO();
			dto.setScanItemCode(tmpDTO.getItemCode());
			setDTOParameter(dto);
		}
	}

	/**
	 * ���ܣ������µ��豸����
	 * @throws DataHandleException
	 */
	private void createSystemItem() throws DataHandleException {
		try {
			SeqProducer seqProducer = new SeqProducer(conn);
//			String itemCode = seqProducer.getStrNextSeq("ETS_SYSTEM_ITEM_S");
			//TODO
			String itemCode = "" + seqProducer.getGUID(); //.getStrNextSeq("ETS_SYSTEM_ITEM");
			dto.setScanItemCode(itemCode);
			dto.setRemark("�ʲ��̵���PDA�����µ��豸����");
			dto.setCurrCreationDate();
			setDTOParameter(dto);
			ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getSystemItemCreateModel();
			DBOperator.updateRecord(sqlModel, conn);
			sqlModel = modelProducer.getItemDistributeModel();
			DBOperator.updateRecord(sqlModel, conn);
			sqlModel = modelProducer.getItemDisApplyModel();
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ������豸�������
	 * @throws DataHandleException
	 */
	private void processItemDistribute() throws DataHandleException {
		try {
			ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getHasItemDistributeModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (!simp.hasResult()) { //���û�з��䣬�򴴽��µ���ʱ����
				sqlModel = modelProducer.getItemDistributeModel();
				DBOperator.updateRecord(sqlModel, conn);
			}
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ������豸�������ԡ�
	 * ������ԭ�ص����и��豸�����
	 * @throws DataHandleException
	 */
	private void updateItemProp() throws DataHandleException {
		try {
			ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getItemPropUpdateModel();
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ������豸�ص�Ϊ��ǰ�̵㵥�ص㡣
	 * ������ԭ�ص���û�и��豸�����
	 * @throws DataHandleException
	 */
	private void updateItemAddress() throws DataHandleException {
		try {
			ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getItemAddressUpdateModel();
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ������µ��豸
	 * @throws DataHandleException
	 */
	private void createItem() throws DataHandleException {
		try {
			ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getItemCreateModel();
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ����:��¼����䶯��ʷ
	 * @throws DataHandleException
	 */
	private void logItemHistory() throws DataHandleException {
		AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();
		if (dto.getArchToTempInv().equals(AssetsDictConstant.STATUS_YES)) {
			historyDTO.setAddressId( userAccount.getTmpAddressId() );
		}
		AmsAssetsCheckLineDTO oriItem = (AmsAssetsCheckLineDTO) orderItems.getDTO("barcode", dto.getBarcode());
		if (oriItem == null) { //����Ϊ�¼�������
			historyDTO.setItemCode(dto.getScanItemCode());
			historyDTO.setResponsibilityUser(dto.getScanResponsibilityUser());
			historyDTO.setResponsibilityDept(dto.getScanResponsibilityDept());
			historyDTO.setAddressId(dto.getAddressId());
		} else { //ԭ���ص����и�����
			if (!oriItem.getItemCode().equals(dto.getScanItemCode())) {
				historyDTO.setItemCode(dto.getScanItemCode());
			}
			if ( oriItem.getResponsibilityUser() != dto.getScanResponsibilityUser() ) {
				historyDTO.setResponsibilityUser(dto.getScanResponsibilityUser());
			}
			if (!oriItem.getResponsibilityDept().equals(dto.getScanResponsibilityDept())) {
				historyDTO.setResponsibilityDept(dto.getScanResponsibilityDept());
			}
		}
		if (historyDTO.needLogHistory()) {
			historyDTO.setBarcode(dto.getBarcode());
			historyDTO.setOrderNo(headerDTO.getTransNo());
			historyDTO.setOrderCategory(AssetsDictConstant.LOG_ORDER_ASSETS);
			String orderUrl = AssetsURLList.ARCHIVE_ORDER_SERVLET;
			orderUrl += "?act=" + AssetsActionConstant.DETAIL_ACTION;
			orderUrl += "&headerId=" + dto.getHeaderId();
			historyDTO.setOrderDtlUrl(orderUrl);
			historyDTO.setCreatedBy(userAccount.getUserId());
			historyDAO.setDTOParameter(historyDTO);
			historyDAO.recordHistory();
		}
	}

	/**
	 * ���ܣ��жϸ������Ƿ���������ݿ���
	 * @return boolean
	 * @throws QueryException
	 */
	private boolean hasItemInDB() throws QueryException {
		ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getHasItemInDBModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		return (simp.hasResult());
	}


	/**
	 * ���ܣ���¼�豸����һ���̵����
	 * @throws DataHandleException
	 */
	private void recordChkLog() throws DataHandleException {
		AmsAssetsChkLogDTO chkLogDTO = new AmsAssetsChkLogDTO();
		chkLogDTO.setBarcode(dto.getBarcode());
		chkLogDTO.setLastChkNo(headerDTO.getTransNo());
		chkLogDTO.setItemCode(dto.getArchItemCode());
		chkLogDTO.setResponsibilityUser(dto.getArchResponsibilityUser());
		chkLogDTO.setResponsibilityDept(dto.getArchResponsibilityDept());
		chkLogDTO.setAddressId(dto.getAddressId());
		String orderUrl = AssetsURLList.ARCHIVE_ORDER_SERVLET;
		orderUrl += "?act=" + AssetsActionConstant.DETAIL_ACTION;
		orderUrl += "&headerId=" + dto.getHeaderId();
		chkLogDTO.setOrderDtlUrl(orderUrl);
		chkLogDTO.setBatchId(headerDTO.getBatchId());
		chkLogDTO.setHeaderId(headerDTO.getHeaderId());
		chkLogDTO.setOrganizationId(userAccount.getOrganizationId());
		chkLogDTO.setOrderType(AssetsDictConstant.ASS_CHK);
		String archiveStatus = dto.getArchiveStatus();
		if (archiveStatus.equals(AssetsDictConstant.ARCHIVE_AS_CURR)) { //��Ŀǰ״̬Ϊ׼
			chkLogDTO.setIsExist(dto.getSystemStatus());
		} else {
			chkLogDTO.setIsExist(dto.getScanStatus());
		}
		chkLogDAO.setDTOParameter(chkLogDTO);
		chkLogDAO.saveCheckLogData();
	}

	/**
	 * ���ܣ����������Ǳ��豸
	 * @throws DataHandleException
	 */
	private void createInsItem() throws DataHandleException {
		ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getInsItemCreateModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ����������豸
	 * @throws DataHandleException
	 */
	private void createRentItem() throws DataHandleException {
		ArchiveLineModel modelProducer = (ArchiveLineModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getRentItemCreateModel();
		DBOperator.updateRecord(sqlModel, conn);
	}
}
