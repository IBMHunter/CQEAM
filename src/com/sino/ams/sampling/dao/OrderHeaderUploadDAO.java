package com.sino.ams.sampling.dao;

import java.sql.Connection;

import com.sino.ams.sampling.constant.SamplingDicts;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.model.SamplingOrderUploadModel;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class OrderHeaderUploadDAO extends AmsAssetsSamplingHeaderDAO {

	public OrderHeaderUploadDAO(BaseUserDTO userAccount, AmsAssetsSamplingHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)dtoParameter;
		sqlProducer = new SamplingOrderUploadModel(userAccount, dto);
	}


	/**
	 * ���ܣ��鵵����ͷ��Ϣ
	 * @throws DataHandleException
	 */
	public void uploadOrderHeader() throws DataHandleException {
		try {
			SamplingOrderUploadModel modelProducer = (SamplingOrderUploadModel) sqlProducer;
			AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) dtoParameter;
			dto.setOrderStatus(SamplingDicts.CHK_STATUS_UPLOADED);
			if (dto.getScanoverBy()==0) {
				dto.setScanoverBy(userAccount.getUserId());
				dto.setCurrCalendar("scanoverDate");
			}
			dto.setUploadBy(userAccount.getUserId());
			dto.setCurrCalendar("uploadDate");
			setDTOParameter(dto);
			SQLModel sqlModel = modelProducer.getOrderUploadModel(); //���µ���Ϊ������״̬
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}
}
