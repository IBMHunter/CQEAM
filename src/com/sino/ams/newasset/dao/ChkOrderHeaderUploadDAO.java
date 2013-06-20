package com.sino.ams.newasset.dao;

import java.sql.Connection;

import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.model.ChkOrderUpLoadModel;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ChkOrderHeaderUploadDAO extends AmsAssetsCheckHeaderDAO {

	public ChkOrderHeaderUploadDAO(BaseUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)dtoParameter;
		sqlProducer = new ChkOrderUpLoadModel(userAccount, dto);
	}

	/**
	 * ���ܣ����µ���ͷ״̬Ϊ�����ء�
	 * @throws DataHandleException
	 */
	public void uploadOrderHeader() throws DataHandleException{
		try {
			AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) dtoParameter;
			ChkOrderUpLoadModel modelProducer = (ChkOrderUpLoadModel) sqlProducer;
			dto.setOrderStatus(AssetsDictConstant.CHK_STATUS_UPLOADED);
			dto.setScanoverBy(userAccount.getUserId());
			dto.setUploadBy(userAccount.getUserId());
			dto.setCurrCalendar("uploadDate");
			modelProducer.setDTOParameter(dto);
			SQLModel sqlModel = modelProducer.getUploadChkOrdersModel();
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}
}
