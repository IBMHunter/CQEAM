package com.sino.td.newasset.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sms.bean.MessageSaver;
import com.sino.sms.constant.SMSConstant;
import com.sino.sms.dto.SfMsgDefineDTO;
import com.sino.td.newasset.dto.TdAssetsTransHeaderDTO;
import com.sino.td.newasset.dto.TdAssetsTransLineDTO;
import com.sino.td.newasset.model.TdRcvLineModel;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class TdLineReceiveDAO extends AMSBaseDAO {
	private TdAssetsTransHeaderDTO headerDTO = null;

	/**
	 * ���ܣ��ʲ�ҵ��ͷ��(EAM) AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter TdAssetsTransLineDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public TdLineReceiveDAO(SfUserDTO userAccount, TdAssetsTransLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) dtoParameter;
		SfUserDTO user = (SfUserDTO) userAccount;
		sqlProducer = new TdRcvLineModel(user, dto);
	}

	public void setOrderHeader(TdAssetsTransHeaderDTO headerDTO) {
		this.headerDTO = headerDTO;
	}

	/**
	 * ���ܣ����շ����ʲ���
	 * @param assetLines DTOSet
	 * @throws DataHandleException
	 */
	public void rcvAssetLines(DTOSet assetLines) throws DataHandleException {
		TdRcvLineModel modelProducer = new TdRcvLineModel(userAccount, null);
		SQLModel sqlModel = null;
		for (int i = 0; i < assetLines.getSize(); i++) { //�����ʲ�
			TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO) assetLines.
										getDTO(i);
			dto.setTransId(headerDTO.getTransId());
			modelProducer.setDTOParameter(dto);
			sqlModel = modelProducer.getAssetsAssignModel(); //���µ�����
			DBOperator.updateRecord(sqlModel, conn);
		}
		try {
			assetLines = assetLines.getSubDTOSet("responsibilityUser");
			for (int i = 0; i < assetLines.getSize(); i++) { //�����ʲ�
				try {
					TdAssetsTransLineDTO dto = (TdAssetsTransLineDTO)
												assetLines.getDTO(i);
					saveMessage(dto);
				} catch (DataHandleException ex) { //���Ŵ洢ʧ�ܲ��׳��쳣������������һ���ʲ�
					ex.printLog();
				}
			}
		} catch (DTOException ex1) {
			ex1.printLog();
			throw new DataHandleException(ex1);
		}
	}

	/**
	 * ���ܣ������ʲ����ն���Ϣ����
	 * @param dto TdAssetsTransLineDTO
	 * @throws DataHandleException
	 */
	private void saveMessage(TdAssetsTransLineDTO dto) throws
			DataHandleException {
		try {
			MessageSaver msgSaver = new MessageSaver(conn);
			SfMsgDefineDTO msgDefineDTO = new SfMsgDefineDTO();
			SQLModel sqlModel = new SQLModel();
			List strArg = new ArrayList();
			String strSql = "SELECT"
							+ " SU.MOVETEL"
							+ " FROM SF_USER     SU,"
							+ " AMS_MIS_EMPLOYEE AME"
							+ " WHERE"
							+ " SU.EMPLOYEE_NUMBER = AME.EMPLOYEE_NUMBER"
							+ " AND AME.EMPLOYEE_ID = ?";
			strArg.add(dto.getResponsibilityUser());
			sqlModel.setSqlStr(strSql);
			sqlModel.setArgs(strArg);
			SimpleQuery sq = new SimpleQuery(sqlModel, conn);
			sq.executeQuery();
			if (sq.hasResult()) {
				String userTel = String.valueOf(sq.getFirstRow().getValue(
						"MOVETEL"));
				msgDefineDTO.setMsgCategoryId(SMSConstant.ASSET_CONFIRM_ID);
				msgDefineDTO.setCreatedBy(userAccount.getUserId());
				msgDefineDTO.setCellPhone(userTel);
				msgDefineDTO.setApplyNumber(dto.getLineId());
				msgDefineDTO.setMsgContent(dto.getResponsibilityUserName() +
										   "�����µ��ʲ���Ҫȷ�ϡ�");
				msgSaver.saveMsg(msgDefineDTO);
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}
}
