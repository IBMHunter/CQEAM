package com.sino.ams.newasset.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsRcvHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.model.AssetsRcvHeaderModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AssetsReceiveDAO extends AMSBaseDAO {
	private String rcvHeaderId = "";

	/**
	 * ���ܣ��ʲ�ҵ��ͷ��(EAM) AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AssetsReceiveDAO(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 *
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
		sqlProducer = new AssetsRcvHeaderModel((SfUserDTO) userAccount, dto);
	}

	/**
	 * ���ܣ�����������ʲ��������պͷ���
	 * @param dtos DTOSet
	 * @return boolean
	 */
	public boolean assignTransAssets(DTOSet dtos) {
		boolean operateResult = false;
		boolean autoCommit = true;
		AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
		boolean rcvProcEnabled = servletConfig.isRcvProcEnabled();
		String transferType = headerDTO.getTransferType();
		try {
			if (canAssignOrder()) {
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				AssetsLineReceiveDAO rcvLineDAO = new AssetsLineReceiveDAO(userAccount, null, conn);
				rcvLineDAO.setOrderHeader(headerDTO);
				rcvLineDAO.rcvAssetLines(dtos);
				AssetsRcvHeaderModel modelProducer = (AssetsRcvHeaderModel) sqlProducer;
				SQLModel sqlModel = modelProducer.getAssetsAssignModel();
				DBOperator.updateRecord(sqlModel, conn); //���շ����ʲ������ĵ���״̬
				if (rcvProcEnabled & transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)) {
					//�Զ����ɵ������յ���������������������������
					AmsAssetsRcvHeaderDAO rcvHeaderDAO = new AmsAssetsRcvHeaderDAO(userAccount, null, conn);
					rcvHeaderDAO.autoCreateRcvDatas(headerDTO, dtos);
					AmsAssetsRcvHeaderDTO rcvHeader = (AmsAssetsRcvHeaderDTO)rcvHeaderDAO.getDTOParameter();
					rcvHeaderId = rcvHeader.getReceiveHeaderId();
				}
				operateResult = true;
			} else {
				prodMessage(AssetsMessageKeys.RCV_INVALID);
			}
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (QueryException ex) {
			ex.printLog();
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					conn.rollback();
					prodMessage(AssetsMessageKeys.RCV_ASSETS_FAILURE);
				} else {
					conn.commit();
					if (rcvProcEnabled) {
						if(transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)){
							this.message.setMessageValue("������ʲ��������շ���ɹ���");
						} else {
							prodMessage(AssetsMessageKeys.RCV_AUTO_CREATE);
						}
					} else {
						prodMessage(AssetsMessageKeys.RCV_ASSETS_SUCCESS);
					}
				}
				message.setIsError(!operateResult);
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
				prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ��жϸõ����Ƿ��ܹ�����
	 * @return boolean
	 * @throws QueryException
	 */
	private boolean canAssignOrder() throws QueryException {
		AssetsRcvHeaderModel modelProducer = (AssetsRcvHeaderModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getCanAssignModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		return!simp.hasResult();
	}

	/**
	 * ���ܣ��жϵ�ǰ�û��Ƿ��ܹ�����ָ������
	 * @return boolean
	 * @throws QueryException
	 */
	public boolean canReceiveOrder() throws QueryException {
		boolean canReceive = true;
		try {
			AssetsRcvHeaderModel modelProducer = (AssetsRcvHeaderModel)
												 sqlProducer;
			SQLModel sqlModel = modelProducer.getCanReceiveModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				Row row = simp.getFirstRow();
				canReceive = (Integer.parseInt(row.getStrValue("NOT_ASSIGNED_NUMBER")) > 0);
			}
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new QueryException(ex);
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		} catch (NumberFormatException ex) {
			Logger.logError(ex);
			throw new QueryException(ex);
		}
		return canReceive;
	}

	/**
	 * ���ܣ���ȡ���ν���ʱ���ɵĵ������յ�ͷID(����Ҫ�������������������̵�����²��д�ֵ)
	 * @return String
	 */
	public String getRcvHeaderId() {
		return rcvHeaderId;
	}
}
